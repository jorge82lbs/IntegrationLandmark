
package org.datacontract.schemas._2004._07.landmark_classes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BookingMasterData complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BookingMasterData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BcatCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BkpoPosnReqm" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BrekNomTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BrekSchedDate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BsarNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BstpNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BtypCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CampNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ClientPicked" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CrcyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DealType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DiscountDate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Efficiency" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="EnhancedYN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnteredPriceYN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EpisName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FloorRate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ItypNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="LineNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="LiveBroadcast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MbruCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NoOfRtgs" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="NonPreemptibleYN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OpenAvail" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="OptIndex" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="OptLimit" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="OptionExpiryDate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PreeStatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PreeTorStatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PrgcNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ProgClassCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProgComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProgNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PsdPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="RowId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SareNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotSareNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SpotStatusName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SpttCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SslgLength" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SttPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="UseReserveYN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookingMasterData", propOrder = {
         "bcatCode", "bkpoPosnReqm", "brekNomTime", "brekSchedDate", "bsarNo", "bstpNo", "btypCode", "campNo",
         "clientPicked", "crcyCode", "dealType", "discountDate", "duration", "efficiency", "enhancedYN",
         "enteredPriceYN", "episName", "floorRate", "itypNo", "lineNo", "liveBroadcast", "mbruCode", "noOfRtgs",
         "nonPreemptibleYN", "openAvail", "optIndex", "optLimit", "optionExpiryDate", "preeStatus", "preeTorStatus",
         "prgcNo", "progClassCode", "progComment", "progName", "progNo", "psdPrice", "rowId", "sareNo", "spotNo",
         "spotSareNo", "spotStatusName", "spttCode", "sslgLength", "sttPrice", "useReserveYN"
    })
public class BookingMasterData {

