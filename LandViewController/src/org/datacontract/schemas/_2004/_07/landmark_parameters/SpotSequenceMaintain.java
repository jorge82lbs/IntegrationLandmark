
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SpotSequenceMaintain complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SpotSequenceMaintain">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BreakSpotCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotSequence" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots}ArrayOfSpotSequence" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpotSequenceMaintain", propOrder = { "breakSpotCount", "spotSequence" })
public class SpotSequenceMaintain {

    @XmlElement(name = "BreakSpotCount")
    protected Integer breakSpotCount;
    @XmlElementRef(name = "SpotSequence",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfSpotSequence> spotSequence;

    /**
     * Gets the value of the breakSpotCount property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBreakSpotCount() {
        return breakSpotCount;
    }

    /**
     * Sets the value of the breakSpotCount property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBreakSpotCount(Integer value) {
        this.breakSpotCount = value;
    }

    /**
     * Gets the value of the spotSequence property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpotSequence }{@code >}
     *
     */
    public JAXBElement<ArrayOfSpotSequence> getSpotSequence() {
        return spotSequence;
    }

    /**
     * Sets the value of the spotSequence property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpotSequence }{@code >}
     *
     */
    public void setSpotSequence(JAXBElement<ArrayOfSpotSequence> value) {
        this.spotSequence = value;
    }

}
