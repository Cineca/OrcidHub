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

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per citation-type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="citation-type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="formatted-unspecified"/>
 *     &lt;enumeration value="bibtex"/>
 *     &lt;enumeration value="ris"/>
 *     &lt;enumeration value="formatted-apa"/>
 *     &lt;enumeration value="formatted-harvard"/>
 *     &lt;enumeration value="formatted-ieee"/>
 *     &lt;enumeration value="formatted-mla"/>
 *     &lt;enumeration value="formatted-vancouver"/>
 *     &lt;enumeration value="formatted-chicago"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "citation-type")
@XmlEnum
public enum CitationType {

    @XmlEnumValue("formatted-unspecified")
    FORMATTED_UNSPECIFIED("formatted-unspecified"),
    @XmlEnumValue("bibtex")
    BIBTEX("bibtex"),
    @XmlEnumValue("ris")
    RIS("ris"),
    @XmlEnumValue("formatted-apa")
    FORMATTED_APA("formatted-apa"),
    @XmlEnumValue("formatted-harvard")
    FORMATTED_HARVARD("formatted-harvard"),
    @XmlEnumValue("formatted-ieee")
    FORMATTED_IEEE("formatted-ieee"),
    @XmlEnumValue("formatted-mla")
    FORMATTED_MLA("formatted-mla"),
    @XmlEnumValue("formatted-vancouver")
    FORMATTED_VANCOUVER("formatted-vancouver"),
    @XmlEnumValue("formatted-chicago")
    FORMATTED_CHICAGO("formatted-chicago");
    private final String value;

    CitationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CitationType fromValue(String v) {
        for (CitationType c: CitationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
