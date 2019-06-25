/**
 * 含有虚拟头结点的链表，保证每一个节点都有前一个节点！（没有虚拟头结点，需要注意：不是所有节点都存在前一个节点，比如头结点，很多时候，都需要特殊处理！）
 * 主要方法：
 * 1.获取链表中元素个数
 * 2.添加元素
 * 3.修改元素
 * 4.删除元素
 * 5.查找元素
 * 6.判断链表是否为空
 * @param <E>
 */
public class LinkedList02<E> {
    //节点内部类
    private class Node {
        public E element;
        public Node next;

        //构造函数
        public Node(E e, Node node) {
            element = e;
            next = node;
        }

        public Node(E e) {
            this(e,null);
        }

        public Node() {
            this(null,null);
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    //链表成员变量
    private int size;//链表元素个数
    private Node dummyHead;//链表虚拟头结点

    //构造函数
    public LinkedList02() {
        this.size = 0;
        this.dummyHead = new Node();
    }

    //获取链表中元素个数
    public int getSize() {
        return size;
    }

    public E getHead() {
        if(dummyHead.next == null) {
            throw new IllegalArgumentException("this linkedlist is empty");
        }
        return dummyHead.next.element;
    }

    //链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList: head="+dummyHead.next+",size="+size+",elements of nodes=head [");

        Node cur = dummyHead.next;
        if(cur == null) {
            return sb.append("] tail").toString();
        }

        while (cur != null) {
            sb.append(cur.element + " -> ");
            cur = cur.next;
        }

        sb.append("NULL] tail");
        return sb.toString();
    }

    //插入元素-任意索引位置
    //在链表中不是常用操作，仅作为练习用
    public void add(int index, E element) {
        //判断index合法性
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("add failed,index is out of bounds");
        }

        Node prev = dummyHead;
        for(int i=0; i<index-1; i++) {//通过index,确定前一个元素
            prev = prev.next;
        }

        //简化！
        prev.next = new Node(element,prev.next);

        //注意维护元素个数！
        size ++;
    }

    //add写好后，可以重写addFirst,addLast
    public void addFirst(E e) {
        add(0,e);
    }

    public void addLast(E e) {
        add(size,e); //是在size的位置插入！不是size-1 !!!!!
    }


    public static void main(String[] args) {
        LinkedList02<Integer> ll = new LinkedList02<>();
        ll.add(0,-10);
        System.out.println(ll.getSize());
//        System.out.println(ll.getHead());
        System.out.println(ll.isEmpty());
        System.out.println("当前链表："+ll);

        ll.add(0,-20);
        System.out.println("当前链表2："+ll);
        //添加元素
        for(int i=0;i<3;i++) {
            ll.addFirst(i);
            System.out.println("LinkedList="+ll.toString());
        }
        System.out.println(ll.getSize());
        System.out.println(ll.isEmpty());
        System.out.println("当前链表："+ll);

        ll.addLast(100);
        System.out.println("尾部插入："+ll);

//        ll.add(-1,30);
//        ll.add(6,30);
        ll.add(4,30);
        System.out.println("add插入1："+ll);
    }
}
