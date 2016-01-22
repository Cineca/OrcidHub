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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="creation-method" type="{http://www.orcid.org/ns/orcid}creation-method" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}completion-date" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}submission-date" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}last-modified-date" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}claimed" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}source" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}deactivation-date" minOccurs="0"/>
 *         &lt;element name="verified-email" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="verified-primary-email" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.orcid.org/ns/orcid}visibility"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "creationMethod",
    "completionDate",
    "submissionDate",
    "lastModifiedDate",
    "claimed",
    "source",
    "deactivationDate",
    "verifiedEmail",
    "verifiedPrimaryEmail"
})
@XmlRootElement(name = "orcid-history")
public class OrcidHistory {

    @XmlElement(name = "creation-method")
    @XmlSchemaType(name = "string")
    protected CreationMethod creationMethod;
    @XmlElement(name = "completion-date")
    protected CompletionDate completionDate;
    @XmlElement(name = "submission-date")
    protected SubmissionDate submissionDate;
    @XmlElement(name = "last-modified-date")
    protected LastModifiedDate lastModifiedDate;
    protected Claimed claimed;
    protected Source source;
    @XmlElement(name = "deactivation-date")
    protected DeactivationDate deactivationDate;
    @XmlElement(name = "verified-email")
    protected boolean verifiedEmail;
    @XmlElement(name = "verified-primary-email")
    protected boolean verifiedPrimaryEmail;
    @XmlAttribute(name = "visibility")
    protected Visibility visibility;

    /**
     * Recupera il valore della proprietà creationMethod.
     * 
     * @return
     *     possible object is
     *     {@link CreationMethod }
     *     
     */
    public CreationMethod getCreationMethod() {
        return creationMethod;
    }

    /**
     * Imposta il valore della proprietà creationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link CreationMethod }
     *     
     */
    public void setCreationMethod(CreationMethod value) {
        this.creationMethod = value;
    }

    /**
     * Recupera il valore della proprietà completionDate.
     * 
     * @return
     *     possible object is
     *     {@link CompletionDate }
     *     
     */
    public CompletionDate getCompletionDate() {
        return completionDate;
    }

    /**
     * Imposta il valore della proprietà completionDate.
     * 
     * @param value
     *     allowed object is
     *     {@link CompletionDate }
     *     
     */
    public void setCompletionDate(CompletionDate value) {
        this.completionDate = value;
    }

    /**
     * Recupera il valore della proprietà submissionDate.
     * 
     * @return
     *     possible object is
     *     {@link SubmissionDate }
     *     
     */
    public SubmissionDate getSubmissionDate() {
        return submissionDate;
    }

    /**
     * Imposta il valore della proprietà submissionDate.
     * 
     * @param value
     *     allowed object is
     *     {@link SubmissionDate }
     *     
     */
    public void setSubmissionDate(SubmissionDate value) {
        this.submissionDate = value;
    }

    /**
     * Recupera il valore della proprietà lastModifiedDate.
     * 
     * @return
     *     possible object is
     *     {@link LastModifiedDate }
     *     
     */
    public LastModifiedDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Imposta il valore della proprietà lastModifiedDate.
     * 
     * @param value
     *     allowed object is
     *     {@link LastModifiedDate }
     *     
     */
    public void setLastModifiedDate(LastModifiedDate value) {
        this.lastModifiedDate = value;
    }

    /**
     * Recupera il valore della proprietà claimed.
     * 
     * @return
     *     possible object is
     *     {@link Claimed }
     *     
     */
    public Claimed getClaimed() {
        return claimed;
    }

    /**
     * Imposta il valore della proprietà claimed.
     * 
     * @param value
     *     allowed object is
     *     {@link Claimed }
     *     
     */
    public void setClaimed(Claimed value) {
        this.claimed = value;
    }

    /**
     * Recupera il valore della proprietà source.
     * 
     * @return
     *     possible object is
     *     {@link Source }
     *     
     */
    public Source getSource() {
        return source;
    }

    /**
     * Imposta il valore della proprietà source.
     * 
     * @param value
     *     allowed object is
     *     {@link Source }
     *     
     */
    public void setSource(Source value) {
        this.source = value;
    }

    /**
     * Recupera il valore della proprietà deactivationDate.
     * 
     * @return
     *     possible object is
     *     {@link DeactivationDate }
     *     
     */
    public DeactivationDate getDeactivationDate() {
        return deactivationDate;
    }

    /**
     * Imposta il valore della proprietà deactivationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link DeactivationDate }
     *     
     */
    public void setDeactivationDate(DeactivationDate value) {
        this.deactivationDate = value;
    }

    /**
     * Recupera il valore della proprietà verifiedEmail.
     * 
     */
    public boolean isVerifiedEmail() {
        return verifiedEmail;
    }

    /**
     * Imposta il valore della proprietà verifiedEmail.
     * 
     */
    public void setVerifiedEmail(boolean value) {
        this.verifiedEmail = value;
    }

    /**
     * Recupera il valore della proprietà verifiedPrimaryEmail.
     * 
     */
    public boolean isVerifiedPrimaryEmail() {
        return verifiedPrimaryEmail;
    }

    /**
     * Imposta il valore della proprietà verifiedPrimaryEmail.
     * 
     */
    public void setVerifiedPrimaryEmail(boolean value) {
        this.verifiedPrimaryEmail = value;
    }

    /**
     * Recupera il valore della proprietà visibility.
     * 
     * @return
     *     possible object is
     *     {@link Visibility }
     *     
     */
    public Visibility getVisibility() {
        return visibility;
    }

    /**
     * Imposta il valore della proprietà visibility.
     * 
     * @param value
     *     allowed object is
     *     {@link Visibility }
     *     
     */
    public void setVisibility(Visibility value) {
        this.visibility = value;
    }

}
