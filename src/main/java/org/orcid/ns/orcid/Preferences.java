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
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}send-email-frequency-days"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}send-change-notifications"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}send-administrative-change-notifications"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}send-orcid-news"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}send-member-update-requests"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}activities-visibility-default"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}developer-tools-enabled" minOccurs="0"/>
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
    "sendEmailFrequencyDays",
    "sendChangeNotifications",
    "sendAdministrativeChangeNotifications",
    "sendOrcidNews",
    "sendMemberUpdateRequests",
    "activitiesVisibilityDefault",
    "developerToolsEnabled"
})
@XmlRootElement(name = "preferences")
public class Preferences {

    @XmlElement(name = "send-email-frequency-days", required = true)
    protected String sendEmailFrequencyDays;
    @XmlElement(name = "send-change-notifications", required = true)
    protected SendChangeNotifications sendChangeNotifications;
    @XmlElement(name = "send-administrative-change-notifications", required = true)
    protected SendAdministrativeChangeNotifications sendAdministrativeChangeNotifications;
    @XmlElement(name = "send-orcid-news", required = true)
    protected SendOrcidNews sendOrcidNews;
    @XmlElement(name = "send-member-update-requests")
    protected boolean sendMemberUpdateRequests;
    @XmlElement(name = "activities-visibility-default", required = true)
    protected ActivitiesVisibilityDefault activitiesVisibilityDefault;
    @XmlElement(name = "developer-tools-enabled")
    protected DeveloperToolsEnabled developerToolsEnabled;

    /**
     * Recupera il valore della proprietà sendEmailFrequencyDays.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendEmailFrequencyDays() {
        return sendEmailFrequencyDays;
    }

    /**
     * Imposta il valore della proprietà sendEmailFrequencyDays.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendEmailFrequencyDays(String value) {
        this.sendEmailFrequencyDays = value;
    }

    /**
     * Recupera il valore della proprietà sendChangeNotifications.
     * 
     * @return
     *     possible object is
     *     {@link SendChangeNotifications }
     *     
     */
    public SendChangeNotifications getSendChangeNotifications() {
        return sendChangeNotifications;
    }

    /**
     * Imposta il valore della proprietà sendChangeNotifications.
     * 
     * @param value
     *     allowed object is
     *     {@link SendChangeNotifications }
     *     
     */
    public void setSendChangeNotifications(SendChangeNotifications value) {
        this.sendChangeNotifications = value;
    }

    /**
     * Recupera il valore della proprietà sendAdministrativeChangeNotifications.
     * 
     * @return
     *     possible object is
     *     {@link SendAdministrativeChangeNotifications }
     *     
     */
    public SendAdministrativeChangeNotifications getSendAdministrativeChangeNotifications() {
        return sendAdministrativeChangeNotifications;
    }

    /**
     * Imposta il valore della proprietà sendAdministrativeChangeNotifications.
     * 
     * @param value
     *     allowed object is
     *     {@link SendAdministrativeChangeNotifications }
     *     
     */
    public void setSendAdministrativeChangeNotifications(SendAdministrativeChangeNotifications value) {
        this.sendAdministrativeChangeNotifications = value;
    }

    /**
     * Recupera il valore della proprietà sendOrcidNews.
     * 
     * @return
     *     possible object is
     *     {@link SendOrcidNews }
     *     
     */
    public SendOrcidNews getSendOrcidNews() {
        return sendOrcidNews;
    }

    /**
     * Imposta il valore della proprietà sendOrcidNews.
     * 
     * @param value
     *     allowed object is
     *     {@link SendOrcidNews }
     *     
     */
    public void setSendOrcidNews(SendOrcidNews value) {
        this.sendOrcidNews = value;
    }

    /**
     * Recupera il valore della proprietà sendMemberUpdateRequests.
     * 
     */
    public boolean isSendMemberUpdateRequests() {
        return sendMemberUpdateRequests;
    }

    /**
     * Imposta il valore della proprietà sendMemberUpdateRequests.
     * 
     */
    public void setSendMemberUpdateRequests(boolean value) {
        this.sendMemberUpdateRequests = value;
    }

    /**
     * Recupera il valore della proprietà activitiesVisibilityDefault.
     * 
     * @return
     *     possible object is
     *     {@link ActivitiesVisibilityDefault }
     *     
     */
    public ActivitiesVisibilityDefault getActivitiesVisibilityDefault() {
        return activitiesVisibilityDefault;
    }

    /**
     * Imposta il valore della proprietà activitiesVisibilityDefault.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivitiesVisibilityDefault }
     *     
     */
    public void setActivitiesVisibilityDefault(ActivitiesVisibilityDefault value) {
        this.activitiesVisibilityDefault = value;
    }

    /**
     * Recupera il valore della proprietà developerToolsEnabled.
     * 
     * @return
     *     possible object is
     *     {@link DeveloperToolsEnabled }
     *     
     */
    public DeveloperToolsEnabled getDeveloperToolsEnabled() {
        return developerToolsEnabled;
    }

    /**
     * Imposta il valore della proprietà developerToolsEnabled.
     * 
     * @param value
     *     allowed object is
     *     {@link DeveloperToolsEnabled }
     *     
     */
    public void setDeveloperToolsEnabled(DeveloperToolsEnabled value) {
        this.developerToolsEnabled = value;
    }

}
