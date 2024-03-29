import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexMan
 */
public class TestXelhaTree {
    public static class IntTree {
        public int item;
        public IntTree left, right;
    }

    public static IntTree createXelhaTree(List<Integer> x){
        return null;
    }

    private static int getItem(IntTree x) {
        if (x == null) { return Integer.MAX_VALUE; }
        return x.item;
    }

    public static boolean isAHeap(IntTree xt) {
        if (xt == null) { return true; }
        if (xt.item > getItem(xt.left)) { return false; }
        if (xt.item > getItem(xt.right)) { return false; }
        return isAHeap(xt.left) && isAHeap(xt.right);
    }

    public static void getTreeValues(IntTree xt, List<Integer> treeValues) {
        // Do an inorder traversal
        // Avoid null-pointer exception
        if (xt == null) {
            return;
        }
        getTreeValues(xt.left, treeValues);
        treeValues.add(xt.item);
        getTreeValues(xt.right, treeValues);
    }

    public static boolean validXelhaTree(IntTree xt, List<Integer> vals) {
        List<Integer> treeValues = new ArrayList<Integer>();
        /* getTreeValues adds all values in xt to treeValues */
        getTreeValues(xt, treeValues);
        // 同时满足两个性质就是validXelhaTree
        return isAHeap(xt) && vals.equals(treeValues) ;
    }
}
