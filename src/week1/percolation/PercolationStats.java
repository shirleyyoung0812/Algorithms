package week1.percolation;

import java.util.Random;
import java.util.stream.DoubleStream;

/**
 * Created by shirleyyoung on 4/17/16.
 */
public class PercolationStats {

    private double[] fractions;
    private final int dim;
    private final int experimentTimes;

    /**
     * perform T independent experiments on an N-by-N grid
     * @param N
     * @param T
     */
    public PercolationStats(int N, int T) {
        dim = N;
        experimentTimes = T;
        fractions = new double[experimentTimes];
        for (int i = 0; i < experimentTimes; i++) {
            fractions[i] = fraction();
        }
    }

    /**
     * sample mean of percolation threshold.
     * @return mean value (double).
     */
    public double mean() {
       return DoubleStream.of(fractions).average().getAsDouble();
    }

    public double stddev() {
        double sum = 0;
        double mean = mean();
        for (double fraction : fractions) {
            sum += Math.pow((fraction - mean), 2.0);
        }
        return Math.sqrt(sum / (experimentTimes - 1));
    }

    /**
     * low  endpoint of 95% confidence interval.
     * @return low endpoint
     */
    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(experimentTimes);
    }

    /**
     * high endpoint of 95% confidence interval.
     * @return high endpoint.
     */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(experimentTimes);
    }

    /**
     * Calculate the fraction of open sites when the system percolates
     * by performing Monte Carlo simulations
     * @return
     */
    private double fraction() {
        double openSites = 0.0;
        Percolation system = new Percolation(dim);
        Random rand = new Random();
        while(!system.percolates()){
            int i = rand.nextInt(dim) + 1;
            int j = rand.nextInt(dim) + 1;
            if (!system.isOpen(i, j)) {
                system.open(i, j);
                openSites++;
            }
        }
        return openSites / (dim * dim);
    }
}
