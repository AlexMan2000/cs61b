package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int[] edgeHistory;

    public MazeCycles(Maze m) {
        super(m);
        s = m.xyTo1D(1, 1);
        distTo[s] = 0;
//        edgeTo[s] = s;
        edgeHistory = new int[m.V()];
        edgeHistory[s] = s;
    }

    @Override
    public void solve() {
        // Your code here!
        dfs(s, s);
    }

    // Helper methods go here
    private boolean dfs(int current, int parent) {
        marked[current] = true;
        announce();
        for (Integer n: maze.adj(current)) {
            if (!marked[n]) {
                edgeHistory[n] = current;
                distTo[n] = distTo[current] + 1;
                // See if there is already any cycles during traversal
                if (!dfs(n, current)) {
                    return false;
                }
            } else {
                if (n != parent) {
                    edgeHistory[n] = current;
                    int start = current;
                    int prev;
                    while (edgeHistory[start] != current) {
                        prev = start;
                        start = edgeHistory[start];
                        edgeTo[start] = prev;
                    }
                    edgeTo[current] = n;
                    announce();
                    return false;
                }
            }
        }
        return true;
    }
}

