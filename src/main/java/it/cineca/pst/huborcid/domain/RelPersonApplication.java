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
 * A RelPersonApplication.
 */
@Entity
@Table(name = "ORCID_REL_PERSONA_APPLICATION")
public class RelPersonApplication extends AbstractAuditingEntity implements Serializable {

	@Id
	@SequenceGenerator(name="SEQ_RELPERSONAPPLICATION_ID_GENERATOR", sequenceName="SEQ_RELPERSONAPPLICATION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_RELPERSONAPPLICATION_ID_GENERATOR")
    private Long id;

    @Column(name = "oauth_access_token")
    private String oauthAccessToken;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "date_released")
    private DateTime dateReleased;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "date_denied")
    private DateTime dateDenied;

    @Column(name = "valid")
    private Boolean valid;

    @Column(name = "denied")
    private Boolean denied;
    
    @Column(name = "error_description")
    private String errorDescription;
    
    @Column(name = "notified")
    private Boolean notified;
    
    @Column(name = "last")
    private Boolean last;

    @Column(name = "custom")
    private Boolean custom;

    @Column(name = "error_not_description")
    private String errorNotDescription;

    @Column(name = "num_retry")
    private Integer numRetry;

    @ManyToOne
    private Person person;

    @ManyToOne
    private Application application;
    
    @ManyToOne
    private Token token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOauthAccessToken() {
        return oauthAccessToken;
    }

    public void setOauthAccessToken(String oauthAccessToken) {
        this.oauthAccessToken = oauthAccessToken;
    }

    public DateTime getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(DateTime dateReleased) {
        this.dateReleased = dateReleased;
    }

    public DateTime getDateDenied() {
        return dateDenied;
    }

    public void setDateDenied(DateTime dateDenied) {
        this.dateDenied = dateDenied;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Boolean getDenied() {
        return denied;
    }

    public void setDenied(Boolean denied) {
        this.denied = denied;
    }

    public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Boolean getNotified() {
		return notified;
	}

	public void setNotified(Boolean notified) {
		this.notified = notified;
	}

	public Boolean getLast() {
		return last;
	}

	public void setLast(Boolean last) {
		this.last = last;
	}

	public Boolean getCustom() {
		return custom;
	}

	public void setCustom(Boolean custom) {
		this.custom = custom;
	}

	public String getErrorNotDescription() {
		return errorNotDescription;
	}

	public void setErrorNotDescription(String errorNotDescription) {
		this.errorNotDescription = errorNotDescription;
	}

	public Integer getNumRetry() {
		return numRetry;
	}

	public void setNumRetry(Integer numRetry) {
		this.numRetry = numRetry;
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
    
    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RelPersonApplication relPersonApplication = (RelPersonApplication) o;

        if ( ! Objects.equals(id, relPersonApplication.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RelPersonApplication{" +
                "id=" + id +
                ", oauthAccessToken='" + oauthAccessToken + "'" +
                ", dateReleased='" + dateReleased + "'" +
                ", dateDenied='" + dateDenied + "'" +
                ", valid='" + valid + "'" +
                ", denied='" + denied + "'" +
                ", errorDescription='" + errorDescription + "'" +
                ", notified='" + notified + "'" +
                ", last='" + last + "'" +
                ", custom='" + custom + "'" +
                ", errorNotDescription='" + errorNotDescription + "'" +
                ", numRetry='" + numRetry + "'" +                
                '}';
    }
}
