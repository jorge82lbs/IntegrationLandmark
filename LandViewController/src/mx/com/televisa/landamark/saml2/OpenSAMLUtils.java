package mx.com.televisa.landamark.saml2;

import java.io.StringWriter;

import javax.xml.namespace.QName;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.shibboleth.utilities.java.support.security.RandomIdentifierGenerationStrategy;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.XMLObjectBuilderFactory;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.io.Marshaller;
import org.opensaml.core.xml.io.MarshallingException;
import org.opensaml.saml.common.SignableSAMLObject;
import org.opensaml.saml.common.xml.SAMLConstants;
import org.opensaml.saml.saml2.core.Issuer;
import org.opensaml.saml.saml2.metadata.Endpoint;
import org.opensaml.saml.saml2.metadata.SingleSignOnService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.w3c.dom.Element;

public class OpenSAMLUtils {
    private static Logger logger = LoggerFactory.getLogger(OpenSAMLUtils.class);
        private static RandomIdentifierGenerationStrategy poSecureRandomIdGenerator;

        static {
            poSecureRandomIdGenerator = new RandomIdentifierGenerationStrategy();

        }

        public static <T> T buildSAMLObject(final Class<T> toClazz) {
            T loObject = null;
            try {
                XMLObjectBuilderFactory loBuilderFactory = XMLObjectProviderRegistrySupport.getBuilderFactory();
                QName loDefaultElementName = (QName)toClazz.getDeclaredField("DEFAULT_ELEMENT_NAME").get(null);
                loObject = (T)loBuilderFactory.getBuilder(loDefaultElementName).buildObject(loDefaultElementName);
            } catch (IllegalAccessException loEx) {
                throw new IllegalArgumentException("Could not create SAML object");
            } catch (NoSuchFieldException loEx) {
                throw new IllegalArgumentException("Could not create SAML object");
            }

            return loObject;
        }

        public static String generateSecureRandomId() {
            return poSecureRandomIdGenerator.generateIdentifier();
        }

        public static void logSAMLObject(final XMLObject toObject) {
            Element loElement = null;

            if (toObject instanceof SignableSAMLObject && ((SignableSAMLObject)toObject).isSigned() && toObject.getDOM() != null) {
                loElement = toObject.getDOM();
            } else {
                try {
                    Marshaller loOut = XMLObjectProviderRegistrySupport.getMarshallerFactory().getMarshaller(toObject);
                    loOut.marshall(toObject);
                    loElement = toObject.getDOM();

                } catch (MarshallingException loEx) {
                    logger.error(loEx.getMessage(), loEx);
                }
            }

            try {
                Transformer loTransformer = TransformerFactory.newInstance().newTransformer();
                loTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
                StreamResult loResult = new StreamResult(new StringWriter());
                DOMSource loSource = new DOMSource(loElement);

                loTransformer.transform(loSource, loResult);
                String loXmlString = loResult.getWriter().toString();

                System.out.println(loXmlString);
            } catch (TransformerConfigurationException loEx) {
                loEx.printStackTrace();
            } catch (TransformerException loEx) {
                loEx.printStackTrace();
            }
        }
        
    public static Issuer buildIssuer() {
        Issuer loIssuer = buildSAMLObject(Issuer.class);
        loIssuer.setValue(SSOParameters.psSP_ENTITY_ID);

        return loIssuer;
    }
    
    public static Endpoint getIPDEndpoint() throws Exception {
        SingleSignOnService loEndpoint = OpenSAMLUtils.buildSAMLObject(SingleSignOnService.class);
        loEndpoint.setBinding(SAMLConstants.SAML2_POST_BINDING_URI);//.SAML2_REDIRECT_BINDING_URI);
        loEndpoint.setLocation(SSOParameters.getIDPSAMLSSOServiceURL());

        return loEndpoint;
    }
}

