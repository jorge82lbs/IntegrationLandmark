/**
* Project: Integration Landmark
*
* File: UtilFaces.java
*
* Created on:  Febrero 6, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/
package mx.com.televisa.landamark.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import mx.com.televisa.landamark.model.AppModuleImpl;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesLogRowBean;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.ApplicationModule;
import oracle.jbo.ViewObject;
import oracle.jbo.client.Configuration;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
//import javax.ws.rs.core.UriBuilder;

/** Esta clase ofrece metodos de utileria para ayuda en el desarrollo<br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Febrero 14, 2017, 12:00 pm
 */
public class UtilFaces {
    
    String                            gsAppModule = "AppModuleDataControl";
    String                            gsAmDef = "mx.com.televisa.landamark.model.AppModuleImpl";
    String                            gsConfig = "AppModuleLocal";
    
    /**
     * Muestra popup generico en pantalla
     * @autor Jorge Luis Bautista Santiago  
     * @param toPopup
     * @return void
     */
    public void showPopup(RichPopup toPopup) {
        FacesContext             loFacesContext = 
            FacesContext.getCurrentInstance();
        ExtendedRenderKitService loService =
            org.apache.myfaces.trinidad.util.Service.getRenderKitService(loFacesContext,
                                                                         ExtendedRenderKitService.class);
        loService.addScript(loFacesContext,
                          "AdfPage.PAGE.findComponent('" + toPopup.getClientId(loFacesContext) +
                          "').show();");
    }

    /**
     * Oculta popup generico de la pantalla pantalla
     * @autor Jorge Luis Bautista Santiago  
     * @param toPopup
     * @return void
     */
    public void hidePopup(RichPopup toPopup) {
        FacesContext             loFacesContext = FacesContext.getCurrentInstance();
        ExtendedRenderKitService loService =
            org.apache.myfaces.trinidad.util.Service.getRenderKitService(loFacesContext,
                                                                         ExtendedRenderKitService.class);
        loService.addScript(loFacesContext,
                          "AdfPage.PAGE.findComponent('" + 
                          toPopup.getClientId(loFacesContext) +
                          "').hide();");
    }
    
    /**
     * Resuelve expresion para obtener objeto
     * @autor Jorge Luis Bautista Santiago  
     * @param tsExpression
     * @return Object
     */
    public Object resolveExpression(String tsExpression) {
        FacesContext      loFacesContext = FacesContext.getCurrentInstance();
        Application       loApp = loFacesContext.getApplication();
        ExpressionFactory loElFactory = loApp.getExpressionFactory();
        ELContext         loElContext = loFacesContext.getELContext();
        ValueExpression   loValueExp =
            loElFactory.createValueExpression(loElContext, tsExpression,
                                            Object.class);
        return loValueExp.getValue(loElContext);
    }
    
    /**
     * Actualiza iterador de la tabla principal con condiciones de busqueda
     * @autor Jorge Luis Bautista Santiago
     * @param tsWhere
     * @param tsIteraror
     * @param toRichTable
     * @return void
     */
    public void refreshTableWhereIterator(String tsWhere, 
                                          String tsIteraror, 
                                          RichTable toRichTable
                                          ) {
        try{
            DCBindingContainer loDCBinding = 
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();         
            DCIteratorBinding  loDCIterator = 
                loDCBinding.findIteratorBinding(tsIteraror);        
            ViewObject         loVO = loDCIterator.getViewObject();
            loVO.setWhereClause(tsWhere);
            //System.out.println(loVO.getQuery());   
            loVO.executeQuery();
            AdfFacesContext.getCurrentInstance().addPartialTarget(toRichTable);
        }catch(Exception loIntExp){
            System.out.println("Error al actualiza front tsIteraror "+tsIteraror);   
            System.out.println(loIntExp.getMessage());
        }
    }

