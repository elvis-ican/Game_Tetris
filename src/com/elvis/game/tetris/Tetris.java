package com.elvis.game.tetris;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * The Tetris class contains the basic functionality of the game.
 */
public class Tetris {
    private Field field;                // Game field
    private GamePiece gamePiece;              // Game piece
    private boolean isGameOver;         // Indicates whether the game is over
    private JFrame frame;

    public Tetris(int width, int height) {
        field = new Field(width, height);
        gamePiece = null;
        frame = new JFrame();
        frame.setTitle("Tetris");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 600);
        frame.setResizable(false);
        frame.add(field.getDisplay());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        initiateFrame();
    }


    public Field getField() {
        return field;
    }

    public GamePiece getGamePiece() {
        return gamePiece;
    }

    public void restart() throws Exception {
        field.clearField();
        Thread.sleep(500);
        this.run();
    }

    public void run() throws Exception {
        // Create a KeyboardObserver object and start it.
        KeyboardObserver keyboardObserver = new KeyboardObserver(field);
        keyboardObserver.start();
        isGameOver = false;
        gamePiece = GamePieceFactory.createRandomGamePiece(field.getWidth()/2 - 1, 0);

        while (!isGameOver) {
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
                    restart();
                else if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    gamePiece.left();
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    gamePiece.right();
                else if (event.getKeyCode() == KeyEvent.VK_UP)
                    gamePiece.rotate();
//                    gamePiece.rotate90Clockwise();
                else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                    gamePiece.downMaximum();
            }
            step();             // Take a step
            field.print();      // Display the field
            Thread.sleep(300);  // Pause 300 milliseconds (about 1/3 of a second)
        }
        // Display "Game Over"
//        field.setGameOver(true);
        if (isGameOver) {
            JOptionPane.showMessageDialog(frame, "You lost :(\n Try again!");
            restart();
        }
    }

    public void step() {
        gamePiece.down();
        // If the game piece can't be placed in the current location
        if (!gamePiece.isCurrentPositionAvailable()) {
            gamePiece.up();                    // Put it back
            gamePiece.land();                // Land it
            isGameOver = gamePiece.getY () <= 1; // If the game piece lands at the very top, then the game is over
            field.removeFullLines();        // Remove the completed lines
            gamePiece = GamePieceFactory.createRandomGamePiece(field.getWidth()/2 - 1, 0); // Create a new game piece
        }
    }

    public void setGamePiece(GamePiece gamePiece) {
        this.gamePiece = gamePiece;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public static Tetris game;

    public static void main(String[] args) throws Exception {
        game = new Tetris(10, 20);
        game.run();
    }
}