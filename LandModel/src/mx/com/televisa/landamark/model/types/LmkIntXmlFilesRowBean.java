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
