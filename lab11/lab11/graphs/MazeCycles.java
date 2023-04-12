package lab11.graphs;
import java.util.ArrayList;
import java.util.List;

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
        edgeTo[s] = s;
        edgeHistory = new int[m.V()];
        edgeHistory[s] = s;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(s, s);
    }

    // Helper methods go here
    private void dfs(int current, int parent) {
        marked[current] = true;
        announce();
        for (Integer n: maze.adj(current)) {
            if (!marked[n]) {
                marked[n] = true;
                announce();
                edgeHistory[n] = current;
                distTo[n] = distTo[current] + 1;
                dfs(n, current);
            } else {
                if (n != parent) {
                    edgeHistory[n] = current;
                    announce();
                    int start = n;
                    int prev = start;
                    System.out.println(start);
                    while (edgeHistory[start] != n) {
                        prev = start;
                        start = edgeHistory[start];
                        edgeTo[start] = prev;
                        System.out.println(start);
                        announce();
                    }
                    return;
                }
            }
        }
    }
}

