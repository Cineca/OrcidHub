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
import it.cineca.pst.huborcid.domain.Person;
import it.cineca.pst.huborcid.domain.RelPersonApplication;
import it.cineca.pst.huborcid.repository.ApplicationRepository;
import it.cineca.pst.huborcid.repository.PersonRepository;
import it.cineca.pst.huborcid.repository.RelPersonApplicationRepository;
import it.cineca.pst.huborcid.repository.TokenRepository;
import it.cineca.pst.huborcid.web.rest.dto.NotifyDTO;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Future;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class OrcidService {
	
	private final Logger log = LoggerFactory.getLogger(OrcidService.class);
	
	@Inject
    private PersonRepository personRepository;
    
    @Inject
    private RelPersonApplicationRepository relPersonApplicationRepository;
    
    @Inject
    private TokenRepository tokenRepository;
	
	RestTemplate restTemplate = new RestTemplate();
	

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Future<String> sendNotify(Application application,RelPersonApplication relPersonApplication) throws InterruptedException, JsonProcessingException, IOException {
    	//TODO commenti e log
    	System.out.println("Looking up " + application.getUrlNotify());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization",getBasicAuthHeader(application.getNotifyUsername(),application.getNotifyPassword()) );
		NotifyDTO notifyDTO = new NotifyDTO();
		notifyDTO.setLocalId(relPersonApplication.getPerson().getLocalID());
		notifyDTO.setOrcid(relPersonApplication.getPerson().getOrcid());
		notifyDTO.setOrcidAccessToken(relPersonApplication.getOauthAccessToken());
		HttpEntity<NotifyDTO> entity = new HttpEntity<NotifyDTO>(notifyDTO,headers);
		//HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> result = null;
		try{
			 result = restTemplate.postForEntity(application.getUrlNotify(), entity, String.class);
		}catch(RestClientException e){
			System.out.println(e.getMessage());
			throw e;
		}
		System.out.println(result.getBody());
		String resultObject = result.getBody();//new ObjectMapper().reader(String.class).readValue(result.getBody());
		
        return new AsyncResult<String>(resultObject);
    }
    
    @Transactional    
    public void deleteUser(Person person){
        relPersonApplicationRepository.deleteByPersonIs(person);
        tokenRepository.deleteByPersonIs(person);
        personRepository.delete(person);
    }
    
    private String getBasicAuthHeader(String user, String password){
    	String plainCreds = user+":"+password;
    	byte[] plainCredsBytes = plainCreds.getBytes();
    	String base64Creds  = new String(Base64.encode(plainCredsBytes));
    	System.out.println("Basic " + base64Creds);
    	return "Basic " + base64Creds;
    }
    
    public static void main(String [] args)
	{
    	String plainCreds = "_palena.cilea@unimi.it"+":"+"1sNotDream";
    	byte[] plainCredsBytes = plainCreds.getBytes();
    	String base64Creds  = new String(Base64.encode(plainCredsBytes));
    	System.out.println("Basic " + base64Creds);
	}

}
