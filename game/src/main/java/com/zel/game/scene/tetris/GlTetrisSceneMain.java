package com.zel.game.scene.tetris;

import com.zel.commonutils.Log;
import com.zel.game.GlApplication;
import com.zel.game.example.move.Board3;
import com.zel.game.scene.GlSceneBase;
import com.zel.game.sprites.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GlTetrisSceneMain extends GlSceneBase {

    private Tetris tetris;
    private Thread animator;
    private GlApplication application;

    private Timer timer;

    public GlTetrisSceneMain(GlApplication application) {
        initBoard();
        this.application = application;
    }

    private void initBoard() {

        addKeyListener(new GlTetrisSceneMain.TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        this.tetris = new Tetris(Tetris.Tetrominoe.LineShape);
        //this.tetris = new Tetris(Tetris.Tetrominoe.SquareShape);
    }

    @Override
    public void draw(Graphics g) {

        tetris.draw(g);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void update() {
        if (tetris.y >= this.B_HEIGHT - tetris.h - 20) {
            tetris.y = this.B_HEIGHT - tetris.h - 20;
        } else {
            //t.move(0, 1);
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            updateEvent(e);
            if (timer != null) {
                timer.stop();
                timer = null;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Log.log("pressed " + e.getKeyCode());
            updateKeyPress(e);
            if (timer == null) {
                timer = new Timer(DELAY, new EventCycle(e));
                timer.start();
            }
        }
    }

    public void updateKeyPress(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            tetris.rotateRight();
        }
    }


    /**
     * long press
     */
    private class EventCycle implements ActionListener {
        private KeyEvent keyEvent;

        EventCycle(KeyEvent keyEvent) {
            this.keyEvent = keyEvent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            updateEvent(this.keyEvent);
        }
    }

    public void updateEvent(KeyEvent e) {
        int key = e.getKeyCode();
        int speed = 5;
        if (key == KeyEvent.VK_LEFT) {
            tetris.move(-speed, 0);
        }

        if (key == KeyEvent.VK_RIGHT) {
            tetris.move(speed, 0);
        }

        if (key == KeyEvent.VK_UP) {
            tetris.move(0, -speed);
        }

        if (key == KeyEvent.VK_DOWN) {
            tetris.move(0, speed);
        }
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
