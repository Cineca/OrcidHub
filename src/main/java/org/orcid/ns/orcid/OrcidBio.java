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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}personal-details" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}biography" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}researcher-urls" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}contact-details" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}keywords" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}external-identifiers" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}delegation" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}applications" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.orcid.org/ns/orcid}scope"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "personalDetails",
    "biography",
    "researcherUrls",
    "contactDetails",
    "keywords",
    "externalIdentifiers",
    "delegation",
    "applications"
})
@XmlRootElement(name = "orcid-bio")
public class OrcidBio {

    @XmlElement(name = "personal-details")
    protected PersonalDetails personalDetails;
    protected Biography biography;
    @XmlElement(name = "researcher-urls")
    protected ResearcherUrls researcherUrls;
    @XmlElement(name = "contact-details")
    protected ContactDetails contactDetails;
    protected Keywords keywords;
    @XmlElement(name = "external-identifiers")
    protected ExternalIdentifiers externalIdentifiers;
    protected Delegation delegation;
    protected Applications applications;
    @XmlAttribute(name = "scope")
    protected Scope scope;

    /**
     * Recupera il valore della proprietà personalDetails.
     * 
     * @return
     *     possible object is
     *     {@link PersonalDetails }
     *     
     */
    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    /**
     * Imposta il valore della proprietà personalDetails.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonalDetails }
     *     
     */
    public void setPersonalDetails(PersonalDetails value) {
        this.personalDetails = value;
    }

    /**
     * Recupera il valore della proprietà biography.
     * 
     * @return
     *     possible object is
     *     {@link Biography }
     *     
     */
    public Biography getBiography() {
        return biography;
    }

    /**
     * Imposta il valore della proprietà biography.
     * 
     * @param value
     *     allowed object is
     *     {@link Biography }
     *     
     */
    public void setBiography(Biography value) {
        this.biography = value;
    }

    /**
     * Recupera il valore della proprietà researcherUrls.
     * 
     * @return
     *     possible object is
     *     {@link ResearcherUrls }
     *     
     */
    public ResearcherUrls getResearcherUrls() {
        return researcherUrls;
    }

    /**
     * Imposta il valore della proprietà researcherUrls.
     * 
     * @param value
     *     allowed object is
     *     {@link ResearcherUrls }
     *     
     */
    public void setResearcherUrls(ResearcherUrls value) {
        this.researcherUrls = value;
    }

    /**
     * Recupera il valore della proprietà contactDetails.
     * 
     * @return
     *     possible object is
     *     {@link ContactDetails }
     *     
     */
    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    /**
     * Imposta il valore della proprietà contactDetails.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactDetails }
     *     
     */
    public void setContactDetails(ContactDetails value) {
        this.contactDetails = value;
    }

    /**
     * Recupera il valore della proprietà keywords.
     * 
     * @return
     *     possible object is
     *     {@link Keywords }
     *     
     */
    public Keywords getKeywords() {
        return keywords;
    }

    /**
     * Imposta il valore della proprietà keywords.
     * 
     * @param value
     *     allowed object is
     *     {@link Keywords }
     *     
     */
    public void setKeywords(Keywords value) {
        this.keywords = value;
    }

    /**
     * Recupera il valore della proprietà externalIdentifiers.
     * 
     * @return
     *     possible object is
     *     {@link ExternalIdentifiers }
     *     
     */
    public ExternalIdentifiers getExternalIdentifiers() {
        return externalIdentifiers;
    }

    /**
     * Imposta il valore della proprietà externalIdentifiers.
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalIdentifiers }
     *     
     */
    public void setExternalIdentifiers(ExternalIdentifiers value) {
        this.externalIdentifiers = value;
    }

    /**
     * Recupera il valore della proprietà delegation.
     * 
     * @return
     *     possible object is
     *     {@link Delegation }
     *     
     */
    public Delegation getDelegation() {
        return delegation;
    }

    /**
     * Imposta il valore della proprietà delegation.
     * 
     * @param value
     *     allowed object is
     *     {@link Delegation }
     *     
     */
    public void setDelegation(Delegation value) {
        this.delegation = value;
    }

    /**
     * Recupera il valore della proprietà applications.
     * 
     * @return
     *     possible object is
     *     {@link Applications }
     *     
     */
    public Applications getApplications() {
        return applications;
    }

    /**
     * Imposta il valore della proprietà applications.
     * 
     * @param value
     *     allowed object is
     *     {@link Applications }
     *     
     */
    public void setApplications(Applications value) {
        this.applications = value;
    }

    /**
     * Recupera il valore della proprietà scope.
     * 
     * @return
     *     possible object is
     *     {@link Scope }
     *     
     */
    public Scope getScope() {
        return scope;
    }

    /**
     * Imposta il valore della proprietà scope.
     * 
     * @param value
     *     allowed object is
     *     {@link Scope }
     *     
     */
    public void setScope(Scope value) {
        this.scope = value;
    }

}