    /**
     * Obtiene la expresion cron desde un servicio REST
     * @autor Jorge Luis Bautista Santiago
     * @param tsUrl
     * @return String
     */
    public String getCronExpression(String tsUrl){
        System.out.println("Obtener expression mediante rest["+tsUrl+"]");
        String lsCronExpr = null;
        /*try {
            ClientConfig loConfig = new DefaultClientConfig(); 
            Client       loClient = Client.create(loConfig);        
            WebResource  loService = loClient.resource(UriBuilder.fromUri(tsUrl).build());
            lsCronExpr = loService.get(String.class);
            System.out.println("success!!");
        }catch(Exception loExp) {    
            loExp.printStackTrace();
            System.out.println("ERROR en REST CRON: "+loExp.getMessage());
        }*/
        lsCronExpr = "NA";
        return lsCronExpr;
    }
    
    /**
     * Obtiene la expresion cron desde un servicio REST para minutos
     * @autor Jorge Luis Bautista Santiago
     * @param tsEvery
     * @return String
     */
    public String getBuildUrlRestMinutes(String tsEvery){
        String lsUrlExprCorn = "http://www.cronmaker.com/rest/minutes/"+tsEvery;
        return lsUrlExprCorn;
    }
    
    /**
     * Obtiene la expresion cron desde un servicio REST para horas
     * @autor Jorge Luis Bautista Santiago
     * @param tsEvery
     * @param tsEveryValue
     * @param psAt
     * @param tsHour
     * @param tsMinute
     * @return String
     */
    public String getBuildUrlRestHours(String tsEvery,
                                       String tsEveryValue,
                                       String tsAt,
                                       String tsHour,
                                       String tsMinute){
        String lsUrlExprCorn = "http://www.cronmaker.com/rest/hourly/";
        if(tsEvery.equalsIgnoreCase("true")){
            lsUrlExprCorn += "every/" + tsEveryValue;
        }else{
            lsUrlExprCorn += "at?hour=" + tsHour + "&minute=" + tsMinute;
        }
        return lsUrlExprCorn;
    }
    
    /**
     * Obtiene la expresion cron desde un servicio REST para dias
     * @autor Jorge Luis Bautista Santiago
     * @param tsEveryDay
     * @param psWeekDays
     * @param tsHour
     * @param tsMinute
     * @return String
     */
    public String getBuildUrlRestDays(String tsEveryDay,
                                      String tsWeekDays,
                                      String tsHour,
                                      String tsMinute
                                     ){
        String lsUrlExprCorn = "http://www.cronmaker.com/rest/daily/";      
        if(tsEveryDay.equalsIgnoreCase("true")){
            lsUrlExprCorn += "everyDay?hour=" + tsHour + "&minute=" + tsMinute;
        }else{
            lsUrlExprCorn += "weekdays?hour=" + tsHour + "&minute=" + tsMinute;
        }
        return lsUrlExprCorn;
    }
    
    /**
     * Obtiene la expresion cron desde un servicio REST para semanas
     * @autor Jorge Luis Bautista Santiago
     * @param tsDays
     * @param tsHour
     * @param tsMinute
     * @return String
     */
    public String getBuildUrlRestWeeks(String tsDays,
                                       String tsHour,
                                       String tsMinute
                                     ){
        String lsUrlExprCorn = "http://www.cronmaker.com/rest/weekly/?days=";   
        lsUrlExprCorn += tsDays + "&";
        lsUrlExprCorn += "hour=" + tsHour + "&minute=" + tsMinute;
        return lsUrlExprCorn;
    }
    
    /**
     * Obtiene la expresion cron desde un servicio REST para Meses
     * @autor Jorge Luis Bautista Santiago
     * @param tsDay
     * @param tsDayValue
     * @param lsOfEvery
     * @param tsOfEveryValue
     * @param tsThePlace
     * @param tsThePlaceDay
     * @param tsHour
     * @param tsMinute
     * @return String
     */
    public String getBuildUrlRestMonths(String tsDay,
                                        String tsDayValue,
                                        String tsOfEvery,
                                        String tsOfEveryValue,
                                        String tsThePlace,
                                        String tsThePlaceDay,
                                        String tsHour,
                                        String tsMinute
                                       ){
        String lsUrlExprCorn = "http://www.cronmaker.com/rest/monthly/";   
        if(tsDay.equalsIgnoreCase("true")){
            lsUrlExprCorn += "day/" + tsDayValue + "/ofEvery/" + tsOfEveryValue + "?";    
        }else{        
            lsUrlExprCorn += tsThePlace + "/" + tsThePlaceDay + "/ofEvery/" + tsOfEveryValue + "?";
        }
        lsUrlExprCorn += "hour=" + tsHour + "&minute=" + tsMinute;
        return lsUrlExprCorn;
    }
    
