/**
 * Created by AlexMan
 */
public class SelectionSort {
    /** Sorts strings destructively. */
    public static void sort(String[] x) {
        // find the smallest item
        // move it to the front
        // selection sort the rest (using recursion?)
        sort(x, 0);
    }

    /**
     * Return the smallest string (alphabetically) starting from index <start>
     * @param x
     * @return
     */
    public static int findSmallest(String[] x, int start){
        int smallestIndex = start;
        for(int i=0;i < x.length; i++){
            // String Comparison
            if(x[i].compareTo(x[smallestIndex]) < 0){
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }


    /**
     * Swap the value of x at index1 and index2
     * @param x
     * @param index1
     * @param index2
     */
    public static void swap(String[] x, int index1, int index2){
        String temp = x[index1];
        x[index1] = x[index2];
        x[index2] = temp;
    }


    /**
     * Helper Method for sort(String[] x), overloaded.
     * @param x  array
     * @param start starting index
     */
    public static void sort(String[] x, int start){
        if(start == x.length -1){
            return;
        }
        int smallestIndex = findSmallest(x, start);
        swap(x, start, smallestIndex);
        sort(x, start+1);
    }

}
