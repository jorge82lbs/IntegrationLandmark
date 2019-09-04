/**
* Project: Paradigm - Landmark Integration
*
* File: CreationBean.java
*
* Created on: Julio 16, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/

package mx.com.televisa.landamark.view.beans;

import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import mx.com.televisa.landamark.model.AppModuleImpl;
import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.model.types.LmkIntCronConfigRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesCatRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesParamsRowBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;
import mx.com.televisa.landamark.services.jobs.ParrillasProgramasCron;
import mx.com.televisa.landamark.util.UtilFaces;
import mx.com.televisa.landamark.view.types.ExecuteServiceResponseBean;
import mx.com.televisa.landamark.view.types.ProcessServiceBean;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelLabelAndMessage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.client.Configuration;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/** Esta clase es un bean que enlaza la pantalla de Creacion de Archivos de Cortes
 * y Programas <br/><br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Julio 16, 2019, 12:00 pm
 */
public class CreationBean{
    private RichInputText poNomService;
    private RichInputText poIdService;
    private RichInputDate poInitialDate;
    private RichInputDate poFinalDate;
    private RichPopup poPopupExecute;
    private RichPanelLabelAndMessage poExecuteMsgLbl;
    private RichOutputText poExecuteIdBinding;
    private RichOutputText poExecuteNomBinding;
    private RichOutputText poExecuteTypeService;
    String                            gsAppModule = "AppModuleDataControl";
    String                            gsAmDef = "mx.com.televisa.landamark.model.AppModuleImpl";
    String                            gsConfig = "AppModuleLocal";
    String                            gsEntityIterator = "LmkIntServicesLogUsrVwView1Iterator";                                            
    private RichInputText poUserName;
    private RichTable poTblChannels;

    public CreationBean() {
    }

