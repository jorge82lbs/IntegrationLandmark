
package mx.com.televisa.landamark.services.beans.rsbreakload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RsBreakLoad complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RsBreakLoad">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FileHeaderRecord" type="{http://mx/com/televisa/landamark/services/beans/rsbreakload}FileHeaderRecord"/>
 *         &lt;element name="ChannelHeaderRecord" type="{http://mx/com/televisa/landamark/services/beans/rsbreakload}ChannelHeaderRecord"/>
 *         &lt;element name="BreaksCollection" type="{http://mx/com/televisa/landamark/services/beans/rsbreakload}BreaksCollection"/>
 *         &lt;element name="ChannelTrailerRecord" type="{http://mx/com/televisa/landamark/services/beans/rsbreakload}ChannelTrailerRecord"/>
 *         &lt;element name="FileTrailerRecord" type="{http://mx/com/televisa/landamark/services/beans/rsbreakload}FileTrailerRecord"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RsBreakLoad", propOrder = {
         "fileHeaderRecord", "channelHeaderRecord", "breaksCollection", "channelTrailerRecord", "fileTrailerRecord"
    })
public class RsBreakLoad {

    @XmlElement(name = "FileHeaderRecord", required = true)
    protected FileHeaderRecord fileHeaderRecord;
    @XmlElement(name = "ChannelHeaderRecord", required = true)
    protected ChannelHeaderRecord channelHeaderRecord;
    @XmlElement(name = "BreaksCollection", required = true)
    protected BreaksCollection breaksCollection;
    @XmlElement(name = "ChannelTrailerRecord", required = true)
    protected ChannelTrailerRecord channelTrailerRecord;
    @XmlElement(name = "FileTrailerRecord", required = true)
    protected FileTrailerRecord fileTrailerRecord;

    /**
     * Gets the value of the fileHeaderRecord property.
     *
     * @return
     *     possible object is
     *     {@link FileHeaderRecord }
     *
     */
    public FileHeaderRecord getFileHeaderRecord() {
        return fileHeaderRecord;
    }

    /**
     * Sets the value of the fileHeaderRecord property.
     *
     * @param value
     *     allowed object is
     *     {@link FileHeaderRecord }
     *
     */
    public void setFileHeaderRecord(FileHeaderRecord value) {
        this.fileHeaderRecord = value;
    }

    /**
     * Gets the value of the channelHeaderRecord property.
     *
     * @return
     *     possible object is
     *     {@link ChannelHeaderRecord }
     *
     */
    public ChannelHeaderRecord getChannelHeaderRecord() {
        return channelHeaderRecord;
    }

    /**
     * Sets the value of the channelHeaderRecord property.
     *
     * @param value
     *     allowed object is
     *     {@link ChannelHeaderRecord }
     *
     */
    public void setChannelHeaderRecord(ChannelHeaderRecord value) {
        this.channelHeaderRecord = value;
    }

    /**
     * Gets the value of the breaksCollection property.
     *
     * @return
     *     possible object is
     *     {@link BreaksCollection }
     *
     */
    public BreaksCollection getBreaksCollection() {
        return breaksCollection;
    }

    /**
     * Sets the value of the breaksCollection property.
     *
     * @param value
     *     allowed object is
     *     {@link BreaksCollection }
     *
     */
    public void setBreaksCollection(BreaksCollection value) {
        this.breaksCollection = value;
    }

    /**
     * Gets the value of the channelTrailerRecord property.
     *
     * @return
     *     possible object is
     *     {@link ChannelTrailerRecord }
     *
     */
    public ChannelTrailerRecord getChannelTrailerRecord() {
        return channelTrailerRecord;
    }

    /**
     * Sets the value of the channelTrailerRecord property.
     *
     * @param value
     *     allowed object is
     *     {@link ChannelTrailerRecord }
     *
     */
    public void setChannelTrailerRecord(ChannelTrailerRecord value) {
        this.channelTrailerRecord = value;
    }

    /**
     * Gets the value of the fileTrailerRecord property.
     *
     * @return
     *     possible object is
     *     {@link FileTrailerRecord }
     *
     */
    public FileTrailerRecord getFileTrailerRecord() {
        return fileTrailerRecord;
    }

    /**
     * Sets the value of the fileTrailerRecord property.
     *
     * @param value
     *     allowed object is
     *     {@link FileTrailerRecord }
     *
     */
    public void setFileTrailerRecord(FileTrailerRecord value) {
        this.fileTrailerRecord = value;
    }

}
