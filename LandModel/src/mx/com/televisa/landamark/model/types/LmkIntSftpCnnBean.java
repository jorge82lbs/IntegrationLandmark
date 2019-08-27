package mx.com.televisa.landamark.model.types;

public class LmkIntSftpCnnBean {
    private String lsHost;
    private Integer liPort;
    private String lsUser;
    private String lsPassword;
    private String lsPathSsoRsa;
    private String lsPwdSsoRsa;
    private String lsRsaType;

    public void setLsRsaType(String lsRsaType) {
        this.lsRsaType = lsRsaType;
    }

    public String getLsRsaType() {
        return lsRsaType;
    }

    public void setLsPwdSsoRsa(String lsPwdSsoRsa) {
        this.lsPwdSsoRsa = lsPwdSsoRsa;
    }

    public String getLsPwdSsoRsa() {
        return lsPwdSsoRsa;
    }

    public void setLsPathSsoRsa(String lsPathSsoRsa) {
        this.lsPathSsoRsa = lsPathSsoRsa;
    }

    public String getLsPathSsoRsa() {
        return lsPathSsoRsa;
    }

    public void setLsHost(String lsHost) {
        this.lsHost = lsHost;
    }

    public String getLsHost() {
        return lsHost;
    }

    public void setLiPort(Integer liPort) {
        this.liPort = liPort;
    }

    public Integer getLiPort() {
        return liPort;
    }

    public void setLsUser(String lsUser) {
        this.lsUser = lsUser;
    }

    public String getLsUser() {
        return lsUser;
    }

    public void setLsPassword(String lsPassword) {
        this.lsPassword = lsPassword;
    }

    public String getLsPassword() {
        return lsPassword;
    }

}
