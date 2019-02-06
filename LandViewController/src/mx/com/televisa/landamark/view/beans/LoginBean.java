/**
* Project: Paradigm - Landmark 
*
* File: LoginBean.java
*
* Created on:  Enero 09, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/

package mx.com.televisa.landamark.view.beans;

import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.landamark.client.userpermission.types.Usuario;
import mx.com.televisa.landamark.model.AppModuleImpl;
import mx.com.televisa.landamark.users.UserInfoBean;
import mx.com.televisa.landamark.users.UserMenuBean;
import mx.com.televisa.landamark.secman.SecurityManagerWs;

import mx.com.televisa.landamark.util.UtilFaces;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.ApplicationModule;
import oracle.jbo.client.Configuration;

/** Esta clase es un bean que enlaza la pantalla de Login<br/><br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Enero 09, 2019, 12:00 pm
 */
public class LoginBean {
    private RichInputText poUserName;
    private RichInputText poPassword;
    private RichPopup poPopupExitConfirm;

    public LoginBean() {
    }

    
    /**
     * Metodo que dispara el boton de ingresar, valida el usuario y psPassword
     * para la aplicacion de Integracion
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String loginAction() {
        String  lsUserName = 
            getPoUserName().getValue() == null ? null : 
            getPoUserName().getValue().toString();
        String  lsPassword = 
            getPoPassword().getValue() == null ? null : 
            getPoPassword().getValue().toString();      
        boolean lbFlagError = false;
        String  lsErrorMessage = null;
        String  lsTokenSecman;
        if (lsUserName != null && lsPassword != null) {                        
            try {                
                /*lsTokenSecman = 
                    validateSecmanUser(lsUserName, lsPassword); 
                */
                    lsTokenSecman = "123456789"; //TEMPORAL
                if (lsTokenSecman != null) {
                    System.out.println("obteniendo permisos");
                    /*Usuario loUserIntegration = 
                        getSecmanUserPermission(lsUserName);*/
                    //if(loUserIntegration != null){
                    if(true){
                        System.out.println("obteniendo permisos...oko");
                        //Settear Datos--------------------------
                        FacesContext        loContext = FacesContext.getCurrentInstance();
                        ExternalContext     loEctx = loContext.getExternalContext();        
                        HttpServletRequest  loRequest = (HttpServletRequest)loEctx.getRequest();
                        HttpSession         loSession = loRequest.getSession(true);
                        loSession.setAttribute("session.pgmIntegration", "true");
                        UserInfoBean        loUserInfo = 
                            (UserInfoBean) new UtilFaces().resolveExpression("#{UserInfoBean}");            
                        DateFormat          ldDateFormat = 
                            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Date                ldDate = new Date();                    
                        loUserInfo.setPsUserFullName("Bautista Santiago Jorge Luis");//loUserIntegration.getNomMostrar().getNomMostrar());
                        loUserInfo.setPsEmail("jlbautistas@teleevisa.com.mx");//loUserIntegration.getMailUsuario().getMailUsuario());
                        loUserInfo.setPsIdUser("666");//loUserIntegration.getIdUsuario().getIdUsuario());
                        loUserInfo.setPsUserName("jlbautistas");//loUserIntegration.getUserName().getUserName());
                        loUserInfo.setPsDateTimeLogin(ldDateFormat.format(ldDate));
                        loUserInfo.setPsToken(lsTokenSecman);
                        loSession.setAttribute("loggedPgmIntegrationUser", loUserInfo.getPsUserName());                             
                        loSession.setAttribute("loggedPgmIntegrationIdUser", loUserInfo.getPsIdUser()); 
                        //### Asignar Operaciones a Usuario Firmado
                        System.out.println("Asignando operaciones");
                        UserMenuBean loMenu = getUserMenuBean(lsUserName);
                        System.out.println("loMenu.getLsOprCrud(): "+loMenu.getLsOprCrud());
                        System.out.println("loMenu.getLsOprDeleteCron(): "+loMenu.getLsOprDeleteCron());
                        System.out.println("loMenu.getLsOprExecuteCron(): "+loMenu.getLsOprExecuteCron());
                        System.out.println("loMenu.getLsOprInsertCron(): "+loMenu.getLsOprInsertCron());
                        System.out.println("loMenu.getLsRolBitacora(): "+loMenu.getLsRolBitacora());
                        System.out.println("loMenu.getLsRolMonitor(): "+loMenu.getLsRolMonitor());
                        System.out.println("loMenu.getLsRolParametersConfig(): "+loMenu.getLsRolParametersConfig());
                        String              lsUrl = 
                            loEctx.getRequestContextPath() + "/faces/homePage";
                        System.out.println("Redireccionando correctamente........");
                        loEctx.redirect(lsUrl);
                    }
                    else{
                        System.out.println("error al obtener permisos");
                        lbFlagError = true;
                        lsErrorMessage = "No es Posible Obtener Permisos";
                    }
                }
            } catch (IOException loEx) {
                System.out.println("Error al redireccionar a home");
                lbFlagError = true;
                lsErrorMessage = loEx.getMessage();
            } catch (Exception loExp) {
                lbFlagError = true;
                lsErrorMessage = loExp.getMessage();
            }
        }
        else{
            lbFlagError = true;
            lsErrorMessage = "Los Campos Son Requeridos";
        }
        if(lbFlagError){
            FacesMessage loMsg = 
                new FacesMessage(lsErrorMessage);
            loMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, loMsg);
        }
        
        
        return null;
    }
    
    /**
     * Obtiene operaciones de Usuario en Security Manager 
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public UserMenuBean getUserMenuBean(String tsUserName) throws Exception {
        UserMenuBean        loMenu = 
            (UserMenuBean) new UtilFaces().resolveExpression("#{UserMenuBean}");
        String              lsFlag = "false";
        lsFlag = "true"; //TEMPORAL
        loMenu.setLsRolBitacora(lsFlag);
        loMenu.setLsRolGeneralParams(lsFlag);
        loMenu.setLsRolMonitor(lsFlag);
        loMenu.setLsRolNotificationsConfig(lsFlag);
        loMenu.setLsRolParametersConfig(lsFlag);
        loMenu.setLsRolServicesConfig(lsFlag);
        loMenu.setLsRolTaskConfig(lsFlag);                        
        loMenu.setLsOprCrud(lsFlag);
        loMenu.setLsOprDeleteCron(lsFlag);
        loMenu.setLsOprExecuteCron(lsFlag);
        loMenu.setLsOprInitStopCron(lsFlag);
        loMenu.setLsOprInsertCron(lsFlag);
        loMenu.setLsRolUsrVtaTradicional(lsFlag);
        /*
        List<String>        laOperaciones = 
            getSecmanUserOperations(tsUserName);
        for (int liI = 0; liI < laOperaciones.size(); liI++) {
            lsFlag = "true";
            if (laOperaciones.get(liI).equalsIgnoreCase("RolBitacora"))
                loMenu.setLsRolBitacora(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("RolGeneralParams"))
                loMenu.setLsRolGeneralParams(lsFlag);                            
                                        
            if (laOperaciones.get(liI).equalsIgnoreCase("RolMonitor"))
                loMenu.setLsRolMonitor(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("RolNotificationsConfig"))
                loMenu.setLsRolNotificationsConfig(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("RolParametersConfig"))
                loMenu.setLsRolParametersConfig(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("RolServicesConfig"))
                loMenu.setLsRolServicesConfig(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("RolTaskConfig"))
                loMenu.setLsRolTaskConfig(lsFlag);                        
            
            if (laOperaciones.get(liI).equalsIgnoreCase("OprCrud"))
                loMenu.setLsOprCrud(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("OprDeleteCron"))
                loMenu.setLsOprDeleteCron(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("OprExecuteCron"))
                loMenu.setLsOprExecuteCron(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("OprInitStopCron"))
                loMenu.setLsOprInitStopCron(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("OprInsertCron"))
                loMenu.setLsOprInsertCron(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("RolUsrVtaTradicional"))
                loMenu.setLsRolUsrVtaTradicional(lsFlag);                                
            
        }
        */
        return loMenu;
    }
    
    /**
     * Obtiene operaciones de Usuario en Security Manager 
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<String> getSecmanUserOperations(String tsUserName) throws Exception {
        List<String>  lsResponse = new ArrayList<String>();        
        SecurityManagerWs loSecMan = new SecurityManagerWs();
        try {
            lsResponse = 
                loSecMan.getUserOperations(tsUserName,
                                          "IntegrationEveTv");
        } catch (Exception loEx) {
            throw new Exception(loEx.getMessage());
        }
        return lsResponse;
    }

    public String clearAction() {
        getPoUserName().setValue(null);
        getPoPassword().setValue(null);
        return null;
    }
    
    /**
     * Valida usuario y password en Security Manager y verifica <br>
     * si el usuario tiene permiso de acceder con la aplicacion deseada
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String validateSecmanUser(String tsUserName, 
                                     String tsPassword
                                    ) throws Exception {
        String            lsResponse = null;   
        SecurityManagerWs loSecMan = new SecurityManagerWs();
        try {
            System.out.println("Validando temporalmente en Integracion");
            lsResponse = 
                loSecMan.loginSecurityManager(tsUserName,
                                              tsPassword, 
                                              "IntegrationEveTv");

        } catch (Exception loEx) {
            throw new Exception(loEx.getMessage());
        }
        System.out.println("Token: "+lsResponse);
        return lsResponse;
    }
        
    /**
     * Sale de la aplicacion y elimina la session
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String exitAppIntegraion() {
        String              lsAmDef =
            "mx.com.televisa.landamark.model.AppModuleImpl";
        String              lsConfig = "AppModuleLocal";
        ExternalContext     loEctx = 
            FacesContext.getCurrentInstance().getExternalContext();
        String              lsUrl = loEctx.getRequestContextPath() + "/faces/indexPage";
        HttpSession         loSession = (HttpSession) loEctx.getSession(false);
        HttpServletResponse loResponse = (HttpServletResponse) loEctx.getResponse();
        SecurityManagerWs   loSecMan = new SecurityManagerWs();
        try {
            System.out.println("Conectando con base de datos");
            ApplicationModule   loAm =
                Configuration.createRootApplicationModule(lsAmDef, lsConfig);
            AppModuleImpl loService = (AppModuleImpl)loAm;
            System.out.println("Salir de Secman");
            try {
                String lsUserName = "jlbautistas";
                    //loService.getValueSessionFromAttribute("loggedIntegrationUser") == null ? null :
                    //loService.getValueSessionFromAttribute("loggedIntegrationUser").toString();                
                loSecMan.logoutSecurityManager(lsUserName, "IntegrationEveTv");
                System.out.println("Saliendo de Secman correctamente");
            } catch (Exception loEx) {
                System.out.println("Util-ERROR: "+loEx.getMessage());
            } finally {
                System.out.println("liberando conexión con base de datos");
                Configuration.releaseRootApplicationModule(loAm, true);
                loAm.remove();
            }
        } catch (Exception loEx) {
            System.out.println("No es posible conectar con la base de datos "+loEx.getMessage());
        }
        loSession.invalidate();
        try {
            loResponse.sendRedirect(lsUrl);
            FacesContext.getCurrentInstance().responseComplete();
        }
        catch (Exception loEx) {
            System.out.println("Error al salir "+loEx.getMessage());
            loEx.printStackTrace();
        }
        return null;
    }

    /**
     * Cancela accion salir de la aplicacion
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String cancelExitAppIntegraion() {
        new UtilFaces().hidePopup(getPoPopupExitConfirm());
        return null;
    }    
    
    
    /**
     * Obtiene permisos de Usuario en Security Manager 
     * @autor Jorge Luis Bautista Santiago
     * @return Usuario
     */
    public Usuario getSecmanUserPermission(String tsUserName) 
    throws Exception {
        Usuario          lsResponse = null;        
        SecurityManagerWs loSecMan = new SecurityManagerWs();
        try {
            lsResponse = 
                loSecMan.getSecmanUserDataSession(tsUserName,
                                              "IntegrationEveTv");
        } catch (Exception loEx) {
            throw new Exception(loEx.getMessage());
        }
        return lsResponse;
    }
    
    /****************** SETTERS AND GETTERS ******************************/    
    
    public void setPoUserName(RichInputText poUserName) {
        this.poUserName = poUserName;
    }

    public RichInputText getPoUserName() {
        return poUserName;
    }

    public void setPoPassword(RichInputText poPassword) {
        this.poPassword = poPassword;
    }

    public RichInputText getPoPassword() {
        return poPassword;
    }

    public void setPoPopupExitConfirm(RichPopup poPopupExitConfirm) {
        this.poPopupExitConfirm = poPopupExitConfirm;
    }

    public RichPopup getPoPopupExitConfirm() {
        return poPopupExitConfirm;
    }
}
