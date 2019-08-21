
package mx.com.televisa.landamark.client.pricest.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for error complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="error">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="psDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="psSourceError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "error", propOrder = { "psDescription", "psSourceError" })
public class Error {

    protected String psDescription;
    protected String psSourceError;

    /**
     * Gets the value of the psDescription property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPsDescription() {
        return psDescription;
    }

    /**
     * Sets the value of the psDescription property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPsDescription(String value) {
        this.psDescription = value;
    }

    /**
     * Gets the value of the psSourceError property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPsSourceError() {
        return psSourceError;
    }

    /**
     * Sets the value of the psSourceError property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPsSourceError(String value) {
        this.psSourceError = value;
    }

}
