
package org.datacontract.schemas._2004._07.landmark_classes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfInteractivite complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfInteractivite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Interactivite" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}Interactivite" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfInteractivite", propOrder = { "interactivite" })
public class ArrayOfInteractivite {

    @XmlElement(name = "Interactivite", nillable = true)
    protected List<Interactivite> interactivite;

    /**
     * Gets the value of the interactivite property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interactivite property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInteractivite().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Interactivite }
     *
     *
     */
    public List<Interactivite> getInteractivite() {
        if (interactivite == null) {
            interactivite = new ArrayList<Interactivite>();
        }
        return this.interactivite;
    }

}
