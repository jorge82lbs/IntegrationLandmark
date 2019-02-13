
package mx.com.televisa.landamark.services.beans.rsbreakload;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChannelHeaderRecord complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ChannelHeaderRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordType" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="RegionalSalesAreaCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SalesAreaCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BreakScheduleDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChannelHeaderRecord", propOrder = {
         "recordType", "regionalSalesAreaCode", "salesAreaCode", "id", "breakScheduleDate" })
public class ChannelHeaderRecord {

    @XmlElement(name = "RecordType", required = true)
    protected BigInteger recordType;
    @XmlElement(name = "RegionalSalesAreaCode", required = true)
    protected String regionalSalesAreaCode;
    @XmlElement(name = "SalesAreaCode", required = true)
    protected String salesAreaCode;
    @XmlElement(name = "Id", required = true)
    protected String id;
    @XmlElement(name = "BreakScheduleDate", required = true)
    protected String breakScheduleDate;

    /**
     * Gets the value of the recordType property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getRecordType() {
        return recordType;
    }

    /**
     * Sets the value of the recordType property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setRecordType(BigInteger value) {
        this.recordType = value;
    }

    /**
     * Gets the value of the regionalSalesAreaCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRegionalSalesAreaCode() {
        return regionalSalesAreaCode;
    }

    /**
     * Sets the value of the regionalSalesAreaCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRegionalSalesAreaCode(String value) {
        this.regionalSalesAreaCode = value;
    }

    /**
     * Gets the value of the salesAreaCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSalesAreaCode() {
        return salesAreaCode;
    }

    /**
     * Sets the value of the salesAreaCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSalesAreaCode(String value) {
        this.salesAreaCode = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the breakScheduleDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBreakScheduleDate() {
        return breakScheduleDate;
    }

    /**
     * Sets the value of the breakScheduleDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBreakScheduleDate(String value) {
        this.breakScheduleDate = value;
    }

}
