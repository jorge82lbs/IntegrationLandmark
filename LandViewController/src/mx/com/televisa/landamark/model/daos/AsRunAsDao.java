/**
* Project: Integraton Paradigm - Landmark
*
* File: AsRunAsDao.java
*
* Created on: Agosto 06, 2019 at 11:00
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
import mx.com.televisa.landamark.model.types.ResponseUpdDao;

/** Clase que accede a base de datos para metodos genericos
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Agosto 06, 2019, 12:00 pm
 */
public class AsRunAsDao {
    public AsRunAsDao() {
        super();
    }
    
    /**
     * Obtiene bandera para procesamiento en log certificado
     * @autor Jorge Luis Bautista Santiago
     * @param tsDate
     * @param tsChannels
     * @return List
     */  
    public Integer getFlagInsertLogCertificado(String tsDate, 
                                               String tsChannels) {
        Integer    liReturn = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = getQueryInsertLogCertificado(tsDate, tsChannels);
        
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
    public String getQueryInsertLogCertificado(String tsDate, String tsChannels) {
        
        String lsQuery = "INSERT INTO EVENTAS.EVETV_LOG_CERTIFICADO_PROCESADO(STNID,BCSTDT,STATUS)\n" + 
        "SELECT\n" + 
        "    LOGHDR.STNID,\n" + 
        "    LOGHDR.BCSTDT,\n" + 
        "    1\n" + 
        "FROM PARADB.LOGHDR LOGHDR\n" + 
        "WHERE LOGHDR.STNID = '" + tsChannels + "' \n" + 
        "AND LOGHDR.BCSTDT >= '"+tsDate+"' -- PARAMETRO INICIAL DE FECHA\n" + 
        "AND (LOGHDR.STNID,LOGHDR.BCSTDT) NOT IN (SELECT A.STNID, A.BCSTDT " +
            " FROM EVENTAS.EVETV_LOG_CERTIFICADO_PROCESADO A\n" + 
        "                                         WHERE LOGHDR.STNID = A.STNID\n" + 
        "                                         AND LOGHDR.BCSTDT = A.BCSTDT)\n" + 
        "AND LOGHDR.LOGEDTLCK >= 7031\n" + 
        "GROUP BY LOGHDR.STNID,LOGHDR.BCSTDT\n" + 
        "ORDER BY  LOGHDR.STNID,LOGHDR.BCSTDT\n";
                
        return lsQuery;
    }
    
    /**
     * Ejecuta procedimiento en base de datos para el encabezado de la orden
     * @autor Jorge Luis Bautista Santiago
     * @param toOrdHeaderModulosTab
     * @return ResponseUpdDao
     */
    public ResponseUpdDao callProcedureGeneraAsRun(String tsStnid, String tsBcstdt) throws SQLException {
        ResponseUpdDao    loResponseUpdDao = new ResponseUpdDao();
        Connection        loCnn = new ConnectionAs400().getConnection();
        CallableStatement loCallStmt = null;
        System.out.println("Parametros(callProcedureGeneraAsRun).........");
        System.out.println("tsStnid: ["+tsStnid+"]");
        System.out.println("tsBcstdt: ["+tsBcstdt+"]");
        java.sql.Date     ltDate = getDateYYYYMMDD(tsBcstdt);
        //System.out.println("ltDate: ["+ltDate+"]");
        String            lsQueryParadigm = "call EVENTAS.LMK_GENERA_ASRUN(?,?)";
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
            loResponseUpdDao.setLsMessage(loExSql.getMessage());
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
    
    /**
     * Obtiene la cadena a argegar al archivo
     * @autor Jorge Luis Bautista Santiago
     * @param tsStnid
     * @param tsBcstdt
     * @return List
     */
    public List<String> getCodAsRunAs(String tsStnid, String tsBcstdt) {
        List<String>    laCodAsRunAs = new ArrayList<String>();
        Connection loCnn = new ConnectionAs400().getConnection();
        ResultSet  loRs = null;
        String     lsQueryParadigm = 
            "SELECT TRIM(SALES_AREA)||REGION_CODE||\n" + 
            "       TRIM(BREAK_SCHEDULE_DATE)||\n" + 
            "       TRIM(BREAK_NOMINAL_TIME)||\n" + 
            "       TRIM(BREAK_NUMBER)||\n" + 
            "       TRIM(SPOT_NUMBER)||\n" + 
            "       TRIM(TRANSMISSION_TIME)||\n" + 
            "       TRIM(INDUSTRY_CODE) COD_ASRUN\n" + 
            "  FROM EVENTAS.LMK_AS_RUN\n" + 
            " WHERE STNID = '"+tsStnid+"'\n" + 
            "   AND BCSTDT = '"+tsBcstdt+"'";
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                laCodAsRunAs.add(loRs.getString(1));
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
        return laCodAsRunAs;
    }

    /**
     * Obtiene el prefijo del canal
     * @autor Jorge Luis Bautista Santiago
     * @param tsStnid
     * @param tsBcstdt
     * @return List
     */
    public List<String> getPrefixChannelAsRunAs(String tsStnid, String tsBcstdt) {
        List<String>    laCodAsRunAs = new ArrayList<String>();
        Connection loCnn = new ConnectionAs400().getConnection();
        ResultSet  loRs = null;
        String     lsQueryParadigm = 
            "SELECT TRIM(SALES_AREA) PREFIX_SA\n" + 
            "  FROM EVENTAS.LMK_AS_RUN\n" + 
            " WHERE STNID = '"+tsStnid+"'\n" + 
            "   AND BCSTDT = '"+tsBcstdt+"'";
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                laCodAsRunAs.add(loRs.getString(1));
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
        return laCodAsRunAs;
    }
    
    /**
     * Actualiza estatus de la tabla de Archivos
     * @autor Jorge Luis Bautista Santiago
     * @param liIdFileXml
     * @param tsEstatus
     * @return ResponseUpdDao
     */
    public ResponseUpdDao updateEstatusXmlFiles(Integer liIdFileXml, String tsEstatus) {
        ResponseUpdDao loResponseUpdDao = new ResponseUpdDao();
        Integer        loValue = 0;
        Connection     loCnn = new ConnectionAs400().getConnection();
        String         lsQueryParadigm = 
            "UPDATE EVENTAS.LMK_INT_XML_FILES_TAB\n" + 
            "   SET IND_ESTATUS = '" + tsEstatus + "'\n" + 
            " WHERE ID_FILE_XML = " + liIdFileXml;
        System.out.println("ACTUALIZANDO ESTATUS de XML_FILES");
        System.out.println(lsQueryParadigm);
        try {
            Statement loStmt = loCnn.createStatement();
            loValue = loStmt.executeUpdate(lsQueryParadigm);
            loResponseUpdDao.setLiAffected(loValue);
            loResponseUpdDao.setLsMessage("SUCCESS");
            loResponseUpdDao.setLsResponse("OK");
        } catch (SQLException loExSql) {
            System.out.println(loExSql.getMessage());
            loResponseUpdDao.setLiAffected(0);
            loResponseUpdDao.setLsMessage(loExSql.getMessage());
            loResponseUpdDao.setLsResponse("ERROR");
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
     * Actualiza en base a la respuesta de netuno las tablas de control de log certificado
     * @autor Jorge Luis Bautista Santiago
     * @param tsDate
     * @param tsChannels
     * @return Integer
     */  
    public ResponseUpdDao getUpdateLogCertificado(String tsChannels, String tsDate) {
        ResponseUpdDao    loResponse = new ResponseUpdDao();
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = 
        "UPDATE EVENTAS.EVETV_LOG_CERTIFICADO_PROCESADO\n" + 
                "   SET STATUS = '0'\n" + 
                " WHERE STNID = '" + tsChannels + "'\n" + 
                "   AND BCSTDT = '" + tsDate + "'";
        System.out.println(lsQueryParadigm);
        try {
            Statement loStmt = loCnn.createStatement();
            Integer liRes = loStmt.executeUpdate(lsQueryParadigm);
            loResponse.setLiAffected(liRes);
            loResponse.setLsResponse("OK");
            loResponse.setLsMessage("Actualizacion de Tabla realizado");
        } catch (SQLException loExSql) {
            loResponse.setLiAffected(0);
            loResponse.setLsResponse("ERROR");
            loResponse.setLsMessage("Error al actualizar "+loExSql.getErrorCode());
            loExSql.printStackTrace();
        }
        finally{
            try {
                loCnn.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return loResponse;
    }    
    
}
