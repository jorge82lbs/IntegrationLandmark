package mx.com.televisa.landamark.services.jobs;

import java.util.Date;

import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.services.service.DummyService;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ExecuteDummyCron implements Job{
    public ExecuteDummyCron() {
        super();
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Ejecucion de Cron >> Dummy ["+new Date()+"]");
        
        Integer                            liIdRequest = 
            new ViewObjectDao().getMaxIdParadigm("Request") + 1;
        
        DummyService loDummyService = new DummyService();
        String lsPits = loDummyService.executeDummyBean("RONDA-BOLO-RAYUELAS-TIGUE");
        System.out.println("ID["+liIdRequest+"]Finish de Cron >> Dummy ["+lsPits+"]");
    }
}
