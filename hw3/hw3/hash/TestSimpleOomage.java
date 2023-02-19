package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /* Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
         */
        // We have to draw any possible pair
        List<List<Integer>> params = new ArrayList<>();
        for (int r = 0; r < 52; r++) {
            for (int g = 0; g < 52; g++) {
                for (int b = 0; b < 52; b++) {
                    List<Integer> nested = new ArrayList<>();
                    nested.add(r * 5);
                    nested.add(g * 5);
                    nested.add(b * 5);
                    params.add(nested);
                }
            }
        }
        // Choose two pair
        int size = params.size();
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                List<Integer> param1 = params.get(i);
                List<Integer> param2 = params.get(j);
                SimpleOomage s1 = new SimpleOomage(param1.get(0), param1.get(1), param1.get(2));
                SimpleOomage s2 = new SimpleOomage(param2.get(0), param2.get(1), param2.get(2));
                if (i == j) {
                    assertEquals(s1.hashCode(), s2.hashCode());
                } else {
                    if (s1.hashCode() == s2.hashCode()) {
                        StringBuilder sb1 = new StringBuilder();
                        StringBuilder sb2 = new StringBuilder();
                        sb1.append("SimpleOomage 1, r: " + s1.red + " ");
                        sb1.append("g: " + s1.green + " ");
                        sb1.append("b: " + s1.blue + " ");
                        sb2.append("SimpleOomage 2, r: " + s2.red + " ");
                        sb2.append("g: " + s2.green + " ");
                        sb2.append("b: " + s2.blue + " ");
                        System.out.println(sb1.toString());
                        System.out.println(sb2.toString());
                    }
                    assertNotEquals(s1.hashCode(), s2.hashCode());
                }
            }
        }
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }


    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /* Uncomment this test after you finish haveNiceHashCode Spread in OomageTestUtility */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
