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
package it.cineca.pst.huborcid.orcid.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;

import org.orcid.ns.orcid.Funding;
import org.orcid.ns.orcid.FundingList;
import org.orcid.ns.orcid.OrcidActivities;
import org.orcid.ns.orcid.OrcidBio;
import org.orcid.ns.orcid.OrcidMessage;
import org.orcid.ns.orcid.OrcidProfile;
import org.orcid.ns.orcid.OrcidWork;
import org.orcid.ns.orcid.OrcidWorks;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


public class OrcidOAuthClient {

	private final Logger log = LoggerFactory.getLogger(OrcidOAuthClient.class);

	private static final String AUTHZ_ENDPOINT = "/oauth/authorize";
	private static final String TOKEN_ENDPOINT = "/oauth/token";
	private static final String READ_BIO_ENDPOINT = "/orcid-bio";
	private static final String WORK_CREATE_ENDPOINT = "/orcid-works";
	private static final String FUNDING_CREATE_ENDPOINT = "/funding";
	
	private static final String SANDBOX_LOGIN_URI = "https://sandbox.orcid.org";
	private static final String SANDBOX_API_URI_TOKEN = "https://sandbox.orcid.org";
	private static final String SANDBOX_API_URI_V1_2 = "http://api.sandbox.orcid.org/v1.2";
	
	private static final String LIVE_LOGIN_URI = "https://orcid.org";
	private static final String LIVE_API_URI_TOKEN = "https://api.orcid.org";
	private static final String LIVE_API_URI_V1_2 = "http://api.orcid.org/v1.2";
	
	private final String clientID;
	private final String clientSecret;
	private final String redirectUri;

	private final String loginUri;
	private final String apiUriToken;
	private final String apiUriV12;
	
	private final JAXBContext orcidMessageContext;


	public OrcidOAuthClient(String clientID, String clientSecret, String redirectUri, OrcidApiType orcidApiType)
			throws JAXBException {
		if (clientID == null || clientSecret == null || redirectUri == null || orcidApiType == null) {
			throw new IllegalArgumentException("cannot create OrcidOAuthClient - missing init parameter(s)");
		}
		if (orcidApiType == OrcidApiType.SANDBOX) {
			this.loginUri = SANDBOX_LOGIN_URI;
			this.apiUriToken = SANDBOX_API_URI_TOKEN;
			this.apiUriV12 = SANDBOX_API_URI_V1_2;
		} else {
			this.loginUri = LIVE_LOGIN_URI;
			this.apiUriToken = LIVE_API_URI_TOKEN;
			this.apiUriV12 = LIVE_API_URI_V1_2;
		}
		this.clientID = clientID;
		this.clientSecret = clientSecret;
		this.redirectUri = redirectUri;
		
		this.orcidMessageContext = JAXBContext.newInstance(OrcidMessage.class);
	}
	
	public OrcidOAuthClient(OrcidApiType orcidApiType)
			throws JAXBException {
		if (orcidApiType == null) {
			throw new IllegalArgumentException("cannot create OrcidOAuthClient - missing init parameter(s)");
		}
		if (orcidApiType == OrcidApiType.SANDBOX) {
			this.loginUri = SANDBOX_LOGIN_URI;
			this.apiUriToken = SANDBOX_API_URI_TOKEN;
			this.apiUriV12 = SANDBOX_API_URI_V1_2;
		} else {
			this.loginUri = LIVE_LOGIN_URI;
			this.apiUriToken = LIVE_API_URI_TOKEN;
			this.apiUriV12 = LIVE_API_URI_V1_2;
		}
		this.clientID = null;
		this.clientSecret = null;
		this.redirectUri = null;
		
		this.orcidMessageContext = JAXBContext.newInstance(OrcidMessage.class);
	}
	

	public String getAuthzCodeRegisterRequest(String state, List<OrcidAuthScope> scopes, String firstName, String lastName, String mail) {
		return getAuthzCodeRequest(state,firstName,lastName,mail,false) + "&scope=" + Joiner.on("%20").join(scopes);
	}
	
	public String getAuthzCodeLoginRequest(String state, List<OrcidAuthScope> scopes, String firstName, String lastName, String mail) {
		return getAuthzCodeRequest(state,firstName,lastName,mail,true) + "&scope=" + Joiner.on("%20").join(scopes);
	}

	private String getAuthzCodeRequest(String state, String firstName, String lastName, String mail, boolean login) {
		String req = loginUri + AUTHZ_ENDPOINT;
		req += "?client_id=" + clientID;
		req += "&response_type=code";
		if(!login){
			if(firstName != null && !firstName.isEmpty())
				req += "&given_names="+firstName;
			if(lastName != null && !lastName.isEmpty())
				req += "&family_names="+lastName;
			if(mail != null && !mail.isEmpty())
				req += "&email="+mail;
			req += "&show_login=false";
		}else{
			req += "&show_login=true";
		}
		if (state != null) {
			req += "&state=" + state;
		}
		req += "&redirect_uri=" + redirectUri;
		return req;
	}
	
	public List<OrcidAuthScope> getListAllScope(){
		List<OrcidAuthScope> list = new ArrayList<OrcidAuthScope>();
		list.add(OrcidAuthScope.CREATE_WORKS);
		list.add(OrcidAuthScope.CREATE_AFFILIATION);
		list.add(OrcidAuthScope.CREATE_FOUNDING);
		list.add(OrcidAuthScope.UPDATE_WORKS);
		list.add(OrcidAuthScope.UPDATE_BIO);
		list.add(OrcidAuthScope.UPDATE_FOUNDING);
		list.add(OrcidAuthScope.UPDATE_AFFILIATION);
		list.add(OrcidAuthScope.READ_PROFILE);
		return list;
	}


