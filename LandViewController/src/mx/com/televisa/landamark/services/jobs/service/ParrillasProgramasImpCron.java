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
import java.io.FileOutputStream;
import java.io.FileWriter;

import java.io.ObjectOutputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;

import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.MappingCatDao;
import mx.com.televisa.landamark.model.daos.ParrillasProgramasDao;

import mx.com.televisa.landamark.model.daos.ResponseBreaksDao;
import mx.com.televisa.landamark.model.daos.XmlFilesDao;
import mx.com.televisa.landamark.model.types.LmkBrkGenericRowBean;
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
            System.out.println("Error en Servicio "+lsServiceName+" "+loRes.getLsMessage());
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
            boolean lbSendFile = true;
            String lsMessSourceErr = "";
            String lsWhere = 
                " AND STNID = '"+lsChannel+"' AND BCSTDT BETWEEN DATE('"+lsFecInicial+"') AND DATE('"+lsFecFinal+"')";            
            //############################### BREAKS #####################################################
            List<LmkBrkGenericRowBean> laNivel1 = loPpDao.getBrkFullNivel1(lsChannel, lsFecInicial, lsFecFinal);
            System.out.println("laNivel1.size() ["+laNivel1.size()+"]");
            if(laNivel1.size() <= 0){
                lbSendFile = false;
                lsMessSourceErr += "NIVEL 1, ";
            }            
            
            List<LmkBrkGenericRowBean> laNivel23 = loPpDao.getBrkFullNivel23(lsChannel, lsFecInicial, lsFecFinal);
            System.out.println("laNivel23.size() ["+laNivel23.size()+"]");
            if(laNivel23.size() <= 0){
                lbSendFile = false;
                lsMessSourceErr += "NIVEL 2 y 3, ";
            }
            
            List<LmkBrkGenericRowBean> laNivel45 = loPpDao.getBrkFullNivel45(lsChannel, lsFecInicial, lsFecFinal);
            System.out.println("laNivel45.size() ["+laNivel45.size()+"]");
            if(laNivel45.size() <= 0){
                lbSendFile = false;
                lsMessSourceErr += "NIVEL 4 y 5, ";
            }
            
            /*
            ////////// FILE HEADER RECORD
            lsWhere = " AND STNID       = '"+lsChannel+"'\n" + 
            "   AND DATE(STRDT) = DATE('"+lsFecInicial+"') \n" + 
            "   AND DATE(EDT)   = DATE('"+lsFecFinal+"') ";
            List<LmkBrkFileHeaderRowBean> laBfhrb = loPpDao.getBrkFileHeader(lsWhere);
            System.out.println("laBfhrb.size() ["+laBfhrb.size()+"]");
            if(laBfhrb.size() <= 0){
                lbSendFile = false;
                lsMessSourceErr += "FILE HEADER RECORD, ";
            }
            
            ////////// CHANNEL HEADER RECORD
            lsWhere = " AND STNID       = '"+lsChannel+"'\n" + 
            "   AND DATE(BCSTDT) BETWEEN DATE('"+lsFecInicial+"') \n" + 
            "                        AND DATE('"+lsFecFinal+"')";
            List<LmkBrkChannelHeaderRowBean> laChrb = loPpDao.getBrkChannelHeader(lsWhere);
            System.out.println("laChrb.size() ["+laChrb.size()+"]");
            if(laChrb.size() <= 0){
                lbSendFile = false;
                lsMessSourceErr += "CHANNEL HEADER RECORD, ";
            }
            
            ////////// BREAK RECORD
            lsWhere = " AND STNID       = '"+lsChannel+"'\n" + 
            "   AND DATE(BCSTDT) BETWEEN DATE('"+lsFecInicial+"') \n" + 
            "                        AND DATE('"+lsFecFinal+"')";
            List<LmkBrkBreakRowBean> laBrb = loPpDao.getBrkBreak(lsWhere);
            System.out.println("laBrb.size() ["+laBrb.size()+"]");
            if(laBrb.size() <= 0){
                lbSendFile = false;
                lsMessSourceErr += "BREAK RECORD, ";
            }
            
            ////////// CHANNEL TRAILER RECORD
            lsWhere = " AND STNID       = '"+lsChannel+"'\n" + 
            "   AND DATE(BCSTDT) BETWEEN DATE('"+lsFecInicial+"') \n" + 
            "                        AND DATE('"+lsFecFinal+"')";
            List<LmkBrkChannelTrailerRowBean> laCtrb = loPpDao.getBrkChannelTrailer(lsWhere);
            System.out.println("laCtrb.size() ["+laCtrb.size()+"]");
            if(laCtrb.size() <= 0){
                lbSendFile = false;
                lsMessSourceErr += "CHANNEL TRAILER RECORD, ";
            }
            
            ////////// FILE TRAILER RECORD
            lsWhere = " AND STNID = '"+lsChannel+"' " +
                "AND STRDT ='"+lsFecInicial+"' AND EDT = '"+lsFecFinal+"'";
            List<LmkBrkFileTrailerRowBean> laFtrb = loPpDao.getBrkFileTrailer(lsWhere);
            System.out.println("laFtrb.size() ["+laFtrb.size()+"]"); 
            if(laFtrb.size() <= 0){
                lbSendFile = false;
                lsMessSourceErr += "FILE TRAILER RECORD, ";
            }
            */
                       
            //############################### PROGRAMM #####################################################
            //lsWhere = " AND STNID = '"+lsChannel+"' AND BCSTDT BETWEEN '"+lsFecInicial+"' AND '"+lsFecFinal+"'";
            lsWhere = "AND STRDT = '"+lsFecInicial+"'\n" + 
            "AND EDT = '"+lsFecFinal+"'\n" + 
            "AND STNID = '"+lsChannel+"'";
            List<LmkProgRowBean> laProgrb = loPpDao.getProgProgramme(lsWhere);
            System.out.println("laProgrb.size() ["+laProgrb.size()+"]"); 
            if(laProgrb.size() <= 0){
                lbSendFile = false;
            }
            
            ////////// File Trailer
            lsWhere = "AND STNID = '"+lsChannel+"' AND STRDT = '"+lsFecInicial+"' AND EDT ='"+lsFecFinal+"'";
            List<LmkProgFileTrailerRowBean> laPrgTrb = loPpDao.getProgProgrammeTrailer(lsWhere);
            System.out.println("laPrgTrb.size() ["+laPrgTrb.size()+"]"); 
            if(laPrgTrb.size() <= 0){
                lbSendFile = false;
            }
            
            System.out.println("Valor de bandera enviar SSH ["+lbSendFile+"]");
            
            if(lbSendFile){
                //##################### Crear Archivo BREAKS en Servidor de Aplicaciones  ######################

                try {
                    File loFileBreaks = getFileBreaksConcat(laNivel1, laNivel23, laNivel45, lsPathFiles, lsChannel);
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
                        loXmlBean.setLsIndEstatus("R"); //De Creado
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
                        System.out.println("Ruta remota PATH_BREAKS: "+lsRemotePath);
                        //tsRemotePath,
                        //tsLocalPath = lsPathFiles,
                        //tsRemoteFileName = loFileBreaks.getName(),
                        //tsLocalFileName = loFileBreaks.getName()
                        ResponseUpdDao loResSendFile = 
                            loSftpMgmnt.uploadFileSFTP(lsRemotePath, 
                                                       lsPathFiles, 
                                                       loFileBreaks.getName(), 
                                                       loFileBreaks.getName()
                                                       );
                        String lsMsg = loResSendFile.getLsMessage();
                        String lsStatEv = "L";
                        if(!loResSendFile.getLsResponse().equalsIgnoreCase("OK")){
                            lsMsg = "ERROR "+loResSendFile.getLsMessage();
                            lsStatEv = "F";
                        }
                        
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("SendFileSSH");//
                        loBitBean.setLiIdLogServices(liIdLogService);
                        loBitBean.setLiIdService(liIdService);
                        loBitBean.setLiIndProcess(liIndProcess);
                        loBitBean.setLiNumProcessId(0);
                        loBitBean.setLiNumPgmProcessId(0);
                        loBitBean.setLsIndEvento(lsMsg);
                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               liIdUser, 
                                               lsUserName);
                        //Actualizar estatus de archivo xml
                        ResponseBreaksDao loResponseBreaksDao = new ResponseBreaksDao();
                        loResponseBreaksDao.updateEstatusXmlFiles(loXmlFile.getLiAffected(), lsStatEv);
                        //NOTAS: loResUpdDao.getLiAffected() devuelve el numero id asignado, así lo programé
                        //E es de ENVIADO a Landmark
                        
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
                        
                        
                        try{
                            System.out.println("Eliminando archivo "+loFileBreaks.getName());
                            //loFileBreaks.deleteOnExit();
                            loFileBreaks.delete();
                            System.out.println("Eliminando archivo ok...");
                        }catch(Exception loExDel){
                            liIndProcess = 
                                        new UtilFaces().getIdConfigParameterByName("GeneralError");
                            loBitBean.setLiIdLogServices(liIdLogService);
                            loBitBean.setLiIdService(liIdService);
                            loBitBean.setLiIndProcess(liIndProcess);
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("Error al eliminar archivo ["+loFileBreaks.getName()+"]");
                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   liIdUser, 
                                                   lsUserName);
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
                        loXmlBean.setLsIndEstatus("R"); //De Creado
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
                        System.out.println("Ruta remota PATH_PROGRAMM: "+lsRemotePath);
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
                        
                        //Actualizar estatus de archivo xml
                        ResponseBreaksDao loResponseBreaksDao = new ResponseBreaksDao();
                        loResponseBreaksDao.updateEstatusXmlFiles(loXmlFile.getLiAffected(), "L");
                        //NOTAS: loResUpdDao.getLiAffected() devuelve el numero id asignado, así lo programé
                        //E es de ENVIADO a Landmark
                        
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
                                        new UtilFaces().getIdConfigParameterByName("DeleteFileError");
                        }else{
                            liIndProcess = 
                                        new UtilFaces().getIdConfigParameterByName("DeleteFileExecute");
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
                                                
                        try{
                            System.out.println("Eliminando archivo "+loFileProgramm.getName());
                            //loFileProgramm.deleteOnExit();
                            loFileProgramm.delete();
                            System.out.println("Eliminando archivo ok...");
                        }catch(Exception loExDel){
                            liIndProcess = 
                                        new UtilFaces().getIdConfigParameterByName("GeneralError");
                            loBitBean.setLiIdLogServices(liIdLogService);
                            loBitBean.setLiIdService(liIdService);
                            loBitBean.setLiIndProcess(liIndProcess);
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("Error al eliminar archivo ["+loFileProgramm.getName()+"]");
                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   liIdUser, 
                                                   lsUserName);
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
            }else{
                //Insertar en bitacora que no se obtuvieron registros 
                liIndProcess = 
                            new UtilFaces().getIdConfigParameterByName("GeneralError");//
                        loBitBean.setLiIdLogServices(liIdLogService);
                        loBitBean.setLiIdService(liIdService);
                        loBitBean.setLiIndProcess(liIndProcess);
                        loBitBean.setLiNumProcessId(0);
                        loBitBean.setLiNumPgmProcessId(0);
                        loBitBean.setLsIndEvento("["+lsServiceName+"] Los archivos no fueron creados " +
                            "Sin registros para "+lsMessSourceErr);
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
        File loFileResponse = new File(tsPath+""+lsFileName);
        //File loFileResponse = File.createTempFile(lsFileName,null);
        System.out.println("Ruta: "+loFileResponse.getPath());
        try {
            FileWriter loWriter = new FileWriter(loFileResponse, true);
            for(LmkProgRowBean loProg : taProgrb){
                if(loProg.getLsFullRow() != null){                                        
                    String lsRow = loProg.getLsFullRow();
                    /*
                    loProg.getLiRecordType()+","+ 
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
                    */
                    loWriter.write(lsRow);
                    loWriter.write("\r\n");
                }
            }      
            for(LmkProgFileTrailerRowBean loProgTrailer : laPrgTrb){
                /*String lsRow = loProgTrailer.getLiRecordType()+","+ 
                loProgTrailer.getLiRecordCount()+","+ 
                loProgTrailer.getLiAllowableGap()+",";
                //loProgTrailer.getLsStnid()+","+ 
                //loProgTrailer.getLsStrdt()+","+
                //loProgTrailer.getLsEdt()+",";         
                */
                if(loProgTrailer.getLsFullRowTrailer() != null){
                    String lsRow = loProgTrailer.getLsFullRowTrailer();
                    loWriter.write(lsRow);
                    loWriter.write("\r\n");
                }
            }  
            loWriter.close();
        }
        catch (Exception e) {
            System.out.println("PROGRAMAS: Error al escribir en ruta local: "+e.getMessage());
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
        File loFileResponse = new File(tsPath+""+lsFileName);
        //File loFileResponse = File.createTempFile(lsFileName,null);
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
            System.out.println("BREAKS: Error al escribir en ruta local: "+e.getMessage());
        }
        return loFileResponse;
    }
    
    /**
     * Crea archivo .brk en base a las tablas de Paradigm
     * @autor Jorge Luis Bautista Santiago   
     * @param lsPath
     * @return File
     */
    public File getFileBreaksConcat(List<LmkBrkGenericRowBean> taNivel1,
                              List<LmkBrkGenericRowBean> taNivel23,
                              List<LmkBrkGenericRowBean> taNivel45,                             
                              String tsPath,
                              String tsChannel) throws Exception{
        
        String lsFileName = getMappingChannelName(tsChannel)+this.getPrefixFileName()+".brk";
        
        //ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream(lsFileName));
        File loFileResponse = new File(tsPath+""+lsFileName);
        
        //File loFileResponse = File.createTempFile(lsFileName,null);
        
        
        System.out.println("Ruta: "+loFileResponse.getPath());
        try {
            FileWriter loWriter = new FileWriter(loFileResponse, true);
            //########## NIVEL 1  ##########
            for(LmkBrkGenericRowBean loBrk : taNivel1){
                String lsRow = loBrk.getLsFullRowNivel1();
                loWriter.write(lsRow);
                loWriter.write("\r\n");
                //escribiendoFichero.writeUTF(lsRow);
            }
            //########## NIVEL 2 y 3  ##########
            for(LmkBrkGenericRowBean loBrk : taNivel23){
                String lsRow = loBrk.getLsFullRowNivel23();
                loWriter.write(lsRow);
                loWriter.write("\r\n");
            }     
            //########## LMK_BRK_BREAK ##########
            for(LmkBrkGenericRowBean loBrk : taNivel45){
                String lsRow = loBrk.getLsFullRowNivel45();
                loWriter.write(lsRow);
                loWriter.write("\r\n");
            }                
                        
            loWriter.close();
        }
        catch (Exception e) {
            System.out.println("BREAKS: Error al escribir en ruta local: "+e.getMessage());
        }/*finally{
            escribiendoFichero.close();
        }*/
        return loFileResponse;
        
        //Mover archivo ya procecsado en el ssh
        
    }
    
}
