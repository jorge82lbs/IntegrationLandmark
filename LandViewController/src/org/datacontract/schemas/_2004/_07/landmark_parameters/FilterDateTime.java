
package org.datacontract.schemas._2004._07.landmark_parameters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for FilterDateTime complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FilterDateTime">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DaysOfWeek" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes}DaysOfWeek" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="EndTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterDateTime", namespace = "http://schemas.datacontract.org/2004/07/Landmark.Parameters.CoreTypes",
         propOrder = { "daysOfWeek", "endDate", "endTime", "startDate", "startTime" })
public class FilterDateTime {

    @XmlElement(name = "DaysOfWeek")
    @XmlSchemaType(name = "string")
    protected DaysOfWeek daysOfWeek;
    @XmlElement(name = "EndDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "EndTime")
    protected Integer endTime;
    @XmlElement(name = "StartDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "StartTime")
    protected Integer startTime;

    /**
     * Gets the value of the daysOfWeek property.
     *
     * @return
     *     possible object is
     *     {@link DaysOfWeek }
     *
     */
    public DaysOfWeek getDaysOfWeek() {
        return daysOfWeek;
    }

    /**
     * Sets the value of the daysOfWeek property.
     *
     * @param value
     *     allowed object is
     *     {@link DaysOfWeek }
     *
     */
    public void setDaysOfWeek(DaysOfWeek value) {
        this.daysOfWeek = value;
    }

    /**
     * Gets the value of the endDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the endTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setEndTime(Integer value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the startDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the startTime property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setStartTime(Integer value) {
        this.startTime = value;
    }

}
