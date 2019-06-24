public class Main {
    public static void main(String[] args) {
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
    }
}
