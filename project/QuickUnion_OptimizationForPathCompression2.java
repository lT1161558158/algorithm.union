
public class QuickUnion_OptimizationForPathCompression2 extends QuickUnion_OptimizationForRank implements Union {

    public QuickUnion_OptimizationForPathCompression2(int cap) {
        super(cap);
    }

    /**
     * 使用递归进行路径压缩,保证生成为最优路径,即最高两层的树
     *
     * @param p
     * @return
     */
    @Override
    public int find(int p) {
        if (p != parent[p])
            parent[p] = find(parent[p]);
        return parent[p];
    }
}
