import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Base64工具类，提供Base64的编码和解码方案<br>
 * base64编码是用64（2的6次方）个ASCII字符来表示256（2的8次方）个ASCII字符，<br>
 * 也就是三位二进制数组经过编码后变为四位的ASCII字符显示，长度比原来增加1/3。
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/6/20
 */
public class Base64Tool {
    /**
     * 编码为Base64
     *
     * @return 编码后的 string
     */
    public static String encode(final String text) {
        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        byte[] textByte = null;
        try {
            textByte = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoder.encodeToString(textByte);
    }

    /**
     * 解密
     *
     * @param encodedText 解码
     * @return 解码
     */
    public static String decode(final String encodedText) {
        final Base64.Decoder decoder = Base64.getDecoder();
        //解码
        String txt;
        try {
            txt = new String(decoder.decode(encodedText), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            txt = "";
        }
        return txt;
    }

    public void test() throws UnsupportedEncodingException {
        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final String text = "字串文字";
        final byte[] textByte = text.getBytes("UTF-8");
        //编码
        final String encodedText = encoder.encodeToString(textByte);
        System.out.println(encodedText);
        //解码
        System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
    }
}
