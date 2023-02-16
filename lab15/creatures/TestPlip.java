package creatures;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
import java.util.List;

import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Tests the plip class   
 *  @authr FIXME
 */

public class TestPlip {

    /* Replace with the magic word given in lab.
     * If you are submitting early, just put in "early" */
    public static final String MAGIC_WORD = "";

    @Test
    public void testBasics() {
        Plip p = new Plip(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(99, 255, 76), p.color());
        p.move();
        assertEquals(1.85, p.energy(), 0.01);
        p.move();
        assertEquals(1.70, p.energy(), 0.01);
        p.stay();
        assertEquals(1.90, p.energy(), 0.01);
        p.stay();
        assertEquals(2.00, p.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Plip p = new Plip(1.5);
        Plip op = p.replicate();
        assertNotSame(p, op);
    }

    @Test
    public void testChoose() {
        Plip p = new Plip(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        //You can create new empties with new Empty();
        //Despite what the spec says, you cannot test for Cloruses nearby yet.
        //Sorry!  

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);
    }

    @Test
    public void testChooseRule2() {
        Plip p = new Plip(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Empty());
        surrounded.put(Direction.RIGHT, new Impassible());


        Action actual = p.chooseAction(surrounded);
        List<Action> expected = new ArrayList<>();
        expected.add(new Action(Action.ActionType.REPLICATE,Direction.LEFT));
        expected.add(new Action(Action.ActionType.REPLICATE,Direction.TOP));

        assertTrue(expected.contains(actual));
    }

    @Test
    public void testChooseRule3() {
        Plip p = new Plip(0.8);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Empty());
        surrounded.put(Direction.RIGHT, new Impassible());


        Action actual = p.chooseAction(surrounded);
        List<Action> expected = new ArrayList<>();
        expected.add(new Action(Action.ActionType.REPLICATE,Direction.LEFT));
        expected.add(new Action(Action.ActionType.REPLICATE,Direction.TOP));

        assertFalse(expected.contains(actual));
    }

    @Test
    public void testChooseRule4() {

    }

    public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestPlip.class));
    }
} 
