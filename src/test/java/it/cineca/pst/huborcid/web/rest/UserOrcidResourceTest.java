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
import it.cineca.pst.huborcid.domain.UserOrcid;
import it.cineca.pst.huborcid.repository.UserOrcidRepository;

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
 * Test class for the UserOrcidResource REST controller.
 *
 * @see UserOrcidResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class UserOrcidResourceTest {

    private static final String DEFAULT_USERNAME = "SAMPLE_TEXT";
    private static final String UPDATED_USERNAME = "UPDATED_TEXT";
    private static final String DEFAULT_PASSWORD_PLAIN = "SAMPLE_TEXT";
    private static final String UPDATED_PASSWORD_PLAIN = "UPDATED_TEXT";
    private static final String DEFAULT_PASSWORD_HASH = "SAMPLE_TEXT";
    private static final String UPDATED_PASSWORD_HASH = "UPDATED_TEXT";

    @Inject
    private UserOrcidRepository userOrcidRepository;

    private MockMvc restUserOrcidMockMvc;

    private UserOrcid userOrcid;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserOrcidResource userOrcidResource = new UserOrcidResource();
        ReflectionTestUtils.setField(userOrcidResource, "userOrcidRepository", userOrcidRepository);
        this.restUserOrcidMockMvc = MockMvcBuilders.standaloneSetup(userOrcidResource).build();
    }

    @Before
    public void initTest() {
        userOrcid = new UserOrcid();
        userOrcid.setUsername(DEFAULT_USERNAME);
        userOrcid.setPasswordPlain(DEFAULT_PASSWORD_PLAIN);
        userOrcid.setPasswordHash(DEFAULT_PASSWORD_HASH);
    }

    @Test
    @Transactional
    public void createUserOrcid() throws Exception {
        int databaseSizeBeforeCreate = userOrcidRepository.findAll().size();

        // Create the UserOrcid
        restUserOrcidMockMvc.perform(post("/api/userOrcids")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userOrcid)))
                .andExpect(status().isCreated());

        // Validate the UserOrcid in the database
        List<UserOrcid> userOrcids = userOrcidRepository.findAll();
        assertThat(userOrcids).hasSize(databaseSizeBeforeCreate + 1);
        UserOrcid testUserOrcid = userOrcids.get(userOrcids.size() - 1);
        assertThat(testUserOrcid.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testUserOrcid.getPasswordPlain()).isEqualTo(DEFAULT_PASSWORD_PLAIN);
        assertThat(testUserOrcid.getPasswordHash()).isEqualTo(DEFAULT_PASSWORD_HASH);
    }

    @Test
    @Transactional
    public void getAllUserOrcids() throws Exception {
        // Initialize the database
        userOrcidRepository.saveAndFlush(userOrcid);

        // Get all the userOrcids
        restUserOrcidMockMvc.perform(get("/api/userOrcids"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(userOrcid.getId().intValue())))
                .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
                .andExpect(jsonPath("$.[*].passwordPlain").value(hasItem(DEFAULT_PASSWORD_PLAIN.toString())))
                .andExpect(jsonPath("$.[*].passwordHash").value(hasItem(DEFAULT_PASSWORD_HASH.toString())));
    }

    @Test
    @Transactional
    public void getUserOrcid() throws Exception {
        // Initialize the database
        userOrcidRepository.saveAndFlush(userOrcid);

        // Get the userOrcid
        restUserOrcidMockMvc.perform(get("/api/userOrcids/{id}", userOrcid.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(userOrcid.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.passwordPlain").value(DEFAULT_PASSWORD_PLAIN.toString()))
            .andExpect(jsonPath("$.passwordHash").value(DEFAULT_PASSWORD_HASH.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserOrcid() throws Exception {
        // Get the userOrcid
        restUserOrcidMockMvc.perform(get("/api/userOrcids/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserOrcid() throws Exception {
        // Initialize the database
        userOrcidRepository.saveAndFlush(userOrcid);

		int databaseSizeBeforeUpdate = userOrcidRepository.findAll().size();

        // Update the userOrcid
        userOrcid.setUsername(UPDATED_USERNAME);
        userOrcid.setPasswordPlain(UPDATED_PASSWORD_PLAIN);
        userOrcid.setPasswordHash(UPDATED_PASSWORD_HASH);
        restUserOrcidMockMvc.perform(put("/api/userOrcids")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userOrcid)))
                .andExpect(status().isOk());

        // Validate the UserOrcid in the database
        List<UserOrcid> userOrcids = userOrcidRepository.findAll();
        assertThat(userOrcids).hasSize(databaseSizeBeforeUpdate);
        UserOrcid testUserOrcid = userOrcids.get(userOrcids.size() - 1);
        assertThat(testUserOrcid.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testUserOrcid.getPasswordPlain()).isEqualTo(UPDATED_PASSWORD_PLAIN);
        assertThat(testUserOrcid.getPasswordHash()).isEqualTo(UPDATED_PASSWORD_HASH);
    }

    @Test
    @Transactional
    public void deleteUserOrcid() throws Exception {
        // Initialize the database
        userOrcidRepository.saveAndFlush(userOrcid);

		int databaseSizeBeforeDelete = userOrcidRepository.findAll().size();

        // Get the userOrcid
        restUserOrcidMockMvc.perform(delete("/api/userOrcids/{id}", userOrcid.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<UserOrcid> userOrcids = userOrcidRepository.findAll();
        assertThat(userOrcids).hasSize(databaseSizeBeforeDelete - 1);
    }
}
