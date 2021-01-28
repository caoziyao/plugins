package com.zel.game.scene.tetris;

import com.zel.commonutils.Log;
import com.zel.game.GlApplication;
import com.zel.game.scene.GlSceneBase;
import com.zel.game.sprites.Sprite;
import com.zel.game.sprites.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GlTetrisSceneMain extends GlSceneBase {

    private List<Tetris> tetrisList;
    private Thread animator;
    private GlApplication application;

    public GlTetrisSceneMain(GlApplication application) {
        initBoard();
        this.application = application;
    }

    private void initBoard() {
        tetrisList = new ArrayList<>();

        addKeyListener(new GlTetrisSceneMain.TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        Tetris tetris = new Tetris();
        this.tetrisList.add(tetris);
    }

    @Override
    public void draw(Graphics g) {

        for (Sprite sprite : this.tetrisList) {
            Image image = sprite.getImage();
            int x = sprite.getX();
            int y = sprite.getY();
            g.drawImage(image, x, y, this);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void update() {
        for (Sprite sprite : this.tetrisList) {
            sprite.move(0, 1);
        }
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
        int speed = 5;
//        if (key == KeyEvent.VK_LEFT) {
//            tetris.move(-speed, 0);
//        }
//
//        if (key == KeyEvent.VK_RIGHT) {
//            tetris.move(speed, 0);
//        }
//
//        if (key == KeyEvent.VK_UP) {
//            tetris.move(0, -speed);
//        }
//
//        if (key == KeyEvent.VK_DOWN) {
//            tetris.move(0, speed);
//        }
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
    public void run() {
        Log.log("main run");
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
