package com.zel.game.sprites;

import java.awt.*;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/28
 */
public class GlSquare extends Sprite {

    private Color color;

    GlSquare(int width, int height) {
        this.w = width;
        this.h = height;
        this.color = new Color(0, 0, 0);
    }

    GlSquare(int width, int height, Color color) {
        this.w = width;
        this.h = height;
        this.color = color;
    }

    public void draw(Graphics g, int x, int y) {

        Color color = this.color;

        g.setColor(color);
        g.fillRect(x + 1, y + 1, w - 2, h - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + h - 1, x, y);
        g.drawLine(x, y, x + w - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + h - 1, x + w - 1, y + h - 1);
        g.drawLine(x + w - 1, y + h  - 1, x + w - 1, y + 1);
    }
}
