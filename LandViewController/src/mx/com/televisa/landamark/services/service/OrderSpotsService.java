/**
* Project: Integraton Paradigm - Landmark
*
* File: OrderSpotsService.java
*
* Created on: Febrero 23, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.faces.model.SelectItem;

import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.ResponseBreaksDao;
import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesLogRowBean;
import mx.com.televisa.landamark.model.types.LmkIntXmlFilesRowBean;
import mx.com.televisa.landamark.services.jobs.service.OrderSpotsImpCron;
import mx.com.televisa.landamark.services.jobs.service.ParrillasProgramasImpCron;
import mx.com.televisa.landamark.services.sftp.SftpManagment;
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

/** Clase que revisa archivos de orden/spots generados por landmark
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
 */
public class OrderSpotsService {
    public OrderSpotsService() {
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
        ResponseBreaksDao      loResponseBreaksDao = new ResponseBreaksDao();
        boolean                lbProcess = true;
       
        String lsReturn = "Order/spots execute";
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
        loBitBean.setLiIndProcess(liIndProcess);
        loBitBean.setLiNumProcessId(0);
        loBitBean.setLiNumPgmProcessId(0);
        loBitBean.setLsIndEvento("Ejecución de Servicio para Orden/Spots de landmark "+
                                 loInput.getLsServiceName());
        
        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                           loInput.getLiIdUser(), 
                                           loInput.getLsUserName());   
        
        //- Ir a la bd de archivos fisicos y extrer los archivos tipo request que se
        //descartarán vs extraidos de carpeta remota
        String lsWhere = " AND UPPER(NOM_FILE) LIKE UPPER('%.xml') ";// + //Solo archivos xml
            //" AND IND_ESTATUS = 'A'";
        
        List<LmkIntXmlFilesRowBean> laList = 
            loResponseBreaksDao.getAllFilesPending(lsWhere);
    
        //- Ir a carpeta de landmark y extraer nombres de archivo *.brk
        String lsPath = 
            loEntityMappedDao.getGeneralParameter("PATH_SPOTS_INPUT", "SSH_CONNECTION");
        String lsExt = "*.XML";
            
        SftpManagment loSmg = new SftpManagment();
        List<String> laLisSsh = loSmg.getListFileServerSFTP(lsPath, lsExt);
        System.out.println("Numero de archivos extraidos del server ssh ["+laLisSsh.size()+"]");
        List<String> laInputList = new ArrayList<String>();
        if(laList.size() > 0){
            System.out.println("Nombres de archivos extraidos la base de datos["+laList.size()+"]");
            for(LmkIntXmlFilesRowBean loBean : laList){
                int liI = 0;
                lbProcess = true;
                //System.out.println("Buscar archivos ssh en lista de la base de datos");
                while(liI < laLisSsh.size() && lbProcess == true){
                    //- Buscar cada nombre de archivo fisico en el grupo
                    //System.out.println("indexOf: loBean.getLsNomFile()["+loBean.getLsNomFile()+
                      //                 "] VS laLis.get("+liI+")["+laLisSsh.get(liI).indexOf(loBean.getLsNomFile())+"]");
                    if(laLisSsh.get(liI).indexOf(loBean.getLsNomFile()) >= 0){
                        lbProcess = false;//Para que el ciclo sea cortado
                        //System.out.println("COINCIDENCIA ENCONTRADA PARA ("+loBean.getLsNomFile()+")");
                        //Discriminar este registro, ya que ya está procesado
                    }else{
                        laInputList.add(laLisSsh.get(liI));
                    }
                    liI++;
                }
                
            }    
        }else{//Een base de datos no hay ninguno en estatus alta
            System.out.println("No  existen archivos guarddos es la bd");
            laInputList.addAll(laLisSsh);
        }
        
        //Quitar repetidos de la lista:
        laInputList = removesRepeated(laInputList);
        if(laInputList.size() > 0){
            //Con un cron por cada archivo encontrado, ejecutar la lectura, guardado y logica de cada archivo
            for(String lsFileName : laInputList){
                String lsTrigger = lsFileName+getIdBitacora();
                 //Invocar job asincrono simple
                 Scheduler loScheduler;
                 try {
                     loScheduler = new StdSchedulerFactory().getScheduler();
                     JobDetail loJob = 
                         JobBuilder.newJob(OrderSpotsImpCron.class).build();
                     Trigger   loTrigger = 
                         TriggerBuilder.newTrigger().withIdentity(lsTrigger).build();
                     JobDataMap loJobDataMap=  loJob.getJobDataMap();
                     //------------------------------------------
                     loJobDataMap.put("lsIdService", String.valueOf(loInput.getLiIdService())); 
                     loJobDataMap.put("liIdUser", String.valueOf(loInput.getLiIdUser())); 
                     loJobDataMap.put("lsUserName", loInput.getLsUserName()); 
                     loJobDataMap.put("lsTypeProcess", loInput.getLsServiceType());
                     loJobDataMap.put("lsServiceName", loInput.getLsServiceName());
                     loJobDataMap.put("lsPathFiles", loInput.getLsPathFiles());                           
                     loJobDataMap.put("lsIdLogService", String.valueOf(liIdLogService)); 
                     //------------------------------------------
                     loJobDataMap.put("lsFileName", lsFileName); 
                     loJobDataMap.put("lsRequestMaster", String.valueOf(loInput.getLiIdRequest())); 
                     loScheduler.scheduleJob(loJob, loTrigger);
                     loScheduler.start();
                     
                 } catch (Exception loEx) {
                     System.out.println("Error en invocacion de cron final "+loEx.getMessage());
                 }
            }            
            
        }else{
            liIndProcess = 
                new UtilFaces().getIdConfigParameterByName("NoData");//
            loBitBean.setLiIdLogServices(liIdLogService);
            loBitBean.setLiIdService(loInput.getLiIdService());
            loBitBean.setLiIndProcess(liIndProcess);
            loBitBean.setLiNumProcessId(0);
            loBitBean.setLiNumPgmProcessId(0);
            loBitBean.setLsIndEvento("No existen archivos Orden/Spots para leer");
            
            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               loInput.getLiIdUser(), 
                                               loInput.getLsUserName());   
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
     * Quita elementos repetidos de una lista de elementos tipo SelectItem
     * @autor Jorge Luis Bautista Santiago
     * @param taInputList
     * @return List
     */
    public List<String> removesRepeated(List<String> taInputList) {
        List<String> laOutputList = new ArrayList<String>();
        Map              laMap = new HashMap();
        for (int liI = 0; liI < taInputList.size(); liI++) {
            laMap.put(taInputList.get(liI),taInputList.get(liI));
        }
        Iterator         loIterator = laMap.entrySet().iterator();
        while (loIterator.hasNext()) {
            Map.Entry  loEntry = (Map.Entry)loIterator.next();
            laOutputList.add(String.valueOf(loEntry.getValue()));
        }
        return laOutputList;
    }
    
}
