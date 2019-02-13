
package mx.com.televisa.landamark.services.beans.rsbreakload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BreakLoadFile complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BreakLoadFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdRequest" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdService" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FileName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RsBreakLoad" type="{http://mx/com/televisa/landamark/services/beans/rsbreakload}RsBreakLoad"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BreakLoadFile", propOrder = {
         "idRequest", "idService", "userName", "idUser", "fileName", "rsBreakLoad" })
public class BreakLoadFile {

    @XmlElement(name = "IdRequest", required = true)
    protected String idRequest;
    @XmlElement(name = "IdService", required = true)
    protected String idService;
    @XmlElement(name = "UserName", required = true)
    protected String userName;
    @XmlElement(name = "IdUser", required = true)
    protected String idUser;
    @XmlElement(name = "FileName", required = true)
    protected String fileName;
    @XmlElement(name = "RsBreakLoad", required = true)
    protected RsBreakLoad rsBreakLoad;

    /**
     * Gets the value of the idRequest property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdRequest() {
        return idRequest;
    }

    /**
     * Sets the value of the idRequest property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdRequest(String value) {
        this.idRequest = value;
    }

    /**
     * Gets the value of the idService property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdService() {
        return idService;
    }

    /**
     * Sets the value of the idService property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdService(String value) {
        this.idService = value;
    }

    /**
     * Gets the value of the userName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the idUser property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * Sets the value of the idUser property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdUser(String value) {
        this.idUser = value;
    }

    /**
     * Gets the value of the fileName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the rsBreakLoad property.
     *
     * @return
     *     possible object is
     *     {@link RsBreakLoad }
     *
     */
    public RsBreakLoad getRsBreakLoad() {
        return rsBreakLoad;
    }

    /**
     * Sets the value of the rsBreakLoad property.
     *
     * @param value
     *     allowed object is
     *     {@link RsBreakLoad }
     *
     */
    public void setRsBreakLoad(RsBreakLoad value) {
        this.rsBreakLoad = value;
    }

}
