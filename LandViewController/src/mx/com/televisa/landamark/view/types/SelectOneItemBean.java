/**
* Project: Paradigm - Landmark Integration
*
* File: SelectOneItemBean.java
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
public class SelectOneItemBean {
    private String lsId;
    private String lsValue;
    private String lsDescription;

    public void setLsId(String lsId) {
        this.lsId = lsId;
    }

    public String getLsId() {
        return lsId;
    }

    public void setLsValue(String lsValue) {
        this.lsValue = lsValue;
    }

    public String getLsValue() {
        return lsValue;
    }

    public void setLsDescription(String lsDescription) {
        this.lsDescription = lsDescription;
    }

    public String getLsDescription() {
        return lsDescription;
    }

}
