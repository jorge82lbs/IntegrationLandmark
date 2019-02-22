/**
* Project: Paradigm - Landamrk Integration
*
* File: MappingBean.java
*
* Created on: Enero 26, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/

package mx.com.televisa.landamark.view.beans;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.model.SelectItem;

import mx.com.televisa.landamark.model.AppModuleImpl;
import mx.com.televisa.landamark.model.types.LmkIntConfigParamRowBean;
import mx.com.televisa.landamark.model.types.LmkIntMappingCatRowBean;
import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.view.types.SelectOneItemBean;

import mx.com.televisa.landamark.util.UtilFaces;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelLabelAndMessage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.client.Configuration;

/** Esta clase es un bean que enlaza la pantalla de mapeo<br/><br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Febrero 14, 2019, 12:00 pm
 */
public class MappingBean {
    private RichInputText poFilterIdMapping;    
    private RichInputText poFilterNomRelation;
    private RichInputText poFilterValueRelation;
    private RichInputText poFilterSystemRelation;
    private RichInputText poFilterNomOrigin;
    private RichInputText poFilterValueOrigin;
    private RichInputText poFilterSystemOrigin;
    private RichInputText poFilterNomDestiny;
    private RichInputText poFilterValueDestiny;
    private RichInputText poFilterSystemDestiny;
    private RichInputText poFilterUsedBy;
    private RichInputText poFilterDescription;
    private RichSelectOneChoice poFilterSystemOriginSel;
    private RichSelectOneChoice poFilterSystemDestinySel;
    private RichInputText poSaveNomRelation;
    private RichInputText poSaveValueRelation;
    private RichInputText poSaveSystemRelation;
    private RichInputText poSaveNomOrigin;
    private RichInputText poSaveValueOrigin;
    private RichSelectOneChoice poSaveSystemOriginSel;
    private RichInputText poSaveSystemOrigin;
    private RichInputText poSaveNomDestiny;
    private RichInputText poSaveValueDestiny;
    private RichSelectBooleanCheckbox poPopSaveStatus;
    private RichPopup poPopupSave;
    private RichPopup poPopupDelete;
    private RichPanelLabelAndMessage poDeleteMsgLbl;
    private RichOutputText poDeleteIdBinding;
    private RichPopup poPopupUpdate;
    private RichInputText poPopUpIdMapping;
    private RichInputText poUpdNomRelation;
    private RichInputText poUpdValueRelation;
    private RichInputText poUpdSystemRelation;
    private RichInputText poUpdNomOrigin;
    private RichInputText poUpdValueOrigin;
    private RichSelectOneChoice poUpdSystemOriginSel;
    private RichInputText poUpdSystemOrigin;
    private RichInputText poUpdValueDestiny;
    private RichInputText poUpdNomDestiny;
    private RichSelectBooleanCheckbox poPopUpdStatus;
    private RichTable poTblMain;

    String                            gsEntityView = "LmkIntMappingCatVwView1";
    String                            gsEntityIterator = "LmkIntMappingCatVwView1Iterator";
    String                            gsAppModule = "AppModuleDataControl";
    String                            gsAmDef = "mx.com.televisa.landamark.model.AppModuleImpl";
    String                            gsConfig = "AppModuleLocal";
    private RichSelectOneChoice poSaveSystemDestinySel;
    private RichSelectOneChoice poUpdSystemDestinySel;
    private RichInputText poSaveUsedBy;
    private RichInputText poSaveDescription;
    private RichInputText poUpdUsedBy;
    private RichInputText poUpdDescription;


    /**
     * Reinicia los valores de busqueda
     * @autor Jorge Luis Bautista Santiago
     * @param toActionEvent
     * @return void
     */
    public void resetFilterValues(ActionEvent toActionEvent) {
        toActionEvent.getSource();        
        getPoFilterIdMapping().setValue(null);
        getPoFilterNomDestiny().setValue(null);
        getPoFilterNomOrigin().setValue(null);
        getPoFilterSystemDestiny().setValue(null);
        getPoFilterSystemDestinySel().setValue(null);
        getPoFilterSystemOrigin().setValue(null);
        getPoFilterSystemOriginSel().setValue(null);
        getPoFilterValueDestiny().setValue(null);
        getPoFilterValueOrigin().setValue(null);
        getPoFilterValueRelation().setValue(null);
        getPoFilterUsedBy().setValue(null);
        getPoFilterDescription().setValue(null);
    }
    
