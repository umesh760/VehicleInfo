package com.example.vehicleinfo;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.AlgorithmParameters;
import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    final static String IV = "IV";
    final static String CIPHERTEXT = "CIPHERTEXT";

    /**
     * Generates a 128 bit AES key from password and salt.
     */
    public static SecretKeySpec generateKey(char[] password, byte[] salt) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(password, salt, 1024, 128);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(tmp.getEncoded(), "AES");
        return secret;
    }

    /**
     * Encrypts the clear text using the previously generated AES key.
     */
    public static Map encrypt(String cleartext, byte[] iv, SecretKeySpec secret) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // If the IvParameterSpec argument is omitted (null), a new IV will be created
        cipher.init(Cipher.ENCRYPT_MODE, secret,
                iv == null ? null : new IvParameterSpec(iv));
        AlgorithmParameters params = cipher.getParameters();
        byte[] usediv = params.getParameterSpec(IvParameterSpec.class).getIV();
        byte[] ciphertext = cipher.doFinal(cleartext.getBytes("UTF-8"));
        Map result = new HashMap();
        result.put(IV, usediv);
        result.put(CIPHERTEXT, ciphertext);
        return result;
    }

    /**
     * Decrypts the cipher text using the previously generated AES key and IV.
     */
    public static String decrypt(byte[] ciphertext, byte[] iv, SecretKeySpec secret) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
        String plaintext = new String(cipher.doFinal(ciphertext), "UTF-8");
        return plaintext;
    }

    public static void main(String[] args) throws Exception {
        byte[] salt = new byte[]{-84, -119, 25, 56, -100, 100, -120, -45, 84, 67, 96, 10, 24, 111, 112, -119, 3};
        byte[] interop_iv = Base64.decode("TZuWY0W5Yn9l9F2DEiU0hg==");
        byte[] iv = null;
        byte[] ciphertext;
        SecretKeySpec secret;
        secret = generateKey("sOme*ShaREd*SecreT".toCharArray(), salt);
        Map result = encrypt("*** Top secret ***", iv, secret);
        ciphertext = (byte[])result.get(CIPHERTEXT);
        iv = (byte[])result.get(IV);
        System.out.println("Cipher text:" + Base64.encode(ciphertext));
        System.out.println("IV:" + Base64.encode(iv) + " (" + iv.length + "bytes)");
        System.out.println("Key:" + Base64.encode(secret.getEncoded()));
        System.out.println("Deciphered: " + decrypt(ciphertext, iv, secret));
        
        // Interop demonstration. Using a fixed IV that is used in the C# example
        result = encrypt("*** Top secret ***", interop_iv, secret);
        ciphertext = (byte[])result.get(CIPHERTEXT);
        iv = (byte[])result.get(IV);
        System.out.println();
        System.out.println("--------------------------------");
        System.out.println("Interop test - using a static IV");
        System.out.println("The data below should be used to retrieve the secret message by the receiver");
        System.out.println("Cipher text:  " + Base64.encode(ciphertext));
        System.out.println("IV:           " + Base64.encode(iv));
    }
}