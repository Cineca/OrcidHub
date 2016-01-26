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
package it.cineca.pst.huborcid.service;

import it.cineca.pst.huborcid.domain.Application;
import it.cineca.pst.huborcid.domain.EnvVariable;
import it.cineca.pst.huborcid.domain.Person;
import it.cineca.pst.huborcid.domain.PersonBio;
import it.cineca.pst.huborcid.domain.RelPersonApplication;
import it.cineca.pst.huborcid.orcid.client.OrcidAccessToken;
import it.cineca.pst.huborcid.orcid.client.OrcidApiType;
import it.cineca.pst.huborcid.orcid.client.OrcidOAuthClient;
import it.cineca.pst.huborcid.repository.EnvVariableRepository;
import it.cineca.pst.huborcid.repository.PersonBioRepository;
import it.cineca.pst.huborcid.repository.PersonRepository;
import it.cineca.pst.huborcid.repository.RelPersonApplicationRepository;
import it.cineca.pst.huborcid.repository.TokenRepository;
import it.cineca.pst.huborcid.web.rest.dto.NotifyDTO;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.bind.JAXBException;

import org.joda.time.DateTime;
import org.orcid.ns.orcid.ExternalIdentifier;
import org.orcid.ns.orcid.ExternalIdentifiers;
import org.orcid.ns.orcid.OrcidBio;
import org.orcid.ns.orcid.ResearcherUrl;
import org.orcid.ns.orcid.ResearcherUrls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
@Scope("prototype")
@Transactional
public class OrcidService {

	final private String SEPARATOR = "|||";
	
	private final Logger log = LoggerFactory.getLogger(OrcidService.class);

	@Inject
	private PersonRepository personRepository;

	@Inject
	private RelPersonApplicationRepository relPersonApplicationRepository;

	@Inject
	private TokenRepository tokenRepository;
	
	@Inject
	private EnvVariableRepository envVarRepository;
	
	@Inject
	private PersonBioRepository personBioRepository;
	
	@Autowired
    private Environment env;
	
	private OrcidApiType orcidApiType;

	RestTemplate restTemplate = new RestTemplate();
	
	@PostConstruct
    public void settingEnv() {
		if(env.acceptsProfiles("prod")){
			orcidApiType = OrcidApiType.LIVE;
		}else{
			orcidApiType = OrcidApiType.SANDBOX;
		}
    }


	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Future<String> sendNotify(RelPersonApplication relPersonApplication)
			throws InterruptedException, JsonProcessingException, IOException {
		// TODO commenti e log
		Application application = relPersonApplication.getApplication();
		System.out.println("Looking up " + application.getUrlNotify());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization",
				getBasicAuthHeader(application.getNotifyUsername(), application.getNotifyPassword()));
		NotifyDTO notifyDTO = new NotifyDTO();
		notifyDTO.setLocalId(relPersonApplication.getPerson().getLocalID());
		notifyDTO.setOrcid(relPersonApplication.getPerson().getOrcid());
		notifyDTO.setOrcidAccessToken(relPersonApplication.getOauthAccessToken());
		HttpEntity<NotifyDTO> entity = new HttpEntity<NotifyDTO>(notifyDTO, headers);
		// HttpEntity<MultiValueMap<String, String>> entity = new
		// HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> result = null;
		String error = null;
		if (relPersonApplication.getPerson().getOrcid() != null) {
			try {
				result = restTemplate.postForEntity(application.getUrlNotify(), entity, String.class);
				System.out.println(result.getBody());
				if (result.getStatusCode().is2xxSuccessful() && (result.getBody().contains("001"))) {
					error = null;
				} else {
					error = result.getStatusCode() + " " + result.getBody();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				error = e.getMessage();

			}
		} else {
			error = "ORCID IS NULL";
		}
		updateNotifyOnDb(relPersonApplication, error);
		
		return new AsyncResult<String>("OK");
	}

	private void updateNotifyOnDb(RelPersonApplication relPersonApplication, String error) {
		if(error==null){
			relPersonApplication.setNotified(true);
			relPersonApplication.setErrorNotDescription(null);
		}else{
			int maxLength = (error.length() < 3900) ? error.length() : 3900;
			error = error.substring(0, maxLength);
			Integer numRetry = (relPersonApplication.getNumRetry() == null) ? 1 : relPersonApplication.getNumRetry() + 1;
			relPersonApplication.setNumRetry(numRetry);
			
			Boolean notify = (relPersonApplication.getNumRetry()>2) ? true : null;
			relPersonApplication.setErrorNotDescription(error);
			relPersonApplication.setNotified(notify);
		}
		relPersonApplicationRepository.save(relPersonApplication);
	}

	@Transactional
	public void deleteUser(Person person) {
		relPersonApplicationRepository.deleteByPersonIs(person);
		tokenRepository.deleteByPersonIs(person);
		personRepository.delete(person);
	}

	@Scheduled(cron = "0 0,15,30,45 * * * *")
	public void notifyFailure() {
		notify(300);
	}
	
	@Scheduled(cron = "0 0 1,2,3,4,5 * * *")
	public void notifyFailureNight() {
		notify(1500);
	}
	
	@Scheduled(cron = "0 */5 * * * *")
	public void countTotal(){
    	EnvVariable total = envVarRepository.findOneByName("orcid.total");
    	EnvVariable totalDay = envVarRepository.findOneByName("orcid.total.day");
    	
    	Long totalOrcid = personRepository.countByOrcidIsNotNull();
    	DateTime date = new DateTime();
    	date = date.withTimeAtStartOfDay();
    	//date.toDateTimeAtStartOfDay();
    	Long totalOrcidDaily = personRepository.countByOrcidIsNotNullAndOrcidReleaseDateGreaterThanEqual(date);
    	total.setVariableValue(totalOrcid.toString());
    	totalDay.setVariableValue(totalOrcidDaily.toString());
	}
	
