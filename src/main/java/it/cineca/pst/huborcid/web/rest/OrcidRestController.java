/**
 * This file is part of huborcid.
 *
 * huborcid is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * huborcid is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with huborcid.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.cineca.pst.huborcid.web.rest;

import it.cineca.pst.huborcid.domain.Application;
import it.cineca.pst.huborcid.domain.Person;
import it.cineca.pst.huborcid.domain.RelPersonApplication;
import it.cineca.pst.huborcid.domain.Token;
import it.cineca.pst.huborcid.orcid.client.OrcidAccessToken;
import it.cineca.pst.huborcid.orcid.client.OrcidApiType;
import it.cineca.pst.huborcid.orcid.client.OrcidAuthScope;
import it.cineca.pst.huborcid.orcid.client.OrcidOAuthClient;
import it.cineca.pst.huborcid.repository.ApplicationRepository;
import it.cineca.pst.huborcid.repository.PersonRepository;
import it.cineca.pst.huborcid.repository.RelPersonApplicationRepository;
import it.cineca.pst.huborcid.repository.TokenRepository;
import it.cineca.pst.huborcid.security.SecurityUtils;
import it.cineca.pst.huborcid.service.OrcidService;
import it.cineca.pst.huborcid.web.rest.dto.ApplicationMinDTO;
import it.cineca.pst.huborcid.web.rest.dto.DeleteUserIdResponseDTO;
import it.cineca.pst.huborcid.web.rest.dto.GetLandingPageResponseDTO;
import it.cineca.pst.huborcid.web.rest.dto.GetTicketRequestDTO;
import it.cineca.pst.huborcid.web.rest.dto.GetTicketResponseDTO;
import it.cineca.pst.huborcid.web.rest.dto.GetUserIdResponseDTO;
import it.cineca.pst.huborcid.web.rest.exception.ApplicationIdMissingException;
import it.cineca.pst.huborcid.web.rest.exception.ApplicationNotFoundException;
import it.cineca.pst.huborcid.web.rest.exception.ApplicationlIDDifferentException;
import it.cineca.pst.huborcid.web.rest.exception.LocalIDDifferentException;
import it.cineca.pst.huborcid.web.rest.exception.LocalIdMissingException;
import it.cineca.pst.huborcid.web.rest.exception.OrcidDeniedForApplicationException;
import it.cineca.pst.huborcid.web.rest.exception.OrcidForUserMissingException;
import it.cineca.pst.huborcid.web.rest.exception.OrgIdIsMissingException;
import it.cineca.pst.huborcid.web.rest.exception.OrgIdIsOnlyForPublicAppException;
import it.cineca.pst.huborcid.web.rest.exception.RelPersonApplicationNotFoundException;
import it.cineca.pst.huborcid.web.rest.exception.TokenAlreadyUsedException;
import it.cineca.pst.huborcid.web.rest.exception.TokenNotFoundException;
import it.cineca.pst.huborcid.web.rest.util.ApplicationMapper;
import it.cineca.pst.huborcid.web.rest.util.ResultCode;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




import com.codahale.metrics.annotation.Timed;


@RestController
public class OrcidRestController {
	
	@Inject
    private PersonRepository personRepository;
	
    @Inject
    private ApplicationRepository applicationRepository;
    
    @Inject
    private RelPersonApplicationRepository relPersonApplicationRepository;
    
    @Inject
    private TokenRepository tokenRepository;
    
    @Inject
    private OrcidService orcidService;
    
    @Autowired
    private Environment env;
    
	private final Logger log = LoggerFactory.getLogger(OrcidRestController.class);
	
	private SecureRandom random =  new SecureRandom();
	
	private OrcidApiType orcidApiType;
	
    public OrcidRestController() {
		super();
	}
    
    @PostConstruct
    public void settingEnv() {
		if(env.acceptsProfiles("prod")){
			orcidApiType = OrcidApiType.LIVE;
		}else{
			orcidApiType = OrcidApiType.SANDBOX;
		}
    }



	@RequestMapping(value = "/user/{LOCALID}/ticket",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public  ResponseEntity<GetTicketResponseDTO> getTicket(@RequestBody GetTicketRequestDTO jsonGetTicket,
    		@PathVariable("LOCALID") String localID 
    		) throws ApplicationNotFoundException, LocalIDDifferentException, LocalIdMissingException, ApplicationIdMissingException, ApplicationlIDDifferentException, OrgIdIsOnlyForPublicAppException, OrgIdIsMissingException {
    	log.debug("REST GETTICKET START. localid [{}], appid [{}]",localID,jsonGetTicket.getAppId());
        

        checkGetTicketInput(localID,jsonGetTicket);
        
        Application application = applicationRepository.findOneByApplicationID(jsonGetTicket.getAppId());
        if(application==null){
        	throw new ApplicationNotFoundException(jsonGetTicket.getAppId());
        }
        
        Person person = personRepository.findOneByLocalID(localID);
        if(person==null){
        	//create new person
        	person = new Person();
        	person.setLocalID(localID);
        }
        //update persona name surname mail
        if(jsonGetTicket.getFirstname() != null && !jsonGetTicket.getFirstname().isEmpty())
        	person.setFirstName(jsonGetTicket.getFirstname());
        if(jsonGetTicket.getLastname() != null && !jsonGetTicket.getLastname().isEmpty())
        	person.setLastName(jsonGetTicket.getLastname());
        if(jsonGetTicket.getMail() != null && !jsonGetTicket.getMail().isEmpty())
        	person.setEmail(jsonGetTicket.getMail());
        personRepository.save(person);
        
        if((jsonGetTicket.getOrgId()!=null)&&(!jsonGetTicket.getOrgId().isEmpty())&&(!application.getAllOrg())){
        	//if app isn't for all org must not specify org-id
        	throw new OrgIdIsOnlyForPublicAppException(application.getApplicationID());
        }else if(application.getAllOrg()&&((jsonGetTicket.getOrgId()==null)||(jsonGetTicket.getOrgId().isEmpty()))){
        	//if app is for all org must specify org-id
        	throw new OrgIdIsMissingException(application.getApplicationID());
        }
        
        //create token
        Token token = new Token();
        token.setApplication(application);
        token.setPerson(person);
        token.setOrgUnit(jsonGetTicket.getOrgId());
        token.setUrlCallback(jsonGetTicket.getUrlCallback());
        token.setDateReleased(DateTime.now());
        tokenRepository.save(token);
        token.setOtt(generateTokenData(token.getId().toString()));
        tokenRepository.save(token);
        
        //search if person-app have an access-token
        RelPersonApplication relPersonApplication = relPersonApplicationRepository.findOneByPersonIsAndApplicationIsAndValidIsTrue(person, application);
        String orcid = person.getOrcid();
        String apiKey = null;
        
        GetTicketResponseDTO response = new GetTicketResponseDTO();
        response.setToken(token.getOtt());
        if((relPersonApplication!=null)&&((relPersonApplication.getDenied()==null)||(relPersonApplication.getDenied()==false))){
        	apiKey = relPersonApplication.getOauthAccessToken();
        	response.setOrcidAccessToken(apiKey);
        }
        response.setOrcid(person.getOrcid());
        if((orcid!=null)&&(apiKey!=null))
        	response.setResultCode(ResultCode.SUCCESS.getCode());
        else
        	response.setResultCode(ResultCode.SUCCESS_ALREADY_EXISTS.getCode());
        
        log.info("REST GETTICKET NEW TICKET.appid [{}], localid [{}], token [{}], resultCode [{}]",jsonGetTicket.getAppId(),localID,token.getOtt(),response.getResultCode());
        log.debug("REST GETTICKET END. localid [{}], token [{}], resultCode [{}]",localID,token.getOtt(),response.getResultCode());
        return new ResponseEntity<GetTicketResponseDTO>(response,HttpStatus.OK);
    }
    
	@RequestMapping(value = "/user/{LOCALID}/delete",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public  ResponseEntity<DeleteUserIdResponseDTO> deleteUser(
    		@PathVariable("LOCALID") String localID ) throws ApplicationNotFoundException, LocalIdMissingException{
		log.debug("REST DELETE_ID START. localid [{}]",localID);
		String currentLogin =SecurityUtils.getCurrentLogin();
        Application application = applicationRepository.findOneByApplicationID(currentLogin);
        if(application==null){
        	throw new ApplicationNotFoundException(currentLogin);
        }
        
        Person person = personRepository.findOneByLocalID(localID);
        if(person==null){
        	throw new LocalIdMissingException();
        }
        
        orcidService.deleteUser(person);
		
        DeleteUserIdResponseDTO response = new DeleteUserIdResponseDTO();
        response.setResultCode(ResultCode.SUCCESS.getCode());
        return new ResponseEntity<DeleteUserIdResponseDTO>(response,HttpStatus.OK);
	}



	@RequestMapping(value = "/user/id/{TOKEN}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public  GetUserIdResponseDTO getUserId(@PathVariable("TOKEN") String tokenString) throws TokenNotFoundException, OrcidForUserMissingException, ApplicationlIDDifferentException, OrcidDeniedForApplicationException {
		log.debug("REST GET-USER-ID START. token [{}]",tokenString);
		
        Token token = tokenRepository.findOneByOtt(tokenString);
        if(token==null){
        	throw new TokenNotFoundException(tokenString);
        }
        
    	String currentLogin = SecurityUtils.getCurrentLogin();
    	if(!currentLogin.equals(token.getApplication().getApplicationID())){
    		throw new ApplicationlIDDifferentException(currentLogin,token.getApplication().getApplicationID());
    	}
        
        RelPersonApplication relPersonApplication = relPersonApplicationRepository.findOneByPersonIsAndApplicationIsAndTokenIs(token.getPerson(), token.getApplication(), token);
        if(token.getPerson().getOrcid()==null){
	        if(relPersonApplication==null){
	        	throw new OrcidForUserMissingException(token.getPerson().getLocalID());
	        }else if(relPersonApplication.getDenied()){
	        	throw new OrcidDeniedForApplicationException(token.getPerson().getLocalID());
	        }else if(!relPersonApplication.getValid()){
	        	throw new OrcidForUserMissingException(token.getPerson().getLocalID());
	        }
		}
        
        GetUserIdResponseDTO response = new GetUserIdResponseDTO();
        if(relPersonApplication!=null)
        	response.setOrcidAccessToken(relPersonApplication.getOauthAccessToken());
        response.setOrcid(token.getPerson().getOrcid());
        response.setResultCode("001");
        
        log.debug("REST GET-USER-ID END. token [{}], orcid [{}]",tokenString,token.getPerson().getOrcid());
        return response;
    }
    
    @RequestMapping(value = "/oauth/{TOKEN}",
            method = RequestMethod.GET)
    @Timed
    public  void oauthUser(@PathVariable("TOKEN") String ott,
    		HttpServletRequest request,
    		HttpServletResponse response) throws IOException, JAXBException, TokenNotFoundException, TokenAlreadyUsedException {
    	log.debug("REST OAUTH START. token [{}]",ott);
        
        Token token = tokenRepository.findOneByOtt(ott);
        if(token == null){
        	throw new TokenNotFoundException(ott);
        }
        //if( token.getDateUsed() !=null){
        //	throw new TokenAlreadyUsedException(ott);
        //}
        
        Person person = token.getPerson();
        Application application = token.getApplication();
        
        //qual è l'organizzazione dell'utente?
        String orgUnit = null;
        if(application.getAllOrg())
        	orgUnit = token.getOrgUnit();
        else
        	orgUnit = application.getOrgUnit();
        
        List<Application> applicationForUser = applicationRepository.findAllByOrgUnitOrAllOrgIsTrue(orgUnit);
        
        List<RelPersonApplication> listApplicationAuth = relPersonApplicationRepository.findAllByPersonIsAndApplicationIn(person, applicationForUser);
        
        //Set old application access key invalid
        for(int i=0;i<listApplicationAuth.size();i++){
        	RelPersonApplication applicationAuthorize = listApplicationAuth.get(i);
        	applicationAuthorize.setValid(false);
        	relPersonApplicationRepository.save(applicationAuthorize);
        }  
        
        //token.setDateUsed(DateTime.now());
        //tokenRepository.save(token);
        
        Application applicationAuthorize = null;
        RelPersonApplication relPersonApplication = null;
       
        //Create new access key record
        for(int i=0;i<applicationForUser.size();i++){
        	applicationAuthorize = applicationForUser.get(i);
        	relPersonApplication = new RelPersonApplication();
        	relPersonApplication.setApplication(applicationAuthorize);
        	relPersonApplication.setPerson(person);
        	relPersonApplication.setToken(token);
        	relPersonApplication.setValid(null);
        	
        	relPersonApplicationRepository.save(relPersonApplication);
        }  
        
        String urlToRedirect = getLandingPageURL(request,ott);

		log.info("REST OAUTH REDIRECT TO APP. listApp [{}], appId [{}], token [{}], urlRedirect [{}]",applicationForUser.size(),applicationAuthorize.getApplicationID(),ott,urlToRedirect);
		log.debug("REST OAUTH FINISH. token [{}], urlToRedirect [{}]",ott,urlToRedirect);
        
        response.sendRedirect(urlToRedirect);
        return;
    }
    
    @RequestMapping(value = "/oauth/apps/{TOKEN}",
            method = RequestMethod.GET)
    @Timed
    public  GetLandingPageResponseDTO oauthAppForUser(@PathVariable("TOKEN") String ott,
    		HttpServletRequest request) throws IOException, JAXBException, TokenNotFoundException, TokenAlreadyUsedException {
    	GetLandingPageResponseDTO response = new GetLandingPageResponseDTO();
    	log.debug("REST OAUTH APPS START. token [{}]",ott);
    	
        Token token = tokenRepository.findOneByOtt(ott);
        if(token == null){
        	throw new TokenNotFoundException(ott);
        }
        
        Person person = token.getPerson();
        Application application = token.getApplication();
        
        log.debug("REST OAUTH APPS START. person [{}], localid [{}]",person,person.getLocalID());
        
        //qual è l'organizzazione dell'utente?
        String orgUnit = null;
        if(application.getAllOrg())
        	orgUnit = token.getOrgUnit();
        else
        	orgUnit = application.getOrgUnit();
        
        List<RelPersonApplication> listApplicationAuth = relPersonApplicationRepository.findAllByTokenIsAndValidIsNull(token);
        String urlRegisterOrcid = null;
        String urlLoginOrcid = null;
        if(listApplicationAuth.size()>0){
        	Application applicationAuthorize = listApplicationAuth.get(0).getApplication();
        	
	        String callBackUrl = getCallbackOrcidURL(request);
	        OrcidOAuthClient clientOrcid = new OrcidOAuthClient(applicationAuthorize.getApplicationID(),
	        		applicationAuthorize.getApplicationSecret(), callBackUrl,orcidApiType);
	        
	        List<OrcidAuthScope> orcidScopes = clientOrcid.getListAllScope();
	        urlRegisterOrcid = clientOrcid.getAuthzCodeRegisterRequest(listApplicationAuth.get(0).getId().toString(),orcidScopes,person.getFirstName(),person.getLastName(),person.getEmail());
	        urlLoginOrcid = clientOrcid.getAuthzCodeLoginRequest(listApplicationAuth.get(0).getId().toString(),orcidScopes,person.getFirstName(),person.getLastName(),person.getEmail());;
        }
        String urlHelp = null;
        if((application.getHelpURL()!=null)&&(!application.getHelpURL().isEmpty())){
        	urlHelp = application.getHelpURL();
        }else if((application.getHelpMail()!=null)&&(!application.getHelpMail().isEmpty())){
        	urlHelp = "mailto:"+application.getHelpMail()+"?Subject=ORCID Support "+person.getLocalID();
        }
        
        
        List<Application> applicationForUser = applicationRepository.findAllByOrgUnitOrAllOrgIsTrue(orgUnit);
        response.setFirstname(person.getFirstName());
        response.setLastname(person.getLastName());
        response.setUrlRegisterOrcid(urlRegisterOrcid);
        response.setUrlLoginOrcid(urlLoginOrcid);
        response.setUrlHelp(urlHelp);
        response.setListApp(ApplicationMapper.from(applicationForUser));
        
        return response;
    }
    
    @RequestMapping(value = "/oauth/finish",
            method = RequestMethod.GET)
    @Timed
    public  void oauthUserFinish(HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam(value="code",required = false) String code,
    		@RequestParam(value="state",required = false) String state,
    		@RequestParam(value="error",required = false) String error,
    		@RequestParam(value="error_description",required = false) String errorDescription) throws IOException, JAXBException, RelPersonApplicationNotFoundException, InterruptedException {
    	
    	log.debug("REST OAUTHFINISH START. code [{}],state/relId [{}],error [{}], error_description [{}]",code,state,error,errorDescription);
        
        RelPersonApplication relPersonApplication = relPersonApplicationRepository.findOne(new Long(state));
        if((relPersonApplication==null)||(relPersonApplication.getValid()!=null)){
        	String relPersonApplicationId = "-1";
        	if(relPersonApplication!=null)
        		relPersonApplicationId = relPersonApplication.getId().toString();
        	throw new RelPersonApplicationNotFoundException(relPersonApplicationId);
        }
        
        Application application = relPersonApplication.getApplication();
    	Person person = relPersonApplication.getPerson();
        Token token = relPersonApplication.getToken();
        
        //invalido token
        if( token.getDateUsed() ==null){
            token.setDateUsed(DateTime.now());
            tokenRepository.save(token);
        }

        if(error!=null){
        	log.info("REST OAUTHFINISH {}. localId [{}],appId [{}],error [{}], error_description [{}]",error,person.getLocalID(),application.getApplicationID(),error,errorDescription);
        	if(error.equals("access_denied")){
        		relPersonApplication.setDenied(true);
        		relPersonApplication.setDateDenied(DateTime.now());
        		relPersonApplication.setValid(true);
        	}else{
        		relPersonApplication.setValid(false);
        	}
        	relPersonApplication.setErrorDescription(error);
        	relPersonApplication.setDateReleased(DateTime.now());
        	relPersonApplicationRepository.save(relPersonApplication);
        	
        }else{

        	String callBackUrl = getCallbackOrcidURL(request);
        	OrcidOAuthClient clientOrcid = new OrcidOAuthClient(application.getApplicationID(),
        			application.getApplicationSecret(), callBackUrl,orcidApiType);
        	log.info("REST OAUTHFINISH REQUEST ACCESSTOKEN. localId [{}],appId [{}], appSecret [{}]. orcidApiType [{}]",person.getLocalID(),application.getApplicationID(),application.getApplicationSecret(),orcidApiType);
        	OrcidAccessToken tokenOrcid = clientOrcid.getAccessToken(code);
        	log.info("REST OAUTHFINISH ACCESSTOKEN RELEASED. localId [{}],appId [{}],accessToken [{}], orcid [{}]",person.getLocalID(),application.getApplicationID(),tokenOrcid.getAccess_token(),tokenOrcid.getOrcid());
        	relPersonApplication.setDateReleased(DateTime.now());
        	relPersonApplication.setOauthAccessToken(tokenOrcid.getAccess_token());
        	relPersonApplication.setValid(true);
        	relPersonApplication.setDenied(false);
        	person.setOrcid(tokenOrcid.getOrcid());
        	person.setOrcidReleaseDate(DateTime.now());
        	relPersonApplicationRepository.save(relPersonApplication);
        	personRepository.save(person);
        	
        	//async
        	orcidService.sendNotify(application,relPersonApplication);
        }
        
        List<RelPersonApplication> listApplicationAuth = relPersonApplicationRepository.findAllByTokenIsAndValidIsNull(token);
        
        if(listApplicationAuth.size()>0){
        	Application applicationAuthorize = listApplicationAuth.get(0).getApplication();
        	
	        String callBackUrl = getCallbackOrcidURL(request);
	        OrcidOAuthClient clientOrcid = new OrcidOAuthClient(applicationAuthorize.getApplicationID(),
	        		applicationAuthorize.getApplicationSecret(), callBackUrl,orcidApiType);
	        
	        List<OrcidAuthScope> orcidScopes = clientOrcid.getListAllScope();
			String urlToRedirect = clientOrcid.getAuthzCodeRegisterRequest(listApplicationAuth.get(0).getId().toString(),orcidScopes,person.getFirstName(),person.getLastName(),person.getEmail());
	        
			log.debug("REST OAUTHFINISH REDIRECT TO APP. listApp [{}], appId [{}], token [{}], urlRedirect [{}]",listApplicationAuth.size(),applicationAuthorize.getApplicationID(),listApplicationAuth.get(0).getToken().getOtt(),urlToRedirect);
	        response.sendRedirect(urlToRedirect);
        }else{
        	String urlToRedirect = token.getUrlCallback();
        	if((urlToRedirect==null)||(urlToRedirect.isEmpty())){
	        	Application appRequest = token.getApplication();
	        	urlToRedirect = appRequest.getUrlCallback();
        	}
        	if(urlToRedirect.contains("?"))
        		urlToRedirect = urlToRedirect + "&";
        	else
        		urlToRedirect = urlToRedirect + "?";
        	urlToRedirect = urlToRedirect + "ott="+token.getOtt();
        	RelPersonApplication resultRel = relPersonApplicationRepository.findOneByPersonIsAndApplicationIsAndTokenIs(token.getPerson(), token.getApplication(), token);
        	String resultCode = null;
        	if((resultRel!=null)&&(resultRel.getValid())&&((resultRel.getDenied()==null)||(resultRel.getDenied()==false))){
        		resultCode = "001";
        	}else if(resultRel.getDenied()){
        		resultCode = ResultCode.ERROR_USER_DENIED.getCode();
        	}else{
        		resultCode = ResultCode.ERROR_ORCID_FOR_USER_MISSING.getCode();
        	}
        	urlToRedirect = urlToRedirect + "&result-code="+resultCode;
        	log.debug("REST OAUTHFINISH FINISH. token [{}], urlToRedirect: [{}], resultCode [{}]",token.getOtt(),urlToRedirect,resultCode);
        	response.sendRedirect(urlToRedirect);
        }
        return;
    }


    private String generateTokenData(String id) {
        String chars = ""
                     + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                     + "0123456789"
                     + "";

        final int PW_LENGTH = 16;
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < PW_LENGTH; i++)
            pass.append(chars.charAt(random.nextInt(chars.length())));
        String token = pass.toString()+ "_"+id;
        return token;
    }
    
    private String getLandingPageURL(HttpServletRequest request,String ott) throws MalformedURLException {
    	//log.debug("REST OAUTH FINISH REQUEST. getScheme [{}], getServerName [{}], getServerPort [{}], getContextPath [{}]",request.getScheme(),request.getServerName(),request.getServerPort(), request.getContextPath());
    	
    	int port = request.getServerPort();

    	if (request.getScheme().equals("http") && port == 80) {
    	    port = -1;
    	} else if (request.getScheme().equals("https") && port == 443) {
    	    port = -1;
    	}
    	
    	String path=request.getContextPath()+"/orcid#/oauth/"+ott;

    	URL serverURL = new URL("https"/*request.getScheme()*/, request.getServerName(), port, path);
    	
    	return serverURL.toString();
    }
    
    private String getCallbackOrcidURL(HttpServletRequest request) throws MalformedURLException {
    	//log.debug("REST OAUTH FINISH REQUEST. getScheme [{}], getServerName [{}], getServerPort [{}], getContextPath [{}]",request.getScheme(),request.getServerName(),request.getServerPort(), request.getContextPath());
    	
    	int port = request.getServerPort();

    	if (request.getScheme().equals("http") && port == 80) {
    	    port = -1;
    	} else if (request.getScheme().equals("https") && port == 443) {
    	    port = -1;
    	}
    	
    	String path=request.getContextPath()+"/oauth/finish";

    	URL serverURL = new URL("https"/*request.getScheme()*/, request.getServerName(), port, path);
    	
    	return serverURL.toString();
    }
    
    private void checkGetTicketInput(String localID,
			GetTicketRequestDTO jsonGetTicket) throws LocalIDDifferentException, LocalIdMissingException, ApplicationIdMissingException, ApplicationlIDDifferentException {
    	String currentLogin = SecurityUtils.getCurrentLogin();
    	if(!currentLogin.equals(jsonGetTicket.getAppId())){
    		throw new ApplicationlIDDifferentException(currentLogin,jsonGetTicket.getAppId());
    	}
    	
        if(!localID.equals(jsonGetTicket.getLocalID()))
        	throw new LocalIDDifferentException(jsonGetTicket.getLocalID(), localID);
        
        if(jsonGetTicket.getLocalID() == null || jsonGetTicket.getLocalID().isEmpty())
        	throw new LocalIdMissingException();
        
        if(jsonGetTicket.getAppId() == null || jsonGetTicket.getAppId().isEmpty())
        	throw new ApplicationIdMissingException();
		
	}
    
    

	
	

}
