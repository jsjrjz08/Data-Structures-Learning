/**
 * 使用链表作为底层数据结构实现集合Set
 * LinkedList为自己实现的数据结构
 * @param <E>
 */
public class LinkedListSet<E> implements Set<E> {
    //Set的成员变量
    private LinkedList list;
    private int size;

    //Set的构造函数
    public LinkedListSet() {
        list = new LinkedList();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public void add(E e) {
        list.addFirst(e);//底层LinkedList只维护了head，而没有tail,所以，选择时间复杂度小的addFirst
    }

    @Override
    public E remove() {
//        return list.removeFirst();
        return null;
    }
}
