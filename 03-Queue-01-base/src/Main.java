import java.util.Random;

public class Main {
    public static void main(String[] args) {
        /*
        System.out.println("------------------------ 数组队列 begin ------------------------");
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        System.out.println(queue);
        System.out.println(queue.isEmpty());
        System.out.println(queue.getSize());
        System.out.println(queue.getCapacity());

        for(int i=0; i<3; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
        System.out.println(queue.isEmpty());
        System.out.println(queue.getSize());
        System.out.println(queue.getCapacity());

        System.out.println("getFront="+queue.getFront());
        System.out.println(queue);

        System.out.println(queue.dequeue());
        System.out.println(queue);

        System.out.println(queue.dequeue());
        System.out.println(queue);

        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println("------------------------ 数组队列 end ------------------------");

        System.out.println();

        System.out.println("------------------------ 循环队列 begin ------------------------");
        LoopQueue<Integer> loop_queue = new LoopQueue<>(5);
        System.out.println(loop_queue);
        System.out.println(loop_queue.isEmpty());
        System.out.println(loop_queue.getSize());
        System.out.println(loop_queue.getCapacity());

        for(int i=0; i<3; i++) {
            loop_queue.enqueue(i);
            System.out.println(loop_queue);
        }
        System.out.println(loop_queue.isEmpty());
        System.out.println(loop_queue.getSize());
        System.out.println(loop_queue.getCapacity());

        System.out.println("getFront="+loop_queue.getFront());
        System.out.println(loop_queue);

        System.out.println(loop_queue.dequeue());
        System.out.println(loop_queue);

        System.out.println(loop_queue.dequeue());
        System.out.println(loop_queue);

        System.out.println(loop_queue.dequeue());
        System.out.println(loop_queue);

        System.out.println("------------------------ 循环队列 end ------------------------");
        */

        System.out.println("************ ArrayQueue与LoopQueue性能测试 ************");
        int opCnt1 = 100000;
        ArrayQueue<Integer> aq = new ArrayQueue<>();
        LoopQueue<Integer> lq = new LoopQueue<>();

        System.out.println("array queue");
        testEnqueue(aq,opCnt1);
        System.out.println("loop queue");
        testEnqueue(lq,opCnt1);

        System.out.println("array queue");
        testDequeue(aq,opCnt1);
        System.out.println("loop queue");
        testDequeue(lq,opCnt1);
        //出队性能差别明显！入队效率数量级差不多，但入队效率ArrayQueue高；出队效率 LoopQueue 明显高！！
        //以上只是大致看一下效率情况，实际的效率还与操作系统、JVM、机器本身配置等有关！
    }

    private static void testEnqueue(Queue<Integer> q, int opCnt) {
        long startTime = System.nanoTime();
        //入队
        Random random = new Random();
        for(int i=0;i<opCnt; i++) {
            q.enqueue(random.nextInt());
        }

        long endTime = System.nanoTime();
        System.out.println("opCnt="+opCnt+",testEnqueue exec time is "+ (endTime - startTime)/1000000000.0 +" s.");
    }

    private static void testDequeue(Queue<Integer> q, int opCnt) {
        long startTime = System.nanoTime();

        //入队
        Random random = new Random();
        for(int i=0; i<opCnt; i++) {
            q.enqueue(random.nextInt());
        }

        //出队
        for(int i=0; i<opCnt; i++) {
            q.dequeue();
        }

        long endTime = System.nanoTime();
        System.out.println("opCnt="+opCnt+",testDequeue exec time is "+ (endTime - startTime)/1000000000.0 +" s.");
    }
}
