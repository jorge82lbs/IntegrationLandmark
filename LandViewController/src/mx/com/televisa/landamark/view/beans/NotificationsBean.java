/**
* Project: Paradigm - Landmark Integration
*
* File: NotificationsBean.java
*
* Created on:  Febrero 6, 2019 at 11:00
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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import mx.com.televisa.landamark.model.AppModuleImpl;
import mx.com.televisa.landamark.model.types.LmkIntMappingCatRowBean;
import mx.com.televisa.landamark.model.types.LmkIntNotificationsRowBean;
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
/** Esta clase es un bean que enlaza la pantalla de Notificaciones<br/><br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Febrero 14, 2019, 12:00 pm
 */
public class NotificationsBean {
    private RichSelectOneChoice poFilterServiceSel;
    private RichTable poTblMain;
    private RichPopup poPopupSave;
    private RichInputText poSaveIdNotification;
    private RichInputText poSaveIdService;
    private RichInputText poSaveIndService;
    private RichSelectOneChoice poSaveIndProcessSel;
    private RichSelectOneChoice poSaveIndUsersGroupSel;
    private RichInputText poSaveIndSubject;
    private RichInputText poSaveIndMessage;
    private RichSelectBooleanCheckbox poPopSaveStatus;
    private RichPopup poPopupDelete;
    private RichPanelLabelAndMessage poDeleteMsgLbl;
    private RichOutputText poDeleteIdBinding;
    private RichPopup poPopupUpdate;
    private RichInputText poPopUpIdNotification;
    private RichSelectBooleanCheckbox poPopUpdStatus;
    private RichInputText poUpdIdService;
    private RichInputText poUpdIndService;
    private RichSelectOneChoice poUpdIndProcessSel;
    private RichSelectOneChoice poUpdIndUsersGroupSel;
    private RichInputText poUpdIndSubject;
    private RichInputText poUpdIndMessage;
    String                            gsEntityView = "LmkIntNotificationsVwView1";
    String                            gsEntityIterator = "LmkIntNotificationsVwView1Iterator";
                                                          
    String                            gsAppModule = "AppModuleDataControl";
    String                            gsAmDef = "mx.com.televisa.landamark.model.AppModuleImpl";
    String                            gsConfig = "AppModuleLocal";
    private RichInputText poNomService;

    public NotificationsBean() {
    }
    
    /**
     * Accion que guarda Configuracion de notificaciones
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String saveAction() {
        String                     lsFieldErrorRq = "";
        boolean                    lbProcess = true;
        
        Integer                    liIdNotification = 
            new ViewObjectDao().getMaxIdParadigm("Notifications") + 1;
        //System.out.println("########## liIdNotification: "+liIdNotification);
        
        String                   lsIdService = 
            getPoSaveIdService().getValue() == null ? "" : 
            getPoSaveIdService().getValue().toString();
        
        //System.out.println("########## lsIdService: "+lsIdService);
        
        String                   liIndProcess = 
            getPoSaveIndProcessSel().getValue() == null ? "" : 
            getPoSaveIndProcessSel().getValue().toString();
        //System.out.println("########## liIndProcess: "+liIndProcess);
        String                   lsIndUsersGroup = 
            getPoSaveIndUsersGroupSel().getValue() == null ? "" : 
            getPoSaveIndUsersGroupSel().getValue().toString();
        //System.out.println("########## lsIndUsersGroup: "+lsIndUsersGroup);
        String                   lsIndSubject = 
            getPoSaveIndSubject().getValue() == null ? "" : 
            getPoSaveIndSubject().getValue().toString();
        //System.out.println("########## lsIndSubject: "+lsIndSubject);
        String                   lsIndMessage = 
            getPoSaveIndMessage().getValue() == null ? "" : 
            getPoSaveIndMessage().getValue().toString();
        //System.out.println("########## lsIndMessage: "+lsIndMessage);
        String                     lsIndEstatus = 
            getPoPopSaveStatus().getValue() == null ? "":
            getPoPopSaveStatus().getValue().toString();
        //System.out.println("########## lsIndEstatus: "+lsIndEstatus);
        String                     lsStatusTab = "0";
        if(lsIndEstatus.equalsIgnoreCase("true")){
            lsStatusTab = "1";
        }
        
        if(liIndProcess.length() < 1){
            lsFieldErrorRq += "Proceso,";
            lbProcess = false;   
        }
        if(lsIndUsersGroup.length() < 1){
            lsFieldErrorRq += "Grupo de Usuarios,";
            lbProcess = false;   
        }
        if(lsIndSubject.length() < 1){
            lsFieldErrorRq += "Asunto,";
            lbProcess = false;   
        }
        if(lsIndMessage.length() < 1){
            lsFieldErrorRq += "Mensaje,";
            lbProcess = false;   
        }        
        
        if(lbProcess){
            ApplicationModule         loAm = 
                Configuration.createRootApplicationModule(gsAmDef, gsConfig);
            AppModuleImpl loService = 
                (AppModuleImpl)loAm;                
            try{
                /*System.out.println("Datos a guardar.............");
                System.out.println("liIdNotification: "+liIdNotification);
                System.out.println("lsIdService: "+lsIdService);
                System.out.println("liIndProcess: "+liIndProcess);
                System.out.println("lsIndUsersGroup: "+lsIndUsersGroup);                
                System.out.println("lsIndSubject: "+lsIndSubject);                
                System.out.println("lsIndMessage: "+lsIndMessage);                
                System.out.println("lsStatusTab: "+lsStatusTab);
                */
                LmkIntNotificationsRowBean loLmkBean = new LmkIntNotificationsRowBean();
                loLmkBean.setLiIdNotification(liIdNotification);
                loLmkBean.setLiIdService(Integer.parseInt(lsIdService));                
                loLmkBean.setLiIndProcess(Integer.parseInt(liIndProcess)); 
                loLmkBean.setLsIndUsersGroup(lsIndUsersGroup);                
                loLmkBean.setLsIndSubject(lsIndSubject);                
                loLmkBean.setLsIndMessage(lsIndMessage);                
                loLmkBean.setLsIndEstatus(lsStatusTab);
                loService.insertNotificationsModel(loLmkBean);            
                System.out.println("Insert OK");
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
        new UtilFaces().refreshTableWhereIterator("1 = 1",
                                                  gsEntityIterator,
                                                  getPoTblMain()
                                                  );
        
