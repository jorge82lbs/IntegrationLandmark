
package mx.com.televisa.landamark.client.auth.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the mx.com.televisa.landamark.client.auth.types package.
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

    private final static QName _AutenticarUsuarioResponse_QNAME =
        new QName("http://tempuri.org/", "autenticarUsuarioResponse");
    private final static QName _AutenticarUsuario_QNAME = new QName("http://tempuri.org/", "autenticarUsuario");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.com.televisa.landamark.client.auth.types
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserLogin }
     *
     */
    public UserLogin createUserLogin() {
        return new UserLogin();
    }

    /**
     * Create an instance of {@link ProcessResponse }
     *
     */
    public ProcessResponse createProcessResponse() {
        return new ProcessResponse();
    }

    /**
     * Create an instance of {@link AutenticarUsuarioResponse }
     *
     */
    public AutenticarUsuarioResponse createAutenticarUsuarioResponse() {
        return new AutenticarUsuarioResponse();
    }

    /**
     * Create an instance of {@link AutenticarUsuario }
     *
     */
    public AutenticarUsuario createAutenticarUsuario() {
        return new AutenticarUsuario();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AutenticarUsuarioResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "autenticarUsuarioResponse")
    public JAXBElement<AutenticarUsuarioResponse> createAutenticarUsuarioResponse(AutenticarUsuarioResponse value) {
        return new JAXBElement<AutenticarUsuarioResponse>(_AutenticarUsuarioResponse_QNAME,
                                                          AutenticarUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AutenticarUsuario }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "autenticarUsuario")
    public JAXBElement<AutenticarUsuario> createAutenticarUsuario(AutenticarUsuario value) {
        return new JAXBElement<AutenticarUsuario>(_AutenticarUsuario_QNAME, AutenticarUsuario.class, null, value);
    }

}
