/**
* Project: Paradigm - Landmark Integration
*
* File: ProcessBean.java
*
* Created on:  Febrero 6, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/
package mx.com.televisa.landamark.view.beans;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.servlet.ServletContext;

import mx.com.televisa.landamark.model.AppModuleImpl;
import mx.com.televisa.landamark.model.types.LmkIntConfigParamRowBean;
import mx.com.televisa.landamark.model.types.LmkIntCronConfigRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesCatRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesParamsRowBean;
import mx.com.televisa.landamark.model.daos.ViewObjectDao;

import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.services.jobs.ExecuteDummyCron;
import mx.com.televisa.landamark.services.jobs.ParrillasProgramasCron;
import mx.com.televisa.landamark.view.types.ExecuteServiceResponseBean;
import mx.com.televisa.landamark.view.types.ProcessServiceBean;
import mx.com.televisa.landamark.view.types.SelectOneItemBean;

import mx.com.televisa.landamark.util.UtilFaces;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputNumberSpinbox;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelLabelAndMessage;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.nav.RichLink;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.client.Configuration;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/** Esta clase es un bean que enlaza la pantalla de Configuracion de Servicios<br/><br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Febrero 14, 2019, 12:00 pm
 */
public class ProcessBean {
    private RichSelectOneChoice       poFilterDesServiceSel;
    private RichSelectOneChoice       poFilterServiceSel;
    private RichInputText             poFilterService;
    private RichInputText             poFilterWsdl;
    private RichSelectOneChoice       poFilterSystem;
    private RichSelectOneChoice       poFilterSystemOr;
    private RichSelectOneChoice       poFilterSystemDes;
    private RichSelectOneChoice       poFilterAsynSel;
    private RichSelectOneChoice       poFilterStatusSel;
    private RichTable                 poTblMain;
    private RichInputText             poUpdIdService;
    private RichInputText             poUpdNomService;
    private RichInputText             poUpdDesService;
    private RichSelectOneChoice       poUpdSystem;
    private RichSelectOneChoice       poUpdSystemOr;
    private RichPopup                 poPopupDelete;
    private RichPanelLabelAndMessage  poDeleteMsgLbl;
    private RichOutputText            poDeleteIdBinding;
    private RichPopup                 poPopupUpdate;
    private RichSelectOneChoice       poUpdSystemDes;
    private RichSelectBooleanCheckbox poUpdAsyn;
    private RichSelectBooleanCheckbox poUpdStatus;
    String                            gsEntityView = "LmkIntServicesCatVwView1";
    String                            gsEntityIterator = "LmkIntServicesCatVwView1Iterator";                                            
    String                            gsAppModule = "AppModuleDataControl";
    String                            gsAmDef = "mx.com.televisa.landamark.model.AppModuleImpl";
    String                            gsConfig = "AppModuleLocal";
    private RichPopup                 poPopupSave;
    private RichInputText             poSaveNomService;
    private RichSelectOneChoice       poSaveNomServiceSel;
    private RichInputText             poSaveWsdl;
    private RichSelectOneChoice       poSaveSystem;
    private RichSelectOneChoice       poSaveSystemOr;
    private RichSelectOneChoice       poSaveSystemDes;
    private RichSelectBooleanCheckbox poSaveAsyn;
    private RichSelectBooleanCheckbox poSaveStatus;
    private RichInputText             poUpdWsdl;
    private RichPopup                 poPopupMerge;
    private RichInputText             poMergeIdService;
    private RichInputText             poMergeNomService;
    private RichSelectBooleanCheckbox poMerge2can;
    private RichSelectBooleanCheckbox poMerge5can;
    private RichSelectBooleanCheckbox poMerge9can;
    private RichInputText             poMergeCliente;
    private RichPopup                 poPopupCron;
    private RichPanelTabbed           poPanelTabbed;
    private RichShowDetailItem        poTabMinutos;
    private RichShowDetailItem        poTabHoras;
    private RichInputNumberSpinbox    poCadaMinutos;
    private RichSelectBooleanCheckbox poLunMinutos;
    private RichSelectBooleanCheckbox poMarMinutos;
    private RichSelectBooleanCheckbox poMieMinutos;
    private RichSelectBooleanCheckbox poJueMinutos;
    private RichSelectBooleanCheckbox poVieMinutos;
    private RichSelectBooleanCheckbox poSabMinutos;
    private RichSelectBooleanCheckbox poDomMinutos;
    private RichInputNumberSpinbox    poCadaHoras;
    private RichSelectBooleanCheckbox poLunHoras;
    private RichSelectBooleanCheckbox poMarHoras;
    private RichSelectBooleanCheckbox poMieHoras;
    private RichInputNumberSpinbox    poBeginHoras;
    private RichSelectBooleanCheckbox poJueHoras;
    private RichSelectBooleanCheckbox poVieHoras;
    private RichSelectBooleanCheckbox poSabHoras;
    private RichSelectBooleanCheckbox poDomHoras;
    private RichInputText             poIdServiceSelected;
    private RichInputNumberSpinbox    poBeginMinutes;
    private RichSelectBooleanRadio    poRadioBegin;
    private RichSelectBooleanRadio    poRadioDayEvery;
    private RichInputNumberSpinbox    poCadaDias;
    private RichSelectBooleanRadio    poRadioDayBegin;
    private RichInputNumberSpinbox    poBeginDias;
    private RichInputNumberSpinbox    poBeginDayMinutes;
    private RichSelectBooleanCheckbox poLunDias;
    private RichSelectBooleanCheckbox poMarDias;
    private RichSelectBooleanCheckbox poMieDias;
    private RichSelectBooleanCheckbox poJueDias;
    private RichSelectBooleanCheckbox poVieDias;
    private RichSelectBooleanCheckbox poSabDias;
    private RichSelectBooleanCheckbox poDomDias;
    private RichInputNumberSpinbox    poBeginSemanas;
    private RichInputNumberSpinbox    poBeginDaySemanas;
    private RichSelectBooleanCheckbox poLunWeek;
    private RichSelectBooleanCheckbox poMarWeek;
    private RichSelectBooleanCheckbox poMieWeek;
    private RichSelectBooleanCheckbox poJueWeek;
    private RichSelectBooleanCheckbox poVieWeek;
    private RichSelectBooleanCheckbox poSabWeek;
    private RichSelectBooleanCheckbox poDomWeek;
    private RichInputNumberSpinbox    poDeadLineHoras;
    private RichInputNumberSpinbox    poDeadLineMinutos;
    private RichInputNumberSpinbox    poDeadLineMinHoras;
    private RichInputNumberSpinbox    poDeadLineMinMinutos;
    private RichInputNumberSpinbox    poBeginHorasMin;
    private RichInputNumberSpinbox    poBeginMinutesMin;
    private RichShowDetailItem        poTabDias;
    private RichShowDetailItem        poTabSemanas;
    private RichInputNumberSpinbox    poMesEveryOf;
    private RichInputNumberSpinbox    poMesHoraIni;
    private RichInputNumberSpinbox    poMesMinutoIni;
    private RichInputNumberSpinbox    poSemanasHoraIni;
    private RichInputNumberSpinbox    poBeginSemanasMinutoIni;
    private RichInputText             poIdTabSelected;
    private RichInputText             poOperation;
    private RichInputText             poIdCronConfiguration;
    private RichInputText             poNomServicioCron;
    private RichLink                  poServiceToCron;
    private RichLink                  poServiceToCronStatus;
    private RichPopup                 poPopupExecute;
    private RichPanelLabelAndMessage  poExecuteMsgLbl;
    private RichOutputText            poExecuteIdBinding;
    private RichOutputText            poExecuteNomBinding;
    private RichPopup                 poPopupBegin;
    private RichPanelLabelAndMessage  poBeginMsgLbl;
    private RichOutputText            poBeginIdBinding;
    private RichOutputText            poBeginNomBinding;
    private RichPopup                 poPopupStop;
    private RichPanelLabelAndMessage  poStopMsgLbl;
    private RichOutputText            poStopIdBinding;
    private RichOutputText            poStopNomBinding;
    
    private RichOutputText            poExecuteTypeService;
    private RichOutputText            poBeginTypeService;
    private RichOutputText            poStopTypeService;
    private RichPopup poPopupParams;
    private RichInputText poParamsIdService;
    private RichInputText poParamsNomService;

