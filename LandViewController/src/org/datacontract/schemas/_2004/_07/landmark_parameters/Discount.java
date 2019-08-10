
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Discount complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Discount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DiscountCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Discount", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
         propOrder = { "discountCode" })
public class Discount {

    @XmlElementRef(name = "DiscountCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> discountCode;

    /**
     * Gets the value of the discountCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getDiscountCode() {
        return discountCode;
    }

    /**
     * Sets the value of the discountCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setDiscountCode(JAXBElement<String> value) {
        this.discountCode = value;
    }

}
