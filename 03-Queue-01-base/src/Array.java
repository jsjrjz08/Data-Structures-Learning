/**
* 动态数组-容量可随数据量增减而变化的数组
* 1.容量变化的时机-添加元素或删除元素
* 2.容量延迟变化，可降低容量震荡的频率，是一种优化方案
* 3.时间复杂度分析、均摊时间复杂度
*  @param <E>
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

    //增加元素**时间复杂度(指最坏情况):O(n) ***resize的时间复杂度是O(2n+1/n)=O(1),所以，均摊复杂度还是O(n)
    public void add(int index,E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("add Failed. index out of bounds.");
        }

        if(size == data.length) { //数组已满,进行2倍扩容
//            System.out.println("xxx");
            resize(2*data.length);
        }

//        for (int i=size; i>index; i--) {
////            System.out.println("i="+i+",index="+index);
//            data[i] = data[i-1];
//        }
        for(int i = size - 1; i >= index ; i --)
            data[i + 1] = data[i];

        data[index] = e;
        size ++;
    }

    //增加元素-在头部**时间复杂度(指最坏情况):O(n) ***resize的时间复杂度是O(2n+1/n)=O(1),所以，均摊复杂度还是O(n)
    public void addFirst(E e) {
        add(0,e);
    }

    //增加元素-在尾部**时间复杂度(指最坏情况):O(1) ***resize的时间复杂度是O(2n+1/n)=O(1),所以，均摊复杂度(最坏情况不是每次都发生)是O(1)
    public void addLast(E e) {
        add(size,e);
    }

    //取元素-指定位置**时间复杂度(指最坏情况):O(1) --随机读取
    public E get(int index) {
        if(index < 0 || index >= size ) {
            throw new IllegalArgumentException("getElment failed.index out of bounds.");
        }
        return data[index];
    }

    //取出第一个元素
    public E getFirst() {
        return get(0);
    }

    //取出最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    //查找元素，返回索引**时间复杂度(指最坏情况):O(n)
    public int find(E e) {
        for(int i=0; i<size; i++) {
            if(data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    //查找所有指定元素**时间复杂度(指最坏情况):O(n)
    public String findAll(E e) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<size; i++) {
            if(data[i].equals(e)) {
                sb.append(i+",");
            }
        }
        return sb.toString().isEmpty() ? "-1" : sb.toString();
    }

    //是否存在某个元素**时间复杂度(指最坏情况):O(n)
    public boolean contains(E e) {
        return find(e) == -1 ? false : true;
    }

    //修改元素**时间复杂度(指最坏情况):O(1)
    public void set(int index,E e) {
        if(index < 0 || index >=size) {
            throw new IllegalArgumentException("setElement failed.index out of bounds.");
        }
        data[index] = e;
    }

    //删除元素-指定位置，返回已删除的元素**时间复杂度(指最坏情况):O(n) ***resize的时间复杂度是O(2n+1/n)=O(1),所以，均摊复杂度还是O(n)
    public E remove(int index) {
        if(index < 0 || index >=size || size==0) {
            throw new IllegalArgumentException("removeElement failed.index out of bounds.");
        }

        //数组如果只使用1/2 && 缩容的最小容量不能到0 !!!!1
//        if(size == data.length/2 && data.length/2 != 0) { //数组使用1/2容量,进行1/2倍缩容
//            resize(data.length/2);
//        }


        E e = data[index];
//        for(int i=index+1; i<size; i++) {
////            System.out.println("i="+i+",index="+index);
//            data[i] = data[i+1];
//        }
        for(int i = index + 1 ; i < size ; i ++)
            data[i - 1] = data[i];

        size --;
        //释放引用的对象
        data[size] =null;

        //延迟缩容 && 缩容的最小容量不能到0 !!!!1
        if(size == data.length/4 && data.length/2 != 0) { //数组使用1/4容量,进行1/2倍缩容
            resize(data.length/2);
        }

        return e;
    }

    //删除第一个元素**时间复杂度(指最坏情况):O(n) ***resize的时间复杂度是O(2n+1/n)=O(1),所以，均摊复杂度还是O(n)
    public E removeFirst() {
        return remove(0);
    }

    //删除最后一个元素**时间复杂度(指最坏情况):O(1) ***resize的时间复杂度是O(2n+1/n)=O(1),所以，均摊复杂度是O(1)
    public E removeLast() {
        return remove(size-1);
    }

    //删除所有指定元素**时间复杂度(指最坏情况):O(n)
    public void removeAllElement(E e) {
        for(int i=0; i<size; i++) {
            if(data[i].equals(e)) {
                remove(i);
            }
        }
    }

    //数组容量动态变化
    private void resize(int newCapacity) {
        //生成一个更大或更小的新数组，将原来的数据复制到新数组，然后将原来的引用指向新数组对象
        E[] newData = (E[]) new Object[newCapacity];
        for(int i=0; i<size; i++) {
            newData[i] = data[i];
        }
        System.out.println("容量变化成功，"+data.length+"->"+newCapacity);
        data = newData;
    }

}
