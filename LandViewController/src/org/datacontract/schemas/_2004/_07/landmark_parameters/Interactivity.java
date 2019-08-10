
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Interactivity.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Interactivity">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="All"/>
 *     &lt;enumeration value="Interactive"/>
 *     &lt;enumeration value="NonInteractive"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "Interactivity", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes")
@XmlEnum
public enum Interactivity {

    @XmlEnumValue("All")
    ALL("All"),
    @XmlEnumValue("Interactive")
    INTERACTIVE("Interactive"),
    @XmlEnumValue("NonInteractive")
    NON_INTERACTIVE("NonInteractive");
    private final String value;

    Interactivity(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Interactivity fromValue(String v) {
        for (Interactivity c : Interactivity.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
