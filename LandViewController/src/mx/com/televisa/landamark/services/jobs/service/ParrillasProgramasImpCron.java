/**
* Project: Integraton Paradigm - Landmark
*
* File: ParrillasProgramasImpCron.java
*
* Created on: Febrero 23, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.jobs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;

import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.MappingCatDao;
import mx.com.televisa.landamark.model.daos.ParrillasProgramasDao;

import mx.com.televisa.landamark.model.daos.XmlFilesDao;
import mx.com.televisa.landamark.model.types.LmkIntMappingCatRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntXmlFilesRowBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;

import mx.com.televisa.landamark.model.types.extract.LmkBrkBreakRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkBrkChannelHeaderRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkBrkChannelTrailerRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkBrkFileHeaderRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkBrkFileTrailerRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkProgFileTrailerRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkProgRowBean;
import mx.com.televisa.landamark.services.sftp.SftpManagment;
import mx.com.televisa.landamark.util.UtilFaces;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/** Clase que ejecuta logica o servicio de Parrillas Programas a Landmark
 * por canal
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
 */
public class ParrillasProgramasImpCron implements Job{
    public ParrillasProgramasImpCron() {
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
        ParrillasProgramasDao     loPpDao = new ParrillasProgramasDao();
        Integer                   liIndProcess = 0;
        EntityMappedDao           loEntityMappedDao = new EntityMappedDao();
        
        Integer                   liIdLogService = Integer.parseInt(lsIdLogService);
        Integer                   liIdService = Integer.parseInt(lsIdService);
        Integer                   liIdUser = Integer.parseInt(lsIdUser);
        
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
        String lsParametersClass = lsChannel+","+lsFecInicial+","+lsFecFinal;
        System.out.println("["+lsChannel+"]Ejecucion de Cron >> ["+new Date()+"]");
        
        ResponseUpdDao loRes = loPpDao.callLmkProgBrkPr(lsChannel, lsFecInicial, lsFecFinal);
        liIndProcess = 
                    new UtilFaces().getIdConfigParameterByName("CallProcedure");
                loBitBean.setLiIdLogServices(liIdLogService);
                loBitBean.setLiIdService(liIdService);
                loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
                loBitBean.setLiNumProcessId(0);
                loBitBean.setLiNumPgmProcessId(0);
                loBitBean.setLsIndEvento("Se ha invocado LMK_PROG_BRK("+lsChannel+","+lsFecInicial+","+lsFecFinal+")");
                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   liIdUser, 
                                                   lsUserName);
        if(loRes.getLsResponse().equalsIgnoreCase("ERROR")){
            liIndProcess = 
                        new UtilFaces().getIdConfigParameterByName("ProceduredError");//
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(liIdService);
                    loBitBean.setLiIndProcess(liIndProcess);
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento("Error en Servicio "+lsServiceName+" "+loRes.getLsMessage());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,liIdUser, lsUserName);
        }
        else{
            String lsWhere = 
                " AND STNID = '"+lsChannel+"' AND BCSTDT BETWEEN DATE('"+lsFecInicial+"') AND DATE('"+lsFecFinal+"')";            
            //############################### BREAKS #####################################################
            ////////// FILE HEADER RECORD
            lsWhere = " AND STNID       = '"+lsChannel+"'\n" + 
            "   AND DATE(STRDT) = DATE('"+lsFecInicial+"') \n" + 
            "   AND DATE(EDT)   = DATE('"+lsFecFinal+"') ";
            List<LmkBrkFileHeaderRowBean> laBfhrb = loPpDao.getBrkFileHeader(lsWhere);
            
            System.out.println("laBfhrb.size() ["+laBfhrb.size()+"]");
            
            ////////// CHANNEL HEADER RECORD
            lsWhere = " AND STNID       = '"+lsChannel+"'\n" + 
            "   AND DATE(BCSTDT) BETWEEN DATE('"+lsFecInicial+"') \n" + 
            "                        AND DATE('"+lsFecFinal+"')";
            List<LmkBrkChannelHeaderRowBean> laChrb = loPpDao.getBrkChannelHeader(lsWhere);
            System.out.println("laChrb.size() ["+laChrb.size()+"]");
            
            ////////// BREAK RECORD
            lsWhere = " AND STNID       = '"+lsChannel+"'\n" + 
            "   AND DATE(BCSTDT) BETWEEN DATE('"+lsFecInicial+"') \n" + 
            "                        AND DATE('"+lsFecFinal+"')";
            List<LmkBrkBreakRowBean> laBrb = loPpDao.getBrkBreak(lsWhere);
            System.out.println("laBrb.size() ["+laBrb.size()+"]");
            
            ////////// CHANNEL TRAILER RECORD
            lsWhere = " AND STNID       = '"+lsChannel+"'\n" + 
            "   AND DATE(BCSTDT) BETWEEN DATE('"+lsFecInicial+"') \n" + 
            "                        AND DATE('"+lsFecFinal+"')";
            List<LmkBrkChannelTrailerRowBean> laCtrb = loPpDao.getBrkChannelTrailer(lsWhere);
            System.out.println("laCtrb.size() ["+laCtrb.size()+"]");
            
            ////////// FILE TRAILER RECORD
            lsWhere = " AND STNID = '"+lsChannel+"' " +
                "AND STRDT ='"+lsFecInicial+"' AND EDT = '"+lsFecFinal+"'";
            List<LmkBrkFileTrailerRowBean> laFtrb = loPpDao.getBrkFileTrailer(lsWhere);
            System.out.println("laFtrb.size() ["+laFtrb.size()+"]"); 
            
            //##################### Crear Archivo BREAKS en Servidor de Aplicaciones  ######################

            try {
                File loFileBreaks = getFileBreaks(laBfhrb, laChrb, laBrb, laCtrb, laFtrb, lsPathFiles, lsChannel);
                liIndProcess = 
                            new UtilFaces().getIdConfigParameterByName("CreateFile");//
                        loBitBean.setLiIdLogServices(liIdLogService);
                        loBitBean.setLiIdService(liIdService);
                        loBitBean.setLiIndProcess(liIndProcess);
                        loBitBean.setLiNumProcessId(0);
                        loBitBean.setLiNumPgmProcessId(0);
                        loBitBean.setLsIndEvento("Archivo "+loFileBreaks.getName()+
                                                 " Creado para servicio "+lsServiceName);
                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                           liIdUser, 
                                                           lsUserName);
                
                //##################### Insertar Archivo en Base de Datos ############################ 
                try {
                    XmlFilesDao loXmlFilesDao = new XmlFilesDao();
                    LmkIntXmlFilesRowBean loXmlBean = new LmkIntXmlFilesRowBean();
                    FileInputStream loFis = new FileInputStream(loFileBreaks);
                    
                    loXmlBean.setLiIdFileXml(0);
                    loXmlBean.setLiIdRequest(liIdLogService);
                    loXmlBean.setLiIdService(liIdService);
                    loXmlBean.setLsNomFile(loFileBreaks.getName());
                    loXmlBean.setLsIndFileType("Response");
                    loXmlBean.setLsIndServiceType(lsTypeProcess);
                    loXmlBean.setLsIndEstatus("1");
                    loXmlBean.setLsNomUserName(lsUserName);
                    loXmlBean.setLsNomUserPathFile(lsPathFiles);
                    loXmlBean.setLiIdUser(liIdUser);
                    loXmlBean.setLoIndFileStream(loFis);
                    loXmlBean.setLsAttribute1(""+lsChannel+","+lsFecInicial+","+lsFecFinal);
                    loXmlBean.setLsAttribute2("BreaksFileType");
                    // - Guardar archivo en bd
                    ResponseUpdDao loXmlFile = 
                        loXmlFilesDao.insertLmkIntXmlFilesTab(loXmlBean);
                    String lsMessInsert = "";
                    if(loXmlFile.getLsResponse().equalsIgnoreCase("OK")){
                        lsMessInsert = "El Archivo "+loFileBreaks.getName()+" se ha guardado en base de datos";
                    }else{
                        lsMessInsert = "Error " + loXmlFile.getLsMessage() + " al guardar archivo "+
                                       loFileBreaks.getName()+" size: "+loFileBreaks.getTotalSpace();
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
                    
                    
                    //############# Enviar archivo(PROGRAMM) a ubicacion remota ###########
                    SftpManagment loSftpMgmnt = new SftpManagment();
                    //Obtener ruta remota de la base de datos
                    String lsRemotePath = loEntityMappedDao.getGeneralParameter("PATH_BREAKS", "SSH_CONNECTION");                    
                    //tsRemotePath,
                    //tsLocalPath = lsPathFiles,
                    //tsRemoteFileName = loFileBreaks.getName(),
                    //tsLocalFileName = loFileBreaks.getName()
                    ResponseUpdDao loResUpdDao = 
                        loSftpMgmnt.uploadFileSFTP(lsRemotePath, 
                                                   lsPathFiles, 
                                                   loFileBreaks.getName(), 
                                                   loFileBreaks.getName()
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
                    
                    
                    
                    // FILE HEADER RECORD
                    String lsWhereDelete = null;
                    lsWhereDelete = " EVENTAS.LMK_BRK_FILE_HEADER WHERE 1 = 1 ";
                    lsWhereDelete += " AND STNID       = '"+lsChannel+"'\n" + 
                    "   AND DATE(STRDT) = DATE('"+lsFecInicial+"') \n" + 
                    "   AND DATE(EDT)   = DATE('"+lsFecFinal+"') ";
                    ResponseUpdDao loResDelFileHeader = 
                        loPpDao.deleteLmkBreaksByTable(lsWhereDelete);
                    if(!loResDelFileHeader.getLsResponse().equalsIgnoreCase("OK")){
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileError");//
                    }else{
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileExecute");//
                    }
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(liIdService);
                    loBitBean.setLiIndProcess(liIndProcess);
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento(loResDelFileHeader.getLsMessage());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               liIdUser, 
                                               lsUserName);
                    
                    //CHANNEL HEADER RECORD
                    lsWhereDelete = " EVENTAS.LMK_BRK_CHANNEL_HEADER WHERE 1 = 1 ";
                    lsWhereDelete += " AND STNID       = '"+lsChannel+"'\n" + 
                                "   AND DATE(BCSTDT) BETWEEN DATE('"+lsFecInicial+"') \n" + 
                                "                        AND DATE('"+lsFecFinal+"')";
                    ResponseUpdDao loResDelChannelHeader = 
                        loPpDao.deleteLmkBreaksByTable(lsWhereDelete);
                    if(!loResDelChannelHeader.getLsResponse().equalsIgnoreCase("OK")){
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileError");//
                    }else{
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileExecute");//
                    }
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(liIdService);
                    loBitBean.setLiIndProcess(liIndProcess);
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento(loResDelChannelHeader.getLsMessage());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               liIdUser, 
                                               lsUserName);
                    
                    //BREAK RECORD
                    lsWhereDelete = " EVENTAS.LMK_BRK_BREAK WHERE 1 = 1 ";
                    lsWhereDelete += " AND STNID       = '"+lsChannel+"'\n" + 
                                "   AND DATE(BCSTDT) BETWEEN DATE('"+lsFecInicial+"') \n" + 
                                "                        AND DATE('"+lsFecFinal+"')";
                    ResponseUpdDao loResDelBreak = 
                        loPpDao.deleteLmkBreaksByTable(lsWhereDelete);
                    if(!loResDelBreak.getLsResponse().equalsIgnoreCase("OK")){
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileError");//
                    }else{
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileExecute");//
                    }
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(liIdService);
                    loBitBean.setLiIndProcess(liIndProcess);
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento(loResDelBreak.getLsMessage());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               liIdUser, 
                                               lsUserName);
                    
                    //CHANNEL TRAILER RECORD
                    lsWhereDelete = " EVENTAS.LMK_BRK_CHANNEL_TRAILER WHERE 1 = 1 ";
                    lsWhereDelete += " AND STNID       = '"+lsChannel+"'\n" + 
                                "   AND DATE(BCSTDT) BETWEEN DATE('"+lsFecInicial+"') \n" + 
                                "                        AND DATE('"+lsFecFinal+"')";
                    ResponseUpdDao loResDelChannelTrailer = 
                        loPpDao.deleteLmkBreaksByTable(lsWhereDelete);
                    if(!loResDelChannelTrailer.getLsResponse().equalsIgnoreCase("OK")){
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileError");//
                    }else{
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileExecute");//
                    }
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(liIdService);
                    loBitBean.setLiIndProcess(liIndProcess);
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento(loResDelChannelTrailer.getLsMessage());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               liIdUser, 
                                               lsUserName);
                    
                    //FILE TRAILER RECORD
                    lsWhereDelete = " EVENTAS.LMK_BRK_FILE_TRAILER WHERE 1 = 1 ";
                    lsWhereDelete += " AND STNID = '"+lsChannel+"' " +
                                    "AND STRDT ='"+lsFecInicial+"' AND EDT = '"+lsFecFinal+"'";
                    ResponseUpdDao loResDelFileTrailer = 
                        loPpDao.deleteLmkBreaksByTable(lsWhereDelete);
                    if(!loResDelFileTrailer.getLsResponse().equalsIgnoreCase("OK")){
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileError");//
                    }else{
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileExecute");//
                    }
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(liIdService);
                    loBitBean.setLiIndProcess(liIndProcess);
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento(loResDelFileTrailer.getLsMessage());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               liIdUser, 
                                               lsUserName);
                    
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
                                                     loFileBreaks.getName()+e.getMessage());
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


            //############################### PROGRAMM #####################################################
            lsWhere = " AND STNID = '"+lsChannel+"' AND BCSTDT BETWEEN '"+lsFecInicial+"' AND '"+lsFecFinal+"'";
            List<LmkProgRowBean> laProgrb = loPpDao.getProgProgramme(lsWhere);
            System.out.println("laProgrb.size() ["+laProgrb.size()+"]"); 
            
            ////////// File Trailer
            lsWhere = "AND STNID = '"+lsChannel+"' AND STRDT = '"+lsFecInicial+"' AND EDT ='"+lsFecFinal+"'";
            List<LmkProgFileTrailerRowBean> laPrgTrb = loPpDao.getProgProgrammeTrailer(lsWhere);
            System.out.println("laPrgTrb.size() ["+laPrgTrb.size()+"]"); 
            
            //##################### Crear Archivo PROGRAMM en Servidor de Aplicaciones  ##########################
            
            File loFileProgramm = null;
            try {
                loFileProgramm = getFileProgramm(laProgrb, laPrgTrb, lsPathFiles, lsChannel);
                liIndProcess = 
                            new UtilFaces().getIdConfigParameterByName("CreateFile");//
                        loBitBean.setLiIdLogServices(liIdLogService);
                        loBitBean.setLiIdService(liIdService);
                        loBitBean.setLiIndProcess(liIndProcess);
                        loBitBean.setLiNumProcessId(0);
                        loBitBean.setLiNumPgmProcessId(0);
                        loBitBean.setLsIndEvento("Archivo "+loFileProgramm.getName()+
                                                 " Creado para servicio "+lsServiceName);
                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                           liIdUser, 
                                                           lsUserName);
                
                //##################### Insertar Archivo en Base de Datos ############################ 
                try {
                    XmlFilesDao loXmlFilesDao = new XmlFilesDao();
                    LmkIntXmlFilesRowBean loXmlBean = new LmkIntXmlFilesRowBean();
                    FileInputStream loFis = new FileInputStream(loFileProgramm);
                    
                    loXmlBean.setLiIdFileXml(0);
                    loXmlBean.setLiIdRequest(liIdLogService);
                    loXmlBean.setLiIdService(liIdService);
                    loXmlBean.setLsNomFile(loFileProgramm.getName());
                    loXmlBean.setLsIndFileType("Response");
                    loXmlBean.setLsIndServiceType(lsTypeProcess);
                    loXmlBean.setLsIndEstatus("1");
                    loXmlBean.setLsNomUserName(lsUserName);
                    loXmlBean.setLsNomUserPathFile(lsPathFiles);
                    loXmlBean.setLiIdUser(liIdUser);
                    loXmlBean.setLoIndFileStream(loFis);
                    loXmlBean.setLsAttribute1(""+lsChannel+","+lsFecInicial+","+lsFecFinal);
                    loXmlBean.setLsAttribute2("ProgramFileType");
                    // - Guardar archivo en bd
                    ResponseUpdDao loXmlFile = 
                        loXmlFilesDao.insertLmkIntXmlFilesTab(loXmlBean);
                    String lsMessInsert = "";
                    if(loXmlFile.getLsResponse().equalsIgnoreCase("OK")){
                        lsMessInsert = "El Archivo "+loFileProgramm.getName()+" se ha guardado en base de datos";
                    }else{
                        lsMessInsert = "Error " + loXmlFile.getLsMessage() + " al guardar archivo "+
                                       loFileProgramm.getName()+" size: "+loFileProgramm.getTotalSpace();
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
                    
                    SftpManagment loSftpMgmnt = new SftpManagment();
                    String lsRemotePath = loEntityMappedDao.getGeneralParameter("PATH_PROGRAMM", "SSH_CONNECTION");                    
                    //tsRemotePath,
                    //tsLocalPath = lsPathFiles,
                    //tsRemoteFileName = loFileBreaks.getName(),
                    //tsLocalFileName = loFileBreaks.getName()
                    ResponseUpdDao loResUpdDao = 
                        loSftpMgmnt.uploadFileSFTP(lsRemotePath, 
                                                   lsPathFiles, 
                                                   loFileProgramm.getName(), 
                                                   loFileProgramm.getName()
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
                    
                    //Eliminar registros de las tablas con parametros de CANAL, FECHA_INICIAL y FECHA_FINAL
                    ResponseUpdDao loResDelProgramm = 
                        loPpDao.deleteLmkProgProgramm(lsChannel, lsFecInicial, lsFecFinal);
                    if(!loResDelProgramm.getLsResponse().equalsIgnoreCase("OK")){
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileError");//
                    }else{
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileExecute");//
                    }
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(liIdService);
                    loBitBean.setLiIndProcess(liIndProcess);
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento(loResDelProgramm.getLsMessage());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               liIdUser, 
                                               lsUserName);
                    
                    ResponseUpdDao loResDelFileTrailer = 
                        loPpDao.deleteLmkProgFileTrailer(lsChannel, lsFecInicial, lsFecFinal);
                    if(!loResDelFileTrailer.getLsResponse().equalsIgnoreCase("OK")){
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileError");//
                    }else{
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("DeleteFileExecute");//DeleteFileExecute
                    }
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(liIdService);
                    loBitBean.setLiIndProcess(liIndProcess);
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento(loResDelFileTrailer.getLsMessage());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               liIdUser, 
                                               lsUserName);
                    
                    
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
                                                     loFileProgramm.getName()+e.getMessage());
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
            
        }
        
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
        
        System.out.println("["+lsChannel+"] FIN DE JOB ["+new Date()+"]");
    }
    
    /**
     * Obtiene, en base a la fecha, el prefijo para el nombre del archivo
     * @autor Jorge Luis Bautista Santiago     
     * @return String
     */
    public String getPrefixFileName(){
        String lsResponse = null;
        DateFormat loDf = new SimpleDateFormat("yyyyMMddHHmm");
        lsResponse = loDf.format(new java.util.Date(System.currentTimeMillis()));
        return lsResponse;
    }
    
    /**
     * Crea archivo .PROG en base a las tablas de Paradigm
     * @autor Jorge Luis Bautista Santiago   
     * @param lsPath
     * @return File
     */
    public File getFileProgramm(List<LmkProgRowBean> taProgrb,
                                List<LmkProgFileTrailerRowBean> laPrgTrb,
                                String tsPath,
                                String tsChannel) throws Exception{
        
        String lsFileName = getMappingChannelName(tsChannel)+this.getPrefixFileName()+"PLAN.PROG";
        File loFileResponse = new File(tsPath+"files\\"+lsFileName);
        System.out.println("Ruta: "+loFileResponse.getPath());
        try {
            FileWriter loWriter = new FileWriter(loFileResponse, true);
            for(LmkProgRowBean loProg : taProgrb){
                String lsRow = loProg.getLiRecordType()+","+ 
                loProg.getLsSalesAreaCode()+","+ 
                loProg.getLsTransmissionRegionCode()+","+ 
                loProg.getLtProgrammeTransmissionDate()+","+ 
                loProg.getLiProgrammeTransmissionStart()+","+ 
                loProg.getLiProgrammeTransmissionEnd()+","+ 
                loProg.getLsRegionalVariation()+","+ 
                loProg.getLsDominantRegionIndicator()+","+ 
                loProg.getLsProgrammeName()+","+ 
                loProg.getLiProgrammeId()+","+ 
                loProg.getLiDuration()+","+ 
                loProg.getLsLiveBroadcast()+","+ 
                loProg.getLsProgrammeOverselling()+","+ 
                loProg.getLsProgrammeShowing()+","+ 
                loProg.getLsProgrammeDescription()+","+ 
                loProg.getLsProgrammeComment()+","+ 
                loProg.getLsEpisodeName()+","+ 
                loProg.getLiEpisodeId()+","+ 
                loProg.getLsCertification()+","+ 
                loProg.getLsProgrammeCategory()+",";
                //loProg.getLsStnid()+","+ 
                //loProg.getLtBcstdt()+","+                                
                //loProg.getLsPgmid()+",";
                loWriter.write(lsRow);
                loWriter.write("\r\n");
            }      
            
            for(LmkProgFileTrailerRowBean loProgTrailer : laPrgTrb){
                String lsRow = loProgTrailer.getLiRecordType()+","+ 
                loProgTrailer.getLiRecordCount()+","+ 
                loProgTrailer.getLiAllowableGap()+",";
                //loProgTrailer.getLsStnid()+","+ 
                //loProgTrailer.getLsStrdt()+","+
                //loProgTrailer.getLsEdt()+",";                              
                loWriter.write(lsRow);
                loWriter.write("\r\n");
            }  
            
            loWriter.close();
        }
        catch (Exception e) {
            System.out.println("Error al escribir "+e.getMessage());
        }
        return loFileResponse;
    }
    /**
     * Crea archivo .PROG en base a las tablas de Paradigm
     * @autor Jorge Luis Bautista Santiago   
     * @param String
     * @return String
     */
    public String getMappingChannelName(String tsChannelPgm){
        String lsChannelLmk = ""+tsChannelPgm;
        MappingCatDao loMappingCatDao = new MappingCatDao();
        String lsWhere = " IND_USED_BY      = 'FILE_CHANNEL_NAME'\n" + 
        "   AND VAL_VALUE_ORIGIN = '" + tsChannelPgm + "'";
        List<LmkIntMappingCatRowBean> laList = loMappingCatDao.getLmkIntServicesParams(lsWhere);
        if(laList.size() > 0){
            lsChannelLmk = laList.get(0).getLsValValueDestiny();
        }
        return lsChannelLmk;
    }        
    
    /**
     * Crea archivo .brk en base a las tablas de Paradigm
     * @autor Jorge Luis Bautista Santiago   
     * @param lsPath
     * @return File
     */
    public File getFileBreaks(List<LmkBrkFileHeaderRowBean> taBfhrb,
                              List<LmkBrkChannelHeaderRowBean> taChrb,
                              List<LmkBrkBreakRowBean> taBrb,
                              List<LmkBrkChannelTrailerRowBean> taCtrb,
                              List<LmkBrkFileTrailerRowBean> taFtrb,
                              String tsPath,
                              String tsChannel) throws Exception{
        
        String lsFileName = getMappingChannelName(tsChannel)+this.getPrefixFileName()+".brk";
        File loFileResponse = new File(tsPath+"files\\"+lsFileName);
        System.out.println("Ruta: "+loFileResponse.getPath());
        try {
            FileWriter loWriter = new FileWriter(loFileResponse, true);
            //########## LMK_BRK_FILE_HEADER  ##########
            for(LmkBrkFileHeaderRowBean loBrk : taBfhrb){
                String lsRow = loBrk.getLiRecordType()+"," +
                loBrk.getLsFileCreationDate()+","+ 
                loBrk.getLsFileCreationTime()+",";
                loWriter.write(lsRow);
                loWriter.write("\r\n");
            }      
            //########## LMK_BRK_CHANNEL_HEADER  ##########
            for(LmkBrkChannelHeaderRowBean loBrk : taChrb){
                String lsRow = loBrk.getLiRecordType()+"," +
                loBrk.getLsRegionalSalesAreaCode()+","+ 
                loBrk.getLsSalesAreaCode()+","+
                loBrk.getLiId()+","+
                loBrk.getLsBreakSchedule()+",";
                loWriter.write(lsRow);
                loWriter.write("\r\n");
            }     
            //########## LMK_BRK_BREAK ##########
            for(LmkBrkBreakRowBean loBrk : taBrb){
                String lsRow = loBrk.getLiRecordType()+"," +
                loBrk.getLsRegionalSalesCode()+","+ 
                loBrk.getLsSalesAreaCode()+","+
                loBrk.getLsBreakSchedule()+","+
                loBrk.getLiBreakNominal()+","+
                loBrk.getLiBreakDuration()+","+
                loBrk.getLsBreakTypeCode()+","+
                loBrk.getLsPositionInProgramme()+","+
                loBrk.getLiBreakNumber()+",";
                loWriter.write(lsRow);
                loWriter.write("\r\n");
            }    
            //########## LMK_BRK_CHANNEL_TRAILER ##########
            for(LmkBrkChannelTrailerRowBean loBrk : taCtrb){
                String lsRow = loBrk.getLiRecordType()+"," +
                loBrk.getLiRecordCount()+",";
                loWriter.write(lsRow);
                loWriter.write("\r\n");
            }   
            //########## LMK_BRK_FILE_TRAILER ##########
            for(LmkBrkFileTrailerRowBean loBrk : taFtrb){
                String lsRow = loBrk.getLiRecordType()+"," +
                loBrk.getLiRecordCount()+",";
                loWriter.write(lsRow);
                loWriter.write("\r\n");
            }   
                        
            loWriter.close();
        }
        catch (Exception e) {
            System.out.println("Error al escribir "+e.getMessage());
        }
        return loFileResponse;
    }
    
}
