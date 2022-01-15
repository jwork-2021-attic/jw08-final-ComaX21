package world;

import asciiPanel.AsciiPanel;
import java.awt.Color;

public enum Tile {

    FLOOR((char) 250, AsciiPanel.green),
    WALL((char) 177, AsciiPanel.brightBlack);

    
    private char glyph;
    private Color color;
    Tile(char glyph, Color color) {
        this.glyph = glyph;
        this.color = color;
    }

    public char glyph() {
        return glyph;
    }

    public Color color() {
        return color;
    }

    public boolean isDiggable() {
        return this == Tile.WALL;
    }

    public boolean isGround() {
        return this == Tile.FLOOR;
    }
}
