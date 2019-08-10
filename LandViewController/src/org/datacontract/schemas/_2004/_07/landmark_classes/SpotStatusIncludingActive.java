
package org.datacontract.schemas._2004._07.landmark_classes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SpotStatusIncludingActive complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SpotStatusIncludingActive">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="StatusText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpotStatusIncludingActive", propOrder = { "status", "statusText" })
public class SpotStatusIncludingActive {

    @XmlElement(name = "Status")
    protected Integer status;
    @XmlElementRef(name = "StatusText", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> statusText;

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    /**
     * Gets the value of the statusText property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getStatusText() {
        return statusText;
    }

    /**
     * Sets the value of the statusText property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setStatusText(JAXBElement<String> value) {
        this.statusText = value;
    }

}
