package com.zel.game.sprites;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Sprite {

    protected int x, y;
    protected Image image;

    public Sprite() {
        x = 0;
        y = 0;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
