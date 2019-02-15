package mx.com.televisa.landamark.users;

import java.util.List;

import oracle.adf.view.rich.component.rich.input.RichInputText;

public class UserOperationList {
    private List<String> laOpertations;

    public void setLaOpertations(List<String> laOpertations) {
        this.laOpertations = laOpertations;
    }

    public List<String> getLaOpertations() {
        return laOpertations;
    }   
}
