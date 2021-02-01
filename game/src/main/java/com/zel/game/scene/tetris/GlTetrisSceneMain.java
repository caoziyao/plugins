package com.zel.game.scene.tetris;

import com.zel.commonutils.Log;
import com.zel.game.GlApplication;
import com.zel.game.scene.GlSceneBase;
import com.zel.game.sprites.GlPoint;
import com.zel.game.sprites.GlSquare;
import com.zel.game.sprites.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class GlTetrisSceneMain extends GlSceneBase {

    private Tetris tetris;
    private Thread animator;
    private GlApplication application;
    // 记录格子。true存在，false不存在
    private boolean[][] boards;
    private int score = 0;
    public int boardWidth = 20;
    public int boardHeight = 20;

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
        this.boards = new boolean[B_WIDTH / boardWidth][B_HEIGHT / boardHeight];
        //this.tetris = new Tetris(Tetris.Tetrominoe.SquareShape);

        this.boards[0][0] = true;
        this.boards[2][4] = true;

        Log.log("boards", B_WIDTH / boardWidth, B_HEIGHT / boardHeight);

        //
        this.tetris.x = 10;
        this.tetris.y = 210;

        setBoardStatus(this.tetris);
    }

    @Override
    public void draw(Graphics g) {
        //tetris.draw(g);
        drawBoard(g);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void update() {
        //if (collision()) {
        //    System.out.println("colooo");
        //
        //    setBoardStatus(this.tetris);
        //
        //    tetris = Tetris.randomTetris();
        //}
        //if (collision()) {
        //    //tetris.y = this.B_HEIGHT - tetris.h - 20;
        //    // get boards
        //    setBoardStatus(this.tetris);
        //    // new tetris
        //    tetris = Tetris.randomTetris();
        //} else {
        //    tetris.move(B_WIDTH / 2, 1);
        //}
        // check down
    }

    /**
     * collision tetris and this.boards
     */
    private boolean collision() {
        int xw = B_WIDTH / this.tetris.w;
        int xh = B_HEIGHT / boardHeight;
        if (this.tetris.y >= B_HEIGHT - 40) {
            return true;
        }
        for (int i = 0; i < xw; i++) {
            for (int j = 0; j < xh; j++) {
                if (boards[i][j] == true) {
                    Rectangle rectangle = new Rectangle(i, j, boardWidth, boardHeight);
                    for (GlPoint point : this.tetris.getShape()) {
                        Rectangle other = new Rectangle(point.x, point.y, boardWidth, boardHeight);
                        if (rectangle.intersects(other)) {
                           return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private void drawBoard(Graphics g) {
        int xw = B_WIDTH / boardWidth;
        int xh = B_HEIGHT / boardHeight;
        for (int x = 0; x < xw; x++) {
            for (int y = 0; y < xh; y++) {
                if (boards[x][y] == true) {
                    Color color = new Color(255, 0, 0);
                    GlSquare square = new GlSquare(boardWidth, boardHeight, color);
                    square.draw(g, x * boardWidth, y * boardHeight);
                }

            }
        }
    }

    private  void setBoardStatus(Tetris tetris) {
        List<GlPoint> shape = tetris.getShape();
        for (GlPoint point : shape) {
            int x = point.x + tetris.x;
            int y = point.y + tetris.y;
            Log.log("xx", x, y);
            this.boards[x][y] = true;
        }

        //// remark removeFullLines
        //int xw = B_WIDTH / boardWidth;
        //int xh = B_HEIGHT / boardHeight;
        //List<Integer> droppedLines = new ArrayList<>();
        //for (int j = 0; j < xh; j++) {
        //    boolean isDropped = true;
        //    for (int i = 0; i < xw; i++) {
        //        if (boards[i][j] == false) {
        //            isDropped = false;
        //            break;
        //        }
        //    }
        //    if (isDropped) {
        //        droppedLines.add(j);
        //    }
        //}
        //
        //// removeFullLines
        //int numFullLines = 0;
        //for (int j = xh - 1; j >= 0; j--) {
        //    if (droppedLines.contains(j)) {
        //        numFullLines += 1;
        //        score += numFullLines;
        //        Log.log("add score", score);
        //        continue;
        //    } else {
        //        for (int i = xw - 1; i >= 0; i--) {
        //            boards[i][j] = boards[i][j-numFullLines];
        //        }
        //        numFullLines = 0;
        //    }
        //}
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
            //Log.log("pressed " + e.getKeyCode());
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
