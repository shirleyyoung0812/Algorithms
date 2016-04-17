package week1.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


/**
 * Created by shirleyyoung on 3/26/16.
 */
public class Percolation {
    private WeightedQuickUnionUF sites;
    private boolean[] openCheck;
    private final int dim;

    /**
     * constructs a Percolation object with dimension N.
     * @param N: dimension.
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        dim = N;
        sites = new WeightedQuickUnionUF(dim * dim + 2);
        openCheck = new boolean[dim * dim + 2];
        openCheck[0] = true;
        openCheck[dim * dim + 1] = true;
        for(int i = 1; i <= dim; i++) {
            sites.union(0, i);
            sites.union(dim * dim + 1, i + dim * (dim - 1));
        }
    }

    /**
     * open site (row i, column j) if it is not open already.
     * @param i: row.
     * @param j: column.
     */
    public void open(int i, int j) {
        checkValidity(i, j);
        int index = getIndex(i, j);
        openCheck[index] = true;
        if ((i - 1) > 0 && isOpen(i - 1, j)){
            sites.union(index, index - dim);
        }
        if ((i + 1) <= dim && isOpen(i + 1, j)){
            sites.union(index, index + dim);
        }
        if ((j - 1) > 0 && isOpen(i, j - 1)){
            sites.union(index, index - 1);
        }
        if ((j + 1) <= dim && isOpen(i, j + 1)) {
            sites.union(index, index + 1);
        }
    }

    /**
     * is site (row i, column j) open?
     * @param i: row.
     * @param j: column.
     * @return true if is open.
     */
    public boolean isOpen(int i, int j) {
        checkValidity(i, j);
        int index = getIndex(i, j);
        return openCheck[index];
    }

    /**
     * if the site is connected to a site at the top row.
     * @param i: row
     * @param j: column
     * @return true if the site is a full site.
     */
    public boolean isFull(int i, int j) {
        checkValidity(i, j);
        int index = getIndex(i, j);
        return sites.connected(0, index);
    }

    /**
     * the system percolates if there is a full site in the bottom row.
     * @return if the system percolates. 
     */
    public boolean percolates() {
        return sites.connected(0, dim * dim + 1);
    }

    private int getIndex(int i, int j) {
        return (i - 1) * dim + j;
    }
    private void checkValidity(int i, int j) {
        if (i <= 0 || i > dim || j <= 0 || j > dim) {
            throw new IndexOutOfBoundsException();
        }
    }
}
