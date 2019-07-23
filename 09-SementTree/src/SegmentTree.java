/**
 * 线段树 --一维、二维、。。。多维
 * 1.不一定是满二叉树、不一定是完全二叉树
 * 2.是平衡二叉树，即最大深度与最小深度的差最多不超过 1
 * 3.使用数组存储线段树，数组应该开辟多大空间呢？
 * 假设线段树是满二叉树、区间有n个元素(即叶子节点个数 )、且不考虑线段树添加元素，那么：
 * 如果n满足n=2^k的形式，那么叶子节点占用最后一层n个存储空间，加上前k-1层的总结点数n，总共大约有2n个节点
 * 如果n不满足n=2^k的形式，那么叶子节点会占用两层存储空间，也就是占用k+1层空间，有上可知,前k层总共大约有2n个节点，则k+1层这一层大约有2n个节点，所以，这k+1层总共约有4n个节点
 * 综上，如果使用静态数组存储线段树，数组应该开辟4n的空间.这4n的空间足以存储这个线段树了，并且很大情况下，会有很多空余空间的。
 *
 * ！！！底层数组不会增加或删除元素
 */
public class SegmentTree<E> {
    private E[] data;//存储区间内n个元素
    private E[] tree;//存储线段树的所有节点
    private Merger<E> merger;//融合器，可定义合并规则

    //构造函数
    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;

        int len = arr.length;

        data = (E[])new Object[len];
        for(int i=0; i<len; i++) {
            data[i] = arr[i];
        }

        tree = (E[])new Object[4*len];
        buildSegmentTree(0,0,len-1);
    }

    //递归函数：在treeIndex的位置创建表示data区间[l,r]的线段树，计算tree[treeIndex]的值
    private void buildSegmentTree(int treeIndex,int l,int r) {
        //结束条件
        if(l==r) {//区间内就一个元素
            tree[treeIndex] = data[l];
            return;
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
//        int mid = (l+r)/2; //l+r的结果有超出整型范围的风险！
        int mid = l + (r-l) / 2;
        //创建treeIndex的左树
        buildSegmentTree(leftChildIndex, l, mid);
        //创建treeIndex的右树
        buildSegmentTree(rightChildIndex, mid + 1, r);
        //treeIndex上的值
        tree[treeIndex] = merger.merge(tree[leftChildIndex],tree[rightChildIndex]);

    }

    //辅助函数 将当前树看成满二叉树，找到当前节点的左孩子节点的索引
    private int leftChild(int curIndex) {
        return 2*curIndex + 1;
    }
    //辅助函数 将当前树看成满二叉树，找到当前节点的右孩子节点的索引
    private int rightChild(int curIndex) {
        return 2*curIndex + 2;
    }

    public int getSize() {
        return data.length;
    }

    public boolean isEmpty() {
        return data.length == 0;
    }

    //查询data中下标为index的值
    public E get(int index) {
        if(index<0 || index>=data.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }

    //查询data区间值：在以treeIndex为根的线段树中查找data下标区间为[queryL,queryR]的统计信息
    public E query(int queryL,int queryR) {
        if(queryL<0 || queryL>=data.length || queryR<0 || queryR>=data.length) {
            throw new IllegalArgumentException("区间值 is illegal");
        }
        if(queryR<queryL) {//保证queryR>=queryL
            int tmp = queryR;
            queryR=queryL;
            queryL=tmp;
        }
        return query(0,0,data.length - 1, queryL, queryR);
    }

    //递归函数：在以treeIndex为根节点的线段树，即查找data的下标区间[l,r]上查找data下标[queryL,queryR]范围内的统计值，并将值返回.
    //注：l和r表示的是treeIndex这个节点的数据的区间范围，也就是说，l和r是data的下标值
    private E query(int treeIndex,int l,int r,int queryL,int queryR) {
        //结束条件
        if(l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;

        if(queryR<= mid ) {//注意区间划分 =
            return query(leftChildIndex,l,mid,queryL,queryR);

        } else if (queryL>= mid+1) {
            return query(rightChildIndex,mid+1,r,queryL,queryR);

        } else {
            return merger.merge(query(leftChildIndex,l,mid,queryL,mid)
                    , query(rightChildIndex, mid+1,r,mid+1,queryR));
        }
    }

    //更新data上指定位置的元素
    public void update(int index, E newValue) {
        if(index<0 || index>=data.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        data[index] = newValue;
        //更新线段树
        update(0,0,data.length-1,index,newValue);
    }

    //递归函数：更新以treeIndex为根节点的线段树上的元素(treeIndex所在节点上的数据表示的是data[l,r]区间上的统计值)
    private void update(int treeIndex,int l,int r,int index,E e) {
        //找到那个元素

        //结束条件
        if(l == r) {
            tree[treeIndex] = e;
            return ;
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        int mid = l+(r-l)/2;

        if(index <= mid) {//左
            update(leftChildIndex,l,mid,index,e);
        } else {//右
            update(rightChildIndex,mid+1,r,index,e);
        }

        //中 ------后序遍历
        tree[treeIndex] = merger.merge(tree[leftChildIndex],tree[rightChildIndex]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[size="+getSize()+",data={");
        for(int i=0;i<data.length;i++) {
            sb.append(data[i]);
            if(i != data.length-1) {
                sb.append(",");
            }
        }
        sb.append("},treeSize="+tree.length+",tree={");
        int treeNotNullCount = 0;
        for(int i=0;i<tree.length;i++) {
            sb.append(tree[i]);
            if(i != tree.length-1) {
                sb.append(",");
            }

            if(tree[i] !=null) {
                treeNotNullCount ++;
            }
        }
        sb.append("}] treeNotNullCount="+treeNotNullCount+" treeArrayLength="+tree.length);
        return sb.toString();
    }

    public static void main(String[] args) {
//        Integer[] arr = {3,7,1};
        Integer[] arr = {-2,0,3,-5,2,-1};
        SegmentTree<Integer> st = new SegmentTree<>(arr,(a,b)-> a+b);//lambda表达式:求和
        System.out.println(st);
//        System.out.println(st.get(1));
        System.out.println(st.query(0,2));
        System.out.println(st.query(2,5));
        System.out.println(st.query(0,5));
        st.update(2,4);
        System.out.println(st);
    }
}
