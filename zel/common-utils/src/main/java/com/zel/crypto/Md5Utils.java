package com.zel.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/4/20
 */
public class Md5Utils extends DigestCommon{

    public static String md5(String md5) {
        java.security.MessageDigest md = null;
        try {
            md =  MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (md == null) {
            return null;
        }

        byte[] array = md.digest(md5.getBytes());
        return DigestCommon.byte2HexStr(array);
    }

    public static void main(String[] args) {
        // DigestUtils.md5(write)
        // 900150983cd24fb0d6963f7d28e17f72
        String abc = Md5Utils.md5("abc");
        System.out.println(abc);
    }
}
