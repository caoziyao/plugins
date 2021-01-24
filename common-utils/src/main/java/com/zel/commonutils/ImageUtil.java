package com.zel.commonutils;

import com.zel.commonutils.crypto.Base64Util;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.UUID;

/**
 * 图片处理工具
 */
public class ImageUtil {

//    private static final LoggerUtil logger = LogFactory.getLogger(Base64Util.class);
    private static final String DIR = "D:/at";;
    /**
     * 随机生成
     * @param imgStr
     * @return
     */
    public static String generateImage(String imgStr) {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String path = DIR + File.separator + uuid + ".jpg";
        ImageUtil.generateImage(imgStr, path);
        generateImage(imgStr, path);
        return path;
    }

    /**
     * base64字符串转化成图片
     * @param imgStr
     * @param filePath
     * @return
     */
    public static String generateImage(String imgStr, String filePath) {
        //对字节数组字符串进行Base64解码并生成图片
        String imgUrl = "";
        //图像数据为空
        if (StringUtils.isBlank(imgStr)) {
            return imgUrl;
        }

        //Base64解码
        byte[] b = Base64Util.decryptBASE64(imgStr);

        //byte[] b = Base64.decodeBase64(imgStr);
        for (int i = 0; i < b.length; ++i) {
            //调整异常数据
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        File dest = new File(filePath);
        //判断目标文件所在的目录是否存在
        if (!dest.getParentFile().exists()) {
            //创建目录
            if (!dest.getParentFile().mkdirs()) {
                return imgUrl;
            }
        }
        //新生成的图片
        //这种写法不需要flush或者close 会自动关闭 FileOutputStream 实现了java中的java.lang.AutoCloseable接口。
        try (OutputStream out = new FileOutputStream(filePath)) {
            out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return imgUrl;
        }
    }


    /**
     * 根据图片地址转换为 base64 编码字符串
     *
     * @param file 图片文件
     * @return 图片 base64 编码字符串
     */
    public static String getImageString(File file) {
        InputStream inputStream = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            inputStream = new FileInputStream(file.getAbsolutePath());
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //java.util.Base64.Encoder encoder= java.util.Base64.getMimeEncoder();
        //return encoder.encodeToString(data);

        return Base64Util.encryptBASE64(data);

        //// 对字节数组 Base64 编码 加密
        //BASE64Encoder base64Encoder = new BASE64Encoder();
        //// 返回 Base64 编码过的字节数组字符串
        //return base64Encoder.encode(data);
    }
}
