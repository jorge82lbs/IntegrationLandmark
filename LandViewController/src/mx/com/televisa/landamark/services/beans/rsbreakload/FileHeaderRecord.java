
package mx.com.televisa.landamark.services.beans.rsbreakload;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FileHeaderRecord complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FileHeaderRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecordType" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="FileCreationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FileCreationTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileHeaderRecord", propOrder = { "recordType", "fileCreationDate", "fileCreationTime" })
public class FileHeaderRecord {

    @XmlElement(name = "RecordType", required = true)
    protected BigInteger recordType;
    @XmlElement(name = "FileCreationDate", required = true)
    protected String fileCreationDate;
    @XmlElement(name = "FileCreationTime", required = true)
    protected String fileCreationTime;

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
     * Gets the value of the fileCreationDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFileCreationDate() {
        return fileCreationDate;
    }

    /**
     * Sets the value of the fileCreationDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFileCreationDate(String value) {
        this.fileCreationDate = value;
    }

    /**
     * Gets the value of the fileCreationTime property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFileCreationTime() {
        return fileCreationTime;
    }

    /**
     * Sets the value of the fileCreationTime property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFileCreationTime(String value) {
        this.fileCreationTime = value;
    }

}
