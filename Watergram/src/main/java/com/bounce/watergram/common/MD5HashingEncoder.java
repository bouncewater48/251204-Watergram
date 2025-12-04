package com.bounce.watergram.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashingEncoder {

    public static String encode(String message) {

        try {
            MessageDigest  messageDigest = MessageDigest.getInstance("md5");

            byte[] bytes = message.getBytes();

            messageDigest.update(bytes);

            byte[] digest = messageDigest.digest();

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < digest.length; i++) {
                sb.append(Integer.toHexString(digest[i] & 0xff));
            }

            return sb.substring();

        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }


}
