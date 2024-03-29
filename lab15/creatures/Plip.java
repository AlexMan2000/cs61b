package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Random;

/** An implementation of a motile pacifist photosynthesizer.
 *  @author Josh Hug
 */
public class Plip extends Creature {

    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;

    /** creates plip with energy equal to E. */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /** creates a plip with energy equal to 1. */
    public Plip() {
        this(1);
    }

    /** Should return a color with red = 99, blue = 76, and green that varies
     *  linearly based on the energy of the Plip. If the plip has zero energy,
     *  it should have a green value of 63. If it has max energy, it should
     *  have a green value of 255. The green value should vary with energy
     *  linearly in between these two extremes. It's not absolutely vital
     *  that you get this exactly correct.
     */
    @Override
    public Color color() {
        r = 99;
        b = 76;
        g = (int) Math.round(this.energy * 0.5 * (255 - 63) + 63);
        return color(r, g, b);
    }

    /** Do nothing with C, Plips are pacifists. */
    @Override
    public void attack(Creature c) {
    }

    /** Plips should lose 0.15 units of energy when moving. If you want to
     *  to avoid the magic number warning, you'll need to make a
     *  private static final variable. This is not required for this lab.
     */
    @Override
    public void move() {
        this.energy -= 0.15;
    }


    /** Plips gain 0.2 energy when staying due to photosynthesis. */
    @Override
    public void stay() {
        this.energy = Math.min(this.energy + 0.2, 2);
    }

    /** Plips and their offspring each get 50% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Plip.
     */
    @Override
    public Plip replicate() {
        Plip np = new Plip(this.energy * 0.5);
        this.energy *= 0.5;
        return np;
    }

    /** Plips take exactly the following actions based on NEIGHBORS:
     *  1. If no empty adjacent spaces, STAY.
     *  2. Otherwise, if energy >= 1, REPLICATE.
     *  3. Otherwise, if any Cloruses, MOVE with 50% probability.
     *  4. Otherwise, if nothing else, STAY
     *
     *  Returns an object of type Action. See Action.java for the
     *  scoop on how Actions work. See SampleCreature.chooseAction()
     *  for an example to follow.
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> available = new ArrayList<>();
        boolean seeClorus = false;
        for (Direction d: neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                available.add(d);
            }
            if (neighbors.get(d).name().equals("clorus")) {
                seeClorus = true;
            }
        }

        if (available.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else {
            if (this.energy > 1) {
                Random r = new Random();
                int rIndex = r.nextInt(available.size());
                return new Action(Action.ActionType.REPLICATE, available.get(rIndex));
            } else {
                if (seeClorus) {
                    Random r = new Random();
                    int rIndex = r.nextInt(available.size());
                    // 一半的概率会move
                    double rDouble = r.nextDouble(); // 我们可以看源码，发现nextDouble就是一个均匀分布
                    if (rDouble < 0.5) {
                        // 返回Action
                        return new Action(Action.ActionType.MOVE, available.get(rIndex));
                    } else {
                        return new Action(Action.ActionType.STAY);
                    }
                } else {
                    return new Action(Action.ActionType.STAY);
                }
            }
        }
    }
}
