package mx.com.televisa.landamark.services.jobs;

import java.util.Date;

import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.services.service.ParrillasProgramasService;
import mx.com.televisa.landamark.view.types.BasicInputParameters;
import mx.com.televisa.landamark.view.types.ResponseService;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ParrillasProgramasCron implements Job{
    public ParrillasProgramasCron() {
        super();
    }

    @Override
    public void execute(JobExecutionContext toJobExecutionContext) throws JobExecutionException {
        System.out.println("Ejecucion de Cron >> Dummy ["+new Date()+"]");
        JobDataMap                loDataMap = toJobExecutionContext.getJobDetail().getJobDataMap();
        String                    lsIdService = loDataMap.getString("lsIdService");  
        String                    lsUserName = loDataMap.getString("lsUserName");
        String                    lsIdUser = loDataMap.getString("liIdUser");
        String                    lsTypeProcess = loDataMap.getString("lsTypeProcess");
        Integer                            liIdRequest = 
            new ViewObjectDao().getMaxIdParadigm("Request") + 1;        
        
        BasicInputParameters loInput = new BasicInputParameters();
        loInput.setLiIdRequest(liIdRequest);
        loInput.setLiIdService(Integer.parseInt(lsIdService));
        loInput.setLiIdUser(Integer.parseInt(lsIdUser));
        loInput.setLsMessage("RONDA-BOLO-RAYUELAS-TIGUE");
        loInput.setLsUserName(lsUserName);
        loInput.setLsServiceType(lsTypeProcess);
        
        ParrillasProgramasService loService = new ParrillasProgramasService();
        ResponseService loPits = loService.executeService(loInput);
        
        System.out.println("ID["+liIdRequest+"]Finish de Cron >> "+lsTypeProcess+" ["+loPits.getLsMessageResponse()+"]");
    }
}
