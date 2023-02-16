import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by AlexMan
 */
public class TestUnionFind {


    @Test
    public void testUnionFind() {
        int[] expected = new int[] {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        UnionFind actual = new UnionFind(15);
        assertArrayEquals(expected, actual.getDS());
    }



    @Test
    public void testParent() {
        UnionFind actual = new UnionFind(3);
        int[] a = actual.getDS();
        a[0] = 2;
        a[1] = 2;
        a[2] = -2;
        assertEquals(2, actual.parent(0));
        assertEquals(2, actual.parent(1));
        assertEquals(-2, actual.parent(2));
    }


    @Test
    public void testFind() {
        int[] array1 = new int[] {1, 2, -2, 4, -1};
        // Test
        UnionFind actual = new UnionFind(array1);
        assertEquals(4, actual.find(3));

        // Test Path Compression, we find 0 which means 0 and 1 are directly connected to the root 2.
        assertEquals(2, actual.find(0));
        assertEquals(2, actual.parent(0));
    }

    @Test
    public void testSizeOf() {
        // The size of 3 should be 2
        UnionFind actual1 = new UnionFind(15);
        actual1.union(2,3);
        assertEquals(2,actual1.sizeOf(3));


        // Should all be 1
        UnionFind actual2 = new UnionFind(15);
        int[] ds = actual2.getDS();
        for (int i = 0; i < ds.length; i++) {
            assertEquals(1, actual2.sizeOf(i));
        }

        UnionFind actual3 = new UnionFind(15);
        actual3.union(2,3);
        assertEquals(2,actual3.sizeOf(2));
        assertEquals(2,actual3.sizeOf(3));
        actual3.union(2,4);
        assertEquals(3,actual3.sizeOf(2));
        assertEquals(3,actual3.sizeOf(3));
        assertEquals(3,actual3.sizeOf(4));
        actual3.union(5,6);
        assertEquals(2,actual3.sizeOf(5));
        assertEquals(2,actual3.sizeOf(6));
        actual3.union(2,5);
        assertEquals(5,actual3.sizeOf(2));
        assertEquals(5,actual3.sizeOf(5));
    }

    @Test
    public void testUnion() {
        UnionFind actual3 = new UnionFind(10);
        actual3.union(2,3);
        assertEquals(3,actual3.parent(2));
        actual3.union(2,4);
        assertEquals(3,actual3.parent(2));
        assertEquals(3,actual3.parent(4));
        actual3.union(5,6);
        assertEquals(6,actual3.parent(5));
        actual3.union(2,5);
        assertEquals(3, actual3.parent(2));
        assertEquals(3, actual3.parent(4));
        assertEquals(3, actual3.parent(6));
    }

    @Test
    public void testConnected() {
        UnionFind actual3 = new UnionFind(10);
        actual3.union(2,3);
        actual3.union(2,4);
        actual3.union(5,6);
        actual3.union(2,5);
        assertTrue(actual3.connected(2, 3));
        assertTrue(actual3.connected(2, 4));
        assertTrue(actual3.connected(5, 6));
        assertTrue(actual3.connected(2, 5));
        assertTrue(actual3.connected(3, 5));
    }





    public static void main(String[] args) {

    }
}
