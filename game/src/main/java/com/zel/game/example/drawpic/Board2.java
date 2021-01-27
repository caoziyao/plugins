package com.zel.game.example.drawpic;

import javax.swing.*;
import java.awt.*;

public class Board2 extends JPanel {

    private Image bardejov;

    public Board2() {

        initBoard();
    }

    private void initBoard() {

        loadImage();

        int w = bardejov.getWidth(this);
        int h =  bardejov.getHeight(this);
        setPreferredSize(new Dimension(w, h));
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon("./game/src/main/resources/img/sprites/redbird-upflap.png");
        bardejov = ii.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bardejov, 0, 0, null);
    }
}
