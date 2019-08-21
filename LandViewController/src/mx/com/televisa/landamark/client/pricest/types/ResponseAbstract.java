
package mx.com.televisa.landamark.client.pricest.types;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for responseAbstract complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="responseAbstract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paError" type="{http://impl.webservice.rtcrd.televisa.com.mx/}error" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="psMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseAbstract", propOrder = { "paError", "psMessage" })
@XmlSeeAlso({ Response.class })
public abstract class ResponseAbstract {

    @XmlElement(nillable = true)
    protected List<Error> paError;
    protected String psMessage;

    /**
     * Gets the value of the paError property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paError property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaError().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Error }
     *
     *
     */
    public List<Error> getPaError() {
        if (paError == null) {
            paError = new ArrayList<Error>();
        }
        return this.paError;
    }

    /**
     * Gets the value of the psMessage property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPsMessage() {
        return psMessage;
    }

    /**
     * Sets the value of the psMessage property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPsMessage(String value) {
        this.psMessage = value;
    }

}
