package mx.com.televisa.landamark.saml2;

import java.io.IOException;

import java.security.Provider;

import java.security.Security;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import mx.com.televisa.landamark.saml2.OpenSAMLUtils;
import mx.com.televisa.landamark.saml2.SPCredentials;
import mx.com.televisa.landamark.saml2.SSOParameters;

import mx.com.televisa.landamark.users.UserMenuBean;

import net.shibboleth.utilities.java.support.component.ComponentInitializationException;
import org.opensaml.core.config.InitializationService;
//import org.opensaml.xmlsec.config.JavaCryptoValidationInitializer;
import org.opensaml.core.config.InitializationException;

import org.apache.commons.lang.ObjectUtils;
import org.joda.time.DateTime;
import org.opensaml.core.config.InitializationException;
import org.opensaml.core.config.InitializationService;
import org.opensaml.messaging.context.MessageContext;
import org.opensaml.messaging.encoder.MessageEncodingException;

import org.opensaml.messaging.handler.MessageHandlerException;
import org.opensaml.messaging.pipeline.servlet.BasicHttpServletMessagePipeline;
import org.opensaml.messaging.pipeline.servlet.HttpServletMessagePipeline;
import org.opensaml.saml.common.SAMLObject;
import org.opensaml.saml.common.binding.security.impl.SAMLOutboundProtocolMessageSigningHandler;
import org.opensaml.saml.common.messaging.context.SAMLBindingContext;
import org.opensaml.saml.common.messaging.context.SAMLEndpointContext;
import org.opensaml.saml.common.messaging.context.SAMLPeerEntityContext;
import org.opensaml.saml.common.xml.SAMLConstants;
import org.opensaml.saml.saml2.binding.encoding.impl.HTTPRedirectDeflateEncoder;
import org.opensaml.saml.saml2.core.*;
import org.opensaml.saml.saml2.metadata.Endpoint;
import org.opensaml.saml.saml2.metadata.SingleSignOnService;
import org.opensaml.xmlsec.SignatureSigningParameters;
//import org.opensaml.xmlsec.config.JavaCryptoValidationInitializer;
import org.opensaml.xmlsec.config.impl.JavaCryptoValidationInitializer;
import org.opensaml.xmlsec.context.SecurityParametersContext;
import org.opensaml.xmlsec.signature.support.SignatureConstants;


public class SSOFilter implements Filter {
    private FilterConfig _filterConfig = null;

    public void init(FilterConfig toFilterConfig) throws ServletException {
        _filterConfig = toFilterConfig;
        
        JavaCryptoValidationInitializer loJavaCryptoValidationInitializer = new JavaCryptoValidationInitializer();
        try {
            loJavaCryptoValidationInitializer.init();
        } catch (InitializationException loIEx) {
            loIEx.printStackTrace();
        }

        for (Provider loJceProvider : Security.getProviders()) {
            System.out.println(loJceProvider.getInfo());
        }

        try {
            System.out.println("Initializing");
            InitializationService.initialize();
        } catch (Throwable loT) {
            loT.printStackTrace();
            throw new RuntimeException("Initialization failed");
        }
    }

    public void destroy() {
        _filterConfig = null;
    }

    public void doFilter(ServletRequest toRequest, ServletResponse toResponse,
                         FilterChain toChain) throws IOException,
                                                   ServletException {
        System.out.println("SSO Filter");
        HttpServletRequest loHttpServletRequest = (HttpServletRequest)toRequest;
        HttpServletResponse loHttpServletResponse = (HttpServletResponse)toResponse;
        
        // LoginPage requested
        if(((HttpServletRequest)toRequest).getPathInfo().startsWith("/indexPage")) {
            // Is Authenticated
            if (loHttpServletRequest.getSession().getAttribute(SSOParameters.psAUTHENTICATED_SESSION_ATTRIBUTE) != null) {
                // Forward to HomePage
                forwardToPage(toRequest, toResponse, "/faces/homePage");
            } else {
                // Continue to LoginPage
                toChain.doFilter(toRequest, toResponse);
            }
        } else { // Other page requested
            // Is authenticated
            if (loHttpServletRequest.getSession().getAttribute(SSOParameters.psAUTHENTICATED_SESSION_ATTRIBUTE) != null) {
                // Verify page requested
                // Page restricted
                if(!validatePage(loHttpServletRequest)) {
                    // Forward to HomePage
                    forwardToPage(toRequest, toResponse, "/faces/homePage");
                // Page OK
                } else {
                    // Continue to page requested
                    toChain.doFilter(toRequest, toResponse);
                }
            // Not authenticated
            } else {
                //Verify login app attribute
                Boolean lbSes =
                    (Boolean) loHttpServletRequest.getSession().getAttribute("loginUserSes");
                System.out.println("loginUserSes: " + lbSes);
                // Login app attribute not present
                if(lbSes == null || lbSes == false) {
                    // Forward to login page
                    forwardToPage(toRequest, toResponse, "/faces/indexPage");
                // Login app attribute present
                } else {
                    // Redirect to Azure AD SSO SAML2
                    setGotoURLOnSession(loHttpServletRequest);
                    try {
                        redirectUserForAuthentication(loHttpServletResponse);
                    } catch (Exception loEx) {
                        loEx.printStackTrace();
                    }
                }
                //loHttpServletRequest.getSession().removeAttribute("loginUserSes");
                loHttpServletRequest.getSession().removeAttribute("loginUserSes");
            }
        }
    }
    