    /**
     * Obtiene el idServicio, fecha inicial y fecha final, si es
     * que el usuario ha ejecutado al menos una vez el servicio de Cortes y Programas
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public Integer getIdCortesyProgramasByUser(String tsUser){        
        Integer liIdService = -1;
        ViewObjectDao loViewObjectDao = new ViewObjectDao();
        liIdService = loViewObjectDao.getServiceCortesProgramasByUser(tsUser);
        return liIdService;
    }    
    
    /**
     * Guarda los parametros de ejecucion del servicio en cuestion
     * @autor Jorge Luis Bautista Santiago
     * @return ResponseUpdDao
     */
    public ResponseUpdDao saveParameters(){
        ResponseUpdDao loResponse = new ResponseUpdDao();
        Integer liCount = 0;
        Integer liFinalIdService = -1;
        
        String lsIdService = getPoIdService().getValue().toString();
        System.out.println("lsIdService["+lsIdService+"]");
        String lsUserName = getPoUserName().getValue().toString();
        System.out.println("lsUserName["+lsUserName+"]");
        String lsFecInicial = 
            getPoInitialDate().getValue() == null ? null : 
            getPoInitialDate().getValue().toString();      
        System.out.println("lsFecInicial["+lsFecInicial+"]");
        String lsFecFinal = 
            getPoFinalDate().getValue() == null ? null : 
            getPoFinalDate().getValue().toString(); 
        System.out.println("lsFecFinal["+lsFecFinal+"]");
        if(lsFecInicial != null && lsFecFinal != null){
            if(Integer.parseInt(lsIdService) < 0){
                //verificar en la bd si ya existe un servicio de cortes con este usuario
                Integer liIdService = getIdCortesyProgramasByUser(lsUserName);
                System.out.println("verificar en la bd si ya existe un servicio de cortes con este usuario ["+liIdService+"]");
                if(liIdService <= 0){
                    liIdService = new ViewObjectDao().getMaxIdServicesCatalog() + 1;    
                    System.out.println("idServicio Consecutivo["+liIdService+"]");
                    String lsDescService = "Cortes y Programas - "+lsUserName;
                    String lsNomService = "ProcessParrillasProgramas";
                    String lsIndServiceWsdl = "";
                    String lsIndSystem = "Usuarios";
                    String lsIndOrigin = "";
                    String lsIndDestiny = "";
                    String lsIndEstatus = "1";
                    String lsIndSynchronous = "true";
                    String lsStatusTab = "0";
                    if(lsIndEstatus.equalsIgnoreCase("true")){
                        lsStatusTab = "1";
                    }
                    String lsAsynTab = "0";
                    if(lsIndSynchronous.equalsIgnoreCase("true")){
                        lsAsynTab = "1";
                    }
                    ApplicationModule         loAm = 
                        Configuration.createRootApplicationModule(gsAmDef, gsConfig);
                    AppModuleImpl loService = 
                        (AppModuleImpl)loAm;        
                    try{
                        LmkIntServicesCatRowBean loLmkBean = new LmkIntServicesCatRowBean();
                        loLmkBean.setLiIdService(liIdService);
                        loLmkBean.setLsNomService(lsNomService);
                        loLmkBean.setLsIndDescService(lsDescService);
                        loLmkBean.setLsIndServiceWsdl(lsIndServiceWsdl);
                        loLmkBean.setLsIndSystem(lsIndSystem);
                        loLmkBean.setLsIndOrigin(lsIndOrigin);
                        loLmkBean.setLsIndDestiny(lsIndDestiny);
                        loLmkBean.setLsIndEstatus(lsStatusTab);
                        loLmkBean.setLsIndSynchronous(lsAsynTab);
                        loLmkBean.setLsIndAttribute1(lsUserName);
                        loService.insertServicesCatModel(loLmkBean);  
                    
                    } catch (Exception loEx) {
                        loResponse.setLsResponse("ERROR");
                        loResponse.setLsMessage("Error al insertar Servicio " + loEx);
                    } finally {
                        Configuration.releaseRootApplicationModule(loAm, true);
                    }
                }
                liFinalIdService = liIdService;
                System.out.println("liFinalIdService(Final): "+liFinalIdService);
            }else{
                System.out.println("liFinalIdService(Final) caso contrario: "+liFinalIdService);
                liFinalIdService = Integer.parseInt(lsIdService);
            }
            
            if(liFinalIdService > 0){
                //String lsFecIni = convertDateMask(ltDateIni, "yyyy-MM-dd");
                //String lsFecFin = convertDateMask(ltDateFin, "yyyy-MM-dd");
                ApplicationModule         loAm = 
                    Configuration.createRootApplicationModule(gsAmDef, gsConfig);
                AppModuleImpl loService = 
                    (AppModuleImpl)loAm;        
                try{
                    System.out.println("deleteServicesParamsModelByServ: "+liFinalIdService);
                    loService.deleteServicesParamsModelByServ(liFinalIdService);
                    try {
                        CollectionModel   loModel =
                            (CollectionModel)getPoTblChannels().getValue();
                        JUCtrlHierBinding loBinding =
                            (JUCtrlHierBinding)loModel.getWrappedData();
                        DCIteratorBinding loIteratorBinding =
                            loBinding.getDCIteratorBinding();
                        Row[]             laRows = loIteratorBinding.getAllRowsInRange();
                        for (int liI = 0; liI < laRows.length; liI++) {
                            String lsSelected = 
                                laRows[liI].getAttribute("Selected") == null ? "false" :
                                laRows[liI].getAttribute("Selected").toString();
                            
                            if(lsSelected.equalsIgnoreCase("true")){
                                liCount ++;
                                String lsCanal = laRows[liI].getAttribute("NomParameter") == null ? "false" :
                                laRows[liI].getAttribute("NomParameter").toString();
                                LmkIntServicesParamsRowBean loLmkParamBean = new LmkIntServicesParamsRowBean();            
                                Integer                  liId = 
                                    new ViewObjectDao().getMaxIdParadigm("ServParameters") + 1;
                                loLmkParamBean.setLiIdParameterServ(liId);
                                loLmkParamBean.setLiIdService(liFinalIdService);
                                String lsValue = "0";
                                if(lsSelected.equalsIgnoreCase("true")){
                                    lsValue = "1";
                                }                                                
                                loLmkParamBean.setLsIndParameter(lsCanal);
                                loLmkParamBean.setLsIndValParameter(lsValue);                        
                                loLmkParamBean.setLsIndEstatus("1");   
                                
                                loService.insertServicesParamsModel(loLmkParamBean);
                                System.out.println("insertando canal: "+loLmkParamBean.getLiIdService());
                            }
                            
                        }
                    } catch (Exception loEx) {
                        loResponse.setLsResponse("ERROR");
                        loResponse.setLsMessage(loEx.getMessage());
                        loEx.printStackTrace();
                    }
                    
                    LmkIntServicesParamsRowBean loLmkFiBean = new LmkIntServicesParamsRowBean();            
                    Integer                  liIdFi = 
                        new ViewObjectDao().getMaxIdParadigm("ServParameters") + 1;
                    loLmkFiBean.setLiIdParameterServ(liIdFi);
                    loLmkFiBean.setLiIdService(liFinalIdService);
                    loLmkFiBean.setLsIndEstatus("1");
                    loLmkFiBean.setLsIndParameter("FECHA_INICIAL");
                    //loLmkFiBean.setLsIndValParameter(lsFecIni);                        
                    loLmkFiBean.setLsIndValParameter(lsFecInicial);                        
                    loService.insertServicesParamsModel(loLmkFiBean); 
                    System.out.println("insertando Fechas Inicial: "+liFinalIdService);
                    LmkIntServicesParamsRowBean loLmkFfBean = new LmkIntServicesParamsRowBean();            
                    Integer                  liIdFf = 
                        new ViewObjectDao().getMaxIdParadigm("ServParameters") + 1;
                    loLmkFfBean.setLiIdParameterServ(liIdFf);
                    loLmkFfBean.setLiIdService(liFinalIdService);
                    loLmkFfBean.setLsIndEstatus("1");
                    loLmkFfBean.setLsIndParameter("FECHA_FINAL");
                    //loLmkFfBean.setLsIndValParameter(lsFecFin);                        
                    loLmkFfBean.setLsIndValParameter(lsFecFinal);                        
                    loService.insertServicesParamsModel(loLmkFfBean);     
                    System.out.println("insertando Fechas Final: "+liFinalIdService);
                    loResponse.setLsResponse("OK");
                    loResponse.setLsMessage("OK");
                    
                } catch (Exception loEx) {   
                    loResponse.setLsResponse("ERROR");
                    loResponse.setLsMessage(loEx.getMessage());
                } finally {
                    Configuration.releaseRootApplicationModule(loAm, true);
                }
            }else{
                loResponse.setLsResponse("ERROR");
                loResponse.setLsMessage("ERROR GENERAL");
            }
        }
        else{
            loResponse.setLsResponse("ERROR");
            loResponse.setLsMessage("El Intervalo de Fechas es Requerido");
        }
        
        if(liCount == 0){
            loResponse.setLsResponse("ERROR");
            loResponse.setLsMessage("Es necesario al menos un canal");
        }
        
        System.out.println("liFinalIdService CLASS["+liFinalIdService+"]");
        loResponse.setLiAffected(liFinalIdService);

        
        return loResponse;
    }

