/**
 * Created by AlexMan
 */
public class LinkedListDeque<T> implements Deque<T> {

    public dequeNode sentinel;
    public int size;


    // Nested Class Definition with reference to T, so no static keyword here
    public class dequeNode{
        T item;
        dequeNode next;
        dequeNode prev;


        // Constructor
        public dequeNode(T i, dequeNode n, dequeNode p){
            this.item = i;
            this.next = n;
            this.prev = p;
        }
    }

    // Constructor
    public LinkedListDeque(){
        // default for empty deque
        sentinel = new dequeNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        sentinel.next = new dequeNode(item, sentinel.next, sentinel);
        size += 1;
    }


    @Override
    public void addLast(T item) {
        sentinel.next = new dequeNode(item, sentinel, sentinel.prev);
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (size > 0) {
            dequeNode first = sentinel.next;
            while (first.next != sentinel) {
                System.out.print(first.item+"");
            }
            System.out.println(first.item);
        } else {
            System.out.println("");
        }
    }

    @Override
    public T removeFirst() {
        size -= 1;
        if (size == 0) {
            return null;
        } else {
            dequeNode nextNext = sentinel.next.next;
            dequeNode next = sentinel.next;
            // Garbage Collection
            next.prev = null;
            next.next = null;
            // Delete the node
            sentinel.next = nextNext;
            return next.item;
        }
    }

    @Override
    public T removeLast() {
        size -= 1;
        if (size == 0) {
            return null;
        } else {
            dequeNode prevPrev = sentinel.prev.prev;
            dequeNode prev = sentinel.prev;
            // Garbage Collection
            prev.prev = null;
            prev.next = null;
            // Delete the node
            sentinel.prev = prevPrev;
            return prev.item;
        }
    }

    @Override
    public T get(int index) {
        if (size == 0 || index >= size) {
            return null;
        } else {
            dequeNode first = sentinel.next;
            while (index > 0) {
                index -- ;
                first = first.next;
            }
            return first.item;
        }
    }

    // Same as get, but uses recursion.
    public T getRecursive(int index){
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, dequeNode node){
        if( size == 0 || index >= size ){
            return null;
        } else if ( index == 0 ) {
            return node.item;
        }
        return getRecursiveHelper(index - 1, node.next);
    }
}