    /**
     * Actualiza en base de datos el servicio seleccionado
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */  
    public String updateAction() {
        String                   lsFieldErrorRq = "";
        boolean                  lbProcess = true;
        
        String                   lsIdMapping = 
            getPoPopUpIdMapping().getValue() == null ? "" : 
            getPoPopUpIdMapping().getValue().toString();        
        String                   lsNomRelation = 
            getPoUpdNomRelation().getValue() == null ? "" : 
            getPoUpdNomRelation().getValue().toString();
        String                   lsValueRelation = 
            getPoUpdValueRelation().getValue() == null ? "" : 
            getPoUpdValueRelation().getValue().toString();
        //----------------------------------------------------
        String                   lsNomOrigin = 
            getPoUpdNomOrigin().getValue() == null ? "" : 
            getPoUpdNomOrigin().getValue().toString();
        String                   lsValueOrigin = 
            getPoUpdValueOrigin().getValue() == null ? "" : 
            getPoUpdValueOrigin().getValue().toString();
        String                   lsSystemOriginSel = 
            getPoUpdSystemOriginSel().getValue() == null ? "" : 
            getPoUpdSystemOriginSel().getValue().toString();
        //----------------------------------------------------
        String                   lsNomDestiny = 
            getPoUpdNomDestiny().getValue() == null ? "" : 
            getPoUpdNomDestiny().getValue().toString();
        String                   lsValueDestiny = 
            getPoUpdValueDestiny().getValue() == null ? "" : 
            getPoUpdValueDestiny().getValue().toString();
        String                   lsSystemDestinySel = 
            getPoUpdSystemDestinySel().getValue() == null ? "" : 
            getPoUpdSystemDestinySel().getValue().toString();
        //----------------------------------------------------
        String                   lsUsedBy = 
            getPoUpdUsedBy().getValue() == null ? "" : 
            getPoUpdUsedBy().getValue().toString();
        String                   lsDescription = 
            getPoUpdDescription().getValue() == null ? "" : 
            getPoUpdDescription().getValue().toString();
        
        String                   lsIndEstatus = 
            getPoPopUpdStatus().getValue() == null ? "" :
            getPoPopUpdStatus().getValue().toString();
        String lsStatusTab = "0";
        if(lsIndEstatus.equalsIgnoreCase("true")){
            lsStatusTab = "1";
        } 
        
        if(lsNomRelation.length() < 1){
            lsFieldErrorRq += "Relación,";
            lbProcess = false;   
        }
        if(lsValueOrigin.length() < 1){
            lsFieldErrorRq += "Valor Origen,";
            lbProcess = false;   
        }
        if(lsSystemOriginSel.length() < 1){
            lsFieldErrorRq += "Sistema Origen,";
            lbProcess = false;   
        }
        
        if(lsValueDestiny.length() < 1){
            lsFieldErrorRq += "Valor Destino,";
            lbProcess = false;   
        }
        if(lsSystemDestinySel.length() < 1){
            lsFieldErrorRq += "Sistema Destino,";
            lbProcess = false;   
        }
        
        if(lbProcess){
            ApplicationModule         loAm = 
                Configuration.createRootApplicationModule(gsAmDef,
                                                          gsConfig);
            AppModuleImpl loService = 
                (AppModuleImpl)loAm;
            try{
                LmkIntMappingCatRowBean loLmkBean = new LmkIntMappingCatRowBean();
                loLmkBean.setLiIdMapping(Integer.parseInt(lsIdMapping));
                loLmkBean.setLsNomRelation(lsNomRelation);                              
                loLmkBean.setLsValValueRelation(lsValueRelation);
                loLmkBean.setLsIndSysSystem("PgmLmk");
                loLmkBean.setLsNomOrigin(lsNomOrigin);
                loLmkBean.setLsValValueOrigin(lsValueOrigin);
                loLmkBean.setLsIndSysOrigin(lsSystemOriginSel);
                loLmkBean.setLsNomDestiny(lsNomDestiny);
                loLmkBean.setLsValValueDestiny(lsValueDestiny);
                loLmkBean.setLsIndSysDestiny(lsSystemDestinySel);
                loLmkBean.setLsIndUsedBy(lsUsedBy);
                loLmkBean.setLsIndDescription(lsDescription);
                loLmkBean.setLsIndEstatus(lsStatusTab);
                loLmkBean.setLiIdMappingRel(0);
                loService.updateMappingModel(loLmkBean); 
            } catch (Exception loEx) {
                FacesMessage loMsg;
                loMsg = new FacesMessage("Error de Comunicacion " + loEx);
                loMsg.setSeverity(FacesMessage.SEVERITY_FATAL);
                FacesContext.getCurrentInstance().addMessage(null, loMsg);
            } finally {
                Configuration.releaseRootApplicationModule(loAm, true);
            }
            
            //Refresh and close
            new UtilFaces().refreshTableWhereIterator("1 = 1",
                                                      gsEntityIterator,
                                                      getPoTblMain()
                                                      );
            new UtilFaces().hidePopup(getPoPopupUpdate());
        }else{
            FacesMessage loMsg;
            loMsg = new FacesMessage("Los Siguiente Campos son Requeridos " + 
                                     lsFieldErrorRq.substring(0, lsFieldErrorRq.length()-1));
            loMsg.setSeverity(FacesMessage.SEVERITY_WARN);
            FacesContext.getCurrentInstance().addMessage(null, loMsg);
        }
        return null;
    }

