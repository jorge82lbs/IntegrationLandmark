
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.datacontract.schemas._2004._07.landmark_classes.BookingResults;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BatchBookingsResult" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}BookingResults" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "batchBookingsResult" })
@XmlRootElement(name = "BatchBookingsResponse")
public class BatchBookingsResponse {

    @XmlElementRef(name = "BatchBookingsResult", namespace = "http://tempuri.org/", type = JAXBElement.class,
                   required = false)
    protected JAXBElement<BookingResults> batchBookingsResult;

    /**
     * Gets the value of the batchBookingsResult property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BookingResults }{@code >}
     *
     */
    public JAXBElement<BookingResults> getBatchBookingsResult() {
        return batchBookingsResult;
    }

    /**
     * Sets the value of the batchBookingsResult property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BookingResults }{@code >}
     *
     */
    public void setBatchBookingsResult(JAXBElement<BookingResults> value) {
        this.batchBookingsResult = value;
    }

}
