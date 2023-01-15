import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by AlexMan
 */
public class TestSelectionSort {

    @Test
    public void testFindSmallest(){
        String[] input = {"i", "have", "an", "egg"};
        int expected = 2;

        int actual = Sort.findSmallest(input, 0);
        assertEquals(expected, actual);

        String[] input2 = {"there", "are", "many", "pigs"};
        int expected2 = 2;

        int actual2 = Sort.findSmallest(input2, 2);
        assertEquals(expected2, actual2);
    }
}
