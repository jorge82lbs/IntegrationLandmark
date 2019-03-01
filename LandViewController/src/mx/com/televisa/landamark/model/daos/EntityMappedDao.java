/**
* Project: Integraton Paradigm - Landmark
*
* File: EntityMappedDao.java
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import mx.com.televisa.landamark.model.AppModuleImpl;
import mx.com.televisa.landamark.model.cnn.ConnectionAs400;
import mx.com.televisa.landamark.model.types.LmkIntConfigParamRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesParamsRowBean;

import oracle.jbo.ApplicationModule;
import oracle.jbo.client.Configuration;

/** Clase que conecta de forma tradicional (JDBC) a bd
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 29, 2019, 12:00 pm
 */
public class EntityMappedDao {
    public EntityMappedDao() {
        super();
    }
    
    /**
     * Obtiene los parametros generales en base al uso
     * @autor Jorge Luis Bautista Santiago
     * @param tsUsedBy
     * @return List
     */    
    public List<LmkIntConfigParamRowBean> getParametersByUsed(String tsUsedBy) {
        List<LmkIntConfigParamRowBean> laList = 
            new ArrayList<LmkIntConfigParamRowBean>();
        Connection  loCnn = new ConnectionAs400().getConnection();
        ResultSet   loRs = null;
        String      lsQueryParadigm = getQueryParametersByUsed(tsUsedBy);
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkIntConfigParamRowBean loItem = new LmkIntConfigParamRowBean();
                loItem.setLiIdParameter(loRs.getInt("ID_PARAMETER"));
                loItem.setLsNomParameter(loRs.getString("NOM_PARAMETER"));
                loItem.setLsDescParameter(loRs.getString("IND_DESC_PARAMETER"));
                loItem.setLsUsedBy(loRs.getString("IND_USED_BY"));
                loItem.setLsValueParameter(loRs.getString("IND_VALUE_PARAMETER"));
                loItem.setLsStatus(loRs.getString("IND_ESTATUS"));
                laList.add(loItem);
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
        return laList;
    }
    
    /**
     * Crea intruccion para obtiener los parametros generales en base al uso
     * @autor Jorge Luis Bautista Santiago
     * @param tsUsedBy
     * @return String
     */    
    public String getQueryParametersByUsed(String tsUsedBy){
        //System.out.println("############## query parametros generales (USED_BY) ##############");
        String lsQuery = 
            "SELECT ID_PARAMETER,\n" + 
            "       NOM_PARAMETER,\n" + 
            "       IND_DESC_PARAMETER,\n" + 
            "       IND_USED_BY,\n" + 
            "       IND_VALUE_PARAMETER,\n" + 
            "       IND_ESTATUS\n" + 
            "  FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB\n" + 
            " WHERE IND_USED_BY = '" + tsUsedBy + "'";
        //System.out.println(lsQuery);
        return lsQuery;
    }
    
    /**
     * Obtiene Valor de Parametro General
     * @autor Jorge Luis Bautista Santiago
     * @param tsName
     * @param tsUsedBy
     * @return String
     */    
    public String getGeneralParameter(String tsName, String tsUsedBy) {
        String      loValue = "";
        Connection  loCnn = new ConnectionAs400().getConnection();
        ResultSet   loRs = null;
        String      lsQueryParadigm = getQueryGeneralParameter(tsName, tsUsedBy);
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                loValue = loRs.getString(1);
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
        return loValue;
    }
    
    /**
     * Obtiene Consulta para Valor de Parametro General
     * @autor Jorge Luis Bautista Santiago
     * @param tsName
     * @param tsUsedBy
     * @return String
     */
    public String getQueryGeneralParameter(String tsName, String tsUsedBy){
        //System.out.println("############## query parametros generales ##############");
        String lsQuery = 
            "SELECT IND_VALUE_PARAMETER \n" + 
            "   FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB\n" + 
            "  WHERE IND_USED_BY   = '" + tsUsedBy + "'\n" + 
            "    AND NOM_PARAMETER = '" + tsName + "'";
        //System.out.println(lsQuery);
        return lsQuery;
    }
    
