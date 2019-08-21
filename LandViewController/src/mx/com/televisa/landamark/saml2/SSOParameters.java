package mx.com.televisa.landamark.saml2;

import mx.com.televisa.landamark.model.daos.ViewObjectDao;

public class SSOParameters {
    public static final String psSP_ENTITY_ID = "IntegracionesLMK";
    public static final String psAUTHENTICATED_SESSION_ATTRIBUTE = "authenticated";
    public static final String psGOTO_URL_SESSION_ATTRIBUTE = "gotoURL";
    public static final String psARTIFACT_RESOLUTION_SERVICE = "http://localhost:8080/webprofile-ref-project/idp/artifactResolutionService";

    public static final String psEMAIL_ATT_NAME = "http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress";
    public static final String psOBJECT_ID_ATT_NAME = "http://schemas.microsoft.com/identity/claims/objectidentifier";

    public static String getAssertionConsumerServiceURL() throws Exception {
        return getParameterVal("ASSERTION_CONSUMER");
        //return getParameterVal(Constants.psACS_PARAM);
        //return "https://192.168.132.1:7102/PgmLandmarkApp/pgmlandmarkacs";
        //return "https://192.168.132.1:7102/IntegracionesLMK/integracioneslmkacs";
    }
    
    public static String getIDPEntityId() throws Exception {
        return getParameterVal("ENTITY_ID");
        //return getParameterVal(Constants.psIDP_ENTITY_ID_PARAM);
        //return "IntegracionesLMK";
    }
    
    public static String getIDPSAMLSSOServiceURL() throws Exception {
        return getParameterVal("URL_LOGIN");
        //return getParameterVal(Constants.psIDP_SSO_SAML2_URL_PARAM);
        //url de login
        //return "https://login.microsoftonline.com/87e71bd2-2a6d-4deb-8dca-d9b3fd7481b9/saml2";
    }
    
    public static String getIDPLogoutURL() throws Exception {
        return getParameterVal("URL_LOGOUT");
        //return getParameterVal(Constants.psIDP_LOGOUT_URL_PARAM);
        //return "https://login.windows.net/87e71bd2-2a6d-4deb-8dca-d9b3fd7481b9/oauth2/logout";
    }
    /*
    private static String getParameterVal(String tsName) throws Exception {
        String lsACS;        
        ViewObjectDao loViewObjectDao = new ViewObjectDao();
        lsACS = loViewObjectDao.getValueByNomParameter(tsName);
        return lsACS;
    }*/
    
    private static String getParameterVal(String tsName) throws Exception {
        String lsACS;        
        ViewObjectDao loViewObjectDao = new ViewObjectDao();
        lsACS = loViewObjectDao.getValueGeneralParameter(tsName,"SAML2_AUTHENTICATION");
        return lsACS;
    }
}
