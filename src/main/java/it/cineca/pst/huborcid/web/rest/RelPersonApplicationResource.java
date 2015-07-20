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
import it.cineca.pst.huborcid.domain.RelPersonApplication;
import it.cineca.pst.huborcid.repository.RelPersonApplicationRepository;
import it.cineca.pst.huborcid.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
 * REST controller for managing RelPersonApplication.
 */
@RestController
@RequestMapping("/api")
public class RelPersonApplicationResource {

    private final Logger log = LoggerFactory.getLogger(RelPersonApplicationResource.class);

    @Inject
    private RelPersonApplicationRepository relPersonApplicationRepository;

    /**
     * POST  /relPersonApplications -> Create a new relPersonApplication.
     */
    @RequestMapping(value = "/relPersonApplications",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody RelPersonApplication relPersonApplication) throws URISyntaxException {
        log.debug("REST request to save RelPersonApplication : {}", relPersonApplication);
        if (relPersonApplication.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new relPersonApplication cannot already have an ID").build();
        }
        relPersonApplicationRepository.save(relPersonApplication);
        return ResponseEntity.created(new URI("/api/relPersonApplications/" + relPersonApplication.getId())).build();
    }

    /**
     * PUT  /relPersonApplications -> Updates an existing relPersonApplication.
     */
    @RequestMapping(value = "/relPersonApplications",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody RelPersonApplication relPersonApplication) throws URISyntaxException {
        log.debug("REST request to update RelPersonApplication : {}", relPersonApplication);
        if (relPersonApplication.getId() == null) {
            return create(relPersonApplication);
        }
        relPersonApplicationRepository.save(relPersonApplication);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /relPersonApplications -> get all the relPersonApplications.
     */
    @RequestMapping(value = "/relPersonApplications",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<RelPersonApplication>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<RelPersonApplication> page = relPersonApplicationRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/relPersonApplications", offset, limit);
        return new ResponseEntity<List<RelPersonApplication>>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /relPersonApplications/:id -> get the "id" relPersonApplication.
     */
    @RequestMapping(value = "/relPersonApplications/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RelPersonApplication> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get RelPersonApplication : {}", id);
        RelPersonApplication relPersonApplication = relPersonApplicationRepository.findOne(id);
        if (relPersonApplication == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(relPersonApplication, HttpStatus.OK);
    }

    /**
     * DELETE  /relPersonApplications/:id -> delete the "id" relPersonApplication.
     */
    @RequestMapping(value = "/relPersonApplications/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete RelPersonApplication : {}", id);
        relPersonApplicationRepository.delete(id);
    }
}
