
package org.datacontract.schemas._2004._07.landmark_classes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Spot complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Spot">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccountItem" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ActPSDPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="AdvertiserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AgencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AltEndTime1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="AltEndTime2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="AltStartTime1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="AltStartTime2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="AlternateSchedule" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="AlternateScheduleType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ApprovedStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AspectRatioCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AudienceValue" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="AutoAllocYn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AutoAllocate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Availability" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BacktoBackMultiPart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="BaseBreakPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="BaseClientBreakPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="BasePSDPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="BaseStartingPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Bonus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="BookedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="BookedOrganisation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BookedPersCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BookedPosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BreakCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BreakNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BreakPosition" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BreakSalesAreaNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BreakType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BroadcasterNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BusinessArea" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BusinessType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CBACCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CPP" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="CPPL" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="CPT" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="CPTL" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Campaign" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CampaignEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="CampaignStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="CampaignStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CampaignStopBooking" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CancellationCharge" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="CancellationReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClashCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClientBreakPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ClientPicked" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CloneSpot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContentText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ContentTextCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CopyClearanceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CopyCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CopyComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CopyFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CopyNarrative" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CopyNativeComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CopyReceivedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="CopyViewed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreditApp" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DCRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DCTakeUp" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DaypartDaysofWeek" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DaypartEndTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DaypartName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DaypartStartTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Deal" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DemographicNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Efficiency" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="EndTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Enhanced" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EpisNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Episode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EpisodeNativeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalBreakId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FalseABIDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="FeaturesinHintSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GrossLength" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="HouseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IACopyCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="IACopyComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IACopyEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="IACopyNarrative" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IACopyRestrictionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IACopyStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="IACopyType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IAHouseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IAIndustryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IAProtected" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IaCpfuNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Impacts" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ImpactsL" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="IndustryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InteractiveCopy" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="InteractiveDescriptor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InteractiveSpot" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="InteractiveType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="InvoiceAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastChangeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="LastChangedOrganisation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastChangedPosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastChangedTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Length" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="LimboRetryPending" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="LineDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LineNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="LinePrgcNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="LineProgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocationNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="LongForm" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="MadeGood" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="MakeGoodRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="MakeGoodSpotNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MakeGoodTakeUp" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="MasterSpotNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MinSpotType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mobility" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MultiPartSpot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NWRatings" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="NomianlPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="NonPreemptible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="OptIndex" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="OptLimit" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Organisation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OriginalProgrammeCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OriginalProgrammeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="OrigionalScheduledTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PSDPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Peak" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="PersonCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Position" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PositionClash" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="PositionInBreak" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Preemptee" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Preemptor" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PremiumCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PremiumDemoNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PremiumImpacts" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="PremiumRatings" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="PreviousStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrgcNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PriceFactor" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ProductClash" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ProductCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ProgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProgNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ProgramBuy" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ProgrammeLock" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ProgrammeNativeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProgrammePosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PromoSpot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProposedCampaign" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ProposedCampaignVersion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ProtectedCopy" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Ratecard" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="RatingType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ratings" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="RatingsL" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="RatingsP" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="RegExcl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegionNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ReptPrgcNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SalesSplitId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ScheduledDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ScheduledTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SecStatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SecUniverse" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="SlottingDays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SlottingEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="SlottingEndTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SlottingStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="SlottingStartTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Solus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SpotNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotSalesAreaNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="StartingPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubstitutionalAdvertisingSpot" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SuspendedCopy" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TEP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TargetSalesAreaNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="TotalNominalPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="TransmissionRegionNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="TransmittedProgramme" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransmittedTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Universe" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ViewPrice" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tba" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Spot", propOrder = {
         "accountItem", "actPSDPrice", "advertiserCode", "agencyCode", "altEndTime1", "altEndTime2", "altStartTime1",
         "altStartTime2", "alternateSchedule", "alternateScheduleType", "approvedStatus", "aspectRatioCode",
         "audienceValue", "autoAllocYn", "autoAllocate", "availability", "backtoBackMultiPart", "baseBreakPrice",
         "baseClientBreakPrice", "basePSDPrice", "baseStartingPrice", "bonus", "bookedDate", "bookedOrganisation",
         "bookedPersCode", "bookedPosition", "breakCategory", "breakNumber", "breakPosition", "breakSalesAreaNumber",
         "breakType", "broadcasterNumber", "businessArea", "businessType", "cbacCode", "cpp", "cppl", "cpt", "cptl",
         "campaign", "campaignEndDate", "campaignStartDate", "campaignStatus", "campaignStopBooking",
         "cancellationCharge", "cancellationReasonCode", "clashCode", "clientBreakPrice", "clientPicked", "cloneSpot",
         "comment", "contentText", "contentTextCode", "copyClearanceNumber", "copyCode", "copyComment", "copyFormat",
         "copyNarrative", "copyNativeComment", "copyReceivedDate", "copyViewed", "creditApp", "dcRequired", "dcTakeUp",
         "daypartDaysofWeek", "daypartEndTime", "daypartName", "daypartStartTime", "deal", "demographicNumber",
         "efficiency", "endDate", "endTime", "enhanced", "episNo", "episode", "episodeNativeName", "externalBreakId",
         "externalReference", "falseABIDate", "featuresinHintSchedule", "grossLength", "houseNumber", "iaCopyCode",
         "iaCopyComment", "iaCopyEndDate", "iaCopyNarrative", "iaCopyRestrictionCode", "iaCopyStartDate", "iaCopyType",
         "iaHouseNumber", "iaIndustryCode", "iaProtected", "iaCpfuNo", "impacts", "impactsL", "industryCode",
         "interactiveCopy", "interactiveDescriptor", "interactiveSpot", "interactiveType", "invoiceAction",
         "lastChangeDate", "lastChangedOrganisation", "lastChangedPosition", "lastChangedTime", "length",
         "limboRetryPending", "lineDescription", "lineNumber", "linePrgcNo", "lineProgName", "locationNo", "longForm",
         "madeGood", "makeGoodRequired", "makeGoodSpotNo", "makeGoodTakeUp", "masterSpotNumber", "minSpotType",
         "mobility", "multiPartSpot", "nwRatings", "nomianlPrice", "nonPreemptible", "optIndex", "optLimit",
         "organisation", "originalProgrammeCategory", "originalProgrammeDate", "origionalScheduledTime", "psdPrice",
         "peak", "personCode", "position", "positionClash", "positionInBreak", "preemptee", "preemptor",
         "premiumCategory", "premiumDemoNo", "premiumImpacts", "premiumRatings", "previousStatus", "prgcNo",
         "priceFactor", "productClash", "productCode", "progName", "progNo", "programBuy", "programmeLock",
         "programmeNativeName", "programmePosition", "promoSpot", "proposedCampaign", "proposedCampaignVersion",
         "protectedCopy", "ratecard", "ratingType", "ratings", "ratingsL", "ratingsP", "regExcl", "regionNo",
         "reptPrgcNo", "salesSplitId", "scheduledDate", "scheduledTime", "secStatus", "secUniverse", "slottingDays",
         "slottingEndDate", "slottingEndTime", "slottingStartDate", "slottingStartTime", "solus", "spotNumber",
         "spotSalesAreaNumber", "spotType", "startDate", "startTime", "startingPrice", "status",
         "substitutionalAdvertisingSpot", "suspendedCopy", "tep", "targetSalesAreaNumber", "totalNominalPrice",
         "transmissionRegionNo", "transmittedProgramme", "transmittedTime", "universe", "viewPrice", "tba"
    })
public class Spot {

