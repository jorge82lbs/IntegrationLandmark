/**
* Project: Integraton Paradigm - Landmark
*
* File: CampaignsAllService.java
*
* Created on: Noviembre 6, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.List;

import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.ServicesParamsDao;
import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesLogRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesParamsRowBean;
import mx.com.televisa.landamark.view.types.BasicInputParameters;
import mx.com.televisa.landamark.view.types.ResponseService;

/** Clase que ejecuta logica o servicio de Lectura de Campañas
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Noviembre 06, 2019, 12:00 pm
 */
public class CampaignsAllService {
    public CampaignsAllService() {
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
       
        String lsReturn = "Campaigns execute";
        
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
        
        loEntityMappedDao.insertSimpleServicesLog(loSlb, 
                                                  loInput.getLiIdUser(), 
                                                  loInput.getLsUserName()
                                                  );
        
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
        Integer                  liIndProcess = 
            loEntityMappedDao.getGeneralParameterID("ExecuteService", 
                                                    "PROCESS_INTEGRATION");
        loBitBean.setLiIdLogServices(liIdLogService);
        loBitBean.setLiIdService(loInput.getLiIdService());
        loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
        loBitBean.setLiNumProcessId(0);
        loBitBean.setLiNumPgmProcessId(0);
        loBitBean.setLsIndEvento("Ejecución de Servicio de Campaigns");
        
        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                           loInput.getLiIdUser(), 
                                           loInput.getLsUserName());                
                                
        liIndProcess = loEntityMappedDao.getGeneralParameterID("ExtractParameters", 
                                                    "PROCESS_INTEGRATION");
        loBitBean.setLiIdLogServices(liIdLogService);
        loBitBean.setLiIdService(loInput.getLiIdService());
        loBitBean.setLiIndProcess(liIndProcess); 
        loBitBean.setLiNumProcessId(0);
        loBitBean.setLiNumPgmProcessId(0);
        loBitBean.setLsIndEvento("Extraer Parámetros para servicio "+loInput.getLsServiceName());
        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                           loInput.getLiIdUser(), 
                                           loInput.getLsUserName());
        // - Extraer parámetros de la tabla
        List<LmkIntServicesParamsRowBean> loParams = 
            loSpDao.getLmkIntServicesParams(loBitBean.getLiIdService());
        
        if( loParams.size() < 2 ){ //FI, FF sin canal(es)
            lbProcess = false;
            liIndProcess = loEntityMappedDao.getGeneralParameterID("ParametersMissing", 
                                                    "PROCESS_INTEGRATION");
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(loInput.getLiIdService());
                    loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento("Parámetros insuficientes para el Servicio (SpotStatus) " +
                                             loInput.getLsServiceName());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                       loInput.getLiIdUser(), 
                                                       loInput.getLsUserName());
            
        }
        else{
            String lsFecInicial = "";
            String lsFecFinal = "";
            for(LmkIntServicesParamsRowBean loBean:loParams){
                if(loBean.getLsIndParameter().equalsIgnoreCase("FECHA_INICIAL")){
                    lsFecInicial = loBean.getLsIndValParameter();
                }
                if(loBean.getLsIndParameter().equalsIgnoreCase("FECHA_FINAL")){
                    lsFecFinal = loBean.getLsIndValParameter();
                }                
            }
            
            //Proceso para guardar parametros en la tabla de log services
            String lsFechasLog = "["+lsFecInicial + ","+lsFecInicial+"]";
            String lsChannelsLog = "";            
           
            loEntityMappedDao.updateParametersServiceLog(liIdLogService, 
                                                         loInput.getLiIdService(), 
                                                         lsFechasLog, 
                                                         lsChannelsLog);
            
            
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
    
    
    /**
     * Obtiene identificador en base al momento capturado en tiempo
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String getId(){
        String     lsResponse = null;
        DateFormat loDf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        lsResponse = loDf.format(new java.util.Date(System.currentTimeMillis()));
        return lsResponse;
    }
    
    
}
