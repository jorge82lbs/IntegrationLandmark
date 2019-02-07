package mx.com.televisa.landamark.view.types;

public class BasicInputParameters {
    private Integer liIdRequest;
    private Integer liIdService;
    private Integer liIdUser;
    private String lsUserName;
    private String lsMessage;
    private String lsServiceType;

    public void setLsMessage(String lsMessage) {
        this.lsMessage = lsMessage;
    }

    public String getLsMessage() {
        return lsMessage;
    }

    public void setLsServiceType(String lsServiceType) {
        this.lsServiceType = lsServiceType;
    }

    public String getLsServiceType() {
        return lsServiceType;
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

    public void setLiIdUser(Integer liIdUser) {
        this.liIdUser = liIdUser;
    }

    public Integer getLiIdUser() {
        return liIdUser;
    }

    public void setLsUserName(String lsUserName) {
        this.lsUserName = lsUserName;
    }

    public String getLsUserName() {
        return lsUserName;
    }


}
