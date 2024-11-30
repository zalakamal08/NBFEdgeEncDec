package com.mycompany.nbfedgeencdec;

 
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import javax.crypto.spec.SecretKeySpec; 

public class AESGCM {
    
    public static String Encryption(String Payload, String Key) throws Exception {
        
        String hexIV = "0".repeat(24);
        
        Payload = asciiToBase64(Payload);
        byte[] plaintext = Base64.getDecoder().decode(Payload);
        byte[] keyBytes = Key.getBytes(StandardCharsets.US_ASCII);
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        byte[] ivBytes = hexStringToByteArray(hexIV);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, ivBytes);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);
        byte[] encryptedData = cipher.doFinal(plaintext);
        return hexIV+Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String Decryption(String Payload, String Key) throws Exception {
        
        String hexIV = Payload.substring(0,24);
        Payload = Payload.substring(24);
        
        byte[] encryptedData = Base64.getDecoder().decode(Payload);
        byte[] keyBytes = Key.getBytes(StandardCharsets.US_ASCII);
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        byte[] ivBytes = hexStringToByteArray(hexIV);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, ivBytes);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
        byte[] decryptedData = cipher.doFinal(encryptedData);
        
        
        return new String(decryptedData);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String asciiToBase64(String asciiString) {
        byte[] asciiBytes = asciiString.getBytes();
        return Base64.getEncoder().encodeToString(asciiBytes);
    }
    
    public static void main(String[] args) {
        
    }
    
}
