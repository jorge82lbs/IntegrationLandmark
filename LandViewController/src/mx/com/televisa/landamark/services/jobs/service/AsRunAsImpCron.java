/**
* Project: Integraton Paradigm - Landmark
*
* File: AsRunAsImpCron.java
*
* Created on: Julio 29, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.jobs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.com.televisa.landamark.model.daos.AsRunAsDao;
import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.XmlFilesDao;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntXmlFilesRowBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;
import mx.com.televisa.landamark.services.sftp.SftpManagment;
import mx.com.televisa.landamark.util.UtilFaces;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/** Clase que ejecuta logica o servicio de As Run As
 * por canal
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Julio 29, 2019, 12:00 pm
 */
public class AsRunAsImpCron implements Job{
    public AsRunAsImpCron() {
        super();
    }
    

    @Override
    public void execute(JobExecutionContext toJobExecutionContext) throws JobExecutionException {        
        JobDataMap                loDataMap = toJobExecutionContext.getJobDetail().getJobDataMap();
        String                    lsIdService = loDataMap.getString("lsIdService");
        String                    lsIdUser = loDataMap.getString("lsIdUser");
        String                    lsUserName = loDataMap.getString("lsUserName");
        String                    lsTypeProcess = loDataMap.getString("lsTypeProcess");
        String                    lsServiceName = loDataMap.getString("lsServiceName");
        String                    lsPathFiles = loDataMap.getString("lsPathFiles");
        String                    lsIdLogService = loDataMap.getString("lsIdLogService");
                        
        String                    lsFecInicial = loDataMap.getString("lsFecInicial");
        String                    lsFecFinal = loDataMap.getString("lsFecFinal");
        String                    lsChannel = loDataMap.getString("lsIdChannel");
        Integer                   liIndProcess = 0;
        EntityMappedDao           loEntityMappedDao = new EntityMappedDao();
        
        Integer                   liIdLogService = Integer.parseInt(lsIdLogService);
        Integer                   liIdService = Integer.parseInt(lsIdService);
        Integer                   liIdUser = Integer.parseInt(lsIdUser);
        AsRunAsDao                loAsRunAsDao = new AsRunAsDao();
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
        String lsParametersClass = lsChannel+","+lsFecInicial+","+lsFecFinal;
        //System.out.println("["+lsChannel+"]Ejecucion de Cron (As Run As) >> ["+new Date()+"]");
        
        //System.out.println("Logica de actualizacion de precios por canal configurado["+lsChannel+"]");
        
        String lsKey = lsChannel + "-" + lsFecInicial;        
        //#################### Agregar logica del servicio ##########################
        //1)      Tienes que censar como en el servicio de Log Certificado de Erandi que el Canal y el dia tenga el candado con el mismo query.
        Integer liFlag = 
            loAsRunAsDao.getFlagInsertLogCertificado(lsFecInicial, 
                                                     lsChannel
                                                    );  
        liIndProcess = new UtilFaces().getIdConfigParameterByName("FlagReconComplete");
        
        loBitBean.setLiIdLogServices(liIdLogService);
        loBitBean.setLiIdService(liIdService);
        loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
        loBitBean.setLiNumProcessId(0);
        loBitBean.setLiNumPgmProcessId(0);
        loBitBean.setLsIndEvento(lsKey + ": Bandera Log Certificado RECON COMPLETE[" + liFlag + "]");
        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                           liIdUser, 
                                           lsUserName);
        
