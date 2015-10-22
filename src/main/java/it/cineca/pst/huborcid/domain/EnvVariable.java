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
 * A EnvVariable.
 */
@Entity
@Table(name = "ORCID_ENV_VARIABLE")
public class EnvVariable extends AbstractAuditingEntity implements Serializable {

	@Id
	@SequenceGenerator(name="SEQ_ENVVAR_ID_GENERATOR", sequenceName="SEQ_ENVVAR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ENVVAR_ID_GENERATOR")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "variable_value")
    private String variableValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(String variableValue) {
        this.variableValue = variableValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnvVariable envVariable = (EnvVariable) o;

        if ( ! Objects.equals(id, envVariable.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EnvVariable{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", variableValue='" + variableValue + "'" +
                '}';
    }
}
