package com.zel.game.example.move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board3 extends JPanel
        implements ActionListener {

    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int DELAY = 25;

    private Image star;
    private Timer timer;
    private int x, y;

    public Board3() {
        initBoard();
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon("./game/src/main/resources/img/sprites/redbird-upflap.png");
        star = ii.getImage();
    }

    private void initBoard() {
        addKeyListener(new Board3.TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImage();
        x = 0;
        y = 0;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    /**
     * Custom painting is done in the paintComponent() method. Note that we also call the paintComponent() method of its parent.
     * The actual painting is delegated to the draw() method.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Here we create a Swing Timer class and call its start() method. Every DELAY ms the timer will call the actionPerformed() method.
     * In order to use the actionPerformed() method, we must implement the ActionListener interface.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
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