    /**
     * Método para obtener la página visitada.
     * 
     * @param tsPathInfo    URL visitada.
     * @return  Nombre de la página visitada.
     */
    private String getPage(String tsPathInfo) {
        String      lsPage = "";
        String[] laPage;
        
        try {
            int liIndex = tsPathInfo.lastIndexOf('/');
            if (liIndex != -1) {
                lsPage = tsPathInfo.substring(liIndex + 1, 
                                              tsPathInfo.length());
            }
            liIndex = lsPage.lastIndexOf(".");
            if (liIndex != -1) {
                laPage = lsPage.split("[.]");
                lsPage = laPage[0];
            }
        } catch (Exception loEx) {
            loEx.printStackTrace();
        }
        return lsPage;
    }
    
    private void forwardToPage(ServletRequest toRequest, 
                                     ServletResponse toResponse, String tsPage) {
        try {
            toRequest.getRequestDispatcher(tsPage).forward(toRequest, 
                                                           toResponse);
            return;
        }
        catch (ServletException loEx) {
            loEx.printStackTrace();
        }
        catch (IOException loEx) {
            loEx.printStackTrace();
        }
    }
    
    private void setGotoURLOnSession(HttpServletRequest toRequest) {
        System.out.println("Set req URL: " + toRequest.getRequestURL().toString());
        toRequest.getSession().setAttribute(SSOParameters.psGOTO_URL_SESSION_ATTRIBUTE, toRequest.getRequestURL().toString());
    }

    private void redirectUserForAuthentication(HttpServletResponse toHttpServletResponse) throws Exception {
        AuthnRequest loAuthnRequest = buildAuthnRequest();
        redirectUserWithRequest(toHttpServletResponse, loAuthnRequest);

    }

    private void redirectUserWithRequest(HttpServletResponse toHttpServletResponse, AuthnRequest toAuthnRequest) throws Exception {

        MessageContext loContext = new MessageContext();

        loContext.setMessage(toAuthnRequest);

        SAMLBindingContext loBindingContext = loContext.getSubcontext(SAMLBindingContext.class, true);
        loBindingContext.setRelayState("teststate");

        SAMLPeerEntityContext peerEntityContext = loContext.getSubcontext(SAMLPeerEntityContext.class, true);

        SAMLEndpointContext loEndpointContext = peerEntityContext.getSubcontext(SAMLEndpointContext.class, true);
        loEndpointContext.setEndpoint(getIPDEndpoint());

        SignatureSigningParameters signatureSigningParameters = new SignatureSigningParameters();
        signatureSigningParameters.setSigningCredential(SPCredentials.getPoCredential());
        signatureSigningParameters.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA256);


        loContext.getSubcontext(SecurityParametersContext.class, true).setSignatureSigningParameters(signatureSigningParameters);

        HTTPRedirectDeflateEncoder loEncoder = new HTTPRedirectDeflateEncoder();

        loEncoder.setMessageContext(loContext);
        loEncoder.setHttpServletResponse(toHttpServletResponse);

        try {
            loEncoder.initialize();
        } catch (ComponentInitializationException loCIEx) {
            throw new RuntimeException(loCIEx);
        }

