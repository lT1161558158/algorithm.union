/**
 * find的复杂度为O(1)
 */
public class QuickFindUnion implements Union {

    //每个元素所在的集合id
    int[] ids;

    public QuickFindUnion(int cap) {
        this.ids = new int[cap];
        //每个元素都属于不同的集合
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }
    }

    /**
     * 将p和q合并到同一个集合中
     * 需要操作p所在的集合中所有的元素的所属集合,并且设置为p集合id
     * 复杂度为 O(n)
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId)
            return;
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == pId)
                ids[i] = qId;
        }
    }

    @Override
    public boolean isConnect(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int find(int p) {
        return ids[p];
    }

    @Override
    public int caption() {
        return ids.length;
    }
}