        if(liFlag > 0){            
//###################################################################################################
            List<String> loFechas = loAsRunAsDao.getFechasPorCanalByEvetv(lsChannel, lsFecInicial);
//###################################################################################################                         
            for(String lsDateYyyyMmDd : loFechas){//Itera entre todas las fechas generadas   
                String  lsFechaQuery = buildDateYYYYMMDD(lsDateYyyyMmDd, "MM/DD/YY"); 
                System.out.println("Ejecutando por Canal["+lsChannel+"] FechafORMATED["+lsFechaQuery+"]");
                //Verificar en EVETV_LOG_CERTIFICADO_PROCESADO si se puede procesar STATUS = 1
                Integer liRes = loAsRunAsDao.getFlagProcess(lsChannel, lsFechaQuery);//Discriminar las fechas que ya fueron procesadas
                if(liRes > 0){            
                    //2)      Ejecutar el SP :  CALL EVENTAS.LMK_GENREA_ASRUN(‘2CAN’,’2018-11-01’)    los parámetros de entrada son los mismos de siempre: Canal y Fecha
                    //System.out.println("2)      Ejecutar el SP :  CALL EVENTAS.LMK_GENERA_ASRUN("+lsChannel+","+lsFecInicial+")    los parámetros de entrada son los mismos de siempre: Canal y Fecha");
                    try {
                        ResponseUpdDao loCll = loAsRunAsDao.callProcedureGeneraAsRun(lsChannel, lsFechaQuery);
                        if(loCll.getLsResponse().equalsIgnoreCase("OK")){
                            System.out.println("Ejecutando por Canal["+lsChannel+"] Fecha["+lsFechaQuery+"].......Procesando");
                            //3)      Después de ejecutado el SP, corres el siguiente query con el que pintaras el archivo final:
                            List<String> laCodList = new ArrayList<String>();
                            laCodList = loAsRunAsDao.getCodAsRunAs(lsChannel, lsFechaQuery); 
                            //4)      El archivo será nombrado de la siguientes manera:   AS.2C.20190805.2315.txt   
                            //( AS por default,  2C que es el campo de SALES AREA,  20190805 ( la fecha de hoy que lo estoy generando), 2315 ( el horario en el que lo estoy generando ).
                            String lsNomarchFijo = "AS";                    
                            String lsNomarchChannel = loAsRunAsDao.getPrefixChannelAsRunAs(lsChannel, lsFechaQuery).get(0);
                            String lsNomarchFecha = getCurrentDateSplit(lsFechaQuery); //getCurrentDate();
                            String lsNomarchHora = getCurrentMiliDate();                    
                            String lsNomarch = 
                                lsNomarchFijo+"."+lsNomarchChannel+"."+lsNomarchFecha+"."+lsNomarchHora+".txt";
                            System.out.println(">>>>["+lsFechaQuery+"]Nombre del archivo["+lsNomarch+"]");
                            File loFileAsRunAs = null;
                            try {
                                loFileAsRunAs = getFileAsRunAs(laCodList, lsPathFiles, lsNomarch);
                                liIndProcess = 
                                            new UtilFaces().getIdConfigParameterByName("CreateFile");//
                                        loBitBean.setLiIdLogServices(liIdLogService);
                                        loBitBean.setLiIdService(liIdService);
                                        loBitBean.setLiIndProcess(liIndProcess);
                                        loBitBean.setLiNumProcessId(0);
                                        loBitBean.setLiNumPgmProcessId(0);
                                        loBitBean.setLsIndEvento("Archivo "+loFileAsRunAs.getName()+
                                                                 " Creado para servicio "+lsServiceName);
                                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                           liIdUser, 
                                                                           lsUserName);
                                
                                //##################### Insertar Archivo en Base de Datos ############################ 
                                try {
                                    XmlFilesDao loXmlFilesDao = new XmlFilesDao();
                                    LmkIntXmlFilesRowBean loXmlBean = new LmkIntXmlFilesRowBean();
                                    FileInputStream loFis = new FileInputStream(loFileAsRunAs);
                                    
                                    loXmlBean.setLiIdFileXml(0);
                                    loXmlBean.setLiIdRequest(liIdLogService);
                                    loXmlBean.setLiIdService(liIdService);
                                    loXmlBean.setLsNomFile(loFileAsRunAs.getName());
                                    loXmlBean.setLsIndFileType("Response");
                                    loXmlBean.setLsIndServiceType(lsTypeProcess);
                                    loXmlBean.setLsIndEstatus("R"); //De Creado
                                    loXmlBean.setLsNomUserName(lsUserName);
                                    loXmlBean.setLsNomUserPathFile(lsPathFiles);
                                    loXmlBean.setLiIdUser(liIdUser);
                                    loXmlBean.setLoIndFileStream(loFis);
                                    loXmlBean.setLsAttribute1(""+lsChannel+","+lsFechaQuery+","+lsFechaQuery);
                                    loXmlBean.setLsAttribute2("AsRunAsFileType");
                                    // - Guardar archivo en bd
                                    ResponseUpdDao loXmlFile = 
                                        loXmlFilesDao.insertLmkIntXmlFilesTab(loXmlBean);
                                    String lsMessInsert = "";
                                    if(loXmlFile.getLsResponse().equalsIgnoreCase("OK")){
                                        lsMessInsert = "El Archivo "+loFileAsRunAs.getName()+" se ha guardado en base de datos";
                                    }else{
                                        lsMessInsert = "Error " + loXmlFile.getLsMessage() + " al guardar archivo "+
                                                       loFileAsRunAs.getName()+" size: "+loFileAsRunAs.getTotalSpace();
                                    }
                                    liIndProcess = 
                                                new UtilFaces().getIdConfigParameterByName("InsertFile");//
                                            loBitBean.setLiIdLogServices(liIdLogService);
                                            loBitBean.setLiIdService(liIdService);
                                            loBitBean.setLiIndProcess(liIndProcess);
                                            loBitBean.setLiNumProcessId(0);
                                            loBitBean.setLiNumPgmProcessId(0);
                                            loBitBean.setLsIndEvento(lsMessInsert);
                                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                       liIdUser, 
                                                                       lsUserName);
                                    //5)      El archivo se depositará en la carpeta que anotaste de ASRUN, pero de todos modos te anexo la foto ( lado derecho esta la ruta)
                                    SftpManagment loSftpMgmnt = new SftpManagment();
                                    String lsRemotePath = loEntityMappedDao.getGeneralParameter("PATH_ASRUN", "SSH_CONNECTION");                    
                                    //tsRemotePath,
                                    //tsLocalPath = lsPathFiles,
                                    //tsRemoteFileName = loFileBreaks.getName(),
                                    //tsLocalFileName = loFileBreaks.getName()
                                    ResponseUpdDao loResUpdDao = 
                                        loSftpMgmnt.uploadFileSFTP(lsRemotePath, 
                                                                   lsPathFiles, 
                                                                   loFileAsRunAs.getName(), 
                                                                   loFileAsRunAs.getName(),
                                                                   loFileAsRunAs
                                                                   );
                                        liIndProcess = 
                                                    new UtilFaces().getIdConfigParameterByName("SendFileSSH");//
                                        loBitBean.setLiIdLogServices(liIdLogService);
                                        loBitBean.setLiIdService(liIdService);
                                        loBitBean.setLiIndProcess(liIndProcess);
                                        loBitBean.setLiNumProcessId(0);
                                        loBitBean.setLiNumPgmProcessId(0);
                                        loBitBean.setLsIndEvento(loResUpdDao.getLsMessage());
                                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                               liIdUser, 
                                                               lsUserName);
                                                            
                                        try{
                                            //Actualizar estatus de archivo xml
                                            ResponseUpdDao loResponseUpdDao = 
                                                loAsRunAsDao.updateEstatusXmlFiles(loXmlFile.getLiAffected(), "L");        
                                            liIndProcess = 
                                                        new UtilFaces().getIdConfigParameterByName("CallProcedure");//
                                            loBitBean.setLiIdLogServices(liIdLogService);
                                            loBitBean.setLiIdService(liIdService);
                                            loBitBean.setLiIndProcess(liIndProcess);
                                            loBitBean.setLiNumProcessId(0);
                                            loBitBean.setLiNumPgmProcessId(0);
                                            loBitBean.setLsIndEvento("Actualizar Estatus de Archivo: "+loResponseUpdDao.getLsMessage());
                                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   liIdUser, 
                                                                   lsUserName);
                                                                    
                                        }catch(Exception loEx){
                                            System.out.println("Err 879"+loEx.getMessage());
                                        }
                                    
                                    //Actualizacion de estatus en tabla de EVETV_LOG_CERTIFICADO_PROCESADO
                                    
                                    try{
                                        //System.out.println("Actualizar log certificado (mandar a bitacora)");
                                        ResponseUpdDao loResponseUpdDao = 
                                            loAsRunAsDao.getUpdateLogCertificado(lsChannel, lsFechaQuery);
                                        //System.out.println("Actualizar log certificado (mandar a bitacora)....OK");    
                                        liIndProcess = 
                                                    new UtilFaces().getIdConfigParameterByName("CallProcedure");//
                                        loBitBean.setLiIdLogServices(liIdLogService);
                                        loBitBean.setLiIdService(liIdService);
                                        loBitBean.setLiIndProcess(liIndProcess);
                                        loBitBean.setLiNumProcessId(0);
                                        loBitBean.setLiNumPgmProcessId(0);
                                        loBitBean.setLsIndEvento("Actualizar Log Certificado: " + 
                                                                 loResponseUpdDao.getLsMessage());
                                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                               liIdUser, 
                                                               lsUserName);
                                    }catch(Exception loEx){
                                            System.out.println("Err 87998"+loEx.getMessage());
                                    }                                                    
                               
                                } catch (FileNotFoundException e) {
                                    System.out.println("Error al convertir File en FileInputStream: "+e.getMessage());
                                    liIndProcess = 
                                                new UtilFaces().getIdConfigParameterByName("GeneralError");
                                            loBitBean.setLiIdLogServices(liIdLogService);
                                            loBitBean.setLiIdService(liIdService);
                                            loBitBean.setLiIndProcess(liIndProcess);
                                            loBitBean.setLiNumProcessId(0);
                                            loBitBean.setLiNumPgmProcessId(0);
                                            loBitBean.setLsIndEvento("No es posible guardar el Archivo: "+
                                                                     loFileAsRunAs.getName()+e.getMessage());
                                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                       liIdUser, 
                                                                       lsUserName);
                                }
                                    
                            } catch (Exception loEx) {
                            liIndProcess = 
                                        new UtilFaces().getIdConfigParameterByName("ErrorCreateFile");//
                                    loBitBean.setLiIdLogServices(liIdLogService);
                                    loBitBean.setLiIdService(liIdService);
                                    loBitBean.setLiIndProcess(liIndProcess);
                                    loBitBean.setLiNumProcessId(0);
                                    loBitBean.setLiNumPgmProcessId(0);
                                    loBitBean.setLsIndEvento("Error al crear Archivo ["+lsServiceName+"] "+ loEx.getMessage() );
                                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                       liIdUser, 
                                                                       lsUserName);
                            }
                            
                            laCodList.clear();
                           
                        }
                        else{
                            //System.out.println("Agregar a bitacora el fallo");
                            liIndProcess = 
                                        new UtilFaces().getIdConfigParameterByName("GeneralError");
                                    loBitBean.setLiIdLogServices(liIdLogService);
                                    loBitBean.setLiIdService(liIdService);
                                    loBitBean.setLiIndProcess(liIndProcess);
                                    loBitBean.setLiNumProcessId(0);
                                    loBitBean.setLiNumPgmProcessId(0);
                                    loBitBean.setLsIndEvento("Fallo en llamada a EVENTAS.LMK_GENERA_ASRUN: "+
                                                             loCll.getLsMessage());
                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                               liIdUser, 
                                                               lsUserName);
                        }                
                        
                    } 
                    catch (SQLException e) {
                        //System.out.println("Error al llamar proceure genera as run ");
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("GeneralError");
                                loBitBean.setLiIdLogServices(liIdLogService);
                                loBitBean.setLiIdService(liIdService);
                                loBitBean.setLiIndProcess(liIndProcess);
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("Fallo en llamada a EVENTAS.LMK_GENERA_ASRUN: "+
                                                         e.getMessage());
                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                           liIdUser, 
                                                           lsUserName);
                    }
                }
 //###################################################################################################                                     
            }
