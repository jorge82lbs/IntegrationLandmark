package mx.com.televisa.landamark.model.types;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

public class LmkSpotstatusTrxBean {
    public LmkSpotstatusTrxBean() {
        super();
    }
    
    private String lsAdvertisercode;
    private String lsAgengycode;
    private Integer liCampaign;
    private Integer liBreakNumber;
    private Integer liDeal;
    private String lsExternalreference;
    private String lsIndustryCode;
    private Integer liLength;
    private Double ldNomianlprice;
    private Double ldRatings;
    private Date ltScheduledate;
    private String lsScheduleDate;
    private Integer liScheduletime;
    private Integer liSpotnumber;
    private Integer liSpotsalesareanumber;
    private String lsStatus;
    private XMLGregorianCalendar loScheduledate;
    private Integer liBreakPosition;
    private Integer liPreemptee;
    private Integer liPreemptor;

    public void setLiBreakPosition(Integer liBreakPosition) {
        this.liBreakPosition = liBreakPosition;
    }

    public Integer getLiBreakPosition() {
        return liBreakPosition;
    }

    public void setLiPreemptee(Integer liPreemptee) {
        this.liPreemptee = liPreemptee;
    }

    public Integer getLiPreemptee() {
        return liPreemptee;
    }

    public void setLiPreemptor(Integer liPreemptor) {
        this.liPreemptor = liPreemptor;
    }

    public Integer getLiPreemptor() {
        return liPreemptor;
    }

    public void setLoScheduledate(XMLGregorianCalendar loScheduledate) {
        this.loScheduledate = loScheduledate;
    }

    public XMLGregorianCalendar getLoScheduledate() {
        return loScheduledate;
    }

    public void setLdRatings(Double ldRatings) {
        this.ldRatings = ldRatings;
    }

    public Double getLdRatings() {
        return ldRatings;
    }

    public void setLdNomianlprice(Double ldNomianlprice) {
        this.ldNomianlprice = ldNomianlprice;
    }

    public Double getLdNomianlprice() {
        return ldNomianlprice;
    }

    public void setLsAdvertisercode(String lsAdvertisercode) {
        this.lsAdvertisercode = lsAdvertisercode;
    }

    public String getLsAdvertisercode() {
        return lsAdvertisercode;
    }

    public void setLsAgengycode(String lsAgengycode) {
        this.lsAgengycode = lsAgengycode;
    }

    public String getLsAgengycode() {
        return lsAgengycode;
    }

    public void setLiCampaign(Integer liCampaign) {
        this.liCampaign = liCampaign;
    }

    public Integer getLiCampaign() {
        return liCampaign;
    }

    public void setLiBreakNumber(Integer liBreakNumber) {
        this.liBreakNumber = liBreakNumber;
    }

    public Integer getLiBreakNumber() {
        return liBreakNumber;
    }

    public void setLiDeal(Integer liDeal) {
        this.liDeal = liDeal;
    }

    public Integer getLiDeal() {
        return liDeal;
    }

    public void setLsExternalreference(String lsExternalreference) {
        this.lsExternalreference = lsExternalreference;
    }

    public String getLsExternalreference() {
        return lsExternalreference;
    }

    public void setLsIndustryCode(String lsIndustryCode) {
        this.lsIndustryCode = lsIndustryCode;
    }

    public String getLsIndustryCode() {
        return lsIndustryCode;
    }

    public void setLiLength(Integer liLength) {
        this.liLength = liLength;
    }

    public Integer getLiLength() {
        return liLength;
    }

    public void setLtScheduledate(Date ltScheduledate) {
        this.ltScheduledate = ltScheduledate;
    }

    public Date getLtScheduledate() {
        return ltScheduledate;
    }

    public void setLsScheduleDate(String lsScheduleDate) {
        this.lsScheduleDate = lsScheduleDate;
    }

    public String getLsScheduleDate() {
        return lsScheduleDate;
    }

    public void setLiScheduletime(Integer liScheduletime) {
        this.liScheduletime = liScheduletime;
    }

    public Integer getLiScheduletime() {
        return liScheduletime;
    }

    public void setLiSpotnumber(Integer liSpotnumber) {
        this.liSpotnumber = liSpotnumber;
    }

    public Integer getLiSpotnumber() {
        return liSpotnumber;
    }

    public void setLiSpotsalesareanumber(Integer liSpotsalesareanumber) {
        this.liSpotsalesareanumber = liSpotsalesareanumber;
    }

    public Integer getLiSpotsalesareanumber() {
        return liSpotsalesareanumber;
    }

    public void setLsStatus(String lsStatus) {
        this.lsStatus = lsStatus;
    }

    public String getLsStatus() {
        return lsStatus;
    }


}
