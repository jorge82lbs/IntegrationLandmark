
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SpotMoveParams complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SpotMoveParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BreakNominalTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BreakNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BreakSalesAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BreakScheduledDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="MultiParts" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams}ArrayOfMultiPart" minOccurs="0"/>
 *         &lt;element name="OrganisationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Overrides" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams}ArrayOfOverrides" minOccurs="0"/>
 *         &lt;element name="PositionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProcessType" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams}BookingProcessType" minOccurs="0"/>
 *         &lt;element name="RowId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotDuration" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotSalesAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SpotStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpotMoveParams", propOrder = {
         "breakNominalTime", "breakNumber", "breakSalesAreaCode", "breakScheduledDate", "multiParts",
         "organisationCode", "overrides", "positionCode", "processType", "rowId", "spotDuration", "spotNumber",
         "spotSalesAreaCode", "spotStatus"
    })
public class SpotMoveParams {

    @XmlElementRef(name = "BreakNominalTime",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> breakNominalTime;
    @XmlElementRef(name = "BreakNumber",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> breakNumber;
    @XmlElementRef(name = "BreakSalesAreaCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> breakSalesAreaCode;
    @XmlElementRef(name = "BreakScheduledDate",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> breakScheduledDate;
    @XmlElementRef(name = "MultiParts", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfMultiPart> multiParts;
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
    @XmlElementRef(name = "SpotDuration",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> spotDuration;
    @XmlElement(name = "SpotNumber")
    protected Integer spotNumber;
    @XmlElementRef(name = "SpotSalesAreaCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> spotSalesAreaCode;
    @XmlElementRef(name = "SpotStatus", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> spotStatus;

    /**
     * Gets the value of the breakNominalTime property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    public JAXBElement<Integer> getBreakNominalTime() {
        return breakNominalTime;
    }

    /**
     * Sets the value of the breakNominalTime property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    public void setBreakNominalTime(JAXBElement<Integer> value) {
        this.breakNominalTime = value;
    }

    /**
     * Gets the value of the breakNumber property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    public JAXBElement<Integer> getBreakNumber() {
        return breakNumber;
    }

    /**
     * Sets the value of the breakNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    public void setBreakNumber(JAXBElement<Integer> value) {
        this.breakNumber = value;
    }

    /**
     * Gets the value of the breakSalesAreaCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getBreakSalesAreaCode() {
        return breakSalesAreaCode;
    }

    /**
     * Sets the value of the breakSalesAreaCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setBreakSalesAreaCode(JAXBElement<String> value) {
        this.breakSalesAreaCode = value;
    }

    /**
     * Gets the value of the breakScheduledDate property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *
     */
    public JAXBElement<XMLGregorianCalendar> getBreakScheduledDate() {
        return breakScheduledDate;
    }

    /**
     * Sets the value of the breakScheduledDate property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *
     */
    public void setBreakScheduledDate(JAXBElement<XMLGregorianCalendar> value) {
        this.breakScheduledDate = value;
    }

    /**
     * Gets the value of the multiParts property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMultiPart }{@code >}
     *
     */
    public JAXBElement<ArrayOfMultiPart> getMultiParts() {
        return multiParts;
    }

    /**
     * Sets the value of the multiParts property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfMultiPart }{@code >}
     *
     */
    public void setMultiParts(JAXBElement<ArrayOfMultiPart> value) {
        this.multiParts = value;
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
     * Gets the value of the spotDuration property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    public JAXBElement<Integer> getSpotDuration() {
        return spotDuration;
    }

    /**
     * Sets the value of the spotDuration property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *
     */
    public void setSpotDuration(JAXBElement<Integer> value) {
        this.spotDuration = value;
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
     * Gets the value of the spotSalesAreaCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getSpotSalesAreaCode() {
        return spotSalesAreaCode;
    }

    /**
     * Sets the value of the spotSalesAreaCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setSpotSalesAreaCode(JAXBElement<String> value) {
        this.spotSalesAreaCode = value;
    }

    /**
     * Gets the value of the spotStatus property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getSpotStatus() {
        return spotStatus;
    }

    /**
     * Sets the value of the spotStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setSpotStatus(JAXBElement<String> value) {
        this.spotStatus = value;
    }

}
