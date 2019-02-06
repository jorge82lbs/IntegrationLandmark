package mx.com.televisa.landamark.model.types;

public class LmkIntRequestsRowBean {
    public LmkIntRequestsRowBean() {
        super();
    }
    private Integer liIdRequest;
    private Integer liIdService;
    private String lsIndServiceType;
    private String lsNomUserName;
    private String lsIndEstatus;

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

    public void setLsIndServiceType(String lsIndServiceType) {
        this.lsIndServiceType = lsIndServiceType;
    }

    public String getLsIndServiceType() {
        return lsIndServiceType;
    }

    public void setLsNomUserName(String lsNomUserName) {
        this.lsNomUserName = lsNomUserName;
    }

    public String getLsNomUserName() {
        return lsNomUserName;
    }

    public void setLsIndEstatus(String lsIndEstatus) {
        this.lsIndEstatus = lsIndEstatus;
    }

    public String getLsIndEstatus() {
        return lsIndEstatus;
    }
}
