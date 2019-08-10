
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import org.datacontract.schemas._2004._07.landmark_classes.ArrayOfBookProcessType;
import org.datacontract.schemas._2004._07.landmark_classes.ArrayOfCopySelectionName;
import org.datacontract.schemas._2004._07.landmark_classes.ArrayOfInteractivite;
import org.datacontract.schemas._2004._07.landmark_classes.ArrayOfPositionInProgrammeName;
import org.datacontract.schemas._2004._07.landmark_classes.ArrayOfSpot;
import org.datacontract.schemas._2004._07.landmark_classes.ArrayOfSpotListReportType;
import org.datacontract.schemas._2004._07.landmark_classes.ArrayOfSpotStatusIncludingActive;
import org.datacontract.schemas._2004._07.landmark_classes.BookingResults;
import org.datacontract.schemas._2004._07.landmark_parameters.BatchBookingParams;
import org.datacontract.schemas._2004._07.landmark_parameters.SpotListFilter;
import org.datacontract.schemas._2004._07.landmark_parameters.SpotSequenceMaintain;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.tempuri package.
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

    private final static QName _LoadForFilterResponseLoadForFilterResult_QNAME =
        new QName("http://tempuri.org/", "LoadForFilterResult");
    private final static QName _LoadPositionInProgrammeResponseLoadPositionInProgrammeResult_QNAME =
        new QName("http://tempuri.org/", "LoadPositionInProgrammeResult");
    private final static QName _LoadSpotListReportTypesResponseLoadSpotListReportTypesResult_QNAME =
        new QName("http://tempuri.org/", "LoadSpotListReportTypesResult");
    private final static QName _LoadBookProcessTypesResponseLoadBookProcessTypesResult_QNAME =
        new QName("http://tempuri.org/", "LoadBookProcessTypesResult");
    private final static QName _BatchBookingsResponseBatchBookingsResult_QNAME =
        new QName("http://tempuri.org/", "BatchBookingsResult");
    private final static QName _LoadInteractivityResponseLoadInteractivityResult_QNAME =
        new QName("http://tempuri.org/", "LoadInteractivityResult");
    private final static QName _LoadForFilterSpotListFilter_QNAME = new QName("http://tempuri.org/", "spotListFilter");
    private final static QName _LoadForFilterCurrency_QNAME = new QName("http://tempuri.org/", "currency");
    private final static QName _BatchBookingsBatchBookings_QNAME = new QName("http://tempuri.org/", "batchBookings");
    private final static QName _SaveSpotsForBreakSequenceSpotSequenceMaintain_QNAME =
        new QName("http://tempuri.org/", "spotSequenceMaintain");
    private final static QName _SaveSpotsForBreakSequencePositionCode_QNAME =
        new QName("http://tempuri.org/", "positionCode");
    private final static QName _SaveSpotsForBreakSequenceOrganisationCode_QNAME =
        new QName("http://tempuri.org/", "organisationCode");
    private final static QName _LoadSpotStatusIncActiveResponseLoadSpotStatusIncActiveResult_QNAME =
        new QName("http://tempuri.org/", "LoadSpotStatusIncActiveResult");
    private final static QName _LoadCopySelectionResponseLoadCopySelectionResult_QNAME =
        new QName("http://tempuri.org/", "LoadCopySelectionResult");
    private final static QName _LoadForFilter2ResponseLoadForFilter2Result_QNAME =
        new QName("http://tempuri.org/", "LoadForFilter2Result");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LoadCopySelectionResponse }
     *
     */
    public LoadCopySelectionResponse createLoadCopySelectionResponse() {
        return new LoadCopySelectionResponse();
    }

    /**
     * Create an instance of {@link SaveSpotsForBreakSequence }
     *
     */
    public SaveSpotsForBreakSequence createSaveSpotsForBreakSequence() {
        return new SaveSpotsForBreakSequence();
    }

    /**
     * Create an instance of {@link LoadSpotListReportTypesResponse }
     *
     */
    public LoadSpotListReportTypesResponse createLoadSpotListReportTypesResponse() {
        return new LoadSpotListReportTypesResponse();
    }

    /**
     * Create an instance of {@link LoadPositionInProgramme }
     *
     */
    public LoadPositionInProgramme createLoadPositionInProgramme() {
        return new LoadPositionInProgramme();
    }

    /**
     * Create an instance of {@link LoadSpotStatusIncActive }
     *
     */
    public LoadSpotStatusIncActive createLoadSpotStatusIncActive() {
        return new LoadSpotStatusIncActive();
    }

    /**
     * Create an instance of {@link LoadForFilter }
     *
     */
    public LoadForFilter createLoadForFilter() {
        return new LoadForFilter();
    }

    /**
     * Create an instance of {@link BatchBookingsResponse }
     *
     */
    public BatchBookingsResponse createBatchBookingsResponse() {
        return new BatchBookingsResponse();
    }

    /**
     * Create an instance of {@link LoadInteractivity }
     *
     */
    public LoadInteractivity createLoadInteractivity() {
        return new LoadInteractivity();
    }

    /**
     * Create an instance of {@link LoadForFilterResponse }
     *
     */
    public LoadForFilterResponse createLoadForFilterResponse() {
        return new LoadForFilterResponse();
    }

    /**
     * Create an instance of {@link LoadBookProcessTypes }
     *
     */
    public LoadBookProcessTypes createLoadBookProcessTypes() {
        return new LoadBookProcessTypes();
    }

    /**
     * Create an instance of {@link LoadCopySelection }
     *
     */
    public LoadCopySelection createLoadCopySelection() {
        return new LoadCopySelection();
    }

    /**
     * Create an instance of {@link LoadPositionInProgrammeResponse }
     *
     */
    public LoadPositionInProgrammeResponse createLoadPositionInProgrammeResponse() {
        return new LoadPositionInProgrammeResponse();
    }

    /**
     * Create an instance of {@link BatchBookings }
     *
     */
    public BatchBookings createBatchBookings() {
        return new BatchBookings();
    }

    /**
     * Create an instance of {@link LoadInteractivityResponse }
     *
     */
    public LoadInteractivityResponse createLoadInteractivityResponse() {
        return new LoadInteractivityResponse();
    }

    /**
     * Create an instance of {@link LoadSpotStatusIncActiveResponse }
     *
     */
    public LoadSpotStatusIncActiveResponse createLoadSpotStatusIncActiveResponse() {
        return new LoadSpotStatusIncActiveResponse();
    }

    /**
     * Create an instance of {@link LoadBookProcessTypesResponse }
     *
     */
    public LoadBookProcessTypesResponse createLoadBookProcessTypesResponse() {
        return new LoadBookProcessTypesResponse();
    }

    /**
     * Create an instance of {@link LoadForFilter2 }
     *
     */
    public LoadForFilter2 createLoadForFilter2() {
        return new LoadForFilter2();
    }

    /**
     * Create an instance of {@link LoadForFilter2Response }
     *
     */
    public LoadForFilter2Response createLoadForFilter2Response() {
        return new LoadForFilter2Response();
    }

    /**
     * Create an instance of {@link SaveSpotsForBreakSequenceResponse }
     *
     */
    public SaveSpotsForBreakSequenceResponse createSaveSpotsForBreakSequenceResponse() {
        return new SaveSpotsForBreakSequenceResponse();
    }

    /**
     * Create an instance of {@link LoadSpotListReportTypes }
     *
     */
    public LoadSpotListReportTypes createLoadSpotListReportTypes() {
        return new LoadSpotListReportTypes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpot }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "LoadForFilterResult",
                    scope = LoadForFilterResponse.class)
    public JAXBElement<ArrayOfSpot> createLoadForFilterResponseLoadForFilterResult(ArrayOfSpot value) {
        return new JAXBElement<ArrayOfSpot>(_LoadForFilterResponseLoadForFilterResult_QNAME, ArrayOfSpot.class,
                                            LoadForFilterResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPositionInProgrammeName }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "LoadPositionInProgrammeResult",
                    scope = LoadPositionInProgrammeResponse.class)
    public JAXBElement<ArrayOfPositionInProgrammeName> createLoadPositionInProgrammeResponseLoadPositionInProgrammeResult(ArrayOfPositionInProgrammeName value) {
        return new JAXBElement<ArrayOfPositionInProgrammeName>(_LoadPositionInProgrammeResponseLoadPositionInProgrammeResult_QNAME,
                                                               ArrayOfPositionInProgrammeName.class,
                                                               LoadPositionInProgrammeResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotListReportType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "LoadSpotListReportTypesResult",
                    scope = LoadSpotListReportTypesResponse.class)
    public JAXBElement<ArrayOfSpotListReportType> createLoadSpotListReportTypesResponseLoadSpotListReportTypesResult(ArrayOfSpotListReportType value) {
        return new JAXBElement<ArrayOfSpotListReportType>(_LoadSpotListReportTypesResponseLoadSpotListReportTypesResult_QNAME,
                                                          ArrayOfSpotListReportType.class,
                                                          LoadSpotListReportTypesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBookProcessType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "LoadBookProcessTypesResult",
                    scope = LoadBookProcessTypesResponse.class)
    public JAXBElement<ArrayOfBookProcessType> createLoadBookProcessTypesResponseLoadBookProcessTypesResult(ArrayOfBookProcessType value) {
        return new JAXBElement<ArrayOfBookProcessType>(_LoadBookProcessTypesResponseLoadBookProcessTypesResult_QNAME,
                                                       ArrayOfBookProcessType.class, LoadBookProcessTypesResponse.class,
                                                       value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookingResults }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "BatchBookingsResult",
                    scope = BatchBookingsResponse.class)
    public JAXBElement<BookingResults> createBatchBookingsResponseBatchBookingsResult(BookingResults value) {
        return new JAXBElement<BookingResults>(_BatchBookingsResponseBatchBookingsResult_QNAME, BookingResults.class,
                                               BatchBookingsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfInteractivite }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "LoadInteractivityResult",
                    scope = LoadInteractivityResponse.class)
    public JAXBElement<ArrayOfInteractivite> createLoadInteractivityResponseLoadInteractivityResult(ArrayOfInteractivite value) {
        return new JAXBElement<ArrayOfInteractivite>(_LoadInteractivityResponseLoadInteractivityResult_QNAME,
                                                     ArrayOfInteractivite.class, LoadInteractivityResponse.class,
                                                     value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotListFilter }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "spotListFilter", scope = LoadForFilter.class)
    public JAXBElement<SpotListFilter> createLoadForFilterSpotListFilter(SpotListFilter value) {
        return new JAXBElement<SpotListFilter>(_LoadForFilterSpotListFilter_QNAME, SpotListFilter.class,
                                               LoadForFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "currency", scope = LoadForFilter.class)
    public JAXBElement<String> createLoadForFilterCurrency(String value) {
        return new JAXBElement<String>(_LoadForFilterCurrency_QNAME, String.class, LoadForFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BatchBookingParams }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "batchBookings", scope = BatchBookings.class)
    public JAXBElement<BatchBookingParams> createBatchBookingsBatchBookings(BatchBookingParams value) {
        return new JAXBElement<BatchBookingParams>(_BatchBookingsBatchBookings_QNAME, BatchBookingParams.class,
                                                   BatchBookings.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotListFilter }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "spotListFilter", scope = LoadForFilter2.class)
    public JAXBElement<SpotListFilter> createLoadForFilter2SpotListFilter(SpotListFilter value) {
        return new JAXBElement<SpotListFilter>(_LoadForFilterSpotListFilter_QNAME, SpotListFilter.class,
                                               LoadForFilter2.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "currency", scope = LoadForFilter2.class)
    public JAXBElement<String> createLoadForFilter2Currency(String value) {
        return new JAXBElement<String>(_LoadForFilterCurrency_QNAME, String.class, LoadForFilter2.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotSequenceMaintain }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "spotSequenceMaintain",
                    scope = SaveSpotsForBreakSequence.class)
    public JAXBElement<SpotSequenceMaintain> createSaveSpotsForBreakSequenceSpotSequenceMaintain(SpotSequenceMaintain value) {
        return new JAXBElement<SpotSequenceMaintain>(_SaveSpotsForBreakSequenceSpotSequenceMaintain_QNAME,
                                                     SpotSequenceMaintain.class, SaveSpotsForBreakSequence.class,
                                                     value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "positionCode", scope = SaveSpotsForBreakSequence.class)
    public JAXBElement<String> createSaveSpotsForBreakSequencePositionCode(String value) {
        return new JAXBElement<String>(_SaveSpotsForBreakSequencePositionCode_QNAME, String.class,
                                       SaveSpotsForBreakSequence.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "organisationCode",
                    scope = SaveSpotsForBreakSequence.class)
    public JAXBElement<String> createSaveSpotsForBreakSequenceOrganisationCode(String value) {
        return new JAXBElement<String>(_SaveSpotsForBreakSequenceOrganisationCode_QNAME, String.class,
                                       SaveSpotsForBreakSequence.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpotStatusIncludingActive }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "LoadSpotStatusIncActiveResult",
                    scope = LoadSpotStatusIncActiveResponse.class)
    public JAXBElement<ArrayOfSpotStatusIncludingActive> createLoadSpotStatusIncActiveResponseLoadSpotStatusIncActiveResult(ArrayOfSpotStatusIncludingActive value) {
        return new JAXBElement<ArrayOfSpotStatusIncludingActive>(_LoadSpotStatusIncActiveResponseLoadSpotStatusIncActiveResult_QNAME,
                                                                 ArrayOfSpotStatusIncludingActive.class,
                                                                 LoadSpotStatusIncActiveResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCopySelectionName }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "LoadCopySelectionResult",
                    scope = LoadCopySelectionResponse.class)
    public JAXBElement<ArrayOfCopySelectionName> createLoadCopySelectionResponseLoadCopySelectionResult(ArrayOfCopySelectionName value) {
        return new JAXBElement<ArrayOfCopySelectionName>(_LoadCopySelectionResponseLoadCopySelectionResult_QNAME,
                                                         ArrayOfCopySelectionName.class,
                                                         LoadCopySelectionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpot }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "LoadForFilter2Result",
                    scope = LoadForFilter2Response.class)
    public JAXBElement<ArrayOfSpot> createLoadForFilter2ResponseLoadForFilter2Result(ArrayOfSpot value) {
        return new JAXBElement<ArrayOfSpot>(_LoadForFilter2ResponseLoadForFilter2Result_QNAME, ArrayOfSpot.class,
                                            LoadForFilter2Response.class, value);
    }

}
