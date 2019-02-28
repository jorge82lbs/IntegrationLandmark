/**
* Project: Integraton Paradigm - Landmark
*
* File: SftpManagment.java
*
* Created on: Febrero 23, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.util.ArrayList;
import java.util.List;

import java.util.Vector;

import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.types.LmkIntConfigParamRowBean;
import mx.com.televisa.landamark.model.types.LmkIntSftpCnnBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;
import mx.com.televisa.landamark.util.UtilFaces;

/** Clase que aministra conexion remota por sftp
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
 */
public class SftpManagment {
    public SftpManagment() {
        super();
    }
    
    /**
    * Descarga un archivo de una ubicacion remota con protocolo sftp
    * @autor Jorge Luis Bautista Santiago
    * @param tsRemotePath
    * @param tsLocalPath
    * @param tsRemoteFileName
    * @param tsLocalFileName
    * @return ResponseUpdDao
    */
    public ResponseUpdDao downloadFileSFTP(String tsRemotePath,
                                           String tsLocalPath,
                                           String tsRemoteFileName,
                                           String tsLocalFileName) {
        ResponseUpdDao loResponse = new ResponseUpdDao();
        JSch loJsch = new JSch();
        Session loSession = null;
        try {
            LmkIntSftpCnnBean loSftpCnn = getSftpDataConnection();
            System.out.println("Incio....");
            loSession = loJsch.getSession(loSftpCnn.getLsPassword(), 
                                          loSftpCnn.getLsHost(), 
                                          loSftpCnn.getLiPort());
            loSession.setConfig("StrictHostKeyChecking", "no");
            loSession.setPassword(loSftpCnn.getLsPassword());
            loSession.connect();
            
            Channel loChannel = loSession.openChannel("sftp");
            loChannel.connect();
            ChannelSftp loSftpChannel = (ChannelSftp) loChannel;
            String lsRemote = tsRemotePath + "/" + tsRemoteFileName;
            String lsLocal = tsLocalPath + "/" + tsLocalFileName;
            loSftpChannel.get(lsRemote, lsLocal);  
            loSftpChannel.exit();
            loSession.disconnect();
            loResponse.setLsResponse("OK");
            loResponse.setLsMessage("Archivo descargado satisfactoriamente");
            loResponse.setLiAffected(0);
            System.out.println("TODO OK");
        } catch (JSchException loEx) {
            loResponse.setLsResponse("ERROR");
            loResponse.setLsMessage(loEx.getMessage());
            loResponse.setLiAffected(0);
        } catch (SftpException loEx) {
            loResponse.setLsResponse("ERROR");
            loResponse.setLsMessage(loEx.getMessage());
            loResponse.setLiAffected(0);
        }
        return loResponse;
    }
    
    /**
    * Envia archivo a ubicacion remota con protocolo sftp
    * @autor Jorge Luis Bautista Santiago
    * @param tsRemotePath
    * @param tsLocalPath
    * @param tsRemoteFileName
    * @param tsLocalFileName
    * @return ResponseUpdDao
    */
    public ResponseUpdDao uploadFileSFTP(String tsRemotePath,
                                           String tsLocalPath,
                                           String tsRemoteFileName,
                                           String tsLocalFileName) {
        ResponseUpdDao loResponse = new ResponseUpdDao();
        JSch loJsch = new JSch();
        Session loSession = null;
        try {
            LmkIntSftpCnnBean loSftpCnn = getSftpDataConnection();
            System.out.println("Incio....");
            loSession = loJsch.getSession(loSftpCnn.getLsPassword(), 
                                          loSftpCnn.getLsHost(), 
                                          loSftpCnn.getLiPort());
            loSession.setConfig("StrictHostKeyChecking", "no");
            loSession.setPassword(loSftpCnn.getLsPassword());
            loSession.connect();
            
            Channel loChannel = loSession.openChannel("sftp");
            loChannel.connect();
            ChannelSftp loSftpChannel = (ChannelSftp) loChannel;
            String lsRemote = tsRemotePath + "/" + tsRemoteFileName;
            String lsLocal = tsLocalPath + "/" + tsLocalFileName;
            loSftpChannel.put(lsLocal,lsRemote);  
            loSftpChannel.exit();
            loSession.disconnect();
            loResponse.setLsResponse("OK");
            loResponse.setLsMessage("El Archivo "+tsLocalFileName+" se ha enviado satisfactoriamente");
            loResponse.setLiAffected(0);
            System.out.println("TODO OK");
        } catch (JSchException loEx) {
            loResponse.setLsResponse("ERROR");
            loResponse.setLsMessage(loEx.getMessage());
            loResponse.setLiAffected(0);
        } catch (SftpException loEx) {
            loResponse.setLsResponse("ERROR");
            loResponse.setLsMessage("ERROR al enviar["+tsLocalFileName+"]: "+loEx.getMessage());
            loResponse.setLiAffected(0);
        }
        return loResponse;
    }
    