    /**
     * Inserta en la tabla de bitacora en base de datos
     * @autor Jorge Luis Bautista Santiago
     * @param toLmkBitBean
     * @return Integer
     */
    public Integer insertBitacoraWs(LmkIntServiceBitacoraRowBean toLmkBitBean, 
                                    Integer tiIdUser,
                                    String tsUserName) {
        Integer    loValue = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = 
            getQueryInsertBitacora(toLmkBitBean, tiIdUser, tsUserName);
        try {
            Statement loStmt = loCnn.createStatement();
            loValue = loStmt.executeUpdate(lsQueryParadigm);
        } catch (SQLException loExSql) {
            System.out.println(loExSql.getMessage());
        }
        finally{
            try {
                loCnn.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return loValue;
    }
    
    /**
     * Genera instruccion para Insertar en la tabla de bitacora en base de datos
     * @autor Jorge Luis Bautista Santiago
     * @param toBitBean
     * @return Integer
     */
    public String getQueryInsertBitacora(LmkIntServiceBitacoraRowBean toBitBean, 
                                         Integer tiIdUser,
                                         String tsUserName){
        String lsQuery = 
            "INSERT INTO EVENTAS.LMK_INT_SERVICE_BITACORA_TAB " +
            "                           (ID_BITACORA,\n" + 
            "                            ID_LOG_SERVICES,\n" + 
            "                            ID_SERVICE,\n" + 
            "                            IND_PROCESS,\n" + 
            "                            NUM_PROCESS_ID,\n" + 
            "                            NUM_PGM_PROCESS_ID,\n" + 
            "                            IND_EVENTO,\n" + 
            "                            IND_ESTATUS,\n" + 
            /*"                            ATTRIBUTE_CATEGORY,\n" + 
            "                            ATTRIBUTE1,\n" + 
            "                            ATTRIBUTE2,\n" + 
            "                            ATTRIBUTE3,\n" + 
            "                            ATTRIBUTE4,\n" + 
            "                            ATTRIBUTE5,\n" + 
            "                            ATTRIBUTE6,\n" + 
            "                            ATTRIBUTE7,\n" + 
            "                            ATTRIBUTE8,\n" + 
            "                            ATTRIBUTE9,\n" + 
            "                            ATTRIBUTE10,\n" + 
            "                            ATTRIBUTE11,\n" + 
            "                            ATTRIBUTE12,\n" + 
            "                            ATTRIBUTE13,\n" + 
            "                            ATTRIBUTE14,\n" + */
            "                            ATTRIBUTE15,\n" + 
            "                            FEC_CREATION_DATE,\n" + 
            "                            NUM_CREATED_BY,\n" + 
            "                            FEC_LAST_UPDATE_DATE,\n" + 
            "                            NUM_LAST_UPDATED_BY,\n" + 
            "                            NUM_LAST_UPDATE_LOGIN\n" + 
            "                           )\n" + 
            "                    VALUES ('" + getIdBitacora() + "',\n" + 
            "                            " + toBitBean.getLiIdLogServices() + ",\n" + 
            "                            " + toBitBean.getLiIdService() + ",\n" + 
            "                            " + toBitBean.getLiIndProcess() + ",\n" + 
            "                            " + toBitBean.getLiNumProcessId() + ",\n" + 
            "                            " + toBitBean.getLiNumPgmProcessId() + ",\n" + 
            "                            '" + toBitBean.getLsIndEvento() + "',\n" + 
            "                            'A',\n" + 
            /*"                            '" + toEvetvIntServiceBitacoraTab.getLsAttributeCategory() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute1() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute2() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute3() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute4() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute5() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute6() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute7() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute8() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute9() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute10() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute11() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute12() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute13() + "',\n" + 
            "                            '" + toEvetvIntServiceBitacoraTab.getLsAttribute14() + "',\n" + */
            "                            '" + tsUserName + "',\n" + 
            "                            CURRENT_TIMESTAMP,\n" + 
            "                            " + tiIdUser + ",\n" + 
            "                            CURRENT_TIMESTAMP,\n" + 
            "                            " + tiIdUser + ",\n" + 
            "                            " + tiIdUser + "\n" + 
            "                           )";
        return lsQuery;
    }
    
    /**
     * Obtiene, en base a la fecha, el id_paradigm a manejar en intergracion
     * @autor Jorge Luis Bautista Santiago     
     * @return String
     */
    public String getIdBitacora(){
        String     lsResponse = null;
        DateFormat loDf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        lsResponse = loDf.format(new java.util.Date(System.currentTimeMillis()));
        return lsResponse;
    }

    /**
    * Obtiene la psKey para codificar y decodificar
    * @autor Jorge Luis Bautista Santiago
    * @return String
    */
    public String getKeyDecoderSimple() {
        String                    lsKey = "LFXqSn21ptd+rNihAuZeMg==";        
        Connection  loCnn = new ConnectionAs400().getConnection();
        ResultSet   loRs = null;
        String      lsQueryParadigm = 
        "SELECT IND_VALUE_PARAMETER\n" + 
        "  FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB\n" + 
        " WHERE IND_USED_BY = 'WS_Autenticacion'\n" + 
        "   AND NOM_PARAMETER = 'Key'";
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                lsKey = loRs.getString(1);
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
        return lsKey;
    }    
    
}
