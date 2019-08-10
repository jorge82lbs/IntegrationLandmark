
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
 * <p>Java class for SpotBookParams complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SpotBookParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AddedValue" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="BreakCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BreakNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BreakNominalTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BreakPositionRequirement" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes}Bkpo" minOccurs="0"/>
 *         &lt;element name="BreakSalesAreaNumber" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="BreakScheduledDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="BreakTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BusinessAreaNumber" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
 *         &lt;element name="BusinessTypeNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CampaignNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CheckClash" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CheckRepetition" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CheckRestrictions" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ClientPicked" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CopySpotNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DaypartNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DealType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeleteChargeTakeUp" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DiscountDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Discounts" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams}ArrayOfDiscount" minOccurs="0"/>
 *         &lt;element name="Enhanced" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="FunctionalityType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="InteractivityTypeNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="IsEnteredPrice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsMakeGoodRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsMakePrimary" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsProgrammeLocked" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="LineNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="LinkSpots" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="LockSessionID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MakeGood" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MakeGoodTakeUp" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="MasterSpotNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MobilityRuleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MultiParts" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams}ArrayOfMultiPart" minOccurs="0"/>
 *         &lt;element name="NonPreemptible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="OptionExpiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="OrganisationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Overrides" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams}ArrayOfOverrides" minOccurs="0"/>
 *         &lt;element name="PositionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PostDiscountPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="PreemptionStatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PreemptorStatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ProcessType" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams}BookingProcessType" minOccurs="0"/>
 *         &lt;element name="PromoSpotLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegionalExclusions" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams}ArrayOfRegionalExclusion" minOccurs="0"/>
 *         &lt;element name="ResetPrice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="RowId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ScheduleNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SequenceNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotBitFlags" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotLength" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="SpotSalesAreaNumber" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="SpotStatus" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes}SpotStatus" minOccurs="0"/>
 *         &lt;element name="SpotTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tolerance" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="UseReserve" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpotBookParams", propOrder = {
         "addedValue", "breakCategoryCode", "breakNo", "breakNominalTime", "breakPositionRequirement",
         "breakSalesAreaNumber", "breakScheduledDate", "breakTypeCode", "businessAreaNumber", "businessTypeNumber",
         "campaignNumber", "checkClash", "checkRepetition", "checkRestrictions", "clientPicked", "copySpotNo",
         "currencyCode", "daypartNo", "dealType", "deleteChargeTakeUp", "discountDate", "discounts", "enhanced",
         "functionalityType", "interactivityTypeNumber", "isEnteredPrice", "isMakeGoodRequired", "isMakePrimary",
         "isProgrammeLocked", "lineNumber", "linkSpots", "lockSessionID", "makeGood", "makeGoodTakeUp", "masterSpotNo",
         "mobilityRuleCode", "multiParts", "nonPreemptible", "optionExpiryDate", "organisationCode", "overrides",
         "positionCode", "postDiscountPrice", "preemptionStatus", "preemptorStatus", "processType", "promoSpotLink",
         "reasonCode", "regionalExclusions", "resetPrice", "rowId", "scheduleNumber", "sequenceNo", "spotBitFlags",
         "spotLength", "spotSalesAreaNumber", "spotStatus", "spotTypeCode", "tolerance", "useReserve"
    })
public class SpotBookParams {

