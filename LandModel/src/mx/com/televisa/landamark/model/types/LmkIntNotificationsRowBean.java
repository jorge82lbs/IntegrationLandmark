package mx.com.televisa.landamark.model.types;

public class LmkIntNotificationsRowBean {
    private Integer liIdNotification;
    private Integer liIdService;
    private Integer liIndProcess;
    private String lsIndUsersGroup;
    private String lsIndSubject;
    private String lsIndMessage;
    private String lsIndEstatus;

    public void setLiIdNotification(Integer liIdNotification) {
        this.liIdNotification = liIdNotification;
    }

    public Integer getLiIdNotification() {
        return liIdNotification;
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

    public void setLsIndUsersGroup(String lsIndUsersGroup) {
        this.lsIndUsersGroup = lsIndUsersGroup;
    }

    public String getLsIndUsersGroup() {
        return lsIndUsersGroup;
    }

    public void setLsIndSubject(String lsIndSubject) {
        this.lsIndSubject = lsIndSubject;
    }

    public String getLsIndSubject() {
        return lsIndSubject;
    }

    public void setLsIndMessage(String lsIndMessage) {
        this.lsIndMessage = lsIndMessage;
    }

    public String getLsIndMessage() {
        return lsIndMessage;
    }

    public void setLsIndEstatus(String lsIndEstatus) {
        this.lsIndEstatus = lsIndEstatus;
    }

    public String getLsIndEstatus() {
        return lsIndEstatus;
    }
}
