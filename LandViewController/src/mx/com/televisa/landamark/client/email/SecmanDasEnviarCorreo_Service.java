
package mx.com.televisa.landamark.client.email;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140319.1121
 * Generated source version: 2.2
 *
 */
@WebServiceClient(name = "SecmanDasEnviarCorreo", targetNamespace = "http://tempuri.org/",
                  wsdlLocation =
                  "http://tvmiddev1.televisa.net:10106/SecmanWebServices/SecmanDasEnviarCorreoSoap12HttpPort?wsdl#%7Bhttp%3A%2F%2Ftempuri.org%2F%7DSecmanDasEnviarCorreo")
public class SecmanDasEnviarCorreo_Service extends Service {

    private final static URL SECMANDASENVIARCORREO_WSDL_LOCATION;
    private final static WebServiceException SECMANDASENVIARCORREO_EXCEPTION;
    private final static QName SECMANDASENVIARCORREO_QNAME = new QName("http://tempuri.org/", "SecmanDasEnviarCorreo");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url =
                new URL("http://tvmiddev1.televisa.net:10106/SecmanWebServices/SecmanDasEnviarCorreoSoap12HttpPort?wsdl#%7Bhttp%3A%2F%2Ftempuri.org%2F%7DSecmanDasEnviarCorreo");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SECMANDASENVIARCORREO_WSDL_LOCATION = url;
        SECMANDASENVIARCORREO_EXCEPTION = e;
    }

    public SecmanDasEnviarCorreo_Service() {
        super(__getWsdlLocation(), SECMANDASENVIARCORREO_QNAME);
    }

    public SecmanDasEnviarCorreo_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SECMANDASENVIARCORREO_QNAME, features);
    }

    public SecmanDasEnviarCorreo_Service(URL wsdlLocation) {
        super(wsdlLocation, SECMANDASENVIARCORREO_QNAME);
    }

    public SecmanDasEnviarCorreo_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SECMANDASENVIARCORREO_QNAME, features);
    }

    public SecmanDasEnviarCorreo_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SecmanDasEnviarCorreo_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns SecmanDasEnviarCorreo
     */
    @WebEndpoint(name = "SecmanDasEnviarCorreoSoap12HttpPort")
    public SecmanDasEnviarCorreo getSecmanDasEnviarCorreoSoap12HttpPort() {
        return super.getPort(new QName("http://tempuri.org/", "SecmanDasEnviarCorreoSoap12HttpPort"),
                             SecmanDasEnviarCorreo.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SecmanDasEnviarCorreo
     */
    @WebEndpoint(name = "SecmanDasEnviarCorreoSoap12HttpPort")
    public SecmanDasEnviarCorreo getSecmanDasEnviarCorreoSoap12HttpPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "SecmanDasEnviarCorreoSoap12HttpPort"),
                             SecmanDasEnviarCorreo.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SECMANDASENVIARCORREO_EXCEPTION != null) {
            throw SECMANDASENVIARCORREO_EXCEPTION;
        }
        return SECMANDASENVIARCORREO_WSDL_LOCATION;
    }

}
