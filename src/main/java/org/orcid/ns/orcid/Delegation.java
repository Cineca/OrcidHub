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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}given-permission-to" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}given-permission-by" minOccurs="0"/>
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
    "givenPermissionTo",
    "givenPermissionBy"
})
@XmlRootElement(name = "delegation")
public class Delegation {

    @XmlElement(name = "given-permission-to")
    protected GivenPermissionTo givenPermissionTo;
    @XmlElement(name = "given-permission-by")
    protected GivenPermissionBy givenPermissionBy;
    @XmlAttribute(name = "visibility")
    protected Visibility visibility;

    /**
     * Recupera il valore della proprietà givenPermissionTo.
     * 
     * @return
     *     possible object is
     *     {@link GivenPermissionTo }
     *     
     */
    public GivenPermissionTo getGivenPermissionTo() {
        return givenPermissionTo;
    }

    /**
     * Imposta il valore della proprietà givenPermissionTo.
     * 
     * @param value
     *     allowed object is
     *     {@link GivenPermissionTo }
     *     
     */
    public void setGivenPermissionTo(GivenPermissionTo value) {
        this.givenPermissionTo = value;
    }

    /**
     * Recupera il valore della proprietà givenPermissionBy.
     * 
     * @return
     *     possible object is
     *     {@link GivenPermissionBy }
     *     
     */
    public GivenPermissionBy getGivenPermissionBy() {
        return givenPermissionBy;
    }

    /**
     * Imposta il valore della proprietà givenPermissionBy.
     * 
     * @param value
     *     allowed object is
     *     {@link GivenPermissionBy }
     *     
     */
    public void setGivenPermissionBy(GivenPermissionBy value) {
        this.givenPermissionBy = value;
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
