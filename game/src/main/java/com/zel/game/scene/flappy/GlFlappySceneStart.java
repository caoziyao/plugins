package com.zel.game.scene.flappy;

//import com.zel.commonutils.Log;
import com.zel.game.GlApplication;
import com.zel.game.scene.GlSceneBase;
import com.zel.game.scene.tetris.GlTetrisSceneMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GlFlappySceneStart extends GlSceneBase {

    private Thread animator;
    private GlApplication application;

    public GlFlappySceneStart(GlApplication application) {
        initBoard();
        this.application = application;
    }

    private void initBoard() {
        addKeyListener(new GlFlappySceneStart.TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
    }

    /**
     * In the previous examples, we executed a task at specific intervals. In this example,
     * the animation will take place inside a thread. The run() method is called only once.
     * This is why why we have a while loop in the method. From this method, we call the cycle() and the repaint() methods.
     */
    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void draw(Graphics g) {
        g.drawString("按 f 开始 flappy 游戏", 20, 20);
        g.drawString("按 t 开始 tetris 游戏", 20, 35);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void update() {

    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            updateEvent(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            updateEvent(e);
        }
    }

    public void updateEvent(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_F) {
            // 开始场景
            //Log.log("start");
            this.application.addScene(new GlFlappySceneMain(this.application));
            this.application.validate();
        } else if  (key == KeyEvent.VK_T) {
            // 开始场景
            //Log.log("start");
            this.application.addScene(new GlTetrisSceneMain(this.application));
            this.application.validate();
        }
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (true) {

            update();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                JOptionPane.showMessageDialog(this, msg, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            beforeTime = System.currentTimeMillis();
        }
    }
}