    /**
     * Oculta popup de Actualizacion
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String cancelUpdateAction() {
        new UtilFaces().hidePopup(getPoPopupUpdate());
        FacesContext       loContext = FacesContext.getCurrentInstance();
        ExternalContext    loEctx = loContext.getExternalContext();
        String             lsUrl = 
            loEctx.getRequestContextPath() + "/faces/mappingPage";        
        try {
            loEctx.redirect(lsUrl);
        } catch (IOException loEx) {
            ;
        }
        return null;
    }

    /**
     * Accion que guarda mapeoe
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String saveAction() {
        String                     lsFieldErrorRq = "";
        boolean                    lbProcess = true;
        Integer                    lsIdMapping = 
            new ViewObjectDao().getMaxIdParadigm("Mapping") + 1;
        
        String                   lsNomRelation = 
            getPoSaveNomRelation().getValue() == null ? "" : 
            getPoSaveNomRelation().getValue().toString();
        String                   lsValueRelation = 
            getPoSaveValueRelation().getValue() == null ? "" : 
            getPoSaveValueRelation().getValue().toString();
        //----------------------------------------------------
        String                   lsNomOrigin = 
            getPoSaveNomOrigin().getValue() == null ? "" : 
            getPoSaveNomOrigin().getValue().toString();
        String                   lsValueOrigin = 
            getPoSaveValueOrigin().getValue() == null ? "" : 
            getPoSaveValueOrigin().getValue().toString();
        String                   lsSystemOriginSel = 
            getPoSaveSystemOriginSel().getValue() == null ? "" : 
            getPoSaveSystemOriginSel().getValue().toString();
        //----------------------------------------------------
        String                   lsNomDestiny = 
            getPoSaveNomDestiny().getValue() == null ? "" : 
            getPoSaveNomDestiny().getValue().toString();
        String                   lsValueDestiny = 
            getPoSaveValueDestiny().getValue() == null ? "" : 
            getPoSaveValueDestiny().getValue().toString();
        String                   lsSystemDestinySel = 
            getPoSaveSystemDestinySel().getValue() == null ? "" : 
            getPoSaveSystemDestinySel().getValue().toString();
        //----------------------------------------------------
        String                   lsUsedBy = 
            getPoSaveUsedBy().getValue() == null ? "" : 
            getPoSaveUsedBy().getValue().toString();
        String                   lsDescription = 
            getPoSaveDescription().getValue() == null ? "" : 
            getPoSaveDescription().getValue().toString();
                
        String                     lsIndEstatus = 
            getPoPopSaveStatus().getValue() == null ? "":
            getPoPopSaveStatus().getValue().toString();
        String                     lsStatusTab = "0";
        if(lsIndEstatus.equalsIgnoreCase("true")){
            lsStatusTab = "1";
        }
        
        if(lsNomRelation.length() < 1){
            lsFieldErrorRq += "Relación,";
            lbProcess = false;   
        }
        if(lsValueOrigin.length() < 1){
            lsFieldErrorRq += "Valor Origen,";
            lbProcess = false;   
        }
        if(lsSystemOriginSel.length() < 1){
            lsFieldErrorRq += "Sistema Origen,";
            lbProcess = false;   
        }
        if(lsValueDestiny.length() < 1){
            lsFieldErrorRq += "Valor Destino,";
            lbProcess = false;   
        }
        if(lsSystemDestinySel.length() < 1){
            lsFieldErrorRq += "Sistema Destino,";
            lbProcess = false;   
        }
        
        if(lbProcess){
            ApplicationModule         loAm = 
                Configuration.createRootApplicationModule(gsAmDef, gsConfig);
            AppModuleImpl loService = 
                (AppModuleImpl)loAm;                
            try{
                LmkIntMappingCatRowBean loLmkBean = new LmkIntMappingCatRowBean();
                loLmkBean.setLiIdMapping(lsIdMapping);
                loLmkBean.setLsNomRelation(lsNomRelation);                              
                loLmkBean.setLsValValueRelation(lsValueRelation);
                loLmkBean.setLsIndSysSystem("PgmLmk");
                loLmkBean.setLsNomOrigin(lsNomOrigin);
                loLmkBean.setLsValValueOrigin(lsValueOrigin);
                loLmkBean.setLsIndSysOrigin(lsSystemOriginSel);
                loLmkBean.setLsNomDestiny(lsNomDestiny);
                loLmkBean.setLsValValueDestiny(lsValueDestiny);
                loLmkBean.setLsIndSysDestiny(lsSystemDestinySel);
                loLmkBean.setLsIndUsedBy(lsUsedBy);
                loLmkBean.setLsIndDescription(lsDescription);
                loLmkBean.setLsIndEstatus(lsStatusTab);
                loLmkBean.setLiIdMappingRel(0);
                loLmkBean.setLsIndEstatus(lsStatusTab);
                loService.insertMappingModel(loLmkBean);            
            } catch (Exception loEx) {
                FacesMessage loMsg;
                loMsg = new FacesMessage("Error de Comunicacion " + loEx);
                loMsg.setSeverity(FacesMessage.SEVERITY_FATAL);
                FacesContext.getCurrentInstance().addMessage(null, loMsg);
            } finally {
                Configuration.releaseRootApplicationModule(loAm, true);
            }
            
            //Refresh and close
            new UtilFaces().refreshTableWhereIterator("1 = 1",
                                                      gsEntityIterator,
                                                      getPoTblMain()
                                                      );
            new UtilFaces().hidePopup(getPoPopupSave());
        }else{
            FacesMessage loMsg;
            loMsg = new FacesMessage("Los Siguiente Campos son Requeridos " + 
                                     lsFieldErrorRq.substring(0, lsFieldErrorRq.length()-1));
            loMsg.setSeverity(FacesMessage.SEVERITY_WARN);
            FacesContext.getCurrentInstance().addMessage(null, loMsg);
        }
        return null;
    }

    /**
     * Oculta popup que solicita datos para guardar 
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String cancelSaveAction() {
        new UtilFaces().hidePopup(getPoPopupSave());
        return null;
    }
    
    /**
     * Actualiza la tabla principal a su estado inicial
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String refreshMainTable() {
        new UtilFaces().refreshTableWhereIterator("1 = 1 ",
                                                  gsEntityIterator,
                                                  getPoTblMain()
                                                  );        
        return null;
    }

    public String searchFilterAction() {
        String lsQuery = " 1 = 1 ";
        
        String                   lsNomRelation = 
            getPoFilterNomRelation().getValue() == null ? "" : 
            getPoFilterNomRelation().getValue().toString();
        if(!lsNomRelation.equalsIgnoreCase("")){
            lsQuery += " AND UPPER(NOM_RELATION) LIKE '" +
                       lsNomRelation.toUpperCase() + "%'";
        }
        
        String                   lsValueRelation = 
            getPoFilterValueRelation().getValue() == null ? "" : 
            getPoFilterValueRelation().getValue().toString();
        if(!lsValueRelation.equalsIgnoreCase("")){
            lsQuery += " AND UPPER(VAL_VALUE_RELATION) LIKE '" +
                       lsValueRelation.toUpperCase() + "%'";
        }
        //----------------------------------------------------
        String                   lsNomOrigin = 
            getPoFilterNomOrigin().getValue() == null ? "" : 
            getPoFilterNomOrigin().getValue().toString();
        if(!lsNomOrigin.equalsIgnoreCase("")){
            lsQuery += " AND UPPER(NOM_ORIGIN) LIKE '" +
                       lsNomOrigin.toUpperCase() + "%'";
        }
        String                   lsValueOrigin = 
            getPoFilterValueOrigin().getValue() == null ? "" : 
            getPoFilterValueOrigin().getValue().toString();
        if(!lsValueOrigin.equalsIgnoreCase("")){
            lsQuery += " AND UPPER(VAL_VALUE_ORIGIN) LIKE '" +
                       lsValueOrigin.toUpperCase() + "%'";
        }
        String                   lsSystemOriginSel = 
            getPoFilterSystemOriginSel().getValue() == null ? "" : 
            getPoFilterSystemOriginSel().getValue().toString();
        if(!lsSystemOriginSel.equalsIgnoreCase("")){
            lsQuery += " AND UPPER(IND_SYS_ORIGIN) LIKE '" +
                       lsSystemOriginSel.toUpperCase() + "%'";
        }
        //----------------------------------------------------
        String                   lsNomDestiny = 
            getPoFilterNomDestiny().getValue() == null ? "" : 
            getPoFilterNomDestiny().getValue().toString();
        if(!lsNomDestiny.equalsIgnoreCase("")){
            lsQuery += " AND UPPER(NOM_DESTINY) LIKE '" +
                       lsNomDestiny.toUpperCase() + "%'";
        }
        
        String                   lsValueDestiny = 
            getPoFilterValueDestiny().getValue() == null ? "" : 
            getPoFilterValueDestiny().getValue().toString();
        if(!lsValueDestiny.equalsIgnoreCase("")){
            lsQuery += " AND UPPER(VAL_VALUE_DESTINY) LIKE '" +
                       lsValueDestiny.toUpperCase() + "%'";
        }
        
        String                   lsSystemDestinySel = 
            getPoFilterSystemDestinySel().getValue() == null ? "" : 
            getPoFilterSystemDestinySel().getValue().toString();
        if(!lsSystemDestinySel.equalsIgnoreCase("")){
            lsQuery += " AND UPPER(IND_SYS_DESTINY) LIKE '" +
                       lsSystemDestinySel.toUpperCase() + "%'";
        }
        //----------------------------------------------------        
              
        String lsUsedBy = 
            getPoFilterUsedBy().getValue() == null ? "" : 
            getPoFilterUsedBy().getValue().toString();        
        if(!lsUsedBy.equalsIgnoreCase("")){
            lsQuery += " AND UPPER(IND_USED_BY) LIKE '"+
                       lsUsedBy.toUpperCase() + "%'";
        }
        String                   lsDescription = 
            getPoFilterDescription().getValue() == null ? "" : 
            getPoFilterDescription().getValue().toString();
        if(!lsDescription.equalsIgnoreCase("")){
            lsQuery += " AND UPPER(IND_DESCRIPTION) LIKE '" +
                       lsDescription.toUpperCase() + "%'";
        }
        System.out.println(lsQuery);
        new UtilFaces().refreshTableWhereIterator(lsQuery,
                                                  gsEntityIterator,
                                                  getPoTblMain()
                                                  );
        return null;
    }

    /**
     * Elimina de la base de datos el parametro general
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String deleteAction() {
        String                   lsId = 
        getPoDeleteIdBinding().getValue() == null ? "" : 
        getPoDeleteIdBinding().getValue().toString();
        ApplicationModule        loAm = 
            Configuration.createRootApplicationModule(gsAmDef, gsConfig);
        AppModuleImpl loService = 
            (AppModuleImpl)loAm;
        try{
            loService.deleteMappingModel(Integer.parseInt(lsId));
        } catch (Exception loEx) {
            FacesMessage loMsg;
            loMsg = new FacesMessage("Error de Comunicacion " + loEx);
            loMsg.setSeverity(FacesMessage.SEVERITY_FATAL);
            FacesContext.getCurrentInstance().addMessage(null, loMsg);
        } finally {
            Configuration.releaseRootApplicationModule(loAm, true);
        }
        new UtilFaces().refreshTableWhereIterator("1 = 1",
                                                  gsEntityIterator,
                                                  getPoTblMain()
                                                  );
        new UtilFaces().hidePopup(getPoPopupDelete());
        return null;
    }

    /**
     * Oculta popup de eliminar servicio
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String cancelDelete() {
        new UtilFaces().hidePopup(getPoPopupDelete());
        return null;
    }
    /**
     * Regresa un conjunto de servicios web 
     * @autor Jorge Luis Bautista Santiago
     * @param tsStrSearch
     * @return List
     */
    public List getListSystems() {
        List<SelectItem>        laList = 
            new ArrayList<SelectItem>();
        ViewObjectDao           loMd = new ViewObjectDao();
        List<SelectOneItemBean> laAllWs = 
            loMd.getListGeneralParametersModelFilter("SYSTEMS_INTEGRATION");
        for(SelectOneItemBean loWs: laAllWs){
            SelectItem loItm = new SelectItem();           
            //loItm.setValue(loWs.getLsId());
            loItm.setValue(loWs.getLsValue());
            loItm.setDescription(loWs.getLsDescription());
            loItm.setLabel(loWs.getLsValue());
            laList.add(loItm);
            
        }
        return laList;
    }
    