    /**
    * Obtiene una lista de nombres de archivos almacenados en una ubicacion remota
    * con protocolo sftp
    * @autor Jorge Luis Bautista Santiago
    * @param tsPath
    * @param tsExt
    * @return List
    */
    public List<String> getListFileServerSFTP(String tsPath, 
                                              String tsExt) {
        List<String> laList = new ArrayList<String>();
        JSch         loJsch = new JSch();
        Session      loSession = null;
        try {
            System.out.println("Incio....");
            LmkIntSftpCnnBean loSftpCnn = getSftpDataConnection();
            loSession = loJsch.getSession(loSftpCnn.getLsPassword(), 
                                          loSftpCnn.getLsHost(), 
                                          loSftpCnn.getLiPort());
            loSession.setConfig("StrictHostKeyChecking", "no");
            loSession.setPassword(loSftpCnn.getLsPassword());
            loSession.connect();
            
            Channel loChannel = loSession.openChannel("sftp");
            loChannel.connect();
            ChannelSftp loSftpChannel = (ChannelSftp) loChannel;
            
            Vector laFilelist = loSftpChannel.ls(tsPath + tsExt);
            for(int liI = 0; liI < laFilelist.size() ; liI++){
                ChannelSftp.LsEntry loEntry = (ChannelSftp.LsEntry) laFilelist.get(liI);
                System.out.println("Archivo:["+loEntry.getFilename()+"]");
                laList.add(loEntry.getFilename());
            }
            loSftpChannel.exit();
            loSession.disconnect();
            System.out.println("TODO OK");
        } catch (JSchException loEx) {
            loEx.printStackTrace();  
        } catch (SftpException loEx) {
            loEx.printStackTrace();
        }
        
        return laList;
    
    }
    
    /**
    * Actualiza registro del log de servicio
    * @autor Jorge Luis Bautista Santiago
    * @param tsRemotePath
    * @param tsLocalPath
    * @param tsRemoteFileName
    * @param tsLocalFileName
    * @return LmkIntSftpCnnBean
    */
    public LmkIntSftpCnnBean getSftpDataConnection(){
        LmkIntSftpCnnBean              loLmkIntSftpCnnBean = new LmkIntSftpCnnBean();
        EntityMappedDao                loEntityMappedDao = new EntityMappedDao();
        List<LmkIntConfigParamRowBean> loList = 
            loEntityMappedDao.getParametersByUsed("SSH_CONNECTION");
        for(LmkIntConfigParamRowBean loBean : loList){
            if(loBean.getLsNomParameter().equalsIgnoreCase("SSH_HOST")){
                loLmkIntSftpCnnBean.setLsHost(loBean.getLsValueParameter());
            }
            if(loBean.getLsNomParameter().equalsIgnoreCase("SSH_PORT")){
                loLmkIntSftpCnnBean.setLiPort(Integer.parseInt(loBean.getLsValueParameter()));
            }
            if(loBean.getLsNomParameter().equalsIgnoreCase("SSH_USER")){
                loLmkIntSftpCnnBean.setLsUser(loBean.getLsValueParameter());
            }
            if(loBean.getLsNomParameter().equalsIgnoreCase("SSH_PASSWORD")){
                String lsPwd = null;
                try {
                    //Seguramente estará codificado, entonces decodificar
                    UtilFaces loUf = new UtilFaces();
                    String lsKey = loUf.getKeyDecoder();
                    lsPwd = loUf.decryptObject(loBean.getLsValueParameter(), lsKey);
                } catch (Exception e) {
                    ;
                }
                loLmkIntSftpCnnBean.setLsUser(lsPwd);
            }
        }
        
        return loLmkIntSftpCnnBean;
        
    }
    
}
