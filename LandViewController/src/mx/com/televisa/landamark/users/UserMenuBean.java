/**
* Project: Paradigm - Landmark
*
* File: UserInfoBean.java
*
* Created on:  Enero 09, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/

package mx.com.televisa.landamark.users;

/** Esta clase es un bean para almacenar los datos informativos del usuario en 
  * sesion <br/><br/>  
  * 
  * @author Jorge Luis Bautista - Omw
  * 
  * @version 01.00.01
  * 
  * @date Enero 09, 2019, 12:00 pm
  */
public class UserMenuBean {
    public UserMenuBean() {
        super();
    }
    
    private String lsPantallaBitacora;
    private String lsPantallaGralConfig;
    private String lsPantallaLoadFile;
    private String lsPantallaMapping;
    private String lsPantallaMonitor;
    private String lsPantallaNotifications;
    private String lsPantallaProcess;
    private String lsPantallaStatusFiles;
    private String lsOprInsertCron;
    private String lsOprDeleteCron;
    private String lsOprExecuteCron;
    private String lsOprInitStopCron;


    public void setLsPantallaBitacora(String lsPantallaBitacora) {
        this.lsPantallaBitacora = lsPantallaBitacora;
    }

    public String getLsPantallaBitacora() {
        return lsPantallaBitacora;
    }

    public void setLsPantallaGralConfig(String lsPantallaGralConfig) {
        this.lsPantallaGralConfig = lsPantallaGralConfig;
    }

    public String getLsPantallaGralConfig() {
        return lsPantallaGralConfig;
    }

    public void setLsPantallaLoadFile(String lsPantallaLoadFile) {
        this.lsPantallaLoadFile = lsPantallaLoadFile;
    }

    public String getLsPantallaLoadFile() {
        return lsPantallaLoadFile;
    }

    public void setLsPantallaMapping(String lsPantallaMapping) {
        this.lsPantallaMapping = lsPantallaMapping;
    }

    public String getLsPantallaMapping() {
        return lsPantallaMapping;
    }

    public void setLsPantallaMonitor(String lsPantallaMonitor) {
        this.lsPantallaMonitor = lsPantallaMonitor;
    }

    public String getLsPantallaMonitor() {
        return lsPantallaMonitor;
    }

    public void setLsPantallaNotifications(String lsPantallaNotifications) {
        this.lsPantallaNotifications = lsPantallaNotifications;
    }

    public String getLsPantallaNotifications() {
        return lsPantallaNotifications;
    }

    public void setLsPantallaProcess(String lsPantallaProcess) {
        this.lsPantallaProcess = lsPantallaProcess;
    }

    public String getLsPantallaProcess() {
        return lsPantallaProcess;
    }

    public void setLsPantallaStatusFiles(String lsPantallaStatusFiles) {
        this.lsPantallaStatusFiles = lsPantallaStatusFiles;
    }

    public String getLsPantallaStatusFiles() {
        return lsPantallaStatusFiles;
    }

    public void setLsOprInsertCron(String lsOprInsertCron) {
        this.lsOprInsertCron = lsOprInsertCron;
    }

    public String getLsOprInsertCron() {
        return lsOprInsertCron;
    }

    public void setLsOprDeleteCron(String lsOprDeleteCron) {
        this.lsOprDeleteCron = lsOprDeleteCron;
    }

    public String getLsOprDeleteCron() {
        return lsOprDeleteCron;
    }

    public void setLsOprExecuteCron(String lsOprExecuteCron) {
        this.lsOprExecuteCron = lsOprExecuteCron;
    }

    public String getLsOprExecuteCron() {
        return lsOprExecuteCron;
    }

    public void setLsOprInitStopCron(String lsOprInitStopCron) {
        this.lsOprInitStopCron = lsOprInitStopCron;
    }

    public String getLsOprInitStopCron() {
        return lsOprInitStopCron;
    }


}
