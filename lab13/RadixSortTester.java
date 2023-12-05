import org.junit.Test;

public class RadixSortTester {

    public static void main(String[] args) {
        String[] str = new String[]{"add", "cab", "fad",
                "fee", "bad", "bee", "fed", "bed", "ace"};

        RadixSort rs = new RadixSort();

        rs.sort(str);

        for (String s : str) {
            System.out.println(s);
        }
    }

}
