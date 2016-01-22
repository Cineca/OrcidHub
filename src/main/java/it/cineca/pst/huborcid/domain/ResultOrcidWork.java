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
 * A ResultOrcidWork.
 */
@Entity
@Table(name = "ORCID_RESULT_WORK")
public class ResultOrcidWork extends AbstractAuditingEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2951046712651694321L;

	@Id
	@SequenceGenerator(name="SEQ_RESULTORCIDWORK_ID_GENERATOR", sequenceName="SEQ_RESULTORCIDWORK")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_RESULTORCIDWORK_ID_GENERATOR")
    private Long id;

    @Column(name = "file_name_upload")
    private String fileNameUpload;

    @Column(name = "status")
    private String status;

    @Column(name = "with_errors")
    private Boolean withErrors;

    @Column(name = "file_result")
    @Lob
    private byte[] fileResult;

    @ManyToOne
    private Application application;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileNameUpload() {
        return fileNameUpload;
    }

    public void setFileNameUpload(String fileNameUpload) {
        this.fileNameUpload = fileNameUpload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getWithErrors() {
        return withErrors;
    }

    public void setWithErrors(Boolean withErrors) {
        this.withErrors = withErrors;
    }

    public byte[] getFileResult() {
        return fileResult;
    }

    public void setFileResult(byte[] fileResult) {
        this.fileResult = fileResult;
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

        ResultOrcidWork resultOrcidWork = (ResultOrcidWork) o;

        if ( ! Objects.equals(id, resultOrcidWork.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ResultOrcidWork{" +
                "id=" + id +
                ", fileNameUpload='" + fileNameUpload + "'" +
                ", status='" + status + "'" +
                ", withErrors='" + withErrors + "'" +
                ", fileResult='" + fileResult + "'" +
                '}';
    }
}
