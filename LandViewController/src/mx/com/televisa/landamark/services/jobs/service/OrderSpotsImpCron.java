/**
* Project: Integraton Paradigm - Landmark
*
* File: OrderSpotsImpCron.java
*
* Created on: Febrero 23, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.jobs.service;

import java.io.File;
import java.io.FileInputStream;

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

/** Clase que ejecuta la lectura, guardado y logica de cada archivo
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
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
        SftpManagment             loSftpManagment = new SftpManagment();
        EntityMappedDao           loEntityMappedDao = new EntityMappedDao();
        Integer                   liIndProcess = 0;
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
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
            loBitBean.setLiIdLogServices(Integer.parseInt(lsRequestMaster));
            loBitBean.setLiIdService(Integer.parseInt(lsIdService));
            loBitBean.setLiIndProcess(liIndProcess);
            loBitBean.setLiNumProcessId(0);
            loBitBean.setLiNumPgmProcessId(0);
            loBitBean.setLsIndEvento("Archivo no encontrado en ubicacion remota "+
                                     lsServiceName);
            
            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               Integer.parseInt(lsIdUser), 
                                               lsUserName);   
        }else{
            //Si el archivo si fue encontrado, entonces guardar copia en carpeta de WL  
            //-- Esto ya esta, al momento de la instruccion            
            //Guardar en base de datos (estatus=P (en paradigm))
            //##################### Insertar Archivo en Base de Datos ############################ 
            try {
                System.out.println("leyendo archivo de: ["+lsPathFiles+lsFileName+"]");
                File loFileInput = new File (lsPathFiles+lsFileName);
                XmlFilesDao loXmlFilesDao = new XmlFilesDao();
                LmkIntXmlFilesRowBean loXmlBean = new LmkIntXmlFilesRowBean();
                FileInputStream loFis = new FileInputStream(loFileInput);
                
                loXmlBean.setLiIdFileXml(0);
                loXmlBean.setLiIdRequest(Integer.parseInt(lsRequestMaster));
                loXmlBean.setLiIdService(Integer.parseInt(lsIdService));
                loXmlBean.setLsNomFile(lsFileName);
                loXmlBean.setLsIndFileType("Requests");
                loXmlBean.setLsIndServiceType(lsTypeProcess);
                loXmlBean.setLsIndEstatus("R"); //De Creado
                loXmlBean.setLsNomUserName(lsUserName);
                loXmlBean.setLsNomUserPathFile(lsPathFiles);
                loXmlBean.setLiIdUser(Integer.parseInt(lsIdUser));
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
                liIndProcess = 
                            new UtilFaces().getIdConfigParameterByName("InsertFile");//
                        loBitBean.setLiIdLogServices(Integer.parseInt(lsRequestMaster));
                        loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                        loBitBean.setLiIndProcess(liIndProcess);
                        loBitBean.setLiNumProcessId(0);
                        loBitBean.setLiNumPgmProcessId(0);
                        loBitBean.setLsIndEvento(lsMessInsert);
                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   Integer.parseInt(lsIdUser), 
                                                   lsUserName);                                                
                
                //Leer archivo (estatus=W (leido))
                //mapear de acuerdo a Paradigm
                //Ejecutar logica que indique jacobo (estatusP (Procesado)) FIN
                
                
            }catch(Exception loEx){
                liIndProcess = 
                            new UtilFaces().getIdConfigParameterByName("GeneralError");//
                        loBitBean.setLiIdLogServices(Integer.parseInt(lsRequestMaster));
                        loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                        loBitBean.setLiIndProcess(liIndProcess);
                        loBitBean.setLiNumProcessId(0);
                        loBitBean.setLiNumPgmProcessId(0);
                        loBitBean.setLsIndEvento("ERROR. "+loEx.getMessage());
                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   Integer.parseInt(lsIdUser), 
                                                   lsUserName);
                
            }
                        
        }
        
        
    }
}
