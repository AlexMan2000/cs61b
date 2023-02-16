package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.Map;

/**
 * Created by AlexMan
 */
public class Clorus extends Creature {

    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;
    /**
     * Creates a creature with the name N. The intention is that this
     * name should be shared between all creatures of the same type.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    @Override
    public void move() {
        this.energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        this.energy += c.energy();
    }

    @Override
    public Creature replicate() {
        double oEnergy = this.energy / 2;
        this.energy -= oEnergy;
        return new Clorus(oEnergy);
    }

    @Override
    public void stay() {
        this.energy -= 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        return null;
    }

    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return new Color(r, g, b);
    }
}
