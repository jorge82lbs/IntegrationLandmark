
package org.datacontract.schemas._2004._07.landmark_classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BookingResult complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BookingResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="RowId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookingResult", propOrder = { "result", "rowId" })
public class BookingResult {

    @XmlElement(name = "Result")
    protected Integer result;
    @XmlElement(name = "RowId")
    protected Integer rowId;

    /**
     * Gets the value of the result property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setResult(Integer value) {
        this.result = value;
    }

    /**
     * Gets the value of the rowId property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getRowId() {
        return rowId;
    }

    /**
     * Sets the value of the rowId property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setRowId(Integer value) {
        this.rowId = value;
    }

}
