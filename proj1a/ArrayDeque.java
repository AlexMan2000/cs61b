/**
 * Created by AlexMan
 */
public class ArrayDeque<T> {

    private static int MAXCAPACITY = 8;
    private static double LOADING_FACTOR = 0.25;
    private T[] items;
    private int size;
    private int front = 0; // The pointer to the front of the deque


    // Constructor
    public ArrayDeque() {
        items = (T[]) new Object[MAXCAPACITY];
        size = 0;
    }

    public void addFirst(T item) {
        if (size == MAXCAPACITY) {
            incResize();
        }
        if (isEmpty()) {
            items[front] = item;
        } else {
            // Loop back to the end
            int newFront = (front + MAXCAPACITY - 1) % MAXCAPACITY;
            items[newFront] = item;
            front = newFront;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == MAXCAPACITY) {
            incResize();
        }
        items[(front + size) % MAXCAPACITY] = item;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size - 1; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println(items[size - 1]);
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int newFront = (front + MAXCAPACITY + 1) % MAXCAPACITY;
        T res = items[front];
        // Garbage Collection
        items[front] = null;
        front = newFront;
        size -= 1;
        decResize();
        return res;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T res = items[(front + size - 1) % MAXCAPACITY];
        // Garbage Collection
        items[(front + size - 1) % MAXCAPACITY] = null;
        size -= 1;
        decResize();
        return res;
    }

    public T get(int index) {
        if (index >= size || isEmpty()) {
            return null;
        }
        return items[(front + index) % MAXCAPACITY];
    }


    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            int oldIndex = (front + i) % MAXCAPACITY;
            int newIndex = (i) % newSize;
            newItems[newIndex] = items[oldIndex];
        }
        MAXCAPACITY = newSize;
        items = newItems;
        // Set the new front
        front = 0;
    }

    // Resize the arraydeque
    private void incResize() {
        int newSize = MAXCAPACITY * 2;
        resize(newSize);
    }

    // Downsize the arraydeque
    private void decResize() {
        if (MAXCAPACITY >= 16 && size < Math.round(MAXCAPACITY * LOADING_FACTOR)) {
            resize(MAXCAPACITY / 2);
        }
    }

//    @Override
//    public String toString() {
//        if (isEmpty()) {
//            return "[]";
//        }
//        StringBuilder res = new StringBuilder();
//        res.append("[");
//        for (int i = 0; i < size - 1; i++) {
//            res.append(items[(front + i) % MAXCAPACITY]);
//            res.append(", ");
//        }
//        res.append(items[(front + size - 1) % MAXCAPACITY]);
//        res.append("]");
//        return res.toString();
//    }
}
