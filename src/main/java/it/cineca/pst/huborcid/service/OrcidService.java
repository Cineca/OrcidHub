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
	private OrcidServiceAtomic orcidServiceAtomic;
	
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
				Future<String> result = orcidServiceAtomic.sendNotify(relPersonApp.get(i));
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
	@Scheduled(cron = "0 * * * * *")
	@Transactional
	public void populateOrcidBio( ) throws JAXBException, InterruptedException{
		Random rn = new Random();
		int n = 20;
		int i = Math.abs(rn.nextInt() % n);
		log.debug("Method populateOrcidBio START");
		Pageable pageable = new PageRequest(i,200);
		List<Person> listPerson = personRepository.findAllByNeedUpdateIsTrue(pageable);
        OrcidOAuthClient clientOrcid = new OrcidOAuthClient(orcidApiType);
		for(Person person: listPerson){
			orcidServiceAtomic.processPersonBio(person, clientOrcid);
		}
		log.debug("Method populateOrcidBio END");
	}

	

}
