/**
* Project: Integraton Paradigm - Landmark
*
* File: OrderSpotsImpCron.java
*
* Created on: Abril 23, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.jobs.service;

import java.io.File;
import java.io.FileInputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.MappingCatDao;
import mx.com.televisa.landamark.model.daos.OrderSpotsDao;
import mx.com.televisa.landamark.model.daos.ResponseBreaksDao;
import mx.com.televisa.landamark.model.daos.XmlFilesDao;
import mx.com.televisa.landamark.model.types.LmkIntMappingCatRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntXmlFilesRowBean;
import mx.com.televisa.landamark.model.types.LmkLogComercialStatusBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;
import mx.com.televisa.landamark.model.types.inject.LmkSpotsRowBean;
import mx.com.televisa.landamark.services.beans.input.spots.Content;
import mx.com.televisa.landamark.services.beans.input.spots.Spot;
import mx.com.televisa.landamark.services.beans.input.spots.Spots;
import mx.com.televisa.landamark.services.sftp.SftpManagment;
import mx.com.televisa.landamark.util.UtilFaces;
import mx.com.televisa.landamark.util.UtilMails;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/** Clase que ejecuta la lectura, guardado y logica de cada archivo
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Abril 23, 2019, 12:00 pm
 */
public class OrderSpotsImpCron implements Job{
    public OrderSpotsImpCron() {
        super();
    }

