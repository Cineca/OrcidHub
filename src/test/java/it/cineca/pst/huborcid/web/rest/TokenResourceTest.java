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

import it.cineca.pst.huborcid.Application;
import it.cineca.pst.huborcid.domain.Token;
import it.cineca.pst.huborcid.repository.TokenRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TokenResource REST controller.
 *
 * @see TokenResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TokenResourceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");


    private static final DateTime DEFAULT_DATE_RELEASED = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATE_RELEASED = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATE_RELEASED_STR = dateTimeFormatter.print(DEFAULT_DATE_RELEASED);

    private static final DateTime DEFAULT_DATE_USED = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATE_USED = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATE_USED_STR = dateTimeFormatter.print(DEFAULT_DATE_USED);
    private static final String DEFAULT_OTT = "SAMPLE_TEXT";
    private static final String UPDATED_OTT = "UPDATED_TEXT";
    private static final String DEFAULT_URL_CALLBACK = "SAMPLE_TEXT";
    private static final String UPDATED_URL_CALLBACK = "UPDATED_TEXT";
    private static final String DEFAULT_ORG_UNIT = "SAMPLE_TEXT";
    private static final String UPDATED_ORG_UNIT = "UPDATED_TEXT";

    @Inject
    private TokenRepository tokenRepository;

    private MockMvc restTokenMockMvc;

    private Token token;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TokenResource tokenResource = new TokenResource();
        ReflectionTestUtils.setField(tokenResource, "tokenRepository", tokenRepository);
        this.restTokenMockMvc = MockMvcBuilders.standaloneSetup(tokenResource).build();
    }

    @Before
    public void initTest() {
        token = new Token();
        token.setDateReleased(DEFAULT_DATE_RELEASED);
        token.setDateUsed(DEFAULT_DATE_USED);
        token.setOtt(DEFAULT_OTT);
        token.setUrlCallback(DEFAULT_URL_CALLBACK);
        token.setOrgUnit(DEFAULT_ORG_UNIT);
    }

    @Test
    @Transactional
    public void createToken() throws Exception {
        int databaseSizeBeforeCreate = tokenRepository.findAll().size();

        // Create the Token
        restTokenMockMvc.perform(post("/api/tokens")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(token)))
                .andExpect(status().isCreated());

        // Validate the Token in the database
        List<Token> tokens = tokenRepository.findAll();
        assertThat(tokens).hasSize(databaseSizeBeforeCreate + 1);
        Token testToken = tokens.get(tokens.size() - 1);
        assertThat(testToken.getDateReleased().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATE_RELEASED);
        assertThat(testToken.getDateUsed().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATE_USED);
        assertThat(testToken.getOtt()).isEqualTo(DEFAULT_OTT);
        assertThat(testToken.getUrlCallback()).isEqualTo(DEFAULT_URL_CALLBACK);
        assertThat(testToken.getOrgUnit()).isEqualTo(DEFAULT_ORG_UNIT);
    }

    @Test
    @Transactional
    public void getAllTokens() throws Exception {
        // Initialize the database
        tokenRepository.saveAndFlush(token);

        // Get all the tokens
        restTokenMockMvc.perform(get("/api/tokens"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(token.getId().intValue())))
                .andExpect(jsonPath("$.[*].dateReleased").value(hasItem(DEFAULT_DATE_RELEASED_STR)))
                .andExpect(jsonPath("$.[*].dateUsed").value(hasItem(DEFAULT_DATE_USED_STR)))
                .andExpect(jsonPath("$.[*].ott").value(hasItem(DEFAULT_OTT.toString())))
                .andExpect(jsonPath("$.[*].urlCallback").value(hasItem(DEFAULT_URL_CALLBACK.toString())))
                .andExpect(jsonPath("$.[*].orgUnit").value(hasItem(DEFAULT_ORG_UNIT.toString())));
    }

    @Test
    @Transactional
    public void getToken() throws Exception {
        // Initialize the database
        tokenRepository.saveAndFlush(token);

        // Get the token
        restTokenMockMvc.perform(get("/api/tokens/{id}", token.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(token.getId().intValue()))
            .andExpect(jsonPath("$.dateReleased").value(DEFAULT_DATE_RELEASED_STR))
            .andExpect(jsonPath("$.dateUsed").value(DEFAULT_DATE_USED_STR))
            .andExpect(jsonPath("$.ott").value(DEFAULT_OTT.toString()))
            .andExpect(jsonPath("$.urlCallback").value(DEFAULT_URL_CALLBACK.toString()))
            .andExpect(jsonPath("$.orgUnit").value(DEFAULT_ORG_UNIT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingToken() throws Exception {
        // Get the token
        restTokenMockMvc.perform(get("/api/tokens/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateToken() throws Exception {
        // Initialize the database
        tokenRepository.saveAndFlush(token);

		int databaseSizeBeforeUpdate = tokenRepository.findAll().size();

        // Update the token
        token.setDateReleased(UPDATED_DATE_RELEASED);
        token.setDateUsed(UPDATED_DATE_USED);
        token.setOtt(UPDATED_OTT);
        token.setUrlCallback(UPDATED_URL_CALLBACK);
        token.setOrgUnit(UPDATED_ORG_UNIT);
        restTokenMockMvc.perform(put("/api/tokens")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(token)))
                .andExpect(status().isOk());

        // Validate the Token in the database
        List<Token> tokens = tokenRepository.findAll();
        assertThat(tokens).hasSize(databaseSizeBeforeUpdate);
        Token testToken = tokens.get(tokens.size() - 1);
        assertThat(testToken.getDateReleased().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATE_RELEASED);
        assertThat(testToken.getDateUsed().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATE_USED);
        assertThat(testToken.getOtt()).isEqualTo(UPDATED_OTT);
        assertThat(testToken.getUrlCallback()).isEqualTo(UPDATED_URL_CALLBACK);
        assertThat(testToken.getOrgUnit()).isEqualTo(UPDATED_ORG_UNIT);
    }

    @Test
    @Transactional
    public void deleteToken() throws Exception {
        // Initialize the database
        tokenRepository.saveAndFlush(token);

		int databaseSizeBeforeDelete = tokenRepository.findAll().size();

        // Get the token
        restTokenMockMvc.perform(delete("/api/tokens/{id}", token.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Token> tokens = tokenRepository.findAll();
        assertThat(tokens).hasSize(databaseSizeBeforeDelete - 1);
    }
}
