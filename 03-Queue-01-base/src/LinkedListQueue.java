/**
 * 使用链表实现队列。
 * 分析：
 * 0） 队列需要在首尾两端进行操作，队首出队-删除元素，队尾入队-添加元素
 * 1）之前的链表只有head指向链表的第一个节点，因此，需要改进链表，给链表添加一个tail指向链表的最后一个节点；
 * 2）对于链表的第一个节点，增删操作都为O(1)；对于最后一个节点，增操作为O(1)，删操作为O(n)--需要找到tail的前一个节点prev，而找prev的过程是o(n)的！
 * 3) 综上，只有将链表的head作为队列的队首、tail作为队列的队尾，才能获得出队和入队时间复杂富都为O(1)的效果。
 * @param <E>
 *
 *
 */
public class LinkedListQueue<E> implements Queue<E> {
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

    //此处不使用dummyHead节点
    private Node head;
    private Node tail;
    private int size; //链表元素个数

    //tail == null (由tail == null 可以推断出，head == null。因为tail是指向链表最后一个不为null的Node对象的): 表示链表是空的
    //head == tail 且 size != 0：表示链表有一个元素

    //构造函数
    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue: size=%d, data=front [",getSize()));

        Node cur = head;
        while(cur != null) {
            sb.append(cur.element+"->");
            cur = cur.next;
        }

        sb.append("NULL] tail");

        return sb.toString();
    }

    //O(1)
    @Override
    public int getSize() {
        return size;
    }

    //O(1)
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //O(1)
    @Override
    public E getFront() {
        if(isEmpty()) {
            throw new IllegalArgumentException("getFront is failed,this queue is empty.");
        }
        return head.element;
    }

    public E getTail() {
        if(isEmpty()) {
            throw new IllegalArgumentException("getTail is failed,this queue is empty.");
        }
        return tail.element;
    }

    //O(1)
    //入队在链表尾部操作
    @Override
    public void enqueue(E e) {
        Node newNode = new Node(e);
        //队列是空的
        if(tail == null) {
            tail = newNode;
            head = tail;
        } else {
            //head不变
//            //tail改变指向
            tail.next = newNode;
            tail = tail.next;
        }

        //注意维护size
        size ++;
    }

    //O(1)
    //出队在链表头部操作
    @Override
    public E dequeue() {
        if(isEmpty()) {
            throw new IllegalArgumentException("dequeue is failed,this queue is empty.");
        }

        Node outNode = head;
        //head改变指向
        head = head.next;
        outNode.next = null;

        //XXXXX tail不变!!!!!!!!!!!!考虑特殊情况：队列只有一个元素，此时做出队，那么head.next就为null,执行head = head.next;后head就是null
        //tail也应该为null了。如果不手动修改tail=null，那么tail还是指向delNode
        //此处特别要注意！！！！！此处和enqueue的 队列为空 判断是呼应的！
        if(head == null) {//此时的head已经向后移动了一个位置
            tail = null;
        }

        //注意维护size
        size --;
        return outNode.element;
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        System.out.println(queue);
        System.out.println(queue.isEmpty());
        System.out.println(queue.getSize());
//        System.out.println(queue.getCapacity());

        for(int i=0; i<3; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
        System.out.println(queue.isEmpty());
        System.out.println(queue.getSize());
//        System.out.println(queue.getCapacity());

        System.out.println("getFront="+queue.getFront());
        System.out.println(queue);

        System.out.println(queue.dequeue());
        System.out.println(queue);

        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println("getTail="+queue.getTail());

        System.out.println(queue.dequeue());
        System.out.println(queue);
        System.out.println("getTail="+queue.getTail());

        //exception
        System.out.println(queue.dequeue());
        System.out.println(queue);
    }
}
