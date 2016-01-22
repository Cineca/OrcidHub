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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Container for a work citation. Citations may be fielded (e.g., RIS, BibTeX - preferred citation type), or may be textual (APA, MLA, Chicago, etc.) The required work-citation-type element indicates the format of the citation.
 * 			
 * 
 * <p>Classe Java per citation complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="citation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="work-citation-type" type="{http://www.orcid.org/ns/orcid}citation-type"/>
 *         &lt;element name="citation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "citation", propOrder = {
    "workCitationType",
    "citation"
})
public class Citation {

    @XmlElement(name = "work-citation-type", required = true, defaultValue = "formatted-unspecified")
    @XmlSchemaType(name = "string")
    protected CitationType workCitationType;
    @XmlElement(required = true)
    protected String citation;

    /**
     * Recupera il valore della proprietà workCitationType.
     * 
     * @return
     *     possible object is
     *     {@link CitationType }
     *     
     */
    public CitationType getWorkCitationType() {
        return workCitationType;
    }

    /**
     * Imposta il valore della proprietà workCitationType.
     * 
     * @param value
     *     allowed object is
     *     {@link CitationType }
     *     
     */
    public void setWorkCitationType(CitationType value) {
        this.workCitationType = value;
    }

    /**
     * Recupera il valore della proprietà citation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCitation() {
        return citation;
    }

    /**
     * Imposta il valore della proprietà citation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCitation(String value) {
        this.citation = value;
    }

}
