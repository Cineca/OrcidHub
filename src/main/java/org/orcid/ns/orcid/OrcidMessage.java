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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}message-version"/>
 *         &lt;choice>
 *           &lt;element ref="{http://www.orcid.org/ns/orcid}orcid-profile"/>
 *           &lt;element ref="{http://www.orcid.org/ns/orcid}orcid-search-results"/>
 *           &lt;element ref="{http://www.orcid.org/ns/orcid}error-desc"/>
 *         &lt;/choice>
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
    "messageVersion",
    "orcidProfile",
    "orcidSearchResults",
    "errorDesc"
})
@XmlRootElement(name = "orcid-message")
public class OrcidMessage {

    @XmlElement(name = "message-version", required = true)
    protected String messageVersion;
    @XmlElement(name = "orcid-profile")
    protected OrcidProfile orcidProfile;
    @XmlElement(name = "orcid-search-results")
    protected OrcidSearchResults orcidSearchResults;
    @XmlElement(name = "error-desc")
    protected ErrorDesc errorDesc;

    /**
     * Recupera il valore della proprietà messageVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageVersion() {
        return messageVersion;
    }

    /**
     * Imposta il valore della proprietà messageVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageVersion(String value) {
        this.messageVersion = value;
    }

    /**
     * Recupera il valore della proprietà orcidProfile.
     * 
     * @return
     *     possible object is
     *     {@link OrcidProfile }
     *     
     */
    public OrcidProfile getOrcidProfile() {
        return orcidProfile;
    }

    /**
     * Imposta il valore della proprietà orcidProfile.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidProfile }
     *     
     */
    public void setOrcidProfile(OrcidProfile value) {
        this.orcidProfile = value;
    }

    /**
     * Recupera il valore della proprietà orcidSearchResults.
     * 
     * @return
     *     possible object is
     *     {@link OrcidSearchResults }
     *     
     */
    public OrcidSearchResults getOrcidSearchResults() {
        return orcidSearchResults;
    }

    /**
     * Imposta il valore della proprietà orcidSearchResults.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidSearchResults }
     *     
     */
    public void setOrcidSearchResults(OrcidSearchResults value) {
        this.orcidSearchResults = value;
    }

    /**
     * Recupera il valore della proprietà errorDesc.
     * 
     * @return
     *     possible object is
     *     {@link ErrorDesc }
     *     
     */
    public ErrorDesc getErrorDesc() {
        return errorDesc;
    }

    /**
     * Imposta il valore della proprietà errorDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorDesc }
     *     
     */
    public void setErrorDesc(ErrorDesc value) {
        this.errorDesc = value;
    }

}
