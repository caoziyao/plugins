package com.zel.game.example.drawpic;

import javax.swing.*;
import java.awt.*;

public class DonutExample extends JFrame {

    public DonutExample() {

        initUI();
    }

    private void initUI() {

        add(new Board2());

        setSize(330, 330);

        setTitle("Donut");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            DonutExample ex = new DonutExample();
            ex.setVisible(true);
        });
    }
}