    /**
     * Ejecuta logica del boton guardar, invoca saveParameters
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String saveAction() {
        System.out.println("Dentro de save Action");
        ResponseUpdDao loResponse = saveParameters();
        
        if(loResponse != null){
            if(loResponse.getLsResponse().equalsIgnoreCase("OK")){
                FacesMessage loMsg;
                loMsg = new FacesMessage("Parámetros Asignados Correctamente");
                loMsg.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, loMsg);
            }else{
                FacesMessage loMsg;
                loMsg = new FacesMessage("ERROR "+loResponse.getLsMessage());
                loMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, loMsg);
                
            }
        }else{
            FacesMessage loMsg;
            loMsg = new FacesMessage("Error al Procesar Información");
            loMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, loMsg);
        }
        
        return null;
    }
    
    /**
     * Convierte a cadena una fecha con la mascara deseada
     * @autor Jorge Luis Bautista Santiago
     * @param ttObjectDate
     * @param tsOutputMask
     * @return String
     */
    public static String convertDateMask(java.util.Date ttObjectDate,
                                        String tsOutputMask) {
        SimpleDateFormat loSdfOut = new SimpleDateFormat(tsOutputMask);
        java.util.Date   ltDate = ttObjectDate;
        String           lsFormatted = "";
                         lsFormatted = loSdfOut.format(ltDate);
        return lsFormatted;
    }

