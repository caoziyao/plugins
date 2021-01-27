package com.zel.game.example.base;

import javax.swing.*;
import java.awt.*;

public class Donut2Example extends JFrame {

    public Donut2Example() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setSize(250, 200);

        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Donut2Example ex = new Donut2Example();
            ex.setVisible(true);
        });
    }
}
