package hw4.puzzle;

import java.util.ArrayList;
import java.util.List;

public class Board implements WorldState {
    private int[][] tiles;
    private int size;
    private enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    private static class Coordinates {
        int x;
        int y;

        Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void setX(int x) {
            this.x = x;
        }

        void setY(int y) {
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Coordinates other = (Coordinates) o;

            return other.getX() == this.getX() && other.getY() == this.getY();
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }

    }

    // Constructs a board from an N-by-N array of tiles where
    // tiles[i][j] = tile at row i, column j
    public Board(int[][] tiles) {
        // Ensures immutability
        size = tiles.length;
        this.tiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // Returns value of tile at row i, column j (or 0 if blank)
    public int tileAt(int i, int j) {
        if (i >= 0 && i <= size && j >= 0 && j <= size) {
            return this.tiles[i][j];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    // Check whether the tile at index i,j is inBound
    private boolean checkTileInBound(int i, int j) {
        if (i >= 0 && i < size && j >= 0 && j < size) {
            return true;
        }
        return false;
    }

    private void switchTile(int i, int j, Direction dir) {
        switch (dir) {
            case UP:
                switchTile(i, j, i - 1, j);
                break;
            case DOWN:
                switchTile(i, j, i + 1, j);
                break;
            case LEFT:
                switchTile(i, j, i, j - 1);
                break;
            case RIGHT:
                switchTile(i, j, i, j + 1);
                break;
            default:
                break;
        }
    }

    private void switchTile(int x1, int y1, int x2, int y2) {
        int temp = tiles[x2][y2];
        tiles[x2][y2] = tiles[x1][y1];
        tiles[x1][y1] = temp;
    }

    // Returns the board size N
    public int size() {
        return size;
    }

    //  Returns the neighbors of the current board
    @Override
    public Iterable<WorldState> neighbors() {
        // x is the row, y is the column
        int x = -1;
        int y = -1;
        List<WorldState> neighbors = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) == 0) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        // Generate the neighbor world state based on the current one
        if (checkTileInBound(x - 1, y)) {
            // Check the up direction
            switchTile(x, y, Direction.UP);
            neighbors.add(new Board(this.tiles));
            switchTile(x, y, Direction.UP);
        }
        if (checkTileInBound(x + 1, y)) {
            // Check the down direction
            switchTile(x, y, Direction.DOWN);
            neighbors.add(new Board(this.tiles));
            switchTile(x, y, Direction.DOWN);
        }
        if (checkTileInBound(x, y - 1)) {
            // Chcek the left direction
            switchTile(x, y, Direction.LEFT);
            neighbors.add(new Board(this.tiles));
            switchTile(x, y, Direction.LEFT);
        }
        if (checkTileInBound(x, y + 1)) {
            // Check the right direction
            switchTile(x, y, Direction.RIGHT);
            neighbors.add(new Board(this.tiles));
            switchTile(x, y, Direction.RIGHT);
        }
        return neighbors;
    }

    private int[][] generateGoal() {
        int[][] goal = new int[size][size];
        int index = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                goal[i][j] = index;
                index++;
            }
        }
        goal[size - 1][size - 1] = 0;
        return goal; // pass by reference
    }

    private static Coordinates computeCoordinate(int number, int[][] grid) {
        int N = grid.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == number) {
                    return new Coordinates(i, j);
                }
            }
        }
        return null;
    }


    private static int computeManhattan(Coordinates c1, Coordinates c2) {
        return Math.abs(c1.getX() - c2.getX()) + Math.abs(c1.getY() - c2.getY());
    }

    private static int computeHamming(Coordinates c1, Coordinates c2) {
        return c1.equals(c2) ? 0 : 1;
    }


    // Hamming estimate described below
    public int hamming() {
        int distance = 0;
        int[][] goal = generateGoal();

        for (int i = 1; i < size * size; i++) {
            Coordinates goalGrid = computeCoordinate(i, goal);
            Coordinates currGird = computeCoordinate(i, this.tiles);
            distance += computeHamming(goalGrid, currGird);
        }

        return distance;
    }

    // Manhattan estimate described below
    public int manhattan() {
        int distance = 0;
        int[][] goal = generateGoal();
        for (int i = 1; i < size * size; i++) {
            Coordinates goalGrid = computeCoordinate(i, goal);
            Coordinates currGird = computeCoordinate(i, this.tiles);
            distance += computeManhattan(goalGrid, currGird);
        }
        return distance;
    }

    // Estimated distance to goal. This method should
    // simply return the results of manhattan() when submitted to
    // Gradescope.
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    // Returns true if this board's tile values are the same
    // position as y's
    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }

        Board other = (Board) y;
        if (other.size() != size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (other.tileAt(i, j) != tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Don't forget to write hashcodes
    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                hash = hash * 31 + tileAt(i, j);
            }
        }
        return hash;
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
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
