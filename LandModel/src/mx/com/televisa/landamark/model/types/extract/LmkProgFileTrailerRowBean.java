package mx.com.televisa.landamark.model.types.extract;

import java.sql.Timestamp;

import java.util.Date;

public class LmkProgFileTrailerRowBean {
    private Integer liRecordType;
    private Integer liRecordCount;
    private Integer liAllowableGap;
    private String lsStnid;
    private String lsStrdt;
    private String lsEdt;    
    private Timestamp ltFechaCreacion;
    private String lsFullRowTrailer;

    public void setLsFullRowTrailer(String lsFullRowTrailer) {
        this.lsFullRowTrailer = lsFullRowTrailer;
    }

    public String getLsFullRowTrailer() {
        return lsFullRowTrailer;
    }

    public void setLsStrdt(String lsStrdt) {
        this.lsStrdt = lsStrdt;
    }

    public String getLsStrdt() {
        return lsStrdt;
    }

    public void setLsEdt(String lsEdt) {
        this.lsEdt = lsEdt;
    }

    public String getLsEdt() {
        return lsEdt;
    }

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

    public void setLtFechaCreacion(Timestamp ltFechaCreacion) {
        this.ltFechaCreacion = ltFechaCreacion;
    }

    public Timestamp getLtFechaCreacion() {
        return ltFechaCreacion;
    }
}
