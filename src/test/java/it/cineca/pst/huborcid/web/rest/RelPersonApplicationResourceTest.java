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
import it.cineca.pst.huborcid.domain.RelPersonApplication;
import it.cineca.pst.huborcid.repository.RelPersonApplicationRepository;

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
 * Test class for the RelPersonApplicationResource REST controller.
 *
 * @see RelPersonApplicationResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class RelPersonApplicationResourceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private static final String DEFAULT_OAUTH_ACCESS_TOKEN = "SAMPLE_TEXT";
    private static final String UPDATED_OAUTH_ACCESS_TOKEN = "UPDATED_TEXT";

    private static final DateTime DEFAULT_DATE_RELEASED = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATE_RELEASED = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATE_RELEASED_STR = dateTimeFormatter.print(DEFAULT_DATE_RELEASED);

    private static final DateTime DEFAULT_DATE_DENIED = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATE_DENIED = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATE_DENIED_STR = dateTimeFormatter.print(DEFAULT_DATE_DENIED);

    private static final Boolean DEFAULT_VALID = false;
    private static final Boolean UPDATED_VALID = true;

    private static final Boolean DEFAULT_DENIED = false;
    private static final Boolean UPDATED_DENIED = true;
    private static final String DEFAULT_ERROR_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_ERROR_DESCRIPTION = "UPDATED_TEXT";

    private static final Boolean DEFAULT_NOTIFIED = false;
    private static final Boolean UPDATED_NOTIFIED = true;

    private static final Boolean DEFAULT_LAST = false;
    private static final Boolean UPDATED_LAST = true;

    private static final Boolean DEFAULT_CUSTOM = false;
    private static final Boolean UPDATED_CUSTOM = true;
    private static final String DEFAULT_ERROR_NOT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_ERROR_NOT_DESCRIPTION = "UPDATED_TEXT";

    private static final Integer DEFAULT_NUM_RETRY = 0;
    private static final Integer UPDATED_NUM_RETRY = 1;

    @Inject
    private RelPersonApplicationRepository relPersonApplicationRepository;

    private MockMvc restRelPersonApplicationMockMvc;

    private RelPersonApplication relPersonApplication;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RelPersonApplicationResource relPersonApplicationResource = new RelPersonApplicationResource();
        ReflectionTestUtils.setField(relPersonApplicationResource, "relPersonApplicationRepository", relPersonApplicationRepository);
        this.restRelPersonApplicationMockMvc = MockMvcBuilders.standaloneSetup(relPersonApplicationResource).build();
    }

    @Before
    public void initTest() {
        relPersonApplication = new RelPersonApplication();
        relPersonApplication.setOauthAccessToken(DEFAULT_OAUTH_ACCESS_TOKEN);
        relPersonApplication.setDateReleased(DEFAULT_DATE_RELEASED);
        relPersonApplication.setDateDenied(DEFAULT_DATE_DENIED);
        relPersonApplication.setValid(DEFAULT_VALID);
        relPersonApplication.setDenied(DEFAULT_DENIED);
        relPersonApplication.setErrorDescription(DEFAULT_ERROR_DESCRIPTION);
        relPersonApplication.setNotified(DEFAULT_NOTIFIED);
        relPersonApplication.setLast(DEFAULT_LAST);
        relPersonApplication.setCustom(DEFAULT_CUSTOM);
        relPersonApplication.setErrorNotDescription(DEFAULT_ERROR_NOT_DESCRIPTION);
        relPersonApplication.setNumRetry(DEFAULT_NUM_RETRY);
    }

    @Test
    @Transactional
    public void createRelPersonApplication() throws Exception {
        int databaseSizeBeforeCreate = relPersonApplicationRepository.findAll().size();

        // Create the RelPersonApplication
        restRelPersonApplicationMockMvc.perform(post("/api/relPersonApplications")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(relPersonApplication)))
                .andExpect(status().isCreated());

        // Validate the RelPersonApplication in the database
        List<RelPersonApplication> relPersonApplications = relPersonApplicationRepository.findAll();
        assertThat(relPersonApplications).hasSize(databaseSizeBeforeCreate + 1);
        RelPersonApplication testRelPersonApplication = relPersonApplications.get(relPersonApplications.size() - 1);
        assertThat(testRelPersonApplication.getOauthAccessToken()).isEqualTo(DEFAULT_OAUTH_ACCESS_TOKEN);
        assertThat(testRelPersonApplication.getDateReleased().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATE_RELEASED);
        assertThat(testRelPersonApplication.getDateDenied().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATE_DENIED);
        assertThat(testRelPersonApplication.getValid()).isEqualTo(DEFAULT_VALID);
        assertThat(testRelPersonApplication.getDenied()).isEqualTo(DEFAULT_DENIED);
        assertThat(testRelPersonApplication.getErrorDescription()).isEqualTo(DEFAULT_ERROR_DESCRIPTION);
        assertThat(testRelPersonApplication.getNotified()).isEqualTo(DEFAULT_NOTIFIED);
        assertThat(testRelPersonApplication.getLast()).isEqualTo(DEFAULT_LAST);
        assertThat(testRelPersonApplication.getCustom()).isEqualTo(DEFAULT_CUSTOM);
        assertThat(testRelPersonApplication.getErrorNotDescription()).isEqualTo(DEFAULT_ERROR_NOT_DESCRIPTION);
        assertThat(testRelPersonApplication.getNumRetry()).isEqualTo(DEFAULT_NUM_RETRY);
    }

    @Test
    @Transactional
    public void getAllRelPersonApplications() throws Exception {
        // Initialize the database
        relPersonApplicationRepository.saveAndFlush(relPersonApplication);

        // Get all the relPersonApplications
        restRelPersonApplicationMockMvc.perform(get("/api/relPersonApplications"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(relPersonApplication.getId().intValue())))
                .andExpect(jsonPath("$.[*].oauthAccessToken").value(hasItem(DEFAULT_OAUTH_ACCESS_TOKEN.toString())))
                .andExpect(jsonPath("$.[*].dateReleased").value(hasItem(DEFAULT_DATE_RELEASED_STR)))
                .andExpect(jsonPath("$.[*].dateDenied").value(hasItem(DEFAULT_DATE_DENIED_STR)))
                .andExpect(jsonPath("$.[*].valid").value(hasItem(DEFAULT_VALID.booleanValue())))
                .andExpect(jsonPath("$.[*].denied").value(hasItem(DEFAULT_DENIED.booleanValue())))
                .andExpect(jsonPath("$.[*].errorDescription").value(hasItem(DEFAULT_ERROR_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].notified").value(hasItem(DEFAULT_NOTIFIED.booleanValue())))
                .andExpect(jsonPath("$.[*].last").value(hasItem(DEFAULT_LAST.booleanValue())))
                .andExpect(jsonPath("$.[*].custom").value(hasItem(DEFAULT_CUSTOM.booleanValue())))
                .andExpect(jsonPath("$.[*].errorNotDescription").value(hasItem(DEFAULT_ERROR_NOT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].numRetry").value(hasItem(DEFAULT_NUM_RETRY)));
    }

    @Test
    @Transactional
    public void getRelPersonApplication() throws Exception {
        // Initialize the database
        relPersonApplicationRepository.saveAndFlush(relPersonApplication);

        // Get the relPersonApplication
        restRelPersonApplicationMockMvc.perform(get("/api/relPersonApplications/{id}", relPersonApplication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(relPersonApplication.getId().intValue()))
            .andExpect(jsonPath("$.oauthAccessToken").value(DEFAULT_OAUTH_ACCESS_TOKEN.toString()))
            .andExpect(jsonPath("$.dateReleased").value(DEFAULT_DATE_RELEASED_STR))
            .andExpect(jsonPath("$.dateDenied").value(DEFAULT_DATE_DENIED_STR))
            .andExpect(jsonPath("$.valid").value(DEFAULT_VALID.booleanValue()))
            .andExpect(jsonPath("$.denied").value(DEFAULT_DENIED.booleanValue()))
            .andExpect(jsonPath("$.errorDescription").value(DEFAULT_ERROR_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.notified").value(DEFAULT_NOTIFIED.booleanValue()))
            .andExpect(jsonPath("$.last").value(DEFAULT_LAST.booleanValue()))
            .andExpect(jsonPath("$.custom").value(DEFAULT_CUSTOM.booleanValue()))
            .andExpect(jsonPath("$.errorNotDescription").value(DEFAULT_ERROR_NOT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.numRetry").value(DEFAULT_NUM_RETRY));
    }

    @Test
    @Transactional
    public void getNonExistingRelPersonApplication() throws Exception {
        // Get the relPersonApplication
        restRelPersonApplicationMockMvc.perform(get("/api/relPersonApplications/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelPersonApplication() throws Exception {
        // Initialize the database
        relPersonApplicationRepository.saveAndFlush(relPersonApplication);

		int databaseSizeBeforeUpdate = relPersonApplicationRepository.findAll().size();

        // Update the relPersonApplication
        relPersonApplication.setOauthAccessToken(UPDATED_OAUTH_ACCESS_TOKEN);
        relPersonApplication.setDateReleased(UPDATED_DATE_RELEASED);
        relPersonApplication.setDateDenied(UPDATED_DATE_DENIED);
        relPersonApplication.setValid(UPDATED_VALID);
        relPersonApplication.setDenied(UPDATED_DENIED);
        relPersonApplication.setErrorDescription(UPDATED_ERROR_DESCRIPTION);
        relPersonApplication.setNotified(UPDATED_NOTIFIED);
        relPersonApplication.setLast(UPDATED_LAST);
        relPersonApplication.setCustom(UPDATED_CUSTOM);
        relPersonApplication.setErrorNotDescription(UPDATED_ERROR_NOT_DESCRIPTION);
        relPersonApplication.setNumRetry(UPDATED_NUM_RETRY);
        restRelPersonApplicationMockMvc.perform(put("/api/relPersonApplications")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(relPersonApplication)))
                .andExpect(status().isOk());

        // Validate the RelPersonApplication in the database
        List<RelPersonApplication> relPersonApplications = relPersonApplicationRepository.findAll();
        assertThat(relPersonApplications).hasSize(databaseSizeBeforeUpdate);
        RelPersonApplication testRelPersonApplication = relPersonApplications.get(relPersonApplications.size() - 1);
        assertThat(testRelPersonApplication.getOauthAccessToken()).isEqualTo(UPDATED_OAUTH_ACCESS_TOKEN);
        assertThat(testRelPersonApplication.getDateReleased().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATE_RELEASED);
        assertThat(testRelPersonApplication.getDateDenied().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATE_DENIED);
        assertThat(testRelPersonApplication.getValid()).isEqualTo(UPDATED_VALID);
        assertThat(testRelPersonApplication.getDenied()).isEqualTo(UPDATED_DENIED);
        assertThat(testRelPersonApplication.getErrorDescription()).isEqualTo(UPDATED_ERROR_DESCRIPTION);
        assertThat(testRelPersonApplication.getNotified()).isEqualTo(UPDATED_NOTIFIED);
        assertThat(testRelPersonApplication.getLast()).isEqualTo(UPDATED_LAST);
        assertThat(testRelPersonApplication.getCustom()).isEqualTo(UPDATED_CUSTOM);
        assertThat(testRelPersonApplication.getErrorNotDescription()).isEqualTo(UPDATED_ERROR_NOT_DESCRIPTION);
        assertThat(testRelPersonApplication.getNumRetry()).isEqualTo(UPDATED_NUM_RETRY);
    }

    @Test
    @Transactional
    public void deleteRelPersonApplication() throws Exception {
        // Initialize the database
        relPersonApplicationRepository.saveAndFlush(relPersonApplication);

		int databaseSizeBeforeDelete = relPersonApplicationRepository.findAll().size();

        // Get the relPersonApplication
        restRelPersonApplicationMockMvc.perform(delete("/api/relPersonApplications/{id}", relPersonApplication.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<RelPersonApplication> relPersonApplications = relPersonApplicationRepository.findAll();
        assertThat(relPersonApplications).hasSize(databaseSizeBeforeDelete - 1);
    }
}
