/**
* Project: Integraton Paradigm - Landmark
*
* File: Spot.java
*
* Created on: Febrero 23, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.beans.input.spots;

import javax.xml.bind.annotation.XmlElement;

/** Bean para mapeo del Spot de entrada enviado por Landmark
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
 */
public class Spot {
    public Spot() {
        super();
    }
    @XmlElement
    private String SalesArea;
    @XmlElement
    private String TxRegion;
    @XmlElement
    private String ScheduleDate;
    @XmlElement
    private String StartTime;
    @XmlElement
    private String BreakNumber;
    @XmlElement
    private String BreakType;
    @XmlElement
    private String Secondary;
    @XmlElement
    private String SpotNumber;
    @XmlElement
    private String ContractNumber;
    @XmlElement
    private String SpotDuration;
    @XmlElement
    private String SpotSalesClassification;
    @XmlElement
    private String PosReqCode;
    @XmlElement
    private String SequenceNumber;
    @XmlElement
    private String ProductKey;
    @XmlElement
    private String ProductName;
    @XmlElement
    private String BonusSpot;
    @XmlElement
    private String BusinessTypeCode;
    @XmlElement
    private String RootClashCode;
    @XmlElement
    private String ClashCode;
    @XmlElement
    private String ClashDescription;
    @XmlElement
    private String CloseCaption;
    @XmlElement
    private String MobilityCode;
    @XmlElement
    private String StartPrice;
    //@XmlElement
    private String PSDPrice;
    @XmlElement
    private String ProgrammeName;
    @XmlElement
    private String AdvertiserID;
    @XmlElement
    private String AdvertiserName;
    @XmlElement
    private String AgencyID;
    @XmlElement
    private String AgencyName;
    
    @XmlElement(name = "Contents")
    private Contents Contents;

    public void setContents(Contents Contents) {
        this.Contents = Contents;
    }

    public Contents getContents() {
        return Contents;
    }
    

    public void setSalesArea(String SalesArea) {
        this.SalesArea = SalesArea;
    }

    public String getSalesArea() {
        return SalesArea;
    }

    public void setTxRegion(String TxRegion) {
        this.TxRegion = TxRegion;
    }

    public String getTxRegion() {
        return TxRegion;
    }

    public void setScheduleDate(String ScheduleDate) {
        this.ScheduleDate = ScheduleDate;
    }

    public String getScheduleDate() {
        return ScheduleDate;
    }

    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setBreakNumber(String BreakNumber) {
        this.BreakNumber = BreakNumber;
    }

    public String getBreakNumber() {
        return BreakNumber;
    }

    public void setBreakType(String BreakType) {
        this.BreakType = BreakType;
    }

    public String getBreakType() {
        return BreakType;
    }

    public void setSecondary(String Secondary) {
        this.Secondary = Secondary;
    }

    public String getSecondary() {
        return Secondary;
    }

    public void setSpotNumber(String SpotNumber) {
        this.SpotNumber = SpotNumber;
    }

    public String getSpotNumber() {
        return SpotNumber;
    }

    public void setContractNumber(String ContractNumber) {
        this.ContractNumber = ContractNumber;
    }

    public String getContractNumber() {
        return ContractNumber;
    }

    public void setSpotDuration(String SpotDuration) {
        this.SpotDuration = SpotDuration;
    }

    public String getSpotDuration() {
        return SpotDuration;
    }

    public void setSpotSalesClassification(String SpotSalesClassification) {
        this.SpotSalesClassification = SpotSalesClassification;
    }

    public String getSpotSalesClassification() {
        return SpotSalesClassification;
    }

    public void setPosReqCode(String PosReqCode) {
        this.PosReqCode = PosReqCode;
    }

    public String getPosReqCode() {
        return PosReqCode;
    }

    public void setSequenceNumber(String SequenceNumber) {
        this.SequenceNumber = SequenceNumber;
    }

    public String getSequenceNumber() {
        return SequenceNumber;
    }

    public void setProductKey(String ProductKey) {
        this.ProductKey = ProductKey;
    }

    public String getProductKey() {
        return ProductKey;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setBonusSpot(String BonusSpot) {
        this.BonusSpot = BonusSpot;
    }

    public String getBonusSpot() {
        return BonusSpot;
    }

    public void setBusinessTypeCode(String BusinessTypeCode) {
        this.BusinessTypeCode = BusinessTypeCode;
    }

    public String getBusinessTypeCode() {
        return BusinessTypeCode;
    }

    public void setRootClashCode(String RootClashCode) {
        this.RootClashCode = RootClashCode;
    }

    public String getRootClashCode() {
        return RootClashCode;
    }

    public void setClashCode(String ClashCode) {
        this.ClashCode = ClashCode;
    }

    public String getClashCode() {
        return ClashCode;
    }

    public void setClashDescription(String ClashDescription) {
        this.ClashDescription = ClashDescription;
    }

    public String getClashDescription() {
        return ClashDescription;
    }

    public void setCloseCaption(String CloseCaption) {
        this.CloseCaption = CloseCaption;
    }

    public String getCloseCaption() {
        return CloseCaption;
    }

    public void setMobilityCode(String MobilityCode) {
        this.MobilityCode = MobilityCode;
    }

    public String getMobilityCode() {
        return MobilityCode;
    }

    public void setStartPrice(String StartPrice) {
        this.StartPrice = StartPrice;
    }

    public String getStartPrice() {
        return StartPrice;
    }

    public void setPSDPrice(String PSDPrice) {
        this.PSDPrice = PSDPrice;
    }

    public String getPSDPrice() {
        return PSDPrice;
    }

    public void setProgrammeName(String ProgrammeName) {
        this.ProgrammeName = ProgrammeName;
    }

    public String getProgrammeName() {
        return ProgrammeName;
    }

    public void setAdvertiserID(String AdvertiserID) {
        this.AdvertiserID = AdvertiserID;
    }

    public String getAdvertiserID() {
        return AdvertiserID;
    }

    public void setAdvertiserName(String AdvertiserName) {
        this.AdvertiserName = AdvertiserName;
    }

    public String getAdvertiserName() {
        return AdvertiserName;
    }

    public void setAgencyID(String AgencyID) {
        this.AgencyID = AgencyID;
    }

    public String getAgencyID() {
        return AgencyID;
    }

    public void setAgencyName(String AgencyName) {
        this.AgencyName = AgencyName;
    }

    public String getAgencyName() {
        return AgencyName;
    }

}
