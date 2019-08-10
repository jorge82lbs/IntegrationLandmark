
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.datacontract.schemas._2004._07.landmark_classes.ArrayOfSpot;


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
 *         &lt;element name="LoadForFilterResult" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}ArrayOfSpot" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "loadForFilterResult" })
@XmlRootElement(name = "LoadForFilterResponse")
public class LoadForFilterResponse {

    @XmlElementRef(name = "LoadForFilterResult", namespace = "http://tempuri.org/", type = JAXBElement.class,
                   required = false)
    protected JAXBElement<ArrayOfSpot> loadForFilterResult;

    /**
     * Gets the value of the loadForFilterResult property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpot }{@code >}
     *
     */
    public JAXBElement<ArrayOfSpot> getLoadForFilterResult() {
        return loadForFilterResult;
    }

    /**
     * Sets the value of the loadForFilterResult property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpot }{@code >}
     *
     */
    public void setLoadForFilterResult(JAXBElement<ArrayOfSpot> value) {
        this.loadForFilterResult = value;
    }

}
