
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BatchBookingParams complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BatchBookingParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrgrCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PosnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SpotAmendParams" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots}ArrayOfSpotAmendParams" minOccurs="0"/>
 *         &lt;element name="SpotBookParams" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots}ArrayOfSpotBookParams" minOccurs="0"/>
 *         &lt;element name="SpotCancelParams" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots}ArrayOfSpotCancelParams" minOccurs="0"/>
 *         &lt;element name="SpotMoveParams" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots}ArrayOfSpotMoveParams" minOccurs="0"/>
 *         &lt;element name="SpotSwapParams" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots}ArrayOfSpotSwapParams" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchBookingParams", propOrder = {
         "orgrCode", "posnCode", "spotAmendParams", "spotBookParams", "spotCancelParams", "spotMoveParams",
         "spotSwapParams"
    })
public class BatchBookingParams {

    @XmlElementRef(name = "OrgrCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> orgrCode;
    @XmlElementRef(name = "PosnCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> posnCode;
    @XmlElementRef(name = "SpotAmendParams",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfSpotAmendParams> spotAmendParams;
    @XmlElementRef(name = "SpotBookParams",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfSpotBookParams> spotBookParams;
    @XmlElementRef(name = "SpotCancelParams",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfSpotCancelParams> spotCancelParams;
    @XmlElementRef(name = "SpotMoveParams",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfSpotMoveParams> spotMoveParams;
    @XmlElementRef(name = "SpotSwapParams",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfSpotSwapParams> spotSwapParams;

    /**
     * Gets the value of the orgrCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getOrgrCode() {
        return orgrCode;
    }

    /**
     * Sets the value of the orgrCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setOrgrCode(JAXBElement<String> value) {
        this.orgrCode = value;
    }

    /**
     * Gets the value of the posnCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getPosnCode() {
        return posnCode;
    }

    /**
     * Sets the value of the posnCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setPosnCode(JAXBElement<String> value) {
        this.posnCode = value;
    }

    /**
     * Gets the value of the spotAmendParams property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpotAmendParams }{@code >}
     *
     */
    public JAXBElement<ArrayOfSpotAmendParams> getSpotAmendParams() {
        return spotAmendParams;
    }

    /**
     * Sets the value of the spotAmendParams property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpotAmendParams }{@code >}
     *
     */
    public void setSpotAmendParams(JAXBElement<ArrayOfSpotAmendParams> value) {
        this.spotAmendParams = value;
    }

    /**
     * Gets the value of the spotBookParams property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpotBookParams }{@code >}
     *
     */
    public JAXBElement<ArrayOfSpotBookParams> getSpotBookParams() {
        return spotBookParams;
    }

    /**
     * Sets the value of the spotBookParams property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpotBookParams }{@code >}
     *
     */
    public void setSpotBookParams(JAXBElement<ArrayOfSpotBookParams> value) {
        this.spotBookParams = value;
    }

    /**
     * Gets the value of the spotCancelParams property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpotCancelParams }{@code >}
     *
     */
    public JAXBElement<ArrayOfSpotCancelParams> getSpotCancelParams() {
        return spotCancelParams;
    }

    /**
     * Sets the value of the spotCancelParams property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpotCancelParams }{@code >}
     *
     */
    public void setSpotCancelParams(JAXBElement<ArrayOfSpotCancelParams> value) {
        this.spotCancelParams = value;
    }

    /**
     * Gets the value of the spotMoveParams property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpotMoveParams }{@code >}
     *
     */
    public JAXBElement<ArrayOfSpotMoveParams> getSpotMoveParams() {
        return spotMoveParams;
    }

    /**
     * Sets the value of the spotMoveParams property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpotMoveParams }{@code >}
     *
     */
    public void setSpotMoveParams(JAXBElement<ArrayOfSpotMoveParams> value) {
        this.spotMoveParams = value;
    }

    /**
     * Gets the value of the spotSwapParams property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpotSwapParams }{@code >}
     *
     */
    public JAXBElement<ArrayOfSpotSwapParams> getSpotSwapParams() {
        return spotSwapParams;
    }

    /**
     * Sets the value of the spotSwapParams property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpotSwapParams }{@code >}
     *
     */
    public void setSpotSwapParams(JAXBElement<ArrayOfSpotSwapParams> value) {
        this.spotSwapParams = value;
    }

}
