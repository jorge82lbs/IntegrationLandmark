
package mx.com.televisa.landamark.client.pricest.types;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for comercialListRequest complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="comercialListRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paSpots" type="{http://impl.webservice.rtcrd.televisa.com.mx/}spotModulo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comercialListRequest", propOrder = { "paSpots" })
public class ComercialListRequest {

    @XmlElement(required = true)
    protected List<SpotModulo> paSpots;

    /**
     * Gets the value of the paSpots property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paSpots property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaSpots().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpotModulo }
     *
     *
     */
    public List<SpotModulo> getPaSpots() {
        if (paSpots == null) {
            paSpots = new ArrayList<SpotModulo>();
        }
        return this.paSpots;
    }

}