        System.out.println("AuthnRequest: ");
        OpenSAMLUtils.logSAMLObject(toAuthnRequest);

        System.out.println("Redirecting to IDP");
        try {
            loEncoder.encode();
        } catch (MessageEncodingException loMEEx) {
            throw new RuntimeException(loMEEx);
        }
    }

    private AuthnRequest buildAuthnRequest() throws Exception {
        AuthnRequest loAuthnRequest = OpenSAMLUtils.buildSAMLObject(AuthnRequest.class);
        loAuthnRequest.setIssueInstant(new DateTime());
        loAuthnRequest.setDestination(getIPDSSODestination());
        loAuthnRequest.setProtocolBinding(SAMLConstants.SAML2_POST_BINDING_URI);//.SAML2_ARTIFACT_BINDING_URI);
        loAuthnRequest.setAssertionConsumerServiceURL(getAssertionConsumerEndpoint());
        loAuthnRequest.setID(OpenSAMLUtils.generateSecureRandomId());
        loAuthnRequest.setIssuer(buildIssuer());
        loAuthnRequest.setNameIDPolicy(buildNameIdPolicy());
        loAuthnRequest.setRequestedAuthnContext(buildRequestedAuthnContext());

        return loAuthnRequest;
    }
        
    private RequestedAuthnContext buildRequestedAuthnContext() {
        RequestedAuthnContext loRequestedAuthnContext = OpenSAMLUtils.buildSAMLObject(RequestedAuthnContext.class);
        loRequestedAuthnContext.setComparison(AuthnContextComparisonTypeEnumeration.EXACT);//MINIMUM);

        AuthnContextClassRef loPasswordAuthnContextClassRef = OpenSAMLUtils.buildSAMLObject(AuthnContextClassRef.class);
        loPasswordAuthnContextClassRef.setAuthnContextClassRef(AuthnContext.PASSWORD_AUTHN_CTX);

        loRequestedAuthnContext.getAuthnContextClassRefs().add(loPasswordAuthnContextClassRef);

        return loRequestedAuthnContext;

    }

    private NameIDPolicy buildNameIdPolicy() {
        NameIDPolicy loNameIDPolicy = OpenSAMLUtils.buildSAMLObject(NameIDPolicy.class);
        loNameIDPolicy.setAllowCreate(true);

        loNameIDPolicy.setFormat(NameIDType.TRANSIENT);

        return loNameIDPolicy;
    }

    private Issuer buildIssuer() {
        Issuer loIssuer = OpenSAMLUtils.buildSAMLObject(Issuer.class);
        loIssuer.setValue(getSPIssuerValue());

        return loIssuer;
    }

    private String getSPIssuerValue() {
        return SSOParameters.psSP_ENTITY_ID;
    }

    private String getAssertionConsumerEndpoint() throws Exception {
        return SSOParameters.getAssertionConsumerServiceURL();
    }

    private String getIPDSSODestination() throws Exception {
        return SSOParameters.getIDPSAMLSSOServiceURL();
    }

    private Endpoint getIPDEndpoint() throws Exception {
        SingleSignOnService loEndpoint = OpenSAMLUtils.buildSAMLObject(SingleSignOnService.class);
        loEndpoint.setBinding(SAMLConstants.SAML2_POST_BINDING_URI);//.SAML2_REDIRECT_BINDING_URI);
        loEndpoint.setLocation(getIPDSSODestination());

        return loEndpoint;
    }

    private boolean validatePage(HttpServletRequest toReq) {
        boolean lbValidated = false;
        
        HttpSession loSes = toReq.getSession();
        String lsPg = getPage(toReq.getPathInfo());
        System.out.println("Pagina: " + lsPg);
        
        UserMenuBean loMnu = (UserMenuBean)loSes.getAttribute("UserMenuBean");
        System.out.println("BEAN: " + loMnu);
        // No está el usuario en sesión
        if(loMnu == null) {
            return false;
        }
        /*
        String lsPrm = Constants.poPgsPrms.get(lsPg);
        System.out.println("Prm: " + lsPrm);
        // La página no está asociada a ningún permiso
        if(lsPrm == null || lsPrm.trim().equals("")) {
            return false;
        }
        lbValidated = loMnu.getPoPrms().get(lsPrm);
        System.out.println("Tiene permiso? " + lbValidated);
        */
        return lbValidated;
    }
}
