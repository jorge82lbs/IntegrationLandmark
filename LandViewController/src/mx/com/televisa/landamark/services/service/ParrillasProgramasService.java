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
import mx.com.televisa.landamark.util.UtilFaces;
import mx.com.televisa.landamark.view.types.BasicInputParameters;
import mx.com.televisa.landamark.view.types.LmkDynaParameters;
import mx.com.televisa.landamark.view.types.MapDynaParameters;
import mx.com.televisa.landamark.view.types.ResponseService;

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
        //### Validacion parametros:
        for(LmkIntServicesParamsRowBean loPrm: loParams){
            if(loPrm.getLsIndParameter().equalsIgnoreCase("2CAN")){
                ls2Can = loPrm.getLsIndValParameter();
            }
            if(loPrm.getLsIndParameter().equalsIgnoreCase("5CAN")){
                ls5Can = loPrm.getLsIndValParameter();
            }
            if(loPrm.getLsIndParameter().equalsIgnoreCase("9CAN")){
                ls9Can = loPrm.getLsIndValParameter();
            }
            if(loPrm.getLsIndParameter().equalsIgnoreCase("CLIENTE")){
                lsCliente = loPrm.getLsIndValParameter();
            }
        }
        
        if( (ls2Can+ls5Can+ls9Can+lsCliente).trim().length() == 0 ){
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
            // - Invocar Stored Procedure
            /*
            String tsStnid = "";
            String tsStrdt = ""; 
            String tsEdt = "";
            ResponseUpdDao loRes = loPpDao.callLmkProgBrkPr(tsStnid, tsStrdt, tsEdt);
            liIndProcess = 
                        new UtilFaces().getIdConfigParameterByName("ExeProcedure");//ExtractData
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(loInput.getLiIdService());
                    loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento("Se ha invocado LMK_PROG_BRK("+tsStnid+","+tsStrdt+","+tsEdt+")");
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                       loInput.getLiIdUser(), 
                                                       loInput.getLsUserName());
            */
            //if(loRes.getLsResponse().equalsIgnoreCase("ERROR")){
            if(1==2){
                liIndProcess = 
                            new UtilFaces().getIdConfigParameterByName("GeneralError");//Error en invocacion de SP
                        loBitBean.setLiIdLogServices(liIdLogService);
                        loBitBean.setLiIdService(loInput.getLiIdService());
                        loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
                        loBitBean.setLiNumProcessId(0);
                        loBitBean.setLiNumPgmProcessId(0);
                        //loBitBean.setLsIndEvento("Error en Servicio "+loInput.getLsServiceName()+" "+loRes.getLsMessage());
                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                           loInput.getLiIdUser(), 
                                                           loInput.getLsUserName());
            }
            else{
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
                File loFile = getPlainFileBreak(loInput.getLsPathFiles());
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
                }
                // - Enviar archivo a ruta FTP
            }
        }
                                
        System.out.println("Ejecutando "+loInput.getLsServiceType()+" a las "+
                           new Date()+" Input["+loInput.getLsMessage()+"]");

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
    
}
