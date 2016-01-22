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
//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.01.16 alle 12:48:36 PM CET 
//


package org.orcid.ns.orcid;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}application-orcid"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}application-name"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}application-website"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}approval-date"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}scope-paths"/>
 *         &lt;element name="application-group-orcid" type="{http://www.orcid.org/ns/orcid}orcid-id"/>
 *         &lt;element name="application-group-name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "applicationOrcid",
    "applicationName",
    "applicationWebsite",
    "approvalDate",
    "scopePaths",
    "applicationGroupOrcid",
    "applicationGroupName"
})
@XmlRootElement(name = "application-summary")
public class ApplicationSummary {

    @XmlElement(name = "application-orcid", required = true)
    protected OrcidId applicationOrcid;
    @XmlElement(name = "application-name", required = true)
    protected ApplicationName applicationName;
    @XmlElement(name = "application-website", required = true)
    protected ApplicationWebsite applicationWebsite;
    @XmlElement(name = "approval-date", required = true)
    protected ApprovalDate approvalDate;
    @XmlElement(name = "scope-paths", required = true)
    protected ScopePaths scopePaths;
    @XmlElement(name = "application-group-orcid", required = true)
    protected OrcidId applicationGroupOrcid;
    @XmlElement(name = "application-group-name", required = true)
    protected String applicationGroupName;

    /**
     * Recupera il valore della proprietà applicationOrcid.
     * 
     * @return
     *     possible object is
     *     {@link OrcidId }
     *     
     */
    public OrcidId getApplicationOrcid() {
        return applicationOrcid;
    }

    /**
     * Imposta il valore della proprietà applicationOrcid.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidId }
     *     
     */
    public void setApplicationOrcid(OrcidId value) {
        this.applicationOrcid = value;
    }

    /**
     * Recupera il valore della proprietà applicationName.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationName }
     *     
     */
    public ApplicationName getApplicationName() {
        return applicationName;
    }

    /**
     * Imposta il valore della proprietà applicationName.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationName }
     *     
     */
    public void setApplicationName(ApplicationName value) {
        this.applicationName = value;
    }

    /**
     * Recupera il valore della proprietà applicationWebsite.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationWebsite }
     *     
     */
    public ApplicationWebsite getApplicationWebsite() {
        return applicationWebsite;
    }

    /**
     * Imposta il valore della proprietà applicationWebsite.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationWebsite }
     *     
     */
    public void setApplicationWebsite(ApplicationWebsite value) {
        this.applicationWebsite = value;
    }

    /**
     * Recupera il valore della proprietà approvalDate.
     * 
     * @return
     *     possible object is
     *     {@link ApprovalDate }
     *     
     */
    public ApprovalDate getApprovalDate() {
        return approvalDate;
    }

    /**
     * Imposta il valore della proprietà approvalDate.
     * 
     * @param value
     *     allowed object is
     *     {@link ApprovalDate }
     *     
     */
    public void setApprovalDate(ApprovalDate value) {
        this.approvalDate = value;
    }

    /**
     * Recupera il valore della proprietà scopePaths.
     * 
     * @return
     *     possible object is
     *     {@link ScopePaths }
     *     
     */
    public ScopePaths getScopePaths() {
        return scopePaths;
    }

    /**
     * Imposta il valore della proprietà scopePaths.
     * 
     * @param value
     *     allowed object is
     *     {@link ScopePaths }
     *     
     */
    public void setScopePaths(ScopePaths value) {
        this.scopePaths = value;
    }

    /**
     * Recupera il valore della proprietà applicationGroupOrcid.
     * 
     * @return
     *     possible object is
     *     {@link OrcidId }
     *     
     */
    public OrcidId getApplicationGroupOrcid() {
        return applicationGroupOrcid;
    }

    /**
     * Imposta il valore della proprietà applicationGroupOrcid.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidId }
     *     
     */
    public void setApplicationGroupOrcid(OrcidId value) {
        this.applicationGroupOrcid = value;
    }

    /**
     * Recupera il valore della proprietà applicationGroupName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationGroupName() {
        return applicationGroupName;
    }

    /**
     * Imposta il valore della proprietà applicationGroupName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationGroupName(String value) {
        this.applicationGroupName = value;
    }

}
