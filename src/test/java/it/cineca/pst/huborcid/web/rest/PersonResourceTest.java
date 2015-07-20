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
import it.cineca.pst.huborcid.domain.Person;
import it.cineca.pst.huborcid.repository.PersonRepository;

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
 * Test class for the PersonResource REST controller.
 *
 * @see PersonResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PersonResourceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private static final String DEFAULT_LOCAL_ID = "SAMPLE_TEXT";
    private static final String UPDATED_LOCAL_ID = "UPDATED_TEXT";
    private static final String DEFAULT_FIRST_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_FIRST_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_LAST_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_LAST_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_EMAIL = "SAMPLE_TEXT";
    private static final String UPDATED_EMAIL = "UPDATED_TEXT";
    private static final String DEFAULT_ORCID = "SAMPLE_TEXT";
    private static final String UPDATED_ORCID = "UPDATED_TEXT";

    private static final DateTime DEFAULT_ORCID_RELEASE_DATE = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_ORCID_RELEASE_DATE = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_ORCID_RELEASE_DATE_STR = dateTimeFormatter.print(DEFAULT_ORCID_RELEASE_DATE);

    @Inject
    private PersonRepository personRepository;

    private MockMvc restPersonMockMvc;

    private Person person;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PersonResource personResource = new PersonResource();
        ReflectionTestUtils.setField(personResource, "personRepository", personRepository);
        this.restPersonMockMvc = MockMvcBuilders.standaloneSetup(personResource).build();
    }

    @Before
    public void initTest() {
        person = new Person();
        person.setLocalID(DEFAULT_LOCAL_ID);
        person.setFirstName(DEFAULT_FIRST_NAME);
        person.setLastName(DEFAULT_LAST_NAME);
        person.setEmail(DEFAULT_EMAIL);
        person.setOrcid(DEFAULT_ORCID);
        person.setOrcidReleaseDate(DEFAULT_ORCID_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void createPerson() throws Exception {
        int databaseSizeBeforeCreate = personRepository.findAll().size();

        // Create the Person
        restPersonMockMvc.perform(post("/api/persons")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(person)))
                .andExpect(status().isCreated());

        // Validate the Person in the database
        List<Person> persons = personRepository.findAll();
        assertThat(persons).hasSize(databaseSizeBeforeCreate + 1);
        Person testPerson = persons.get(persons.size() - 1);
        assertThat(testPerson.getLocalID()).isEqualTo(DEFAULT_LOCAL_ID);
        assertThat(testPerson.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testPerson.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testPerson.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPerson.getOrcid()).isEqualTo(DEFAULT_ORCID);
        assertThat(testPerson.getOrcidReleaseDate().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_ORCID_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void getAllPersons() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the persons
        restPersonMockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(person.getId().intValue())))
                .andExpect(jsonPath("$.[*].localID").value(hasItem(DEFAULT_LOCAL_ID.toString())))
                .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
                .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].orcid").value(hasItem(DEFAULT_ORCID.toString())))
                .andExpect(jsonPath("$.[*].orcidReleaseDate").value(hasItem(DEFAULT_ORCID_RELEASE_DATE_STR)));
    }

    @Test
    @Transactional
    public void getPerson() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get the person
        restPersonMockMvc.perform(get("/api/persons/{id}", person.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(person.getId().intValue()))
            .andExpect(jsonPath("$.localID").value(DEFAULT_LOCAL_ID.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.orcid").value(DEFAULT_ORCID.toString()))
            .andExpect(jsonPath("$.orcidReleaseDate").value(DEFAULT_ORCID_RELEASE_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingPerson() throws Exception {
        // Get the person
        restPersonMockMvc.perform(get("/api/persons/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerson() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

		int databaseSizeBeforeUpdate = personRepository.findAll().size();

        // Update the person
        person.setLocalID(UPDATED_LOCAL_ID);
        person.setFirstName(UPDATED_FIRST_NAME);
        person.setLastName(UPDATED_LAST_NAME);
        person.setEmail(UPDATED_EMAIL);
        person.setOrcid(UPDATED_ORCID);
        person.setOrcidReleaseDate(UPDATED_ORCID_RELEASE_DATE);
        restPersonMockMvc.perform(put("/api/persons")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(person)))
                .andExpect(status().isOk());

        // Validate the Person in the database
        List<Person> persons = personRepository.findAll();
        assertThat(persons).hasSize(databaseSizeBeforeUpdate);
        Person testPerson = persons.get(persons.size() - 1);
        assertThat(testPerson.getLocalID()).isEqualTo(UPDATED_LOCAL_ID);
        assertThat(testPerson.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testPerson.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testPerson.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPerson.getOrcid()).isEqualTo(UPDATED_ORCID);
        assertThat(testPerson.getOrcidReleaseDate().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_ORCID_RELEASE_DATE);
    }

    @Test
    @Transactional
    public void deletePerson() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

		int databaseSizeBeforeDelete = personRepository.findAll().size();

        // Get the person
        restPersonMockMvc.perform(delete("/api/persons/{id}", person.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Person> persons = personRepository.findAll();
        assertThat(persons).hasSize(databaseSizeBeforeDelete - 1);
    }
}
