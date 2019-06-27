/**
 * 含有头结点且没有虚拟头结点的链表（没有虚拟头结点，需要注意：不是所有节点都存在前一个节点，比如头结点，很多时候，都需要特殊处理！）
 * 主要方法：
 * 1.获取链表中元素个数
 * 2.添加元素：头 O(1)--因为有头结点 尾 O(n)---需要从头到尾遍历所有节点 中间 O(n/2)=O(n)
 * 3.修改元素：头 O(1)--因为有头结点 尾 O(n)---需要从头到尾遍历所有节点 中间 O(n/2)=O(n)
 * 4.删除元素：头 O(1)--因为有头结点 尾 O(n)---需要从头到尾遍历所有节点 中间 O(n/2)=O(n)
 * 5.查找元素：头 O(1)--因为有头结点 尾 O(n)---需要从头到尾遍历所有节点 中间 O(n/2)=O(n)
 * 6.判断链表是否为空 :O(1)--因为有size
 * 7.判断链表是否包含某元素:O(n)
 * @param <E>
 */
public class LinkedList01<E> {
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
    private Node head;//链表头结点

    //构造函数
    public LinkedList01() {
        this.size = 0;
        this.head = null;
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
        sb.append("LinkedList: head="+head+",size="+size+",elements of nodes=head [");

        Node cur = head;
        while (cur != null) {
            sb.append(cur.element + " -> ");
            cur = cur.next;
        }

        sb.append("NULL] tail");
        return sb.toString();
    }

    //插入元素-从最简单的头部插入元素开始！！！由易入难！
    public void addFirst00(E element) {
        //链表为空
//        if(head == null) {
//            head = new Node(element);
//        } else {
//            head = new Node(element,head);
//        }

        //不需要做以上判断，合并做以下写法！
        head = new Node(element,head);
        size ++;
    }

    //在尾部插入元素
    public void addLast00(E element) {
        //链表为空
        if(head == null) {
            head = new Node(element);
        } else {
            //找到当前链表的最后一个元素,并修改它的next域
            Node cur = head;
            while (cur.next != null) {//最后一个节点的next域为null
                cur = cur.next;
            }

            cur.next = new Node(element);

        }

        size ++;
    }

    //插入元素-任意索引位置
    //在链表中不是常用操作，仅作为练习用
    public void add(int index, E element) {
        //判断index合法性
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("add failed,index is out of bounds");
        }

        //因为在某个位置插入元素的关键是：找到这个位置的节点的前一个节点，而如果插入位置在头结点的位置，那么是找不到头结点的前一个节点的，所以，需要特殊处理一下
        if(index == 0) {
            head = new Node(element,head);
        } else {
            Node prev = head;
            for(int i=0; i<index-1; i++) {//通过index,确定前一个元素
                prev = prev.next;
            }

            //下面两句的顺序很重要！！不能交换顺序！！
//            Node node = new Node(element);
//            node.next = prev.next;
//            prev.next = node;

            //简化！
            prev.next = new Node(element,prev.next);
        }

        //注意维护元素个数！
        size ++;
    }

    //add写好后，可以重写addFirst,addLast
    public void addFirst(E e) {
        add(0,e);
    }

    public void addLast(E e) {
//        add(size-1,e);
        add(size,e); //是在size的位置插入！不是size-1 !!!!!
    }


//    public E getHead() {
//        if(head == null) {
//            throw new IllegalArgumentException("this linkedlist is empty");
//        }
//        return head.element;
//    }

    //获得指定位置的元素
    //在链表中不是常用操作，仅作为练习用
    public E get(int index) {
        if(index<0 || index >= size) {
            throw new IllegalArgumentException("get failed,index is out of bounds.");
        }

        if(head == null) {
            return null;
        } else {
            Node cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur.element;
        }
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size -1);
    }

    //更新元素
    //在链表中不是常用操作，仅作为练习用
    public void set(int index, E e) {
        if(index<0 || index >= size) {
            throw new IllegalArgumentException("get failed,index is out of bounds.");
        }
        Node cur = head;
        for(int i=0; i<index; i++) {
            cur = cur.next;
        }
        cur.element = e;
    }

    //删除元素，并返回删除的元素
    //在链表中不是常用操作，仅作为练习用
    public E remove(int index) {
        if(index<0 || index >= size) {
            throw new IllegalArgumentException("get failed,index is out of bounds.");
        }

        //思路：找到指定位置的前一个节点元素prev，delNode=prev.next;修改prev.next=delNode.next;delNode.next=null;
        //注意：由于第一个节点没有前一个节点，所以，需要特殊处理！
        if(index == 0) {
            if(head == null) {
                return null;
            } else {
                Node delNode = head;
                head = head.next;
                delNode.next = null;
                size --;
                return delNode.element;
            }
        } else {
            Node prev = head;
            for(int i=0; i< index-1; i++) {
                prev = prev.next;
            }
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;

            //注意维护变量！
            size --;

            return delNode.element;
        }
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    //查找链表是否包含某元素
    public boolean contains(E e) {
        Node cur = head;

//        if(cur == null) {
//            throw new IllegalArgumentException("empty linkedlist");
//        }

        while (cur != null) {
            if(cur.element.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


    public static void main(String[] args) {
        LinkedList01<Integer> ll = new LinkedList01<>();
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
    }
}
