/**
 * 使用链表作为底层数据结构实现集合Set
 * LinkedList为自己实现的数据结构
 * @param <E>
 */
public class LinkedListSet<E> implements Set<E> {
    //Set的成员变量
    private LinkedList list;
//    private int size;

    //Set的构造函数
    public LinkedListSet() {
        list = new LinkedList();
//        size = 0;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public void add(E e) {
        if(!list.contains(e)) { //不添加重复元素！！
            list.addFirst(e);//底层LinkedList只维护了head，而没有tail,所以，选择时间复杂度小的addFirst
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    public static void main(String[] args) {
        LinkedListSet<Integer> set = new LinkedListSet<>();

        System.out.println(set.getSize());
        System.out.println(set.isEmpty());
        set.add(50);
        set.add(30);
        set.add(60);
        set.add(80);
        set.add(40);
        set.add(70);
        set.add(20);

        set.add(55);
        set.add(52);
        set.add(57);
        set.add(88);
        set.add(86);
        set.add(90);
        set.add(30);
        System.out.println(set);
        System.out.println(set.getSize());
        System.out.println(set.isEmpty());
        set.remove(-1);
        System.out.println(set);
        set.remove(30);
        System.out.println(set);
        System.out.println(set.contains(34));
        System.out.println(set.contains(30));
        System.out.println(set);
    }
}
