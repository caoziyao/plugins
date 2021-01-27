package com.zel.game.example.move;

import javax.swing.*;
import java.awt.*;

/**
 * https://zetcode.com/javagames/animation/
 */
public class SwingTimerEx extends JFrame {

    public SwingTimerEx() {

        initUI();
    }

    private void initUI() {

        add(new Board3());

        setResizable(false);
        pack();

        setTitle("Star");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SwingTimerEx ex = new SwingTimerEx();
            ex.setVisible(true);
        });
    }
}
