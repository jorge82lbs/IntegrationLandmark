
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
 *         &lt;element name="LoadForFilter2Result" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}ArrayOfSpot" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "loadForFilter2Result" })
@XmlRootElement(name = "LoadForFilter2Response")
public class LoadForFilter2Response {

    @XmlElementRef(name = "LoadForFilter2Result", namespace = "http://tempuri.org/", type = JAXBElement.class,
                   required = false)
    protected JAXBElement<ArrayOfSpot> loadForFilter2Result;

    /**
     * Gets the value of the loadForFilter2Result property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpot }{@code >}
     *
     */
    public JAXBElement<ArrayOfSpot> getLoadForFilter2Result() {
        return loadForFilter2Result;
    }

    /**
     * Sets the value of the loadForFilter2Result property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpot }{@code >}
     *
     */
    public void setLoadForFilter2Result(JAXBElement<ArrayOfSpot> value) {
        this.loadForFilter2Result = value;
    }

}
