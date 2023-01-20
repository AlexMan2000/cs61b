/**
 * Created by AlexMan
 */
public class LinkedListDeque<T> {

    private DequeNode sentinel;
    private int size;


    // Nested Class Definition with reference to T, so no static keyword here
    public class DequeNode {
        T item;
        DequeNode next;
        DequeNode prev;


        // Constructor
        public DequeNode(T i, DequeNode n, DequeNode p) {
            this.item = i;
            this.next = n;
            this.prev = p;
        }
    }

    // Constructor
    public LinkedListDeque() {
        // default for empty deque
        sentinel = new DequeNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new DequeNode(item, sentinel.next, sentinel);
        size += 1;
    }


    public void addLast(T item) {
        sentinel.next = new DequeNode(item, sentinel, sentinel.prev);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size > 0) {
            DequeNode first = sentinel.next;
            while (first.next != sentinel) {
                System.out.print(first.item + "");
            }
            System.out.println(first.item);
        } else {
            System.out.println("");
        }
    }

    public T removeFirst() {
        size -= 1;
        if (size == 0) {
            return null;
        } else {
            DequeNode nextNext = sentinel.next.next;
            DequeNode next = sentinel.next;
            // Garbage Collection
            next.prev = null;
            next.next = null;
            // Delete the node
            sentinel.next = nextNext;
            return next.item;
        }
    }

    public T removeLast() {
        size -= 1;
        if (size == 0) {
            return null;
        } else {
            DequeNode prevPrev = sentinel.prev.prev;
            DequeNode prev = sentinel.prev;
            // Garbage Collection
            prev.prev = null;
            prev.next = null;
            // Delete the node
            sentinel.prev = prevPrev;
            return prev.item;
        }
    }

    public T get(int index) {
        if (size == 0 || index >= size) {
            return null;
        } else {
            DequeNode first = sentinel.next;
            while (index > 0) {
                index--;
                first = first.next;
            }
            return first.item;
        }
    }

    // Same as get, but uses recursion.
    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, DequeNode node) {
        if (size == 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(index - 1, node.next);
    }
}
