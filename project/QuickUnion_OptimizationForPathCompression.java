
public class QuickUnion_OptimizationForPathCompression extends QuickUnion_OptimizationForRank implements Union {

    public QuickUnion_OptimizationForPathCompression(int cap) {
        super(cap);
    }

    @Override
    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
}
