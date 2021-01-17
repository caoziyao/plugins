package com.zel.commonutils.crypto;

import java.util.Base64;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/18
 */
public class Base64Util {


    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) {
        Base64.Decoder decoder = Base64.getMimeDecoder();
        return decoder.decode(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) {
        Base64.Encoder encoder = Base64.getMimeEncoder();
        return encoder.encodeToString(key);
    }

    public static void main(String[] args) {

    }
}
