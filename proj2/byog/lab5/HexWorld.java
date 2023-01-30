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
     * @param s
     */
    public void addHexagon(TETile[][] world, Position p, int s) {
        int maxlength = s + (s - 1) * 2;
        // Render the tiles for the world
        Position upperPosition = new Position(p.x,p.y);
        Position lowerPosition = new Position(p.x, p.y - (s - 1));
        drawUpper(world, upperPosition, s, maxlength);
        drawLower(world, lowerPosition, s, maxlength);
    }

    /**
     * Draw the upper part of the hexagon
     * @param s
     * @param maxLength
     */
    public void drawUpper(TETile[][] world, Position p, int s, int maxLength) {
        // Compute the maximum length of side
        for (int i = 0; i < s; i++){
            int sideNum = s + i * 2;
            int blankNum = maxLength - sideNum;
            drawLine(world, p, blankNum, sideNum);
        }
    }

    /**
     * Draw the lower part of the hexagon
     * @param s
     * @param maxLength
     */
    public void drawLower(TETile[][] world,Position p, int s, int maxLength) {
        for (int i = s - 1; i > -1; i--){
            int sideNum = s + i * 2;
            int blankNum = maxLength - sideNum;
            drawLine(world, p, blankNum, sideNum);
        }
    }


    /**
     * Draw a single line based on the number
     * of blank tiles and side tiles
     * @param blankNum
     * @param sideLength
     */
    public void drawLine(TETile[][] world, Position p, int blankNum, int sideLength) {
        int startPositionX = p.x;
        int startPositionY = p.y;
        for (int i = 0; i < blankNum / 2; i++){
            world[p.x][p.y] = Tileset.NOTHING;
        }
        for (int i = 0; i < sideLength ; i++){
//            world[p.x][p.y]
        }
        for (int i = 0; i < blankNum / 2; i++){
            world[p.x][p.y] = Tileset.NOTHING;
        }
    }

    public static void main(String[] args) {
        // Create the world.
        HexWorld hw = new HexWorld();
        // Set the size of the canvas
        int width = 100;
        int height = 100;
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


        // draws the world to the screen
        ter.renderFrame(world);
    }

}
