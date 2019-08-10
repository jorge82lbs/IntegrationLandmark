
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SpotSwapParams complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SpotSwapParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrganisationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Overrides" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams}ArrayOfOverrides" minOccurs="0"/>
 *         &lt;element name="PositionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProcessType" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams}BookingProcessType" minOccurs="0"/>
 *         &lt;element name="RowId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SwapSpotNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpotSwapParams", propOrder = {
         "organisationCode", "overrides", "positionCode", "processType", "rowId", "spotNumber", "swapSpotNumber"
    })
public class SpotSwapParams {

    @XmlElementRef(name = "OrganisationCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> organisationCode;
    @XmlElementRef(name = "Overrides", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfOverrides> overrides;
    @XmlElementRef(name = "PositionCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> positionCode;
    @XmlElement(name = "ProcessType")
    @XmlSchemaType(name = "string")
    protected BookingProcessType processType;
    @XmlElement(name = "RowId")
    protected Integer rowId;
    @XmlElement(name = "SpotNumber")
    protected Integer spotNumber;
    @XmlElement(name = "SwapSpotNumber")
    protected Integer swapSpotNumber;

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
     * Gets the value of the overrides property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOverrides }{@code >}
     *
     */
    public JAXBElement<ArrayOfOverrides> getOverrides() {
        return overrides;
    }

    /**
     * Sets the value of the overrides property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOverrides }{@code >}
     *
     */
    public void setOverrides(JAXBElement<ArrayOfOverrides> value) {
        this.overrides = value;
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

    /**
     * Gets the value of the processType property.
     *
     * @return
     *     possible object is
     *     {@link BookingProcessType }
     *
     */
    public BookingProcessType getProcessType() {
        return processType;
    }

    /**
     * Sets the value of the processType property.
     *
     * @param value
     *     allowed object is
     *     {@link BookingProcessType }
     *
     */
    public void setProcessType(BookingProcessType value) {
        this.processType = value;
    }

    /**
     * Gets the value of the rowId property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getRowId() {
        return rowId;
    }

    /**
     * Sets the value of the rowId property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setRowId(Integer value) {
        this.rowId = value;
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
     * Gets the value of the swapSpotNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSwapSpotNumber() {
        return swapSpotNumber;
    }

    /**
     * Sets the value of the swapSpotNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSwapSpotNumber(Integer value) {
        this.swapSpotNumber = value;
    }

}
