/**
* Project: Integraton Paradigm - Landmark
*
* File: NotificationsDao.java
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
import mx.com.televisa.landamark.model.types.LmkIntMappingCatRowBean;
import mx.com.televisa.landamark.model.types.LmkIntNotificationsRowBean;

/** Clase que accede a base de datos para metodos de la Configuracion de Notificaciones
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
 */
public class NotificationsDao {
    public NotificationsDao() {
        super();
    }
    
    /**
     * Obtiene la lista de valores mapeados configurados
     * @autor Jorge Luis Bautista Santiago
     * @param tsWhere
     * @return List
     */
    public List<LmkIntNotificationsRowBean> getLmkIntServicesParams(String tsWhere){
        List<LmkIntNotificationsRowBean> laReturn = 
            new ArrayList<LmkIntNotificationsRowBean>();
        Connection              loCnn = new ConnectionAs400().getConnection();
        ResultSet               loRs = null;
        String                  lsQueryParadigm = getQueryLmkIntNotifications(tsWhere);        
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkIntNotificationsRowBean loItem = new LmkIntNotificationsRowBean();
                loItem.setLiIdNotification(loRs.getInt("ID_NOTIFICATION"));
                loItem.setLiIdService(loRs.getInt("ID_SERVICE"));
                loItem.setLiIndProcess(loRs.getInt("IND_PROCESS"));
                loItem.setLsIndUsersGroup(loRs.getString("IND_USERS_GROUP"));
                loItem.setLsIndSubject(loRs.getString("IND_SUBJECT"));
                loItem.setLsIndMessage(loRs.getString("IND_MESSAGE"));
                loItem.setLsIndEstatus(loRs.getString("IND_ESTATUS"));
                
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
    
    public String getQueryLmkIntNotifications(String tsWhere){
        String lsQuery = 
            "SELECT ID_NOTIFICATION,\n" + 
            "       ID_SERVICE,\n" + 
            "       IND_PROCESS,\n" + 
            "       IND_USERS_GROUP,\n" + 
            "       IND_SUBJECT,\n" + 
            "       IND_MESSAGE,\n" + 
            "       IND_ESTATUS,\n" + 
            "       ATTRIBUTE_CATEGORY,\n" + 
            "       ATTRIBUTE1,\n" + 
            "       ATTRIBUTE2,\n" + 
            "       ATTRIBUTE3\n" + 
            "  FROM EVENTAS.LMK_INT_NOTIFICATIONS_TAB\n";
        if(tsWhere != null){
            lsQuery += " WHERE 1 = 1 AND " + tsWhere;
        }
        
        return lsQuery;
        
    }
}
