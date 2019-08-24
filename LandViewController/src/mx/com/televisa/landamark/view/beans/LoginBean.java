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
import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.users.UserInfoBean;
import mx.com.televisa.landamark.users.UserMenuBean;
import mx.com.televisa.landamark.secman.SecurityManagerWs;

import mx.com.televisa.landamark.users.UserOperationList;
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
    private RichPopup poLoginPopup;

    public void setPoLoginPopup(RichPopup poLoginPopup) {
        this.poLoginPopup = poLoginPopup;
    }

    public RichPopup getPoLoginPopup() {
        return poLoginPopup;
    }

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
                
                //lsTokenSecman = validateSecmanUser(lsUserName, lsPassword); 
                lsTokenSecman = "123456789"; //TEMPORAL
                if (lsTokenSecman != null) {
                    Usuario loUserIntegration = getSecmanUserPermission(lsUserName);
                    if(loUserIntegration != null){
                    //if(true){
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
                        /*
                        loUserInfo.setPsUserFullName("Bautista Santiago Jorge Luis");//loUserIntegration.getNomMostrar().getNomMostrar());
                        loUserInfo.setPsEmail("jlbautistas@teleevisa.com.mx");//loUserIntegration.getMailUsuario().getMailUsuario());
                        loUserInfo.setPsIdUser("666");//loUserIntegration.getIdUsuario().getIdUsuario());
                        loUserInfo.setPsUserName("jlbautistas");//loUserIntegration.getUserName().getUserName());
                        */
                        loUserInfo.setPsUserFullName(loUserIntegration.getNomMostrar().getNomMostrar());
                        loUserInfo.setPsEmail(loUserIntegration.getMailUsuario().getMailUsuario());
                        loUserInfo.setPsIdUser(loUserIntegration.getIdUsuario().getIdUsuario());
                        loUserInfo.setPsUserName(loUserIntegration.getUserName().getUserName());
                        
                        loUserInfo.setPsDateTimeLogin(ldDateFormat.format(ldDate));
                        loUserInfo.setPsToken(lsTokenSecman);
                        loSession.setAttribute("loggedPgmIntegrationUser", loUserInfo.getPsUserName());                             
                        loSession.setAttribute("loggedPgmIntegrationIdUser", loUserInfo.getPsIdUser()); 
                        
                        UserMenuBean loUserMenuBean = getUserMenuBean(lsUserName);
                        
                        //Agregar informacion de Cortes y Programa
                        loSession.setAttribute("idServiceCortes", loUserMenuBean.getLsUserIdServiceCortes());
                        loSession.setAttribute("listChannelsCortes", loUserMenuBean.getLsUserListChannelsCortes());                             
                        
                        //Agregar informacion de Actualizacion de Precios
                        loSession.setAttribute("idServicePrecios", loUserMenuBean.getLsUserIdServicePrecios());
                        loSession.setAttribute("listChannelsPrecios", loUserMenuBean.getLsUserListChannelsPrecios());                             
                        
                        String              lsUrl = 
                            loEctx.getRequestContextPath() + "/faces/homePage";
                        loEctx.redirect(lsUrl);
                    }
                    else{
                        lbFlagError = true;
                        lsErrorMessage = "No es Posible Obtener Permisos";
                    }
                }
            } catch (IOException loEx) {
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
     * @return UserMenuBean
     */
    public UserMenuBean getUserMenuBean(String tsUserName) throws Exception {
        String lsChannels = "";
        UserOperationList        loUserOperationList = 
            (UserOperationList) new UtilFaces().resolveExpression("#{UserOperationList}");
        loUserOperationList.setLsUserName(tsUserName);
        List<String> laList = new ArrayList<String>();
        
        UserMenuBean        loMenu = 
            (UserMenuBean) new UtilFaces().resolveExpression("#{UserMenuBean}");
        String              lsFlag = "false";
        lsFlag = "true";
        
        loMenu.setLsPantallaBitacora(lsFlag);
        loMenu.setLsPantallaGralConfig(lsFlag);
        loMenu.setLsPantallaLoadFile(lsFlag);
        loMenu.setLsPantallaMapping(lsFlag);
        loMenu.setLsPantallaMonitor(lsFlag);
        loMenu.setLsPantallaNotifications(lsFlag);
        loMenu.setLsPantallaProcess(lsFlag);
        loMenu.setLsPantallaStatusFiles(lsFlag);
        loMenu.setLsPantallaCreateFile(lsFlag);
        
        loMenu.setLsOprDeleteCron("true");
        loMenu.setLsOprExecuteCron("true");
        loMenu.setLsOprInitStopCron("true");
        loMenu.setLsOprInsertCron("true");
        
        List<String>        laOperaciones = 
            getSecmanUserOperations(tsUserName);
        for (int liI = 0; liI < laOperaciones.size(); liI++) {
            //System.out.println("####### Operation["+laOperaciones.get(liI)+"] ######");
            lsFlag = "true";
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaBitacora"))
                loMenu.setLsPantallaBitacora(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaGralConfig"))
                loMenu.setLsPantallaGralConfig(lsFlag);                            
                                        
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaLoadFile"))
                loMenu.setLsPantallaLoadFile(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaMapping"))
                loMenu.setLsPantallaMapping(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaMonitor"))
                loMenu.setLsPantallaMonitor(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaNotifications"))
                loMenu.setLsPantallaNotifications(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaProcess"))
                loMenu.setLsPantallaProcess(lsFlag);                        
            
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaStatusFiles")){
                loMenu.setLsPantallaStatusFiles(lsFlag);    
            }
        if (laOperaciones.get(liI).equalsIgnoreCase("PantallaCreationFiles")){
            loMenu.setLsPantallaCreateFile(lsFlag);    
        }
        if (laOperaciones.get(liI).equalsIgnoreCase("PantallaPriceFiles")){
            loMenu.setLsPantallaPrecios(lsFlag);    
        }
            if(laOperaciones.get(liI).startsWith("Ch-")){
                String[] laChn = laOperaciones.get(liI).split("-");
                if(laChn.length > 1){
                    laList.add(laChn[1]);
                    //System.out.println("Canal: "+laChn[1]);
                    lsChannels += "'"+laChn[1]+"',";
                    //System.out.println("Canales: "+laChn[1]);
                    loUserOperationList.setLaOpertations(laList);
                }
            }
            if(lsChannels.length() > 0){
                loMenu.setLsUserListChannelsCortes(lsChannels.substring(0, lsChannels.length()-1));    
                loMenu.setLsUserListChannelsPrecios(lsChannels.substring(0, lsChannels.length()-1));    
            }
                        
        }
        
        //Obtener el idServicio de cortes y programas para el usuario firmado
        List<String> laListCortes = getCortesyProgramasByUser(tsUserName);
        
        if(Integer.parseInt(laListCortes.get(0)) > 0){
            loMenu.setLsUserIdServiceCortes(laListCortes.get(0));
            loMenu.setLsUserFecInicialCortes(laListCortes.get(1));
            loMenu.setLsUserFecFinalCortes(laListCortes.get(2));
            loMenu.setLsUserNomServiceCortes("Generar Archivo de Cortes y Programas");
        }else{
            loMenu.setLsUserIdServiceCortes(laListCortes.get(0));
            loMenu.setLsUserFecInicialCortes("");
            loMenu.setLsUserFecFinalCortes("");
            loMenu.setLsUserNomServiceCortes("Generar Archivo de Cortes y Programas");
        }
        
        //Obtener el idServicio de Actualizacion de Precios para el usuario firmado
        List<String> laListPrecios = getActualizacionPreciosByUser(tsUserName);
        
        if(Integer.parseInt(laListPrecios.get(0)) > 0){
            loMenu.setLsUserIdServicePrecios(laListPrecios.get(0));
            loMenu.setLsUserFecInicialPrecios(laListPrecios.get(1));
            loMenu.setLsUserFecFinalPrecios(laListPrecios.get(2));
            loMenu.setLsUserNomServicePrecios("Generar Archivo de Cortes y Programas");
        }else{
            loMenu.setLsUserIdServicePrecios(laListPrecios.get(0));
            loMenu.setLsUserFecInicialPrecios("");
            loMenu.setLsUserFecFinalPrecios("");
            loMenu.setLsUserNomServicePrecios("Generar Archivo de Cortes y Programas");
        }
        
        
        
        return loMenu;
    }
    
    /**
     * Obtiene el idServicio, fecha inicial y fecha final, si es
     * que el usuario ha ejecutado al menos una vez el servicio de Cortes y Programas
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<String> getCortesyProgramasByUser(String tsUser){        
        List<String> laList = new ArrayList<String>();
        ViewObjectDao loViewObjectDao = new ViewObjectDao();
        laList = loViewObjectDao.getServiceCortesByUser(tsUser);
        return laList;
    }
    
    /**
     * Obtiene el idServicio, fecha inicial y fecha final, si es
     * que el usuario ha ejecutado al menos una vez el servicio de Cortes y Programas
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<String> getActualizacionPreciosByUser(String tsUser){        
        List<String> laList = new ArrayList<String>();
        ViewObjectDao loViewObjectDao = new ViewObjectDao();
        laList = loViewObjectDao.getServicePreciosByUser(tsUser);
        return laList;
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
                                          "IntegrationLandmark");
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
            lsResponse = 
                loSecMan.loginSecurityManager(tsUserName,
                                              tsPassword, 
                                              "IntegrationLandmark");

        } catch (Exception loEx) {
            throw new Exception(loEx.getMessage());
        }
        return lsResponse;
    }
        
    /**
     * Sale de la aplicacion y elimina la session
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String exitAppIntegraion() {
        /*String              lsAmDef =
            "mx.com.televisa.landamark.model.AppModuleImpl";
        String              lsConfig = "AppModuleLocal";
        ExternalContext     loEctx = 
            FacesContext.getCurrentInstance().getExternalContext();
        String              lsUrl = loEctx.getRequestContextPath() + "/faces/indexPage";
        HttpSession         loSession = (HttpSession) loEctx.getSession(false);
        HttpServletResponse loResponse = (HttpServletResponse) loEctx.getResponse();
        SecurityManagerWs   loSecMan = new SecurityManagerWs();
        try {
            ApplicationModule   loAm =
                Configuration.createRootApplicationModule(lsAmDef, lsConfig);
            AppModuleImpl loService = (AppModuleImpl)loAm;
            try {
                String lsUserName = 
                    loService.getValueSessionFromAttribute("loggedPgmIntegrationUser") == null ? null :
                    loService.getValueSessionFromAttribute("loggedPgmIntegrationUser").toString();                
                loSecMan.logoutSecurityManager(lsUserName, "IntegrationLandmark");
            } catch (Exception loEx) {
                System.out.println("Util-ERROR: "+loEx.getMessage());
            } finally {
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
        */
        
        try {
            ExternalContext     loEctx = 
                FacesContext.getCurrentInstance().getExternalContext();
            HttpServletResponse loResponse = (HttpServletResponse) loEctx.getResponse();
            EntityMappedDao loEntityMappedDao = new EntityMappedDao();
            String lsUrlLogout = 
                loEntityMappedDao.getGeneralParameter("URL_LOGOUT", "SAML2_AUTHENTICATION");
            //String lsUrlLogout = "https://login.windows.net/87e71bd2-2a6d-4deb-8dca-d9b3fd7481b9/oauth2/logout";
            loResponse.sendRedirect(lsUrlLogout);
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
                                              "IntegrationLandmark");
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

    public String closeLoginPopup() {
        poLoginPopup.hide();
        return null;
    }
    
    public String redirectToHome() {        
        ExternalContext     loEctx = 
            FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest loReq = (HttpServletRequest) loEctx.getRequest();
        HttpSession         loSession = loReq.getSession();
        loReq.setAttribute("loginUserReq", true);
        loSession.setAttribute("loginUserSes", true);
        
        return "homePage";
    }
    
    public String redirectToHome2() {
        String  lsUserName = "jserranod";            
        //String  lsPassword = "Arisbeth31.25";
        boolean lbFlagError = false;
        String  lsErrorMessage = null;
        String  lsTokenSecman;
        //if (lsUserName != null && lsPassword != null) {                        
            try {                
                
                //lsTokenSecman = validateSecmanUser(lsUserName, lsPassword); 
                lsTokenSecman = "123456789"; //TEMPORAL
                if (lsTokenSecman != null) {
                    Usuario loUserIntegration = getSecmanUserPermission(lsUserName);
                    if(loUserIntegration != null){
                    //if(true){
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
                        /*
                        loUserInfo.setPsUserFullName("Bautista Santiago Jorge Luis");//loUserIntegration.getNomMostrar().getNomMostrar());
                        loUserInfo.setPsEmail("jlbautistas@televisa.com.mx");//loUserIntegration.getMailUsuario().getMailUsuario());
                        loUserInfo.setPsIdUser("666");//loUserIntegration.getIdUsuario().getIdUsuario());
                        loUserInfo.setPsUserName("jlbautistas");//loUserIntegration.getUserName().getUserName());
                        */
                        loUserInfo.setPsUserFullName(loUserIntegration.getNomMostrar().getNomMostrar());
                        loUserInfo.setPsEmail(loUserIntegration.getMailUsuario().getMailUsuario());
                        loUserInfo.setPsIdUser(loUserIntegration.getIdUsuario().getIdUsuario());
                        loUserInfo.setPsUserName(loUserIntegration.getUserName().getUserName());
                        
                        loUserInfo.setPsDateTimeLogin(ldDateFormat.format(ldDate));
                        loUserInfo.setPsToken(lsTokenSecman);
                        loSession.setAttribute("loggedPgmIntegrationUser", loUserInfo.getPsUserName());                             
                        loSession.setAttribute("loggedPgmIntegrationIdUser", loUserInfo.getPsIdUser()); 
                        
                        UserMenuBean loUserMenuBean = getUserMenuBean(lsUserName);
                        
                        //Agregar informacion de Cortes y Programa
                        loSession.setAttribute("idServiceCortes", loUserMenuBean.getLsUserIdServiceCortes());
                        loSession.setAttribute("listChannelsCortes", loUserMenuBean.getLsUserListChannelsCortes());                             
                        
                        //Agregar informacion de Actualizacion de Precios
                        loSession.setAttribute("idServicePrecios", loUserMenuBean.getLsUserIdServicePrecios());
                        loSession.setAttribute("listChannelsPrecios", loUserMenuBean.getLsUserListChannelsPrecios());                             
                        
                        String              lsUrl = 
                            loEctx.getRequestContextPath() + "/faces/homePage";
                        loEctx.redirect(lsUrl);
                    }
                    else{
                        lbFlagError = true;
                        lsErrorMessage = "No es Posible Obtener Permisos";
                    }
                }
            } catch (IOException loEx) {
                lbFlagError = true;
                lsErrorMessage = loEx.getMessage();
            } catch (Exception loExp) {
                lbFlagError = true;
                lsErrorMessage = loExp.getMessage();
            }
        /*}
        else{
            lbFlagError = true;
            lsErrorMessage = "Los Campos Son Requeridos";
        }*/
        if(lbFlagError){
            FacesMessage loMsg = 
                new FacesMessage(lsErrorMessage);
            loMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, loMsg);
        }
        return null;
    }
    
}
