public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    //构造函数
    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Stack:size=%d,data=top[%s]",linkedList.getSize(),linkedList.toString()));
        return sb.toString();
    }

    //因为链表结构对于头结点的增、删、改、查操作是O(1)的，而且，栈是针对栈顶一端操作的，
    //所以，使用链表的xxFirst操作，能够获得最小的时间复杂度

    //O(1)
    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    //O(1)
    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    public static void main(String[] args) {

        LinkedListStack<Integer> stk = new LinkedListStack<>();
        System.out.println(stk.isEmpty());
        for(int i=0; i<10; i++) {
            stk.push(i);
            System.out.println(stk);
        }
        System.out.println(stk);
        System.out.println(stk.isEmpty());

        System.out.println(stk.pop());
        System.out.println(stk);

        System.out.println(stk.peek());

        System.out.println("size="+stk.getSize());

        //getCapacity与具体的底层实现有关，此处使用链表实现栈结构，链表的capacity没有意义，所以这里没有设计这个方法
//        System.out.println("capacity="+stk.getCapacity());

        stk.push(10);
        System.out.println(stk);
        stk.push(11);
        System.out.println(stk);

    }
}
