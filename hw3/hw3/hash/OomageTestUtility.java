package hw3.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        int bucketNum = -1;
        Map<Integer, Integer> mmap = new HashMap<>();
        for (int i = 0; i < M; i++) {
            mmap.put(i, 0);
        }
        for (Oomage o: oomages) {
            //  & 0x7FFFFFFF 就是取负号，防止negative hashCode的出现而导致 % 因为
            // 默认不采用floorMod, 而导致数组越界。
            bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            mmap.put(bucketNum, mmap.get(bucketNum) + 1);
        }
        for (Integer key: mmap.keySet()) {
            Integer num = mmap.get(key);
            if (num < N / 50 || num > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
