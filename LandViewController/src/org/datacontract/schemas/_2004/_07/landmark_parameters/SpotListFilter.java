
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfint;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;


/**
 * <p>Java class for SpotListFilter complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SpotListFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AlternateSchedule" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="AlternateSchedules" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="BonusSpot" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="BreakType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BusinessAreas" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="BusinessTypes" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="CallerOrganisationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CallerPositionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Campaigns" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="CbacCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CbacDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClashCodes" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="Clients" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="ConvDemographicNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CopyCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CopyProcess" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Deals" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="DemographicNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DiscountCodes" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="DiscountRangeEnd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DiscountRangeStart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EfficiencyHigh" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="EfficiencyLow" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="ExcludeRatingsData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="FilterCriteria" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes}SpotListFilterCriteria" minOccurs="0"/>
 *         &lt;element name="FilterDateTimes" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes}ArrayOfFilterDateTime" minOccurs="0"/>
 *         &lt;element name="IncludeCopyDetails" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeInvoiceInfo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludePackages" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludePremiumCategoryData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludePrimary" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeProgrammeOrCategory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeSchedulePayments" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeSubAreas" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="InclusiveDiscountRange" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="InclusivePreemptionRange" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Interactivity" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes}Interactivity" minOccurs="0"/>
 *         &lt;element name="OrganisationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ParpName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ParpNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PositionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PositionInBreak" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PositionInProgramme" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PreemptionCodes" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="PreemptionRangeEnd" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PreemptionRangeStart" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PriceHigh" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="PriceLow" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="ProductCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ProductReportingCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProgrammeCategoryNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ProgrammeNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="RatingsHigh" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="RatingsLow" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="RegnBreakout" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ReportingPositionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RetrieveCopy" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SalesAreas" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="SecondaryDemographicNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SecondaryRatingsSupplier" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SpotLengths" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="SpotNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotStatuses" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
 *         &lt;element name="SpotTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TxmnFailData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="UntxmdSpotData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpotListFilter", propOrder = {
         "alternateSchedule", "alternateSchedules", "bonusSpot", "breakType", "businessAreas", "businessTypes",
         "callerOrganisationCode", "callerPositionCode", "campaigns", "cbacCode", "cbacDescription", "clashCodes",
         "clients", "convDemographicNumber", "copyCode", "copyProcess", "currency", "deals", "demographicNumber",
         "discountCodes", "discountRangeEnd", "discountRangeStart", "efficiencyHigh", "efficiencyLow",
         "excludeRatingsData", "filterCriteria", "filterDateTimes", "includeCopyDetails", "includeInvoiceInfo",
         "includePackages", "includePremiumCategoryData", "includePrimary", "includeProgrammeOrCategory",
         "includeSchedulePayments", "includeSubAreas", "inclusiveDiscountRange", "inclusivePreemptionRange",
         "interactivity", "organisationCode", "parpName", "parpNo", "positionCode", "positionInBreak",
         "positionInProgramme", "preemptionCodes", "preemptionRangeEnd", "preemptionRangeStart", "priceHigh",
         "priceLow", "productCode", "productReportingCategory", "productSource", "programmeCategoryNumber",
         "programmeNumber", "ratingsHigh", "ratingsLow", "regnBreakout", "reportingPositionCode", "retrieveCopy",
         "salesAreas", "secondaryDemographicNumber", "secondaryRatingsSupplier", "spotLengths", "spotNumber",
         "spotStatuses", "spotTypeCode", "txmnFailData", "untxmdSpotData"
    })
public class SpotListFilter {

    @XmlElement(name = "AlternateSchedule")
    protected Integer alternateSchedule;
    @XmlElementRef(name = "AlternateSchedules",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> alternateSchedules;
    @XmlElement(name = "BonusSpot")
    protected Boolean bonusSpot;
    @XmlElementRef(name = "BreakType", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> breakType;
    @XmlElementRef(name = "BusinessAreas",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> businessAreas;
    @XmlElementRef(name = "BusinessTypes",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> businessTypes;
    @XmlElementRef(name = "CallerOrganisationCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> callerOrganisationCode;
    @XmlElementRef(name = "CallerPositionCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> callerPositionCode;
    @XmlElementRef(name = "Campaigns", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> campaigns;
    @XmlElementRef(name = "CbacCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> cbacCode;
    @XmlElementRef(name = "CbacDescription",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> cbacDescription;
    @XmlElementRef(name = "ClashCodes", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> clashCodes;
    @XmlElementRef(name = "Clients", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> clients;
    @XmlElement(name = "ConvDemographicNumber")
    protected Integer convDemographicNumber;
    @XmlElement(name = "CopyCode")
    protected Integer copyCode;
    @XmlElement(name = "CopyProcess")
    protected Integer copyProcess;
    @XmlElementRef(name = "Currency", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> currency;
    @XmlElementRef(name = "Deals", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> deals;
    @XmlElement(name = "DemographicNumber")
    protected Integer demographicNumber;
    @XmlElementRef(name = "DiscountCodes",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> discountCodes;
    @XmlElementRef(name = "DiscountRangeEnd",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> discountRangeEnd;
    @XmlElementRef(name = "DiscountRangeStart",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> discountRangeStart;
    @XmlElement(name = "EfficiencyHigh")
    protected Float efficiencyHigh;
    @XmlElement(name = "EfficiencyLow")
    protected Float efficiencyLow;
    @XmlElement(name = "ExcludeRatingsData")
    protected Boolean excludeRatingsData;
    @XmlElement(name = "FilterCriteria")
    @XmlSchemaType(name = "string")
    protected SpotListFilterCriteria filterCriteria;
    @XmlElementRef(name = "FilterDateTimes",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfFilterDateTime> filterDateTimes;
    @XmlElement(name = "IncludeCopyDetails")
    protected Boolean includeCopyDetails;
    @XmlElement(name = "IncludeInvoiceInfo")
    protected Boolean includeInvoiceInfo;
    @XmlElement(name = "IncludePackages")
    protected Boolean includePackages;
    @XmlElement(name = "IncludePremiumCategoryData")
    protected Boolean includePremiumCategoryData;
    @XmlElement(name = "IncludePrimary")
    protected Boolean includePrimary;
    @XmlElement(name = "IncludeProgrammeOrCategory")
    protected Boolean includeProgrammeOrCategory;
    @XmlElement(name = "IncludeSchedulePayments")
    protected Boolean includeSchedulePayments;
    @XmlElement(name = "IncludeSubAreas")
    protected Boolean includeSubAreas;
    @XmlElement(name = "InclusiveDiscountRange")
    protected Boolean inclusiveDiscountRange;
    @XmlElement(name = "InclusivePreemptionRange")
    protected Boolean inclusivePreemptionRange;
    @XmlElement(name = "Interactivity")
    @XmlSchemaType(name = "string")
    protected Interactivity interactivity;
    @XmlElementRef(name = "OrganisationCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> organisationCode;
    @XmlElementRef(name = "ParpName", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> parpName;
    @XmlElement(name = "ParpNo")
    protected Integer parpNo;
    @XmlElementRef(name = "PositionCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> positionCode;
    @XmlElement(name = "PositionInBreak")
    protected Integer positionInBreak;
    @XmlElementRef(name = "PositionInProgramme",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> positionInProgramme;
    @XmlElementRef(name = "PreemptionCodes",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> preemptionCodes;
    @XmlElement(name = "PreemptionRangeEnd")
    protected Integer preemptionRangeEnd;
    @XmlElement(name = "PreemptionRangeStart")
    protected Integer preemptionRangeStart;
    @XmlElement(name = "PriceHigh")
    protected Float priceHigh;
    @XmlElement(name = "PriceLow")
    protected Float priceLow;
    @XmlElement(name = "ProductCode")
    protected Integer productCode;
    @XmlElementRef(name = "ProductReportingCategory",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> productReportingCategory;
    @XmlElementRef(name = "ProductSource",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> productSource;
    @XmlElement(name = "ProgrammeCategoryNumber")
    protected Integer programmeCategoryNumber;
    @XmlElement(name = "ProgrammeNumber")
    protected Integer programmeNumber;
    @XmlElement(name = "RatingsHigh")
    protected Float ratingsHigh;
    @XmlElement(name = "RatingsLow")
    protected Float ratingsLow;
    @XmlElement(name = "RegnBreakout")
    protected Boolean regnBreakout;
    @XmlElementRef(name = "ReportingPositionCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> reportingPositionCode;
    @XmlElement(name = "RetrieveCopy")
    protected Boolean retrieveCopy;
    @XmlElementRef(name = "SalesAreas", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> salesAreas;
    @XmlElement(name = "SecondaryDemographicNumber")
    protected Integer secondaryDemographicNumber;
    @XmlElement(name = "SecondaryRatingsSupplier")
    protected Boolean secondaryRatingsSupplier;
    @XmlElementRef(name = "SpotLengths",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> spotLengths;
    @XmlElement(name = "SpotNumber")
    protected Integer spotNumber;
    @XmlElementRef(name = "SpotStatuses",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> spotStatuses;
    @XmlElementRef(name = "SpotTypeCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> spotTypeCode;
    @XmlElement(name = "TxmnFailData")
    protected Boolean txmnFailData;
    @XmlElement(name = "UntxmdSpotData")
    protected Boolean untxmdSpotData;

    /**
     * Gets the value of the alternateSchedule property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getAlternateSchedule() {
        return alternateSchedule;
    }

    /**
     * Sets the value of the alternateSchedule property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setAlternateSchedule(Integer value) {
        this.alternateSchedule = value;
    }

    /**
     * Gets the value of the alternateSchedules property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public JAXBElement<ArrayOfint> getAlternateSchedules() {
        return alternateSchedules;
    }

    /**
     * Sets the value of the alternateSchedules property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public void setAlternateSchedules(JAXBElement<ArrayOfint> value) {
        this.alternateSchedules = value;
    }

    /**
     * Gets the value of the bonusSpot property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isBonusSpot() {
        return bonusSpot;
    }

    /**
     * Sets the value of the bonusSpot property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setBonusSpot(Boolean value) {
        this.bonusSpot = value;
    }

    /**
     * Gets the value of the breakType property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getBreakType() {
        return breakType;
    }

    /**
     * Sets the value of the breakType property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setBreakType(JAXBElement<String> value) {
        this.breakType = value;
    }

    /**
     * Gets the value of the businessAreas property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public JAXBElement<ArrayOfint> getBusinessAreas() {
        return businessAreas;
    }

    /**
     * Sets the value of the businessAreas property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public void setBusinessAreas(JAXBElement<ArrayOfint> value) {
        this.businessAreas = value;
    }

    /**
     * Gets the value of the businessTypes property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public JAXBElement<ArrayOfint> getBusinessTypes() {
        return businessTypes;
    }

    /**
     * Sets the value of the businessTypes property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public void setBusinessTypes(JAXBElement<ArrayOfint> value) {
        this.businessTypes = value;
    }

    /**
     * Gets the value of the callerOrganisationCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCallerOrganisationCode() {
        return callerOrganisationCode;
    }

    /**
     * Sets the value of the callerOrganisationCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCallerOrganisationCode(JAXBElement<String> value) {
        this.callerOrganisationCode = value;
    }

    /**
     * Gets the value of the callerPositionCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCallerPositionCode() {
        return callerPositionCode;
    }

    /**
     * Sets the value of the callerPositionCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCallerPositionCode(JAXBElement<String> value) {
        this.callerPositionCode = value;
    }

    /**
     * Gets the value of the campaigns property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public JAXBElement<ArrayOfint> getCampaigns() {
        return campaigns;
    }

    /**
     * Sets the value of the campaigns property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public void setCampaigns(JAXBElement<ArrayOfint> value) {
        this.campaigns = value;
    }

    /**
     * Gets the value of the cbacCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCbacCode() {
        return cbacCode;
    }

    /**
     * Sets the value of the cbacCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCbacCode(JAXBElement<String> value) {
        this.cbacCode = value;
    }

    /**
     * Gets the value of the cbacDescription property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCbacDescription() {
        return cbacDescription;
    }

    /**
     * Sets the value of the cbacDescription property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCbacDescription(JAXBElement<String> value) {
        this.cbacDescription = value;
    }

    /**
     * Gets the value of the clashCodes property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *
     */
    public JAXBElement<ArrayOfstring> getClashCodes() {
        return clashCodes;
    }

    /**
     * Sets the value of the clashCodes property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *
     */
    public void setClashCodes(JAXBElement<ArrayOfstring> value) {
        this.clashCodes = value;
    }

    /**
     * Gets the value of the clients property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *
     */
    public JAXBElement<ArrayOfstring> getClients() {
        return clients;
    }

    /**
     * Sets the value of the clients property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *
     */
    public void setClients(JAXBElement<ArrayOfstring> value) {
        this.clients = value;
    }

    /**
     * Gets the value of the convDemographicNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getConvDemographicNumber() {
        return convDemographicNumber;
    }

    /**
     * Sets the value of the convDemographicNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setConvDemographicNumber(Integer value) {
        this.convDemographicNumber = value;
    }

    /**
     * Gets the value of the copyCode property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getCopyCode() {
        return copyCode;
    }

    /**
     * Sets the value of the copyCode property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setCopyCode(Integer value) {
        this.copyCode = value;
    }

    /**
     * Gets the value of the copyProcess property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getCopyProcess() {
        return copyProcess;
    }

    /**
     * Sets the value of the copyProcess property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setCopyProcess(Integer value) {
        this.copyProcess = value;
    }

    /**
     * Gets the value of the currency property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCurrency(JAXBElement<String> value) {
        this.currency = value;
    }

    /**
     * Gets the value of the deals property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public JAXBElement<ArrayOfint> getDeals() {
        return deals;
    }

    /**
     * Sets the value of the deals property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public void setDeals(JAXBElement<ArrayOfint> value) {
        this.deals = value;
    }

    /**
     * Gets the value of the demographicNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getDemographicNumber() {
        return demographicNumber;
    }

    /**
     * Sets the value of the demographicNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setDemographicNumber(Integer value) {
        this.demographicNumber = value;
    }

    /**
     * Gets the value of the discountCodes property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *
     */
    public JAXBElement<ArrayOfstring> getDiscountCodes() {
        return discountCodes;
    }

    /**
     * Sets the value of the discountCodes property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *
     */
    public void setDiscountCodes(JAXBElement<ArrayOfstring> value) {
        this.discountCodes = value;
    }

    /**
     * Gets the value of the discountRangeEnd property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getDiscountRangeEnd() {
        return discountRangeEnd;
    }

    /**
     * Sets the value of the discountRangeEnd property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setDiscountRangeEnd(JAXBElement<String> value) {
        this.discountRangeEnd = value;
    }

    /**
     * Gets the value of the discountRangeStart property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getDiscountRangeStart() {
        return discountRangeStart;
    }

    /**
     * Sets the value of the discountRangeStart property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setDiscountRangeStart(JAXBElement<String> value) {
        this.discountRangeStart = value;
    }

    /**
     * Gets the value of the efficiencyHigh property.
     *
     * @return
     *     possible object is
     *     {@link Float }
     *
     */
    public Float getEfficiencyHigh() {
        return efficiencyHigh;
    }

    /**
     * Sets the value of the efficiencyHigh property.
     *
     * @param value
     *     allowed object is
     *     {@link Float }
     *
     */
    public void setEfficiencyHigh(Float value) {
        this.efficiencyHigh = value;
    }

    /**
     * Gets the value of the efficiencyLow property.
     *
     * @return
     *     possible object is
     *     {@link Float }
     *
     */
    public Float getEfficiencyLow() {
        return efficiencyLow;
    }

    /**
     * Sets the value of the efficiencyLow property.
     *
     * @param value
     *     allowed object is
     *     {@link Float }
     *
     */
    public void setEfficiencyLow(Float value) {
        this.efficiencyLow = value;
    }

    /**
     * Gets the value of the excludeRatingsData property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isExcludeRatingsData() {
        return excludeRatingsData;
    }

    /**
     * Sets the value of the excludeRatingsData property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setExcludeRatingsData(Boolean value) {
        this.excludeRatingsData = value;
    }

    /**
     * Gets the value of the filterCriteria property.
     *
     * @return
     *     possible object is
     *     {@link SpotListFilterCriteria }
     *
     */
    public SpotListFilterCriteria getFilterCriteria() {
        return filterCriteria;
    }

    /**
     * Sets the value of the filterCriteria property.
     *
     * @param value
     *     allowed object is
     *     {@link SpotListFilterCriteria }
     *
     */
    public void setFilterCriteria(SpotListFilterCriteria value) {
        this.filterCriteria = value;
    }

    /**
     * Gets the value of the filterDateTimes property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfFilterDateTime }{@code >}
     *
     */
    public JAXBElement<ArrayOfFilterDateTime> getFilterDateTimes() {
        return filterDateTimes;
    }

    /**
     * Sets the value of the filterDateTimes property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfFilterDateTime }{@code >}
     *
     */
    public void setFilterDateTimes(JAXBElement<ArrayOfFilterDateTime> value) {
        this.filterDateTimes = value;
    }

    /**
     * Gets the value of the includeCopyDetails property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIncludeCopyDetails() {
        return includeCopyDetails;
    }

    /**
     * Sets the value of the includeCopyDetails property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIncludeCopyDetails(Boolean value) {
        this.includeCopyDetails = value;
    }

    /**
     * Gets the value of the includeInvoiceInfo property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIncludeInvoiceInfo() {
        return includeInvoiceInfo;
    }

    /**
     * Sets the value of the includeInvoiceInfo property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIncludeInvoiceInfo(Boolean value) {
        this.includeInvoiceInfo = value;
    }

    /**
     * Gets the value of the includePackages property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIncludePackages() {
        return includePackages;
    }

    /**
     * Sets the value of the includePackages property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIncludePackages(Boolean value) {
        this.includePackages = value;
    }

    /**
     * Gets the value of the includePremiumCategoryData property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIncludePremiumCategoryData() {
        return includePremiumCategoryData;
    }

    /**
     * Sets the value of the includePremiumCategoryData property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIncludePremiumCategoryData(Boolean value) {
        this.includePremiumCategoryData = value;
    }

    /**
     * Gets the value of the includePrimary property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIncludePrimary() {
        return includePrimary;
    }

    /**
     * Sets the value of the includePrimary property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIncludePrimary(Boolean value) {
        this.includePrimary = value;
    }

    /**
     * Gets the value of the includeProgrammeOrCategory property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIncludeProgrammeOrCategory() {
        return includeProgrammeOrCategory;
    }

    /**
     * Sets the value of the includeProgrammeOrCategory property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIncludeProgrammeOrCategory(Boolean value) {
        this.includeProgrammeOrCategory = value;
    }

    /**
     * Gets the value of the includeSchedulePayments property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIncludeSchedulePayments() {
        return includeSchedulePayments;
    }

    /**
     * Sets the value of the includeSchedulePayments property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIncludeSchedulePayments(Boolean value) {
        this.includeSchedulePayments = value;
    }

    /**
     * Gets the value of the includeSubAreas property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIncludeSubAreas() {
        return includeSubAreas;
    }

    /**
     * Sets the value of the includeSubAreas property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIncludeSubAreas(Boolean value) {
        this.includeSubAreas = value;
    }

    /**
     * Gets the value of the inclusiveDiscountRange property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isInclusiveDiscountRange() {
        return inclusiveDiscountRange;
    }

    /**
     * Sets the value of the inclusiveDiscountRange property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setInclusiveDiscountRange(Boolean value) {
        this.inclusiveDiscountRange = value;
    }

    /**
     * Gets the value of the inclusivePreemptionRange property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isInclusivePreemptionRange() {
        return inclusivePreemptionRange;
    }

    /**
     * Sets the value of the inclusivePreemptionRange property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setInclusivePreemptionRange(Boolean value) {
        this.inclusivePreemptionRange = value;
    }

    /**
     * Gets the value of the interactivity property.
     *
     * @return
     *     possible object is
     *     {@link Interactivity }
     *
     */
    public Interactivity getInteractivity() {
        return interactivity;
    }

    /**
     * Sets the value of the interactivity property.
     *
     * @param value
     *     allowed object is
     *     {@link Interactivity }
     *
     */
    public void setInteractivity(Interactivity value) {
        this.interactivity = value;
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
     * Gets the value of the parpName property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getParpName() {
        return parpName;
    }

    /**
     * Sets the value of the parpName property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setParpName(JAXBElement<String> value) {
        this.parpName = value;
    }

    /**
     * Gets the value of the parpNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getParpNo() {
        return parpNo;
    }

    /**
     * Sets the value of the parpNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setParpNo(Integer value) {
        this.parpNo = value;
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
     * Gets the value of the positionInBreak property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPositionInBreak() {
        return positionInBreak;
    }

    /**
     * Sets the value of the positionInBreak property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPositionInBreak(Integer value) {
        this.positionInBreak = value;
    }

    /**
     * Gets the value of the positionInProgramme property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getPositionInProgramme() {
        return positionInProgramme;
    }

    /**
     * Sets the value of the positionInProgramme property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setPositionInProgramme(JAXBElement<String> value) {
        this.positionInProgramme = value;
    }

    /**
     * Gets the value of the preemptionCodes property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public JAXBElement<ArrayOfint> getPreemptionCodes() {
        return preemptionCodes;
    }

    /**
     * Sets the value of the preemptionCodes property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public void setPreemptionCodes(JAXBElement<ArrayOfint> value) {
        this.preemptionCodes = value;
    }

    /**
     * Gets the value of the preemptionRangeEnd property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPreemptionRangeEnd() {
        return preemptionRangeEnd;
    }

    /**
     * Sets the value of the preemptionRangeEnd property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPreemptionRangeEnd(Integer value) {
        this.preemptionRangeEnd = value;
    }

    /**
     * Gets the value of the preemptionRangeStart property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPreemptionRangeStart() {
        return preemptionRangeStart;
    }

    /**
     * Sets the value of the preemptionRangeStart property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPreemptionRangeStart(Integer value) {
        this.preemptionRangeStart = value;
    }

    /**
     * Gets the value of the priceHigh property.
     *
     * @return
     *     possible object is
     *     {@link Float }
     *
     */
    public Float getPriceHigh() {
        return priceHigh;
    }

    /**
     * Sets the value of the priceHigh property.
     *
     * @param value
     *     allowed object is
     *     {@link Float }
     *
     */
    public void setPriceHigh(Float value) {
        this.priceHigh = value;
    }

    /**
     * Gets the value of the priceLow property.
     *
     * @return
     *     possible object is
     *     {@link Float }
     *
     */
    public Float getPriceLow() {
        return priceLow;
    }

    /**
     * Sets the value of the priceLow property.
     *
     * @param value
     *     allowed object is
     *     {@link Float }
     *
     */
    public void setPriceLow(Float value) {
        this.priceLow = value;
    }

    /**
     * Gets the value of the productCode property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setProductCode(Integer value) {
        this.productCode = value;
    }

    /**
     * Gets the value of the productReportingCategory property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getProductReportingCategory() {
        return productReportingCategory;
    }

    /**
     * Sets the value of the productReportingCategory property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setProductReportingCategory(JAXBElement<String> value) {
        this.productReportingCategory = value;
    }

    /**
     * Gets the value of the productSource property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getProductSource() {
        return productSource;
    }

    /**
     * Sets the value of the productSource property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setProductSource(JAXBElement<String> value) {
        this.productSource = value;
    }

    /**
     * Gets the value of the programmeCategoryNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getProgrammeCategoryNumber() {
        return programmeCategoryNumber;
    }

    /**
     * Sets the value of the programmeCategoryNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setProgrammeCategoryNumber(Integer value) {
        this.programmeCategoryNumber = value;
    }

    /**
     * Gets the value of the programmeNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getProgrammeNumber() {
        return programmeNumber;
    }

    /**
     * Sets the value of the programmeNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setProgrammeNumber(Integer value) {
        this.programmeNumber = value;
    }

    /**
     * Gets the value of the ratingsHigh property.
     *
     * @return
     *     possible object is
     *     {@link Float }
     *
     */
    public Float getRatingsHigh() {
        return ratingsHigh;
    }

    /**
     * Sets the value of the ratingsHigh property.
     *
     * @param value
     *     allowed object is
     *     {@link Float }
     *
     */
    public void setRatingsHigh(Float value) {
        this.ratingsHigh = value;
    }

    /**
     * Gets the value of the ratingsLow property.
     *
     * @return
     *     possible object is
     *     {@link Float }
     *
     */
    public Float getRatingsLow() {
        return ratingsLow;
    }

    /**
     * Sets the value of the ratingsLow property.
     *
     * @param value
     *     allowed object is
     *     {@link Float }
     *
     */
    public void setRatingsLow(Float value) {
        this.ratingsLow = value;
    }

    /**
     * Gets the value of the regnBreakout property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isRegnBreakout() {
        return regnBreakout;
    }

    /**
     * Sets the value of the regnBreakout property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setRegnBreakout(Boolean value) {
        this.regnBreakout = value;
    }

    /**
     * Gets the value of the reportingPositionCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getReportingPositionCode() {
        return reportingPositionCode;
    }

    /**
     * Sets the value of the reportingPositionCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setReportingPositionCode(JAXBElement<String> value) {
        this.reportingPositionCode = value;
    }

    /**
     * Gets the value of the retrieveCopy property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isRetrieveCopy() {
        return retrieveCopy;
    }

    /**
     * Sets the value of the retrieveCopy property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setRetrieveCopy(Boolean value) {
        this.retrieveCopy = value;
    }

    /**
     * Gets the value of the salesAreas property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public JAXBElement<ArrayOfint> getSalesAreas() {
        return salesAreas;
    }

    /**
     * Sets the value of the salesAreas property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public void setSalesAreas(JAXBElement<ArrayOfint> value) {
        this.salesAreas = value;
    }

    /**
     * Gets the value of the secondaryDemographicNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSecondaryDemographicNumber() {
        return secondaryDemographicNumber;
    }

    /**
     * Sets the value of the secondaryDemographicNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSecondaryDemographicNumber(Integer value) {
        this.secondaryDemographicNumber = value;
    }

    /**
     * Gets the value of the secondaryRatingsSupplier property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isSecondaryRatingsSupplier() {
        return secondaryRatingsSupplier;
    }

    /**
     * Sets the value of the secondaryRatingsSupplier property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setSecondaryRatingsSupplier(Boolean value) {
        this.secondaryRatingsSupplier = value;
    }

    /**
     * Gets the value of the spotLengths property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public JAXBElement<ArrayOfint> getSpotLengths() {
        return spotLengths;
    }

    /**
     * Sets the value of the spotLengths property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public void setSpotLengths(JAXBElement<ArrayOfint> value) {
        this.spotLengths = value;
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
     * Gets the value of the spotStatuses property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public JAXBElement<ArrayOfint> getSpotStatuses() {
        return spotStatuses;
    }

    /**
     * Sets the value of the spotStatuses property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *
     */
    public void setSpotStatuses(JAXBElement<ArrayOfint> value) {
        this.spotStatuses = value;
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
     * Gets the value of the txmnFailData property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isTxmnFailData() {
        return txmnFailData;
    }

    /**
     * Sets the value of the txmnFailData property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setTxmnFailData(Boolean value) {
        this.txmnFailData = value;
    }

    /**
     * Gets the value of the untxmdSpotData property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isUntxmdSpotData() {
        return untxmdSpotData;
    }

    /**
     * Sets the value of the untxmdSpotData property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setUntxmdSpotData(Boolean value) {
        this.untxmdSpotData = value;
    }

}
