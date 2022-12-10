package com.works.services;

import com.google.crypto.tink.subtle.AesGcmJce;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@PropertySource("classpath:application.properties")
public class TinkEncDec {

    @Value("${key128Bit}")
    private String key128Bit;

    @Value("${associatedData}")
    private String associatedData;

    public String encrypt( String plainText ) {
        String stringEncrypt = "";
        try {

            AesGcmJce aesGcmJce = new AesGcmJce(key128Bit.getBytes());
            byte[] byteEncrypt = aesGcmJce.encrypt(plainText.getBytes(), associatedData.getBytes());
            stringEncrypt = new String(byteEncrypt, "ISO-8859-1");
            stringEncrypt = Base64.getEncoder().encodeToString(stringEncrypt.getBytes());

        } catch (Exception e) {
            System.err.println("encrypt Error :" + e);
        }
        return stringEncrypt;
    }


    public String decrypt( String cipherText ) {
        String stringDecrypt = "";
        try {
            byte[] bytes = Base64.getDecoder().decode(cipherText);
            cipherText = new String(bytes);
            byte[] convertEncode = cipherText.getBytes("ISO-8859-1");
            AesGcmJce aesGcmJce = new AesGcmJce(key128Bit.getBytes());
            byte[] descBytes = aesGcmJce.decrypt(convertEncode, associatedData.getBytes());
            stringDecrypt = new String(descBytes);

        } catch (Exception e) {
            System.err.println("decrypt Error : " + e);
        }
        return stringDecrypt;
    }

}
