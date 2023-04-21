import edu.princeton.cs.algs4.Queue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by AlexMan
 */
public class TestingQuickSort {

    @Test
    public void testQuickSort() {
        Queue<String> actual = new Queue<>();
        actual.enqueue("Ethan");
        actual.enqueue("Alice");
        actual.enqueue("Salmon");
        actual.enqueue("Bob");
        actual.enqueue("Zoe");
        actual.enqueue("Vanessa");
        Queue<String> expected = new Queue<>();
        expected.enqueue("Alice");
        expected.enqueue("Bob");
        expected.enqueue("Ethan");
        expected.enqueue("Salmon");
        expected.enqueue("Vanessa");
        expected.enqueue("Zoe");
        Queue<String> resQ = QuickSort.quickSort(actual);
        assertEquals(resQ.size(), expected.size());
        while (!expected.isEmpty()) {
            assertEquals(resQ.dequeue(), expected.dequeue());
        }
    }
}
