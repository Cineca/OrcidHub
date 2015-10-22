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

import java.util.ArrayList;
import java.util.List;

import it.cineca.pst.huborcid.domain.Application;
import it.cineca.pst.huborcid.domain.RelPersonApplication;
import it.cineca.pst.huborcid.web.rest.dto.ApplicationMinDTO;



public final class ApplicationMapper {

	public static ApplicationMinDTO from (Application application,Boolean custom){
		ApplicationMinDTO applicationMin = new ApplicationMinDTO();
		applicationMin.setId(application.getId());
		applicationMin.setName(application.getName());
		applicationMin.setDescription(application.getDescription());
		applicationMin.setCanDelete(custom);
		return applicationMin;
	}
	
	public static List<ApplicationMinDTO> fromListAppCustom (List<Application> applications){
		List<ApplicationMinDTO> applicationsMin = new ArrayList<ApplicationMinDTO>();
		for(int i=0;i<applications.size();i++){
			applicationsMin.add(from(applications.get(i),true));
		}
		return applicationsMin;
	}
	
	public static List<ApplicationMinDTO> fromListRelApp (List<RelPersonApplication> applications){
		List<ApplicationMinDTO> applicationsMin = new ArrayList<ApplicationMinDTO>();
		for(int i=0;i<applications.size();i++){
			applicationsMin.add(from(applications.get(i).getApplication(),applications.get(i).getCustom()));
		}
		return applicationsMin;
	}
	
	
	
}
