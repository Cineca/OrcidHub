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
 * <p>Classe Java per creation-method.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="creation-method">
 *   &lt;restriction base="{http://www.orcid.org/ns/orcid}non-empty-string">
 *     &lt;enumeration value="API"/>
 *     &lt;enumeration value="Direct"/>
 *     &lt;enumeration value="Member-referred"/>
 *     &lt;enumeration value="website"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "creation-method")
@XmlEnum
public enum CreationMethod {


    /**
     * The ORCID Record was created using the ORCID
     * 						Member API.
     * 					
     * 
     */
    API("API"),

    /**
     * The user registered on the /register page.
     * 					
     * 
     */
    @XmlEnumValue("Direct")
    DIRECT("Direct"),

    /**
     * The registered on the /oauth/signin.
     * 					
     * 
     */
    @XmlEnumValue("Member-referred")
    MEMBER_REFERRED("Member-referred"),

    /**
     * DEPRECATED use Direct or Member-referred
     * 						instead. The method used to create the ORCID Record (Website or
     * 						API).
     * 					
     * 
     */
    @XmlEnumValue("website")
    WEBSITE("website");
    private final String value;

    CreationMethod(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CreationMethod fromValue(String v) {
        for (CreationMethod c: CreationMethod.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
