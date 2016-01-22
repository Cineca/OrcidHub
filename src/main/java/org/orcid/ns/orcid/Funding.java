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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}funding-type"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}organization-defined-type" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}funding-title"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}short-description" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}amount" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}url" minOccurs="0"/>
 *         &lt;element name="start-date" type="{http://www.orcid.org/ns/orcid}fuzzy-date" minOccurs="0"/>
 *         &lt;element name="end-date" type="{http://www.orcid.org/ns/orcid}fuzzy-date" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}funding-external-identifiers" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}funding-contributors" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}organization"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}source" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}created-date" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}last-modified-date" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.orcid.org/ns/orcid}put-code"/>
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
    "fundingType",
    "organizationDefinedType",
    "fundingTitle",
    "shortDescription",
    "amount",
    "url",
    "startDate",
    "endDate",
    "fundingExternalIdentifiers",
    "fundingContributors",
    "organization",
    "source",
    "createdDate",
    "lastModifiedDate"
})
@XmlRootElement(name = "funding")
public class Funding {

    @XmlElement(name = "funding-type", required = true)
    protected String fundingType;
    @XmlElement(name = "organization-defined-type")
    protected String organizationDefinedType;
    @XmlElement(name = "funding-title", required = true)
    protected FundingTitle fundingTitle;
    @XmlElement(name = "short-description")
    protected String shortDescription;
    protected Amount amount;
    protected Url url;
    @XmlElement(name = "start-date")
    protected FuzzyDate startDate;
    @XmlElement(name = "end-date")
    protected FuzzyDate endDate;
    @XmlElement(name = "funding-external-identifiers")
    protected FundingExternalIdentifiers fundingExternalIdentifiers;
    @XmlElement(name = "funding-contributors")
    protected FundingContributors fundingContributors;
    @XmlElement(required = true)
    protected Organization organization;
    protected Source source;
    @XmlElement(name = "created-date")
    protected CreatedDate createdDate;
    @XmlElement(name = "last-modified-date")
    protected LastModifiedDate lastModifiedDate;
    @XmlAttribute(name = "put-code")
    protected BigInteger putCode;
    @XmlAttribute(name = "visibility")
    protected Visibility visibility;

    /**
     * Recupera il valore della proprietà fundingType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundingType() {
        return fundingType;
    }

    /**
     * Imposta il valore della proprietà fundingType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundingType(String value) {
        this.fundingType = value;
    }

    /**
     * Recupera il valore della proprietà organizationDefinedType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationDefinedType() {
        return organizationDefinedType;
    }

    /**
     * Imposta il valore della proprietà organizationDefinedType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationDefinedType(String value) {
        this.organizationDefinedType = value;
    }

    /**
     * Recupera il valore della proprietà fundingTitle.
     * 
     * @return
     *     possible object is
     *     {@link FundingTitle }
     *     
     */
    public FundingTitle getFundingTitle() {
        return fundingTitle;
    }

    /**
     * Imposta il valore della proprietà fundingTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link FundingTitle }
     *     
     */
    public void setFundingTitle(FundingTitle value) {
        this.fundingTitle = value;
    }

    /**
     * Recupera il valore della proprietà shortDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Imposta il valore della proprietà shortDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortDescription(String value) {
        this.shortDescription = value;
    }

    /**
     * Recupera il valore della proprietà amount.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Imposta il valore della proprietà amount.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAmount(Amount value) {
        this.amount = value;
    }

    /**
     * Recupera il valore della proprietà url.
     * 
     * @return
     *     possible object is
     *     {@link Url }
     *     
     */
    public Url getUrl() {
        return url;
    }

    /**
     * Imposta il valore della proprietà url.
     * 
     * @param value
     *     allowed object is
     *     {@link Url }
     *     
     */
    public void setUrl(Url value) {
        this.url = value;
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
     * Recupera il valore della proprietà fundingExternalIdentifiers.
     * 
     * @return
     *     possible object is
     *     {@link FundingExternalIdentifiers }
     *     
     */
    public FundingExternalIdentifiers getFundingExternalIdentifiers() {
        return fundingExternalIdentifiers;
    }

    /**
     * Imposta il valore della proprietà fundingExternalIdentifiers.
     * 
     * @param value
     *     allowed object is
     *     {@link FundingExternalIdentifiers }
     *     
     */
    public void setFundingExternalIdentifiers(FundingExternalIdentifiers value) {
        this.fundingExternalIdentifiers = value;
    }

    /**
     * Recupera il valore della proprietà fundingContributors.
     * 
     * @return
     *     possible object is
     *     {@link FundingContributors }
     *     
     */
    public FundingContributors getFundingContributors() {
        return fundingContributors;
    }

    /**
     * Imposta il valore della proprietà fundingContributors.
     * 
     * @param value
     *     allowed object is
     *     {@link FundingContributors }
     *     
     */
    public void setFundingContributors(FundingContributors value) {
        this.fundingContributors = value;
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
