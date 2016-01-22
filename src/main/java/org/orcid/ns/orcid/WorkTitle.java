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
 *         &lt;element name="title" type="{http://www.orcid.org/ns/orcid}non-empty-string"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}subtitle" minOccurs="0"/>
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}translated-title" minOccurs="0"/>
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
    "title",
    "subtitle",
    "translatedTitle"
})
@XmlRootElement(name = "work-title")
public class WorkTitle {

    @XmlElement(required = true)
    protected String title;
    protected Subtitle subtitle;
    @XmlElement(name = "translated-title")
    protected TranslatedTitle translatedTitle;

    /**
     * Recupera il valore della proprietà title.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposta il valore della proprietà title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Recupera il valore della proprietà subtitle.
     * 
     * @return
     *     possible object is
     *     {@link Subtitle }
     *     
     */
    public Subtitle getSubtitle() {
        return subtitle;
    }

    /**
     * Imposta il valore della proprietà subtitle.
     * 
     * @param value
     *     allowed object is
     *     {@link Subtitle }
     *     
     */
    public void setSubtitle(Subtitle value) {
        this.subtitle = value;
    }

    /**
     * Recupera il valore della proprietà translatedTitle.
     * 
     * @return
     *     possible object is
     *     {@link TranslatedTitle }
     *     
     */
    public TranslatedTitle getTranslatedTitle() {
        return translatedTitle;
    }

    /**
     * Imposta il valore della proprietà translatedTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link TranslatedTitle }
     *     
     */
    public void setTranslatedTitle(TranslatedTitle value) {
        this.translatedTitle = value;
    }

}
