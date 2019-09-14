/**
* Project: Integraton Paradigm - Landmark
*
* File: SpotStatusService.java
*
* Created on: Septiembre 6, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.service;

import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfint;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import java.sql.SQLException;

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
import mx.com.televisa.landamark.model.daos.ServicesParamsDao;
import mx.com.televisa.landamark.model.daos.SpotStatusDao;
import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.model.daos.XmlFilesDao;
import mx.com.televisa.landamark.model.types.LandmarkSecurityWsBean;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesLogRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServicesParamsRowBean;
import mx.com.televisa.landamark.model.types.LmkIntXmlFilesRowBean;
import mx.com.televisa.landamark.model.types.LmkSpotsBean;
import mx.com.televisa.landamark.model.types.LmkSpotstatusTrxBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;
import mx.com.televisa.landamark.view.types.BasicInputParameters;
import mx.com.televisa.landamark.view.types.ResponseService;

import org.datacontract.schemas._2004._07.landmark_classes.ArrayOfSpot;
import org.datacontract.schemas._2004._07.landmark_classes.Spot;
import org.datacontract.schemas._2004._07.landmark_parameters.ArrayOfFilterDateTime;
import org.datacontract.schemas._2004._07.landmark_parameters.DaysOfWeek;
import org.datacontract.schemas._2004._07.landmark_parameters.FilterDateTime;
import org.datacontract.schemas._2004._07.landmark_parameters.Interactivity;
import org.datacontract.schemas._2004._07.landmark_parameters.SpotListFilter;
import org.datacontract.schemas._2004._07.landmark_parameters.SpotListFilterCriteria;

import org.tempuri.ILandmarkSpotsSpot;
import org.tempuri.LandmarkSpotsSpot;

/** Clase que ejecuta logica o servicio de Estado del Spot
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Septiembre 06, 2019, 12:00 pm
 */
public class SpotStatusAllService {
    public SpotStatusAllService() {
        super();
    }
    
    /**
     * Funcion principal de la clase
     * @autor Jorge Luis Bautista Santiago
     * @param loInput
     * @return ResponseService
     */
    public ResponseService executeService(BasicInputParameters loInput){
        
        ResponseService        loResponseService = new ResponseService();
        EntityMappedDao        loEntityMappedDao = new EntityMappedDao();
        ServicesParamsDao      loSpDao = new ServicesParamsDao();
        boolean                lbProcess = true;
       
        String lsReturn = "SpotStatus execute";
        
        loResponseService.setLiIdRequest(loInput.getLiIdRequest());
        loResponseService.setLiIdService(loInput.getLiIdService());
        loResponseService.setLiIdUser(loInput.getLiIdUser());
        loResponseService.setLsMessageResponse(lsReturn);
        loResponseService.setLsServiceType(loInput.getLsServiceType());
        loResponseService.setLsMessageResponse(lsReturn);
        loResponseService.setLsUserName(loInput.getLsUserName());
        //Obtener idLog service de la tabla 
        Integer                   liIdLogService = new ViewObjectDao().getMaxIdParadigm("Log") + 1;
        LmkIntServicesLogRowBean loSlb = new LmkIntServicesLogRowBean();
        loSlb.setLiIdLogServices(liIdLogService);
        loSlb.setLiIdService(loInput.getLiIdService());
        loSlb.setLiIndProcess(0);
        loSlb.setLiNumPgmProcessId(loInput.getLiIdRequest());
        loSlb.setLiNumProcessId(loInput.getLiIdRequest());
        loSlb.setLiNumUser(loInput.getLiIdUser());
        loSlb.setLsIndEstatus("1");
        loSlb.setLsIndResponse("N");
        loSlb.setLsIndServiceType(loInput.getLsServiceType());
        loSlb.setLsMessage("Execute "+loInput.getLsServiceName());
        loSlb.setLsUserName(loInput.getLsUserName());
        loSlb.setLiIdUser(loInput.getLiIdUser());
        
        loEntityMappedDao.insertSimpleServicesLog(loSlb, 
                                                  loInput.getLiIdUser(), 
                                                  loInput.getLsUserName()
                                                  );
        
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
        Integer                  liIndProcess = 
            loEntityMappedDao.getGeneralParameterID("ExecuteService", 
                                                    "PROCESS_INTEGRATION");
        loBitBean.setLiIdLogServices(liIdLogService);
        loBitBean.setLiIdService(loInput.getLiIdService());
        loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
        loBitBean.setLiNumProcessId(0);
        loBitBean.setLiNumPgmProcessId(0);
        loBitBean.setLsIndEvento("Ejecución de Servicio de SpotStatus");
        
        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                           loInput.getLiIdUser(), 
                                           loInput.getLsUserName());                
                                
