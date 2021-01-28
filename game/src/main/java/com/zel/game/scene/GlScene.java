package com.zel.game.scene;

import java.awt.*;

public interface GlScene  extends Runnable {
    void draw(Graphics g);
    void update();
}
