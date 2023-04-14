package hw4.puzzle;

/**
 * Created by AlexMan
 */
public class SearchNode implements Comparable {

    private WorldState ws;
    private int numMoves;
    private int priority;
    private WorldState previousState;

    public SearchNode(WorldState ws, int numMoves, WorldState prev) {
        this.ws = ws;
        this.numMoves = numMoves;
        this.priority = this.numMoves + this.ws.estimatedDistanceToGoal();
        this.previousState = prev;
    }

    @Override
    public int compareTo(Object o) {
        SearchNode other = (SearchNode) o;
        return this.priority - other.priority;
    }

    public WorldState getWorldState() {
        return ws;
    }

    public int getMoves() {
        return numMoves;
    }

    public WorldState getPreviousState() {
        return previousState;
    }
}
