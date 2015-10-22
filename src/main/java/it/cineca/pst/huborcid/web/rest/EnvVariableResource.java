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
import it.cineca.pst.huborcid.domain.EnvVariable;
import it.cineca.pst.huborcid.repository.EnvVariableRepository;
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
 * REST controller for managing EnvVariable.
 */
@RestController
@RequestMapping("/api")
public class EnvVariableResource {

    private final Logger log = LoggerFactory.getLogger(EnvVariableResource.class);

    @Inject
    private EnvVariableRepository envVariableRepository;

    /**
     * POST  /envVariables -> Create a new envVariable.
     */
    @RequestMapping(value = "/envVariables",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody EnvVariable envVariable) throws URISyntaxException {
        log.debug("REST request to save EnvVariable : {}", envVariable);
        if (envVariable.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new envVariable cannot already have an ID").build();
        }
        envVariableRepository.save(envVariable);
        return ResponseEntity.created(new URI("/api/envVariables/" + envVariable.getId())).build();
    }

    /**
     * PUT  /envVariables -> Updates an existing envVariable.
     */
    @RequestMapping(value = "/envVariables",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody EnvVariable envVariable) throws URISyntaxException {
        log.debug("REST request to update EnvVariable : {}", envVariable);
        if (envVariable.getId() == null) {
            return create(envVariable);
        }
        envVariableRepository.save(envVariable);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /envVariables -> get all the envVariables.
     */
    @RequestMapping(value = "/envVariables",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<EnvVariable> getAll() {
        log.debug("REST request to get all EnvVariables");
        return envVariableRepository.findAll();
    }

    /**
     * GET  /envVariables/:id -> get the "id" envVariable.
     */
    @RequestMapping(value = "/envVariables/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EnvVariable> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get EnvVariable : {}", id);
        EnvVariable envVariable = envVariableRepository.findOne(id);
        if (envVariable == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(envVariable, HttpStatus.OK);
    }

    /**
     * DELETE  /envVariables/:id -> delete the "id" envVariable.
     */
    @RequestMapping(value = "/envVariables/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete EnvVariable : {}", id);
        envVariableRepository.delete(id);
    }
}
