
package org.datacontract.schemas._2004._07.landmark_classes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBookingMasterData complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfBookingMasterData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BookingMasterData" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}BookingMasterData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBookingMasterData", propOrder = { "bookingMasterData" })
public class ArrayOfBookingMasterData {

    @XmlElement(name = "BookingMasterData", nillable = true)
    protected List<BookingMasterData> bookingMasterData;

    /**
     * Gets the value of the bookingMasterData property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bookingMasterData property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBookingMasterData().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BookingMasterData }
     *
     *
     */
    public List<BookingMasterData> getBookingMasterData() {
        if (bookingMasterData == null) {
            bookingMasterData = new ArrayList<BookingMasterData>();
        }
        return this.bookingMasterData;
    }

}
