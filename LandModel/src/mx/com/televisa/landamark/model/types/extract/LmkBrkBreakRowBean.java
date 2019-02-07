package mx.com.televisa.landamark.model.types.extract;

import java.sql.Timestamp;

import java.util.Date;

public class LmkBrkBreakRowBean {
    private Integer liRecordType;
    private String lsRegionalSalesCode;
    private String lsSalesAreaCode;
    private String lsBreakSchedule;
    private Integer liBreakNominal;
    private Integer liBreakDuration;
    private String lsBreakTypeCode;
    private String lsPositionInProgramme;
    private Integer liBreakNumber;
    private String lsStnid;
    private Date ltBcstdt;
    private Timestamp ltFechaCreacion;

    public void setLiRecordType(Integer liRecordType) {
        this.liRecordType = liRecordType;
    }

    public Integer getLiRecordType() {
        return liRecordType;
    }

    public void setLsRegionalSalesCode(String lsRegionalSalesCode) {
        this.lsRegionalSalesCode = lsRegionalSalesCode;
    }

    public String getLsRegionalSalesCode() {
        return lsRegionalSalesCode;
    }

    public void setLsSalesAreaCode(String lsSalesAreaCode) {
        this.lsSalesAreaCode = lsSalesAreaCode;
    }

    public String getLsSalesAreaCode() {
        return lsSalesAreaCode;
    }

    public void setLsBreakSchedule(String lsBreakSchedule) {
        this.lsBreakSchedule = lsBreakSchedule;
    }

    public String getLsBreakSchedule() {
        return lsBreakSchedule;
    }

    public void setLiBreakNominal(Integer liBreakNominal) {
        this.liBreakNominal = liBreakNominal;
    }

    public Integer getLiBreakNominal() {
        return liBreakNominal;
    }

    public void setLiBreakDuration(Integer liBreakDuration) {
        this.liBreakDuration = liBreakDuration;
    }

    public Integer getLiBreakDuration() {
        return liBreakDuration;
    }

    public void setLsBreakTypeCode(String lsBreakTypeCode) {
        this.lsBreakTypeCode = lsBreakTypeCode;
    }

    public String getLsBreakTypeCode() {
        return lsBreakTypeCode;
    }

    public void setLsPositionInProgramme(String lsPositionInProgramme) {
        this.lsPositionInProgramme = lsPositionInProgramme;
    }

    public String getLsPositionInProgramme() {
        return lsPositionInProgramme;
    }

    public void setLiBreakNumber(Integer liBreakNumber) {
        this.liBreakNumber = liBreakNumber;
    }

    public Integer getLiBreakNumber() {
        return liBreakNumber;
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

    public void setLtFechaCreacion(Timestamp ltFechaCreacion) {
        this.ltFechaCreacion = ltFechaCreacion;
    }

    public Timestamp getLtFechaCreacion() {
        return ltFechaCreacion;
    }
}
