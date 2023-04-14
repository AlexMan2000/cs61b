package hw4.puzzle;

public class Board implements WorldState{
    private int[][] tiles;
    private int N;


    // Constructs a board from an N-by-N array of tiles where
    // tiles[i][j] = tile at row i, column j
    public Board(int[][] tiles) {
        // Ensures immutability
        N = tiles.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }

    }

    // Returns value of tile at row i, column j (or 0 if blank)
    public int tileAt(int i, int j) {
        if (i >= 0 && i <= N && j >= 0 && j <= N) {
            return this.tiles[i][j];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    // Returns the board size N
    public int size() {
        return N;
    }

    //  Returns the neighbors of the current board
    @Override
    public Iterable<WorldState> neighbors() {
        return null;
    }

    // Hamming estimate described below
    public int hamming() {
        return 0;
    }

    // Manhattan estimate described below
    public int manhattan() {
        return 0;
    }

    // Estimated distance to goal. This method should
    // simply return the results of manhattan() when submitted to
    // Gradescope.
    @Override
    public int estimatedDistanceToGoal() {
        return 0;
    }

    // Returns true if this board's tile values are the same
    // position as y's
    @Override
    public boolean equals(Object y) {
        return false;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
