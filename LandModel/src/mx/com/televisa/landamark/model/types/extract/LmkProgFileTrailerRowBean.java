package mx.com.televisa.landamark.model.types.extract;

import java.sql.Timestamp;

import java.util.Date;

public class LmkProgFileTrailerRowBean {
    private Integer liRecordType;
    private Integer liRecordCount;
    private Integer liAllowableGap;
    private String lsStnid;
    private Date ltBcstdt;
    private String lsPgmid;
    private Timestamp ltFechaCreacion;


    public void setLiRecordType(Integer liRecordType) {
        this.liRecordType = liRecordType;
    }

    public Integer getLiRecordType() {
        return liRecordType;
    }

    public void setLiRecordCount(Integer liRecordCount) {
        this.liRecordCount = liRecordCount;
    }

    public Integer getLiRecordCount() {
        return liRecordCount;
    }

    public void setLiAllowableGap(Integer liAllowableGap) {
        this.liAllowableGap = liAllowableGap;
    }

    public Integer getLiAllowableGap() {
        return liAllowableGap;
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

    public void setLsPgmid(String lsPgmid) {
        this.lsPgmid = lsPgmid;
    }

    public String getLsPgmid() {
        return lsPgmid;
    }

    public void setLtFechaCreacion(Timestamp ltFechaCreacion) {
        this.ltFechaCreacion = ltFechaCreacion;
    }

    public Timestamp getLtFechaCreacion() {
        return ltFechaCreacion;
    }
}
