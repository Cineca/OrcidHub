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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}encrypted-password"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}security-question-id" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}encrypted-security-answer" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}encrypted-verification-code"/>
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
    "encryptedPassword",
    "securityQuestionId",
    "encryptedSecurityAnswer",
    "encryptedVerificationCode"
})
@XmlRootElement(name = "security-details")
public class SecurityDetails {

    @XmlElement(name = "encrypted-password", required = true)
    protected EncryptedPassword encryptedPassword;
    @XmlElement(name = "security-question-id")
    protected SecurityQuestionId securityQuestionId;
    @XmlElement(name = "encrypted-security-answer")
    protected EncryptedSecurityAnswer encryptedSecurityAnswer;
    @XmlElement(name = "encrypted-verification-code", required = true)
    protected EncryptedVerificationCode encryptedVerificationCode;

    /**
     * Recupera il valore della proprietà encryptedPassword.
     * 
     * @return
     *     possible object is
     *     {@link EncryptedPassword }
     *     
     */
    public EncryptedPassword getEncryptedPassword() {
        return encryptedPassword;
    }

    /**
     * Imposta il valore della proprietà encryptedPassword.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedPassword }
     *     
     */
    public void setEncryptedPassword(EncryptedPassword value) {
        this.encryptedPassword = value;
    }

    /**
     * Recupera il valore della proprietà securityQuestionId.
     * 
     * @return
     *     possible object is
     *     {@link SecurityQuestionId }
     *     
     */
    public SecurityQuestionId getSecurityQuestionId() {
        return securityQuestionId;
    }

    /**
     * Imposta il valore della proprietà securityQuestionId.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityQuestionId }
     *     
     */
    public void setSecurityQuestionId(SecurityQuestionId value) {
        this.securityQuestionId = value;
    }

    /**
     * Recupera il valore della proprietà encryptedSecurityAnswer.
     * 
     * @return
     *     possible object is
     *     {@link EncryptedSecurityAnswer }
     *     
     */
    public EncryptedSecurityAnswer getEncryptedSecurityAnswer() {
        return encryptedSecurityAnswer;
    }

    /**
     * Imposta il valore della proprietà encryptedSecurityAnswer.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedSecurityAnswer }
     *     
     */
    public void setEncryptedSecurityAnswer(EncryptedSecurityAnswer value) {
        this.encryptedSecurityAnswer = value;
    }

    /**
     * Recupera il valore della proprietà encryptedVerificationCode.
     * 
     * @return
     *     possible object is
     *     {@link EncryptedVerificationCode }
     *     
     */
    public EncryptedVerificationCode getEncryptedVerificationCode() {
        return encryptedVerificationCode;
    }

    /**
     * Imposta il valore della proprietà encryptedVerificationCode.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedVerificationCode }
     *     
     */
    public void setEncryptedVerificationCode(EncryptedVerificationCode value) {
        this.encryptedVerificationCode = value;
    }

}
