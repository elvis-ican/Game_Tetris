package com.elvis.game.tetris;

/**
 * The GamePieceFactory class is responsible for creating GamePiece objects.
 */
public class GamePieceFactory {
    /**
     * A set of six patterns for creating game pieces
     */

    /**
     * The method selects a random pattern and uses it to create a new game piece.
     */
    public static GamePiece createRandomGamePiece(int x, int y) {
        int [][][] BRICKS = {{
                {1, 1, 0},                          //   X X
                {0, 1, 1},                          //     X X
                {0, 0, 0}}, {                       //

                {2, 0, 0},                          //   X
                {2, 2, 0},                          //   X X
                {0, 2, 0}}, {                       //     X

                {0, 3, 0},                          //   X
                {0, 3, 0},                          //   X
                {0, 3, 0}}, {                       //   X

                {4, 4, 0},                          //   X X
                {4, 4, 0},                          //   X X
                {0, 0, 0}}, {                       //

                {5, 5, 5},                          //   X X X
                {0, 5, 0},                          //     X
                {0, 0, 0}}, {                       //

                {6, 6, 6},                          //   X X X
                {6, 6, 6},                          //   X X X
                {0, 0, 0}}                          //
        };
        int index = (int) (Math.random() * 6);
        return new GamePiece(x, y, BRICKS[index]);
    }
}