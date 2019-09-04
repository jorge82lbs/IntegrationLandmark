package mx.com.televisa.landamark.saml2;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import mx.com.televisa.landamark.client.userpermission.types.Usuario;
import mx.com.televisa.landamark.model.daos.ViewObjectDao;
import mx.com.televisa.landamark.secman.SecurityManagerWs;
import mx.com.televisa.landamark.users.UserInfoBean;
import mx.com.televisa.landamark.users.UserMenuBean;
import mx.com.televisa.landamark.users.UserOperationList;

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

@WebServlet(name = "ACSServlet", urlPatterns = { "/integracioneslmkacs" })
public class ACSServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig toConfig) throws ServletException {
        super.init(toConfig);
    }

    public void doGet(HttpServletRequest toReq,
                      HttpServletResponse toResp) throws ServletException,
                                                           IOException {
        String lsCPath = toReq.getContextPath();
        toResp.sendRedirect(lsCPath + "/faces/indexPage");
        
        //doPost(toReq,toResp);
    }

    @Override
    protected void doPost(HttpServletRequest toReq,
                          HttpServletResponse toResp) throws ServletException,
                                                                          IOException {
        try {
            String loResponseMessage = toReq.getParameter("SAMLResponse");
            //System.out.println("SAMLResponse: " + loResponseMessage);
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
            //System.out.println();
            //EncryptedAssertion loEncryptedAssertion = loResponse.getEncryptedAssertions().get(0);
            
            //Assertion loAssertion = decryptAssertion(loEncryptedAssertion);
            Assertion loAssertion = loResponse.getAssertions().get(0);
            verifyAssertionSignature(loAssertion);
            //System.out.println("Decrypted Assertion: ");
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
                //System.out.println("Email: "+lsEmail);
                try {
                    lsUser = getEmailPrefix(lsEmail);
                } catch (Exception loEx) {
                    loEx.printStackTrace();
                }
                //System.out.println("username: " + lsUser);
                
                //========Cargar en session los valores necesarios para el uso de la aplicacion=====
                System.out.println("========Cargar en session los valores necesarios para el uso de la aplicacion=====");
                try{
                    Usuario loUserIntegration = getSecmanUserPermission(lsUser);
                    if(loUserIntegration != null){
                    //if(true){
                        //Settear Datos--------------------------
                        //FacesContext        loContext = FacesContext.getCurrentInstance();
                        //ExternalContext     loEctx = loContext.getExternalContext();        
                        //HttpServletRequest  loRequest = toReq;//(HttpServletRequest)loEctx.getRequest();
                        //HttpSession         loSession = loRequest.getSession(true);
                        HttpSession         loSession = toReq.getSession(true);
                        loSession.setAttribute("session.pgmIntegration", "true");
                        //UserInfoBean        loUserInfo = 
                          //  (UserInfoBean) new UtilFaces().resolveExpression("#{UserInfoBean}");  
                        UserInfoBean        loUserInfo = new UserInfoBean();
                        DateFormat          ldDateFormat = 
                            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Date                ldDate = new Date();                    
                        /*
                        loUserInfo.setPsUserFullName("Bautista Santiago Jorge Luis");//loUserIntegration.getNomMostrar().getNomMostrar());
                        loUserInfo.setPsEmail("jlbautistas@teleevisa.com.mx");//loUserIntegration.getMailUsuario().getMailUsuario());
                        loUserInfo.setPsIdUser("666");//loUserIntegration.getIdUsuario().getIdUsuario());
                        loUserInfo.setPsUserName("jlbautistas");//loUserIntegration.getUserName().getUserName());
                        */
                        loUserInfo.setPsUserFullName(loUserIntegration.getNomMostrar().getNomMostrar());
                        loUserInfo.setPsEmail(loUserIntegration.getMailUsuario().getMailUsuario());
                        loUserInfo.setPsIdUser(loUserIntegration.getIdUsuario().getIdUsuario());
                        loUserInfo.setPsUserName(loUserIntegration.getUserName().getUserName());
                        loUserInfo.setPsDateTimeLogin(ldDateFormat.format(ldDate));
                        loUserInfo.setPsToken("SAML2_AUTHENTICATED");
                        loSession.setAttribute("loggedPgmIntegrationUser", loUserInfo.getPsUserName());                             
                        loSession.setAttribute("loggedPgmIntegrationIdUser", loUserInfo.getPsIdUser()); 
                        
                        loSession.setAttribute("UserInfoBean", loUserInfo);
                        
                        UserMenuBean loUserMenuBean = getUserMenuBean(lsUser, loSession);
                        System.out.println("=================================================================");
                        //Agregar informacion de Cortes y Programa
                        loSession.setAttribute("idServiceCortes", loUserMenuBean.getLsUserIdServiceCortes());
                        loSession.setAttribute("listChannelsCortes", loUserMenuBean.getLsUserListChannelsCortes());                             
                        
                        //Agregar informacion de Actualizacion de Precios
                        loSession.setAttribute("idServicePrecios", loUserMenuBean.getLsUserIdServicePrecios());
                        loSession.setAttribute("listChannelsPrecios", loUserMenuBean.getLsUserListChannelsPrecios());                             
                    }
                } catch (IOException loEx) {
                    System.out.println("Erro al validar en secman 33 "+loEx.getMessage());
                } catch (Exception loExp) {
                    System.out.println("Erro al validar en secman 323 "+loExp.getMessage());
                }
                //==================================================================================
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

        //System.out.println("Artifact received");
        
        Artifact loArtifact = buildArtifactFromRequest(toReq);
        //System.out.println("Artifact: " + loArtifact.getArtifact());

        ArtifactResolve loArtifactResolve = buildArtifactResolve(loArtifact);
        //System.out.println("Sending ArtifactResolve");
        //System.out.println("ArtifactResolve: ");
        OpenSAMLUtils.logSAMLObject(loArtifactResolve);

        ArtifactResponse loArtifactResponse = sendAndReceiveArtifactResolve(loArtifactResolve, toResp);
        //System.out.println("ArtifactResponse received");
        //System.out.println("ArtifactResponse: ");
        OpenSAMLUtils.logSAMLObject(loArtifactResponse);

        validateDestinationAndLifetime(loArtifactResponse, toReq);

        EncryptedAssertion loEncryptedAssertion = getEncryptedAssertion(loArtifactResponse);
        
        
        Assertion loAssertion = decryptAssertion(loEncryptedAssertion);
        verifyAssertionSignature(loAssertion);
        //System.out.println("Decrypted Assertion: ");
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
        //String lsGoToURL = (String)toReq.getSession().getAttribute(SSOParameters.psGOTO_URL_SESSION_ATTRIBUTE);
        //String lsGoToURL = "faces/homePage";
        //System.out.println("Redirecting to requested URL: " + lsGoToURL);
        try {
            String lsCPath = toReq.getContextPath();
            System.out.println("CPath redirect>>>: " + lsCPath);
            toResp.sendRedirect(lsCPath + "/faces/homePage");
//            toResp.sendRedirect(lsGoToURL);
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
            System.out.println("01: SSOParameters.psARTIFACT_RESOLUTION_SERVICE{"+SSOParameters.psARTIFACT_RESOLUTION_SERVICE+"}");
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
        System.out.println("(02) SSOParameters.psARTIFACT_RESOLUTION_SERVICE{"+SSOParameters.psARTIFACT_RESOLUTION_SERVICE+"}");
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
    
    
    /**
     * Obtiene permisos de Usuario en Security Manager 
     * @autor Jorge Luis Bautista Santiago
     * @return Usuario
     */
    public Usuario getSecmanUserPermission(String tsUserName) 
    throws Exception {
        Usuario          lsResponse = null;        
        SecurityManagerWs loSecMan = new SecurityManagerWs();
        try {
            System.out.println("Instanciando secman para permisos");
            lsResponse = 
                loSecMan.getSecmanUserDataSession(tsUserName,
                                              "IntegrationLandmark");
        } catch (Exception loEx) {
            System.out.println("error secman 567");
            throw new Exception(loEx.getMessage());
        }
        return lsResponse;
    }
    
    /**
     * Obtiene operaciones de Usuario en Security Manager 
     * @autor Jorge Luis Bautista Santiago
     * @return UserMenuBean
     */
    public UserMenuBean getUserMenuBean(String tsUserName, HttpSession toSess) throws Exception {                
        System.out.println("Dentro de getUserMenuBean");
        String lsChannels = "";
        UserOperationList        loUserOperationList = new UserOperationList();        
            //(UserOperationList) new UtilFaces().resolveExpression("#{UserOperationList}");
        loUserOperationList.setLsUserName(tsUserName);
        List<String> laList = new ArrayList<String>();
        
        UserMenuBean        loMenu = new UserMenuBean();        
            //(UserMenuBean) new UtilFaces().resolveExpression("#{UserMenuBean}");
        String              lsFlag = "false";
        //lsFlag = "true";
        
        loMenu.setLsPantallaBitacora(lsFlag);
        loMenu.setLsPantallaGralConfig(lsFlag);
        loMenu.setLsPantallaLoadFile(lsFlag);
        loMenu.setLsPantallaMapping(lsFlag);
        loMenu.setLsPantallaMonitor(lsFlag);
        loMenu.setLsPantallaNotifications(lsFlag);
        loMenu.setLsPantallaProcess(lsFlag);
        loMenu.setLsPantallaStatusFiles(lsFlag);
        loMenu.setLsPantallaCreateFile(lsFlag);
        loMenu.setLsPantallaPrecios(lsFlag);
        
        loMenu.setLsOprDeleteCron("true");
        loMenu.setLsOprExecuteCron("true");
        loMenu.setLsOprInitStopCron("true");
        loMenu.setLsOprInsertCron("true");
        
        List<String>        laOperaciones = 
            getSecmanUserOperations(tsUserName);
        for (int liI = 0; liI < laOperaciones.size(); liI++) {
            //System.out.println("####### Operation["+laOperaciones.get(liI)+"] ######");
            lsFlag = "true";
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaBitacora"))
                loMenu.setLsPantallaBitacora(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaGralConfig"))
                loMenu.setLsPantallaGralConfig(lsFlag);                            
                                        
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaLoadFile"))
                loMenu.setLsPantallaLoadFile(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaMapping"))
                loMenu.setLsPantallaMapping(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaMonitor"))
                loMenu.setLsPantallaMonitor(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaNotifications"))
                loMenu.setLsPantallaNotifications(lsFlag);
            
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaProcess"))
                loMenu.setLsPantallaProcess(lsFlag);                        
            
            if (laOperaciones.get(liI).equalsIgnoreCase("PantallaStatusFiles")){
                loMenu.setLsPantallaStatusFiles(lsFlag);    
            }
        if (laOperaciones.get(liI).equalsIgnoreCase("PantallaCreationFiles")){
            loMenu.setLsPantallaCreateFile(lsFlag);    
        }
        if (laOperaciones.get(liI).equalsIgnoreCase("PantallaPriceFiles")){
            loMenu.setLsPantallaPrecios(lsFlag);    
        }
            if(laOperaciones.get(liI).startsWith("Ch-")){
                String[] laChn = laOperaciones.get(liI).split("-");
                if(laChn.length > 1){
                    laList.add(laChn[1]);
                    //System.out.println("Canal: "+laChn[1]);
                    lsChannels += "'"+laChn[1]+"',";
                    //System.out.println("Canales: "+laChn[1]);
                    loUserOperationList.setLaOpertations(laList);
                }
            }
            if(lsChannels.length() > 0){
                loMenu.setLsUserListChannelsCortes(lsChannels.substring(0, lsChannels.length()-1));    
                loMenu.setLsUserListChannelsPrecios(lsChannels.substring(0, lsChannels.length()-1));    
            }
                        
        }
        
        //Obtener el idServicio de cortes y programas para el usuario firmado
        List<String> laListCortes = getCortesyProgramasByUser(tsUserName);
        
        if(Integer.parseInt(laListCortes.get(0)) > 0){
            loMenu.setLsUserIdServiceCortes(laListCortes.get(0));
            loMenu.setLsUserFecInicialCortes(laListCortes.get(1));
            loMenu.setLsUserFecFinalCortes(laListCortes.get(2));
            loMenu.setLsUserNomServiceCortes("Generar Archivo de Cortes y Programas");
        }else{
            loMenu.setLsUserIdServiceCortes(laListCortes.get(0));
            loMenu.setLsUserFecInicialCortes("");
            loMenu.setLsUserFecFinalCortes("");
            loMenu.setLsUserNomServiceCortes("Generar Archivo de Cortes y Programas");
        }
        
        //Obtener el idServicio de Actualizacion de Precios para el usuario firmado
        List<String> laListPrecios = getActualizacionPreciosByUser(tsUserName);
        
        if(Integer.parseInt(laListPrecios.get(0)) > 0){
            loMenu.setLsUserIdServicePrecios(laListPrecios.get(0));
            loMenu.setLsUserFecInicialPrecios(laListPrecios.get(1));
            loMenu.setLsUserFecFinalPrecios(laListPrecios.get(2));
            loMenu.setLsUserNomServicePrecios("Generar Archivo de Cortes y Programas");
        }else{
            loMenu.setLsUserIdServicePrecios(laListPrecios.get(0));
            loMenu.setLsUserFecInicialPrecios("");
            loMenu.setLsUserFecFinalPrecios("");
            loMenu.setLsUserNomServicePrecios("Generar Archivo de Cortes y Programas");
        }
                
        toSess.setAttribute("UserOperationList", loUserOperationList);
        toSess.setAttribute("UserMenuBean", loMenu);
        
        return loMenu;
    }
    
    /**
     * Obtiene el idServicio, fecha inicial y fecha final, si es
     * que el usuario ha ejecutado al menos una vez el servicio de Cortes y Programas
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<String> getActualizacionPreciosByUser(String tsUser){        
        List<String> laList = new ArrayList<String>();
        ViewObjectDao loViewObjectDao = new ViewObjectDao();
        laList = loViewObjectDao.getServicePreciosByUser(tsUser);
        return laList;
    }
    
    /**
     * Obtiene el idServicio, fecha inicial y fecha final, si es
     * que el usuario ha ejecutado al menos una vez el servicio de Cortes y Programas
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<String> getCortesyProgramasByUser(String tsUser){        
        List<String> laList = new ArrayList<String>();
        ViewObjectDao loViewObjectDao = new ViewObjectDao();
        laList = loViewObjectDao.getServiceCortesByUser(tsUser);
        return laList;
    }
    
    /**
     * Obtiene operaciones de Usuario en Security Manager 
     * @autor Jorge Luis Bautista Santiago
     * @return List
     */
    public List<String> getSecmanUserOperations(String tsUserName) throws Exception {
        List<String>  lsResponse = new ArrayList<String>();        
        SecurityManagerWs loSecMan = new SecurityManagerWs();
        try {
            lsResponse = 
                loSecMan.getUserOperations(tsUserName,
                                          "IntegrationLandmark");
        } catch (Exception loEx) {
            throw new Exception(loEx.getMessage());
        }
        return lsResponse;
    }

    
}