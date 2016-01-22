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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}orcid-identifier" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}orcid-deprecated" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}orcid-preferences" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}orcid-history" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}orcid-bio" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}orcid-activities" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}orcid-internal" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" type="{http://www.orcid.org/ns/orcid}orcid-type" default="user" />
 *       &lt;attribute name="client-type" type="{http://www.orcid.org/ns/orcid}client-type" />
 *       &lt;attribute name="group-type" type="{http://www.orcid.org/ns/orcid}group-type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "orcidIdentifier",
    "orcidDeprecated",
    "orcidPreferences",
    "orcidHistory",
    "orcidBio",
    "orcidActivities",
    "orcidInternal"
})
@XmlRootElement(name = "orcid-profile")
public class OrcidProfile {

    @XmlElement(name = "orcid-identifier")
    protected OrcidId orcidIdentifier;
    @XmlElement(name = "orcid-deprecated")
    protected OrcidDeprecated orcidDeprecated;
    @XmlElement(name = "orcid-preferences")
    protected OrcidPreferences orcidPreferences;
    @XmlElement(name = "orcid-history")
    protected OrcidHistory orcidHistory;
    @XmlElement(name = "orcid-bio")
    protected OrcidBio orcidBio;
    @XmlElement(name = "orcid-activities")
    protected OrcidActivities orcidActivities;
    @XmlElement(name = "orcid-internal")
    protected OrcidInternal orcidInternal;
    @XmlAttribute(name = "type")
    protected OrcidType type;
    @XmlAttribute(name = "client-type")
    protected ClientType clientType;
    @XmlAttribute(name = "group-type")
    protected GroupType groupType;

    /**
     * Recupera il valore della proprietà orcidIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link OrcidId }
     *     
     */
    public OrcidId getOrcidIdentifier() {
        return orcidIdentifier;
    }

    /**
     * Imposta il valore della proprietà orcidIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidId }
     *     
     */
    public void setOrcidIdentifier(OrcidId value) {
        this.orcidIdentifier = value;
    }

    /**
     * Recupera il valore della proprietà orcidDeprecated.
     * 
     * @return
     *     possible object is
     *     {@link OrcidDeprecated }
     *     
     */
    public OrcidDeprecated getOrcidDeprecated() {
        return orcidDeprecated;
    }

    /**
     * Imposta il valore della proprietà orcidDeprecated.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidDeprecated }
     *     
     */
    public void setOrcidDeprecated(OrcidDeprecated value) {
        this.orcidDeprecated = value;
    }

    /**
     * Recupera il valore della proprietà orcidPreferences.
     * 
     * @return
     *     possible object is
     *     {@link OrcidPreferences }
     *     
     */
    public OrcidPreferences getOrcidPreferences() {
        return orcidPreferences;
    }

    /**
     * Imposta il valore della proprietà orcidPreferences.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidPreferences }
     *     
     */
    public void setOrcidPreferences(OrcidPreferences value) {
        this.orcidPreferences = value;
    }

    /**
     * Recupera il valore della proprietà orcidHistory.
     * 
     * @return
     *     possible object is
     *     {@link OrcidHistory }
     *     
     */
    public OrcidHistory getOrcidHistory() {
        return orcidHistory;
    }

    /**
     * Imposta il valore della proprietà orcidHistory.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidHistory }
     *     
     */
    public void setOrcidHistory(OrcidHistory value) {
        this.orcidHistory = value;
    }

    /**
     * Recupera il valore della proprietà orcidBio.
     * 
     * @return
     *     possible object is
     *     {@link OrcidBio }
     *     
     */
    public OrcidBio getOrcidBio() {
        return orcidBio;
    }

    /**
     * Imposta il valore della proprietà orcidBio.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidBio }
     *     
     */
    public void setOrcidBio(OrcidBio value) {
        this.orcidBio = value;
    }

    /**
     * Recupera il valore della proprietà orcidActivities.
     * 
     * @return
     *     possible object is
     *     {@link OrcidActivities }
     *     
     */
    public OrcidActivities getOrcidActivities() {
        return orcidActivities;
    }

    /**
     * Imposta il valore della proprietà orcidActivities.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidActivities }
     *     
     */
    public void setOrcidActivities(OrcidActivities value) {
        this.orcidActivities = value;
    }

    /**
     * Recupera il valore della proprietà orcidInternal.
     * 
     * @return
     *     possible object is
     *     {@link OrcidInternal }
     *     
     */
    public OrcidInternal getOrcidInternal() {
        return orcidInternal;
    }

    /**
     * Imposta il valore della proprietà orcidInternal.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidInternal }
     *     
     */
    public void setOrcidInternal(OrcidInternal value) {
        this.orcidInternal = value;
    }

    /**
     * Recupera il valore della proprietà type.
     * 
     * @return
     *     possible object is
     *     {@link OrcidType }
     *     
     */
    public OrcidType getType() {
        if (type == null) {
            return OrcidType.USER;
        } else {
            return type;
        }
    }

    /**
     * Imposta il valore della proprietà type.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidType }
     *     
     */
    public void setType(OrcidType value) {
        this.type = value;
    }

    /**
     * Recupera il valore della proprietà clientType.
     * 
     * @return
     *     possible object is
     *     {@link ClientType }
     *     
     */
    public ClientType getClientType() {
        return clientType;
    }

    /**
     * Imposta il valore della proprietà clientType.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientType }
     *     
     */
    public void setClientType(ClientType value) {
        this.clientType = value;
    }

    /**
     * Recupera il valore della proprietà groupType.
     * 
     * @return
     *     possible object is
     *     {@link GroupType }
     *     
     */
    public GroupType getGroupType() {
        return groupType;
    }

    /**
     * Imposta il valore della proprietà groupType.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupType }
     *     
     */
    public void setGroupType(GroupType value) {
        this.groupType = value;
    }

}
