
package org.datacontract.schemas._2004._07.landmark_classes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CopySelectionName complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CopySelectionName">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Selection" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SelectionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CopySelectionName", propOrder = { "selection", "selectionName" })
public class CopySelectionName {

    @XmlElement(name = "Selection")
    protected Integer selection;
    @XmlElementRef(name = "SelectionName", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> selectionName;

    /**
     * Gets the value of the selection property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSelection() {
        return selection;
    }

    /**
     * Sets the value of the selection property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSelection(Integer value) {
        this.selection = value;
    }

    /**
     * Gets the value of the selectionName property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getSelectionName() {
        return selectionName;
    }

    /**
     * Sets the value of the selectionName property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setSelectionName(JAXBElement<String> value) {
        this.selectionName = value;
    }

}
