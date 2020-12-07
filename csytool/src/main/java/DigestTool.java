import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description: 摘要算法  MD5、SHA-1、SHA-256、HMAC等
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/26
 */
public class DigestTool {

    private static final String SHA_1 = "SHA-1";
    private static final String SHA_256 = "SHA-256";

    /**
     * 将byte转为16进制
     *
     * @param bytes bytes 数组
     * @return
     */
    public static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    /**
     * sha1
     *
     * @param password 密码
     * @param salts 盐
     * @return
     */
    public static String sha1(String password, String... salts) {
        return "";
        //try {
        //    MessageDigest md = MessageDigest.getInstance(SHA_1);
        //    md.update(password.getBytes());
        //    for (String salt : salts) {
        //        md.update(salt.getBytes());
        //    }
        //    MessageDigest tc1 = (MessageDigest) md.clone();
        //
        //    byte[] bytes = tc1.digest();
        //    return gua.commons.DigestTool.byte2Hex(bytes);
        //
        //} catch (NoSuchAlgorithmException | CloneNotSupportedException e) {
        //    e.printStackTrace();
        //    // throw new DigestException("couldn't make digest of partial content");
        //    return null;
        //}
    }

    /**
     * SHA256
     *
     * @param password 加密后的报文
     * @return
     */
    public static String sha256(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA_256);
            md.update(password.getBytes());
            md.update(salt.getBytes());

            return byte2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
