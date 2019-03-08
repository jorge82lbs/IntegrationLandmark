/**
* Project: Paradigm - Landmark 
*
* File: OrderSpotsCron.java
*
* Created on:  Enero 09, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/
package mx.com.televisa.landamark.services.jobs;

import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.services.service.OrderSpotsService;
import mx.com.televisa.landamark.view.types.BasicInputParameters;
import mx.com.televisa.landamark.view.types.ResponseService;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/** Esta clase Ejecuta en segundo plano el proceso lectura de orden/spots<br/><br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Enero 09, 2019, 12:00 pm
 */
public class OrderSpotsCron implements Job{
    public OrderSpotsCron() {
        super();
    }

    /**
     * Metodo que ejecuta la lectura de eorden/spots de Landmark a Paradigm
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
        loInput.setLsMessage("Ejecucion de Escaneo de Orden/spots de Landmark");
        loInput.setLsUserName(lsUserName);
        loInput.setLsServiceType(lsTypeProcess);
        loInput.setLsServiceName(lsServiceName);
        loInput.setLsPathFiles(lsPathFiles);
        
        OrderSpotsService loService = new OrderSpotsService();
        ResponseService loPits = loService.executeService(loInput);
        
        System.out.println("ID["+liIdRequest+"]Finish de Cron >> "+lsTypeProcess+
                           " ["+loPits.getLsMessageResponse()+"]");
    }
}
