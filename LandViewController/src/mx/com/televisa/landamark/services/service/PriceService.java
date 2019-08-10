/**
* Project: Integraton Paradigm - Landmark
*
* File: PriceService.java
*
* Created on: Julio 29, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/

package mx.com.televisa.landamark.services.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.ServicesParamsDao;
import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesLogRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesParamsRowBean;
import mx.com.televisa.landamark.services.jobs.service.PriceImpCron;
import mx.com.televisa.landamark.util.UtilFaces;
import mx.com.televisa.landamark.view.types.BasicInputParameters;
import mx.com.televisa.landamark.view.types.ResponseService;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/** Clase que ejecuta logica o servicio de Actualizacion de Precios a Landmark
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Julio 29, 2019, 12:00 pm
 */
public class PriceService {
    public PriceService() {
        super();
    }
    
    /**
     * Funcion principal de la clase
     * @autor Jorge Luis Bautista Santiago
     * @param loInput
     * @return ResponseService
     */
    public ResponseService executeService(BasicInputParameters loInput){
        
        ResponseService        loResponseService = new ResponseService();
        EntityMappedDao        loEntityMappedDao = new EntityMappedDao();
        ServicesParamsDao      loSpDao = new ServicesParamsDao();
        boolean                lbProcess = true;
       
        String lsReturn = "Actualizacion de Precios execute";
        loResponseService.setLiIdRequest(loInput.getLiIdRequest());
        loResponseService.setLiIdService(loInput.getLiIdService());
        loResponseService.setLiIdUser(loInput.getLiIdUser());
        loResponseService.setLsMessageResponse(lsReturn);
        loResponseService.setLsServiceType(loInput.getLsServiceType());
        loResponseService.setLsMessageResponse(lsReturn);
        loResponseService.setLsUserName(loInput.getLsUserName());
        //Obtener idLog service de la tabla 
        Integer                   liIdLogService = new ViewObjectDao().getMaxIdParadigm("Log") + 1;
        LmkIntServicesLogRowBean loSlb = new LmkIntServicesLogRowBean();
        loSlb.setLiIdLogServices(liIdLogService);
        loSlb.setLiIdService(loInput.getLiIdService());
        loSlb.setLiIndProcess(0);
        loSlb.setLiNumPgmProcessId(loInput.getLiIdRequest());
        loSlb.setLiNumProcessId(loInput.getLiIdRequest());
        loSlb.setLiNumUser(loInput.getLiIdUser());
        loSlb.setLsIndEstatus("1");
        loSlb.setLsIndResponse("N");
        loSlb.setLsIndServiceType(loInput.getLsServiceType());
        loSlb.setLsMessage("Execute "+loInput.getLsServiceName());
        loSlb.setLsUserName(loInput.getLsUserName());
        loSlb.setLiIdUser(loInput.getLiIdUser());
        
        new UtilFaces().insertLogServiceService(loSlb);
        
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
        Integer                  liIndProcess = 
            new UtilFaces().getIdConfigParameterByName("ExecuteService");//
        loBitBean.setLiIdLogServices(liIdLogService);
        loBitBean.setLiIdService(loInput.getLiIdService());
        loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
        loBitBean.setLiNumProcessId(0);
        loBitBean.setLiNumPgmProcessId(0);
        loBitBean.setLsIndEvento("Ejecución de Servicio de Actualizacion de Precios");
        
        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                           loInput.getLiIdUser(), 
                                           loInput.getLsUserName());                
                                
