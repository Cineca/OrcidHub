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
package it.cineca.pst.huborcid.repository;

import java.util.List;

import it.cineca.pst.huborcid.domain.Application;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA repository for the Application entity.
 */
public interface ApplicationRepository extends JpaRepository<Application,Long> {

	Application findOneByApplicationID(String applicationID);
	
	List<Application> findAllByOrgUnitOrAllOrgIsTrue(String applicationID);
	
	@Query("select a from Application a where a.id not in :listIds")
	List<Application> findAllCustomApps(@Param("listIds")List<Long> listIds);
}
