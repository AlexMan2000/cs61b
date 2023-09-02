package byog.Core;


import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Abstraction for a room and hallway.
 * Hallway is a room with width 3.
 */
public class Room {

    private WorldState ws;

    private TETile[][] world;
    private int worldWidth;
    private int worldHeight;

    private Position anchor; // Bottom-left corner of the room

    private int WIDTH; // Width of the Room

    private int HEIGHT; // Height of the Room

    private boolean connected;

    public Room(WorldState ws, Position anchor, int width, int height) {
        this.ws = ws;
        this.world = ws.getWorld();
        this.anchor = anchor;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.worldWidth = world.length;
        this.worldHeight = world[0].length;
    }


    /**
     * Draw a wall with length n starting from StartingPos upward
     * @param n
     */
    public void drawVerticalLine(int n, Position startingPos) {
        int x = startingPos.x;
        int y = startingPos.y;
        for (int i = 0; i < n; i++) {
            world[x][y + i] = Tileset.WALL;
            this.ws.getOccupied()[x][y + i] = true;
        }
    }


    /**
     * Draw a wall with length n starting from StartingPos upward
     * @param n
     */
    public void drawHorizontalLine(int n, Position startingPos) {
        int x = startingPos.x;
        int y = startingPos.y;
        for (int i = 0; i < n; i++) {
            world[x + i][y] = Tileset.WALL;
            this.ws.getOccupied()[x + i][y] = true;
        }
    }

    public void drawFloor() {
        int x = anchor.x;
        int y = anchor.y;
        for (int i = x + 1; i < x + WIDTH - 1; i++) {
            for (int j = y + 1; j < y + HEIGHT - 1; j++) {
                world[i][j] = Tileset.FLOOR;
                this.ws.getOccupied()[i][j] = true;
            }
        }
    }


    public void drawVerticalCorridor() {
        int x = anchor.x;
        int y = anchor.y;
        for (int j = y; j < y + HEIGHT; j++) {
            world[x + 1][j] = Tileset.FLOOR;
            this.ws.getOccupied()[x + 1][j] = true;
        }
    }


    public void drawHorizontalCorridor() {
        int x = anchor.x;
        int y = anchor.y;
        for (int i = x; i < x + WIDTH; i++) {
            world[i][y + 1] = Tileset.FLOOR;
            this.ws.getOccupied()[i][y + 1] = true;
        }
    }

    /**
     * Draw a room with its bottom left corner at Position anchor
     */
    public void drawRoom() {
        drawVerticalLine(HEIGHT, anchor);
        drawVerticalLine(HEIGHT, anchor.moveHorizon(WIDTH));
        drawHorizontalLine(WIDTH, anchor);
        drawHorizontalLine(WIDTH, anchor.moveVertical(HEIGHT));
        drawFloor();
    }


    /**
     * Draw a straight hallway at given end Positions
     */
    public void drawVerticalHallway() {
        drawVerticalLine(HEIGHT, anchor);
        drawVerticalLine(HEIGHT, anchor.moveHorizon(WIDTH));
        drawVerticalCorridor();
    }


    public void drawHorizontalHallway() {
        drawHorizontalLine(WIDTH, anchor);
        drawHorizontalLine(WIDTH, anchor.moveVertical(HEIGHT));
        drawHorizontalCorridor();
    }


    public void drawLHallWay() {

    }

    /**
     * Test whether this room is overlapped with other room
     * @param room
     * @return
     */
    public boolean isOverLapped(Room room) {
        if (isLeftSideContain(room) || isRightSideContain(room) || isTopSideContain(room) || isBottomSideContain(room)) {
            return true;
        }

        return false;
    }

    public boolean isLeftSideContain(Room room) {
        int leftIndex = getTopLeft().x;

        // Interval for this room
        int topIndex = getTopLeft().y;
        int bottomIndex = getBottomLeft().y;

        // Interval for other room
        int oTopIndex = room.getTopLeft().y;
        int oBottomIndex = room.getBottomLeft().y;

        if (room.getBottomLeft().x <= leftIndex
                && leftIndex <= room.getTopRight().x
                && isIntervalOverlapped(bottomIndex, topIndex, oBottomIndex, oTopIndex)) {
            return true;
        }
        return false;
    }

    public boolean isRightSideContain(Room room) {
        int rightIndex = getTopRight().x;

        // Interval for this room
        int topIndex = getTopRight().y;
        int bottomIndex = getBottomRight().y;

        // Interval for other room
        int oTopIndex = room.getTopRight().y;
        int oBottomIndex = room.getBottomRight().y;

        if (room.getBottomLeft().x <= rightIndex
                && rightIndex <= room.getTopRight().x
                && isIntervalOverlapped(bottomIndex, topIndex, oBottomIndex, oTopIndex)) {
            return true;
        }
        return false;
    }

    public boolean isTopSideContain(Room room) {
        int topIndex = getTopRight().y;

        // Interval for this room
        int leftIndex = getTopLeft().x;
        int rightIndex = getTopRight().x;

        // Interval for other room
        int oLeftIndex = room.getTopLeft().x;
        int oRightIndex = room.getTopRight().x;

        if (room.getBottomLeft().y <= topIndex
                && topIndex <= room.getTopRight().y
                && isIntervalOverlapped(leftIndex, rightIndex, oLeftIndex, oRightIndex)) {
            return true;
        }
        return false;
    }

    public boolean isBottomSideContain(Room room) {
        int bottomIndex = getBottomLeft().y;

        // Interval for this room
        int leftIndex = getTopLeft().x;
        int rightIndex = getTopRight().x;

        // Interval for other room
        int oLeftIndex = room.getTopLeft().x;
        int oRightIndex = room.getTopRight().x;

        if (room.getBottomLeft().y <= bottomIndex
                && bottomIndex <= room.getTopRight().y
                && isIntervalOverlapped(leftIndex, rightIndex, oLeftIndex, oRightIndex)) {
            return true;
        }
        return false;
    }

    /**
     * Test whether two intervals [i1Left, i1Right], and [i2Left, i2Right] are overlapped.
     * @param i1Left
     * @param i1Right i1Left < i1Right
     * @param i2Left
     * @param i2Right i2Left < i2Right
     * @return
     */
    public boolean isIntervalOverlapped(int i1Left, int i1Right, int i2Left, int i2Right) {
        if (i1Right < i2Left || i2Right < i1Left) {
            return false;
        }

        return true;
    }

    public boolean isWithinBound() {
        return anchor.x >= 0 && anchor.y >= 0 && anchor.x + getWIDTH() <= worldWidth && anchor.y + getHEIGHT() <= worldHeight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Room room = (Room) obj;
        if (WIDTH == room.WIDTH && HEIGHT == room.HEIGHT && anchor.equals(room.anchor)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int res = 0;
        for (int i = anchor.x; i < anchor.moveHorizon(WIDTH).x; i++) {
            for (int j = anchor.y; j < anchor.moveVertical(HEIGHT).y; j++) {
                Position temp = new Position(i, j);
                res = res * 31 + temp.hashCode();
            }
        }
        return res;
    }

    public Position getAnchor() {
        return anchor;
    }


    public Position getBottomLeft() {
        return anchor;
    }

    public Position getBottomRight() {
        return anchor.moveHorizon(getWIDTH());
    }

    public Position getTopLeft() {
        return anchor.moveVertical(getHEIGHT());
    }

    public Position getTopRight() {
        return anchor.moveVertical(getHEIGHT()).moveHorizon(getWIDTH());
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
