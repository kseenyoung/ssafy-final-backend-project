package com.ssafy.api.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.http.HttpStatus;

public class Encryption {
    public static String encrypt(String planText) throws MyException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(planText.getBytes());
            byte[] byteData = md.digest();
            StringBuffer sb = new StringBuffer();

            for(int i=0; i< byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 255) + 256, 16).substring(1));
            }

            StringBuffer hexString = new StringBuffer();

            for(int i=0; i<byteData.length; ++i) {
                String hex = Integer.toHexString(255 & byteData[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();

        } catch(Exception e) {
            e.printStackTrace();
            throw new MyException("암호화 실패", HttpStatus.BAD_REQUEST);
        }

    }


}
