package mx.com.televisa.landamark.model.daos;

import java.io.IOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import mx.com.televisa.landamark.model.cnn.ConnectionAs400;
import mx.com.televisa.landamark.model.types.GzipDcdpTrxBean;
import mx.com.televisa.landamark.model.types.GzipDcdtTrxBean;
import mx.com.televisa.landamark.model.types.GzipDealTrxBean;
import mx.com.televisa.landamark.model.types.GzipDemdTrxBean;
import mx.com.televisa.landamark.model.types.GzipPredTrxBean;
import mx.com.televisa.landamark.model.types.LmkIntXmlFilesRowBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;

public class FileGzipDao {
    public FileGzipDao() {
        super();
    }
    
    /**
     * Obtiene los parametros generales en base al uso
     * @autor Jorge Luis Bautista Santiago
     * @param tsWhere
     * @return List
     */    
    public List<LmkIntXmlFilesRowBean> getLastFilesGzip(String tsWhere) {
        List<LmkIntXmlFilesRowBean> laList = 
            new ArrayList<LmkIntXmlFilesRowBean>();
        Connection  loCnn = new ConnectionAs400().getConnection();
        ResultSet   loRs = null;
        String      lsQueryParadigm = getQueryFileGzip(tsWhere);
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkIntXmlFilesRowBean loItem = new LmkIntXmlFilesRowBean();
                loItem.setLiIdFileXml(loRs.getInt("ID_FILE_XML"));
                loItem.setLiIdRequest(loRs.getInt("ID_REQUEST"));
                loItem.setLiIdService(loRs.getInt("ID_SERVICE"));
                loItem.setLsNomFile(loRs.getString("NOM_FILE"));
                loItem.setLsIndFileType(loRs.getString("IND_FILE_TYPE"));
                loItem.setLsIndServiceType(loRs.getString("IND_SERVICE_TYPE"));                
                loItem.setLsIndEstatus(loRs.getString("IND_ESTATUS"));
                
                loItem.setLsAttribute11(loRs.getString("ATTRIBUTE11"));
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
    public String getQueryFileGzip(String lsWhere){
        String lsQuery = 
            "SELECT ID_FILE_XML,\n" + 
            "       ID_REQUEST,\n" + 
            "       ID_SERVICE,\n" + 
            "       NOM_FILE,\n" + 
            "       IND_FILE_TYPE,\n" + 
            "       IND_SERVICE_TYPE,\n" + 
            "       NOM_USER_NAME,\n" + 
            "       NOM_PATH_FILE,\n" + 
            "       IND_ESTATUS,\n" + 
            "       ATTRIBUTE_CATEGORY,\n" + 
            "       ATTRIBUTE1,\n" + 
            "       ATTRIBUTE2,\n" + 
            "       ATTRIBUTE3,\n" + 
            "       ATTRIBUTE4,\n" + 
            "       ATTRIBUTE5,\n" + 
            "       ATTRIBUTE6, \n" + 
            "       ATTRIBUTE7, \n" + 
            "       ATTRIBUTE8, \n" + 
            "       ATTRIBUTE9, \n" + 
            "       ATTRIBUTE10,\n" + 
            "       ATTRIBUTE11,\n" + 
            "       ATTRIBUTE12,\n" + 
            "       ATTRIBUTE13,\n" + 
            "       ATTRIBUTE14,\n" + 
            "       ATTRIBUTE15 \n" + 
            " FROM EVENTAS.LMK_INT_XML_FILES_TAB ";
        lsQuery += " WHERE 1 = 1 " + lsWhere;
        lsQuery += " ORDER BY FEC_CREATION_DATE DESC";
        //System.out.println(lsQuery);
        return lsQuery;
    }
    
    /**
     * Se inserta registro en la tabla definida como tipo Dcdp
     * @autor Jorge Luis Bautista Santiago
     * @param tsTableName
     * @param toBean
     * @return ResponseUpdDao
     */   
    public ResponseUpdDao insertDcdpTrx(String tsTableName, 
                                        GzipDcdpTrxBean toBean
                                       ){
        ResponseUpdDao loRes = new ResponseUpdDao();
        Integer    liReturn = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = getQueryInsertDcdpTrx(tsTableName, toBean);
        
        try {
            Statement loStmt = loCnn.createStatement();
            liReturn = loStmt.executeUpdate(lsQueryParadigm);
            loRes.setLiAffected(liReturn);
            loRes.setLsResponse("OK");
            loRes.setLsMessage("OK");
        } catch (SQLException loExSql) {
            loRes.setLiAffected(0);
            loRes.setLsResponse("ERROR");
            loRes.setLsMessage(loExSql.getMessage());
            loExSql.printStackTrace();
        }
        finally{
            try {
                loCnn.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return loRes;
    }
    
     /**
      * Crea instruccio para insertar registro en la tabla definida como tipo Dcdp
      * @autor Jorge Luis Bautista Santiago
      * @param tsTableName
      * @param toBean
      * @return String
      */   
    public String getQueryInsertDcdpTrx(String tsTableName, 
                                        GzipDcdpTrxBean toBean){
        String lsQuery = 
            "INSERT INTO " + tsTableName + "(";
        if(toBean.getLiCampNo()!= null){ lsQuery += "  CAMP_NO,\n";}
        if(toBean.getLiSareNo()!= null){ lsQuery += "  SARE_NO,\n";}
        if(toBean.getLiDcdpNo()!= null){ lsQuery += "  DCDP_NO,\n";}
        if(toBean.getLdRevenuePerc()!= null){ lsQuery += "  REVENUE_PERC,\n";}
        if(toBean.getLdRtgsPerc()!= null){ lsQuery += "  RTGS_PERC,\n";}
        if(toBean.getLiDemdDemoNo()!= null){ lsQuery += "  DEMD_DEMO_NO,\n";}
        if(toBean.getLdCpp()!= null){ lsQuery += "  CPP,\n";}
        if(toBean.getLiNoOfSpots()!= null){ lsQuery += "  NO_OF_SPOTS,\n";}
        if(toBean.getLiMasterDcdpNo()!= null){ lsQuery += "  MASTER_DCDP_NO,\n";}
        if(toBean.getLiDcdpNameId()!= null){ lsQuery += "  DCDP_NAME_ID,\n";}
        if(toBean.getLiDealNo()!= null){ lsQuery += "  DEAL_NO\n";}
            lsQuery += "                            )\n" + 
            "                     VALUES (";
        if(toBean.getLiCampNo()!= null){ lsQuery += " "+toBean.getLiCampNo()+",--CAMP_NO\n";}
        if(toBean.getLiSareNo()!= null){ lsQuery += " "+toBean.getLiSareNo()+",--SARE_NO\n";}
        if(toBean.getLiDcdpNo()!= null){ lsQuery += " "+toBean.getLiDcdpNo()+",--DCDP_NO\n";}
        if(toBean.getLdRevenuePerc()!= null){ lsQuery += " "+toBean.getLdRevenuePerc()+",--REVENUE_PERC\n";}
        if(toBean.getLdRtgsPerc()!= null){ lsQuery += " "+toBean.getLdRtgsPerc()+",--RTGS_PERC\n";}
        if(toBean.getLiDemdDemoNo()!= null){ lsQuery += " "+toBean.getLiDemdDemoNo()+",--DEMD_DEMO_NO\n";}
        if(toBean.getLdCpp()!= null){ lsQuery += " "+toBean.getLdCpp()+",--CPP\n";}
        if(toBean.getLiNoOfSpots()!= null){ lsQuery += " "+toBean.getLiNoOfSpots()+",--NO_OF_SPOTS\n";}
        if(toBean.getLiMasterDcdpNo()!= null){ lsQuery += " "+toBean.getLiMasterDcdpNo()+",--MASTER_DCDP_NO\n";}
        if(toBean.getLiDcdpNameId()!= null){ lsQuery += " "+toBean.getLiDcdpNameId()+",--DCDP_NAME_ID\n";}
        if(toBean.getLiDealNo()!= null){ lsQuery += " "+toBean.getLiDealNo()+"--DEAL_NO\n";}
            lsQuery += "                            )";
        return lsQuery;
    }
    
    
    /**
     * Se inserta registro en la tabla definida como tipo Dcdt
     * @autor Jorge Luis Bautista Santiago
     * @param tsTableName
     * @param toBean
     * @return ResponseUpdDao
     */   
    public ResponseUpdDao insertDcdtTrx(String tsTableName, 
                                        GzipDcdtTrxBean toBean
                                       ){
        ResponseUpdDao loRes = new ResponseUpdDao();
        Integer    liReturn = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = getQueryInsertDcdtTrx(tsTableName, toBean);
        
        try {
            Statement loStmt = loCnn.createStatement();
            liReturn = loStmt.executeUpdate(lsQueryParadigm);
            loRes.setLiAffected(liReturn);
            loRes.setLsResponse("OK");
            loRes.setLsMessage("OK");
        } catch (SQLException loExSql) {
            loRes.setLiAffected(0);
            loRes.setLsResponse("ERROR");
            loRes.setLsMessage(loExSql.getMessage());
            loExSql.printStackTrace();
        }
        finally{
            try {
                loCnn.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return loRes;
    }
    
     /**
      * Crea instruccio para insertar registro en la tabla definida como tipo Dcdt
      * @autor Jorge Luis Bautista Santiago
      * @param tsTableName
      * @param toBean
      * @return String
      */   
    public String getQueryInsertDcdtTrx(String tsTableName, 
                                        GzipDcdtTrxBean toBean){
        String lsQuery = 
            "INSERT INTO " + tsTableName + "(";
        if(toBean.getLiDealNo()!= null){ lsQuery += "  DEAL_NO,\n";}
        if(toBean.getLiDcdpNo()!= null){ lsQuery += "  DCDP_NO,\n";}
        if(toBean.getLiDcdtSttDay()!= null){ lsQuery += "  DCDT_STT_DAY,\n";}
        if(toBean.getLiDcdtSttTime()!= null){ lsQuery += "  DCDT_STT_TIME,\n";}
        if(toBean.getLiEndDay()!= null){ lsQuery += "  END_DAY,\n";}
        if(toBean.getLiEndTime()!= null){ lsQuery += "  END_TIME,\n";}
        if(toBean.getLiNoOfChanges()!= null){ lsQuery += "  NO_OF_CHANGES,\n";}
        if(toBean.getLiSareNo()!= null){ lsQuery += "  SARE_NO,\n";}
        if(toBean.getLiCampNo()!= null){ lsQuery += "  CAMP_NO\n";}
        lsQuery += 
            "       )\n" + 
            "       VALUES(\n";
        if(toBean.getLiDealNo()!= null){ lsQuery += " "+toBean.getLiDealNo()+",--DEAL_NO\n";}
        if(toBean.getLiDcdpNo()!= null){ lsQuery += " "+toBean.getLiDcdpNo()+",--DCDP_NO\n";}
        if(toBean.getLiDcdtSttDay()!= null){ lsQuery += " "+toBean.getLiDcdtSttDay()+",--DCDT_STT_DAY\n";}
        if(toBean.getLiDcdtSttTime()!= null){ lsQuery += " "+toBean.getLiDcdtSttTime()+",--DCDT_STT_TIME\n";}
        if(toBean.getLiEndDay()!= null){ lsQuery += " "+toBean.getLiEndDay()+",--END_DAY\n";}
        if(toBean.getLiEndTime()!= null){ lsQuery += " "+toBean.getLiEndTime()+",--END_TIME\n";}
        if(toBean.getLiNoOfChanges()!= null){ lsQuery += " "+toBean.getLiNoOfChanges()+",--NO_OF_CHANGES\n";}
        if(toBean.getLiSareNo()!= null){ lsQuery += " "+toBean.getLiSareNo()+",--SARE_NO\n";}
        if(toBean.getLiCampNo()!= null){ lsQuery += " "+toBean.getLiCampNo()+"--CAMP_NO\n";}
        lsQuery += 
            "       )";
        return lsQuery;
    }
    
    
    
    /**
     * Se inserta registro en la tabla definida como tipo Deal
     * @autor Jorge Luis Bautista Santiago
     * @param tsTableName
     * @param toBean
     * @return ResponseUpdDao
     */   
    public ResponseUpdDao insertDealTrx(String tsTableName, 
                                        GzipDealTrxBean toBean
                                       ){
        ResponseUpdDao loRes = new ResponseUpdDao();
        Integer    liReturn = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = getQueryInsertDealTrx(tsTableName, toBean);
        
        try {
            Statement loStmt = loCnn.createStatement();
            liReturn = loStmt.executeUpdate(lsQueryParadigm);
            loRes.setLiAffected(liReturn);
            loRes.setLsResponse("OK");
            loRes.setLsMessage("OK");
        } catch (SQLException loExSql) {
            loRes.setLiAffected(0);
            loRes.setLsResponse("ERROR");
            loRes.setLsMessage(loExSql.getMessage());
            loExSql.printStackTrace();
        }
        finally{
            try {
                loCnn.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return loRes;
    }
    
    
    
    
    /**
     * Crea instruccio para insertar registro en la tabla definida como tipo Deal
     * @autor Jorge Luis Bautista Santiago
     * @param tsTableName
     * @param toBean
     * @return String
     */   
    public String getQueryInsertDealTrx(String tsTableName,
                                       GzipDealTrxBean toBean){
       String lsQuery = 
           "INSERT INTO " + tsTableName + " (\n";
        if(toBean.getLiMasterDeal()!= null){ lsQuery += "  MASTER_DEAL,\n";}
        if(toBean.getLsClntCode()!= null){ lsQuery += "  CLNT_CODE,\n";}
        if(toBean.getLsStatus()!= null){ lsQuery += "  STATUS,\n";}
        if(toBean.getLsSttDate()!= null){ lsQuery += "  STT_DATE,\n";}
        if(toBean.getLsEndDate()!= null){ lsQuery += "  END_DATE,\n";}
        if(toBean.getLiDealNo()!= null){ lsQuery += "  DEAL_NO\n";}
       lsQuery +=
           "       )\n" + 
           "VALUES (\n";
        if(toBean.getLiMasterDeal()!= null){ lsQuery += " "+toBean.getLiMasterDeal()+",--MASTER_DEAL\n";}
        if(toBean.getLsClntCode()!= null){ lsQuery += " '"+toBean.getLsClntCode()+"',--CLNT_CODE\n";}
        if(toBean.getLsStatus()!= null){ lsQuery += " '"+toBean.getLsStatus()+"',--STATUS\n";}
        if(toBean.getLsSttDate()!= null){ lsQuery += " '"+toBean.getLsSttDate()+"',--STT_DATE\n";}
        if(toBean.getLsEndDate()!= null){ lsQuery += " '"+toBean.getLsEndDate()+"',--END_DATE\n";}
        if(toBean.getLiDealNo()!= null){ lsQuery += " "+toBean.getLiDealNo()+"--DEAL_NO\n";}
        lsQuery +=
           "       )";
       return lsQuery;
    }
    
    
    /**
     * Se inserta registro en la tabla definida como tipo Demd
     * @autor Jorge Luis Bautista Santiago
     * @param tsTableName
     * @param toBean
     * @return ResponseUpdDao
     */   
    public ResponseUpdDao insertDemdTrx(String tsTableName, 
                                        GzipDemdTrxBean toBean
                                       ){
        ResponseUpdDao loRes = new ResponseUpdDao();
        Integer    liReturn = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = getQueryInsertDemdTrx(tsTableName, toBean);
        
        try {
            Statement loStmt = loCnn.createStatement();
            liReturn = loStmt.executeUpdate(lsQueryParadigm);
            loRes.setLiAffected(liReturn);
            loRes.setLsResponse("OK");
            loRes.setLsMessage("OK");
        } catch (SQLException loExSql) {
            loRes.setLiAffected(0);
            loRes.setLsResponse("ERROR");
            loRes.setLsMessage(loExSql.getMessage());
            loExSql.printStackTrace();
        }
        finally{
            try {
                loCnn.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return loRes;
    }
    
    
    /**
     * Crea instruccio para insertar registro en la tabla definida como tipo Demd
     * @autor Jorge Luis Bautista Santiago
     * @param tsTableName
     * @param toBean
     * @return String
     */   
    public String getQueryInsertDemdTrx(String tsTableName,
                                       GzipDemdTrxBean toBean){
       String lsQuery = 
           "INSERT INTO " + tsTableName + " (\n";
        if(toBean.getLiDemoNo()!= null){ lsQuery += "  DEMO_NO,\n";}
        if(toBean.getLdRevenuePerc()!= null){ lsQuery += "  REVENUE_PERC,\n";}
        if(toBean.getLiSareNo()!= null){ lsQuery += "  SARE_NO,\n";}
        if(toBean.getLdCpp()!= null){ lsQuery += "  CPP,\n";}
        if(toBean.getLiDealNo()!= null){ lsQuery += "  DEAL_NO\n";}
       lsQuery += 
           "       )\n" + 
           "VALUES (\n";
        if(toBean.getLiDemoNo()!= null){ lsQuery += " "+toBean.getLiDemoNo()+",--DEMO_NO\n";}
        if(toBean.getLdRevenuePerc()!= null){ lsQuery += " "+toBean.getLdRevenuePerc()+",--REVENUE_PERC\n";}
        if(toBean.getLiSareNo()!= null){ lsQuery += " "+toBean.getLiSareNo()+",--SARE_NO\n";}
        if(toBean.getLdCpp()!= null){ lsQuery += " "+toBean.getLdCpp()+",--CPP\n";}
        if(toBean.getLiDealNo()!= null){ lsQuery += " "+toBean.getLiDealNo()+"--DEAL_NO\n";}
        lsQuery += 
           "       )";
       return lsQuery;
    }
    
    /**
     * Se inserta registro en la tabla definida como tipo Demd
     * @autor Jorge Luis Bautista Santiago
     * @param tsTableName
     * @param toBean
     * @return ResponseUpdDao
     */   
    public ResponseUpdDao deleteTrx(String tsTableName){
        ResponseUpdDao loRes = new ResponseUpdDao();
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = 
            "DELETE \n" + 
            "FROM "+tsTableName;
        try {
            Statement loStmt = loCnn.createStatement();
            loStmt.executeUpdate(lsQueryParadigm);
            loRes.setLsResponse("OK");
            loRes.setLsMessage("OK");
        } catch (SQLException loExSql) {
            loRes.setLiAffected(0);
            loRes.setLsResponse("ERROR");
            loRes.setLsMessage(loExSql.getMessage());
            loExSql.printStackTrace();
        }
        finally{
            try {
                loCnn.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return loRes;
    }
    
    /**
     * Ejecuta procedimiento en base de datos para procesar archivos gzip
     * @autor Jorge Luis Bautista Santiago
     * @param toOrdHeaderModulosTab
     * @return ResponseUpdDao
     */
    public ResponseUpdDao callProcedureLoadTable(String tsTableName) throws SQLException {
        ResponseUpdDao    loResponseUpdDao = new ResponseUpdDao();
        Connection        loCnn = new ConnectionAs400().getConnection();
        CallableStatement loCallStmt = null;
        System.out.println("Parametros(callProcedureLoadTable).........");
        System.out.println("tsTableName: ["+tsTableName+"]");
        String            lsQueryParadigm = "call EVENTAS.LMK_CARGA_TABLAS(?)";
        try {
            loCallStmt = loCnn.prepareCall(lsQueryParadigm);
            loCallStmt.setString(1, tsTableName);
            loCallStmt.execute();
            loResponseUpdDao.setLsResponse("OK");
            loResponseUpdDao.setLiAffected(0);
            loResponseUpdDao.setLsMessage("Success");
            //loResponseUpdDao.setLsMessage("Deshabilitado");
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
     * Insertar en la tabla de archivos fisicos de request y response xml
     * @autor Jorge Luis Bautista Santiago
     * @param LmkIntXmlFilesRowBean
     * @return boolean
     */
    public ResponseUpdDao insertLmkIntXmlFilesTab(LmkIntXmlFilesRowBean toXmlFile) {
        ResponseUpdDao    loResponse = new ResponseUpdDao();
       /* try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {;
        }
    */
       
        Connection loCnn = new ConnectionAs400().getConnection();
        Integer    liIdXml = getMaxIdParadigm("RstXmlFiles") + 1;
        try {
            String lsSql = "INSERT INTO EVENTAS.LMK_INT_XML_FILES_TAB(ID_FILE_XML,\n" + 
            "                                            ID_REQUEST,\n" + 
            "                                            ID_SERVICE,\n" + 
            "                                            NOM_FILE,\n" + 
            "                                            IND_FILE_TYPE,\n" + 
            "                                            IND_SERVICE_TYPE,\n" + 
            "                                            IND_ESTATUS,\n" + 
            "                                            FEC_CREATION_DATE,\n" + 
            "                                            NOM_USER_NAME,\n" + 
            "                                            NOM_PATH_FILE,\n" + 
            "                                            NUM_CREATED_BY,\n" + 
            "                                            FEC_LAST_UPDATE_DATE,\n" + 
            "                                            NUM_LAST_UPDATED_BY,\n" + 
            "                                            ATTRIBUTE1,\n" + 
            "                                            ATTRIBUTE2,\n" + 
            "                                            ATTRIBUTE11,\n" + 
            "                                            ATTRIBUTE12,\n" + 
            "                                            IND_FILE_STREAM\n" + 
            "                                           )\n" + 
            "                                   VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            loStmt.setString(14, toXmlFile.getLsAttribute1());
            loStmt.setString(15, toXmlFile.getLsAttribute2());
            loStmt.setString(16, toXmlFile.getLsAttribute11());
            loStmt.setString(17, toXmlFile.getLsAttribute12());
            loStmt.setBinaryStream(18, toXmlFile.getLoIndFileStream());
            loStmt.execute();
            loResponse.setLsResponse("OK");
            loResponse.setLsMessage("OK");
            loResponse.setLiAffected(liIdXml);
        } catch (SQLException loExSql) {
            System.out.println("ERROR XML_FILE: "+loExSql.getMessage());
            loResponse.setLsResponse("ERROR");
            loResponse.setLsMessage(loExSql.getMessage());
            loResponse.setLiAffected(0);
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
    
    public Timestamp getCurrentTimestamp(){
        java.util.Date ltToday = new java.util.Date();
        return new java.sql.Timestamp(ltToday.getTime());
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
     * Se inserta registro en la tabla definida como tipo Demd
     * @autor Jorge Luis Bautista Santiago
     * @param tsTableName
     * @param toBean
     * @return ResponseUpdDao
     */   
    public ResponseUpdDao insertPredTrx(String tsTableName, 
                                        GzipPredTrxBean toBean
                                       ){
        ResponseUpdDao loRes = new ResponseUpdDao();
        Integer    liReturn = 0;
        Connection loCnn = new ConnectionAs400().getConnection();
        String     lsQueryParadigm = getQueryInsertPredTrx(tsTableName, toBean);
        
        try {
            Statement loStmt = loCnn.createStatement();
            liReturn = loStmt.executeUpdate(lsQueryParadigm);
            loRes.setLiAffected(liReturn);
            loRes.setLsResponse("OK");
            loRes.setLsMessage("OK");
        } catch (SQLException loExSql) {
            loRes.setLiAffected(0);
            loRes.setLsResponse("ERROR");
            loRes.setLsMessage(loExSql.getMessage());
            loExSql.printStackTrace();
        }
        finally{
            try {
                loCnn.close();
            } catch (SQLException loEx) {
                loEx.printStackTrace();
            }
        }
        return loRes;
    }
    
    
    /**
     * Crea instruccio para insertar registro en la tabla definida como tipo Demd
     * @autor Jorge Luis Bautista Santiago
     * @param tsTableName
     * @param toBean
     * @return String
     */   
    public String getQueryInsertPredTrx(String tsTableName,
                                       GzipPredTrxBean toBean){
       String lsQuery = 
           "INSERT INTO "+tsTableName+" (";
        if(toBean.getLsDemoNo()!= null){ lsQuery += "  DEMO_NO,\n";}
        if(toBean.getLsPredSareNo()!= null){ lsQuery += "  PRED_DARE_NO,\n";}
        if(toBean.getLsBrekSchedDate()!= null){ lsQuery += "  BREAK_SCHED_DATE,\n";}
        if(toBean.getLsBreakNo()!= null){ lsQuery += "  BREAK_NO,\n";}
        if(toBean.getLsNoOfRtgs()!= null){ lsQuery += "  NO_OF_RTGS,\n";}
        if(toBean.getLsSareNo()!= null){ lsQuery += "  SARE_NO\n";}
        lsQuery += 
           "                             )\n" + 
           "                      VALUES (";
        if(toBean.getLsDemoNo()!= null){ lsQuery += " "+toBean.getLsDemoNo()+",--DEMO_NO\n";}
        if(toBean.getLsPredSareNo()!= null){ lsQuery += " "+toBean.getLsPredSareNo()+",--PRED_DARE_NO\n";}
        if(toBean.getLsBrekSchedDate()!= null){ lsQuery += " '"+toBean.getLsBrekSchedDate()+"',--BREAK_SCHED_DATE\n";}
        if(toBean.getLsBreakNo()!= null){ lsQuery += " "+toBean.getLsBreakNo()+",--BREAK_NO\n";}
        if(toBean.getLsNoOfRtgs()!= null){ lsQuery += " "+toBean.getLsNoOfRtgs()+",--NO_OF_RTGS\n";}
        if(toBean.getLsSareNo()!= null){ lsQuery += " "+toBean.getLsSareNo()+"--SARE_NO\n";}
        lsQuery += 
           "                             )";
       return lsQuery;
    }
    
    
}
