/**
* Project: Integraton Paradigm - Landmark
*
* File: PriceImpCron.java
*
* Created on: Julio 29, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.jobs.service;

import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfint;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.InputStream;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import mx.com.televisa.landamark.model.daos.AsRunAsDao;
import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.PriceDao;
import mx.com.televisa.landamark.model.daos.XmlFilesDao;
import mx.com.televisa.landamark.model.types.LandmarkSecurityWsBean;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntXmlFilesRowBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;
import mx.com.televisa.landamark.util.UtilFaces;

import org.datacontract.schemas._2004._07.landmark_classes.ArrayOfSpot;

import org.datacontract.schemas._2004._07.landmark_classes.Spot;
import org.datacontract.schemas._2004._07.landmark_parameters.ArrayOfFilterDateTime;
import org.datacontract.schemas._2004._07.landmark_parameters.DaysOfWeek;
import org.datacontract.schemas._2004._07.landmark_parameters.FilterDateTime;
import org.datacontract.schemas._2004._07.landmark_parameters.Interactivity;
import org.datacontract.schemas._2004._07.landmark_parameters.SpotListFilter;
import org.datacontract.schemas._2004._07.landmark_parameters.SpotListFilterCriteria;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.tempuri.ILandmarkSpotsSpot;
import org.tempuri.LandmarkSpotsSpot;


/** Clase que ejecuta logica o servicio de Actualizacion de Precios
 * por canal
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Julio 29, 2019, 12:00 pm
 */
public class PriceImpCron  implements Job{
    public PriceImpCron() {
        super();
    }
    

    @Override
    public void execute(JobExecutionContext toJobExecutionContext) throws JobExecutionException {        
        JobDataMap                loDataMap = toJobExecutionContext.getJobDetail().getJobDataMap();
        String                    lsIdService = loDataMap.getString("lsIdService");
        String                    lsIdUser = loDataMap.getString("lsIdUser");
        String                    lsUserName = loDataMap.getString("lsUserName");
        String                    lsTypeProcess = loDataMap.getString("lsTypeProcess");
        String                    lsServiceName = loDataMap.getString("lsServiceName");
        String                    lsPathFiles = loDataMap.getString("lsPathFiles");
        String                    lsIdLogService = loDataMap.getString("lsIdLogService");
        AsRunAsDao                loAsRunAsDao = new AsRunAsDao();      
        PriceDao                  loPriceDao = new PriceDao();
        String                    lsFecInicial = loDataMap.getString("lsFecInicial");
        String                    lsFecFinal = loDataMap.getString("lsFecFinal");
        String                    lsChannel = loDataMap.getString("lsIdChannel");
        Integer                   liIndProcess = 0;
        EntityMappedDao           loEntityMappedDao = new EntityMappedDao();
        
        Integer                   liIdLogService = Integer.parseInt(lsIdLogService);
        Integer                   liIdService = Integer.parseInt(lsIdService);
        Integer                   liIdUser = Integer.parseInt(lsIdUser);
        
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
        String lsParametersClass = lsChannel+","+lsFecInicial+","+lsFecFinal;
        System.out.println("["+lsChannel+"]Ejecucion de Cron (Actualizacion de Precios) >> ["+new Date()+"]");
        
        //ResponseUpdDao loRes = loPpDao.callLmkProgBrkPr(lsChannel, lsFecInicial, lsFecFinal);
        System.out.println("Logica de actualizacion de precios por canal configurado["+lsChannel+"]");
        String lsKey = lsChannel + "-" + lsFecInicial;        
        //0.- Validar si es posible procesar, considerar:
        //0.1.- Que se hayan ejecutado los procesos previos, as run as
        //0.2.- Segun Jacobo, validar con el mismo query de log certificado
        Integer liFlag = 
            loAsRunAsDao.getFlagInsertLogCertificado(lsFecInicial, 
                                                     lsChannel
                                                    );  
        liIndProcess = new UtilFaces().getIdConfigParameterByName("FlagReconComplete");
        
        loBitBean.setLiIdLogServices(liIdLogService);
        loBitBean.setLiIdService(liIdService);
        loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
        loBitBean.setLiNumProcessId(0);
        loBitBean.setLiNumPgmProcessId(0);
        loBitBean.setLsIndEvento(lsKey + ": Bandera Log Certificado RECON COMPLETE[" + liFlag + "]" +
            "para Precios");
        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                           liIdUser, 
                                           lsUserName);
        
