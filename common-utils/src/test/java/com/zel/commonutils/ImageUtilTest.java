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
    public void testImage() {
        File file = new File("D:/photo.png");
        Path path = Paths.get("D:/photo.png");

        String imageString = ImageUtil.getImageString(file);
        System.out.println(imageString);
//        try {
//            BufferedImage image = ImageIO.read(file);
//            System.out.println(image);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        ImageUtil.generateImage(imageString, "D:/at/tt/newa.jpg");
//        ImageUtil.generateImage(imageString);
    }

}