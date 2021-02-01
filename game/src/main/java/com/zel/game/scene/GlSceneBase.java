package com.zel.game.scene;

import javax.swing.*;
import java.awt.*;

/**
 * 并不是所有的类都是用来描绘对象的，如果一个类中没有包含足够的信息来描绘一个具体的对象，那么这样的类称为抽象类。
 */
public abstract class GlSceneBase extends JPanel
        implements GlScene {

    protected final int B_WIDTH = 400;
    protected final int B_HEIGHT = 300 - 20;
    // todo 帧数
    protected final int DELAY = 50;

//    @Override
//    public void run() {
//        System.out.println("base run");
//    }

    /**
     * 定义抽象方法，draw
     */
    @Override
    public abstract void draw(Graphics g);

    @Override
    public abstract void update();
}
