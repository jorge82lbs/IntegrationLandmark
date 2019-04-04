/**
* Project: Integraton Paradigm - Landmark
*
* File: Spots.java
*
* Created on: Febrero 23, 2019 at 11:00
*
* Copyright (c) - OMW - 2019
*/
package mx.com.televisa.landamark.services.beans.input.spots;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** Bean que contiene todos los spsots de mapeo 
 *
 * @author Jorge Luis Bautista Santiago - OMW
 *
 * @version 01.00.01
 *
 * @date Febrero 23, 2019, 12:00 pm
 */

@XmlRootElement(name="Spots")
public class Spots {
    public Spots() {
        super();
    }
    
    @XmlElement(name = "Spot")
    private List<Spot> Spot;

    public void setSpot(List<Spot> Spot) {
        this.Spot = Spot;
    }

    public List<Spot> getSpot() {
        return Spot;
    }

}
