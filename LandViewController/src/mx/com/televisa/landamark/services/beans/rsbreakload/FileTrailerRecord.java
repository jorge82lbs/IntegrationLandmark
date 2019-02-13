
package mx.com.televisa.landamark.services.beans.rsbreakload;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FileTrailerRecord complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FileTrailerRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordType" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="RecordCount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileTrailerRecord", propOrder = { "recordType", "recordCount" })
public class FileTrailerRecord {

    @XmlElement(name = "RecordType", required = true)
    protected BigInteger recordType;
    @XmlElement(name = "RecordCount", required = true)
    protected String recordCount;

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
     * Gets the value of the recordCount property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRecordCount() {
        return recordCount;
    }

    /**
     * Sets the value of the recordCount property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRecordCount(String value) {
        this.recordCount = value;
    }

}
