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
import it.cineca.pst.huborcid.domain.ResultOrcidWork;
import it.cineca.pst.huborcid.repository.ResultOrcidWorkRepository;

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
 * Test class for the ResultOrcidWorkResource REST controller.
 *
 * @see ResultOrcidWorkResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ResultOrcidWorkResourceTest {

    private static final String DEFAULT_FILE_NAME_UPLOAD = "SAMPLE_TEXT";
    private static final String UPDATED_FILE_NAME_UPLOAD = "UPDATED_TEXT";
    private static final String DEFAULT_STATUS = "SAMPLE_TEXT";
    private static final String UPDATED_STATUS = "UPDATED_TEXT";

    private static final Boolean DEFAULT_WITH_ERRORS = false;
    private static final Boolean UPDATED_WITH_ERRORS = true;

    @Inject
    private ResultOrcidWorkRepository resultOrcidWorkRepository;

    private MockMvc restResultOrcidWorkMockMvc;

    private ResultOrcidWork resultOrcidWork;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ResultOrcidWorkResource resultOrcidWorkResource = new ResultOrcidWorkResource();
        ReflectionTestUtils.setField(resultOrcidWorkResource, "resultOrcidWorkRepository", resultOrcidWorkRepository);
        this.restResultOrcidWorkMockMvc = MockMvcBuilders.standaloneSetup(resultOrcidWorkResource).build();
    }

    @Before
    public void initTest() {
        resultOrcidWork = new ResultOrcidWork();
        resultOrcidWork.setFileNameUpload(DEFAULT_FILE_NAME_UPLOAD);
        resultOrcidWork.setStatus(DEFAULT_STATUS);
        resultOrcidWork.setWithErrors(DEFAULT_WITH_ERRORS);
    }

    @Test
    @Transactional
    public void createResultOrcidWork() throws Exception {
        int databaseSizeBeforeCreate = resultOrcidWorkRepository.findAll().size();

        // Create the ResultOrcidWork
        restResultOrcidWorkMockMvc.perform(post("/api/resultOrcidWorks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(resultOrcidWork)))
                .andExpect(status().isCreated());

        // Validate the ResultOrcidWork in the database
        List<ResultOrcidWork> resultOrcidWorks = resultOrcidWorkRepository.findAll();
        assertThat(resultOrcidWorks).hasSize(databaseSizeBeforeCreate + 1);
        ResultOrcidWork testResultOrcidWork = resultOrcidWorks.get(resultOrcidWorks.size() - 1);
        assertThat(testResultOrcidWork.getFileNameUpload()).isEqualTo(DEFAULT_FILE_NAME_UPLOAD);
        assertThat(testResultOrcidWork.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testResultOrcidWork.getWithErrors()).isEqualTo(DEFAULT_WITH_ERRORS);
    }

    @Test
    @Transactional
    public void getAllResultOrcidWorks() throws Exception {
        // Initialize the database
        resultOrcidWorkRepository.saveAndFlush(resultOrcidWork);

        // Get all the resultOrcidWorks
        restResultOrcidWorkMockMvc.perform(get("/api/resultOrcidWorks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(resultOrcidWork.getId().intValue())))
                .andExpect(jsonPath("$.[*].fileNameUpload").value(hasItem(DEFAULT_FILE_NAME_UPLOAD.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
                .andExpect(jsonPath("$.[*].withErrors").value(hasItem(DEFAULT_WITH_ERRORS.booleanValue())));
    }

    @Test
    @Transactional
    public void getResultOrcidWork() throws Exception {
        // Initialize the database
        resultOrcidWorkRepository.saveAndFlush(resultOrcidWork);

        // Get the resultOrcidWork
        restResultOrcidWorkMockMvc.perform(get("/api/resultOrcidWorks/{id}", resultOrcidWork.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(resultOrcidWork.getId().intValue()))
            .andExpect(jsonPath("$.fileNameUpload").value(DEFAULT_FILE_NAME_UPLOAD.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.withErrors").value(DEFAULT_WITH_ERRORS.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingResultOrcidWork() throws Exception {
        // Get the resultOrcidWork
        restResultOrcidWorkMockMvc.perform(get("/api/resultOrcidWorks/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResultOrcidWork() throws Exception {
        // Initialize the database
        resultOrcidWorkRepository.saveAndFlush(resultOrcidWork);

		int databaseSizeBeforeUpdate = resultOrcidWorkRepository.findAll().size();

        // Update the resultOrcidWork
        resultOrcidWork.setFileNameUpload(UPDATED_FILE_NAME_UPLOAD);
        resultOrcidWork.setStatus(UPDATED_STATUS);
        resultOrcidWork.setWithErrors(UPDATED_WITH_ERRORS);
        restResultOrcidWorkMockMvc.perform(put("/api/resultOrcidWorks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(resultOrcidWork)))
                .andExpect(status().isOk());

        // Validate the ResultOrcidWork in the database
        List<ResultOrcidWork> resultOrcidWorks = resultOrcidWorkRepository.findAll();
        assertThat(resultOrcidWorks).hasSize(databaseSizeBeforeUpdate);
        ResultOrcidWork testResultOrcidWork = resultOrcidWorks.get(resultOrcidWorks.size() - 1);
        assertThat(testResultOrcidWork.getFileNameUpload()).isEqualTo(UPDATED_FILE_NAME_UPLOAD);
        assertThat(testResultOrcidWork.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testResultOrcidWork.getWithErrors()).isEqualTo(UPDATED_WITH_ERRORS);
    }

    @Test
    @Transactional
    public void deleteResultOrcidWork() throws Exception {
        // Initialize the database
        resultOrcidWorkRepository.saveAndFlush(resultOrcidWork);

		int databaseSizeBeforeDelete = resultOrcidWorkRepository.findAll().size();

        // Get the resultOrcidWork
        restResultOrcidWorkMockMvc.perform(delete("/api/resultOrcidWorks/{id}", resultOrcidWork.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ResultOrcidWork> resultOrcidWorks = resultOrcidWorkRepository.findAll();
        assertThat(resultOrcidWorks).hasSize(databaseSizeBeforeDelete - 1);
    }
}
