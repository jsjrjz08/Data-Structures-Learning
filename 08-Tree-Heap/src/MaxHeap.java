/**
 * 使用之前自己实现的动态数组实现一个大顶堆
 *
 * 大顶堆特点:
 * 0.堆顶元素是整棵树上最大的元素 ----使用堆可以实现优先队列的基础
 * 1.元素有可比较性,每一个节点都不小于它的左孩子和右孩子(堆中某个节点的值总是不大于其父节点的值)
 * 2.是完全二叉树（节点按层排列，排满一层后，再排下一层。完全二叉树：从根结点到倒数第二层满足满二叉树，最后一层可以不完全填充，其叶子结点都靠左对齐）
 * 3.是平衡二叉树
 *
 * @param <E>
 */
public class MaxHeap<E extends Comparable<E> > {

    //使用动态数组实现大顶堆
    private Array<E> array;

    //构造函数
    public MaxHeap(int capacity) {
        this.array = new Array<>(capacity);
    }

    public MaxHeap() {
        this.array = new Array<>();
    }

    //构造函数-heapify
    /**
     * 将arr看做堆，从第一个非叶子节点开始，逐步调整元素位置
     * @param arr
     */
    public MaxHeap(E[] arr) {
        if(arr.length == 0) {
            throw new IllegalArgumentException("can not get any element in arr");
        }
        int len = arr.length;
        this.array = new Array<>(arr);

        int firstLoopIndex = getParent(len-1);
        while(firstLoopIndex >= 0) {//注意边界值！ ==0 也可以进入循环，否则，如果元素个数小于等于3个，那么，就不会进行下沉操作了，造成堆顶元素可能不是最大元素的后果!
            siftDown(firstLoopIndex);
            firstLoopIndex --;
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

    //辅助私有方法 -- 方便查找一个节点的父亲节点下标、左孩子节点下标、右孩子节点下标
    private int getParent(int curIndex) {
        if(curIndex >= getSize() || curIndex < 0) {
            throw new IllegalArgumentException("index out of bounds.");
        }
//        if(0 == curIndex) {
//            return -1;//throw new IllegalArgumentException("this is root.root has no parent.");
//        }
        return (curIndex -1) / 2 ;//整除，取得整数部分
    }

    private int getLeft(int curIndex) {
//        if(curIndex >= getSize() || curIndex < 0) {
//            throw new IllegalArgumentException("index out of bounds.");
//        }
//        if(curIndex * 2 + 1 >= getSize()) {//此处似乎不需要做边界判断，因为这是一个private方法，内部使用过程中做好传参控制就好了
//            return -1;
//        }

        return curIndex * 2 + 1 ;
    }

    private int getRight(int curIndex) {
        if(curIndex >= getSize()|| curIndex < 0) {
            throw new IllegalArgumentException("index out of bounds.");
        }
//        if(curIndex*2+2 >= getSize()) {
//            return -1;
//        }

        return curIndex*2+2;
    }

    //添加节点：在数组最后一个位置追加一个元素+保持堆的性质(调整新元素在数组中的位置-上浮)
    void add(E e) {
        //添加元素
        array.addLast(e);//size+1了
        //保持堆的性质(调整新元素在数组中的位置-上浮)
        siftUp(getSize() -1);
    }

    //上浮
    private void siftUp(int curIndex) {
        if(curIndex >= getSize()|| curIndex < 0) {
            throw new IllegalArgumentException("index out of bounds.");
        }
        //非根节点+当前元素比父亲节点大，交换元素
        while(curIndex > 0 && array.get(curIndex).compareTo(array.get(getParent(curIndex))) > 0) {
            array.swap(curIndex,getParent(curIndex));
            curIndex = getParent(curIndex);
        }
    }

    //查看最大元素
    public E findMax() {
        if(getSize() == 0) {
            throw new IllegalArgumentException("can not findMax from an empty heap");
        }
        return array.get(0);
    }

    //取出（删除）最大元素
    public E extractMax() {
        E ret = findMax();
        //将最后一个元素和最大元素交换位置，然后删除并返回最后一个元素+下沉第一个元素 ---这样做是为了避免出现两棵树
        array.swap(0,array.getSize() - 1);
        array.removeLast();
        siftDown(0);
        return ret;
    }

    //下沉
    private void siftDown(int curIndex) {
//        System.out.println("cur index="+curIndex);
//        if(curIndex >= getSize()|| curIndex < 0) {
//            throw new IllegalArgumentException("index out of bounds.");
//        }

        //如果有左孩子
        while(getLeft(curIndex) < getSize()) {
            //j记录左右孩子中最大值的下标
            int j = getLeft(curIndex);
            //如果节点j存在右边的兄弟节点，并且这个兄弟节点的元素大于j的元素，则j改为右边兄弟节点的下标值
            if((j+1) < getSize() && array.get(j+1).compareTo(array.get(j)) > 0) {
                j = j+1; //
            }//目前，j记录的是当前节点的左右孩子节点中的最大值

            if(array.get(curIndex).compareTo(array.get(j)) < 0) {
                array.swap(curIndex,j);
                curIndex = j;
            } else {//必要的，否则无法退出循环
                break;
            }
        }

//        while(getRight(curIndex)>0 && getRight(curIndex) < getSize()) {//存在bug
//            int maxIndex = getMax(getLeft(curIndex),getRight(curIndex));
//            if(array.get(curIndex).compareTo(array.get(maxIndex)) < 0) {
//                array.swap(curIndex,maxIndex);
//                curIndex = maxIndex;
//            } else {
//                break;
//            }
//        }
    }

    //返回两个索引中最大元素所在索引
    private int getMaxIndex(int i, int j) {
        if(i>=getSize() || j>=getSize() || i<0 || j<0) {
            throw new IllegalArgumentException("index ("+i+","+j+") out of bounds");
        }
        return array.get(i).compareTo(array.get(j)) > 0 ? i : j;
    }

    //------------ 取出最大元素后，放入一个新元素
    public E replace(E e) {
        E ret = findMax();
        array.set(0,e);
        //保持堆的性质
        siftDown(0);
        return ret;
    }

    public static void main(String[] args) {
//        Integer[] initArr = {15,11,3,7,9,8,20,30,40,50,67};
//        Integer[] initArr = {1,5,1};
        Integer[] initArr = {2,2,3,2,2,2};
        MaxHeap<Integer> maxHeap = new MaxHeap<>(initArr.length);
        for(int i=0;i<initArr.length;i++) {
            maxHeap.add(initArr[i]);
        }

        System.out.println("原始大顶堆："+maxHeap);
        System.out.println("删除："+maxHeap.extractMax()+"后,maxHeap="+maxHeap);
        System.out.println("查看最大元素："+maxHeap.findMax()+"后,maxHeap=findMax"+maxHeap);
//        System.out.println("getMaxIndex(5,7)："+maxHeap.getMaxIndex(5,7));
        int e=18;
        maxHeap.replace(e);
        System.out.println("replace 为新值 "+e+" 后，堆为："+maxHeap);


        System.out.println("测试heapify");
        //测试heapify
        MaxHeap<Integer> maxHeap2 = new MaxHeap<>(initArr);
        System.out.println("原始大顶堆："+maxHeap2);
        System.out.println("删除："+maxHeap2.extractMax()+"后,maxHeap2="+maxHeap2);
        System.out.println("查看最大元素："+maxHeap2.findMax()+"后,maxHeap2=findMax"+maxHeap2);
//        System.out.println("getMaxIndex(5,7)："+maxHeap2.getMaxIndex(5,7));
        maxHeap2.replace(e);
        System.out.println("replace 为新值 "+e+" 后，堆为："+maxHeap2);
    }


}
