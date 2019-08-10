
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DaysOfWeek.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DaysOfWeek">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Monday"/>
 *     &lt;enumeration value="Tuesday"/>
 *     &lt;enumeration value="Wednesday"/>
 *     &lt;enumeration value="Thursday"/>
 *     &lt;enumeration value="Friday"/>
 *     &lt;enumeration value="Saturday"/>
 *     &lt;enumeration value="Sunday"/>
 *     &lt;enumeration value="AllDays"/>
 *     &lt;enumeration value="NoDays"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "DaysOfWeek", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes")
@XmlEnum
public enum DaysOfWeek {

    @XmlEnumValue("Monday")
    MONDAY("Monday"),
    @XmlEnumValue("Tuesday")
    TUESDAY("Tuesday"),
    @XmlEnumValue("Wednesday")
    WEDNESDAY("Wednesday"),
    @XmlEnumValue("Thursday")
    THURSDAY("Thursday"),
    @XmlEnumValue("Friday")
    FRIDAY("Friday"),
    @XmlEnumValue("Saturday")
    SATURDAY("Saturday"),
    @XmlEnumValue("Sunday")
    SUNDAY("Sunday"),
    @XmlEnumValue("AllDays")
    ALL_DAYS("AllDays"),
    @XmlEnumValue("NoDays")
    NO_DAYS("NoDays");
    private final String value;

    DaysOfWeek(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DaysOfWeek fromValue(String v) {
        for (DaysOfWeek c : DaysOfWeek.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
