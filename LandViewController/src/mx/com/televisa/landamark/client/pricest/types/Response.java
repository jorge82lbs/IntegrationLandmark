
package mx.com.televisa.landamark.client.pricest.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for response complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="response">
 *   &lt;complexContent>
 *     &lt;extension base="{http://impl.webservice.rtcrd.televisa.com.mx/}responseAbstract">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "response")
@XmlSeeAlso({ SpotConciliacionResponse.class, SpotConciliacionResult.class })
public class Response extends ResponseAbstract {


}
