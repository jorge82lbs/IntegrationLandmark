package mx.com.televisa.landamark.model.types;

public class LmkIntServicesLogRowBean {
    public LmkIntServicesLogRowBean() {
        super();
    }
    private Integer liIdLogServices;
    private Integer liIdService;
    private Integer liIndProcess;
    private String lsIndResponse;
    private Integer liNumUser;
    private Integer liNumProcessId;
    private Integer liNumPgmProcessId;
    private String lsIndServiceType;
    private String lsIndEstatus;

    public void setLiIdLogServices(Integer liIdLogServices) {
        this.liIdLogServices = liIdLogServices;
    }

    public Integer getLiIdLogServices() {
        return liIdLogServices;
    }

    public void setLiIdService(Integer liIdService) {
        this.liIdService = liIdService;
    }

    public Integer getLiIdService() {
        return liIdService;
    }

    public void setLiIndProcess(Integer liIndProcess) {
        this.liIndProcess = liIndProcess;
    }

    public Integer getLiIndProcess() {
        return liIndProcess;
    }

    public void setLsIndResponse(String lsIndResponse) {
        this.lsIndResponse = lsIndResponse;
    }

    public String getLsIndResponse() {
        return lsIndResponse;
    }

    public void setLiNumUser(Integer liNumUser) {
        this.liNumUser = liNumUser;
    }

    public Integer getLiNumUser() {
        return liNumUser;
    }

    public void setLiNumProcessId(Integer liNumProcessId) {
        this.liNumProcessId = liNumProcessId;
    }

    public Integer getLiNumProcessId() {
        return liNumProcessId;
    }

    public void setLiNumPgmProcessId(Integer liNumPgmProcessId) {
        this.liNumPgmProcessId = liNumPgmProcessId;
    }

    public Integer getLiNumPgmProcessId() {
        return liNumPgmProcessId;
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
}
