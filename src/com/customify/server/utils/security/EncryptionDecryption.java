package com.customify.server.utils.security;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


/**
 * @author SAMUEL DUSHIMIMANA
 * @role
 * This  CLASS will encrypt texts and decrypt passwords into user readable format ...
 * * */


public class EncryptionDecryption {
    private Cipher cipher;

    private KeyGenerator keyGenerator;

    private SecretKey  secretKey;



    /**
     * @author SAMUEL DUSHIMIMANA
     * @role
     * This  method encrypts texts
     * * */


    public  String encrypt(String plainText)
            throws Exception {
        keyGenerator =  KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // block size is 128bits
        secretKey = keyGenerator.generateKey();
        cipher = Cipher.getInstance("AES"); //SunJCE provider AES algorithm, mode(optional) and padding schema(optional)


        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }


    /**
     * @author SAMUEL DUSHIMIMANA
     * @role
     * This  method decrypt passwords
     * * */

    public  String decrypt(String encryptedText)
            throws Exception {

        keyGenerator =  KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // block size is 128bits
        secretKey = keyGenerator.generateKey();

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        return decryptedText;
    }


    public Cipher getCipher() {
        return cipher;
    }

    public void setCipher(Cipher cipher) {
        this.cipher = cipher;
    }

    public KeyGenerator getKeyGenerator() {
        return keyGenerator;
    }

    public void setKeyGenerator(KeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }
}
