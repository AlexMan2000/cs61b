package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    private class Node implements Comparable {
        int id;
        int priority;

        public Node (int id, int priority) {
            this.id = id;
            this.priority = priority;
        }

        @Override
        public int compareTo(Object o) {
            return this.priority - ((Node) o).priority;
        }

        public int getId() {
            return id;
        }
    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int xcorS = maze.toX(v);
        int ycorS = maze.toY(v);
        int xcorT = maze.toX(t);
        int ycorT = maze.toY(t);
        return Math.abs(xcorS - xcorT) + Math.abs(ycorS - ycorT);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // The Code

        MinPQ<Node> fringe = new MinPQ<>();
        fringe.insert(new Node(s, h(s)));

        Node currNode = fringe.delMin();
        Integer currId = currNode.getId();
        marked[s] = true;
        announce();
        while (currNode.getId() != t) {
            Iterable<Integer> neighbors = maze.adj(currId);
            for (Integer neighbor: neighbors) {
                if (!marked[neighbor]) {
                    distTo[neighbor] = distTo[currId] + 1;
                    edgeTo[neighbor] = currNode.getId();
                    fringe.insert(new Node(neighbor, distTo[neighbor] + h(neighbor)));
                }
            }
            currNode = fringe.delMin();
            currId = currNode.getId();
            marked[currNode.getId()] = true;
            announce();
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

