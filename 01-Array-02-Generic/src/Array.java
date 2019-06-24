/**
 *  泛型化
 *  1.加类型 <E>,其中E可用其他字母代替
 *  2.构造函数只能通过Object对象强制转换成E[]
 *  3.对象比较需要使用equals方法
 *  4.自定义对象需要重写equals方法
 *  4.删除对象元素的时候，需要释放对象
 * @param <E>
 */
public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity) {
        this.size = 0;
        this.data = (E[])new Object[capacity];
    }

    public Array() {
        this(10);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(String.format("size=%d, capacity=%d, data=(",size,data.length));

        for(int i=0; i<size; i++) {
            sb.append(data[i]);
            if(i != (size-1)) {
                sb.append(", ");
            }
        }

        sb.append(")]");
        return sb.toString();
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    //增加元素
    public void add(int index,E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("add Failed. index out of bounds.");
        }

        for (int i=size; i>index; i--) {
            data[i] = data[i-1];
        }
        data[index] = e;
        size ++;
    }

    //增加元素-在头部
    public void addFirst(E e) {
        add(0,e);
    }

    //增加元素-在尾部
    public void addLast(E e) {
        add(size,e);
    }

    //取出元素-指定位置
    public E get(int index) {
        if(index < 0 || index >= size ) {
            throw new IllegalArgumentException("getElment failed.index out of bounds.");
        }
        return data[index];
    }

    //查找元素，返回索引
    public int find(E e) {
        for(int i=0; i<size; i++) {
            if(data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    //查找所有指定元素
    public String findAll(E e) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<size; i++) {
            if(data[i].equals(e)) {
                sb.append(i+",");
            }
        }
        return sb.toString().isEmpty() ? "-1" : sb.toString();
    }

    //是否存在某个元素
    public boolean contains(E e) {
        return find(e) == -1 ? false : true;
    }

    //修改元素
    public void set(int index,E e) {
        if(index < 0 || index >=size) {
            throw new IllegalArgumentException("setElement failed.index out of bounds.");
        }
        data[index] = e;
    }

    //删除元素-指定位置，返回已删除的元素
    public E remove(int index) {
        if(index < 0 || index >=size) {
            throw new IllegalArgumentException("removeElement failed.index out of bounds.");
        }
        E e = data[index];
        for(int i=index; i<size; i++) {
            data[i] = data[i+1];
        }

        size --;

        //释放引用的对象
        data[size] =null;

        return e;
    }

    //删除第一个元素
    public E removeFirst() {
        return remove(0);
    }

    //删除最后一个元素
    public E removeLast() {
        return remove(size-1);
    }

    //删除所有指定元素
    public void removeAllElement(E e) {
        for(int i=0; i<size; i++) {
            if(data[i].equals(e)) {
                remove(i);
            }
        }
    }

}
