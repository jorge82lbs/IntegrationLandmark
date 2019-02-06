
package mx.com.televisa.landamark.client.operations.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObtenerOperacionesUsuarioResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ObtenerOperacionesUsuarioResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://xmlns.soin.tvsa.com/Secman/Servicios/UsuarioPermisos}OperacionesUsuarioOutputParameters" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObtenerOperacionesUsuarioResponse", namespace = "http://webServices.view.secman.televisa.com.mx/",
         propOrder = { "_return" })
public class ObtenerOperacionesUsuarioResponse {

    @XmlElement(name = "return", namespace = "")
    protected OperacionesUsuarioOutputParameters _return;

    /**
     * Gets the value of the return property.
     *
     * @return
     *     possible object is
     *     {@link OperacionesUsuarioOutputParameters }
     *
     */
    public OperacionesUsuarioOutputParameters getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value
     *     allowed object is
     *     {@link OperacionesUsuarioOutputParameters }
     *
     */
    public void setReturn(OperacionesUsuarioOutputParameters value) {
        this._return = value;
    }

}