	public OrcidAccessToken getAccessToken(String authorizationCode) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("code", authorizationCode);
		map.add("redirect_uri", redirectUri);
		map.add("client_id", clientID);
		map.add("client_secret", clientSecret);
		map.add("grant_type", "authorization_code");
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> result = null;
		try{
			 result = restTemplate.exchange(apiUriToken + TOKEN_ENDPOINT, HttpMethod.POST, entity, String.class);
		}catch(RestClientException e){
			log.debug(String.format("Method getAccessToken, exception %s", e.getMessage()));
			throw e;
		}
		log.debug(String.format("Method getAccessToken, result %s", result.getBody()));
		return new ObjectMapper().reader(OrcidAccessToken.class).readValue(result.getBody());
	}
	
	
	/**
	 * Ritorna OrcidBio della persona
	 * @param token
	 * @return
	 * @throws JAXBException
	 */
	public OrcidBio getOrcidBio(OrcidAccessToken token) throws JAXBException {
		log.debug(String.format("Method getOrcidBio START, token=[%s], orcid=[%s]", token.getAccess_token(), token.getOrcid()));
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer ".concat( token.getAccess_token()) );
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> result = null;
		try{
			String url = String.format( "%s//%s%s", apiUriV12, token.getOrcid(), READ_BIO_ENDPOINT );
			result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		}catch(RestClientException e){
			log.debug(String.format("Method getOrcidBio, exception=[%s]", e.getMessage()));
			throw e;
		}
		log.debug(String.format("Method getOrcidBio, result=[%s]", result.getBody()));
	    OrcidMessage message =
				(OrcidMessage) orcidMessageContext.createUnmarshaller().unmarshal(new StringReader(result.getBody()));

		log.debug(String.format("Method getOrcidBio END, token=[%s], orcid=[%s]", token.getAccess_token(), token.getOrcid()));

	    return message.getOrcidProfile().getOrcidBio();
	}


	public void appendWork(OrcidAccessToken token, OrcidWork work) throws JAXBException{
		log.debug(String.format("Method appendWork START, token=[%s], orcid=[%s]", token.getAccess_token(), token.getOrcid()));
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer ".concat( token.getAccess_token()) );
		headers.add("Content-Type", "application/orcid+xml" );

		log.info(token.getAccess_token());
		try {
			String url = String.format( "%s/%s%s", apiUriV12, token.getOrcid(), WORK_CREATE_ENDPOINT );
			OrcidMessage orcidMessage = wrapWork(work);
			Writer writer = new StringWriter();
			orcidMessageContext.createMarshaller().marshal(orcidMessage, writer);
			HttpEntity<String> request = new HttpEntity<String>(writer.toString(), headers);
			log.debug(String.format("Method appendWork, writer=[%s]", writer.toString()));
			restTemplate.postForObject(url, request, String.class);
		} catch (RestClientException e) {
			log.debug(String.format("Method appendWork, exception=[%s]", e.getMessage()));
			throw e;
		} 
		log.debug(String.format("Method appendWork END, token=[%s], orcid=[%s]", token.getAccess_token(), token.getOrcid()));
	}
	
	public void appendFunding(OrcidAccessToken token, Funding funding) throws JAXBException{
		log.debug(String.format("Method appendFunding START, token=[%s], orcid=[%s]", token.getAccess_token(), token.getOrcid()));
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer ".concat( token.getAccess_token()) );
		headers.add("Content-Type", "application/orcid+xml" );

		log.info(token.getAccess_token());
		try {
			String url = String.format( "%s/%s%s", apiUriV12, token.getOrcid(), FUNDING_CREATE_ENDPOINT );
			OrcidMessage orcidMessage = wrapFunding(funding);
			Writer writer = new StringWriter();
			orcidMessageContext.createMarshaller().marshal(orcidMessage, writer);
			HttpEntity<String> request = new HttpEntity<String>(writer.toString(), headers);
			log.debug(String.format("Method appendFunding, writer=[%s]", writer.toString()));
			restTemplate.postForObject(url, request, String.class);
		} catch (RestClientException e) {
			log.debug(String.format("Method appendFunding, exception=[%s]", e.getMessage()));
			throw e;
		} 
		log.debug(String.format("Method appendFunding END, token=[%s], orcid=[%s]", token.getAccess_token(), token.getOrcid()));
	}
	
	
	private static OrcidMessage wrapWork(OrcidWork work) {
		OrcidWorks works = new OrcidWorks();
		works.getOrcidWork().add(work);
		OrcidActivities activities = new OrcidActivities();
		activities.setOrcidWorks(works);
		OrcidProfile profile = new OrcidProfile();
		profile.setOrcidActivities(activities);
		OrcidMessage message = new OrcidMessage();
		message.setOrcidProfile(profile);
		message.setMessageVersion("1.2");
		return message;
	}
	
	private static OrcidMessage wrapFunding(Funding funding) {
		FundingList fundingList = new FundingList();
		fundingList.getFunding().add(funding);
		OrcidActivities activities = new OrcidActivities();
		activities.setFundingList(fundingList);
		OrcidProfile profile = new OrcidProfile();
		profile.setOrcidActivities(activities);
		OrcidMessage message = new OrcidMessage();
		message.setOrcidProfile(profile);
		message.setMessageVersion("1.2");
		return message;
	}
}
