package mx.com.televisa.landamark.view.types;

import java.util.List;

public class MapDynaParameters {
    private List<LmkDynaParameters> laParameters;

    public void setLaParameters(List<LmkDynaParameters> laParameters) {
        this.laParameters = laParameters;
    }

    public List<LmkDynaParameters> getLaParameters() {
        return laParameters;
    }
}
