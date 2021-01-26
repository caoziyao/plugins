package com.zel.commonutils;

import junit.framework.TestCase;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUtilTest extends TestCase {

    @Test
    public void testImage() throws IOException {
        File file = new File("D:/1.jpg");
        Path path = Paths.get("D:/1.jpg");

//        String imageString = ImageUtil.getImageString(file);
//        System.out.println(imageString);

        ImageUtil.draw(file, 400, 400);

//        String url = "url.jpg";
//        int index = url.lastIndexOf(".");// 最后一个后缀点出现的下标
//        String suffix = url.substring(index + 1, url.length()).toLowerCase();// 获取文件后缀名
//        System.out.println(suffix);
    }

}