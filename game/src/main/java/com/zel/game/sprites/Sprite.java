package com.zel.game.sprites;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Sprite {

    public int x, y;
    public int w, h;
    protected Image image;

    public Sprite() {
        x = 0;
        y = 0;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }


    /**
     * collision detection
     * @return
     */
    public boolean collision(Sprite other) {
        Rectangle o = other.getBounds();
        Rectangle bounds = this.getBounds();
        return bounds.intersects(o);
    }

    private Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }
}