    /**
     * Muestra popup de confirmacion para ejecutar el servicio
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String showProcessAction() {
        getPoExecuteIdBinding().setValue("11");
        getPoExecuteNomBinding().setValue("lsNomService");
        getPoExecuteTypeService().setValue("lsDesService");
        getPoExecuteMsgLbl().setLabel("Guardar Parámetros y Crear Archivo de Parrillas Cortes y Programas");
        new UtilFaces().showPopup(getPoPopupExecute());
        return null;
    }
    
    
    /**
     * Ejecuta inicio del cron del servicio seleccionado
     * @autor Jorge Luis Bautista Santiago
     * @param lsAction
     * @param toPopup
     * @return void
     */
    public void executeProcessAction(String lsIdService, 
                                     String lsTypeService,
                                     String lsNameService){               
       
        String              lsFinalMessage = "Acción Satisfactoria";
        String              lsColorMessage = "blue";
        //String              lsGeneralAction = lsAction;
        Integer             liIdUser = null;
        String              lsUserName = null;
        System.out.println("dentro de eexecuteProcessAction");
        ApplicationModule   loAm =
            Configuration.createRootApplicationModule(gsAmDef, gsConfig);
        AppModuleImpl loService = (AppModuleImpl)loAm;
        try {
            try{
                liIdUser = 
                    loService.getValueSessionFromAttribute("loggedPgmIntegrationIdUser") == null ? null :
                    Integer.parseInt(loService.getValueSessionFromAttribute("loggedPgmIntegrationIdUser"));
                System.out.println(">>>> liIdUser: "+liIdUser);
                lsUserName = 
                    loService.getValueSessionFromAttribute("loggedPgmIntegrationUser") == null ? null :
                    loService.getValueSessionFromAttribute("loggedPgmIntegrationUser").toString();
                
            } catch (Exception loEx) {
                lsFinalMessage = loEx.getMessage();
                lsColorMessage = "red";
                System.out.println("Err 5874 "+loEx.getMessage());
            } finally {
                Configuration.releaseRootApplicationModule(loAm, true);
            }
            
            System.out.println(">>>> lsUserName: "+lsUserName);
            String lsIdTrigger = lsIdService + "-" + lsTypeService;
            System.out.println("********** lsIdTrigger["+lsIdTrigger+"] ==> [EXECUTE] (usuario)*********");
            //lsGeneralAction = lsServiceAction;
            //System.out.println(">>>> lsGeneralAction: "+lsGeneralAction);
            ProcessServiceBean loProcessBean = new ProcessServiceBean();
            loProcessBean.setLiIdUser(liIdUser);
            loProcessBean.setLsUserName(lsUserName);
            loProcessBean.setLsIdTrigger(lsIdTrigger);
            loProcessBean.setLsIdService(lsIdService);
            loProcessBean.setLsServiceToInvoke(lsTypeService);
            loProcessBean.setLsServiceAction("EXECUTE");
            
            loProcessBean.setLsTypeProcess(lsTypeService);
            loProcessBean.setLsServiceName(lsNameService);
            
            if(lsTypeService != null){
                /*################ PARRILLAS AND PROGRAMAS ############################*/
                //if(lsTypeService.equalsIgnoreCase("ProcessParrillasProgramas")){
                    System.out.println(">>>> Ejecutar processServiceExecution para ProcessParrillasProgramas (USUARIO): ");
                    ExecuteServiceResponseBean loRes =
                        processServiceExecution(loProcessBean, 
                                                ParrillasProgramasCron.class);
                    lsColorMessage = loRes.getLsColor();
                    lsFinalMessage = loRes.getLsMessage();
                //}
            }                        
        } catch (Exception loEx) {
            lsFinalMessage = loEx.getMessage();
            lsColorMessage = "red";
            System.out.println("Err 5874 "+loEx.getMessage());
        }
        StringBuilder loMessage = new StringBuilder("<html><body>");
        loMessage.append("<p style='color:" + lsColorMessage + "'><b>" + lsFinalMessage + "</i></b></p>");
        loMessage.append("</body></html>");        
        FacesMessage loMsg = 
            new FacesMessage(loMessage.toString());     
        loMsg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext loFctx = FacesContext.getCurrentInstance();
        loFctx.addMessage(null, loMsg);
    }
    