    /**
     * Muestra popup que solicita datos para guardar
     * @autor Jorge Luis Bautista Santiago  
     * @param toActionEvent
     * @return String
     */
    public void showSavePopup(ActionEvent toActionEvent) {
        toActionEvent.getSource();        
        getPoSaveNomRelation().setValue("");
        getPoSaveValueRelation().setValue("");
        getPoSaveNomOrigin().setValue("");
        getPoSaveValueOrigin().setValue("");
        getPoSaveSystemOriginSel().setValue("");
        getPoSaveNomDestiny().setValue("");
        getPoSaveValueDestiny().setValue("");
        getPoSaveSystemDestinySel().setValue("");
        getPoSaveUsedBy().setValue("");
        getPoSaveDescription().setValue("");
        getPoPopSaveStatus().setValue("");
        new UtilFaces().showPopup(getPoPopupSave());
    }
    
    /**
     * Muestra popup que solicita datos para eliminar
     * @autor Jorge Luis Bautista Santiago  
     * @param toActionEvent
     * @return void
     */
    public void showDeletePopup(ActionEvent toActionEvent) {
        toActionEvent.getSource();
        FacesCtrlHierNodeBinding loNode = 
            (FacesCtrlHierNodeBinding) getPoTblMain().getSelectedRowData();
        String                   lsIdParameter = 
            loNode.getAttribute("IdMapping") == null ? "" : 
            loNode.getAttribute("IdMapping").toString(); 
        String                   lsValOrigen = 
            loNode.getAttribute("ValValueOrigin") == null ? "" : 
            loNode.getAttribute("ValValueOrigin").toString();
        String                   lsValDestino = 
            loNode.getAttribute("ValValueDestiny") == null ? "" : 
            loNode.getAttribute("ValValueDestiny").toString();
        
        getPoDeleteIdBinding().setValue(lsIdParameter);
        getPoDeleteMsgLbl().setLabel("Eliminar a " + lsValOrigen + " <==> " + lsValDestino);
        new UtilFaces().showPopup(getPoPopupDelete());
    }
    
