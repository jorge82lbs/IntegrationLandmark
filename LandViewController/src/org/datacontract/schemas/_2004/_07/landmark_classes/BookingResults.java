
package org.datacontract.schemas._2004._07.landmark_classes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BookingResults complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BookingResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MasterData" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}ArrayOfBookingMasterData" minOccurs="0"/>
 *         &lt;element name="Messages" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}ArrayOfBookingMessage" minOccurs="0"/>
 *         &lt;element name="Overrides" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}ArrayOfBookingOverride" minOccurs="0"/>
 *         &lt;element name="Results" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}ArrayOfBookingResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookingResults", propOrder = { "masterData", "messages", "overrides", "results" })
public class BookingResults {

    @XmlElementRef(name = "MasterData", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfBookingMasterData> masterData;
    @XmlElementRef(name = "Messages", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfBookingMessage> messages;
    @XmlElementRef(name = "Overrides", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfBookingOverride> overrides;
    @XmlElementRef(name = "Results", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfBookingResult> results;

    /**
     * Gets the value of the masterData property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBookingMasterData }{@code >}
     *
     */
    public JAXBElement<ArrayOfBookingMasterData> getMasterData() {
        return masterData;
    }

    /**
     * Sets the value of the masterData property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBookingMasterData }{@code >}
     *
     */
    public void setMasterData(JAXBElement<ArrayOfBookingMasterData> value) {
        this.masterData = value;
    }

    /**
     * Gets the value of the messages property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBookingMessage }{@code >}
     *
     */
    public JAXBElement<ArrayOfBookingMessage> getMessages() {
        return messages;
    }

    /**
     * Sets the value of the messages property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBookingMessage }{@code >}
     *
     */
    public void setMessages(JAXBElement<ArrayOfBookingMessage> value) {
        this.messages = value;
    }

    /**
     * Gets the value of the overrides property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBookingOverride }{@code >}
     *
     */
    public JAXBElement<ArrayOfBookingOverride> getOverrides() {
        return overrides;
    }

    /**
     * Sets the value of the overrides property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBookingOverride }{@code >}
     *
     */
    public void setOverrides(JAXBElement<ArrayOfBookingOverride> value) {
        this.overrides = value;
    }

    /**
     * Gets the value of the results property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBookingResult }{@code >}
     *
     */
    public JAXBElement<ArrayOfBookingResult> getResults() {
        return results;
    }

    /**
     * Sets the value of the results property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBookingResult }{@code >}
     *
     */
    public void setResults(JAXBElement<ArrayOfBookingResult> value) {
        this.results = value;
    }

}
