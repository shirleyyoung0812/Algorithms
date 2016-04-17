package week1;

/**
 * find: O(lgN), depth of p and q
 * union O(1)
 * Created by shirleyyoung on 3/26/16.
 */
public class QuickUnionUF {
    private int[] id;
    // track # of elements rooted by current element
    private int[] sz;

    public QuickUnionUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    /**
     * Chase parent pointers until reach root
     * @param i: the position of element in the array
     * @return: root of i
     */
    private int root(int i) {
        while (i != id[i]) {
            //path compression: make every other node in path point to its
            //grandparent.
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    /**
     * change root of p to point to root of q
     * depth of p and q array accesses
     * @param p root of which to be changed.
     * @param q root of which to change the other element.
     */
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }

    }
}
