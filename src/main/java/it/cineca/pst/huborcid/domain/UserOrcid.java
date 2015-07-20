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
import java.util.Objects;

/**
 * A UserOrcid.
 */
@Entity
@Table(name = "ORCID_USER_ORCID")
public class UserOrcid extends AbstractAuditingEntity implements Serializable {

	@Id
	@SequenceGenerator(name="SEQ_USERORCID_ID_GENERATOR", sequenceName="SEQ_USERORCID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_USERORCID_ID_GENERATOR")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password_plain")
    private String passwordPlain;

    @Column(name = "password_hash")
    private String passwordHash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordPlain() {
        return passwordPlain;
    }

    public void setPasswordPlain(String passwordPlain) {
        this.passwordPlain = passwordPlain;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserOrcid userOrcid = (UserOrcid) o;

        if ( ! Objects.equals(id, userOrcid.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserOrcid{" +
                "id=" + id +
                ", username='" + username + "'" +
                ", passwordPlain='" + passwordPlain + "'" +
                ", passwordHash='" + passwordHash + "'" +
                '}';
    }
}
