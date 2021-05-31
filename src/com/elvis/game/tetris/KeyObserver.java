package com.elvis.game.tetris;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class KeyObserver extends KeyAdapter {
    private Queue<KeyEvent> keyEvents = new ArrayBlockingQueue<>(100);
    private Field field;

    public KeyObserver(Field field) {
        this.field = field;
        JFrame game = new JFrame();
        game.setTitle("Tetris");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(300, 600);
        game.setResizable(false);
        game.add(field.getDisplay());
        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        keyEvents.add(e);
    }

    public boolean hasKeyEvents() {
        return !keyEvents.isEmpty();
    }

    public KeyEvent getEventFromTop() {
        return keyEvents.poll();
    }
}
