package com.ssafy.api.utils;

import com.ssafy.api.exception.MyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public class SHAEncryption {
//    public static String encrypt(String planText) throws MyException {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            md.update(planText.getBytes());
//            byte[] byteData = md.digest();
//            StringBuffer sb = new StringBuffer();
//
//            for(int i=0; i< byteData.length; i++) {
//                sb.append(Integer.toString((byteData[i] & 255) + 256, 16).substring(1));
//            }
//
//            StringBuffer hexString = new StringBuffer();
//
//            for(int i=0; i<byteData.length; ++i) {
//                String hex = Integer.toHexString(255 & byteData[i]);
//                if(hex.length() == 1) {
//                    hexString.append('0');
//                }
//
//                hexString.append(hex);
//            }
//
//            return hexString.toString();
//
//        } catch(Exception e) {
//            e.printStackTrace();
//            throw new MyException("암호화 실패", HttpStatus.BAD_REQUEST);
//        }
//
//    }

    public static String encrypt(String source, String salt) {
        byte byteData[] = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(source.getBytes());
            md.update(salt.getBytes());
            byteData = md.digest();
            System.out.println("원문: " + source + "   SHA-256: " + byteData.length + "," + byteArrayToHex(byteData));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return byteArrayToHex(byteData);
    }

    public static String genSalt() {
        UUID salt = UUID.randomUUID();
        System.out.println("salt : " + salt);
        return salt.toString();
    }

    public static String byteArrayToHex(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    }



}
