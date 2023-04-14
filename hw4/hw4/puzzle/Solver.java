package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

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

    private int numEnqueued;

    private SearchNode finalNode;

    public Solver(WorldState initial) {
        // 1. Initialize the priority queue&solution path
        fringe = new MinPQ<>();
        path = new ArrayList<>();
        visited = new HashSet<>();
        numMoves = 0;

        // 2. Insert the initial world state
        fringe.insert(new SearchNode(initial,
                0, null, null));
        numEnqueued++;

        // 3. Start solving the puzzle
        solver();
//        solver2();
    }

    public int moves() {
        return numMoves;
    }


    public Iterable<WorldState> solution() {
        // Construct the path from the finalNode
        Stack<WorldState> reversePath = new Stack<>();
        reversePath.push(finalNode.getWorldState());
        while (finalNode.getPreviousNode() != null) {
            finalNode = finalNode.getPreviousNode();
            reversePath.push(finalNode.getWorldState());
        }
        while (!reversePath.isEmpty()) {
            path.add(reversePath.pop());
        }
        return path;
    }

    // Fancier Way Optimization
    private void solver2() {
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
                            currNode.getMoves() + 1, currState, currNode));
                    numEnqueued++;
                }
            }
            currNode = fringe.delMin();
            currState = currNode.getWorldState();
        }
        numMoves = currNode.getMoves();
    }

    // Only checks for te grandparents
    private void solver() {
        SearchNode currNode = fringe.delMin();
        WorldState currState = currNode.getWorldState();
        while (!currState.isGoal()) {
            /* Notice that some students would construct the path
               along the way like the following
               path.add(currState); But this is wrong because
               we may add some node that doesn't belong to
               our path(each time we dequeue the minimum priority element,
               which is not guaranteed to be on the path)
               , see A star algorithm for more details
            */
            Iterable<WorldState> neighbors = currState.neighbors();
            for (WorldState ws: neighbors) {
                // Only checks for te grandparents, make sure we use equals()
                // instead of == for instance comparison
                if (!ws.equals(currNode.getPreviousState())) {
                    fringe.insert(new SearchNode(ws,
                            currNode.getMoves() + 1, currState, currNode));
                    numEnqueued++;
                }
            }
            currNode = fringe.delMin();
            currState = currNode.getWorldState();
        }
        numMoves = currNode.getMoves();
        finalNode = currNode;
    }
}
