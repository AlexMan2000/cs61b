import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexMan
 */
public class UserQueue<T> {

    private int size;
    private UserStack<T> s1;

    public UserQueue() {
        s1 = new UserStack<>();
        size = 0;
    }


    public void push(T item) {
        s1.push(item);
        size++;
    }

    public T pop() {
        if (s1.isEmpty()) {
            return null;
        }
        UserStack<T> auxStack = new UserStack<>();
        while (!s1.isEmpty()) {
            auxStack.push(s1.pop());
        }
        T elem = auxStack.pop();
        while (!auxStack.isEmpty()) {
            s1.push(auxStack.pop());
        }
        size--;
        return elem;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        UserStack<T> temp = new UserStack<>();
        int popNum = size - index;
        while (popNum > 1) {
            temp.push(s1.pop());
            popNum--;
        }
        T res = s1.pop();
        // Restore the data structure
        s1.push(res);
        while (!temp.isEmpty()) {
            s1.push(temp.pop());
        }
        return res;
    }

    /**
     * Used for test
     * @return
     */
    public List<T> getArray() {
        UserStack<T> us = new UserStack<>();
        List<T> list = new ArrayList<>();
        while (!s1.isEmpty()) {
            us.push(s1.pop());
        }
        while (!us.isEmpty()) {
            list.add(us.pop());
        }
        return list;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (this == null || o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        UserQueue<T> casted = (UserQueue) o;
        List<T> castedArray = casted.getArray();
        List<T> thisArray = this.getArray();
        return castedArray.equals(thisArray);
    }


    @Override
    public String toString() {
        List<T> thisArray = this.getArray();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < thisArray.size() - 1; i++) {
            sb.append(thisArray.get(i) + ", ");
        }
        sb.append(thisArray.get(thisArray.size() - 1));
        sb.append("]");
        return sb.toString();
    }
}
