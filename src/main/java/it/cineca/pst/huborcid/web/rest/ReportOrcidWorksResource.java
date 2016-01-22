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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codahale.metrics.annotation.Timed;

import it.cineca.pst.huborcid.domain.Application;
import it.cineca.pst.huborcid.domain.ResultOrcidWork;
import it.cineca.pst.huborcid.repository.ApplicationRepository;
import it.cineca.pst.huborcid.repository.ResultOrcidWorkRepository;
import it.cineca.pst.huborcid.security.SecurityUtils;
import it.cineca.pst.huborcid.web.rest.util.PaginationUtil;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

@RestController
@RequestMapping("/api")
public class ReportOrcidWorksResource {
	
	@Inject
    private ResultOrcidWorkRepository resultOrcidWorkRepository;
	    
	@Inject
    private ApplicationRepository applicationRepository;

	private final Logger log = LoggerFactory.getLogger(ReportOrcidWorksResource.class);

    
	/**
	* GET  /reportOrcidWorks -> get all the resultOrcidWorks of application.
	 * @throws URISyntaxException 
	*/
    @RequestMapping(value = "/reportOrcidWorks/app", 
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ResultOrcidWork>> getAllOfApplication(@RequestParam(value = "page" , required = false) Integer offset,
                 @RequestParam(value = "per_page", required = false) Integer limit) throws URISyntaxException{
    	String currentLogin =SecurityUtils.getCurrentLogin();
		Application application = applicationRepository.findOneByApplicationID(currentLogin);
        log.debug("REST request to get all ResultOrcidWorks of application : {}", currentLogin);
        Sort sort = new Sort(Sort.Direction.DESC, Arrays.asList("createdDate"));
        Page<ResultOrcidWork> listResultOrcidWork =  resultOrcidWorkRepository.findAllByApplicationIs(application, PaginationUtil.generatePageRequest(offset, limit,sort));
        
    	List<ResultOrcidWork> listReport = new ArrayList<ResultOrcidWork>();
    	for(int i=0;i<listResultOrcidWork.getContent().size();i++){
    		ResultOrcidWork resultOrcidWork = listResultOrcidWork.getContent().get(i);
    		listReport.add(resultOrcidWork);
    	}
        
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(listResultOrcidWork, "/api/reportOrcidWorks/app", offset, limit);
        
        return new ResponseEntity<List<ResultOrcidWork>>(listReport, headers, HttpStatus.OK);
    }

}
