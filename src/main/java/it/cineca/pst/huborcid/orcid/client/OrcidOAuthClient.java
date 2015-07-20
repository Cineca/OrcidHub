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

import javax.xml.bind.JAXBException;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


public class OrcidOAuthClient {

	private static final Logger log = Logger.getLogger(OrcidOAuthClient.class.getName());

	private static final String AUTHZ_ENDPOINT = "/oauth/authorize";
	private static final String TOKEN_ENDPOINT = "/oauth/token";


	private static final String SANDBOX_LOGIN_URI = "https://sandbox.orcid.org";
	private static final String SANDBOX_API_URI_TOKEN = "https://api.sandbox.orcid.org";
	
	private static final String LIVE_LOGIN_URI = "https://orcid.org";
	private static final String LIVE_API_URI_TOKEN = "https://api.orcid.org";

	private final String clientID;
	private final String clientSecret;
	private final String redirectUri;

	private final String loginUri;
	private final String apiUriToken;


	public OrcidOAuthClient(String clientID, String clientSecret, String redirectUri, OrcidApiType orcidApiType)
			throws JAXBException {
		if (clientID == null || clientSecret == null || redirectUri == null || orcidApiType == null) {
			throw new IllegalArgumentException("cannot create OrcidOAuthClient - missing init parameter(s)");
		}
		if (orcidApiType == OrcidApiType.SANDBOX) {
			this.loginUri = SANDBOX_LOGIN_URI;
			this.apiUriToken = SANDBOX_API_URI_TOKEN;
		} else {
			this.loginUri = LIVE_LOGIN_URI;
			this.apiUriToken = LIVE_API_URI_TOKEN;
		}
		this.clientID = clientID;
		this.clientSecret = clientSecret;
		this.redirectUri = redirectUri;
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
			System.out.println(e.getMessage());
			throw e;
		}
		System.out.println(result.getBody());
		return new ObjectMapper().reader(OrcidAccessToken.class).readValue(result.getBody());
	}
	


}
