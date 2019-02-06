package mx.com.televisa.landamark.model.types;

public class LmkIntServicesParamsRowBean {
    public LmkIntServicesParamsRowBean() {
        super();
    }
    private Integer liIdParameterServ; 
    private Integer liIdService; 
    private String lsIndParameter;
    private String lsIndValParameter;
    private String lsIndEstatus;

    public void setLiIdParameterServ(Integer liIdParameterServ) {
        this.liIdParameterServ = liIdParameterServ;
    }

    public Integer getLiIdParameterServ() {
        return liIdParameterServ;
    }

    public void setLiIdService(Integer liIdService) {
        this.liIdService = liIdService;
    }

    public Integer getLiIdService() {
        return liIdService;
    }

    public void setLsIndParameter(String lsIndParameter) {
        this.lsIndParameter = lsIndParameter;
    }

    public String getLsIndParameter() {
        return lsIndParameter;
    }

    public void setLsIndValParameter(String lsIndValParameter) {
        this.lsIndValParameter = lsIndValParameter;
    }

    public String getLsIndValParameter() {
        return lsIndValParameter;
    }

    public void setLsIndEstatus(String lsIndEstatus) {
        this.lsIndEstatus = lsIndEstatus;
    }

    public String getLsIndEstatus() {
        return lsIndEstatus;
    }
}