    /**
     * Metodo que ejecuta la lectura guardado y logica de cada archivo
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
        String                    lsFileName = loDataMap.getString("lsFileName");
        String                    lsRequestMaster = loDataMap.getString("lsRequestMaster");
        
        String                    lsIdLogService = loDataMap.getString("lsIdLogService");
        
        SftpManagment             loSftpManagment = new SftpManagment();
        EntityMappedDao           loEntityMappedDao = new EntityMappedDao();
        Integer                   liIndProcess = 0;
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
        Integer                   liIdService = Integer.parseInt(lsIdService);
        Integer                   liIdUser = Integer.parseInt(lsIdUser);
        Integer                   liIdLogService = Integer.parseInt(lsIdLogService);
                
        UtilMails                 loUtilMail = new UtilMails();
        //Ir mediante ssh por el archivo a servidor de lanmdark
        String lsPathRemote = 
            loEntityMappedDao.getGeneralParameter("PATH_SPOTS_INPUT", "SSH_CONNECTION");
        
        ResponseUpdDao loResDn = 
            loSftpManagment.downloadFileSFTP(lsPathRemote, lsPathFiles, lsFileName, lsFileName);
        
        if(!loResDn.getLsResponse().equalsIgnoreCase("OK")){
            //Archivo no encontrado
            // Si por alguna razon ya lo borraron, es decir que no fue encontrado, mandar error a bitacora
            liIndProcess = 
                new UtilFaces().getIdConfigParameterByName("NoData");//
            loBitBean.setLiIdLogServices(liIdLogService);
            loBitBean.setLiIdService(liIdService);
            loBitBean.setLiIndProcess(liIndProcess);
            loBitBean.setLiNumProcessId(0);
            loBitBean.setLiNumPgmProcessId(0);
            loBitBean.setLsIndEvento("Archivo no encontrado "+
                                     loResDn.getLsMessage());
            
            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               liIdUser, 
                                               lsUserName);   
            /*
            loUtilMail.buildMailByProcess(liIdLogService, 
                                          Integer.parseInt(lsIdService), 
                                          liIndProcess, 
                                          liIdUser, 
                                          lsUserName,
                                          lsServiceName,
                                          "");*/
            
        }
        else{
            //Validar nombre del archivo
            boolean lbRes = validateNomarch(lsFileName);
            //System.out.println("Validar Nombre del Archivo:["+lbRes+"]");
            
            lbRes = true;
            //System.out.println("Por el momento cambiar a true:["+lbRes+"]");
            
            if(!lbRes){
                liIndProcess = 
                            new UtilFaces().getIdConfigParameterByName("ErrorFileName");//
                loBitBean.setLiIdLogServices(liIdLogService);
                loBitBean.setLiIdService(liIdService);
                loBitBean.setLiIndProcess(liIndProcess);
                loBitBean.setLiNumProcessId(0);
                loBitBean.setLiNumPgmProcessId(0);
                loBitBean.setLsIndEvento("ERROR Nombre de Archivo incorrecto >> "+lsFileName);
                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   liIdUser, 
                                                   lsUserName);  
                /*loUtilMail.buildMailByProcess(liIdLogService, 
                                              Integer.parseInt(lsIdService), 
                                              liIndProcess, 
                                              liIdUser, 
                                              lsUserName,
                                              lsServiceName, 
                                              "");*/
            }
            else{
                //Si el archivo si fue encontrado, entonces guardar copia en carpeta de WL  
                //-- Esto ya esta, al momento de la instruccion            
                //Guardar en base de datos (estatus=P (en paradigm))
                //##################### Insertar Archivo en Base de Datos ############################ 
                try {
                    //System.out.println("leyendo archivo de: ["+lsPathFiles+lsFileName+"]");
                    File loFileInput = new File (lsPathFiles+lsFileName);
                    XmlFilesDao loXmlFilesDao = new XmlFilesDao();
                    LmkIntXmlFilesRowBean loXmlBean = new LmkIntXmlFilesRowBean();
                    FileInputStream loFis = new FileInputStream(loFileInput);
                    
                    //System.out.println("lsRequestMaster["+lsRequestMaster+"] >>> Debe ser el id del LOG");
                    //System.out.println("lsIdService["+lsIdService+"]");
                    //System.out.println("lsIdUser["+lsIdUser+"]");
                    
                    loXmlBean.setLiIdFileXml(0);
                    loXmlBean.setLiIdRequest(liIdLogService);
                    loXmlBean.setLiIdService(liIdService);
                    loXmlBean.setLsNomFile(lsFileName);
                    loXmlBean.setLsIndFileType("REQUEST");
                    loXmlBean.setLsIndServiceType(lsTypeProcess);
                    loXmlBean.setLsIndEstatus("R"); //De Creado
                    loXmlBean.setLsNomUserName(lsUserName);
                    loXmlBean.setLsNomUserPathFile(lsPathFiles);
                    loXmlBean.setLiIdUser(liIdUser);
                    loXmlBean.setLoIndFileStream(loFis);
                    loXmlBean.setLsAttribute1("");
                    loXmlBean.setLsAttribute2("OrdSpotsFileType");
                    // - Guardar archivo en bd
                    ResponseUpdDao loXmlFile = 
                        loXmlFilesDao.insertLmkIntXmlFilesTab(loXmlBean);
                    String lsMessInsert = "";
                    if(loXmlFile.getLsResponse().equalsIgnoreCase("OK")){
                        lsMessInsert = "El Archivo "+lsFileName+" se ha guardado en base de datos";
                    }else{
                        lsMessInsert = "Error " + loXmlFile.getLsMessage() + " al guardar archivo "+
                                       lsFileName+" size: "+loFileInput.getTotalSpace();
                    }
                    //System.out.println("Insertar en bitacora que se ha insertado registro para monitoreo");
                    liIndProcess = 
                                new UtilFaces().getIdConfigParameterByName("InsertFile");//
                    //System.out.println("liIndProcess["+liIndProcess+"]");
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(liIdService);
                    loBitBean.setLiIndProcess(liIndProcess);
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento(lsMessInsert);
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                       liIdUser, 
                                                       lsUserName);                                                
                    
                    //Leer archivo (estatus=W (leido))
                    //Aquí va la lectura del xml, el archivo fisico 
                    //System.out.println("Leer e insertar spots en paradigm");
                    ResponseUpdDao loRes = insertSpotsParadimg(lsPathFiles, lsFileName);
                    if(!loRes.getLsResponse().equalsIgnoreCase("OK")){
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("InsertError");//
                        loBitBean.setLiIdLogServices(liIdLogService);
                        loBitBean.setLiIdService(liIdService);
                        loBitBean.setLiIndProcess(liIndProcess);
                        loBitBean.setLiNumProcessId(0);
                        loBitBean.setLiNumPgmProcessId(0);
                        loBitBean.setLsIndEvento("ERROR al insertar spots >> "+loRes.getLsMessage());
                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                           liIdUser, 
                                                           lsUserName);  
                        
                        //Actualizar estatus de archivo xml, Leido con errores
                        ResponseBreaksDao loResReadFile = new ResponseBreaksDao();
                        loResReadFile.updateEstatusXmlFiles(loXmlFile.getLiAffected(), "M");
                        
                    }
                    else{//Continuar como dice el flujo de Jacobo
                        //System.out.println("Spots de xml insertados en tablas temporales de paradigm.... OK");
                        //Actualizar estatus de archivo xml, leido, en tablas temporales
                        ResponseBreaksDao loResReadFile = new ResponseBreaksDao();
                        loResReadFile.updateEstatusXmlFiles(loXmlFile.getLiAffected(), "W");
                        
                        //Obtener Canal y fecha de acuerdo al nombre del archivo
                        //20190822.- La fecha se obtiene del primer spot, ya devuelto en ResponseUpdDao
                        //            de insertSpotsParadimg
                        String[] laNomarch = lsFileName.split("\\.");
                        String lsStnidNomarch = laNomarch[1];
                        //String lsBcstdtNomarch = laNomarch[2];
                        String lsStnid = getChannelMapped(lsStnidNomarch);
                        String lsBcstdt = loRes.getLsMessage();
                        String lsKeyChannel = lsStnid + " - "+lsBcstdt;
                        try{
                        loResReadFile.updateParametersXmlFiles(loXmlFile.getLiAffected(), 
                                                               "["+lsStnid+","+lsBcstdt+"]"
                                                               );
                        }catch(Exception loEx){
                            //System.out.println("Error al actualizar parametros "+loEx.getMessage());
                        }
                        /*String lsBcstdt = getBcstdtMapped(lsBcstdtNomarch,
                                                          "yyyymmdd",
                                                          "yyyy-mm-dd");*/
                        //System.out.println("####################################");
                        //System.out.println("lsStnid["+lsStnid+"]");
                        //System.out.println("lsBcstdt["+lsBcstdt+"]");
                        //System.out.println("####################################");
                        
                        OrderSpotsDao loOrderSpotsDao = new OrderSpotsDao();
                        // 2)      Ejecutar el SP…. EVENTAS.LMK_VALIDA_SPOTS(STNID, BCSTDT)
                         
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("CallProcedure");//
                        loBitBean.setLiIdLogServices(liIdLogService);
                        loBitBean.setLiIdService(liIdService);
                        loBitBean.setLiIndProcess(liIndProcess);
                        loBitBean.setLiNumProcessId(0);
                        loBitBean.setLiNumPgmProcessId(0);
                        loBitBean.setLsIndEvento("("+loRes.getLiAffected()+" Spots) Ejecucion de LMK_VALIDA_SPOTS("+lsStnid+", "+lsBcstdt+")");
                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                           liIdUser, 
                                                           lsUserName);  
                        //System.out.println("Validando spots con  callLmkValidaSpotsPr");
                        ResponseUpdDao loResValida = 
                             loOrderSpotsDao.callLmkValidaSpotsPr(lsStnid, lsBcstdt);
                        
                        if(!loResValida.getLsResponse().equalsIgnoreCase("OK")){
                            //System.out.println("Error callLmkValidaSpotsPr "+loResValida.getLsMessage());
                            liIndProcess = 
                                        new UtilFaces().getIdConfigParameterByName("ValidateError");//
                            loBitBean.setLiIdLogServices(liIdLogService);
                            loBitBean.setLiIdService(liIdService);
                            loBitBean.setLiIndProcess(liIndProcess);
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("ERROR AL VALIDAR SPOTS("+lsStnid+", "+lsBcstdt+") >> " + 
                                                     loRes.getLsMessage());
                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                               liIdUser, 
                                                               lsUserName);  
                        }
                        else{
                            //  3)      Al término de la ejecución de este SP… validar la tabla … EVENTAS.LMK_SPOTS_STATUS … 
                            // al final esta tabla funciona como la de EVETV de LMK_LOG_COMERCIAL_STATUS donde a través del Canal 
                            // y la Fecha de Transmisión se pondrá el tipo de error que se validó antes de ejecutar el resto de 
                            // la información… Si la tabla tiene un solo registro con el Status Igual a OK, entonces se procede 
                            // al punto 4.. Si hay errores, aquí definiremos por el campo de TIPO .. a quien se los 
                            // enviaremos vía correo.
                            //System.out.println("Validacion OK de LMK_LOG_COMERCIAL_STATUS ");
                            liIndProcess = 
                                        new UtilFaces().getIdConfigParameterByName("ExeProcedure");//
                            loBitBean.setLiIdLogServices(liIdLogService);
                            loBitBean.setLiIdService(liIdService);
                            loBitBean.setLiIndProcess(liIndProcess);
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("Validacion LMK_LOG_COMERCIAL_STATUS("+lsStnid+", "+lsBcstdt+") >> ");
                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                               liIdUser, 
                                                               lsUserName);  
                            Integer liNumOk = 
                                loOrderSpotsDao.validateOkStatusSpots(lsStnid, lsBcstdt);
                            //System.out.println("Numero de spots en OK ["+liNumOk+"]");
                            if(liNumOk == 0){//No  existe ningun registro con OK
                                //System.out.println("Todos los spots contienen incidencias");
                                liIndProcess = 
                                            new UtilFaces().getIdConfigParameterByName("GeneralError");//
                                loBitBean.setLiIdLogServices(liIdLogService);
                                loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                                loBitBean.setLiIndProcess(liIndProcess);
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("Todos los Spots continen incidencias("+lsStnid+", "+lsBcstdt+") >> ");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   liIdUser, 
                                                                   lsUserName);  
                                
                                liIndProcess = 
                                            new UtilFaces().getIdConfigParameterByName("ErrorConfig");//
                                loBitBean.setLiIdLogServices(liIdLogService);
                                loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                                loBitBean.setLiIndProcess(liIndProcess);
                                loBitBean.setLiNumProcessId(liIndProcess);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("Enviar Correo para informar de Incidencias para " +
                                    "incidencias("+lsStnid+", "+lsBcstdt+") ");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   liIdUser, 
                                                                   lsUserName);  
                                //System.out.println("Enviar correo para informar que todo fue con incidencias");
                                try{
                                    
                                    List<LmkLogComercialStatusBean> laList = 
                                        loOrderSpotsDao.getLogComercialStatusKO(lsStnid, lsBcstdt);
                                    
                                    
                                loUtilMail.buildMailByProcessErrSpots(liIdLogService, 
                                                              Integer.parseInt(lsIdService), 
                                                              liIndProcess, 
                                                              liIdUser, 
                                                              lsUserName,
                                                              lsServiceName,
                                                              lsKeyChannel,
                                                              laList);
                                }catch(Exception loEx){
                                    System.out.println("Error al intentar enviar correo "+loEx.getMessage());
                                }
                                //Actualizar estatus de archivo xml, leido, en tablas temporales
                                loResReadFile.updateEstatusXmlFiles(loXmlFile.getLiAffected(), "E");
                                //System.out.println("Cambiando status al archivo");
                                
                            }
                            else{//Se procede al punto 4
                                boolean lbSendMail = true;
                                //  4)      Ejecutar el SP… EVENTAS.LMK_GENERA_SPOTS(STNID, BCSTDT)
                                //System.out.println("Generar spots LMK_GENERA_SPOTS");
                                liIndProcess = 
                                            new UtilFaces().getIdConfigParameterByName("CallProcedure");//
                                loBitBean.setLiIdLogServices(liIdLogService);
                                loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                                loBitBean.setLiIndProcess(liIndProcess);
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("Generar spots LMK_GENERA_SPOTS("+lsStnid+", "+lsBcstdt+")");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   liIdUser, 
                                                                   lsUserName);  
                                
                                ResponseUpdDao loResGeneraSpots = 
                                    loOrderSpotsDao.callLmkGeneraSpotsPr(lsStnid, lsBcstdt);
                                //System.out.println("Resultado de: LMK_GENERA_LOGS "+loResGeneraSpots.getLsResponse());
                                if(!loResGeneraSpots.getLsResponse().equalsIgnoreCase("OK")){
                                    //System.out.println("ERROR LMK_GENERA_SPOTS "+loResGeneraSpots.getLsMessage());
                                    lbSendMail = false;
                                    liIndProcess = 
                                                new UtilFaces().getIdConfigParameterByName("GeneralError");//
                                    loBitBean.setLiIdLogServices(liIdLogService);
                                    loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                                    loBitBean.setLiIndProcess(liIndProcess);
                                    loBitBean.setLiNumProcessId(0);
                                    loBitBean.setLiNumPgmProcessId(0);
                                    loBitBean.setLsIndEvento("ERROR al GENERAR spots >> "+loResGeneraSpots.getLsMessage());
                                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                       liIdUser, 
                                                                       lsUserName);  
                                }                                
                                
                                //  5)      Ejecutar el SP… EVENTAS.LMK_GENERA_LOG(STNID, BCSTDT)
                                liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("CallProcedure");//
                                loBitBean.setLiIdLogServices(liIdLogService);
                                loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                                loBitBean.setLiIndProcess(liIndProcess);
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("Generar LMK_GENERA_LOGS("+lsStnid+", "+lsBcstdt+")");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   liIdUser, 
                                                                   lsUserName);  
                                //System.out.println("Generar LMK_GENERA_LOGS");
                                ResponseUpdDao loResGeneraLogs = 
                                    loOrderSpotsDao.callLmkGeneraLogsPr(lsStnid, lsBcstdt);
                                //System.out.println("Resultado de: LMK_GENERA_LOGS "+loResGeneraLogs.getLsResponse());
                                if(!loResGeneraLogs.getLsResponse().equalsIgnoreCase("OK")){
                                    //System.out.println("ERROR LMK_GENERA_LOGS "+loResGeneraLogs.getLsMessage());
                                    lbSendMail = false;
                                    liIndProcess = 
                                                new UtilFaces().getIdConfigParameterByName("GeneralError");//
                                    loBitBean.setLiIdLogServices(liIdLogService);
                                    loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                                    loBitBean.setLiIndProcess(liIndProcess);
                                    loBitBean.setLiNumProcessId(0);
                                    loBitBean.setLiNumPgmProcessId(0);
                                    loBitBean.setLsIndEvento("ERROR al GENERAR LOGS >> "+loResGeneraLogs.getLsMessage());
                                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                       liIdUser, 
                                                                       lsUserName);  
                                }                                
                                //  6)      Al termino de este último SP se enviará un correo de notificación a la gente de Trafico Log que su Log ha sido procesado correctamente desde Landmark ATS.
                                if(lbSendMail){
                                    //Enviar Correo
                                    //System.out.println("Enviar Correo informando que todo OK");
                                    liIndProcess = 
                                                new UtilFaces().getIdConfigParameterByName("SendEmail");//
                                    loBitBean.setLiIdLogServices(liIdLogService);
                                    loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                                    loBitBean.setLiIndProcess(liIndProcess);
                                    loBitBean.setLiNumProcessId(0);
                                    loBitBean.setLiNumPgmProcessId(0);
                                    loBitBean.setLsIndEvento("Enviar Correo para informar proceso " +
                                        "satisfactorio("+lsStnid+", "+lsBcstdt+") ");
                                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                       liIdUser, 
                                                                       lsUserName);  
                                    
                                    //System.out.println("Enviar correo");
                                    loUtilMail.buildMailByProcess(liIdLogService, 
                                                                  Integer.parseInt(lsIdService), 
                                                                  liIndProcess, 
                                                                  liIdUser, 
                                                                  lsUserName,
                                                                  lsServiceName,
                                                                  lsKeyChannel);
                                    //Actualizar estatus de archivo xml                                    
                                    loResReadFile.updateEstatusXmlFiles(loXmlFile.getLiAffected(), "C");      
                                    //System.out.println("Cambiar estatus al archivo de TODO OK (C)");
                                }else{
                                    //Actualizar estatus de archivo xml
                                    loResReadFile.updateEstatusXmlFiles(loXmlFile.getLiAffected(), "E");   
                                    //System.out.println("Cambiar estatus al archivo a ERROR (E)");
                                }
                                
                                String lsPathReaded = 
                                    loEntityMappedDao.getGeneralParameter("PATH_SPOTS_INPUT_RDD", "SSH_CONNECTION");
                                //Mover archivo a carpeta de revisados
                                try{
                                    loSftpManagment.moveFileSFTP(lsPathReaded, 
                                                       lsPathRemote, 
                                                       lsFileName, 
                                                       lsFileName);
                                }catch(Exception loEx){
                                    System.out.println("Error al mover archivo "+loEx.getMessage());
                                }
                            }
                        }
                        
                    }
                    
                    //mapear de acuerdo a Paradigm
                    //Ejecutar logica que indique jacobo (estatusP (Procesado)) FIN
                    
                }catch(Exception loEx){
                    System.out.println("Error 9857: "+loEx.getMessage());
                    liIndProcess = 
                                new UtilFaces().getIdConfigParameterByName("GeneralError");//
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                    loBitBean.setLiIndProcess(liIndProcess);
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento("ERROR. "+loEx.getMessage());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                       liIdUser, 
                                                       lsUserName);
                    
                }
            }        
        }                
    }
    
    /**
     * Valida el nombre del archivo con formato preestablecido
     * @autor Jorge Luis Bautista Santiago
     * @param lsFileName
     * @return boolean
     */
    public boolean validateNomarch(String lsFileName){
        boolean lbResponse = true;
        String[] laNameArr = lsFileName.split("\\.");
        //System.out.println("laNameArr.length["+laNameArr.length+"]");
        if(laNameArr.length != 5){
            lbResponse = false;
        }
        return lbResponse;
    }
    
    /**
     * Obtiene el canal paradigm de acuerdo al mapeo
     * @autor Jorge Luis Bautista Santiago
     * @param tsChannel
     * @return String
     */
    public String getChannelMapped(String tsChannel){
        String lsChannel = "";
        MappingCatDao loMcd = new MappingCatDao();
        String lsWhere = " VAL_VALUE_DESTINY = '"+tsChannel+"'";
        List<LmkIntMappingCatRowBean> laArr = loMcd.getLmkIntServicesParams(lsWhere);
        if(laArr.size() > 0){
            lsChannel = laArr.get(0).getLsValValueOrigin();
        }
        return lsChannel;
    }
    
    /**
     * Obtiene la fecha en un formato especifico, a partir de otra mascara
     * @autor Jorge Luis Bautista Santiago
     * @param tsBcstdt
     * @param tsMaskInput
     * @param tsMaskOutput
     * @return String
     */
    public String getBcstdtMapped(String tsBcstdt, 
                                  String tsMaskInput,
                                  String tsMaskOutput){
        String           lsDateAsString = "";
        SimpleDateFormat lsParser = new SimpleDateFormat(tsMaskInput);        
        SimpleDateFormat lsFormatter = new SimpleDateFormat(tsMaskOutput);

        try {
            Date ltDate = lsParser.parse(tsBcstdt);
            lsDateAsString = lsFormatter.format(ltDate);
        } catch (ParseException loEx) {
            ;
        }
        return lsDateAsString;
    }
    
    /**
     * Inserta y valida los spots en paradigm tablas temporales
     * @autor Jorge Luis Bautista Santiago
     * @param tsFilePath
     * @param tsFileName
     * @return ResponseUpdDao
     */
    public ResponseUpdDao insertSpotsParadimg(String tsFilePath, String tsFileName){
        ResponseUpdDao loResponseUpdDao = new ResponseUpdDao();
        //System.out.println("-------------------- SPOTS -------------------------");    
        try {  
            String lsFileRead = tsFilePath+tsFileName;
            //System.out.println("Se debe de leer de : ["+lsFileRead+"]");            
            //lsFileRead = "C:\\Comercializacion\\Landmark\\AppUtilConsole\\Client\\SP.9C9C.20190329.90639.XML";
            //System.out.println("pero por el momeneto se LEE de "+lsFileRead);
            JAXBContext context = JAXBContext.newInstance(Spots.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Spots loSpots = (Spots)unmarshaller.unmarshal(new File(lsFileRead));
            //System.out.println("numero de spots: "+loSpots.getSpot().size());    
            //System.out.println("1)  Se insertan los datos en la tabla EVENTAS.LMK_SPOTS \n( los últimos 8 datos de esta tabla se dejan en blanco porque serán usados por el procedimiento para ligar información de Paradigm)");    
            OrderSpotsDao loOrderSpotsDao = new OrderSpotsDao();
            int liI = 0;
            boolean lbSpots = true;
            String lsFecha = "";
            List<LmkSpotsRowBean> laSpotsIns = new ArrayList<LmkSpotsRowBean>();
            while(liI < loSpots.getSpot().size() && lbSpots == true ){
                Spot loSpot = loSpots.getSpot().get(liI);
                if(liI == 0){
                    lsFecha = loSpot.getScheduleDate();
                    //System.out.println("Fecha para procesar: ["+lsFecha+"]");
                }
                LmkSpotsRowBean loLmkSpotsRowBean = new LmkSpotsRowBean();
                loLmkSpotsRowBean = getLmkSpotsRowBeanBySpot(loSpot);
                //Insertar en tabla:
                ResponseUpdDao loRes = loOrderSpotsDao.insertLmkSpot(loLmkSpotsRowBean);
                laSpotsIns.add(loLmkSpotsRowBean);
                if(!loRes.getLsResponse().equalsIgnoreCase("OK")){
                    lbSpots = false;
                }
                liI++;
            }
            
            //for(Spot loSpot : loSpots.getSpot()){
              //LmkSpotsRowBean loLmkSpotsRowBean = new LmkSpotsRowBean();
              //loLmkSpotsRowBean = getLmkSpotsRowBeanBySpot(loSpot);
              //loOrderSpotsDao.insertLmkSpot(loLmkSpotsRowBean);
            //}
            if(!lbSpots){
                loResponseUpdDao.setLiAffected(0);
                loResponseUpdDao.setLsMessage("ERROR");
                loResponseUpdDao.setLsResponse("ERROR");
                
                //Eliminar spots insertados: laSpotsIns
                for(LmkSpotsRowBean loLmkSpotsRowBean : laSpotsIns){                  
                  loOrderSpotsDao.deleteLmkSpotsInserted(loLmkSpotsRowBean);
                }
                
            }else{
                loResponseUpdDao.setLiAffected(loSpots.getSpot().size());
                loResponseUpdDao.setLsResponse("OK");
                loResponseUpdDao.setLsMessage(lsFecha); //Fecha para procesar
            }
            
            
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            loResponseUpdDao.setLiAffected(0);
            loResponseUpdDao.setLsMessage(e.getMessage());
            loResponseUpdDao.setLsResponse("ERROR");
        }
        return loResponseUpdDao;
    }
    
    /**
     * Obtiene el spot en formato de paradigm, a partir del spot del xml
     * @autor Jorge Luis Bautista Santiago
     * @param toSpot
     * @return LmkSpotsRowBean
     */
    public LmkSpotsRowBean getLmkSpotsRowBeanBySpot(Spot toSpot){
        LmkSpotsRowBean loSpotsRowBean = new LmkSpotsRowBean();
        
        String lsScheduleDate = 
            toSpot.getScheduleDate() == null ? "null" : 
            toSpot.getScheduleDate();        
        
        String lsBreakNominalTime =            
            toSpot.getStartTime() == null ? "null" : 
            toSpot.getStartTime(); 
        
        Integer liBreakNumber = 
            toSpot.getBreakNumber() == null ? null : 
            Integer.parseInt(toSpot.getBreakNumber());
                
        Integer liSpotNumber =  
            toSpot.getSpotNumber() == null ? null : 
            Integer.parseInt(toSpot.getSpotNumber()); 
            
        String lsSpotDuration =             
            toSpot.getSpotDuration() == null ? "null" : 
            toSpot.getSpotDuration();      
         
        String lsProductCode =             
            toSpot.getProductKey() == null ? "null" : 
            toSpot.getProductKey();
        
        Integer liSequenceNumber =             
            toSpot.getSequenceNumber() == null ? null : 
            Integer.parseInt(toSpot.getSequenceNumber()); 
        
        Integer liMultiPartMember =            
            toSpot.getSequenceNumber() == null ? null : 
            Integer.parseInt(toSpot.getSequenceNumber()); 
        
        String lsCommercialTitle = 
            toSpot.getProductName() == null ? "null" : 
            toSpot.getProductName().replace("'", "''");    
                
        String lsBreakType =  
            toSpot.getBreakType() == null ? "null" : 
            toSpot.getBreakType(); 
       
        String lsSecondary = 
            toSpot.getSecondary() == null ? "null" : 
            toSpot.getSecondary(); 
       
        String lsMobilityCode =          
            toSpot.getMobilityCode() == null ? "null" : 
            toSpot.getMobilityCode();
        
        String lsRootClassCode =             
            toSpot.getRootClashCode() == null ? "null" : 
            toSpot.getRootClashCode().replace("'", "''");
        
        String lsClashCode = 
            toSpot.getClashCode() == null ? "null" : 
            toSpot.getClashCode().replace("'", "''");
        
        String lsClashDescription =             
            toSpot.getClashDescription() == null ? "null" : 
            toSpot.getClashDescription().replace("'", "''");
        
        String lsNativeCommercialTitle = "null";
        
        String lsCloseCaption =          
            toSpot.getCloseCaption() == null ? "null" : 
            toSpot.getCloseCaption();
        
        String lsCopyType = "null";
        
        Integer liStartPrice =           
            toSpot.getStartPrice() == null ? null : 
            Integer.parseInt(toSpot.getStartPrice());
        
        Integer liPsdPrice =             
            toSpot.getPSDPrice() == null ? null : 
            Integer.parseInt(toSpot.getPSDPrice());
        
        String lsSpotType =  
            toSpot.getSpotSalesClassification() == null ? "null" : 
            toSpot.getSpotSalesClassification();    
        
        String lsAdvertiser =             
            toSpot.getAdvertiserName() == null ? "null" : 
            toSpot.getAdvertiserName().replace("'", "''");    
        
        Integer liCampaignNumber =            
            toSpot.getContractNumber() == null ? null : 
            Integer.parseInt(toSpot.getContractNumber());
        
        String lsBusinessTypeCode =             
            toSpot.getBusinessTypeCode() == null ? "null" : 
            toSpot.getBusinessTypeCode();
               
        String lsBonusSpot =             
            toSpot.getBonusSpot() == null ? "null" : 
            toSpot.getBonusSpot();    
        
        String lsAdvertiserId =           
            toSpot.getAdvertiserID() == null ? "null" : 
            toSpot.getAdvertiserID();
        
        String lsAgencyId =          
            toSpot.getAgencyID() == null ? "null" : 
            toSpot.getAgencyID();
        
        String lsAgencyName = 
            toSpot.getAgencyName() == null ? null : 
            toSpot.getAgencyName().replace("'", "''");;
          
        String lsProgrammeName =             
            toSpot.getProgrammeName() == null ? "null" : 
            toSpot.getProgrammeName().replace("'", "''");                   
        
        String lsBookingPosition = "null";
        
        String lsAdvid = "null";        
            
        String lsAgyid = "null";
        
        Integer liOrdid = null;
        
        Integer liOrdlnid = null;
        
        String lsStnid = "null";
        
        String lsPgmid = "null";
        
        Integer liBrkdtid = null;
        
        Integer liMstlogedtid = null;
        
        String lsHouseNumber = null;
        String lsIndustryCode = null;
        String lsCopyComment = null;
        
        String lsSalesArea = 
            toSpot.getSalesArea() == null ? "null" : 
            toSpot.getSalesArea();        
        
             
        loSpotsRowBean.setLsScheduledDate(lsScheduleDate);
        loSpotsRowBean.setLsBreakNominalTime(lsBreakNominalTime);
        loSpotsRowBean.setLiBreakNumber(liBreakNumber);
        loSpotsRowBean.setLsBreakType(lsBreakType);
        loSpotsRowBean.setLsSecondary(lsSecondary);
        loSpotsRowBean.setLiSpotNumber(liSpotNumber);
        loSpotsRowBean.setLiCampaignNumber(liCampaignNumber);
        loSpotsRowBean.setLsSpotDuration(lsSpotDuration);
        loSpotsRowBean.setLsSpotType(lsSpotType);
        loSpotsRowBean.setLiSequenceNumber(liSequenceNumber);
        loSpotsRowBean.setLsProductCode(lsProductCode);
        loSpotsRowBean.setLsCommercialTitle(lsCommercialTitle);
        loSpotsRowBean.setLsBonusSpot(lsBonusSpot);
        loSpotsRowBean.setLsBusinessTypeCode(lsBusinessTypeCode);
        loSpotsRowBean.setLsRootClassCode(lsRootClassCode);
        loSpotsRowBean.setLsClashCode(lsClashCode);
        loSpotsRowBean.setLsClashDescription(lsClashDescription);
        loSpotsRowBean.setLsCloseCaption(lsCloseCaption);
        loSpotsRowBean.setLsMobilityCode(lsMobilityCode);
        loSpotsRowBean.setLiStartPrice(liStartPrice);
        loSpotsRowBean.setLiPsdPrice(liPsdPrice);
        loSpotsRowBean.setLsProgrammeName(lsProgrammeName);
        loSpotsRowBean.setLsAdvertiserId(lsAdvertiserId);
        loSpotsRowBean.setLsAdvertiser(lsAdvertiser);
        loSpotsRowBean.setLsAgencyId(lsAgencyId);
        loSpotsRowBean.setLsAgencyName(lsAgencyName);
        loSpotsRowBean.setLiMultiPartMember(liMultiPartMember);
        loSpotsRowBean.setLsBookingPosition(lsBookingPosition);
        loSpotsRowBean.setLsNativeCommercialTitle(lsNativeCommercialTitle);
        loSpotsRowBean.setLsCopyType(lsCopyType);
        loSpotsRowBean.setLsAdvid(lsAdvid);
        loSpotsRowBean.setLsAgyid(lsAgyid);
        loSpotsRowBean.setLiOrdid(liOrdid);
        loSpotsRowBean.setLiOrdlnid(liOrdlnid);
        loSpotsRowBean.setLsStnid(lsStnid);
        loSpotsRowBean.setLsPgmid(lsPgmid);
        loSpotsRowBean.setLiBrkdtid(liBrkdtid);
        loSpotsRowBean.setLiMstlogedtid(liMstlogedtid);
        
        loSpotsRowBean.setLsSalesArea(lsSalesArea);
        
        
        if(toSpot.getContents() != null){
            for(Content loContent : toSpot.getContents().getContent()){
                /*System.out.println("### "+loContent.getAspectRatio());    
                System.out.println("### "+loContent.getCopyComment());    
                System.out.println("### "+loContent.getExpirationDate());    
                System.out.println("### "+loContent.getFirstAirDate());    
                System.out.println("### "+loContent.getHouseNumber());    
                System.out.println("### "+loContent.getIndustryCode());    
                System.out.println("### "+loContent.getPrimaryCopy());   
                */
                lsHouseNumber = loContent.getHouseNumber();
                lsIndustryCode = loContent.getIndustryCode();
                lsCopyComment = loContent.getCopyComment() == null ? null : 
                    loContent.getCopyComment().replace("'", "''");
                
                loSpotsRowBean.setLsHouseNumber(lsHouseNumber);
                loSpotsRowBean.setLsIndustryCode(lsIndustryCode);
                loSpotsRowBean.setLsCopyComments(lsCopyComment);
                
            }
        }
        
        return loSpotsRowBean;
    }
    
}
