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
 * <p>Classe Java per visibility.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="visibility">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="private"/>
 *     &lt;enumeration value="limited"/>
 *     &lt;enumeration value="public"/>
 *     &lt;enumeration value="registered-only"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "visibility")
@XmlEnum
public enum Visibility {


    /**
     * The data can only be seen by the researcher or contributor. This data may be used internally by ORCID for Record disambiguation purposes.
     * 					
     * 
     */
    @XmlEnumValue("private")
    PRIVATE("private"),

    /**
     * The data can only be seen by trusted parties (organizations or people) as indicated by the researcher or contributor. This information is only shared with systems that the researcher or contributor has specifically granted authorization (using OAuth).
     * 					
     * 
     */
    @XmlEnumValue("limited")
    LIMITED("limited"),

    /**
     * The data can be seen by anyone. It is publically available via the ORCID Registry website and the public API without further authroization by the researcher or contributor.
     * 					
     * 
     */
    @XmlEnumValue("public")
    PUBLIC("public"),

    /**
     * The data is shared only with the registered user.
     * 					
     * 
     */
    @XmlEnumValue("registered-only")
    REGISTERED_ONLY("registered-only");
    private final String value;

    Visibility(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Visibility fromValue(String v) {
        for (Visibility c: Visibility.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
