/**
* Project: Integraton Paradigm - EveTV
*
* File: ParrillasProgramasDao.java
*
* Created on: Febrero 23, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.model.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import mx.com.televisa.landamark.model.cnn.ConnectionAs400;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;

/** Objeto que accede a base de datos para consultar Parrillas de Programas y Cortes
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
 */
public class ParrillasProgramasDao {
    public ParrillasProgramasDao() {
        super();
    }
    
    /**
     * Ejecuta llamado a Procedimiento Almacenado en Paradigm
     * @autor Jorge Luis Bautista Santiago
     * @return ResponseUpdDao
     */
    public ResponseUpdDao callLmkProgBrkPr(String tsStnid, 
                                           String tsStrdt, 
                                           String tsEdt) {
        System.out.println("Dentro de callTraditionalSalePr");
        System.out.println("**** setteando EVENTAS.LMK_PROG_BRK ******");
        System.out.println("tsStnid["+tsStnid+"]");
        System.out.println("tsStrdt["+tsStrdt+"]");
        System.out.println("tsEdt["+tsEdt+"]");
        ResponseUpdDao loResponseUpdDao = new ResponseUpdDao();
        String lsResult = "EVETV_SPOTS_VTA_TRADICIONAL(";
        Connection        loCnn = new ConnectionAs400().getConnection();
        CallableStatement loCallStmt = null;
        String            lsQueryParadigm = "call EVENTAS.LMK_PROG_BRK(?,?,?)";
        try {
            System.out.println("Dentro de callTraditionalSalePr llamando "+lsQueryParadigm);
            loCallStmt = loCnn.prepareCall(lsQueryParadigm);            
            
            if(tsStnid != null){
                if(!tsStnid.trim().equalsIgnoreCase("")){
                    loCallStmt.setString(1, tsStnid);
                    lsResult += ""+tsStnid+",";
                }else{
                    loCallStmt.setString(1, null);
                    lsResult += "null,";
                }
            }else{
                loCallStmt.setString(1, null);
                lsResult += "null,";
            }
            
            if(tsStrdt != null){
                if(!tsStrdt.trim().equalsIgnoreCase("")){
                    java.sql.Date     ltDateStrt = getDateYYYYMMDD(tsStrdt);
                    loCallStmt.setDate(2, ltDateStrt);    
                    lsResult += ""+ltDateStrt+",";
                }else{
                    loCallStmt.setDate(2, null);
                    lsResult += "null,";
                }
                
            }else{
                loCallStmt.setDate(2, null);
                lsResult += "null,";
            }
            if(tsEdt != null){
                if(!tsEdt.trim().equalsIgnoreCase("")){
                    java.sql.Date     ltDateEnd = getDateYYYYMMDD(tsEdt);
                    loCallStmt.setDate(3, ltDateEnd);
                    lsResult += ""+ltDateEnd+",";
                }else{
                    loCallStmt.setDate(3, null);
                    lsResult += "null,";
                }
                
            }else{
                loCallStmt.setDate(3, null);
                lsResult += "null,";
            }                                    
            loCallStmt.executeUpdate();
            loResponseUpdDao.setLsResponse("OK");
            loResponseUpdDao.setLsMessage(""+lsResult.substring(0, lsResult.length()-1)+") Execute Success!!!");
            System.out.println(lsResult.substring(0, lsResult.length()-1)+") Execute Success!!!");
        } catch (SQLException loExSql) {
            //System.out.println("ERROR AL EJECUTAR: "+loExSql.getMessage());
            loResponseUpdDao.setLsResponse("ERROR");
            loResponseUpdDao.setLsMessage(loExSql.getMessage());
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
            loEx.printStackTrace();
        }
        java.sql.Date ltDateResponse = new java.sql.Date(ltDatePivot.getTime());
        return ltDateResponse;
    }    
}
