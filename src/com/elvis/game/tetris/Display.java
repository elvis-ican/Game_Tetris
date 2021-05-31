package com.elvis.game.tetris;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 25;
    private static final int TILE_MARGIN = 3;
    private Field field;
    private Color[] colors = {Color.gray,new Color(0xCD5C5C), new Color(0x6495ED),
            Color.cyan, Color.magenta, Color.orange, new Color(0x40E0D0), Color.yellow};

    public Display(Field field) {
        setFocusable(true);
        this.field = field;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
                drawTile(g, field.canvas[y][x], x, y);
            }
        }
        if (field.isGameOver) {
            JOptionPane.showMessageDialog(this, "You lost :(");
        }
    }

    private void drawTile(Graphics g2, int color, int x, int y) {
        Graphics2D g = ((Graphics2D) g2);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);
        g.setColor(colors[color]);
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE , 2, 2);
    }

    private static int offsetCoors(int arg) {
        return arg * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
    }

}
