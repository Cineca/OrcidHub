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
import it.cineca.pst.huborcid.domain.ResultOrcidWork;
import it.cineca.pst.huborcid.repository.ApplicationRepository;
import it.cineca.pst.huborcid.repository.ResultOrcidWorkRepository;
import it.cineca.pst.huborcid.security.SecurityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing ResultOrcidWork.
 */
@RestController
@RequestMapping("/api")
public class ResultOrcidWorkResource {

    private final Logger log = LoggerFactory.getLogger(ResultOrcidWorkResource.class);

    @Inject
    private ResultOrcidWorkRepository resultOrcidWorkRepository;
    
    @Inject
    private ApplicationRepository applicationRepository;

    /**
     * POST  /resultOrcidWorks -> Create a new resultOrcidWork.
     */
    @RequestMapping(value = "/resultOrcidWorks",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody ResultOrcidWork resultOrcidWork) throws URISyntaxException {
        log.debug("REST request to save ResultOrcidWork : {}", resultOrcidWork);
        if (resultOrcidWork.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new resultOrcidWork cannot already have an ID").build();
        }
        resultOrcidWorkRepository.save(resultOrcidWork);
        return ResponseEntity.created(new URI("/api/resultOrcidWorks/" + resultOrcidWork.getId())).build();
    }

    /**
     * PUT  /resultOrcidWorks -> Updates an existing resultOrcidWork.
     */
    @RequestMapping(value = "/resultOrcidWorks",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody ResultOrcidWork resultOrcidWork) throws URISyntaxException {
        log.debug("REST request to update ResultOrcidWork : {}", resultOrcidWork);
        if (resultOrcidWork.getId() == null) {
            return create(resultOrcidWork);
        }
        resultOrcidWorkRepository.save(resultOrcidWork);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /resultOrcidWorks -> get all the resultOrcidWorks.
     */
    @RequestMapping(value = "/resultOrcidWorks",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ResultOrcidWork> getAll() {
        log.debug("REST request to get all ResultOrcidWorks");
        return resultOrcidWorkRepository.findAll();
    }

    /**
     * GET  /resultOrcidWorks/:id -> get the "id" resultOrcidWork.
     */
    @RequestMapping(value = "/resultOrcidWorks/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ResultOrcidWork> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get ResultOrcidWork : {}", id);
        ResultOrcidWork resultOrcidWork = resultOrcidWorkRepository.findOne(id);
        if (resultOrcidWork == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resultOrcidWork, HttpStatus.OK);
    }

    /**
     * DELETE  /resultOrcidWorks/:id -> delete the "id" resultOrcidWork.
     */
    @RequestMapping(value = "/resultOrcidWorks/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete ResultOrcidWork : {}", id);
        resultOrcidWorkRepository.delete(id);
    }
    
    
  
}
