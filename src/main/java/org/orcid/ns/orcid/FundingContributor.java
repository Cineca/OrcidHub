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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}contributor-orcid" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}credit-name" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}contributor-email" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}funding-contributor-attributes" minOccurs="0"/>
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
    "contributorOrcid",
    "creditName",
    "contributorEmail",
    "fundingContributorAttributes"
})
@XmlRootElement(name = "funding-contributor")
public class FundingContributor {

    @XmlElement(name = "contributor-orcid")
    protected OrcidId contributorOrcid;
    @XmlElement(name = "credit-name")
    protected CreditName creditName;
    @XmlElement(name = "contributor-email")
    protected ContributorEmail contributorEmail;
    @XmlElement(name = "funding-contributor-attributes")
    protected FundingContributorAttributes fundingContributorAttributes;

    /**
     * Recupera il valore della proprietà contributorOrcid.
     * 
     * @return
     *     possible object is
     *     {@link OrcidId }
     *     
     */
    public OrcidId getContributorOrcid() {
        return contributorOrcid;
    }

    /**
     * Imposta il valore della proprietà contributorOrcid.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidId }
     *     
     */
    public void setContributorOrcid(OrcidId value) {
        this.contributorOrcid = value;
    }

    /**
     * Recupera il valore della proprietà creditName.
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
     * Recupera il valore della proprietà contributorEmail.
     * 
     * @return
     *     possible object is
     *     {@link ContributorEmail }
     *     
     */
    public ContributorEmail getContributorEmail() {
        return contributorEmail;
    }

    /**
     * Imposta il valore della proprietà contributorEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link ContributorEmail }
     *     
     */
    public void setContributorEmail(ContributorEmail value) {
        this.contributorEmail = value;
    }

    /**
     * Recupera il valore della proprietà fundingContributorAttributes.
     * 
     * @return
     *     possible object is
     *     {@link FundingContributorAttributes }
     *     
     */
    public FundingContributorAttributes getFundingContributorAttributes() {
        return fundingContributorAttributes;
    }

    /**
     * Imposta il valore della proprietà fundingContributorAttributes.
     * 
     * @param value
     *     allowed object is
     *     {@link FundingContributorAttributes }
     *     
     */
    public void setFundingContributorAttributes(FundingContributorAttributes value) {
        this.fundingContributorAttributes = value;
    }

}
