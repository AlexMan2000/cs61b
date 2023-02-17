package hw2;

//import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

//import edu.princeton.cs.algs4.QuickFindUF;

public class Percolation {
    private int size;
    private int[][] grid;
    // The disjoint set with top and bottom virtual nodes
    private WeightedQuickUnionUF wqu;
    // The disjoint set with just the top virtual node.
    private WeightedQuickUnionUF wquForBlockedBackWash;
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
        // Creating node 0,1,2,...., N*N - 1 for grid points
        // Adding two virtual nodes, where N * N for top virtual point
        // and N * N + 1 for bottom virtual point
        wqu = new WeightedQuickUnionUF(N * N + 2);
        wquForBlockedBackWash = new WeightedQuickUnionUF(N * N + 1);
//        wqu = new QuickFindUF(N * N + 2);

        // Initialize the concrete grids
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
                wquForBlockedBackWash.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }
        }
        if (col - 1 >= 0) {
            isO = isOpen(row, col - 1);
            if (isO) {
                wqu.union(xyTo1D(row, col), xyTo1D(row, col - 1));
                wquForBlockedBackWash.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            }
        }
        if (row + 1 < size) {
            isO = isOpen(row + 1, col);
            if (isO) {
                wqu.union(xyTo1D(row, col), xyTo1D(row + 1, col));
                wquForBlockedBackWash.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
        }
        if (row - 1 >= 0) {
            isO = isOpen(row - 1, col);
            if (isO) {
                wqu.union(xyTo1D(row, col), xyTo1D(row - 1, col));
                wquForBlockedBackWash.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
        }
        // Connect to the top
        if (row == 0) {
            wqu.union(xyTo1D(row, col), size * size);
            wquForBlockedBackWash.union(xyTo1D(row, col), size * size);
        }
        // Don't connect to the bottom for wquForBlockedBackWash
        if (row == size - 1) {
            wqu.union(xyTo1D(row, col), size * size + 1);
        }
        numOpen++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateCor(row, col);
        return grid[row][col] == 1; // opened
    }

    // is the site (row, col) full? Should prevent backwash
    public boolean isFull(int row, int col) {
        validateCor(row, col);
        return wquForBlockedBackWash.connected(xyTo1D(row, col), size * size);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        // If the top and bottom are connected, then the system percolates.
        return wqu.connected(size * size, size * size + 1);
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
