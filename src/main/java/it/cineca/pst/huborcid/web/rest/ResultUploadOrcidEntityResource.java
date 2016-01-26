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
import it.cineca.pst.huborcid.domain.ResultUploadOrcidEntity;
import it.cineca.pst.huborcid.repository.ResultUploadOrcidEntityRepository;
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
 * REST controller for managing ResultUploadOrcidEntity.
 */
@RestController
@RequestMapping("/api")
public class ResultUploadOrcidEntityResource {

    private final Logger log = LoggerFactory.getLogger(ResultUploadOrcidEntityResource.class);

    @Inject
    private ResultUploadOrcidEntityRepository resultUploadOrcidEntityRepository;

    /**
     * POST  /resultUploadOrcidEntitys -> Create a new resultUploadOrcidEntity.
     */
    @RequestMapping(value = "/resultUploadOrcidEntitys",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody ResultUploadOrcidEntity resultUploadOrcidEntity) throws URISyntaxException {
        log.debug("REST request to save ResultUploadOrcidEntity : {}", resultUploadOrcidEntity);
        if (resultUploadOrcidEntity.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new resultUploadOrcidEntity cannot already have an ID").build();
        }
        resultUploadOrcidEntityRepository.save(resultUploadOrcidEntity);
        return ResponseEntity.created(new URI("/api/resultUploadOrcidEntitys/" + resultUploadOrcidEntity.getId())).build();
    }

    /**
     * PUT  /resultUploadOrcidEntitys -> Updates an existing resultUploadOrcidEntity.
     */
    @RequestMapping(value = "/resultUploadOrcidEntitys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody ResultUploadOrcidEntity resultUploadOrcidEntity) throws URISyntaxException {
        log.debug("REST request to update ResultUploadOrcidEntity : {}", resultUploadOrcidEntity);
        if (resultUploadOrcidEntity.getId() == null) {
            return create(resultUploadOrcidEntity);
        }
        resultUploadOrcidEntityRepository.save(resultUploadOrcidEntity);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /resultUploadOrcidEntitys -> get all the resultUploadOrcidEntitys.
     */
    @RequestMapping(value = "/resultUploadOrcidEntitys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ResultUploadOrcidEntity> getAll() {
        log.debug("REST request to get all ResultUploadOrcidEntitys");
        return resultUploadOrcidEntityRepository.findAll();
    }

    /**
     * GET  /resultUploadOrcidEntitys/:id -> get the "id" resultUploadOrcidEntity.
     */
    @RequestMapping(value = "/resultUploadOrcidEntitys/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ResultUploadOrcidEntity> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get ResultUploadOrcidEntity : {}", id);
        ResultUploadOrcidEntity resultUploadOrcidEntity = resultUploadOrcidEntityRepository.findOne(id);
        if (resultUploadOrcidEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resultUploadOrcidEntity, HttpStatus.OK);
    }

    /**
     * DELETE  /resultUploadOrcidEntitys/:id -> delete the "id" resultUploadOrcidEntity.
     */
    @RequestMapping(value = "/resultUploadOrcidEntitys/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete ResultUploadOrcidEntity : {}", id);
        resultUploadOrcidEntityRepository.delete(id);
    }
}
