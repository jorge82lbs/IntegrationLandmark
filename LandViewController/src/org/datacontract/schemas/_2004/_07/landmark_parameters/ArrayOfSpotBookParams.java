
package org.datacontract.schemas._2004._07.landmark_parameters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSpotBookParams complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfSpotBookParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SpotBookParams" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots}SpotBookParams" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSpotBookParams", propOrder = { "spotBookParams" })
public class ArrayOfSpotBookParams {

    @XmlElement(name = "SpotBookParams", nillable = true)
    protected List<SpotBookParams> spotBookParams;

    /**
     * Gets the value of the spotBookParams property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spotBookParams property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpotBookParams().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpotBookParams }
     *
     *
     */
    public List<SpotBookParams> getSpotBookParams() {
        if (spotBookParams == null) {
            spotBookParams = new ArrayList<SpotBookParams>();
        }
        return this.spotBookParams;
    }

}
