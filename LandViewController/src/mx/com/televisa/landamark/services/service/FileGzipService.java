/**
* Project: Integraton Paradigm - Landmark
*
* File: FileGzipService.java
*
* Created on: Septiembre 11, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/

package mx.com.televisa.landamark.services.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.FileGzipDao;
import mx.com.televisa.landamark.model.daos.ServicesParamsDao;
import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.model.daos.XmlFilesDao;
import mx.com.televisa.landamark.model.types.GzipDcdpTrxBean;
import mx.com.televisa.landamark.model.types.GzipDcdtTrxBean;
import mx.com.televisa.landamark.model.types.GzipDealTrxBean;
import mx.com.televisa.landamark.model.types.GzipDemdTrxBean;
import mx.com.televisa.landamark.model.types.GzipPredTrxBean;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesLogRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesParamsRowBean;
import mx.com.televisa.landamark.model.types.LmkIntXmlFilesRowBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;
import mx.com.televisa.landamark.services.sftp.SftpManagment;
import mx.com.televisa.landamark.util.UtilFaces;
import mx.com.televisa.landamark.view.types.BasicInputParameters;
import mx.com.televisa.landamark.view.types.ResponseService;

/** Clase que ejecuta logica o servicio de lectura de archivo gzip
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Septimbre 11, 2019, 12:00 pm
 */
