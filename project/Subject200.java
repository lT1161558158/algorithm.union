import java.util.*;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject200 {
    class Point {
        int x;
        int y;
        Point parent;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            parent = this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    class Union {
        Map<Point, Integer> rank = new HashMap<>();
        Map<Point, Point> all = new HashMap<>();

        void union(Point p, Point q) {
            all.putIfAbsent(p, p);
            all.putIfAbsent(q, q);
            p = all.get(p);
            q = all.get(q);
            Point pRoot = find(p);
            Point qRoot = find(q);

            if (pRoot == qRoot)
                return;
            int pRank = rank.getOrDefault(pRoot, 1);
            int qRank = rank.getOrDefault(qRoot, 1);
            if (pRank < qRank) {
                pRoot.parent = qRoot;
            } else if (pRank > qRank) {
                qRoot.parent = pRoot;
            } else {
                qRoot.parent = pRoot;
                rank.put(pRoot, pRank + 1);
//                rank.remove(qRoot);
            }
        }

        boolean isConnect(Point p, Point q) {
            return find(p) == find(q);
        }

        Point find(Point p) {
            while (p != p.parent) {
//                p.parent = p.parent.parent;
                p = p.parent;
            }
            return p.parent;
        }
    }

    /**
     * 用并查集实际上做了一些额外的事情,最好的方法是使用dfs
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        Union union = new Union();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    Point point = new Point(i, j);
                    union.union(point, point);
                    if (i > 0 && grid[i - 1][j] == '1') {
                        union.union(point, new Point(i - 1, j));
                    }
                    if (j > 0 && grid[i][j - 1] == '1') {
                        union.union(point, new Point(i, j - 1));
                    }
                }
            }
        }
        return (int) union.all.values().stream().map(union::find).distinct().count();
    }

    /**
     * 用dfs实现
     *
     * @param grid
     * @return
     */
//    public int numIslands(char[][] grid) {
//        int c = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                if (grid[i][j] == '1') {
//                    ++c;
//                    dfs(grid,i,j);
//                }
//            }
//        }
//        return c;
//    }

    void dfs(char[][] grid, int x, int y) {
        if (grid[x][y] != '1')
            return;
        grid[x][y] = 'a';
        if (x > 0)
            dfs(grid, x - 1, y);
        if (x < grid.length - 1)
            dfs(grid, x + 1, y);
        if (y > 0)
            dfs(grid, x, y - 1);
        if (y < grid[0].length - 1)
            dfs(grid, x, y + 1);
    }
    public static void main(String[] args) {
        Subject200 subject200 = new Subject200();
        String input = "11000\n" +
                "11000\n" +
                "00100\n" +
                "00011";
        String[] lines = input.split("\n");
        int y = lines.length;
        int x = lines[0].length();
        char[][] chars = new char[x + 1][y + 1];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                chars[j][i] = lines[j].charAt(i);
            }
        }
        System.out.println(subject200.numIslands(chars));
    }
}
