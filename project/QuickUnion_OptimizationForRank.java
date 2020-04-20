import java.util.Arrays;

public class QuickUnion_OptimizationForRank extends QuickUnion implements Union {
    //表示以i为根的集合所表示的树的层数
    int[] rank;

    public QuickUnion_OptimizationForRank(int cap) {
        super(cap);
        rank = new int[cap];
        Arrays.fill(rank, 1);//初始时层数都是1
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        //根据rank判断合并方向
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
            //不需要维护rank,rank不同时,rank分数总是选择那个大的
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            //上述操作后qRoot对应的树就不存在了,因为q被合并到了p中
            rank[pRoot] += 1;
        }
    }
}
