package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

/**
 * Created by AlexMan
 */
public class Solver {
    private MinPQ<WorldState> fringe;  // The priority queue data structure
    private int numMoves;              // The minimum number of moves to solve the puzzle starting at the initial WorldState.
    private Iterable<WorldState> path; // A sequence of WorldStates from the initial WorldState to the solution

    public Solver(WorldState initial) {
        // 1. Initialize the priority queue
        fringe = new MinPQ<>();

        // 2. Insert the initial world state
        fringe.insert(initial);

        // 3. Start solving the puzzle
        solver();
    }

    public int moves() {

    }


    public Iterable<WorldState> solution() {

    }

    private void solver() {
        WorldState curr = fringe.delMin();
        while (!curr.isGoal()) {
        }
    }
}
