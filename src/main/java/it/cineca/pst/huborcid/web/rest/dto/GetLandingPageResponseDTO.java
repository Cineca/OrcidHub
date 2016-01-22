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

import java.util.List;

public class GetLandingPageResponseDTO {
	
	String firstname;
	String lastname;
	String urlRegisterOrcid;
	String urlLoginOrcid;
	String urlHelp;
	List<ApplicationMinDTO> listApp;
	
	
	public GetLandingPageResponseDTO() {
		super();
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
	public String getUrlRegisterOrcid() {
		return urlRegisterOrcid;
	}
	public void setUrlRegisterOrcid(String urlRegisterOrcid) {
		this.urlRegisterOrcid = urlRegisterOrcid;
	}
	public String getUrlLoginOrcid() {
		return urlLoginOrcid;
	}
	public void setUrlLoginOrcid(String urlLoginOrcid) {
		this.urlLoginOrcid = urlLoginOrcid;
	}
	public String getUrlHelp() {
		return urlHelp;
	}
	public void setUrlHelp(String urlHelp) {
		this.urlHelp = urlHelp;
	}
	public List<ApplicationMinDTO> getListApp() {
		return listApp;
	}
	public void setListApp(List<ApplicationMinDTO> listApp) {
		this.listApp = listApp;
	}
}