    /**
     * Obtiene la expresion cron desde un servicio REST para Meses
     * @autor Jorge Luis Bautista Santiago
     * @param tsHour
     * @param tsMinute
     * @return String
     */
    public String buildTimeCron(String tsHour, String tsMinute){
        String lsResponse = "";
        if(tsHour != null && tsMinute != null){
            if(tsHour.length() == 1){
                lsResponse += "0" + tsHour;
            }else{
                lsResponse += tsHour;
            }
            lsResponse += ":";
            if(tsMinute.length() == 1){
                lsResponse += "0" + tsMinute;
            }else{
                lsResponse += tsMinute;
            }
        }else{
            lsResponse = "ERROR";
        }
        return lsResponse;
    }
    
    /**
     * Desencripta una cadena en base a una psKey
     * @autor Jorge Luis Bautista Santiago
     * @param tsText
     * @param tsKey
     * @return String
     */
    public String decryptObject(String tsText,
                                 String tsKey) throws Exception {
        try{
            Cipher loCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] laKeyBytes = new byte[16];
            byte[] laByteEncode = tsKey.getBytes("UTF-8");
            int    liLength = laByteEncode.length;
            if (liLength > laKeyBytes.length)
                liLength = laKeyBytes.length;
            System.arraycopy(laByteEncode, 0, laKeyBytes, 0, liLength);
            SecretKeySpec   loKeySpec = new SecretKeySpec(laKeyBytes, "AES");
            IvParameterSpec loIvSpec = new IvParameterSpec(laKeyBytes);
            loCipher.init(Cipher.DECRYPT_MODE, loKeySpec, loIvSpec);
            BASE64Decoder   loDecoder = new BASE64Decoder();
            byte[]          laResults = 
                loCipher.doFinal(loDecoder.decodeBuffer(tsText));             
            return new String(laResults, "UTF-8");            
        }catch(Exception loEx){
            System.out.println("ERROR: "+loEx.getMessage());
            return null;
        }
    }

    /**
     * Encripta una cadena en base a una psKey
     * @autor Jorge Luis Bautista Santiago
     * @param tsText
     * @param tsKey
     * @return String
     */
    public String encryptObject(String tsText,
                                String tsKey) throws Exception {
        try{
            Cipher loCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] laKeyBytes = new byte[16];
            byte[] laByteEncode = tsKey.getBytes("UTF-8");
            int    liLength = laByteEncode.length;
            if (liLength > laKeyBytes.length)
                liLength = laKeyBytes.length;
            System.arraycopy(laByteEncode, 0, laKeyBytes, 0, liLength);
            SecretKeySpec   loKeySpec = new SecretKeySpec(laKeyBytes, "AES");
            IvParameterSpec loIvSpec = new IvParameterSpec(laKeyBytes);
            loCipher.init(Cipher.ENCRYPT_MODE, loKeySpec, loIvSpec);
            byte[]          laResponse = loCipher.doFinal(tsText.getBytes("UTF-8"));
            BASE64Encoder   loEncoder = new BASE64Encoder();
            return loEncoder.encode(laResponse);
        }catch(Exception loEx){
            return null;
        }
    }

    /**
    * Obtiene la psKey para codificar y decodificar
    * @autor Jorge Luis Bautista Santiago
    * @return String
    */
    public String getKeyDecoder() {
        String                    lsKey = "LFXqSn21ptd+rNihAuZeMg==";        
        ApplicationModule         loAm =
            Configuration.createRootApplicationModule(gsAmDef, gsConfig);
        AppModuleImpl loService = (AppModuleImpl)loAm;
        try {
            lsKey = loService.getKeyDecoderUserFromDb();
        } catch (Exception loEx) {
            lsKey = "";
        } finally {
            Configuration.releaseRootApplicationModule(loAm, true);
            loAm.remove();
        }
        return lsKey;
    }
    
    /**
     * Obtiene parametro general por nombre
     * @autor Jorge Luis Bautista Santiago
     * @param tsParameterName
     * @return String
     */
    public String getConfigParameterByName(String tsParameterName) {
        String                    lsParameterValue = null;
        ApplicationModule         loAm =
            Configuration.createRootApplicationModule(gsAmDef, gsConfig);
        AppModuleImpl loService = (AppModuleImpl)loAm;
        try {
            lsParameterValue = loService.getParameterValue(tsParameterName);
        } catch (Exception loEx) {
            ;
        } finally {
            Configuration.releaseRootApplicationModule(loAm, true);
            loAm.remove();
        }
        return lsParameterValue;
    }
    
    /**
     * Obtiene el ID parametro general por nombre
     * @autor Jorge Luis Bautista Santiago
     * @param tsParameterName
     * @return String
     */
    public Integer getIdConfigParameterByName(String tsParameterName) {
        Integer                   lsIdParameter = null;
        ApplicationModule         loAm =
            Configuration.createRootApplicationModule(gsAmDef, gsConfig);
        AppModuleImpl loService = (AppModuleImpl)loAm;
        try {
            lsIdParameter = loService.getIdParameterValue(tsParameterName);
        } catch (Exception loEx) {
            ;
        } finally {
            Configuration.releaseRootApplicationModule(loAm, true);
            loAm.remove();
        }
        return lsIdParameter;
    }
    
    /**
     * Agrega registro en bitacora del servicio
     * @autor Jorge Luis Bautista Santiago
     * @param toBean
     * @return void
     */
    public void insertBitacoraServiceService(LmkIntServiceBitacoraRowBean loBean) {
       ApplicationModule         loAm =
           Configuration.createRootApplicationModule(gsAmDef, gsConfig);
       AppModuleImpl loService = (AppModuleImpl)loAm;
       try {
           loService.insertServiceBitacoraModel(loBean);
       } catch (Exception loEx) {
           System.out.println("Util-ERROR(insertBitacoraServiceService): " + loEx.getMessage());
       } finally {
           Configuration.releaseRootApplicationModule(loAm, true);
           loAm.remove();
       }
    }
    
    /**
     * Agrega registro en bitacora del servicio
     * @autor Jorge Luis Bautista Santiago
     * @param piIdLogServices
     * @param tiIdService
     * @param piIndProcess
     * @param psIndResponse
     * @param piNumUser
     * @param piNumEvtbProcessId
     * @param piNumPgmProcessID
     * @param psProceso
     * @return void
     */
    public void updateStatusCronService(Integer tiIdService,
                                        String tsStatus,
                                        String tsUserName,
                                        String tsIdUser,
                                        String tsStatCron
                                        ) {
       ApplicationModule         loAm =
           Configuration.createRootApplicationModule(gsAmDef, gsConfig);
       AppModuleImpl loService = (AppModuleImpl)loAm;
       try {
           loService.updateStatusCronConfigModel(tiIdService,                                                
                                                 tsStatus,
                                                 tsUserName,
                                                 tsIdUser,
                                                 tsStatCron
                                                 );
       } catch (Exception loEx) {
           System.out.println("Util-ERROR(updateStatusCronService): " + loEx.getMessage());
       } finally {
           Configuration.releaseRootApplicationModule(loAm, true);
           loAm.remove();
       }
    }
    
    
    /**
     * Obtiene, en base a la fecha, el id_paradigm a manejar en intergracion
     * @autor Jorge Luis Bautista Santiago     
     * @return Integer
     */
    public String getIdBitacora(){
        String     lsResponse = null;
        DateFormat loDf = new SimpleDateFormat("yyyyMMddHHmmss");
        lsResponse = loDf.format(new Date(System.currentTimeMillis()));
        return lsResponse;
    }
    
    /**
     * Obtiene, en base a la fecha, el dia de la semana actual
     * @autor Jorge Luis Bautista Santiago     
     * @return String
     */
    public String getCurrentDayOfWeek(){
        String   lsResponse = null;
        Calendar loCal = Calendar.getInstance();
        String[] laStrDays = new String[]{
                                        "SUNDAY",
                                        "MONDAY",
                                        "TUESDAY",
                                        "WEDNESDAY",
                                        "THURSDAY",
                                        "FRIDAY",
                                        "SATURDAY"};
       
        lsResponse = laStrDays[loCal.get(Calendar.DAY_OF_WEEK) - 1];
        return lsResponse;
    }
    
    /**
     * Obtiene, en base a la fecha, la hora actual
     * @autor Jorge Luis Bautista Santiago     
     * @return String
     */
    public String getCurrentHour(){
        String   lsResponse = null;
        Calendar loCal = Calendar.getInstance();
        int      liHour = loCal.get(Calendar.HOUR_OF_DAY);
        int      liMin = loCal.get(Calendar.MINUTE);
        String   lsZero = "";
        if(liMin < 10){
            lsZero = "0";
        }
        lsResponse = String.valueOf(liHour) + ":" + lsZero + String.valueOf(liMin);
        return lsResponse;
    }
    
    /**
     * Compara hora actual con hora deadline, devuelve verdadero si
     * la hora actual es menor o igual a hora deadline
     * Regresa true si la hora actual es menor o igual al deadline
     * Regresa false si la hora actual es mayor al deadline
     * @autor Jorge Luis Bautista Santiago     
     * @return boolean
     */
    public boolean isCurrentHourValid(String tsHourDeadLine){
        boolean lbRes = true;
        String lsDeadLine = tsHourDeadLine == null ? "" : tsHourDeadLine;
        String lsCurrentHour = getCurrentHour();
        if(lsDeadLine.length() > 1){
            try{
                DateFormat loDf = new SimpleDateFormat("HH:mm");
                Date tiCurrentHour = loDf.parse(lsCurrentHour);
                Date tiDeadLineHour = loDf.parse(tsHourDeadLine);                
                if(tiCurrentHour.compareTo(tiDeadLineHour) > 0){
                    lbRes = false;//la hora actual es mayor al deadline
                }
            }catch(ParseException loEx){
                loEx.getMessage();
                lbRes = false;
            }
        }
        return lbRes;
    }
    
    /**
     * Agrega registro del log de servicio
     * @autor Jorge Luis Bautista Santiago
     * @param toLmkBean
     * @return void
     */
    public void insertLogServiceService(LmkIntServicesLogRowBean toLmkBean) {
       ApplicationModule         loAm =
           Configuration.createRootApplicationModule(gsAmDef, gsConfig);
       AppModuleImpl loService = (AppModuleImpl)loAm;
       try {
           loService.insertServicesLogModel(toLmkBean);
       } catch (Exception loEx) {
           System.out.println("Util-ERROR(insertLogServiceService): " + loEx.getMessage());
       } finally {
           Configuration.releaseRootApplicationModule(loAm, true);
           loAm.remove();
       }
    }
    
    /**
    * Actualiza registro del log de servicio
    * @autor Jorge Luis Bautista Santiago
    * @param toLmkBean
    * @return void
    */
    public void updateLogServiceService(LmkIntServicesLogRowBean toLmkBean) {
       ApplicationModule         loAm =
           Configuration.createRootApplicationModule(gsAmDef, gsConfig);
       AppModuleImpl loService = (AppModuleImpl)loAm;
       try {
           loService.updateServicesLogModel(toLmkBean);
       } catch (Exception loEx) {
           System.out.println("ERROR en update LogService:"+loEx.getMessage());
       } finally {
           Configuration.releaseRootApplicationModule(loAm, true);
           loAm.remove();
       }
    }
    
}