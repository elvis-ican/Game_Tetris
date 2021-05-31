package com.elvis.game.tetris;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Checking {
    private static int width = 5;
    private static int height = 5;
    private static int[][] matrix = {   {0,0,0,0,0},
                                        {0,1,1,0,1},
                                        {1,1,1,1,1},
                                        {0,1,1,0,0},
                                        {1,1,1,1,1}  };

    public static void main(String[] args) {
        removeFullLines();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println();
        removeFullLines2();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
    }

    public static void removeFullLines() {
        int deleteCount = 0;
        List<int[]> temp = new ArrayList<>();
        for (int i = height-1; i >= 0; i--) {
            boolean isAllOne = true;
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] != 1) {
                    isAllOne = false;
                }
            }
            if (isAllOne) {
                matrix[i] = null;
                deleteCount++;
            }
        }
        for (int i = height-1; i >= 0; i--) {
            if (matrix[i] != null)
                temp.add(matrix[i]);
        }
        for (int i = height-1; i >= deleteCount; i--) {
            matrix[i] = temp.get(0);
            temp.remove(0);
        }
        for (int i = 0; i < deleteCount; i++) {
            matrix[i] = new int[width];
        }

        // For example, like this:
        // Create a list to store the lines
        // Copy all the non-empty lines into a list.
        // Add incomplete lines to the beginning of the list.
        // Convert the list back into a matrix
    }

    public static void removeFullLines2() {
        LinkedList<int[]> lines = new LinkedList<>();

        for (int i = height-1; i>=0; i--) {
            boolean isAllOne = true;
            for (int j = width-1; j >0; j--) {
                if (matrix[i][j] != 1) {
                    isAllOne = false;
                }
            }
            if(!isAllOne) {
                lines.add(matrix[i]);
            }
        }
        for (int i = lines.size(); i < height; i++) {
            lines.add(new int[width]);
        }
        for (int i = height-1; i >= 0; i--) {
            matrix[i] = lines.removeFirst();
        }
    }
}
