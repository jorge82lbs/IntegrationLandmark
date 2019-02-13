
package mx.com.televisa.landamark.services.beans.rsbreakload;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BreakRecord complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BreakRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordType" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="RegionalSalesAreaCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SalesAreaCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BreakScheduleDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BreakNominalTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BreakDuration" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BreakTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PositioninProgramme" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BreakNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BreakRecord", propOrder = {
         "recordType", "regionalSalesAreaCode", "salesAreaCode", "breakScheduleDate", "breakNominalTime",
         "breakDuration", "breakTypeCode", "positioninProgramme", "breakNumber"
    })
public class BreakRecord {

    @XmlElement(name = "RecordType", required = true)
    protected BigInteger recordType;
    @XmlElement(name = "RegionalSalesAreaCode", required = true)
    protected String regionalSalesAreaCode;
    @XmlElement(name = "SalesAreaCode", required = true)
    protected String salesAreaCode;
    @XmlElement(name = "BreakScheduleDate", required = true)
    protected String breakScheduleDate;
    @XmlElement(name = "BreakNominalTime", required = true)
    protected String breakNominalTime;
    @XmlElement(name = "BreakDuration", required = true)
    protected String breakDuration;
    @XmlElement(name = "BreakTypeCode", required = true)
    protected String breakTypeCode;
    @XmlElement(name = "PositioninProgramme", required = true)
    protected String positioninProgramme;
    @XmlElement(name = "BreakNumber", required = true)
    protected String breakNumber;

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

    /**
     * Gets the value of the breakNominalTime property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBreakNominalTime() {
        return breakNominalTime;
    }

    /**
     * Sets the value of the breakNominalTime property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBreakNominalTime(String value) {
        this.breakNominalTime = value;
    }

    /**
     * Gets the value of the breakDuration property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBreakDuration() {
        return breakDuration;
    }

    /**
     * Sets the value of the breakDuration property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBreakDuration(String value) {
        this.breakDuration = value;
    }

    /**
     * Gets the value of the breakTypeCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBreakTypeCode() {
        return breakTypeCode;
    }

    /**
     * Sets the value of the breakTypeCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBreakTypeCode(String value) {
        this.breakTypeCode = value;
    }

    /**
     * Gets the value of the positioninProgramme property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPositioninProgramme() {
        return positioninProgramme;
    }

    /**
     * Sets the value of the positioninProgramme property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPositioninProgramme(String value) {
        this.positioninProgramme = value;
    }

    /**
     * Gets the value of the breakNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBreakNumber() {
        return breakNumber;
    }

    /**
     * Sets the value of the breakNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBreakNumber(String value) {
        this.breakNumber = value;
    }

}
