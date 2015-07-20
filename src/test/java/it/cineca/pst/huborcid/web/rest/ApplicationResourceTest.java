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

import it.cineca.pst.huborcid.domain.Application;
import it.cineca.pst.huborcid.repository.ApplicationRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ApplicationResource REST controller.
 *
 * @see ApplicationResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = it.cineca.pst.huborcid.Application.class)
@WebAppConfiguration
@IntegrationTest
public class ApplicationResourceTest {

    private static final String DEFAULT_APPLICATION_ID = "SAMPLE_TEXT";
    private static final String UPDATED_APPLICATION_ID = "UPDATED_TEXT";
    private static final String DEFAULT_APPLICATION_SECRET = "SAMPLE_TEXT";
    private static final String UPDATED_APPLICATION_SECRET = "UPDATED_TEXT";
    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_URL_CALLBACK = "SAMPLE_TEXT";
    private static final String UPDATED_URL_CALLBACK = "UPDATED_TEXT";
    private static final String DEFAULT_ORG_UNIT = "SAMPLE_TEXT";
    private static final String UPDATED_ORG_UNIT = "UPDATED_TEXT";
    private static final String DEFAULT_EMAIL = "SAMPLE_TEXT";
    private static final String UPDATED_EMAIL = "UPDATED_TEXT";
    private static final String DEFAULT_URL_NOTIFY = "SAMPLE_TEXT";
    private static final String UPDATED_URL_NOTIFY = "UPDATED_TEXT";
    private static final String DEFAULT_NOTIFY_USERNAME = "SAMPLE_TEXT";
    private static final String UPDATED_NOTIFY_USERNAME = "UPDATED_TEXT";
    private static final String DEFAULT_NOTIFY_PASSWORD = "SAMPLE_TEXT";
    private static final String UPDATED_NOTIFY_PASSWORD = "UPDATED_TEXT";

    private static final Boolean DEFAULT_ALLORG = false;
    private static final Boolean UPDATED_ALLORG = true;
    private static final String DEFAULT_HELP_URL = "SAMPLE_TEXT";
    private static final String UPDATED_HELP_URL = "UPDATED_TEXT";
    private static final String DEFAULT_HELP_MAIL = "SAMPLE_TEXT";
    private static final String UPDATED_HELP_MAIL = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";

    @Inject
    private ApplicationRepository applicationRepository;

    private MockMvc restApplicationMockMvc;

