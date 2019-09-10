package mx.com.televisa.landamark.model.list;

import mx.com.televisa.landamark.model.AppModuleImpl;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jul 18 20:38:42 CDT 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class LmkIntListChannelpAllVwViewImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public LmkIntListChannelpAllVwViewImpl() {
    }
    
    @Override
    protected void executeQueryForCollection(Object toQc, 
                                             Object[] taParams, 
                                             int tiNumUserParams) {
        
        String lsWhere = " 1 = 1 ";
        if(getWhereClause() == null){
            /*
            UserOperationList loUserOperationList = (UserOperationList)resolveExpression("#{UserOperationList}");
            for(String lsOperation : loUserOperationList.getLaOpertations()){
                System.out.println("lsOperation: "+lsOperation);            
            }*/
            AppModuleImpl loAm = new AppModuleImpl();
            String              lsIdService = 
                loAm.getValueSessionFromAttribute("idServicePrecios");
            String              lsListChannels = 
                loAm.getValueSessionFromAttribute("listChannelsPrecios") == null ? "" : 
                loAm.getValueSessionFromAttribute("listChannelsPrecios");
            
            //System.out.println("lsIdService(Precios): "+lsIdService);
            //System.out.println("lsListChannels(Precios): "+lsListChannels);
            if(Integer.parseInt(lsIdService) > 0 ){
                lsWhere += " AND ID_SERVICE = " + lsIdService;
                setWhereClause(lsWhere);
            }else{
                lsWhere += " AND 1 = 2 ";
                lsWhere += " ) ";
                lsWhere += " UNION ";
                lsWhere += " ( ";
                lsWhere += " SELECT DISTINCT rand(100) as ROWID,\n" + 
                "       -1                   ID_SERVICE,\n" + 
                "       'ACPRECIOS'                NOM_SERVICE,\n" + 
                "       'Actualizacion de Precios' IND_DESC_SERVICE,\n" + 
                "       CAN.ID_PARAMETER,\n" + 
                "       CAN.NOM_PARAMETER,\n" + 
                "       CAN.IND_DESC_PARAMETER,\n" + 
                "       'NA'                 IND_USED_BY,\n" + 
                "       CAN.IND_VALUE_PARAMETER IND_VALUE_PARAMETER,\n" + 
                "       1                    IND_ESTATUS,\n" + 
                "       CURRENT_TIMESTAMP         FEC_CREATION_DATE,\n" + 
                "       'false'              VAL_MOSTRAR,\n" + 
                "       'abcedario'          VAL_VALUE,\n" + 
                "       'false'              SELECTED\n" + 
                "  FROM EVENTAS.LMK_INT_CONFIG_PARAM_TAB CAN\n" + 
                " WHERE CAN.IND_USED_BY = 'CHANNEL_PARAMETER'";
            }
            
            // Mostrar solo los canales de fuerza de ventas configurado
            if(lsListChannels.length() > 0){
                lsWhere += " AND IND_DESC_PARAMETER IN ("+lsListChannels+") ";   
            }else{
                lsWhere += " AND IND_DESC_PARAMETER IN ('NA') ";   
            }
            
        }
        else{
            lsWhere = getWhereClause();
        }
        //System.out.println(getQuery());
        setOrderByClause("IND_DESC_SERVICE, NOM_SERVICE");
        setWhereClause(lsWhere);
        super.executeQueryForCollection(toQc, taParams, tiNumUserParams);
    }

}

