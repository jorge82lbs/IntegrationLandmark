
package org.datacontract.schemas._2004._07.landmark_classes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BookProcessType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BookProcessType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BookProcessTypeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BookProcessTypeID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookProcessType", propOrder = { "bookProcessTypeDescription", "bookProcessTypeID" })
public class BookProcessType {

    @XmlElementRef(name = "BookProcessTypeDescription",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> bookProcessTypeDescription;
    @XmlElement(name = "BookProcessTypeID")
    protected Integer bookProcessTypeID;

    /**
     * Gets the value of the bookProcessTypeDescription property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getBookProcessTypeDescription() {
        return bookProcessTypeDescription;
    }

    /**
     * Sets the value of the bookProcessTypeDescription property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setBookProcessTypeDescription(JAXBElement<String> value) {
        this.bookProcessTypeDescription = value;
    }

    /**
     * Gets the value of the bookProcessTypeID property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBookProcessTypeID() {
        return bookProcessTypeID;
    }

    /**
     * Sets the value of the bookProcessTypeID property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBookProcessTypeID(Integer value) {
        this.bookProcessTypeID = value;
    }

}
