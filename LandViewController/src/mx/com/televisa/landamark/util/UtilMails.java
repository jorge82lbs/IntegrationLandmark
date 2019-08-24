/**
* Project: Paradigm - Landmark 
*
* File: UtilMails.java
*
* Created on:  Enero 09, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/
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

/** Esta clase ejecuta procesos de envío de correo<br/><br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Enero 09, 2019, 12:00 pm
 */
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
                
                //-----------------------------------------------------------------------
                /*System.out.println("Por lo pronto mandar correo solo a jlbautistas");
                laTo.clear();
                EmailDestinationAddress loJL = new EmailDestinationAddress();
                loJL.setLsAddressTo("jlbautistas@televisa.com.mx");
                loJL.setLsNameTo("jlbautistas@televisa.com.mx");
                laTo.add(loJL);*/
                //-----------------------------------------------------------------------
                
                if(laTo.size() > 0){
                    System.out.println("Si existen destinatarios");
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
                                    new UtilFaces().getIdConfigParameterByName("SendEmail");
                        lsMessBit = "Correo enviado satisfactoriamente";
                    }else{
                        liIndProcess = 
                                    new UtilFaces().getIdConfigParameterByName("FailSendEmail");
                        lsMessBit = "Correo NO enviado "+loResMail.getLsMessage();
                    }
                }else{
                    liIndProcess = 
                                new UtilFaces().getIdConfigParameterByName("MissingAddressee");
                    lsMessBit = "Correo NO enviado el grupo no contiene destinatarios";
                }
            }else{
                liIndProcess = 
                            new UtilFaces().getIdConfigParameterByName("ErrorUserGroup");
                lsMessBit = "Correo NO enviado el grupo no existe grupo configurado";
            }
            
        }else{
            liIndProcess = 
                        new UtilFaces().getIdConfigParameterByName("ErrorConfig");
            lsMessBit = "Correo NO enviado el grupo no existe Configuracion de Notificaciones";
        }
        
        System.out.println("Insertar en bitacora ERROR al enviar email");
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
