/**
 * 循环队列，不再复用之前自定义的Array，而是从底层写起
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front;
    private int tail;
    private int size; //队列中的元素个数


    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1]; //循环队列需要浪费一个空间！！
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    //返回实际的所能容纳的最大元素个数
    public int getCapacity() {
        return data.length - 1;//循环队列需要浪费一个空间！！
    }

    @Override
    public void enqueue(E e) {
        //队列已满，需要扩容
        if((tail+1) % data.length == front) {
            resize(getCapacity()*2);
        }

        //在容量充足的条件下，进行入队操作
        data[tail] = e;

//        tail ++; //error!没有考虑到 循环的问题！！！
        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public E dequeue() {
        //队列为空，无法出队
        if(isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        E res = data[front];
        data[front] = null;//回收原来front位置的对象

        front = (front+1) % data.length;
        size --;

        //是否缩小容量？size要与实际容量getCapacity()作比较，而不是与data.length作比较！！！
        if(size == getCapacity()/4 && getCapacity()/2 != 0) {
            resize(getCapacity()/2);
        }

        return res;
    }

    @Override
    public E getFront() {
        //异常处理！！！！！
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
//        return (size == 0) ? null : data[0];//error,front not always equals 0!
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
//        return (size == 0) ? true : false;
        return front == tail;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LoopQueue: front [size=%d, capacity=%d, data=",size,getCapacity()));
        for(int i=front; i<tail; i = (i+1) % data.length) {//tail 可能比front 小
            sb.append(data[i]);
            if(i != tail - 1) {
//            if(i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        //将data中的元素拷贝到newData。
        // 无论原来front是否等于0，都将
        // data[front]的元素复制到newData[0]
        // data[front+1]的元素复制到newData[0+1]
        for(int i=0;i<size;i++) {
//            newData[i] = data[i+front];
            //时刻记得循环！防止i+front在data中下标越界！
            newData[i] = data[(i+front) % data.length];
        }


        System.out.println(String.format("容量变化成功，%d->%d",getCapacity(),newCapacity));

        //修改data的指向
        data = newData;
        //一定要修改 front 和 tail !!!!否则java报错:内存溢出
        front = 0;
        tail = size;

        //data的size没有发生变化
        // capacity可以通过getCapacity()获得

    }


}
