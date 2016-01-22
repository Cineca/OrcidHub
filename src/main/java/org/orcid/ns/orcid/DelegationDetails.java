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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}approval-date"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}delegate-summary"/>
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
    "approvalDate",
    "delegateSummary"
})
@XmlRootElement(name = "delegation-details")
public class DelegationDetails {

    @XmlElement(name = "approval-date", required = true)
    protected ApprovalDate approvalDate;
    @XmlElement(name = "delegate-summary", required = true)
    protected DelegateSummary delegateSummary;

    /**
     * Recupera il valore della proprietà approvalDate.
     * 
     * @return
     *     possible object is
     *     {@link ApprovalDate }
     *     
     */
    public ApprovalDate getApprovalDate() {
        return approvalDate;
    }

    /**
     * Imposta il valore della proprietà approvalDate.
     * 
     * @param value
     *     allowed object is
     *     {@link ApprovalDate }
     *     
     */
    public void setApprovalDate(ApprovalDate value) {
        this.approvalDate = value;
    }

    /**
     * Recupera il valore della proprietà delegateSummary.
     * 
     * @return
     *     possible object is
     *     {@link DelegateSummary }
     *     
     */
    public DelegateSummary getDelegateSummary() {
        return delegateSummary;
    }

    /**
     * Imposta il valore della proprietà delegateSummary.
     * 
     * @param value
     *     allowed object is
     *     {@link DelegateSummary }
     *     
     */
    public void setDelegateSummary(DelegateSummary value) {
        this.delegateSummary = value;
    }

}
