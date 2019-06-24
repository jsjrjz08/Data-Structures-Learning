/**
 * 使用数组实现Stack,出栈、入栈、查看栈顶元素的（均摊）时间复杂度均为O(1)
 * @param <E>
 */
//利用自定义的Array实现Stack
public class ArrayStack<E> implements Stack<E> {
    private Array<E> array;

    public ArrayStack() {
        array = new Array<>();
    }

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int size = array.getSize();
        sb.append(String.format("size=%d, capacity=%d, stack data=",size,array.getCapacity()));
        for (int i=0; i<size; i++) {
            sb.append(array.get(i));
            if(i != size -1 ) {
                sb.append(", ");
            }
        }
        sb.append("] -> top");
        return sb.toString();
    }

    //Stack的具体实现类ArrayStack特有的方法！
    public int getCapacity() {
        return array.getCapacity();
    }

    //O(1) **resize均摊复杂度为O(1)
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    //O(1) **resize均摊复杂度为O(1)
    @Override
    public E pop() {
        return array.removeLast();
    }

    //O(1)
    @Override
    public E peek() {
        return array.getLast();
    }

    //O(1)
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    //O(1)
    @Override
    public int getSize() {
        return array.getSize();
    }
}
