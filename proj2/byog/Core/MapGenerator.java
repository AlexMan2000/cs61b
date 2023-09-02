package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class MapGenerator {

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public static final int WIDTH = 80;
    public static final int HEIGHT = 50;

    private MapGenerationParameter mgp;

    /**
     * Used for generating random world, random tiles without meeting
     * up with any requirements
     * @param tiles
     */
    public static void fillWithRandomTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = randomTile();
            }
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.NOTHING;
            default: return Tileset.NOTHING;
        }
    }

    /**
     * Used for generating maze
     * @return
     */
    private TETile[][] generateMaze() {
        return null;
    }

    /**
     * Used for current project, generating random world with rooms and hallways
     * @return
     */
    public TETile[][] generateRandomWorld() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] worldCanvas = new TETile[WIDTH][HEIGHT];

        WorldState ws = new WorldState(worldCanvas, 42);

        ws.generateRandomWorld();

        System.out.println(ws);

        return ws.getWorld();
    }







}