    @XmlElement(name = "AccountItem")
    protected Integer accountItem;
    @XmlElement(name = "ActPSDPrice")
    protected Double actPSDPrice;
    @XmlElementRef(name = "AdvertiserCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> advertiserCode;
    @XmlElementRef(name = "AgencyCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> agencyCode;
    @XmlElement(name = "AltEndTime1")
    protected Integer altEndTime1;
    @XmlElement(name = "AltEndTime2")
    protected Integer altEndTime2;
    @XmlElement(name = "AltStartTime1")
    protected Integer altStartTime1;
    @XmlElement(name = "AltStartTime2")
    protected Integer altStartTime2;
    @XmlElement(name = "AlternateSchedule")
    protected Integer alternateSchedule;
    @XmlElement(name = "AlternateScheduleType")
    protected Integer alternateScheduleType;
    @XmlElementRef(name = "ApprovedStatus",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> approvedStatus;
    @XmlElementRef(name = "AspectRatioCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> aspectRatioCode;
    @XmlElement(name = "AudienceValue")
    protected Double audienceValue;
    @XmlElement(name = "AutoAllocYn")
    protected Boolean autoAllocYn;
    @XmlElementRef(name = "AutoAllocate", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> autoAllocate;
    @XmlElement(name = "Availability")
    protected Integer availability;
    @XmlElement(name = "BacktoBackMultiPart")
    protected Boolean backtoBackMultiPart;
    @XmlElement(name = "BaseBreakPrice")
    protected Double baseBreakPrice;
    @XmlElement(name = "BaseClientBreakPrice")
    protected Double baseClientBreakPrice;
    @XmlElement(name = "BasePSDPrice")
    protected Double basePSDPrice;
    @XmlElement(name = "BaseStartingPrice")
    protected Double baseStartingPrice;
    @XmlElement(name = "Bonus")
    protected Boolean bonus;
    @XmlElement(name = "BookedDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar bookedDate;
    @XmlElementRef(name = "BookedOrganisation",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> bookedOrganisation;
    @XmlElement(name = "BookedPersCode")
    protected Integer bookedPersCode;
    @XmlElementRef(name = "BookedPosition",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> bookedPosition;
    @XmlElementRef(name = "BreakCategory", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> breakCategory;
    @XmlElement(name = "BreakNumber")
    protected Integer breakNumber;
    @XmlElement(name = "BreakPosition")
    protected Integer breakPosition;
    @XmlElement(name = "BreakSalesAreaNumber")
    protected Integer breakSalesAreaNumber;
    @XmlElementRef(name = "BreakType", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> breakType;
    @XmlElement(name = "BroadcasterNumber")
    protected Integer broadcasterNumber;
    @XmlElement(name = "BusinessArea")
    protected Integer businessArea;
    @XmlElementRef(name = "BusinessType", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> businessType;
    @XmlElementRef(name = "CBACCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> cbacCode;
    @XmlElement(name = "CPP")
    protected Double cpp;
    @XmlElement(name = "CPPL")
    protected Double cppl;
    @XmlElement(name = "CPT")
    protected Double cpt;
    @XmlElement(name = "CPTL")
    protected Double cptl;
    @XmlElement(name = "Campaign")
    protected Integer campaign;
    @XmlElement(name = "CampaignEndDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar campaignEndDate;
    @XmlElement(name = "CampaignStartDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar campaignStartDate;
    @XmlElementRef(name = "CampaignStatus",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> campaignStatus;
    @XmlElement(name = "CampaignStopBooking")
    protected Boolean campaignStopBooking;
    @XmlElement(name = "CancellationCharge")
    protected Double cancellationCharge;
    @XmlElementRef(name = "CancellationReasonCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> cancellationReasonCode;
    @XmlElementRef(name = "ClashCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> clashCode;
    @XmlElement(name = "ClientBreakPrice")
    protected Double clientBreakPrice;
    @XmlElement(name = "ClientPicked")
    protected Boolean clientPicked;
    @XmlElementRef(name = "CloneSpot", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> cloneSpot;
    @XmlElementRef(name = "Comment", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> comment;
    @XmlElementRef(name = "ContentText", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> contentText;
    @XmlElementRef(name = "ContentTextCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> contentTextCode;
    @XmlElementRef(name = "CopyClearanceNumber",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> copyClearanceNumber;
    @XmlElement(name = "CopyCode")
    protected Integer copyCode;
    @XmlElementRef(name = "CopyComment", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> copyComment;
    @XmlElementRef(name = "CopyFormat", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> copyFormat;
    @XmlElementRef(name = "CopyNarrative", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> copyNarrative;
    @XmlElementRef(name = "CopyNativeComment",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> copyNativeComment;
    @XmlElement(name = "CopyReceivedDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar copyReceivedDate;
    @XmlElementRef(name = "CopyViewed", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> copyViewed;
    @XmlElement(name = "CreditApp")
    protected Boolean creditApp;
    @XmlElement(name = "DCRequired")
    protected Boolean dcRequired;
    @XmlElement(name = "DCTakeUp")
    protected Boolean dcTakeUp;
    @XmlElementRef(name = "DaypartDaysofWeek",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> daypartDaysofWeek;
    @XmlElement(name = "DaypartEndTime")
    protected Integer daypartEndTime;
    @XmlElementRef(name = "DaypartName", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> daypartName;
    @XmlElement(name = "DaypartStartTime")
    protected Integer daypartStartTime;
    @XmlElement(name = "Deal")
    protected Integer deal;
    @XmlElement(name = "DemographicNumber")
    protected Integer demographicNumber;
    @XmlElement(name = "Efficiency")
    protected Double efficiency;
    @XmlElement(name = "EndDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "EndTime")
    protected Integer endTime;
    @XmlElement(name = "Enhanced")
    protected Boolean enhanced;
    @XmlElement(name = "EpisNo")
    protected Integer episNo;
    @XmlElementRef(name = "Episode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> episode;
    @XmlElementRef(name = "EpisodeNativeName",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> episodeNativeName;
    @XmlElementRef(name = "ExternalBreakId",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> externalBreakId;
    @XmlElementRef(name = "ExternalReference",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> externalReference;
    @XmlElement(name = "FalseABIDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar falseABIDate;
    @XmlElementRef(name = "FeaturesinHintSchedule",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> featuresinHintSchedule;
    @XmlElement(name = "GrossLength")
    protected Integer grossLength;
    @XmlElementRef(name = "HouseNumber", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> houseNumber;
    @XmlElement(name = "IACopyCode")
    protected Integer iaCopyCode;
    @XmlElementRef(name = "IACopyComment", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> iaCopyComment;
    @XmlElement(name = "IACopyEndDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar iaCopyEndDate;
    @XmlElementRef(name = "IACopyNarrative",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> iaCopyNarrative;
    @XmlElementRef(name = "IACopyRestrictionCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> iaCopyRestrictionCode;
    @XmlElement(name = "IACopyStartDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar iaCopyStartDate;
    @XmlElementRef(name = "IACopyType", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> iaCopyType;
    @XmlElementRef(name = "IAHouseNumber", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> iaHouseNumber;
    @XmlElementRef(name = "IAIndustryCode",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> iaIndustryCode;
    @XmlElement(name = "IAProtected")
    protected Boolean iaProtected;
    @XmlElement(name = "IaCpfuNo")
    protected Integer iaCpfuNo;
    @XmlElement(name = "Impacts")
    protected Double impacts;
    @XmlElement(name = "ImpactsL")
    protected Double impactsL;
    @XmlElementRef(name = "IndustryCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> industryCode;
    @XmlElement(name = "InteractiveCopy")
    protected Integer interactiveCopy;
    @XmlElementRef(name = "InteractiveDescriptor",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> interactiveDescriptor;
    @XmlElement(name = "InteractiveSpot")
    protected Boolean interactiveSpot;
    @XmlElement(name = "InteractiveType")
    protected Integer interactiveType;
    @XmlElementRef(name = "InvoiceAction", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> invoiceAction;
    @XmlElement(name = "LastChangeDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastChangeDate;
    @XmlElementRef(name = "LastChangedOrganisation",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> lastChangedOrganisation;
    @XmlElementRef(name = "LastChangedPosition",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> lastChangedPosition;
    @XmlElement(name = "LastChangedTime")
    protected Integer lastChangedTime;
    @XmlElement(name = "Length")
    protected Integer length;
    @XmlElement(name = "LimboRetryPending")
    protected Boolean limboRetryPending;
    @XmlElementRef(name = "LineDescription",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> lineDescription;
    @XmlElement(name = "LineNumber")
    protected Integer lineNumber;
    @XmlElement(name = "LinePrgcNo")
    protected Integer linePrgcNo;
    @XmlElementRef(name = "LineProgName", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> lineProgName;
    @XmlElement(name = "LocationNo")
    protected Integer locationNo;
    @XmlElement(name = "LongForm")
    protected Boolean longForm;
    @XmlElement(name = "MadeGood")
    protected Boolean madeGood;
    @XmlElement(name = "MakeGoodRequired")
    protected Boolean makeGoodRequired;
    @XmlElement(name = "MakeGoodSpotNo")
    protected Integer makeGoodSpotNo;
    @XmlElement(name = "MakeGoodTakeUp")
    protected Boolean makeGoodTakeUp;
    @XmlElement(name = "MasterSpotNumber")
    protected Integer masterSpotNumber;
    @XmlElementRef(name = "MinSpotType", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> minSpotType;
    @XmlElementRef(name = "Mobility", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> mobility;
    @XmlElementRef(name = "MultiPartSpot", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> multiPartSpot;
    @XmlElement(name = "NWRatings")
    protected Double nwRatings;
    @XmlElement(name = "NomianlPrice")
    protected Double nomianlPrice;
    @XmlElement(name = "NonPreemptible")
    protected Boolean nonPreemptible;
    @XmlElement(name = "OptIndex")
    protected Integer optIndex;
    @XmlElement(name = "OptLimit")
    protected Integer optLimit;
    @XmlElementRef(name = "Organisation", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> organisation;
    @XmlElementRef(name = "OriginalProgrammeCategory",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> originalProgrammeCategory;
    @XmlElement(name = "OriginalProgrammeDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar originalProgrammeDate;
    @XmlElement(name = "OrigionalScheduledTime")
    protected Integer origionalScheduledTime;
    @XmlElement(name = "PSDPrice")
    protected Double psdPrice;
    @XmlElement(name = "Peak")
    protected Boolean peak;
    @XmlElement(name = "PersonCode")
    protected Integer personCode;
    @XmlElementRef(name = "Position", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> position;
    @XmlElement(name = "PositionClash")
    protected Boolean positionClash;
    @XmlElement(name = "PositionInBreak")
    protected Integer positionInBreak;
    @XmlElement(name = "Preemptee")
    protected Integer preemptee;
    @XmlElement(name = "Preemptor")
    protected Integer preemptor;
    @XmlElementRef(name = "PremiumCategory",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> premiumCategory;
    @XmlElement(name = "PremiumDemoNo")
    protected Integer premiumDemoNo;
    @XmlElement(name = "PremiumImpacts")
    protected Double premiumImpacts;
    @XmlElement(name = "PremiumRatings")
    protected Double premiumRatings;
    @XmlElementRef(name = "PreviousStatus",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> previousStatus;
    @XmlElement(name = "PrgcNo")
    protected Integer prgcNo;
    @XmlElement(name = "PriceFactor")
    protected Double priceFactor;
    @XmlElement(name = "ProductClash")
    protected Boolean productClash;
    @XmlElement(name = "ProductCode")
    protected Integer productCode;
    @XmlElementRef(name = "ProgName", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> progName;
    @XmlElement(name = "ProgNo")
    protected Integer progNo;
    @XmlElement(name = "ProgramBuy")
    protected Boolean programBuy;
    @XmlElement(name = "ProgrammeLock")
    protected Boolean programmeLock;
    @XmlElementRef(name = "ProgrammeNativeName",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> programmeNativeName;
    @XmlElementRef(name = "ProgrammePosition",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> programmePosition;
    @XmlElementRef(name = "PromoSpot", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> promoSpot;
    @XmlElement(name = "ProposedCampaign")
    protected Integer proposedCampaign;
    @XmlElement(name = "ProposedCampaignVersion")
    protected Integer proposedCampaignVersion;
    @XmlElement(name = "ProtectedCopy")
    protected Boolean protectedCopy;
    @XmlElement(name = "Ratecard")
    protected Integer ratecard;
    @XmlElementRef(name = "RatingType", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> ratingType;
    @XmlElement(name = "Ratings")
    protected Double ratings;
    @XmlElement(name = "RatingsL")
    protected Double ratingsL;
    @XmlElement(name = "RatingsP")
    protected Double ratingsP;
    @XmlElementRef(name = "RegExcl", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> regExcl;
    @XmlElement(name = "RegionNo")
    protected Integer regionNo;
    @XmlElement(name = "ReptPrgcNo")
    protected Integer reptPrgcNo;
    @XmlElement(name = "SalesSplitId")
    protected Integer salesSplitId;
    @XmlElement(name = "ScheduledDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar scheduledDate;
    @XmlElement(name = "ScheduledTime")
    protected Integer scheduledTime;
    @XmlElement(name = "SecStatus")
    protected Boolean secStatus;
    @XmlElement(name = "SecUniverse")
    protected Double secUniverse;
    @XmlElementRef(name = "SlottingDays", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> slottingDays;
    @XmlElement(name = "SlottingEndDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar slottingEndDate;
    @XmlElement(name = "SlottingEndTime")
    protected Integer slottingEndTime;
    @XmlElement(name = "SlottingStartDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar slottingStartDate;
    @XmlElement(name = "SlottingStartTime")
    protected Integer slottingStartTime;
    @XmlElement(name = "Solus")
    protected Boolean solus;
    @XmlElement(name = "SpotNumber")
    protected Integer spotNumber;
    @XmlElement(name = "SpotSalesAreaNumber")
    protected Integer spotSalesAreaNumber;
    @XmlElementRef(name = "SpotType", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> spotType;
    @XmlElement(name = "StartDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "StartTime")
    protected Integer startTime;
    @XmlElement(name = "StartingPrice")
    protected Double startingPrice;
    @XmlElementRef(name = "Status", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> status;
    @XmlElement(name = "SubstitutionalAdvertisingSpot")
    protected Boolean substitutionalAdvertisingSpot;
    @XmlElement(name = "SuspendedCopy")
    protected Boolean suspendedCopy;
    @XmlElementRef(name = "TEP", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> tep;
    @XmlElement(name = "TargetSalesAreaNumber")
    protected Integer targetSalesAreaNumber;
    @XmlElement(name = "TotalNominalPrice")
    protected Double totalNominalPrice;
    @XmlElement(name = "TransmissionRegionNo")
    protected Integer transmissionRegionNo;
    @XmlElementRef(name = "TransmittedProgramme",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> transmittedProgramme;
    @XmlElement(name = "TransmittedTime")
    protected Integer transmittedTime;
    @XmlElement(name = "Universe")
    protected Integer universe;
    @XmlElement(name = "ViewPrice")
    protected Boolean viewPrice;
    protected Double tba;

    /**
     * Gets the value of the accountItem property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getAccountItem() {
        return accountItem;
    }

    /**
     * Sets the value of the accountItem property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setAccountItem(Integer value) {
        this.accountItem = value;
    }

    /**
     * Gets the value of the actPSDPrice property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getActPSDPrice() {
        return actPSDPrice;
    }

    /**
     * Sets the value of the actPSDPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setActPSDPrice(Double value) {
        this.actPSDPrice = value;
    }

    /**
     * Gets the value of the advertiserCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getAdvertiserCode() {
        return advertiserCode;
    }

    /**
     * Sets the value of the advertiserCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setAdvertiserCode(JAXBElement<String> value) {
        this.advertiserCode = value;
    }

    /**
     * Gets the value of the agencyCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getAgencyCode() {
        return agencyCode;
    }

    /**
     * Sets the value of the agencyCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setAgencyCode(JAXBElement<String> value) {
        this.agencyCode = value;
    }

    /**
     * Gets the value of the altEndTime1 property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getAltEndTime1() {
        return altEndTime1;
    }

    /**
     * Sets the value of the altEndTime1 property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setAltEndTime1(Integer value) {
        this.altEndTime1 = value;
    }

    /**
     * Gets the value of the altEndTime2 property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getAltEndTime2() {
        return altEndTime2;
    }

    /**
     * Sets the value of the altEndTime2 property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setAltEndTime2(Integer value) {
        this.altEndTime2 = value;
    }

    /**
     * Gets the value of the altStartTime1 property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getAltStartTime1() {
        return altStartTime1;
    }

    /**
     * Sets the value of the altStartTime1 property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setAltStartTime1(Integer value) {
        this.altStartTime1 = value;
    }

    /**
     * Gets the value of the altStartTime2 property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getAltStartTime2() {
        return altStartTime2;
    }

    /**
     * Sets the value of the altStartTime2 property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setAltStartTime2(Integer value) {
        this.altStartTime2 = value;
    }

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
     * Gets the value of the alternateScheduleType property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getAlternateScheduleType() {
        return alternateScheduleType;
    }

    /**
     * Sets the value of the alternateScheduleType property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setAlternateScheduleType(Integer value) {
        this.alternateScheduleType = value;
    }

    /**
     * Gets the value of the approvedStatus property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getApprovedStatus() {
        return approvedStatus;
    }

    /**
     * Sets the value of the approvedStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setApprovedStatus(JAXBElement<String> value) {
        this.approvedStatus = value;
    }

    /**
     * Gets the value of the aspectRatioCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getAspectRatioCode() {
        return aspectRatioCode;
    }

    /**
     * Sets the value of the aspectRatioCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setAspectRatioCode(JAXBElement<String> value) {
        this.aspectRatioCode = value;
    }

    /**
     * Gets the value of the audienceValue property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getAudienceValue() {
        return audienceValue;
    }

    /**
     * Sets the value of the audienceValue property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setAudienceValue(Double value) {
        this.audienceValue = value;
    }

    /**
     * Gets the value of the autoAllocYn property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isAutoAllocYn() {
        return autoAllocYn;
    }

    /**
     * Sets the value of the autoAllocYn property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setAutoAllocYn(Boolean value) {
        this.autoAllocYn = value;
    }

    /**
     * Gets the value of the autoAllocate property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getAutoAllocate() {
        return autoAllocate;
    }

    /**
     * Sets the value of the autoAllocate property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setAutoAllocate(JAXBElement<String> value) {
        this.autoAllocate = value;
    }

    /**
     * Gets the value of the availability property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getAvailability() {
        return availability;
    }

    /**
     * Sets the value of the availability property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setAvailability(Integer value) {
        this.availability = value;
    }

    /**
     * Gets the value of the backtoBackMultiPart property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isBacktoBackMultiPart() {
        return backtoBackMultiPart;
    }

    /**
     * Sets the value of the backtoBackMultiPart property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setBacktoBackMultiPart(Boolean value) {
        this.backtoBackMultiPart = value;
    }

    /**
     * Gets the value of the baseBreakPrice property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getBaseBreakPrice() {
        return baseBreakPrice;
    }

    /**
     * Sets the value of the baseBreakPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setBaseBreakPrice(Double value) {
        this.baseBreakPrice = value;
    }

    /**
     * Gets the value of the baseClientBreakPrice property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getBaseClientBreakPrice() {
        return baseClientBreakPrice;
    }

    /**
     * Sets the value of the baseClientBreakPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setBaseClientBreakPrice(Double value) {
        this.baseClientBreakPrice = value;
    }

    /**
     * Gets the value of the basePSDPrice property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getBasePSDPrice() {
        return basePSDPrice;
    }

    /**
     * Sets the value of the basePSDPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setBasePSDPrice(Double value) {
        this.basePSDPrice = value;
    }

    /**
     * Gets the value of the baseStartingPrice property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getBaseStartingPrice() {
        return baseStartingPrice;
    }

    /**
     * Sets the value of the baseStartingPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setBaseStartingPrice(Double value) {
        this.baseStartingPrice = value;
    }

    /**
     * Gets the value of the bonus property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isBonus() {
        return bonus;
    }

    /**
     * Sets the value of the bonus property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setBonus(Boolean value) {
        this.bonus = value;
    }

    /**
     * Gets the value of the bookedDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getBookedDate() {
        return bookedDate;
    }

    /**
     * Sets the value of the bookedDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setBookedDate(XMLGregorianCalendar value) {
        this.bookedDate = value;
    }

    /**
     * Gets the value of the bookedOrganisation property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getBookedOrganisation() {
        return bookedOrganisation;
    }

    /**
     * Sets the value of the bookedOrganisation property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setBookedOrganisation(JAXBElement<String> value) {
        this.bookedOrganisation = value;
    }

    /**
     * Gets the value of the bookedPersCode property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBookedPersCode() {
        return bookedPersCode;
    }

    /**
     * Sets the value of the bookedPersCode property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBookedPersCode(Integer value) {
        this.bookedPersCode = value;
    }

    /**
     * Gets the value of the bookedPosition property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getBookedPosition() {
        return bookedPosition;
    }

    /**
     * Sets the value of the bookedPosition property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setBookedPosition(JAXBElement<String> value) {
        this.bookedPosition = value;
    }

    /**
     * Gets the value of the breakCategory property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getBreakCategory() {
        return breakCategory;
    }

    /**
     * Sets the value of the breakCategory property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setBreakCategory(JAXBElement<String> value) {
        this.breakCategory = value;
    }

    /**
     * Gets the value of the breakNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBreakNumber() {
        return breakNumber;
    }

    /**
     * Sets the value of the breakNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBreakNumber(Integer value) {
        this.breakNumber = value;
    }

    /**
     * Gets the value of the breakPosition property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBreakPosition() {
        return breakPosition;
    }

    /**
     * Sets the value of the breakPosition property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBreakPosition(Integer value) {
        this.breakPosition = value;
    }

    /**
     * Gets the value of the breakSalesAreaNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBreakSalesAreaNumber() {
        return breakSalesAreaNumber;
    }

    /**
     * Sets the value of the breakSalesAreaNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBreakSalesAreaNumber(Integer value) {
        this.breakSalesAreaNumber = value;
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
     * Gets the value of the broadcasterNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBroadcasterNumber() {
        return broadcasterNumber;
    }

    /**
     * Sets the value of the broadcasterNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBroadcasterNumber(Integer value) {
        this.broadcasterNumber = value;
    }

    /**
     * Gets the value of the businessArea property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBusinessArea() {
        return businessArea;
    }

    /**
     * Sets the value of the businessArea property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBusinessArea(Integer value) {
        this.businessArea = value;
    }

    /**
     * Gets the value of the businessType property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getBusinessType() {
        return businessType;
    }

    /**
     * Sets the value of the businessType property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setBusinessType(JAXBElement<String> value) {
        this.businessType = value;
    }

    /**
     * Gets the value of the cbacCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCBACCode() {
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
    public void setCBACCode(JAXBElement<String> value) {
        this.cbacCode = value;
    }

    /**
     * Gets the value of the cpp property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getCPP() {
        return cpp;
    }

    /**
     * Sets the value of the cpp property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setCPP(Double value) {
        this.cpp = value;
    }

    /**
     * Gets the value of the cppl property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getCPPL() {
        return cppl;
    }

    /**
     * Sets the value of the cppl property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setCPPL(Double value) {
        this.cppl = value;
    }

    /**
     * Gets the value of the cpt property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getCPT() {
        return cpt;
    }

    /**
     * Sets the value of the cpt property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setCPT(Double value) {
        this.cpt = value;
    }

    /**
     * Gets the value of the cptl property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getCPTL() {
        return cptl;
    }

    /**
     * Sets the value of the cptl property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setCPTL(Double value) {
        this.cptl = value;
    }

    /**
     * Gets the value of the campaign property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getCampaign() {
        return campaign;
    }

    /**
     * Sets the value of the campaign property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setCampaign(Integer value) {
        this.campaign = value;
    }

    /**
     * Gets the value of the campaignEndDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getCampaignEndDate() {
        return campaignEndDate;
    }

    /**
     * Sets the value of the campaignEndDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setCampaignEndDate(XMLGregorianCalendar value) {
        this.campaignEndDate = value;
    }

    /**
     * Gets the value of the campaignStartDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getCampaignStartDate() {
        return campaignStartDate;
    }

    /**
     * Sets the value of the campaignStartDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setCampaignStartDate(XMLGregorianCalendar value) {
        this.campaignStartDate = value;
    }

    /**
     * Gets the value of the campaignStatus property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCampaignStatus() {
        return campaignStatus;
    }

    /**
     * Sets the value of the campaignStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCampaignStatus(JAXBElement<String> value) {
        this.campaignStatus = value;
    }

    /**
     * Gets the value of the campaignStopBooking property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isCampaignStopBooking() {
        return campaignStopBooking;
    }

    /**
     * Sets the value of the campaignStopBooking property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setCampaignStopBooking(Boolean value) {
        this.campaignStopBooking = value;
    }

    /**
     * Gets the value of the cancellationCharge property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getCancellationCharge() {
        return cancellationCharge;
    }

    /**
     * Sets the value of the cancellationCharge property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setCancellationCharge(Double value) {
        this.cancellationCharge = value;
    }

    /**
     * Gets the value of the cancellationReasonCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCancellationReasonCode() {
        return cancellationReasonCode;
    }

    /**
     * Sets the value of the cancellationReasonCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCancellationReasonCode(JAXBElement<String> value) {
        this.cancellationReasonCode = value;
    }

    /**
     * Gets the value of the clashCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getClashCode() {
        return clashCode;
    }

    /**
     * Sets the value of the clashCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setClashCode(JAXBElement<String> value) {
        this.clashCode = value;
    }

    /**
     * Gets the value of the clientBreakPrice property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getClientBreakPrice() {
        return clientBreakPrice;
    }

    /**
     * Sets the value of the clientBreakPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setClientBreakPrice(Double value) {
        this.clientBreakPrice = value;
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
     * Gets the value of the cloneSpot property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCloneSpot() {
        return cloneSpot;
    }

    /**
     * Sets the value of the cloneSpot property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCloneSpot(JAXBElement<String> value) {
        this.cloneSpot = value;
    }

    /**
     * Gets the value of the comment property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setComment(JAXBElement<String> value) {
        this.comment = value;
    }

    /**
     * Gets the value of the contentText property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getContentText() {
        return contentText;
    }

    /**
     * Sets the value of the contentText property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setContentText(JAXBElement<String> value) {
        this.contentText = value;
    }

    /**
     * Gets the value of the contentTextCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getContentTextCode() {
        return contentTextCode;
    }

    /**
     * Sets the value of the contentTextCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setContentTextCode(JAXBElement<String> value) {
        this.contentTextCode = value;
    }

    /**
     * Gets the value of the copyClearanceNumber property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCopyClearanceNumber() {
        return copyClearanceNumber;
    }

    /**
     * Sets the value of the copyClearanceNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCopyClearanceNumber(JAXBElement<String> value) {
        this.copyClearanceNumber = value;
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
     * Gets the value of the copyComment property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCopyComment() {
        return copyComment;
    }

    /**
     * Sets the value of the copyComment property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCopyComment(JAXBElement<String> value) {
        this.copyComment = value;
    }

    /**
     * Gets the value of the copyFormat property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCopyFormat() {
        return copyFormat;
    }

    /**
     * Sets the value of the copyFormat property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCopyFormat(JAXBElement<String> value) {
        this.copyFormat = value;
    }

    /**
     * Gets the value of the copyNarrative property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCopyNarrative() {
        return copyNarrative;
    }

    /**
     * Sets the value of the copyNarrative property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCopyNarrative(JAXBElement<String> value) {
        this.copyNarrative = value;
    }

    /**
     * Gets the value of the copyNativeComment property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCopyNativeComment() {
        return copyNativeComment;
    }

    /**
     * Sets the value of the copyNativeComment property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCopyNativeComment(JAXBElement<String> value) {
        this.copyNativeComment = value;
    }

    /**
     * Gets the value of the copyReceivedDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getCopyReceivedDate() {
        return copyReceivedDate;
    }

    /**
     * Sets the value of the copyReceivedDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setCopyReceivedDate(XMLGregorianCalendar value) {
        this.copyReceivedDate = value;
    }

    /**
     * Gets the value of the copyViewed property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCopyViewed() {
        return copyViewed;
    }

    /**
     * Sets the value of the copyViewed property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCopyViewed(JAXBElement<String> value) {
        this.copyViewed = value;
    }

    /**
     * Gets the value of the creditApp property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isCreditApp() {
        return creditApp;
    }

    /**
     * Sets the value of the creditApp property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setCreditApp(Boolean value) {
        this.creditApp = value;
    }

    /**
     * Gets the value of the dcRequired property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isDCRequired() {
        return dcRequired;
    }

    /**
     * Sets the value of the dcRequired property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setDCRequired(Boolean value) {
        this.dcRequired = value;
    }

    /**
     * Gets the value of the dcTakeUp property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isDCTakeUp() {
        return dcTakeUp;
    }

    /**
     * Sets the value of the dcTakeUp property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setDCTakeUp(Boolean value) {
        this.dcTakeUp = value;
    }

    /**
     * Gets the value of the daypartDaysofWeek property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getDaypartDaysofWeek() {
        return daypartDaysofWeek;
    }

    /**
     * Sets the value of the daypartDaysofWeek property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setDaypartDaysofWeek(JAXBElement<String> value) {
        this.daypartDaysofWeek = value;
    }

    /**
     * Gets the value of the daypartEndTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getDaypartEndTime() {
        return daypartEndTime;
    }

    /**
     * Sets the value of the daypartEndTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setDaypartEndTime(Integer value) {
        this.daypartEndTime = value;
    }

    /**
     * Gets the value of the daypartName property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getDaypartName() {
        return daypartName;
    }

    /**
     * Sets the value of the daypartName property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setDaypartName(JAXBElement<String> value) {
        this.daypartName = value;
    }

    /**
     * Gets the value of the daypartStartTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getDaypartStartTime() {
        return daypartStartTime;
    }

    /**
     * Sets the value of the daypartStartTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setDaypartStartTime(Integer value) {
        this.daypartStartTime = value;
    }

    /**
     * Gets the value of the deal property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getDeal() {
        return deal;
    }

    /**
     * Sets the value of the deal property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setDeal(Integer value) {
        this.deal = value;
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
     * Gets the value of the efficiency property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getEfficiency() {
        return efficiency;
    }

    /**
     * Sets the value of the efficiency property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setEfficiency(Double value) {
        this.efficiency = value;
    }

    /**
     * Gets the value of the endDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the endTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setEndTime(Integer value) {
        this.endTime = value;
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
     * Gets the value of the episNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getEpisNo() {
        return episNo;
    }

    /**
     * Sets the value of the episNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setEpisNo(Integer value) {
        this.episNo = value;
    }

    /**
     * Gets the value of the episode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getEpisode() {
        return episode;
    }

    /**
     * Sets the value of the episode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setEpisode(JAXBElement<String> value) {
        this.episode = value;
    }

    /**
     * Gets the value of the episodeNativeName property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getEpisodeNativeName() {
        return episodeNativeName;
    }

    /**
     * Sets the value of the episodeNativeName property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setEpisodeNativeName(JAXBElement<String> value) {
        this.episodeNativeName = value;
    }

    /**
     * Gets the value of the externalBreakId property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getExternalBreakId() {
        return externalBreakId;
    }

    /**
     * Sets the value of the externalBreakId property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setExternalBreakId(JAXBElement<String> value) {
        this.externalBreakId = value;
    }

    /**
     * Gets the value of the externalReference property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getExternalReference() {
        return externalReference;
    }

    /**
     * Sets the value of the externalReference property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setExternalReference(JAXBElement<String> value) {
        this.externalReference = value;
    }

    /**
     * Gets the value of the falseABIDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFalseABIDate() {
        return falseABIDate;
    }

    /**
     * Sets the value of the falseABIDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setFalseABIDate(XMLGregorianCalendar value) {
        this.falseABIDate = value;
    }

    /**
     * Gets the value of the featuresinHintSchedule property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getFeaturesinHintSchedule() {
        return featuresinHintSchedule;
    }

    /**
     * Sets the value of the featuresinHintSchedule property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setFeaturesinHintSchedule(JAXBElement<String> value) {
        this.featuresinHintSchedule = value;
    }

    /**
     * Gets the value of the grossLength property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getGrossLength() {
        return grossLength;
    }

    /**
     * Sets the value of the grossLength property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setGrossLength(Integer value) {
        this.grossLength = value;
    }

    /**
     * Gets the value of the houseNumber property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the value of the houseNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setHouseNumber(JAXBElement<String> value) {
        this.houseNumber = value;
    }

    /**
     * Gets the value of the iaCopyCode property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getIACopyCode() {
        return iaCopyCode;
    }

    /**
     * Sets the value of the iaCopyCode property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setIACopyCode(Integer value) {
        this.iaCopyCode = value;
    }

    /**
     * Gets the value of the iaCopyComment property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getIACopyComment() {
        return iaCopyComment;
    }

    /**
     * Sets the value of the iaCopyComment property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setIACopyComment(JAXBElement<String> value) {
        this.iaCopyComment = value;
    }

    /**
     * Gets the value of the iaCopyEndDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getIACopyEndDate() {
        return iaCopyEndDate;
    }

    /**
     * Sets the value of the iaCopyEndDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setIACopyEndDate(XMLGregorianCalendar value) {
        this.iaCopyEndDate = value;
    }

    /**
     * Gets the value of the iaCopyNarrative property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getIACopyNarrative() {
        return iaCopyNarrative;
    }

    /**
     * Sets the value of the iaCopyNarrative property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setIACopyNarrative(JAXBElement<String> value) {
        this.iaCopyNarrative = value;
    }

    /**
     * Gets the value of the iaCopyRestrictionCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getIACopyRestrictionCode() {
        return iaCopyRestrictionCode;
    }

    /**
     * Sets the value of the iaCopyRestrictionCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setIACopyRestrictionCode(JAXBElement<String> value) {
        this.iaCopyRestrictionCode = value;
    }

    /**
     * Gets the value of the iaCopyStartDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getIACopyStartDate() {
        return iaCopyStartDate;
    }

    /**
     * Sets the value of the iaCopyStartDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setIACopyStartDate(XMLGregorianCalendar value) {
        this.iaCopyStartDate = value;
    }

    /**
     * Gets the value of the iaCopyType property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getIACopyType() {
        return iaCopyType;
    }

    /**
     * Sets the value of the iaCopyType property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setIACopyType(JAXBElement<String> value) {
        this.iaCopyType = value;
    }

    /**
     * Gets the value of the iaHouseNumber property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getIAHouseNumber() {
        return iaHouseNumber;
    }

    /**
     * Sets the value of the iaHouseNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setIAHouseNumber(JAXBElement<String> value) {
        this.iaHouseNumber = value;
    }

    /**
     * Gets the value of the iaIndustryCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getIAIndustryCode() {
        return iaIndustryCode;
    }

    /**
     * Sets the value of the iaIndustryCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setIAIndustryCode(JAXBElement<String> value) {
        this.iaIndustryCode = value;
    }

    /**
     * Gets the value of the iaProtected property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isIAProtected() {
        return iaProtected;
    }

    /**
     * Sets the value of the iaProtected property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIAProtected(Boolean value) {
        this.iaProtected = value;
    }

    /**
     * Gets the value of the iaCpfuNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getIaCpfuNo() {
        return iaCpfuNo;
    }

    /**
     * Sets the value of the iaCpfuNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setIaCpfuNo(Integer value) {
        this.iaCpfuNo = value;
    }

    /**
     * Gets the value of the impacts property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getImpacts() {
        return impacts;
    }

    /**
     * Sets the value of the impacts property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setImpacts(Double value) {
        this.impacts = value;
    }

    /**
     * Gets the value of the impactsL property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getImpactsL() {
        return impactsL;
    }

    /**
     * Sets the value of the impactsL property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setImpactsL(Double value) {
        this.impactsL = value;
    }

    /**
     * Gets the value of the industryCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getIndustryCode() {
        return industryCode;
    }

    /**
     * Sets the value of the industryCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setIndustryCode(JAXBElement<String> value) {
        this.industryCode = value;
    }

    /**
     * Gets the value of the interactiveCopy property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getInteractiveCopy() {
        return interactiveCopy;
    }

    /**
     * Sets the value of the interactiveCopy property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setInteractiveCopy(Integer value) {
        this.interactiveCopy = value;
    }

    /**
     * Gets the value of the interactiveDescriptor property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getInteractiveDescriptor() {
        return interactiveDescriptor;
    }

    /**
     * Sets the value of the interactiveDescriptor property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setInteractiveDescriptor(JAXBElement<String> value) {
        this.interactiveDescriptor = value;
    }

    /**
     * Gets the value of the interactiveSpot property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isInteractiveSpot() {
        return interactiveSpot;
    }

    /**
     * Sets the value of the interactiveSpot property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setInteractiveSpot(Boolean value) {
        this.interactiveSpot = value;
    }

    /**
     * Gets the value of the interactiveType property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getInteractiveType() {
        return interactiveType;
    }

    /**
     * Sets the value of the interactiveType property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setInteractiveType(Integer value) {
        this.interactiveType = value;
    }

    /**
     * Gets the value of the invoiceAction property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getInvoiceAction() {
        return invoiceAction;
    }

    /**
     * Sets the value of the invoiceAction property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setInvoiceAction(JAXBElement<String> value) {
        this.invoiceAction = value;
    }

    /**
     * Gets the value of the lastChangeDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getLastChangeDate() {
        return lastChangeDate;
    }

    /**
     * Sets the value of the lastChangeDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setLastChangeDate(XMLGregorianCalendar value) {
        this.lastChangeDate = value;
    }

    /**
     * Gets the value of the lastChangedOrganisation property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getLastChangedOrganisation() {
        return lastChangedOrganisation;
    }

    /**
     * Sets the value of the lastChangedOrganisation property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setLastChangedOrganisation(JAXBElement<String> value) {
        this.lastChangedOrganisation = value;
    }

    /**
     * Gets the value of the lastChangedPosition property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getLastChangedPosition() {
        return lastChangedPosition;
    }

    /**
     * Sets the value of the lastChangedPosition property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setLastChangedPosition(JAXBElement<String> value) {
        this.lastChangedPosition = value;
    }

    /**
     * Gets the value of the lastChangedTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getLastChangedTime() {
        return lastChangedTime;
    }

    /**
     * Sets the value of the lastChangedTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setLastChangedTime(Integer value) {
        this.lastChangedTime = value;
    }

    /**
     * Gets the value of the length property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setLength(Integer value) {
        this.length = value;
    }

    /**
     * Gets the value of the limboRetryPending property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isLimboRetryPending() {
        return limboRetryPending;
    }

    /**
     * Sets the value of the limboRetryPending property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setLimboRetryPending(Boolean value) {
        this.limboRetryPending = value;
    }

    /**
     * Gets the value of the lineDescription property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getLineDescription() {
        return lineDescription;
    }

    /**
     * Sets the value of the lineDescription property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setLineDescription(JAXBElement<String> value) {
        this.lineDescription = value;
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
     * Gets the value of the linePrgcNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getLinePrgcNo() {
        return linePrgcNo;
    }

    /**
     * Sets the value of the linePrgcNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setLinePrgcNo(Integer value) {
        this.linePrgcNo = value;
    }

    /**
     * Gets the value of the lineProgName property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getLineProgName() {
        return lineProgName;
    }

    /**
     * Sets the value of the lineProgName property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setLineProgName(JAXBElement<String> value) {
        this.lineProgName = value;
    }

    /**
     * Gets the value of the locationNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getLocationNo() {
        return locationNo;
    }

    /**
     * Sets the value of the locationNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setLocationNo(Integer value) {
        this.locationNo = value;
    }

    /**
     * Gets the value of the longForm property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isLongForm() {
        return longForm;
    }

    /**
     * Sets the value of the longForm property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setLongForm(Boolean value) {
        this.longForm = value;
    }

    /**
     * Gets the value of the madeGood property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isMadeGood() {
        return madeGood;
    }

    /**
     * Sets the value of the madeGood property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setMadeGood(Boolean value) {
        this.madeGood = value;
    }

    /**
     * Gets the value of the makeGoodRequired property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isMakeGoodRequired() {
        return makeGoodRequired;
    }

    /**
     * Sets the value of the makeGoodRequired property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setMakeGoodRequired(Boolean value) {
        this.makeGoodRequired = value;
    }

    /**
     * Gets the value of the makeGoodSpotNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getMakeGoodSpotNo() {
        return makeGoodSpotNo;
    }

    /**
     * Sets the value of the makeGoodSpotNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setMakeGoodSpotNo(Integer value) {
        this.makeGoodSpotNo = value;
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
     * Gets the value of the masterSpotNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getMasterSpotNumber() {
        return masterSpotNumber;
    }

    /**
     * Sets the value of the masterSpotNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setMasterSpotNumber(Integer value) {
        this.masterSpotNumber = value;
    }

    /**
     * Gets the value of the minSpotType property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getMinSpotType() {
        return minSpotType;
    }

    /**
     * Sets the value of the minSpotType property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setMinSpotType(JAXBElement<String> value) {
        this.minSpotType = value;
    }

    /**
     * Gets the value of the mobility property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getMobility() {
        return mobility;
    }

    /**
     * Sets the value of the mobility property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setMobility(JAXBElement<String> value) {
        this.mobility = value;
    }

    /**
     * Gets the value of the multiPartSpot property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getMultiPartSpot() {
        return multiPartSpot;
    }

    /**
     * Sets the value of the multiPartSpot property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setMultiPartSpot(JAXBElement<String> value) {
        this.multiPartSpot = value;
    }

    /**
     * Gets the value of the nwRatings property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getNWRatings() {
        return nwRatings;
    }

    /**
     * Sets the value of the nwRatings property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setNWRatings(Double value) {
        this.nwRatings = value;
    }

    /**
     * Gets the value of the nomianlPrice property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getNomianlPrice() {
        return nomianlPrice;
    }

    /**
     * Sets the value of the nomianlPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setNomianlPrice(Double value) {
        this.nomianlPrice = value;
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
     * Gets the value of the optIndex property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getOptIndex() {
        return optIndex;
    }

    /**
     * Sets the value of the optIndex property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setOptIndex(Integer value) {
        this.optIndex = value;
    }

    /**
     * Gets the value of the optLimit property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getOptLimit() {
        return optLimit;
    }

    /**
     * Sets the value of the optLimit property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setOptLimit(Integer value) {
        this.optLimit = value;
    }

    /**
     * Gets the value of the organisation property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getOrganisation() {
        return organisation;
    }

    /**
     * Sets the value of the organisation property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setOrganisation(JAXBElement<String> value) {
        this.organisation = value;
    }

    /**
     * Gets the value of the originalProgrammeCategory property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getOriginalProgrammeCategory() {
        return originalProgrammeCategory;
    }

    /**
     * Sets the value of the originalProgrammeCategory property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setOriginalProgrammeCategory(JAXBElement<String> value) {
        this.originalProgrammeCategory = value;
    }

    /**
     * Gets the value of the originalProgrammeDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getOriginalProgrammeDate() {
        return originalProgrammeDate;
    }

    /**
     * Sets the value of the originalProgrammeDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setOriginalProgrammeDate(XMLGregorianCalendar value) {
        this.originalProgrammeDate = value;
    }

    /**
     * Gets the value of the origionalScheduledTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getOrigionalScheduledTime() {
        return origionalScheduledTime;
    }

    /**
     * Sets the value of the origionalScheduledTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setOrigionalScheduledTime(Integer value) {
        this.origionalScheduledTime = value;
    }

    /**
     * Gets the value of the psdPrice property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getPSDPrice() {
        return psdPrice;
    }

    /**
     * Sets the value of the psdPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setPSDPrice(Double value) {
        this.psdPrice = value;
    }

    /**
     * Gets the value of the peak property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isPeak() {
        return peak;
    }

    /**
     * Sets the value of the peak property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setPeak(Boolean value) {
        this.peak = value;
    }

    /**
     * Gets the value of the personCode property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPersonCode() {
        return personCode;
    }

    /**
     * Sets the value of the personCode property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPersonCode(Integer value) {
        this.personCode = value;
    }

    /**
     * Gets the value of the position property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setPosition(JAXBElement<String> value) {
        this.position = value;
    }

    /**
     * Gets the value of the positionClash property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isPositionClash() {
        return positionClash;
    }

    /**
     * Sets the value of the positionClash property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setPositionClash(Boolean value) {
        this.positionClash = value;
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
     * Gets the value of the preemptee property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPreemptee() {
        return preemptee;
    }

    /**
     * Sets the value of the preemptee property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPreemptee(Integer value) {
        this.preemptee = value;
    }

    /**
     * Gets the value of the preemptor property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPreemptor() {
        return preemptor;
    }

    /**
     * Sets the value of the preemptor property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPreemptor(Integer value) {
        this.preemptor = value;
    }

    /**
     * Gets the value of the premiumCategory property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getPremiumCategory() {
        return premiumCategory;
    }

    /**
     * Sets the value of the premiumCategory property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setPremiumCategory(JAXBElement<String> value) {
        this.premiumCategory = value;
    }

    /**
     * Gets the value of the premiumDemoNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPremiumDemoNo() {
        return premiumDemoNo;
    }

    /**
     * Sets the value of the premiumDemoNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPremiumDemoNo(Integer value) {
        this.premiumDemoNo = value;
    }

    /**
     * Gets the value of the premiumImpacts property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getPremiumImpacts() {
        return premiumImpacts;
    }

    /**
     * Sets the value of the premiumImpacts property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setPremiumImpacts(Double value) {
        this.premiumImpacts = value;
    }

    /**
     * Gets the value of the premiumRatings property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getPremiumRatings() {
        return premiumRatings;
    }

    /**
     * Sets the value of the premiumRatings property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setPremiumRatings(Double value) {
        this.premiumRatings = value;
    }

    /**
     * Gets the value of the previousStatus property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getPreviousStatus() {
        return previousStatus;
    }

    /**
     * Sets the value of the previousStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setPreviousStatus(JAXBElement<String> value) {
        this.previousStatus = value;
    }

    /**
     * Gets the value of the prgcNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPrgcNo() {
        return prgcNo;
    }

    /**
     * Sets the value of the prgcNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPrgcNo(Integer value) {
        this.prgcNo = value;
    }

    /**
     * Gets the value of the priceFactor property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getPriceFactor() {
        return priceFactor;
    }

    /**
     * Sets the value of the priceFactor property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setPriceFactor(Double value) {
        this.priceFactor = value;
    }

    /**
     * Gets the value of the productClash property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isProductClash() {
        return productClash;
    }

    /**
     * Sets the value of the productClash property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setProductClash(Boolean value) {
        this.productClash = value;
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
     * Gets the value of the progName property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getProgName() {
        return progName;
    }

    /**
     * Sets the value of the progName property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setProgName(JAXBElement<String> value) {
        this.progName = value;
    }

    /**
     * Gets the value of the progNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getProgNo() {
        return progNo;
    }

    /**
     * Sets the value of the progNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setProgNo(Integer value) {
        this.progNo = value;
    }

    /**
     * Gets the value of the programBuy property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isProgramBuy() {
        return programBuy;
    }

    /**
     * Sets the value of the programBuy property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setProgramBuy(Boolean value) {
        this.programBuy = value;
    }

    /**
     * Gets the value of the programmeLock property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isProgrammeLock() {
        return programmeLock;
    }

    /**
     * Sets the value of the programmeLock property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setProgrammeLock(Boolean value) {
        this.programmeLock = value;
    }

    /**
     * Gets the value of the programmeNativeName property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getProgrammeNativeName() {
        return programmeNativeName;
    }

    /**
     * Sets the value of the programmeNativeName property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setProgrammeNativeName(JAXBElement<String> value) {
        this.programmeNativeName = value;
    }

    /**
     * Gets the value of the programmePosition property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getProgrammePosition() {
        return programmePosition;
    }

    /**
     * Sets the value of the programmePosition property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setProgrammePosition(JAXBElement<String> value) {
        this.programmePosition = value;
    }

    /**
     * Gets the value of the promoSpot property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getPromoSpot() {
        return promoSpot;
    }

    /**
     * Sets the value of the promoSpot property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setPromoSpot(JAXBElement<String> value) {
        this.promoSpot = value;
    }

    /**
     * Gets the value of the proposedCampaign property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getProposedCampaign() {
        return proposedCampaign;
    }

    /**
     * Sets the value of the proposedCampaign property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setProposedCampaign(Integer value) {
        this.proposedCampaign = value;
    }

    /**
     * Gets the value of the proposedCampaignVersion property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getProposedCampaignVersion() {
        return proposedCampaignVersion;
    }

    /**
     * Sets the value of the proposedCampaignVersion property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setProposedCampaignVersion(Integer value) {
        this.proposedCampaignVersion = value;
    }

    /**
     * Gets the value of the protectedCopy property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isProtectedCopy() {
        return protectedCopy;
    }

    /**
     * Sets the value of the protectedCopy property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setProtectedCopy(Boolean value) {
        this.protectedCopy = value;
    }

    /**
     * Gets the value of the ratecard property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getRatecard() {
        return ratecard;
    }

    /**
     * Sets the value of the ratecard property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setRatecard(Integer value) {
        this.ratecard = value;
    }

    /**
     * Gets the value of the ratingType property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getRatingType() {
        return ratingType;
    }

    /**
     * Sets the value of the ratingType property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setRatingType(JAXBElement<String> value) {
        this.ratingType = value;
    }

    /**
     * Gets the value of the ratings property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getRatings() {
        return ratings;
    }

    /**
     * Sets the value of the ratings property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setRatings(Double value) {
        this.ratings = value;
    }

    /**
     * Gets the value of the ratingsL property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getRatingsL() {
        return ratingsL;
    }

    /**
     * Sets the value of the ratingsL property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setRatingsL(Double value) {
        this.ratingsL = value;
    }

    /**
     * Gets the value of the ratingsP property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getRatingsP() {
        return ratingsP;
    }

    /**
     * Sets the value of the ratingsP property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setRatingsP(Double value) {
        this.ratingsP = value;
    }

    /**
     * Gets the value of the regExcl property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getRegExcl() {
        return regExcl;
    }

    /**
     * Sets the value of the regExcl property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setRegExcl(JAXBElement<String> value) {
        this.regExcl = value;
    }

    /**
     * Gets the value of the regionNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getRegionNo() {
        return regionNo;
    }

    /**
     * Sets the value of the regionNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setRegionNo(Integer value) {
        this.regionNo = value;
    }

    /**
     * Gets the value of the reptPrgcNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getReptPrgcNo() {
        return reptPrgcNo;
    }

    /**
     * Sets the value of the reptPrgcNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setReptPrgcNo(Integer value) {
        this.reptPrgcNo = value;
    }

    /**
     * Gets the value of the salesSplitId property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSalesSplitId() {
        return salesSplitId;
    }

    /**
     * Sets the value of the salesSplitId property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSalesSplitId(Integer value) {
        this.salesSplitId = value;
    }

    /**
     * Gets the value of the scheduledDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getScheduledDate() {
        return scheduledDate;
    }

    /**
     * Sets the value of the scheduledDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setScheduledDate(XMLGregorianCalendar value) {
        this.scheduledDate = value;
    }

    /**
     * Gets the value of the scheduledTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getScheduledTime() {
        return scheduledTime;
    }

    /**
     * Sets the value of the scheduledTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setScheduledTime(Integer value) {
        this.scheduledTime = value;
    }

    /**
     * Gets the value of the secStatus property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isSecStatus() {
        return secStatus;
    }

    /**
     * Sets the value of the secStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setSecStatus(Boolean value) {
        this.secStatus = value;
    }

    /**
     * Gets the value of the secUniverse property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getSecUniverse() {
        return secUniverse;
    }

    /**
     * Sets the value of the secUniverse property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setSecUniverse(Double value) {
        this.secUniverse = value;
    }

    /**
     * Gets the value of the slottingDays property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getSlottingDays() {
        return slottingDays;
    }

    /**
     * Sets the value of the slottingDays property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setSlottingDays(JAXBElement<String> value) {
        this.slottingDays = value;
    }

    /**
     * Gets the value of the slottingEndDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getSlottingEndDate() {
        return slottingEndDate;
    }

    /**
     * Sets the value of the slottingEndDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setSlottingEndDate(XMLGregorianCalendar value) {
        this.slottingEndDate = value;
    }

    /**
     * Gets the value of the slottingEndTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSlottingEndTime() {
        return slottingEndTime;
    }

    /**
     * Sets the value of the slottingEndTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSlottingEndTime(Integer value) {
        this.slottingEndTime = value;
    }

    /**
     * Gets the value of the slottingStartDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getSlottingStartDate() {
        return slottingStartDate;
    }

    /**
     * Sets the value of the slottingStartDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setSlottingStartDate(XMLGregorianCalendar value) {
        this.slottingStartDate = value;
    }

    /**
     * Gets the value of the slottingStartTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSlottingStartTime() {
        return slottingStartTime;
    }

    /**
     * Sets the value of the slottingStartTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSlottingStartTime(Integer value) {
        this.slottingStartTime = value;
    }

    /**
     * Gets the value of the solus property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isSolus() {
        return solus;
    }

    /**
     * Sets the value of the solus property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setSolus(Boolean value) {
        this.solus = value;
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
     * Gets the value of the spotSalesAreaNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSpotSalesAreaNumber() {
        return spotSalesAreaNumber;
    }

    /**
     * Sets the value of the spotSalesAreaNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSpotSalesAreaNumber(Integer value) {
        this.spotSalesAreaNumber = value;
    }

    /**
     * Gets the value of the spotType property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getSpotType() {
        return spotType;
    }

    /**
     * Sets the value of the spotType property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setSpotType(JAXBElement<String> value) {
        this.spotType = value;
    }

    /**
     * Gets the value of the startDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the startTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setStartTime(Integer value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the startingPrice property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getStartingPrice() {
        return startingPrice;
    }

    /**
     * Sets the value of the startingPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setStartingPrice(Double value) {
        this.startingPrice = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setStatus(JAXBElement<String> value) {
        this.status = value;
    }

    /**
     * Gets the value of the substitutionalAdvertisingSpot property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isSubstitutionalAdvertisingSpot() {
        return substitutionalAdvertisingSpot;
    }

    /**
     * Sets the value of the substitutionalAdvertisingSpot property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setSubstitutionalAdvertisingSpot(Boolean value) {
        this.substitutionalAdvertisingSpot = value;
    }

    /**
     * Gets the value of the suspendedCopy property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isSuspendedCopy() {
        return suspendedCopy;
    }

    /**
     * Sets the value of the suspendedCopy property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setSuspendedCopy(Boolean value) {
        this.suspendedCopy = value;
    }

    /**
     * Gets the value of the tep property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getTEP() {
        return tep;
    }

    /**
     * Sets the value of the tep property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setTEP(JAXBElement<String> value) {
        this.tep = value;
    }

    /**
     * Gets the value of the targetSalesAreaNumber property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getTargetSalesAreaNumber() {
        return targetSalesAreaNumber;
    }

    /**
     * Sets the value of the targetSalesAreaNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setTargetSalesAreaNumber(Integer value) {
        this.targetSalesAreaNumber = value;
    }

    /**
     * Gets the value of the totalNominalPrice property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getTotalNominalPrice() {
        return totalNominalPrice;
    }

    /**
     * Sets the value of the totalNominalPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setTotalNominalPrice(Double value) {
        this.totalNominalPrice = value;
    }

    /**
     * Gets the value of the transmissionRegionNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getTransmissionRegionNo() {
        return transmissionRegionNo;
    }

    /**
     * Sets the value of the transmissionRegionNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setTransmissionRegionNo(Integer value) {
        this.transmissionRegionNo = value;
    }

    /**
     * Gets the value of the transmittedProgramme property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getTransmittedProgramme() {
        return transmittedProgramme;
    }

    /**
     * Sets the value of the transmittedProgramme property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setTransmittedProgramme(JAXBElement<String> value) {
        this.transmittedProgramme = value;
    }

    /**
     * Gets the value of the transmittedTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getTransmittedTime() {
        return transmittedTime;
    }

    /**
     * Sets the value of the transmittedTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setTransmittedTime(Integer value) {
        this.transmittedTime = value;
    }

    /**
     * Gets the value of the universe property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getUniverse() {
        return universe;
    }

    /**
     * Sets the value of the universe property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setUniverse(Integer value) {
        this.universe = value;
    }

    /**
     * Gets the value of the viewPrice property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isViewPrice() {
        return viewPrice;
    }

    /**
     * Sets the value of the viewPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setViewPrice(Boolean value) {
        this.viewPrice = value;
    }

    /**
     * Gets the value of the tba property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getTba() {
        return tba;
    }

    /**
     * Sets the value of the tba property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setTba(Double value) {
        this.tba = value;
    }

}
