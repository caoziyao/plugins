package com.zel.commonutils.crypto;

import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.Cipher;
import java.security.Key;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 *   javax. crypto.Cipher 每次都要实例化，大量的实例化导致 Cipher 实例化失败。
 *   产生了java.lang.IllegalStateException:Cipher not initialized问题
 * @author csy
 * @version 1.0.0
 * @since 2020/3/20
 */
public class AESEncrypt extends DigestCommon {

    /**
     * 对称加解密DES密钥Key
     */
    // public final static String KEY = "VTLiISh6CzlRGd15";
    //private String KEY = null;
    private static Cipher mEncryptCipher = null;
    private static Cipher mDecryptCipher = null;

    private static ConcurrentHashMap<String, AESEncrypt> Map = new ConcurrentHashMap<>();

    private AESEncrypt(String key) throws Exception {
        //this.KEY = key;
        //初始化加密和解密密码提供类
        mEncryptCipher = Cipher.getInstance("DES");
        mEncryptCipher.init(Cipher.ENCRYPT_MODE, getKey(key.getBytes()));
        mDecryptCipher = Cipher.getInstance("DES");
        mDecryptCipher.init(Cipher.DECRYPT_MODE, getKey(key.getBytes()));
    }

    public static AESEncrypt getInstance(String key) throws Exception {
        if (Map.containsKey(key)) {
            return Map.get(key);
        } else {
            AESEncrypt aes = new AESEncrypt(key);
            Map.put(key, aes);
            return aes;
        }
    }

    /**
     * 对 字符串 加密
     */
    public String encrypt(String strIn) throws Exception {
        return byte2HexStr(encrypt(strIn.getBytes()));
    }


    /**
     * 对 字节数组 加密
     */
    public byte[] encrypt(byte[] arrB) throws Exception {
        return mEncryptCipher.doFinal(arrB);
    }

    /**
     * 解密 字符串
     */
    public String decrypt(String strIn) throws Exception {
        return new String(decrypt(hexStr2Byte(strIn)));
    }


    /**
     * 解密 字节数组
     */
    public byte[] decrypt(byte[] arrB) throws Exception {
        return mDecryptCipher.doFinal(arrB);
    }

    /**
     * 解密用的密钥（字节数组）长度必须为8个字节否则返回null, 不足8位时后面补0，超出8位只取前8位
     *
     * @param arrBTmp 构成该字符串的字节数组
     * @return 生成的密钥
     * @throws Exception
     */

    private Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];

        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        // 生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

        return key;
    }


    public static void main(String[] args) {
        try {
            String KEY = RandomStringUtils.randomAlphanumeric(16);
            System.out.println("KEY: " + KEY);
            String text = "cado1836ads44";
            System.out.println("加密前：" + text);

            AESEncrypt des = AESEncrypt.getInstance(KEY);
            String pwd = des.encrypt(text);
            System.out.println("加密后：" + pwd);
            pwd = des.decrypt(pwd);
            System.out.println("解密密后：" + pwd);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
