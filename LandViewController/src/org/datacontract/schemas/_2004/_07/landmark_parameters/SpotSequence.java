
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SpotSequence complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SpotSequence">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BreakPositionReqm" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="EstimatedTxmnTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SequenceInBreak" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="TransmissionRegion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpotSequence", propOrder = {
         "breakPositionReqm", "estimatedTxmnTime", "sequenceInBreak", "spotNumber", "transmissionRegion" })
public class SpotSequence {

    @XmlElement(name = "BreakPositionReqm")
    protected Integer breakPositionReqm;
    @XmlElement(name = "EstimatedTxmnTime")
    protected Integer estimatedTxmnTime;
    @XmlElement(name = "SequenceInBreak")
    protected Integer sequenceInBreak;
    @XmlElement(name = "SpotNumber")
    protected Integer spotNumber;
    @XmlElement(name = "TransmissionRegion")
    protected Integer transmissionRegion;

    /**
     * Gets the value of the breakPositionReqm property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBreakPositionReqm() {
        return breakPositionReqm;
    }

    /**
     * Sets the value of the breakPositionReqm property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBreakPositionReqm(Integer value) {
        this.breakPositionReqm = value;
    }

    /**
     * Gets the value of the estimatedTxmnTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getEstimatedTxmnTime() {
        return estimatedTxmnTime;
    }

    /**
     * Sets the value of the estimatedTxmnTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setEstimatedTxmnTime(Integer value) {
        this.estimatedTxmnTime = value;
    }

    /**
     * Gets the value of the sequenceInBreak property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSequenceInBreak() {
        return sequenceInBreak;
    }

    /**
     * Sets the value of the sequenceInBreak property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSequenceInBreak(Integer value) {
        this.sequenceInBreak = value;
    }

    /**
     * Gets the value of the spotNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSpotNumber() {
        return spotNumber;
    }

    /**
     * Sets the value of the spotNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSpotNumber(Integer value) {
        this.spotNumber = value;
    }

    /**
     * Gets the value of the transmissionRegion property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getTransmissionRegion() {
        return transmissionRegion;
    }

    /**
     * Sets the value of the transmissionRegion property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setTransmissionRegion(Integer value) {
        this.transmissionRegion = value;
    }

}
