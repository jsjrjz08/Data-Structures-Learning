import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 使用链表实现的线段树
 * 节省空间；抽象出Node类
 * @param <E>
 */
public class LinkedListSegmentTree<E> {
    private class Node {
        public E e;
        public int start;
        public int end;
        public Node left;
        public Node right;

        public Node(E e,int start,int end,Node left,Node right) {
            this.start = start;
            this.end = end;
            this.e = e;
            this.left = left;
            this.right = right;
        }

        public Node(E e,int start,int end) {
            this(e,start,end,null,null);
        }

        @Override
        public String toString() {
            return "[element="+e+",seg=["+start+","+end+"],left="+left+",right="+right+"]";
        }
    }

    private Node tree;
    private Merger<E> merger;
    private E[] data;

    //构造函数
    public LinkedListSegmentTree(E[] arr, Merger<E> merger) {
        int len = arr.length;
        if(len == 0) {
            throw new IllegalArgumentException("array is empty");
        }
        this.data = (E[])new Object[len];
        this.merger = merger;

        for(int i=0; i<len; i++) {
            data[i] = arr[i];
        }

        //创建树
        this.tree = buildSegmentTree(0,len-1);
    }

    //递归函数：使用data在区间[l,r]上的元素创建线段树，并返回新的线段树的根
    private Node buildSegmentTree(int l,int r) {
        //结束条件
        if(l == r) {
            return new Node(data[l],l,r);//设置e
        }

        //创建根节点
        Node node = new Node(null,l,r);//未设置e,left,right

        int mid = l+(r-l)/2;
        //左
        node.left = buildSegmentTree(l,mid);
        //右
        node.right = buildSegmentTree(mid+1,r);
        //当前node的e
        node.e = merger.merge(node.left.e,node.right.e);

        return node;
    }

    public int getSize() {
        return data.length;
    }

    public boolean isEmpty() {
        return data.length == 0;
    }

    //区间查询
    public E query(int queryL,int queryR) {
        if(queryL<0 || queryL >= data.length ||
                queryR<0 || queryR >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        if(queryR < queryL) {
            int tmp = queryL;
            queryL = queryR;
            queryR = tmp;
        }

        return query(tree,queryL,queryR);
    }

    //递归函数；在以node为根节点的线段树上查找[queryL,queryR]的区间统计值，并返回该值
    private E query(Node node,int queryL,int queryR) {

        if(node.start == queryL && node.end == queryR) {
            return node.e;
        }

        int l = node.start;
        int r = node.end;
        int mid = l + (r - l) /2 ;
        if(queryR <= mid) {//左 -----注意做好比较！！！！！！
            return query(node.left,queryL,queryR);

        } else if (mid+1 <= queryL) {//右
            return query(node.right,queryL,queryR);
        } else {
            return merger.merge(
                    query(node.left,queryL,mid),
                    query(node.right,mid+1,queryR)
            );
        }
    }

    //更新指定位置的元素
    public void update(int index, E e) {
        if(index<0 || index>=data.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        data[index] = e;
        update(tree,index,e);
    }

    //递归函数：在以node为根节点的线段树上更新data[index]为e,并返回新的线段树的根节点
    private Node update(Node node,int index,E e) {
        //结束条件
        if(node.start == node.end) {
            node.e = e;
            return node;
        }

        int l = node.start;
        int r = node.end;
        int mid = l+(r-l)/2;

        if(index <= mid) {
            node.left = update(node.left,index,e);
        } else {//index>=mid+1
            node.right = update(node.right,index,e);
        }

        node.e = merger.merge(node.left.e,node.right.e);
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[size="+data.length+",data=[");
        for(int i=0;i<data.length;i++) {
            sb.append(data[i]);
            if(i != data.length-1) {
                sb.append(",");
            }
        }
        sb.append("],tree=[");

        //层序遍历线段树
        sb.append(levelOrder(tree));

        sb.append("]]");
        return sb.toString();
    }

    // 层序遍历以node为根节点的树
    private String levelOrder(Node node) {
        //结束条件
        if(node == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        int listElementCnt = 1;

        while(!q.isEmpty()) {
            Node topNode = q.remove();
            res.append(topNode.e+",");
            if(topNode.left != null) {
                q.add(topNode.left);
                listElementCnt ++;
            }
            if(topNode.right != null) {
                q.add(topNode.right);
                listElementCnt ++;
            }
        }

//        System.out.println("链表中的元素个数="+listElementCnt);
        String s = res.toString();
        return s.substring(0,s.length()-1);
    }

    public static void main(String[] args) {
//        Integer[] arr = {3,7,1};
        Integer[] arr = {-2,0,3,-5,2,-1};
        LinkedListSegmentTree<Integer> st = new LinkedListSegmentTree<>(arr,(a,b)-> a+b);//lambda表达式:求和
        System.out.println(st);
        System.out.println(st.query(0,2));
        System.out.println(st.query(2,5));
        System.out.println(st.query(0,5));
        st.update(2,4);
        System.out.println(st);
    }

}
