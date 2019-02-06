
package mx.com.televisa.landamark.client.operations.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PermisosUsuarioInputParameters complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PermisosUsuarioInputParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nomAplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PermisosUsuarioInputParameters", propOrder = { "userName", "nomAplicacion" })
public class PermisosUsuarioInputParameters {

    @XmlElement(name = "UserName", required = true)
    protected String userName;
    @XmlElement(required = true)
    protected String nomAplicacion;

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
     * Gets the value of the nomAplicacion property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNomAplicacion() {
        return nomAplicacion;
    }

    /**
     * Sets the value of the nomAplicacion property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNomAplicacion(String value) {
        this.nomAplicacion = value;
    }

}
