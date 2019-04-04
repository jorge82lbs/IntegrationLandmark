/**
* Project: Integraton Paradigm - Landmark
*
* File: OrderSpotsImpCron.java
*
* Created on: Febrero 23, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.jobs.service;

import java.io.File;
import java.io.FileInputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.MappingCatDao;
import mx.com.televisa.landamark.model.daos.OrderSpotsDao;
import mx.com.televisa.landamark.model.daos.XmlFilesDao;
import mx.com.televisa.landamark.model.types.LmkIntMappingCatRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.LmkIntXmlFilesRowBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;
import mx.com.televisa.landamark.model.types.inject.LmkSpotsRowBean;
import mx.com.televisa.landamark.services.beans.input.spots.Spot;
import mx.com.televisa.landamark.services.beans.input.spots.Spots;
import mx.com.televisa.landamark.services.sftp.SftpManagment;

import mx.com.televisa.landamark.util.UtilFaces;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/** Clase que ejecuta la lectura, guardado y logica de cada archivo
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
 */
public class OrderSpotsImpCron implements Job{
    public OrderSpotsImpCron() {
        super();
    }

    /**
     * Metodo que ejecuta la lectura guardado y logica de cada archivo
     * @autor Jorge Luis Bautista Santiago
     * @return void
     */
    @Override
    public void execute(JobExecutionContext toJobExecutionContext) throws JobExecutionException {
        JobDataMap                loDataMap = toJobExecutionContext.getJobDetail().getJobDataMap();
        String                    lsIdService = loDataMap.getString("lsIdService");  
        String                    lsUserName = loDataMap.getString("lsUserName");
        String                    lsIdUser = loDataMap.getString("liIdUser");
        String                    lsTypeProcess = loDataMap.getString("lsTypeProcess");
        String                    lsServiceName = loDataMap.getString("lsServiceName");
        String                    lsPathFiles = loDataMap.getString("lsPathFiles");
        String                    lsFileName = loDataMap.getString("lsFileName");
        String                    lsRequestMaster = loDataMap.getString("lsRequestMaster");
        SftpManagment             loSftpManagment = new SftpManagment();
        EntityMappedDao           loEntityMappedDao = new EntityMappedDao();
        Integer                   liIndProcess = 0;
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
        //Ir mediante ssh por el archivo a servidor de lanmdark
        String lsPathRemote = 
            loEntityMappedDao.getGeneralParameter("PATH_SPOTS_INPUT", "SSH_CONNECTION");
        
        ResponseUpdDao loResDn = 
            loSftpManagment.downloadFileSFTP(lsPathRemote, lsPathFiles, lsFileName, lsFileName);
        if(!loResDn.getLsResponse().equalsIgnoreCase("OK")){
            //Archivo no encontrado
            // Si por alguna razon ya lo borraron, es decir que no fue encontrado, mandar error a bitacora
            liIndProcess = 
                new UtilFaces().getIdConfigParameterByName("NoData");//
            loBitBean.setLiIdLogServices(Integer.parseInt(lsRequestMaster));
            loBitBean.setLiIdService(Integer.parseInt(lsIdService));
            loBitBean.setLiIndProcess(liIndProcess);
            loBitBean.setLiNumProcessId(0);
            loBitBean.setLiNumPgmProcessId(0);
            loBitBean.setLsIndEvento("Archivo no encontrado en ubicacion remota "+
                                     lsServiceName);
            
            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                               Integer.parseInt(lsIdUser), 
                                               lsUserName);   
        }else{
            //Validar nombre del archivo
            //
            boolean lbRes = validateNomarch(lsFileName);
            System.out.println("Validar Archivo:["+lbRes+"]");
            lbRes = true;
            System.out.println("Por el momento cambiar a true:["+lbRes+"]");
            if(!lbRes){
                liIndProcess = 
                            new UtilFaces().getIdConfigParameterByName("GeneralError");//
                        loBitBean.setLiIdLogServices(Integer.parseInt(lsRequestMaster));
                        loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                        loBitBean.setLiIndProcess(liIndProcess);
                        loBitBean.setLiNumProcessId(0);
                        loBitBean.setLiNumPgmProcessId(0);
                        loBitBean.setLsIndEvento("ERROR Nombre de Archivo incorrecto >> "+lsFileName);
                loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                   Integer.parseInt(lsIdUser), 
                                                   lsUserName);  
            }
            else{
                //Si el archivo si fue encontrado, entonces guardar copia en carpeta de WL  
                //-- Esto ya esta, al momento de la instruccion            
                //Guardar en base de datos (estatus=P (en paradigm))
                //##################### Insertar Archivo en Base de Datos ############################ 
                try {
                    System.out.println("leyendo archivo de: ["+lsPathFiles+lsFileName+"]");
                    File loFileInput = new File (lsPathFiles+lsFileName);
                    XmlFilesDao loXmlFilesDao = new XmlFilesDao();
                    LmkIntXmlFilesRowBean loXmlBean = new LmkIntXmlFilesRowBean();
                    FileInputStream loFis = new FileInputStream(loFileInput);
                    
                    loXmlBean.setLiIdFileXml(0);
                    loXmlBean.setLiIdRequest(Integer.parseInt(lsRequestMaster));
                    loXmlBean.setLiIdService(Integer.parseInt(lsIdService));
                    loXmlBean.setLsNomFile(lsFileName);
                    loXmlBean.setLsIndFileType("Requests");
                    loXmlBean.setLsIndServiceType(lsTypeProcess);
                    loXmlBean.setLsIndEstatus("R"); //De Creado
                    loXmlBean.setLsNomUserName(lsUserName);
                    loXmlBean.setLsNomUserPathFile(lsPathFiles);
                    loXmlBean.setLiIdUser(Integer.parseInt(lsIdUser));
                    loXmlBean.setLoIndFileStream(loFis);
                    loXmlBean.setLsAttribute1("");
                    loXmlBean.setLsAttribute2("OrdSpotsFileType");
                    // - Guardar archivo en bd
                    ResponseUpdDao loXmlFile = 
                        loXmlFilesDao.insertLmkIntXmlFilesTab(loXmlBean);
                    String lsMessInsert = "";
                    if(loXmlFile.getLsResponse().equalsIgnoreCase("OK")){
                        lsMessInsert = "El Archivo "+lsFileName+" se ha guardado en base de datos";
                    }else{
                        lsMessInsert = "Error " + loXmlFile.getLsMessage() + " al guardar archivo "+
                                       lsFileName+" size: "+loFileInput.getTotalSpace();
                    }
                    liIndProcess = 
                                new UtilFaces().getIdConfigParameterByName("InsertFile");//
                            loBitBean.setLiIdLogServices(Integer.parseInt(lsRequestMaster));
                            loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                            loBitBean.setLiIndProcess(liIndProcess);
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento(lsMessInsert);
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                       Integer.parseInt(lsIdUser), 
                                                       lsUserName);                                                
                    
                    //Leer archivo (estatus=W (leido))
                    //Aqu� va la lectura del xml, el archivo fisico 
                    ResponseUpdDao loRes = insertSpotsParadimg(lsPathFiles, lsFileName);
                    if(!loRes.getLsResponse().equalsIgnoreCase("OK")){
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("GeneralError");//
                                loBitBean.setLiIdLogServices(Integer.parseInt(lsRequestMaster));
                                loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                                loBitBean.setLiIndProcess(liIndProcess);
                                loBitBean.setLiNumProcessId(0);
                                loBitBean.setLiNumPgmProcessId(0);
                                loBitBean.setLsIndEvento("ERROR al insertar spots >> "+loRes.getLsMessage());
                        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                           Integer.parseInt(lsIdUser), 
                                                           lsUserName);  
                    }
                    else{//Continuar como dice el flujo de Jacobo
                        //Obtener Canal y fecha de acuerdo al nombre del archivo
                        String[] laNomarch = lsFileName.split(".");
                        String lsStnidNomarch = laNomarch[1];
                        String lsBcstdtNomarch = laNomarch[2];
                        String lsStnid = getChannelMapped(lsStnidNomarch);
                        String lsBcstdt = getBcstdtMapped(lsBcstdtNomarch,
                                                          "yyyymmdd",
                                                          "yyyy-mm-dd");
                        System.out.println("lsStnid["+lsStnid+"]");
                        System.out.println("lsBcstdt["+lsBcstdt+"]");
                        OrderSpotsDao loOrderSpotsDao = new OrderSpotsDao();
                         // 2)      Ejecutar el SP�. EVENTAS.LMK_VALIDA_SPOTS(STNID, BCSTDT)
                         ResponseUpdDao loResValida = 
                             loOrderSpotsDao.callLmkValidaSpotsPr(lsStnid, lsBcstdt);
                        if(!loResValida.getLsResponse().equalsIgnoreCase("OK")){
                            liIndProcess = 
                                        new UtilFaces().getIdConfigParameterByName("GeneralError");//
                                    loBitBean.setLiIdLogServices(Integer.parseInt(lsRequestMaster));
                                    loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                                    loBitBean.setLiIndProcess(liIndProcess);
                                    loBitBean.setLiNumProcessId(0);
                                    loBitBean.setLiNumPgmProcessId(0);
                                    loBitBean.setLsIndEvento("ERROR al Validar spots >> "+loRes.getLsMessage());
                            loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                               Integer.parseInt(lsIdUser), 
                                                               lsUserName);  
                        }
                        else{
                            //  3)      Al t�rmino de la ejecuci�n de este SP� validar la tabla � EVENTAS.LMK_SPOTS_STATUS � 
                            // al final esta tabla funciona como la de EVETV de LOG_COMERCIAL_STATUS donde a trav�s del Canal 
                            // y la Fecha de Transmisi�n se pondr� el tipo de error que se valid� antes de ejecutar el resto de 
                            // la informaci�n� Si la tabla tiene un solo registro con el Status Igual a OK, entonces se procede 
                            // al punto 4.. Si hay errores, aqu� definiremos por el campo de TIPO .. a quien se los 
                            // enviaremos v�a correo.
                            //  4)      Ejecutar el SP� EVENTAS.EVETV_GENERA_SPOTS(STNID, BCSTDT)
                            //  5)      Ejecutar el SP� EVENTAS.EVETV_GENERA_LOG(STNID, BCSTDT)
                            //  6)      Al termino de este �ltimo SP se enviar� un correo de notificaci�n a la gente de Trafico Log que su Log ha sido procesado correctamente desde Landmark ATS.
                            
                        }
                         
                    }
                    
                    //mapear de acuerdo a Paradigm
                    //Ejecutar logica que indique jacobo (estatusP (Procesado)) FIN
                    
                    
                }catch(Exception loEx){
                    liIndProcess = 
                                new UtilFaces().getIdConfigParameterByName("GeneralError");//
                            loBitBean.setLiIdLogServices(Integer.parseInt(lsRequestMaster));
                            loBitBean.setLiIdService(Integer.parseInt(lsIdService));
                            loBitBean.setLiIndProcess(liIndProcess);
                            loBitBean.setLiNumProcessId(0);
                            loBitBean.setLiNumPgmProcessId(0);
                            loBitBean.setLsIndEvento("ERROR. "+loEx.getMessage());
                    loEntityMappedDao.insertBitacoraWs(loBitBean,
                                                       Integer.parseInt(lsIdUser), 
                                                       lsUserName);
                    
                }
            }        
        }                
    }
    
    public boolean validateNomarch(String lsFileName){
        boolean lbResponse = true;
        //Split
        String[] laNameArr = lsFileName.split(".");
        if(laNameArr.length != 4){
            lbResponse = false;
        }
        return lbResponse;
    }
    
    public String getChannelMapped(String tsChannel){
        String lsChannel = "";
        MappingCatDao loMcd = new MappingCatDao();
        String lsWhere = " VAL_VALUE_DESTINY = '"+tsChannel+"'";
        List<LmkIntMappingCatRowBean> laArr = loMcd.getLmkIntServicesParams(lsWhere);
        if(laArr.size() > 0){
            lsChannel = laArr.get(0).getLsIndSysOrigin();
        }
        return lsChannel;
    }
    
   
    public String getBcstdtMapped(String tsBcstdt, 
                                  String tsMaskInput,
                                  String tsMaskOutput){
        String lsDateAsString = "";
        SimpleDateFormat lsParser = new SimpleDateFormat(tsMaskInput);        
        SimpleDateFormat lsFormatter = new SimpleDateFormat(tsMaskOutput);

        try {
            Date ltDate = lsParser.parse(tsBcstdt);
            lsDateAsString = lsFormatter.format(ltDate);
        } catch (ParseException loEx) {
            ;
        }
        return lsDateAsString;
    }
    
    public ResponseUpdDao insertSpotsParadimg(String tsFilePath, String tsFileName){
        ResponseUpdDao loResponseUpdDao = new ResponseUpdDao();
        System.out.println("-------------------- SPOTS -------------------------");    
        try {  
            String lsFileRead = tsFilePath+tsFileName;
            System.out.println("Se debe de leer de : ["+lsFileRead+"]");            
            lsFileRead = "C:\\Comercializacion\\Landmark\\AppUtilConsole\\Client\\SP.9C9C.20190329.90639.XML";
            System.out.println("pero por el momeneto se LEE de "+lsFileRead);
            JAXBContext context = JAXBContext.newInstance(Spots.class );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Spots loSpots = (Spots)unmarshaller.unmarshal(new File(lsFileRead));
            System.out.println("numero de spots: "+loSpots.getSpot().size());    
            System.out.println("1)  Se insertan los datos en la tabla EVENTAS.LMK_SPOTS \n( los �ltimos 8 datos de esta tabla se dejan en blanco porque ser�n usados por el procedimiento para ligar informaci�n de Paradigm)");    
            OrderSpotsDao loOrderSpotsDao = new OrderSpotsDao();
            for(Spot loSpot : loSpots.getSpot()){
                System.out.println("---------------------------------------------");    
                LmkSpotsRowBean loLmkSpotsRowBean = new LmkSpotsRowBean();
                loLmkSpotsRowBean = getLmkSpotsRowBeanBySpot(loSpot);
                //Insertar en tabla:
                loOrderSpotsDao.insertLmkSpot(loLmkSpotsRowBean);
                System.out.println("---------------------------------------------");    
            }             
            loResponseUpdDao.setLiAffected(loSpots.getSpot().size());
            loResponseUpdDao.setLsMessage("OK");
            loResponseUpdDao.setLsResponse("OK");
            
        } catch (JAXBException e) {
            loResponseUpdDao.setLiAffected(0);
            loResponseUpdDao.setLsMessage(e.getMessage());
            loResponseUpdDao.setLsResponse("ERROR");
        }
        return loResponseUpdDao;
    }
    
    public LmkSpotsRowBean getLmkSpotsRowBeanBySpot(Spot toSpot){
        LmkSpotsRowBean loSpotsRowBean = new LmkSpotsRowBean();
        
        String lsScheduleDate = 
            toSpot.getScheduleDate() == null ? "null" : 
            toSpot.getScheduleDate();        
        
        String lsBreakNominalTime =            
            toSpot.getStartTime() == null ? "null" : 
            toSpot.getStartTime(); 
        
        Integer liBreakNumber = 
            toSpot.getBreakNumber() == null ? null : 
            Integer.parseInt(toSpot.getBreakNumber());
                
        Integer liSpotNumber =  
            toSpot.getSpotNumber() == null ? null : 
            Integer.parseInt(toSpot.getSpotNumber()); 
            
        String lsSpotDuration =             
            toSpot.getSpotDuration() == null ? "null" : 
            toSpot.getSpotDuration();      
         
        String lsProductCode =             
            toSpot.getProductKey() == null ? "null" : 
            toSpot.getProductKey();
        
        Integer liSequenceNumber =             
            toSpot.getSequenceNumber() == null ? null : 
            Integer.parseInt(toSpot.getSequenceNumber()); 
        
        Integer liMultiPartMember =            
            toSpot.getSequenceNumber() == null ? null : 
            Integer.parseInt(toSpot.getSequenceNumber()); 
        
        String lsCommercialTitle = 
            toSpot.getProductName() == null ? "null" : 
            toSpot.getProductName();
                
        String lsBreakType =  
            toSpot.getBreakType() == null ? "null" : 
            toSpot.getBreakType(); 
       
        String lsSecondary = 
            toSpot.getSecondary() == null ? "null" : 
            toSpot.getSecondary(); 
       
        String lsMobilityCode =          
            toSpot.getMobilityCode() == null ? "null" : 
            toSpot.getMobilityCode();
        
        String lsRootClassCode =             
            toSpot.getRootClashCode() == null ? "null" : 
            toSpot.getRootClashCode();
        
        String lsClashCode = 
            toSpot.getClashCode() == null ? "null" : 
            toSpot.getClashCode();
        
        String lsClashDescription =             
            toSpot.getClashDescription() == null ? "null" : 
            toSpot.getClashDescription();
        
        String lsNativeCommercialTitle = "null";
        
        String lsCloseCaption =          
            toSpot.getCloseCaption() == null ? "null" : 
            toSpot.getCloseCaption();
        
        String lsCopyType = "null";
        
        Integer liStartPrice =           
            toSpot.getStartPrice() == null ? null : 
            Integer.parseInt(toSpot.getStartPrice());
        
        Integer liPsdPrice =             
            toSpot.getPSDPrice() == null ? null : 
            Integer.parseInt(toSpot.getPSDPrice());
        
        String lsSpotType =  
            toSpot.getSpotSalesClassification() == null ? "null" : 
            toSpot.getSpotSalesClassification();    
        
        String lsAdvertiser =             
            toSpot.getAdvertiserName() == null ? "null" : 
            toSpot.getAdvertiserName();    
        
        Integer liCampaignNumber =            
            toSpot.getContractNumber() == null ? null : 
            Integer.parseInt(toSpot.getContractNumber());
        
        String lsBusinessTypeCode =             
            toSpot.getBusinessTypeCode() == null ? "null" : 
            toSpot.getBusinessTypeCode();
               
        String lsBonusSpot =             
            toSpot.getBonusSpot() == null ? "null" : 
            toSpot.getBonusSpot();    
        
        String lsAdvertiserId =           
            toSpot.getAdvertiserID() == null ? "null" : 
            toSpot.getAdvertiserID();
        
        String lsAgencyId =          
            toSpot.getAgencyID() == null ? "null" : 
            toSpot.getAgencyID();
        
        String lsAgencyName = 
            toSpot.getAgencyName() == null ? "null" : 
            toSpot.getAgencyName();
          
        String lsProgrammeName =             
            toSpot.getProgrammeName() == null ? "null" : 
            toSpot.getProgrammeName();
        
        String lsBookingPosition = null;
        
        String lsAdvid = null;        
            
        String lsAgyid = null;
        
        Integer liOrdid = null;
        
        Integer liOrdlnid = null;
        
        String lsStnid = null;
        
        String lsPgmid = null;
        
        Integer liBrkdtid = null;
        
        Integer liMstlogedtid = null;

        
        loSpotsRowBean.setLsScheduledDate(lsScheduleDate);
        loSpotsRowBean.setLsBreakNominalTime(lsBreakNominalTime);
        loSpotsRowBean.setLiBreakNumber(liBreakNumber);
        loSpotsRowBean.setLsBreakType(lsBreakType);
        loSpotsRowBean.setLsSecondary(lsSecondary);
        loSpotsRowBean.setLiSpotNumber(liSpotNumber);
        loSpotsRowBean.setLiCampaignNumber(liCampaignNumber);
        loSpotsRowBean.setLsSpotDuration(lsSpotDuration);
        loSpotsRowBean.setLsSpotType(lsSpotType);
        loSpotsRowBean.setLiSequenceNumber(liSequenceNumber);
        loSpotsRowBean.setLsProductCode(lsProductCode);
        loSpotsRowBean.setLsCommercialTitle(lsCommercialTitle);
        loSpotsRowBean.setLsBonusSpot(lsBonusSpot);
        loSpotsRowBean.setLsBusinessTypeCode(lsBusinessTypeCode);
        loSpotsRowBean.setLsRootClassCode(lsRootClassCode);
        loSpotsRowBean.setLsClashCode(lsClashCode);
        loSpotsRowBean.setLsClashDescription(lsClashDescription);
        loSpotsRowBean.setLsCloseCaption(lsCloseCaption);
        loSpotsRowBean.setLsMobilityCode(lsMobilityCode);
        loSpotsRowBean.setLiStartPrice(liStartPrice);
        loSpotsRowBean.setLiPsdPrice(liPsdPrice);
        loSpotsRowBean.setLsProgrammeName(lsProgrammeName);
        loSpotsRowBean.setLsAdvertiserId(lsAdvertiserId);
        loSpotsRowBean.setLsAdvertiser(lsAdvertiser);
        loSpotsRowBean.setLsAgencyId(lsAgencyId);
        loSpotsRowBean.setLsAgencyName(lsAgencyName);
        loSpotsRowBean.setLiMultiPartMember(liMultiPartMember);
        loSpotsRowBean.setLsBookingPosition(lsBookingPosition);
        loSpotsRowBean.setLsNativeCommercialTitle(lsNativeCommercialTitle);
        loSpotsRowBean.setLsCopyType(lsCopyType);
        loSpotsRowBean.setLsAdvid(lsAdvid);
        loSpotsRowBean.setLsAgyid(lsAgyid);
        loSpotsRowBean.setLiOrdid(liOrdid);
        loSpotsRowBean.setLiOrdlnid(liOrdlnid);
        loSpotsRowBean.setLsStnid(lsStnid);
        loSpotsRowBean.setLsPgmid(lsPgmid);
        loSpotsRowBean.setLiBrkdtid(liBrkdtid);
        loSpotsRowBean.setLiMstlogedtid(liMstlogedtid);
        return loSpotsRowBean;
    }
    
}
