package com.zel.game.sprites;

import com.zel.game.common.GlImage;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Bird extends Sprite{

    private final String filePath = "./game/src/main/resources/img/sprites/redbird-upflap.png";

    public Bird() {
        super();
        this.image = GlImage.loadImage(filePath);
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
