
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bkpo.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Bkpo">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="FST"/>
 *     &lt;enumeration value="SND"/>
 *     &lt;enumeration value="THD"/>
 *     &lt;enumeration value="APN"/>
 *     &lt;enumeration value="PEN"/>
 *     &lt;enumeration value="LST"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "Bkpo", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes")
@XmlEnum
public enum Bkpo {

    @XmlEnumValue("None")
    NONE("None"),
    FST("FST"),
    SND("SND"),
    THD("THD"),
    APN("APN"),
    PEN("PEN"),
    LST("LST");
    private final String value;

    Bkpo(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Bkpo fromValue(String v) {
        for (Bkpo c : Bkpo.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
