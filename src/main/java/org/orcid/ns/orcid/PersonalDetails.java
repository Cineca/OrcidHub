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
 *         &lt;element name="given-names">
 *           &lt;simpleType>
 *             &lt;union memberTypes=" {http://www.orcid.org/ns/orcid}non-empty-string {http://www.orcid.org/ns/orcid}empty">
 *             &lt;/union>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="family-name" type="{http://www.orcid.org/ns/orcid}non-empty-string" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}credit-name" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}other-names" minOccurs="0"/>
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
    "givenNames",
    "familyName",
    "creditName",
    "otherNames"
})
@XmlRootElement(name = "personal-details")
public class PersonalDetails {

    @XmlElement(name = "given-names", required = true)
    protected String givenNames;
    @XmlElement(name = "family-name")
    protected String familyName;
    @XmlElement(name = "credit-name")
    protected CreditName creditName;
    @XmlElement(name = "other-names")
    protected OtherNames otherNames;

    /**
     * Recupera il valore della proprietà givenNames.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenNames() {
        return givenNames;
    }

    /**
     * Imposta il valore della proprietà givenNames.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGivenNames(String value) {
        this.givenNames = value;
    }

    /**
     * Recupera il valore della proprietà familyName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Imposta il valore della proprietà familyName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamilyName(String value) {
        this.familyName = value;
    }

    /**
     * The full name of the researcher or contributor as it generally appears on their published works. (see element for more details.) The visibility attribute (private, limited or public) can be set at record creation, and indicates who can see this section of information. 
     * 						
     * 
     * @return
     *     possible object is
     *     {@link CreditName }
     *     
     */
    public CreditName getCreditName() {
        return creditName;
    }

    /**
     * Imposta il valore della proprietà creditName.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditName }
     *     
     */
    public void setCreditName(CreditName value) {
        this.creditName = value;
    }

    /**
     * (Optional) Container that includes other name(s) that the researcher or contributor may be known by. (see element for more details.) The visibility attribute (private, limited or public) can be set at record creation, and indicates who can see this section of information. 
     * 						
     * 
     * @return
     *     possible object is
     *     {@link OtherNames }
     *     
     */
    public OtherNames getOtherNames() {
        return otherNames;
    }

    /**
     * Imposta il valore della proprietà otherNames.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherNames }
     *     
     */
    public void setOtherNames(OtherNames value) {
        this.otherNames = value;
    }

}
