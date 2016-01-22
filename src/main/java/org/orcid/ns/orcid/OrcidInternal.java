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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}security-details" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}preferences" minOccurs="0"/>
 *         &lt;element name="group-orcid-identifier" type="{http://www.orcid.org/ns/orcid}orcid-id" minOccurs="0"/>
 *         &lt;element name="referred-by" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}salesforce-id" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.orcid.org/ns/orcid}scope"/>
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
    "securityDetails",
    "preferences",
    "groupOrcidIdentifier",
    "referredBy",
    "salesforceId"
})
@XmlRootElement(name = "orcid-internal")
public class OrcidInternal {

    @XmlElement(name = "security-details")
    protected SecurityDetails securityDetails;
    protected Preferences preferences;
    @XmlElement(name = "group-orcid-identifier")
    protected OrcidId groupOrcidIdentifier;
    @XmlElement(name = "referred-by")
    protected String referredBy;
    @XmlElement(name = "salesforce-id")
    protected String salesforceId;
    @XmlAttribute(name = "scope")
    protected Scope scope;
    @XmlAttribute(name = "visibility")
    protected Visibility visibility;

    /**
     * Recupera il valore della proprietà securityDetails.
     * 
     * @return
     *     possible object is
     *     {@link SecurityDetails }
     *     
     */
    public SecurityDetails getSecurityDetails() {
        return securityDetails;
    }

    /**
     * Imposta il valore della proprietà securityDetails.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityDetails }
     *     
     */
    public void setSecurityDetails(SecurityDetails value) {
        this.securityDetails = value;
    }

    /**
     * Recupera il valore della proprietà preferences.
     * 
     * @return
     *     possible object is
     *     {@link Preferences }
     *     
     */
    public Preferences getPreferences() {
        return preferences;
    }

    /**
     * Imposta il valore della proprietà preferences.
     * 
     * @param value
     *     allowed object is
     *     {@link Preferences }
     *     
     */
    public void setPreferences(Preferences value) {
        this.preferences = value;
    }

    /**
     * Recupera il valore della proprietà groupOrcidIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link OrcidId }
     *     
     */
    public OrcidId getGroupOrcidIdentifier() {
        return groupOrcidIdentifier;
    }

    /**
     * Imposta il valore della proprietà groupOrcidIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidId }
     *     
     */
    public void setGroupOrcidIdentifier(OrcidId value) {
        this.groupOrcidIdentifier = value;
    }

    /**
     * Recupera il valore della proprietà referredBy.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferredBy() {
        return referredBy;
    }

    /**
     * Imposta il valore della proprietà referredBy.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferredBy(String value) {
        this.referredBy = value;
    }

    /**
     * Recupera il valore della proprietà salesforceId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesforceId() {
        return salesforceId;
    }

    /**
     * Imposta il valore della proprietà salesforceId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesforceId(String value) {
        this.salesforceId = value;
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
