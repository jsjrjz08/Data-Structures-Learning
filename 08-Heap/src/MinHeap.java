/**
 * 使用动态数组实现小顶堆
 * 小顶堆特点：
 * 1.元素具有可比性
 * 2.每一个节点都不大于它的左右孩子
 * 3.是一颗完全二叉树（每一层排满后才开始排下一层；元素靠左对齐）
 *
 * @param <E>
 */
public class MinHeap<E extends Comparable<E>> {
    private Array<E> array;

    //构造函数
    public MinHeap(int capacity) {
        array = new Array<>(capacity);
    }

    public MinHeap() {
        array = new Array<>();
    }

    //heapify
    public MinHeap(E[] arr) {
        array = new Array<>(arr);
        for(int i=parent(array.getSize()-1); i>=0; i--) {
            siftDown(i);
        }
    }

    @Override
    public String toString() {
        return array.toString();
    }

    int getSize() {
        return array.getSize();
    }

    boolean isEmpty() {
        return array.isEmpty();
    }

    private int parent(int curIndex) {
        if(curIndex == 0) {
            throw new IllegalArgumentException("root has no parents");
        }
        return (curIndex - 1) / 2 ;
    }

    private int leftChild(int curIndex) {
        return 2*curIndex+1;
    }

    private int rightChild(int curIndex) {
        return 2*curIndex+2;
    }

    //上浮 因为是小顶堆，所以，当前元素小于父亲节点的时候才上浮
    private void siftUp(int curIndex) {
        while(curIndex > 0 && array.get(curIndex).compareTo(array.get(parent(curIndex))) < 0) {
            array.swap(curIndex,parent(curIndex));
            curIndex = parent(curIndex); /////不要忘记！！！！
        }
    }

    //下沉 因为是小顶堆，所以，当前元素比左右孩子中的最小值还大的时候，该元素需要与左右孩子中的最小值的元素交换位置
    private void siftDown(int curIndex) {
        while(leftChild(curIndex) < array.getSize()) {
            int min = leftChild(curIndex);
            if((min+1) < array.getSize() && array.get(min+1).compareTo(array.get(min)) < 0) {
                min = min+1;
            }

            if(array.get(curIndex).compareTo(array.get(min)) > 0) {
                array.swap(curIndex,min);
                curIndex = min;
            } else {
                break;
            }
        }
    }

    //查看最小元素------------O(1)
    E findMin() {
        if(array.getSize() == 0) {
            throw new IllegalArgumentException("can not findMin in an empty heap");
        }
        return array.get(0);
    }

    //添加元素------------O(logn)
    void add(E e) {
        array.addLast(e);
        //保持堆的性质--上浮:因为元素添加到最后，只能向上浮
        siftUp(array.getSize() - 1);
    }

    //取出并删除最小元素------------O(logn)
    E extractMin() {
        E min = findMin();
        array.swap(0,array.getSize() - 1);
        array.removeLast();
        //保持堆的性质
        siftDown(0);
        return min;
    }

    //不使用以上实现的[extracMin+add]方法组合实现,因为组合的话，时间复杂度为O(logn)
    //取出最小元素后，放入一个新元素。返回取出的原最小元素------------O(1)
    E replace(E e) {
        E min = findMin();
        array.set(0,e);
        //保持堆的性质
        siftDown(0);
        return min;
    }


    public static void main(String[] args) {
        Integer[] initArr = {2,3,2,1};
//        Integer[] initArr = {15,11,3,7,9,8,20,30,40,50,67};
//        Integer[] initArr = {19,5,1};
        MinHeap<Integer> minHeap = new MinHeap<>(initArr.length);
        for(int i=0;i<initArr.length;i++) {
            minHeap.add(initArr[i]);
        }

        System.out.println("原始小顶堆："+minHeap);
        System.out.println("删除："+minHeap.extractMin()+"后,minHeap="+minHeap);
        System.out.println("查看最小元素："+minHeap.findMin()+"后,minHeap=findMin"+minHeap);
        int e=18;
        minHeap.replace(e);
        System.out.println("replace 为新值 "+e+" 后，堆为："+minHeap);


        System.out.println("测试heapify");
        //测试heapify
        MinHeap<Integer> minHeap2 = new MinHeap<>(initArr);
        System.out.println("原始小顶堆："+minHeap2);
        System.out.println("删除："+minHeap2.extractMin()+"后,minHeap2="+minHeap2);
        System.out.println("查看最小元素："+minHeap2.findMin()+"后,minHeap2=findMin"+minHeap2);
        minHeap2.replace(e);
        System.out.println("replace 为新值 "+e+" 后，堆为："+minHeap2);
    }


}
