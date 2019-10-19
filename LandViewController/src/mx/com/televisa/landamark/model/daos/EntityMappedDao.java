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

import mx.com.televisa.landamark.client.email.types.local.EmailDestinationAddress;
import mx.com.televisa.landamark.model.cnn.ConnectionAs400;
import mx.com.televisa.landamark.model.types.LmkIntConfigParamRowBean;
import mx.com.televisa.landamark.model.types.LmkIntCronConfigRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesLogRowBean;

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
    
    /**
     * Obtiene los parametros generales en base al uso
     * @autor Jorge Luis Bautista Santiago
     * @param tiId
     * @return List
     */    
    public List<LmkIntConfigParamRowBean> getParametersById(Integer tiId) {
        List<LmkIntConfigParamRowBean> laList = 
            new ArrayList<LmkIntConfigParamRowBean>();
        Connection  loCnn = new ConnectionAs400().getConnection();
        ResultSet   loRs = null;
        String      lsQueryParadigm = getQueryParametersById(tiId);
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
     * @param tiId
     * @return String
     */    
    public String getQueryParametersById(Integer tiId){
        //System.out.println("############## query parametros generales (USED_BY) ##############");
        String lsQuery = 
            "SELECT ID_PARAMETER,\n" + 
            "       NOM_PARAMETER,\n" + 
            "       IND_DESC_PARAMETER,\n" + 
            "       IND_USED_BY,\n" + 
            "       IND_VALUE_PARAMETER,\n" + 
            "       IND_ESTATUS\n" + 
            "  FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB\n" + 
            " WHERE ID_PARAMETER = " + tiId + "";
        //System.out.println(lsQuery);
        return lsQuery;
    }
    
    /**
     * Inserta en la tabla de bitacora en base de datos
     * @autor Jorge Luis Bautista Santiago
     * @param toLmkBitBean
     * @return Integer
     */
    public void updateParametersServiceLog(Integer tiIdLogService,
                                           Integer tiIdService,
                                           String tsFechas,
                                           String tsChannels) {
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = 
            "UPDATE EVENTAS.LMK_INT_SERVICES_LOG_TAB\n" + 
            "   SET ATTRIBUTE1 = '"+tsFechas+"',\n" + 
            "       ATTRIBUTE2 = '"+tsChannels+"'\n" + 
            " WHERE ID_LOG_SERVICES = "+tiIdLogService+" " +
            "   AND ID_SERVICE = "+tiIdService;
        try {
            Statement loStmt = loCnn.createStatement();
            loStmt.executeUpdate(lsQueryParadigm);
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
    }
        
    /**
     * Inserta en la tabla de LMK_INT_SERVICES_LOG_TAB en base de datos
     * @autor Jorge Luis Bautista Santiago
     * @param toLmkBitBean
     * @return Integer
     */
    public Integer insertSimpleServicesLog(LmkIntServicesLogRowBean toLmkBean, 
                                    Integer tiIdUser,
                                    String tsUserName) {
        Integer    loValue = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQuery = 
            getQueryInsertServicesLog(toLmkBean, tiIdUser, tsUserName);
        //System.out.println(lsQuery);
        try {
            Statement loStmt = loCnn.createStatement();
            loValue = loStmt.executeUpdate(lsQuery);
            //System.out.println("*********** ok ************");
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
    public String getQueryInsertServicesLog(LmkIntServicesLogRowBean toLmkBean, 
                                         Integer tiIdUser,
                                         String tsUserName){
        String lsQuery = 
            "INSERT INTO EVENTAS.LMK_INT_SERVICES_LOG_TAB (ID_LOG_SERVICES,\n" + 
            "                                              ID_SERVICE,\n" + 
            "                                              IND_PROCESS,\n" + 
            "                                              IND_RESPONSE,\n" + 
            "                                              NUM_USER,\n" + 
            "                                              NUM_PROCESS_ID,\n" + 
            "                                              NUM_PGM_PROCESS_ID,\n" + 
            "                                              FEC_REQUEST,                                              \n" + 
            "                                              IND_SERVICE_TYPE,\n" + 
            "                                              IND_ESTATUS,\n" + 
            "                                              FEC_CREATION_DATE,\n" + 
            "                                              FEC_LAST_UPDATE_DATE,\n" + 
            "                                              ATTRIBUTE15,                                              \n" + 
            "                                              NUM_CREATED_BY,\n" + 
            "                                              NUM_LAST_UPDATED_BY,\n" + 
            "                                              NUM_LAST_UPDATE_LOGIN\n" + 
            "                                              )\n" + 
            "                                      VALUES ("+toLmkBean.getLiIdLogServices()+",\n" + 
            "                                              "+toLmkBean.getLiIdService()+",\n" + 
            "                                              "+toLmkBean.getLiIndProcess()+",\n" + 
            "                                              '"+toLmkBean.getLsIndResponse()+"',\n" + 
            "                                              "+tiIdUser+",\n" + 
            "                                              "+toLmkBean.getLiNumProcessId()+",\n" + 
            "                                              "+toLmkBean.getLiNumPgmProcessId()+",\n" + 
            "                                              CURRENT_TIMESTAMP,\n" + 
            "                                              '"+toLmkBean.getLsIndServiceType()+"',\n" + 
            "                                              'A',\n" + 
            "                                              CURRENT_TIMESTAMP,\n" + 
            "                                              CURRENT_TIMESTAMP,\n" + 
            "                                              '"+tsUserName+"',\n" + 
            "                                              "+tiIdUser+",\n" + 
            "                                              "+tiIdUser+",\n" + 
            "                                              "+tiIdUser+"\n" + 
            "                                              )";
        return lsQuery;
    }
    
    /**
     * Obtiene Valor de Parametro General
     * @autor Jorge Luis Bautista Santiago
     * @param tsName
     * @param tsUsedBy
     * @return String
     */    
    public Integer getGeneralParameterID(String tsName, String tsUsedBy) {
        Integer     loValue = 0;
        Connection  loCnn = new ConnectionAs400().getConnection();
        ResultSet   loRs = null;
        String      lsQueryParadigm = getQueryGeneralParameterID(tsName, tsUsedBy);
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                loValue = loRs.getInt(1);
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
    public String getQueryGeneralParameterID(String tsName, String tsUsedBy){
        //System.out.println("############## query parametros generales ##############");
        String lsQuery = 
            "SELECT ID_PARAMETER \n" + 
            "   FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB\n" + 
            "  WHERE IND_USED_BY   = '" + tsUsedBy + "'\n" + 
            "    AND NOM_PARAMETER = '" + tsName + "'";
        //System.out.println(lsQuery);
        return lsQuery;
    }
    
    
    /**
     * Actualiza en la tabla de crones en base de datos
     * @autor Jorge Luis Bautista Santiago
     * @param toLMKIntServiceBitacoraTab
     * @return Integer
     */
    public Integer disableInitializedCron() {
        Integer    loValue = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm =             
            "UPDATE EVENTAS.LMK_INT_CRON_CONFIG_TAB" +
            "   SET IND_ESTATUS = '4'" +
            " WHERE IND_ESTATUS = '2'";
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
     * Actualiza en la tabla de crones en base de datos
     * @autor Jorge Luis Bautista Santiago
     * @return Integer
     */
    public Integer enableInitializedCron() {
        Integer    loValue = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = 
            "UPDATE EVENTAS.LMK_INT_CRON_CONFIG_TAB" +
            "   SET IND_ESTATUS = '2'" +
            " WHERE IND_ESTATUS = '4'";
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
     * Obtiene direcciones de correo como destinatarios
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<LmkIntCronConfigRowBean> getServicesCronExecution(){
        List<LmkIntCronConfigRowBean> laServices = new ArrayList<LmkIntCronConfigRowBean>();
        Connection                    loCnn = new ConnectionAs400().getConnection();
        ResultSet                     loRs = null;
        String                        lsQueryParadigm = getQueryServicesExecution();
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkIntCronConfigRowBean loServiceBean = new LmkIntCronConfigRowBean();             
                loServiceBean.setLiIdService(loRs.getInt("ID_SERVICE"));
                loServiceBean.setLsIndBeginSchedule(loRs.getString("IND_BEGIN_SCHEDULE"));  
                loServiceBean.setLsIndEndSchedule(loRs.getString("IND_END_SCHEDULE"));  
                loServiceBean.setLsIndCronExpression(loRs.getString("IND_CRON_EXPRESSION"));                
                loServiceBean.setLsIndEstatus(loRs.getString("IND_ESTATUS"));                
                //loServiceBean.setAttribute14(loRs.getString("ATTRIBUTE14"));  
                loServiceBean.setAttribute1(loRs.getString("ATTRIBUTE1"));  
                loServiceBean.setLsIndPeriodicity(loRs.getString("IND_PERIODICITY"));  
                loServiceBean.setLsIndValTypeSchedule(loRs.getString("IND_VAL_TYPE_SCHEDULE"));  
                //loServiceBean.setLiNumLastUpdateBy(loRs.getInt("NUM_LAST_UPDATED_BY")); 
                //loServiceBean.setAttribute12(loRs.getString("ATTRIBUTE12"));
                //loServiceBean.setFecLastUpdateDate(loRs.getTimestamp("FEC_LAST_UPDATE_DATE"));  
                laServices.add(loServiceBean);
            }
        } catch (SQLException loExSql) {
            System.out.println("ERROR AL EJECUTAR: ");
            //System.out.println(lsQueryParadigm);
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
        return laServices;
    }
    
    public String getQueryServicesExecution(){
        String lsQuery = 
            "    SELECT ID_CONFIGURATION,\n" + 
            "		ID_SERVICE,\n" + 
            "		IND_PERIODICITY,\n" + 
            "		IND_BEGIN_SCHEDULE,\n" + 
            "		IND_BEGIN_MINUTE,\n" + 
            "		IND_BEGIN_SECOND,\n" + 
            "		IND_END_SCHEDULE,\n" + 
            "		IND_END_MINUTE,\n" + 
            "		IND_END_SECOND,\n" + 
            "		IND_TYPE_SCHEDULE,\n" + 
            "		IND_VAL_TYPE_SCHEDULE,\n" + 
            "		IND_MONDAY,\n" + 
            "		IND_TUESDAY,\n" + 
            "		IND_WEDNESDAY,\n" + 
            "		IND_THURSDAY,\n" + 
            "		IND_FRIDAY,\n" + 
            "		IND_SATURDAY,\n" + 
            "		IND_SUNDAY,\n" + 
            "		IND_DAY_MONTH,\n" + 
            "		IND_WEEK_MONTH,\n" + 
            "		IND_CRON_EXPRESSION,\n" + 
            "		IND_ESTATUS,\n" + 
            "		ATTRIBUTE_CATEGORY,\n" + 
            "		ATTRIBUTE1,\n" + 
            "		ATTRIBUTE2,\n" + 
            "		ATTRIBUTE3,\n" + 
            "		ATTRIBUTE4,\n" + 
            "		ATTRIBUTE5,\n" + 
            "		ATTRIBUTE6,\n" + 
            "		ATTRIBUTE7,\n" + 
            "		ATTRIBUTE8,\n" + 
            "		ATTRIBUTE9,\n" + 
            "		ATTRIBUTE10,\n" + 
            "		ATTRIBUTE11,\n" + 
            "		ATTRIBUTE12,\n" + 
            "		ATTRIBUTE13,\n" + 
            "		ATTRIBUTE14,\n" + 
            "		ATTRIBUTE15,\n" + 
            "		FEC_CREATION_DATE,\n" + 
            "		NUM_CREATED_BY,\n" + 
            "		FEC_LAST_UPDATE_DATE,\n" + 
            "		NUM_LAST_UPDATED_BY,\n" + 
            "           NUM_LAST_UPDATE_LOGIN  \n" + 
            "     FROM  EVENTAS.LMK_INT_CRON_CONFIG_TAB\n" + 
            "    WHERE  IND_ESTATUS = '4'";
        return lsQuery;
    }
    
    /**
     * Obtiene Valor de Configuracion Sincrona o Asincrona del servicio en cuestion
     * @autor Jorge Luis Bautista Santiago
     * @param tsIdService
     * @return String
     */
    public String getTypeService(String tsIdService) {
        String     lsValue = "";
        Connection loCnn = new ConnectionAs400().getConnection();
        ResultSet  loRs = null;
        String     lsQueryParadigm = getQueryTypeService(tsIdService);
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                lsValue = loRs.getString(1);
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
        return lsValue;
    }
    
    /**
     * Obtiene Consulta Valor de Configuracion Sincrona o Asincrona del servicio en cuestion
     * @autor Jorge Luis Bautista Santiago
     * @param tsIdService
     * @return String
     */
    public String getQueryTypeService(String tsIdService){
        String lsQuery = 
            "SELECT NOM_SERVICE\n" + 
            "  FROM EVENTAS.LMK_INT_SERVICES_CAT_TAB \n" + 
            " WHERE ID_SERVICE = " + tsIdService;
        return lsQuery;
    }
    
    
    /**
     * Obtiene Valor de Configuracion Sincrona o Asincrona del servicio en cuestion
     * @autor Jorge Luis Bautista Santiago
     * @param tsIdService
     * @return String
     */
    public String getNameDescService(String tsIdService) {
        String     lsValue = "";
        Connection loCnn = new ConnectionAs400().getConnection();
        ResultSet  loRs = null;
        String     lsQueryParadigm = getQueryNameService(tsIdService);
        //System.out.println(lsQueryParadigm);
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                lsValue = loRs.getString(1);
            }
        } catch (SQLException loExSql) {
            System.out.println("Erro al ex "+loExSql.getCause());
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
        return lsValue;
    }
    
    /**
     * Obtiene Consulta Valor de Configuracion Sincrona o Asincrona del servicio en cuestion
     * @autor Jorge Luis Bautista Santiago
     * @param tsIdService
     * @return String
     */
    public String getQueryNameService(String tsIdService){
        String lsQuery = 
            "SELECT IND_DESC_SERVICE\n" + 
            "  FROM EVENTAS.LMK_INT_SERVICES_CAT_TAB \n" + 
            " WHERE ID_SERVICE = " + tsIdService;
        return lsQuery;
    }
    
    /**
     * Actualiza en la tabla de crones en base de datos
     * @autor Jorge Luis Bautista Santiago
     * @return Integer
     */
    public void updateStatusCron(String tsStatus, String tsIdService) {
        Integer    loValue = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = 
            "UPDATE EVENTAS.LMK_INT_CRON_CONFIG_TAB" +
            "   SET IND_ESTATUS = '"+tsStatus+"'" +
            " WHERE ID_SERVICE = "+tsIdService;
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
        //return loValue;
    }
    
    /**
         * Obtiene direcciones de correo como destinatarios
         * @autor Jorge Luis Bautista Santiago
         * @return List
         */
        public List<EmailDestinationAddress> getDestinationAddress(String tsIdService, String tsGroup, String tsProcess){
            List<EmailDestinationAddress> loEmails = new ArrayList<EmailDestinationAddress>();
            Connection                    loCnn = new ConnectionAs400().getConnection();
            ResultSet                     loRs = null;
            String                        lsQueryParadigm = getQueryEmailsDyna(tsIdService,tsGroup,tsProcess);
            try {
                Statement loStmt = loCnn.createStatement();
                loRs = loStmt.executeQuery(lsQueryParadigm);  
                while(loRs.next()){
                    EmailDestinationAddress loEmailBean = new EmailDestinationAddress();             
                    loEmailBean.setLsNameTo(loRs.getString("NOM_PARAMETER") == null ? null : 
                                              loRs.getString("NOM_PARAMETER").trim());
                    loEmailBean.setLsAddressTo(loRs.getString("NOM_PARAMETER") == null ? null : 
                                              loRs.getString("NOM_PARAMETER").trim());    
                    //System.out.println("TO("+loEmailBean.getLsNameTo()+") TO_ADD("+loEmailBean.getLsAddressTo()+")");
                    loEmails.add(loEmailBean);
                }
            } catch (SQLException loExSql) {
                System.out.println("ERROR AL EJECUTAR: ");
                //System.out.println(lsQueryParadigm);
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
            return loEmails;
        }
        
       
        public String getQueryEmailsDyna(String tsIdService, String tsGroup, String tsProcess){
            String lsQuery = 
                "SELECT DISTINCT CPT.NOM_PARAMETER,\n" + 
                "       CPT.IND_VALUE_PARAMETER\n" + 
                "  FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB    CPT,\n" + 
                "       EVENTAS.LMK_INT_NOTIFICATIONS_TAB   NTF\n" + 
                " WHERE 1 = 1 \n" + 
                "   AND NTF.IND_USERS_GROUP         = CPT.IND_DESC_PARAMETER\n" + 
                "   AND NTF.ID_SERVICE              = "+tsIdService+"\n" + 
                "   AND CPT.IND_ESTATUS             = '1'\n" + 
                "   AND CPT.IND_VALUE_PARAMETER     = '"+tsProcess+"' \n" + 
                "   AND CPT.IND_DESC_PARAMETER      = '"+tsGroup+"'\n" + 
                "   AND CPT.IND_USED_BY             = 'EMAIL_WSINTEGRATION'";
            return lsQuery;
        }
        
    
}
