package com.zel.commonutils;

import junit.framework.TestCase;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUtilTest extends TestCase {

    @Test
    public void testImage() throws IOException {

         final BigDecimal baseDPI  = new BigDecimal(203);
        // 1mm 转化为像素比例。保留3位小数
        final BigDecimal rate = baseDPI.divide(new BigDecimal("25.4"),2, BigDecimal.ROUND_UP);

//        int width = 60;
//        int height = 45;
//        int xw  = rate.multiply(new BigDecimal(width)).setScale( 0, BigDecimal.ROUND_UP ).intValue();
//        int xh = rate.multiply(new BigDecimal(height)).setScale( 0, BigDecimal.ROUND_UP ).intValue();

        System.out.println(48*8/2.1);

        System.out.println(rate);
    }

}