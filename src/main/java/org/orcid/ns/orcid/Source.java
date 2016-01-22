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
 *         &lt;choice minOccurs="0">
 *           &lt;element ref="{http://www.orcid.org/ns/orcid}source-orcid"/>
 *           &lt;element ref="{http://www.orcid.org/ns/orcid}source-client-id"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}source-name" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}source-date" minOccurs="0"/>
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
    "sourceOrcid",
    "sourceClientId",
    "sourceName",
    "sourceDate"
})
@XmlRootElement(name = "source")
public class Source {

    @XmlElement(name = "source-orcid")
    protected OrcidId sourceOrcid;
    @XmlElement(name = "source-client-id")
    protected ClientId sourceClientId;
    @XmlElement(name = "source-name")
    protected SourceName sourceName;
    @XmlElement(name = "source-date")
    protected SourceDate sourceDate;

    /**
     * Recupera il valore della proprietà sourceOrcid.
     * 
     * @return
     *     possible object is
     *     {@link OrcidId }
     *     
     */
    public OrcidId getSourceOrcid() {
        return sourceOrcid;
    }

    /**
     * Imposta il valore della proprietà sourceOrcid.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidId }
     *     
     */
    public void setSourceOrcid(OrcidId value) {
        this.sourceOrcid = value;
    }

    /**
     * Recupera il valore della proprietà sourceClientId.
     * 
     * @return
     *     possible object is
     *     {@link ClientId }
     *     
     */
    public ClientId getSourceClientId() {
        return sourceClientId;
    }

    /**
     * Imposta il valore della proprietà sourceClientId.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientId }
     *     
     */
    public void setSourceClientId(ClientId value) {
        this.sourceClientId = value;
    }

    /**
     * Recupera il valore della proprietà sourceName.
     * 
     * @return
     *     possible object is
     *     {@link SourceName }
     *     
     */
    public SourceName getSourceName() {
        return sourceName;
    }

    /**
     * Imposta il valore della proprietà sourceName.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceName }
     *     
     */
    public void setSourceName(SourceName value) {
        this.sourceName = value;
    }

    /**
     * Recupera il valore della proprietà sourceDate.
     * 
     * @return
     *     possible object is
     *     {@link SourceDate }
     *     
     */
    public SourceDate getSourceDate() {
        return sourceDate;
    }

    /**
     * Imposta il valore della proprietà sourceDate.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceDate }
     *     
     */
    public void setSourceDate(SourceDate value) {
        this.sourceDate = value;
    }

}
