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
 *         &lt;element name="name" type="{http://www.orcid.org/ns/orcid}non-empty-string"/>
 *         &lt;element name="address" type="{http://www.orcid.org/ns/orcid}organization-address"/>
 *         &lt;element name="disambiguated-organization" type="{http://www.orcid.org/ns/orcid}disambiguated-organization" minOccurs="0"/>
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
    "name",
    "address",
    "disambiguatedOrganization"
})
@XmlRootElement(name = "organization")
public class Organization {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected OrganizationAddress address;
    @XmlElement(name = "disambiguated-organization")
    protected DisambiguatedOrganization disambiguatedOrganization;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietà address.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationAddress }
     *     
     */
    public OrganizationAddress getAddress() {
        return address;
    }

    /**
     * Imposta il valore della proprietà address.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationAddress }
     *     
     */
    public void setAddress(OrganizationAddress value) {
        this.address = value;
    }

    /**
     * Recupera il valore della proprietà disambiguatedOrganization.
     * 
     * @return
     *     possible object is
     *     {@link DisambiguatedOrganization }
     *     
     */
    public DisambiguatedOrganization getDisambiguatedOrganization() {
        return disambiguatedOrganization;
    }

    /**
     * Imposta il valore della proprietà disambiguatedOrganization.
     * 
     * @param value
     *     allowed object is
     *     {@link DisambiguatedOrganization }
     *     
     */
    public void setDisambiguatedOrganization(DisambiguatedOrganization value) {
        this.disambiguatedOrganization = value;
    }

}
