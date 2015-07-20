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


public enum OrcidAuthScope {

	AUTHENTICATE("/authenticate"),
	CREATE_WORKS("/orcid-works/create"),
	CREATE_EXTERNAL_ID("/orcid-bio/external-identifiers/create"),
	CREATE_PROFILE("/orcid-profile/create"),
	UPDATE_BIO("/orcid-bio/update"),
	UPDATE_WORKS("/orcid-works/update"),
	READ_PROFILE("/orcid-profile/read-limited"),
	READ_BIO("/orcid-bio/read-limited"),
	READ_WORKS("/orcid-works/read-limited"),
	READPUBLIC("/read-public"),
	CREATE_AFFILIATION("/affiliations/create"),
	CREATE_FOUNDING("/funding/create"),
	UPDATE_AFFILIATION("/affiliations/update"),
	UPDATE_FOUNDING("/funding/update"),
	REGISTER_WEBHOOK("/webhook");
	
	private final String stringValue;
	private OrcidAuthScope(final String s) { stringValue = s; }
	public String toString() { return stringValue; }
}
