package Map61B;

import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * An array based implementation of the Map61B class.
 */
public class ArrayMap<K, V> implements Map61B<K, V> {
    private K[] keys;
    private V[] values;
    private int size;
    private int MAXCAPACITY = 100;

    public ArrayMap() {
        keys = (K[]) new Object[MAXCAPACITY];
        values = (V[]) new Object[MAXCAPACITY];
        size = 0;
    }

    /** Returns the index of the given key if it exists,
     *  -1 otherwise. */
    private int keyIndex(K key) {
        // the upper bound may better be "size", because we don't want to
        // traverse all the empty keys
        for(int i = 0; i < size; i++){
            // might not use ==, instead we should use .equals()
            // equals is similar to assertEquals
            // If two objects point to the same address, equals returns true
            // If not, then equals invoke the "equals()" method on the
            // object, and let user define how equal objects should be
            // like, more on this later.
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Judge if we have a specified key
     * @param key
     * @return
     */
    public boolean containsKey(K key) {
        int index = keyIndex(key);
        return index > -1;
    }


    // Without resizing
    public void put(K key, V value) {
        int index = keyIndex(key);
        if (index == -1) {
            // If DNE
            keys[size] = key;
            values[size] = value;
            size++; // Don't forget.
        } else {
            // If exists
            values[index] = value;
        }
    }


    /**
     * Get the value by a key
     * @param key
     * @return
     */
    public V get(K key) {
        int index = keyIndex(key);
        if (index > -1) {
            return values[index];
        }
        return null;
    }

    public int size() {
        return size;
    }


    /**
     * Get all keys, and return a List
     * @return
     */
    public List<K> keys() {
        List<K> keyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            keyList.add(keys[i]);
        }
        return keyList;
    }

    @Test
    public void test() {
        ArrayMap<Integer, Integer> am = new ArrayMap<Integer, Integer>();
        am.put(2, 5);
        int expected = 5;
        assertEquals((Integer) expected, am.get(2));
    }

    public static void main(String[] args) {
        ArrayMap<String, Integer> m = new ArrayMap<String, Integer>();
        m.put("horse", 3);
        m.put("fish", 9);
        m.put("house", 10);
    }
}
