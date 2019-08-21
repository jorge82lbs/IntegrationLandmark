
package mx.com.televisa.landamark.client.pricest.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for spotConciliaionResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="spotConciliaionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SpotConciliacionResult" type="{http://impl.webservice.rtcrd.televisa.com.mx/}spotConciliacionResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spotConciliaionResponse", propOrder = { "spotConciliacionResult" })
public class SpotConciliaionResponse {

    @XmlElement(name = "SpotConciliacionResult")
    protected SpotConciliacionResponse spotConciliacionResult;

    /**
     * Gets the value of the spotConciliacionResult property.
     *
     * @return
     *     possible object is
     *     {@link SpotConciliacionResponse }
     *
     */
    public SpotConciliacionResponse getSpotConciliacionResult() {
        return spotConciliacionResult;
    }

    /**
     * Sets the value of the spotConciliacionResult property.
     *
     * @param value
     *     allowed object is
     *     {@link SpotConciliacionResponse }
     *
     */
    public void setSpotConciliacionResult(SpotConciliacionResponse value) {
        this.spotConciliacionResult = value;
    }

}
