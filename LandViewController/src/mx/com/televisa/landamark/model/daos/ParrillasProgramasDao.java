/**
* Project: Integraton Paradigm - Landmark
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
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import mx.com.televisa.landamark.model.cnn.ConnectionAs400;
import mx.com.televisa.landamark.model.types.LmkIntServicesParamsRowBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;
import mx.com.televisa.landamark.model.types.extract.LmkBrkBreakRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkBrkChannelHeaderRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkBrkChannelTrailerRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkBrkFileHeaderRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkBrkFileTrailerRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkProgFileTrailerRowBean;
import mx.com.televisa.landamark.model.types.extract.LmkProgRowBean;

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
        String lsResult = "EVENTAS.LMK_PROG_BRK(";
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
    
    
    // Para el BREAK        
    ////////// FILE HEADER RECORD
    /**
     * Obtiene registros de la tabla FILE HEADER RECORD para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<LmkBrkFileHeaderRowBean> getBrkFileHeader(String lsWhere){
        List<LmkBrkFileHeaderRowBean> loRes = 
            new ArrayList<LmkBrkFileHeaderRowBean>();
        
        Connection              loCnn = new ConnectionAs400().getConnection();
        ResultSet               loRs = null;
        String                  lsQueryParadigm = getQueryBrkFileHeader(lsWhere);        
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkBrkFileHeaderRowBean loItem = new LmkBrkFileHeaderRowBean();
                loItem.setLiRecordType(loRs.getInt("RECORD_TYPE"));
                loItem.setLsFileCreationDate(loRs.getString("FILE_CREATION_DATE"));
                loItem.setLtFileCreationTime(loRs.getTimestamp("FILE_CREATION_TIME"));
                loItem.setLsStnid(loRs.getString("STNID"));
                loItem.setLtBcstdt(loRs.getDate("BCSTDT"));
                loRes.add(loItem);
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
        
        return loRes;
    }
    
    /**
     * Crea instruccicon para Obtiner registros de la tabla FILE HEADER RECORD para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public String getQueryBrkFileHeader(String lsWhere){
        String lsQuery = "SELECT RECORD_TYPE,\n" + 
        "        FILE_CREATION_DATE,\n" + 
        "        FILE_CREATION_TIME,\n" + 
        "        STNID,\n" + 
        "        BCSTDT\n" + 
        "   FROM EVENTAS.LMK_BRK_FILE_HEADER";
        lsQuery += " WHERE 1 = 1 " + lsWhere;
        
        return lsQuery;
    }
    
    ////////// CHANNEL HEADER RECORD
    /**
     * Obtiene registros de la tabla EVENTAS.LMK_BRK_CHANNEL_HEADER para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<LmkBrkChannelHeaderRowBean> getBrkChannelHeader(String lsWhere){
        List<LmkBrkChannelHeaderRowBean> loRes = 
            new ArrayList<LmkBrkChannelHeaderRowBean>();
        
        Connection              loCnn = new ConnectionAs400().getConnection();
        ResultSet               loRs = null;
        String                  lsQueryParadigm = getQueryBrkChannelHeader(lsWhere);        
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkBrkChannelHeaderRowBean loItem = new LmkBrkChannelHeaderRowBean();
                loItem.setLiRecordType(loRs.getInt("RECORD_TYPE"));
                loItem.setLsRegionalSalesAreaCode(loRs.getString("REGIONAL_SALES_AREA_CODE"));
                loItem.setLsSalesAreaCode(loRs.getString("SALES_AREA_CODE"));
                loItem.setLiId(loRs.getInt("ID"));
                loItem.setLsBreakSchedule(loRs.getString("BREAK_SCHEDULE"));
                loItem.setLsStnid(loRs.getString("STNID"));
                loItem.setLtBcstdt(loRs.getDate("BCSTDT"));
                loItem.setLtFechaCreacion(loRs.getTimestamp("FECHA_CREACION"));
                loRes.add(loItem);
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
        
        return loRes;
    }
    
    /**
     * Crea instruccicon para Obtiner registros de la tabla FILE HEADER RECORD para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public String getQueryBrkChannelHeader(String lsWhere){
        String lsQuery = "SELECT RECORD_TYPE,\n" + 
        "        REGIONAL_SALES_AREA_CODE,\n" + 
        "        SALES_AREA_CODE,\n" + 
        "        ID,\n" + 
        "        BREAK_SCHEDULE,\n" + 
        "        STNID,\n" + 
        "        BCSTDT,\n" + 
        "        FECHA_CREACION\n" + 
        "   FROM EVENTAS.LMK_BRK_CHANNEL_HEADER";
        lsQuery += " WHERE 1 = 1 " + lsWhere;
        
        return lsQuery;
    }
    
    
    ////////// BREAK RECORD
    /**
     * Obtiene registros de la tabla EVENTAS.LMK_BRK_BREAK para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<LmkBrkBreakRowBean> getBrkBreak(String lsWhere){
        List<LmkBrkBreakRowBean> loRes = 
            new ArrayList<LmkBrkBreakRowBean>();
        
        Connection              loCnn = new ConnectionAs400().getConnection();
        ResultSet               loRs = null;
        String                  lsQueryParadigm = getQueryBrkBreak(lsWhere);        
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkBrkBreakRowBean loItem = new LmkBrkBreakRowBean();
                loItem.setLiRecordType(loRs.getInt("RECORD_TYPE"));
                loItem.setLsRegionalSalesCode(loRs.getString("REGIONAL_SALES_CODE"));
                loItem.setLsSalesAreaCode(loRs.getString("SALES_AREA_CODE"));
                loItem.setLsBreakSchedule(loRs.getString("BREAK_SCHEDULE"));
                loItem.setLiBreakNominal(loRs.getInt("BREAK_NOMINAL"));
                loItem.setLiBreakDuration(loRs.getInt("BREAK_DURATION"));
                loItem.setLsBreakTypeCode(loRs.getString("BREAK_TYPE_CODE"));
                loItem.setLsPositionInProgramme(loRs.getString("POSITION_IN_PROGRAMME"));
                loItem.setLiBreakNumber(loRs.getInt("BREAK_NUMBER"));
                loItem.setLsStnid(loRs.getString("STNID"));
                loItem.setLtBcstdt(loRs.getDate("BCSTDT"));
                loItem.setLtFechaCreacion(loRs.getTimestamp("FECHA_CREACION"));
                loRes.add(loItem);
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
        
        return loRes;
    }
    
    /**
     * Crea instruccicon para Obtiner registros de la tabla FILE HEADER RECORD para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public String getQueryBrkBreak(String lsWhere){
        String lsQuery = "SELECT RECORD_TYPE,\n" + 
        "        REGIONAL_SALES_CODE,\n" + 
        "        SALES_AREA_CODE,\n" + 
        "        BREAK_SCHEDULE,\n" + 
        "        BREAK_NOMINAL,\n" + 
        "        BREAK_DURATION,\n" + 
        "        BREAK_TYPE_CODE,\n" + 
        "        POSITION_IN_PROGRAMME,\n" + 
        "        BREAK_NUMBER,\n" + 
        "        STNID,\n" + 
        "        BCSTDT,\n" + 
        "        FECHA_CREACION\n" + 
        "   FROM EVENTAS.LMK_BRK_BREAK";
        lsQuery += " WHERE 1 = 1 " + lsWhere;
        
        return lsQuery;
    }
    
    ////////// CHANNEL TRAILER RECORD
    /**
     * Obtiene registros de la tabla EVENTAS.LMK_BRK_CHANNEL_TRAILER para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<LmkBrkChannelTrailerRowBean> getBrkChannelTrailer(String lsWhere){
        List<LmkBrkChannelTrailerRowBean> loRes = 
            new ArrayList<LmkBrkChannelTrailerRowBean>();
        
        Connection              loCnn = new ConnectionAs400().getConnection();
        ResultSet               loRs = null;
        String                  lsQueryParadigm = getQueryBrkChannelTrailer(lsWhere);        
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkBrkChannelTrailerRowBean loItem = new LmkBrkChannelTrailerRowBean();
                loItem.setLiRecordType(loRs.getInt("RECORD_TYPE"));
                loItem.setLiRecordCount(loRs.getInt("RECORD_COUNT"));                
                loItem.setLsStnid(loRs.getString("STNID"));
                loItem.setLtBcstdt(loRs.getDate("BCSTDT"));
                loItem.setLtFechaCreacion(loRs.getTimestamp("FECHA_CREACION"));
                loRes.add(loItem);
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
        
        return loRes;
    }
    
    /**
     * Crea instruccicon para Obtiner registros de la tabla EVENTAS.LMK_BRK_CHANNEL_TRAILER para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public String getQueryBrkChannelTrailer(String lsWhere){
        String lsQuery = " SELECT RECORD_TYPE,\n" + 
        "        RECORD_COUNT,\n" + 
        "        STNID,\n" + 
        "        BCSTDT,\n" + 
        "        FECHA_CREACION \n" + 
        "   FROM EVENTAS.LMK_BRK_CHANNEL_TRAILER";
        lsQuery += " WHERE 1 = 1 " + lsWhere;
        
        return lsQuery;
    }
    
    
    ////////// FILE TRAILER RECORD
    /**
     * Obtiene registros de la tabla EVENTAS.LMK_BRK_FILE_TRAILER para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<LmkBrkFileTrailerRowBean> getBrkFileTrailer(String lsWhere){
        List<LmkBrkFileTrailerRowBean> loRes = 
            new ArrayList<LmkBrkFileTrailerRowBean>();
        
        Connection              loCnn = new ConnectionAs400().getConnection();
        ResultSet               loRs = null;
        String                  lsQueryParadigm = getQueryBrkFileTrailer(lsWhere);        
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkBrkFileTrailerRowBean loItem = new LmkBrkFileTrailerRowBean();
                loItem.setLiRecordType(loRs.getInt("RECORD_TYPE"));
                loItem.setLiRecordCount(loRs.getInt("RECORD_COUNT"));                
                loItem.setLsStnid(loRs.getString("STNID"));
                loItem.setLtBcstdt(loRs.getDate("BCSTDT"));
                loItem.setLtFechaCreacion(loRs.getTimestamp("FECHA_CREACION"));
                loRes.add(loItem);
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
        
        return loRes;
    }
    
    /**
     * Crea instruccicon para Obtiner registros de la tabla EVENTAS.LMK_BRK_CHANNEL_TRAILER para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public String getQueryBrkFileTrailer(String lsWhere){
        String lsQuery = " SELECT RECORD_TYPE,\n" + 
        "        RECORD_COUNT,\n" + 
        "        STNID,\n" + 
        "        BCSTDT,\n" + 
        "        FECHA_CREACION \n" + 
        "   FROM EVENTAS.LMK_BRK_CHANNEL_TRAILER";
        lsQuery += " WHERE 1 = 1 " + lsWhere;
        
        return lsQuery;
    }
    
    // Para el PROGRAM
    ////////// FILE HEADER RECORD
    ////////// Programme 
    /**
     * Obtiene registros de la tabla EVENTAS.LMK_PROG para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<LmkProgRowBean> getProgProgramme(String lsWhere){
        List<LmkProgRowBean> loRes = 
            new ArrayList<LmkProgRowBean>();
        
        Connection              loCnn = new ConnectionAs400().getConnection();
        ResultSet               loRs = null;
        String                  lsQueryParadigm = getQueryProgProgramme(lsWhere);        
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkProgRowBean loItem = new LmkProgRowBean();
                loItem.setLiRecordType(loRs.getInt("RECORD_TYPE"));
                loItem.setLsSalesAreaCode(loRs.getString("SALES_AREA_CODE"));
                loItem.setLsTransmissionRegionCode(loRs.getString("TRANSMISSION_REGION_CODE"));
                loItem.setLtProgrammeTransmissionDate(loRs.getDate("PROGRAMME_TRANSMISSION_DATE"));
                loItem.setLiProgrammeTransmissionStart(loRs.getInt("PROGRAMME_TRANSMISSION_START"));
                loItem.setLiProgrammeTransmissionEnd(loRs.getInt("PROGRAMME_TRANSMISSION_END"));
                loItem.setLsRegionalVariation(loRs.getString("REGIONAL_VARIATION"));
                loItem.setLsDominantRegionIndicator(loRs.getString("DOMINANT_REGION_INDICATOR"));
                loItem.setLsProgrammeName(loRs.getString("PROGRAMME_NAME"));
                loItem.setLiProgrammeId(loRs.getInt("PROGRAMME_ID"));
                loItem.setLiDuration(loRs.getInt("DURATION"));
                loItem.setLsLiveBroadcast(loRs.getString("LIVE_BROADCAST"));
                loItem.setLsProgrammeOverselling(loRs.getString("PROGRAMME_OVERSELLING"));
                loItem.setLsProgrammeShowing(loRs.getString("PROGRAMME_SHOWING"));
                loItem.setLsProgrammeDescription(loRs.getString("PROGRAMME_DESCRIPTION"));
                loItem.setLsProgrammeComment(loRs.getString("PROGRAMME_COMMENT"));
                loItem.setLsEpisodeName(loRs.getString("EPISODE_NAME"));
                loItem.setLiEpisodeId(loRs.getInt("EPISODE_ID"));
                loItem.setLsCertification(loRs.getString("CERTIFICATION"));
                loItem.setLsProgrammeCategory(loRs.getString("PROGRAMME_CATEGORY"));
                loItem.setLsStnid(loRs.getString("STNID"));
                loItem.setLtBcstdt(loRs.getDate("BCSTDT"));
                loItem.setLsPgmid(loRs.getString("PGMID"));
                loItem.setLtFechaCreacion(loRs.getTimestamp("FECHA_CREACION"));
                loRes.add(loItem);
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
        
        return loRes;
    }
    
    /**
     * Crea instruccicon para Obtiner registros de la tabla EVENTAS.LMK_PROG para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public String getQueryProgProgramme(String lsWhere){
        String lsQuery = "SELECT RECORD_TYPE,\n" + 
        "        SALES_AREA_CODE,\n" + 
        "        TRANSMISSION_REGION_CODE,\n" + 
        "        PROGRAMME_TRANSMISSION_DATE,\n" + 
        "        PROGRAMME_TRANSMISSION_START,\n" + 
        "        PROGRAMME_TRANSMISSION_END,\n" + 
        "        REGIONAL_VARIATION,\n" + 
        "        DOMINANT_REGION_INDICATOR,\n" + 
        "        PROGRAMME_NAME,\n" + 
        "        PROGRAMME_ID,\n" + 
        "        DURATION,\n" + 
        "        LIVE_BROADCAST,\n" + 
        "        PROGRAMME_OVERSELLING,\n" + 
        "        PROGRAMME_SHOWING,\n" + 
        "        PROGRAMME_DESCRIPTION,\n" + 
        "        PROGRAMME_COMMENT,\n" + 
        "        EPISODE_NAME,\n" + 
        "        EPISODE_ID,\n" + 
        "        CERTIFICATION,\n" + 
        "        PROGRAMME_CATEGORY,\n" + 
        "        STNID,\n" + 
        "        BCSTDT,\n" + 
        "        PGMID,\n" + 
        "        FECHA_CREACION\n" + 
        "   FROM EVENTAS.LMK_PROG";
        lsQuery += " WHERE 1 = 1 " + lsWhere;
        
        return lsQuery;
    }
    ////////// File Trailer
    /**
     * Obtiene registros de la tabla EVENTAS.LMK_PROG_FILE_TRAILER para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<LmkProgFileTrailerRowBean> getProgProgrammeTrailer(String lsWhere){
        List<LmkProgFileTrailerRowBean> loRes = 
            new ArrayList<LmkProgFileTrailerRowBean>();
        
        Connection              loCnn = new ConnectionAs400().getConnection();
        ResultSet               loRs = null;
        String                  lsQueryParadigm = getQueryProgProgrammeTrailer(lsWhere);        
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkProgFileTrailerRowBean loItem = new LmkProgFileTrailerRowBean();
                loItem.setLiRecordType(loRs.getInt("RECORD_TYPE"));
                loItem.setLiRecordCount(loRs.getInt("RECORD_COUNT"));
                loItem.setLiAllowableGap(loRs.getInt("ALLOWABLE_GAP"));                
                loItem.setLsStnid(loRs.getString("STNID"));
                loItem.setLsStrdt(loRs.getString("STRDT"));
                loItem.setLsEdt(loRs.getString("EDT"));
                
                loItem.setLtFechaCreacion(loRs.getTimestamp("FECHA_CREACION"));
                loRes.add(loItem);
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
        
        return loRes;
    }
    
    /**
     * Crea instruccicon para Obtiner registros de la tabla EVENTAS.LMK_PROG_TRAILER para armar archivo
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public String getQueryProgProgrammeTrailer(String lsWhere){
        String lsQuery = "SELECT RECORD_TYPE,\n" + 
        "        RECORD_COUNT,\n" + 
        "        ALLOWABLE_GAP,\n" + 
        "        STNID,\n" + 
        "        STRDT,\n" + 
        "        EDT,\n" + 
        "        FECHA_CREACION\n" + 
        "   FROM EVENTAS.LMK_PROG_FILE_TRAILER\n";
        lsQuery += " WHERE 1 = 1 " + lsWhere;
        
        return lsQuery;
    }
    
    /**
     * Ejecuta llamado a Procedimiento Almacenado en Paradigm
     * @autor Jorge Luis Bautista Santiago
     * @return ResponseUpdDao
     */
    public ResponseUpdDao deleteLmkProgProgramm(String tsStnid, 
                                                String tsStrdt, 
                                                String tsEdt) {
        ResponseUpdDao loResponseUpdDao = new ResponseUpdDao();
        String lsParameters = tsStnid+","+tsStrdt+","+tsEdt;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = 
            "DELETE \n" + 
            "  FROM EVENTAS.LMK_PROG \n" + 
            " WHERE STNID = '"+tsStnid+"'\n" + 
            "   AND DATE(BCSTDT) BETWEEN DATE('"+tsStrdt+"') \n" + 
            "                        AND DATE('"+tsEdt+"')";
        try {
            Statement loStmt = loCnn.createStatement();
            Integer liRes = loStmt.executeUpdate(lsQueryParadigm);
            loResponseUpdDao.setLiAffected(liRes);
            loResponseUpdDao.setLsMessage("Registros eliminados EVENTAS.LMK_PROG("+lsParameters+")");
            loResponseUpdDao.setLsResponse("OK");
        } catch (SQLException loExSql) {
            System.out.println(loExSql.getMessage());
            loResponseUpdDao.setLiAffected(0);
            loResponseUpdDao.setLsMessage("ERROR (deleteLmkProgProgramm): "+loExSql.getMessage());
            loResponseUpdDao.setLsResponse("OK");
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
     * Ejecuta llamado a Procedimiento Almacenado en Paradigm
     * @autor Jorge Luis Bautista Santiago
     * @return ResponseUpdDao
     */
    public ResponseUpdDao deleteLmkProgFileTrailer(String tsStnid, 
                                                   String tsStrdt, 
                                                   String tsEdt) {
        ResponseUpdDao loResponseUpdDao = new ResponseUpdDao();
        String lsParameters = tsStnid+","+tsStrdt+","+tsEdt;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = 
            "DELETE \n" + 
            "  FROM EVENTAS.LMK_PROG_FILE_TRAILER \n" + 
            " WHERE STNID = '"+tsStnid+"'\n" + 
            "   AND DATE(STRDT) = DATE('"+tsStrdt+"')\n" + 
            "   AND DATE(EDT)   = DATE('"+tsEdt+"')";
        try {
            Statement loStmt = loCnn.createStatement();
            Integer liRes = loStmt.executeUpdate(lsQueryParadigm);
            loResponseUpdDao.setLiAffected(liRes);
            loResponseUpdDao.setLsMessage("Registros eliminados EVENTAS.LMK_PROG_FILE_TRAILER("+lsParameters+")");
            loResponseUpdDao.setLsResponse("OK");
        } catch (SQLException loExSql) {
            System.out.println(loExSql.getMessage());
            loResponseUpdDao.setLiAffected(0);
            loResponseUpdDao.setLsMessage("ERROR (deleteLmkFileTrailer): "+loExSql.getMessage());
            loResponseUpdDao.setLsResponse("OK");
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
    
}
