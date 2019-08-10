
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MultiPart complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="MultiPart">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BreakPositionRequirement" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes}Bkpo" minOccurs="0"/>
 *         &lt;element name="InteractivityTypeNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="IsEnteredPrice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="SequenceNumber" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="SpotLength" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="SpotNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiPart", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
         propOrder = {
         "breakPositionRequirement", "interactivityTypeNumber", "isEnteredPrice", "price", "sequenceNumber",
         "spotLength", "spotNumber", "spotTypeCode"
    })
public class MultiPart {

    @XmlElement(name = "BreakPositionRequirement")
    @XmlSchemaType(name = "string")
    protected Bkpo breakPositionRequirement;
    @XmlElement(name = "InteractivityTypeNumber")
    protected Integer interactivityTypeNumber;
    @XmlElement(name = "IsEnteredPrice")
    protected Boolean isEnteredPrice;
    @XmlElementRef(name = "Price", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<Double> price;
    @XmlElement(name = "SequenceNumber")
    protected Short sequenceNumber;
    @XmlElement(name = "SpotLength")
    protected Short spotLength;
    @XmlElement(name = "SpotNumber")
    protected Integer spotNumber;
    @XmlElementRef(name = "SpotTypeCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> spotTypeCode;

    /**
     * Gets the value of the breakPositionRequirement property.
     *
     * @return
     *     possible object is
     *     {@link Bkpo }
     *
     */
    public Bkpo getBreakPositionRequirement() {
        return breakPositionRequirement;
    }

    /**
     * Sets the value of the breakPositionRequirement property.
     *
     * @param value
     *     allowed object is
     *     {@link Bkpo }
     *
     */
    public void setBreakPositionRequirement(Bkpo value) {
        this.breakPositionRequirement = value;
    }

    /**
     * Gets the value of the interactivityTypeNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getInteractivityTypeNumber() {
        return interactivityTypeNumber;
    }

    /**
     * Sets the value of the interactivityTypeNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setInteractivityTypeNumber(Integer value) {
        this.interactivityTypeNumber = value;
    }

    /**
     * Gets the value of the isEnteredPrice property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIsEnteredPrice() {
        return isEnteredPrice;
    }

    /**
     * Sets the value of the isEnteredPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIsEnteredPrice(Boolean value) {
        this.isEnteredPrice = value;
    }

    /**
     * Gets the value of the price property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *
     */
    public JAXBElement<Double> getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *
     */
    public void setPrice(JAXBElement<Double> value) {
        this.price = value;
    }

    /**
     * Gets the value of the sequenceNumber property.
     *
     * @return
     *     possible object is
     *     {@link Short }
     *
     */
    public Short getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the value of the sequenceNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Short }
     *
     */
    public void setSequenceNumber(Short value) {
        this.sequenceNumber = value;
    }

    /**
     * Gets the value of the spotLength property.
     *
     * @return
     *     possible object is
     *     {@link Short }
     *
     */
    public Short getSpotLength() {
        return spotLength;
    }

    /**
     * Sets the value of the spotLength property.
     *
     * @param value
     *     allowed object is
     *     {@link Short }
     *
     */
    public void setSpotLength(Short value) {
        this.spotLength = value;
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
     * Gets the value of the spotTypeCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getSpotTypeCode() {
        return spotTypeCode;
    }

    /**
     * Sets the value of the spotTypeCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setSpotTypeCode(JAXBElement<String> value) {
        this.spotTypeCode = value;
    }

}
