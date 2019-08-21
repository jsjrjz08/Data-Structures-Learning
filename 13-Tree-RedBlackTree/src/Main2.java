import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main2 {
    public static void main(String[] args) {
        int opCnt = 20000000;
        ArrayList<Integer> testData = new ArrayList<>();
        Random random = new Random();
        for(int i=0;i<opCnt;i++) {
            testData.add(random.nextInt(Integer.MAX_VALUE));
        }

        testTree(new BSTMap<>(),testData,false);
        testTree(new AVLTree<>(),testData,false);
        testTree(new RBTree<>(),testData,false);

        testTree(new AVLTree<>(),testData,true);
        testTree(new RBTree<>(),testData,true);
    }

    private static void testTree(Map<Integer,Object> map,ArrayList<Integer> testData,boolean isOrdered) {
        if(isOrdered) {
            Collections.sort(testData);
            System.out.println("---------"+map.getClass().getName()+"---排序-----");
        } else {
            System.out.println("---------"+map.getClass().getName()+"---顺序随机-----");
        }

        long startTime = System.nanoTime();
        for(Integer x:testData) {
            map.add(x,null);
        }

        long endTime = System.nanoTime();
        System.out.println("total time = "+(endTime-startTime)/1000000000.0+"s.");
    }
}
//
//        ---------BSTMap---顺序随机-----
//        total time = 98.909416434s.
//        ---------AVLTree---顺序随机-----
//        total time = 97.006580813s.
//        ---------RBTree---顺序随机-----
//        total time = 85.099810058s.
//        ---------AVLTree---排序-----
//        total time = 17.802404537s.
//        ---------RBTree---排序-----
//        total time = 27.452358435s.
//
