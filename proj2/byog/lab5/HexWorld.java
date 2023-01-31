package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    /**
     * Side length of the hexagon to be drawn
     * Add the hexagon to the TETile[][] world
     * @param world The whole canvas
     * @param p The top-left corner of the hexagon(including the NOTHING Block)
     * @param s Side length
     * @param tileType The type of the tile to draw
     */
    public void addHexagon(TETile[][] world, Position p, int s, int tileType) {
        int maxlength = s + (s - 1) * 2;
        // Render the tiles for the world
        Position upperPosition = new Position(p.x,p.y);
        Position lowerPosition = new Position(p.x, p.y - s);
        drawLower(world, lowerPosition, s, maxlength, tileType);
        drawUpper(world, upperPosition, s, maxlength, tileType);
    }

    /**
     * Draw the upper part of the hexagon
     * @param s
     * @param maxLength
     */
    public void drawUpper(TETile[][] world, Position p, int s, int maxLength, int tileType) {
        int yCor = p.y;
        // Compute the maximum length of side
        for (int i = 0; i < s; i++){
            int sideNum = s + i * 2;
            int blankNum = maxLength - sideNum;
            Position pos = new Position(p.x, yCor);
            yCor--;
            drawLine(world, pos, blankNum, sideNum, tileType);
        }
    }

    /**
     * Draw the lower part of the hexagon
     * @param s
     * @param maxLength
     */
    public void drawLower(TETile[][] world,Position p, int s, int maxLength, int tileType) {
        int yCor = p.y;
        for (int i = s - 1; i > -1; i--){
            int sideNum = s + i * 2;
            int blankNum = maxLength - sideNum;
            Position pos = new Position(p.x, yCor);
            yCor--;
            drawLine(world, pos, blankNum, sideNum, tileType);
        }
    }


    /**
     * Draw a single line based on the number
     * of blank tiles and side tiles
     * @param blankNum
     * @param sideLength
     */
    public void drawLine(TETile[][] world, Position p, int blankNum, int sideLength, int tileType) {
        TETile t;
        switch (tileType) {
            case 0: t = Tileset.WALL; break;
            case 1: t = Tileset.FLOWER; break;
            case 2: t = Tileset.TREE; break;
            case 3: t = Tileset.MOUNTAIN; break;
            case 4: t = Tileset.SAND; break;
            case 5: t = Tileset.GRASS; break;
            default: t = Tileset.NOTHING; break;
        }
        int positionX = p.x;
        int positionY = p.y;
        for (int i = 0; i < blankNum / 2; i++){
            positionX += 1;
        }
        for (int i = 0; i < sideLength ; i++){
            if (tileType == 3) {
                world[positionX][positionY] = TETile.colorVariant(t, 50,50,50, new Random());
            }else {
                world[positionX][positionY] = t;
            }
            positionX += 1;
        }
    }


    /**
     * We actually want to draw all the hexagons column by column
     * @param world
     * @param hw
     */
    public static void draw19Hexagons(TETile[][] world, HexWorld hw) {
        // Left
        hw.addHexagon(world, new Position(2,11),3,5);
        hw.addHexagon(world, new Position(2,17),3,5);
        hw.addHexagon(world, new Position(2,23),3,3);

        // Left middle
        hw.addHexagon(world, new Position(7,8),3,1);
        hw.addHexagon(world, new Position(7,14),3,3);
        hw.addHexagon(world, new Position(7,20),3,3);
        hw.addHexagon(world, new Position(7,26),3,5);

        // Middle
        hw.addHexagon(world, new Position(12,5),3, 3);
        hw.addHexagon(world, new Position(12,11),3, 3);
        hw.addHexagon(world, new Position(12,17),3, 3);
        hw.addHexagon(world, new Position(12,23),3, 3);
        hw.addHexagon(world, new Position(12,29),3, 4);

        // Right middle
        hw.addHexagon(world, new Position(17,8),3,3);
        hw.addHexagon(world, new Position(17,14),3,2);
        hw.addHexagon(world, new Position(17,20),3,4);
        hw.addHexagon(world, new Position(17,26),3,1);

        // Right
        hw.addHexagon(world, new Position(22,11),3,4);
        hw.addHexagon(world, new Position(22,17),3,2);
        hw.addHexagon(world, new Position(22,23),3,1);
    }


    public static void main(String[] args) {
        // Create the world, setting up the parameters
        HexWorld hw = new HexWorld();
        // Set the size of the canvas
        int width = 30;
        int height = 30;
        int sideLength = 3;
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);

        // initialize tiles
        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // Start to draw the hexagon
        draw19Hexagons(world, hw);

        // draws the world to the screen
        ter.renderFrame(world);
    }

}
