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
import it.cineca.pst.huborcid.domain.Token;
import it.cineca.pst.huborcid.repository.TokenRepository;
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
 * REST controller for managing Token.
 */
@RestController
@RequestMapping("/api")
public class TokenResource {

    private final Logger log = LoggerFactory.getLogger(TokenResource.class);

    @Inject
    private TokenRepository tokenRepository;

    /**
     * POST  /tokens -> Create a new token.
     */
    @RequestMapping(value = "/tokens",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Token token) throws URISyntaxException {
        log.debug("REST request to save Token : {}", token);
        if (token.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new token cannot already have an ID").build();
        }
        tokenRepository.save(token);
        return ResponseEntity.created(new URI("/api/tokens/" + token.getId())).build();
    }

    /**
     * PUT  /tokens -> Updates an existing token.
     */
    @RequestMapping(value = "/tokens",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Token token) throws URISyntaxException {
        log.debug("REST request to update Token : {}", token);
        if (token.getId() == null) {
            return create(token);
        }
        tokenRepository.save(token);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /tokens -> get all the tokens.
     */
    @RequestMapping(value = "/tokens",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Token>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Token> page = tokenRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tokens", offset, limit);
        return new ResponseEntity<List<Token>>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tokens/:id -> get the "id" token.
     */
    @RequestMapping(value = "/tokens/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Token> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Token : {}", id);
        Token token = tokenRepository.findOne(id);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    /**
     * DELETE  /tokens/:id -> delete the "id" token.
     */
    @RequestMapping(value = "/tokens/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Token : {}", id);
        tokenRepository.delete(id);
    }
}