    /**
     * Ejecuta la logica de ejecucion de servicio para Programas
     * @autor Jorge Luis Bautista Santiago
     * @param toPrcBean
     * @param poService
     * @return ExecuteServiceResponseBean
     */
    public ExecuteServiceResponseBean processServiceExecution(ProcessServiceBean toPrcBean,
                                                              Class<? extends Job> loClassCron
                                                              ){
        ExecuteServiceResponseBean         loRes = new ExecuteServiceResponseBean();
        String                             lsFinalMessage = "";
        String                             lsColorMessage = "red";
        lsFinalMessage = 
            "El Servicio de " + toPrcBean.getLsServiceToInvoke() + 
            " ha sido ejecutado en segundo plano";
        lsColorMessage = "black";
        boolean lbPrExe = true;
        /*LmkIntCronConfigRowBean loRowCron = 
            poService.getRowCronConfigByServiceModel(Integer.parseInt(toPrcBean.getLsIdService()));
        if(loRowCron != null){
            if(loRowCron.getLsIndEstatus().equalsIgnoreCase("2")){
                lbPrExe = false;
            }
        }*/
        
        if(lbPrExe){
            EntityMappedDao loEntityMappedDao = new EntityMappedDao();
            
            /*new UtilFaces().updateStatusCronService(Integer.parseInt(toPrcBean.getLsIdService()),
                                                    "1",
                                                    null,
                                                    null,
                                                    null
                                                    );*/
            
            Integer liIndProcess = 
                loEntityMappedDao.getGeneralParameterID("ExecuteCron", 
                                                    "PROCESS_INTEGRATION");
            
            Integer liNumPgmProcessID = 0;
            Integer liNumEvtbProcessId = 0;
            LmkIntServiceBitacoraRowBean loBean = new LmkIntServiceBitacoraRowBean();
            loBean.setLiIdLogServices(0);
            loBean.setLiIdService(Integer.parseInt(toPrcBean.getLsIdService()));
            loBean.setLiIndProcess(liIndProcess);
            loBean.setLsIndEvento("Ejecucion por Usuario "+toPrcBean.getLsServiceName());
            loBean.setLiNumProcessId(liNumEvtbProcessId);
            loBean.setLiNumPgmProcessId(liNumPgmProcessID);
            loBean.setLsIdBitacora("0");
            loBean.setLiIdUser(toPrcBean.getLiIdUser());
            loBean.setLsUserName(toPrcBean.getLsUserName());
            
            loEntityMappedDao.insertBitacoraWs(loBean,
                                               toPrcBean.getLiIdUser(), 
                                               toPrcBean.getLsUserName());
            
    
            Scheduler loScheduler;
            try {
                loScheduler = new StdSchedulerFactory().getScheduler();
                JobDetail loJob = 
                    JobBuilder.newJob(loClassCron).build();
                Trigger   loTrigger = 
                    TriggerBuilder.newTrigger().withIdentity(toPrcBean.getLsIdTrigger()).build();
                JobDataMap loJobDataMap=  loJob.getJobDataMap();
                loJobDataMap.put("lsIdService", toPrcBean.getLsIdService()); 
                loJobDataMap.put("liIdUser", String.valueOf(toPrcBean.getLiIdUser())); 
                loJobDataMap.put("lsUserName", toPrcBean.getLsUserName()); 
                //loJobDataMap.put("lsIdRequest", lsIdRequest); 
                loJobDataMap.put("lsTypeRequest", "normal");
                loJobDataMap.put("lsTypeProcess", toPrcBean.getLsTypeProcess());
                loJobDataMap.put("lsServiceName", toPrcBean.getLsServiceName());
                loJobDataMap.put("lsPathFiles", getRealPath());                    
                loScheduler.scheduleJob(loJob, loTrigger);
                loScheduler.start();
                
            } catch (Exception loEx) {
                lsFinalMessage = loEx.getMessage();
                lsColorMessage = "red";
            } 
        }else{
            lsFinalMessage = "ATENCION: Actualmente Existe en Ejecucion";
            lsColorMessage = "red";
        }
        refreshMainTable();
        loRes.setLsColor(lsColorMessage);
        loRes.setLsMessage(lsFinalMessage);
        return loRes;
    }

    /**
     * Obtiene la ruta real del contexto
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String getRealPath(){
        ServletContext        loContext = getContext();
        return loContext.getRealPath("/");
    }
    
    /**
     * Obtiene el contexto del proyecto
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public ServletContext getContext() {
       return (ServletContext)getFacesContext().getExternalContext().getContext();
    }
    
    /**
     * Obtiene el contexto
     * @autor Jorge Luis Bautista Santiago
     * @return FacesContext
     */
    public static FacesContext getFacesContext() {
       return FacesContext.getCurrentInstance();
    }
    
