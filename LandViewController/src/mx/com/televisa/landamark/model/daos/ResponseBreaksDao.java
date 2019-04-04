/**
* Project: Integraton Paradigm - Landmark
*
* File: SftpManagment.java
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

import java.util.ArrayList;
import java.util.List;

import mx.com.televisa.landamark.model.cnn.ConnectionAs400;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntXmlFilesRowBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;

/** Clase bean para retorno de metodos
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
 */
public class ResponseBreaksDao {
    public ResponseBreaksDao() {
        super();
    }
    
    /**
     * Obtiene los parametros generales en base al uso
     * @autor Jorge Luis Bautista Santiago
     * @param tsWhere
     * @return List
     */    
    public List<LmkIntXmlFilesRowBean> getAllFilesPending(String tsWhere) {
        List<LmkIntXmlFilesRowBean> laList = 
            new ArrayList<LmkIntXmlFilesRowBean>();
        Connection  loCnn = new ConnectionAs400().getConnection();
        ResultSet   loRs = null;
        String      lsQueryParadigm = getQueryAllFilesPending(tsWhere);
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
    public String getQueryAllFilesPending(String lsWhere){
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
        //System.out.println(lsQuery);
        return lsQuery;
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
    
    
}
