package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;
    private static int MAX_CAPACITY = DEFAULT_SIZE;

    private ArrayMap<K, V>[] buckets;
    private int size;


    private int loadFactor() {
        return size / buckets.length;
    }

    // Add for testing
    public int maxCapacity() {
        return MAX_CAPACITY;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("No such key!");
        }

        return buckets[hash(key)].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("No such key!");
        }
        ArrayMap<K, V> bucket = buckets[hash(key)];
        int oSize = bucket.size();
        bucket.put(key, value);
        if (bucket.size() > oSize) {
            size++;
        }
        resize();
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Resize the bucket size and rehash
     */
    private void resize() {
        if (size >= MAX_LF * MAX_CAPACITY) {
            int newSize = MAX_CAPACITY * 2;
            ArrayMap<K, V>[] newBuckets = new ArrayMap[newSize];
            MAX_CAPACITY = newSize;

            // Initialiing
            for (int i = 0; i < newBuckets.length; i++) {
                newBuckets[i] = new ArrayMap<>();
            }

            // Copying
            for (int i = 0; i < buckets.length; i++) {
                ArrayMap<K, V> bucket = buckets[i];
                for (K key: bucket.keySet()) {
                    // Hashing
                    int bIndex = Math.floorMod(key.hashCode(), newSize);
                    newBuckets[bIndex].put(key, bucket.get(key));
                }
            }
            buckets = newBuckets;
        }
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> res = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            ArrayMap<K, V> bucket = buckets[i];
            res.addAll(bucket.keySet());
        }
        return res;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The argument is illegal");
        }
        int hashCode = hash(key);
        ArrayMap<K, V> bucket = buckets[hashCode];
        int oSize = bucket.size();
        V res = bucket.remove(key);
        if (bucket.size() < oSize) {
            size--;
        }
        return res;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("The argument is illegal");
        }
        int hashCode = hash(key);
        ArrayMap<K, V> bucket = buckets[hashCode];
        int oSize = bucket.size();
        // Only when the value is specified.
        if (bucket.get(key).equals(value)) {
            V res = bucket.remove(key);
            if (bucket.size() < oSize) {
                size--;
            }
            return res;
        }
        return null;
    }

    private class HashMapIterator implements Iterator<K> {

        int bucketIndex;
        Iterator<K> currentBucket;

        HashMapIterator() {
            bucketIndex = 0;
            currentBucket = buckets[bucketIndex].iterator();
        }

        private boolean bound() {
            return bucketIndex >= buckets.length - 1;
        }

        @Override
        public boolean hasNext() {
            if (currentBucket.hasNext()) {
                return true;
            }

            while (!currentBucket.hasNext()) {
                if (bound()) {
                    return false;
                }
                currentBucket = buckets[bucketIndex++].iterator();
            }
            return true;
        }

        @Override
        public K next() {
            if (hasNext()) {
                return currentBucket.next();
            }
            throw new RuntimeException("Stop Iteration!");
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }
}
