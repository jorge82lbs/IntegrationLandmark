
package org.datacontract.schemas._2004._07.landmark_parameters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSpotAmendParams complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfSpotAmendParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SpotAmendParams" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots}SpotAmendParams" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSpotAmendParams", propOrder = { "spotAmendParams" })
public class ArrayOfSpotAmendParams {

    @XmlElement(name = "SpotAmendParams", nillable = true)
    protected List<SpotAmendParams> spotAmendParams;

    /**
     * Gets the value of the spotAmendParams property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spotAmendParams property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpotAmendParams().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpotAmendParams }
     *
     *
     */
    public List<SpotAmendParams> getSpotAmendParams() {
        if (spotAmendParams == null) {
            spotAmendParams = new ArrayList<SpotAmendParams>();
        }
        return this.spotAmendParams;
    }

}
