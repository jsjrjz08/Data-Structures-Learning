/**
 * 使用数组的方式实现队列，入队、查看队首元素（均摊）时间复杂度均为O(1)；出队时间复杂度为O(n)，数据规模大的情况下，效率低，可使用循环队列优化。
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue() {
        this.array = new Array<E>();
    }

    public ArrayQueue(int capacity) {
        this.array = new Array<>(capacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = array.getSize();
        sb.append(String.format("Queue: front [size=%d, capacity=%d, data=",size,array.getCapacity()));
        for(int i=0; i<size; i++) {
            sb.append(array.get(i));
            if(i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    //由具体的实现类型定义的 *** O(1)
    public int getCapacity() {
        return array.getCapacity();
    }

    // *** 均摊O(1)
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    // *** 均摊O(n)。每次出队一个元素，后面余下的元素都要往前移动一个位置，数据规模极大的情况下，效率极低!!!!
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    // *** O(1)
    @Override
    public E getFront() {
        return array.get(0);
    }

    // *** O(1)
    @Override
    public int getSize() {
        return array.getSize();
    }

    // *** O(1)
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }
}
