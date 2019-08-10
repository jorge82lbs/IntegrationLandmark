
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SpotListFilterCriteria.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SpotListFilterCriteria">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GetByCampaign"/>
 *     &lt;enumeration value="GetByClient"/>
 *     &lt;enumeration value="GetByDeal"/>
 *     &lt;enumeration value="GetByProduct"/>
 *     &lt;enumeration value="GetBySalesArea"/>
 *     &lt;enumeration value="GetBySpot"/>
 *     &lt;enumeration value="GetBySpotClone"/>
 *     &lt;enumeration value="GetByBreak"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "SpotListFilterCriteria",
         namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes")
@XmlEnum
public enum SpotListFilterCriteria {

    @XmlEnumValue("GetByCampaign")
    GET_BY_CAMPAIGN("GetByCampaign"),
    @XmlEnumValue("GetByClient")
    GET_BY_CLIENT("GetByClient"),
    @XmlEnumValue("GetByDeal")
    GET_BY_DEAL("GetByDeal"),
    @XmlEnumValue("GetByProduct")
    GET_BY_PRODUCT("GetByProduct"),
    @XmlEnumValue("GetBySalesArea")
    GET_BY_SALES_AREA("GetBySalesArea"),
    @XmlEnumValue("GetBySpot")
    GET_BY_SPOT("GetBySpot"),
    @XmlEnumValue("GetBySpotClone")
    GET_BY_SPOT_CLONE("GetBySpotClone"),
    @XmlEnumValue("GetByBreak")
    GET_BY_BREAK("GetByBreak");
    private final String value;

    SpotListFilterCriteria(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SpotListFilterCriteria fromValue(String v) {
        for (SpotListFilterCriteria c : SpotListFilterCriteria.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
