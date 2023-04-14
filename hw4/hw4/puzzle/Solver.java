package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by AlexMan
 */
public class Solver {
    /*
     The priority queue data structure, containing the priority of WorldState
     */
    private MinPQ<SearchNode> fringe;

    /*
    The minimum number of moves to solve the puzzle starting at the initial WorldState.
     */
    private int numMoves;

    /*
    A sequence of WorldStates from the initial WorldState to the solution
     */
    private List<WorldState> path;

    /*
     A data structure that stores the visited nodes
     */
    private Set<WorldState> visited;

    public int numEnqueued;

    public Solver(WorldState initial) {
        // 1. Initialize the priority queue&solution path
        fringe = new MinPQ<>();
        path = new ArrayList<>();
        visited = new HashSet<>();
        numMoves = 0;

        // 2. Insert the initial world state
        fringe.insert(new SearchNode(initial,
                0, null));
        numEnqueued++;
        path.add(initial);

        // 3. Start solving the puzzle
//        solver();
        solver2();
    }

    public int moves() {
        return numMoves;
    }


    public Iterable<WorldState> solution() {
        return path;
    }

    // Fancier Way Optimization
    private void solver() {
        SearchNode currNode = fringe.delMin();
        WorldState currState = currNode.getWorldState();
        visited.add(currState);
        while (!currState.isGoal()) {
            path.add(currState);
            visited.add(currState);
            Iterable<WorldState> neighbors = currState.neighbors();
            for (WorldState ws: neighbors) {
                // Fancier Critical Optimization
                if (!visited.contains(ws)) {
                    fringe.insert(new SearchNode(ws,
                            currNode.getMoves() + 1, currState));
                    numEnqueued++;
                }
            }
            currNode = fringe.delMin();
            currState = currNode.getWorldState();
        }
        numMoves = currNode.getMoves();
    }

    // Only checks for te grandparents
    private void solver2() {
        SearchNode currNode = fringe.delMin();
        WorldState currState = currNode.getWorldState();
        while (!currState.isGoal()) {
            path.add(currState);
            Iterable<WorldState> neighbors = currState.neighbors();
            for (WorldState ws: neighbors) {
                // Only checks for te grandparents
                if (ws != currNode.getPreviousState()) {
                    fringe.insert(new SearchNode(ws,
                            currNode.getMoves() + 1, currState));
                    numEnqueued++;
                }
            }
            currNode = fringe.delMin();
            currState = currNode.getWorldState();
        }
        numMoves = currNode.getMoves();
    }
}
