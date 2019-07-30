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
    private String lsPantallaPrecios;
    
    private String lsUserIdServiceCortes;
    private String lsUserNomServiceCortes;
    private String lsUserFecInicialCortes;
    private String lsUserFecFinalCortes;
    private String lsUserListChannelsCortes;
    
    private String lsUserIdServicePrecios;
    private String lsUserNomServicePrecios;
    private String lsUserFecInicialPrecios;
    private String lsUserFecFinalPrecios;
    private String lsUserListChannelsPrecios;
    
    private String lsOprInsertCron;
    private String lsOprDeleteCron;
    private String lsOprExecuteCron;
    private String lsOprInitStopCron;

    private String lsPantallaCreateFile;

    public void setLsPantallaPrecios(String lsPantallaPrecios) {
        this.lsPantallaPrecios = lsPantallaPrecios;
    }

    public String getLsPantallaPrecios() {
        return lsPantallaPrecios;
    }

    public void setLsUserIdServicePrecios(String lsUserIdServicePrecios) {
        this.lsUserIdServicePrecios = lsUserIdServicePrecios;
    }

    public String getLsUserIdServicePrecios() {
        return lsUserIdServicePrecios;
    }

    public void setLsUserNomServicePrecios(String lsUserNomServicePrecios) {
        this.lsUserNomServicePrecios = lsUserNomServicePrecios;
    }

    public String getLsUserNomServicePrecios() {
        return lsUserNomServicePrecios;
    }

    public void setLsUserFecInicialPrecios(String lsUserFecInicialPrecios) {
        this.lsUserFecInicialPrecios = lsUserFecInicialPrecios;
    }

    public String getLsUserFecInicialPrecios() {
        return lsUserFecInicialPrecios;
    }

    public void setLsUserFecFinalPrecios(String lsUserFecFinalPrecios) {
        this.lsUserFecFinalPrecios = lsUserFecFinalPrecios;
    }

    public String getLsUserFecFinalPrecios() {
        return lsUserFecFinalPrecios;
    }

    public void setLsUserListChannelsPrecios(String lsUserListChannelsPrecios) {
        this.lsUserListChannelsPrecios = lsUserListChannelsPrecios;
    }

    public String getLsUserListChannelsPrecios() {
        return lsUserListChannelsPrecios;
    }

    public void setLsUserListChannelsCortes(String lsUserListChannelsCortes) {
        this.lsUserListChannelsCortes = lsUserListChannelsCortes;
    }

    public String getLsUserListChannelsCortes() {
        return lsUserListChannelsCortes;
    }

    public void setLsUserIdServiceCortes(String lsUserIdServiceCortes) {
        this.lsUserIdServiceCortes = lsUserIdServiceCortes;
    }

    public String getLsUserIdServiceCortes() {
        return lsUserIdServiceCortes;
    }

    public void setLsUserNomServiceCortes(String lsUserNomServiceCortes) {
        this.lsUserNomServiceCortes = lsUserNomServiceCortes;
    }

    public String getLsUserNomServiceCortes() {
        return lsUserNomServiceCortes;
    }

    public void setLsUserFecInicialCortes(String lsUserFecInicialCortes) {
        this.lsUserFecInicialCortes = lsUserFecInicialCortes;
    }

    public String getLsUserFecInicialCortes() {
        return lsUserFecInicialCortes;
    }

    public void setLsUserFecFinalCortes(String lsUserFecFinalCortes) {
        this.lsUserFecFinalCortes = lsUserFecFinalCortes;
    }

    public String getLsUserFecFinalCortes() {
        return lsUserFecFinalCortes;
    }

    public void setLsPantallaCreateFile(String lsPantallaCreateFile) {
        this.lsPantallaCreateFile = lsPantallaCreateFile;
    }

    public String getLsPantallaCreateFile() {
        return lsPantallaCreateFile;
    }

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
