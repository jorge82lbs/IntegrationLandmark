
package mx.com.televisa.landamark.services.beans.rsbreakload;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BreaksCollection complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BreaksCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BreaksList" type="{http://mx/com/televisa/landamark/services/beans/rsbreakload}BreakRecord" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BreaksCollection", propOrder = { "breaksList" })
public class BreaksCollection {

    @XmlElement(name = "BreaksList", required = true)
    protected List<BreakRecord> breaksList;

    /**
     * Gets the value of the breaksList property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the breaksList property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBreaksList().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BreakRecord }
     *
     *
     */
    public List<BreakRecord> getBreaksList() {
        if (breaksList == null) {
            breaksList = new ArrayList<BreakRecord>();
        }
        return this.breaksList;
    }

}
