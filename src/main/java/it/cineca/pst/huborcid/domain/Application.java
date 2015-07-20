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

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Application.
 */

@Entity
@Table(name = "ORCID_APPLICATION")
public class Application extends AbstractAuditingEntity implements Serializable {

	@Id
    @SequenceGenerator(name="SEQ_PERSON_ID_GENERATOR", sequenceName="SEQ_PERSON")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PERSON_ID_GENERATOR")
    private Long id;

    @Column(name = "application_id")
    private String applicationID;

    @Column(name = "application_secret")
    private String applicationSecret;

    @Column(name = "name")
    private String name;

    @Column(name = "url_callback")
    private String urlCallback;

    @Column(name = "org_unit")
    private String orgUnit;

    @Column(name = "email")
    private String email;

    @Column(name = "url_notify")
    private String urlNotify;

    @Column(name = "notify_username")
    private String notifyUsername;

    @Column(name = "notify_password")
    private String notifyPassword;

    @Column(name = "allOrg")
    private Boolean allOrg;
    
    @Column(name = "help_url")
    private String helpURL;

    @Column(name = "help_mail")
    private String helpMail;
    
    @Column(name = "description")
    private String description;

    @ManyToOne
    private UserOrcid authUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getApplicationSecret() {
        return applicationSecret;
    }

    public void setApplicationSecret(String applicationSecret) {
        this.applicationSecret = applicationSecret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlNotify() {
        return urlNotify;
    }

    public void setUrlNotify(String urlNotify) {
        this.urlNotify = urlNotify;
    }

    public String getNotifyUsername() {
        return notifyUsername;
    }

    public void setNotifyUsername(String notifyUsername) {
        this.notifyUsername = notifyUsername;
    }

    public String getNotifyPassword() {
        return notifyPassword;
    }

    public void setNotifyPassword(String notifyPassword) {
        this.notifyPassword = notifyPassword;
    }

    public Boolean getAllOrg() {
        return allOrg;
    }

    public void setAllOrg(Boolean allOrg) {
        this.allOrg = allOrg;
    }

    public String getHelpURL() {
		return helpURL;
	}

	public void setHelpURL(String helpURL) {
		this.helpURL = helpURL;
	}

	public String getHelpMail() {
		return helpMail;
	}

	public void setHelpMail(String helpMail) {
		this.helpMail = helpMail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserOrcid getAuthUser() {
        return authUser;
    }

    public void setAuthUser(UserOrcid userOrcid) {
        this.authUser = userOrcid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Application application = (Application) o;

        if ( ! Objects.equals(id, application.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", applicationID='" + applicationID + "'" +
                ", applicationSecret='" + applicationSecret + "'" +
                ", name='" + name + "'" +
                ", urlCallback='" + urlCallback + "'" +
                ", orgUnit='" + orgUnit + "'" +
                ", email='" + email + "'" +
                ", urlNotify='" + urlNotify + "'" +
                ", notifyUsername='" + notifyUsername + "'" +
                ", notifyPassword='" + notifyPassword + "'" +
                ", allOrg='" + allOrg + "'" +
                ", helpURL='" + helpURL + "'" +
                ", helpMail='" + helpMail + "'" +
                ", description='" + description + "'" +
                '}';
    }
}
