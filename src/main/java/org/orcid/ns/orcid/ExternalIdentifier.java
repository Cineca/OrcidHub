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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}external-id-common-name" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}external-id-reference" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}external-id-url" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}source" minOccurs="0"/>
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
    "externalIdCommonName",
    "externalIdReference",
    "externalIdUrl",
    "source"
})
@XmlRootElement(name = "external-identifier")
public class ExternalIdentifier {

    @XmlElement(name = "external-id-common-name")
    protected ExternalIdCommonName externalIdCommonName;
    @XmlElement(name = "external-id-reference")
    protected ExternalIdReference externalIdReference;
    @XmlElement(name = "external-id-url")
    protected ExternalIdUrl externalIdUrl;
    protected Source source;

    /**
     * Recupera il valore della proprietà externalIdCommonName.
     * 
     * @return
     *     possible object is
     *     {@link ExternalIdCommonName }
     *     
     */
    public ExternalIdCommonName getExternalIdCommonName() {
        return externalIdCommonName;
    }

    /**
     * Imposta il valore della proprietà externalIdCommonName.
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalIdCommonName }
     *     
     */
    public void setExternalIdCommonName(ExternalIdCommonName value) {
        this.externalIdCommonName = value;
    }

    /**
     * Recupera il valore della proprietà externalIdReference.
     * 
     * @return
     *     possible object is
     *     {@link ExternalIdReference }
     *     
     */
    public ExternalIdReference getExternalIdReference() {
        return externalIdReference;
    }

    /**
     * Imposta il valore della proprietà externalIdReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalIdReference }
     *     
     */
    public void setExternalIdReference(ExternalIdReference value) {
        this.externalIdReference = value;
    }

    /**
     * Recupera il valore della proprietà externalIdUrl.
     * 
     * @return
     *     possible object is
     *     {@link ExternalIdUrl }
     *     
     */
    public ExternalIdUrl getExternalIdUrl() {
        return externalIdUrl;
    }

    /**
     * Imposta il valore della proprietà externalIdUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalIdUrl }
     *     
     */
    public void setExternalIdUrl(ExternalIdUrl value) {
        this.externalIdUrl = value;
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

}