        liIndProcess = loEntityMappedDao.getGeneralParameterID("ExtractParameters", 
                                                    "PROCESS_INTEGRATION");
        loBitBean.setLiIdLogServices(liIdLogService);
        loBitBean.setLiIdService(loInput.getLiIdService());
        loBitBean.setLiIndProcess(liIndProcess); 
        loBitBean.setLiNumProcessId(0);
        loBitBean.setLiNumPgmProcessId(0);
        loBitBean.setLsIndEvento("Extraer Parámetros para servicio "+loInput.getLsServiceName());
        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                           loInput.getLiIdUser(), 
                                           loInput.getLsUserName());
        // - Extraer parámetros de la tabla
        List<LmkIntServicesParamsRowBean> loParams = 
            loSpDao.getLmkIntServicesParams(loBitBean.getLiIdService());
        
        if( loParams.size() < 3 ){ //FI, FF y al menos un canal
            lbProcess = false;
            liIndProcess = loEntityMappedDao.getGeneralParameterID("ParametersMissing", 
                                                    "PROCESS_INTEGRATION");
                    loBitBean.setLiIdLogServices(liIdLogService);
                    loBitBean.setLiIdService(loInput.getLiIdService());
                    loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
                    loBitBean.setLiNumProcessId(0);
                    loBitBean.setLiNumPgmProcessId(0);
                    loBitBean.setLsIndEvento("Parámetros insuficientes para el Servicio (SpotStatus) " +
                                             loInput.getLsServiceName());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                       loInput.getLiIdUser(), 
                                                       loInput.getLsUserName());
            
        }
        else{
            String lsFecInicial = "";
            String lsFecFinal = "";
            List<String> laChannels = new ArrayList<String>();
            for(LmkIntServicesParamsRowBean loBean:loParams){
                if(loBean.getLsIndParameter().equalsIgnoreCase("FECHA_INICIAL")){
                    lsFecInicial = loBean.getLsIndValParameter();
                }
                if(loBean.getLsIndParameter().equalsIgnoreCase("FECHA_FINAL")){
                    lsFecFinal = loBean.getLsIndValParameter();
                }
                if(!loBean.getLsIndParameter().equalsIgnoreCase("FECHA_INICIAL") &&
                !loBean.getLsIndParameter().equalsIgnoreCase("FECHA_FINAL")){
                     if(loBean.getLsIndValParameter().equalsIgnoreCase("1")){
                         laChannels.add(loBean.getLsIndParameter());
                     }
                }
                
            }
            
            //Proceso para guardar parametros en la tabla de log services
            String lsFechasLog = "["+lsFecInicial + ","+lsFecInicial+"]";
            String lsChannelsLog = "";            
            for(String lsChannel : laChannels){
                lsChannelsLog += lsChannel+",";
            }
            loEntityMappedDao.updateParametersServiceLog(liIdLogService, 
                                                         loInput.getLiIdService(), 
                                                         lsFechasLog, 
                                                         lsChannelsLog);
            
            //laChannels Contiene todos los canales configurados para este proposito
            /*boolean     lbFlagRecon = true;
            int         liI = 0;
            AsRunAsDao  loAsRunAsDao = new AsRunAsDao();      
            
            while(lbFlagRecon && liI < laChannels.size()){
                String lsChannel = laChannels.get(liI);
                Integer liFlag = 
                    loAsRunAsDao.getFlagInsertLogCertificado(lsFecInicial, 
                                                             lsChannel
                                                            );  
                if(liFlag > 0){
                    lbFlagRecon = false;
                }
                liI++;
            }
            
            
            liIndProcess = 
            loEntityMappedDao.getGeneralParameterID("FlagReconComplete", 
                                                    "PROCESS_INTEGRATION");
            
            loBitBean.setLiIdLogServices(liIdLogService);
            loBitBean.setLiIdService(loInput.getLiIdService());
            loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
            loBitBean.setLiNumProcessId(0);
            loBitBean.setLiNumPgmProcessId(0);
            loBitBean.setLsIndEvento("Validación de todos los canales, RECON COMPLETE[" + lbFlagRecon + "]" +
                "para SpotStatus");
            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               loInput.getLiIdUser(), 
                                               loInput.getLsUserName());
            */
            //if(lbFlagRecon){
                //Eliminar tabla de transaccion
                SpotStatusDao loSpotStatusDao = new SpotStatusDao();
                ResponseUpdDao loDel = loSpotStatusDao.deleteRowsSpotStatusTrx();
                System.out.println("NE- Resultado de eliminar registros: "+loDel.getLsResponse());
                if(loDel.getLsResponse().equalsIgnoreCase("OK")){                
                    //Todos los canales superaron la validacion
                    ArrayOfSpot loArrayOfSpot = 
                        getRequestLandmarkPrices(laChannels, 
                                                 lsFecInicial, 
                                                 lsFecFinal,
                                                 liIdLogService,
                                                 loInput.getLiIdService(),                 
                                                 loInput.getLiIdUser(), 
                                                 loInput.getLsUserName(),
                                                 loInput.getLsServiceType());//Verificar este
                    //2.- Leer el response XML
                    if(loArrayOfSpot != null){
                        System.out.println("Leer response xml landmark");
                        //ResponseUpdDao loSetSpot = setArrayOfSpotParadigm(loArrayOfSpot);   
                        ResponseUpdDao loSetSpot = 
                            setArrayOfSpotParadigm(loArrayOfSpot);/*,
                                                   lsChannelsLog, 
                                                   lsFecInicial, 
                                                   lsFecFinal,
                                                   liIdLogService,
                                                   loInput.getLiIdService(),
                                                   loInput.getLiIdUser(), 
                                                   loInput.getLsUserName(),
                                                   loInput.getLsServiceType());   */
                        
                        System.out.println(">>>> getLsResponse: "+loSetSpot.getLsResponse());
                        System.out.println(">>>> getLsMessage: "+loSetSpot.getLsMessage());
                        
                        liIndProcess =                             
                                    loEntityMappedDao.getGeneralParameterID("InsertCtrlTable", 
                                                                            "PROCESS_INTEGRATION");
                                loBitBean.setLiIdLogServices(liIdLogService);
                                loBitBean.setLiIdService(loInput.getLiIdService());
                                loBitBean.setLiIndProcess(liIndProcess);
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento(loSetSpot.getLsMessage());
                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                        loInput.getLiIdUser(), 
                        loInput.getLsUserName());
                    }
                    
                    liIndProcess = 
                                loEntityMappedDao.getGeneralParameterID("ProcessFinish", 
                                                                        "PROCESS_INTEGRATION");
                            loBitBean.setLiIdLogServices(liIdLogService);
                            loBitBean.setLiIdService(loInput.getLiIdService());
                            loBitBean.setLiIndProcess(liIndProcess);
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("Proceso finalizado para ["+lsChannelsLog+lsFechasLog+"]");
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                    loInput.getLiIdUser(), 
                    loInput.getLsUserName());
                }else{
                    liIndProcess = 
                                loEntityMappedDao.getGeneralParameterID("GeneralError", 
                                                                        "PROCESS_INTEGRATION");
                            loBitBean.setLiIdLogServices(liIdLogService);
                            loBitBean.setLiIdService(loInput.getLiIdService());
                            loBitBean.setLiIndProcess(liIndProcess);
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("No es posible eliminar registros de LMK_SPOTSTATUS_TRX");
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                    loInput.getLiIdUser(), 
                    loInput.getLsUserName());
                }
                System.out.println("["+lsChannelsLog+"] FIN DE JOB ["+new Date()+"]");
            //}
        }
        return loResponseService;
    }
    
    public ResponseUpdDao setArrayOfSpotParadigm(ArrayOfSpot toArrayOfSpot){
        boolean lbResult = true;
        Integer liGeneralNumSpots = 0;
        String lsMessage = "Proceso de Lectura y Actualización Satisfactorio";
        List<LmkSpotstatusTrxBean> loAllSpots = new ArrayList<LmkSpotstatusTrxBean>();
                
        ResponseUpdDao loResponseUpdDao = new ResponseUpdDao();
        
        List<Spot> loListSpot = toArrayOfSpot.getSpot();
        liGeneralNumSpots = loListSpot.size();
        loResponseUpdDao.setLiAffected(liGeneralNumSpots);
        System.out.println("Num spots: "+loListSpot.size());
        lsMessage = "("+liGeneralNumSpots+" spots) "+lsMessage;
        for(Spot loSpot : loListSpot){
            
            LmkSpotstatusTrxBean loBean = new LmkSpotstatusTrxBean();
            loBean.setLsAdvertisercode(loSpot.getAdvertiserCode().getValue());
            
            loBean.setLsAdvertisercode(loSpot.getAdvertiserCode().getValue());
            loBean.setLsAgengycode(loSpot.getAgencyCode().getValue());
            loBean.setLiCampaign(loSpot.getCampaign());
            loBean.setLiBreakNumber(loSpot.getBreakNumber());
            loBean.setLiDeal(loSpot.getDeal());
            loBean.setLsExternalreference(loSpot.getExternalReference().getValue());
            loBean.setLsIndustryCode(loSpot.getIndustryCode().getValue());
            loBean.setLiLength(loSpot.getLength());
            loBean.setLdNomianlprice(loSpot.getNomianlPrice());
            loBean.setLdRatings(loSpot.getRatings());
            ////////////////////////////////////////////////////////////////////
            SimpleDateFormat loSdf = new SimpleDateFormat("yyyy-MM-dd");
            GregorianCalendar loGc = 
                loSpot.getScheduledDate().toGregorianCalendar();
            String lsScheduledate = loSdf.format(loGc.getTime());
            loBean.setLsScheduleDate(lsScheduledate);
            loBean.setLoScheduledate(loSpot.getScheduledDate());                                    
            ////////////////////////////////////////////////////////////////////
            loBean.setLiScheduletime(loSpot.getScheduledTime());
            loBean.setLiSpotnumber(loSpot.getSpotNumber());
            loBean.setLiSpotsalesareanumber(loSpot.getSpotSalesAreaNumber());
            loBean.setLiBreakPosition(loSpot.getBreakPosition());
            loBean.setLiPreemptee(loSpot.getPreemptee());
            loBean.setLiPreemptor(loSpot.getPreemptor());
            //loBean.setLsStatus(loSpot.getStatus().getValue());            
            //Llenar lista de rows de la nueva tabla
            loAllSpots.add(loBean);
            
        }
        
        //Validar si existe al menos un registro
        if(loAllSpots.size() <= 0){
            lbResult = false;
            lsMessage = "Sin Spots Para Procesar";
        }else{
            SpotStatusDao loSpotStatusDao = new SpotStatusDao();
            //Insertar todos los spots en la tabla de transaccion
            boolean lbFlagInsert = true;
            int liI = 0;
            System.out.println("Insertar en tabla de control la lista de Spots");
            /*
            while(lbFlagInsert && liI < loAllSpots.size()){
                LmkSpotstatusTrxBean loBean = loAllSpots.get(liI);
                System.out.print("Advertisercode: "+loBean.getLsAdvertisercode());
                System.out.print("\tAgengycode: "+loBean.getLsAgengycode());
                System.out.print("\tSpotnumber: "+loBean.getLiSpotnumber());
                System.out.print("\tScheduleDate: "+loBean.getLsScheduleDate());
                System.out.print("\tCampaign: "+loBean.getLiCampaign());
                System.out.print("\tBreakNumber: "+loBean.getLiBreakNumber());
                System.out.println("");
                liI++;
            }*/
            
            while(lbFlagInsert && liI < loAllSpots.size()){
                LmkSpotstatusTrxBean loBean = loAllSpots.get(liI);
                ResponseUpdDao loRes = loSpotStatusDao.insertRowSpotStatusTrx(loBean);    
                if(!loRes.getLsResponse().equalsIgnoreCase("OK")){
                    lbFlagInsert = false;
                    lsMessage = loRes.getLsMessage();
                }
                liI++;
            }
            if(!lbFlagInsert){
                lbResult = false;
                loSpotStatusDao.deleteRowsSpotStatusTrx();
            }else{
                //Invocar SP final LMK_UPD_SPOTSTATUS
                System.out.println("Invocar SP final LMK_UPD_SPOTSTATUS");
                try {
                    ResponseUpdDao loRes = loSpotStatusDao.callProcedureUpdSpotStatus();
                    if(!loRes.getLsResponse().equalsIgnoreCase("OK")){
                        lbResult = false;
                        lsMessage = loRes.getLsMessage();
                    }
                } catch (SQLException e) {
                    lbResult = false;
                    lsMessage = e.getMessage();
                }
            }
        }
        
        if(lbResult){
            System.out.println("OK");
            loResponseUpdDao.setLsResponse("OK");
        }else{
            System.out.println("ERROR");
            loResponseUpdDao.setLsResponse("ERROR");
        }
        loResponseUpdDao.setLsMessage(lsMessage);
        
        return loResponseUpdDao;
    }
    
    
    public ResponseUpdDao setArrayOfSpotParadigmRead(ArrayOfSpot toArrayOfSpot){
        boolean lbResult = true;
        String lsMessage = "Proceso de Inserción y Actualización Satisfactorio";
        
        ResponseUpdDao loResponseUpdDao = new ResponseUpdDao();
        //PriceDao loPriceDao = new PriceDao();
                
        List<Spot> loListSpot = toArrayOfSpot.getSpot();
        System.out.println("loListSpot.size()= "+loListSpot.size());
        for(Spot loSpot : loListSpot){
            
            Integer liSpotNumber = loSpot.getSpotNumber();            
            System.out.println("CANAL["+loSpot.getSalesSplitId()+"] SpotNumber["+loSpot.getSpotNumber()+"]");
            Double ldCpp = loSpot.getCPP();
            System.out.println("ldCpp["+ldCpp+"]");
            //Double ldCppl = loSpot.getCPPL();
            //Double ldCpt = loSpot.getCPT();
            //Double ldCptl = loSpot.getCPTL();
            Integer liLength = loSpot.getLength();
            System.out.println("liLength["+liLength+"]");
            Double ldNominalPrice = loSpot.getNomianlPrice();
            System.out.println("ldNominalPrice["+ldNominalPrice+"]");
            //Double ldTotalNominalPrice = loSpot.getTotalNominalPrice();
            Double ldPriceFactor = loSpot.getPriceFactor();
            System.out.println("ldPriceFactor["+ldPriceFactor+"]");
            Double ldRatings = loSpot.getRatings();
            System.out.println("ldRatings["+ldRatings+"]");
            
            //Se necesitan los valores de 
            // piOrderID
            // piSpotID
            /*List<LmkSpotsBean> loSpotsList = 
                loPriceDao.getSpotInfo(liSpotNumber);            
            if(loSpotsList.size() > 0){
                for(LmkSpotsBean loBean : loSpotsList){
                    System.out.println(">> getLiOrdId: "+loBean.getLiOrdId());
                    System.out.println(">> getLiSpotNumber: "+loBean.getLiSpotNumber());
                    System.out.println(">> getLiSptmstid: "+loBean.getLiSptmstid());
                }
            } */
        }
        
        if(lbResult){
            loResponseUpdDao.setLsResponse("OK");
        }else{
            loResponseUpdDao.setLsResponse("ERROR");
        }
        loResponseUpdDao.setLsMessage(lsMessage);
        
        return loResponseUpdDao;
    }
    
    
    
    /**
     * Obtiene, en base a la fecha, el id_paradigm a manejar en intergracion
     * @autor Jorge Luis Bautista Santiago     
     * @return String
     */
    public String getIdBitacora(){
        String lsResponse = null;
        DateFormat loDf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        lsResponse = loDf.format(new java.util.Date(System.currentTimeMillis()));
        return lsResponse;
    }
    
    private ArrayOfSpot getRequestLandmarkPrices(List<String> toChannels, 
                                                 String tsInitialDate, 
                                                 String tsFinalDate,
                                                 Integer tiIdLogService,
                                                 Integer tiIdService,
                                                 Integer tiIdUser,
                                                 String tsUserName,
                                                 String lsTypeProcess){
        ArrayOfSpot                  loArrOf = new ArrayOfSpot();
        PriceDao                     loPriceDao = new PriceDao();
        Integer                      liIndProcess = 0;
        EntityMappedDao              loEntityMappedDao = new EntityMappedDao();
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
        //System.out.println("dentro de getRequestLandmarkPrices");
        String lsAllChannels = "";
        for(String lsCanal : toChannels){
            lsAllChannels += lsCanal+",";
        }
        try{
            //System.out.println("dentro de getRequestLandmarkPrices try.....");
            String lsQNameXML = "";
            LandmarkSpotsSpot  loLandmarkSpotsSpot = new LandmarkSpotsSpot();
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
                new JAXBElement<String>(new QName(lsQNameXML, "BreakType"),String.class, "");
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
            
            //JAXBElement<String> loCallerPositionCode = 
              //  new JAXBElement<String>(new QName(lsQNameXML, "CallerPositionCode"),String.class, "TRN07");
            //loSpotListFilter.setCallerPositionCode(loCallerPositionCode);
            
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
            //Cambio de acuerdo a JEJ loSpotListFilter.setCopyProcess(-1);
            loSpotListFilter.setCopyProcess(0);
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
            //String tsFinalDate2 = tsFinalDate+"";
            SimpleDateFormat loSdf = new SimpleDateFormat("yyyy-MM-dd");
            Date loDateTmp;
            try {
                loDateTmp = loSdf.parse(tsFinalDate);
                GregorianCalendar loGregorCalendar = new GregorianCalendar();
                loGregorCalendar.setTime(loDateTmp);
                XMLGregorianCalendar loFecFinXml = 
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(loGregorCalendar);
                loFecFinXml.setTimezone(0);
                loFilterTime.setEndDate(loFecFinXml);
            } catch (ParseException e) {
                System.out.println("Error al parsear: "+e.getMessage());
            } catch (DatatypeConfigurationException e) {
                System.out.println("Error Fechas : "+e.getMessage());
            }
            loFilterTime.setEndTime(255959);        
            Date loDateStart;
            try {
                loDateStart = loSdf.parse(tsInitialDate);
                GregorianCalendar loGregorCalendar = new GregorianCalendar();
                loGregorCalendar.setTime(loDateStart);
                XMLGregorianCalendar loFecInicialXml = 
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(loGregorCalendar);
                loFecInicialXml.setTimezone(0);
                loFilterTime.setStartDate(loFecInicialXml);
            } catch (ParseException e) {
                System.out.println("Error al parsear: "+e.getMessage());
            } catch (DatatypeConfigurationException e) {
                System.out.println("Error Fechas 999: "+e.getMessage());
            }        
            loFilterTime.setStartTime(20000);        
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
            for(String tsChannel : toChannels){
                //Debido a que en esta clase solo llega un canal, no es necesario iterar
                //Ir al la base de datos para obtener el correspondiente mapeo del actual canal
                List<String> loListChannels = loPriceDao.getCodPriceChannel(tsChannel);
                if(loListChannels.size() > 0){
                    laObjsSales.add(Integer.parseInt(loListChannels.get(0)));
                    //System.out.println("Sales Area obtenida ["+loListChannels.get(0)+"] de "+tsChannel);
                }else{
                    loEntityMappedDao.getGeneralParameterID("ErrorConfig", 
                                                            "PROCESS_INTEGRATION");
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
            } 
            loObjSales.setInt(laObjsSales);
            JAXBElement<ArrayOfint> laSales = 
                new JAXBElement<ArrayOfint>(new QName(lsQNameXML, "SalesAreas"),ArrayOfint.class,loObjSales);
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
            
            try{                        
                StreamResult result =
                new StreamResult(new File("C:\\Users\\Jorge-OMW\\Desktop\\pruebas\\Request-Landmark"+getId()+".xml"));
                //transformer.transform(source, result);
                JAXB.marshal(loSpotListFilter, result);
            }catch(Exception loExo){
                System.out.println("Error al Guardar archivo fisico "+loExo.getMessage());
            }
            //String lsNomFile = "";
            //##################### Insertar Archivo en Base de Datos ############################ 
            String lsNomFile = "";
            try{
                XmlFilesDao loXmlFilesDao = new XmlFilesDao();
                System.out.println("Guardando archivo xml en bd");
                ByteArrayOutputStream loBaos = new ByteArrayOutputStream();                     
                JAXB.marshal(loSpotListFilter, new StreamResult(loBaos));
                InputStream           loFileXml = new ByteArrayInputStream(loBaos.toByteArray()); 
                lsNomFile = "SpotStatus REQ - " + getId() + ".xml";
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
                loXmlBean.setLsAttribute1(""+lsAllChannels+""+tsInitialDate+","+tsFinalDate);
                loXmlBean.setLsAttribute2("SpotStatuseFileType");
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
                            loEntityMappedDao.getGeneralParameterID("InsertFile", 
                                                                    "PROCESS_INTEGRATION");
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
                loEntityMappedDao.getGeneralParameterID("GeneralError", 
                                                        "PROCESS_INTEGRATION");
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
                loEntityMappedDao.getGeneralParameterID("InvokingService", 
                                                        "PROCESS_INTEGRATION");
                loBitBean.setLiIdLogServices(tiIdLogService);
                loBitBean.setLiIdService(tiIdService);
                loBitBean.setLiIndProcess(liIndProcess); //Tipo de Proceso
                loBitBean.setLiNumProcessId(0);
                loBitBean.setLiNumPgmProcessId(0);
                loBitBean.setLsIndEvento("Servicio Landmark invocado para SpotStatus");
                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   tiIdUser, 
                                                   tsUserName);
                //########################################################################################
                try{
                    loArrOf = loInstanceLandmarkSpotsSpot.loadForFilter2(loSpotListFilter, "MXN");
                }catch(Exception loEx){
                    System.out.println("Error al consumir servicio loadForFilter2 "+loEx.getMessage());
                    loEntityMappedDao.getGeneralParameterID("GeneralError", 
                                                            "PROCESS_INTEGRATION");
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
                //Al usuario no le interesa el response de Landmark
                System.out.println("Guardar archivo fisico RESPONSE local");
                try{ /*                     
                    StreamResult result =
                    new StreamResult(new File("C:\\Users\\Jorge-OMW\\Desktop\\pruebas\\Response-Landmark"+getId()+".xml"));
                    JAXB.marshal(loArrOf, result);
                    */
                    System.out.println("Guardando archivo response xml en bd");
                    /*ByteArrayOutputStream loBaosRes = new ByteArrayOutputStream();
                    JAXB.marshal(loArrOf, loBaosRes);
                    InputStream           loFileXmlRes = new ByteArrayInputStream(loBaosRes.toByteArray()); 
                    
                    lsNomFile = "SpotStatus RES - " + getId() + ".xml";
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
                    loXmlBean.setLsAttribute1(""+lsAllChannels+","+tsInitialDate+","+tsFinalDate);
                    loXmlBean.setLsAttribute2("SpotStatusFileType");
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
                                loEntityMappedDao.getGeneralParameterID("InsertFile", 
                                                                        "PROCESS_INTEGRATION");
                            loBitBean.setLiIdLogServices(tiIdLogService);
                            loBitBean.setLiIdService(tiIdService);
                            loBitBean.setLiIndProcess(liIndProcess);
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento(lsMessInsert);
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                       tiIdUser, 
                                                       tsUserName);
                    
                    */
                    }catch(Exception loEx){
                        System.out.println("Error al guardar archivo "+loEx.getMessage());
                        loEntityMappedDao.getGeneralParameterID("GeneralError", 
                                                                "PROCESS_INTEGRATION");
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
                loEntityMappedDao.getGeneralParameterID("GeneralError", 
                                                        "PROCESS_INTEGRATION");
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
        }
        catch(Exception loExGral){
            loArrOf = null;
            System.out.println("Error general al consumir servicio "+loExGral.getMessage());
            loEntityMappedDao.getGeneralParameterID("GeneralError", 
                                                    "PROCESS_INTEGRATION");
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
