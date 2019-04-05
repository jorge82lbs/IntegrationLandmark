/**
* Project: Integraton Paradigm - Landmark
*
* File: OrderSpotsDao.java
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

import mx.com.televisa.landamark.model.types.ResponseUpdDao;
import mx.com.televisa.landamark.model.types.inject.LmkSpotsRowBean;
import mx.com.televisa.landamark.util.ConnectionAs400;

/** Clase que accede a base de datos para metodos de Ordenes Spots que provienen de Landmark
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
 */
public class OrderSpotsDao {
    public OrderSpotsDao() {
        super();
    }
    
    /**
     * Inserta un registro en la tabla de spots
     * @autor Jorge Luis Bautista Santiago
     * @param toBean
     * @return ResponseUpdDao
     */  
    public ResponseUpdDao insertLmkSpot(LmkSpotsRowBean toBean){
        ResponseUpdDao loResponseUpdDao = new ResponseUpdDao();
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = getQueryInsertLmkSpot(toBean);
        try {
            Statement loStmt = loCnn.createStatement();
            int liRes = loStmt.executeUpdate(lsQueryParadigm);
            loResponseUpdDao.setLsResponse("OK");
            loResponseUpdDao.setLiAffected(liRes);
            loResponseUpdDao.setLsMessage("OK");
        } catch (SQLException loExSql) {
            loResponseUpdDao.setLsResponse("ERROR");
            loResponseUpdDao.setLiAffected(0);
            loResponseUpdDao.setLsMessage(loExSql.getMessage());
            System.out.println("ERROR AL INSERTAR SPOT: "+loExSql.getMessage());
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
     * Obtiene instruccion para Insertar un registro en la tabla de spots
     * @autor Jorge Luis Bautista Santiago
     * @param toBean
     * @return ResponseUpdDao
     */  
    public String getQueryInsertLmkSpot(LmkSpotsRowBean toBean){
        String lsQuery = 
            "INSERT INTO EVENTAS.LMK_SPOTS (SCHEDULED_DATE,\n" + 
            "                       BREAK_NOMINAL_TIME,\n" + 
            "                       BREAK_NUMBER,\n" + 
            "                       BREAK_TYPE,\n" + 
            "                       SECONDARY,\n" + 
            "                       SPOT_NUMBER,\n" + 
            "                       CAMPAIGN_NUMBER,\n" + 
            "                       SPOT_DURATION,\n" + 
            "                       SPOT_TYPE,\n" + 
            "                       SEQUENCE_NUMBER,\n" + 
            "                       PRODUCT_CODE,\n" + 
            "                       COMMERCIAL_TITLE,\n" + 
            "                       BONUS_SPOT,\n" + 
            "                       BUSINESS_TYPE_CODE,\n" + 
            "                       ROOT_CLASS_CODE,\n" + 
            "                       CLASH_CODE,\n" + 
            "                       CLASH_DESCRIPTION,\n" + 
            "                       CLOSE_CAPTION,\n" + 
            "                       MOBILITY_CODE,\n" + 
            "                       START_PRICE,\n" + 
            "                       PSD_PRICE,\n" + 
            "                       PROGRAMME_NAME,\n" + 
            "                       ADVERTISER_ID,\n" + 
            "                       ADVERTISER,\n" + 
            "                       AGENCY_ID,\n" + 
            "                       AGENCY_NAME,\n" + 
            "                       MULTI_PART_MEMBER\n" + 
            //"                       BOOKING_POSITION,\n" + 
            //"                       NATIVE_COMMERCIAL_TITLE,\n" + 
            //"                       COPY_TYPE,\n" + 
            //"                       ADVID,\n" + 
            //"                       AGYID,\n" + 
            //"                       ORDID,\n" + 
            //"                       ORDLNID,\n" + 
            //"                       STNID,\n" + 
            //"                       PGMID,\n" + 
            //"                       BRKDTID,\n" + 
            //"                       MSTLOGEDTID\n" + 
            "                      )\n" + 
            "	            VALUES ('"+toBean.getLsScheduledDate()+"',\n" + 
            "                       '"+toBean.getLsBreakNominalTime()+"',\n" + 
            "                       "+toBean.getLiBreakNumber()+",\n" + 
            "                       '"+toBean.getLsBreakType()+"',\n" + 
            "                       '"+toBean.getLsSecondary()+"',\n" + 
            "                       "+toBean.getLiSpotNumber()+",\n" + 
            "                       "+toBean.getLiCampaignNumber()+",\n" + 
            "                       '"+toBean.getLsSpotDuration()+"',\n" + 
            "                       '"+toBean.getLsSpotType()+"',\n" + 
            "                       "+toBean.getLiSequenceNumber()+",\n" + 
            "                       '"+toBean.getLsProductCode()+"',\n" + 
            "                       '"+toBean.getLsCommercialTitle()+"',\n" + 
            "                       '"+toBean.getLsBonusSpot()+"',\n" + 
            "                       '"+toBean.getLsBusinessTypeCode()+"',\n" + 
            "                       '"+toBean.getLsRootClassCode()+"',\n" + 
            "                       '"+toBean.getLsClashCode()+"',\n" + 
            "                       '"+toBean.getLsClashDescription()+"',\n" + 
            "                       '"+toBean.getLsCloseCaption()+"',\n" + 
            "                       '"+toBean.getLsMobilityCode()+"',\n" + 
            "                       "+toBean.getLiStartPrice()+",\n" + 
            "                       "+toBean.getLiPsdPrice()+",\n" + 
            "                       '"+toBean.getLsProgrammeName()+"',\n" + 
            "                       '"+toBean.getLsAdvertiserId()+"',\n" + 
            "                       '"+toBean.getLsAdvertiser()+"',\n" + 
            "                       '"+toBean.getLsAgencyId()+"',\n" + 
            "                       '"+toBean.getLsAgencyName()+"',\n" + 
            "                       "+toBean.getLiMultiPartMember()+"\n" + 
            //"                       '"+toBean.getLsBookingPosition()+"',\n" + 
            //"                       '"+toBean.getLsNativeCommercialTitle()+"',\n" + 
            //"                       '"+toBean.getLsCopyType()+"',\n" + 
            //"                       '"+toBean.getLsAdvid()+"',\n" + 
            //"                       '"+toBean.getLsAgyid()+"',\n" + 
            //"                       "+toBean.getLiOrdid()+",\n" + 
            //"                       "+toBean.getLiOrdlnid()+",\n" + 
            //"                       '"+toBean.getLsStnid()+"',\n" + 
            //"                       '"+toBean.getLsPgmid()+"',\n" + 
            //"                       "+toBean.getLiBrkdtid()+",\n" + 
            //"                       "+toBean.getLiMstlogedtid()+"\n" + 
            "                      )";
        
        //System.out.println("**************************************************");
        //System.out.println(lsQuery);
        //System.out.println("**************************************************");
        
        return lsQuery;
    }
    
    
    /**
     * Ejecuta llamado a Procedimiento Almacenado en Paradigm
     * @autor Jorge Luis Bautista Santiago
     * @return ResponseUpdDao
     */
    public ResponseUpdDao callLmkValidaSpotsPr(String tsStnid, 
                                           String tsBcstdt) {
        //System.out.println("Dentro de callTraditionalSalePr");
        System.out.println("**** setteando EVENTAS.LMK_VALIDA_SPOTS ******");
        System.out.println("tsStnid["+tsStnid+"]");
        System.out.println("tsStrdt["+tsBcstdt+"]");
        ResponseUpdDao loResponseUpdDao = new ResponseUpdDao();
        String lsResult = "EVENTAS.LMK_VALIDA_SPOTS(";
        Connection        loCnn = new mx.com.televisa.landamark.model.cnn.ConnectionAs400().getConnection();
        CallableStatement loCallStmt = null;
        String            lsQueryParadigm = "call EVENTAS.LMK_VALIDA_SPOTS(?,?)";
        try {
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
            
            if(tsBcstdt != null){
                if(!tsBcstdt.trim().equalsIgnoreCase("")){
                    java.sql.Date     ltDateStrt = getDateYYYYMMDD(tsBcstdt);
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
                                         
            loCallStmt.executeUpdate();
            loResponseUpdDao.setLsResponse("OK");
            loResponseUpdDao.setLsMessage(""+lsResult.substring(0, lsResult.length()-1)+") Execute Success!!!");
            //System.out.println(lsResult.substring(0, lsResult.length()-1)+") Execute Success!!!");
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
     * Ejecuta llamado a Procedimiento Almacenado en Paradigm
     * @autor Jorge Luis Bautista Santiago
     * @return ResponseUpdDao
     */
    public ResponseUpdDao callLmkGeneraSpotsPr(String tsStnid, 
                                           String tsBcstdt) {
        //System.out.println("Dentro de callTraditionalSalePr");
        System.out.println("**** setteando EVENTAS.EVETV_GENERA_SPOTS ******");
        System.out.println("tsStnid["+tsStnid+"]");
        System.out.println("tsStrdt["+tsBcstdt+"]");
        ResponseUpdDao loResponseUpdDao = new ResponseUpdDao();
        String lsResult = "EVENTAS.EVETV_GENERA_SPOTS(";
        Connection        loCnn = new mx.com.televisa.landamark.model.cnn.ConnectionAs400().getConnection();
        CallableStatement loCallStmt = null;
        String            lsQueryParadigm = "call EVENTAS.EVETV_GENERA_SPOTS(?,?)";
        try {
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
            
            if(tsBcstdt != null){
                if(!tsBcstdt.trim().equalsIgnoreCase("")){
                    java.sql.Date     ltDateStrt = getDateYYYYMMDD(tsBcstdt);
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
                                         
            loCallStmt.executeUpdate();
            loResponseUpdDao.setLsResponse("OK");
            loResponseUpdDao.setLsMessage(""+lsResult.substring(0, lsResult.length()-1)+") Execute Success!!!");
            //System.out.println(lsResult.substring(0, lsResult.length()-1)+") Execute Success!!!");
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
     * Ejecuta llamado a Procedimiento Almacenado en Paradigm
     * @autor Jorge Luis Bautista Santiago
     * @return ResponseUpdDao
     */
    public ResponseUpdDao callLmkGeneraLogsPr(String tsStnid, 
                                           String tsBcstdt) {
        //System.out.println("Dentro de callTraditionalSalePr");
        System.out.println("**** setteando EVENTAS.EVETV_GENERA_LOG ******");
        System.out.println("tsStnid["+tsStnid+"]");
        System.out.println("tsStrdt["+tsBcstdt+"]");
        ResponseUpdDao loResponseUpdDao = new ResponseUpdDao();
        String lsResult = "EVENTAS.EVETV_GENERA_LOG(";
        Connection        loCnn = new mx.com.televisa.landamark.model.cnn.ConnectionAs400().getConnection();
        CallableStatement loCallStmt = null;
        String            lsQueryParadigm = "call EVENTAS.EVETV_GENERA_LOG(?,?)";
        try {
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
            
            if(tsBcstdt != null){
                if(!tsBcstdt.trim().equalsIgnoreCase("")){
                    java.sql.Date     ltDateStrt = getDateYYYYMMDD(tsBcstdt);
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
                                         
            loCallStmt.executeUpdate();
            loResponseUpdDao.setLsResponse("OK");
            loResponseUpdDao.setLsMessage(""+lsResult.substring(0, lsResult.length()-1)+") Execute Success!!!");
            //System.out.println(lsResult.substring(0, lsResult.length()-1)+") Execute Success!!!");
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
    
    /**
     * Revisa si existe un OK despues de la validacion
     * @autor Jorge Luis Bautista Santiago
     * @param tsStnid
     * @param tsBcstdt
     * @return Integer
     */
    public Integer validateOkStatusSpots(String tsStnid, String tsBcstdt) {
        //tsBcstdt formato YYYY-MM-DD
        Integer    loFlag = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        ResultSet  loRs = null;
        String     lsQueryParadigm = 
            "SELECT COUNT(1) \n" + 
            "  FROM EVENTAS.LOG_COMERCIAL_STATUS \n" + 
            " WHERE STATUS = 'OK'\n" + 
            "   AND STNID = '"+tsStnid+"'\n" + 
            "   AND BCSTDT = '"+tsBcstdt+"'";
        System.out.println(lsQueryParadigm);
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                loFlag = loRs.getInt(1);
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
        return loFlag;
    }
    
    
    
}
