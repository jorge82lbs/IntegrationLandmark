package mx.com.televisa.landamark.model.types;

import java.io.InputStream;

public class LmkIntXmlFilesRowBean {
    public LmkIntXmlFilesRowBean() {
        super();
    }
    private Integer liIdFileXml;
    private Integer liIdRequest;
    private Integer liIdService;
    private String lsNomFile;
    private String lsIndFileType;
    private String lsIndServiceType;
    private InputStream loIndFileStream;
    private String lsIndEstatus;
    private String lsFecCreationDate;
    private String lsNomUserName;
    private String lsNomUserPathFile;
    private Integer liIdUser;
    private String lsAttribute1;
    private String lsAttribute2;
    private String lsAttribute11;
    private String lsAttribute12;

    public void setLsAttribute12(String lsAttribute12) {
        this.lsAttribute12 = lsAttribute12;
    }

    public String getLsAttribute12() {
        return lsAttribute12;
    }

    public void setLsAttribute11(String lsAttribute11) {
        this.lsAttribute11 = lsAttribute11;
    }

    public String getLsAttribute11() {
        return lsAttribute11;
    }

    public void setLsAttribute2(String lsAttribute2) {
        this.lsAttribute2 = lsAttribute2;
    }

    public String getLsAttribute2() {
        return lsAttribute2;
    }

    public void setLsAttribute1(String lsAttribute1) {
        this.lsAttribute1 = lsAttribute1;
    }

    public String getLsAttribute1() {
        return lsAttribute1;
    }

    public void setLiIdUser(Integer liIdUser) {
        this.liIdUser = liIdUser;
    }

    public Integer getLiIdUser() {
        return liIdUser;
    }

    public void setLsNomUserPathFile(String lsNomUserPathFile) {
        this.lsNomUserPathFile = lsNomUserPathFile;
    }

    public String getLsNomUserPathFile() {
        return lsNomUserPathFile;
    }

    public void setLoIndFileStream(InputStream loIndFileStream) {
        this.loIndFileStream = loIndFileStream;
    }

    public InputStream getLoIndFileStream() {
        return loIndFileStream;
    }

    public void setLiIdFileXml(Integer liIdFileXml) {
        this.liIdFileXml = liIdFileXml;
    }

    public Integer getLiIdFileXml() {
        return liIdFileXml;
    }

    public void setLiIdRequest(Integer liIdRequest) {
        this.liIdRequest = liIdRequest;
    }

    public Integer getLiIdRequest() {
        return liIdRequest;
    }

    public void setLiIdService(Integer liIdService) {
        this.liIdService = liIdService;
    }

    public Integer getLiIdService() {
        return liIdService;
    }

    public void setLsNomFile(String lsNomFile) {
        this.lsNomFile = lsNomFile;
    }

    public String getLsNomFile() {
        return lsNomFile;
    }

    public void setLsIndFileType(String lsIndFileType) {
        this.lsIndFileType = lsIndFileType;
    }

    public String getLsIndFileType() {
        return lsIndFileType;
    }

    public void setLsIndServiceType(String lsIndServiceType) {
        this.lsIndServiceType = lsIndServiceType;
    }

    public String getLsIndServiceType() {
        return lsIndServiceType;
    }

    public void setLsIndEstatus(String lsIndEstatus) {
        this.lsIndEstatus = lsIndEstatus;
    }

    public String getLsIndEstatus() {
        return lsIndEstatus;
    }

    public void setLsFecCreationDate(String lsFecCreationDate) {
        this.lsFecCreationDate = lsFecCreationDate;
    }

    public String getLsFecCreationDate() {
        return lsFecCreationDate;
    }

    public void setLsNomUserName(String lsNomUserName) {
        this.lsNomUserName = lsNomUserName;
    }

    public String getLsNomUserName() {
        return lsNomUserName;
    }
}
