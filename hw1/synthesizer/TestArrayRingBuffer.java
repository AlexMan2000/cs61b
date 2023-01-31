package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testEnQeQueue() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        System.out.println("enqueue()");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            arb.enqueue(i);
        }

        System.out.println("dequeue()");
        for (int i = 0; i < 11; i++) {
            System.out.println(i);
            arb.dequeue();
        }
    }


    @Test
    public void testIteration() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(10);
        System.out.println("enqueue()");
        for (double i = 0; i < 10; i++) {
            System.out.println(i);
            arb.enqueue(i);
        }
        for (double elem: arb) {
            System.out.println(elem);
        }

        System.out.println("Creating Overflow");
        for (double i = 0; i < 10; i++) {
            System.out.println(i);
            arb.enqueue(i);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