    /**
     * Actualiza la tabla principal a su estado inicial
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String refreshMainTable() {
        String lsUserName = getPoUserName().getValue().toString();
        new UtilFaces().refreshTableWhereIterator("USER_NAME = '"+lsUserName+"'",
                                                  gsEntityIterator,
                                                  getPoTblChannels()
                                                  );
        return null;
    }

    /**
     * Ejecuta logica de la confirmacion de ejecutar servicio
     * Guarda canales y posteriormente ejecuta el servicio
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String executeExecuteAction() {
        ResponseUpdDao loResponse = saveParameters();
        new UtilFaces().hidePopup(getPoPopupExecute());
        if(loResponse != null){
            if(loResponse.getLsResponse().equalsIgnoreCase("OK")){
                String lsIdService = String.valueOf(loResponse.getLiAffected());
                    //getPoIdService().getValue().toString(); 
                System.out.println("lsIdService a ejcutar["+lsIdService+"]");
                String lsUserName = getPoUserName().getValue().toString();
                System.out.println("lsUserName a ejcutar["+lsUserName+"]");
                //Ejecutar los procesos, 
                System.out.println("Ejecutar prrogramas por usuario");
                executeProcessAction(lsIdService, 
                                     "ProcessParrillasProgramas",
                                     "Parrillas Cortes y Programas por "+ lsUserName
                                     );
            }else{
                FacesMessage loMsg;
                loMsg = new FacesMessage("ERROR "+loResponse.getLsMessage());
                loMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, loMsg);
                
            }
        }else{
            FacesMessage loMsg;
            loMsg = new FacesMessage("Error al Procesar Información");
            loMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, loMsg);
        }

        return null;
    }

    /**
     * Cancela accion de Ejecutar servicio
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String cancelExecuteAction() {
        new UtilFaces().hidePopup(getPoPopupExecute());
        return null;
    }
    
    /************************************ SETTERS AND GETTERS ************************/
    
    public void setPoNomService(RichInputText poNomService) {
        this.poNomService = poNomService;
    }

    public RichInputText getPoNomService() {
        return poNomService;
    }

    public void setPoIdService(RichInputText poIdService) {
        this.poIdService = poIdService;
    }

    public RichInputText getPoIdService() {
        return poIdService;
    }

    public void setPoInitialDate(RichInputDate poInitialDate) {
        this.poInitialDate = poInitialDate;
    }

    public RichInputDate getPoInitialDate() {
        return poInitialDate;
    }

    public void setPoFinalDate(RichInputDate poFinalDate) {
        this.poFinalDate = poFinalDate;
    }

    public RichInputDate getPoFinalDate() {
        return poFinalDate;
    }
    public void setPoPopupExecute(RichPopup poPopupExecute) {
        this.poPopupExecute = poPopupExecute;
    }

    public RichPopup getPoPopupExecute() {
        return poPopupExecute;
    }

    public void setPoExecuteMsgLbl(RichPanelLabelAndMessage poExecuteMsgLbl) {
        this.poExecuteMsgLbl = poExecuteMsgLbl;
    }

    public RichPanelLabelAndMessage getPoExecuteMsgLbl() {
        return poExecuteMsgLbl;
    }

    public void setPoExecuteIdBinding(RichOutputText poExecuteIdBinding) {
        this.poExecuteIdBinding = poExecuteIdBinding;
    }

    public RichOutputText getPoExecuteIdBinding() {
        return poExecuteIdBinding;
    }

    public void setPoExecuteNomBinding(RichOutputText poExecuteNomBinding) {
        this.poExecuteNomBinding = poExecuteNomBinding;
    }

    public RichOutputText getPoExecuteNomBinding() {
        return poExecuteNomBinding;
    }

    public void setPoExecuteTypeService(RichOutputText poExecuteTypeService) {
        this.poExecuteTypeService = poExecuteTypeService;
    }

    public RichOutputText getPoExecuteTypeService() {
        return poExecuteTypeService;
    }


    public void setPoUserName(RichInputText poUserName) {
        this.poUserName = poUserName;
    }

    public RichInputText getPoUserName() {
        return poUserName;
    }

    public void setPoTblChannels(RichTable poTblChannels) {
        this.poTblChannels = poTblChannels;
    }

    public RichTable getPoTblChannels() {
        return poTblChannels;
    }
}
