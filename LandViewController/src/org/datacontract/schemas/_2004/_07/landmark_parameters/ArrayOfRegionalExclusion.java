
package org.datacontract.schemas._2004._07.landmark_parameters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRegionalExclusion complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfRegionalExclusion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegionalExclusion" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams}RegionalExclusion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRegionalExclusion",
         namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams", propOrder = {
         "regionalExclusion" })
public class ArrayOfRegionalExclusion {

    @XmlElement(name = "RegionalExclusion", nillable = true)
    protected List<RegionalExclusion> regionalExclusion;

    /**
     * Gets the value of the regionalExclusion property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the regionalExclusion property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegionalExclusion().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegionalExclusion }
     *
     *
     */
    public List<RegionalExclusion> getRegionalExclusion() {
        if (regionalExclusion == null) {
            regionalExclusion = new ArrayList<RegionalExclusion>();
        }
        return this.regionalExclusion;
    }

}
