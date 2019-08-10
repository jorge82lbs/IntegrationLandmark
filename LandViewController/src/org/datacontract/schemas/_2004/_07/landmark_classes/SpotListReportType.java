
package org.datacontract.schemas._2004._07.landmark_classes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SpotListReportType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SpotListReportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Selection" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SelectionText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpotListReportType", propOrder = { "selection", "selectionText" })
public class SpotListReportType {

    @XmlElement(name = "Selection")
    protected Integer selection;
    @XmlElementRef(name = "SelectionText", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> selectionText;

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
     * Gets the value of the selectionText property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getSelectionText() {
        return selectionText;
    }

    /**
     * Sets the value of the selectionText property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setSelectionText(JAXBElement<String> value) {
        this.selectionText = value;
    }

}
