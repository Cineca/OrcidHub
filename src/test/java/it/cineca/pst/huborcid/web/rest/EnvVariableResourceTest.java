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
import it.cineca.pst.huborcid.domain.EnvVariable;
import it.cineca.pst.huborcid.repository.EnvVariableRepository;

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
 * Test class for the EnvVariableResource REST controller.
 *
 * @see EnvVariableResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class EnvVariableResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_VARIABLE_VALUE = "SAMPLE_TEXT";
    private static final String UPDATED_VARIABLE_VALUE = "UPDATED_TEXT";

    @Inject
    private EnvVariableRepository envVariableRepository;

    private MockMvc restEnvVariableMockMvc;

    private EnvVariable envVariable;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EnvVariableResource envVariableResource = new EnvVariableResource();
        ReflectionTestUtils.setField(envVariableResource, "envVariableRepository", envVariableRepository);
        this.restEnvVariableMockMvc = MockMvcBuilders.standaloneSetup(envVariableResource).build();
    }

    @Before
    public void initTest() {
        envVariable = new EnvVariable();
        envVariable.setName(DEFAULT_NAME);
        envVariable.setVariableValue(DEFAULT_VARIABLE_VALUE);
    }

    @Test
    @Transactional
    public void createEnvVariable() throws Exception {
        int databaseSizeBeforeCreate = envVariableRepository.findAll().size();

        // Create the EnvVariable
        restEnvVariableMockMvc.perform(post("/api/envVariables")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(envVariable)))
                .andExpect(status().isCreated());

        // Validate the EnvVariable in the database
        List<EnvVariable> envVariables = envVariableRepository.findAll();
        assertThat(envVariables).hasSize(databaseSizeBeforeCreate + 1);
        EnvVariable testEnvVariable = envVariables.get(envVariables.size() - 1);
        assertThat(testEnvVariable.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEnvVariable.getVariableValue()).isEqualTo(DEFAULT_VARIABLE_VALUE);
    }

    @Test
    @Transactional
    public void getAllEnvVariables() throws Exception {
        // Initialize the database
        envVariableRepository.saveAndFlush(envVariable);

        // Get all the envVariables
        restEnvVariableMockMvc.perform(get("/api/envVariables"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(envVariable.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].variableValue").value(hasItem(DEFAULT_VARIABLE_VALUE.toString())));
    }

    @Test
    @Transactional
    public void getEnvVariable() throws Exception {
        // Initialize the database
        envVariableRepository.saveAndFlush(envVariable);

        // Get the envVariable
        restEnvVariableMockMvc.perform(get("/api/envVariables/{id}", envVariable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(envVariable.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.variableValue").value(DEFAULT_VARIABLE_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnvVariable() throws Exception {
        // Get the envVariable
        restEnvVariableMockMvc.perform(get("/api/envVariables/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnvVariable() throws Exception {
        // Initialize the database
        envVariableRepository.saveAndFlush(envVariable);

		int databaseSizeBeforeUpdate = envVariableRepository.findAll().size();

        // Update the envVariable
        envVariable.setName(UPDATED_NAME);
        envVariable.setVariableValue(UPDATED_VARIABLE_VALUE);
        restEnvVariableMockMvc.perform(put("/api/envVariables")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(envVariable)))
                .andExpect(status().isOk());

        // Validate the EnvVariable in the database
        List<EnvVariable> envVariables = envVariableRepository.findAll();
        assertThat(envVariables).hasSize(databaseSizeBeforeUpdate);
        EnvVariable testEnvVariable = envVariables.get(envVariables.size() - 1);
        assertThat(testEnvVariable.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEnvVariable.getVariableValue()).isEqualTo(UPDATED_VARIABLE_VALUE);
    }

    @Test
    @Transactional
    public void deleteEnvVariable() throws Exception {
        // Initialize the database
        envVariableRepository.saveAndFlush(envVariable);

		int databaseSizeBeforeDelete = envVariableRepository.findAll().size();

        // Get the envVariable
        restEnvVariableMockMvc.perform(delete("/api/envVariables/{id}", envVariable.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<EnvVariable> envVariables = envVariableRepository.findAll();
        assertThat(envVariables).hasSize(databaseSizeBeforeDelete - 1);
    }
}
