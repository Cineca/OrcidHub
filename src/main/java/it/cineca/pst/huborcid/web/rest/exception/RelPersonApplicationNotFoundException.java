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
package it.cineca.pst.huborcid.web.rest.exception;


public class RelPersonApplicationNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537061376635309897L;
	
	String relPersonApplicationId = null;
	
	public RelPersonApplicationNotFoundException(String relPersonApplicationId) {
		super("A generic error occurred (RelPersonApplication: "+relPersonApplicationId +" not found or used). Please close this page and try to restart association to orcid from the beggining!");
		this.relPersonApplicationId = relPersonApplicationId;
	}

	public String getRelPersonApplicationId() {
		return relPersonApplicationId;
	}

	public void setRelPersonApplicationId(String relPersonApplicationId) {
		this.relPersonApplicationId = relPersonApplicationId;
	}

	
}
