/**
* Project: Integraton Paradigm - Landmark
*
* File: ServicesParamsDao.java
*
* Created on: Febrero 23, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.model.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import mx.com.televisa.landamark.model.cnn.ConnectionAs400;
import mx.com.televisa.landamark.model.types.LmkIntServicesParamsRowBean;

/** Clase 
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
 */
public class ServicesParamsDao {
    
    /**
     * Obtiene la configuracion de los parametros de un determinado servicio
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<LmkIntServicesParamsRowBean> getLmkIntServicesParams(Integer liIdService){
        List<LmkIntServicesParamsRowBean> laReturn = 
            new ArrayList<LmkIntServicesParamsRowBean>();
        Connection              loCnn = new ConnectionAs400().getConnection();
        ResultSet               loRs = null;
        String                  lsQueryParadigm = getQueryLmkIntServicesParams(liIdService);        
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkIntServicesParamsRowBean loItem = new LmkIntServicesParamsRowBean();
                loItem.setLiIdParameterServ(loRs.getInt("ID_PARAMETER_SERV"));
                loItem.setLiIdService(loRs.getInt("ID_SERVICE"));
                loItem.setLsIndParameter(loRs.getString("IND_PARAMETER"));
                loItem.setLsIndValParameter(loRs.getString("IND_VAL_PARAMETER"));
                loItem.setLsIndEstatus(loRs.getString("IND_ESTATUS"));
                laReturn.add(loItem);
            }
        } catch (SQLException loExSql) {
            loExSql.printStackTrace();
        }
        finally{
            try {
                loCnn.close();
                loRs.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return laReturn;
    }
    
    /**
     * Obtiene instruccion para crear la configuracion de los parametros de un determinado servicio
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public String getQueryLmkIntServicesParams(Integer liIdService){
        String lsQuery = "SELECT ID_PARAMETER_SERV,\n" + 
        "        ID_SERVICE,\n" + 
        "        IND_PARAMETER,\n" + 
        "        IND_VAL_PARAMETER,\n" + 
        "        IND_ESTATUS\n" +         
        "   FROM EVENTAS.LMK_INT_SERVICES_PARAMS_TAB\n" + 
        "  WHERE ID_SERVICE = "+liIdService;
        
        return lsQuery;
    }
}
