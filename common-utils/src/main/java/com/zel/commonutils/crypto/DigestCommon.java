package com.zel.commonutils.crypto;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/4/20
 */
public class DigestCommon {

    /**
     * HEX转码 String to Byte
     */
    public static byte[] hexStr2Byte(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    /**
     * HEX转码 Byte to  String
     */
    public static String byte2HexStr(byte[] arrB) {
        //int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        //StringBuffer sb = new StringBuffer(iLen * 2);
        //for (int i = 0; i < iLen; i++) {
        //    int intTmp = arrB[i];
        //    // 把负数转换为正数
        //    while (intTmp < 0) {
        //        intTmp = intTmp + 256;
        //    }
        //    // 小于0F的数需要在前面补0
        //    if (intTmp < 16) {
        //        sb.append("0");
        //    }
        //    sb.append(Integer.toString(intTmp, 16));
        //}
        // return sb.toString();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arrB.length; ++i) {
            sb.append(Integer.toHexString((arrB[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }
}
