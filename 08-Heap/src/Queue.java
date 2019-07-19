public interface Queue<E> {
    //入队-从队列尾部进入
    void enqueue(E e);
    //出队-从队列首部出队
    E dequeue();
    //查看队首元素
    E getFront();
    //队列元素个数
    int getSize();
    //队列是否为空
    boolean isEmpty();
}
