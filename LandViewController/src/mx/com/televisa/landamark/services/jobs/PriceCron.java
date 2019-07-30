/**
* Project: Paradigm - Landmark 
*
* File: PriceCron.java
*
* Created on:  Julio 29, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/
package mx.com.televisa.landamark.services.jobs;

import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.services.service.ParrillasProgramasService;
import mx.com.televisa.landamark.view.types.BasicInputParameters;
import mx.com.televisa.landamark.view.types.ResponseService;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/** Esta clase Ejecuta en segundo plano el proceso de Actualizacion de Precios<br/><br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Junio 29, 2019, 12:00 pm
 */
public class PriceCron implements Job{
    public PriceCron() {
        super();
    }
    
    /**
     * Metodo que ejecuta la logica de Actualizacion de precios en segundo plano
     * capa intermedia entre la invocación del proceso y el servicio
     * @autor Jorge Luis Bautista Santiago
     * @return void
     */
    @Override
    public void execute(JobExecutionContext toJobExecutionContext) throws JobExecutionException {        
        JobDataMap                loDataMap = toJobExecutionContext.getJobDetail().getJobDataMap();
        String                    lsIdService = loDataMap.getString("lsIdService");  
        String                    lsUserName = loDataMap.getString("lsUserName");
        String                    lsIdUser = loDataMap.getString("liIdUser");
        String                    lsTypeProcess = loDataMap.getString("lsTypeProcess");
        String                    lsServiceName = loDataMap.getString("lsServiceName");
        String                    lsPathFiles = loDataMap.getString("lsPathFiles");
        
        Integer                            liIdRequest = 
            new ViewObjectDao().getMaxIdParadigm("Request") + 1;        
        
        BasicInputParameters loInput = new BasicInputParameters();
        loInput.setLiIdRequest(liIdRequest);
        loInput.setLiIdService(Integer.parseInt(lsIdService));
        loInput.setLiIdUser(Integer.parseInt(lsIdUser));
        loInput.setLsMessage("Ejecucion de Actualizacion de Precios");
        loInput.setLsUserName(lsUserName);
        loInput.setLsServiceType(lsTypeProcess);
        loInput.setLsServiceName(lsServiceName);
        loInput.setLsPathFiles(lsPathFiles);
        
        ParrillasProgramasService loService = new ParrillasProgramasService();
        ResponseService loPits = loService.executeService(loInput);
        
        System.out.println("ID["+liIdRequest+"]Finish de Cron(Actualizacion de precios) >> "+
                           lsTypeProcess+" ["+loPits.getLsMessageResponse()+"]");
    }       
    

    
    
}
