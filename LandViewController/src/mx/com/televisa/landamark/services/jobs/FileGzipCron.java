/**
* Project: Paradigm - Landmark 
*
* File: FileGzipCron.java
*
* Created on:  Septiembre 11, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/
package mx.com.televisa.landamark.services.jobs;

import java.util.Date;

import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.services.service.DummyService;
import mx.com.televisa.landamark.services.service.FileGzipService;
import mx.com.televisa.landamark.view.types.BasicInputParameters;
import mx.com.televisa.landamark.view.types.ResponseService;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/** Esta clase Ejecuta en segundo plano el proceso de Extraccion de archivo gz 
 * y procesamiento de archivo plano<br/><br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Septiembre 11, 2019, 12:00 pm
 */
public class FileGzipCron  implements Job{
    public FileGzipCron() {
        super();
    }
    
    @Override
    public void execute(JobExecutionContext toJobExecutionContext) throws JobExecutionException {
        System.out.println("Ejecucion de Cron >> FileGzip ["+new Date()+"]");
        JobDataMap                loDataMap = toJobExecutionContext.getJobDetail().getJobDataMap();
        String                    lsIdService = loDataMap.getString("lsIdService");  
        String                    lsUserName = loDataMap.getString("lsUserName");
        String                    lsIdUser = loDataMap.getString("liIdUser");
        String                    lsTypeProcess = loDataMap.getString("lsTypeProcess");
        String                    lsServiceName = loDataMap.getString("lsServiceName");
        String                    lsPathFiles = loDataMap.getString("lsPathFiles");
        
        Integer                            liIdRequest = 
            new ViewObjectDao().getMaxIdParadigm("Request") + 1;
        
        FileGzipService loDummyService = new FileGzipService();
        
        BasicInputParameters loInput = new BasicInputParameters();
        loInput.setLiIdRequest(liIdRequest);
        loInput.setLiIdService(Integer.parseInt(lsIdService));
        loInput.setLiIdUser(Integer.parseInt(lsIdUser));
        loInput.setLsMessage("Procesamiento de Archivo Gz para "+lsServiceName);
        loInput.setLsUserName(lsUserName);
        loInput.setLsServiceType(lsTypeProcess);
        loInput.setLsServiceName(lsServiceName);
        loInput.setLsPathFiles(lsPathFiles);
        
        ResponseService loPits = loDummyService.executeService(loInput);
        
        System.out.println("ID["+liIdRequest+"]Finish de Cron >> FileGzip ["+loPits.getLsMessageResponse()+"]");
    }
}
