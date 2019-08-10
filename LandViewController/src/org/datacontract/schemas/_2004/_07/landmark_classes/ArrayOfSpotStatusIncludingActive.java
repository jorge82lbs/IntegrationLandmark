
package org.datacontract.schemas._2004._07.landmark_classes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSpotStatusIncludingActive complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfSpotStatusIncludingActive">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SpotStatusIncludingActive" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}SpotStatusIncludingActive" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSpotStatusIncludingActive", propOrder = { "spotStatusIncludingActive" })
public class ArrayOfSpotStatusIncludingActive {

    @XmlElement(name = "SpotStatusIncludingActive", nillable = true)
    protected List<SpotStatusIncludingActive> spotStatusIncludingActive;

    /**
     * Gets the value of the spotStatusIncludingActive property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spotStatusIncludingActive property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpotStatusIncludingActive().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpotStatusIncludingActive }
     *
     *
     */
    public List<SpotStatusIncludingActive> getSpotStatusIncludingActive() {
        if (spotStatusIncludingActive == null) {
            spotStatusIncludingActive = new ArrayList<SpotStatusIncludingActive>();
        }
        return this.spotStatusIncludingActive;
    }

}