public class FileGzipService {
    public FileGzipService() {
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
        SftpManagment          loSftpManagment = new SftpManagment();
        String                 lsPathFiles = loInput.getLsPathFiles();
        Integer                liIdService = loInput.getLiIdService();
        Integer                liIdUser =  loInput.getLiIdUser();
        String                 lsUserName = loInput.getLsUserName();
        FileGzipDao            loFileGzipDao = new FileGzipDao();
       
        String lsReturn = "Lectura de archivo gz execute";
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
        loBitBean.setLiIndProcess(liIndProcess);
        loBitBean.setLiNumProcessId(0);
        loBitBean.setLiNumPgmProcessId(0);
        loBitBean.setLsIndEvento("Ejecución de Servicio de Lectura de archivo GZ");
        
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
        
        if(loParams.size() < 3 ){ //Nombre de la tabla, prefijo nomarch y tipo (DCCP, DEALS )
            lbProcess = false;
            liIndProcess = loEntityMappedDao.getGeneralParameterID("ParametersMissing", 
                                                    "PROCESS_INTEGRATION");
            loBitBean.setLiIdLogServices(liIdLogService);
            loBitBean.setLiIdService(loInput.getLiIdService());
            loBitBean.setLiIndProcess(liIndProcess); 
            loBitBean.setLiNumProcessId(0);
            loBitBean.setLiNumPgmProcessId(0);
            loBitBean.setLsIndEvento("Parámetros insuficientes para el Servicio " +
                                     loInput.getLsServiceName());
            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               loInput.getLiIdUser(), 
                                               loInput.getLsUserName());
            
        }
        else{
            String lsTable = "";
            String lsPrefixName = "";
            String lsTableType = "";
            for(LmkIntServicesParamsRowBean loBean:loParams){
                if(loBean.getLsIndParameter().equalsIgnoreCase("TABLE")){
                    lsTable = loBean.getLsIndValParameter();
                }
                if(loBean.getLsIndParameter().equalsIgnoreCase("PREFIX_FILE_NAME")){
                    lsPrefixName = loBean.getLsIndValParameter();
                }
                if(loBean.getLsIndParameter().equalsIgnoreCase("TABLE_TYPE")){
                    lsTableType = loBean.getLsIndValParameter();
                }
                
            }
            
            //Proceso para guardar parametros en la tabla de log services
            String lsTableLog = "["+lsTable + ","+lsPrefixName+"]";
            String lsChannelsLog = lsTableType;
            loEntityMappedDao.updateParametersServiceLog(liIdLogService, 
                                                         loInput.getLiIdService(), 
                                                         lsTableLog, 
                                                         lsChannelsLog);
            
            //######## Logica de Jacobo ##################
            // Poleo
            //Extraer lista de archivos(Nomarch_CurrDate.gz) en ruta configurable
            String lsDateSufix = getCurrentDate();
            boolean lbValSsh = valiateIfExistSsh(lsPrefixName, lsDateSufix);
            if(lbValSsh){
                String lsFullNomArch = lsPrefixName + "_" + lsDateSufix + ".gz";
                String lsFullNomArchExtracted = lsPrefixName + "_" + lsDateSufix + "";
                
                //Extraer archivo
                String lsPathRemote = 
                    loEntityMappedDao.getGeneralParameter("PATH_GZIP_INPUT", "SSH_CONNECTION");
                ResponseUpdDao loResDn = 
                    loSftpManagment.downloadFileSFTP(lsPathRemote, 
                                                     lsPathFiles, 
                                                     lsFullNomArch, 
                                                     lsFullNomArch);
                
                if(!loResDn.getLsResponse().equalsIgnoreCase("OK")){
                    //Archivo no encontrado
                    // Si por alguna razon ya lo borraron, es decir que no fue encontrado, mandar error a bitacora
                    System.out.println("El proceso debe detener...........");
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
                    
                }
                else{
                    //Continua
                    liIndProcess = loEntityMappedDao.getGeneralParameterID("Execute", 
                                                            "PROCESS_INTEGRATION");
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(loInput.getLiIdService());
                    loBitBean.setLiIndProcess(liIndProcess); 
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento("Archivo gz extraido exitosamente " +
                                             loInput.getLsServiceName());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                       loInput.getLiIdUser(), 
                                                       loInput.getLsUserName());
                    //Extraer archivo fisico .gz del direcorio remoto
                    File loFileInput = new File(lsPathFiles+lsFullNomArch);
                    
                    //###########################################################################
                    /*
                    String lsAtt11 = "";
                    System.out.println("Obtener atributos del archivo gzip ");
                    try {
                        BasicFileAttributes loAtt =
                            Files.readAttributes(loFileInput.toPath(), BasicFileAttributes.class);                        
                        System.out.println("Fecha de Creacion["+loAtt.creationTime()+"] de clase["+loAtt.creationTime().getClass()+"]");
                        System.out.println("fileKey["+loAtt.fileKey()+"] de clase["+loAtt.fileKey().getClass()+"]");
                        System.out.println("lastAccessTime["+loAtt.lastAccessTime()+"] de clase["+loAtt.lastAccessTime().getClass()+"]");
                        System.out.println("lastModifiedTime["+loAtt.lastModifiedTime()+"] de clase["+loAtt.lastModifiedTime().getClass()+"]");
                        System.out.println("size["+loAtt.size()+"] de clase["+loAtt.size()+"]");
                        lsAtt11 = String.valueOf(loAtt.creationTime());
                    } catch (IOException e) {
                        System.out.println("Error al obtener atributos del archivo "+e.getMessage());
                    }*/
                    //###########################################################################
                    Long llFechaFileSsh = new Long(0);
                    try {
                        boolean lbContinue = true;
                        boolean lbValidate = false;
                        String lsControlString = "";
                        //Verificar si existe en la bd
                        String lsWhere = 
                            " AND UPPER(NOM_FILE) = UPPER('"+lsFullNomArch+"') ";
                        List<LmkIntXmlFilesRowBean> laList = 
                            loFileGzipDao.getLastFilesGzip(lsWhere);
                        if(laList.size() > 0){//Existe al menos uno
                            lbValidate = true;
                            lsControlString = laList.get(0).getLsAttribute11();
                            System.out.println("lsControlString("+lsControlString+")");
                        }else{
                            System.out.println("No existen archivos en la bd, NO insertar en bitacora ");
                        }
                        if(lbContinue){                            
                            System.out.println("Barni es un dinosaurio");
                            
                            FileInputStream loFis = new FileInputStream(loFileInput);
                            FileInputStream loFis2 = new FileInputStream(loFileInput);
                            //Extraer Archivo comprimido GZ
                            //loFis es el archivo .gz extraido del ssh
                            FileOutputStream loOut = gunzipIt(loFis, lsFullNomArchExtracted);                            
                            if(loOut != null){
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("Execute", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(liIdLogService);
                                loBitBean.setLiIdService(loInput.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("El archivo plano se ha extraído con exito  " +
                                                         loInput.getLsServiceName());
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   loInput.getLiIdUser(), 
                                                                   loInput.getLsUserName());
                                
                                //Descomprimir archivo
                                //Lectura de archivo plano
                                //Insertar en tabla correspondiente
                                //Llamado de stored proceure
                                //Fin    
                                String lsMesDelete = "";
                                System.out.println("Descomprimir OK");
                                try{
                                    loFileGzipDao.deleteTrx(lsTable);                                    
                                    lsMesDelete = "Eliminacion de registros deprecados satisfactorio";
                                }catch(Exception loExDel){
                                    lsMesDelete = loExDel.getMessage();
                                }
                                
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("Execute", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(liIdLogService);
                                loBitBean.setLiIdService(loInput.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento(lsMesDelete + " en " + 
                                                         lsTable);
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   loInput.getLiIdUser(), 
                                                                   loInput.getLsUserName());
                                System.out.println("Rollback a registros insertados en " + 
                                                         lsTable);
                                
                                
                                ResponseUpdDao loRdd = 
                                    readWriteFile(lsFullNomArchExtracted, 
                                                  lsTableType,
                                                  lsTable,
                                                  loBitBean,
                                                  loInput.getLiIdUser(), 
                                                  loInput.getLsUserName(),
                                                  lbValidate,
                                                  lsControlString
                                                  );
                                if(loRdd.getLsResponse().equalsIgnoreCase("OK")){
                                    liIndProcess = loEntityMappedDao.getGeneralParameterID("ExtractData", 
                                                                            "PROCESS_INTEGRATION");
                                    System.out.println("(split)loRdd.getLsMessage()["+loRdd.getLsMessage()+"]");
                                    String[] laMessArr = loRdd.getLsMessage().split("\\@");
                                    String lsMess1 = loRdd.getLsMessage();
                                    String lsStringControl = "";
                                    String lsContinue = "1";
                                    System.out.println("Tamaño de laMessArr["+laMessArr.length+"]");
                                    if(laMessArr.length > 2){
                                        lsMess1 = laMessArr[0];
                                        lsStringControl = laMessArr[1];
                                        lsContinue = laMessArr[2];
                                    }
                                    System.out.println("lsMess1["+lsMess1+"]");
                                    System.out.println("lsStringControl["+lsStringControl+"]");
                                    System.out.println("lsContinue["+lsContinue+"]");
                                    
                                    if(lsContinue.equalsIgnoreCase("1")){
                                        loBitBean.setLiIdLogServices(liIdLogService);
                                        loBitBean.setLiIdService(loInput.getLiIdService());
                                        loBitBean.setLiIndProcess(liIndProcess); 
                                        loBitBean.setLiNumProcessId(0);
                                        loBitBean.setLiNumPgmProcessId(0);
                                        loBitBean.setLsIndEvento(lsMess1 + " en Tabla Auxiliar " + 
                                                                 loInput.getLsServiceName());
                                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                           loInput.getLiIdUser(), 
                                                                           loInput.getLsUserName());
                                        System.out.println("Registros insertados en tabla");
                                        
                                        try {
                                            System.out.println("Llamando a stored procuedure ()");
                                            ResponseUpdDao loCall = 
                                            loFileGzipDao.callProcedureLoadTable(lsTable);
                                            
                                            liIndProcess = loEntityMappedDao.getGeneralParameterID("CallProcedure", 
                                                                                    "PROCESS_INTEGRATION");
                                            loBitBean.setLiIdLogServices(liIdLogService);
                                            loBitBean.setLiIdService(loInput.getLiIdService());
                                            loBitBean.setLiIndProcess(liIndProcess); 
                                            loBitBean.setLiNumProcessId(0);
                                            loBitBean.setLiNumPgmProcessId(0);
                                            loBitBean.setLsIndEvento("Llamada a LMK_CARGA_TABLAS("+lsTable+") " + 
                                                                     loCall.getLsMessage());
                                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                               loInput.getLiIdUser(), 
                                                                               loInput.getLsUserName());
                                            
                                            //##################### Insertar Archivo en Base de Datos ############################ 
                                            try {
                                                LmkIntXmlFilesRowBean loXmlBean = new LmkIntXmlFilesRowBean();
                                                //FileInputStream loFis = new FileInputStream(loFileAsRunAs);
                                                
                                                loXmlBean.setLiIdFileXml(0);
                                                loXmlBean.setLiIdRequest(liIdLogService);
                                                loXmlBean.setLiIdService(liIdService);
                                                loXmlBean.setLsNomFile(loFileInput.getName());
                                                loXmlBean.setLsIndFileType("Response");
                                                loXmlBean.setLsIndServiceType(loInput.getLsServiceType());
                                                loXmlBean.setLsIndEstatus("C"); //De Completo
                                                loXmlBean.setLsNomUserName(lsUserName);
                                                loXmlBean.setLsNomUserPathFile(lsPathFiles);
                                                loXmlBean.setLiIdUser(liIdUser);
                                                loXmlBean.setLoIndFileStream(loFis2);
                                                loXmlBean.setLsAttribute1(lsTableLog);
                                                loXmlBean.setLsAttribute2("FileGzipType");
                                                loXmlBean.setLsAttribute11(lsStringControl);//cifra control
                                                loXmlBean.setLsAttribute12(lsTableType);
                                                System.out.println("Guardar archivo fisico en bd");
                                                // - Guardar archivo en bd
                                                ResponseUpdDao loXmlFile = 
                                                    loFileGzipDao.insertLmkIntXmlFilesTab(loXmlBean);
                                                String lsMessInsert = "";
                                                if(loXmlFile.getLsResponse().equalsIgnoreCase("OK")){
                                                    lsMessInsert = "El Archivo "+loFileInput.getName()+" se ha guardado en Base de Datos";
                                                }else{
                                                    lsMessInsert = "Error " + loXmlFile.getLsMessage() + " al guardar archivo "+
                                                                   loFileInput.getName()+" size: "+loFileInput.getTotalSpace();
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
                                                
                                                
                                                
                                            } catch (Exception e) {
                                                System.out.println("Error al convertir File en FileInputStream: "+e.getMessage());
                                                liIndProcess = 
                                                            new UtilFaces().getIdConfigParameterByName("GeneralError");
                                                        loBitBean.setLiIdLogServices(liIdLogService);
                                                        loBitBean.setLiIdService(liIdService);
                                                        loBitBean.setLiIndProcess(liIndProcess);
                                                        loBitBean.setLiNumProcessId(0);
                                                        loBitBean.setLiNumPgmProcessId(0);
                                                        loBitBean.setLsIndEvento("No es posible guardar el Archivo: "+
                                                                                 loFileInput.getName()+e.getMessage());
                                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                                   liIdUser, 
                                                                                   lsUserName);
                                            }
                                            finally{
                                                System.out.println("Se elimina archivo fisico con deleteOnExit()");
                                                loFileInput.deleteOnExit();
                                            }
                                            
                                        } catch (SQLException e) {
                                            liIndProcess = loEntityMappedDao.getGeneralParameterID("GeneralError", 
                                                                                    "PROCESS_INTEGRATION");
                                            loBitBean.setLiIdLogServices(liIdLogService);
                                            loBitBean.setLiIdService(loInput.getLiIdService());
                                            loBitBean.setLiIndProcess(liIndProcess); 
                                            loBitBean.setLiNumProcessId(0);
                                            loBitBean.setLiNumPgmProcessId(0);
                                            loBitBean.setLsIndEvento(lsTable + " >> Excepcion " + 
                                                                     e.getMessage());
                                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                               loInput.getLiIdUser(), 
                                                                               loInput.getLsUserName());
                                        }
                                    }

                                }else{                                    
                                    loFileGzipDao.deleteTrx(lsTable);
                                    liIndProcess = loEntityMappedDao.getGeneralParameterID("GeneralError", 
                                                                            "PROCESS_INTEGRATION");
                                    loBitBean.setLiIdLogServices(liIdLogService);
                                    loBitBean.setLiIdService(loInput.getLiIdService());
                                    loBitBean.setLiIndProcess(liIndProcess); 
                                    loBitBean.setLiNumProcessId(0);
                                    loBitBean.setLiNumPgmProcessId(0);
                                    loBitBean.setLsIndEvento("Rollback a registros insertados en " + 
                                                             lsTable);
                                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                       loInput.getLiIdUser(), 
                                                                       loInput.getLsUserName());
                                    System.out.println("Rollback a registros insertados en " + 
                                                             lsTable);
                                
                                }
                                
                            }else{
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("ExtractData", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(liIdLogService);
                                loBitBean.setLiIdService(loInput.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("No es posible descomprimir archivo  " +
                                                         loInput.getLsServiceName());
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   loInput.getLiIdUser(), 
                                                                   loInput.getLsUserName());
                                System.out.println("No es posible descomprimir archivo (Bitacora)");
                            }
                        }
                        
                    } catch (FileNotFoundException e) {
                        System.out.println("Eror al crear FileInputStream "+e.getMessage());
                    }
                }                                
            }
            else{
                System.out.println("No existen archivos, insertar en bitacora ");
                liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                        "PROCESS_INTEGRATION");
                loBitBean.setLiIdLogServices(liIdLogService);
                loBitBean.setLiIdService(loInput.getLiIdService());
                loBitBean.setLiIndProcess(liIndProcess); 
                loBitBean.setLiNumProcessId(0);
                loBitBean.setLiNumPgmProcessId(0);
                loBitBean.setLsIndEvento("Sin archivos para procesar para " +
                                         loInput.getLsServiceName());
                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   loInput.getLiIdUser(), 
                                                   loInput.getLsUserName());
            }
            
            //#################################################################################################
            System.out.println("Proceso finalizado para "+loInput.getLsServiceName());
            liIndProcess = 
                        new UtilFaces().getIdConfigParameterByName("ProcessFinish");
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(liIdService);
                    loBitBean.setLiIndProcess(liIndProcess);
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento("Proceso finalizado para " + 
                                             loInput.getLsServiceName());
            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               liIdUser, 
                                               lsUserName);
            
            
        }
                                
        return loResponseService;
    }
    
    /**
     * Lee archivo descomprimido y escribe en las tablas correspondientes
     * @autor Jorge Luis Bautista Santiago
     * @param tsFileUnziped
     * @param tsFileType
     * @param tsTable
     * @param toBitacora
     * @param tiIdUser
     * @param tsUserName
     * @param tbValidate
     * @param lsCtrlBd
     * @return ResponseUpdDao 
     */
    public ResponseUpdDao readWriteFile(String tsFileUnziped, 
                                        String tsFileType,
                                        String tsTable,
                                        LmkIntServiceBitacoraRowBean toBitacora,
                                        Integer tiIdUser,
                                        String tsUserName,
                                        boolean tbValidate,
                                        String lsCtrlBd){
        ResponseUpdDao loRes = new ResponseUpdDao();
        FileGzipDao    loFileGzipDao = new FileGzipDao();
        String         lsCadenaControl = lsCtrlBd;
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
        FileReader loFileUnzip;
        Integer liIndProcess = 0;
        EntityMappedDao loEntityMappedDao = new EntityMappedDao();
        System.out.println("tsFileType["+tsFileType+"]");
        try {
            loFileUnzip = new FileReader(tsFileUnziped);
            BufferedReader loBuffReader = new BufferedReader(loFileUnzip);
            String lsLine;
            String lsMessage = "";
            String lsContinue = "1";
            try {
                if(tsFileType.equalsIgnoreCase("DCDP")){
                    List<GzipDcdpTrxBean> laDcdpList = new ArrayList<GzipDcdpTrxBean>();
                    List<String>          loLinesFile = new ArrayList<String>();
                    while ((lsLine = loBuffReader.readLine()) != null) {
                        loLinesFile.add(lsLine);
                        String[] laLineDcdp = lsLine.split("\\|");                        
                        if(laLineDcdp.length > 0){
                            //Verificar si el dato[0] es un numero
                            if(validateRegularExpression(laLineDcdp[0],"^[0-9]+$")){
                                GzipDcdpTrxBean loBean = new GzipDcdpTrxBean();
                                //System.out.println("setLiDealNo["+laLineDcdp[0]+"]");
                                if(!laLineDcdp[0].isEmpty()){
                                    loBean.setLiDealNo(Integer.parseInt(laLineDcdp[0]));    
                                }else{
                                    loBean.setLiDealNo(null);    
                                }
                                //loBean.setLiCampNo(Integer.parseInt(laLineDcdp[1]));
                                //System.out.println("setLiCampNo["+laLineDcdp[1]+"]");
                                if(!laLineDcdp[1].isEmpty()){
                                    loBean.setLiCampNo(Integer.parseInt(laLineDcdp[1]));
                                }else{
                                    loBean.setLiCampNo(null);
                                }
                                //loBean.setLiSareNo(Integer.parseInt(laLineDcdp[2]));
                                //System.out.println("setLiSareNo["+laLineDcdp[2]+"]");
                                if(!laLineDcdp[2].isEmpty()){
                                    loBean.setLiSareNo(Integer.parseInt(laLineDcdp[2]));
                                }else{
                                    loBean.setLiSareNo(null);
                                }
                                //loBean.setLiDcdpNo(Integer.parseInt(laLineDcdp[3]));
                                //System.out.println("setLiDcdpNo["+laLineDcdp[3]+"]");
                                if(!laLineDcdp[3].isEmpty()){
                                    loBean.setLiDcdpNo(Integer.parseInt(laLineDcdp[3]));
                                }else{
                                    loBean.setLiDcdpNo(null);
                                }
                                //loBean.setLdRevenuePerc(Double.parseDouble(laLineDcdp[6]));
                                //System.out.println("setLdRevenuePerc["+laLineDcdp[6]+"]");
                                if(!laLineDcdp[6].isEmpty()){
                                    loBean.setLdRevenuePerc(Double.parseDouble(laLineDcdp[6]));
                                }else{
                                    loBean.setLdRevenuePerc(null);
                                }
                                //loBean.setLdRtgsPerc(Double.parseDouble(laLineDcdp[8]));
                                //System.out.println("setLdRtgsPerc["+laLineDcdp[8]+"]");
                                if(!laLineDcdp[8].isEmpty()){
                                    loBean.setLdRtgsPerc(Double.parseDouble(laLineDcdp[8]));
                                }else{
                                    loBean.setLdRtgsPerc(null);
                                }
                                //loBean.setLiDemdDemoNo(Integer.parseInt(laLineDcdp[11]));
                                //System.out.println("setLiDemdDemoNo["+laLineDcdp[11]+"]");
                                if(!laLineDcdp[11].isEmpty()){
                                    loBean.setLiDemdDemoNo(Integer.parseInt(laLineDcdp[11]));
                                }else{
                                    loBean.setLiDemdDemoNo(null);
                                }
                                //loBean.setLdCpp(Double.parseDouble(laLineDcdp[15]));
                                //System.out.println("setLdCpp["+laLineDcdp[15]+"] isEmpty["+laLineDcdp[15].isEmpty()+"]");
                                //if(laLineDcdp[15] != null){
                                if(!laLineDcdp[15].isEmpty()){
                                    loBean.setLdCpp(Double.parseDouble(laLineDcdp[15]));
                                }else{
                                    loBean.setLdCpp(null);
                                }
                                //loBean.setLiNoOfSpots(Integer.parseInt(laLineDcdp[17]));
                                //System.out.println("setLiNoOfSpots["+laLineDcdp[17]+"]");
                                if(!laLineDcdp[17].isEmpty()){
                                    loBean.setLiNoOfSpots(Integer.parseInt(laLineDcdp[17]));
                                }else{
                                    loBean.setLiNoOfSpots(null);
                                }
                                //loBean.setLiMasterDcdpNo(Integer.parseInt(laLineDcdp[24]));
                                //System.out.println("setLiMasterDcdpNo["+laLineDcdp[24]+"]");
                                if(!laLineDcdp[24].isEmpty()){
                                    loBean.setLiMasterDcdpNo(Integer.parseInt(laLineDcdp[24]));
                                }else{
                                    loBean.setLiMasterDcdpNo(null); 
                                }
                                //loBean.setLiDcdpNameId(Integer.parseInt(laLineDcdp[27]));
                                //System.out.println("setLiDcdpNameId["+laLineDcdp[27]+"]");
                                if(!laLineDcdp[27].isEmpty()){
                                    loBean.setLiDcdpNameId(Integer.parseInt(laLineDcdp[27]));                                
                                }else{
                                    loBean.setLiDcdpNameId(null);
                                }
                                laDcdpList.add(loBean);
                            }
                        }
                    }
                    
                    boolean lbProcess = true;
                    
                    if(laDcdpList.size() > 0){
                        lsCadenaControl = loLinesFile.get(loLinesFile.size()-1);
                        
                        if(tbValidate){
                            System.out.println("Verificar si el archivo es el mismo que está almacenado en BD");
                            System.out.println("********** Last Line Dcdp["+loLinesFile.get(loLinesFile.size()-1)+"]***********");
                            System.out.println("********** Cadena Control["+lsCadenaControl+"]***********");
                            if(lsCtrlBd.trim().equalsIgnoreCase(loLinesFile.get(loLinesFile.size()-1).trim())){
                                System.out.println("Son iguales, el archivo ya fue procesado");
                                lbProcess = false;
                                lsContinue = "0";
                                loRes.setLsResponse("OK");
                                loRes.setLiAffected(0);
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                                loBitBean.setLiIdService(toBitacora.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("El archivo ya fue procesado Cadena Control ["+
                                                         loLinesFile.get(loLinesFile.size()-1).trim()+"]");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   tiIdUser, 
                                                                   tsUserName);
                            }
                        }
                    }
                    System.out.println("Procesar insertar lineas? ["+lbProcess+"]");
                    if(lbProcess){                   
                        System.out.println("**********Tamaño lista Dcdp["+laDcdpList.size()+"]***********");
                        if(laDcdpList.size() > 0){
                            //for(GzipDcdpTrxBean loBean : laDcdpList){
                            boolean lbFlag = true;
                            int liI = 0;
                            while(lbFlag && liI < laDcdpList.size()){
                                GzipDcdpTrxBean loBean = laDcdpList.get(liI);
                                ResponseUpdDao loResp = 
                                    loFileGzipDao.insertDcdpTrx(tsTable, loBean);    
                                if(!loResp.getLsResponse().equalsIgnoreCase("OK")){
                                    lbFlag = false;
                                }
                                liI++;
                            }
                            if(!lbFlag){
                                loRes.setLsResponse("ERROR");
                                System.out.println("Uno o mas registros no fueron insertados");
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                                loBitBean.setLiIdService(toBitacora.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("Uno o mas registros no fueron insertados");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   tiIdUser, 
                                                                   tsUserName);
                            }else{
                                loRes.setLsResponse("OK");
                            }
                            lsMessage = 
                                "["+liI+"/"+laDcdpList.size()+"] Registros Insertados";
                        }else{
                            loRes.setLsResponse("ERROR");
                            System.out.println("Sin lineas para procesar[DCDP]");
                            liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                    "PROCESS_INTEGRATION");
                            loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                            loBitBean.setLiIdService(toBitacora.getLiIdService());
                            loBitBean.setLiIndProcess(liIndProcess); 
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("Sin lineas para procesar[DCDP]");
                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                               tiIdUser, 
                                                               tsUserName);
                        }    
                    }
                    
                    lsMessage += 
                        "@"+lsCadenaControl+"@"+lsContinue;
                    
                }
                if(tsFileType.equalsIgnoreCase("DCDT")){
                    List<GzipDcdtTrxBean> laDcdtList = new ArrayList<GzipDcdtTrxBean>();
                    List<String>          loLinesFile = new ArrayList<String>();
                    while ((lsLine = loBuffReader.readLine()) != null) {
                        loLinesFile.add(lsLine);
                        String[] laLineDcdt = lsLine.split("\\|");
                        if(laLineDcdt.length > 0){
                            //Verificar si el dato[0] es un numero
                            if(validateRegularExpression(laLineDcdt[0],"^[0-9]+$")){
                                GzipDcdtTrxBean loBean = new GzipDcdtTrxBean();
                                //loBean.setLiCampNo(Integer.parseInt(laLineDcdp[0]));
                                //System.out.println("setLiCampNo["+laLineDcdp[0]+"]");
                                if(!laLineDcdt[0].isEmpty()){
                                    loBean.setLiCampNo(Integer.parseInt(laLineDcdt[0]));
                                }else{
                                    loBean.setLiCampNo(null);
                                }
                                
                                //loBean.setLiDcdpNo(Integer.parseInt(laLineDcdp[1]));
                                //System.out.println("setLiDealNo["+laLineDcdp[1]+"]");
                                if(!laLineDcdt[1].isEmpty()){
                                    loBean.setLiDealNo(Integer.parseInt(laLineDcdt[1]));
                                }else{
                                    loBean.setLiDealNo(null);
                                }
                                
                                //loBean.setLiDcdpNo(Integer.parseInt(laLineDcdp[2]));
                                //System.out.println("setLiDcdpNo["+laLineDcdp[2]+"]");
                                if(!laLineDcdt[2].isEmpty()){
                                    loBean.setLiDcdpNo(Integer.parseInt(laLineDcdt[2]));
                                }else{
                                    loBean.setLiDcdpNo(null);
                                }
                                
                                //loBean.setLiDcdpNo(Integer.parseInt(laLineDcdp[3]));
                                //System.out.println("setLiDcdtSttDay["+laLineDcdp[3]+"]");
                                if(!laLineDcdt[3].isEmpty()){
                                    loBean.setLiDcdtSttDay(Integer.parseInt(laLineDcdt[3]));
                                }else{
                                    loBean.setLiDcdtSttDay(null);
                                }
                                
                                //loBean.setLiDcdpNo(Integer.parseInt(laLineDcdp[4]));
                                //System.out.println("setLiDcdtSttTime["+laLineDcdp[4]+"]");
                                if(!laLineDcdt[4].isEmpty()){
                                    loBean.setLiDcdtSttTime(Integer.parseInt(laLineDcdt[4]));
                                }else{
                                    loBean.setLiDcdtSttTime(null);
                                }
                                
                                
                                //loBean.setLiDcdpNo(Integer.parseInt(laLineDcdp[5]));
                                //System.out.println("setLiEndDay["+laLineDcdp[5]+"]");
                                if(!laLineDcdt[5].isEmpty()){
                                    loBean.setLiEndDay(Integer.parseInt(laLineDcdt[5]));
                                }else{
                                    loBean.setLiEndDay(null);
                                }
                                
                                //loBean.setLiDcdpNo(Integer.parseInt(laLineDcdp[6]));
                                //System.out.println("setLiEndTime["+laLineDcdp[6]+"]");
                                if(!laLineDcdt[6].isEmpty()){
                                    loBean.setLiEndTime(Integer.parseInt(laLineDcdt[6]));
                                }else{
                                    loBean.setLiEndTime(null);
                                }
                                
                                //loBean.setLiDcdpNo(Integer.parseInt(laLineDcdp[7]));
                                //System.out.println("setLiNoOfChanges["+laLineDcdp[7]+"]");
                                if(!laLineDcdt[7].isEmpty()){
                                    loBean.setLiNoOfChanges(Integer.parseInt(laLineDcdt[7]));
                                }else{
                                    loBean.setLiNoOfChanges(null);
                                }
                                
                                //loBean.setLiSareNo(Integer.parseInt(laLineDcdp[8]));
                                //System.out.println("setLiSareNo["+laLineDcdp[8]+"]");
                                if(!laLineDcdt[8].isEmpty()){
                                    loBean.setLiSareNo(Integer.parseInt(laLineDcdt[8]));
                                }else{
                                    loBean.setLiSareNo(null);
                                }
                                
                                laDcdtList.add(loBean);
                            }
                        }
                        //System.out.println(lsLine);
                    }
                    boolean lbProcess = true;
                    
                    if(laDcdtList.size() > 0){
                        lsCadenaControl = loLinesFile.get(loLinesFile.size()-1);
                        
                        if(tbValidate){
                            System.out.println("Verificar si el archivo es el mismo que está almacenado en BD");
                            System.out.println("********** Last Line Dcdt["+loLinesFile.get(loLinesFile.size()-1)+"]***********");
                            System.out.println("********** Cadena Control["+lsCadenaControl+"]***********");
                            if(lsCtrlBd.trim().equalsIgnoreCase(loLinesFile.get(loLinesFile.size()-1).trim())){
                                System.out.println("Son iguales, el archivo ya fue procesado");
                                lbProcess = false;
                                lsContinue = "0";
                                loRes.setLsResponse("OK");
                                loRes.setLiAffected(0);
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                                loBitBean.setLiIdService(toBitacora.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("El archivo ya fue procesado Cadena Control ["+
                                                         loLinesFile.get(loLinesFile.size()-1).trim()+"]");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   tiIdUser, 
                                                                   tsUserName);
                            }
                        }
                    }
                    System.out.println("Procesar insertar lineas? ["+lbProcess+"]");
                    if(lbProcess){                   
                        System.out.println("**********Tamaño lista Dcdt["+laDcdtList.size()+"]***********");
                        if(laDcdtList.size() > 0){
                            //for(GzipDcdpTrxBean loBean : laDcdpList){
                            boolean lbFlag = true;
                            int liI = 0;
                            while(lbFlag && liI < laDcdtList.size()){
                                GzipDcdtTrxBean loBean = laDcdtList.get(liI);
                                ResponseUpdDao loResp = 
                                    loFileGzipDao.insertDcdtTrx(tsTable, loBean);    
                                if(!loResp.getLsResponse().equalsIgnoreCase("OK")){
                                    lbFlag = false;
                                }
                                liI++;
                            }
                            if(!lbFlag){
                                loRes.setLsResponse("ERROR");
                                System.out.println("Uno o mas registros no fueron insertados");
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                                loBitBean.setLiIdService(toBitacora.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("Uno o mas registros no fueron insertados");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   tiIdUser, 
                                                                   tsUserName);
                            }else{
                                loRes.setLsResponse("OK");
                            }
                            lsMessage = 
                                "["+liI+"/"+laDcdtList.size()+"] Registros Insertados";
                        }else{
                            loRes.setLsResponse("ERROR");
                            System.out.println("Sin lineas para procesar[DCDT]");
                            liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                    "PROCESS_INTEGRATION");
                            loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                            loBitBean.setLiIdService(toBitacora.getLiIdService());
                            loBitBean.setLiIndProcess(liIndProcess); 
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("Sin lineas para procesar[DCDT]");
                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                               tiIdUser, 
                                                               tsUserName);
                        }                        
                    }
                    
                    lsMessage += "@"+lsCadenaControl+"@"+lsContinue;
                    
                }
                if(tsFileType.equalsIgnoreCase("DEAL")){
                    List<GzipDealTrxBean> laDealList = new ArrayList<GzipDealTrxBean>();
                    List<String>          loLinesFile = new ArrayList<String>();
                    while ((lsLine = loBuffReader.readLine()) != null) {
                        loLinesFile.add(lsLine);
                        String[] laLineDeal = lsLine.split("\\|");
                        if(laLineDeal.length > 0){
                            //Verificar si el dato[0] es un numero
                            if(validateRegularExpression(laLineDeal[0],"^[0-9]+$")){
                                GzipDealTrxBean loBean = new GzipDealTrxBean();
                                
                                //loBean.setLiDcdpNo(Integer.parseInt(laLineDcdp[0]));
                                //System.out.println("setLiDealNo["+laLineDcdp[0]+"]");
                                if(!laLineDeal[0].isEmpty()){
                                    loBean.setLiDealNo(Integer.parseInt(laLineDeal[0]));
                                }else{
                                    loBean.setLiDealNo(null);
                                }
                                                                
                                //System.out.println("setLiMasterDeal["+laLineDcdp[1]+"]");
                                if(!laLineDeal[1].isEmpty()){
                                    loBean.setLiMasterDeal(Integer.parseInt(laLineDeal[1]));
                                }else{
                                    loBean.setLiMasterDeal(null);
                                }
                                
                                //System.out.println("setLiMasterDeal["+laLineDcdp[1]+"]");
                                if(!laLineDeal[1].isEmpty()){
                                    loBean.setLiMasterDeal(Integer.parseInt(laLineDeal[1]));
                                }else{
                                    loBean.setLiMasterDeal(null);
                                }
                                
                                //System.out.println("setLsClntCode["+laLineDcdp[2]+"]");
                                if(!laLineDeal[2].isEmpty()){
                                    loBean.setLsClntCode(laLineDeal[2]);
                                }else{
                                    loBean.setLsClntCode(null);
                                }
                                
                                //System.out.println("setLsStatus["+laLineDcdp[3]+"]");
                                if(!laLineDeal[3].isEmpty()){
                                    loBean.setLsStatus(laLineDeal[3]);
                                }else{
                                    loBean.setLsStatus(null);
                                }
                                
                                //System.out.println("setLsSttDate["+laLineDcdp[15]+"]");
                                if(!laLineDeal[15].isEmpty()){
                                    loBean.setLsSttDate(laLineDeal[15]);
                                }else{
                                    loBean.setLsSttDate(null);
                                }
                                
                                //System.out.println("setLsEndDate["+laLineDcdp[16]+"]");
                                if(!laLineDeal[16].isEmpty()){
                                    loBean.setLsEndDate(laLineDeal[16]);
                                }else{
                                    loBean.setLsEndDate(null);
                                }
                                
                                laDealList.add(loBean);
                            }
                        }
                        //System.out.println(lsLine);
                    }
                    boolean lbProcess = true;
                    
                    if(laDealList.size() > 0){
                        lsCadenaControl = loLinesFile.get(loLinesFile.size()-1);
                        
                        if(tbValidate){
                            System.out.println("Verificar si el archivo es el mismo que está almacenado en BD");
                            System.out.println("********** Last Line Deal["+loLinesFile.get(loLinesFile.size()-1)+"]***********");
                            System.out.println("********** Cadena Control["+lsCadenaControl+"]***********");
                            if(lsCtrlBd.trim().equalsIgnoreCase(loLinesFile.get(loLinesFile.size()-1).trim())){
                                System.out.println("Son iguales, el archivo ya fue procesado");
                                lbProcess = false;
                                lsContinue = "0";
                                loRes.setLsResponse("OK");
                                loRes.setLiAffected(0);
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                                loBitBean.setLiIdService(toBitacora.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("El archivo ya fue procesado Cadena Control ["+
                                                         loLinesFile.get(loLinesFile.size()-1).trim()+"]");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   tiIdUser, 
                                                                   tsUserName);
                            }
                        }
                    }
                    System.out.println("Procesar insertar lineas? ["+lbProcess+"]");
                    if(lbProcess){   
                        System.out.println("**********Tamaño lista Deal["+laDealList.size()+"]***********");
                        if(laDealList.size() > 0){
                            //for(GzipDcdpTrxBean loBean : laDcdpList){
                            boolean lbFlag = true;
                            int liI = 0;
                            while(lbFlag && liI < laDealList.size()){
                                GzipDealTrxBean loBean = laDealList.get(liI);
                                ResponseUpdDao loResp = 
                                    loFileGzipDao.insertDealTrx(tsTable, loBean);    
                                if(!loResp.getLsResponse().equalsIgnoreCase("OK")){
                                    lbFlag = false;
                                }
                                liI++;
                            }
                            if(!lbFlag){
                                loRes.setLsResponse("ERROR");
                                System.out.println("Uno o mas registros no fueron insertados");
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                                loBitBean.setLiIdService(toBitacora.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("Uno o mas registros no fueron insertados");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   tiIdUser, 
                                                                   tsUserName);
                            }else{
                                loRes.setLsResponse("OK");
                            }
                            lsMessage = 
                                "["+liI+"/"+laDealList.size()+"] Registros Insertados";
                        }else{
                            loRes.setLsResponse("ERROR");
                            System.out.println("Sin lineas para procesar[DEAL]");
                            liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                    "PROCESS_INTEGRATION");
                            loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                            loBitBean.setLiIdService(toBitacora.getLiIdService());
                            loBitBean.setLiIndProcess(liIndProcess); 
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("Sin lineas para procesar[DEAL]");
                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                               tiIdUser, 
                                                               tsUserName);
                        }
                    }
                    
                    lsMessage += 
                        "@"+lsCadenaControl+"@"+lsContinue;
                    
                }
                if(tsFileType.equalsIgnoreCase("DEMD")){
                    List<GzipDemdTrxBean> laDemdList = new ArrayList<GzipDemdTrxBean>();
                    List<String>          loLinesFile = new ArrayList<String>();
                    while ((lsLine = loBuffReader.readLine()) != null) {
                        loLinesFile.add(lsLine);
                        String[] laLineDemd = lsLine.split("\\|");
                        if(laLineDemd.length > 0){
                            //Verificar si el dato[0] es un numero
                            if(validateRegularExpression(laLineDemd[0],"^[0-9]+$")){
                                GzipDemdTrxBean loBean = new GzipDemdTrxBean();
                                
                                //System.out.println("setLiDealNo["+laLineDcdp[0]+"]");
                                if(!laLineDemd[0].isEmpty()){
                                    loBean.setLiDealNo(Integer.parseInt(laLineDemd[0]));
                                }else{
                                    loBean.setLiDealNo(null);
                                }
                                 
                                //System.out.println("setLiDemoNo["+laLineDcdp[1]+"]");
                                if(!laLineDemd[1].isEmpty()){
                                    loBean.setLiDemoNo(Integer.parseInt(laLineDemd[1]));
                                }else{
                                    loBean.setLiDemoNo(null);
                                }                               
                                
                                //System.out.println("setLdRevenuePerc["+laLineDcdp[4]+"]");
                                if(!laLineDemd[4].isEmpty()){
                                    loBean.setLdRevenuePerc(Double.parseDouble(laLineDemd[4]));
                                }else{
                                    loBean.setLdRevenuePerc(null);
                                }
                                
                                //System.out.println("setLiSareNo["+laLineDcdp[9]+"]");
                                if(!laLineDemd[9].isEmpty()){
                                    loBean.setLiSareNo(Integer.parseInt(laLineDemd[9]));
                                }else{
                                    loBean.setLiSareNo(null);
                                }
                                
                                //System.out.println("setLdCpp["+laLineDcdp[10]+"]");
                                if(!laLineDemd[10].isEmpty()){
                                    loBean.setLdCpp(Double.parseDouble(laLineDemd[10]));
                                }else{
                                    loBean.setLdCpp(null);
                                }
                                
                                laDemdList.add(loBean);
                            }
                        }
                        //System.out.println(lsLine);
                    }
                    boolean lbProcess = true;
                    
                    if(laDemdList.size() > 0){
                        lsCadenaControl = loLinesFile.get(loLinesFile.size()-1);
                        
                        if(tbValidate){
                            System.out.println("Verificar si el archivo es el mismo que está almacenado en BD");
                            System.out.println("********** Last Line Demd["+loLinesFile.get(loLinesFile.size()-1)+"]***********");
                            System.out.println("********** Cadena Control["+lsCadenaControl+"]***********");
                            if(lsCtrlBd.trim().equalsIgnoreCase(loLinesFile.get(loLinesFile.size()-1).trim())){
                                System.out.println("Son iguales, el archivo ya fue procesado");
                                lbProcess = false;
                                lsContinue = "0";
                                loRes.setLsResponse("OK");
                                loRes.setLiAffected(0);
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                                loBitBean.setLiIdService(toBitacora.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("El archivo ya fue procesado Cadena Control ["+
                                                         loLinesFile.get(loLinesFile.size()-1).trim()+"]");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   tiIdUser, 
                                                                   tsUserName);
                            }
                        }
                    }
                    System.out.println("Procesar insertar lineas? ["+lbProcess+"]");
                    if(lbProcess){  
                        System.out.println("**********Tamaño lista Demd["+laDemdList.size()+"]***********");
                        if(laDemdList.size() > 0){
                            //for(GzipDcdpTrxBean loBean : laDcdpList){
                            boolean lbFlag = true;
                            int liI = 0;
                            while(lbFlag && liI < laDemdList.size()){
                                GzipDemdTrxBean loBean = laDemdList.get(liI);
                                ResponseUpdDao loResp = 
                                    loFileGzipDao.insertDemdTrx(tsTable, loBean);    
                                if(!loResp.getLsResponse().equalsIgnoreCase("OK")){
                                    lbFlag = false;
                                }
                                liI++;
                            }
                            if(!lbFlag){
                                loRes.setLsResponse("ERROR");
                                System.out.println("Uno o mas registros no fueron insertados");
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                                loBitBean.setLiIdService(toBitacora.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("Uno o mas registros no fueron insertados");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   tiIdUser, 
                                                                   tsUserName);
                            }else{
                                loRes.setLsResponse("OK");
                            }
                            lsMessage = 
                                "["+liI+"/"+laDemdList.size()+"] Registros Insertados";
                        }else{
                            loRes.setLsResponse("ERROR");
                            System.out.println("Sin lineas para procesar[DEMD]");
                            liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                    "PROCESS_INTEGRATION");
                            loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                            loBitBean.setLiIdService(toBitacora.getLiIdService());
                            loBitBean.setLiIndProcess(liIndProcess); 
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("Sin lineas para procesar[DEMD]");
                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                               tiIdUser, 
                                                               tsUserName);
                        }
                    }
                    
                    lsMessage += 
                        "@"+lsCadenaControl+"@"+lsContinue;
                    
                }
                
                if(tsFileType.equalsIgnoreCase("PRED")){
                    List<GzipPredTrxBean> laPredList = new ArrayList<GzipPredTrxBean>();
                    List<String>          loLinesFile = new ArrayList<String>();
                    while ((lsLine = loBuffReader.readLine()) != null) {
                        loLinesFile.add(lsLine);
                        String[] laLineDcdp = lsLine.split("\\|");                        
                        if(laLineDcdp.length > 0){
                            //Verificar si el dato[0] es un numero
                            if(validateRegularExpression(laLineDcdp[0],"^[0-9]+$")){
                                GzipPredTrxBean loBean = new GzipPredTrxBean();
                                //System.out.println("setLsSareNo["+laLineDcdp[0]+"]");
                                if(!laLineDcdp[0].isEmpty()){
                                    loBean.setLsSareNo(laLineDcdp[0]);    
                                }else{
                                    loBean.setLsSareNo(null);    
                                }
                                //System.out.println("setLsDemoNo["+laLineDcdp[1]+"]");
                                if(!laLineDcdp[1].isEmpty()){
                                    loBean.setLsDemoNo(laLineDcdp[1]);    
                                }else{
                                    loBean.setLsDemoNo(null);    
                                }
                                //System.out.println("setLsPredSareNo["+laLineDcdp[2]+"]");
                                if(!laLineDcdp[2].isEmpty()){
                                    loBean.setLsPredSareNo(laLineDcdp[2]);    
                                }else{
                                    loBean.setLsPredSareNo(null);    
                                }
                                //System.out.println("setLsBrekSchedDate["+laLineDcdp[3]+"]");
                                if(!laLineDcdp[3].isEmpty()){
                                    loBean.setLsBrekSchedDate(laLineDcdp[3]);    
                                }else{
                                    loBean.setLsBrekSchedDate(null);    
                                }
                                //System.out.println("setLsBreakNo["+laLineDcdp[4]+"]");
                                if(!laLineDcdp[4].isEmpty()){
                                    loBean.setLsBreakNo(laLineDcdp[4]);    
                                }else{
                                    loBean.setLsBreakNo(null);    
                                }
                                //System.out.println("setLsNoOfRtgs["+laLineDcdp[5]+"]");
                                if(!laLineDcdp[5].isEmpty()){
                                    loBean.setLsNoOfRtgs(laLineDcdp[5]);    
                                }else{
                                    loBean.setLsNoOfRtgs(null);    
                                }
                                
                                laPredList.add(loBean);
                            }
                        }
                    }
                    
                    boolean lbProcess = true;
                    
                    if(laPredList.size() > 0){
                        lsCadenaControl = loLinesFile.get(loLinesFile.size()-1);
                        
                        if(tbValidate){
                            System.out.println("Verificar si el archivo es el mismo que está almacenado en BD");
                            System.out.println("********** Last Line Pred["+loLinesFile.get(loLinesFile.size()-1)+"]***********");
                            System.out.println("********** Cadena Control["+lsCadenaControl+"]***********");
                            if(lsCtrlBd.trim().equalsIgnoreCase(loLinesFile.get(loLinesFile.size()-1).trim())){
                                System.out.println("Son iguales, el archivo ya fue procesado");
                                lbProcess = false;
                                lsContinue = "0";
                                loRes.setLsResponse("OK");
                                loRes.setLiAffected(0);
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                                loBitBean.setLiIdService(toBitacora.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("El archivo ya fue procesado Cadena Control ["+
                                                         loLinesFile.get(loLinesFile.size()-1).trim()+"]");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   tiIdUser, 
                                                                   tsUserName);
                            }
                        }
                    }
                    System.out.println("Procesar insertar lineas? ["+lbProcess+"]");
                    if(lbProcess){                   
                        System.out.println("**********Tamaño lista Pred["+laPredList.size()+"]***********");
                        if(laPredList.size() > 0){
                            //for(GzipDcdpTrxBean loBean : laDcdpList){
                            boolean lbFlag = true;
                            int liI = 0;
                            while(lbFlag && liI < laPredList.size()){
                                GzipPredTrxBean loBean = laPredList.get(liI);
                                ResponseUpdDao loResp = 
                                    loFileGzipDao.insertPredTrx(tsTable, loBean);    
                                if(!loResp.getLsResponse().equalsIgnoreCase("OK")){
                                    lbFlag = false;
                                }
                                liI++;
                            }
                            if(!lbFlag){
                                loRes.setLsResponse("ERROR");
                                System.out.println("Uno o mas registros no fueron insertados");
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                                loBitBean.setLiIdService(toBitacora.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("Uno o mas registros no fueron insertados");
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   tiIdUser, 
                                                                   tsUserName);
                            }else{
                                loRes.setLsResponse("OK");
                            }
                            lsMessage = 
                                "["+liI+"/"+laPredList.size()+"] Registros Insertados";
                        }else{
                            loRes.setLsResponse("ERROR");
                            System.out.println("Sin lineas para procesar[DCDP]");
                            liIndProcess = loEntityMappedDao.getGeneralParameterID("NoData", 
                                                                    "PROCESS_INTEGRATION");
                            loBitBean.setLiIdLogServices(toBitacora.getLiIdLogServices());
                            loBitBean.setLiIdService(toBitacora.getLiIdService());
                            loBitBean.setLiIndProcess(liIndProcess); 
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("Sin lineas para procesar[DCDP]");
                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                               tiIdUser, 
                                                               tsUserName);
                        }    
                    }
                    
                    lsMessage += 
                        "@"+lsCadenaControl+"@"+lsContinue;
                    
                }
                
                
                loFileUnzip.close();
                loRes.setLsMessage(lsMessage);
            } catch (IOException e) {
                loRes.setLsResponse("ERROR");
                loRes.setLsMessage(e.getMessage());
                liIndProcess = 
                    loEntityMappedDao.getGeneralParameterID("ProceduredError", 
                                                            "PROCESS_INTEGRATION");
                loBitBean.setLsIndEvento("Error en la lectura del archivo plano  " + 
                                         e.getMessage());
                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   tiIdUser, 
                                                   tsUserName);
                System.out.println("error al leer archivo: "+e.getMessage());
            }
        } catch (FileNotFoundException e) {
            loRes.setLsResponse("ERROR");
            loRes.setLsMessage(e.getMessage());
            liIndProcess = 
                loEntityMappedDao.getGeneralParameterID("GeneralError", 
                                                        "PROCESS_INTEGRATION");
            loBitBean.setLsIndEvento("Archivo No encontrado " + 
                                     e.getMessage());
            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               tiIdUser, 
                                               tsUserName);
            
            System.out.println("Archivo No encontrado: "+e.getMessage());
        }
        
        return loRes;
    }
    
    public boolean valiateIfExistSsh(String tsFilePrefix, 
                                     String tsFileDate){
        boolean lbResponse = true;
        EntityMappedDao loEntityMappedDao = new EntityMappedDao();
        String lsPath = 
            loEntityMappedDao.getGeneralParameter("PATH_GZIP_INPUT", "SSH_CONNECTION");
        String lsFileName = tsFilePrefix+"_"+tsFileDate+".gz";
        System.out.println("Buscar archivo en del server ssh ["+lsFileName+"]");
        SftpManagment loSmg = new SftpManagment();
        List<String> laLisSsh = loSmg.getFileServerSFTP(lsPath, lsFileName);
        System.out.println("Numero de archivos extraidos(GZip) del server ssh ["+laLisSsh.size()+"]");
        if(laLisSsh.size() <= 0){
            lbResponse = false;
        }
        
        return lbResponse;
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
    public String getIdBitacora(){
        String lsResponse = null;
        DateFormat loDf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        lsResponse = loDf.format(new java.util.Date(System.currentTimeMillis()));
        return lsResponse;
    }
    
    /**
     * Descomprime archivo gzip
     * @autor Jorge Luis Bautista Santiago     
     * @return String
     */
    @SuppressWarnings("oracle.jdeveloper.java.nested-assignment")
    public FileOutputStream gunzipIt(FileInputStream toFileGzip, 
                                     String tsFileUnziped){
        byte[] laBuffer = new byte[1024];
        try{     
            GZIPInputStream loGzis = 
                new GZIPInputStream(toFileGzip);
     
            FileOutputStream loOut = 
                new FileOutputStream(tsFileUnziped);
     
            int liLen;
            while ((liLen = loGzis.read(laBuffer)) > 0) {
                loOut.write(laBuffer, 0, liLen);
            }
            loGzis.close();
            loOut.close();
            System.out.println("Done unziped");
            return loOut;
            
        }catch(IOException ex){
           ex.printStackTrace();   
           return null;
        }
    }             
    
    
    /** Valida expresiones regulares de forma dinamica
      * @autor Jorge Luis Bautista 
      * @param tsClientString
      * @param tsRegularExpression
      * @return boolean
    */
    public boolean validateRegularExpression(String tsClientString, String tsRegularExpression){
        boolean lbReturn = false;
        String  lsToValidate = 
            tsClientString == null ? "" : tsClientString;
        if(!lsToValidate.trim().equalsIgnoreCase("")){
            Matcher loMat = null;
            Pattern loPat = null;
            String  lsExpReg = tsRegularExpression;
            loPat = Pattern.compile(lsExpReg);
            loMat = loPat.matcher(lsToValidate);
            if (!loMat.find()){
                lbReturn = false; 
            }else{
                lbReturn = true;
            }
        }        
        return lbReturn;
    }
    
    /** Obtiene en forma de lista, todas las lineas del archivo plano
      * @autor Jorge Luis Bautista 
      * @param tsFileUnziped
      * @return List
    */
    public List<String> getLinesFileUnziped(String tsFileUnziped){
        List<String>   laFileLinesList = new ArrayList<String>();
        FileReader     loFileUnzip;
        try {
            loFileUnzip = new FileReader(tsFileUnziped);
            BufferedReader loBuffReader = new BufferedReader(loFileUnzip);
            String lsLine;
            try {               
                while ((lsLine = loBuffReader.readLine()) != null) {
                    //System.out.println(lsLine);
                    laFileLinesList.add(lsLine);
                }
                loFileUnzip.close();
            } catch (IOException e) {
                System.out.println("error al leer archivo: "+e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo No encontrado: "+e.getMessage());
        }
        
        return laFileLinesList;
    }
    
    
    
}
