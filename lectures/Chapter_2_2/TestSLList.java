import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by AlexMan
 */
public class TestSLList {

    @Test
    public void testDeleteFirst(){
        SLList L = new SLList(10);
        L.addFirst(20);
        L.addFirst(30);
        L.deleteFirst();

        SLList exp = new SLList(10);
        exp.addFirst(20);
        assertEquals(20, L.getFirst());
    }

    @Test
    public void testDeleteFirstNullArguments(){
        SLList L = new SLList();
        SLList exp = new SLList();
        assertEquals(exp.getFirst(), L.getFirst());
    }

}
