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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.FileGzipDao;
import mx.com.televisa.landamark.model.daos.ResponseBreaksDao;
import mx.com.televisa.landamark.model.daos.ServicesParamsDao;
import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.model.daos.XmlFilesDao;
import mx.com.televisa.landamark.model.types.GzipDcdpTrxBean;
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
                    try {
                        boolean lbContinue = true;                        
                        FileGzipDao loFileGzipDao = new FileGzipDao();
                        //Verificar si existe en la bd
                        String lsWhere = 
                            " AND UPPER(NOM_FILE) = UPPER('"+lsFullNomArch+"') ";
                        List<LmkIntXmlFilesRowBean> laList = 
                            loFileGzipDao.getLastFilesGzip(lsWhere);
                        if(laList.size() > 0){//Existe al menos uno                            
                            //Validar si ya se ha procesado, mediante la fecha de creacion del achivo
                            //guardado en attribute11
                            Long llFechaFileSsh = loFileInput.lastModified();
                            Long llFechaFileBd = Long.parseLong(laList.get(0).getLsAttribute11());
                            if(llFechaFileSsh.compareTo(llFechaFileBd) == 0 ){
                                lbContinue = false;
                                // Es el mismo achivo
                                liIndProcess = loEntityMappedDao.getGeneralParameterID("Execute", 
                                                                        "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(liIdLogService);
                                loBitBean.setLiIdService(loInput.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess); 
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("El archivo encontrado ya fue procesado " +
                                                         loInput.getLsServiceName());
                                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                                   loInput.getLiIdUser(), 
                                                                   loInput.getLsUserName());
                                
                                System.out.println("El archivo encontrado ya fue procesado");
                                loFileInput.deleteOnExit();
                            }                            
                        }else{
                            System.out.println("No existen archivos en la bd, NO insertar en bitacora ");
                            
                        }
                        if(lbContinue){                            
                            System.out.println("Barni es un dinosaurio");
                            
                            FileInputStream loFis = new FileInputStream(loFileInput);
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
                                System.out.println("Descomprimir OK");
                                //File loFileUnzip = new File(lsFullNomArchExtracted);
                                ResponseUpdDao loRdd = 
                                    readWriteFile(lsFullNomArchExtracted, 
                                                  lsTableType,
                                                  loBitBean,
                                                  loInput.getLiIdUser(), 
                                                  loInput.getLsUserName(),
                                                  loInput.getLsServiceName()
                                                  );
                                
                                
                                
                                
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
            
        }
                                
        return loResponseService;
    }
    
    public ResponseUpdDao readWriteFile(String tsFileUnziped, 
                                        String tsFileType,
                                        LmkIntServiceBitacoraRowBean loBitBean,
                                        Integer tiIdUser,
                                        String tsUserName,
                                        String tsServiceName){
        ResponseUpdDao loRes = new ResponseUpdDao();
        FileReader loFileUnzip;
        Integer liIndProcess = 0;
        EntityMappedDao loEntityMappedDao = new EntityMappedDao();
        System.out.println("tsFileType["+tsFileType+"]");
        try {
            loFileUnzip = new FileReader(tsFileUnziped);
            BufferedReader loBuffReader = new BufferedReader(loFileUnzip);
            String lsLine;
            try {
                if(tsFileType.equalsIgnoreCase("DCDP")){
                    List<GzipDcdpTrxBean> laDcdpList = new ArrayList<GzipDcdpTrxBean>();
                    while ((lsLine = loBuffReader.readLine()) != null) {
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
                        //System.out.println(lsLine);
                    }
                    System.out.println("**********Tamaño lista Dcdp["+laDcdpList.size()+"]***********");
                    if(laDcdpList.size() > 0){
                        //Insertar lineas en tabla
                    }else{
                        System.out.println("Sin lineas para procesar[DCDP]");
                    }
                    
                }
                if(tsFileType.equalsIgnoreCase("DCDT")){
                    while ((lsLine = loBuffReader.readLine()) != null) {
                        System.out.println(lsLine);
                    }
                }
                if(tsFileType.equalsIgnoreCase("DEAL")){
                    
                }
                if(tsFileType.equalsIgnoreCase("DEMD")){
                    
                }
                
                loFileUnzip.close();
                loRes.setLsResponse("OK");
                loRes.setLsMessage("OK");
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
    
    
}
