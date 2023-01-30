import edu.princeton.cs.algs4.Queue;

/**
 * Created by AlexMan
 */
public class RightQueue<T> extends Queue<T> {
    public void push(T item) {
        enqueue(item);
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return dequeue();
    }
}
