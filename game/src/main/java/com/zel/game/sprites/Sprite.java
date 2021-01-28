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
}
