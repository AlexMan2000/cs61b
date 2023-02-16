import org.junit.Test;

import java.util.HashSet;

/**
 * Created by AlexMan
 */
public class UnionIntersect {

    @Test
    public void testUnion() {
        int[] a1 = {5,3,2,7,8};
        int[] a2 = {3,5};
    }

    @Test
    public void testIntersect() {
        int[] a1 = {3,2,5,7,8};
        int[] a2 = {3,5,9,10};
    }


    public int[] union(int[] A, int[] B) {
        HashSet<Integer> set = new HashSet<>();
        for (int num: A) {
            set.add(num);
        }

        for (int num: B) {
            set.add(num);
        }

        int[] res = new int[set.size()];
        int i = 0;
        for (int num: set) {
            res[i] = num;
            i++;
        }
        return res;
    }


    public int[] intersect(int[] A, int[] B) {
        HashSet<Integer> unionSet = new HashSet<>();
        HashSet<Integer> intersectionSet = new HashSet<>();
        for (int num: A) {
            unionSet.add(num);
        }

        for (int num: B) {
            if (unionSet.contains(num)) {
                intersectionSet.add(num);
            }
        }

        int[] res = new int[intersectionSet.size()];
        int i = 0;
        for (int num: intersectionSet) {
            res[i] = num;
            i++;
        }
        return res;
    }

}
