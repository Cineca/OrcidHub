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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.cineca.pst.huborcid.domain.util.CustomDateTimeDeserializer;
import it.cineca.pst.huborcid.domain.util.CustomDateTimeSerializer;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Token.
 */
@Entity
@Table(name = "ORCID_TOKEN")
public class Token extends AbstractAuditingEntity implements Serializable {

	@Id
	@SequenceGenerator(name="SEQ_TOKEN_ID_GENERATOR", sequenceName="SEQ_TOKEN")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TOKEN_ID_GENERATOR")
    private Long id;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "date_released")
    private DateTime dateReleased;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "date_used")
    private DateTime dateUsed;
    
    @Column(name = "ott")
    private String ott;
    
    @Column(name = "url_callback")
    private String urlCallback;

    @Column(name = "org_unit")
    private String orgUnit;

    @ManyToOne
    private Person person;

    @ManyToOne
    private Application application;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(DateTime dateReleased) {
        this.dateReleased = dateReleased;
    }

    public DateTime getDateUsed() {
        return dateUsed;
    }

    public void setDateUsed(DateTime dateUsed) {
        this.dateUsed = dateUsed;
    }

    public String getOtt() {
        return ott;
    }

    public void setOtt(String ott) {
        this.ott = ott;
    }

    public String getUrlCallback() {
		return urlCallback;
	}

	public void setUrlCallback(String urlCallback) {
		this.urlCallback = urlCallback;
	}

	public String getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}

	public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Token token = (Token) o;

        if ( ! Objects.equals(id, token.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", dateReleased='" + dateReleased + "'" +
                ", dateUsed='" + dateUsed + "'" +
                ", ott='" + ott + "'" +
                ", urlCallback='" + urlCallback + "'" +
                ", orgUnit='" + orgUnit + "'" +
                '}';
    }
}
