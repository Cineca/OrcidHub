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
package it.cineca.pst.huborcid.web.rest;

import com.codahale.metrics.annotation.Timed;

import it.cineca.pst.huborcid.domain.Application;
import it.cineca.pst.huborcid.domain.RelPersonApplication;
import it.cineca.pst.huborcid.repository.ApplicationRepository;
import it.cineca.pst.huborcid.repository.RelPersonApplicationRepository;
import it.cineca.pst.huborcid.security.SecurityUtils;
import it.cineca.pst.huborcid.web.rest.dto.ReportatDTO;
import it.cineca.pst.huborcid.web.rest.util.PaginationUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing RelPersonApplication.
 */
@RestController
@RequestMapping("/api")
public class ReportatResource {

    private final Logger log = LoggerFactory.getLogger(ReportatResource.class);

    @Inject
    private RelPersonApplicationRepository relPersonApplicationRepository;
    
    @Inject
    private ApplicationRepository applicationRepository;


    /**
     * GET  /reportat -> get all the relPersonApplications.
     */
    @RequestMapping(value = "/reportat",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ReportatDTO>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
    	String currentLogin = SecurityUtils.getCurrentLogin();
    	Application application = applicationRepository.findOneByApplicationID(currentLogin);
    	Sort sort = new Sort(Sort.Direction.ASC, "person.localID");
    	Page<RelPersonApplication> listAccessToken = relPersonApplicationRepository.findAllByLastIsTrueAndApplicationIs(application,PaginationUtil.generatePageRequest(offset, limit,sort));
    	List<ReportatDTO> listReportat = new ArrayList<ReportatDTO>();
    	for(int i=0;i<listAccessToken.getContent().size();i++){
    		RelPersonApplication relPerson = listAccessToken.getContent().get(i);
    		ReportatDTO reportasDTO = new ReportatDTO();
    		reportasDTO.setLocalId(relPerson.getPerson().getLocalID());
    		reportasDTO.setOrcid(relPerson.getPerson().getOrcid());
    		reportasDTO.setOrcidCreated(relPerson.getPerson().getOrcidReleaseDate());
    		reportasDTO.setOrcidAccassToken(relPerson.getOauthAccessToken());
    		if((relPerson.getDenied()==null)||(relPerson.getDenied()==false)){
    			reportasDTO.setOrcidAccassToken(relPerson.getOauthAccessToken());
    			reportasDTO.setAccessTokenCreated(relPerson.getDateReleased());	
    		}
    		//FIXME quando verrà gestita la revoca andrà implementato
    		//reportasDTO.setAccessTokenRevoked(null);
    		listReportat.add(reportasDTO);
    	}
    	
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(listAccessToken, "/api/reportat", offset, limit);
        
        return new ResponseEntity<List<ReportatDTO>>(listReportat, headers, HttpStatus.OK);
    }

   
}