    /**
     * Muestra popup para ejecutar el servicio
     * @autor Jorge Luis Bautista Santiago
     * @param loActionEvent
     * @return void
     */
    public void showExecuteService(ActionEvent loActionEvent) {
        loActionEvent.getSource();
        FacesCtrlHierNodeBinding loNode = 
            (FacesCtrlHierNodeBinding) getPoTblMain().getSelectedRowData();
        String                   lsIdService = 
            loNode.getAttribute("IdService") == null ? "" : 
            loNode.getAttribute("IdService").toString(); 
        String                   lsDesService = 
            loNode.getAttribute("IndDescService") == null ? "" : 
            loNode.getAttribute("IndDescService").toString();
        String                   lsNomService = 
            loNode.getAttribute("NomService") == null ? "" : 
            loNode.getAttribute("NomService").toString();
        String                   lsEstatus = 
            loNode.getAttribute("IndEstatus") == null ? "" : 
            loNode.getAttribute("IndEstatus").toString();
        
        if(lsEstatus.equalsIgnoreCase("1")){
            getPoExecuteIdBinding().setValue(lsIdService);
            getPoExecuteNomBinding().setValue(lsNomService);
            getPoExecuteTypeService().setValue(lsDesService);
            getPoExecuteMsgLbl().setLabel("Desea Ejcutar el servicio " + lsDesService + " de tipo " + lsNomService + "?");
            new UtilFaces().showPopup(getPoPopupExecute());
        }else{
            FacesMessage loMsg =
                new FacesMessage("El Servicio se encuentra Inactivo");
            loMsg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, loMsg);
        }
    }

    /**
     * Muestra popup para Iniciar el servicio
     * @autor Jorge Luis Bautista Santiago
     * @param loActionEvent
     * @return void
     */
    public void showBeginCron(ActionEvent loActionEvent) {
        loActionEvent.getSource();
        FacesCtrlHierNodeBinding loNode = 
            (FacesCtrlHierNodeBinding) getPoTblMain().getSelectedRowData();
        String                   lsIdService = 
            loNode.getAttribute("IdService") == null ? "" : 
            loNode.getAttribute("IdService").toString();     
        String                   lsDesService = 
            loNode.getAttribute("IndDescService") == null ? "" : 
            loNode.getAttribute("IndDescService").toString();
        String                   lsNomService = 
            loNode.getAttribute("NomService") == null ? "" : 
            loNode.getAttribute("NomService").toString();
        
        getPoBeginIdBinding().setValue(lsIdService);
        getPoBeginNomBinding().setValue(lsNomService);
        getPoBeginTypeService().setValue(lsDesService);
        getPoBeginMsgLbl().setLabel("Desea Iniciar el servicio " + lsDesService + " de tipo " + lsNomService + "?");
        new UtilFaces().showPopup(getPoPopupBegin());       
        
    }

    /**
     * Muestra popup para deteter cron
     * @autor Jorge Luis Bautista Santiago
     * @param loActionEvent
     * @return void
     */
    public void showStopCron(ActionEvent loActionEvent) {
        loActionEvent.getSource();
        FacesCtrlHierNodeBinding loNode = 
            (FacesCtrlHierNodeBinding) getPoTblMain().getSelectedRowData();
        String                   lsIdService = 
            loNode.getAttribute("IdService") == null ? "" : 
            loNode.getAttribute("IdService").toString();     
        String                   lsDesService = 
            loNode.getAttribute("IndDescService") == null ? "" : 
            loNode.getAttribute("IndDescService").toString();
        String                   lsNomService = 
            loNode.getAttribute("NomService") == null ? "" : 
            loNode.getAttribute("NomService").toString();        
        
        getPoStopIdBinding().setValue(lsIdService);
        getPoStopNomBinding().setValue(lsNomService);
        getPoStopTypeService().setValue(lsDesService);
        getPoStopMsgLbl().setLabel("Desea Detener el servicio " + lsDesService + " de tipo " + lsNomService + "?");
        new UtilFaces().showPopup(getPoPopupStop());
       
    }

    /**
     * Ejecuta manualmente el servicio seleccionado
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String executeExecuteAction() {
        executeProcessAction("EXECUTE", 
                             getPoExecuteIdBinding(), 
                             getPoExecuteNomBinding(),
                             getPoExecuteTypeService(),
                             getPoPopupExecute());
        return null;
    }
    
    /**
     * Ejecuta la logica de ejecucion de servicio para Programas
     * @autor Jorge Luis Bautista Santiago
     * @param toPrcBean
     * @param poService
     * @return ExecuteServiceResponseBean
     */
    public ExecuteServiceResponseBean processServiceExecution(ProcessServiceBean toPrcBean,
                                                              Class<? extends Job> loClassCron,
                                                              AppModuleImpl poService
                                                              ){
        ExecuteServiceResponseBean         loRes = new ExecuteServiceResponseBean();
        String                             lsFinalMessage = "";
        String                             lsColorMessage = "red";
        
        if(toPrcBean.getLsServiceAction().equalsIgnoreCase("EXECUTE")){
            lsFinalMessage = 
                "El Servicio de " + toPrcBean.getLsServiceToInvoke() + 
                " ha sido ejecutado en segundo plano";
            lsColorMessage = "black";
            boolean lbPrExe = true;
            LmkIntCronConfigRowBean loRowCron = 
                poService.getRowCronConfigByServiceModel(Integer.parseInt(toPrcBean.getLsIdService()));
            if(loRowCron != null){
                if(loRowCron.getLsIndEstatus().equalsIgnoreCase("2")){
                    lbPrExe = false;
                }
            }
            if(lbPrExe){
                new UtilFaces().updateStatusCronService(Integer.parseInt(toPrcBean.getLsIdService()),
                                                        "1",
                                                        null,
                                                        null,
                                                        null
                                                        );
                
                Integer liIndProcess = new UtilFaces().getIdConfigParameterByName("Execute");
                Integer liNumPgmProcessID = 0;
                Integer liNumEvtbProcessId = 0;
                LmkIntServiceBitacoraRowBean loBean = new LmkIntServiceBitacoraRowBean();
                loBean.setLiIdLogServices(0);
                loBean.setLiIdService(Integer.parseInt(toPrcBean.getLsIdService()));
                loBean.setLiIndProcess(liIndProcess);
                loBean.setLsIndEvento("Ejecucion Manual de Servicio "+toPrcBean.getLsServiceName());
                loBean.setLiNumProcessId(liNumEvtbProcessId);
                loBean.setLiNumPgmProcessId(liNumPgmProcessID);
                loBean.setLsIdBitacora("0");
                loBean.setLiIdUser(toPrcBean.getLiIdUser());
                loBean.setLsUserName(toPrcBean.getLsUserName());
                new UtilFaces().insertBitacoraServiceService(loBean);
        
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
        }
        
        if(toPrcBean.getLsServiceAction().equalsIgnoreCase("BEGIN")){
            // Verificar si ya se encuentra en ejecucion
            LmkIntCronConfigRowBean loRowCron = 
                poService.getRowCronConfigByServiceModel(Integer.parseInt(toPrcBean.getLsIdService()));
            if(loRowCron != null){           
                // Verificar si puede iniciar de acuerdo al estatus
                if(!loRowCron.getLsIndEstatus().equalsIgnoreCase("2")){
                    Scheduler loScheduler;
                    try {
                        boolean lbSimple = false;
                        Trigger loTrigger = null;
                        String lsCronExpression = 
                            poService.getCronExpressionModel(Integer.parseInt(toPrcBean.getLsIdService()));
                        if(lsCronExpression != null){
                            loScheduler = new StdSchedulerFactory().getScheduler();
                            JobDetail loJob = 
                                JobBuilder.newJob(loClassCron).build();
                            if(loRowCron.getLsIndPeriodicity().equalsIgnoreCase("MINUTOS")){
                                lbSimple = true;
                            }
                            if(loRowCron.getLsIndPeriodicity().equalsIgnoreCase("HORAS")){
                                lbSimple = true;
                            }
                            if(!lbSimple){
                                loTrigger = 
                                    TriggerBuilder.newTrigger().withIdentity(toPrcBean.getLsIdTrigger()).
                                    withSchedule(CronScheduleBuilder.cronSchedule(lsCronExpression)
                                ).startNow().build();
                            }else{
                                Date ltCurrent = new Date();
                                String lsCurrDate = "";
                                SimpleDateFormat lodfCurrent = new SimpleDateFormat("yyyy-MM-dd");
                                lsCurrDate = lodfCurrent.format(ltCurrent);
                                Date ltFechaIni = new Date();
                                Date ltFechaFin = new Date();
                                String lsInicio = 
                                    loRowCron.getLsIndBeginSchedule() == null ? "08:00" : 
                                    loRowCron.getLsIndBeginSchedule();
                                String lsFin = 
                                    loRowCron.getAttribute1() == null ? "23:50" : 
                                    loRowCron.getAttribute1();
                                String lsEvery =  
                                    loRowCron.getLsIndValTypeSchedule() == null ? "23" : 
                                    loRowCron.getLsIndValTypeSchedule();
                                Integer liEvery = Integer.parseInt(lsEvery);
                                SimpleDateFormat lodf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                                String lsStartDate = lsCurrDate + " " + lsInicio + ":00.0";
                                String lsFinalDate = lsCurrDate + " " + lsFin + ":00.0";
                                try {
                                    ltFechaIni = lodf.parse(lsStartDate);
                                    ltFechaFin = lodf.parse(lsFinalDate);
                                } catch (ParseException e) {
                                    System.out.println("Error al parsear: "+e.getMessage());
                                    e.printStackTrace();
                                }
                                
                                if(loRowCron.getLsIndPeriodicity().equalsIgnoreCase("MINUTOS")){
                                    loTrigger = 
                                        TriggerBuilder.newTrigger().withIdentity(toPrcBean.getLsIdTrigger()).withSchedule(
                                    SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(liEvery).repeatForever()
                                    ).startAt(ltFechaIni).endAt(ltFechaFin).build();
                                }else{
                                    loTrigger = 
                                        TriggerBuilder.newTrigger().withIdentity(toPrcBean.getLsIdTrigger()).withSchedule(
                                    SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(liEvery).repeatForever()
                                    ).startAt(ltFechaIni).endAt(ltFechaFin).build();
                                }
                                
                            }
                            JobDataMap loJobDataMap=  loJob.getJobDataMap();
                            loJobDataMap.put("lsIdService", toPrcBean.getLsIdService()); 
                            loJobDataMap.put("liIdUser", String.valueOf(toPrcBean.getLiIdUser())); 
                            loJobDataMap.put("lsUserName", toPrcBean.getLsUserName()); 
                            loJobDataMap.put("lsTypeRequest", "normal");
                            loJobDataMap.put("lsTypeProcess", toPrcBean.getLsTypeProcess());
                            loJobDataMap.put("lsServiceName", toPrcBean.getLsServiceName());
                            loJobDataMap.put("lsPathFiles", getRealPath());
                            loScheduler.scheduleJob(loJob, loTrigger);
                            Integer piIndProcess = 
                                new UtilFaces().getIdConfigParameterByName("BeginCron");
                            Integer piNumPgmProcessID = 0;
                            Integer piNumEvtbProcessId = 0;
                            
                            LmkIntServiceBitacoraRowBean loBean = new LmkIntServiceBitacoraRowBean();
                            loBean.setLiIdLogServices(0);
                            loBean.setLiIdService(Integer.parseInt(toPrcBean.getLsIdService()));
                            loBean.setLiIndProcess(piIndProcess);
                            loBean.setLsIndEvento("Inicio Programado del Servicio "+toPrcBean.getLsServiceName());
                            loBean.setLiNumProcessId(piNumEvtbProcessId);
                            loBean.setLiNumPgmProcessId(piNumPgmProcessID);
                            loBean.setLsIdBitacora("0");
                            loBean.setLiIdUser(toPrcBean.getLiIdUser());
                            loBean.setLsUserName(toPrcBean.getLsUserName());
                            new UtilFaces().insertBitacoraServiceService(loBean);
                            
                            new UtilFaces().updateStatusCronService(Integer.parseInt(toPrcBean.getLsIdService()),
                                                                    "2",
                                                                    null,
                                                                    null,
                                                                    "exe"
                                                                    );
                            
                            loScheduler.start();
                            lsFinalMessage = 
                                "El Servicio de " + toPrcBean.getLsServiceToInvoke() + 
                                " ha sido Iniciado en segundo plano";
                            lsColorMessage = "black";
                            
                        }else{
                            lsFinalMessage = 
                                "No Existe Configuracion Cron para " + toPrcBean.getLsServiceToInvoke() + " ";
                            lsColorMessage = "red";
                        }
                    } catch (Exception loEx) {
                        
                        lsFinalMessage = loEx.getMessage();
                        lsColorMessage = "red";
                    } 
                }else{
                    lsFinalMessage = "ATENCION: Actualmente Existe en Ejecucion";
                    lsColorMessage = "red";
                }
            }else{
                lsFinalMessage = "ATENCION: No Existe Configuracion Para Iniciar Cron";
                lsColorMessage = "red";
            }
        }
        
        /*###### Detener Cron del Servicio #####*/
        if(toPrcBean.getLsServiceAction().equalsIgnoreCase("STOP")){
            String    lsNewCronExpression = "0 0 1 1/1 * ? *"; 
            Scheduler loScheduler;
            try {                        
                loScheduler = new StdSchedulerFactory().getScheduler();
                Trigger loTrigger = 
                    TriggerBuilder.newTrigger().withIdentity(toPrcBean.getLsIdTrigger()).withSchedule(
                       CronScheduleBuilder.cronSchedule(lsNewCronExpression)).startNow().build();
                loScheduler.rescheduleJob(new TriggerKey(toPrcBean.getLsIdTrigger()), loTrigger);
                loScheduler.deleteJob(loTrigger.getJobKey());
                lsFinalMessage = "El Servicio de " + toPrcBean.getLsServiceToInvoke() + " ha sido Detenido";
                lsColorMessage = "black";
                Integer liIndProcess = new UtilFaces().getIdConfigParameterByName("StopCron");
                Integer liNumPgmProcessID = 0;
                Integer liNumEvtbProcessId = 0;
                
                LmkIntServiceBitacoraRowBean loBean = new LmkIntServiceBitacoraRowBean();
                loBean.setLiIdLogServices(0);
                loBean.setLiIdService(Integer.parseInt(toPrcBean.getLsIdService()));
                loBean.setLiIndProcess(liIndProcess);
                loBean.setLsIndEvento("Servicio "+toPrcBean.getLsServiceName()+" en segundo plano ha sido Detenido");
                loBean.setLiNumProcessId(liNumEvtbProcessId);
                loBean.setLiNumPgmProcessId(liNumPgmProcessID);
                loBean.setLsIdBitacora("0");
                loBean.setLiIdUser(toPrcBean.getLiIdUser());
                loBean.setLsUserName(toPrcBean.getLsUserName());
                
                new UtilFaces().insertBitacoraServiceService(loBean);
                new UtilFaces().updateStatusCronService(Integer.parseInt(toPrcBean.getLsIdService()),
                                                        "3",
                                                        null,
                                                        null,
                                                        null
                                                        );
            } catch (SchedulerException loEx) {
                System.out.println("SchedulerException: " + loEx.getMessage());
                lsColorMessage = "red";
            } 
        }
        loRes.setLsColor(lsColorMessage);
        loRes.setLsMessage(lsFinalMessage);
        return loRes;
    }

    /**
     * Cancele ejecución manual del servicio seleccionado
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String cancelExecuteAction() {
        new UtilFaces().hidePopup(getPoPopupExecute());      
        FacesContext       loContext = FacesContext.getCurrentInstance();
        ExternalContext    loEctx = loContext.getExternalContext();
        String             lsUrl = 
            loEctx.getRequestContextPath() + "/faces/processPage";
        try {
            loEctx.redirect(lsUrl);
        } catch (IOException loEx) {
            ;
        }
        return null;
    }
    
    /**
     * Ejecuta inicio del cron del servicio seleccionado
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String executeBeginAction() {
        executeProcessAction("BEGIN", 
                             getPoBeginIdBinding(), 
                             getPoBeginNomBinding(),
                             getPoBeginTypeService(),
                             getPoPopupBegin());
        return null;
    }
    
    /**
     * Ejecuta inicio del cron del servicio seleccionado
     * @autor Jorge Luis Bautista Santiago
     * @param lsAction
     * @param toPopup
     * @return void
     */
    public void executeProcessAction(String lsAction, 
                                     RichOutputText loIdService, 
                                     RichOutputText loTypeService,
                                     RichOutputText loNameService,
                                     RichPopup toPopup){               
       
        String              lsFinalMessage = "Acción Satisfactoria";
        String              lsColorMessage = "blue";
        String              lsGeneralAction = lsAction;
        Integer             liIdUser = null;
        String              lsUserName = null;
        ApplicationModule   loAm =
            Configuration.createRootApplicationModule(gsAmDef, gsConfig);
        AppModuleImpl loService = (AppModuleImpl)loAm;
        try {
            liIdUser = 
                loService.getValueSessionFromAttribute("loggedPgmIntegrationIdUser") == null ? null :
                Integer.parseInt(loService.getValueSessionFromAttribute("loggedPgmIntegrationIdUser"));
            //System.out.println(">>>> liIdUser: "+liIdUser);
            lsUserName = 
                loService.getValueSessionFromAttribute("loggedPgmIntegrationUser") == null ? null :
                loService.getValueSessionFromAttribute("loggedPgmIntegrationUser").toString();
            //System.out.println(">>>> lsUserName: "+lsUserName);
            String lsIdService = 
                loIdService.getValue() == null ? null : 
                loIdService.getValue().toString();    
            //System.out.println(">>>> lsIdService: "+lsIdService);
            String lsTypeService = 
                loTypeService.getValue() == null ? null : 
                loTypeService.getValue().toString();
            //System.out.println(">>>> lsTypeService: "+lsTypeService);
            
            String lsNameService = 
                loNameService.getValue() == null ? null : 
                loNameService.getValue().toString();
            //System.out.println(">>>> lsNameService: "+lsNameService);
            
            String lsServiceAction = lsAction;   
            String lsIdTrigger = lsIdService + "-" + lsTypeService;
            System.out.println("********** lsIdTrigger["+lsIdTrigger+"] ==> ["+lsServiceAction+"]*********");
            lsGeneralAction = lsServiceAction;
            //System.out.println(">>>> lsGeneralAction: "+lsGeneralAction);
            ProcessServiceBean loProcessBean = new ProcessServiceBean();
            loProcessBean.setLiIdUser(liIdUser);
            loProcessBean.setLsUserName(lsUserName);
            loProcessBean.setLsIdTrigger(lsIdTrigger);
            loProcessBean.setLsIdService(lsIdService);
            loProcessBean.setLsServiceToInvoke(lsTypeService);
            loProcessBean.setLsServiceAction(lsServiceAction);
            
            loProcessBean.setLsTypeProcess(lsTypeService);
            loProcessBean.setLsServiceName(lsNameService);
            
            if(lsTypeService != null){
                /*################ DUMMY ############################*/
                if(lsTypeService.equalsIgnoreCase("DummyProcess")){
                    System.out.println(">>>> Ejecutar processServiceExecution para DummyProcess: ");
                    ExecuteServiceResponseBean loRes =
                    processServiceExecution(loProcessBean, 
                                            ExecuteDummyCron.class,
                                            loService);
                    lsColorMessage = loRes.getLsColor();
                    lsFinalMessage = loRes.getLsMessage();
                }
                /*################ PARRILLAS AND PROGRAMAS ############################*/
                if(lsTypeService.equalsIgnoreCase("ProcessParrillasProgramas")){
                    System.out.println(">>>> Ejecutar processServiceExecution para ProcessParrillasProgramas: ");
                    ExecuteServiceResponseBean loRes =
                        processServiceExecution(loProcessBean, 
                                                ParrillasProgramasCron.class,
                                                loService);
                    lsColorMessage = loRes.getLsColor();
                    lsFinalMessage = loRes.getLsMessage();
                }
                
            }                        
        } catch (Exception loEx) {
            lsFinalMessage = loEx.getMessage();
            lsColorMessage = "red";
            System.out.println("Err 5874 "+loEx.getMessage());
        } finally {
            Configuration.releaseRootApplicationModule(loAm, true);
            loAm.remove();            
            if(!lsGeneralAction.equalsIgnoreCase("EXECUTE")){
                System.out.println("Actualizando tabla principal");
                refreshMainTable();
            }
            
        }
        new UtilFaces().hidePopup(toPopup);
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
     * Cancele ejecución de inicio de cron del servicio seleccionado
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String cancelBeginAction() {
        new UtilFaces().hidePopup(getPoPopupBegin());      
        FacesContext       loContext = FacesContext.getCurrentInstance();
        ExternalContext    loEctx = loContext.getExternalContext();
        String             lsUrl = 
            loEctx.getRequestContextPath() + "/faces/processPage";
        try {
            loEctx.redirect(lsUrl);
        } catch (IOException loEx) {
            ;
        }
        return null;
    }
    
    /**
     * Detiene cron en ejecucion, la siguiente ejecucion no se realiza
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String executeStopAction() {
        executeProcessAction("STOP", 
                             getPoStopIdBinding(), 
                             getPoStopNomBinding(),
                             getPoStopTypeService(),
                             getPoPopupStop());
        return null;
    }

    /**
     * Cancele ejecución manual del servicio seleccionado
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String cancelStopAction() {
        new UtilFaces().hidePopup(getPoPopupStop());      
        FacesContext       loContext = FacesContext.getCurrentInstance();
        ExternalContext    loEctx = loContext.getExternalContext();
        String             lsUrl = 
            loEctx.getRequestContextPath() + "/faces/processPage";
        try {
            loEctx.redirect(lsUrl);
        } catch (IOException loEx) {
            ;
        }
        return null;
    }

    /**
     * Guarda en base de datos la configuracion realizada
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String saveConfigurationAction(){
        boolean   lbProcess = true;
        String    lsCronConfig = null;
        UtilFaces loUtilFaces = new UtilFaces();        
        Integer   liIdConfiguration = null;
        Integer   liIdService = null;
        String    lsIndPeriodicity = null;
        String    lsIndBeginSchedule = null;
        String    lsIndTypeSchedule = null;    
        String    lsIndValTypeSchedule = null;    
        String    lsIndHourDeadLine = null;
        String    lsIndMinuteDeadLine = null;
        
        String    lsIndHourBegin = null;
        String    lsIndMinuteBegin= null;
        
        Integer   liIndDayMonth = null;
        Integer   liIndWeekMonth = null;
        String    lsIndCronExpression   = null;         
        Integer   liIndMonday = null;
        Integer   liIndTuesday = null;
        Integer   liIndWednesday = null;
        Integer   liIndThursday = null;
        Integer   liIndFriday = null;
        Integer   liIndSaturday = null;
        Integer   liIndSunday = null;
        String    lsStatusTab = "1";

        liIdService = getPoIdServiceSelected().getValue() == null ? 0 : 
            Integer.parseInt(getPoIdServiceSelected().getValue().toString());      
        System.out.println("liIdService: "+liIdService);
        lsIndPeriodicity = 
            getPoIdTabSelected().getValue() == null ? "" : 
            getPoIdTabSelected().getValue().toString();   
        System.out.println("lsIndPeriodicity: "+lsIndPeriodicity);
        if(liIdService > 0){
            // ###################### MINUTOS ##########################
            if(lsIndPeriodicity.equalsIgnoreCase("MINUTOS")){
                //Obtener ID_CONFIGURATION                
                lsIndBeginSchedule = null;
                lsIndTypeSchedule = "EVERY";                
                lsIndValTypeSchedule = 
                    getPoCadaMinutos().getValue() == null ? null : 
                    getPoCadaMinutos().getValue().toString();                
                lsIndHourDeadLine = 
                    getPoDeadLineMinutos().getValue() == null ? null : 
                    getPoDeadLineMinutos().getValue().toString(); 
                lsIndMinuteDeadLine = 
                    getPoDeadLineMinMinutos().getValue() == null ? null : 
                    getPoDeadLineMinMinutos().getValue().toString(); 
                
                lsIndHourBegin = 
                    getPoBeginHorasMin().getValue() == null ? "" : 
                    getPoBeginHorasMin().getValue().toString(); 
                lsIndMinuteBegin = 
                    getPoBeginMinutesMin().getValue() == null ? "" : 
                    getPoBeginMinutesMin().getValue().toString(); 
                
                if(lsIndHourBegin.equalsIgnoreCase("")){
                    lsIndHourBegin = "23";
                }
                if(lsIndMinuteBegin.equalsIgnoreCase("")){
                    lsIndMinuteBegin = "59";
                }
                
                lsIndBeginSchedule = new UtilFaces().buildTimeCron(lsIndHourBegin, lsIndMinuteBegin);                
                
                if(lsIndValTypeSchedule != null){
                    lbProcess = true;
                    liIndDayMonth = null;
                    liIndWeekMonth = null;
                    lsCronConfig = 
                        loUtilFaces.getBuildUrlRestMinutes(lsIndValTypeSchedule);
                    lsIndCronExpression = "0 0/" + lsIndValTypeSchedule + " * 1/1 * ? *";
                        //new UtilFaces().getCronExpression(lsCronConfig); 
                    
                    String lsLunes = 
                        getPoLunMinutos().getValue() == null ? "" : 
                        getPoLunMinutos().getValue().toString();
                    if(lsLunes.equalsIgnoreCase("true")){liIndMonday = 1;}else{liIndMonday = 0;}
                    
                    String lsMartes = 
                        getPoMarMinutos().getValue() == null ? "" : 
                        getPoMarMinutos().getValue().toString();
                    if(lsMartes.equalsIgnoreCase("true")){liIndTuesday = 1;}else{liIndTuesday = 0;}
                    
                    String lsMiercoles = 
                        getPoMieMinutos().getValue() == null ? "" : 
                        getPoMieMinutos().getValue().toString();
                    if(lsMiercoles.equalsIgnoreCase("true")){liIndWednesday = 1;}else{liIndWednesday = 0;}
                    
                    String lsJueves = 
                        getPoJueMinutos().getValue() == null ? "" : 
                        getPoJueMinutos().getValue().toString();
                    if(lsJueves.equalsIgnoreCase("true")){liIndThursday = 1;}else{liIndThursday = 0;}
                    
                    String lsViernes = 
                        getPoVieMinutos().getValue() == null ? "" : 
                        getPoVieMinutos().getValue().toString();
                    if(lsViernes.equalsIgnoreCase("true")){liIndFriday = 1;}else{liIndFriday = 0;}
                    
                    String lsSabado = 
                        getPoSabMinutos().getValue() == null ? "" : 
                        getPoSabMinutos().getValue().toString();
                    if(lsSabado.equalsIgnoreCase("true")){liIndSaturday = 1;}else{liIndSaturday = 0;}
                    
                    String lsDomingo = 
                        getPoDomMinutos().getValue() == null ? "" :
                        getPoDomMinutos().getValue().toString();
                    if(lsDomingo.equalsIgnoreCase("true")){liIndSunday = 1;}else{liIndSunday = 0;}
                }else{
                    lbProcess = false;
                }
                resetHoursTab();
                resetDaysTab();                
                resetWeeksTab();
                                
            }
            // ###################### HORAS ##########################
            if(lsIndPeriodicity.equalsIgnoreCase("HORAS")){
                System.out.println("Configuracion para HORAS");
                
                lsIndHourDeadLine = 
                    getPoDeadLineHoras().getValue() == null ? null : 
                    getPoDeadLineHoras().getValue().toString(); 
                System.out.println("lsIndHourDeadLine: "+lsIndHourDeadLine);                

                lsIndMinuteDeadLine = 
                    getPoDeadLineMinHoras().getValue() == null ? null : 
                    getPoDeadLineMinHoras().getValue().toString(); 
                System.out.println("lsIndMinuteDeadLine: "+lsIndMinuteDeadLine);                               
                // Validar si es RadioBegin
                String lsEveryValue = null;
                String lsHours = null;
                String lsMinutes = null;
                //if(lsRadioHourEvery.equalsIgnoreCase("true")){
                    lsIndTypeSchedule = "EVERY";                
                    lsIndValTypeSchedule = 
                        getPoCadaHoras().getValue() == null ? null : 
                        getPoCadaHoras().getValue().toString();
                    System.out.println("lsIndValTypeSchedule: "+lsIndValTypeSchedule);
                    lsEveryValue = lsIndValTypeSchedule;
                    System.out.println("lsEveryValue: "+lsEveryValue);
                    if(lsIndValTypeSchedule == null){
                        lbProcess = false;
                    }
                    lsHours = 
                        getPoBeginHoras().getValue() == null ? "" : 
                        getPoBeginHoras().getValue().toString();
                    System.out.println("lsHours: "+lsHours);
                    lsMinutes = 
                        getPoBeginMinutes().getValue() == null ? "" : 
                        getPoBeginMinutes().getValue().toString();
                    System.out.println("lsMinutes: "+lsMinutes);
                    if(lsHours.equalsIgnoreCase("")){
                        lsHours = "23";
                    }
                    if(lsMinutes.equalsIgnoreCase("")){
                        lsMinutes = "59";
                    }
                    lsIndHourBegin = lsHours;
                    lsIndMinuteBegin = lsMinutes;
                    System.out.println("###### lsIndHourBegin: "+lsIndBeginSchedule);
                    System.out.println("###### lsIndMinuteBegin: "+lsIndMinuteBegin);
                    lsIndBeginSchedule = new UtilFaces().buildTimeCron(lsHours, lsMinutes);
                    System.out.println("lsIndBeginSchedule: "+lsIndBeginSchedule);
                //}                    
                // Validar si es RadioBegin
                lsIndHourBegin = lsHours;
                lsIndMinuteBegin = lsMinutes;
                System.out.println("######***** lsIndHourBegin: "+lsIndBeginSchedule);
                System.out.println("######****** lsIndMinuteBegin: "+lsIndMinuteBegin);                
                lsIndTypeSchedule = "EVERY";
                
                lsIndValTypeSchedule = 
                    getPoCadaHoras().getValue() == null ? null : 
                    getPoCadaHoras().getValue().toString();
                System.out.println("lsIndValTypeSchedule: "+lsIndValTypeSchedule);
                lsEveryValue = lsIndValTypeSchedule;
                System.out.println("lsEveryValue: "+lsEveryValue);
                lsHours = 
                    getPoBeginHoras().getValue() == null ? "" : 
                    getPoBeginHoras().getValue().toString();
                System.out.println("lsHours: "+lsHours);
                lsMinutes = 
                    getPoBeginMinutes().getValue() == null ? "" : 
                    getPoBeginMinutes().getValue().toString();
                System.out.println("lsMinutes: "+lsMinutes);
                if(lsHours.equalsIgnoreCase("")){
                    lsHours = "23";
                }
                System.out.println("lsHours: "+lsHours);
                if(lsMinutes.equalsIgnoreCase("")){
                    lsMinutes = "59";
                }
                System.out.println("lsMinutes: "+lsMinutes);
                lsIndBeginSchedule = new UtilFaces().buildTimeCron(lsHours, lsMinutes);
                System.out.println("lsIndBeginSchedule: "+lsIndBeginSchedule);
                if(lsIndBeginSchedule.equalsIgnoreCase("ERROR")){
                    lbProcess = false;
                }   
                System.out.println("lbProcess 001: "+lbProcess);
                if(lbProcess){
                    liIndDayMonth = null;
                    liIndWeekMonth = null;
                    
                    lsCronConfig = loUtilFaces.getBuildUrlRestHours("true",
                                                                   lsEveryValue,
                                                                   "false",
                                                                   lsHours,
                                                                   lsMinutes);
                    System.out.println("lsCronConfig: "+lsCronConfig);
                    lsIndCronExpression = "0 0 " + lsIndValTypeSchedule + " 1/1 * ? *";
//                            new UtilFaces().getCronExpression(lsCronConfig);
System.out.println("llsIndCronExpression: "+lsIndCronExpression);
                    String lsLunes =
                        getPoLunHoras().getValue() == null ? "" : 
                        getPoLunHoras().getValue().toString();
                    System.out.println("lsLunes 001: "+lsLunes);
                    if(lsLunes.equalsIgnoreCase("true")){liIndMonday = 1;}else{liIndMonday = 0;}
                    
                    String lsMartes = 
                        getPoMarHoras().getValue() == null ? "" :
                        getPoMarHoras().getValue().toString();
                    System.out.println("lsMartes 001: "+lsMartes);
                    if(lsMartes.equalsIgnoreCase("true")){liIndTuesday = 1;}else{liIndTuesday = 0;}
                    
                    String lsMiercoles = 
                        getPoMieHoras().getValue() == null ? "" : 
                        getPoMieHoras().getValue().toString();
                    System.out.println("lsMiercoles 001: "+lsMiercoles);
                    if(lsMiercoles.equalsIgnoreCase("true")){liIndWednesday = 1;}else{liIndWednesday = 0;}
                    
                    String lsJueves =
                        getPoJueHoras().getValue() == null ? "" :
                        getPoJueHoras().getValue().toString();
                    System.out.println("lsJueves 001: "+lsJueves);
                    if(lsJueves.equalsIgnoreCase("true")){liIndThursday = 1;}else{liIndThursday = 0;}
                    
                    String lsViernes = 
                        getPoVieHoras().getValue() == null ? "" : 
                        getPoVieHoras().getValue().toString();
                    System.out.println("lsViernes 001: "+lsViernes);
                    if(lsViernes.equalsIgnoreCase("true")){liIndFriday = 1;}else{liIndFriday = 0;}
                    
                    String lsSabado = 
                        getPoSabHoras().getValue() == null ? "" : 
                        getPoSabHoras().getValue().toString();
                    System.out.println("lsSabado 001: "+lsSabado);
                    if(lsSabado.equalsIgnoreCase("true")){liIndSaturday = 1;}else{liIndSaturday = 0;}
                    
                    String lsDomingo = 
                        getPoDomHoras().getValue() == null ? "" : 
                        getPoDomHoras().getValue().toString();
                    System.out.println("lsDomingo 001: "+lsDomingo);
                    if(lsDomingo.equalsIgnoreCase("true")){liIndSunday = 1;}else{liIndSunday = 0;}
                }
                resetMinuteTab();
                resetDaysTab();
                resetWeeksTab();
            }            
            // ###################### DIAS ##########################
            if(lsIndPeriodicity.equalsIgnoreCase("DIAS")){
                //Obtener ID_CONFIGURATION
                String lsHours = null;
                String lsMinutes = null;
                String lsRadioDayEvery = "true";

                lsIndTypeSchedule = "EVERY";   
                lsHours = 
                    getPoBeginDias().getValue() == null ? null : 
                    getPoBeginDias().getValue().toString();
                
                lsMinutes = 
                    getPoBeginDayMinutes().getValue() == null ? null : 
                    getPoBeginDayMinutes().getValue().toString();
                
                lsIndBeginSchedule = new UtilFaces().buildTimeCron(lsHours, lsMinutes);
                
                if(lsIndBeginSchedule.equalsIgnoreCase("ERROR")){
                    lbProcess = false;
                }
                if(lbProcess){
                    liIndDayMonth = null;
                    liIndWeekMonth = null;
                    
                    lsCronConfig = loUtilFaces.getBuildUrlRestDays(lsRadioDayEvery,
                                                                          "false",
                                                                          lsHours,
                                                                          lsMinutes);
                    
                    lsIndCronExpression   = new UtilFaces().getCronExpression(lsCronConfig); 
                    String lsLunes = 
                        getPoLunDias().getValue() == null ? "" : 
                        getPoLunDias().getValue().toString();
                    if(lsLunes.equalsIgnoreCase("true")){liIndMonday = 1;}else{liIndMonday = 0;}
                    
                    String lsMartes = 
                        getPoMarDias().getValue() == null ? "" : 
                        getPoMarDias().getValue().toString();
                    if(lsMartes.equalsIgnoreCase("true")){liIndTuesday = 1;}else{liIndTuesday = 0;}
                    
                    String lsMiercoles = 
                        getPoMieDias().getValue() == null ? "" : 
                        getPoMieDias().getValue().toString();
                    if(lsMiercoles.equalsIgnoreCase("true")){liIndWednesday = 1;}else{liIndWednesday = 0;}
                    
                    String lsJueves = 
                        getPoJueDias().getValue() == null ? "" : 
                        getPoJueDias().getValue().toString();
                    if(lsJueves.equalsIgnoreCase("true")){liIndThursday = 1;}else{liIndThursday = 0;}
                    
                    String lsViernes =
                        getPoVieDias().getValue() == null ? "" :
                        getPoVieDias().getValue().toString();
                    if(lsViernes.equalsIgnoreCase("true")){liIndFriday = 1;}else{liIndFriday = 0;}
                    
                    String lsSabado = 
                        getPoSabDias().getValue() == null ? "" : 
                        getPoSabDias().getValue().toString();
                    if(lsSabado.equalsIgnoreCase("true")){liIndSaturday = 1;}else{liIndSaturday = 0;}
                    
                    String lsDomingo = 
                        getPoDomDias().getValue() == null ? "" : 
                        getPoDomDias().getValue().toString();
                    if(lsDomingo.equalsIgnoreCase("true")){liIndSunday = 1;}else{liIndSunday = 0;}
                }
                resetMinuteTab();
                resetHoursTab();              
                resetWeeksTab();                  
            }
            // ###################### SEMANAS ##########################
            if(lsIndPeriodicity.equalsIgnoreCase("SEMANAS")){
                lsIndTypeSchedule = "EVERY";
                String lsHours = null;
                String lsMinutes = null;
                //Validar que al menos este seleccionado un dia
                String lsLunes = 
                    getPoLunWeek().getValue() == null ? "" :
                    getPoLunWeek().getValue().toString();
                if(lsLunes.equalsIgnoreCase("true")){liIndMonday = 1;}else{liIndMonday = 0;}
                
                String lsMartes = 
                    getPoMarWeek().getValue() == null ? "" : 
                    getPoMarWeek().getValue().toString();
                if(lsMartes.equalsIgnoreCase("true")){liIndTuesday = 1;}else{liIndTuesday = 0;}
                
                String lsMiercoles = 
                    getPoMieWeek().getValue() == null ? "" :
                    getPoMieWeek().getValue().toString();
                if(lsMiercoles.equalsIgnoreCase("true")){liIndWednesday = 1;}else{liIndWednesday = 0;}
                
                String lsJueves = 
                    getPoJueWeek().getValue() == null ? "" :
                    getPoJueWeek().getValue().toString();
                if(lsJueves.equalsIgnoreCase("true")){liIndThursday = 1;}else{liIndThursday = 0;}
                
                String lsViernes = 
                    getPoVieWeek().getValue() == null ? "" : 
                    getPoVieWeek().getValue().toString();
                if(lsViernes.equalsIgnoreCase("true")){liIndFriday = 1;}else{liIndFriday = 0;}
                
                String lsSabado = 
                    getPoSabWeek().getValue() == null ? "" : 
                    getPoSabWeek().getValue().toString();
                if(lsSabado.equalsIgnoreCase("true")){liIndSaturday = 1;}else{liIndSaturday = 0;}
                
                String lsDomingo = 
                    getPoDomWeek().getValue() == null ? "" : 
                    getPoDomWeek().getValue().toString();
                if(lsDomingo.equalsIgnoreCase("true")){liIndSunday = 1;}else{liIndSunday = 0;}
                
                if(liIndMonday == 0 && liIndTuesday == 0 && liIndWednesday == 0 &&
                liIndThursday == 0 && liIndFriday == 0 && liIndSaturday == 0 && liIndSunday == 0){
                    lbProcess = false;                       
                } 
                if(lbProcess){
                    lsHours = 
                        getPoSemanasHoraIni().getValue() == null ? null : 
                        getPoSemanasHoraIni().getValue().toString();
                    
                    lsMinutes = 
                        getPoBeginSemanasMinutoIni().getValue() == null ? null : 
                        getPoBeginSemanasMinutoIni().getValue().toString();
                    lsIndBeginSchedule = new UtilFaces().buildTimeCron(lsHours, lsMinutes);
                    if(lsIndBeginSchedule.equalsIgnoreCase("ERROR")){
                        lbProcess = false;
                    }
                }
                if(lbProcess){
                    String lsDays = "";
                    if(liIndMonday == 1){
                        lsDays += "Mon,";
                    }
                    if(liIndTuesday == 1){
                        lsDays += "Tue,";
                    }
                    if(liIndWednesday == 1){
                        lsDays += "Wed,";
                    }
                    if(liIndThursday == 1){
                        lsDays += "Thu,";
                    }
                    if(liIndFriday == 1){
                        lsDays += "Fri,";
                    }
                    if(liIndSaturday == 1){
                        lsDays += "Sat,";
                    }
                    if(liIndSunday == 1){
                        lsDays += "Sun,";
                    }
                    
                    lsCronConfig = 
                        loUtilFaces.getBuildUrlRestWeeks(lsDays.substring(0, lsDays.length()-1),
                                                                          lsHours,
                                                                          lsMinutes);
                    
                    lsIndCronExpression = new UtilFaces().getCronExpression(lsCronConfig); 
                }
                resetMinuteTab();
                resetHoursTab();
                resetDaysTab();  
            }            
            System.out.println("lbProcess 002: "+lbProcess);
            if(lbProcess){
                if(lsIndCronExpression != null){                                    
                    ApplicationModule         loAm = 
                        Configuration.createRootApplicationModule(gsAmDef, gsConfig);
                    AppModuleImpl loService = (AppModuleImpl)loAm;
                    
                    try{
                        LmkIntCronConfigRowBean toLmkBean = new LmkIntCronConfigRowBean();
                        //validar si es un update o insert
                        Integer liIdConfigurationUpd = 
                            loService.searchIdCronConfigModel(liIdService);
                        System.out.println("buscar con liIdService: "+liIdService);
                        if(lsIndMinuteDeadLine != null){
                            if(lsIndMinuteDeadLine.length()<2){
                                lsIndMinuteDeadLine = "0"+lsIndMinuteDeadLine;
                            }
                        }
                        toLmkBean.setLiIdConfiguration(liIdConfigurationUpd);
                        toLmkBean.setLiIdService(liIdService);
                        toLmkBean.setLsIndPeriodicity(lsIndPeriodicity);
                       toLmkBean.setLsIndBeginSchedule(lsIndBeginSchedule);
                       toLmkBean.setLsIndBeginMinute(lsIndHourBegin);                        
                       toLmkBean.setLsIndBeginSecond(lsIndMinuteBegin);
                       toLmkBean.setLsIndEndMinute(lsIndHourDeadLine);
                       toLmkBean.setLsIndEndSecond(lsIndMinuteDeadLine);
                       
                       toLmkBean.setLsIndTypeSchedule(lsIndTypeSchedule);
                       toLmkBean.setLsIndValTypeSchedule(lsIndValTypeSchedule);
                       toLmkBean.setLiIndMonday(liIndMonday);
                       toLmkBean.setLiIndTuesday(liIndTuesday);
                       toLmkBean.setLiIndWednesday(liIndWednesday);
                       toLmkBean.setLiIndThursday(liIndThursday);
                       toLmkBean.setLiIndFriday(liIndFriday);
                       toLmkBean.setLiIndSaturday(liIndSaturday);
                       toLmkBean.setLiIndSunday(liIndSunday);
                       toLmkBean.setLiIndDayMonth(liIndDayMonth);
                       toLmkBean.setLiIndWeekMonth(liIndWeekMonth);
                       toLmkBean.setLsIndCronExpression(lsIndCronExpression);
                       
                       toLmkBean.setLsIndEstatus(lsStatusTab);
                        
                        if(liIdConfigurationUpd > 0){
                            System.out.println("UPDATE");
                            loService.updateCronConfigModel(toLmkBean); 
                        }else{
                            liIdConfiguration = 
                                new ViewObjectDao().getMaxIdParadigm("Cron") + 1;
                            toLmkBean.setLiIdConfiguration(liIdConfiguration);
                            loService.insertCronConfigModel(toLmkBean); 
                        }    
                        FacesMessage loMsg;
                        loMsg = new FacesMessage("Operacion Realizada Satisfactoriamente");
                        loMsg.setSeverity(FacesMessage.SEVERITY_INFO);
                        FacesContext.getCurrentInstance().addMessage(null, loMsg);
                    } catch (Exception loEx) {
                        FacesMessage loMsg;
                        loMsg = new FacesMessage("Error de Comunicacion " + loEx);
                        loMsg.setSeverity(FacesMessage.SEVERITY_FATAL);
                        FacesContext.getCurrentInstance().addMessage(null, loMsg);
                    } finally {
                        Configuration.releaseRootApplicationModule(loAm, true);
                    }                    
                }else{
                    FacesMessage loMsg;
                    loMsg = new FacesMessage("No es Posible asignar Periodicidad");
                    loMsg.setSeverity(FacesMessage.SEVERITY_FATAL);
                    FacesContext.getCurrentInstance().addMessage(null, loMsg);
                }
            }else{
                FacesMessage loMsg = 
                    new FacesMessage("No Es Posible Guardar la Configuracion, Revise los Parametros");
                loMsg.setSeverity(FacesMessage.SEVERITY_FATAL);
                FacesContext.getCurrentInstance().addMessage(null, loMsg);
            }
        }
        new UtilFaces().hidePopup(getPoPopupCron());
        return null;
    }

    /**
     * Reinicia valores de configuracion Cron para Minutos
     * @autor Jorge Luis Bautista Santiago
     * @return void
     */
    public void resetMinuteTab(){
        getPoCadaMinutos().setValue(null);
        getPoLunMinutos().setValue(null);
        getPoMarMinutos().setValue(null);
        getPoMieMinutos().setValue(null);
        getPoJueMinutos().setValue(null);
        getPoVieMinutos().setValue(null);
        getPoSabMinutos().setValue(null);
        getPoDomMinutos().setValue(null);
        
        getPoDeadLineMinutos().setValue(null);
        getPoBeginHorasMin().setValue(null);
        getPoBeginMinutesMin().setValue(null);        
        getPoDeadLineMinMinutos().setValue(null);
        
    }
    
    /**
     * Reinicia valores de configuracion Cron para Horas
     * @autor Jorge Luis Bautista Santiago
     * @return void
     */
    public void resetHoursTab(){
        //getPoRadioHourEvery().setValue(null);
        getPoCadaHoras().setValue(null);
        //getPoRadioHourBegin().setValue(null);
        getPoBeginHoras().setValue(null);
        getPoBeginMinutes().setValue(null);
        getPoLunHoras().setValue(null);
        getPoMarHoras().setValue(null);
        getPoMieHoras().setValue(null);
        getPoJueHoras().setValue(null);
        getPoVieHoras().setValue(null);
        getPoSabHoras().setValue(null);
        getPoDomHoras().setValue(null);
        
        getPoDeadLineHoras().setValue(null);
        getPoDeadLineMinHoras().setValue(null);    
    }
    
    /**
     * Reinicia valores de configuracion Cron para Dias
     * @autor Jorge Luis Bautista Santiago
     * @return void
     */
    public void resetDaysTab(){
        getPoRadioDayEvery().setValue(null);
        getPoCadaDias().setValue(null);
        getPoRadioDayBegin().setValue(null);
        getPoBeginDias().setValue(null);
        getPoBeginDayMinutes().setValue(null);
        getPoLunDias().setValue(null);
        getPoMarDias().setValue(null);
        getPoMieDias().setValue(null);
        getPoJueDias().setValue(null);
        getPoVieDias().setValue(null);
        getPoSabDias().setValue(null);
        getPoDomDias().setValue(null);
    }
    
    /**
     * Reinicia valores de configuracion Cron para Semanas
     * @autor Jorge Luis Bautista Santiago
     * @return void
     */
    public void resetWeeksTab(){
        getPoSemanasHoraIni().setValue(null);
        getPoBeginSemanasMinutoIni().setValue(null);
        getPoLunWeek().setValue(null);
        getPoMarWeek().setValue(null);
        getPoMieWeek().setValue(null);
        getPoJueWeek().setValue(null);
        getPoVieWeek().setValue(null);
        getPoSabWeek().setValue(null);
        getPoDomWeek().setValue(null);
    }

    /**
     * Guarda en bd la configuracion cron de ese servicio
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String saveCronAction() {
        saveConfigurationAction();
        return null;
    }

    /**
     * Cancela el guardado de configuracion cron
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String cancelSaveCronAction() {
        new UtilFaces().hidePopup(getPoPopupMerge());      
        FacesContext       loContext = FacesContext.getCurrentInstance();
        ExternalContext    loEctx = loContext.getExternalContext();
        String             lsUrl = 
            loEctx.getRequestContextPath() + "/faces/processPage";
        try {
            loEctx.redirect(lsUrl);
        } catch (IOException loEx) {
            ;
        }
        return null;
    }

    /**
     * Indica al contolador la pestaÃ±a seleccionada
     * @autor Jorge Luis Bautista Santiago
     * @param psDisclosureEvent
     * @return void
     */    
    public void disMinutos(DisclosureEvent psDisclosureEvent) {
        if (psDisclosureEvent.isExpanded()) {
            getPoIdTabSelected().setValue("MINUTOS");
        }
    }
    
     /**
      * Indica al contolador la pestaÃ±a seleccionada
      * @autor Jorge Luis Bautista Santiago
      * @param psDisclosureEvent
      * @return void
      */   
     public void disHoras(DisclosureEvent psDisclosureEvent) {
         if (psDisclosureEvent.isExpanded()) {
             getPoIdTabSelected().setValue("HORAS");
         }
     }
     
     /**
      * Indica al contolador la pestaÃ±a seleccionada
      * @autor Jorge Luis Bautista Santiago
      * @param psDisclosureEvent
      * @return void
      */  
     public void disDias(DisclosureEvent psDisclosureEvent) {
         if (psDisclosureEvent.isExpanded()) {            
             getPoIdTabSelected().setValue("DIAS");
         }
     }
     
     /**
      * Indica al contolador la pestaÃ±a seleccionada
      * @autor Jorge Luis Bautista Santiago
      * @param psDisclosureEvent
      * @return void
      */  
     public void disSemanas(DisclosureEvent psDisclosureEvent) {
         if (psDisclosureEvent.isExpanded()) {            
             getPoIdTabSelected().setValue("SEMANAS");
         }
     }
     
    /**
     * Valida la hora de comienzo respecto a la hora cron
     * @autor Jorge Luis Bautista Santiago
     * @param toValueChangeEvent
     * @return void
     */
    public void validateDayEveryRadio(ValueChangeEvent toValueChangeEvent) {
        toValueChangeEvent.getSource();
        getPoRadioDayEvery().setValue(true);
        getPoRadioDayBegin().setValue(false);
    }
    
    public ProcessBean() {
    }
    
    /**
     * Guarda en bd los parámetros de ese servicio
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String mergeAction() {        
        String                   lsIdService = 
            getPoMergeIdService().getValue() == null ? "" : 
            getPoMergeIdService().getValue().toString();
        
        String                     lsInd2can = 
            getPoMerge2can().getValue() == null ? "":
            getPoMerge2can().getValue().toString();
        String                     lslsInd2canTab = "0";
        if(lsInd2can.equalsIgnoreCase("true")){
            lslsInd2canTab = "1";
        }
        String                     lsInd5can = 
            getPoMerge5can().getValue() == null ? "":
            getPoMerge5can().getValue().toString();
        String                     lslsInd5canTab = "0";
        if(lsInd5can.equalsIgnoreCase("true")){
            lslsInd5canTab = "1";
        }
        String                     lsInd9can = 
            getPoMerge9can().getValue() == null ? "":
            getPoMerge9can().getValue().toString();
        String                     lsInd9canTab = "0";
        if(lsInd9can.equalsIgnoreCase("true")){
            lsInd9canTab = "1";
        }
        String                   lsCliente = 
            getPoMergeCliente().getValue() == null ? "" : 
            getPoMergeCliente().getValue().toString();
        
        ApplicationModule         loAm = 
            Configuration.createRootApplicationModule(gsAmDef, gsConfig);
        AppModuleImpl loService = 
            (AppModuleImpl)loAm;        
        try{
            loService.deleteServicesParamsModelByServ(Integer.parseInt(lsIdService));
            LmkIntServicesParamsRowBean loLmk2CanBean = new LmkIntServicesParamsRowBean();            
            Integer                  liId2can = 
                new ViewObjectDao().getMaxIdParadigm("ServParameters") + 1;
            loLmk2CanBean.setLiIdParameterServ(liId2can);
            loLmk2CanBean.setLiIdService(Integer.parseInt(lsIdService));
            loLmk2CanBean.setLsIndEstatus("1");
            loLmk2CanBean.setLsIndParameter("2CAN");
            loLmk2CanBean.setLsIndValParameter(lslsInd2canTab);                        
            loService.insertServicesParamsModel(loLmk2CanBean);  
            System.out.println("2CAN insert ok");
            LmkIntServicesParamsRowBean loLmk5CanBean = new LmkIntServicesParamsRowBean();            
            Integer                  liId5can = 
                new ViewObjectDao().getMaxIdParadigm("ServParameters") + 1;
            loLmk5CanBean.setLiIdParameterServ(liId5can);
            loLmk5CanBean.setLiIdService(Integer.parseInt(lsIdService));
            loLmk5CanBean.setLsIndEstatus("1");
            loLmk5CanBean.setLsIndParameter("5CAN");
            loLmk5CanBean.setLsIndValParameter(lslsInd5canTab);                        
            loService.insertServicesParamsModel(loLmk5CanBean);  
            System.out.println("5CAN insert ok");
            LmkIntServicesParamsRowBean loLmk9CanBean = new LmkIntServicesParamsRowBean();            
            Integer                  liId9can = 
                new ViewObjectDao().getMaxIdParadigm("ServParameters") + 1;
            loLmk9CanBean.setLiIdParameterServ(liId9can);
            loLmk9CanBean.setLiIdService(Integer.parseInt(lsIdService));
            loLmk9CanBean.setLsIndEstatus("1");
            loLmk9CanBean.setLsIndParameter("9CAN");
            loLmk9CanBean.setLsIndValParameter(lsInd9canTab);                        
            loService.insertServicesParamsModel(loLmk9CanBean);  
            System.out.println("9CAN insert ok");
            LmkIntServicesParamsRowBean loLmkCtenBean = new LmkIntServicesParamsRowBean();            
            Integer                  liIdCte = 
                new ViewObjectDao().getMaxIdParadigm("ServParameters") + 1;
            loLmkCtenBean.setLiIdParameterServ(liIdCte);
            loLmkCtenBean.setLiIdService(Integer.parseInt(lsIdService));
            loLmkCtenBean.setLsIndEstatus("1");
            loLmkCtenBean.setLsIndParameter("CLIENTE");
            loLmkCtenBean.setLsIndValParameter(lsCliente);                        
            loService.insertServicesParamsModel(loLmkCtenBean);  
            System.out.println("CLIENTE insert ok");
            FacesMessage loMsg;
            loMsg = new FacesMessage("Parámetros Asignados Satisfactoriamente");
            loMsg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, loMsg);
        } catch (Exception loEx) {
            FacesMessage loMsg;
            loMsg = new FacesMessage("Error de Comunicacion " + loEx);
            loMsg.setSeverity(FacesMessage.SEVERITY_FATAL);
            FacesContext.getCurrentInstance().addMessage(null, loMsg);
        } finally {
            Configuration.releaseRootApplicationModule(loAm, true);
        }
        
        new UtilFaces().hidePopup(getPoPopupMerge());
        
        return null;
    }

     /**
      * Cierra en pantalla el popup para configurar parametros
      * @autor Jorge Luis Bautista Santiago  
      * @param toActionEvent
      * @return void
      */
    public String cancelMergeAction() {
         
        getPoMerge2can().setSelected(false);        
        getPoMerge5can().setSelected(false);        
        getPoMerge9can().setSelected(false);   
        getPoMergeCliente().setValue(null);   
        getPoMergeIdService().setValue(null);   
        getPoMergeNomService().setValue(null);   
        
        new UtilFaces().hidePopup(getPoPopupMerge());      
        FacesContext       loContext = FacesContext.getCurrentInstance();
        ExternalContext    loEctx = loContext.getExternalContext();
        String             lsUrl = 
            loEctx.getRequestContextPath() + "/faces/processPage";
        try {
            loEctx.redirect(lsUrl);
        } catch (IOException loEx) {
            ;
        }
        return null;
    }

    /**
     * Muestra en pantalla el popup para configurar parametros
     * @autor Jorge Luis Bautista Santiago  
     * @param toActionEvent
     * @return void
     */
    public void showAddParameters(ActionEvent loActionEvent) {
        loActionEvent.getSource();
        FacesCtrlHierNodeBinding loNode = 
            (FacesCtrlHierNodeBinding) getPoTblMain().getSelectedRowData();
        String                   lsIdService = 
            loNode.getAttribute("IdService") == null ? "" : 
            loNode.getAttribute("IdService").toString();
        String                   lsDesService = 
            loNode.getAttribute("IndDescService") == null ? "" : 
            loNode.getAttribute("IndDescService").toString();
        
        getPoMergeIdService().setValue(lsIdService);        
        getPoMergeNomService().setValue(lsDesService);
        
        // Obtener valores si es que ya se han guardado y settear valores  
        ApplicationModule         loAm = 
            Configuration.createRootApplicationModule(gsAmDef, gsConfig);
        AppModuleImpl loService = 
            (AppModuleImpl)loAm;        
        try{
            
            List<LmkIntServicesParamsRowBean> loColBean = 
                new ArrayList<LmkIntServicesParamsRowBean>();
            
            loColBean = loService.getParametersServiceByIdService(Integer.parseInt(lsIdService));
            
            if(loColBean.size()>0){
                for(LmkIntServicesParamsRowBean loBean : loColBean){
                    if(loBean.getLsIndParameter().equalsIgnoreCase("2CAN")){
                        if(loBean.getLsIndValParameter().equalsIgnoreCase("1")){
                            getPoMerge2can().setSelected(true);        
                        }
                        else{
                            getPoMerge2can().setSelected(false);        
                        }
                    }
                    if(loBean.getLsIndParameter().equalsIgnoreCase("5CAN")){
                        if(loBean.getLsIndValParameter().equalsIgnoreCase("1")){
                            getPoMerge5can().setSelected(true);        
                        }
                        else{
                            getPoMerge5can().setSelected(false);        
                        }
                    }
                    if(loBean.getLsIndParameter().equalsIgnoreCase("9CAN")){
                        if(loBean.getLsIndValParameter().equalsIgnoreCase("1")){
                            getPoMerge9can().setSelected(true);        
                        }
                        else{
                            getPoMerge9can().setSelected(false);        
                        }
                    }
                    if(loBean.getLsIndParameter().equalsIgnoreCase("CLIENTE")){
                        getPoMergeCliente().setValue(loBean.getLsIndValParameter());    
                    }
                }
            }else{
                getPoMerge2can().setSelected(false);        
                getPoMerge5can().setSelected(false);        
                getPoMerge9can().setSelected(false);   
                getPoMergeCliente().setValue(null);    
            }
            
            
            
            
        }catch (Exception loEx) {
            FacesMessage loMsg;
            loMsg = new FacesMessage("No es posible obtener parémetros " + loEx);
            loMsg.setSeverity(FacesMessage.SEVERITY_FATAL);
            FacesContext.getCurrentInstance().addMessage(null, loMsg);
        } finally {
            Configuration.releaseRootApplicationModule(loAm, true);
        }
        
        new UtilFaces().showPopup(getPoPopupMerge());
    }
    
    /**
     * Reinicia los valores de busqueda
     * @autor Jorge Luis Bautista Santiago  
     * @param toActionEvent
     * @return void
     */
    public void resetFilterValues(ActionEvent toActionEvent) {
        toActionEvent.getSource();
        getPoFilterService().setValue("");
        getPoFilterWsdl().setValue("");
        getPoFilterSystem().setValue("");
        getPoFilterSystemOr().setValue("");
        getPoFilterSystemDes().setValue("");
        getPoFilterStatusSel().setValue("");
        getPoFilterAsynSel().setValue("");
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
    
    /**
     * Ejecuta la busqueda en base a los parametros
     * @autor Jorge Luis Bautista Santiago       
     * @return String
     */
    public String searchFilterAction() {
        String lsQuery = " 1 = 1 ";
        String lsNomServiceSelected = 
            getPoFilterDesServiceSel().getValue() == null ? "" : 
            getPoFilterDesServiceSel().getValue().toString();        
        if(!lsNomServiceSelected.equalsIgnoreCase("")){
            lsQuery += " AND ID_SERVICE = " + lsNomServiceSelected;
        }
        
        String lsNomService = 
            getPoFilterServiceSel().getValue() == null ? "" :
            getPoFilterServiceSel().getValue().toString();        
        if(!lsNomService.equalsIgnoreCase("")){
            lsQuery += " AND UPPER(NOM_SERVICE) LIKE '" + 
                       lsNomService.toUpperCase() + "%'";
        }
        /*
        String lsIndServiceWsdl = 
            getPoFilterWsdl().getValue() == null ? "" :
            getPoFilterWsdl().getValue().toString();
        if(!lsIndServiceWsdl.equalsIgnoreCase("")){
            lsQuery += " AND UPPER(IND_SERVICE_WSDL) LIKE '" +
                       lsIndServiceWsdl.toUpperCase() + "%'";
        }
                
        String lsIndSystem = 
            getPoFilterSystem().getValue() == null ? "" : 
            getPoFilterSystem().getValue().toString();
        if(!lsIndSystem.equalsIgnoreCase("")){
            lsQuery += " AND IND_SYSTEM = '" + lsIndSystem + "'";
        }
        String lsIndOrigin = 
            getPoFilterSystemOr().getValue() == null ? "" : 
            getPoFilterSystemOr().getValue().toString();
        if(!lsIndOrigin.equalsIgnoreCase("")){
            lsQuery += " AND IND_ORIGIN = '" + lsIndOrigin + "'";
        }
        
        String lsIndDestiny = 
            getPoFilterSystemDes().getValue() == null ? "" :
            getPoFilterSystemDes().getValue().toString();
        if(!lsIndDestiny.equalsIgnoreCase("")){
            lsQuery += " AND IND_DESTINY = '" + lsIndDestiny + "'";
        }
        
        String lsIndSynchronous = 
            getPoFilterAsynSel().getValue() == null ? "" :
            getPoFilterAsynSel().getValue().toString();
        if(!lsIndSynchronous.equalsIgnoreCase("")){
            lsQuery += " AND IND_SYNCHRONOUS = '" + lsIndSynchronous + "'";
        }                      

        */
        String lsIndEstatus = 
            getPoFilterStatusSel().getValue() == null ? "" :
            getPoFilterStatusSel().getValue().toString();        
        if(!lsIndEstatus.equalsIgnoreCase("")){
            lsQuery += " AND IND_ESTATUS = '" + lsIndEstatus + "'";
        }
                
        new UtilFaces().refreshTableWhereIterator(lsQuery,
                                                  gsEntityIterator,
                                                  getPoTblMain()
                                                  );
        return null;
    }


    public void showSavePopup(ActionEvent toActionEvent) {
        toActionEvent.getSource();
        getPoSaveNomService().setValue("");
        getPoSaveWsdl().setValue("");
        getPoSaveSystem().setValue("");
        getPoSaveSystemOr().setValue("");
        getPoSaveSystemDes().setValue("");
        getPoSaveStatus().setValue("");
        getPoSaveAsyn().setValue("");
        new UtilFaces().showPopup(getPoPopupSave());
    }

    /**
     * Muesta valores para configurar cron por servicio seleccionado
     * @autor Jorge Luis Bautista Santiago
     * @param tsStrSearch
     * @return String
     */
    
    public void showEditPopup(ActionEvent loActionEvent) {  
        loActionEvent.getSource();
        FacesCtrlHierNodeBinding loNode = 
            (FacesCtrlHierNodeBinding) getPoTblMain().getSelectedRowData();
        String                   lsIdService = 
            loNode.getAttribute("IdService") == null ? "" : 
            loNode.getAttribute("IdService").toString();
        String                   lsNomService = 
            loNode.getAttribute("NomService") == null ? "" : 
            loNode.getAttribute("NomService").toString();
        String                   lsDesService = 
            loNode.getAttribute("IndDescService") == null ? "" : 
            loNode.getAttribute("IndDescService").toString();
        String                   lsIndServiceWsdl = 
            loNode.getAttribute("IndServiceWsdl") == null ? "" : 
            loNode.getAttribute("IndServiceWsdl").toString();
        String                   lsIndSystem = 
            loNode.getAttribute("IndSystem") == null ? "" : 
            loNode.getAttribute("IndSystem").toString();
        String                   lsIndOrigin = 
            loNode.getAttribute("IndOrigin") == null ? "" : 
            loNode.getAttribute("IndOrigin").toString();
        String                   lsIndDestiny = 
            loNode.getAttribute("IndDestiny") == null ? "" : 
            loNode.getAttribute("IndDestiny").toString();
        String                   lsIndSynchronous = 
            loNode.getAttribute("IndSynchronous") == null ? "" : 
            loNode.getAttribute("IndSynchronous").toString();
        String                   lsIndEstatus = 
            loNode.getAttribute("IndEstatus") == null ? "" : 
            loNode.getAttribute("IndEstatus").toString();

        getPoUpdIdService().setValue(lsIdService);        
        getPoUpdNomService().setValue(lsNomService);
        getPoUpdDesService().setValue(lsDesService);
        getPoUpdWsdl().setValue(lsIndServiceWsdl);
        getPoUpdSystem().setValue(lsIndSystem);
        getPoUpdSystemOr().setValue(lsIndOrigin);
        getPoUpdSystemDes().setValue(lsIndDestiny);
                
        if(lsIndSynchronous.equalsIgnoreCase("1")){
            getPoUpdAsyn().setSelected(true);
        }else{
            getPoUpdAsyn().setSelected(false);
        }
        if(lsIndEstatus.equalsIgnoreCase("1")){
            getPoUpdStatus().setSelected(true);
        }else{
            getPoUpdStatus().setSelected(false);
        }
        
        new UtilFaces().showPopup(getPoPopupUpdate());
    }

    /**
     * Muestra popup que solicita datos para eliminar el servicio
     * @autor Jorge Luis Bautista Santiago  
     * @param toActionEvent
     * @return void
     */
    public void showDeletePopup(ActionEvent toActionEvent) {
        toActionEvent.getSource();
        FacesCtrlHierNodeBinding loNode = 
            (FacesCtrlHierNodeBinding) getPoTblMain().getSelectedRowData();
        String                   lsIdService = 
            loNode.getAttribute("IdService") == null ? "" : 
            loNode.getAttribute("IdService").toString();
        String                   lsServiceName = 
            loNode.getAttribute("NomService") == null ? "" : 
            loNode.getAttribute("NomService").toString();
        String                   lsServiceDesc = 
            loNode.getAttribute("IndDescService") == null ? "" : 
            loNode.getAttribute("IndDescService").toString();
        getPoDeleteIdBinding().setValue(lsIdService);
        getPoDeleteMsgLbl().setLabel("Eliminar a " + lsServiceDesc + " - " + lsServiceName + "?");
        new UtilFaces().showPopup(getPoPopupDelete());
    }
    
    /**
     * Deshabilita valores de configuracion Cron para Minutos
     * @autor Jorge Luis Bautista Santiago
     * @param tbFlag
     * @return void
     */
    public void resetDisableMinuteTab(boolean tbFlag){
        getPoCadaMinutos().setDisabled(tbFlag);
        getPoLunMinutos().setDisabled(tbFlag);
        getPoMarMinutos().setDisabled(tbFlag);
        getPoMieMinutos().setDisabled(tbFlag);
        getPoJueMinutos().setDisabled(tbFlag);
        getPoVieMinutos().setDisabled(tbFlag);
        getPoSabMinutos().setDisabled(tbFlag);
        getPoDomMinutos().setDisabled(tbFlag);
        getPoDeadLineMinutos().setDisabled(tbFlag);        
        getPoDeadLineMinMinutos().setDisabled(tbFlag);
        getPoBeginHorasMin().setDisabled(tbFlag);
        getPoBeginMinutesMin().setDisabled(tbFlag);
    }
    
    /**
     * Deshabilita valores de configuracion Cron para Horas
     * @autor Jorge Luis Bautista Santiago
     * @param tbFlag
     * @return void
     */
    public void resetDisableHoursTab(boolean tbFlag){
        //getPoRadioHourEvery().setDisabled(tbFlag);
        getPoCadaHoras().setDisabled(tbFlag);
        //getPoRadioHourBegin().setDisabled(tbFlag);
        getPoBeginHoras().setDisabled(tbFlag);
        getPoBeginMinutes().setDisabled(tbFlag);
        getPoLunHoras().setDisabled(tbFlag);
        getPoMarHoras().setDisabled(tbFlag);
        getPoMieHoras().setDisabled(tbFlag);
        getPoJueHoras().setDisabled(tbFlag);
        getPoVieHoras().setDisabled(tbFlag);
        getPoSabHoras().setDisabled(tbFlag);
        getPoDomHoras().setDisabled(tbFlag);
        
        getPoDeadLineHoras().setDisabled(tbFlag);
        getPoDeadLineMinHoras().setDisabled(tbFlag);
    }
    
    /**
     * Deshabilita valores de configuracion Cron para Dias
     * @autor Jorge Luis Bautista Santiago
     * @param tbFlag
     * @return void
     */
    public void resetDisableDaysTab(boolean tbFlag){
        getPoRadioDayEvery().setDisabled(tbFlag);
        getPoCadaDias().setDisabled(tbFlag);
        getPoRadioDayBegin().setDisabled(tbFlag);
        getPoBeginDias().setDisabled(tbFlag);
        getPoBeginDayMinutes().setDisabled(tbFlag);
        getPoLunDias().setDisabled(tbFlag);
        getPoMarDias().setDisabled(tbFlag);
        getPoMieDias().setDisabled(tbFlag);
        getPoJueDias().setDisabled(tbFlag);
        getPoVieDias().setDisabled(tbFlag);
        getPoSabDias().setDisabled(tbFlag);
        getPoDomDias().setDisabled(tbFlag);
    }
    
    /**
     * Deshabilita valores de configuracion Cron para Semanas
     * @autor Jorge Luis Bautista Santiago
     * @param tbFlag
     * @return void
     */
    public void resetDisableWeeksTab(boolean tbFlag){
        getPoSemanasHoraIni().setDisabled(tbFlag);
        getPoBeginSemanasMinutoIni().setDisabled(tbFlag);
        getPoLunWeek().setDisabled(tbFlag);
        getPoMarWeek().setDisabled(tbFlag);
        getPoMieWeek().setDisabled(tbFlag);
        getPoJueWeek().setDisabled(tbFlag);
        getPoVieWeek().setDisabled(tbFlag);
        getPoSabWeek().setDisabled(tbFlag);
        getPoDomWeek().setDisabled(tbFlag);
    }
    
    /**
     * Deshabilita todas las pestaÃ±as
     * @autor Jorge Luis Bautista Santiago
     * @param tbFlag
     * @return void
     */
    public void resetDisableAllTab(boolean tbFlag){
        getPoTabMinutos().setDisabled(tbFlag);
        getPoTabHoras().setDisabled(tbFlag);
        getPoTabDias().setDisabled(tbFlag);
        getPoTabSemanas().setDisabled(tbFlag);
    }

    /**
     * Visualiza en pantalla la tabla deseada
     * @autor Jorge Luis Bautista Santiago
     * @param toTabBind
     * @param toRichPanelTabbed
     * @return void
     */
    public void setDisclosedFirstTab(RichShowDetailItem toTabBind,
                                     RichPanelTabbed toRichPanelTabbed) {
        for (UIComponent child : toRichPanelTabbed.getChildren()) {
            RichShowDetailItem sdi = (RichShowDetailItem) child;
            if (sdi.getClientId().equals(toTabBind.getClientId())) {
                sdi.setDisclosed(true);
            } else {
                sdi.setDisclosed(false);
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(toRichPanelTabbed);
    }
       
    /**
     * Muestra panel de tab para configurar crones
     * @autor Jorge Luis Bautista Santiago  
     * @param actionEvent, 
     * @return void
     */
    public void showTabCronService(ActionEvent toActionEvent) {
        toActionEvent.getSource();
        toActionEvent.getSource();
        resetDisableMinuteTab(false);
        resetDisableHoursTab(false);
        resetDisableDaysTab(false);
        resetDisableWeeksTab(false);
        resetDisableAllTab(false);    
        FacesCtrlHierNodeBinding  loNode = 
            (FacesCtrlHierNodeBinding) getPoTblMain().getSelectedRowData();
        String                    lsIdService = 
            loNode.getAttribute("IdService") == null ? "" : 
            loNode.getAttribute("IdService").toString();
        getPoIdServiceSelected().setValue(lsIdService);
               
        ApplicationModule         loAm = 
            Configuration.createRootApplicationModule(gsAmDef, gsConfig);
        AppModuleImpl loService = (AppModuleImpl)loAm;
        try{
            Integer liRes = loService.searchCronConfigModel(Integer.parseInt(lsIdService));  
            String                lsIdNomService = 
                loNode.getAttribute("IndDescService") == null ? "" : 
                loNode.getAttribute("IndDescService").toString();
            if(liRes == 0){
                getPoOperation().setValue("INSERT");
                resetMinuteTab();resetHoursTab();resetDaysTab();resetWeeksTab();             
                getPoIdTabSelected().setValue("MINUTOS");
                getPoOperation().setValue("INSERT");
                setDisclosedFirstTab(getPoTabMinutos(),getPoPanelTabbed());
                getPoServiceToCron().setText(lsIdNomService);
                getPoServiceToCron().setVisible(true);
                getPoServiceToCronStatus().setText("");
                //getPoServiceToCronStatus().setVisible(false);
            }else{
                getPoOperation().setValue("UPDATE");
                getPoNomServicioCron().setValue(lsIdNomService);
                getPoServiceToCron().setText(lsIdNomService);
                getPoServiceToCron().setVisible(true);
                Integer liIdCronConf = loService.searchIdCronConfigModel(Integer.parseInt(lsIdService));  
                getPoIdCronConfiguration().setValue(liIdCronConf);
                
                //SETTEAR PANEL DE TAB
                LmkIntCronConfigRowBean loRowCron = 
                    loService.getRowCronConfigByServiceModel(Integer.parseInt(lsIdService));
                setTabPanelToEdit(loRowCron);                    
            }
        } catch (Exception loEx) {
            FacesMessage loMsg;
            loMsg = new FacesMessage("Error de Comunicacion " + loEx);
            loMsg.setSeverity(FacesMessage.SEVERITY_FATAL);
            FacesContext.getCurrentInstance().addMessage(null, loMsg);
        } finally {
            Configuration.releaseRootApplicationModule(loAm, true);
        }                     
        new UtilFaces().showPopup(getPoPopupCron());
    }


    public String saveAction() {
        Integer liIdService = new ViewObjectDao().getMaxIdServicesCatalog() + 1;
        String lsDescService = 
            getPoSaveNomService().getValue() == null ? "" : 
            getPoSaveNomService().getValue().toString();
        String lsNomService = 
            getPoSaveNomServiceSel().getValue() == null ? "" : 
            getPoSaveNomServiceSel().getValue().toString();
        String lsIndServiceWsdl = 
            getPoSaveWsdl().getValue() == null ? "" :
            getPoSaveWsdl().getValue().toString();
        String lsIndSystem = 
            getPoSaveSystem().getValue() == null ? "" : 
            getPoSaveSystem().getValue().toString();
        String lsIndOrigin = 
            getPoSaveSystemOr().getValue() == null ? "" :
            getPoSaveSystemOr().getValue().toString();
        String lsIndDestiny = 
            getPoSaveSystemDes().getValue() == null ? "" : 
            getPoSaveSystemDes().getValue().toString();
        String lsIndEstatus = 
            getPoSaveStatus().getValue() == null ? "" : 
            getPoSaveStatus().getValue().toString();
        String lsIndSynchronous = 
            getPoSaveAsyn().getValue() == null ? "" : 
            getPoSaveAsyn().getValue().toString();
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
            Integer liRes = 0;
            liRes = loService.validateExistByNomServicesCatModel(lsNomService);
            if(liRes == 0){
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
                
                loService.insertServicesCatModel(loLmkBean);  
            }else{
                FacesMessage loMsg;
                loMsg = new FacesMessage("El Servicio Solo Puede Existir Una Vez");
                loMsg.setSeverity(FacesMessage.SEVERITY_WARN);
                FacesContext.getCurrentInstance().addMessage(null, loMsg);
            }
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
        new UtilFaces().hidePopup(getPoPopupSave());
        return null;
    }
    
    /**
     * settea valores en el tab panel para editar
     * @autor Jorge Luis Bautista Santiago  
     * @param toRowCron, 
     * @return void
     */
    public void setTabPanelToEdit(LmkIntCronConfigRowBean toRowCron){
        String  lsIndPeriodicity = 
            toRowCron.getLsIndPeriodicity() == null ? "": 
            toRowCron.getLsIndPeriodicity();
        System.out.println("lsIndPeriodicity: "+lsIndPeriodicity);
        String  lsIndBeginSchedule = 
            toRowCron.getLsIndBeginSchedule() == null ? "": 
            toRowCron.getLsIndBeginSchedule();
        System.out.println("lsIndBeginSchedule: "+lsIndBeginSchedule);
        String  lsIndTypeSchedule =
            toRowCron.getLsIndTypeSchedule() == null ? "": 
            toRowCron.getLsIndTypeSchedule();           
        System.out.println("lsIndTypeSchedule: "+lsIndTypeSchedule);
        String  lsIndValTypeSchedule = 
            toRowCron.getLsIndValTypeSchedule() == null ? "": 
            toRowCron.getLsIndValTypeSchedule();    
        System.out.println("lsIndValTypeSchedule: "+lsIndValTypeSchedule);
        String  lsHourDl  = 
            toRowCron.getLsIndEndMinute() == null ? "": 
            toRowCron.getLsIndEndMinute();
        System.out.println("lsHourDl: "+lsHourDl);
        String  lsMinuteDl   = 
            toRowCron.getLsIndEndSecond() == null ? "": 
            toRowCron.getLsIndEndSecond();
        System.out.println("lsMinuteDl: "+lsMinuteDl);
        String  lsHourBegin  = 
            toRowCron.getLsIndBeginMinute() == null ? "": 
            toRowCron.getLsIndBeginMinute();
        System.out.println("lsHourBegin: "+lsHourBegin);
        String  lsMinuteBegin   = 
            toRowCron.getLsIndBeginSecond() == null ? "": 
            toRowCron.getLsIndBeginSecond();
        System.out.println("lsMinuteBegin: "+lsMinuteBegin);
        //String  lsAttribute1 = toRowCron.getAttribute1() == null ? "" : toRowCron.getAttribute1();
        boolean lbMonday = false;
        boolean lbTuesday = false;
        boolean lbWednesday = false;
        boolean lbThursday = false;
        boolean lbFriday = false;
        boolean lbSaturday = false;
        boolean lbSunday = false;
       
        Integer piIndMonday = toRowCron.getLiIndMonday();
        System.out.println("piIndMonday: "+piIndMonday);
        if(piIndMonday != null){lbMonday = piIndMonday == 1 ? true : false;}        
        Integer piIndTuesday = toRowCron.getLiIndTuesday();
        System.out.println("piIndTuesday: "+piIndTuesday);
        if(piIndMonday != null){lbTuesday = piIndTuesday == 1 ? true : false;}
        Integer piIndWednesday = toRowCron.getLiIndWednesday();
        System.out.println("piIndWednesday: "+piIndWednesday);
        if(piIndMonday != null){lbWednesday = piIndWednesday == 1 ? true : false;}
        Integer piIndThursday = toRowCron.getLiIndThursday();
        System.out.println("piIndThursday: "+piIndThursday);
        if(piIndMonday != null){lbThursday = piIndThursday == 1 ? true : false;}
        Integer piIndFriday = toRowCron.getLiIndFriday();
        System.out.println("piIndFriday: "+piIndFriday);
        if(piIndMonday != null){lbFriday = piIndFriday == 1 ? true : false;}
        Integer piIndSaturday = toRowCron.getLiIndSaturday();
        System.out.println("piIndSaturday: "+piIndSaturday);
        if(piIndMonday != null){lbSaturday = piIndSaturday == 1 ? true : false;}
        Integer piIndSunday = toRowCron.getLiIndSunday();
        System.out.println("piIndSunday: "+piIndSunday);
        if(piIndMonday != null){lbSunday = piIndSunday == 1 ? true : false;}
        getPoIdTabSelected().setValue(lsIndPeriodicity);
        String lsStatusService = null;
        if(toRowCron.getLsIndEstatus() != null){
            if(toRowCron.getLsIndEstatus().equalsIgnoreCase("1")){lsStatusService = "Configurado";}
            if(toRowCron.getLsIndEstatus().equalsIgnoreCase("2")){lsStatusService = "En Ejecucion";}
            if(toRowCron.getLsIndEstatus().equalsIgnoreCase("3")){lsStatusService = "Detenido";}
        }else{
            lsStatusService = "Sin Configuracion";
        }
        getPoServiceToCronStatus().setText("Estado: "+lsStatusService);
        //getPoServiceToCronStatus().setVisible(true);
        if(lsIndPeriodicity.equalsIgnoreCase("MINUTOS")){  
            System.out.println("Configuracion para MINUTOS");
            resetHoursTab();
            resetDaysTab();
            resetWeeksTab();            
            getPoCadaMinutos().setValue(lsIndValTypeSchedule);
            getPoLunMinutos().setValue(lbMonday);
            getPoMarMinutos().setValue(lbTuesday);
            getPoMieMinutos().setValue(lbWednesday);
            getPoJueMinutos().setValue(lbThursday);
            getPoVieMinutos().setValue(lbFriday);
            getPoSabMinutos().setValue(lbSaturday);
            getPoDomMinutos().setValue(lbSunday);
            getPoDeadLineMinutos().setValue(lsHourDl);
            getPoDeadLineMinMinutos().setValue(lsMinuteDl);
            //String[] laBegin = lsIndBeginSchedule.split(":");
            //if(laBegin.length > 1){
                getPoBeginHorasMin().setValue(lsHourBegin);
                getPoBeginMinutesMin().setValue(lsMinuteBegin);    
            //}                        
            setDisclosedFirstTab(getPoTabMinutos(),getPoPanelTabbed());
        }
        
        if(lsIndPeriodicity.equalsIgnoreCase("HORAS")){ 
            resetMinuteTab();
            resetDaysTab();
            resetWeeksTab();
            if(lsIndTypeSchedule.equalsIgnoreCase("AT")){ //Configuracion por hora inicial
                getPoCadaHoras().setValue(lsIndValTypeSchedule);
                getPoBeginHoras().setValue(lsHourBegin);
                getPoBeginMinutes().setValue(lsMinuteBegin);                
                getPoCadaHoras().setValue(null);
            }else{
                getPoCadaHoras().setValue(lsIndValTypeSchedule);            
                getPoBeginHoras().setValue(lsHourBegin);
                getPoBeginMinutes().setValue(lsMinuteBegin);
            }
            getPoLunHoras().setValue(lbMonday);
            getPoMarHoras().setValue(lbTuesday);
            getPoMieHoras().setValue(lbWednesday);
            getPoJueHoras().setValue(lbThursday);
            getPoVieHoras().setValue(lbFriday);
            getPoSabHoras().setValue(lbSaturday);
            getPoDomHoras().setValue(lbSunday);
            getPoDeadLineHoras().setValue(lsHourDl);
            getPoDeadLineMinHoras().setValue(lsMinuteDl);
            setDisclosedFirstTab(getPoTabHoras(),getPoPanelTabbed());
        }
        
        if(lsIndPeriodicity.equalsIgnoreCase("DIAS")){  
            resetHoursTab();
            resetMinuteTab();
            resetWeeksTab();
            String[] laBegin = lsIndBeginSchedule.split(":");
            getPoCadaHoras().setValue(lsIndValTypeSchedule);                             
            getPoBeginDias().setValue(laBegin[0]);
            getPoBeginDayMinutes().setValue(laBegin[1]);
            getPoLunDias().setValue(lbMonday);
            getPoMarDias().setValue(lbTuesday);
            getPoMieDias().setValue(lbWednesday);
            getPoJueDias().setValue(lbThursday);
            getPoVieDias().setValue(lbFriday);
            getPoSabDias().setValue(lbSaturday);
            getPoDomDias().setValue(lbSunday);
            setDisclosedFirstTab(getPoTabDias(),getPoPanelTabbed());
        }
        
        if(lsIndPeriodicity.equalsIgnoreCase("SEMANAS")){  
            resetHoursTab();
            resetDaysTab();
            resetMinuteTab();
            String[] laBegin = lsIndBeginSchedule.split(":");
            getPoSemanasHoraIni().setValue(laBegin[0]);
            getPoBeginSemanasMinutoIni().setValue(laBegin[1]);
            getPoLunWeek().setValue(lbMonday);
            getPoMarWeek().setValue(lbTuesday);
            getPoMieWeek().setValue(lbWednesday);
            getPoJueWeek().setValue(lbThursday);
            getPoVieWeek().setValue(lbFriday);
            getPoSabWeek().setValue(lbSaturday);
            getPoDomWeek().setValue(lbSunday);
            
            setDisclosedFirstTab(getPoTabSemanas(),getPoPanelTabbed());
            
        }        
       
    }
    
    /**
     * Cancela la accion de guardar y oculta el popup
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String cancelSaveAction() {
        new UtilFaces().hidePopup(getPoPopupSave());
        return null;
    }

    /**
     * Elimina el servicio web de la base de datos
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String deleteAction() {
        String                    lsIdService = 
            getPoDeleteIdBinding().getValue() == null ? "" : 
            getPoDeleteIdBinding().getValue().toString();
        ApplicationModule         loAm = 
            Configuration.createRootApplicationModule(gsAmDef, gsConfig);
        AppModuleImpl loService = (AppModuleImpl)loAm;
        try{
            loService.deleteServicesCatModel(Integer.parseInt(lsIdService));            
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
    public String cancelDeleteAction() {
        new UtilFaces().hidePopup(getPoPopupDelete());
        return null;
    }

    /**
     * Actualiza en base de datos el servicio seleccionado
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String updateAction() {        
        String                   lsIdService = 
            getPoUpdIdService().getValue() == null ? "" : 
            getPoUpdIdService().getValue().toString();
        String                   lsNomService = 
            getPoUpdNomService().getValue() == null ? "" : 
            getPoUpdNomService().getValue().toString();
        String                   lsDesService = 
            getPoUpdDesService().getValue() == null ? "" : 
            getPoUpdDesService().getValue().toString();
        String                   lsIndServiceWsdl = 
            getPoUpdWsdl().getValue() == null ? "" :
            getPoUpdWsdl().getValue().toString();
        String                   lsIndSystem = 
            getPoUpdSystem().getValue() == null ? "" : 
            getPoUpdSystem().getValue().toString();
        String                   lsIndOrigin = 
            getPoUpdSystemOr().getValue() == null ? "" : 
            getPoUpdSystemOr().getValue().toString();
        String                   lsIndDestiny = 
            getPoUpdSystemDes().getValue() == null ? "" :
            getPoUpdSystemDes().getValue().toString();
        String                   lsIndEstatus = 
            getPoUpdStatus().getValue() == null ? "" : 
            getPoUpdStatus().getValue().toString();
        String                   lsIndSynchronous = 
            getPoUpdAsyn().getValue() == null ? "" :
            getPoUpdAsyn().getValue().toString();
        String                   lsStatusTab = "0";
        if(lsIndEstatus.equalsIgnoreCase("true")){
            lsStatusTab = "1";
        }
        String                    lsAsynTab = "0";
        if(lsIndSynchronous.equalsIgnoreCase("true")){
            lsAsynTab = "1";
        }
        ApplicationModule          loAm = 
            Configuration.createRootApplicationModule(gsAmDef, gsConfig);
        AppModuleImpl  loService = (AppModuleImpl)loAm;
        try{
            LmkIntServicesCatRowBean loLmkBean = new LmkIntServicesCatRowBean();
            loLmkBean.setLiIdService(Integer.parseInt(lsIdService));
            loLmkBean.setLsNomService(lsNomService);
            loLmkBean.setLsIndDescService(lsDesService);
            loLmkBean.setLsIndServiceWsdl(lsIndServiceWsdl);
            loLmkBean.setLsIndSystem(lsIndSystem);
            loLmkBean.setLsIndOrigin(lsIndOrigin);
            loLmkBean.setLsIndDestiny(lsIndDestiny);
            loLmkBean.setLsIndEstatus(lsStatusTab);
            loLmkBean.setLsIndSynchronous(lsAsynTab);
            
            loService.updateServicesCatModel(loLmkBean);            
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
        new UtilFaces().hidePopup(getPoPopupUpdate());
        
        return null;
    }
    
    /**
     * Oculta popup de Actualizacion
     * @autor Jorge Luis Bautista Santiago  
     * @return String
     */
    public String cancelUpdateAction() {
        getPoUpdNomService().setValue("");
        getPoUpdWsdl().setValue("");
        getPoUpdSystem().setValue("");
        getPoUpdSystemOr().setValue("");
        getPoUpdSystemDes().setValue("");
        getPoUpdStatus().setValue("");
        getPoUpdAsyn().setValue("");
        new UtilFaces().hidePopup(getPoPopupUpdate());        
        FacesContext       loContext = FacesContext.getCurrentInstance();
        ExternalContext    loEctx = loContext.getExternalContext();
        String             lsUrl = 
            loEctx.getRequestContextPath() + "/faces/processPage";
        try {
            loEctx.redirect(lsUrl);
        } catch (IOException loEx) {
            ;
        }
        return null;
    }
    
    /**
     * Regresa un conjunto de servicios web 
     * @autor Jorge Luis Bautista Santiago
     * @param tsStrSearch
     * @return List
     */
    public List getListWebServicesValid() {
        List<SelectItem>        laList = 
            new ArrayList<SelectItem>();    
        ViewObjectDao           loMd = new ViewObjectDao();
        List<SelectOneItemBean> laAllWs = 
            loMd.getListGeneralParametersModelFilter("INTEGRATION_WSERVICES");
        for(SelectOneItemBean loWs: laAllWs){
            SelectItem loItm = new SelectItem();            
            loItm.setValue(loWs.getLsValue());
            loItm.setDescription(loWs.getLsValue());
            loItm.setLabel(loWs.getLsDescription());
            laList.add(loItm);
        }
        return laList;
    }
    
    /**
     * Regresa un conjunto de servicios web 
     * @autor Jorge Luis Bautista Santiago
     * @param tsStrSearch
     * @return List
     */
    public List getListWebServices() {
        List<SelectItem>        laList = 
            new ArrayList<SelectItem>();
        ViewObjectDao           loMd = new ViewObjectDao();
        List<SelectOneItemBean> laAllWs = loMd.getListAllWebServicesModel();
        for(SelectOneItemBean loWs: laAllWs){
            SelectItem loItm = new SelectItem();            
            loItm.setValue(loWs.getLsId());
            loItm.setDescription(loWs.getLsValue());
            loItm.setLabel(loWs.getLsValue());
            laList.add(loItm);
        }
        return laList;
    }
    
    /**
     * Regresa un conjunto de servicios web 
     * @autor Jorge Luis Bautista Santiago
     * @param tsStrSearch
     * @return List
     */
    public List getListWebServicesValidSearch() {
        List<SelectItem>        laList = 
            new ArrayList<SelectItem>();    
        ViewObjectDao           loMd = new ViewObjectDao();
        List<SelectOneItemBean> laAllWs = 
            loMd.getListGeneralParametersModelFilter("INTEGRATION_WSERVICES");
        for(SelectOneItemBean loWs: laAllWs){
            SelectItem loItm = new SelectItem();            
            loItm.setValue(loWs.getLsValue());
            loItm.setDescription(loWs.getLsValue());
            loItm.setLabel(loWs.getLsDescription());
            laList.add(loItm);
        }
        return laList;
    }
    
    /*********** SETTERS AND GETTER ***********/
    
    public void setPoFilterDesServiceSel(RichSelectOneChoice poFilterDesServiceSel) {
        this.poFilterDesServiceSel = poFilterDesServiceSel;
    }

    public RichSelectOneChoice getPoFilterDesServiceSel() {
        return poFilterDesServiceSel;
    }

    public void setPoFilterServiceSel(RichSelectOneChoice poFilterServiceSel) {
        this.poFilterServiceSel = poFilterServiceSel;
    }

    public RichSelectOneChoice getPoFilterServiceSel() {
        return poFilterServiceSel;
    }

    public void setPoFilterService(RichInputText poFilterService) {
        this.poFilterService = poFilterService;
    }

    public RichInputText getPoFilterService() {
        return poFilterService;
    }

    public void setPoFilterWsdl(RichInputText poFilterWsdl) {
        this.poFilterWsdl = poFilterWsdl;
    }

    public RichInputText getPoFilterWsdl() {
        return poFilterWsdl;
    }

    public void setPoFilterSystem(RichSelectOneChoice poFilterSystem) {
        this.poFilterSystem = poFilterSystem;
    }

    public RichSelectOneChoice getPoFilterSystem() {
        return poFilterSystem;
    }

    public void setPoFilterSystemOr(RichSelectOneChoice poFilterSystemOr) {
        this.poFilterSystemOr = poFilterSystemOr;
    }

    public RichSelectOneChoice getPoFilterSystemOr() {
        return poFilterSystemOr;
    }

    public void setPoFilterSystemDes(RichSelectOneChoice poFilterSystemDes) {
        this.poFilterSystemDes = poFilterSystemDes;
    }

    public RichSelectOneChoice getPoFilterSystemDes() {
        return poFilterSystemDes;
    }

    public void setPoFilterAsynSel(RichSelectOneChoice poFilterAsynSel) {
        this.poFilterAsynSel = poFilterAsynSel;
    }

    public RichSelectOneChoice getPoFilterAsynSel() {
        return poFilterAsynSel;
    }

    public void setPoFilterStatusSel(RichSelectOneChoice poFilterStatusSel) {
        this.poFilterStatusSel = poFilterStatusSel;
    }

    public RichSelectOneChoice getPoFilterStatusSel() {
        return poFilterStatusSel;
    }

    public void setPoTblMain(RichTable poTblMain) {
        this.poTblMain = poTblMain;
    }

    public RichTable getPoTblMain() {
        return poTblMain;
    }

    public void setPoUpdIdService(RichInputText poUpdIdService) {
        this.poUpdIdService = poUpdIdService;
    }

    public RichInputText getPoUpdIdService() {
        return poUpdIdService;
    }

    public void setPoUpdNomService(RichInputText poUpdNomService) {
        this.poUpdNomService = poUpdNomService;
    }

    public RichInputText getPoUpdNomService() {
        return poUpdNomService;
    }

    public void setPoUpdDesService(RichInputText poUpdDesService) {
        this.poUpdDesService = poUpdDesService;
    }

    public RichInputText getPoUpdDesService() {
        return poUpdDesService;
    }   

    public void setPoUpdSystem(RichSelectOneChoice poUpdSystem) {
        this.poUpdSystem = poUpdSystem;
    }

    public RichSelectOneChoice getPoUpdSystem() {
        return poUpdSystem;
    }

    public void setPoUpdSystemOr(RichSelectOneChoice poUpdSystemOr) {
        this.poUpdSystemOr = poUpdSystemOr;
    }

    public RichSelectOneChoice getPoUpdSystemOr() {
        return poUpdSystemOr;
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

    public void setPoUpdSystemDes(RichSelectOneChoice poUpdSystemDes) {
        this.poUpdSystemDes = poUpdSystemDes;
    }

    public RichSelectOneChoice getPoUpdSystemDes() {
        return poUpdSystemDes;
    }

    public void setPoUpdAsyn(RichSelectBooleanCheckbox poUpdAsyn) {
        this.poUpdAsyn = poUpdAsyn;
    }

    public RichSelectBooleanCheckbox getPoUpdAsyn() {
        return poUpdAsyn;
    }

    public void setPoUpdStatus(RichSelectBooleanCheckbox poUpdStatus) {
        this.poUpdStatus = poUpdStatus;
    }

    public RichSelectBooleanCheckbox getPoUpdStatus() {
        return poUpdStatus;
    }

    public void setPoPopupSave(RichPopup poPopupSave) {
        this.poPopupSave = poPopupSave;
    }

    public RichPopup getPoPopupSave() {
        return poPopupSave;
    }

    public void setPoSaveNomService(RichInputText poSaveNomService) {
        this.poSaveNomService = poSaveNomService;
    }

    public RichInputText getPoSaveNomService() {
        return poSaveNomService;
    }

    public void setPoSaveNomServiceSel(RichSelectOneChoice poSaveNomServiceSel) {
        this.poSaveNomServiceSel = poSaveNomServiceSel;
    }

    public RichSelectOneChoice getPoSaveNomServiceSel() {
        return poSaveNomServiceSel;
    }

    public void setPoSaveWsdl(RichInputText poSaveWsdl) {
        this.poSaveWsdl = poSaveWsdl;
    }

    public RichInputText getPoSaveWsdl() {
        return poSaveWsdl;
    }

    public void setPoSaveSystem(RichSelectOneChoice poSaveSystem) {
        this.poSaveSystem = poSaveSystem;
    }

    public RichSelectOneChoice getPoSaveSystem() {
        return poSaveSystem;
    }

    public void setPoSaveSystemOr(RichSelectOneChoice poSaveSystemOr) {
        this.poSaveSystemOr = poSaveSystemOr;
    }

    public RichSelectOneChoice getPoSaveSystemOr() {
        return poSaveSystemOr;
    }

    public void setPoSaveSystemDes(RichSelectOneChoice poSaveSystemDes) {
        this.poSaveSystemDes = poSaveSystemDes;
    }

    public RichSelectOneChoice getPoSaveSystemDes() {
        return poSaveSystemDes;
    }

    public void setPoSaveAsyn(RichSelectBooleanCheckbox poSaveAsyn) {
        this.poSaveAsyn = poSaveAsyn;
    }

    public RichSelectBooleanCheckbox getPoSaveAsyn() {
        return poSaveAsyn;
    }

    public void setPoSaveStatus(RichSelectBooleanCheckbox poSaveStatus) {
        this.poSaveStatus = poSaveStatus;
    }

    public RichSelectBooleanCheckbox getPoSaveStatus() {
        return poSaveStatus;
    }

    public void setPoUpdWsdl(RichInputText poUpdWsdl) {
        this.poUpdWsdl = poUpdWsdl;
    }

    public RichInputText getPoUpdWsdl() {
        return poUpdWsdl;
    }


    public void setPoPopupMerge(RichPopup poPopupMerge) {
        this.poPopupMerge = poPopupMerge;
    }

    public RichPopup getPoPopupMerge() {
        return poPopupMerge;
    }

    public void setPoMergeIdService(RichInputText poMergeIdService) {
        this.poMergeIdService = poMergeIdService;
    }

    public RichInputText getPoMergeIdService() {
        return poMergeIdService;
    }

    public void setPoMergeNomService(RichInputText poMergeNomService) {
        this.poMergeNomService = poMergeNomService;
    }

    public RichInputText getPoMergeNomService() {
        return poMergeNomService;
    }

    public void setPoMerge2can(RichSelectBooleanCheckbox poMerge2can) {
        this.poMerge2can = poMerge2can;
    }

    public RichSelectBooleanCheckbox getPoMerge2can() {
        return poMerge2can;
    }

    public void setPoMerge5can(RichSelectBooleanCheckbox poMerge5can) {
        this.poMerge5can = poMerge5can;
    }

    public RichSelectBooleanCheckbox getPoMerge5can() {
        return poMerge5can;
    }

    public void setPoMerge9can(RichSelectBooleanCheckbox poMerge9can) {
        this.poMerge9can = poMerge9can;
    }

    public RichSelectBooleanCheckbox getPoMerge9can() {
        return poMerge9can;
    }

    public void setPoMergeCliente(RichInputText poMergeCliente) {
        this.poMergeCliente = poMergeCliente;
    }

    public RichInputText getPoMergeCliente() {
        return poMergeCliente;
    }

    public void setPoPopupCron(RichPopup poPopupCron) {
        this.poPopupCron = poPopupCron;
    }

    public RichPopup getPoPopupCron() {
        return poPopupCron;
    }
    
    public void setPoPanelTabbed(RichPanelTabbed poPanelTabbed) {
        this.poPanelTabbed = poPanelTabbed;
    }

    public RichPanelTabbed getPoPanelTabbed() {
        return poPanelTabbed;
    }

    public void setPoTabMinutos(RichShowDetailItem poTabMinutos) {
        this.poTabMinutos = poTabMinutos;
    }

    public RichShowDetailItem getPoTabMinutos() {
        return poTabMinutos;
    }

    public void setPoTabHoras(RichShowDetailItem poTabHoras) {
        this.poTabHoras = poTabHoras;
    }

    public RichShowDetailItem getPoTabHoras() {
        return poTabHoras;
    }
    
    public void setPoCadaMinutos(RichInputNumberSpinbox poCadaMinutos) {
        this.poCadaMinutos = poCadaMinutos;
    }

    public RichInputNumberSpinbox getPoCadaMinutos() {
        return poCadaMinutos;
    }

    public void setPoSemanasHoraIni(RichInputNumberSpinbox poSemanasHoraIni) {
        this.poSemanasHoraIni = poSemanasHoraIni;
    }

    public RichInputNumberSpinbox getPoSemanasHoraIni() {
        return poSemanasHoraIni;
    }

    public void setPoBeginSemanasMinutoIni(RichInputNumberSpinbox poBeginSemanasMinutoIni) {
        this.poBeginSemanasMinutoIni = poBeginSemanasMinutoIni;
    }

    public RichInputNumberSpinbox getPoBeginSemanasMinutoIni() {
        return poBeginSemanasMinutoIni;
    }
    

    public void setPoBeginHorasMin(RichInputNumberSpinbox poBeginHorasMin) {
        this.poBeginHorasMin = poBeginHorasMin;
    }

    public RichInputNumberSpinbox getPoBeginHorasMin() {
        return poBeginHorasMin;
    }

    public void setPoBeginMinutesMin(RichInputNumberSpinbox poBeginMinutesMin) {
        this.poBeginMinutesMin = poBeginMinutesMin;
    }

    public RichInputNumberSpinbox getPoBeginMinutesMin() {
        return poBeginMinutesMin;
    }

    public void setPoTabDias(RichShowDetailItem poTabDias) {
        this.poTabDias = poTabDias;
    }

    public RichShowDetailItem getPoTabDias() {
        return poTabDias;
    }

    public void setPoTabSemanas(RichShowDetailItem poTabSemanas) {
        this.poTabSemanas = poTabSemanas;
    }

    public RichShowDetailItem getPoTabSemanas() {
        return poTabSemanas;
    }

    public void setPoMesEveryOf(RichInputNumberSpinbox poMesEveryOf) {
        this.poMesEveryOf = poMesEveryOf;
    }

    public RichInputNumberSpinbox getPoMesEveryOf() {
        return poMesEveryOf;
    }

    public void setPoMesHoraIni(RichInputNumberSpinbox poMesHoraIni) {
        this.poMesHoraIni = poMesHoraIni;
    }

    public RichInputNumberSpinbox getPoMesHoraIni() {
        return poMesHoraIni;
    }

    public void setPoMesMinutoIni(RichInputNumberSpinbox poMesMinutoIni) {
        this.poMesMinutoIni = poMesMinutoIni;
    }

    public RichInputNumberSpinbox getPoMesMinutoIni() {
        return poMesMinutoIni;
    }


    public void setPoDeadLineHoras(RichInputNumberSpinbox poDeadLineHoras) {
        this.poDeadLineHoras = poDeadLineHoras;
    }

    public RichInputNumberSpinbox getPoDeadLineHoras() {
        return poDeadLineHoras;
    }

    public void setPoDeadLineMinutos(RichInputNumberSpinbox poDeadLineMinutos) {
        this.poDeadLineMinutos = poDeadLineMinutos;
    }

    public RichInputNumberSpinbox getPoDeadLineMinutos() {
        return poDeadLineMinutos;
    }

    public void setPoDeadLineMinHoras(RichInputNumberSpinbox poDeadLineMinHoras) {
        this.poDeadLineMinHoras = poDeadLineMinHoras;
    }

    public RichInputNumberSpinbox getPoDeadLineMinHoras() {
        return poDeadLineMinHoras;
    }

    public void setPoDeadLineMinMinutos(RichInputNumberSpinbox poDeadLineMinMinutos) {
        this.poDeadLineMinMinutos = poDeadLineMinMinutos;
    }

    public RichInputNumberSpinbox getPoDeadLineMinMinutos() {
        return poDeadLineMinMinutos;
    }

    public void setPoLunMinutos(RichSelectBooleanCheckbox poLunMinutos) {
        this.poLunMinutos = poLunMinutos;
    }

    public RichSelectBooleanCheckbox getPoLunMinutos() {
        return poLunMinutos;
    }

    public void setPoMarMinutos(RichSelectBooleanCheckbox poMarMinutos) {
        this.poMarMinutos = poMarMinutos;
    }

    public RichSelectBooleanCheckbox getPoMarMinutos() {
        return poMarMinutos;
    }

    public void setPoMieMinutos(RichSelectBooleanCheckbox poMieMinutos) {
        this.poMieMinutos = poMieMinutos;
    }

    public RichSelectBooleanCheckbox getPoMieMinutos() {
        return poMieMinutos;
    }

    public void setPoJueMinutos(RichSelectBooleanCheckbox poJueMinutos) {
        this.poJueMinutos = poJueMinutos;
    }

    public RichSelectBooleanCheckbox getPoJueMinutos() {
        return poJueMinutos;
    }

    public void setPoVieMinutos(RichSelectBooleanCheckbox poVieMinutos) {
        this.poVieMinutos = poVieMinutos;
    }

    public RichSelectBooleanCheckbox getPoVieMinutos() {
        return poVieMinutos;
    }

    public void setPoSabMinutos(RichSelectBooleanCheckbox poSabMinutos) {
        this.poSabMinutos = poSabMinutos;
    }

    public RichSelectBooleanCheckbox getPoSabMinutos() {
        return poSabMinutos;
    }

    public void setPoDomMinutos(RichSelectBooleanCheckbox poDomMinutos) {
        this.poDomMinutos = poDomMinutos;
    }

    public RichSelectBooleanCheckbox getPoDomMinutos() {
        return poDomMinutos;
    }

    public void setPoCadaHoras(RichInputNumberSpinbox poCadaHoras) {
        this.poCadaHoras = poCadaHoras;
    }

    public RichInputNumberSpinbox getPoCadaHoras() {
        return poCadaHoras;
    }

    public void setPoLunHoras(RichSelectBooleanCheckbox poLunHoras) {
        this.poLunHoras = poLunHoras;
    }

    public RichSelectBooleanCheckbox getPoLunHoras() {
        return poLunHoras;
    }

    public void setPoMarHoras(RichSelectBooleanCheckbox poMarHoras) {
        this.poMarHoras = poMarHoras;
    }

    public RichSelectBooleanCheckbox getPoMarHoras() {
        return poMarHoras;
    }

    public void setPoMieHoras(RichSelectBooleanCheckbox poMieHoras) {
        this.poMieHoras = poMieHoras;
    }

    public RichSelectBooleanCheckbox getPoMieHoras() {
        return poMieHoras;
    }

    public void setPoBeginHoras(RichInputNumberSpinbox poBeginHoras) {
        this.poBeginHoras = poBeginHoras;
    }

    public RichInputNumberSpinbox getPoBeginHoras() {
        return poBeginHoras;
    }

    public void setPoJueHoras(RichSelectBooleanCheckbox poJueHoras) {
        this.poJueHoras = poJueHoras;
    }

    public RichSelectBooleanCheckbox getPoJueHoras() {
        return poJueHoras;
    }

    public void setPoVieHoras(RichSelectBooleanCheckbox poVieHoras) {
        this.poVieHoras = poVieHoras;
    }

    public RichSelectBooleanCheckbox getPoVieHoras() {
        return poVieHoras;
    }

    public void setPoSabHoras(RichSelectBooleanCheckbox poSabHoras) {
        this.poSabHoras = poSabHoras;
    }

    public RichSelectBooleanCheckbox getPoSabHoras() {
        return poSabHoras;
    }

    public void setPoDomHoras(RichSelectBooleanCheckbox poDomHoras) {
        this.poDomHoras = poDomHoras;
    }

    public RichSelectBooleanCheckbox getPoDomHoras() {
        return poDomHoras;
    }

    public void setPoIdServiceSelected(RichInputText poIdServiceSelected) {
        this.poIdServiceSelected = poIdServiceSelected;
    }

    public RichInputText getPoIdServiceSelected() {
        return poIdServiceSelected;
    }

    public void setPoBeginMinutes(RichInputNumberSpinbox poBeginMinutes) {
        this.poBeginMinutes = poBeginMinutes;
    }

    public RichInputNumberSpinbox getPoBeginMinutes() {
        return poBeginMinutes;
    }

    public void setPoRadioBegin(RichSelectBooleanRadio poRadioBegin) {
        this.poRadioBegin = poRadioBegin;
    }

    public RichSelectBooleanRadio getPoRadioBegin() {
        return poRadioBegin;
    }

    public void setPoRadioDayEvery(RichSelectBooleanRadio poRadioDayEvery) {
        this.poRadioDayEvery = poRadioDayEvery;
    }

    public RichSelectBooleanRadio getPoRadioDayEvery() {
        return poRadioDayEvery;
    }

    public void setPoCadaDias(RichInputNumberSpinbox poCadaDias) {
        this.poCadaDias = poCadaDias;
    }

    public RichInputNumberSpinbox getPoCadaDias() {
        return poCadaDias;
    }

    public void setPoRadioDayBegin(RichSelectBooleanRadio poRadioDayBegin) {
        this.poRadioDayBegin = poRadioDayBegin;
    }

    public RichSelectBooleanRadio getPoRadioDayBegin() {
        return poRadioDayBegin;
    }

    public void setPoBeginDias(RichInputNumberSpinbox poBeginDias) {
        this.poBeginDias = poBeginDias;
    }

    public RichInputNumberSpinbox getPoBeginDias() {
        return poBeginDias;
    }

    public void setPoBeginDayMinutes(RichInputNumberSpinbox poBeginDayMinutes) {
        this.poBeginDayMinutes = poBeginDayMinutes;
    }

    public RichInputNumberSpinbox getPoBeginDayMinutes() {
        return poBeginDayMinutes;
    }

    public void setPoLunDias(RichSelectBooleanCheckbox poLunDias) {
        this.poLunDias = poLunDias;
    }

    public RichSelectBooleanCheckbox getPoLunDias() {
        return poLunDias;
    }

    public void setPoMarDias(RichSelectBooleanCheckbox poMarDias) {
        this.poMarDias = poMarDias;
    }

    public RichSelectBooleanCheckbox getPoMarDias() {
        return poMarDias;
    }

    public void setPoMieDias(RichSelectBooleanCheckbox poMieDias) {
        this.poMieDias = poMieDias;
    }

    public RichSelectBooleanCheckbox getPoMieDias() {
        return poMieDias;
    }

    public void setPoJueDias(RichSelectBooleanCheckbox poJueDias) {
        this.poJueDias = poJueDias;
    }

    public RichSelectBooleanCheckbox getPoJueDias() {
        return poJueDias;
    }

    public void setPoVieDias(RichSelectBooleanCheckbox poVieDias) {
        this.poVieDias = poVieDias;
    }

    public RichSelectBooleanCheckbox getPoVieDias() {
        return poVieDias;
    }

    public void setPoSabDias(RichSelectBooleanCheckbox poSabDias) {
        this.poSabDias = poSabDias;
    }

    public RichSelectBooleanCheckbox getPoSabDias() {
        return poSabDias;
    }

    public void setPoDomDias(RichSelectBooleanCheckbox poDomDias) {
        this.poDomDias = poDomDias;
    }

    public RichSelectBooleanCheckbox getPoDomDias() {
        return poDomDias;
    }

    public void setPoBeginSemanas(RichInputNumberSpinbox poBeginSemanas) {
        this.poBeginSemanas = poBeginSemanas;
    }

    public RichInputNumberSpinbox getPoBeginSemanas() {
        return poBeginSemanas;
    }

    public void setPoBeginDaySemanas(RichInputNumberSpinbox poBeginDaySemanas) {
        this.poBeginDaySemanas = poBeginDaySemanas;
    }

    public RichInputNumberSpinbox getPoBeginDaySemanas() {
        return poBeginDaySemanas;
    }

    public void setPoLunWeek(RichSelectBooleanCheckbox poLunWeek) {
        this.poLunWeek = poLunWeek;
    }

    public RichSelectBooleanCheckbox getPoLunWeek() {
        return poLunWeek;
    }

    public void setPoMarWeek(RichSelectBooleanCheckbox poMarWeek) {
        this.poMarWeek = poMarWeek;
    }

    public RichSelectBooleanCheckbox getPoMarWeek() {
        return poMarWeek;
    }

    public void setPoMieWeek(RichSelectBooleanCheckbox poMieWeek) {
        this.poMieWeek = poMieWeek;
    }

    public RichSelectBooleanCheckbox getPoMieWeek() {
        return poMieWeek;
    }

    public void setPoJueWeek(RichSelectBooleanCheckbox poJueWeek) {
        this.poJueWeek = poJueWeek;
    }

    public RichSelectBooleanCheckbox getPoJueWeek() {
        return poJueWeek;
    }

    public void setPoVieWeek(RichSelectBooleanCheckbox poVieWeek) {
        this.poVieWeek = poVieWeek;
    }

    public RichSelectBooleanCheckbox getPoVieWeek() {
        return poVieWeek;
    }

    public void setPoSabWeek(RichSelectBooleanCheckbox poSabWeek) {
        this.poSabWeek = poSabWeek;
    }

    public RichSelectBooleanCheckbox getPoSabWeek() {
        return poSabWeek;
    }

    public void setPoDomWeek(RichSelectBooleanCheckbox poDomWeek) {
        this.poDomWeek = poDomWeek;
    }

    public RichSelectBooleanCheckbox getPoDomWeek() {
        return poDomWeek;
    }

    public void setPoIdTabSelected(RichInputText poIdTabSelected) {
        this.poIdTabSelected = poIdTabSelected;
    }

    public RichInputText getPoIdTabSelected() {
        return poIdTabSelected;
    }

    public void setPoOperation(RichInputText poOperation) {
        this.poOperation = poOperation;
    }

    public RichInputText getPoOperation() {
        return poOperation;
    }

    public void setPoIdCronConfiguration(RichInputText poIdCronConfiguration) {
        this.poIdCronConfiguration = poIdCronConfiguration;
    }

    public RichInputText getPoIdCronConfiguration() {
        return poIdCronConfiguration;
    }

    public void setPoNomServicioCron(RichInputText poNomServicioCron) {
        this.poNomServicioCron = poNomServicioCron;
    }

    public RichInputText getPoNomServicioCron() {
        return poNomServicioCron;
    }

    public void setPoServiceToCron(RichLink poServiceToCron) {
        this.poServiceToCron = poServiceToCron;
    }

    public RichLink getPoServiceToCron() {
        return poServiceToCron;
    }

    public void setPoServiceToCronStatus(RichLink poServiceToCronStatus) {
        this.poServiceToCronStatus = poServiceToCronStatus;
    }

    public RichLink getPoServiceToCronStatus() {
        return poServiceToCronStatus;
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

    public void setPoPopupBegin(RichPopup poPopupBegin) {
        this.poPopupBegin = poPopupBegin;
    }

    public RichPopup getPoPopupBegin() {
        return poPopupBegin;
    }

    public void setPoBeginMsgLbl(RichPanelLabelAndMessage poBeginMsgLbl) {
        this.poBeginMsgLbl = poBeginMsgLbl;
    }

    public RichPanelLabelAndMessage getPoBeginMsgLbl() {
        return poBeginMsgLbl;
    }

    public void setPoBeginIdBinding(RichOutputText poBeginIdBinding) {
        this.poBeginIdBinding = poBeginIdBinding;
    }

    public RichOutputText getPoBeginIdBinding() {
        return poBeginIdBinding;
    }

    public void setPoBeginNomBinding(RichOutputText poBeginNomBinding) {
        this.poBeginNomBinding = poBeginNomBinding;
    }

    public RichOutputText getPoBeginNomBinding() {
        return poBeginNomBinding;
    }

    public void setPoPopupStop(RichPopup poPopupStop) {
        this.poPopupStop = poPopupStop;
    }

    public RichPopup getPoPopupStop() {
        return poPopupStop;
    }

    public void setPoStopMsgLbl(RichPanelLabelAndMessage poStopMsgLbl) {
        this.poStopMsgLbl = poStopMsgLbl;
    }

    public RichPanelLabelAndMessage getPoStopMsgLbl() {
        return poStopMsgLbl;
    }

    public void setPoStopIdBinding(RichOutputText poStopIdBinding) {
        this.poStopIdBinding = poStopIdBinding;
    }

    public RichOutputText getPoStopIdBinding() {
        return poStopIdBinding;
    }

    public void setPoStopNomBinding(RichOutputText poStopNomBinding) {
        this.poStopNomBinding = poStopNomBinding;
    }

    public RichOutputText getPoStopNomBinding() {
        return poStopNomBinding;
    }

    public void setPoExecuteTypeService(RichOutputText poExecuteTypeService) {
        this.poExecuteTypeService = poExecuteTypeService;
    }

    public RichOutputText getPoExecuteTypeService() {
        return poExecuteTypeService;
    }

    public void setPoBeginTypeService(RichOutputText poBeginTypeService) {
        this.poBeginTypeService = poBeginTypeService;
    }

    public RichOutputText getPoBeginTypeService() {
        return poBeginTypeService;
    }

    public void setPoStopTypeService(RichOutputText poStopTypeService) {
        this.poStopTypeService = poStopTypeService;
    }

    public RichOutputText getPoStopTypeService() {
        return poStopTypeService;
    }

    public static FacesContext getFacesContext() {
       return FacesContext.getCurrentInstance();
    }
    
    public ServletContext getContext() {
       return (ServletContext)getFacesContext().getExternalContext().getContext();
   }
    
    public String getRealPath(){
        ServletContext        loContext = getContext();
        return loContext.getRealPath("/");
    }
    
    public String getContextPath(){
        ServletContext        loContext = getContext();
        return loContext.getContextPath();
    }

    public void setPoPopupParams(RichPopup poPopupParams) {
        this.poPopupParams = poPopupParams;
    }

    public RichPopup getPoPopupParams() {
        return poPopupParams;
    }

    public void setPoParamsIdService(RichInputText poParamsIdService) {
        this.poParamsIdService = poParamsIdService;
    }

    public RichInputText getPoParamsIdService() {
        return poParamsIdService;
    }

    public void setPoParamsNomService(RichInputText poParamsNomService) {
        this.poParamsNomService = poParamsNomService;
    }

    public RichInputText getPoParamsNomService() {
        return poParamsNomService;
    }

    public String saveParamsAction() {
        // Add event code here...
        return null;
    }

    public String cancelSaveParamsAction() {
        // Add event code here...
        return null;
    }
}
