package mx.com.televisa.landamark.model.daos;

import java.io.InputStream;
import mx.com.televisa.landamark.model.cnn.ConnectionAs400;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.sql.Timestamp;

import java.util.Date;

import mx.com.televisa.landamark.model.types.LmkIntXmlFilesRowBean;

public class XmlFilesDao {
    public XmlFilesDao() {
        super();
    }
    
    /**
     * Insertar en la tabla de archivos fisicos de request y response xml
     * @autor Jorge Luis Bautista Santiago
     * @param LmkIntXmlFilesRowBean
     * @return boolean
     */
    public boolean insertLmkIntXmlFilesTab(LmkIntXmlFilesRowBean toXmlFile) {
        boolean    liResponse = false;
        Connection loCnn = new ConnectionAs400().getConnection();
        Integer    liIdXml = getMaxIdParadigm("RstXmlFiles") + 1;
        try {
            String lsSql = "INSERT INTO EVENTAS.LMK_INT_XML_FILES_TAB(ID_FILE_XML,\n" + 
            "                                            ID_REQUEST,\n" + 
            "                                            ID_SERVICE,\n" + 
            "                                            NOM_FILE,\n" + 
            "                                            IND_FILE_TYPE,\n" + 
            "                                            IND_SERVICE_TYPE,                                           \n" + 
            "                                            IND_ESTATUS,\n" + 
            "                                            FEC_CREATION_DATE,\n" + 
            "                                            NOM_USER_NAME,\n" + 
            "                                            NOM_PATH_FILE,\n" + 
            "                                            NUM_CREATED_BY,\n" + 
            "                                            FEC_LAST_UPDATE_DATE,\n" + 
            "                                            NUM_LAST_UPDATED_BY,\n" + 
            "                                            IND_FILE_STREAM\n" + 
            "                                           )\n" + 
            "                                   VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement loStmt = loCnn.prepareStatement(lsSql);
            loStmt.setInt(1, liIdXml);
            loStmt.setInt(2, toXmlFile.getLiIdRequest());
            loStmt.setInt(3, toXmlFile.getLiIdService());
            loStmt.setString(4, toXmlFile.getLsNomFile());
            loStmt.setString(5, toXmlFile.getLsIndFileType());
            loStmt.setString(6, toXmlFile.getLsIndServiceType());
            loStmt.setString(7, toXmlFile.getLsIndEstatus());
            loStmt.setTimestamp(8, getCurrentTimestamp());
            loStmt.setString(9, toXmlFile.getLsNomUserName());
            loStmt.setString(10, toXmlFile.getLsNomUserPathFile());
            loStmt.setInt(11, toXmlFile.getLiIdUser());
            loStmt.setTimestamp(12, getCurrentTimestamp());
            loStmt.setInt(13, toXmlFile.getLiIdUser());
            loStmt.setBinaryStream(14, toXmlFile.getLoIndFileStream());
            liResponse = loStmt.execute();
            
        } catch (SQLException loExSql) {
            System.out.println("ERROR XML_FILE: "+loExSql.getMessage());
        }
        finally{
            try {
                loCnn.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return liResponse;
    }
    
    /**
     * Obtiene identificador maximo ingresado en tabla
     * @autor Jorge Luis Bautista Santiago     
     * @param tsTable
     * @return Integer
     */
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
        if(tsTable.equalsIgnoreCase("RstProgramas")){
            lsTable = "EVENTAS.LMK_INT_RST_PROGRAMAS_TAB";
            lsField = "ID_REQUEST";
        }
        
        
        if(tsTable.equalsIgnoreCase("RstXmlFiles")){
            lsTable = "EVENTAS.LMK_INT_XML_FILES_TAB";
            lsField = "ID_FILE_XML";
        }
        if(tsTable.equalsIgnoreCase("RstRequest")){
            lsTable = "EVENTAS.LMK_INT_REQUESTS_TAB";
            lsField = "ID_REQUEST";
        }
        
        String lsQueryParadigm = getQueryMaxParadigm(lsTable,lsField);
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

    /**
     * Genera instruccion para obtener identificador maximo ingresado en tabla
     * @autor Jorge Luis Bautista Santiago
     * @param tsTable
     * @param tsField
     * @return Integer
     */
    public String getQueryMaxParadigm(String tsTable, String tsField) {
        String lsQuery = 
        "SELECT coalesce(MAX(" + tsField + "),0) \n" + 
        "  FROM " + tsTable;
        return lsQuery;
    }
    
    public Timestamp getCurrentTimestamp(){
        java.util.Date ltToday = new java.util.Date();
        return new java.sql.Timestamp(ltToday.getTime());
    }

    
}
