package mx.com.televisa.landamark.model.types;

public class LmkBrkGenericRowBean {
    private Integer liRecordType;
    private String lsFullRowConcated;
    private String lsFullRowNivel1;
    private String lsFullRowNivel23;
    private String lsFullRowNivel45;

    public void setLsFullRowNivel1(String lsFullRowNivel1) {
        this.lsFullRowNivel1 = lsFullRowNivel1;
    }

    public String getLsFullRowNivel1() {
        return lsFullRowNivel1;
    }

    public void setLsFullRowNivel23(String lsFullRowNivel23) {
        this.lsFullRowNivel23 = lsFullRowNivel23;
    }

    public String getLsFullRowNivel23() {
        return lsFullRowNivel23;
    }

    public void setLsFullRowNivel45(String lsFullRowNivel45) {
        this.lsFullRowNivel45 = lsFullRowNivel45;
    }

    public String getLsFullRowNivel45() {
        return lsFullRowNivel45;
    }

    public void setLiRecordType(Integer liRecordType) {
        this.liRecordType = liRecordType;
    }

    public Integer getLiRecordType() {
        return liRecordType;
    }

    public void setLsFullRowConcated(String lsFullRowConcated) {
        this.lsFullRowConcated = lsFullRowConcated;
    }

    public String getLsFullRowConcated() {
        return lsFullRowConcated;
    }
}