    private Application application;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ApplicationResource applicationResource = new ApplicationResource();
        ReflectionTestUtils.setField(applicationResource, "applicationRepository", applicationRepository);
        this.restApplicationMockMvc = MockMvcBuilders.standaloneSetup(applicationResource).build();
    }

    @Before
    public void initTest() {
        application = new Application();
        application.setApplicationID(DEFAULT_APPLICATION_ID);
        application.setApplicationSecret(DEFAULT_APPLICATION_SECRET);
        application.setName(DEFAULT_NAME);
        application.setUrlCallback(DEFAULT_URL_CALLBACK);
        application.setOrgUnit(DEFAULT_ORG_UNIT);
        application.setEmail(DEFAULT_EMAIL);
        application.setUrlNotify(DEFAULT_URL_NOTIFY);
        application.setNotifyUsername(DEFAULT_NOTIFY_USERNAME);
        application.setNotifyPassword(DEFAULT_NOTIFY_PASSWORD);
        application.setAllOrg(DEFAULT_ALLORG);
        application.setHelpURL(DEFAULT_HELP_URL);
        application.setHelpMail(DEFAULT_HELP_MAIL);
        application.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createApplication() throws Exception {
        int databaseSizeBeforeCreate = applicationRepository.findAll().size();

        // Create the Application
        restApplicationMockMvc.perform(post("/api/applications")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(application)))
                .andExpect(status().isCreated());

        // Validate the Application in the database
        List<Application> applications = applicationRepository.findAll();
        assertThat(applications).hasSize(databaseSizeBeforeCreate + 1);
        Application testApplication = applications.get(applications.size() - 1);
        assertThat(testApplication.getApplicationID()).isEqualTo(DEFAULT_APPLICATION_ID);
        assertThat(testApplication.getApplicationSecret()).isEqualTo(DEFAULT_APPLICATION_SECRET);
        assertThat(testApplication.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testApplication.getUrlCallback()).isEqualTo(DEFAULT_URL_CALLBACK);
        assertThat(testApplication.getOrgUnit()).isEqualTo(DEFAULT_ORG_UNIT);
        assertThat(testApplication.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testApplication.getUrlNotify()).isEqualTo(DEFAULT_URL_NOTIFY);
        assertThat(testApplication.getNotifyUsername()).isEqualTo(DEFAULT_NOTIFY_USERNAME);
        assertThat(testApplication.getNotifyPassword()).isEqualTo(DEFAULT_NOTIFY_PASSWORD);
        assertThat(testApplication.getAllOrg()).isEqualTo(DEFAULT_ALLORG);
        assertThat(testApplication.getHelpURL()).isEqualTo(DEFAULT_HELP_URL);
        assertThat(testApplication.getHelpMail()).isEqualTo(DEFAULT_HELP_MAIL);
        assertThat(testApplication.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllApplications() throws Exception {
        // Initialize the database
        applicationRepository.saveAndFlush(application);

        // Get all the applications
        restApplicationMockMvc.perform(get("/api/applications"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(application.getId().intValue())))
                .andExpect(jsonPath("$.[*].applicationID").value(hasItem(DEFAULT_APPLICATION_ID.toString())))
                .andExpect(jsonPath("$.[*].applicationSecret").value(hasItem(DEFAULT_APPLICATION_SECRET.toString())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].urlCallback").value(hasItem(DEFAULT_URL_CALLBACK.toString())))
                .andExpect(jsonPath("$.[*].orgUnit").value(hasItem(DEFAULT_ORG_UNIT.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].urlNotify").value(hasItem(DEFAULT_URL_NOTIFY.toString())))
                .andExpect(jsonPath("$.[*].notifyUsername").value(hasItem(DEFAULT_NOTIFY_USERNAME.toString())))
                .andExpect(jsonPath("$.[*].notifyPassword").value(hasItem(DEFAULT_NOTIFY_PASSWORD.toString())))
                .andExpect(jsonPath("$.[*].allOrg").value(hasItem(DEFAULT_ALLORG.booleanValue())))
                .andExpect(jsonPath("$.[*].helpURL").value(hasItem(DEFAULT_HELP_URL.toString())))
                .andExpect(jsonPath("$.[*].helpMail").value(hasItem(DEFAULT_HELP_MAIL.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getApplication() throws Exception {
        // Initialize the database
        applicationRepository.saveAndFlush(application);

        // Get the application
        restApplicationMockMvc.perform(get("/api/applications/{id}", application.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(application.getId().intValue()))
            .andExpect(jsonPath("$.applicationID").value(DEFAULT_APPLICATION_ID.toString()))
            .andExpect(jsonPath("$.applicationSecret").value(DEFAULT_APPLICATION_SECRET.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.urlCallback").value(DEFAULT_URL_CALLBACK.toString()))
            .andExpect(jsonPath("$.orgUnit").value(DEFAULT_ORG_UNIT.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.urlNotify").value(DEFAULT_URL_NOTIFY.toString()))
            .andExpect(jsonPath("$.notifyUsername").value(DEFAULT_NOTIFY_USERNAME.toString()))
            .andExpect(jsonPath("$.notifyPassword").value(DEFAULT_NOTIFY_PASSWORD.toString()))
            .andExpect(jsonPath("$.allOrg").value(DEFAULT_ALLORG.booleanValue()))
            .andExpect(jsonPath("$.helpURL").value(DEFAULT_HELP_URL.toString()))
            .andExpect(jsonPath("$.helpMail").value(DEFAULT_HELP_MAIL.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingApplication() throws Exception {
        // Get the application
        restApplicationMockMvc.perform(get("/api/applications/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApplication() throws Exception {
        // Initialize the database
        applicationRepository.saveAndFlush(application);

		int databaseSizeBeforeUpdate = applicationRepository.findAll().size();

        // Update the application
        application.setApplicationID(UPDATED_APPLICATION_ID);
        application.setApplicationSecret(UPDATED_APPLICATION_SECRET);
        application.setName(UPDATED_NAME);
        application.setUrlCallback(UPDATED_URL_CALLBACK);
        application.setOrgUnit(UPDATED_ORG_UNIT);
        application.setEmail(UPDATED_EMAIL);
        application.setUrlNotify(UPDATED_URL_NOTIFY);
        application.setNotifyUsername(UPDATED_NOTIFY_USERNAME);
        application.setNotifyPassword(UPDATED_NOTIFY_PASSWORD);
        application.setAllOrg(UPDATED_ALLORG);
        application.setHelpURL(UPDATED_HELP_URL);
        application.setHelpMail(UPDATED_HELP_MAIL);
        application.setDescription(UPDATED_DESCRIPTION);
        restApplicationMockMvc.perform(put("/api/applications")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(application)))
                .andExpect(status().isOk());

        // Validate the Application in the database
        List<Application> applications = applicationRepository.findAll();
        assertThat(applications).hasSize(databaseSizeBeforeUpdate);
        Application testApplication = applications.get(applications.size() - 1);
        assertThat(testApplication.getApplicationID()).isEqualTo(UPDATED_APPLICATION_ID);
        assertThat(testApplication.getApplicationSecret()).isEqualTo(UPDATED_APPLICATION_SECRET);
        assertThat(testApplication.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testApplication.getUrlCallback()).isEqualTo(UPDATED_URL_CALLBACK);
        assertThat(testApplication.getOrgUnit()).isEqualTo(UPDATED_ORG_UNIT);
        assertThat(testApplication.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testApplication.getUrlNotify()).isEqualTo(UPDATED_URL_NOTIFY);
        assertThat(testApplication.getNotifyUsername()).isEqualTo(UPDATED_NOTIFY_USERNAME);
        assertThat(testApplication.getNotifyPassword()).isEqualTo(UPDATED_NOTIFY_PASSWORD);
        assertThat(testApplication.getAllOrg()).isEqualTo(UPDATED_ALLORG);
        assertThat(testApplication.getHelpURL()).isEqualTo(UPDATED_HELP_URL);
        assertThat(testApplication.getHelpMail()).isEqualTo(UPDATED_HELP_MAIL);
        assertThat(testApplication.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteApplication() throws Exception {
        // Initialize the database
        applicationRepository.saveAndFlush(application);

		int databaseSizeBeforeDelete = applicationRepository.findAll().size();

        // Get the application
        restApplicationMockMvc.perform(delete("/api/applications/{id}", application.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Application> applications = applicationRepository.findAll();
        assertThat(applications).hasSize(databaseSizeBeforeDelete - 1);
    }
}
