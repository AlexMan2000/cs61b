/**
 * Created by AlexMan
 */
public class UserQueueOneStack<T> extends UserQueue<T>{

    private UserStack<T> s1;
    private int size;

    public UserQueueOneStack() {
        s1 = new UserStack<>();
        size = 0;
    }

    @Override
    public void push(T item) {
        s1.push(item);
    }

    @Override
    public T pop() {
        if (s1.isEmpty()) {
            return null;
        }
        return pop(s1.pop());
    }


    /**
     * Recursive Implementation of pop, helper function
     * @param previous, used as the recursion state
     * @return the bottom element in the stack
     */
    public T pop(T previous) {
        if (s1.isEmpty()) {
            return previous;
        }
        T current = s1.pop();
        T res = pop(current);
        // Revoke
        push(previous);
        return res;
    }
}
