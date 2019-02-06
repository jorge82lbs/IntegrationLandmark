 /**
 * Project: Integraton Paradigm - Landamrk
 *
 * File: ExecuteServiceResponseBean.java
 *
 * Created on: Enero 23, 2019 at 11:00
 *
 * Copyright (c) - OMW - 2019
 */
package mx.com.televisa.landamark.view.types;

/** Bean representativo de respuesta id-description<br/><br/>
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Enero 23, 2017, 12:00 pm
 */
public class ExecuteServiceResponseBean {
    private String lsColor;
    private String lsMessage;

    public void setLsColor(String lsColor) {
        this.lsColor = lsColor;
    }

    public String getLsColor() {
        return lsColor;
    }

    public void setLsMessage(String lsMessage) {
        this.lsMessage = lsMessage;
    }

    public String getLsMessage() {
        return lsMessage;
    }
}