    @XmlElementRef(name = "BcatCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> bcatCode;
    @XmlElement(name = "BkpoPosnReqm")
    protected Integer bkpoPosnReqm;
    @XmlElement(name = "BrekNomTime")
    protected Integer brekNomTime;
    @XmlElement(name = "BrekSchedDate")
    protected Integer brekSchedDate;
    @XmlElement(name = "BsarNo")
    protected Integer bsarNo;
    @XmlElement(name = "BstpNo")
    protected Integer bstpNo;
    @XmlElementRef(name = "BtypCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> btypCode;
    @XmlElement(name = "CampNo")
    protected Integer campNo;
    @XmlElementRef(name = "ClientPicked", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientPicked;
    @XmlElementRef(name = "CrcyCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> crcyCode;
    @XmlElementRef(name = "DealType", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> dealType;
    @XmlElement(name = "DiscountDate")
    protected Integer discountDate;
    @XmlElement(name = "Duration")
    protected Integer duration;
    @XmlElement(name = "Efficiency")
    protected Double efficiency;
    @XmlElementRef(name = "EnhancedYN", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> enhancedYN;
    @XmlElementRef(name = "EnteredPriceYN",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> enteredPriceYN;
    @XmlElementRef(name = "EpisName", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> episName;
    @XmlElement(name = "FloorRate")
    protected Double floorRate;
    @XmlElement(name = "ItypNo")
    protected Integer itypNo;
    @XmlElement(name = "LineNo")
    protected Integer lineNo;
    @XmlElementRef(name = "LiveBroadcast", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> liveBroadcast;
    @XmlElementRef(name = "MbruCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> mbruCode;
    @XmlElement(name = "NoOfRtgs")
    protected Double noOfRtgs;
    @XmlElementRef(name = "NonPreemptibleYN",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> nonPreemptibleYN;
    @XmlElement(name = "OpenAvail")
    protected Integer openAvail;
    @XmlElement(name = "OptIndex")
    protected Integer optIndex;
    @XmlElement(name = "OptLimit")
    protected Integer optLimit;
    @XmlElement(name = "OptionExpiryDate")
    protected Integer optionExpiryDate;
    @XmlElement(name = "PreeStatus")
    protected Integer preeStatus;
    @XmlElement(name = "PreeTorStatus")
    protected Integer preeTorStatus;
    @XmlElement(name = "PrgcNo")
    protected Integer prgcNo;
    @XmlElementRef(name = "ProgClassCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> progClassCode;
    @XmlElementRef(name = "ProgComment", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> progComment;
    @XmlElementRef(name = "ProgName", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> progName;
    @XmlElement(name = "ProgNo")
    protected Integer progNo;
    @XmlElement(name = "PsdPrice")
    protected Double psdPrice;
    @XmlElement(name = "RowId")
    protected Integer rowId;
    @XmlElement(name = "SareNo")
    protected Integer sareNo;
    @XmlElement(name = "SpotNo")
    protected Integer spotNo;
    @XmlElement(name = "SpotSareNo")
    protected Integer spotSareNo;
    @XmlElementRef(name = "SpotStatusName",
                   namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> spotStatusName;
    @XmlElementRef(name = "SpttCode", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> spttCode;
    @XmlElement(name = "SslgLength")
    protected Integer sslgLength;
    @XmlElement(name = "SttPrice")
    protected Double sttPrice;
    @XmlElementRef(name = "UseReserveYN", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> useReserveYN;

    /**
     * Gets the value of the bcatCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getBcatCode() {
        return bcatCode;
    }

    /**
     * Sets the value of the bcatCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setBcatCode(JAXBElement<String> value) {
        this.bcatCode = value;
    }

    /**
     * Gets the value of the bkpoPosnReqm property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBkpoPosnReqm() {
        return bkpoPosnReqm;
    }

    /**
     * Sets the value of the bkpoPosnReqm property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBkpoPosnReqm(Integer value) {
        this.bkpoPosnReqm = value;
    }

    /**
     * Gets the value of the brekNomTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBrekNomTime() {
        return brekNomTime;
    }

    /**
     * Sets the value of the brekNomTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBrekNomTime(Integer value) {
        this.brekNomTime = value;
    }

    /**
     * Gets the value of the brekSchedDate property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBrekSchedDate() {
        return brekSchedDate;
    }

    /**
     * Sets the value of the brekSchedDate property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBrekSchedDate(Integer value) {
        this.brekSchedDate = value;
    }

    /**
     * Gets the value of the bsarNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBsarNo() {
        return bsarNo;
    }

    /**
     * Sets the value of the bsarNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBsarNo(Integer value) {
        this.bsarNo = value;
    }

    /**
     * Gets the value of the bstpNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getBstpNo() {
        return bstpNo;
    }

    /**
     * Sets the value of the bstpNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setBstpNo(Integer value) {
        this.bstpNo = value;
    }

    /**
     * Gets the value of the btypCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getBtypCode() {
        return btypCode;
    }

    /**
     * Sets the value of the btypCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setBtypCode(JAXBElement<String> value) {
        this.btypCode = value;
    }

    /**
     * Gets the value of the campNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getCampNo() {
        return campNo;
    }

    /**
     * Sets the value of the campNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setCampNo(Integer value) {
        this.campNo = value;
    }

    /**
     * Gets the value of the clientPicked property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getClientPicked() {
        return clientPicked;
    }

    /**
     * Sets the value of the clientPicked property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setClientPicked(JAXBElement<String> value) {
        this.clientPicked = value;
    }

    /**
     * Gets the value of the crcyCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCrcyCode() {
        return crcyCode;
    }

    /**
     * Sets the value of the crcyCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCrcyCode(JAXBElement<String> value) {
        this.crcyCode = value;
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
     * Gets the value of the discountDate property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getDiscountDate() {
        return discountDate;
    }

    /**
     * Sets the value of the discountDate property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setDiscountDate(Integer value) {
        this.discountDate = value;
    }

    /**
     * Gets the value of the duration property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setDuration(Integer value) {
        this.duration = value;
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
     * Gets the value of the enhancedYN property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getEnhancedYN() {
        return enhancedYN;
    }

    /**
     * Sets the value of the enhancedYN property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setEnhancedYN(JAXBElement<String> value) {
        this.enhancedYN = value;
    }

    /**
     * Gets the value of the enteredPriceYN property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getEnteredPriceYN() {
        return enteredPriceYN;
    }

    /**
     * Sets the value of the enteredPriceYN property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setEnteredPriceYN(JAXBElement<String> value) {
        this.enteredPriceYN = value;
    }

    /**
     * Gets the value of the episName property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getEpisName() {
        return episName;
    }

    /**
     * Sets the value of the episName property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setEpisName(JAXBElement<String> value) {
        this.episName = value;
    }

    /**
     * Gets the value of the floorRate property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getFloorRate() {
        return floorRate;
    }

    /**
     * Sets the value of the floorRate property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setFloorRate(Double value) {
        this.floorRate = value;
    }

    /**
     * Gets the value of the itypNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getItypNo() {
        return itypNo;
    }

    /**
     * Sets the value of the itypNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setItypNo(Integer value) {
        this.itypNo = value;
    }

    /**
     * Gets the value of the lineNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getLineNo() {
        return lineNo;
    }

    /**
     * Sets the value of the lineNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setLineNo(Integer value) {
        this.lineNo = value;
    }

    /**
     * Gets the value of the liveBroadcast property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getLiveBroadcast() {
        return liveBroadcast;
    }

    /**
     * Sets the value of the liveBroadcast property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setLiveBroadcast(JAXBElement<String> value) {
        this.liveBroadcast = value;
    }

    /**
     * Gets the value of the mbruCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getMbruCode() {
        return mbruCode;
    }

    /**
     * Sets the value of the mbruCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setMbruCode(JAXBElement<String> value) {
        this.mbruCode = value;
    }

    /**
     * Gets the value of the noOfRtgs property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getNoOfRtgs() {
        return noOfRtgs;
    }

    /**
     * Sets the value of the noOfRtgs property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setNoOfRtgs(Double value) {
        this.noOfRtgs = value;
    }

    /**
     * Gets the value of the nonPreemptibleYN property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getNonPreemptibleYN() {
        return nonPreemptibleYN;
    }

    /**
     * Sets the value of the nonPreemptibleYN property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setNonPreemptibleYN(JAXBElement<String> value) {
        this.nonPreemptibleYN = value;
    }

    /**
     * Gets the value of the openAvail property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getOpenAvail() {
        return openAvail;
    }

    /**
     * Sets the value of the openAvail property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setOpenAvail(Integer value) {
        this.openAvail = value;
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
     * Gets the value of the optionExpiryDate property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getOptionExpiryDate() {
        return optionExpiryDate;
    }

    /**
     * Sets the value of the optionExpiryDate property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setOptionExpiryDate(Integer value) {
        this.optionExpiryDate = value;
    }

    /**
     * Gets the value of the preeStatus property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPreeStatus() {
        return preeStatus;
    }

    /**
     * Sets the value of the preeStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPreeStatus(Integer value) {
        this.preeStatus = value;
    }

    /**
     * Gets the value of the preeTorStatus property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPreeTorStatus() {
        return preeTorStatus;
    }

    /**
     * Sets the value of the preeTorStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPreeTorStatus(Integer value) {
        this.preeTorStatus = value;
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
     * Gets the value of the progClassCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getProgClassCode() {
        return progClassCode;
    }

    /**
     * Sets the value of the progClassCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setProgClassCode(JAXBElement<String> value) {
        this.progClassCode = value;
    }

    /**
     * Gets the value of the progComment property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getProgComment() {
        return progComment;
    }

    /**
     * Sets the value of the progComment property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setProgComment(JAXBElement<String> value) {
        this.progComment = value;
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
     * Gets the value of the psdPrice property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getPsdPrice() {
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
    public void setPsdPrice(Double value) {
        this.psdPrice = value;
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
     * Gets the value of the sareNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSareNo() {
        return sareNo;
    }

    /**
     * Sets the value of the sareNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSareNo(Integer value) {
        this.sareNo = value;
    }

    /**
     * Gets the value of the spotNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSpotNo() {
        return spotNo;
    }

    /**
     * Sets the value of the spotNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSpotNo(Integer value) {
        this.spotNo = value;
    }

    /**
     * Gets the value of the spotSareNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSpotSareNo() {
        return spotSareNo;
    }

    /**
     * Sets the value of the spotSareNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSpotSareNo(Integer value) {
        this.spotSareNo = value;
    }

    /**
     * Gets the value of the spotStatusName property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getSpotStatusName() {
        return spotStatusName;
    }

    /**
     * Sets the value of the spotStatusName property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setSpotStatusName(JAXBElement<String> value) {
        this.spotStatusName = value;
    }

    /**
     * Gets the value of the spttCode property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getSpttCode() {
        return spttCode;
    }

    /**
     * Sets the value of the spttCode property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setSpttCode(JAXBElement<String> value) {
        this.spttCode = value;
    }

    /**
     * Gets the value of the sslgLength property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSslgLength() {
        return sslgLength;
    }

    /**
     * Sets the value of the sslgLength property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSslgLength(Integer value) {
        this.sslgLength = value;
    }

    /**
     * Gets the value of the sttPrice property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getSttPrice() {
        return sttPrice;
    }

    /**
     * Sets the value of the sttPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setSttPrice(Double value) {
        this.sttPrice = value;
    }

    /**
     * Gets the value of the useReserveYN property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getUseReserveYN() {
        return useReserveYN;
    }

    /**
     * Sets the value of the useReserveYN property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setUseReserveYN(JAXBElement<String> value) {
        this.useReserveYN = value;
    }

}
