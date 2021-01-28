package com.zel.game.sprites;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/28
 */
@Getter
@Setter
public class GlPoint {
    public int x;
    public int y;

    public GlPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
