import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

/**
 * Created by Jenny Huang on 3/12/19.
 */
public class TestMyTrieSet {

    // assumes add/contains work
    @Test
    public void sanityClearTest() {
        MyTrieSet t = new MyTrieSet();
        for (int i = 0; i < 455; i++) {
            t.add("hi" + i);
            //make sure put is working via contains
            assertTrue(t.contains("hi" + i));
        }
        t.clear();
        for (int i = 0; i < 455; i++) {
            assertFalse(t.contains("hi" + i));
        }
    }

    // assumes add works
    @Test
    public void sanityContainsTest() {
        MyTrieSet t = new MyTrieSet();
        assertFalse(t.contains("waterYouDoingHere"));
        t.add("waterYouDoingHere");
        assertTrue(t.contains("waterYouDoingHere"));
    }

    // assumes add works
    @Test
    public void sanityPrefixTest() {
        String[] saStrings = new String[]{"same", "sam", "sad", "sap"};
        String[] otherStrings = new String[]{"a", "awls", "hello"};

        MyTrieSet t = new MyTrieSet();
        for (String s: saStrings) {
            t.add(s);
        }
        for (String s: otherStrings) {
            t.add(s);
        }

        List<String> keys = t.keysWithPrefix("sa");
        for (String s: saStrings) {
            assertTrue(keys.contains(s));
        }
        for (String s: otherStrings) {
            assertFalse(keys.contains(s));
        }

        // Test for invalid prefix
        List<String> keysV = t.keysWithPrefix("tt");
        assertEquals(0, keysV.size());
    }

    @Test
    public void testCollectAllKeys() {
        String[] saStrings = new String[]{"same", "sam", "sad", "sap"};
        Set<String> saStringsSet = new HashSet<>();
        saStringsSet.addAll(Arrays.asList(saStrings));
        MyTrieSet t = new MyTrieSet();
        for (String s: saStrings) {
            t.add(s);
        }
        assertEquals(saStringsSet, t.collectAllKeysSet());
    }


    @Test
    public void testNumKeys() {
        String[] saStrings = new String[]{"same", "sam", "sad", "sap"};
        MyTrieSet t = new MyTrieSet();
        for (String s: saStrings) {
            t.add(s);
        }
        assertEquals(t.numOfKeys(), 4);
    }

    @Test
    public void testLongestPrefix() {
        String[] saStrings = new String[]{"same", "sam", "sad", "sap"};
        MyTrieSet t = new MyTrieSet();
        for (String s: saStrings) {
            t.add(s);
        }
        assertEquals("sam", t.longestPrefixOf("sample"));

        saStrings = new String[]{"samplet", "samp", "sad", "sap"};
        t = new MyTrieSet();
        for (String s: saStrings) {
            t.add(s);
        }
        assertEquals("sample", t.longestPrefixOf("sample"));
    }

    @Test
    public void testDelete() {
        String[] saStrings = new String[]{"same", "sam", "sad", "sap"};
        Set<String> set = new HashSet<>();
        MyTrieSet t = new MyTrieSet();
        for (String s: saStrings) {
            set.add(s);
            t.add(s);
        }
        assertEquals(set, t.collectAllKeysSet());

        t.delete("same");
        set.remove("same");
        assertEquals(set, t.collectAllKeysSet());

        t.delete("sam");
        set.remove("sam");
        assertEquals(set, t.collectAllKeysSet());

        t.delete("sad");
        set.remove("sad");
        assertEquals(set, t.collectAllKeysSet());

        t.delete("sap");
        set.remove("sap");
        assertEquals(set, t.collectAllKeysSet());

        assertEquals(t.numOfKeys(), 0);
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestMyTrieSet.class);
    }



}
