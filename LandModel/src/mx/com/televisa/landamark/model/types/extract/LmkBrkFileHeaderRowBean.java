package mx.com.televisa.landamark.model.types.extract;

import java.sql.Timestamp;

import java.util.Date;

public class LmkBrkFileHeaderRowBean {

    private Integer liRecordType;
    private String lsFileCreationDate;
    private String lsFileCreationTime;
    private String lsStnid;
    private Date ltBcstdt;

    public void setLsFileCreationTime(String lsFileCreationTime) {
        this.lsFileCreationTime = lsFileCreationTime;
    }

    public String getLsFileCreationTime() {
        return lsFileCreationTime;
    }

    public void setLiRecordType(Integer liRecordType) {
        this.liRecordType = liRecordType;
    }

    public Integer getLiRecordType() {
        return liRecordType;
    }

    public void setLsFileCreationDate(String lsFileCreationDate) {
        this.lsFileCreationDate = lsFileCreationDate;
    }

    public String getLsFileCreationDate() {
        return lsFileCreationDate;
    }

    public void setLsStnid(String lsStnid) {
        this.lsStnid = lsStnid;
    }

    public String getLsStnid() {
        return lsStnid;
    }

    public void setLtBcstdt(Date ltBcstdt) {
        this.ltBcstdt = ltBcstdt;
    }

    public Date getLtBcstdt() {
        return ltBcstdt;
    }
}
