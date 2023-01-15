import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by AlexMan
 */
public class TestIntList {


    @Test
    public void testdAddAdjacent(){
        IntList L6 = new IntList(4, null);
        IntList L5 = new IntList(3, L6);
        IntList L4 = new IntList(3, L5);
        IntList L3 = new IntList(2, L4);
        IntList L2 = new IntList(1, L3);
        IntList L1 = new IntList(1, L2);
        L1.addAdjacent();

        IntList M3 = new IntList(4, null);
        IntList M2 = new IntList(6, M3);
        IntList M1 = new IntList(4, M2);
        String t1 = L1.strLinkedList();
        String t2 = M1.strLinkedList();

        System.out.println(t1);
        System.out.println(t2);

        assertEquals(true, t1.equals(t2));
    }

    @Test
    public void testConstructor(){
        IntList L6 = new IntList(4, null);
        IntList L5 = new IntList(3, L6);
        IntList L4 = new IntList(3, L5);
        IntList L3 = new IntList(2, L4);
        IntList L2 = new IntList(1, L3);
        IntList L1 = new IntList(1, L2);

        int[] temp = {1, 1, 2, 3, 3, 4};
        IntList M1 = new IntList(temp);
        String t1 = L1.strLinkedList();
        String t2 = M1.strLinkedList();

        System.out.println(t1);
        System.out.println(t2);

        assertEquals(true, t1.equals(t2));
    }


    @Test
    public void testSquareList(){
        int[] temp1 = {1,2};
        int[] temp2 = {1,1,2,4};
        IntList L1 = new IntList(temp1);
        IntList L2 = new IntList(temp2);

        L1.squareList();

        String t1 = L1.strLinkedList();
        String t2 = L2.strLinkedList();
        System.out.println(t1);
        System.out.println(t2);

        assertEquals(true, t1.equals(t2));
    }


    @Test
    public void testSquareListAddFirst(){
        int[] temp1 = {1,2};
        int[] temp2 = {5,1,1,2,4};
        IntList L1 = new IntList(temp1);
        IntList L2 = new IntList(temp2);

        L1.addFirst(5);

        String t1 = L1.strLinkedList();
        String t2 = L2.strLinkedList();
        System.out.println(t1);
        System.out.println(t2);

        assertEquals(true, t1.equals(t2));
    }
}
