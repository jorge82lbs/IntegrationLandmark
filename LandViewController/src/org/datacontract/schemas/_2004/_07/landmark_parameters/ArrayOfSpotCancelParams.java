
package org.datacontract.schemas._2004._07.landmark_parameters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSpotCancelParams complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfSpotCancelParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SpotCancelParams" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots}SpotCancelParams" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSpotCancelParams", propOrder = { "spotCancelParams" })
public class ArrayOfSpotCancelParams {

    @XmlElement(name = "SpotCancelParams", nillable = true)
    protected List<SpotCancelParams> spotCancelParams;

    /**
     * Gets the value of the spotCancelParams property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spotCancelParams property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpotCancelParams().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpotCancelParams }
     *
     *
     */
    public List<SpotCancelParams> getSpotCancelParams() {
        if (spotCancelParams == null) {
            spotCancelParams = new ArrayList<SpotCancelParams>();
        }
        return this.spotCancelParams;
    }

}
