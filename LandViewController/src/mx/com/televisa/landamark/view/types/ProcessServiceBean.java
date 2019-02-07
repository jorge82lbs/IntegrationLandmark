/**
* Project: Paradigm - Landmark Integration
*
* File: ProcessServiceBean.java
*
* Created on:  Febrero 6, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/
package mx.com.televisa.landamark.view.types;


/** Esta clase es un bean que representa un lista de valores<br/><br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Febrero 14, 2019, 12:00 pm
 */
public class ProcessServiceBean {
    public ProcessServiceBean() {
        super();
    }
    
    private Integer liIdUser;
    private String lsUserName;
    private String lsIdService;
    private String lsServiceToInvoke;
    private String lsServiceAction;
    private String lsIdTrigger;
    private String lsServiceName;
    private String lsTypeProcess;

    public void setLsServiceName(String lsServiceName) {
        this.lsServiceName = lsServiceName;
    }

    public String getLsServiceName() {
        return lsServiceName;
    }

    public void setLsTypeProcess(String lsTypeProcess) {
        this.lsTypeProcess = lsTypeProcess;
    }

    public String getLsTypeProcess() {
        return lsTypeProcess;
    }

    public void setLsIdTrigger(String lsIdTrigger) {
        this.lsIdTrigger = lsIdTrigger;
    }

    public String getLsIdTrigger() {
        return lsIdTrigger;
    }

    public void setLiIdUser(Integer liIdUser) {
        this.liIdUser = liIdUser;
    }

    public Integer getLiIdUser() {
        return liIdUser;
    }

    public void setLsUserName(String lsUserName) {
        this.lsUserName = lsUserName;
    }

    public String getLsUserName() {
        return lsUserName;
    }

    public void setLsIdService(String lsIdService) {
        this.lsIdService = lsIdService;
    }

    public String getLsIdService() {
        return lsIdService;
    }

    public void setLsServiceToInvoke(String lsServiceToInvoke) {
        this.lsServiceToInvoke = lsServiceToInvoke;
    }

    public String getLsServiceToInvoke() {
        return lsServiceToInvoke;
    }

    public void setLsServiceAction(String lsServiceAction) {
        this.lsServiceAction = lsServiceAction;
    }

    public String getLsServiceAction() {
        return lsServiceAction;
    }

}
