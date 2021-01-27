package com.zel.game.example.move3;

import javax.swing.*;
import java.awt.*;

public class ThreadAnimationEx extends JFrame {

    public ThreadAnimationEx() {

        initUI();
    }

    private void initUI() {

        add(new Board5());

        setResizable(false);
        pack();

        setTitle("Star");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new ThreadAnimationEx();
            ex.setVisible(true);
        });
    }
}
