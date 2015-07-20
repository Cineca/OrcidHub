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
package it.cineca.pst.huborcid.web.rest.util;

/** Models the various ResultCode
 * 
 * @author andrea catalano
 *
 */
public enum ResultCode {

	SUCCESS("001"),
	SUCCESS_ALREADY_EXISTS("002"),
	ERROR_APP_ID_NOT_FOUND("401"),
	ERROR_TOKEN_NOT_FOUND("402"),
	ERROR_TOKEN_EXPIRED("403"),
	ERROR_LOCAL_ID_DIFFERENT("404"),
	ERROR_APP_ID_MISSING("405"),
	ERROR_LOCAL_ID_MISSING("406"),
	ERROR_ORCID_FOR_USER_MISSING("407"),
	ERROR_REL_PERSON_APPLICATION_NOT_FOUND("408"),
	ERROR_APP_ID_DIFFERENT_LOGIN("409"),
	ERROR_USER_DENIED("410"),
	ERROR_ORG_ID_IS_ONLY_FOR_PUBLIC_APP("412"),
	ERROR_ORG_ID_MISSING("413"),
	ERROR_GENERIC_FAIL("500"),
	ERROR_ORCID_DOWN("501");
	
	private final String stringValue;
	private ResultCode(final String s) { stringValue = s; }
	public String getCode() { return stringValue; }
	public String toString() { return stringValue; }
}