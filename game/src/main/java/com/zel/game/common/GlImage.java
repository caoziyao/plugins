package com.zel.game.common;

import javax.swing.*;
import java.awt.*;

public class GlImage {

    public static Image loadImage(String filePath) {
        ImageIcon ii = new ImageIcon(filePath);
        return ii.getImage();
    }
}
