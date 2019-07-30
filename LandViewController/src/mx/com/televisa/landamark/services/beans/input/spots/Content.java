package mx.com.televisa.landamark.services.beans.input.spots;

import javax.xml.bind.annotation.XmlElement;

public class Content {
    public Content() {
        super();
    }    
    @XmlElement
    private String HouseNumber;
    @XmlElement
    private String IndustryCode;
    @XmlElement
    private String FirstAirDate;
    @XmlElement
    private String ExpirationDate;
    @XmlElement
    private String AspectRatio;
    @XmlElement
    private String CopyComment;
    @XmlElement
    private String PrimaryCopy;


    public void setHouseNumber(String HouseNumber) {
        this.HouseNumber = HouseNumber;
    }

    public String getHouseNumber() {
        return HouseNumber;
    }

    public void setIndustryCode(String IndustryCode) {
        this.IndustryCode = IndustryCode;
    }

    public String getIndustryCode() {
        return IndustryCode;
    }

    public void setFirstAirDate(String FirstAirDate) {
        this.FirstAirDate = FirstAirDate;
    }

    public String getFirstAirDate() {
        return FirstAirDate;
    }

    public void setExpirationDate(String ExpirationDate) {
        this.ExpirationDate = ExpirationDate;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setAspectRatio(String AspectRatio) {
        this.AspectRatio = AspectRatio;
    }

    public String getAspectRatio() {
        return AspectRatio;
    }

    public void setCopyComment(String CopyComment) {
        this.CopyComment = CopyComment;
    }

    public String getCopyComment() {
        return CopyComment;
    }

    public void setPrimaryCopy(String PrimaryCopy) {
        this.PrimaryCopy = PrimaryCopy;
    }

    public String getPrimaryCopy() {
        return PrimaryCopy;
    }

}
