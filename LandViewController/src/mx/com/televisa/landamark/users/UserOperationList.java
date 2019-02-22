package mx.com.televisa.landamark.users;

import java.util.List;

import oracle.adf.view.rich.component.rich.input.RichInputText;

public class UserOperationList {
    
    private String lsUserName;
    private Integer liIdUser;
    private List<String> laOpertations;

    public void setLsUserName(String lsUserName) {
        this.lsUserName = lsUserName;
    }

    public String getLsUserName() {
        return lsUserName;
    }

    public void setLiIdUser(Integer liIdUser) {
        this.liIdUser = liIdUser;
    }

    public Integer getLiIdUser() {
        return liIdUser;
    }

    public void setLaOpertations(List<String> laOpertations) {
        this.laOpertations = laOpertations;
    }

    public List<String> getLaOpertations() {
        return laOpertations;
    }   
}
