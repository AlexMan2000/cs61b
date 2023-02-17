package hw2;

//import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

//import edu.princeton.cs.algs4.QuickFindUF;

public class Percolation {
    private int size;
    private int[][] grid;
    private WeightedQuickUnionUF wqu;
//    private QuickFindUF wqu;
    private int numOpen;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be positive integer!");
        }

        // Auto-initialized to be zero.
        grid = new int[N][N];
        size = N;
        numOpen = 0;

        // Initialize the disjoint set data structure to track the connection
        // WeightedQuickUnionUF, creating N*N nodes 0,1,...,N*N, row by row
        wqu = new WeightedQuickUnionUF(N * N);
//        wqu = new QuickFindUF(N * N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateCor(row, col);
        // Deduplicate
        if (isOpen(row, col)) {
            return;
        }

        grid[row][col] = 1;

        // See if any neighbors have been opened, if so, connect them!
        boolean isO;
        if (col + 1 < size) {
            isO = isOpen(row, col + 1);
            if (isO) {
                wqu.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }
        }
        if (col - 1 >= 0) {
            isO = isOpen(row, col - 1);
            if (isO) {
                wqu.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            }
        }
        if (row + 1 < size) {
            isO = isOpen(row + 1, col);
            if (isO) {
                wqu.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
        }
        if (row - 1 >= 0) {
            isO = isOpen(row - 1, col);
            if (isO) {
                wqu.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
        }
        numOpen++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateCor(row, col);
        return grid[row][col] == 1; // opened
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateCor(row, col);
        if (row == 0) {
            if (isOpen(row, col)) {
                return true;
            }
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                int grid1Dtop = xyTo1D(0, i);
                int grid1D = xyTo1D(row, col);
                if (wqu.connected(grid1Dtop, grid1D)) {
                    return true;
                }
            }
            return false;
        }
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        // If any of the bottom elements are connected to the top ones, then the system percolates
        if (size == 1) {
            if (numberOfOpenSites() == 1) {
                return true;
            }
        } else {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    // Bottom row
                    int bGrid1D = xyTo1D(size - 1, i);
                    // Top Row
                    int tGrid1D = xyTo1D(0, j);
                    if (wqu.connected(bGrid1D, tGrid1D)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // use for unit testing (not required)
    public static void main(String[] args) {

    }


    /**
     * Some Helper Methods
     */


    /**
     * Map a 2D point to 1D representation for disjoint set
     * @param row
     * @param col
     * @return
     */
    private int xyTo1D(int row, int col) {
        return row * size + col;
    }

    /**
     * Validate whether a 2D coordinate is valid, otherwise throw an exception
     * @param row
     * @param col
     */
    private void validateCor(int row, int col) {
        if (!(row >= 0 && row < size && col >= 0 && col < size)) {
            throw new IndexOutOfBoundsException("Invalid coordinate, please check your input!");
        }
    }
}
