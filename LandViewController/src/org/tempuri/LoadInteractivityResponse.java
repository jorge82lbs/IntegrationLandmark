
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.datacontract.schemas._2004._07.landmark_classes.ArrayOfInteractivite;


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
 *         &lt;element name="LoadInteractivityResult" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}ArrayOfInteractivite" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "loadInteractivityResult" })
@XmlRootElement(name = "LoadInteractivityResponse")
public class LoadInteractivityResponse {

    @XmlElementRef(name = "LoadInteractivityResult", namespace = "http://tempuri.org/", type = JAXBElement.class,
                   required = false)
    protected JAXBElement<ArrayOfInteractivite> loadInteractivityResult;

    /**
     * Gets the value of the loadInteractivityResult property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfInteractivite }{@code >}
     *
     */
    public JAXBElement<ArrayOfInteractivite> getLoadInteractivityResult() {
        return loadInteractivityResult;
    }

    /**
     * Sets the value of the loadInteractivityResult property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfInteractivite }{@code >}
     *
     */
    public void setLoadInteractivityResult(JAXBElement<ArrayOfInteractivite> value) {
        this.loadInteractivityResult = value;
    }

}
