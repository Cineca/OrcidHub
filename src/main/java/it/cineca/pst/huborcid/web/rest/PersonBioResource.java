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
import it.cineca.pst.huborcid.domain.PersonBio;
import it.cineca.pst.huborcid.repository.PersonBioRepository;
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
 * REST controller for managing PersonBio.
 */
@RestController
@RequestMapping("/api")
public class PersonBioResource {

    private final Logger log = LoggerFactory.getLogger(PersonBioResource.class);

    @Inject
    private PersonBioRepository personBioRepository;

    /**
     * POST  /personBios -> Create a new personBio.
     */
    @RequestMapping(value = "/personBios",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PersonBio> create(@RequestBody PersonBio personBio) throws URISyntaxException {
        log.debug("REST request to save PersonBio : {}", personBio);
        if (personBio.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new personBio cannot already have an ID").body(null);
        }
        PersonBio result = personBioRepository.save(personBio);
        return ResponseEntity.created(new URI("/api/personBios/" + personBio.getId())).body(result);
    }

    /**
     * PUT  /personBios -> Updates an existing personBio.
     */
    @RequestMapping(value = "/personBios",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PersonBio> update(@RequestBody PersonBio personBio) throws URISyntaxException {
        log.debug("REST request to update PersonBio : {}", personBio);
        if (personBio.getId() == null) {
            return create(personBio);
        }
        PersonBio result = personBioRepository.save(personBio);
        return ResponseEntity.ok().body(result);
    }

    /**
     * GET  /personBios -> get all the personBio.
     */
    @RequestMapping(value = "/personBios",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<PersonBio> getAll() {
        log.debug("REST request to get all PersonBio");
        return personBioRepository.findAll();
    }

    /**
     * GET  /personBios/:id -> get the "id" personBio.
     */
    @RequestMapping(value = "/personBios/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PersonBio> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get PersonBio : {}", id);
        PersonBio personBio = personBioRepository.findOne(id);
        if (personBio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personBio, HttpStatus.OK);
    }

    /**
     * DELETE  /personBios/:id -> delete the "id" personBio.
     */
    @RequestMapping(value = "/personBios/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete PersonBio : {}", id);
        personBioRepository.delete(id);
    }
}
