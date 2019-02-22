package mx.com.televisa.landamark.model.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import mx.com.televisa.landamark.model.cnn.ConnectionAs400;
import mx.com.televisa.landamark.model.types.LmkIntMappingCatRowBean;

public class MappingCatDao {
    /**
     * Obtiene la lista de valores mapeados configurados
     * @autor Jorge Luis Bautista Santiago
     * @param tsWhere
     * @return List
     */
    public List<LmkIntMappingCatRowBean> getLmkIntServicesParams(String tsWhere){
        List<LmkIntMappingCatRowBean> laReturn = 
            new ArrayList<LmkIntMappingCatRowBean>();
        Connection              loCnn = new ConnectionAs400().getConnection();
        ResultSet               loRs = null;
        String                  lsQueryParadigm = getQueryLmkIntServicesParams(tsWhere);        
        try {
            Statement loStmt = loCnn.createStatement();
            loRs = loStmt.executeQuery(lsQueryParadigm);  
            while(loRs.next()){
                LmkIntMappingCatRowBean loItem = new LmkIntMappingCatRowBean();
                loItem.setLiIdMapping(loRs.getInt("ID_MAPPING"));
                loItem.setLsNomRelation(loRs.getString("NOM_RELATION"));
                loItem.setLsNomOrigin(loRs.getString("NOM_ORIGIN"));
                loItem.setLsNomDestiny(loRs.getString("NOM_DESTINY"));
                
                loItem.setLsValValueRelation(loRs.getString("VAL_VALUE_RELATION"));
                loItem.setLsValValueOrigin(loRs.getString("VAL_VALUE_ORIGIN"));
                loItem.setLsValValueDestiny(loRs.getString("VAL_VALUE_DESTINY"));
                
                loItem.setLsIndSysSystem(loRs.getString("IND_SYS_SYSTEM"));
                loItem.setLsIndSysOrigin(loRs.getString("IND_SYS_ORIGIN"));
                loItem.setLsIndSysDestiny(loRs.getString("IND_SYS_DESTINY"));
                
                loItem.setLsIndUsedBy(loRs.getString("IND_USED_BY"));
                loItem.setLsIndDescription(loRs.getString("IND_DESCRIPTION"));
                loItem.setLiIdMappingRel(loRs.getInt("ID_MAPPING_REL"));
                
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
    
    
     /**
      * Crea instruccion para Obtener la lista de valores mapeados configurados
      * @autor Jorge Luis Bautista Santiago
      * @param tsWhere
      * @return String
      */
    public String getQueryLmkIntServicesParams(String tsWhere){
        String lsQuery = "SELECT ID_MAPPING,\n" + 
        "	   NOM_RELATION,\n" + 
        "  	   NOM_ORIGIN,\n" + 
        "	   NOM_DESTINY,\n" + 
        "	   VAL_VALUE_RELATION,\n" + 
        "	   VAL_VALUE_ORIGIN,\n" + 
        "	   VAL_VALUE_DESTINY,\n" + 
        "	   IND_SYS_SYSTEM,\n" + 
        "	   IND_SYS_ORIGIN,\n" + 
        "	   IND_SYS_DESTINY,\n" + 
        "	   IND_USED_BY,\n" + 
        "	   IND_DESCRIPTION,\n" + 
        "	   ID_MAPPING_REL,\n" + 
        "	   IND_ESTATUS\n" + 
        "  FROM EVENTAS.LMK_INT_MAPPING_CAT_TAB\n" + 
        " WHERE 1 = 1";
        
        if(tsWhere != null){
            lsQuery += " AND ";
            lsQuery += tsWhere;
        }
        
        return lsQuery;
    }
}
