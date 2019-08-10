
package org.datacontract.schemas._2004._07.landmark_classes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCopySelectionName complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfCopySelectionName">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CopySelectionName" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}CopySelectionName" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCopySelectionName", propOrder = { "copySelectionName" })
public class ArrayOfCopySelectionName {

    @XmlElement(name = "CopySelectionName", nillable = true)
    protected List<CopySelectionName> copySelectionName;

    /**
     * Gets the value of the copySelectionName property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the copySelectionName property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCopySelectionName().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CopySelectionName }
     *
     *
     */
    public List<CopySelectionName> getCopySelectionName() {
        if (copySelectionName == null) {
            copySelectionName = new ArrayList<CopySelectionName>();
        }
        return this.copySelectionName;
    }

}
