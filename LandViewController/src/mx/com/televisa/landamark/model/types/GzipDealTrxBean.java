package mx.com.televisa.landamark.model.types;

public class GzipDealTrxBean {
    public GzipDealTrxBean() {
        super();
    }
    
    private Integer liDealNo;
    private Integer liMasterDeal;
    private String lsClntCode;
    private String lsStatus;
    private String lsSttDate;
    private String lsEndDate;

    public void setLiDealNo(Integer liDealNo) {
        this.liDealNo = liDealNo;
    }

    public Integer getLiDealNo() {
        return liDealNo;
    }

    public void setLiMasterDeal(Integer liMasterDeal) {
        this.liMasterDeal = liMasterDeal;
    }

    public Integer getLiMasterDeal() {
        return liMasterDeal;
    }

    public void setLsClntCode(String lsClntCode) {
        this.lsClntCode = lsClntCode;
    }

    public String getLsClntCode() {
        return lsClntCode;
    }

    public void setLsStatus(String lsStatus) {
        this.lsStatus = lsStatus;
    }

    public String getLsStatus() {
        return lsStatus;
    }

    public void setLsSttDate(String lsSttDate) {
        this.lsSttDate = lsSttDate;
    }

    public String getLsSttDate() {
        return lsSttDate;
    }

    public void setLsEndDate(String lsEndDate) {
        this.lsEndDate = lsEndDate;
    }

    public String getLsEndDate() {
        return lsEndDate;
    }
}
