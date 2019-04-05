package mx.com.televisa.landamark.util;

import java.util.List;

import mx.com.televisa.landamark.client.email.types.local.EmailDestinationAddress;
import mx.com.televisa.landamark.model.daos.EntityMappedDao;
import mx.com.televisa.landamark.model.daos.NotificationsDao;
import mx.com.televisa.landamark.model.types.LmkIntConfigParamRowBean;
import mx.com.televisa.landamark.model.types.LmkIntNotificationsRowBean;
import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;
import mx.com.televisa.landamark.model.types.ResponseUpdDao;
import mx.com.televisa.landamark.secman.MailManagement;
import mx.com.televisa.landamark.secman.SecurityManagerWs;

public class UtilMails {
    public UtilMails() {
        super();
    }
    
    public void buildMailByProcess(Integer tiRequest,
                                   Integer tiIdService, 
                                   Integer tiIdProcess,
                                   Integer tiIdUser,
                                   String tsUserName
                                  ){
        // Obtener Parametros de configuracion de notificaciones para el servicio
        NotificationsDao loNotificationsDao = new NotificationsDao();
        Integer liIndProcess = 0;
        String lsMessBit = "";
        String tsWhere = " ID_SERVICE = " + tiIdService;
        tsWhere += " AND IND_PROCESS = " + tiIdProcess;
        List<LmkIntNotificationsRowBean> laConfig = 
            loNotificationsDao.getLmkIntServicesParams(tsWhere);
        if(laConfig.size()>0){// Validar que existan
            // Obtener destintarios ene base al grupo de usuarios    
            if(laConfig.get(0).getLsIndUsersGroup() != null){
                SecurityManagerWs loSecman = new SecurityManagerWs();
                List<EmailDestinationAddress> laTo = 
                    loSecman.getListEmailAddressByGroup(laConfig.get(0).getLsIndUsersGroup());
                
                if(laTo.size() > 0){
                    //Obtener la descripcion del idProcess
                    EntityMappedDao loEntityMappedDao = new EntityMappedDao();
                    List<LmkIntConfigParamRowBean> loPrcss = 
                        loEntityMappedDao.getParametersById(tiIdProcess);
                    
                    MailManagement loMail = new MailManagement();
                    ResponseUpdDao loResMail = 
                        loMail.sendSimpleEmail(laConfig.get(0).getLsIndSubject(), 
                                               laConfig.get(0).getLsIndMessage(), 
                                               loPrcss.get(0).getLsDescParameter(), 
                                               laTo);
                    if(loResMail.getLsResponse().equalsIgnoreCase("OK") ){
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("sendMail");
                        lsMessBit = "Correo enviado satisfactoriamente";
                    }else{
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("GeneralError");
                        lsMessBit = "Correo NO enviado "+loResMail.getLsMessage();
                        System.out.println("Insertar en bitacora que el correo no fue enviado");
                    }
                }else{
                    liIndProcess = 
                                new UtilFaces().getIdConfigParameterByName("GeneralError");
                    lsMessBit = "Correo NO enviado el grupo no contiene destinatarios";
                    System.out.println("Insertar en bitacora que el grupo no contiene destinatarios");
                }
            }else{
                liIndProcess = 
                            new UtilFaces().getIdConfigParameterByName("GeneralError");
                lsMessBit = "Correo NO enviado el grupo no existe grupo configurado";
                System.out.println("Insertar en bitacora que no existe grupo configurado");
            }
            
        }else{
            liIndProcess = 
                        new UtilFaces().getIdConfigParameterByName("GeneralError");
            lsMessBit = "Correo NO enviado el grupo no existe Configuracion de Notificaciones";
            System.out.println("Insertar en bitacora que no existe la configuracion");
            //Insertar en bitacora que no existe la configuracion
        }
        
        System.out.println("Insertar en bitacora de alta escuela");
        LmkIntServiceBitacoraRowBean loBitBean = new LmkIntServiceBitacoraRowBean();
        EntityMappedDao           loEntityMappedDao = new EntityMappedDao();
        
                loBitBean.setLiIdLogServices(tiRequest);
                loBitBean.setLiIdService(tiIdService);
                loBitBean.setLiIndProcess(liIndProcess);
                loBitBean.setLiNumProcessId(0);
                loBitBean.setLiNumPgmProcessId(0);
                loBitBean.setLsIndEvento(lsMessBit);
        loEntityMappedDao.insertBitacoraWs(loBitBean,
                                           tiIdUser, 
                                           tsUserName);
        
        // Validar que al menos sea uno
        // Validar con expresion regular que sea un correo (esto no, ya debe estar validado)
        // Enviar mail considerando:
        // * Asunto
        // * Cuerpo del mensaje (algun resultset para tabla)
        // * Destinatarios
        // Enviar correo, invocando secman-controller
        
    }
}
