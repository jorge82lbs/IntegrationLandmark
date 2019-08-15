package mx.com.televisa.landamark.saml2;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.shibboleth.utilities.java.support.component.ComponentInitializationException;
import net.shibboleth.utilities.java.support.httpclient.HttpClientBuilder;

import org.apache.commons.codec.binary.Base64;

import org.joda.time.DateTime;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.io.Unmarshaller;
import org.opensaml.core.xml.io.UnmarshallerFactory;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.core.xml.schema.impl.XSAnyImpl;
import org.opensaml.messaging.context.InOutOperationContext;
import org.opensaml.messaging.context.MessageContext;
import org.opensaml.messaging.encoder.MessageEncodingException;
import org.opensaml.messaging.handler.MessageHandler;
import org.opensaml.messaging.handler.MessageHandlerException;
import org.opensaml.messaging.handler.impl.BasicMessageHandlerChain;
import org.opensaml.messaging.pipeline.httpclient.BasicHttpClientMessagePipeline;
import org.opensaml.messaging.pipeline.httpclient.HttpClientMessagePipeline;
import org.opensaml.profile.context.ProfileRequestContext;
import org.opensaml.saml.common.SAMLObject;
import org.opensaml.saml.common.binding.security.impl.MessageLifetimeSecurityHandler;
import org.opensaml.saml.common.binding.security.impl.ReceivedEndpointSecurityHandler;
import org.opensaml.saml.common.binding.security.impl.SAMLOutboundProtocolMessageSigningHandler;
import org.opensaml.saml.common.messaging.context.SAMLMessageInfoContext;
import org.opensaml.saml.saml2.binding.decoding.impl.HttpClientResponseSOAP11Decoder;
import org.opensaml.saml.saml2.binding.encoding.impl.HttpClientRequestSOAP11Encoder;
import org.opensaml.saml.saml2.core.Artifact;
import org.opensaml.saml.saml2.core.ArtifactResolve;
import org.opensaml.saml.saml2.core.ArtifactResponse;
import org.opensaml.saml.saml2.core.Assertion;
import org.opensaml.saml.saml2.core.Attribute;
import org.opensaml.saml.saml2.core.EncryptedAssertion;
import org.opensaml.saml.saml2.core.Issuer;
import org.opensaml.saml.saml2.core.Response;
import org.opensaml.saml.saml2.encryption.Decrypter;
import org.opensaml.saml.security.impl.SAMLSignatureProfileValidator;
import org.opensaml.soap.client.http.AbstractPipelineHttpSOAPClient;
import org.opensaml.soap.common.SOAPException;
import org.opensaml.xmlsec.SignatureSigningParameters;
import org.opensaml.xmlsec.context.SecurityParametersContext;
import org.opensaml.xmlsec.encryption.support.DecryptionException;
import org.opensaml.xmlsec.encryption.support.InlineEncryptedKeyResolver;
import org.opensaml.xmlsec.keyinfo.impl.StaticKeyInfoCredentialResolver;
import org.opensaml.xmlsec.signature.support.SignatureConstants;
import org.opensaml.xmlsec.signature.support.SignatureException;
import org.opensaml.xmlsec.signature.support.SignatureValidator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

