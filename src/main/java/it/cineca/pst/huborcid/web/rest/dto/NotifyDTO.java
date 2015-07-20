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

public class NotifyDTO {
	
	String localId;
	String orcid;
	String orcidAccessToken;
	
	public NotifyDTO() {
	}

	@JsonProperty("local-id")
	public String getLocalId() {
		return localId;
	}
	@JsonProperty("local-id")
	public void setLocalId(String localId) {
		this.localId = localId;
	}
	@JsonProperty("orcid")
	public String getOrcid() {
		return orcid;
	}
	@JsonProperty("orcid")
	public void setOrcid(String orcid) {
		this.orcid = orcid;
	}
	@JsonProperty("orcid-access-token")
	public String getOrcidAccessToken() {
		return orcidAccessToken;
	}
	@JsonProperty("orcid-access-token")
	public void setOrcidAccessToken(String orcidAccessToken) {
		this.orcidAccessToken = orcidAccessToken;
	}
	

}
