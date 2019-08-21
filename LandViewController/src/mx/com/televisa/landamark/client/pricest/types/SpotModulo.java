
package mx.com.televisa.landamark.client.pricest.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for spotModulo complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="spotModulo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="piOrderID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="piSpotID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pdSpotPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pdSpotRating" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pdCPR" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pdPorcentRecDuration" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pdPorcentRecPosition" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pdPorcentRecPiggyBack" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pdPorcentRecDigital" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pdPorcentRecManual" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pdPorcentFactDuracion" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="psSpotFijo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spotModulo", propOrder = {
         "piOrderID", "piSpotID", "pdSpotPrice", "pdSpotRating", "pdCPR", "pdPorcentRecDuration",
         "pdPorcentRecPosition", "pdPorcentRecPiggyBack", "pdPorcentRecDigital", "pdPorcentRecManual",
         "pdPorcentFactDuracion", "psSpotFijo"
    })
public class SpotModulo {

    protected int piOrderID;
    protected int piSpotID;
    protected double pdSpotPrice;
    protected double pdSpotRating;
    protected double pdCPR;
    protected double pdPorcentRecDuration;
    protected double pdPorcentRecPosition;
    protected double pdPorcentRecPiggyBack;
    protected double pdPorcentRecDigital;
    protected double pdPorcentRecManual;
    protected double pdPorcentFactDuracion;
    protected String psSpotFijo;

    /**
     * Gets the value of the piOrderID property.
     *
     */
    public int getPiOrderID() {
        return piOrderID;
    }

    /**
     * Sets the value of the piOrderID property.
     *
     */
    public void setPiOrderID(int value) {
        this.piOrderID = value;
    }

    /**
     * Gets the value of the piSpotID property.
     *
     */
    public int getPiSpotID() {
        return piSpotID;
    }

    /**
     * Sets the value of the piSpotID property.
     *
     */
    public void setPiSpotID(int value) {
        this.piSpotID = value;
    }

    /**
     * Gets the value of the pdSpotPrice property.
     *
     */
    public double getPdSpotPrice() {
        return pdSpotPrice;
    }

    /**
     * Sets the value of the pdSpotPrice property.
     *
     */
    public void setPdSpotPrice(double value) {
        this.pdSpotPrice = value;
    }

    /**
     * Gets the value of the pdSpotRating property.
     *
     */
    public double getPdSpotRating() {
        return pdSpotRating;
    }

    /**
     * Sets the value of the pdSpotRating property.
     *
     */
    public void setPdSpotRating(double value) {
        this.pdSpotRating = value;
    }

    /**
     * Gets the value of the pdCPR property.
     *
     */
    public double getPdCPR() {
        return pdCPR;
    }

    /**
     * Sets the value of the pdCPR property.
     *
     */
    public void setPdCPR(double value) {
        this.pdCPR = value;
    }

    /**
     * Gets the value of the pdPorcentRecDuration property.
     *
     */
    public double getPdPorcentRecDuration() {
        return pdPorcentRecDuration;
    }

    /**
     * Sets the value of the pdPorcentRecDuration property.
     *
     */
    public void setPdPorcentRecDuration(double value) {
        this.pdPorcentRecDuration = value;
    }

    /**
     * Gets the value of the pdPorcentRecPosition property.
     *
     */
    public double getPdPorcentRecPosition() {
        return pdPorcentRecPosition;
    }

    /**
     * Sets the value of the pdPorcentRecPosition property.
     *
     */
    public void setPdPorcentRecPosition(double value) {
        this.pdPorcentRecPosition = value;
    }

    /**
     * Gets the value of the pdPorcentRecPiggyBack property.
     *
     */
    public double getPdPorcentRecPiggyBack() {
        return pdPorcentRecPiggyBack;
    }

    /**
     * Sets the value of the pdPorcentRecPiggyBack property.
     *
     */
    public void setPdPorcentRecPiggyBack(double value) {
        this.pdPorcentRecPiggyBack = value;
    }

    /**
     * Gets the value of the pdPorcentRecDigital property.
     *
     */
    public double getPdPorcentRecDigital() {
        return pdPorcentRecDigital;
    }

    /**
     * Sets the value of the pdPorcentRecDigital property.
     *
     */
    public void setPdPorcentRecDigital(double value) {
        this.pdPorcentRecDigital = value;
    }

    /**
     * Gets the value of the pdPorcentRecManual property.
     *
     */
    public double getPdPorcentRecManual() {
        return pdPorcentRecManual;
    }

    /**
     * Sets the value of the pdPorcentRecManual property.
     *
     */
    public void setPdPorcentRecManual(double value) {
        this.pdPorcentRecManual = value;
    }

    /**
     * Gets the value of the pdPorcentFactDuracion property.
     *
     */
    public double getPdPorcentFactDuracion() {
        return pdPorcentFactDuracion;
    }

    /**
     * Sets the value of the pdPorcentFactDuracion property.
     *
     */
    public void setPdPorcentFactDuracion(double value) {
        this.pdPorcentFactDuracion = value;
    }

    /**
     * Gets the value of the psSpotFijo property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPsSpotFijo() {
        return psSpotFijo;
    }

    /**
     * Sets the value of the psSpotFijo property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPsSpotFijo(String value) {
        this.psSpotFijo = value;
    }

}
