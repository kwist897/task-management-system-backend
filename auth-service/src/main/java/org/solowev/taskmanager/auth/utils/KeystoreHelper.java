package org.solowev.taskmanager.auth.utils;

import lombok.extern.slf4j.Slf4j;
import org.solowev.taskmanager.auth.exceptions.KeyStoreException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

@Slf4j
@Component
public final class KeystoreHelper {
    /**
     * type of the keystore
     */
    private static final String KEYSTORE_TYPE = "PKCS12";

    @Value("${app.keystore.pass}")
    private String keyStorePass;

    @Value("${app.keystore.alias}")
    private String keyStoreAlias;

    @Value("${app.keystore.file}")
    private String fileName;

    /**
     * Method extracts keys from the keystore
     *
     * @return KeyPair containing private and public keys
     */
    public KeyPair extractKeyPair() {
        try {
            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
            keyStore.load(new ClassPathResource(fileName).getInputStream(), keyStorePass.toCharArray());

            PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyStoreAlias, keyStorePass.toCharArray());
            PublicKey publicKey = keyStore.getCertificate(keyStoreAlias).getPublicKey();

            return new KeyPair(publicKey, privateKey);
        } catch (Exception e) {
            log.error("couldn't load KeyStore");
            throw new KeyStoreException();
        }
    }
}
