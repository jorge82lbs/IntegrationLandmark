
package org.datacontract.schemas._2004._07.landmark_parameters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMultiPart complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfMultiPart">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MultiPart" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams}MultiPart" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMultiPart", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.APIParams",
         propOrder = { "multiPart" })
public class ArrayOfMultiPart {

    @XmlElement(name = "MultiPart", nillable = true)
    protected List<MultiPart> multiPart;

    /**
     * Gets the value of the multiPart property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the multiPart property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMultiPart().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MultiPart }
     *
     *
     */
    public List<MultiPart> getMultiPart() {
        if (multiPart == null) {
            multiPart = new ArrayList<MultiPart>();
        }
        return this.multiPart;
    }

}
