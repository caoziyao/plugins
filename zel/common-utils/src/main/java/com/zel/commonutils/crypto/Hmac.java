package com.zel.commonutils.crypto;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/3/20
 */
public class Hmac {

    public static final String KEY_MAC = "HmacMD5";
    public static final String KEY_MAC_SHA256 = "HmacSHA256";

    /**
     * 初始化HMAC密钥
     *
     * @return
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64Util.encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     *
     * @param data 需要加密的字节数组
     * @param key  密钥
     * @return 字节数组
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(Base64Util.decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        String inputStr = "这是一个测试字符串aaabbbccc111222333";
        System.out.println("原始数据：" + inputStr);

        String key = initMacKey();
        System.out.println("Mac密钥:\n" + key);
        byte[] inputData = inputStr.getBytes();

        BigInteger mac = new BigInteger(encryptHMAC(inputData, inputStr));
        System.out.println("HMAC:\n" + mac.toString(16));
    }

}
