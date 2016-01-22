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

public class GetUserIdResponseDTO {
	
	String resultCode;
	String orcid;
	String orcidAccessToken;
	
	public GetUserIdResponseDTO() {
	}
	
	
	
	public GetUserIdResponseDTO(String resultCode, String orcid,
			String orcidAccessToken) {
		this.resultCode = resultCode;
		this.orcid = orcid;
		this.orcidAccessToken = orcidAccessToken;
	}

	@JsonProperty("result-code")
	public String getResultCode() {
		return resultCode;
	}
	@JsonProperty("result-code")
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public String getOrcid() {
		return orcid;
	}
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
