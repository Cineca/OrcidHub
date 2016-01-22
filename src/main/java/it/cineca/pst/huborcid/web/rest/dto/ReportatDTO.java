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

import it.cineca.pst.huborcid.domain.util.CustomDateTimeDeserializer;
import it.cineca.pst.huborcid.domain.util.CustomDateTimeSerializer;

import java.util.Date;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ReportatDTO {
	
	String localId;
	
	String orcid;
	
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
	DateTime orcidCreated;
    
	String orcidAccassToken;
	
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
	DateTime accessTokenCreated;
    
	Boolean accessTokenRevoked;
	
	
	public ReportatDTO() {
	}

	public String getLocalId() {
		return localId;
	}
	public void setLocalId(String localId) {
		this.localId = localId;
	}
	public String getOrcid() {
		return orcid;
	}
	public void setOrcid(String orcid) {
		this.orcid = orcid;
	}
	public DateTime getOrcidCreated() {
		return orcidCreated;
	}
	public void setOrcidCreated(DateTime orcidCreated) {
		this.orcidCreated = orcidCreated;
	}
	public String getOrcidAccassToken() {
		return orcidAccassToken;
	}
	public void setOrcidAccassToken(String orcidAccassToken) {
		this.orcidAccassToken = orcidAccassToken;
	}
	public DateTime getAccessTokenCreated() {
		return accessTokenCreated;
	}
	public void setAccessTokenCreated(DateTime accessTokenCreated) {
		this.accessTokenCreated = accessTokenCreated;
	}
	public Boolean getAccessTokenRevoked() {
		return accessTokenRevoked;
	}
	public void setAccessTokenRevoked(Boolean accessTokenRevoked) {
		this.accessTokenRevoked = accessTokenRevoked;
	}
}
