
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfint;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.datacontract.schemas._2004._07.landmark_parameters package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ArrayOfSpotSwapParams_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "ArrayOfSpotSwapParams");
    private final static QName _Overrides_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "Overrides");
    private final static QName _Interactivity_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes", "Interactivity");
    private final static QName _SpotSequenceMaintain_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotSequenceMaintain");
    private final static QName _ArrayOfSpotBookParams_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "ArrayOfSpotBookParams");
    private final static QName _ArrayOfDiscount_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "ArrayOfDiscount");
    private final static QName _RegionalExclusion_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "RegionalExclusion");
    private final static QName _SpotListFilterCriteria_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes", "SpotListFilterCriteria");
    private final static QName _SpotSequence_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotSequence");
    private final static QName _SpotCancelParams_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotCancelParams");
    private final static QName _BookingProcessType_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "BookingProcessType");
    private final static QName _DaysOfWeek_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes", "DaysOfWeek");
    private final static QName _ArrayOfSpotCancelParams_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "ArrayOfSpotCancelParams");
    private final static QName _ArrayOfRegionalExclusion_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "ArrayOfRegionalExclusion");
    private final static QName _ArrayOfSpotAmendParams_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "ArrayOfSpotAmendParams");
    private final static QName _SpotSwapParams_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotSwapParams");
    private final static QName _BatchBookingParams_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "BatchBookingParams");
    private final static QName _ArrayOfMultiPart_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "ArrayOfMultiPart");
    private final static QName _ArrayOfSpotMoveParams_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "ArrayOfSpotMoveParams");
    private final static QName _SpotAmendParams_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotAmendParams");
    private final static QName _Bkpo_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes", "Bkpo");
    private final static QName _SpotMoveParams_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotMoveParams");
    private final static QName _FilterDateTime_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes", "FilterDateTime");
    private final static QName _SpotStatus_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes", "SpotStatus");
    private final static QName _SpotListFilter_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotListFilter");
    private final static QName _Discount_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "Discount");
    private final static QName _ArrayOfOverrides_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "ArrayOfOverrides");
    private final static QName _ArrayOfSpotSequence_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "ArrayOfSpotSequence");
    private final static QName _SpotBookParams_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotBookParams");
    private final static QName _ArrayOfFilterDateTime_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes", "ArrayOfFilterDateTime");
    private final static QName _MultiPart_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "MultiPart");
    private final static QName _DiscountDiscountCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "DiscountCode");
    private final static QName _SpotAmendParamsMultiParts_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "MultiParts");
    private final static QName _SpotAmendParamsPromoSpotLink_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "PromoSpotLink");
    private final static QName _SpotAmendParamsRegionalExclusions_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "RegionalExclusions");
    private final static QName _SpotAmendParamsOverrides_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "Overrides");
    private final static QName _SpotAmendParamsSAPNumber_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SAPNumber");
    private final static QName _SpotAmendParamsBreakCategoryCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "BreakCategoryCode");
    private final static QName _SpotAmendParamsDealType_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "DealType");
    private final static QName _SpotAmendParamsBreakTypeCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "BreakTypeCode");
    private final static QName _SpotAmendParamsMobilityRuleCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "MobilityRuleCode");
    private final static QName _SpotAmendParamsDiscounts_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "Discounts");
    private final static QName _SpotAmendParamsPositionCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "PositionCode");
    private final static QName _SpotAmendParamsCurrencyCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "CurrencyCode");
    private final static QName _SpotAmendParamsOrganisationCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "OrganisationCode");
    private final static QName _SpotAmendParamsSpotTypeCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotTypeCode");
    private final static QName _SpotAmendParamsReasonCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "ReasonCode");
    private final static QName _MultiPartSpotTypeCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "SpotTypeCode");
    private final static QName _MultiPartPrice_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "Price");
    private final static QName _SpotMoveParamsSpotSalesAreaCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotSalesAreaCode");
    private final static QName _SpotMoveParamsBreakNominalTime_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "BreakNominalTime");
    private final static QName _SpotMoveParamsSpotStatus_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotStatus");
    private final static QName _SpotMoveParamsBreakScheduledDate_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "BreakScheduledDate");
    private final static QName _SpotMoveParamsBreakSalesAreaCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "BreakSalesAreaCode");
    private final static QName _SpotMoveParamsBreakNumber_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "BreakNumber");
    private final static QName _SpotMoveParamsSpotDuration_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotDuration");
    private final static QName _RegionalExclusionExclYN_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "ExclYN");
    private final static QName _SpotListFilterClashCodes_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "ClashCodes");
    private final static QName _SpotListFilterCallerOrganisationCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "CallerOrganisationCode");
    private final static QName _SpotListFilterSpotStatuses_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotStatuses");
    private final static QName _SpotListFilterCallerPositionCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "CallerPositionCode");
    private final static QName _SpotListFilterCurrency_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "Currency");
    private final static QName _SpotListFilterBreakType_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "BreakType");
    private final static QName _SpotListFilterBusinessTypes_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "BusinessTypes");
    private final static QName _SpotListFilterDeals_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "Deals");
    private final static QName _SpotListFilterPositionInProgramme_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "PositionInProgramme");
    private final static QName _SpotListFilterCampaigns_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "Campaigns");
    private final static QName _SpotListFilterAlternateSchedules_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "AlternateSchedules");
    private final static QName _SpotListFilterPreemptionCodes_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "PreemptionCodes");
    private final static QName _SpotListFilterSalesAreas_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SalesAreas");
    private final static QName _SpotListFilterDiscountCodes_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "DiscountCodes");
    private final static QName _SpotListFilterProductReportingCategory_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "ProductReportingCategory");
    private final static QName _SpotListFilterReportingPositionCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "ReportingPositionCode");
    private final static QName _SpotListFilterClients_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "Clients");
    private final static QName _SpotListFilterSpotLengths_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "SpotLengths");
    private final static QName _SpotListFilterCbacCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "CbacCode");
    private final static QName _SpotListFilterParpName_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "ParpName");
    private final static QName _SpotListFilterDiscountRangeEnd_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "DiscountRangeEnd");
    private final static QName _SpotListFilterProductSource_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "ProductSource");
    private final static QName _SpotListFilterCbacDescription_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "CbacDescription");
    private final static QName _SpotListFilterBusinessAreas_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "BusinessAreas");
    private final static QName _SpotListFilterFilterDateTimes_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "FilterDateTimes");
    private final static QName _SpotListFilterDiscountRangeStart_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "DiscountRangeStart");
    private final static QName _BatchBookingParamsOrgrCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "OrgrCode");
    private final static QName _BatchBookingParamsPosnCode_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", "PosnCode");
    private final static QName _OverridesMsgText_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "MsgText");
    private final static QName _OverridesExtraInfo_QNAME =
        new QName("http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", "ExtraInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.landmark_parameters
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SpotSequenceMaintain }
     *
     */
    public SpotSequenceMaintain createSpotSequenceMaintain() {
        return new SpotSequenceMaintain();
    }

    /**
     * Create an instance of {@link SpotListFilter }
     *
     */
    public SpotListFilter createSpotListFilter() {
        return new SpotListFilter();
    }

    /**
     * Create an instance of {@link BatchBookingParams }
     *
     */
    public BatchBookingParams createBatchBookingParams() {
        return new BatchBookingParams();
    }

    /**
     * Create an instance of {@link SpotSwapParams }
     *
     */
    public SpotSwapParams createSpotSwapParams() {
        return new SpotSwapParams();
    }

    /**
     * Create an instance of {@link SpotMoveParams }
     *
     */
    public SpotMoveParams createSpotMoveParams() {
        return new SpotMoveParams();
    }

    /**
     * Create an instance of {@link ArrayOfSpotSequence }
     *
     */
    public ArrayOfSpotSequence createArrayOfSpotSequence() {
        return new ArrayOfSpotSequence();
    }

    /**
     * Create an instance of {@link ArrayOfSpotAmendParams }
     *
     */
    public ArrayOfSpotAmendParams createArrayOfSpotAmendParams() {
        return new ArrayOfSpotAmendParams();
    }

    /**
     * Create an instance of {@link SpotBookParams }
     *
     */
    public SpotBookParams createSpotBookParams() {
        return new SpotBookParams();
    }

    /**
     * Create an instance of {@link SpotAmendParams }
     *
     */
    public SpotAmendParams createSpotAmendParams() {
        return new SpotAmendParams();
    }

    /**
     * Create an instance of {@link SpotSequence }
     *
     */
    public SpotSequence createSpotSequence() {
        return new SpotSequence();
    }

    /**
     * Create an instance of {@link SpotCancelParams }
     *
     */
    public SpotCancelParams createSpotCancelParams() {
        return new SpotCancelParams();
    }

    /**
     * Create an instance of {@link ArrayOfSpotCancelParams }
     *
     */
    public ArrayOfSpotCancelParams createArrayOfSpotCancelParams() {
        return new ArrayOfSpotCancelParams();
    }

    /**
     * Create an instance of {@link ArrayOfSpotMoveParams }
     *
     */
    public ArrayOfSpotMoveParams createArrayOfSpotMoveParams() {
        return new ArrayOfSpotMoveParams();
    }

    /**
     * Create an instance of {@link ArrayOfSpotSwapParams }
     *
     */
    public ArrayOfSpotSwapParams createArrayOfSpotSwapParams() {
        return new ArrayOfSpotSwapParams();
    }

    /**
     * Create an instance of {@link ArrayOfSpotBookParams }
     *
     */
    public ArrayOfSpotBookParams createArrayOfSpotBookParams() {
        return new ArrayOfSpotBookParams();
    }

    /**
     * Create an instance of {@link FilterDateTime }
     *
     */
    public FilterDateTime createFilterDateTime() {
        return new FilterDateTime();
    }

    /**
     * Create an instance of {@link ArrayOfFilterDateTime }
     *
     */
    public ArrayOfFilterDateTime createArrayOfFilterDateTime() {
        return new ArrayOfFilterDateTime();
    }

    /**
     * Create an instance of {@link ArrayOfRegionalExclusion }
     *
     */
    public ArrayOfRegionalExclusion createArrayOfRegionalExclusion() {
        return new ArrayOfRegionalExclusion();
    }

    /**
     * Create an instance of {@link MultiPart }
     *
     */
    public MultiPart createMultiPart() {
        return new MultiPart();
    }

    /**
     * Create an instance of {@link RegionalExclusion }
     *
     */
    public RegionalExclusion createRegionalExclusion() {
        return new RegionalExclusion();
    }

    /**
     * Create an instance of {@link ArrayOfDiscount }
     *
     */
    public ArrayOfDiscount createArrayOfDiscount() {
        return new ArrayOfDiscount();
    }

    /**
     * Create an instance of {@link Discount }
     *
     */
    public Discount createDiscount() {
        return new Discount();
    }

    /**
     * Create an instance of {@link Overrides }
     *
     */
    public Overrides createOverrides() {
        return new Overrides();
    }

    /**
     * Create an instance of {@link ArrayOfOverrides }
     *
     */
    public ArrayOfOverrides createArrayOfOverrides() {
        return new ArrayOfOverrides();
    }

    /**
     * Create an instance of {@link ArrayOfMultiPart }
     *
     */
    public ArrayOfMultiPart createArrayOfMultiPart() {
        return new ArrayOfMultiPart();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotSwapParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "ArrayOfSpotSwapParams")
    public JAXBElement<ArrayOfSpotSwapParams> createArrayOfSpotSwapParams(ArrayOfSpotSwapParams value) {
        return new JAXBElement<ArrayOfSpotSwapParams>(_ArrayOfSpotSwapParams_QNAME, ArrayOfSpotSwapParams.class, null,
                                                      value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Overrides }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "Overrides")
    public JAXBElement<Overrides> createOverrides(Overrides value) {
        return new JAXBElement<Overrides>(_Overrides_QNAME, Overrides.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Interactivity }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes",
                    name = "Interactivity")
    public JAXBElement<Interactivity> createInteractivity(Interactivity value) {
        return new JAXBElement<Interactivity>(_Interactivity_QNAME, Interactivity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotSequenceMaintain }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotSequenceMaintain")
    public JAXBElement<SpotSequenceMaintain> createSpotSequenceMaintain(SpotSequenceMaintain value) {
        return new JAXBElement<SpotSequenceMaintain>(_SpotSequenceMaintain_QNAME, SpotSequenceMaintain.class, null,
                                                     value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotBookParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "ArrayOfSpotBookParams")
    public JAXBElement<ArrayOfSpotBookParams> createArrayOfSpotBookParams(ArrayOfSpotBookParams value) {
        return new JAXBElement<ArrayOfSpotBookParams>(_ArrayOfSpotBookParams_QNAME, ArrayOfSpotBookParams.class, null,
                                                      value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDiscount }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "ArrayOfDiscount")
    public JAXBElement<ArrayOfDiscount> createArrayOfDiscount(ArrayOfDiscount value) {
        return new JAXBElement<ArrayOfDiscount>(_ArrayOfDiscount_QNAME, ArrayOfDiscount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegionalExclusion }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "RegionalExclusion")
    public JAXBElement<RegionalExclusion> createRegionalExclusion(RegionalExclusion value) {
        return new JAXBElement<RegionalExclusion>(_RegionalExclusion_QNAME, RegionalExclusion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotListFilterCriteria }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes",
                    name = "SpotListFilterCriteria")
    public JAXBElement<SpotListFilterCriteria> createSpotListFilterCriteria(SpotListFilterCriteria value) {
        return new JAXBElement<SpotListFilterCriteria>(_SpotListFilterCriteria_QNAME, SpotListFilterCriteria.class,
                                                       null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotSequence }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotSequence")
    public JAXBElement<SpotSequence> createSpotSequence(SpotSequence value) {
        return new JAXBElement<SpotSequence>(_SpotSequence_QNAME, SpotSequence.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotCancelParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotCancelParams")
    public JAXBElement<SpotCancelParams> createSpotCancelParams(SpotCancelParams value) {
        return new JAXBElement<SpotCancelParams>(_SpotCancelParams_QNAME, SpotCancelParams.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookingProcessType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "BookingProcessType")
    public JAXBElement<BookingProcessType> createBookingProcessType(BookingProcessType value) {
        return new JAXBElement<BookingProcessType>(_BookingProcessType_QNAME, BookingProcessType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DaysOfWeek }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes",
                    name = "DaysOfWeek")
    public JAXBElement<DaysOfWeek> createDaysOfWeek(DaysOfWeek value) {
        return new JAXBElement<DaysOfWeek>(_DaysOfWeek_QNAME, DaysOfWeek.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotCancelParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "ArrayOfSpotCancelParams")
    public JAXBElement<ArrayOfSpotCancelParams> createArrayOfSpotCancelParams(ArrayOfSpotCancelParams value) {
        return new JAXBElement<ArrayOfSpotCancelParams>(_ArrayOfSpotCancelParams_QNAME, ArrayOfSpotCancelParams.class,
                                                        null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRegionalExclusion }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "ArrayOfRegionalExclusion")
    public JAXBElement<ArrayOfRegionalExclusion> createArrayOfRegionalExclusion(ArrayOfRegionalExclusion value) {
        return new JAXBElement<ArrayOfRegionalExclusion>(_ArrayOfRegionalExclusion_QNAME,
                                                         ArrayOfRegionalExclusion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotAmendParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "ArrayOfSpotAmendParams")
    public JAXBElement<ArrayOfSpotAmendParams> createArrayOfSpotAmendParams(ArrayOfSpotAmendParams value) {
        return new JAXBElement<ArrayOfSpotAmendParams>(_ArrayOfSpotAmendParams_QNAME, ArrayOfSpotAmendParams.class,
                                                       null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotSwapParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotSwapParams")
    public JAXBElement<SpotSwapParams> createSpotSwapParams(SpotSwapParams value) {
        return new JAXBElement<SpotSwapParams>(_SpotSwapParams_QNAME, SpotSwapParams.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BatchBookingParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "BatchBookingParams")
    public JAXBElement<BatchBookingParams> createBatchBookingParams(BatchBookingParams value) {
        return new JAXBElement<BatchBookingParams>(_BatchBookingParams_QNAME, BatchBookingParams.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMultiPart }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "ArrayOfMultiPart")
    public JAXBElement<ArrayOfMultiPart> createArrayOfMultiPart(ArrayOfMultiPart value) {
        return new JAXBElement<ArrayOfMultiPart>(_ArrayOfMultiPart_QNAME, ArrayOfMultiPart.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotMoveParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "ArrayOfSpotMoveParams")
    public JAXBElement<ArrayOfSpotMoveParams> createArrayOfSpotMoveParams(ArrayOfSpotMoveParams value) {
        return new JAXBElement<ArrayOfSpotMoveParams>(_ArrayOfSpotMoveParams_QNAME, ArrayOfSpotMoveParams.class, null,
                                                      value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotAmendParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotAmendParams")
    public JAXBElement<SpotAmendParams> createSpotAmendParams(SpotAmendParams value) {
        return new JAXBElement<SpotAmendParams>(_SpotAmendParams_QNAME, SpotAmendParams.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Bkpo }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes", name = "Bkpo")
    public JAXBElement<Bkpo> createBkpo(Bkpo value) {
        return new JAXBElement<Bkpo>(_Bkpo_QNAME, Bkpo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotMoveParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotMoveParams")
    public JAXBElement<SpotMoveParams> createSpotMoveParams(SpotMoveParams value) {
        return new JAXBElement<SpotMoveParams>(_SpotMoveParams_QNAME, SpotMoveParams.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FilterDateTime }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes",
                    name = "FilterDateTime")
    public JAXBElement<FilterDateTime> createFilterDateTime(FilterDateTime value) {
        return new JAXBElement<FilterDateTime>(_FilterDateTime_QNAME, FilterDateTime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotStatus }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes",
                    name = "SpotStatus")
    public JAXBElement<SpotStatus> createSpotStatus(SpotStatus value) {
        return new JAXBElement<SpotStatus>(_SpotStatus_QNAME, SpotStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotListFilter }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotListFilter")
    public JAXBElement<SpotListFilter> createSpotListFilter(SpotListFilter value) {
        return new JAXBElement<SpotListFilter>(_SpotListFilter_QNAME, SpotListFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Discount }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "Discount")
    public JAXBElement<Discount> createDiscount(Discount value) {
        return new JAXBElement<Discount>(_Discount_QNAME, Discount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfOverrides }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "ArrayOfOverrides")
    public JAXBElement<ArrayOfOverrides> createArrayOfOverrides(ArrayOfOverrides value) {
        return new JAXBElement<ArrayOfOverrides>(_ArrayOfOverrides_QNAME, ArrayOfOverrides.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotSequence }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "ArrayOfSpotSequence")
    public JAXBElement<ArrayOfSpotSequence> createArrayOfSpotSequence(ArrayOfSpotSequence value) {
        return new JAXBElement<ArrayOfSpotSequence>(_ArrayOfSpotSequence_QNAME, ArrayOfSpotSequence.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotBookParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotBookParams")
    public JAXBElement<SpotBookParams> createSpotBookParams(SpotBookParams value) {
        return new JAXBElement<SpotBookParams>(_SpotBookParams_QNAME, SpotBookParams.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFilterDateTime }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes",
                    name = "ArrayOfFilterDateTime")
    public JAXBElement<ArrayOfFilterDateTime> createArrayOfFilterDateTime(ArrayOfFilterDateTime value) {
        return new JAXBElement<ArrayOfFilterDateTime>(_ArrayOfFilterDateTime_QNAME, ArrayOfFilterDateTime.class, null,
                                                      value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPart }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "MultiPart")
    public JAXBElement<MultiPart> createMultiPart(MultiPart value) {
        return new JAXBElement<MultiPart>(_MultiPart_QNAME, MultiPart.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "DiscountCode", scope = Discount.class)
    public JAXBElement<String> createDiscountDiscountCode(String value) {
        return new JAXBElement<String>(_DiscountDiscountCode_QNAME, String.class, Discount.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMultiPart }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "MultiParts", scope = SpotAmendParams.class)
    public JAXBElement<ArrayOfMultiPart> createSpotAmendParamsMultiParts(ArrayOfMultiPart value) {
        return new JAXBElement<ArrayOfMultiPart>(_SpotAmendParamsMultiParts_QNAME, ArrayOfMultiPart.class,
                                                 SpotAmendParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "PromoSpotLink", scope = SpotAmendParams.class)
    public JAXBElement<String> createSpotAmendParamsPromoSpotLink(String value) {
        return new JAXBElement<String>(_SpotAmendParamsPromoSpotLink_QNAME, String.class, SpotAmendParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRegionalExclusion }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "RegionalExclusions", scope = SpotAmendParams.class)
    public JAXBElement<ArrayOfRegionalExclusion> createSpotAmendParamsRegionalExclusions(ArrayOfRegionalExclusion value) {
        return new JAXBElement<ArrayOfRegionalExclusion>(_SpotAmendParamsRegionalExclusions_QNAME,
                                                         ArrayOfRegionalExclusion.class, SpotAmendParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfOverrides }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "Overrides",
                    scope = SpotAmendParams.class)
    public JAXBElement<ArrayOfOverrides> createSpotAmendParamsOverrides(ArrayOfOverrides value) {
        return new JAXBElement<ArrayOfOverrides>(_SpotAmendParamsOverrides_QNAME, ArrayOfOverrides.class,
                                                 SpotAmendParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "SAPNumber",
                    scope = SpotAmendParams.class)
    public JAXBElement<Double> createSpotAmendParamsSAPNumber(Double value) {
        return new JAXBElement<Double>(_SpotAmendParamsSAPNumber_QNAME, Double.class, SpotAmendParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "BreakCategoryCode", scope = SpotAmendParams.class)
    public JAXBElement<String> createSpotAmendParamsBreakCategoryCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsBreakCategoryCode_QNAME, String.class, SpotAmendParams.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "DealType",
                    scope = SpotAmendParams.class)
    public JAXBElement<String> createSpotAmendParamsDealType(String value) {
        return new JAXBElement<String>(_SpotAmendParamsDealType_QNAME, String.class, SpotAmendParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "BreakTypeCode", scope = SpotAmendParams.class)
    public JAXBElement<String> createSpotAmendParamsBreakTypeCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsBreakTypeCode_QNAME, String.class, SpotAmendParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "MobilityRuleCode", scope = SpotAmendParams.class)
    public JAXBElement<String> createSpotAmendParamsMobilityRuleCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsMobilityRuleCode_QNAME, String.class, SpotAmendParams.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDiscount }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "Discounts",
                    scope = SpotAmendParams.class)
    public JAXBElement<ArrayOfDiscount> createSpotAmendParamsDiscounts(ArrayOfDiscount value) {
        return new JAXBElement<ArrayOfDiscount>(_SpotAmendParamsDiscounts_QNAME, ArrayOfDiscount.class,
                                                SpotAmendParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "PositionCode", scope = SpotAmendParams.class)
    public JAXBElement<String> createSpotAmendParamsPositionCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsPositionCode_QNAME, String.class, SpotAmendParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "CurrencyCode", scope = SpotAmendParams.class)
    public JAXBElement<String> createSpotAmendParamsCurrencyCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsCurrencyCode_QNAME, String.class, SpotAmendParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "OrganisationCode", scope = SpotAmendParams.class)
    public JAXBElement<String> createSpotAmendParamsOrganisationCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsOrganisationCode_QNAME, String.class, SpotAmendParams.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotTypeCode", scope = SpotAmendParams.class)
    public JAXBElement<String> createSpotAmendParamsSpotTypeCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsSpotTypeCode_QNAME, String.class, SpotAmendParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "ReasonCode", scope = SpotAmendParams.class)
    public JAXBElement<String> createSpotAmendParamsReasonCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsReasonCode_QNAME, String.class, SpotAmendParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "PositionCode", scope = SpotSwapParams.class)
    public JAXBElement<String> createSpotSwapParamsPositionCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsPositionCode_QNAME, String.class, SpotSwapParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfOverrides }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "Overrides",
                    scope = SpotSwapParams.class)
    public JAXBElement<ArrayOfOverrides> createSpotSwapParamsOverrides(ArrayOfOverrides value) {
        return new JAXBElement<ArrayOfOverrides>(_SpotAmendParamsOverrides_QNAME, ArrayOfOverrides.class,
                                                 SpotSwapParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "OrganisationCode", scope = SpotSwapParams.class)
    public JAXBElement<String> createSpotSwapParamsOrganisationCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsOrganisationCode_QNAME, String.class, SpotSwapParams.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "SpotTypeCode", scope = MultiPart.class)
    public JAXBElement<String> createMultiPartSpotTypeCode(String value) {
        return new JAXBElement<String>(_MultiPartSpotTypeCode_QNAME, String.class, MultiPart.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", name = "Price",
                    scope = MultiPart.class)
    public JAXBElement<Double> createMultiPartPrice(Double value) {
        return new JAXBElement<Double>(_MultiPartPrice_QNAME, Double.class, MultiPart.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotSalesAreaCode", scope = SpotMoveParams.class)
    public JAXBElement<String> createSpotMoveParamsSpotSalesAreaCode(String value) {
        return new JAXBElement<String>(_SpotMoveParamsSpotSalesAreaCode_QNAME, String.class, SpotMoveParams.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "BreakNominalTime", scope = SpotMoveParams.class)
    public JAXBElement<Integer> createSpotMoveParamsBreakNominalTime(Integer value) {
        return new JAXBElement<Integer>(_SpotMoveParamsBreakNominalTime_QNAME, Integer.class, SpotMoveParams.class,
                                        value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotStatus", scope = SpotMoveParams.class)
    public JAXBElement<String> createSpotMoveParamsSpotStatus(String value) {
        return new JAXBElement<String>(_SpotMoveParamsSpotStatus_QNAME, String.class, SpotMoveParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMultiPart }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "MultiParts", scope = SpotMoveParams.class)
    public JAXBElement<ArrayOfMultiPart> createSpotMoveParamsMultiParts(ArrayOfMultiPart value) {
        return new JAXBElement<ArrayOfMultiPart>(_SpotAmendParamsMultiParts_QNAME, ArrayOfMultiPart.class,
                                                 SpotMoveParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "BreakScheduledDate", scope = SpotMoveParams.class)
    public JAXBElement<XMLGregorianCalendar> createSpotMoveParamsBreakScheduledDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SpotMoveParamsBreakScheduledDate_QNAME,
                                                     XMLGregorianCalendar.class, SpotMoveParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "BreakSalesAreaCode", scope = SpotMoveParams.class)
    public JAXBElement<String> createSpotMoveParamsBreakSalesAreaCode(String value) {
        return new JAXBElement<String>(_SpotMoveParamsBreakSalesAreaCode_QNAME, String.class, SpotMoveParams.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "BreakNumber", scope = SpotMoveParams.class)
    public JAXBElement<Integer> createSpotMoveParamsBreakNumber(Integer value) {
        return new JAXBElement<Integer>(_SpotMoveParamsBreakNumber_QNAME, Integer.class, SpotMoveParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "PositionCode", scope = SpotMoveParams.class)
    public JAXBElement<String> createSpotMoveParamsPositionCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsPositionCode_QNAME, String.class, SpotMoveParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfOverrides }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "Overrides",
                    scope = SpotMoveParams.class)
    public JAXBElement<ArrayOfOverrides> createSpotMoveParamsOverrides(ArrayOfOverrides value) {
        return new JAXBElement<ArrayOfOverrides>(_SpotAmendParamsOverrides_QNAME, ArrayOfOverrides.class,
                                                 SpotMoveParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "OrganisationCode", scope = SpotMoveParams.class)
    public JAXBElement<String> createSpotMoveParamsOrganisationCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsOrganisationCode_QNAME, String.class, SpotMoveParams.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotDuration", scope = SpotMoveParams.class)
    public JAXBElement<Integer> createSpotMoveParamsSpotDuration(Integer value) {
        return new JAXBElement<Integer>(_SpotMoveParamsSpotDuration_QNAME, Integer.class, SpotMoveParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "ExclYN", scope = RegionalExclusion.class)
    public JAXBElement<String> createRegionalExclusionExclYN(String value) {
        return new JAXBElement<String>(_RegionalExclusionExclYN_QNAME, String.class, RegionalExclusion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "PositionCode", scope = SpotCancelParams.class)
    public JAXBElement<String> createSpotCancelParamsPositionCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsPositionCode_QNAME, String.class, SpotCancelParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfOverrides }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "Overrides",
                    scope = SpotCancelParams.class)
    public JAXBElement<ArrayOfOverrides> createSpotCancelParamsOverrides(ArrayOfOverrides value) {
        return new JAXBElement<ArrayOfOverrides>(_SpotAmendParamsOverrides_QNAME, ArrayOfOverrides.class,
                                                 SpotCancelParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "OrganisationCode", scope = SpotCancelParams.class)
    public JAXBElement<String> createSpotCancelParamsOrganisationCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsOrganisationCode_QNAME, String.class, SpotCancelParams.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "ReasonCode", scope = SpotCancelParams.class)
    public JAXBElement<String> createSpotCancelParamsReasonCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsReasonCode_QNAME, String.class, SpotCancelParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "ClashCodes", scope = SpotListFilter.class)
    public JAXBElement<ArrayOfstring> createSpotListFilterClashCodes(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_SpotListFilterClashCodes_QNAME, ArrayOfstring.class,
                                              SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "CallerOrganisationCode", scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterCallerOrganisationCode(String value) {
        return new JAXBElement<String>(_SpotListFilterCallerOrganisationCode_QNAME, String.class, SpotListFilter.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotStatuses", scope = SpotListFilter.class)
    public JAXBElement<ArrayOfint> createSpotListFilterSpotStatuses(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_SpotListFilterSpotStatuses_QNAME, ArrayOfint.class, SpotListFilter.class,
                                           value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "CallerPositionCode", scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterCallerPositionCode(String value) {
        return new JAXBElement<String>(_SpotListFilterCallerPositionCode_QNAME, String.class, SpotListFilter.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "Currency",
                    scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterCurrency(String value) {
        return new JAXBElement<String>(_SpotListFilterCurrency_QNAME, String.class, SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "BreakType",
                    scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterBreakType(String value) {
        return new JAXBElement<String>(_SpotListFilterBreakType_QNAME, String.class, SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "BusinessTypes", scope = SpotListFilter.class)
    public JAXBElement<ArrayOfint> createSpotListFilterBusinessTypes(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_SpotListFilterBusinessTypes_QNAME, ArrayOfint.class, SpotListFilter.class,
                                           value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "Deals",
                    scope = SpotListFilter.class)
    public JAXBElement<ArrayOfint> createSpotListFilterDeals(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_SpotListFilterDeals_QNAME, ArrayOfint.class, SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "PositionInProgramme", scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterPositionInProgramme(String value) {
        return new JAXBElement<String>(_SpotListFilterPositionInProgramme_QNAME, String.class, SpotListFilter.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "Campaigns",
                    scope = SpotListFilter.class)
    public JAXBElement<ArrayOfint> createSpotListFilterCampaigns(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_SpotListFilterCampaigns_QNAME, ArrayOfint.class, SpotListFilter.class,
                                           value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "AlternateSchedules", scope = SpotListFilter.class)
    public JAXBElement<ArrayOfint> createSpotListFilterAlternateSchedules(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_SpotListFilterAlternateSchedules_QNAME, ArrayOfint.class,
                                           SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "PreemptionCodes", scope = SpotListFilter.class)
    public JAXBElement<ArrayOfint> createSpotListFilterPreemptionCodes(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_SpotListFilterPreemptionCodes_QNAME, ArrayOfint.class, SpotListFilter.class,
                                           value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SalesAreas", scope = SpotListFilter.class)
    public JAXBElement<ArrayOfint> createSpotListFilterSalesAreas(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_SpotListFilterSalesAreas_QNAME, ArrayOfint.class, SpotListFilter.class,
                                           value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "DiscountCodes", scope = SpotListFilter.class)
    public JAXBElement<ArrayOfstring> createSpotListFilterDiscountCodes(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_SpotListFilterDiscountCodes_QNAME, ArrayOfstring.class,
                                              SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "ProductReportingCategory", scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterProductReportingCategory(String value) {
        return new JAXBElement<String>(_SpotListFilterProductReportingCategory_QNAME, String.class,
                                       SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "ReportingPositionCode", scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterReportingPositionCode(String value) {
        return new JAXBElement<String>(_SpotListFilterReportingPositionCode_QNAME, String.class, SpotListFilter.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "Clients",
                    scope = SpotListFilter.class)
    public JAXBElement<ArrayOfstring> createSpotListFilterClients(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_SpotListFilterClients_QNAME, ArrayOfstring.class, SpotListFilter.class,
                                              value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotLengths", scope = SpotListFilter.class)
    public JAXBElement<ArrayOfint> createSpotListFilterSpotLengths(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_SpotListFilterSpotLengths_QNAME, ArrayOfint.class, SpotListFilter.class,
                                           value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "CbacCode",
                    scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterCbacCode(String value) {
        return new JAXBElement<String>(_SpotListFilterCbacCode_QNAME, String.class, SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "ParpName",
                    scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterParpName(String value) {
        return new JAXBElement<String>(_SpotListFilterParpName_QNAME, String.class, SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "DiscountRangeEnd", scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterDiscountRangeEnd(String value) {
        return new JAXBElement<String>(_SpotListFilterDiscountRangeEnd_QNAME, String.class, SpotListFilter.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "ProductSource", scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterProductSource(String value) {
        return new JAXBElement<String>(_SpotListFilterProductSource_QNAME, String.class, SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "CbacDescription", scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterCbacDescription(String value) {
        return new JAXBElement<String>(_SpotListFilterCbacDescription_QNAME, String.class, SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "BusinessAreas", scope = SpotListFilter.class)
    public JAXBElement<ArrayOfint> createSpotListFilterBusinessAreas(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_SpotListFilterBusinessAreas_QNAME, ArrayOfint.class, SpotListFilter.class,
                                           value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "PositionCode", scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterPositionCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsPositionCode_QNAME, String.class, SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFilterDateTime }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "FilterDateTimes", scope = SpotListFilter.class)
    public JAXBElement<ArrayOfFilterDateTime> createSpotListFilterFilterDateTimes(ArrayOfFilterDateTime value) {
        return new JAXBElement<ArrayOfFilterDateTime>(_SpotListFilterFilterDateTimes_QNAME, ArrayOfFilterDateTime.class,
                                                      SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "OrganisationCode", scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterOrganisationCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsOrganisationCode_QNAME, String.class, SpotListFilter.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "DiscountRangeStart", scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterDiscountRangeStart(String value) {
        return new JAXBElement<String>(_SpotListFilterDiscountRangeStart_QNAME, String.class, SpotListFilter.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotTypeCode", scope = SpotListFilter.class)
    public JAXBElement<String> createSpotListFilterSpotTypeCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsSpotTypeCode_QNAME, String.class, SpotListFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotSequence }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotSequence", scope = SpotSequenceMaintain.class)
    public JAXBElement<ArrayOfSpotSequence> createSpotSequenceMaintainSpotSequence(ArrayOfSpotSequence value) {
        return new JAXBElement<ArrayOfSpotSequence>(_SpotSequence_QNAME, ArrayOfSpotSequence.class,
                                                    SpotSequenceMaintain.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotCancelParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotCancelParams", scope = BatchBookingParams.class)
    public JAXBElement<ArrayOfSpotCancelParams> createBatchBookingParamsSpotCancelParams(ArrayOfSpotCancelParams value) {
        return new JAXBElement<ArrayOfSpotCancelParams>(_SpotCancelParams_QNAME, ArrayOfSpotCancelParams.class,
                                                        BatchBookingParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotAmendParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotAmendParams", scope = BatchBookingParams.class)
    public JAXBElement<ArrayOfSpotAmendParams> createBatchBookingParamsSpotAmendParams(ArrayOfSpotAmendParams value) {
        return new JAXBElement<ArrayOfSpotAmendParams>(_SpotAmendParams_QNAME, ArrayOfSpotAmendParams.class,
                                                       BatchBookingParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotBookParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotBookParams", scope = BatchBookingParams.class)
    public JAXBElement<ArrayOfSpotBookParams> createBatchBookingParamsSpotBookParams(ArrayOfSpotBookParams value) {
        return new JAXBElement<ArrayOfSpotBookParams>(_SpotBookParams_QNAME, ArrayOfSpotBookParams.class,
                                                      BatchBookingParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotMoveParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotMoveParams", scope = BatchBookingParams.class)
    public JAXBElement<ArrayOfSpotMoveParams> createBatchBookingParamsSpotMoveParams(ArrayOfSpotMoveParams value) {
        return new JAXBElement<ArrayOfSpotMoveParams>(_SpotMoveParams_QNAME, ArrayOfSpotMoveParams.class,
                                                      BatchBookingParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotSwapParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotSwapParams", scope = BatchBookingParams.class)
    public JAXBElement<ArrayOfSpotSwapParams> createBatchBookingParamsSpotSwapParams(ArrayOfSpotSwapParams value) {
        return new JAXBElement<ArrayOfSpotSwapParams>(_SpotSwapParams_QNAME, ArrayOfSpotSwapParams.class,
                                                      BatchBookingParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "OrgrCode",
                    scope = BatchBookingParams.class)
    public JAXBElement<String> createBatchBookingParamsOrgrCode(String value) {
        return new JAXBElement<String>(_BatchBookingParamsOrgrCode_QNAME, String.class, BatchBookingParams.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "PosnCode",
                    scope = BatchBookingParams.class)
    public JAXBElement<String> createBatchBookingParamsPosnCode(String value) {
        return new JAXBElement<String>(_BatchBookingParamsPosnCode_QNAME, String.class, BatchBookingParams.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "MsgText", scope = Overrides.class)
    public JAXBElement<String> createOverridesMsgText(String value) {
        return new JAXBElement<String>(_OverridesMsgText_QNAME, String.class, Overrides.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
                    name = "ExtraInfo", scope = Overrides.class)
    public JAXBElement<String> createOverridesExtraInfo(String value) {
        return new JAXBElement<String>(_OverridesExtraInfo_QNAME, String.class, Overrides.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMultiPart }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "MultiParts", scope = SpotBookParams.class)
    public JAXBElement<ArrayOfMultiPart> createSpotBookParamsMultiParts(ArrayOfMultiPart value) {
        return new JAXBElement<ArrayOfMultiPart>(_SpotAmendParamsMultiParts_QNAME, ArrayOfMultiPart.class,
                                                 SpotBookParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "PromoSpotLink", scope = SpotBookParams.class)
    public JAXBElement<String> createSpotBookParamsPromoSpotLink(String value) {
        return new JAXBElement<String>(_SpotAmendParamsPromoSpotLink_QNAME, String.class, SpotBookParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRegionalExclusion }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "RegionalExclusions", scope = SpotBookParams.class)
    public JAXBElement<ArrayOfRegionalExclusion> createSpotBookParamsRegionalExclusions(ArrayOfRegionalExclusion value) {
        return new JAXBElement<ArrayOfRegionalExclusion>(_SpotAmendParamsRegionalExclusions_QNAME,
                                                         ArrayOfRegionalExclusion.class, SpotBookParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfOverrides }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "Overrides",
                    scope = SpotBookParams.class)
    public JAXBElement<ArrayOfOverrides> createSpotBookParamsOverrides(ArrayOfOverrides value) {
        return new JAXBElement<ArrayOfOverrides>(_SpotAmendParamsOverrides_QNAME, ArrayOfOverrides.class,
                                                 SpotBookParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "BreakCategoryCode", scope = SpotBookParams.class)
    public JAXBElement<String> createSpotBookParamsBreakCategoryCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsBreakCategoryCode_QNAME, String.class, SpotBookParams.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "DealType",
                    scope = SpotBookParams.class)
    public JAXBElement<String> createSpotBookParamsDealType(String value) {
        return new JAXBElement<String>(_SpotAmendParamsDealType_QNAME, String.class, SpotBookParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "BreakTypeCode", scope = SpotBookParams.class)
    public JAXBElement<String> createSpotBookParamsBreakTypeCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsBreakTypeCode_QNAME, String.class, SpotBookParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "MobilityRuleCode", scope = SpotBookParams.class)
    public JAXBElement<String> createSpotBookParamsMobilityRuleCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsMobilityRuleCode_QNAME, String.class, SpotBookParams.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDiscount }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots", name = "Discounts",
                    scope = SpotBookParams.class)
    public JAXBElement<ArrayOfDiscount> createSpotBookParamsDiscounts(ArrayOfDiscount value) {
        return new JAXBElement<ArrayOfDiscount>(_SpotAmendParamsDiscounts_QNAME, ArrayOfDiscount.class,
                                                SpotBookParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "PositionCode", scope = SpotBookParams.class)
    public JAXBElement<String> createSpotBookParamsPositionCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsPositionCode_QNAME, String.class, SpotBookParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "CurrencyCode", scope = SpotBookParams.class)
    public JAXBElement<String> createSpotBookParamsCurrencyCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsCurrencyCode_QNAME, String.class, SpotBookParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "OrganisationCode", scope = SpotBookParams.class)
    public JAXBElement<String> createSpotBookParamsOrganisationCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsOrganisationCode_QNAME, String.class, SpotBookParams.class,
                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "SpotTypeCode", scope = SpotBookParams.class)
    public JAXBElement<String> createSpotBookParamsSpotTypeCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsSpotTypeCode_QNAME, String.class, SpotBookParams.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots",
                    name = "ReasonCode", scope = SpotBookParams.class)
    public JAXBElement<String> createSpotBookParamsReasonCode(String value) {
        return new JAXBElement<String>(_SpotAmendParamsReasonCode_QNAME, String.class, SpotBookParams.class, value);
    }

}
