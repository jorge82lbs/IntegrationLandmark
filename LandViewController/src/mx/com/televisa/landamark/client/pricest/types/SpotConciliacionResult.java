
package mx.com.televisa.landamark.client.pricest.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for spotConciliacionResult complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="spotConciliacionResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.webservice.rtcrd.televisa.com.mx/}response">
 *       &lt;sequence>
 *         &lt;element name="piSpotID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spotConciliacionResult", propOrder = { "piSpotID" })
public class SpotConciliacionResult extends Response {

    protected Integer piSpotID;

    /**
     * Gets the value of the piSpotID property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getPiSpotID() {
        return piSpotID;
    }

    /**
     * Sets the value of the piSpotID property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setPiSpotID(Integer value) {
        this.piSpotID = value;
    }

}
