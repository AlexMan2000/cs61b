import java.util.Iterator;

public class ArraySet<T> {

    private T[] items;
    private int MAXCAPACITY = 100;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[MAXCAPACITY];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map. 
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("Cannot add null!");
        }
        if (!contains(x)) {
            items[size] = x;
            size++;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }


    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    // Nested CLass, cannot be static, since we need the generic type T
    // And if declare it to be static, then even if we don't need to invoke
    // the iterator() method, this nested class will still take up some
    // memory, and this is not what we want
    // We want it to be lazily loaded, so non-static is preferred.
    // Also we want to refer to the size variables of outer class, non-static
    // is thus definitely a better choice.
    private class ArraySetIterator implements Iterator<T> {

        int indexPos;

        // Since we have to new ... and set the indexPos = 0, so self-defined constructor is needed.
        public ArraySetIterator() {
            indexPos = 0;
        }


        @Override
        public boolean hasNext() {
            return indexPos < size;
        }

        @Override
        public T next() {
            T res = items[indexPos];
            indexPos++;
            return res;
        }
    }


    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i += 1) {
            returnSB.append(items[i].toString());
            returnSB.append(", ");
        }
        returnSB.append(items[size - 1]);
        returnSB.append("}");
        return returnSB.toString();
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
//        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
//        System.out.println(s.contains("horse"));
        System.out.println(s.size());  // 3

        Iterator<String> asser = s.iterator();
        while(asser.hasNext()) {
            System.out.println(asser.next());
        }

        System.out.println(s);
    }
}