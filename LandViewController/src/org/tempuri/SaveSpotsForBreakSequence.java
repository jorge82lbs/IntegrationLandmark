
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.datacontract.schemas._2004._07.landmark_parameters.SpotSequenceMaintain;


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
 *         &lt;element name="spotSequenceMaintain" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots}SpotSequenceMaintain" minOccurs="0"/>
 *         &lt;element name="organisationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "spotSequenceMaintain", "organisationCode", "positionCode" })
@XmlRootElement(name = "SaveSpotsForBreakSequence")
public class SaveSpotsForBreakSequence {

    @XmlElementRef(name = "spotSequenceMaintain", namespace = "http://tempuri.org/", type = JAXBElement.class,
                   required = false)
    protected JAXBElement<SpotSequenceMaintain> spotSequenceMaintain;
    @XmlElementRef(name = "organisationCode", namespace = "http://tempuri.org/", type = JAXBElement.class,
                   required = false)
    protected JAXBElement<String> organisationCode;
    @XmlElementRef(name = "positionCode", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> positionCode;

    /**
     * Gets the value of the spotSequenceMaintain property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SpotSequenceMaintain }{@code >}
     *
     */
    public JAXBElement<SpotSequenceMaintain> getSpotSequenceMaintain() {
        return spotSequenceMaintain;
    }

    /**
     * Sets the value of the spotSequenceMaintain property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SpotSequenceMaintain }{@code >}
     *
     */
    public void setSpotSequenceMaintain(JAXBElement<SpotSequenceMaintain> value) {
        this.spotSequenceMaintain = value;
    }

    /**
     * Gets the value of the organisationCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getOrganisationCode() {
        return organisationCode;
    }

    /**
     * Sets the value of the organisationCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setOrganisationCode(JAXBElement<String> value) {
        this.organisationCode = value;
    }

    /**
     * Gets the value of the positionCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getPositionCode() {
        return positionCode;
    }

    /**
     * Sets the value of the positionCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setPositionCode(JAXBElement<String> value) {
        this.positionCode = value;
    }

}
