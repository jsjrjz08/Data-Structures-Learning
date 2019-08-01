import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int size  = 10000000;
        int opCnt = 10000000;
//        testUF(new UnionFind1(size),opCnt);
//        testUF(new UnionFind2(size),opCnt);
//        testUF(new UnionFind3(size),opCnt);
//        testUF(new UnionFind4(size),opCnt);
        testUF(new UnionFind5(size),opCnt);
        testUF(new UnionFind6(size),opCnt);
    }

    /**
     *
     * @param uf UnionFind对象
     * @param opCnt 操作次数
     */
    public static void testUF(UF uf,int opCnt) {
        System.out.println("--------"+uf.getClass().getName()+"--------");
        int size = uf.getSize();//元素个数
        Random random = new Random();
        long startTime = System.nanoTime();

        for(int i=0;i<opCnt;i++) {
            uf.unionElements(random.nextInt(size),random.nextInt(size));
        }
        long midTime = System.nanoTime();

        for(int i=0;i<opCnt;i++) {
            uf.isConnected(random.nextInt(size),random.nextInt(size));
        }
        long endTime = System.nanoTime();

        System.out.println("total costs "+(endTime-startTime)/1000000000.0+"s " +
                ": union costs "+(midTime-startTime)/1000000000.0+"s,find costs "+(endTime-midTime)/1000000000.0+"s.");
        System.out.println();
    }
}
