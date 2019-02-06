
package mx.com.televisa.landamark.client.operations.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the mx.com.televisa.landamark.client.operations.types package.
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

    private final static QName _ObtenerOperacionesUsuario_QNAME =
        new QName("http://webServices.view.secman.televisa.com.mx/", "ObtenerOperacionesUsuario");
    private final static QName _ObtenerOperacionesUsuarioResponse_QNAME =
        new QName("http://webServices.view.secman.televisa.com.mx/", "ObtenerOperacionesUsuarioResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.com.televisa.landamark.client.operations.types
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObtenerOperacionesUsuarioResponse }
     *
     */
    public ObtenerOperacionesUsuarioResponse createObtenerOperacionesUsuarioResponse() {
        return new ObtenerOperacionesUsuarioResponse();
    }

    /**
     * Create an instance of {@link ObtenerOperacionesUsuario }
     *
     */
    public ObtenerOperacionesUsuario createObtenerOperacionesUsuario() {
        return new ObtenerOperacionesUsuario();
    }

    /**
     * Create an instance of {@link OperacionNum }
     *
     */
    public OperacionNum createOperacionNum() {
        return new OperacionNum();
    }

    /**
     * Create an instance of {@link OperacionId }
     *
     */
    public OperacionId createOperacionId() {
        return new OperacionId();
    }

    /**
     * Create an instance of {@link OperacionesCollection }
     *
     */
    public OperacionesCollection createOperacionesCollection() {
        return new OperacionesCollection();
    }

    /**
     * Create an instance of {@link UserName }
     *
     */
    public UserName createUserName() {
        return new UserName();
    }

    /**
     * Create an instance of {@link OperacionesUsuarioOutputParameters }
     *
     */
    public OperacionesUsuarioOutputParameters createOperacionesUsuarioOutputParameters() {
        return new OperacionesUsuarioOutputParameters();
    }

    /**
     * Create an instance of {@link OperacionDesc }
     *
     */
    public OperacionDesc createOperacionDesc() {
        return new OperacionDesc();
    }

    /**
     * Create an instance of {@link NomAplicacion }
     *
     */
    public NomAplicacion createNomAplicacion() {
        return new NomAplicacion();
    }

    /**
     * Create an instance of {@link Operacion }
     *
     */
    public Operacion createOperacion() {
        return new Operacion();
    }

    /**
     * Create an instance of {@link PermisosUsuarioInputParameters }
     *
     */
    public PermisosUsuarioInputParameters createPermisosUsuarioInputParameters() {
        return new PermisosUsuarioInputParameters();
    }

    /**
     * Create an instance of {@link OperacionName }
     *
     */
    public OperacionName createOperacionName() {
        return new OperacionName();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerOperacionesUsuario }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://webServices.view.secman.televisa.com.mx/", name = "ObtenerOperacionesUsuario")
    public JAXBElement<ObtenerOperacionesUsuario> createObtenerOperacionesUsuario(ObtenerOperacionesUsuario value) {
        return new JAXBElement<ObtenerOperacionesUsuario>(_ObtenerOperacionesUsuario_QNAME,
                                                          ObtenerOperacionesUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerOperacionesUsuarioResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://webServices.view.secman.televisa.com.mx/",
                    name = "ObtenerOperacionesUsuarioResponse")
    public JAXBElement<ObtenerOperacionesUsuarioResponse> createObtenerOperacionesUsuarioResponse(ObtenerOperacionesUsuarioResponse value) {
        return new JAXBElement<ObtenerOperacionesUsuarioResponse>(_ObtenerOperacionesUsuarioResponse_QNAME,
                                                                  ObtenerOperacionesUsuarioResponse.class, null, value);
    }

}
