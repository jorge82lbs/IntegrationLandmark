/**
* Project: Integraton Paradigm - Landmark
*
* File: SpotStatusDao.java
*
* Created on: Septiembre 16, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/

package mx.com.televisa.landamark.model.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import mx.com.televisa.landamark.model.cnn.ConnectionAs400;
import mx.com.televisa.landamark.model.types.LmkSpotstatusTrxBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;

/** Clase que accede a base de datos para metodos genericos de
 * SpotStatus
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Septiembre 16, 2019, 12:00 pm
 */
public class SpotStatusDao {
    public SpotStatusDao() {
        super();
    }
    
    /**
     * Elimina registros de la tabla auxiliar transaccional
     * @autor Jorge Luis Bautista Santiago
     * @return Integer
     */  
    public ResponseUpdDao deleteRowsSpotStatusTrx() {
        ResponseUpdDao    liReturn = new ResponseUpdDao();
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = 
            "DELETE \n" + 
            "  FROM EVENTAS.LMK_SPOTSTATUS_TRX";
        try {
            Statement loStmt = loCnn.createStatement();
            Integer liRes = loStmt.executeUpdate(lsQueryParadigm);
            liReturn.setLiAffected(liRes);
            liReturn.setLsResponse("OK");
            liReturn.setLsMessage("OK");
        } catch (SQLException loExSql) {
            liReturn.setLiAffected(0);
            liReturn.setLsResponse("OK");
            liReturn.setLsMessage(loExSql.getMessage());
            System.out.println("ERROR al elimiar registros EVENTAS.LMK_SPOTSTATUS_TRX "+loExSql.getCause());
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
     * Inserta registros de la tabla auxiliar transaccional
     * @autor Jorge Luis Bautista Santiago
     * @return Integer
     */  
    public ResponseUpdDao insertRowSpotStatusTrx(LmkSpotstatusTrxBean loBean) {
        ResponseUpdDao    liReturn = new ResponseUpdDao();
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = getQueryInsertSpotStatusTrx(loBean);
        try {
            Statement loStmt = loCnn.createStatement();
            Integer liRes = loStmt.executeUpdate(lsQueryParadigm);
            liReturn.setLiAffected(liRes);
            liReturn.setLsResponse("OK");
            liReturn.setLsMessage("OK");
        } catch (SQLException loExSql) {
            liReturn.setLiAffected(0);
            liReturn.setLsResponse("ERROR");
            liReturn.setLsMessage(loExSql.getMessage());
            System.out.println("ERROR al INSERTAR registros EVENTAS.LMK_SPOTSTATUS_TRX "+loExSql.getCause());
            System.out.println(lsQueryParadigm);
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
    public String getQueryInsertSpotStatusTrx(LmkSpotstatusTrxBean loBean) {
        
        String lsQuery = 
            "INSERT INTO EVENTAS.LMK_SPOTSTATUS_TRX(ADVERTISERCODE,\n" + 
            "                                       AGENGYCODE,\n" + 
            "                                       CAMPAIGN,\n" + 
            "                                       BREAK_NUMBER,\n" + 
            "                                       DEAL,\n" + 
            "                                       EXTERNALREFERENCE,\n" + 
            "                                       INDUSTRY_CODE,\n" + 
            "                                       LENGTH,\n" + 
            "                                       NOMIANLPRICE,\n" + 
            "                                       SCHEDULEDATE,\n" + 
            "                                       SCHEDULETIME,\n" + 
            "                                       SPOTNUMBER,\n" + 
        "                                       BREAKPOSITION,\n" + 
        "                                       PREEMPTEE,\n" + 
        "                                       PREEMPTOR,\n"; 
        if(loBean.getLdRatings()!= null){ lsQuery += "  RATINGS,\n";}
        if(loBean.getLsRatingsType()!= null){ lsQuery += "  RATINGTYPE,\n";}
            lsQuery += "                                       SPOTSALESAREANUMBER\n" + 
            "				       )\n" + 
            "				VALUES ('"+loBean.getLsAdvertisercode()+"',--ADVERTISERCODE,\n" + 
            "                                       '"+loBean.getLsAgengycode()+"',--AGENGYCODE,\n" + 
            "                                       "+loBean.getLiCampaign()+",--CAMPAIGN,\n" + 
            "                                       "+loBean.getLiBreakNumber()+",--BREAK_NUMBER,\n" + 
            "                                       "+loBean.getLiDeal()+",--DEAL,\n" + 
            "                                       '"+loBean.getLsExternalreference()+"',--EXTERNALREFERENCE,\n" + 
            "                                       '"+loBean.getLsIndustryCode()+"',--INDUSTRY_CODE,\n" + 
            "                                       "+loBean.getLiLength()+",--LENGTH,\n" + 
            "                                       "+loBean.getLdNomianlprice()+",--NOMIANLPRICE,\n" + 
            "                                       DATE('"+loBean.getLsScheduleDate()+"'),--SCHEDULEDATE,\n" + 
            "                                       "+loBean.getLiScheduletime()+",--SCHEDULETIME,\n" + 
            "                                       "+loBean.getLiSpotnumber()+",--SPOTNUMBER,\n" + 
        "                                       "+loBean.getLiBreakPosition()+",--BREAKPOSITION,\n" + 
        "                                       "+loBean.getLiPreemptee()+",--PREEMPTEE,\n" + 
        "                                       "+loBean.getLiPreemptor()+",--PREEMPTOR,\n";
        if(loBean.getLdRatings()!= null){ lsQuery += " "+loBean.getLdRatings()+",--RATINGS\n";}
        if(loBean.getLsRatingsType()!= null){ lsQuery += " '"+loBean.getLsRatingsType()+"',--RATINGTYPE\n";}
            lsQuery +=  "                                       "+loBean.getLiSpotsalesareanumber()+"--SPOTSALESAREANUMBER,\n" + 
            "				      )";
                
        return lsQuery;
    }

    /**
     * Ejecuta procedimiento en base de datos para el encabezado de la orden
     * @autor Jorge Luis Bautista Santiago
     * @param toOrdHeaderModulosTab
     * @return ResponseUpdDao
     */
    public ResponseUpdDao callProcedureUpdSpotStatus() throws SQLException {
        ResponseUpdDao    loResponseUpdDao = new ResponseUpdDao();
        Connection        loCnn = new ConnectionAs400().getConnection();
        CallableStatement loCallStmt = null;
        System.out.println("Parametros(callProcedureUpdSpotStatus).........");
        String            lsQueryParadigm = "call EVENTAS.LMK_UPD_SPOTSTATUS";
        try {
            loCallStmt = loCnn.prepareCall(lsQueryParadigm);
            loCallStmt.execute();
            loResponseUpdDao.setLsResponse("OK");
            loResponseUpdDao.setLiAffected(0);
            loResponseUpdDao.setLsMessage("Success callProcedureUpdSpotStatus");
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
    
}
