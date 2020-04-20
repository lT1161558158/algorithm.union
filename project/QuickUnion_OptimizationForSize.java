import java.util.Arrays;

public class QuickUnion_OptimizationForSize extends QuickUnion implements Union {
    int[] sz;

    public QuickUnion_OptimizationForSize(int cap) {
        super(cap);
        sz = new int[cap];
        Arrays.fill(sz, 1);
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;

        //按元素的多少判断合并方向
        //元素少的集合合并到元素多的集合上
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
