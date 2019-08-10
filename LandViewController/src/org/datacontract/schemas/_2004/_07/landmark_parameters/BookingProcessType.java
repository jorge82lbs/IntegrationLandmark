
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BookingProcessType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BookingProcessType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Default"/>
 *     &lt;enumeration value="DontBook"/>
 *     &lt;enumeration value="BookFindNext"/>
 *     &lt;enumeration value="BookFindPrev"/>
 *     &lt;enumeration value="BookFindNearest"/>
 *     &lt;enumeration value="BookKeepBooking"/>
 *     &lt;enumeration value="RepriceOnly"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "BookingProcessType",
         namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams")
@XmlEnum
public enum BookingProcessType {

    @XmlEnumValue("Default")
    DEFAULT("Default"),
    @XmlEnumValue("DontBook")
    DONT_BOOK("DontBook"),
    @XmlEnumValue("BookFindNext")
    BOOK_FIND_NEXT("BookFindNext"),
    @XmlEnumValue("BookFindPrev")
    BOOK_FIND_PREV("BookFindPrev"),
    @XmlEnumValue("BookFindNearest")
    BOOK_FIND_NEAREST("BookFindNearest"),
    @XmlEnumValue("BookKeepBooking")
    BOOK_KEEP_BOOKING("BookKeepBooking"),
    @XmlEnumValue("RepriceOnly")
    REPRICE_ONLY("RepriceOnly");
    private final String value;

    BookingProcessType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BookingProcessType fromValue(String v) {
        for (BookingProcessType c : BookingProcessType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
