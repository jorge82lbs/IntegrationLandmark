
package mx.com.televisa.landamark.client.pricest.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for user complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="user">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="psUserName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="psPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = { "psUserName", "psPassword" })
public class User {

    @XmlElement(required = true)
    protected String psUserName;
    @XmlElement(required = true)
    protected String psPassword;

    /**
     * Gets the value of the psUserName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPsUserName() {
        return psUserName;
    }

    /**
     * Sets the value of the psUserName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPsUserName(String value) {
        this.psUserName = value;
    }

    /**
     * Gets the value of the psPassword property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPsPassword() {
        return psPassword;
    }

    /**
     * Sets the value of the psPassword property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPsPassword(String value) {
        this.psPassword = value;
    }

}
