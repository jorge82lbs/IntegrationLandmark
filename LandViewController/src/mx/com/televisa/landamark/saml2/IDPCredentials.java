package mx.com.televisa.landamark.saml2;

import org.opensaml.security.credential.Credential;
import org.opensaml.security.credential.CredentialSupport;
import org.opensaml.security.crypto.KeySupport;
import java.security.*;

public class IDPCredentials {
    private static final Credential poCredential;

    static {
        poCredential = generateCredential();
    }

    private static Credential generateCredential() {
        try {
            KeyPair loKeyPair = KeySupport.generateKeyPair("RSA", 2048/*1024*/, null);
            return CredentialSupport.getSimpleCredential(loKeyPair.getPublic(), loKeyPair.getPrivate());
        } catch (NoSuchAlgorithmException loNSAEx) {
            throw new RuntimeException(loNSAEx);
        } catch (NoSuchProviderException loNSPEx) {
            throw new RuntimeException(loNSPEx);
        }
    }

    public static Credential getPoCredential() {
        return poCredential;
    }
}
