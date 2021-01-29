package com.zel.game.sprites;

import com.zel.commonutils.Log;
import junit.framework.TestCase;

public class TetrisTest extends TestCase {

    public void testGetAngle() {
        int size = Tetris.Tetrominoe.size();
        Log.log("size", size);
    }
}