/**
 * 含有虚拟头结点的链表，保证每一个节点都有前一个节点！（没有虚拟头结点，需要注意：不是所有节点都存在前一个节点，比如头结点，很多时候，都需要特殊处理！）
 * 主要方法：
 * 1.获取链表中元素个数
 * 2.添加元素：头 O(1)--因为有头结点 尾 O(n)---需要从头到尾遍历所有节点 中间 O(n/2)=O(n)
 * 3.修改元素：头 O(1)--因为有头结点 尾 O(n)---需要从头到尾遍历所有节点 中间 O(n/2)=O(n)
 * 4.删除元素：头 O(1)--因为有头结点 尾 O(n)---需要从头到尾遍历所有节点 中间 O(n/2)=O(n)
 * 5.查找元素：头 O(1)--因为有头结点 尾 O(n)---需要从头到尾遍历所有节点 中间 O(n/2)=O(n)
 * 6.判断链表是否为空 :O(1)--因为有size
 * 7.判断链表是否包含某元素:O(n)
 *
 * 用户不会感知内部存在dummyHead;构造函数需要初始化dummyHead，让它指向一个节点对象;dummyhead.next是head节点；
 * 使用dummyHead之后，每一个实际节点都能找到前一个节点了，就不需要对头节点做特殊处理了；
 * 使用dummyHead之后，根据需求确定循环次数 i<next-1 还是 i<next！！！
 *
 * @param <E>
 */
public class LinkedList<E> {
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
    public LinkedList() {
        this.size = 0;
        this.dummyHead = new Node();
    }

    //获取链表中元素个数
    public int getSize() {
        return size;
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

//        Node prev = dummyHead.next; //prev从dummyHead开始，并且i到index-1为止!
        Node prev = dummyHead;
        for(int i=0; i<index; i++) {//通过index,确定前一个元素
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

    public E get(int index) {
        if(index<0 || index >= size) {
            throw new IllegalArgumentException("get failed,index is out of bounds.");
        }

        Node cur = dummyHead.next;
        for(int i=0;i<index;i++) {
            cur = cur.next;
        }
        return cur.element;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size-1);
    }

    public void set(int index, E e) {
        if(index<0 || index >= size) {
            throw new IllegalArgumentException("get failed,index is out of bounds.");
        }
        Node cur = dummyHead.next;
        for(int i=0;i<index;i++) {
            cur = cur.next;
        }
        cur.element = e;
    }

    public E remove(int index) {
        if(index<0 || index >= size) {
            throw new IllegalArgumentException("get failed,index is out of bounds.");
        }

        Node prev = dummyHead;
        for(int i=0;i<index;i++) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;

        //一定要维护！！
        size --;

        return delNode.element;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size -1);
    }

    //查找链表是否包含某元素
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while(cur != null) {
            if(cur.element.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    //删除指定元素
    public void removeElement(E e) {
        dummyHead.next = removeElement(dummyHead.next,e);
    }

    //递归：在以node为头节点的链表中删除指定元素e,并返回新链表的头节点
    public Node removeElement(Node node,E e) {
        //结束条件
        if(node == null) {
            return null;
        }

        //函数主体
        if(node.element.equals(e)) {
            node = node.next;
            size --;
        } else {
            node.next = removeElement(node.next,e);
        }
        return node;
    }

    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        System.out.println("当前链表："+ll);
        System.out.println("contains："+ll.contains(0));
        System.out.println("contains："+ll.contains(null));
        System.out.println("isEmpty："+ll.isEmpty());
        ll.add(0,-10);
        System.out.println("当前链表："+ll);
        System.out.println("getLast="+ll.getLast());
        ll.set(0,666);
        System.out.println("get(0)="+ll.get(0));
        System.out.println(ll.getSize());
//        System.out.println(ll.getHead());
        System.out.println(ll.isEmpty());
        System.out.println("当前链表："+ll);

        ll.add(0,-20);
        System.out.println("get(1)="+ll.get(1));
        System.out.println("当前链表2："+ll);
        //添加元素
        for(int i=0;i<3;i++) {
            ll.addFirst(i);
            System.out.println("LinkedList="+ll.toString());
        }
        System.out.println(ll.getSize());
        System.out.println(ll.isEmpty());
        System.out.println("当前链表："+ll);
        System.out.println("getFirst="+ll.getFirst());

        ll.addLast(100);
        System.out.println("尾部插入："+ll);

//        ll.add(-1,30);
//        ll.add(6,30);
        ll.add(4,30);
        System.out.println("add插入1："+ll);
        System.out.println("contains："+ll.contains(0));
        System.out.println("contains："+ll.contains(666));
        System.out.println("contains："+ll.contains(null));

        System.out.println("remove1："+ll.remove(1)+",ll1="+ll);
        System.out.println("remove2："+ll.remove(5)+",ll2="+ll);
        System.out.println("removeFirst3："+ll.removeFirst()+",ll3="+ll);
        System.out.println("removeLast4："+ll.removeLast()+",ll4="+ll);

        int e=30;
        ll.addLast(e);
        ll.addLast(e);
        System.out.println("addLast："+ll);
        ll.removeElement(e);
        System.out.println("after removeElement "+e+" list is "+ ll);
    }
}