	void notify(Integer num){
		Pageable topXXX = new PageRequest(0, num);
		DateTime dt = new DateTime();
		dt.minusMinutes(15);
		List<RelPersonApplication> relPersonApp = relPersonApplicationRepository
				.findAllByLastIsTrueAndNotifiedIsFalse(dt, topXXX);
		System.out.println("Notify size: " + relPersonApp.size());
		for (int i = 0; i < relPersonApp.size(); i++) { 
			try {
				System.out.println(i + " Notify: notify " + relPersonApp.get(i).getId());
				Future<String> result = sendNotify(relPersonApp.get(i));
				result.get();
				System.out.println(i + " Notify: notified " + relPersonApp.get(i).getId());
			} catch (Exception e) {
			}
		}
	}

	private String getBasicAuthHeader(String user, String password) {
		String plainCreds = user + ":" + password;
		byte[] plainCredsBytes = plainCreds.getBytes();
		String base64Creds = new String(Base64.encode(plainCredsBytes));
		System.out.println("Basic " + base64Creds);
		return "Basic " + base64Creds;
	}
	
	
	/**
	 * Mediante chiamata rest ogni 5 minuti, aggiorna i dati personali (biografia etc) per tutte le persone che hanno need_update = 1 e last = 1 e token not null.
	 * @throws JAXBException
	 * @throws InterruptedException 
	 */
	@Scheduled(cron = "0 */5 * * * *")
	public void populateOrcidBio( ) throws JAXBException, InterruptedException{
		Random random = new Random();
		Integer numRandom = random.nextInt(30);
		wait( numRandom );
		log.debug("Method populateOrcidBio START");
		Pageable pageable = new PageRequest(0, 200);
		List<Person> listPerson = personRepository.findAllByNeedUpdateIsTrue(pageable);
        OrcidOAuthClient clientOrcid = new OrcidOAuthClient(orcidApiType);
		for(Person person: listPerson){
			List<RelPersonApplication> listRelPersonApp = relPersonApplicationRepository.findAllByPersonIsAndLastIsTrueAndOauthAccessTokenIsNotNull(person);
			OrcidBio orcidBio = null;
			for(RelPersonApplication relPersonApplication: listRelPersonApp){
				String orcid = relPersonApplication.getPerson().getOrcid();
				OrcidAccessToken orcidAccessToken = new OrcidAccessToken();
				orcidAccessToken.setOrcid(orcid);
				orcidAccessToken.setAccess_token(relPersonApplication.getOauthAccessToken());
				try {
					orcidBio = clientOrcid.getOrcidBio(orcidAccessToken);

					manageOrcidBio(person, orcidBio);
					
					log.info(String.format("Method populateOrcidBio: save personBio person.id=[%s]", person.getId()));
					break;
				} catch (Exception e) {
					log.info(String.format("Method populateOrcidBio: error personBio person.id=[%s], token=[%s], orcid=[%s]", person.getId(), relPersonApplication.getOauthAccessToken(), orcid));
				}
			}
			person.setNeedUpdate(false);
		}
		log.debug("Method populateOrcidBio END");
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void manageOrcidBio(Person person, OrcidBio orcidBio){		
		log.debug(String.format("Method manageOrcidBio START, person.id=[%s]", person.getId()));
		
		StringBuilder urls = new StringBuilder();
		ResearcherUrls researcherUrls = orcidBio.getResearcherUrls();
		if( researcherUrls!=null ){
			List<ResearcherUrl> listUrls = researcherUrls.getResearcherUrl();
			for(ResearcherUrl researcherUrl : listUrls){
				String urlName = researcherUrl.getUrlName();
				String url = researcherUrl.getUrl().getValue();
				if(urls.length()!=0){
					urls.append(SEPARATOR);
				}
				urls.append(String.format("%s (%s)", urlName, url));
			}
		}

		StringBuilder identifiers = new StringBuilder();
		ExternalIdentifiers externalIdentifiers = orcidBio.getExternalIdentifiers();
		if( externalIdentifiers!=null ){
			List<ExternalIdentifier> listExternalIdentifiers = externalIdentifiers.getExternalIdentifier();
			for(ExternalIdentifier externalIdentifier : listExternalIdentifiers){
				String commonNameExId = externalIdentifier.getExternalIdCommonName().getContent();
				String referenceExId = externalIdentifier.getExternalIdReference().getContent();
				String urlExId = externalIdentifier.getExternalIdUrl().getValue();
				if(identifiers.length()!=0){
					identifiers.append(SEPARATOR);
				}
				identifiers.append(String.format("%s (%s - %s)", commonNameExId, referenceExId, urlExId));
			}
		}
		
		personBioRepository.deleteByPersonIs(person);
		
		PersonBio personBio = new PersonBio();
		personBio.setPerson(person);
		if(orcidBio.getBiography() != null){
			personBio.setBiography(orcidBio.getBiography().getValue());
		}
		personBio.setResearcher_urls(urls.toString());
		personBio.setExternal_identifiers(identifiers.toString());
		personBioRepository.save(personBio);
		
		log.debug(String.format("Method manageOrcidBio END, person.id=%s", person.getId()));
	}
	

}
