
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SpotStatus.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SpotStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Spot"/>
 *     &lt;enumeration value="Option"/>
 *     &lt;enumeration value="InDispute"/>
 *     &lt;enumeration value="Limbo"/>
 *     &lt;enumeration value="Cancelled"/>
 *     &lt;enumeration value="Any"/>
 *     &lt;enumeration value="PreEmpted"/>
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="Proposed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "SpotStatus", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes")
@XmlEnum
public enum SpotStatus {

    @XmlEnumValue("Spot")
    SPOT("Spot"),
    @XmlEnumValue("Option")
    OPTION("Option"),
    @XmlEnumValue("InDispute")
    IN_DISPUTE("InDispute"),
    @XmlEnumValue("Limbo")
    LIMBO("Limbo"),
    @XmlEnumValue("Cancelled")
    CANCELLED("Cancelled"),
    @XmlEnumValue("Any")
    ANY("Any"),
    @XmlEnumValue("PreEmpted")
    PRE_EMPTED("PreEmpted"),
    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Proposed")
    PROPOSED("Proposed");
    private final String value;

    SpotStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SpotStatus fromValue(String v) {
        for (SpotStatus c : SpotStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
