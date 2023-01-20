/**
 * Created by AlexMan
 */
public class LinkedListDeque<T> {

    private DequeNode sentinel;
    private int size;


    // Nested Class Definition with reference to T, so no static keyword here
    private class DequeNode {
        T item;
        DequeNode next;
        DequeNode prev;


        // Constructor
        DequeNode(T i, DequeNode n, DequeNode p) {
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
        if (isEmpty()) {
            DequeNode newNode = new DequeNode(item, sentinel.next, sentinel);
            sentinel.next = newNode;
            sentinel.prev = newNode;
        } else {
            DequeNode nextNode = sentinel.next;
            sentinel.next = new DequeNode(item, nextNode, sentinel);
            nextNode.prev = sentinel.next;
        }
        size += 1;
    }


    public void addLast(T item) {
        if (isEmpty()) {
            DequeNode newNode = new DequeNode(item, sentinel, sentinel.prev);
            sentinel.next = newNode;
            sentinel.prev = newNode;
        } else {
            DequeNode lastNode = sentinel.prev;
            sentinel.prev = new DequeNode(item, sentinel, lastNode);
            lastNode.next = sentinel.prev;
        }
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
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            DequeNode nextNext = sentinel.next.next;
            T res = sentinel.next.item;
            // Garbage Collection, 要让指向这个节点的节点放弃对该节点的引用
            // Delete the node
            sentinel.next = nextNext;
            nextNext.prev = sentinel;
            return res;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            DequeNode prevPrev = sentinel.prev.prev;
            T res = sentinel.prev.item;
            // Garbage Collection
            // Delete the node
            sentinel.prev = prevPrev;
            prevPrev.next = sentinel;
            return res;
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

//    @Override
//    public String toString() {
//        if (size == 0) {
//            return "[]";
//        } else {
//            StringBuilder res = new StringBuilder();
//            res.append("[");
//            DequeNode first = sentinel.next;
//            while (first.next!= sentinel){
//                res.append(first.item);
//                first = first.next;
//                res.append(", ");
//            }
//            res.append(first.item);
//            res.append("]");
//            return res.toString();
//        }
//    }
}
