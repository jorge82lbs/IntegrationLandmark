package mx.com.televisa.landamark.services.service;

import java.util.Date;

public class DummyService {
    public DummyService() {
        super();
    }
    public String executeDummyBean(String lsInput){
        String lsReturn = "Son mis pitbulls y el bolo";
        System.out.println("Ejecutando DummyBean a las "+new Date()+" Input["+lsInput+"]");
        return lsReturn;
    }
}
