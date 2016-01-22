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

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="type" type="{http://www.orcid.org/ns/orcid}affiliation-type"/>
 *         &lt;element name="department-name" type="{http://www.orcid.org/ns/orcid}non-empty-string" minOccurs="0"/>
 *         &lt;element name="role-title" type="{http://www.orcid.org/ns/orcid}non-empty-string" minOccurs="0"/>
 *         &lt;element name="start-date" type="{http://www.orcid.org/ns/orcid}fuzzy-date" minOccurs="0"/>
 *         &lt;element name="end-date" type="{http://www.orcid.org/ns/orcid}fuzzy-date" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}organization"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}source" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}created-date" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}last-modified-date" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.orcid.org/ns/orcid}visibility"/>
 *       &lt;attGroup ref="{http://www.orcid.org/ns/orcid}put-code"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "type",
    "departmentName",
    "roleTitle",
    "startDate",
    "endDate",
    "organization",
    "source",
    "createdDate",
    "lastModifiedDate"
})
@XmlRootElement(name = "affiliation")
public class Affiliation {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected AffiliationType type;
    @XmlElement(name = "department-name")
    protected String departmentName;
    @XmlElement(name = "role-title")
    protected String roleTitle;
    @XmlElement(name = "start-date")
    protected FuzzyDate startDate;
    @XmlElement(name = "end-date")
    protected FuzzyDate endDate;
    @XmlElement(required = true)
    protected Organization organization;
    protected Source source;
    @XmlElement(name = "created-date")
    protected CreatedDate createdDate;
    @XmlElement(name = "last-modified-date")
    protected LastModifiedDate lastModifiedDate;
    @XmlAttribute(name = "visibility")
    protected Visibility visibility;
    @XmlAttribute(name = "put-code")
    protected BigInteger putCode;

    /**
     * Recupera il valore della proprietà type.
     * 
     * @return
     *     possible object is
     *     {@link AffiliationType }
     *     
     */
    public AffiliationType getType() {
        return type;
    }

    /**
     * Imposta il valore della proprietà type.
     * 
     * @param value
     *     allowed object is
     *     {@link AffiliationType }
     *     
     */
    public void setType(AffiliationType value) {
        this.type = value;
    }

    /**
     * Recupera il valore della proprietà departmentName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Imposta il valore della proprietà departmentName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentName(String value) {
        this.departmentName = value;
    }

    /**
     * Recupera il valore della proprietà roleTitle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleTitle() {
        return roleTitle;
    }

    /**
     * Imposta il valore della proprietà roleTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleTitle(String value) {
        this.roleTitle = value;
    }

    /**
     * Recupera il valore della proprietà startDate.
     * 
     * @return
     *     possible object is
     *     {@link FuzzyDate }
     *     
     */
    public FuzzyDate getStartDate() {
        return startDate;
    }

    /**
     * Imposta il valore della proprietà startDate.
     * 
     * @param value
     *     allowed object is
     *     {@link FuzzyDate }
     *     
     */
    public void setStartDate(FuzzyDate value) {
        this.startDate = value;
    }

    /**
     * Recupera il valore della proprietà endDate.
     * 
     * @return
     *     possible object is
     *     {@link FuzzyDate }
     *     
     */
    public FuzzyDate getEndDate() {
        return endDate;
    }

    /**
     * Imposta il valore della proprietà endDate.
     * 
     * @param value
     *     allowed object is
     *     {@link FuzzyDate }
     *     
     */
    public void setEndDate(FuzzyDate value) {
        this.endDate = value;
    }

    /**
     * Recupera il valore della proprietà organization.
     * 
     * @return
     *     possible object is
     *     {@link Organization }
     *     
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * Imposta il valore della proprietà organization.
     * 
     * @param value
     *     allowed object is
     *     {@link Organization }
     *     
     */
    public void setOrganization(Organization value) {
        this.organization = value;
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

    /**
     * Recupera il valore della proprietà createdDate.
     * 
     * @return
     *     possible object is
     *     {@link CreatedDate }
     *     
     */
    public CreatedDate getCreatedDate() {
        return createdDate;
    }

    /**
     * Imposta il valore della proprietà createdDate.
     * 
     * @param value
     *     allowed object is
     *     {@link CreatedDate }
     *     
     */
    public void setCreatedDate(CreatedDate value) {
        this.createdDate = value;
    }

    /**
     * Recupera il valore della proprietà lastModifiedDate.
     * 
     * @return
     *     possible object is
     *     {@link LastModifiedDate }
     *     
     */
    public LastModifiedDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Imposta il valore della proprietà lastModifiedDate.
     * 
     * @param value
     *     allowed object is
     *     {@link LastModifiedDate }
     *     
     */
    public void setLastModifiedDate(LastModifiedDate value) {
        this.lastModifiedDate = value;
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

    /**
     * Recupera il valore della proprietà putCode.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPutCode() {
        return putCode;
    }

    /**
     * Imposta il valore della proprietà putCode.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPutCode(BigInteger value) {
        this.putCode = value;
    }

}
