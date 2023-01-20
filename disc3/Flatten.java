/**
 * Created by AlexMan
 */
public class Flatten {


    public static int[] flatten(int[][] x) {
        int totalLength = 0;

        for (int[] subarray: x) {
            totalLength += subarray.length;
        }
        int[] a = new int[totalLength];
        int aIndex = 0;
        for (int i=0; i < x.length; i ++) {
            int[] currArr = x[i];
            for(int j =0; j < currArr.length; j++){
                a[aIndex] = currArr[j];
                aIndex ++;
            }
        }
        return a;
    }
}
