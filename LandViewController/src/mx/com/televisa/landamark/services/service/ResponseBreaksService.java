/**
* Project: Integraton Paradigm - Landmark
*
* File: ResponseBreaksService.java
*
* Created on: Febrero 23, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.service;

import java.util.List;

import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.ResponseBreaksDao;
import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.model.types.LmkIntConfigParamRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesLogRowBean;
import mx.com.televisa.landamark.model.types.LmkIntXmlFilesRowBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;
import mx.com.televisa.landamark.services.sftp.SftpManagment;
import mx.com.televisa.landamark.util.UtilFaces;
import mx.com.televisa.landamark.view.types.BasicInputParameters;
import mx.com.televisa.landamark.view.types.ResponseService;

/** Clase que revisa respuesta de landmark para los archivos de breaks
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
 */
public class ResponseBreaksService {
    public ResponseBreaksService() {
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
        ResponseBreaksDao      loResponseBreaksDao = new ResponseBreaksDao();
        boolean                lbProcess = true;
       
        String lsReturn = "Breaks Response execute";
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
            new UtilFaces().getIdConfigParameterByName("ExecuteService");//
        loBitBean.setLiIdLogServices(liIdLogService);
        loBitBean.setLiIdService(loInput.getLiIdService());
        loBitBean.setLiIndProcess(liIndProcess);
        loBitBean.setLiNumProcessId(0);
        loBitBean.setLiNumPgmProcessId(0);
        loBitBean.setLsIndEvento("Ejecución de Servicio para Respuesta de Breaks de landmark "+
                                 loInput.getLsServiceName());
        
        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                           loInput.getLiIdUser(), 
                                           loInput.getLsUserName());   
        
        //- Ir a la bd de archivos fisicos y extrer los pendientes de respuesta
        String lsWhere = " AND NOM_FILE LIKE '%.brk' " +
            " AND IND_ESTATUS = 'L'"; //enviado a landmak
        List<LmkIntXmlFilesRowBean> laList = 
            loResponseBreaksDao.getAllFilesPending(lsWhere);
        
        if(laList.size() > 0){
            //- Ir a carpeta de landmark y extraer nombres de archivo *.brk
            String lsPath = 
                loEntityMappedDao.getGeneralParameter("PATH_BREAKS_OUT", "SSH_CONNECTION");
            String lsExt = "*.brk";
                
            SftpManagment loSmg = new SftpManagment();
            List<String> laLis = loSmg.getListFileServerSFTP(lsPath, lsExt);
            System.out.println("numero de archivos extriados del server ssh ["+laLis.size()+"]");
            
            for(LmkIntXmlFilesRowBean loBean : laList){
                int liI = 0;
                lbProcess = true;
                while(liI < laLis.size() && lbProcess == true){
                    //- Buscar cada nombre de archivo fisico en el grupo
                    //System.out.println("indexOf: loBean.getLsNomFile()["+loBean.getLsNomFile()+
                      //                 "] VS laLis.get("+liI+")["+laLis.get(liI).indexOf(loBean.getLsNomFile())+"]");
                    if(laLis.get(liI).indexOf(loBean.getLsNomFile()) >= 0){
                        //Es verdadero
                        System.out.println("COINCIDENCIA ENCONTRADA PARA ("+loBean.getLsNomFile()+")");
                        lbProcess = false;
                        String lsStatus = "C";
                        //Verificar si contiene la cadena de error
                        System.out.println(""+laLis.get(liI)+".indexOf(\"FAIL\"): "+laLis.get(liI).indexOf("FAIL"));
                        if(laLis.get(liI).indexOf("FAIL") >= 0){
                            lsStatus = "E";
                        }                    
                        //- Actualizar estatus si fue encontrado
                        loResponseBreaksDao.updateEstatusXmlFiles(loBean.getLiIdFileXml(), lsStatus);
                    }
                    liI++;
                }
                
            }    
            
        }else{
            liIndProcess = 
                new UtilFaces().getIdConfigParameterByName("NoData");//
            loBitBean.setLiIdLogServices(liIdLogService);
            loBitBean.setLiIdService(loInput.getLiIdService());
            loBitBean.setLiIndProcess(liIndProcess);
            loBitBean.setLiNumProcessId(0);
            loBitBean.setLiNumPgmProcessId(0);
            loBitBean.setLsIndEvento("No existen archivos con pendiente de respuesta");
            
            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               loInput.getLiIdUser(), 
                                               loInput.getLsUserName());   
        }
        
        return loResponseService;
                   
    }
}
