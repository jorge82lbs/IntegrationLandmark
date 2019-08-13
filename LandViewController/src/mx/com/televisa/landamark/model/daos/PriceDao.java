/**
* Project: Integraton Paradigm - Landmark
*
* File: PriceDao.java
*
* Created on: Agosto 16, 2019 at 11:00
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
import mx.com.televisa.landamark.model.types.LandmarkSecurityWsBean;

/** Clase que accede a base de datos para metodos genericos de
 * Actualizacion de Precios
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Agosto 16, 2019, 12:00 pm
 */
public class PriceDao {
    public PriceDao() {
        super();
    }
    
    /**
     * Obtiene el valor codigo del canal mapeado, respecto a landmark
     * @autor Jorge Luis Bautista Santiago
     * @param tsChannel
     * @return List
     */
    public List<String> getCodPriceChannel(String tsChannel) {
        List<String>    laCodChannel = new ArrayList<String>();
        Connection loCnn = new ConnectionAs400().getConnection();
        ResultSet  loRs = null;
        String     lsQueryParadigm = 
            "SELECT VAL_VALUE_DESTINY\n" + 
            "  FROM EVENTAS.LMK_INT_MAPPING_CAT_TAB\n" + 
            " WHERE IND_USED_BY      = 'SPOT_PRICE'\n" + 
            "   AND VAL_VALUE_ORIGIN = '" + tsChannel + "'";
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                laCodChannel.add(loRs.getString(1));
            }
        } catch (SQLException loExSql) {
            System.out.println("ERROR AL EJECUTAR: ");
            System.out.println(lsQueryParadigm);
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
        return laCodChannel;
    }

    /**
     * Obtiene el valor codigo del canal mapeado, respecto a landmark
     * @autor Jorge Luis Bautista Santiago
     * @param tsChannel
     * @return List
     */
    public List<String> getLandmarkSecurityValues() {
        List<String>    laCodChannel = new ArrayList<String>();
        Connection loCnn = new ConnectionAs400().getConnection();
        ResultSet  loRs = null;
        String     lsQueryParadigm = 
            "SELECT IND_VALUE_PARAMETER \n" + 
            "  FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB\n" + 
            " WHERE IND_USED_BY   = 'LANDMARK_SECURITY_WS'\n" + 
            "   AND NOM_PARAMETER = 'USER_NAME'\n" + 
            "UNION    \n" + 
            "SELECT IND_VALUE_PARAMETER \n" + 
            "  FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB\n" + 
            " WHERE IND_USED_BY   = 'LANDMARK_SECURITY_WS'\n" + 
            "   AND NOM_PARAMETER = 'USER_VALUE'\n" + 
            " UNION \n" + 
            "SELECT IND_VALUE_PARAMETER \n" + 
            "  FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB\n" + 
            " WHERE IND_USED_BY   = 'LANDMARK_SECURITY_WS'\n" + 
            "   AND NOM_PARAMETER = 'PASSWORD_NAME'\n" + 
            "UNION \n" + 
            "SELECT IND_VALUE_PARAMETER \n" + 
            "  FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB\n" + 
            " WHERE IND_USED_BY   = 'LANDMARK_SECURITY_WS'\n" + 
            "   AND NOM_PARAMETER = 'PASSWORD_VALUE';";
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                laCodChannel.add(loRs.getString(1));
            }
        } catch (SQLException loExSql) {
            System.out.println("ERROR AL EJECUTAR: ");
            System.out.println(lsQueryParadigm);
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
        return laCodChannel;
    }
    
    public LandmarkSecurityWsBean getLandSecurityBeanValues(){
        LandmarkSecurityWsBean loLandmarkSecurityWsBean = new LandmarkSecurityWsBean();
        List<String> laList = getLandmarkSecurityValues();
        if(laList.size() > 3){
            loLandmarkSecurityWsBean.setLsNameUser(laList.get(0));
            loLandmarkSecurityWsBean.setLsValueUser(laList.get(1));
            loLandmarkSecurityWsBean.setLsNamePassword(laList.get(2));
            loLandmarkSecurityWsBean.setLsValuePassword(laList.get(3));
        }else{
            loLandmarkSecurityWsBean = null;
        }
        
        return loLandmarkSecurityWsBean;
    }
    
}
