import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by AlexMan
 */
public class IntList2Test {

    @Test
    public void testIntList2(){
        IntList2 t4 = new IntList2(30, null);
        IntList2 t3 = new IntList2(20, t4);
        IntList2 t2 = new IntList2(10, t3);
        IntList2 t1 = new IntList2(5, t2);

        IntList2 tm4 = new IntList2(30, null);
        IntList2 tm3 = new IntList2(10, tm4);
        IntList2 tm2 = new IntList2(20, tm3);
        IntList2 tm1 = new IntList2(5, tm2);

        IntList2.evenOdd(t1);

        assertEquals(tm1.toString(),t1.toString());
    }
}
