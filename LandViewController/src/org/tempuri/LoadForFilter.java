
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.datacontract.schemas._2004._07.landmark_parameters.SpotListFilter;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="spotListFilter" type="{http://schemas.datacontract.org/2004/07/Landmark.Parameters.Spots}SpotListFilter" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "spotListFilter", "currency" })
@XmlRootElement(name = "LoadForFilter")
public class LoadForFilter {

    @XmlElementRef(name = "spotListFilter", namespace = "http://tempuri.org/", type = JAXBElement.class,
                   required = false)
    protected JAXBElement<SpotListFilter> spotListFilter;
    @XmlElementRef(name = "currency", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> currency;

    /**
     * Gets the value of the spotListFilter property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SpotListFilter }{@code >}
     *
     */
    public JAXBElement<SpotListFilter> getSpotListFilter() {
        return spotListFilter;
    }

    /**
     * Sets the value of the spotListFilter property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SpotListFilter }{@code >}
     *
     */
    public void setSpotListFilter(JAXBElement<SpotListFilter> value) {
        this.spotListFilter = value;
    }

    /**
     * Gets the value of the currency property.
     *
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     *
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCurrency(JAXBElement<String> value) {
        this.currency = value;
    }

}
