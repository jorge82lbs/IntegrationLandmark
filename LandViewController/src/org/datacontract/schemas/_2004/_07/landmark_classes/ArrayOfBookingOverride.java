
package org.datacontract.schemas._2004._07.landmark_classes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBookingOverride complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfBookingOverride">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BookingOverride" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}BookingOverride" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBookingOverride", propOrder = { "bookingOverride" })
public class ArrayOfBookingOverride {

    @XmlElement(name = "BookingOverride", nillable = true)
    protected List<BookingOverride> bookingOverride;

    /**
     * Gets the value of the bookingOverride property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bookingOverride property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBookingOverride().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BookingOverride }
     *
     *
     */
    public List<BookingOverride> getBookingOverride() {
        if (bookingOverride == null) {
            bookingOverride = new ArrayList<BookingOverride>();
        }
        return this.bookingOverride;
    }

}
