import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by AlexMan
 */
public class TestIntList {

    @Test
    public void testDreaming(){
        int[] temp = {1, 2, 3, 4, 5};
        IntList L1 = IntList.list(1, 2, 3, 4, 5);
        IntList L2 = new IntList(temp);

        String t1 = L1.strLinkedList();
        String t2 = L2.strLinkedList();
        System.out.println(t1);
        System.out.println(t2);

        assertEquals(true, t1.equals(t2));
    }
}
