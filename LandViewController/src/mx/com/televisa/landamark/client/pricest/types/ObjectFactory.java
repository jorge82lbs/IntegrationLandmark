
package mx.com.televisa.landamark.client.pricest.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the mx.com.televisa.landamark.client.pricest.types package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SpotConciliaionResponse_QNAME =
        new QName("http://impl.webservice.rtcrd.televisa.com.mx/", "spotConciliaionResponse");
    private final static QName _SpotConciliaion_QNAME =
        new QName("http://impl.webservice.rtcrd.televisa.com.mx/", "spotConciliaion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.com.televisa.landamark.client.pricest.types
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SpotConciliaion }
     *
     */
    public SpotConciliaion createSpotConciliaion() {
        return new SpotConciliaion();
    }

    /**
     * Create an instance of {@link SpotConciliaionResponse }
     *
     */
    public SpotConciliaionResponse createSpotConciliaionResponse() {
        return new SpotConciliaionResponse();
    }

    /**
     * Create an instance of {@link Response }
     *
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link Error }
     *
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link SpotModulo }
     *
     */
    public SpotModulo createSpotModulo() {
        return new SpotModulo();
    }

    /**
     * Create an instance of {@link SpotConciliacionResponse }
     *
     */
    public SpotConciliacionResponse createSpotConciliacionResponse() {
        return new SpotConciliacionResponse();
    }

    /**
     * Create an instance of {@link ComercialListRequest }
     *
     */
    public ComercialListRequest createComercialListRequest() {
        return new ComercialListRequest();
    }

    /**
     * Create an instance of {@link SpotConciliacionResult }
     *
     */
    public SpotConciliacionResult createSpotConciliacionResult() {
        return new SpotConciliacionResult();
    }

    /**
     * Create an instance of {@link User }
     *
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotConciliaionResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://impl.webservice.rtcrd.televisa.com.mx/", name = "spotConciliaionResponse")
    public JAXBElement<SpotConciliaionResponse> createSpotConciliaionResponse(SpotConciliaionResponse value) {
        return new JAXBElement<SpotConciliaionResponse>(_SpotConciliaionResponse_QNAME, SpotConciliaionResponse.class,
                                                        null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpotConciliaion }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://impl.webservice.rtcrd.televisa.com.mx/", name = "spotConciliaion")
    public JAXBElement<SpotConciliaion> createSpotConciliaion(SpotConciliaion value) {
        return new JAXBElement<SpotConciliaion>(_SpotConciliaion_QNAME, SpotConciliaion.class, null, value);
    }

}
