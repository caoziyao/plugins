package com.zel.game;

import com.zel.game.scene.flappy.GlFlappySceneStart;

import java.awt.EventQueue;
import javax.swing.*;

/**
 * https://zetcode.com/javagames/
 */
public class GlApplication extends JFrame {

    public GlApplication() {
        initUI();
    }

    private JPanel scene;

    private void initUI() {

        addScene(new GlFlappySceneStart(this));

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


    public void addScene(JPanel scene) {
        if (this.scene != null) {
            remove(this.scene);
        }
        this.scene = scene;
        add(scene);
    }
}
