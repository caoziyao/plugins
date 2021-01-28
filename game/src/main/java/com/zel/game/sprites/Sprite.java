package com.zel.game.sprites;

import com.zel.game.common.GlImage;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Sprite {

    protected int x, y;
    protected int w, h;
    private String filePath = "./game/src/main/resources/img/sprites/redbird-upflap.png";
    protected Image image;

    public Sprite() {
        x = 0;
        y = 0;
        this.image = GlImage.loadImage(filePath);
        this.w = this.image.getWidth(null);
        this.h = this.image.getHeight(null);
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
