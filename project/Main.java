import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 测试函数
 */
public class Main {
    private static double testUnion(Union union, int m) {
        int caption = union.caption();
        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(caption);
            int b = random.nextInt(caption);
            union.union(a, b);
        }
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(caption);
            int b = random.nextInt(caption);
            union.isConnect(a, b);
        }
        long end = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end - start);
    }

    public static void main(String[] args) {
        int cap = (int) 1e5;
        int m = (int) 1e7;
        List<Union> unionList = new ArrayList<>();
//        unionList.add(new QuickFindUnion(cap));
//        unionList.add(new QuickUnion(cap));//太慢了
        unionList.add(new QuickUnion_OptimizationForSize(cap));
        unionList.add(new QuickUnion_OptimizationForRank(cap));
        unionList.add(new QuickUnion_OptimizationForPathCompression(cap));
        unionList.add(new QuickUnion_OptimizationForPathCompression2(cap));


        unionList.forEach(union -> {
            double v = testUnion(union, m);
            System.out.println(union.getClass().getSimpleName() + " use " + v + " ms");
        });
    }
}
