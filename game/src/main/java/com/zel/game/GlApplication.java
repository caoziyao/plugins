package com.zel.game;


import com.zel.game.scene.GlSceneBase;
import com.zel.game.scene.GlSceneMain;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * https://zetcode.com/javagames/
 */
public class GlApplication extends JFrame {

    public GlApplication() {
        initUI();
    }

    private void initUI() {

        add(new GlSceneMain());
//        remove();
//        add(new GlSceneMain(), 1);

        setTitle("Moving sprite");
        setSize(400, 300);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GlApplication ex = new GlApplication();
            ex.setVisible(true);
        });
    }
}
