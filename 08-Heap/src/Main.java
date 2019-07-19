import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int opCount = 3000000;
        Random rand = new Random();
        Integer[] arr = new Integer[opCount];
        for(int i=0;i<opCount;i++) {
            arr[i] = rand.nextInt(Integer.MAX_VALUE);
//            arr[i] = rand.nextInt(10);
        }

        System.out.println("heapify方式创建堆");
        testHeap(arr,true);
        System.out.println("add方式创建堆");
        testHeap(arr,false);

    }

    public static void testHeap(Integer[] arr, boolean isHeapify) {
        long startTime = System.nanoTime();
        int len = arr.length;
        MaxHeap<Integer> heap;
        if(isHeapify) {
            heap = new MaxHeap<>(arr);
        } else {
            heap = new MaxHeap<>(len);
            for(int i=0;i<len;i++) {
                heap.add(arr[i]);
            }
        }
//        System.out.println(heap);
        long midTime = System.nanoTime();

        ArrayList<Integer> res = new ArrayList<>(len);
        for(int i=0; i<len; i++) {
            res.add(heap.extractMax());
        }
        long endTime = System.nanoTime();


//        StringBuilder sb = new StringBuilder();
//        sb.append("res[0]="+res.get(0)+",");
        //检查是否倒序排列
        for(int i=1;i<len;i++) {
//            sb.append("res["+i+"]="+res.get(i)+",");
            if(res.get(i-1) < res.get(i)) {
                throw new IllegalArgumentException("order error exception");
            }
        }
//        System.out.println(sb.toString());

        System.out.println(String.format("创建 maxHeap 花费 %f s,逐个取出最大元素花费 %f s."
                ,(midTime-startTime)/1000000000.0,(endTime-midTime)/1000000000.0));
    }
}
