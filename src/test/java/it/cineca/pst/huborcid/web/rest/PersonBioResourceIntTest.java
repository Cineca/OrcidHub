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
import it.cineca.pst.huborcid.domain.PersonBio;
import it.cineca.pst.huborcid.repository.PersonBioRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
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
 * Test class for the PersonBioResource REST controller.
 *
 * @see PersonBioResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PersonBioResourceIntTest {

    private static final String DEFAULT_BIOGRAPHY = "AAAAA";
    private static final String UPDATED_BIOGRAPHY = "BBBBB";
    private static final String DEFAULT_RESEARCHER_URLS = "AAAAA";
    private static final String UPDATED_RESEARCHER_URLS = "BBBBB";
    private static final String DEFAULT_EXTERNAL_IDENTIFIERS = "AAAAA";
    private static final String UPDATED_EXTERNAL_IDENTIFIERS = "BBBBB";

    @Inject
    private PersonBioRepository personBioRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restPersonBioMockMvc;

    private PersonBio personBio;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PersonBioResource personBioResource = new PersonBioResource();
        ReflectionTestUtils.setField(personBioResource, "personBioRepository", personBioRepository);
        this.restPersonBioMockMvc = MockMvcBuilders.standaloneSetup(personBioResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        personBio = new PersonBio();
        personBio.setBiography(DEFAULT_BIOGRAPHY);
        personBio.setResearcher_urls(DEFAULT_RESEARCHER_URLS);
        personBio.setExternal_identifiers(DEFAULT_EXTERNAL_IDENTIFIERS);
    }

    @Test
    @Transactional
    public void createPersonBio() throws Exception {
        int databaseSizeBeforeCreate = personBioRepository.findAll().size();

        // Create the PersonBio

        restPersonBioMockMvc.perform(post("/api/personBios")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(personBio)))
                .andExpect(status().isCreated());

        // Validate the PersonBio in the database
        List<PersonBio> personBios = personBioRepository.findAll();
        assertThat(personBios).hasSize(databaseSizeBeforeCreate + 1);
        PersonBio testPersonBio = personBios.get(personBios.size() - 1);
        assertThat(testPersonBio.getBiography()).isEqualTo(DEFAULT_BIOGRAPHY);
        assertThat(testPersonBio.getResearcher_urls()).isEqualTo(DEFAULT_RESEARCHER_URLS);
        assertThat(testPersonBio.getExternal_identifiers()).isEqualTo(DEFAULT_EXTERNAL_IDENTIFIERS);
    }

    @Test
    @Transactional
    public void getAllPersonBios() throws Exception {
        // Initialize the database
        personBioRepository.saveAndFlush(personBio);

        // Get all the personBios
        restPersonBioMockMvc.perform(get("/api/personBios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(personBio.getId().intValue())))
                .andExpect(jsonPath("$.[*].biography").value(hasItem(DEFAULT_BIOGRAPHY.toString())))
                .andExpect(jsonPath("$.[*].researcher_urls").value(hasItem(DEFAULT_RESEARCHER_URLS.toString())))
                .andExpect(jsonPath("$.[*].external_identifiers").value(hasItem(DEFAULT_EXTERNAL_IDENTIFIERS.toString())));
    }

    @Test
    @Transactional
    public void getPersonBio() throws Exception {
        // Initialize the database
        personBioRepository.saveAndFlush(personBio);

        // Get the personBio
        restPersonBioMockMvc.perform(get("/api/personBios/{id}", personBio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(personBio.getId().intValue()))
            .andExpect(jsonPath("$.biography").value(DEFAULT_BIOGRAPHY.toString()))
            .andExpect(jsonPath("$.researcher_urls").value(DEFAULT_RESEARCHER_URLS.toString()))
            .andExpect(jsonPath("$.external_identifiers").value(DEFAULT_EXTERNAL_IDENTIFIERS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPersonBio() throws Exception {
        // Get the personBio
        restPersonBioMockMvc.perform(get("/api/personBios/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonBio() throws Exception {
        // Initialize the database
        personBioRepository.saveAndFlush(personBio);

		int databaseSizeBeforeUpdate = personBioRepository.findAll().size();

        // Update the personBio
        personBio.setBiography(UPDATED_BIOGRAPHY);
        personBio.setResearcher_urls(UPDATED_RESEARCHER_URLS);
        personBio.setExternal_identifiers(UPDATED_EXTERNAL_IDENTIFIERS);

        restPersonBioMockMvc.perform(put("/api/personBios")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(personBio)))
                .andExpect(status().isOk());

        // Validate the PersonBio in the database
        List<PersonBio> personBios = personBioRepository.findAll();
        assertThat(personBios).hasSize(databaseSizeBeforeUpdate);
        PersonBio testPersonBio = personBios.get(personBios.size() - 1);
        assertThat(testPersonBio.getBiography()).isEqualTo(UPDATED_BIOGRAPHY);
        assertThat(testPersonBio.getResearcher_urls()).isEqualTo(UPDATED_RESEARCHER_URLS);
        assertThat(testPersonBio.getExternal_identifiers()).isEqualTo(UPDATED_EXTERNAL_IDENTIFIERS);
    }

    @Test
    @Transactional
    public void deletePersonBio() throws Exception {
        // Initialize the database
        personBioRepository.saveAndFlush(personBio);

		int databaseSizeBeforeDelete = personBioRepository.findAll().size();

        // Get the personBio
        restPersonBioMockMvc.perform(delete("/api/personBios/{id}", personBio.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PersonBio> personBios = personBioRepository.findAll();
        assertThat(personBios).hasSize(databaseSizeBeforeDelete - 1);
    }
}
