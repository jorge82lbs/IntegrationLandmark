
package org.datacontract.schemas._2004._07.landmark_classes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BookingMessage complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BookingMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MsgNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MsgText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Override" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
@XmlType(name = "BookingMessage", propOrder = { "msgNo", "msgText", "override", "rowId" })
public class BookingMessage {

    @XmlElement(name = "MsgNo")
    protected Integer msgNo;
    @XmlElementRef(name = "MsgText", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Classes.Spots",
                   type = JAXBElement.class, required = false)
    protected JAXBElement<String> msgText;
    @XmlElement(name = "Override")
    protected Boolean override;
    @XmlElement(name = "RowId")
    protected Integer rowId;

    /**
     * Gets the value of the msgNo property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getMsgNo() {
        return msgNo;
    }

    /**
     * Sets the value of the msgNo property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setMsgNo(Integer value) {
        this.msgNo = value;
    }

    /**
     * Gets the value of the msgText property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getMsgText() {
        return msgText;
    }

    /**
     * Sets the value of the msgText property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setMsgText(JAXBElement<String> value) {
        this.msgText = value;
    }

    /**
     * Gets the value of the override property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isOverride() {
        return override;
    }

    /**
     * Sets the value of the override property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setOverride(Boolean value) {
        this.override = value;
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
