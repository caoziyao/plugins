package com.zel.game.example.move3;

import com.zel.game.example.move.Board3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Animating objects using a thread is the most effective and accurate way of animation.
 */
public class Board5 extends JPanel
        implements Runnable {

    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int DELAY = 25;

    private Image star;
    private Thread animator;
    private int x, y;

    public Board5() {

        initBoard();
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon("./game/src/main/resources/img/sprites/redbird-upflap.png");
        star = ii.getImage();
    }

    private void initBoard() {
        addKeyListener(new Board5.TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImage();

        x = 0;
        y = 0;
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

    private void draw(Graphics g) {

        g.drawImage(star, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    private void update() {

        x += 1;
        y += 1;

        if (y > B_HEIGHT) {

            y = 0;
            x = 0;
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
            //System.out.println(sleep);

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
            x -= speed;
        }

        if (key == KeyEvent.VK_RIGHT) {
            x += speed;
        }

        if (key == KeyEvent.VK_UP) {
            y -= speed;
        }

        if (key == KeyEvent.VK_DOWN) {
            y += speed;
        }
    }
}
