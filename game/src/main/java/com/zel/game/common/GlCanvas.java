package com.zel.game.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GlCanvas {

    private static Graphics2D    graphics;


    public static void drawPoint() {
        int width = 100;
        int height = 100;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        graphics = image.createGraphics();

//        graphics.setStroke(new BasicStroke(n6 + 0.7f));
        graphics.fill3DRect(10, 10, 10, 10, true);

        graphics.dispose();


        File file = new File("./test.png");
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
