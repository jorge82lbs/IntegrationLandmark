
package mx.com.televisa.landamark.client.pricest.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for spotConciliaion complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="spotConciliaion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Request" type="{http://impl.webservice.rtcrd.televisa.com.mx/}comercialListRequest"/>
 *         &lt;element name="User" type="{http://impl.webservice.rtcrd.televisa.com.mx/}user"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spotConciliaion", propOrder = { "request", "user" })
public class SpotConciliaion {

    @XmlElement(name = "Request", required = true)
    protected ComercialListRequest request;
    @XmlElement(name = "User", required = true)
    protected User user;

    /**
     * Gets the value of the request property.
     *
     * @return
     *     possible object is
     *     {@link ComercialListRequest }
     *
     */
    public ComercialListRequest getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     *
     * @param value
     *     allowed object is
     *     {@link ComercialListRequest }
     *
     */
    public void setRequest(ComercialListRequest value) {
        this.request = value;
    }

    /**
     * Gets the value of the user property.
     *
     * @return
     *     possible object is
     *     {@link User }
     *
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     *
     * @param value
     *     allowed object is
     *     {@link User }
     *
     */
    public void setUser(User value) {
        this.user = value;
    }

}
