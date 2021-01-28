package com.zel.game.scene;

import com.zel.game.common.GlImage;
import com.zel.game.sprites.Bird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GlSceneMain extends GlSceneBase {

    private Bird bird;
    private Thread animator;

    public GlSceneMain() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new GlSceneMain.TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        bird = new Bird();
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

    /**
     * 重写父类中的抽象方法，实现计算 draw 功能
     * @param g
     */
    @Override
    public void draw(Graphics g) {

        Image image = bird.getImage();
        int x = bird.getX();
        int y = bird.getY();

        g.drawImage(image, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void update() {

        bird.move(1, 1);
        if (bird.getY() > B_HEIGHT) {
           bird.moveTo(0, 0);
        }
    }

    @Override
    public void run() {
        System.out.println("main run");
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
        if (key == KeyEvent.VK_LEFT) {
            bird.move(-speed, 0);
        }

        if (key == KeyEvent.VK_RIGHT) {
            bird.move(speed, 0);
        }

        if (key == KeyEvent.VK_UP) {
            bird.move(0, -speed);
        }

        if (key == KeyEvent.VK_DOWN) {
            bird.move(0, speed);
        }
    }
}