        liIndProcess = 
            new UtilFaces().getIdConfigParameterByName("ExtractParameters");//
        loBitBean.setLiIdLogServices(liIdLogService);
        loBitBean.setLiIdService(loInput.getLiIdService());
        loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
        loBitBean.setLiNumProcessId(0);
        loBitBean.setLiNumPgmProcessId(0);
        loBitBean.setLsIndEvento("Extraer Parámetros para servicio "+loInput.getLsServiceName());
        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                           loInput.getLiIdUser(), 
                                           loInput.getLsUserName());
        // - Extraer parámetros de la tabla
        List<LmkIntServicesParamsRowBean> loParams = 
            loSpDao.getLmkIntServicesParams(loBitBean.getLiIdService());
        
        if( loParams.size() < 3 ){ //FI, FF y al menos un canal
            lbProcess = false;
            liIndProcess = 
                        new UtilFaces().getIdConfigParameterByName("ParametersMissing");//
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(loInput.getLiIdService());
                    loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento("Parámetros insuficientes para el Servicio (Actualizacion de Precios " +
                                             loInput.getLsServiceName());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                       loInput.getLiIdUser(), 
                                                       loInput.getLsUserName());
            
        }
        else{
            String lsFecInicial = "";
            String lsFecFinal = "";
            List<String> laChannels = new ArrayList<String>();
            for(LmkIntServicesParamsRowBean loBean:loParams){
                if(loBean.getLsIndParameter().equalsIgnoreCase("FECHA_INICIAL")){
                    lsFecInicial = loBean.getLsIndValParameter();
                }
                if(loBean.getLsIndParameter().equalsIgnoreCase("FECHA_FINAL")){
                    lsFecFinal = loBean.getLsIndValParameter();
                }
                if(!loBean.getLsIndParameter().equalsIgnoreCase("FECHA_INICIAL") &&
                !loBean.getLsIndParameter().equalsIgnoreCase("FECHA_FINAL")){
                     if(loBean.getLsIndValParameter().equalsIgnoreCase("1")){
                         laChannels.add(loBean.getLsIndParameter());
                     }
                }
                
            }
            
            //Proceso para guardar parametros en la tabla de log services
            String lsFechasLog = "["+lsFecInicial + ","+lsFecInicial+"]";
            String lsChannelsLog = "";
            for(String lsChannel : laChannels){
                lsChannelsLog += lsChannel+",";
            }
            loEntityMappedDao.updateParametersServiceLog(liIdLogService, 
                                                         loInput.getLiIdService(), 
                                                         lsFechasLog, 
                                                         lsChannelsLog);
            
            
            for(String lsChannel : laChannels){
                String lsTrigger = lsChannel+getIdBitacora();
                 //Invocar job asincrono simple
                 Scheduler loScheduler;
                 try {
                     loScheduler = new StdSchedulerFactory().getScheduler();
                     JobDetail loJob = 
                         JobBuilder.newJob(PriceImpCron.class).build();
                     Trigger   loTrigger = 
                         TriggerBuilder.newTrigger().withIdentity(lsTrigger).build();
                     JobDataMap loJobDataMap=  loJob.getJobDataMap();
                     //------------------------------------------
                     loJobDataMap.put("lsIdService", String.valueOf(loInput.getLiIdService())); 
                     loJobDataMap.put("lsIdUser", String.valueOf(loInput.getLiIdUser())); 
                     loJobDataMap.put("lsUserName", loInput.getLsUserName()); 
                     loJobDataMap.put("lsTypeProcess", loInput.getLsServiceType());
                     loJobDataMap.put("lsServiceName", loInput.getLsServiceName());
                     loJobDataMap.put("lsPathFiles", loInput.getLsPathFiles());                           
                     loJobDataMap.put("lsIdLogService", String.valueOf(liIdLogService)); 
                     //------------------------------------------
                     loJobDataMap.put("lsFecInicial", lsFecInicial); 
                     loJobDataMap.put("lsFecFinal", lsFecFinal); 
                     loJobDataMap.put("lsIdChannel", lsChannel); 
                     loScheduler.scheduleJob(loJob, loTrigger);
                     loScheduler.start();
                     
                 } catch (Exception loEx) {
                     System.out.println("Error en invocacion de cron final "+loEx.getMessage());
                 }
            }
        }
                                
        return loResponseService;
    }

    /**
     * Obtiene, en base a la fecha, el id_paradigm a manejar en intergracion
     * @autor Jorge Luis Bautista Santiago     
     * @return String
     */
    public String getIdBitacora(){
        String lsResponse = null;
        DateFormat loDf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        lsResponse = loDf.format(new java.util.Date(System.currentTimeMillis()));
        return lsResponse;
    }

}
