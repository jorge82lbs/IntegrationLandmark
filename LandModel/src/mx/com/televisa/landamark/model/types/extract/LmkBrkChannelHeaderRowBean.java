package mx.com.televisa.landamark.model.types.extract;

import java.sql.Timestamp;

import java.util.Date;

public class LmkBrkChannelHeaderRowBean {
    private Integer liRecordType;
    private String lsRegionalSalesAreaCode;
    private String lsSalesAreaCode;
    private Integer liId;
    private String lsBreakSchedule;
    private String lsStnid;
    private Date ltBcstdt;
    private Timestamp ltFechaCreacion;


    public void setLiRecordType(Integer liRecordType) {
        this.liRecordType = liRecordType;
    }

    public Integer getLiRecordType() {
        return liRecordType;
    }

    public void setLsRegionalSalesAreaCode(String lsRegionalSalesAreaCode) {
        this.lsRegionalSalesAreaCode = lsRegionalSalesAreaCode;
    }

    public String getLsRegionalSalesAreaCode() {
        return lsRegionalSalesAreaCode;
    }

    public void setLsSalesAreaCode(String lsSalesAreaCode) {
        this.lsSalesAreaCode = lsSalesAreaCode;
    }

    public String getLsSalesAreaCode() {
        return lsSalesAreaCode;
    }

    public void setLiId(Integer liId) {
        this.liId = liId;
    }

    public Integer getLiId() {
        return liId;
    }

    public void setLsBreakSchedule(String lsBreakSchedule) {
        this.lsBreakSchedule = lsBreakSchedule;
    }

    public String getLsBreakSchedule() {
        return lsBreakSchedule;
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
