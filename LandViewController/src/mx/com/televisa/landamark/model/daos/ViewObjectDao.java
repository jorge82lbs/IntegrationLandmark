/**
* Project: Paradigm - Landmark Integration
*
* File: ViewObjectDao.java
*
* Created on:  Febrero 6, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/
package mx.com.televisa.landamark.model.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mx.com.televisa.landamark.model.cnn.ConnectionAs400;
import mx.com.televisa.landamark.model.interefaces.ViewObjectInterface;
import mx.com.televisa.landamark.view.types.SelectOneItemBean;

/** Crea conexion a una base de datos de Paradigm<br/><br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Febrero 14, 2019, 12:00 pm
 */
public class ViewObjectDao implements ViewObjectInterface{
    public ViewObjectDao() {
        super();
    }

    /**
     * Obtiene el numero maximo de la tabla de servicio de catalogos
     * @autor Jorge Luis Bautista Santiago
     * @return Integer
     */
    @Override
    public Integer getMaxIdServicesCatalog() {
        Integer    liReturn = 0; 
        Connection loCnn = new ConnectionAs400().getConnection();
        ResultSet  loRs = null;
        String     lsQueryParadigm = getQueryMaxServicesCatalog();
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                liReturn = loRs.getInt(1);
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
        
        return liReturn;
    }
    @Override
    public String getQueryMaxServicesCatalog() {
        String lsQuery = 
        "SELECT coalesce(MAX(ID_SERVICE),0) \n" + 
        "  FROM EVENTAS.LMK_INT_SERVICES_CAT_TAB";
        return lsQuery;
    }

    @Override
    public Integer getMaxIdParadigm(String tsTable) {
        Integer    liReturn = 0; 
        Connection loCnn = new ConnectionAs400().getConnection();
        ResultSet  loRs = null;
        String     lsTable = "";
        String     lsField = "";
        if(tsTable.equalsIgnoreCase("Parameters")){
            lsTable = "EVENTAS.LMK_INT_CONFIG_PARAM_TAB";
            lsField = "ID_PARAMETER";
        }
        if(tsTable.equalsIgnoreCase("ServParameters")){
            lsTable = "EVENTAS.LMK_INT_SERVICES_PARAMS_TAB";
            lsField = "ID_PARAMETER_SERV";
        }
        if(tsTable.equalsIgnoreCase("Mapping")){
            lsTable = "EVENTAS.LMK_INT_MAPPING_CAT_TAB";
            lsField = "ID_MAPPING";
        }
        if(tsTable.equalsIgnoreCase("Notifications")){
            lsTable = "EVENTAS.LMK_INT_NOTIFICATIONS_TAB";
            lsField = "ID_NOTIFICATION";
        }
        if(tsTable.equalsIgnoreCase("Cron")){
            lsTable = "EVENTAS.LMK_INT_CRON_CONFIG_TAB";
            lsField = "ID_CONFIGURATION";
        }
        if(tsTable.equalsIgnoreCase("Log")){
            lsTable = "EVENTAS.LMK_INT_SERVICES_LOG_TAB";
            lsField = "ID_LOG_SERVICES";
        }
        if(tsTable.equalsIgnoreCase("Bit")){
            lsTable = "EVENTAS.LMK_INT_SERVICE_BITACORA_TAB";
            lsField = "ID_BITACORA";
        }
        if(tsTable.equalsIgnoreCase("Request")){
            lsTable = "EVENTAS.LMK_INT_REQUESTS_TAB";
            lsField = "ID_REQUEST";
        }
        if(tsTable.equalsIgnoreCase("RstXmlFiles")){
            try {
                lsTable = "EVENTAS.LMK_INT_XML_FILES_TAB";
                lsField = "ID_FILE_XML";
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("err sleep22");
            }
        }
        
        String lsQueryParadigm = getQueryMaxParadigm(lsTable,lsField);
        //String lsQueryParadigm = getQueryMaxOracle(lsTable,lsField);
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                liReturn = loRs.getInt(1);
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
        return liReturn;
    }

    @Override
    public String getQueryMaxParadigm(String tsTable, String tsField) {
        String lsQuery = 
        "SELECT coalesce(MAX(" + tsField + "),0) \n" + 
        "  FROM " + tsTable;
        return lsQuery;
    }
    
