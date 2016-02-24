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
package it.cineca.pst.huborcid.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A PersonBio.
 */
@Entity
@Table(name = "ORCID_PERSON_BIO")
public class PersonBio extends AbstractAuditingEntity implements Serializable {

    @Id
    @SequenceGenerator(name="SEQ_PERSONBIO_ID_GENERATOR", sequenceName="SEQ_PERSONBIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PERSONBIO_ID_GENERATOR")
    private Long id;

    @Column(name = "biography")
    private String biography;

    @Column(name = "researcher_urls")
    private String researcher_urls;

    @Column(name = "external_identifiers")
    private String external_identifiers;

    @OneToOne(fetch = FetchType.LAZY)
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getResearcher_urls() {
        return researcher_urls;
    }

    public void setResearcher_urls(String researcher_urls) {
        this.researcher_urls = researcher_urls;
    }

    public String getExternal_identifiers() {
        return external_identifiers;
    }

    public void setExternal_identifiers(String external_identifiers) {
        this.external_identifiers = external_identifiers;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonBio personBio = (PersonBio) o;

        if ( ! Objects.equals(id, personBio.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PersonBio{" +
                "id=" + id +
                ", biography='" + biography + "'" +
                ", researcher_urls='" + researcher_urls + "'" +
                ", external_identifiers='" + external_identifiers + "'" +
                '}';
    }
}
