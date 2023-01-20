import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by AlexMan
 */
public class FlattenTest {

    @Test
    public void testFlatten(){
        int[][] a = new int[][] {{1,2},{3,4}};
        int[] flattened = Flatten.flatten(a);
        int[] exp = new int[] {1,2,3,4};
        assertArrayEquals(exp, flattened);
    }
}
