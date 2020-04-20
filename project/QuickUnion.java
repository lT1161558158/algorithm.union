public class QuickUnion implements Union {
    //每个元素的parent节点
    int[] parent;

    public QuickUnion(int cap) {
        this.parent = new int[cap];
        //初始化,每个节点指向自身
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (qRoot == pRoot)
            return;
        parent[qRoot] = pRoot;
    }

    @Override
    public boolean isConnect(int p, int q) {
        return find(p) == find(q);
    }

    //找到所在树的 root 节点
    @Override
    public int find(int p) {
        while (parent[p] != p)
            p = parent[p];
        return p;
    }

    @Override
    public int caption() {
        return parent.length;
    }
}