        new UtilFaces().hidePopup(getPoPopupSave());     
        /*FacesContext       loContext = FacesContext.getCurrentInstance();
        ExternalContext    loEctx = loContext.getExternalContext();
        String             lsUrl = 
            loEctx.getRequestContextPath() + "/faces/notificationsPage";
        try {
            loEctx.redirect(lsUrl);
        } catch (IOException loEx) {
            ;
        }*/
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
            loService.deleteNotificationsModel(Integer.parseInt(lsId));
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
     * Actualiza en base de datos el servicio seleccionado
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */  
    public String updateAction() {
        String                   lsFieldErrorRq = "";
        boolean                  lbProcess = true;
        
        String                   lsIdNotification = 
            getPoPopUpIdNotification().getValue() == null ? "" : 
            getPoPopUpIdNotification().getValue().toString();
        String                   lsIdService = 
            getPoUpdIdService().getValue() == null ? "" : 
            getPoUpdIdService().getValue().toString();
        String                   liIndProcess = 
            getPoUpdIndProcessSel().getValue() == null ? "" : 
            getPoUpdIndProcessSel().getValue().toString();
        String                   lsIndUsersGroup = 
            getPoUpdIndUsersGroupSel().getValue() == null ? "" : 
            getPoUpdIndUsersGroupSel().getValue().toString();
        String                   lsIndSubject = 
            getPoUpdIndSubject().getValue() == null ? "" : 
            getPoUpdIndSubject().getValue().toString();
        String                   lsIndMessage = 
            getPoUpdIndMessage().getValue() == null ? "" : 
            getPoUpdIndMessage().getValue().toString();
        
        String                     lsIndEstatus = 
            getPoPopUpdStatus().getValue() == null ? "":
            getPoPopUpdStatus().getValue().toString();
        String                     lsStatusTab = "0";
        if(lsIndEstatus.equalsIgnoreCase("true")){
            lsStatusTab = "1";
        }
        if(liIndProcess.length() < 1){
            lsFieldErrorRq += "Proceso,";
            lbProcess = false;   
        }
        if(lsIndUsersGroup.length() < 1){
            lsFieldErrorRq += "Grupo de Usuarios,";
            lbProcess = false;   
        }
        if(lsIndSubject.length() < 1){
            lsFieldErrorRq += "Asunto,";
            lbProcess = false;   
        }
        if(lsIndMessage.length() < 1){
            lsFieldErrorRq += "Mensaje,";
            lbProcess = false;   
        }                
        
        if(lbProcess){
            ApplicationModule         loAm = 
                Configuration.createRootApplicationModule(gsAmDef,
                                                          gsConfig);
            AppModuleImpl loService = 
                (AppModuleImpl)loAm;
            try{
                LmkIntNotificationsRowBean loLmkBean = new LmkIntNotificationsRowBean();
                loLmkBean.setLiIdNotification(Integer.parseInt(lsIdNotification));                
                loLmkBean.setLiIdService(Integer.parseInt(lsIdService));                
                loLmkBean.setLiIndProcess(Integer.parseInt(liIndProcess)); 
                loLmkBean.setLsIndUsersGroup(lsIndUsersGroup);                
                loLmkBean.setLsIndSubject(lsIndSubject);                
                loLmkBean.setLsIndMessage(lsIndMessage);                
                loLmkBean.setLsIndEstatus(lsStatusTab);
                loService.updateNotificationsModel(loLmkBean);  
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
        new UtilFaces().refreshTableWhereIterator("1 = 1",
                                                  gsEntityIterator,
                                                  getPoTblMain()
                                                  );
        new UtilFaces().hidePopup(getPoPopupUpdate());
        
        return null;
        
        
        /*
        new UtilFaces().refreshTableWhereIterator("1 = 1",
                                                  gsEntityIterator,
                                                  getPoTblMain()
                                                  );
        new UtilFaces().hidePopup(getPoPopupUpdate());
*/
        /*
        FacesContext       loContext = FacesContext.getCurrentInstance();
        ExternalContext    loEctx = loContext.getExternalContext();
        String             lsUrl = 
            loEctx.getRequestContextPath() + "/faces/notificationsPage";        
        try {
            String lsIdService = getPoUpdIdService().getValue().toString();
            String lsNomService = getPoUpdIndService().getValue().toString();
            loEctx.redirect(lsUrl);
            getPoTblMain().setVisible(true);
            getPoUpdIndUsersGroupSel().setValue(lsIdService);
            getPoNomService().setValue(lsNomService);
            
        } catch (IOException loEx) {
            ;
        }*/
        //return null;
    }

    /**
     * Regresa un conjunto de servicios configurados 
     * @autor Jorge Luis Bautista Santiago
     * @param tsStrSearch
     * @return List
     */
    public List getListAllServices() {
        List<SelectItem>        laList = 
            new ArrayList<SelectItem>();
        ViewObjectDao           loMd = new ViewObjectDao();
        List<SelectOneItemBean> laAllWs = 
            loMd.getListAllWebServicesModel();
        for(SelectOneItemBean loWs: laAllWs){
            SelectItem loItm = new SelectItem();           
            loItm.setValue(loWs.getLsId());
            //loItm.setValue(loWs.getLsValue());
            loItm.setDescription(loWs.getLsDescription());
            loItm.setLabel(loWs.getLsValue());
            laList.add(loItm);
            
        }
        return laList;
    }
    
    /**
     * Regresa un conjunto de Procesos 
     * @autor Jorge Luis Bautista Santiago
     * @param tsStrSearch
     * @return List
     */
    public List getListProcess() {
        List<SelectItem>        laList = 
            new ArrayList<SelectItem>();
        ViewObjectDao           loMd = new ViewObjectDao();
        List<SelectOneItemBean> laAllWs = 
            loMd.getListGeneralParametersModelFilter("PROCESS_INTEGRATION");//
        for(SelectOneItemBean loWs: laAllWs){
            SelectItem loItm = new SelectItem();           
            loItm.setValue(loWs.getLsId());
            //loItm.setValue(loWs.getLsValue());
            loItm.setDescription(loWs.getLsDescription());
            loItm.setLabel(loWs.getLsValue());
            laList.add(loItm);
            
        }
        return laList;
    }
    
    /**
     * Regresa un conjunto de grupo de usuarios 
     * @autor Jorge Luis Bautista Santiago
     * @param tsStrSearch
     * @return List
     */
    public List getListUsersGroups() {
        List<SelectItem>        laList = 
            new ArrayList<SelectItem>();
        ViewObjectDao           loMd = new ViewObjectDao();
        List<SelectOneItemBean> laAllWs = 
            loMd.getListGeneralParametersModelFilter("EMAIL_USERS_GROUPS");
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
        
        String                   lsIdService = 
            getPoFilterServiceSel().getValue() == null ? "0" : 
            getPoFilterServiceSel().getValue().toString(); 
        String                   lsDesService = 
        getPoNomService().getValue() == null ? "0" : 
        getPoNomService().getValue().toString(); 
        
        getPoSaveIdService().setValue(lsIdService);    
        getPoSaveIndService().setValue(lsDesService);    
        
        getPoSaveIdNotification().setValue("");    
        getPoSaveIndMessage().setValue("");    
        getPoSaveIndProcessSel().setValue("");    
        getPoSaveIndSubject().setValue("");    
        getPoSaveIndUsersGroupSel().setValue("");   
        new UtilFaces().showPopup(getPoPopupSave());
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
        String                   liIdNotification = 
            loNode.getAttribute("IdNotification") == null ? "" : 
            loNode.getAttribute("IdNotification").toString();
        
        String                   liIdService = 
            loNode.getAttribute("IdService") == null ? "" : 
            loNode.getAttribute("IdService").toString();
        String                   liNomService = 
            loNode.getAttribute("NomService") == null ? "" : 
            loNode.getAttribute("NomService").toString();
        
        String                   liIndProcess = 
            loNode.getAttribute("IndProcess") == null ? "" : 
            loNode.getAttribute("IndProcess").toString();
        String                   liNomProcess = 
            loNode.getAttribute("NomProcess") == null ? "" : 
            loNode.getAttribute("NomProcess").toString();
        
        String                   lsIndUsersGroup = 
            loNode.getAttribute("IndUsersGroup") == null ? "" : 
            loNode.getAttribute("IndUsersGroup").toString();
        String                   lsNomUsersGroup = 
            loNode.getAttribute("NomGroup") == null ? "" : 
            loNode.getAttribute("NomGroup").toString();
                
        String                   lsIndSubject = 
            loNode.getAttribute("IndSubject") == null ? "" : 
            loNode.getAttribute("IndSubject").toString();
        String                   lsIndMessage = 
            loNode.getAttribute("IndMessage") == null ? "" : 
            loNode.getAttribute("IndMessage").toString();
        
        String                   lsIndEstatus = 
            loNode.getAttribute("IndEstatus") == null ? "" : 
            loNode.getAttribute("IndEstatus").toString();
        
        // Settear valores al popup
        getPoPopUpIdNotification().setValue(liIdNotification);                
        //liIdService
        getPoUpdIdService().setValue(liIdService);
        //liNomService
        getPoUpdIndService().setValue(liNomService);
        //liIndProcess
        getPoUpdIndProcessSel().setValue(liIndProcess);                
        //liNomProcess
        //getPoUpdIndProcessSel().setValue(liIndProcess);
        //lsIndUsersGroup
        getPoUpdIndUsersGroupSel().setValue(lsNomUsersGroup);                
        //lsNomUsersGroup        
        //lsIndSubject
        getPoUpdIndSubject().setValue(lsIndSubject);                
        //lsIndMessage
        getPoUpdIndMessage().setValue(lsIndMessage);                
        
        if(lsIndEstatus.equalsIgnoreCase("1")){
            getPoPopUpdStatus().setSelected(true);
        }else{
            getPoPopUpdStatus().setSelected(false);
        }                
        
        new UtilFaces().showPopup(getPoPopupUpdate());
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
        String                   lsIdNotification = 
            loNode.getAttribute("IdNotification") == null ? "" : 
            loNode.getAttribute("IdNotification").toString();        
        String                   lsProcess = 
            loNode.getAttribute("NomProcess") == null ? "" : 
            loNode.getAttribute("NomProcess").toString();
        String                   lsUsersGroup = 
            loNode.getAttribute("NomGroup") == null ? "" : 
            loNode.getAttribute("NomGroup").toString();
        getPoDeleteIdBinding().setValue(lsIdNotification);
        getPoDeleteMsgLbl().setLabel("Eliminar a " + lsProcess + " ["+lsUsersGroup+"]");
        new UtilFaces().showPopup(getPoPopupDelete());
    }
    
    /*********** SETTERS AND GETTERS ***********/

    public void setPoFilterServiceSel(RichSelectOneChoice poFilterServiceSel) {
        this.poFilterServiceSel = poFilterServiceSel;
    }

    public RichSelectOneChoice getPoFilterServiceSel() {
        return poFilterServiceSel;
    }

    public void setPoTblMain(RichTable poTblMain) {
        this.poTblMain = poTblMain;
    }

    public RichTable getPoTblMain() {
        return poTblMain;
    }

    public void setPoPopupSave(RichPopup poPopupSave) {
        this.poPopupSave = poPopupSave;
    }

    public RichPopup getPoPopupSave() {
        return poPopupSave;
    }

    public void setPoSaveIdNotification(RichInputText poSaveIdNotification) {
        this.poSaveIdNotification = poSaveIdNotification;
    }

    public RichInputText getPoSaveIdNotification() {
        return poSaveIdNotification;
    }

    public void setPoSaveIdService(RichInputText poSaveIdService) {
        this.poSaveIdService = poSaveIdService;
    }

    public RichInputText getPoSaveIdService() {
        return poSaveIdService;
    }

    public void setPoSaveIndService(RichInputText poSaveIndService) {
        this.poSaveIndService = poSaveIndService;
    }

    public RichInputText getPoSaveIndService() {
        return poSaveIndService;
    }

    public void setPoSaveIndProcessSel(RichSelectOneChoice poSaveIndProcessSel) {
        this.poSaveIndProcessSel = poSaveIndProcessSel;
    }

    public RichSelectOneChoice getPoSaveIndProcessSel() {
        return poSaveIndProcessSel;
    }

    public void setPoSaveIndUsersGroupSel(RichSelectOneChoice poSaveIndUsersGroupSel) {
        this.poSaveIndUsersGroupSel = poSaveIndUsersGroupSel;
    }

    public RichSelectOneChoice getPoSaveIndUsersGroupSel() {
        return poSaveIndUsersGroupSel;
    }

    public void setPoSaveIndSubject(RichInputText poSaveIndSubject) {
        this.poSaveIndSubject = poSaveIndSubject;
    }

    public RichInputText getPoSaveIndSubject() {
        return poSaveIndSubject;
    }

    public void setPoSaveIndMessage(RichInputText poSaveIndMessage) {
        this.poSaveIndMessage = poSaveIndMessage;
    }

    public RichInputText getPoSaveIndMessage() {
        return poSaveIndMessage;
    }

    public void setPoPopSaveStatus(RichSelectBooleanCheckbox poPopSaveStatus) {
        this.poPopSaveStatus = poPopSaveStatus;
    }

    public RichSelectBooleanCheckbox getPoPopSaveStatus() {
        return poPopSaveStatus;
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

    public void setPoPopUpIdNotification(RichInputText poPopUpIdNotification) {
        this.poPopUpIdNotification = poPopUpIdNotification;
    }

    public RichInputText getPoPopUpIdNotification() {
        return poPopUpIdNotification;
    }

    public void setPoPopUpdStatus(RichSelectBooleanCheckbox poPopUpdStatus) {
        this.poPopUpdStatus = poPopUpdStatus;
    }

    public RichSelectBooleanCheckbox getPoPopUpdStatus() {
        return poPopUpdStatus;
    }

    public void setPoUpdIdService(RichInputText poUpdIdService) {
        this.poUpdIdService = poUpdIdService;
    }

    public RichInputText getPoUpdIdService() {
        return poUpdIdService;
    }

    public void setPoUpdIndService(RichInputText poUpdIndService) {
        this.poUpdIndService = poUpdIndService;
    }

    public RichInputText getPoUpdIndService() {
        return poUpdIndService;
    }

    public void setPoUpdIndProcessSel(RichSelectOneChoice poUpdIndProcessSel) {
        this.poUpdIndProcessSel = poUpdIndProcessSel;
    }

    public RichSelectOneChoice getPoUpdIndProcessSel() {
        return poUpdIndProcessSel;
    }

    public void setPoUpdIndUsersGroupSel(RichSelectOneChoice poUpdIndUsersGroupSel) {
        this.poUpdIndUsersGroupSel = poUpdIndUsersGroupSel;
    }

    public RichSelectOneChoice getPoUpdIndUsersGroupSel() {
        return poUpdIndUsersGroupSel;
    }

    public void setPoUpdIndSubject(RichInputText poUpdIndSubject) {
        this.poUpdIndSubject = poUpdIndSubject;
    }

    public RichInputText getPoUpdIndSubject() {
        return poUpdIndSubject;
    }

    public void setPoUpdIndMessage(RichInputText poUpdIndMessage) {
        this.poUpdIndMessage = poUpdIndMessage;
    }

    public RichInputText getPoUpdIndMessage() {
        return poUpdIndMessage;
    }

    public void selectServiceListener(ValueChangeEvent loValueChangeEvent) {
        String lsIdService = 
            loValueChangeEvent.getNewValue() == null ? null : 
            loValueChangeEvent.getNewValue().toString();        
        if(lsIdService == null){
            getPoTblMain().setVisible(false);
        }
        else{
            //Buscar nombre del servicio
            String lsNomService = 
                new ViewObjectDao().getNomServiceByIdService(lsIdService);
            
            new UtilFaces().refreshTableWhereIterator("ID_SERVICE = "+lsIdService,
                                                      gsEntityIterator,
                                                      getPoTblMain()
                                                      );
            
            getPoTblMain().setVisible(true);
            getPoNomService().setValue(lsNomService);
        }
        
    }

    public void setPoNomService(RichInputText poNomService) {
        this.poNomService = poNomService;
    }

    public RichInputText getPoNomService() {
        return poNomService;
    }

    
}
