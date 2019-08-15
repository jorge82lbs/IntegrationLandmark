package mx.com.televisa.landamark.saml2;

import java.io.InputStream;

import java.security.KeyStore;

import java.util.HashMap;
import java.util.Map;

import net.shibboleth.utilities.java.support.resolver.CriteriaSet;
import net.shibboleth.utilities.java.support.resolver.Criterion;
import net.shibboleth.utilities.java.support.resolver.ResolverException;

import org.opensaml.core.criterion.EntityIdCriterion;
import org.opensaml.security.credential.Credential;
import org.opensaml.security.credential.impl.KeyStoreCredentialResolver;

public class IDPCredentials2 {

    private static final String psKEY_STORE_PASSWORD = "qweqwe";
    private static final String psKEY_STORE_ENTRY_PASSWORD = "qweqwe";
    private static final String psKEY_STORE_PATH = "azure.jks";
    private static final String psKEY_ENTRY_ID = "sp_key";
    
    private static final Credential poCredential;

    static {
        try {
            KeyStore loKeystore = readKeystoreFromFile(psKEY_STORE_PATH, psKEY_STORE_PASSWORD);
            Map<String, String> loPasswordMap = new HashMap<String, String>();
            loPasswordMap.put(psKEY_ENTRY_ID, psKEY_STORE_ENTRY_PASSWORD);
            KeyStoreCredentialResolver loResolver = new KeyStoreCredentialResolver(loKeystore, loPasswordMap);

            Criterion loCriterion = new EntityIdCriterion(psKEY_ENTRY_ID);
            CriteriaSet loCriteriaSet = new CriteriaSet();
            loCriteriaSet.add(loCriterion);

            poCredential = loResolver.resolveSingle(loCriteriaSet);

        } catch (ResolverException loREx) {
            throw new RuntimeException("Something went wrong reading credentials", loREx);
        }
    }
    
    private static KeyStore readKeystoreFromFile(String toPathToKeyStore, String toKeyStorePassword) {
        try {
            KeyStore loKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream loIS = SPCredentials.class.getResourceAsStream(toPathToKeyStore);
            loKeyStore.load(loIS, toKeyStorePassword.toCharArray());
            loIS.close();
            return loKeyStore;
        } catch (Exception loEx) {
            loEx.printStackTrace();
            throw new RuntimeException("Something went wrong reading keystore", loEx);
        }
    }

    public static Credential getPoCredential() {
        System.out.println("IDPCredentials2.getPoCredential");
        return poCredential;
    }
}
