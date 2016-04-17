package week1.percolation;

/**
 * Created by shirleyyoung on 4/17/16.
 */
public class PercolationMain {

    public static void main(String args[]) {
        PercolationStats stats = new PercolationStats(100, 100);
        System.out.println(stats.mean());
        System.out.println(stats.stddev());
        System.out.println(stats.confidenceLo());
        System.out.println(stats.confidenceHi());
    }
}