        if(liFlag > 0){
            
            //1.- Consumir servicio de jacobo 
            //1.1.- Almacenar en bd elemento-valor de: Authorization: Basic
            //1.2.- Almacenar en bd elemento-valor de: Password7 (encriptado)
            //1.3.- Almacenar en bd elemento valor de: LMK-Environment
            //1.4.- Almacenar en bd elemento valor de: 1:tlvtrain
            ArrayOfSpot loArrayOfSpot = getRequestLandmarkPrices(lsChannel, 
                                                 lsFecInicial, 
                                                 lsFecFinal,
                                                 liIdLogService,
                                                 liIdService,
                                                 liIdUser,
                                                 lsUserName,
                                                 lsTypeProcess);
            //2.- Leer el response XML
            if(loArrayOfSpot != null){
                //2.1.- Por ahora cada dato pasa sin validacion
                
                //3.- Insertar en tablas de Alex Morel
                //3.1.- Preguntar a Jacobo, por el mapeo de campos de las tablas
                    
            }
            
            liIndProcess = 
                        new UtilFaces().getIdConfigParameterByName("ProcessFinish");//
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(liIdService);
                    loBitBean.setLiIndProcess(liIndProcess);
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento("Proceso finalizado para ["+lsParametersClass+"]");
            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               liIdUser, 
                                               lsUserName);
            
