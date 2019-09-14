package mx.com.televisa.landamark.model.types;

public class GzipDemdTrxBean {
    public GzipDemdTrxBean() {
        super();
    }
    
    private Integer liDealNo;
    private Integer liDemoNo;
    private Double ldRevenuePerc;
    private Integer liSareNo;
    private Double ldCpp;

    public void setLiDealNo(Integer liDealNo) {
        this.liDealNo = liDealNo;
    }

    public Integer getLiDealNo() {
        return liDealNo;
    }

    public void setLiDemoNo(Integer liDemoNo) {
        this.liDemoNo = liDemoNo;
    }

    public Integer getLiDemoNo() {
        return liDemoNo;
    }

    public void setLdRevenuePerc(Double ldRevenuePerc) {
        this.ldRevenuePerc = ldRevenuePerc;
    }

    public Double getLdRevenuePerc() {
        return ldRevenuePerc;
    }

    public void setLiSareNo(Integer liSareNo) {
        this.liSareNo = liSareNo;
    }

    public Integer getLiSareNo() {
        return liSareNo;
    }

    public void setLdCpp(Double ldCpp) {
        this.ldCpp = ldCpp;
    }

    public Double getLdCpp() {
        return ldCpp;
    }

}