    @XmlElement(name = "AddedValue")
    protected Boolean addedValue;
    @XmlElementRef(name = "BreakCategoryCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> breakCategoryCode;
    @XmlElement(name = "BreakNo")
    protected Integer breakNo;
    @XmlElement(name = "BreakNominalTime")
    protected Integer breakNominalTime;
    @XmlElement(name = "BreakPositionRequirement")
    @XmlSchemaType(name = "string")
    protected Bkpo breakPositionRequirement;
    @XmlElement(name = "BreakSalesAreaNumber")
    protected Short breakSalesAreaNumber;
    @XmlElement(name = "BreakScheduledDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar breakScheduledDate;
    @XmlElementRef(name = "BreakTypeCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> breakTypeCode;
    @XmlElement(name = "BusinessAreaNumber")
    protected Byte businessAreaNumber;
    @XmlElement(name = "BusinessTypeNumber")
    protected Integer businessTypeNumber;
    @XmlElement(name = "CampaignNumber")
    protected Integer campaignNumber;
    @XmlElement(name = "CheckClash")
    protected Boolean checkClash;
    @XmlElement(name = "CheckRepetition")
    protected Boolean checkRepetition;
    @XmlElement(name = "CheckRestrictions")
    protected Boolean checkRestrictions;
    @XmlElement(name = "ClientPicked")
    protected Boolean clientPicked;
    @XmlElement(name = "CopySpotNo")
    protected Integer copySpotNo;
    @XmlElementRef(name = "CurrencyCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> currencyCode;
    @XmlElement(name = "DaypartNo")
    protected Integer daypartNo;
    @XmlElementRef(name = "DealType", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> dealType;
    @XmlElement(name = "DeleteChargeTakeUp")
    protected Boolean deleteChargeTakeUp;
    @XmlElement(name = "DiscountDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar discountDate;
    @XmlElementRef(name = "Discounts", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfDiscount> discounts;
    @XmlElement(name = "Enhanced")
    protected Boolean enhanced;
    @XmlElement(name = "FunctionalityType")
    protected Integer functionalityType;
    @XmlElement(name = "InteractivityTypeNumber")
    protected Integer interactivityTypeNumber;
    @XmlElement(name = "IsEnteredPrice")
    protected Boolean isEnteredPrice;
    @XmlElement(name = "IsMakeGoodRequired")
    protected Boolean isMakeGoodRequired;
    @XmlElement(name = "IsMakePrimary")
    protected Boolean isMakePrimary;
    @XmlElement(name = "IsProgrammeLocked")
    protected Boolean isProgrammeLocked;
    @XmlElement(name = "LineNumber")
    protected Integer lineNumber;
    @XmlElement(name = "LinkSpots")
    protected Boolean linkSpots;
    @XmlElement(name = "LockSessionID")
    protected Integer lockSessionID;
    @XmlElement(name = "MakeGood")
    protected Integer makeGood;
    @XmlElement(name = "MakeGoodTakeUp")
    protected Boolean makeGoodTakeUp;
    @XmlElement(name = "MasterSpotNo")
    protected Integer masterSpotNo;
    @XmlElementRef(name = "MobilityRuleCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> mobilityRuleCode;
    @XmlElementRef(name = "MultiParts", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfMultiPart> multiParts;
    @XmlElement(name = "NonPreemptible")
    protected Boolean nonPreemptible;
    @XmlElement(name = "OptionExpiryDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar optionExpiryDate;
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
    @XmlElement(name = "PostDiscountPrice")
    protected Double postDiscountPrice;
    @XmlElement(name = "PreemptionStatus")
    protected Integer preemptionStatus;
    @XmlElement(name = "PreemptorStatus")
    protected Integer preemptorStatus;
    @XmlElement(name = "ProcessType")
    @XmlSchemaType(name = "string")
    protected BookingProcessType processType;
    @XmlElementRef(name = "PromoSpotLink",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> promoSpotLink;
    @XmlElementRef(name = "ReasonCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> reasonCode;
    @XmlElementRef(name = "RegionalExclusions",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfRegionalExclusion> regionalExclusions;
    @XmlElement(name = "ResetPrice")
    protected Boolean resetPrice;
    @XmlElement(name = "RowId")
    protected Integer rowId;
    @XmlElement(name = "ScheduleNumber")
    protected Integer scheduleNumber;
    @XmlElement(name = "SequenceNo")
    protected Integer sequenceNo;
    @XmlElement(name = "SpotBitFlags")
    protected Integer spotBitFlags;
    @XmlElement(name = "SpotLength")
    protected Short spotLength;
    @XmlElement(name = "SpotSalesAreaNumber")
    protected Short spotSalesAreaNumber;
    @XmlElement(name = "SpotStatus")
    @XmlSchemaType(name = "string")
    protected SpotStatus spotStatus;
    @XmlElementRef(name = "SpotTypeCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> spotTypeCode;
    @XmlElement(name = "Tolerance")
    protected Integer tolerance;
    @XmlElement(name = "UseReserve")
    protected Boolean useReserve;

    /**
     * Gets the value of the addedValue property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isAddedValue() {
        return addedValue;
    }

    /**
     * Sets the value of the addedValue property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setAddedValue(Boolean value) {
        this.addedValue = value;
    }

    /**
     * Gets the value of the breakCategoryCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getBreakCategoryCode() {
        return breakCategoryCode;
    }

    /**
     * Sets the value of the breakCategoryCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setBreakCategoryCode(JAXBElement<String> value) {
        this.breakCategoryCode = value;
    }

    /**
     * Gets the value of the breakNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBreakNo() {
        return breakNo;
    }

    /**
     * Sets the value of the breakNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBreakNo(Integer value) {
        this.breakNo = value;
    }

    /**
     * Gets the value of the breakNominalTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBreakNominalTime() {
        return breakNominalTime;
    }

    /**
     * Sets the value of the breakNominalTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBreakNominalTime(Integer value) {
        this.breakNominalTime = value;
    }

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
     * Gets the value of the breakSalesAreaNumber property.
     *
     * @return
     *     possible object is
     *     {@link Short }
     *
     */
    public Short getBreakSalesAreaNumber() {
        return breakSalesAreaNumber;
    }

    /**
     * Sets the value of the breakSalesAreaNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Short }
     *
     */
    public void setBreakSalesAreaNumber(Short value) {
        this.breakSalesAreaNumber = value;
    }

    /**
     * Gets the value of the breakScheduledDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getBreakScheduledDate() {
        return breakScheduledDate;
    }

    /**
     * Sets the value of the breakScheduledDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setBreakScheduledDate(XMLGregorianCalendar value) {
        this.breakScheduledDate = value;
    }

    /**
     * Gets the value of the breakTypeCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getBreakTypeCode() {
        return breakTypeCode;
    }

    /**
     * Sets the value of the breakTypeCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setBreakTypeCode(JAXBElement<String> value) {
        this.breakTypeCode = value;
    }

    /**
     * Gets the value of the businessAreaNumber property.
     *
     * @return
     *     possible object is
     *     {@link Byte }
     *
     */
    public Byte getBusinessAreaNumber() {
        return businessAreaNumber;
    }

    /**
     * Sets the value of the businessAreaNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Byte }
     *
     */
    public void setBusinessAreaNumber(Byte value) {
        this.businessAreaNumber = value;
    }

    /**
     * Gets the value of the businessTypeNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBusinessTypeNumber() {
        return businessTypeNumber;
    }

    /**
     * Sets the value of the businessTypeNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBusinessTypeNumber(Integer value) {
        this.businessTypeNumber = value;
    }

    /**
     * Gets the value of the campaignNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getCampaignNumber() {
        return campaignNumber;
    }

    /**
     * Sets the value of the campaignNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setCampaignNumber(Integer value) {
        this.campaignNumber = value;
    }

    /**
     * Gets the value of the checkClash property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isCheckClash() {
        return checkClash;
    }

    /**
     * Sets the value of the checkClash property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setCheckClash(Boolean value) {
        this.checkClash = value;
    }

    /**
     * Gets the value of the checkRepetition property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isCheckRepetition() {
        return checkRepetition;
    }

    /**
     * Sets the value of the checkRepetition property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setCheckRepetition(Boolean value) {
        this.checkRepetition = value;
    }

    /**
     * Gets the value of the checkRestrictions property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isCheckRestrictions() {
        return checkRestrictions;
    }

    /**
     * Sets the value of the checkRestrictions property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setCheckRestrictions(Boolean value) {
        this.checkRestrictions = value;
    }

    /**
     * Gets the value of the clientPicked property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isClientPicked() {
        return clientPicked;
    }

    /**
     * Sets the value of the clientPicked property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setClientPicked(Boolean value) {
        this.clientPicked = value;
    }

    /**
     * Gets the value of the copySpotNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getCopySpotNo() {
        return copySpotNo;
    }

    /**
     * Sets the value of the copySpotNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setCopySpotNo(Integer value) {
        this.copySpotNo = value;
    }

    /**
     * Gets the value of the currencyCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCurrencyCode(JAXBElement<String> value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the daypartNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getDaypartNo() {
        return daypartNo;
    }

    /**
     * Sets the value of the daypartNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setDaypartNo(Integer value) {
        this.daypartNo = value;
    }

    /**
     * Gets the value of the dealType property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getDealType() {
        return dealType;
    }

    /**
     * Sets the value of the dealType property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setDealType(JAXBElement<String> value) {
        this.dealType = value;
    }

    /**
     * Gets the value of the deleteChargeTakeUp property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isDeleteChargeTakeUp() {
        return deleteChargeTakeUp;
    }

    /**
     * Sets the value of the deleteChargeTakeUp property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setDeleteChargeTakeUp(Boolean value) {
        this.deleteChargeTakeUp = value;
    }

    /**
     * Gets the value of the discountDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDiscountDate() {
        return discountDate;
    }

    /**
     * Sets the value of the discountDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setDiscountDate(XMLGregorianCalendar value) {
        this.discountDate = value;
    }

    /**
     * Gets the value of the discounts property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDiscount }{@code >}
     *
     */
    public JAXBElement<ArrayOfDiscount> getDiscounts() {
        return discounts;
    }

    /**
     * Sets the value of the discounts property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDiscount }{@code >}
     *
     */
    public void setDiscounts(JAXBElement<ArrayOfDiscount> value) {
        this.discounts = value;
    }

    /**
     * Gets the value of the enhanced property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isEnhanced() {
        return enhanced;
    }

    /**
     * Sets the value of the enhanced property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setEnhanced(Boolean value) {
        this.enhanced = value;
    }

    /**
     * Gets the value of the functionalityType property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getFunctionalityType() {
        return functionalityType;
    }

    /**
     * Sets the value of the functionalityType property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setFunctionalityType(Integer value) {
        this.functionalityType = value;
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
     * Gets the value of the isMakeGoodRequired property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIsMakeGoodRequired() {
        return isMakeGoodRequired;
    }

    /**
     * Sets the value of the isMakeGoodRequired property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIsMakeGoodRequired(Boolean value) {
        this.isMakeGoodRequired = value;
    }

    /**
     * Gets the value of the isMakePrimary property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIsMakePrimary() {
        return isMakePrimary;
    }

    /**
     * Sets the value of the isMakePrimary property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIsMakePrimary(Boolean value) {
        this.isMakePrimary = value;
    }

    /**
     * Gets the value of the isProgrammeLocked property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIsProgrammeLocked() {
        return isProgrammeLocked;
    }

    /**
     * Sets the value of the isProgrammeLocked property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIsProgrammeLocked(Boolean value) {
        this.isProgrammeLocked = value;
    }

    /**
     * Gets the value of the lineNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getLineNumber() {
        return lineNumber;
    }

    /**
     * Sets the value of the lineNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setLineNumber(Integer value) {
        this.lineNumber = value;
    }

    /**
     * Gets the value of the linkSpots property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isLinkSpots() {
        return linkSpots;
    }

    /**
     * Sets the value of the linkSpots property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setLinkSpots(Boolean value) {
        this.linkSpots = value;
    }

    /**
     * Gets the value of the lockSessionID property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getLockSessionID() {
        return lockSessionID;
    }

    /**
     * Sets the value of the lockSessionID property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setLockSessionID(Integer value) {
        this.lockSessionID = value;
    }

    /**
     * Gets the value of the makeGood property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getMakeGood() {
        return makeGood;
    }

    /**
     * Sets the value of the makeGood property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setMakeGood(Integer value) {
        this.makeGood = value;
    }

    /**
     * Gets the value of the makeGoodTakeUp property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isMakeGoodTakeUp() {
        return makeGoodTakeUp;
    }

    /**
     * Sets the value of the makeGoodTakeUp property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setMakeGoodTakeUp(Boolean value) {
        this.makeGoodTakeUp = value;
    }

    /**
     * Gets the value of the masterSpotNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getMasterSpotNo() {
        return masterSpotNo;
    }

    /**
     * Sets the value of the masterSpotNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setMasterSpotNo(Integer value) {
        this.masterSpotNo = value;
    }

    /**
     * Gets the value of the mobilityRuleCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getMobilityRuleCode() {
        return mobilityRuleCode;
    }

    /**
     * Sets the value of the mobilityRuleCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setMobilityRuleCode(JAXBElement<String> value) {
        this.mobilityRuleCode = value;
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
     * Gets the value of the nonPreemptible property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isNonPreemptible() {
        return nonPreemptible;
    }

    /**
     * Sets the value of the nonPreemptible property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setNonPreemptible(Boolean value) {
        this.nonPreemptible = value;
    }

    /**
     * Gets the value of the optionExpiryDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getOptionExpiryDate() {
        return optionExpiryDate;
    }

    /**
     * Sets the value of the optionExpiryDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setOptionExpiryDate(XMLGregorianCalendar value) {
        this.optionExpiryDate = value;
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
     * Gets the value of the postDiscountPrice property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getPostDiscountPrice() {
        return postDiscountPrice;
    }

    /**
     * Sets the value of the postDiscountPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setPostDiscountPrice(Double value) {
        this.postDiscountPrice = value;
    }

    /**
     * Gets the value of the preemptionStatus property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPreemptionStatus() {
        return preemptionStatus;
    }

    /**
     * Sets the value of the preemptionStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPreemptionStatus(Integer value) {
        this.preemptionStatus = value;
    }

    /**
     * Gets the value of the preemptorStatus property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPreemptorStatus() {
        return preemptorStatus;
    }

    /**
     * Sets the value of the preemptorStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPreemptorStatus(Integer value) {
        this.preemptorStatus = value;
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
     * Gets the value of the promoSpotLink property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getPromoSpotLink() {
        return promoSpotLink;
    }

    /**
     * Sets the value of the promoSpotLink property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setPromoSpotLink(JAXBElement<String> value) {
        this.promoSpotLink = value;
    }

    /**
     * Gets the value of the reasonCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets the value of the reasonCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setReasonCode(JAXBElement<String> value) {
        this.reasonCode = value;
    }

    /**
     * Gets the value of the regionalExclusions property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRegionalExclusion }{@code >}
     *
     */
    public JAXBElement<ArrayOfRegionalExclusion> getRegionalExclusions() {
        return regionalExclusions;
    }

    /**
     * Sets the value of the regionalExclusions property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRegionalExclusion }{@code >}
     *
     */
    public void setRegionalExclusions(JAXBElement<ArrayOfRegionalExclusion> value) {
        this.regionalExclusions = value;
    }

    /**
     * Gets the value of the resetPrice property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isResetPrice() {
        return resetPrice;
    }

    /**
     * Sets the value of the resetPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setResetPrice(Boolean value) {
        this.resetPrice = value;
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
     * Gets the value of the scheduleNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getScheduleNumber() {
        return scheduleNumber;
    }

    /**
     * Sets the value of the scheduleNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setScheduleNumber(Integer value) {
        this.scheduleNumber = value;
    }

    /**
     * Gets the value of the sequenceNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSequenceNo() {
        return sequenceNo;
    }

    /**
     * Sets the value of the sequenceNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSequenceNo(Integer value) {
        this.sequenceNo = value;
    }

    /**
     * Gets the value of the spotBitFlags property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSpotBitFlags() {
        return spotBitFlags;
    }

    /**
     * Sets the value of the spotBitFlags property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSpotBitFlags(Integer value) {
        this.spotBitFlags = value;
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
     * Gets the value of the spotSalesAreaNumber property.
     *
     * @return
     *     possible object is
     *     {@link Short }
     *
     */
    public Short getSpotSalesAreaNumber() {
        return spotSalesAreaNumber;
    }

    /**
     * Sets the value of the spotSalesAreaNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Short }
     *
     */
    public void setSpotSalesAreaNumber(Short value) {
        this.spotSalesAreaNumber = value;
    }

    /**
     * Gets the value of the spotStatus property.
     *
     * @return
     *     possible object is
     *     {@link SpotStatus }
     *
     */
    public SpotStatus getSpotStatus() {
        return spotStatus;
    }

    /**
     * Sets the value of the spotStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link SpotStatus }
     *
     */
    public void setSpotStatus(SpotStatus value) {
        this.spotStatus = value;
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

    /**
     * Gets the value of the tolerance property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getTolerance() {
        return tolerance;
    }

    /**
     * Sets the value of the tolerance property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setTolerance(Integer value) {
        this.tolerance = value;
    }

    /**
     * Gets the value of the useReserve property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isUseReserve() {
        return useReserve;
    }

    /**
     * Sets the value of the useReserve property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setUseReserve(Boolean value) {
        this.useReserve = value;
    }

}
