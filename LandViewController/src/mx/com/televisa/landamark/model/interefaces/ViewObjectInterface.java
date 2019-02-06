/**
* Project: Paradigm - eVeTV Integration
*
* File: ViewObjectInterface.java
*
* Created on:  Enero 26, 2019 at 11:00
*
* Copyright (c) - Omw - 2019
*/
package mx.com.televisa.landamark.model.interefaces;

import java.util.List;

import mx.com.televisa.landamark.view.types.SelectOneItemBean;


/** Interfaz para implemetarse en conexion a la base de datos
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Enero 24, 2019, 12:00 pm
 */
public interface ViewObjectInterface {
    public Integer getMaxIdServicesCatalog();
    public String getQueryMaxServicesCatalog();
    public Integer getMaxIdParadigm(String psTable);
    public String getQueryMaxParadigm(String psTable, String psField);
    public String getIdServiceByNomService(String psNomService);
    public String getProcessIdByNomParameter(String psNomParameter);
    public String getUsersGroupByDescParameter(String psDescParameter);
    public List<SelectOneItemBean> getListAllWebServicesModel();
    public String getQueryAllWebServices();
    public List<SelectOneItemBean> getListGeneralParametersModelFilter(String psArgs);
    public String getQueryGeneralParameters(String psArgs);
}