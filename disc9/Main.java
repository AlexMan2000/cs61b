/**
 * Created by AlexMan
 */
public class Main {

    public static boolean isBSTBad(TreeNode T){
        if (T == null) {
            return true;
        } else if (T.left != null && T.left.val > T.val) {
            return false;
        } else if (T.right != null && T.right.val < T.val) {
            return false;
        } else {
            return isBSTBad(T.left) && isBSTBad(T.right);
        }
    }


    public static boolean isBSTGood(TreeNode T){
        return isBSTHelper(T, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


    public static boolean isBSTHelper(TreeNode T, int minValue, int maxValue){
        if (T == null) {
            return true;
        }

        if (T.val <= minValue || T.val >= maxValue) {
            return false;
        }

        return isBSTHelper(T.left, minValue, T.val) && isBSTHelper(T.right,  T.val, maxValue);

    }

    public static void main(String[] args) {

    }
}
