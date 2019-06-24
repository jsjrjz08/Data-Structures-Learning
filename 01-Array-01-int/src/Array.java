
public class Array {
    private int[] data;
    private int size;

    public Array(int capacity) {
        this.size = 0;
        this.data = new int[capacity];
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
    public void add(int index,int e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("add Failed. index out of bounds.");
        }

//        if(data.length == size) {
//            throw new ArrayIndexOutOfBoundsException("add Failed.array is oversized");
//        }

        for (int i=size; i>index; i--) {
            data[i] = data[i-1];
        }
        data[index] = e;
        size ++;
    }

    //增加元素-在头部
    public void addFirst(int e) {
        add(0,e);
    }

    //增加元素-在尾部
    public void addLast(int e) {
        add(size,e);
    }

    //取出元素-指定位置
    public int get(int index) {
        if(index < 0 || index >= size ) {
            throw new IllegalArgumentException("getElment failed.index out of bounds.");
        }
        return data[index];
    }

    //查找元素，返回索引
    public int find(int e) {
        for(int i=0; i<size; i++) {
            if(data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    //查找所有指定元素
    public int[] findAll(int e) {
        int[] res = new int[size];
        for(int i=0; i<size; i++) {
            if(data[i] == e) {
                res[i]=i;
            }
        }
        return res;
    }

    //是否存在某个元素
    public boolean contains(int e) {
        return find(e) == -1 ? false : true;
    }

    //修改元素
    public void set(int index,int e) {
        if(index < 0 || index >=size) {
            throw new IllegalArgumentException("setElement failed.index out of bounds.");
        }
        data[index] = e;
    }

    //删除元素-指定位置，返回已删除的元素
    public int remove(int index) {
        if(index < 0 || index >=size) {
            throw new IllegalArgumentException("removeElement failed.index out of bounds.");
        }
        int e = data[index];
        for(int i=index; i<size; i++) {
            data[i] = data[i+1];
        }
        size --;
        return e;
    }

    //删除第一个元素
    public int removeFirst() {
        return remove(0);
    }

    //删除最后一个元素
    public int removeLast() {
        return remove(size-1);
    }

    //删除所有指定元素
    public void removeAllElement(int e) {
        for(int i=0; i<size; i++) {
            if(data[i] == e) {
                remove(i);
            }
        }
    }

}
