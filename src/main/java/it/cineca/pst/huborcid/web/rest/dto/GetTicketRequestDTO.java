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
package it.cineca.pst.huborcid.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetTicketRequestDTO {
	
	String localID;
	String urlCallback;
	String appId;
	String orgId;
	String firstname;
	String lastname;
	String mail;
	
	
	
	public GetTicketRequestDTO() {
	}
	

	@JsonProperty("local-id")
	public String getLocalID() {
		return localID;
	}

	@JsonProperty("local-id")
	public void setLocalID(String localID) {
		this.localID = localID;
	}

	@JsonProperty("url-callback")
	public String getUrlCallback() {
		return urlCallback;
	}
	@JsonProperty("url-callback")
	public void setUrlCallback(String urlCallback) {
		this.urlCallback = urlCallback;
	}
	
	@JsonProperty("app-id")
	public String getAppId() {
		return appId;
	}
	@JsonProperty("app-id")
	public void setAppId(String appId) {
		this.appId = appId;
	}

	@JsonProperty("org-id")
	public String getOrgId() {
		return orgId;
	}
	@JsonProperty("org-id")
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}
