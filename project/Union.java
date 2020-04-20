/**
 * 并查集接口
 * 操作元素为 Point
 */
public interface Union{
    /**
     * 连接
     * @param p
     * @param q
     */
    void union(int p, int q);

    /**
     *
     * @param p
     * @param q
     * @return true if p connect q
     */
    boolean isConnect(int p,int q);
    /**
     * 找到p对应的集合id
     */
    int find(int p);
    int caption();
}