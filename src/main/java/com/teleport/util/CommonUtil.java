package com.teleport.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@Component
@Slf4j
public class CommonUtil {

    /**
     * Method for unique hash generation based on data
     * @param data
     * @param length
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String generateTrackingHash(String data, int length) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] byteArray = md.digest(data.getBytes(StandardCharsets.UTF_8));

        String hex = HexFormat.of().formatHex(byteArray);
        if(length>0){
            return hex.substring(0, length).toUpperCase();
        }

        return hex.substring(0, 4).toUpperCase();
    }

}
