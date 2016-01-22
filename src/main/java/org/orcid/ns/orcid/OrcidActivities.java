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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}affiliations" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}orcid-works" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}funding-list" minOccurs="0"/>
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
    "affiliations",
    "orcidWorks",
    "fundingList"
})
@XmlRootElement(name = "orcid-activities")
public class OrcidActivities {

    protected Affiliations affiliations;
    @XmlElement(name = "orcid-works")
    protected OrcidWorks orcidWorks;
    @XmlElement(name = "funding-list")
    protected FundingList fundingList;

    /**
     * Recupera il valore della proprietà affiliations.
     * 
     * @return
     *     possible object is
     *     {@link Affiliations }
     *     
     */
    public Affiliations getAffiliations() {
        return affiliations;
    }

    /**
     * Imposta il valore della proprietà affiliations.
     * 
     * @param value
     *     allowed object is
     *     {@link Affiliations }
     *     
     */
    public void setAffiliations(Affiliations value) {
        this.affiliations = value;
    }

    /**
     * Recupera il valore della proprietà orcidWorks.
     * 
     * @return
     *     possible object is
     *     {@link OrcidWorks }
     *     
     */
    public OrcidWorks getOrcidWorks() {
        return orcidWorks;
    }

    /**
     * Imposta il valore della proprietà orcidWorks.
     * 
     * @param value
     *     allowed object is
     *     {@link OrcidWorks }
     *     
     */
    public void setOrcidWorks(OrcidWorks value) {
        this.orcidWorks = value;
    }

    /**
     * Recupera il valore della proprietà fundingList.
     * 
     * @return
     *     possible object is
     *     {@link FundingList }
     *     
     */
    public FundingList getFundingList() {
        return fundingList;
    }

    /**
     * Imposta il valore della proprietà fundingList.
     * 
     * @param value
     *     allowed object is
     *     {@link FundingList }
     *     
     */
    public void setFundingList(FundingList value) {
        this.fundingList = value;
    }

}
