
package org.datacontract.schemas._2004._07.landmark_parameters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfFilterDateTime complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfFilterDateTime">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FilterDateTime" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes}FilterDateTime" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFilterDateTime",
         namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes", propOrder = {
         "filterDateTime" })
public class ArrayOfFilterDateTime {

    @XmlElement(name = "FilterDateTime", nillable = true)
    protected List<FilterDateTime> filterDateTime;

    public void setFilterDateTime(List<FilterDateTime> filterDateTime) {
        this.filterDateTime = filterDateTime;
    }

    /**
     * Gets the value of the filterDateTime property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filterDateTime property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilterDateTime().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FilterDateTime }
     *
     *
     */
    public List<FilterDateTime> getFilterDateTime() {
        if (filterDateTime == null) {
            filterDateTime = new ArrayList<FilterDateTime>();
        }
        return this.filterDateTime;
    }

}
