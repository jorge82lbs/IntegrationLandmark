/**
* Project: Integraton Paradigm - Landmark
*
* File: ParrillasProgramasService.java
*
* Created on: Febrero 23, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.service;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;

import java.io.InputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.ParrillasProgramasDao;
import mx.com.televisa.landamark.model.daos.ServicesParamsDao;
import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.model.daos.XmlFilesDao;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesLogRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesParamsRowBean;
import mx.com.televisa.landamark.model.types.LmkIntXmlFilesRowBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;
import mx.com.televisa.landamark.model.types.extract.LmkBrkBreakRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkBrkChannelHeaderRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkBrkChannelTrailerRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkBrkFileHeaderRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkBrkFileTrailerRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkProgFileTrailerRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkProgRowBean;
import mx.com.televisa.landamark.services.jobs.service.ParrillasProgramasImpCron;
import mx.com.televisa.landamark.util.UtilFaces;
import mx.com.televisa.landamark.view.types.BasicInputParameters;
import mx.com.televisa.landamark.view.types.LmkDynaParameters;
import mx.com.televisa.landamark.view.types.MapDynaParameters;
import mx.com.televisa.landamark.view.types.ResponseService;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import utils.system;

/** Clase que ejecuta logica o servicio de Parrillas Programas a Landmark
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
 */
public class ParrillasProgramasService {
    public ParrillasProgramasService() {
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
        ParrillasProgramasDao  loPpDao = new ParrillasProgramasDao();
        boolean                lbProcess = true;
        
        String ls2Can = "";
        String ls5Can = "";
        String ls9Can = "";
        String lsCliente = "";                
        
        String lsReturn = "Parrillas Programas execute";
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
            new UtilFaces().getIdConfigParameterByName("ExeProcedure");//ExtractData
        loBitBean.setLiIdLogServices(liIdLogService);
        loBitBean.setLiIdService(loInput.getLiIdService());
        loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
        loBitBean.setLiNumProcessId(0);
        loBitBean.setLiNumPgmProcessId(0);
        loBitBean.setLsIndEvento("Ejecución de Servicio de Breaks y Programas a landmark");
        
        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                           loInput.getLiIdUser(), 
                                           loInput.getLsUserName());                
                                
        liIndProcess = 
            new UtilFaces().getIdConfigParameterByName("ExtractData");//
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
                        new UtilFaces().getIdConfigParameterByName("GeneralError");//ExtractData
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(loInput.getLiIdService());
                    loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento("Parámetros insuficientes para el Servicio "+loInput.getLsServiceName());
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
            