            System.out.println("["+lsChannel+"] FIN DE JOB ["+new Date()+"]");
            
        }
        
    }
    
    private ArrayOfSpot getRequestLandmarkPrices(String tsChannel, 
                                                 String tsInitialDate, 
                                                 String tsFinalDate,
                                                 Integer tiIdLogService,
                                                 Integer tiIdService,
                                                 Integer tiIdUser,
                                                 String tsUserName,
                                                 String lsTypeProcess){
        ArrayOfSpot loArrOf = new ArrayOfSpot();
        PriceDao loPriceDao = new PriceDao();
        Integer                   liIndProcess = 0;
        EntityMappedDao           loEntityMappedDao = new EntityMappedDao();
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
        try{
            String lsQNameXML = "";
            LandmarkSpotsSpot loLandmarkSpotsSpot = new LandmarkSpotsSpot();
            ILandmarkSpotsSpot loInstanceLandmarkSpotsSpot = 
                loLandmarkSpotsSpot.getBasicHttpBindingILandmarkSpotsSpot();
            // Add your code to call the desired methods.
            SpotListFilter loSpotListFilter = new SpotListFilter();        
            loSpotListFilter.setAlternateSchedule(0);
            ArrayOfint loObjAs = new ArrayOfint();
            List<Integer> laObjsAs = new ArrayList<Integer>();
            //laObjsAs.add(1);
            loObjAs.setInt(laObjsAs);
            JAXBElement<ArrayOfint> laAs = 
                new JAXBElement<ArrayOfint>(new QName(lsQNameXML, "AlternateSchedules"),ArrayOfint.class,loObjAs);
            loSpotListFilter.setAlternateSchedules(laAs);        
            loSpotListFilter.setBonusSpot(false);        
            JAXBElement<String> loBreakType = 
                new JAXBElement<String>(new QName(lsQNameXML, "BreakType"),String.class, "CO");
            loSpotListFilter.setBreakType(loBreakType);        
            ArrayOfint loObjBa = new ArrayOfint();
            List<Integer> laObjsBa = new ArrayList<Integer>();
            //laObjsBa.add(1);
            loObjBa.setInt(laObjsBa);
            JAXBElement<ArrayOfint> laBusinessArea = 
                new JAXBElement<ArrayOfint>(new QName(lsQNameXML, "BusinessAreas"),ArrayOfint.class,loObjBa);
            loSpotListFilter.setBusinessAreas(laBusinessArea);        
            ArrayOfint loObj = new ArrayOfint();
            List<Integer> laObjs = new ArrayList<Integer>();
            laObjs.add(1);
            laObjs.add(2);
            laObjs.add(3);
            laObjs.add(4);
            loObj.setInt(laObjs);
            JAXBElement<ArrayOfint> laBusinessTypes = 
                new JAXBElement<ArrayOfint>(new QName(lsQNameXML, "BusinessTypes"),ArrayOfint.class,loObj);
            loSpotListFilter.setBusinessTypes(laBusinessTypes);        
            JAXBElement<String> loCallerOrganisationCode = 
                new JAXBElement<String>(new QName(lsQNameXML, "CallerOrganisationCode"),String.class, "TL");
            loSpotListFilter.setCallerOrganisationCode(loCallerOrganisationCode);
            
            JAXBElement<String> loCallerPositionCode = 
                new JAXBElement<String>(new QName(lsQNameXML, "CallerPositionCode"),String.class, "TRN07");
            loSpotListFilter.setCallerPositionCode(loCallerPositionCode);
            
            ArrayOfint loObjCa = new ArrayOfint();
            List<Integer> laObjsCa = new ArrayList<Integer>();
            //laObjsCa.add(1);
            loObjCa.setInt(laObjsCa);
            JAXBElement<ArrayOfint> laCampaigns = 
                new JAXBElement<ArrayOfint>(new QName(lsQNameXML, "Campaigns"),ArrayOfint.class,loObjCa);
            loSpotListFilter.setCampaigns(laCampaigns);
            
            ArrayOfstring loObjClash = new ArrayOfstring();
            List<String> laObjsClash = new ArrayList<String>();
            //loObjClash.add(1);
            loObjClash.setString(laObjsClash);
            JAXBElement<ArrayOfstring> laClash = 
                new JAXBElement<ArrayOfstring>(new QName(lsQNameXML, "ClashCodes"),ArrayOfstring.class,loObjClash);
            loSpotListFilter.setClashCodes(laClash);        
            ArrayOfstring loClients = new ArrayOfstring();
            List<String> laClients = new ArrayList<String>();
            //loObjClash.add(1);
            loClients.setString(laClients);
            JAXBElement<ArrayOfstring> laClientsJax = 
                new JAXBElement<ArrayOfstring>(new QName(lsQNameXML, "Clients"),ArrayOfstring.class,loClients);
            loSpotListFilter.setClients(laClientsJax);
            loSpotListFilter.setConvDemographicNumber(0);
            loSpotListFilter.setCopyCode(0);
            loSpotListFilter.setCopyProcess(-1);
            JAXBElement<String> loCurrency = 
                new JAXBElement<String>(new QName(lsQNameXML, "Currency"),String.class, "MXN");
            loSpotListFilter.setCurrency(loCurrency);        
            ArrayOfint loObjDeals = new ArrayOfint();
            List<Integer> laObjsDeals = new ArrayList<Integer>();
            //laObjsCa.add(1);
            loObjDeals.setInt(laObjsDeals);
            JAXBElement<ArrayOfint> laDeals = 
                new JAXBElement<ArrayOfint>(new QName(lsQNameXML, "Deals"),ArrayOfint.class,loObjDeals);
            loSpotListFilter.setDeals(laDeals);        
            loSpotListFilter.setDemographicNumber(-1);        
            ArrayOfstring loObjDesCod = new ArrayOfstring();
            List<String> laObjsDesCod = new ArrayList<String>();
            loObjDesCod.setString(laObjsDesCod);
            JAXBElement<ArrayOfstring> laDesCod = 
                new JAXBElement<ArrayOfstring>(new QName(lsQNameXML, "DiscountCodes"),ArrayOfstring.class,loObjDesCod);
            loSpotListFilter.setDiscountCodes(laDesCod);
            loSpotListFilter.setEfficiencyHigh(new Float(0));
            loSpotListFilter.setEfficiencyLow(new Float(0));
            loSpotListFilter.setExcludeRatingsData(false);
            loSpotListFilter.setFilterCriteria(SpotListFilterCriteria.GET_BY_SALES_AREA);        
            ArrayOfFilterDateTime laFdt = new ArrayOfFilterDateTime();
            List<FilterDateTime> loFilterDateTime = new ArrayList<FilterDateTime>();
            FilterDateTime loFilterTime = new FilterDateTime();
            loFilterTime.setDaysOfWeek(DaysOfWeek.ALL_DAYS);
            SimpleDateFormat loSdf = new SimpleDateFormat("yyyy-MM-dd");
            Date loDateTmp;
            try {
                loDateTmp = loSdf.parse(tsFinalDate);
                GregorianCalendar loGregorCalendar = new GregorianCalendar();
                loGregorCalendar.setTime(loDateTmp);
                XMLGregorianCalendar loFecFinXml = 
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(loGregorCalendar);
                loFilterTime.setEndDate(loFecFinXml);
            } catch (ParseException e) {
                System.out.println("Error al parsear: "+e.getMessage());
            } catch (DatatypeConfigurationException e) {
                System.out.println("Error Fechas : "+e.getMessage());
            }
            loFilterTime.setEndTime(235959);        
            Date loDateStart;
            try {
                loDateStart = loSdf.parse(tsInitialDate);
                GregorianCalendar loGregorCalendar = new GregorianCalendar();
                loGregorCalendar.setTime(loDateStart);
                XMLGregorianCalendar loFecInicialXml = 
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(loGregorCalendar);
                loFilterTime.setStartDate(loFecInicialXml);
            } catch (ParseException e) {
                System.out.println("Error al parsear: "+e.getMessage());
            } catch (DatatypeConfigurationException e) {
                System.out.println("Error Fechas 999: "+e.getMessage());
            }        
            loFilterTime.setStartTime(60000);        
            loFilterDateTime.add(loFilterTime);
            laFdt.setFilterDateTime(loFilterDateTime);
            JAXBElement<ArrayOfFilterDateTime> laFilterDateTimes = 
                new JAXBElement<ArrayOfFilterDateTime>(new QName(lsQNameXML, "FilterDateTimes"),ArrayOfFilterDateTime.class,laFdt);
            loSpotListFilter.setFilterDateTimes(laFilterDateTimes);
            loSpotListFilter.setIncludeCopyDetails(false);
            loSpotListFilter.setIncludeInvoiceInfo(false);
            loSpotListFilter.setIncludePackages(false);
            loSpotListFilter.setIncludePremiumCategoryData(false);
            loSpotListFilter.setIncludePrimary(true);
            loSpotListFilter.setIncludeProgrammeOrCategory(false);
            loSpotListFilter.setIncludeSchedulePayments(false);
            loSpotListFilter.setIncludeSubAreas(true);
            loSpotListFilter.setInclusiveDiscountRange(false);
            loSpotListFilter.setInclusivePreemptionRange(false);
            loSpotListFilter.setInteractivity(Interactivity.ALL);
            loSpotListFilter.setParpNo(0);
            loSpotListFilter.setPositionInBreak(-1);
            ArrayOfint loObjPree = new ArrayOfint();
            List<Integer> laObjsPree = new ArrayList<Integer>();
            //laObjsCa.add(1);
            loObjPree.setInt(laObjsPree);
            JAXBElement<ArrayOfint> laPree = 
                new JAXBElement<ArrayOfint>(new QName(lsQNameXML, "PreemptionCodes"),ArrayOfint.class,loObjPree);
            loSpotListFilter.setPreemptionCodes(laPree);        
            loSpotListFilter.setPreemptionRangeEnd(0);
            loSpotListFilter.setPreemptionRangeStart(0);
            loSpotListFilter.setPriceHigh(new Float(0.00));
            loSpotListFilter.setPriceLow(new Float(0.00));
            loSpotListFilter.setProductCode(-1);
            loSpotListFilter.setProgrammeCategoryNumber(-1);
            loSpotListFilter.setProgrammeNumber(-1);
            loSpotListFilter.setRatingsHigh(new Float(0.00));
            loSpotListFilter.setRatingsLow(new Float(0.00));
            loSpotListFilter.setRegnBreakout(false);
            loSpotListFilter.setRetrieveCopy(false);        
            
            ArrayOfint loObjSales = new ArrayOfint();
            List<Integer> laObjsSales = new ArrayList<Integer>();
            //Debido a que en esta clase solo llega un canal, no es necesario iterar
            //Ir al la base de datos para obtener el correspondiente mapeo del actual canal
            List<String> loListChannels = loPriceDao.getCodPriceChannel(tsChannel);
            if(loListChannels.size() > 0){
                laObjsSales.add(Integer.parseInt(loListChannels.get(0)));
                System.out.println("Sales Area obtenida ["+loListChannels.get(0)+"] de "+tsChannel);
            }else{
                liIndProcess = new UtilFaces().getIdConfigParameterByName("ErrorConfig");
                loBitBean.setLiIdLogServices(tiIdLogService);
                loBitBean.setLiIdService(tiIdService);
                loBitBean.setLiIndProcess(liIndProcess);
                loBitBean.setLiNumProcessId(0);
                loBitBean.setLiNumPgmProcessId(0);
                loBitBean.setLsIndEvento("No existe configuracion para obtener SalesArea de "+tsChannel);
                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   tiIdUser, 
                                                   tsUserName);
                
            }
            loObjSales.setInt(laObjsSales);
            JAXBElement<ArrayOfint> laSales = 
                new JAXBElement<ArrayOfint>(new QName(lsQNameXML, "BusinessTypes"),ArrayOfint.class,loObjSales);
            loSpotListFilter.setSalesAreas(laSales);
            
            loSpotListFilter.setSecondaryDemographicNumber(-3);
            loSpotListFilter.setSecondaryRatingsSupplier(false);                
            
            ArrayOfint loObjSpotsl = new ArrayOfint();
            List<Integer> laObjsSpotsl = new ArrayList<Integer>();
            //laObjsSales.add(1);
            loObjSpotsl.setInt(laObjsSpotsl);
            JAXBElement<ArrayOfint> laSpotsl = 
                new JAXBElement<ArrayOfint>(new QName(lsQNameXML, "SpotLengths"),ArrayOfint.class,loObjSpotsl);
            loSpotListFilter.setSpotLengths(laSpotsl);
            
            loSpotListFilter.setSpotNumber(0);
            
            ArrayOfint loObjSpotsSt = new ArrayOfint();
            List<Integer> laObjsSpotsSt = new ArrayList<Integer>();
            //laObjsSales.add(1);
            loObjSpotsSt.setInt(laObjsSpotsSt);
            JAXBElement<ArrayOfint> laSpotsSt = 
                new JAXBElement<ArrayOfint>(new QName(lsQNameXML, "SpotStatuses"),ArrayOfint.class,loObjSpotsSt);
            loSpotListFilter.setSpotStatuses(laSpotsSt);
                    
            loSpotListFilter.setTxmnFailData(false);
            loSpotListFilter.setUntxmdSpotData(false);
            
            System.out.println("SETT SECURITY");
            
            LandmarkSecurityWsBean loLsWs = loPriceDao.getLandSecurityBeanValues();        
            Map<String, Object> loReqCtx = ((BindingProvider)loInstanceLandmarkSpotsSpot).getRequestContext();
            //req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WS_URL);
            Map<String, List<String>> loHeaders = new HashMap<String, List<String>>();
            loHeaders.put(loLsWs.getLsNameUser(), Collections.singletonList(loLsWs.getLsValueUser()));
            loHeaders.put(loLsWs.getLsNamePassword(), Collections.singletonList(loLsWs.getLsValuePassword()));
            loReqCtx.put(MessageContext.HTTP_REQUEST_HEADERS, loHeaders);
            System.out.println("SETT SECURITY.....OK");
            System.out.println("Guardar archivo fisico REQUEST");
            /*
            try{                        
                StreamResult result =
                new StreamResult(new File("C:\\Users\\Jorge-OMW\\Desktop\\pruebas\\Request-Alex"+getId()+".xml"));
                //transformer.transform(source, result);
                JAXB.marshal(spotListFilter, result);
            }catch(Exception loExo){
                System.out.println("Error al Guardar archivo fisico "+loExo.getMessage());
            }*/
            //##################### Insertar Archivo en Base de Datos ############################ 
            String lsNomFile = "";
            try{
                /*
                StreamResult result =
                new StreamResult(new File("C:\\Users\\JorgeOWM\\Desktop\\ParrillasOnDemandXml"+getId()+".xml"));
                //transformer.transform(source, result);
                JAXB.marshal(loXmlentrada, result);
                */
                XmlFilesDao loXmlFilesDao = new XmlFilesDao();
                System.out.println("Guardando archivo xml en bd");
                ByteArrayOutputStream loBaos = new ByteArrayOutputStream();                     
                JAXB.marshal(loSpotListFilter, new StreamResult(loBaos));
                InputStream           loFileXml = new ByteArrayInputStream(loBaos.toByteArray()); 
                lsNomFile = "PreciosRequest-" + getId() + ".xml";
                LmkIntXmlFilesRowBean loXmlBean = new LmkIntXmlFilesRowBean();
                loXmlBean.setLiIdFileXml(0);
                loXmlBean.setLiIdRequest(tiIdLogService);
                loXmlBean.setLiIdService(tiIdService);
                loXmlBean.setLsNomFile(lsNomFile);
                loXmlBean.setLsIndFileType("Request");
                loXmlBean.setLsIndServiceType(lsTypeProcess);
                loXmlBean.setLsIndEstatus("R"); //De Creado
                loXmlBean.setLsNomUserName(tsUserName);
                //loXmlBean.setLsNomUserPathFile(sPathFiles);
                loXmlBean.setLiIdUser(tiIdUser);
                loXmlBean.setLoIndFileStream(loFileXml);
                loXmlBean.setLsAttribute1(""+tsChannel+","+tsInitialDate+","+tsFinalDate);
                loXmlBean.setLsAttribute2("PriceFileType");
                // - Guardar archivo en bd
                ResponseUpdDao loXmlFile = 
                    loXmlFilesDao.insertLmkIntXmlFilesTab(loXmlBean);
                String lsMessInsert = "";
                if(loXmlFile.getLsResponse().equalsIgnoreCase("OK")){
                    lsMessInsert = "El Archivo "+lsNomFile+" se ha guardado en base de datos";
                }else{
                    lsMessInsert = "Error " + loXmlFile.getLsMessage() + " al guardar archivo "+
                                   lsNomFile+"";
                }
                liIndProcess = 
                            new UtilFaces().getIdConfigParameterByName("InsertFile");//
                        loBitBean.setLiIdLogServices(tiIdLogService);
                        loBitBean.setLiIdService(tiIdService);
                        loBitBean.setLiIndProcess(liIndProcess);
                        loBitBean.setLiNumProcessId(0);
                        loBitBean.setLiNumPgmProcessId(0);
                        loBitBean.setLsIndEvento(lsMessInsert);
                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   tiIdUser, 
                                                   tsUserName);
                
                
            }catch(Exception loEx){
                System.out.println("Error al guardar archivo "+loEx.getMessage());
                liIndProcess = new UtilFaces().getIdConfigParameterByName("GeneralError");
                loBitBean.setLiIdLogServices(tiIdLogService);
                loBitBean.setLiIdService(tiIdService);
                loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
                loBitBean.setLiNumProcessId(0);
                loBitBean.setLiNumPgmProcessId(0);
                loBitBean.setLsIndEvento("Error al generar Archivo XML-Request");
                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   tiIdUser, 
                                                   tsUserName);
                
                
            }        
            try{
                System.out.println("Invocar Servicio................");
                liIndProcess = new UtilFaces().getIdConfigParameterByName("InvokingService");
                loBitBean.setLiIdLogServices(tiIdLogService);
                loBitBean.setLiIdService(tiIdService);
                loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
                loBitBean.setLiNumProcessId(0);
                loBitBean.setLiNumPgmProcessId(0);
                loBitBean.setLsIndEvento("Servicio Landmark invocado para Actualizacion de Precios");
                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   tiIdUser, 
                                                   tsUserName);
                //########################################################################################
                try{
                    loArrOf = loInstanceLandmarkSpotsSpot.loadForFilter2(loSpotListFilter, "MXN");
                }catch(Exception loEx){
                    System.out.println("Error al guardar archivo "+loEx.getMessage());
                    liIndProcess = new UtilFaces().getIdConfigParameterByName("GeneralError");
                    loBitBean.setLiIdLogServices(tiIdLogService);
                    loBitBean.setLiIdService(tiIdService);
                    loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento("Error al consumir Servicio Web LoadForFilter2");
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                       tiIdUser, 
                                                       tsUserName);
                }
                //########################################################################################
                System.out.println("Guardar archivo fisico RESPONSE");
                try{  
                    /*
                    StreamResult result =
                    new StreamResult(new File("C:\\Users\\Jorge-OMW\\Desktop\\pruebas\\Response-Alex"+getId()+".xml"));
                    //transformer.transform(source, result);
                    JAXB.marshal(loArrof, result);*/
                    
                    System.out.println("Guardando archivo response xml en bd");
                    ByteArrayOutputStream loBaosRes = new ByteArrayOutputStream();
                    JAXB.marshal(loArrOf, loBaosRes);
                    InputStream           loFileXmlRes = new ByteArrayInputStream(loBaosRes.toByteArray()); 
                    
                    lsNomFile = "PreciosResponse-" + getId() + ".xml";
                    LmkIntXmlFilesRowBean loXmlBean = new LmkIntXmlFilesRowBean();
                    loXmlBean.setLiIdFileXml(0);
                    loXmlBean.setLiIdRequest(tiIdLogService);
                    loXmlBean.setLiIdService(tiIdService);
                    loXmlBean.setLsNomFile(lsNomFile);
                    loXmlBean.setLsIndFileType("Response");
                    loXmlBean.setLsIndServiceType(lsTypeProcess);
                    loXmlBean.setLsIndEstatus("R"); //De Creado
                    loXmlBean.setLsNomUserName(tsUserName);
                    //loXmlBean.setLsNomUserPathFile(sPathFiles);
                    loXmlBean.setLiIdUser(tiIdUser);
                    loXmlBean.setLoIndFileStream(loFileXmlRes);
                    loXmlBean.setLsAttribute1(""+tsChannel+","+tsInitialDate+","+tsFinalDate);
                    loXmlBean.setLsAttribute2("PriceFileType");
                    // - Guardar archivo en bd
                    XmlFilesDao loXmlFilesDao = new XmlFilesDao();
                    ResponseUpdDao loXmlFile = 
                        loXmlFilesDao.insertLmkIntXmlFilesTab(loXmlBean);
                    String lsMessInsert = "";
                    if(loXmlFile.getLsResponse().equalsIgnoreCase("OK")){
                        lsMessInsert = "El Archivo "+lsNomFile+" se ha guardado en base de datos";
                    }else{
                        lsMessInsert = "Error " + loXmlFile.getLsMessage() + " al guardar archivo "+
                                       lsNomFile+"";
                    }
                    liIndProcess = 
                                new UtilFaces().getIdConfigParameterByName("InsertFile");//
                            loBitBean.setLiIdLogServices(tiIdLogService);
                            loBitBean.setLiIdService(tiIdService);
                            loBitBean.setLiIndProcess(liIndProcess);
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento(lsMessInsert);
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                       tiIdUser, 
                                                       tsUserName);
                    
                    
                    }catch(Exception loEx){
                        System.out.println("Error al guardar archivo "+loEx.getMessage());
                        liIndProcess = new UtilFaces().getIdConfigParameterByName("GeneralError");
                        loBitBean.setLiIdLogServices(tiIdLogService);
                        loBitBean.setLiIdService(tiIdService);
                        loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
                        loBitBean.setLiNumProcessId(0);
                        loBitBean.setLiNumPgmProcessId(0);
                        loBitBean.setLsIndEvento("Error al generar Archivo XML-Request");
                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                           tiIdUser, 
                                                           tsUserName);
                        
                    
                    }
            }catch(Exception loEx){
                loArrOf = null;
                liIndProcess = new UtilFaces().getIdConfigParameterByName("GeneralError");
                loBitBean.setLiIdLogServices(tiIdLogService);
                loBitBean.setLiIdService(tiIdService);
                loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
                loBitBean.setLiNumProcessId(0);
                loBitBean.setLiNumPgmProcessId(0);
                loBitBean.setLsIndEvento("Error al consumir Servicio Landmark "+loEx.getMessage());
                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   tiIdUser, 
                                                   tsUserName);
            }
        }catch(Exception loExGral){
            loArrOf = null;
            System.out.println("Error general al consumir servicio");
            liIndProcess = new UtilFaces().getIdConfigParameterByName("GeneralError");
            loBitBean.setLiIdLogServices(tiIdLogService);
            loBitBean.setLiIdService(tiIdService);
            loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
            loBitBean.setLiNumProcessId(0);
            loBitBean.setLiNumPgmProcessId(0);
            loBitBean.setLsIndEvento("Error General al consumir Servicio Landmark "+loExGral.getMessage());
            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               tiIdUser, 
                                               tsUserName);
        }
        return loArrOf;
    }
    
    /**
     * Obtiene identificador en base al momento capturado en tiempo
     * @autor Jorge Luis Bautista Santiago
     * @return String
     */
    public String getId(){
        String     lsResponse = null;
        DateFormat loDf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        lsResponse = loDf.format(new java.util.Date(System.currentTimeMillis()));
        return lsResponse;
    }
    
}