    /**
     * Muestra popup que solicita datos para editar parametro
     * @autor Jorge Luis Bautista Santiago  
     * @param toActionEvent
     * @return void
     */
    public void showEditPopup(ActionEvent toActionEvent) {
        toActionEvent.getSource();
        FacesCtrlHierNodeBinding loNode = 
            (FacesCtrlHierNodeBinding) getPoTblMain().getSelectedRowData();
        String                   lsIdMapping = 
            loNode.getAttribute("IdMapping") == null ? "" : 
            loNode.getAttribute("IdMapping").toString();
        String                   lsNomRelation = 
            loNode.getAttribute("NomRelation") == null ? "" : 
            loNode.getAttribute("NomRelation").toString();
        String                   lsValueRelation = 
            loNode.getAttribute("ValValueRelation") == null ? "" : 
            loNode.getAttribute("ValValueRelation").toString();
        
        String                   lsNomOrigin = 
            loNode.getAttribute("NomOrigin") == null ? "" : 
            loNode.getAttribute("NomOrigin").toString();
        String                   lsValueOrigin = 
            loNode.getAttribute("ValValueOrigin") == null ? "" : 
            loNode.getAttribute("ValValueOrigin").toString();
        String                   lsSystemOriginSel = 
            loNode.getAttribute("IndSysOrigin") == null ? "" : 
            loNode.getAttribute("IndSysOrigin").toString();
        String                   lsNomDestiny = 
            loNode.getAttribute("NomDestiny") == null ? "" : 
            loNode.getAttribute("NomDestiny").toString();
        String                   lsValueDestiny = 
            loNode.getAttribute("ValValueDestiny") == null ? "" : 
            loNode.getAttribute("ValValueDestiny").toString();
        String                   lsSystemDestinySel = 
            loNode.getAttribute("IndSysDestiny") == null ? "" : 
            loNode.getAttribute("IndSysDestiny").toString();
        String                   lsDescription = 
            loNode.getAttribute("IndDescription") == null ? "" : 
            loNode.getAttribute("IndDescription").toString();
        
        String                   lsIndUsedBy = 
            loNode.getAttribute("IndUsedBy") == null ? "" : 
            loNode.getAttribute("IndUsedBy").toString();        
        String                   lsIndEstatus = 
            loNode.getAttribute("IndEstatus") == null ? "" : 
            loNode.getAttribute("IndEstatus").toString();
        
        // Settear valores al popup
        getPoPopUpIdMapping().setValue(lsIdMapping);                
        getPoUpdNomRelation().setValue(lsNomRelation);
        getPoUpdValueRelation().setValue(lsValueRelation);        
        getPoUpdNomOrigin().setValue(lsNomOrigin);        
        getPoUpdValueOrigin().setValue(lsValueOrigin);
        
        getPoUpdSystemOriginSel().setValue(lsSystemOriginSel);
        getPoUpdNomDestiny().setValue(lsNomDestiny);
        getPoUpdValueDestiny().setValue(lsValueDestiny);
        getPoUpdSystemDestinySel().setValue(lsSystemDestinySel);
        
        getPoUpdUsedBy().setValue(lsIndUsedBy);
        getPoUpdDescription().setValue(lsDescription);
        
        if(lsIndEstatus.equalsIgnoreCase("1")){
            getPoPopUpdStatus().setSelected(true);
        }else{
            getPoPopUpdStatus().setSelected(false);
        }                
        
        new UtilFaces().showPopup(getPoPopupUpdate());
    }
    