            for(String lsChannel : laChannels){
                String lsTrigger = lsChannel+getIdBitacora();
                 //Invocar job asincrono simple
                 Scheduler loScheduler;
                 try {
                     loScheduler = new StdSchedulerFactory().getScheduler();
                     JobDetail loJob = 
                         JobBuilder.newJob(ParrillasProgramasImpCron.class).build();
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
            // - Leer Tablas involucradas
                
                // Para el BREAK
                ////////// FILE HEADER RECORD
                //loPpDao
                //List<LmkBrkFileHeaderRowBean> laBfhrb = loPpDao.getBrkFileHeader();
                
                ////////// CHANNEL HEADER RECORD
                //List<LmkBrkChannelHeaderRowBean> laChrb = loPpDao.getBrkChannelHeader();
                
                ////////// BREAK RECORD
                //List<LmkBrkBreakRowBean> laBrb = loPpDao.getBrkBreak();
                
                ////////// CHANNEL TRAILER RECORD
                //List<LmkBrkChannelTrailerRowBean> laCtrb = loPpDao.getBrkChannelTrailer();
                
                ////////// FILE TRAILER RECORD
                //List<LmkBrkFileTrailerRowBean> laFtrb = loPpDao.getBrkFileTrailer();
                
                // Para el PROGRAM
                ////////// Programme 
                //List<LmkProgRowBean> laProgrb = loPpDao.getProgProgramme();
                
                ////////// File Trailer
                //List<LmkProgFileTrailerRowBean> laPrgTrb = loPpDao.getProgProgrammeTrailer();
                
                // - Armar y creear archivo, convertir de acuerdo al mapeo
                /*File loFile = getPlainFileBreak(loInput.getLsPathFiles());
                liIndProcess = 
                            new UtilFaces().getIdConfigParameterByName("Execute");
                        loBitBean.setLiIdLogServices(liIdLogService);
                        loBitBean.setLiIdService(loInput.getLiIdService());
                        loBitBean.setLiIndProcess(liIndProcess);
                        loBitBean.setLiNumProcessId(0);
                        loBitBean.setLiNumPgmProcessId(0);
                        loBitBean.setLsIndEvento("Archivo "+loFile.getName()+" Creado para servicio "+loInput.getLsServiceName());
                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                           loInput.getLiIdUser(), 
                                                           loInput.getLsUserName());
                try {
                    XmlFilesDao loXmlFilesDao = new XmlFilesDao();
                    LmkIntXmlFilesRowBean loXmlBean = new LmkIntXmlFilesRowBean();
                    FileInputStream loFis = new FileInputStream(loFile);
                    
                    loXmlBean.setLiIdFileXml(0);
                    loXmlBean.setLiIdRequest(loInput.getLiIdRequest());
                    loXmlBean.setLiIdService(loInput.getLiIdService());
                    loXmlBean.setLsNomFile(loFile.getName());
                    loXmlBean.setLsIndFileType("Response");
                    loXmlBean.setLsIndServiceType(loInput.getLsServiceType());
                    loXmlBean.setLsIndEstatus("1");
                    loXmlBean.setLsNomUserName(loInput.getLsUserName());
                    loXmlBean.setLsNomUserPathFile(loInput.getLsPathFiles());
                    loXmlBean.setLiIdUser(loInput.getLiIdUser());
                    loXmlBean.setLoIndFileStream(loFis);
                    // - Guardar archivo en bd
                    loXmlFilesDao.insertLmkIntXmlFilesTab(loXmlBean);
                    
                    liIndProcess = 
                                new UtilFaces().getIdConfigParameterByName("InsertCtrlTable");
                            loBitBean.setLiIdLogServices(liIdLogService);
                            loBitBean.setLiIdService(loInput.getLiIdService());
                            loBitBean.setLiIndProcess(liIndProcess);
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("Guardando Archivo "+loFile.getName()+" En base de datos");
                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                               loInput.getLiIdUser(), 
                                                               loInput.getLsUserName());
                    
                    
                } catch (FileNotFoundException e) {
                    System.out.println("Error al convertir File en FileInputStream: "+e.getMessage());
                    liIndProcess = 
                                new UtilFaces().getIdConfigParameterByName("GeneralError");
                            loBitBean.setLiIdLogServices(liIdLogService);
                            loBitBean.setLiIdService(loInput.getLiIdService());
                            loBitBean.setLiIndProcess(liIndProcess);
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("No es posible guardar el Archivo: "+loFile.getName()+e.getMessage());
                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                               loInput.getLiIdUser(), 
                                                               loInput.getLsUserName());
                }*/
                // - Enviar archivo a ruta FTP
        }
                                
        return loResponseService;
    }
    
    public File getPlainFileBreak(String tsPath){        
            
        File loFileResponse = new File(tsPath+"files\\TestBreak.txt");
        System.out.println("Ruta: "+loFileResponse.getPath());
        try {
            FileWriter loWriter = new FileWriter(loFileResponse, true);
            
            loWriter.write("120171027164331");
            loWriter.write("\r\n");
            loWriter.write("2C2C2120161107");
            loWriter.write("\r\n");
            loWriter.write("3C2C220161107060807150CMC00049");
            loWriter.write("\r\n");
            loWriter.write("3C2C220161107062808150CMC00051");
            loWriter.write("\r\n");
            loWriter.write("3C2C220161107063839030NCC00172");
            loWriter.write("\r\n");
            loWriter.write("400000266");
            loWriter.write("\r\n");
            loWriter.write("500000268");
            loWriter.write("\r\n");
            
            loWriter.close();
        }
        catch (Exception e) {
            System.out.println("Error al escribir "+e.getMessage());
        }
        return loFileResponse;
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
