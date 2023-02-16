package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Created by AlexMan
 */
public class RandomWorldGenerator {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;

    private final long SEED;
    private final Random RANDOM;

    public RandomWorldGenerator(int s) {
        this.SEED = s;
        this.RANDOM = new Random(this.SEED);
    }

    /**
     * Random generation algorithm
     * @param tiles
     */
    public void fillWithRandomTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = randomTile();
            }
        }
    }

    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private TETile randomTile() {
        int tileNum = this.RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WALL; // Stands for walls
            case 1: return Tileset.FLOOR; // Stands for floors
            case 2: return Tileset.LOCKED_DOOR; // Stands for locked door.`
            default: return Tileset.NOTHING; // Stands for outside spaces
        }
    }

    public void generateWorld() {
        // Initialize the TERender, used to render the drawn tiles onto the screen.
        TERenderer ter = new TERenderer();
        // Initialize the world
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] randomTiles = new TETile[WIDTH][HEIGHT];
        fillWithRandomTiles(randomTiles);

        ter.renderFrame(randomTiles);
    }


    public void main(String[] args) {
        generateWorld();
    }
}
