
package org.datacontract.schemas._2004._07.landmark_classes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBookProcessType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfBookProcessType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BookProcessType" type="{http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots}BookProcessType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBookProcessType", propOrder = { "bookProcessType" })
public class ArrayOfBookProcessType {

    @XmlElement(name = "BookProcessType", nillable = true)
    protected List<BookProcessType> bookProcessType;

    /**
     * Gets the value of the bookProcessType property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bookProcessType property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBookProcessType().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BookProcessType }
     *
     *
     */
    public List<BookProcessType> getBookProcessType() {
        if (bookProcessType == null) {
            bookProcessType = new ArrayList<BookProcessType>();
        }
        return this.bookProcessType;
    }

}
