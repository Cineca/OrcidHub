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
 * <p>Classe Java per scope-path-type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="scope-path-type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="/authenticate"/>
 *     &lt;enumeration value="/orcid-bio/read-limited"/>
 *     &lt;enumeration value="/orcid-profile/read-limited"/>
 *     &lt;enumeration value="/orcid-works/read-limited"/>
 *     &lt;enumeration value="/funding/read-limited"/>
 *     &lt;enumeration value="/orcid-patents/read-limited"/>
 *     &lt;enumeration value="/orcid-works/update"/>
 *     &lt;enumeration value="/funding/update"/>
 *     &lt;enumeration value="/orcid-patents/update"/>
 *     &lt;enumeration value="/orcid-bio/external-identifiers/create"/>
 *     &lt;enumeration value="/orcid-bio/update"/>
 *     &lt;enumeration value="/orcid-works/create"/>
 *     &lt;enumeration value="/funding/create"/>
 *     &lt;enumeration value="/orcid-patents/create"/>
 *     &lt;enumeration value="/orcid-profile/create"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "scope-path-type")
@XmlEnum
public enum ScopePathType {

    @XmlEnumValue("/authenticate")
    AUTHENTICATE("/authenticate"),
    @XmlEnumValue("/orcid-bio/read-limited")
    ORCID_BIO_READ_LIMITED("/orcid-bio/read-limited"),
    @XmlEnumValue("/orcid-profile/read-limited")
    ORCID_PROFILE_READ_LIMITED("/orcid-profile/read-limited"),
    @XmlEnumValue("/orcid-works/read-limited")
    ORCID_WORKS_READ_LIMITED("/orcid-works/read-limited"),
    @XmlEnumValue("/funding/read-limited")
    FUNDING_READ_LIMITED("/funding/read-limited"),
    @XmlEnumValue("/orcid-patents/read-limited")
    ORCID_PATENTS_READ_LIMITED("/orcid-patents/read-limited"),
    @XmlEnumValue("/orcid-works/update")
    ORCID_WORKS_UPDATE("/orcid-works/update"),
    @XmlEnumValue("/funding/update")
    FUNDING_UPDATE("/funding/update"),
    @XmlEnumValue("/orcid-patents/update")
    ORCID_PATENTS_UPDATE("/orcid-patents/update"),
    @XmlEnumValue("/orcid-bio/external-identifiers/create")
    ORCID_BIO_EXTERNAL_IDENTIFIERS_CREATE("/orcid-bio/external-identifiers/create"),
    @XmlEnumValue("/orcid-bio/update")
    ORCID_BIO_UPDATE("/orcid-bio/update"),
    @XmlEnumValue("/orcid-works/create")
    ORCID_WORKS_CREATE("/orcid-works/create"),
    @XmlEnumValue("/funding/create")
    FUNDING_CREATE("/funding/create"),
    @XmlEnumValue("/orcid-patents/create")
    ORCID_PATENTS_CREATE("/orcid-patents/create"),
    @XmlEnumValue("/orcid-profile/create")
    ORCID_PROFILE_CREATE("/orcid-profile/create");
    private final String value;

    ScopePathType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ScopePathType fromValue(String v) {
        for (ScopePathType c: ScopePathType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
