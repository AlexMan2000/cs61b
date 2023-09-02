package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldState {

    private TETile[][] world;

    private boolean[][] occupied;

    private List<Room> rooms;

    private long seed;
    private Random random;

    private int worldHeight;
    private int worldWidth;

    public WorldState() {
        this.rooms = new ArrayList<>();
        this.random = new Random(42);
        worldWidth = this.random.nextInt(1920);
        worldHeight = this.random.nextInt(1080);
        this.occupied = new boolean[worldWidth][worldHeight];
        initializeWorld(worldWidth, worldHeight);
    }

    public WorldState(TETile[][] world, long seed) {
        this.world = world;
        this.rooms = new ArrayList<>();
        this.seed = seed;
        this.random = new Random();
        worldWidth = world.length;
        worldHeight = world[0].length;
        this.occupied = new boolean[worldWidth][worldHeight];
        initializeWorld(worldWidth, worldHeight);
    }


    private void initializeWorld(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                occupied[i][j] = false;
            }
        }
    }


    /**
     * Add a random number of rooms to the canvas
     */
    public void addRoom() {
        addRandomRoom(50);
    }


    /**
     * Add a specific number of rooms go the canvas
     * @param numOfRoom
     */
    public void addRandomRoom(int numOfRoom) {
        // Make the size of the room controllable
        int maxWidth = (worldWidth - 3) / 4;
        int maxHeight = (worldHeight - 3) / 4;

        while (rooms.size() < numOfRoom) {
            System.out.println(rooms.size());
            int x = this.random.nextInt(worldWidth);
            int y = this.random.nextInt(worldHeight);
            while (occupied[x][y]) {
                System.out.println("Found un unoccupied place!");
                x = this.random.nextInt(worldWidth);
                y = this.random.nextInt(worldHeight);
            }
            int width = 3 + this.random.nextInt(maxWidth);
            int height = 3 + this.random.nextInt(maxHeight);

            Room newRandomRoom = new Room(this, new Position(x,y), width, height);

            if (!newRandomRoom.isWithinBound()){
                System.out.println("边界条件校验不通过");
                continue;
            }
            boolean flag = false;
            for (Room room: rooms) {
                if (newRandomRoom.isOverLapped(room)) {
                    System.out.println("重叠校验不通过");
                    flag = true;
                }
            }
            if (!flag) {
                System.out.println("正在绘制--------------------");
                rooms.add(newRandomRoom);
                newRandomRoom.drawRoom();
            }
        }


    }


    public void connectRooms(Room room1, Room room2) {
        // See if the rooms can be connected using straight hallways




    }



    public void addHallways() {
        for (Room room1: rooms) {
            for (Room room2: rooms) {
                if (room1.equals(room2)) {
                    connectRooms(room1, room2);
                }
            }
        }
    }


    public void generateRandomWorld() {
        System.out.println("加房间");
        addRoom();
    }

    public boolean[][] getOccupied() {
        return occupied;
    }

    public void setOccupied(boolean[][] occupied) {
        this.occupied = occupied;
    }

    public TETile[][] getWorld() {
        return world;
    }

    public void setWorld(TETile[][] world) {
        this.world = world;
    }


    public String toString() {
        return TETile.toString(world);
    }
}
