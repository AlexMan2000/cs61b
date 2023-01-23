import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by AlexMan
 */
public class SList {
    public class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }
    private static IntNode sentinel; // static sentinel is the flaw

    public SList() {
        sentinel = new IntNode(982734, null);
    }
    public void insertFront(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
    }
    public int getFront() {
        if (sentinel.next == null) {
            return -1;
        }
        return sentinel.next.item;
    }

    @Test
    public void test() {
        SList s1 = new SList();
        SList s2 = new SList();
        s1.insertFront(1);
        s2.insertFront(2);
        assertNotEquals(s1.getFront(), s2.getFront());
        assertEquals(1, s1.getFront()); /* also fails */
    }
}
