package byog.lab6;

import org.junit.Test;

/**
 * Created by AlexMan
 */
public class TestingMemoryGame {

    @Test
    public void testGenerateString() {
        int seed = 42;
        MemoryGame game = new MemoryGame(40, 40, seed);
        System.out.println(game.generateRandomString(5));
    }


    @Test
    public void testFlashSequence() {
        int seed = 42;
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.flashSequence("w3s");
    }


    @Test
    public void testSolicitInput() {
        int seed = 42;
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.solicitNCharsInput(8);
    }
}
