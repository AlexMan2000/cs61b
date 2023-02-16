package hw2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

public class PercolationStats {
    List<Double> container;
    int size;
    int T;
    Percolation p;

    // perform T independent experiments on an N-bfy-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("The argument is illegal!");
        }

        size = N;
        this.T = T;
        // Initialize all sites to be blocked
        p = pf.make(N);

        container = simulate();
    }

    // sample mean of percolation threshold
    public double mean() {
        double sum = 0.0;
        for (double num: container) {
            sum += num;
        }
        return sum / T;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double sum = 0.0;
        double mu = mean();
        for (double num: container) {
            sum += (num - mu) * (num - mu);
        }
        return sum / (T - 1);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double mu = mean();
        double std = stddev();
        return mu - (1.96 * std) / (Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mu = mean();
        double std = stddev();
        return mu + (1.96 * std) / (Math.sqrt(T));
    }


    public List<Double> simulate() {
        List<Double> al = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            al.add(simulateOnce());
        }
        return al;
    }

    public double simulateOnce() {
        while (!p.percolates()) {
            int rrow = StdRandom.uniform(size);
            int rcol = StdRandom.uniform(size);
            p.open(rrow, rcol);
        }
        return p.numberOfOpenSites();
    }

    public static void main(String[] args) {
        PercolationFactory p = new PercolationFactory();
        int[] nList = new int[] {50};
        int[] tList = new int[] {100, 200, 400, 800, 1600, 3200};
        StringBuilder sb;
        for (int n: nList) {
            for (int t: tList) {
                sb = new StringBuilder();
                PercolationStats ps = new PercolationStats(n, t, p);
                sb.append("N: " + n + " ");
                sb.append("T: " + t + " ");
                sb.append("Mean " + ps.mean() + " ");
                sb.append("Stddev: " + ps.stddev() + " ");
                sb.append("Confidence Low " + ps.confidenceLow() + " ");
                sb.append("Confidence High" + ps.confidenceHigh() + " ");
                System.out.println(sb.toString());
            }
        }
    }
}
