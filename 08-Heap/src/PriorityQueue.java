public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    //构造函数
    public PriorityQueue(int capacity) {
        maxHeap = new MaxHeap<>(capacity);
    }

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    public PriorityQueue(E[] arr) {
        maxHeap = new MaxHeap<>(arr);
    }

    @Override
    public String toString() {
        return maxHeap.toString();
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    public static void main(String[] args) {
        Integer[] arr = {8,3,7,3,6,1};
        PriorityQueue<Integer> pq = new PriorityQueue<>(arr);

        System.out.println(pq);

        StringBuilder sb = new StringBuilder();
        while(! pq.isEmpty()) {
            sb.append(pq.dequeue()+",");
        }
        System.out.println(sb.toString());
    }
}
