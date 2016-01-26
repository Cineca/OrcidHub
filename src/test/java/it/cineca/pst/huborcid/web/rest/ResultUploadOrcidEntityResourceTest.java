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
import it.cineca.pst.huborcid.domain.ResultUploadOrcidEntity;
import it.cineca.pst.huborcid.repository.ResultUploadOrcidEntityRepository;

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
 * Test class for the ResultUploadOrcidEntityResource REST controller.
 *
 * @see ResultUploadOrcidEntityResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ResultUploadOrcidEntityResourceTest {

    private static final String DEFAULT_FILE_NAME_UPLOAD = "SAMPLE_TEXT";
    private static final String UPDATED_FILE_NAME_UPLOAD = "UPDATED_TEXT";
    private static final String DEFAULT_STATUS = "SAMPLE_TEXT";
    private static final String UPDATED_STATUS = "UPDATED_TEXT";

    private static final Boolean DEFAULT_WITH_ERRORS = false;
    private static final Boolean UPDATED_WITH_ERRORS = true;
    private static final String DEFAULT_ENTITY_TYPE = "SAMPLE_TEXT";
    private static final String UPDATED_ENTITY_TYPE = "UPDATED_TEXT";

    @Inject
    private ResultUploadOrcidEntityRepository resultUploadOrcidEntityRepository;

    private MockMvc restResultUploadOrcidEntityMockMvc;

    private ResultUploadOrcidEntity resultUploadOrcidEntity;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ResultUploadOrcidEntityResource resultUploadOrcidEntityResource = new ResultUploadOrcidEntityResource();
        ReflectionTestUtils.setField(resultUploadOrcidEntityResource, "resultUploadOrcidEntityRepository", resultUploadOrcidEntityRepository);
        this.restResultUploadOrcidEntityMockMvc = MockMvcBuilders.standaloneSetup(resultUploadOrcidEntityResource).build();
    }

    @Before
    public void initTest() {
        resultUploadOrcidEntity = new ResultUploadOrcidEntity();
        resultUploadOrcidEntity.setFileNameUpload(DEFAULT_FILE_NAME_UPLOAD);
        resultUploadOrcidEntity.setStatus(DEFAULT_STATUS);
        resultUploadOrcidEntity.setWithErrors(DEFAULT_WITH_ERRORS);
        resultUploadOrcidEntity.setEntityType(DEFAULT_ENTITY_TYPE);
    }

    @Test
    @Transactional
    public void createResultUploadOrcidEntity() throws Exception {
        int databaseSizeBeforeCreate = resultUploadOrcidEntityRepository.findAll().size();

        // Create the ResultUploadOrcidEntity
        restResultUploadOrcidEntityMockMvc.perform(post("/api/resultUploadOrcidEntitys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(resultUploadOrcidEntity)))
                .andExpect(status().isCreated());

        // Validate the ResultUploadOrcidEntity in the database
        List<ResultUploadOrcidEntity> resultUploadOrcidEntitys = resultUploadOrcidEntityRepository.findAll();
        assertThat(resultUploadOrcidEntitys).hasSize(databaseSizeBeforeCreate + 1);
        ResultUploadOrcidEntity testResultUploadOrcidEntity = resultUploadOrcidEntitys.get(resultUploadOrcidEntitys.size() - 1);
        assertThat(testResultUploadOrcidEntity.getFileNameUpload()).isEqualTo(DEFAULT_FILE_NAME_UPLOAD);
        assertThat(testResultUploadOrcidEntity.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testResultUploadOrcidEntity.getWithErrors()).isEqualTo(DEFAULT_WITH_ERRORS);
        assertThat(testResultUploadOrcidEntity.getEntityType()).isEqualTo(DEFAULT_ENTITY_TYPE);
    }

    @Test
    @Transactional
    public void getAllResultUploadOrcidEntitys() throws Exception {
        // Initialize the database
        resultUploadOrcidEntityRepository.saveAndFlush(resultUploadOrcidEntity);

        // Get all the resultUploadOrcidEntitys
        restResultUploadOrcidEntityMockMvc.perform(get("/api/resultUploadOrcidEntitys"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(resultUploadOrcidEntity.getId().intValue())))
                .andExpect(jsonPath("$.[*].fileNameUpload").value(hasItem(DEFAULT_FILE_NAME_UPLOAD.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
                .andExpect(jsonPath("$.[*].withErrors").value(hasItem(DEFAULT_WITH_ERRORS.booleanValue())))
                .andExpect(jsonPath("$.[*].entityType").value(hasItem(DEFAULT_ENTITY_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getResultUploadOrcidEntity() throws Exception {
        // Initialize the database
        resultUploadOrcidEntityRepository.saveAndFlush(resultUploadOrcidEntity);

        // Get the resultUploadOrcidEntity
        restResultUploadOrcidEntityMockMvc.perform(get("/api/resultUploadOrcidEntitys/{id}", resultUploadOrcidEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(resultUploadOrcidEntity.getId().intValue()))
            .andExpect(jsonPath("$.fileNameUpload").value(DEFAULT_FILE_NAME_UPLOAD.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.withErrors").value(DEFAULT_WITH_ERRORS.booleanValue()))
            .andExpect(jsonPath("$.entityType").value(DEFAULT_ENTITY_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingResultUploadOrcidEntity() throws Exception {
        // Get the resultUploadOrcidEntity
        restResultUploadOrcidEntityMockMvc.perform(get("/api/resultUploadOrcidEntitys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResultUploadOrcidEntity() throws Exception {
        // Initialize the database
        resultUploadOrcidEntityRepository.saveAndFlush(resultUploadOrcidEntity);

		int databaseSizeBeforeUpdate = resultUploadOrcidEntityRepository.findAll().size();

        // Update the resultUploadOrcidEntity
        resultUploadOrcidEntity.setFileNameUpload(UPDATED_FILE_NAME_UPLOAD);
        resultUploadOrcidEntity.setStatus(UPDATED_STATUS);
        resultUploadOrcidEntity.setWithErrors(UPDATED_WITH_ERRORS);
        resultUploadOrcidEntity.setEntityType(UPDATED_ENTITY_TYPE);
        restResultUploadOrcidEntityMockMvc.perform(put("/api/resultUploadOrcidEntitys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(resultUploadOrcidEntity)))
                .andExpect(status().isOk());

        // Validate the ResultUploadOrcidEntity in the database
        List<ResultUploadOrcidEntity> resultUploadOrcidEntitys = resultUploadOrcidEntityRepository.findAll();
        assertThat(resultUploadOrcidEntitys).hasSize(databaseSizeBeforeUpdate);
        ResultUploadOrcidEntity testResultUploadOrcidEntity = resultUploadOrcidEntitys.get(resultUploadOrcidEntitys.size() - 1);
        assertThat(testResultUploadOrcidEntity.getFileNameUpload()).isEqualTo(UPDATED_FILE_NAME_UPLOAD);
        assertThat(testResultUploadOrcidEntity.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testResultUploadOrcidEntity.getWithErrors()).isEqualTo(UPDATED_WITH_ERRORS);
        assertThat(testResultUploadOrcidEntity.getEntityType()).isEqualTo(UPDATED_ENTITY_TYPE);
    }

    @Test
    @Transactional
    public void deleteResultUploadOrcidEntity() throws Exception {
        // Initialize the database
        resultUploadOrcidEntityRepository.saveAndFlush(resultUploadOrcidEntity);

		int databaseSizeBeforeDelete = resultUploadOrcidEntityRepository.findAll().size();

        // Get the resultUploadOrcidEntity
        restResultUploadOrcidEntityMockMvc.perform(delete("/api/resultUploadOrcidEntitys/{id}", resultUploadOrcidEntity.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ResultUploadOrcidEntity> resultUploadOrcidEntitys = resultUploadOrcidEntityRepository.findAll();
        assertThat(resultUploadOrcidEntitys).hasSize(databaseSizeBeforeDelete - 1);
    }
}
