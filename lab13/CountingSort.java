import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 *
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        System.out.println(max);
        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted;
    }

    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        // TODO make counting sort work with arrays containing negative numbers.
        ArrayList<Integer> negatives = new ArrayList<>();
        ArrayList<Integer> nonnegatives = new ArrayList<>();

        for (int i: arr) {
            if (i < 0) {
                negatives.add(i);
            } else {
                nonnegatives.add(i);
            }
        }
        int[] nonnegativesArray = new int[nonnegatives.size()];

        for(int i = 0; i< nonnegatives.size(); i++) {
            nonnegativesArray[i] = nonnegatives.get(i);
        }
        // Call counting sort for positive/negative numbers
        int[] sortedPositive = naiveCountingSort(nonnegativesArray);


        int[] sortedNegativeInverted = null;
        if (negatives.size() != 0) {
            int[] negativesArray = new int[negatives.size()];
            // Invert the negative numbers
            for(int i = 0; i< negatives.size(); i++) {
                negativesArray[i] = - negatives.get(i);
            }
            int[] sortedNegative = naiveCountingSort(negativesArray);
            sortedNegativeInverted = new int[sortedNegative.length];
            // Invert the negative numbers back
            for(int i = 0; i< sortedNegative.length; i++) {
                sortedNegativeInverted[i] = - sortedNegative[sortedNegative.length - 1 - i];
            }
        } else {
            sortedNegativeInverted = new int[0];
        }

        // Concatenate
        int[] sorted = new int[sortedPositive.length + sortedNegativeInverted.length];
        int k = 0;
        for (int i = 0; i < sortedNegativeInverted.length; i++, k++) {
            sorted[k] = sortedNegativeInverted[i];
        }

        for (int i = 0; i < sortedPositive.length; i++, k++) {
            sorted[k] = sortedPositive[i];
        }



        return sorted;
    }
}
