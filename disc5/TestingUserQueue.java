import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by AlexMan
 */
public class TestingUserQueue {

    @Test
    public void randomizeTestPushPop() {
        UserQueue<Integer> s1 = new UserQueue<>();
        UserQueueOneStack<Integer> s2 = new UserQueueOneStack<>();
        RightQueue<Integer> exp = new RightQueue<>();
        StringBuilder sb = new StringBuilder();
        int N = 1000;
        for (int i = 0; i < N; i++) {
            double rand = StdRandom.uniform();
            int randInt = StdRandom.uniform(10);
            if (rand > 0.5) {
                s1.push(randInt);
                s2.push(randInt);
                exp.push(randInt);
                sb.append("push(");
                sb.append(randInt + ")\n");
            } else {
                Integer popped1 = s1.pop();
                Integer popped2 = s2.pop();
                Integer poppedExp = exp.pop();
                assertEquals("s1 pop()", popped1, poppedExp);
                assertEquals("s2 pop()", popped2, poppedExp);
            }
        }

    }

    @Test
    public void testGet() {
        UserQueue<Integer> s1 = new UserQueue<>();
        for (int i = 0; i < 5; i++) {
            s1.push(i);
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(s1.get(i), (Integer) i);
        }
    }
}
