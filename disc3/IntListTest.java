import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by AlexMan
 */
public class IntListTest {


    @Test
    public void testSkippify(){
        IntList p = IntList.of(1,2,3,4,5,6,7,8,9,10);
        p.skippify();
        IntList exp = IntList.of(1,3,6,10);
        assertEquals(exp, p);
    }

}
