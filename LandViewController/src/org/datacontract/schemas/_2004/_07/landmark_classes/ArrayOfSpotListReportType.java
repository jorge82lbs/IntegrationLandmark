
package org.datacontract.schemas._2004._07.landmark_classes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSpotListReportType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfSpotListReportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SpotListReportType" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}SpotListReportType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSpotListReportType", propOrder = { "spotListReportType" })
public class ArrayOfSpotListReportType {

    @XmlElement(name = "SpotListReportType", nillable = true)
    protected List<SpotListReportType> spotListReportType;

    /**
     * Gets the value of the spotListReportType property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spotListReportType property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpotListReportType().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpotListReportType }
     *
     *
     */
    public List<SpotListReportType> getSpotListReportType() {
        if (spotListReportType == null) {
            spotListReportType = new ArrayList<SpotListReportType>();
        }
        return this.spotListReportType;
    }

}
