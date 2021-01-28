package com.zel.game.sprites;


import com.zel.game.common.GlImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tetris extends Sprite{
    private final String filePath = "./game/src/main/resources/img/sprites/redbird-upflap.png";

    public Tetris() {
        super();
        this.image = GlImage.loadImage(filePath);
    }
}
