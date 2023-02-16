import java.util.Arrays;

public class UnionFind {

    // TODO - Add instance variables?
    private int[] ds;


    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        ds = new int[n];
        for (int i = 0; i < n; i++) {
            ds[i] = -1;
        }
    }


    /**
     * Helper Method: Initialize the UnionFind with given data structure
     * @param array
     */
    public UnionFind(int[] array) {
        ds = array;
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (!(0 <= vertex) || !(vertex < ds.length)) {
            throw new IllegalArgumentException("No such vertex!");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        validate(v1);
        return -ds[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        return ds[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        if (v1 == v2) {
            return;
        }
        int root1 = find(v1);
        int root2 = find(v2);
        int treeSize1 = sizeOf(root1);
        int treeSize2 = sizeOf(root2);
        if (treeSize1 <= treeSize2) {
            ds[root1] = root2;
            ds[root2] -= treeSize1;
        } else {
            ds[root2] = root1;
            ds[root1] -= treeSize2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        int p = parent(vertex);
        if (p < 0) {
            return vertex;
        } else {
            int root = find(p);
            ds[vertex] = root;
            return root;
        }
    }


    /**
     * Helper function for size of underlying data structure
     * @return
     */

    public int lengthOfDS() {
        return ds.length;
    }

    public int[] getDS() {
        return ds;
    }

    /**
     * Helper function for comparing
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        UnionFind other = (UnionFind) o;
        if (this.lengthOfDS() != other.lengthOfDS()) {
            return false;
        }

        int[] thisDS = getDS();
        int[] otherDS = other.getDS();
        for (int i = 0; i < this.lengthOfDS(); i++) {
            if (thisDS[i] != otherDS[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(ds);
    }

    /**
     * Helper function for stream output
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb =new StringBuilder();
        int[] thisDS = getDS();
        for (int i = 0; i < this.lengthOfDS() - 1;i++) {
            sb.append(thisDS[i]);
            sb.append(" ");
        }
        sb.append(thisDS[this.lengthOfDS() - 1]);
        return sb.toString();
    }


}
