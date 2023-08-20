import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {

    static final int RADIX = 256;

    static int MAX_LENGTH;

    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
//        LSDSort(asciis);
        MSDSort(asciis);
        return asciis;
    }


    private static void LSDSort(String[] asciis) {
        // Padding
        int maxLength = Integer.MIN_VALUE;
        for (String s: asciis) {
            maxLength = maxLength > s.length() ? maxLength: s.length();
        }

        // Right append 0 to the string
        String[] asciiCopy = asciis.clone();
        for (int i = 0; i < asciiCopy.length; i++) {
            StringBuilder sb = new StringBuilder(asciiCopy[i]);
            for (int j = 0; j < maxLength - asciiCopy[i].length(); j++) {
                sb.append("\0");
            }
            asciiCopy[i] = sb.toString();
        }

        // Start Sorting from right to left
        for (int d = maxLength - 1; d >= 0; d--) {
            sortHelperLSD(asciiCopy, d);
        }

        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = asciiCopy[i];
        }
    }


    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        String[] sorted = new String[asciis.length]; //  Sorted result of ascii based on asciis[i][index]

        // Frequency map
        int[] freqMap = new int[RADIX]; // Dictionary for frequency

        for (String ascii: asciis) {
            freqMap[ascii.charAt(index)]++;
        }

        // Position map
        int[] startingPosition = new int[RADIX]; // Dictionary for position

        // Calculating positions
        for (int i = 0; i < freqMap.length; i++) {
            int pos = 0;
            for (int j = 0; j < i; j++) {
                pos += freqMap[j];
            }
            startingPosition[i] = pos;
        }

        // Sorting according to the position map
        for (String ascii: asciis) {
            sorted[startingPosition[ascii.charAt(index)]] = ascii;
            startingPosition[ascii.charAt(index)]++;
        }

        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = sorted[i];
        }
    }



    private static void MSDSort(String[] asciis) {
        // Padding
        int maxLength = Integer.MIN_VALUE;
        for (String s: asciis) {
            maxLength = maxLength > s.length() ? maxLength: s.length();
        }

        MAX_LENGTH = maxLength;

        // Right append 0 to the string
        String[] asciiCopy = asciis.clone();
        for (int i = 0; i < asciiCopy.length; i++) {
            StringBuilder sb = new StringBuilder(asciiCopy[i]);
            for (int j = 0; j < maxLength - asciiCopy[i].length(); j++) {
                sb.append("\0");
            }
            asciiCopy[i] = sb.toString();
        }

        sortHelperMSD(asciiCopy, 0, asciis.length, 0);

        for (int i = 0; i < asciiCopy.length; i++) {
            asciis[i] = asciiCopy[i];
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        if (index == MAX_LENGTH - 1 || start == end) {
            return;
        }

        // Create a freqMap in [start, end)
        int[] freqMap = new int[RADIX];

        for (int i = start; i < end; i++) {
            freqMap[asciis[i].charAt(index)]++;
        }

        // Create a positionMap in [start, end)
        int[] positionMap = new int[RADIX];

        for (int i = 0; i < freqMap.length; i++) {
            int newPos = start;
            for (int j = 0; j < i; j++) {
               newPos += freqMap[j];
            }
            positionMap[i] = newPos;
        }

        int[] positionMapCopy = Arrays.copyOfRange(positionMap, 0, positionMap.length);

        String[] sorted = Arrays.copyOfRange(asciis, 0, asciis.length);

        for (int i = start; i < end; i++) {
            sorted[positionMap[asciis[i].charAt(index)]] = asciis[i];
            positionMap[asciis[i].charAt(index)]++;
        }

        // Modify the original list,changing the reference
        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = sorted[i];
        }

        // Break into subproblems
        int startPos = start;
        int endPos;
        int pos = start;
        for (int i = 0; i < positionMapCopy.length; i++) {
            if (positionMapCopy[i] > 0 && positionMapCopy[i] != pos) {
                endPos = positionMapCopy[i];
                sortHelperMSD(asciis, startPos, endPos, index + 1);
                startPos = endPos;
                pos = endPos;
            }
        }
    }
}
