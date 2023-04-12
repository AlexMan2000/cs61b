package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

import java.util.ArrayDeque;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s; // Source Vertex
    private int t; // Target Vertex
    private boolean targetFound; // Wether we have found the target vertex
    private Maze maze; // The maze object

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> fringe = new Queue<>();
        fringe.enqueue(s);
        marked[s] = true;
        announce();
        while (!fringe.isEmpty()) {
            int n = fringe.dequeue();
            if (n == t) {
                targetFound = true;
                return;
            }
            for (Integer neighbor: maze.adj(n)) {
                if (!marked[neighbor]) {
                    fringe.enqueue(neighbor);
                    marked[neighbor] = true;
                    edgeTo[neighbor] = n;
                    distTo[neighbor] = distTo[n] + 1;
                    announce();
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