    /*********** SETTERS AND GETTERS ***********/
    public void setPoFilterValueRelation(RichInputText poFilterValueRelation) {
        this.poFilterValueRelation = poFilterValueRelation;
    }

    public RichInputText getPoFilterValueRelation() {
        return poFilterValueRelation;
    }

    public void setPoFilterSystemRelation(RichInputText poFilterSystemRelation) {
        this.poFilterSystemRelation = poFilterSystemRelation;
    }

    public RichInputText getPoFilterSystemRelation() {
        return poFilterSystemRelation;
    }

    public void setPoFilterNomOrigin(RichInputText poFilterNomOrigin) {
        this.poFilterNomOrigin = poFilterNomOrigin;
    }

    public RichInputText getPoFilterNomOrigin() {
        return poFilterNomOrigin;
    }

    public void setPoFilterValueOrigin(RichInputText poFilterValueOrigin) {
        this.poFilterValueOrigin = poFilterValueOrigin;
    }

    public RichInputText getPoFilterValueOrigin() {
        return poFilterValueOrigin;
    }

    public void setPoFilterSystemOrigin(RichInputText poFilterSystemOrigin) {
        this.poFilterSystemOrigin = poFilterSystemOrigin;
    }

    public RichInputText getPoFilterSystemOrigin() {
        return poFilterSystemOrigin;
    }

    public void setPoFilterNomDestiny(RichInputText poFilterNomDestiny) {
        this.poFilterNomDestiny = poFilterNomDestiny;
    }

    public RichInputText getPoFilterNomDestiny() {
        return poFilterNomDestiny;
    }

    public void setPoFilterValueDestiny(RichInputText poFilterValueDestiny) {
        this.poFilterValueDestiny = poFilterValueDestiny;
    }

    public RichInputText getPoFilterValueDestiny() {
        return poFilterValueDestiny;
    }

    public void setPoFilterSystemDestiny(RichInputText poFilterSystemDestiny) {
        this.poFilterSystemDestiny = poFilterSystemDestiny;
    }

