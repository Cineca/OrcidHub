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

import it.cineca.pst.huborcid.domain.UserOrcid;
import it.cineca.pst.huborcid.repository.UserOrcidRepository;
import it.cineca.pst.huborcid.web.rest.util.PaginationUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * REST controller for managing UserOrcid.
 */
@RestController
@RequestMapping("/api")
public class UserOrcidResource {

    private final Logger log = LoggerFactory.getLogger(UserOrcidResource.class);

    @Inject
    private UserOrcidRepository userOrcidRepository;
    
    @Inject
    private PasswordEncoder passwordEncoder;

    /**
     * POST  /userOrcids -> Create a new userOrcid.
     */
    @RequestMapping(value = "/userOrcids",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody UserOrcid userOrcid) throws URISyntaxException {
        log.debug("REST request to save UserOrcid : {}", userOrcid);
        if (userOrcid.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new userOrcid cannot already have an ID").build();
        }
        String passwordEncoded = passwordEncoder.encode(userOrcid.getPasswordPlain());
        userOrcid.setPasswordHash(passwordEncoded);
        userOrcidRepository.save(userOrcid);
        return ResponseEntity.created(new URI("/api/userOrcids/" + userOrcid.getId())).build();
    }

    /**
     * PUT  /userOrcids -> Updates an existing userOrcid.
     */
    @RequestMapping(value = "/userOrcids",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody UserOrcid userOrcid) throws URISyntaxException {
        log.debug("REST request to update UserOrcid : {}", userOrcid);
        if (userOrcid.getId() == null) {
            return create(userOrcid);
        }
        String passwordEncoded = passwordEncoder.encode(userOrcid.getPasswordPlain());
        userOrcid.setPasswordHash(passwordEncoded);
        userOrcidRepository.save(userOrcid);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /userOrcids -> get all the userOrcids.
     */
    @RequestMapping(value = "/userOrcids",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<UserOrcid>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<UserOrcid> page = userOrcidRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/userOrcids", offset, limit);
        return new ResponseEntity<List<UserOrcid>>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /userOrcids/:id -> get the "id" userOrcid.
     */
    @RequestMapping(value = "/userOrcids/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserOrcid> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get UserOrcid : {}", id);
        UserOrcid userOrcid = userOrcidRepository.findOne(id);
        if (userOrcid == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOrcid, HttpStatus.OK);
    }

    /**
     * DELETE  /userOrcids/:id -> delete the "id" userOrcid.
     */
    @RequestMapping(value = "/userOrcids/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete UserOrcid : {}", id);
        userOrcidRepository.delete(id);
    }
}
