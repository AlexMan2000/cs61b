package hw3.hash;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */

        /* After getting your simpleOomages to spread out
           nicely, be sure to try
           scale = 0.5, N = 2000, M = 100. */

        double scale = 1.0;
        int N = 100;
        int M = 10;

        HashTableDrawingUtility.setScale(scale);
        List<Oomage> oomies = new ArrayList<>();
        for (int i = 0; i < N; i += 1) {
//            oomies.add(SimpleOomage.randomSimpleOomage());
            oomies.add(ComplexOomage.randomComplexOomage());
//            List<Integer> params = new ArrayList<>();
//            // Generate a random sequence of variable length
//            Random r = new Random();
//            int length = 8;
//            for (int t = 0; t < length; t++) {
//                int num = r.nextInt(255);
//                params.add(num);
//            }
//            params.add(0);
//            params.add(0);
//            ComplexOomage co = new ComplexOomage(params);
//            System.out.println(co.params);
//            System.out.println(co.hashCode());
//            oomies.add(co);
        }
        visualize(oomies, M, scale);
    }

    public static void visualize(List<Oomage> oomages, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);
        int[] numInBucket = new int[M];
        for (Oomage s : oomages) {
            int bucketNumber = (s.hashCode() & 0x7FFFFFFF) % M;
            double x = HashTableDrawingUtility.xCoord(numInBucket[bucketNumber]);
            numInBucket[bucketNumber] += 1;
            double y = HashTableDrawingUtility.yCoord(bucketNumber, M);
            s.draw(x, y, scale);
        }
    }
} 