    public RichInputText getPoFilterSystemDestiny() {
        return poFilterSystemDestiny;
    }

    public void setPoFilterUsedBy(RichInputText poFilterUsedBy) {
        this.poFilterUsedBy = poFilterUsedBy;
    }

    public RichInputText getPoFilterUsedBy() {
        return poFilterUsedBy;
    }

    public void setPoFilterDescription(RichInputText poFilterDescription) {
        this.poFilterDescription = poFilterDescription;
    }

    public RichInputText getPoFilterDescription() {
        return poFilterDescription;
    }

    public void setPoFilterIdMapping(RichInputText poFilterIdMapping) {
        this.poFilterIdMapping = poFilterIdMapping;
    }

    public RichInputText getPoFilterIdMapping() {
        return poFilterIdMapping;
    }
   
    public void setPoFilterNomRelation(RichInputText poFilterNomRelation) {
        this.poFilterNomRelation = poFilterNomRelation;
    }

    public RichInputText getPoFilterNomRelation() {
        return poFilterNomRelation;
    }

    public void setPoFilterSystemOriginSel(RichSelectOneChoice poFilterSystemOriginSel) {
        this.poFilterSystemOriginSel = poFilterSystemOriginSel;
    }

    public RichSelectOneChoice getPoFilterSystemOriginSel() {
        return poFilterSystemOriginSel;
    }

    public void setPoFilterSystemDestinySel(RichSelectOneChoice poFilterSystemDestinySel) {
        this.poFilterSystemDestinySel = poFilterSystemDestinySel;
    }

    public RichSelectOneChoice getPoFilterSystemDestinySel() {
        return poFilterSystemDestinySel;
    }

    public void setPoSaveNomRelation(RichInputText poSaveNomRelation) {
        this.poSaveNomRelation = poSaveNomRelation;
    }

    public RichInputText getPoSaveNomRelation() {
        return poSaveNomRelation;
    }

    public void setPoSaveValueRelation(RichInputText poSaveValueRelation) {
        this.poSaveValueRelation = poSaveValueRelation;
    }

    public RichInputText getPoSaveValueRelation() {
        return poSaveValueRelation;
    }

    public void setPoSaveSystemRelation(RichInputText poSaveSystemRelation) {
        this.poSaveSystemRelation = poSaveSystemRelation;
    }

    public RichInputText getPoSaveSystemRelation() {
        return poSaveSystemRelation;
    }

    public void setPoSaveNomOrigin(RichInputText poSaveNomOrigin) {
        this.poSaveNomOrigin = poSaveNomOrigin;
    }

    public RichInputText getPoSaveNomOrigin() {
        return poSaveNomOrigin;
    }

    public void setPoSaveValueOrigin(RichInputText poSaveValueOrigin) {
        this.poSaveValueOrigin = poSaveValueOrigin;
    }

    public RichInputText getPoSaveValueOrigin() {
        return poSaveValueOrigin;
    }

    public void setPoSaveSystemOriginSel(RichSelectOneChoice poSaveSystemOriginSel) {
        this.poSaveSystemOriginSel = poSaveSystemOriginSel;
    }

    public RichSelectOneChoice getPoSaveSystemOriginSel() {
        return poSaveSystemOriginSel;
    }

    public void setPoSaveSystemOrigin(RichInputText poSaveSystemOrigin) {
        this.poSaveSystemOrigin = poSaveSystemOrigin;
    }

    public RichInputText getPoSaveSystemOrigin() {
        return poSaveSystemOrigin;
    }

    public void setPoSaveNomDestiny(RichInputText poSaveNomDestiny) {
        this.poSaveNomDestiny = poSaveNomDestiny;
    }

    public RichInputText getPoSaveNomDestiny() {
        return poSaveNomDestiny;
    }

    public void setPoSaveValueDestiny(RichInputText poSaveValueDestiny) {
        this.poSaveValueDestiny = poSaveValueDestiny;
    }

    public RichInputText getPoSaveValueDestiny() {
        return poSaveValueDestiny;
    }

    public void setPoPopSaveStatus(RichSelectBooleanCheckbox poPopSaveStatus) {
        this.poPopSaveStatus = poPopSaveStatus;
    }

    public RichSelectBooleanCheckbox getPoPopSaveStatus() {
        return poPopSaveStatus;
    }    

    public void setPoPopupSave(RichPopup poPopupSave) {
        this.poPopupSave = poPopupSave;
    }

    public RichPopup getPoPopupSave() {
        return poPopupSave;
    }

    public void setPoPopupDelete(RichPopup poPopupDelete) {
        this.poPopupDelete = poPopupDelete;
    }

    public RichPopup getPoPopupDelete() {
        return poPopupDelete;
    }

    public void setPoDeleteMsgLbl(RichPanelLabelAndMessage poDeleteMsgLbl) {
        this.poDeleteMsgLbl = poDeleteMsgLbl;
    }

    public RichPanelLabelAndMessage getPoDeleteMsgLbl() {
        return poDeleteMsgLbl;
    }

