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
import it.cineca.pst.huborcid.domain.RelPersonApplication;
import it.cineca.pst.huborcid.domain.Token;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Spring Data JPA repository for the RelPersonApplication entity.
 */
public interface RelPersonApplicationRepository extends JpaRepository<RelPersonApplication,Long> {

	List<RelPersonApplication> findAllByPersonIsAndApplicationIn(Person person, List<Application> applications);
	
	List<RelPersonApplication> findAllByPersonIsAndLastIsTrue(Person person);
	
	List<RelPersonApplication> findAllByPersonIsAndApplicationInAndValidIsTrue(Person person, List<Application> applications);
	
	@Query("select rel from RelPersonApplication rel where rel.valid = true AND (rel.notified = false OR rel.notified is null) ORDER BY rel.id DESC")
	List<RelPersonApplication> findAllByValidIsTrueAndNotifiedIsFalse(Pageable pageable);
	
	@Query("select rel from RelPersonApplication rel where rel.last = true AND (rel.notified = false OR rel.notified is null) AND rel.createdDate < :hourDate ORDER BY rel.id DESC")
	List<RelPersonApplication> findAllByLastIsTrueAndNotifiedIsFalse(@Param("hourDate")DateTime hourDate, Pageable pageable);
	
	List<RelPersonApplication> findAllByTokenIsAndValidIsNull(Token token);
	
	Page<RelPersonApplication> findAllByLastIsTrueAndApplicationIs(Application application, Pageable page);
	
	List<RelPersonApplication> findAllByLastIsTrueAndApplicationIs(Application application, Sort sort);
	
	List<RelPersonApplication> findAllByValidIsTrueAndApplicationIs(Application application, Sort sort);
	
	RelPersonApplication findOneByPersonIsAndApplicationIsAndLastIsTrue(Person person, Application application);

	RelPersonApplication findOneByPersonIsAndApplicationIsAndTokenIs(Person person, Application application, Token token);
	
	RelPersonApplication findOneByApplicationIsAndTokenIs(Application application, Token token);
	
	List<RelPersonApplication> findAllByPersonIsAndApplicationIs(Person person, Application application);
	
	Long deleteByPersonIs(Person person);
	
	List<RelPersonApplication> findAllByPersonIsAndLastIsTrueAndOauthAccessTokenIsNotNull(Person person);
	
	
	@Query("SELECT personApp FROM RelPersonApplication personApp INNER JOIN personApp.person person WHERE personApp.application = :application AND personApp.last = true "
			+ "AND ( person.orcid = :orcid OR person.localID = :localid ) ") 
	List<RelPersonApplication> findAllByApplicationIsAndLastIsTrueAndOrcidIsOrLocalIdIs(@Param("application")Application application, @Param("orcid")String orcid, @Param("localid")String localId);
}
