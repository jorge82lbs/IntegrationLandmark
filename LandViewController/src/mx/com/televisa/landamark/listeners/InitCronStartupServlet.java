/**
* Project: Paradigm - Landmark
*
* File: InitCronStartupServlet.java
*
* Created on:  Septiembre 6, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/
package mx.com.televisa.landamark.listeners;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import mx.com.televisa.landamark.model.daos.EntityMappedDao;

import mx.com.televisa.landamark.model.types.LmkIntServiceBitacoraRowBean;

import org.quartz.ee.servlet.QuartzInitializerListener;

/** Esta clase inicializa las crones activos despues de un reinicio del servidor <br/><br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Septiembre 14, 2019, 12:00 pm
 */

@WebListener
public class InitCronStartupServlet extends QuartzInitializerListener{
    public InitCronStartupServlet() {
        super();
    }
    
    /**
     * Incializa el contxto del servlet
     * @autor Jorge Luis Bautista Santiago  
     * @param loExpression
     * @return Object
     */
    @Override
    public void contextInitialized(ServletContextEvent loSce) {
        super.contextInitialized(loSce);
        System.out.println(">>> Actualizando estatus de crones a inactivos, solo los que tienen estatus Activo [2]");
        EntityMappedDao loEntityMappedDao = new EntityMappedDao();
        Integer liRes = loEntityMappedDao.disableInitializedCron();
        if(liRes > 0){
            System.out.println(">>> Se han deshabilitado crones activos por reinicio - estatus Deshabilitado [4]");
            LmkIntServiceBitacoraRowBean loEvetvIntServiceBitacoraTab = new LmkIntServiceBitacoraRowBean();
            loEvetvIntServiceBitacoraTab.setLiIdLogServices(0);
            loEvetvIntServiceBitacoraTab.setLiIdService(0);
            loEvetvIntServiceBitacoraTab.setLiIndProcess(0);
            loEvetvIntServiceBitacoraTab.setLiNumProcessId(0);
            loEvetvIntServiceBitacoraTab.setLiNumPgmProcessId(0);
            loEvetvIntServiceBitacoraTab.setLsIndEvento("Se han deshabilitado crones activos por reinicio");
            loEntityMappedDao.insertBitacoraWs(loEvetvIntServiceBitacoraTab,0, "Server");
        }
        
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        super.contextDestroyed(sce);
    }        
    
    public Date getDateYyyyMmDd(Date ttDate){
        String           lsCurrDate = "";
        SimpleDateFormat lodfCurrent = new SimpleDateFormat("yyyy-MM-dd");
        lsCurrDate = lodfCurrent.format(ttDate);
        SimpleDateFormat lodf = new SimpleDateFormat("yyyy-MM-dd");
        Date             ltFechaReturn = new Date();
        try {
            ltFechaReturn = lodf.parse(lsCurrDate);
        } catch (ParseException e) {
            System.out.println("Error al parsear: "+e.getMessage());
            e.printStackTrace();
        }
        return ltFechaReturn;
    }
    
}
