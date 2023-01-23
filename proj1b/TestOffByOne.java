import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    // Uncomment this class once you've created your
    // CharacterComparator interface and OffByOne class.

    @Test
    public void testEqualChars() {
        OffByOne obo = new OffByOne();
        assertEquals(true, obo.equalChars('a', 'b'));
        assertEquals(true, obo.equalChars('r', 'q'));
        assertEquals(false, obo.equalChars('a', 'e'));
    }

    @Test
    public void testOffByOne() {
        char t1 = 'a';
        char t2 = 'b';
        char t3 = 'a';
        char t4 = '&';
        char t5 = '%';
        char t6 = 'B';
        char t7 = '3';
        char t8 = '4';
        assertFalse(offByOne.equalChars(t1, t3));
        assertTrue(offByOne.equalChars(t1, t2));
        assertTrue(offByOne.equalChars(t4, t5));
        assertFalse(offByOne.equalChars(t1, t6));
        assertTrue(offByOne.equalChars(t7, t8));
    }

}
