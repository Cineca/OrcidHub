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
import it.cineca.pst.huborcid.web.rest.util.ResultCode;

public class UploadResponseDTO {
	
	String resultCode;
	String message;

	public UploadResponseDTO() {
		super();
	}
	
	public UploadResponseDTO(ResultCode resultCode, String message) {
		super();
		this.resultCode = resultCode.getCode();
		this.message = message;
	}
	
	@JsonProperty("code")
	public String getResultCode() {
		return resultCode;
	}
	@JsonProperty("code")
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	@JsonProperty("message")
	public String getMessage() {
		return message;
	}
	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

}