    public String getQueryMaxOracle(String tsTable, String tsField) {
        String lsQuery = 
        "SELECT NVL(MAX(" + tsField + "),0) \n" + 
        "  FROM " + tsTable;
        return lsQuery;
    }

    @Override
    public String getIdServiceByNomService(String tsNomService) {
        String     liReturn = "0"; 
        Connection loCnn = new ConnectionAs400().getConnection();
        ResultSet  loRs = null;
        String     lsQueryParadigm = 
        "     SELECT ID_SERVICE\n" + 
        "       FROM EVENTAS.LMK_INT_SERVICES_CAT_TAB       \n" + 
        "      WHERE NOM_SERVICE = '" + tsNomService + "'";
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                liReturn = loRs.getString(1);
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
        return liReturn;
    }

    @Override
    public String getProcessIdByNomParameter(String tsNomParameter) {
        String     liReturn = "0"; 
        Connection loCnn = new ConnectionAs400().getConnection();
        ResultSet  loRs = null;
        String     lsQueryParadigm = "SELECT ID_PARAMETER         \n" + 
        "     FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB\n" + 
        "    WHERE IND_USED_BY = 'PROCESS_INTEGRATION'\n" + 
        "      AND NOM_PARAMETER  = '" + tsNomParameter + "'";
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                liReturn = loRs.getString(1);
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
        return liReturn;
    }

    @Override
    public String getUsersGroupByDescParameter(String tsDescParameter) {
        String     liReturn = "0"; 
        Connection loCnn = new ConnectionAs400().getConnection();
        ResultSet  loRs = null;
        String     lsQueryParadigm = "  SELECT NOM_PARAMETER          \n" + 
        "     FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB\n" + 
        "    WHERE IND_USED_BY        = 'USERS_GROUP_INTEGRATION'\n" + 
        "      AND IND_DESC_PARAMETER = '" + tsDescParameter + "'";
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                liReturn = loRs.getString(1);
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
        return liReturn;
    }

    @Override
    public List<SelectOneItemBean> getListAllWebServicesModel() {
        List<SelectOneItemBean> laReturn = new ArrayList<SelectOneItemBean>();
        Connection              loCnn = new ConnectionAs400().getConnection();
        ResultSet               loRs = null;
        String                  lsQueryParadigm = getQueryAllWebServices();        
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                SelectOneItemBean loItem = new SelectOneItemBean();
                loItem.setLsId(loRs.getString("ID_SERVICE"));
                loItem.setLsValue(loRs.getString("IND_DESC_SERVICE"));
                loItem.setLsDescription(loRs.getString("NOM_SERVICE"));
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

    @Override
    public String getQueryAllWebServices() {
        String lsQuery = "SELECT ID_SERVICE,\n" + 
        "       NOM_SERVICE,\n" + 
        "       IND_DESC_SERVICE,\n" + 
        "       IND_SERVICE_WSDL\n" + 
        "  FROM EVENTAS.LMK_INT_SERVICES_CAT_TAB";
        return lsQuery;
    }

    @Override
    public List<SelectOneItemBean> getListGeneralParametersModelFilter(String tsArgs) {
        List<SelectOneItemBean> laReturn = new ArrayList<SelectOneItemBean>();
        Connection              loCnn = new ConnectionAs400().getConnection();
        ResultSet               loRs = null;
        String                  lsQueryParadigm = getQueryGeneralParameters(tsArgs);        
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                SelectOneItemBean loItem = new SelectOneItemBean();
                loItem.setLsId(loRs.getString("ID_PARAMETER"));
                loItem.setLsValue(loRs.getString("NOM_PARAMETER"));
                loItem.setLsDescription(loRs.getString("IND_DESC_PARAMETER"));
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

    @Override
    public String getQueryGeneralParameters(String tsArgs) {
        String lsQuery = " SELECT ID_PARAMETER,\n" + 
                "          NOM_PARAMETER,\n" + 
                "          IND_DESC_PARAMETER,      \n" + 
                "          IND_VALUE_PARAMETER\n" + 
                "     FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB     \n" + 
                "    WHERE IND_USED_BY = '" + tsArgs + "'";
        return lsQuery;
    }
}
