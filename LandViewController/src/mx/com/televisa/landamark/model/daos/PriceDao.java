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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import mx.com.televisa.landamark.model.cnn.ConnectionAs400;
import mx.com.televisa.landamark.model.types.LandmarkSecurityWsBean;
import mx.com.televisa.landamark.model.types.LmkSpotsBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;

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
            "   AND NOM_PARAMETER = 'PASSWORD_VALUE'";
        try {
            System.out.println(lsQueryParadigm);
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
    
    public List<LmkSpotsBean> getSpotInfo(Integer tiSpotNumber){
        List<LmkSpotsBean> laListSpots = new ArrayList<LmkSpotsBean>();
        
        Connection loCnn = new ConnectionAs400().getConnection();
        ResultSet  loRs = null;
        String     lsQueryParadigm = 
            "SELECT ORDID,\n" + 
            "       SPOT_NUMBER,\n" + 
            "       SPTMSTID\n" + 
            "  FROM EVENTAS.LMK_SPOTS\n" + 
            " WHERE SPOT_NUMBER = " + tiSpotNumber;
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkSpotsBean loSpot = new LmkSpotsBean();
                loSpot.setLiOrdId(loRs.getInt("ORDID"));
                loSpot.setLiSpotNumber(loRs.getInt("SPOT_NUMBER"));
                loSpot.setLiSptmstid(loRs.getInt("SPTMSTID"));
                laListSpots.add(loSpot);
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
        return laListSpots;
    }
    
    
    /**
     * Obtiene bandera para procesamiento en log certificado
     * @autor Jorge Luis Bautista Santiago
     * @param tsDate
     * @param tsChannels
     * @return List
     */  
    public Integer updateLmkSptRev(Integer tiSptmstid,
                                   Double tdPrecio) {
        Integer    liReturn = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = 
            "UPDATE PARADB.SPTREV\n" + 
            "   SET SPTRT = "+tdPrecio+"\n" + 
            " WHERE SPTMSTID = "+tiSptmstid;
        
        try {
            Statement loStmt = loCnn.createStatement();
            liReturn = loStmt.executeUpdate(lsQueryParadigm);
        } catch (SQLException loExSql) {
            loExSql.printStackTrace();
        }
        finally{
            try {
                loCnn.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return liReturn;
    }
    
    
    /**
     * Obtiene bandera para procesamiento en log certificado
     * @autor Jorge Luis Bautista Santiago
     * @param tsDate
     * @param tsChannels
     * @return List
     */  
    public Integer getFlagInsertLogCertificadoMod(String tsDate, 
                                               String tsChannels) {
        Integer    liReturn = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = getQueryInsertLogCertificadoMod(tsDate, tsChannels);
        
        try {
            Statement loStmt = loCnn.createStatement();
            liReturn = loStmt.executeUpdate(lsQueryParadigm);
        } catch (SQLException loExSql) {
            loExSql.printStackTrace();
        }
        finally{
            try {
                loCnn.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return liReturn;
    }
    
    /**
     * Genera instruccion para obtener bandera para procesamiento en log certificado
     * @autor Jorge Luis Bautista Santiago
     * @param tsDate
     * @param tsChannels
     * @return List
     */  
    public String getQueryInsertLogCertificadoMod(String tsDate, String tsChannels) {
        
        String lsQuery = "INSERT INTO EVENTAS.LMK_LOG_CERTIFICADO_PROCESADO(STNID,BCSTDT,STATUS)\n" + 
        "SELECT\n" + 
        "    LOGHDR.STNID,\n" + 
        "    LOGHDR.BCSTDT,\n" + 
        "    1\n" + 
        "FROM PARADB.LOGHDR LOGHDR\n" + 
        "WHERE LOGHDR.STNID = '" + tsChannels + "' \n" + 
        "AND LOGHDR.BCSTDT >= '"+tsDate+"' -- PARAMETRO INICIAL DE FECHA\n" + 
        "AND (LOGHDR.STNID,LOGHDR.BCSTDT) NOT IN (SELECT A.STNID, A.BCSTDT " +
            " FROM EVENTAS.LMK_LOG_CERTIFICADO_PROCESADO A\n" + 
        "                                         WHERE LOGHDR.STNID = A.STNID\n" + 
        "                                         AND LOGHDR.BCSTDT = A.BCSTDT)\n" + 
        "AND LOGHDR.LOGEDTLCK >= 7031\n" + 
        "GROUP BY LOGHDR.STNID,LOGHDR.BCSTDT\n" + 
        "ORDER BY  LOGHDR.STNID,LOGHDR.BCSTDT\n";
                
        return lsQuery;
    }
    
    /**
     * Elimina de la tabla de control de log certificado
     * @autor Jorge Luis Bautista Santiago
     * @param tsDate
     * @param tsChannels
     * @return Integer
     */  
    public Integer deleteLogCertificado(String tsDate, 
                                        String tsChannels
                                        ) {
        Integer    liReturn = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = 
            getQueryDeleteCertificado(tsDate, tsChannels);
        
        try {
            Statement loStmt = loCnn.createStatement();
            loStmt.executeUpdate(lsQueryParadigm);
            liReturn = 66;
        } catch (SQLException loExSql) {
            loExSql.printStackTrace();
        }
        finally{
            try {
                loCnn.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return liReturn;
    }
    
    /**
     * Genera instruccion para obtener bandera para procesamiento en log certificado
     * @autor Jorge Luis Bautista Santiago
     * @param tsDate
     * @param tsChannels
     * @return List
     */  
    public String getQueryDeleteCertificado(String tsDate, String tsChannels) {
        
        String lsQuery = 
            "DELETE\n" + 
            "  FROM EVENTAS.LMK_LOG_CERTIFICADO_PROCESADO\n" + 
            " WHERE STNID  = '"+tsChannels+"'\n" + 
            "   AND BCSTDT = '"+tsDate+"'";
                
        return lsQuery;
    }
    
    /**
     * Ejecuta procedimiento en base de datos para el encabezado de la orden
     * @autor Jorge Luis Bautista Santiago
     * @param toOrdHeaderModulosTab
     * @return ResponseUpdDao
     */
    public ResponseUpdDao callPostConciliation(String tsStnid, String tsBcstdt) throws SQLException {
        ResponseUpdDao    loResponseUpdDao = new ResponseUpdDao();
        Connection        loCnn = new ConnectionAs400().getConnection();
        CallableStatement loCallStmt = null;
        System.out.println("Parametros(callPostConciliation).........");
        System.out.println("tsStnid: ["+tsStnid+"]");
        System.out.println("tsBcstdt: ["+tsBcstdt+"]");
        java.sql.Date     ltDate = getDateYYYYMMDD(tsBcstdt);
        //System.out.println("ltDate: ["+ltDate+"]");
        String            lsQueryParadigm = "call PCOM.CA_UPDATE_COSTO_SERVICIO(?,?)";
        try {
            loCallStmt = loCnn.prepareCall(lsQueryParadigm);
            loCallStmt.setString(1, tsStnid);
            loCallStmt.setDate(2, ltDate);
            loCallStmt.execute();
            loResponseUpdDao.setLsResponse("OK");
            loResponseUpdDao.setLiAffected(0);
            loResponseUpdDao.setLsMessage("Success");
        } catch (SQLException loExSql) {
            System.out.println("ERROR AL EJECUTAR: ");
            System.out.println(lsQueryParadigm);
            loResponseUpdDao.setLsResponse("ERROR");
            loResponseUpdDao.setLiAffected(0);
            loResponseUpdDao.setLsMessage("ERROR: "+loExSql.getMessage());
            System.out.println(loExSql.getMessage());
            throw loExSql;
        }
        finally{
            try {
                loCnn.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return loResponseUpdDao;
    }
    
    /**
     * Convierte una cadena en formato fecha a una fecha real sql con ese mismo formato
     * @autor Jorge Luis Bautista Santiago
     * @param lsDateStr
     * @return java.sql.Date
     */
    private java.sql.Date getDateYYYYMMDD(String lsDateStr){
        SimpleDateFormat loFormatText = new SimpleDateFormat("yyyy-MM-dd");
        String           lsStrDate = lsDateStr;
        java.util.Date             ltDatePivot = null;
        try {
            ltDatePivot = loFormatText.parse(lsStrDate);
        } catch (ParseException loEx) {
            System.out.println("ERROR al PARSEAR (getDateYYYYMMDD)");
            loEx.printStackTrace();
        }
        java.sql.Date ltDateResponse = new java.sql.Date(ltDatePivot.getTime());
        return ltDateResponse;
    }

    
}
