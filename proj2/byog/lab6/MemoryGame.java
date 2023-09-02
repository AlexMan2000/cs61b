package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver; // Any wrong input could trigger this to be true and terminate the program
    private boolean playerTurn; // See if to print out "Watch!" or "Type!"
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};
    private int randomIndex;

    public static void main(String[] args) {
        int seed = 42;
        MemoryGame game = new MemoryGame(40, 40, seed);  // Modify the parameter list
        game.startGame();
    }

    // Modified the parameter list
    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        this.rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        StringBuilder sb = new StringBuilder();
        for (int i = 0;  i < n ;i++) {
            int randInt = this.rand.nextInt(CHARACTERS.length);
            sb.append(CHARACTERS[randInt]);
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        StdDraw.clear(); // Clear previous info from the screen
        StdDraw.line(0, this.height - 5 , this.width, this.height - 5);
        StdDraw.text(5, this.height - 2.5, "Round: " + round);

        // Depending on "type" or "Watch", display different items
        StdDraw.text(this.width / 2, this.height - 2.5,  playerTurn ? "Type":"Watch!");
        StdDraw.text(this.width / 2, this.height / 2, s);

        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.text(this.width - 7.5, this.height - 2.5, ENCOURAGEMENT[randomIndex]);
        StdDraw.show(); // Flush onto the screen
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        StdDraw.pause(500);
        for (int i = 0; i < letters.length(); i++) {
            char l = letters.charAt(i);
            drawFrame(String.valueOf(l));
            StdDraw.pause(500);
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    /**
     * Get n characters from keyboard type event
     * @param n
     * @return
     */
    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        StringBuilder sb = new StringBuilder();
        drawFrame("");
        int counter = n;
        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
            } else {
                char key = StdDraw.nextKeyTyped();
                sb.append(key);
                drawFrame(sb.toString());
                counter--;
                if (counter == 0) {
                    break;
                }
            }
        }
        return sb.toString();
    }

    private void switchPlayer() {
        playerTurn = ! playerTurn;
    }

    private String watchPhase() {
        randomIndex = this.rand.nextInt(ENCOURAGEMENT.length);
        String randString = generateRandomString(round);
        flashSequence(randString);
        return randString;
    }

    private void typePhase(String randString) {
        randomIndex = this.rand.nextInt(ENCOURAGEMENT.length);
        drawFrame("");
        String inputString = solicitNCharsInput(randString.length());
        StdDraw.pause(500);
        if (!inputString.equals(randString)) {
            drawFrame("Game Over! You made it to round: " + round);
            gameOver = true;
        } else {
            drawFrame("Correct, well done!");
            StdDraw.pause(1500);
        }
    }

    public void startGame() {
        round = 1;
        gameOver = false;
        playerTurn = false;
        //TODO: Establish Game loop
        while (!gameOver) {
            // Watch Phase first
            String randString = watchPhase();
            // Switch to player
            switchPlayer();
            // Player type
            typePhase(randString);
            switchPlayer();
            round++;
        }
    }
}
