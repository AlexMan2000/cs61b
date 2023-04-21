import edu.princeton.cs.algs4.Queue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by AlexMan
 */
public class TestingMergeSort {

    @Test
    public void testMakeSingleItemQueues() {
        Queue<String> students = new Queue<>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
//        Queue<Queue<String>> res = MergeSort.makeSingleItemQueues(students);
//        for (Queue<String> tq: res) {
//            assertEquals(tq.dequeue(),students.dequeue());
//        }
    }

    @Test
    public void mergeSortedQueues() {
        Queue<String> students1 = new Queue<>();
        students1.enqueue("Alice");
        students1.enqueue("Ethan");
        students1.enqueue("Vanessa");
        Queue<String> students2 = new Queue<>();
        students2.enqueue("Bob");
        students2.enqueue("Salmon");
        students2.enqueue("Zoe");
//        Queue<String> resQ = MergeSort.mergeSortedQueues(students1, students2);
        Queue<String> actual = new Queue<>();
        actual.enqueue("Alice");
        actual.enqueue("Bob");
        actual.enqueue("Ethan");
        actual.enqueue("Salmon");
        actual.enqueue("Vanessa");
        actual.enqueue("Zoe");
//        assertEquals(resQ.size(), actual.size());
//        while (!actual.isEmpty()) {
//            assertEquals(resQ.dequeue(), actual.dequeue());
//        }
    }

    @Test
    public void testMergeSort() {
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
        Queue<String> resQ = MergeSort.mergeSort(actual);
        assertEquals(resQ.size(), expected.size());
        while (!expected.isEmpty()) {
            assertEquals(resQ.dequeue(), expected.dequeue());
        }
    }
}
