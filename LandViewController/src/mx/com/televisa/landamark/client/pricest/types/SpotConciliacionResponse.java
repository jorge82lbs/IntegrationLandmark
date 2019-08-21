
package mx.com.televisa.landamark.client.pricest.types;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for spotConciliacionResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="spotConciliacionResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.webservice.rtcrd.televisa.com.mx/}response">
 *       &lt;sequence>
 *         &lt;element name="paSpotResult" type="{http://impl.webservice.rtcrd.televisa.com.mx/}spotConciliacionResult" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spotConciliacionResponse", propOrder = { "paSpotResult" })
public class SpotConciliacionResponse extends Response {

    @XmlElement(nillable = true)
    protected List<SpotConciliacionResult> paSpotResult;

    /**
     * Gets the value of the paSpotResult property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paSpotResult property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaSpotResult().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpotConciliacionResult }
     *
     *
     */
    public List<SpotConciliacionResult> getPaSpotResult() {
        if (paSpotResult == null) {
            paSpotResult = new ArrayList<SpotConciliacionResult>();
        }
        return this.paSpotResult;
    }

}