    public void setPoDeleteIdBinding(RichOutputText poDeleteIdBinding) {
        this.poDeleteIdBinding = poDeleteIdBinding;
    }

    public RichOutputText getPoDeleteIdBinding() {
        return poDeleteIdBinding;
    }


    public void setPoPopupUpdate(RichPopup poPopupUpdate) {
        this.poPopupUpdate = poPopupUpdate;
    }

    public RichPopup getPoPopupUpdate() {
        return poPopupUpdate;
    }

    public void setPoPopUpIdMapping(RichInputText poPopUpIdMapping) {
        this.poPopUpIdMapping = poPopUpIdMapping;
    }

    public RichInputText getPoPopUpIdMapping() {
        return poPopUpIdMapping;
    }

    public void setPoUpdNomRelation(RichInputText poUpdNomRelation) {
        this.poUpdNomRelation = poUpdNomRelation;
    }

    public RichInputText getPoUpdNomRelation() {
        return poUpdNomRelation;
    }

    public void setPoUpdValueRelation(RichInputText poUpdValueRelation) {
        this.poUpdValueRelation = poUpdValueRelation;
    }

    public RichInputText getPoUpdValueRelation() {
        return poUpdValueRelation;
    }

    public void setPoUpdSystemRelation(RichInputText poUpdSystemRelation) {
        this.poUpdSystemRelation = poUpdSystemRelation;
    }

    public RichInputText getPoUpdSystemRelation() {
        return poUpdSystemRelation;
    }

    public void setPoUpdNomOrigin(RichInputText poUpdNomOrigin) {
        this.poUpdNomOrigin = poUpdNomOrigin;
    }

    public RichInputText getPoUpdNomOrigin() {
        return poUpdNomOrigin;
    }

    public void setPoUpdValueOrigin(RichInputText poUpdValueOrigin) {
        this.poUpdValueOrigin = poUpdValueOrigin;
    }

    public RichInputText getPoUpdValueOrigin() {
        return poUpdValueOrigin;
    }

    public void setPoUpdSystemOriginSel(RichSelectOneChoice poUpdSystemOriginSel) {
        this.poUpdSystemOriginSel = poUpdSystemOriginSel;
    }

    public RichSelectOneChoice getPoUpdSystemOriginSel() {
        return poUpdSystemOriginSel;
    }

    public void setPoUpdSystemOrigin(RichInputText poUpdSystemOrigin) {
        this.poUpdSystemOrigin = poUpdSystemOrigin;
    }

    public RichInputText getPoUpdSystemOrigin() {
        return poUpdSystemOrigin;
    }

    public void setPoUpdValueDestiny(RichInputText poUpdValueDestiny) {
        this.poUpdValueDestiny = poUpdValueDestiny;
    }

    public RichInputText getPoUpdValueDestiny() {
        return poUpdValueDestiny;
    }

    public void setPoUpdNomDestiny(RichInputText poUpdNomDestiny) {
        this.poUpdNomDestiny = poUpdNomDestiny;
    }

    public RichInputText getPoUpdNomDestiny() {
        return poUpdNomDestiny;
    }

    public void setPoPopUpdStatus(RichSelectBooleanCheckbox poPopUpdStatus) {
        this.poPopUpdStatus = poPopUpdStatus;
    }

    public RichSelectBooleanCheckbox getPoPopUpdStatus() {
        return poPopUpdStatus;
    }
    

    public void setPoTblMain(RichTable poTblMain) {
        this.poTblMain = poTblMain;
    }

    public RichTable getPoTblMain() {
        return poTblMain;
    }


    public void setPoSaveSystemDestinySel(RichSelectOneChoice poSaveSystemDestinySel) {
        this.poSaveSystemDestinySel = poSaveSystemDestinySel;
    }

    public RichSelectOneChoice getPoSaveSystemDestinySel() {
        return poSaveSystemDestinySel;
    }

    public void setPoUpdSystemDestinySel(RichSelectOneChoice poUpdSystemDestinySel) {
        this.poUpdSystemDestinySel = poUpdSystemDestinySel;
    }

    public RichSelectOneChoice getPoUpdSystemDestinySel() {
        return poUpdSystemDestinySel;
    }

    public void setPoSaveUsedBy(RichInputText poSaveUsedBy) {
        this.poSaveUsedBy = poSaveUsedBy;
    }

    public RichInputText getPoSaveUsedBy() {
        return poSaveUsedBy;
    }

    public void setPoSaveDescription(RichInputText poSaveDescription) {
        this.poSaveDescription = poSaveDescription;
    }

    public RichInputText getPoSaveDescription() {
        return poSaveDescription;
    }

    public void setPoUpdUsedBy(RichInputText poUpdUsedBy) {
        this.poUpdUsedBy = poUpdUsedBy;
    }

    public RichInputText getPoUpdUsedBy() {
        return poUpdUsedBy;
    }

    public void setPoUpdDescription(RichInputText poUpdDescription) {
        this.poUpdDescription = poUpdDescription;
    }

    public RichInputText getPoUpdDescription() {
        return poUpdDescription;
    }
}
