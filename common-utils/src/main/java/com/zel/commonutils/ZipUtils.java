package com.zel.commonutils;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.LinkedList;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

/**
 * 数据压缩
 */
public class ZipUtils {

    private final static int BUFFER = 1024;

    /**
     * 数据压缩
     *
     * @param data 字符串对象
     * @return
     * @throws Exception
     */
    public static String gzip(String data) throws Exception {
        byte[] buff = data.getBytes("UTF-8");
        return ZipUtils.encryptBASE64(gzip(buff));
    }

    /**
     * 数据压缩
     *
     * @param data 字符串字节数组
     * @return
     * @throws Exception
     */
    private static byte[] gzip(byte[] data) throws Exception {
        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = null;
        byte[] output = null;
        try {
            bais = new ByteArrayInputStream(data);
            baos = new ByteArrayOutputStream();

            // 压缩
            gzip(bais, baos);
            output = baos.toByteArray();
            baos.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != baos) {
                baos.close();
            }
            if (null != bais) {
                bais.close();
            }
        }

        return output;
    }

    /**
     * 数据压缩
     *
     * @param is 输入流
     * @param os 输出流
     * @throws Exception
     */
    private static void gzip(InputStream is, OutputStream os) throws Exception {
        GZIPOutputStream gos = null;
        try {
            gos = new GZIPOutputStream(os);

            int count;
            byte data[] = new byte[BUFFER];
            while ((count = is.read(data, 0, BUFFER)) != -1) {
                gos.write(data, 0, count);
            }
            gos.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != gos) {
                gos.close();
            }
        }
    }

    /**
     * 数据解压缩
     *
     * @param data 已压缩字符串
     * @return
     * @throws Exception
     */
    public static String gunzip(String data) throws Exception {
        byte[] buff = ZipUtils.decryptBASE64(data);
        return new String(gunizp(buff), "UTF-8");
    }

    /**
     * 数据解压缩
     *
     * @param data
     * @param charset
     * @return
     * @throws Exception
     */
    public static String gunzip(String data, String charset) throws Exception {
        byte[] buff = ZipUtils.decryptBASE64(data);
        return new String(gunizp(buff), charset);
    }

    /**
     * 数据解压缩
     *
     * @param data 待解压缩字节数组
     * @return
     * @throws Exception
     */
    private static byte[] gunizp(byte[] data) throws Exception {
        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = null;
        try {
            bais = new ByteArrayInputStream(data);
            baos = new ByteArrayOutputStream();

            // 解压缩
            gunizp(bais, baos);
            data = baos.toByteArray();
            baos.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != baos) {
                baos.close();
            }
            if (null != bais) {
                bais.close();
            }
        }
        return data;
    }

    /**
     * 数据解压缩
     *
     * @param is
     * @param os
     * @throws Exception
     */
    private static void gunizp(InputStream is, OutputStream os) throws Exception {

        GZIPInputStream gis = null;
        try {
            gis = new GZIPInputStream(is);

            int count;
            byte data[] = new byte[BUFFER];
            while ((count = gis.read(data, 0, BUFFER)) != -1) {
                os.write(data, 0, count);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != gis) {
                gis.close();
            }
        }
    }

    /**
     * BASE64解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    private static byte[] decryptBASE64(String data) throws Exception {
        return Base64.decodeBase64(data);
    }

    /**
     * BASE64加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    private static String encryptBASE64(byte[] data) throws Exception {
//        return Base64.encodeBase64String(data).replace("[\\s*\t\n\r]", "");
        return Base64.encodeBase64String(data);
    }


    public static void main(String[] args) throws Exception {
        System.out.println(ZipUtils.gzip("123"));
        System.out.println(ZipUtils.gunzip("H4sIAAAAAAAAADM0MgYA0mNIiAMAAAA=", "UTF-8"));
    }
}
