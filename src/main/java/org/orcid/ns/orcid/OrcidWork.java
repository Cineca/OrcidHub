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

import java.math.BigInteger;
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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}work-title" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}journal-title" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}short-description" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}work-citation" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}work-type"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}publication-date" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}work-external-identifiers" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}url" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}work-contributors" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}source" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}created-date" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}last-modified-date" minOccurs="0"/>
 *         &lt;element name="language-code" type="{http://www.orcid.org/ns/orcid}language-code" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}country" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.orcid.org/ns/orcid}visibility"/>
 *       &lt;attGroup ref="{http://www.orcid.org/ns/orcid}put-code"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "workTitle",
    "journalTitle",
    "shortDescription",
    "workCitation",
    "workType",
    "publicationDate",
    "workExternalIdentifiers",
    "url",
    "workContributors",
    "source",
    "createdDate",
    "lastModifiedDate",
    "languageCode",
    "country"
})
@XmlRootElement(name = "orcid-work")
public class OrcidWork {

    @XmlElement(name = "work-title")
    protected WorkTitle workTitle;
    @XmlElement(name = "journal-title")
    protected JournalTitle journalTitle;
    @XmlElement(name = "short-description")
    protected String shortDescription;
    @XmlElement(name = "work-citation")
    protected Citation workCitation;
    @XmlElement(name = "work-type", required = true)
    protected String workType;
    @XmlElement(name = "publication-date")
    protected PublicationDate publicationDate;
    @XmlElement(name = "work-external-identifiers")
    protected WorkExternalIdentifiers workExternalIdentifiers;
    protected Url url;
    @XmlElement(name = "work-contributors")
    protected WorkContributors workContributors;
    protected Source source;
    @XmlElement(name = "created-date")
    protected CreatedDate createdDate;
    @XmlElement(name = "last-modified-date")
    protected LastModifiedDate lastModifiedDate;
    @XmlElement(name = "language-code")
    @XmlSchemaType(name = "string")
    protected LanguageCode languageCode;
    protected Country country;
    @XmlAttribute(name = "visibility")
    protected Visibility visibility;
    @XmlAttribute(name = "put-code")
    protected BigInteger putCode;

    /**
     * Recupera il valore della proprietà workTitle.
     * 
     * @return
     *     possible object is
     *     {@link WorkTitle }
     *     
     */
    public WorkTitle getWorkTitle() {
        return workTitle;
    }

    /**
     * Imposta il valore della proprietà workTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkTitle }
     *     
     */
    public void setWorkTitle(WorkTitle value) {
        this.workTitle = value;
    }

    /**
     * Recupera il valore della proprietà journalTitle.
     * 
     * @return
     *     possible object is
     *     {@link JournalTitle }
     *     
     */
    public JournalTitle getJournalTitle() {
        return journalTitle;
    }

    /**
     * Imposta il valore della proprietà journalTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link JournalTitle }
     *     
     */
    public void setJournalTitle(JournalTitle value) {
        this.journalTitle = value;
    }

    /**
     * Recupera il valore della proprietà shortDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Imposta il valore della proprietà shortDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortDescription(String value) {
        this.shortDescription = value;
    }

    /**
     * Recupera il valore della proprietà workCitation.
     * 
     * @return
     *     possible object is
     *     {@link Citation }
     *     
     */
    public Citation getWorkCitation() {
        return workCitation;
    }

    /**
     * Imposta il valore della proprietà workCitation.
     * 
     * @param value
     *     allowed object is
     *     {@link Citation }
     *     
     */
    public void setWorkCitation(Citation value) {
        this.workCitation = value;
    }

    /**
     * Recupera il valore della proprietà workType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * Imposta il valore della proprietà workType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkType(String value) {
        this.workType = value;
    }

    /**
     * Recupera il valore della proprietà publicationDate.
     * 
     * @return
     *     possible object is
     *     {@link PublicationDate }
     *     
     */
    public PublicationDate getPublicationDate() {
        return publicationDate;
    }

    /**
     * Imposta il valore della proprietà publicationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link PublicationDate }
     *     
     */
    public void setPublicationDate(PublicationDate value) {
        this.publicationDate = value;
    }

    /**
     * Recupera il valore della proprietà workExternalIdentifiers.
     * 
     * @return
     *     possible object is
     *     {@link WorkExternalIdentifiers }
     *     
     */
    public WorkExternalIdentifiers getWorkExternalIdentifiers() {
        return workExternalIdentifiers;
    }

    /**
     * Imposta il valore della proprietà workExternalIdentifiers.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkExternalIdentifiers }
     *     
     */
    public void setWorkExternalIdentifiers(WorkExternalIdentifiers value) {
        this.workExternalIdentifiers = value;
    }

    /**
     * Recupera il valore della proprietà url.
     * 
     * @return
     *     possible object is
     *     {@link Url }
     *     
     */
    public Url getUrl() {
        return url;
    }

    /**
     * Imposta il valore della proprietà url.
     * 
     * @param value
     *     allowed object is
     *     {@link Url }
     *     
     */
    public void setUrl(Url value) {
        this.url = value;
    }

    /**
     * Recupera il valore della proprietà workContributors.
     * 
     * @return
     *     possible object is
     *     {@link WorkContributors }
     *     
     */
    public WorkContributors getWorkContributors() {
        return workContributors;
    }

    /**
     * Imposta il valore della proprietà workContributors.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkContributors }
     *     
     */
    public void setWorkContributors(WorkContributors value) {
        this.workContributors = value;
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
     * Recupera il valore della proprietà createdDate.
     * 
     * @return
     *     possible object is
     *     {@link CreatedDate }
     *     
     */
    public CreatedDate getCreatedDate() {
        return createdDate;
    }

    /**
     * Imposta il valore della proprietà createdDate.
     * 
     * @param value
     *     allowed object is
     *     {@link CreatedDate }
     *     
     */
    public void setCreatedDate(CreatedDate value) {
        this.createdDate = value;
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
     * Recupera il valore della proprietà languageCode.
     * 
     * @return
     *     possible object is
     *     {@link LanguageCode }
     *     
     */
    public LanguageCode getLanguageCode() {
        return languageCode;
    }

    /**
     * Imposta il valore della proprietà languageCode.
     * 
     * @param value
     *     allowed object is
     *     {@link LanguageCode }
     *     
     */
    public void setLanguageCode(LanguageCode value) {
        this.languageCode = value;
    }

    /**
     * Recupera il valore della proprietà country.
     * 
     * @return
     *     possible object is
     *     {@link Country }
     *     
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Imposta il valore della proprietà country.
     * 
     * @param value
     *     allowed object is
     *     {@link Country }
     *     
     */
    public void setCountry(Country value) {
        this.country = value;
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

    /**
     * Recupera il valore della proprietà putCode.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPutCode() {
        return putCode;
    }

    /**
     * Imposta il valore della proprietà putCode.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPutCode(BigInteger value) {
        this.putCode = value;
    }

}
