
package org.datacontract.schemas._2004._07.landmark_classes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPositionInProgrammeName complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfPositionInProgrammeName">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PositionInProgrammeName" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}PositionInProgrammeName" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPositionInProgrammeName", propOrder = { "positionInProgrammeName" })
public class ArrayOfPositionInProgrammeName {

    @XmlElement(name = "PositionInProgrammeName", nillable = true)
    protected List<PositionInProgrammeName> positionInProgrammeName;

    /**
     * Gets the value of the positionInProgrammeName property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the positionInProgrammeName property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPositionInProgrammeName().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PositionInProgrammeName }
     *
     *
     */
    public List<PositionInProgrammeName> getPositionInProgrammeName() {
        if (positionInProgrammeName == null) {
            positionInProgrammeName = new ArrayList<PositionInProgrammeName>();
        }
        return this.positionInProgrammeName;
    }

}