//###################################################################################################                         
        }
        else{
            //System.out.println("Bandera Recon complete");
            liIndProcess = 
                        new UtilFaces().getIdConfigParameterByName("GeneralError");
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(liIdService);
                    loBitBean.setLiIndProcess(liIndProcess);
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento("Validacion reconComplete no satisfactoria: ");
            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               liIdUser, 
                                               lsUserName);
        }
        //###########################################################################
        liIndProcess = 
                    new UtilFaces().getIdConfigParameterByName("ProcessFinish");//
                loBitBean.setLiIdLogServices(liIdLogService);
                loBitBean.setLiIdService(liIdService);
                loBitBean.setLiIndProcess(liIndProcess);
                loBitBean.setLiNumProcessId(0);
                loBitBean.setLiNumPgmProcessId(0);
                loBitBean.setLsIndEvento("Proceso finalizado para ["+lsParametersClass+"]");
        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                           liIdUser, 
                                           lsUserName);
        
        //System.out.println("["+lsChannel+"] FIN DE JOB ["+new Date()+"]");
    }
    
    /**
     * Crea archivo .PROG en base a las tablas de Paradigm
     * @autor Jorge Luis Bautista Santiago   
     * @param lsPath
     * @return File
     */
    public File getFileAsRunAs(List<String> laLineAsRun,
                               String tsPath,
                               String tsFilename) throws Exception{
        File loFileResponse = new File(tsPath+""+tsFilename);
        //System.out.println("Ruta: "+loFileResponse.getPath());
        try {
            FileWriter loWriter = new FileWriter(loFileResponse, true);
            for(String lsLine : laLineAsRun){
                if(lsLine != null){                                                            
                    loWriter.write(lsLine);
                    loWriter.write("\r\n");
                }
            }                  
            loWriter.close();
        }
        catch (Exception e) {
            System.out.println("AS_RUN_AS: Error al escribir en ruta local: "+e.getMessage());
        }
        return loFileResponse;
    }
    
    
    /**
     * Obtiene, en base a la fecha, el id_paradigm a manejar en intergracion
     * @autor Jorge Luis Bautista Santiago     
     * @return String
     */
    public String getCurrentDate(){
        String lsResponse = null;
        DateFormat loDf = new SimpleDateFormat("yyyyMMdd");
        lsResponse = loDf.format(new java.util.Date(System.currentTimeMillis()));
        return lsResponse;
    }
    
    /**
     * Obtiene, en base a la fecha, el id_paradigm a manejar en intergracion
     * @autor Jorge Luis Bautista Santiago     
     * @return String
     */
    public String getCurrentDateSplit(String lsDate){
        String lsResponse = null;
        lsResponse = lsDate.replace("-", "");
        return lsResponse;
    }
    
    /**
     * Obtiene, en base a la fecha, el id_paradigm a manejar en intergracion
     * @autor Jorge Luis Bautista Santiago     
     * @return String
     */
    public String getCurrentMiliDate(){
        String lsResponse = null;
        DateFormat loDf = new SimpleDateFormat("HHmm");
        lsResponse = loDf.format(new java.util.Date(System.currentTimeMillis()));
        return lsResponse;
    }
    
    /**
     * Construye en formato de fecha aceptado por paradigm
     * @autor Jorge Luis Bautista Santiago  
     * @param tsDate
     * @param tsInMask
     * @return String
     */
    private String buildDateYYYYMMDD(String tsDate, String tsInMask){
        String   lsResponse = null;
        String[] laArrDate = tsDate.split("/");
        if(laArrDate.length > 0){
            String lsMonth = laArrDate[0];
            String lsDay = laArrDate[1];
            String lsYear = convierteFecha(tsDate,tsInMask,"YYYY");
            lsResponse = lsYear+"-"+lsMonth+"-"+lsDay;
        }
        return lsResponse;
    }
    
    /**
     * Convierte entre formatos de mascara de fechas
     * @autor Jorge Luis Bautista Santiago  
     * @param tsCadenaFecha
     * @param tsMascaraEntrada
     * @param tsMascaraSalida
     * @return String
     */
    private static String convierteFecha(String tsCadenaFecha, 
                                        String tsMascaraEntrada,
                                        String tsMascaraSalida) {

        SimpleDateFormat loSdfEntrada = new SimpleDateFormat(tsMascaraEntrada);
        SimpleDateFormat loSdfSalida = new SimpleDateFormat(tsMascaraSalida);
        Date             ltDatePiv = new Date();
        String           lsFechaFormateada = "";
        try {
            ltDatePiv = loSdfEntrada.parse(tsCadenaFecha);
            lsFechaFormateada = loSdfSalida.format(ltDatePiv);
        } 
        catch (ParseException loEx) {
            lsFechaFormateada = null;
        }
        return lsFechaFormateada;
    }

}