@WebServlet(name = "ACSServlet", urlPatterns = { "/pgmlandmarkacs" })
public class ACSServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig toConfig) throws ServletException {
        super.init(toConfig);
    }

    public void doGet(HttpServletRequest toReq,
                      HttpServletResponse toResp) throws ServletException,
                                                           IOException {
        String lsCPath = toReq.getContextPath();
        System.out.println("CPath>>>: " + lsCPath);
        toResp.sendRedirect(lsCPath + "/faces/indexPage");
        
        //doPost(toReq,toResp);
    }


    @Override
    protected void doPost(HttpServletRequest toReq,
                          HttpServletResponse toResp) throws ServletException,
                                                                          IOException {
        System.out.println("doPost>>>>> saml2");
        try {
            String loResponseMessage = toReq.getParameter("SAMLResponse");
            System.out.println("SAMLResponse: " + loResponseMessage);
            byte[] laBase64DecodedResponse = new Base64().decode(loResponseMessage);

            ByteArrayInputStream loIS =
                new ByteArrayInputStream(laBase64DecodedResponse);

            DocumentBuilderFactory loDocumentBuilderFactory =
                DocumentBuilderFactory.newInstance();
            loDocumentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder loDocBuilder =
                loDocumentBuilderFactory.newDocumentBuilder();

            Document loDocument = loDocBuilder.parse(loIS);
            Element loElement = loDocument.getDocumentElement();
            
            
            UnmarshallerFactory loUnmarshallerFactory = XMLObjectProviderRegistrySupport.getUnmarshallerFactory();
            Unmarshaller loUnmarshaller = loUnmarshallerFactory.getUnmarshaller(loElement);
            XMLObject loResponseXmlObj = loUnmarshaller.unmarshall(loElement);
            
            
            Response loResponse = (Response) loResponseXmlObj;
            //System.out.println("Response: ");
            //OpenSAMLUtils.logSAMLObject(loResponse);
            System.out.println();
            //EncryptedAssertion loEncryptedAssertion = loResponse.getEncryptedAssertions().get(0);
            
            //Assertion loAssertion = decryptAssertion(loEncryptedAssertion);
            Assertion loAssertion = loResponse.getAssertions().get(0);
            verifyAssertionSignature(loAssertion);
            System.out.println("Decrypted Assertion: ");
            OpenSAMLUtils.logSAMLObject(loAssertion);

            Map<String, List<String>> laAtts = logAssertionAttributes(loAssertion);
            logAuthenticationInstant(loAssertion);
            logAuthenticationMethod(loAssertion);
            
            String lsUser = "";
            String lsObjId = "";
            List<String> laUserVals = laAtts.get(SSOParameters.psEMAIL_ATT_NAME);
            List<String> laObjIdVals = laAtts.get(SSOParameters.psOBJECT_ID_ATT_NAME);
            if(laUserVals != null && laUserVals.size() > 0) {
                String lsEmail = laUserVals.get(0);
                try {
                    lsUser = getEmailPrefix(lsEmail);
                } catch (Exception loEx) {
                    loEx.printStackTrace();
                }
                System.out.println("email: " + lsUser);
            }
            if(laObjIdVals != null && laObjIdVals.size() > 0) {
                lsObjId = laObjIdVals.get(0);
                System.out.println("ObjectId: " + lsObjId);
            }
            /*if(false) {
                lsUser = "hvgarciar";
            }*/
            setAuthenticatedSession(toReq);
            toReq.getSession().setAttribute(Constants.psUSER_NAME_SES_ATT, 
                                          lsUser);
            //toReq.getSession().setAttribute(Constants.psOBJECT_ID_SES_ATT, 
              //                              lsObjId);
            redirectToGotoURL(toReq, toResp);
            
            return;
            
        } catch (IOException loIOEx) {
            // TODO: Add catch code
            loIOEx.printStackTrace();
        } catch (SAXException loSAXEx) {
            // TODO: Add catch code
            loSAXEx.printStackTrace();
        } catch (ParserConfigurationException loPCEx) {
            // TODO: Add catch code
            loPCEx.printStackTrace();
        } catch (UnmarshallingException loUEx) {
            loUEx.printStackTrace();
        }


        System.out.println("Artifact received");
        
        Artifact loArtifact = buildArtifactFromRequest(toReq);
        System.out.println("Artifact: " + loArtifact.getArtifact());

        ArtifactResolve loArtifactResolve = buildArtifactResolve(loArtifact);
        System.out.println("Sending ArtifactResolve");
        System.out.println("ArtifactResolve: ");
        OpenSAMLUtils.logSAMLObject(loArtifactResolve);

        ArtifactResponse loArtifactResponse = sendAndReceiveArtifactResolve(loArtifactResolve, toResp);
        System.out.println("ArtifactResponse received");
        System.out.println("ArtifactResponse: ");
        OpenSAMLUtils.logSAMLObject(loArtifactResponse);

        validateDestinationAndLifetime(loArtifactResponse, toReq);

        EncryptedAssertion loEncryptedAssertion = getEncryptedAssertion(loArtifactResponse);
        
        
        Assertion loAssertion = decryptAssertion(loEncryptedAssertion);
        verifyAssertionSignature(loAssertion);
        System.out.println("Decrypted Assertion: ");
        OpenSAMLUtils.logSAMLObject(loAssertion);

        logAssertionAttributes(loAssertion);
        logAuthenticationInstant(loAssertion);
        logAuthenticationMethod(loAssertion);

        setAuthenticatedSession(toReq);
        redirectToGotoURL(toReq, toResp);
    }

    private void validateDestinationAndLifetime(ArtifactResponse toArtifactResponse, HttpServletRequest toRequest) {
        MessageContext loContext = new MessageContext<ArtifactResponse>();
        loContext.setMessage(toArtifactResponse);

        SAMLMessageInfoContext loMessageInfoContext = loContext.getSubcontext(SAMLMessageInfoContext.class, true);
        loMessageInfoContext.setMessageIssueInstant(toArtifactResponse.getIssueInstant());

        MessageLifetimeSecurityHandler loLlifetimeSecurityHandler = new MessageLifetimeSecurityHandler();
        loLlifetimeSecurityHandler.setClockSkew(1000);
        loLlifetimeSecurityHandler.setMessageLifetime(2000);
        loLlifetimeSecurityHandler.setRequiredRule(true);

        ReceivedEndpointSecurityHandler loReceivedEndpointSecurityHandler = new ReceivedEndpointSecurityHandler();
        loReceivedEndpointSecurityHandler.setHttpServletRequest(toRequest);
        List laHandlers = new ArrayList<MessageHandler>();
        laHandlers.add(loLlifetimeSecurityHandler);
        laHandlers.add(loReceivedEndpointSecurityHandler);

        BasicMessageHandlerChain<ArtifactResponse> loHandlerChain = new BasicMessageHandlerChain<ArtifactResponse>();
        loHandlerChain.setHandlers(laHandlers);

        try {
            loHandlerChain.initialize();
            loHandlerChain.doInvoke(loContext);
        } catch (ComponentInitializationException loCIEx) {
            throw new RuntimeException(loCIEx);
        } catch (MessageHandlerException loMHEx) {
            throw new RuntimeException(loMHEx);
        }


    }

    private Assertion decryptAssertion(EncryptedAssertion toEncryptedAssertion) {
        StaticKeyInfoCredentialResolver loKeyInfoCredentialResolver = new StaticKeyInfoCredentialResolver(SPCredentials.getPoCredential());

        Decrypter decrypter = new Decrypter(null, loKeyInfoCredentialResolver, new InlineEncryptedKeyResolver());
        decrypter.setRootInNewDocument(true);

        try {
            return decrypter.decrypt(toEncryptedAssertion);
        } catch (DecryptionException loDEx) {
            throw new RuntimeException(loDEx);
        }
    }
    
    private void verifyAssertionSignature(Response toRespAssertion) {

        if (!toRespAssertion.isSigned()) {
            throw new RuntimeException("The SAML Assertion was not signed");
        }

        try {
            SAMLSignatureProfileValidator loProfileValidator = new SAMLSignatureProfileValidator();
            loProfileValidator.validate(toRespAssertion.getSignature());

            SignatureValidator.validate(toRespAssertion.getSignature(), IDPCredentials.getPoCredential());

            System.out.println("SAML Assertion signature verified");
        } catch (SignatureException loSEx) {
            loSEx.printStackTrace();
        }

    }

    private void verifyAssertionSignature(Assertion toAssertion) {

        if (!toAssertion.isSigned()) {
            throw new RuntimeException("The SAML Assertion was not signed");
        }

        try {
            SAMLSignatureProfileValidator loProfileValidator = new SAMLSignatureProfileValidator();
            loProfileValidator.validate(toAssertion.getSignature());

            SignatureValidator.validate(toAssertion.getSignature(), IDPCredentials2.getPoCredential());

            System.out.println("SAML Assertion signature verified");
        } catch (SignatureException loSEx) {
            loSEx.printStackTrace();
        }

    }


    private void setAuthenticatedSession(HttpServletRequest toReq) {
        toReq.getSession().setAttribute(SSOParameters.psAUTHENTICATED_SESSION_ATTRIBUTE, true);
    }

    private void redirectToGotoURL(HttpServletRequest toReq, HttpServletResponse toResp) {
        String lsGoToURL = (String)toReq.getSession().getAttribute(SSOParameters.psGOTO_URL_SESSION_ATTRIBUTE);
        System.out.println("Redirecting to requested URL: " + lsGoToURL);
        try {
            toResp.sendRedirect(lsGoToURL);
        } catch (IOException loIOEx) {
            throw new RuntimeException(loIOEx);
        }
    }

    private void logAuthenticationMethod(Assertion toAssertion) {
        System.out.println("Authentication method: " + toAssertion.getAuthnStatements().get(0)
                .getAuthnContext().getAuthnContextClassRef().getAuthnContextClassRef());
    }

    private void logAuthenticationInstant(Assertion toAssertion) {
        System.out.println("Authentication instant: " + toAssertion.getAuthnStatements().get(0).getAuthnInstant());
    }

    private Map<String, List<String>> logAssertionAttributes(Assertion toAssertion) {
        Map<String, List<String>> loAtts = new HashMap<>();
        for (Attribute loAttribute : toAssertion.getAttributeStatements().get(0).getAttributes()) {
            List<String> laAttVals = new ArrayList<>();
            System.out.println("Attribute name: " + loAttribute.getName());
            for (XMLObject attributeValue : loAttribute.getAttributeValues()) {
                System.out.println("Attribute value: " + ((XSAnyImpl) attributeValue).getTextContent());
                laAttVals.add(((XSAnyImpl) attributeValue).getTextContent());
            }
            loAtts.put(loAttribute.getName(), laAttVals);
        }
        return loAtts;
    }

    private EncryptedAssertion getEncryptedAssertion(ArtifactResponse toArtifactResponse) {
        Response response = (Response)toArtifactResponse.getMessage();
        return response.getEncryptedAssertions().get(0);
    }

    private ArtifactResponse sendAndReceiveArtifactResolve(final ArtifactResolve toArtifactResolve, HttpServletResponse toServletResponse) {
        try {

            MessageContext<ArtifactResolve> loContextout = new MessageContext<ArtifactResolve>();

            loContextout.setMessage(toArtifactResolve);

            SignatureSigningParameters loSignatureSigningParameters = new SignatureSigningParameters();
            loSignatureSigningParameters.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA256);
            loSignatureSigningParameters.setSigningCredential(SPCredentials.getPoCredential());
            loSignatureSigningParameters.setSignatureCanonicalizationAlgorithm(SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);

            SecurityParametersContext loSecurityParametersContext = loContextout.getSubcontext(SecurityParametersContext.class, true);
            loSecurityParametersContext.setSignatureSigningParameters(loSignatureSigningParameters);

            InOutOperationContext<ArtifactResponse, ArtifactResolve> loContext = new ProfileRequestContext<ArtifactResponse, ArtifactResolve>();
            loContext.setOutboundMessageContext(loContextout);



            AbstractPipelineHttpSOAPClient<SAMLObject, SAMLObject> loSoapClient = new AbstractPipelineHttpSOAPClient() {
                protected HttpClientMessagePipeline newPipeline() throws SOAPException {
                    HttpClientRequestSOAP11Encoder loEncoder = new HttpClientRequestSOAP11Encoder();
                    HttpClientResponseSOAP11Decoder loDecoder = new HttpClientResponseSOAP11Decoder();

                    BasicHttpClientMessagePipeline loPipeline = new BasicHttpClientMessagePipeline(
                            loEncoder,
                            loDecoder
                    );

                    loPipeline.setOutboundPayloadHandler(new SAMLOutboundProtocolMessageSigningHandler());
                    return loPipeline;
                }};

            HttpClientBuilder loClientBuilder = new HttpClientBuilder();

            loSoapClient.setHttpClient(loClientBuilder.buildClient());
            loSoapClient.send(SSOParameters.psARTIFACT_RESOLUTION_SERVICE, loContext);

            return loContext.getInboundMessageContext().getMessage();
        } catch (SecurityException loSEx) {
            throw new RuntimeException(loSEx);
        } catch (ComponentInitializationException loCIEx) {
            throw new RuntimeException(loCIEx);
        } catch (MessageEncodingException loMEEx) {
            throw new RuntimeException(loMEEx);
        } catch (IllegalAccessException loIAEx) {
            throw new RuntimeException(loIAEx);
        } catch (Exception loEx) {
            throw new RuntimeException(loEx);
        }

    }

    private Artifact buildArtifactFromRequest(final HttpServletRequest toReq) {
        Artifact loArtifact = OpenSAMLUtils.buildSAMLObject(Artifact.class);
        //Object loO = toReq.getParameter("SAMLart");
        //System.out.println("SAMLart = " + loO);
        loArtifact.setArtifact(toReq.getParameter("SAMLart"));
        return loArtifact;
    }

    private ArtifactResolve buildArtifactResolve(final Artifact toArtifact) {
        ArtifactResolve loArtifactResolve = OpenSAMLUtils.buildSAMLObject(ArtifactResolve.class);

        Issuer loIssuer = OpenSAMLUtils.buildSAMLObject(Issuer.class);
        loIssuer.setValue(SSOParameters.psSP_ENTITY_ID);
        loArtifactResolve.setIssuer(loIssuer);

        loArtifactResolve.setIssueInstant(new DateTime());

        loArtifactResolve.setID(OpenSAMLUtils.generateSecureRandomId());

        loArtifactResolve.setDestination(SSOParameters.psARTIFACT_RESOLUTION_SERVICE);

        loArtifactResolve.setArtifact(toArtifact);

        return loArtifactResolve;
    }
    
    public static String getEmailPrefix(String tsEmail) throws Exception {
       int liAtIdx = tsEmail.indexOf('@');
       if(liAtIdx <= 0) {
           throw new Exception("@ Symbol not found");
       }
       return tsEmail.substring(0, liAtIdx);
   }
}