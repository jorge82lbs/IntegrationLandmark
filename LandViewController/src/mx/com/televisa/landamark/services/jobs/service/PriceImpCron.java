/**
* Project: Integraton Paradigm - Landmark
*
* File: PriceImpCron.java
*
* Created on: Julio 29, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.jobs.service;

import java.util.Date;

import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.util.UtilFaces;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/** Clase que ejecuta logica o servicio de Actualizacion de Precios
 * por canal
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Julio 29, 2019, 12:00 pm
 */
public class PriceImpCron  implements Job{
    public PriceImpCron() {
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
        
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
        String lsParametersClass = lsChannel+","+lsFecInicial+","+lsFecFinal;
        System.out.println("["+lsChannel+"]Ejecucion de Cron (Actualizacion de Precios) >> ["+new Date()+"]");
        
        //ResponseUpdDao loRes = loPpDao.callLmkProgBrkPr(lsChannel, lsFecInicial, lsFecFinal);
        System.out.println("Logica de actualizacion de precios por canal configurado["+lsChannel+"]");
        //0.- Validar si es posible procesar, considerar:
        //0.1.- Que se hayan ejecutado los procesos previos, as run as
        //0.2.- Segun Jacobo, validar con el mismo query de log certificado
        
        //1.- Consumir servicio de jacobo 
        //1.1.- Almacenar en bd elemento-valor de: Authorization: Basic
        //1.2.- Almacenar en bd elemento-valor de: Password7 (encriptado)
        //1.3.- Almacenar en bd elemento valor de: LMK-Environment
        //1.4.- Almacenar en bd elemento valor de: 1:tlvtrain
        
        //2.- Leer el response XML
        //2.1.- Por ahora cada dato pasa sin validacion
        
        //3.- Insertar en tablas de Alex Morel
        //3.1.- Preguntar a Jacobo, por el mapeo de campos de las tablas
        
        

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
    
}
