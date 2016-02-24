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

import it.cineca.pst.huborcid.domain.Application;
import it.cineca.pst.huborcid.domain.Person;
import it.cineca.pst.huborcid.domain.PersonBio;
import it.cineca.pst.huborcid.domain.RelPersonApplication;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data JPA repository for the PersonBio entity.
 */
public interface PersonBioRepository extends JpaRepository<PersonBio,Long> {
	
	@Modifying
	@Transactional
	@Query("delete from PersonBio bio where bio.person = :person")
	void deleteByPersonIs(@Param("person")Person person);
	
	@Query("select bio from RelPersonApplication personApp inner join personApp.person person inner join person.personBio bio where personApp.oauthAccessToken is not null and personApp.application = :application and personApp.last = true") 
	List<PersonBio> findAllPersonByApplicationIsAndLastIsTrue(@Param("application")Application application, Sort sort);
	
	
	

}
