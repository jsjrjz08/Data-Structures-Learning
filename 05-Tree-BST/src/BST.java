import java.util.LinkedList;
import java.util.Queue;

/**
 * 二分搜索树：动态数据结构；非线性数据结构；是二叉树(每个节点最多有两个孩子)；节点上的元素要有可比性；顺序性
 * 每个节点上的值都小于其左子树的所有非空节点、都大于右子树的所有非空节点、没有重复元素；
 * 每一颗子树均为二分搜索树；除根节点外，所有节点都有且只有一个父节点；空节点也是一颗二分搜索树；二分搜索树不一定是一颗满二叉树
 * 查询效率高；创建二分搜索树耗时长；元素可比较
 *
 * 深度优先遍历-前序遍历： 根节点 - 左孩子 - 右孩子
 * 深度优先遍历-中序遍历： 左孩子  - 根结点 - 右孩子
 * 深度优先遍历-后序遍历： 左孩子 - 右孩子 - 根结点
 *
 * 广度优先遍历-层序遍历：
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> {//元素可比较！Comparable
    //节点定义（对外屏蔽！private）
    private class Node {
        E e;
        Node left,right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    //二分搜索树
    private int size;//元素个数
    private Node root;//根节点

    //构造函数
    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {

//        return childString(root);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("BST size=%d,elements are :\n",size));
        generateBSTString(root,0, sb);
        return sb.toString();
    }

    private String childString(Node node) {
        //结束条件
        if(node == null) {
            return null;
        }

        //递归
        StringBuilder sb = new StringBuilder();
        sb.append("root["+node+"],");
        sb.append("left["+childString(node.left)+"],");
        sb.append("right["+childString(node.right)+"].");

        return sb.toString();
    }

    //递归-前序遍历：带有层次展示的打印
    private void generateBSTString(Node node, int depth, StringBuilder sb) {
//        StringBuilder sb = new StringBuilder();

        //结束条件
        if(node == null) {
            sb.append(generateDepthString(depth)+"null\n");
            return;
        }

        //当前节点
        sb.append(generateDepthString(depth)+node+"\n");

        //左-递归
//        sb.append(generateBSTString(node.left, depth+1)+"\n");
        generateBSTString(node.left, depth+1, sb);
        //右-递归
//        sb.append(generateBSTString(node.right, depth+1)+"\n");
        generateBSTString(node.right, depth+1, sb);
    }

    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<depth; i++) {
            sb.append("**");
        }
        return sb.toString();
    }

    //添加元素节点
    public void add(E e) {
        root = add(root,e);
    }

    //递归方法:在二分搜索树上以node为根的树添加新元素e，
    //并返回新的二分搜索树的根---根是不断变化的
    private Node add(Node node, E e) {
        //结束条件
        if(node == null) {
            size ++;
            return new Node(e);
        }

        //递归调用
        if(node.e.compareTo(e) >0) {//新元素小于根节点、但左子树不为null
            //在node的左子树找到一个空节点位置插入新元素;修改左子树的根
            node.left = add(node.left,e);

        } else if(node.e.compareTo(e) <0) {//新元素大于根节点、但右子树不为null
            //在node的右子树找到一个空节点位置插入新元素;修改右子树的根
            node.right = add(node.right,e);

        } // else node.e.compareTo(e) ==0 //不作处理

        return node;

    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    //递归方法：在以node为根节点的树中是否包含指定元素
    private boolean contains(Node node,E e) {
        //参数校验
        if(e == null) {
            throw new IllegalArgumentException("input element is null");
        }
        //递归结束条件
        if(node == null) {
            return false;
        }

        if(node.e.compareTo(e) == 0) {
            return true;

        } else if(node.e.compareTo(e)>0) {
            return contains(node.left, e);

        } else{// (node.e.compareTo(e)<0)
            return contains(node.right, e);

        }

    }

    //前序遍历 --最自然的遍历方式
    public void preOrder() {
        preOrder(root);
    }

    //前序遍历 递归方法
    private void preOrder(Node node) {
        //结束条件
        if(node == null) {
            return;
        }

        //打印当前节点
        System.out.print(node+",");

        //打印当前节点的左子树
        preOrder(node.left);
        //打印当前节点的右子树
        preOrder(node.right);
    }

    //中序遍历 --遍历结果是有序的
    public void inOrder() {
        inOrder(root);
    }

    //中序遍历 递归方法
    private void inOrder(Node node) {
        if(node == null) {
            return;
        }
//        if(node.left == null) {
//            return;
//        }

        //左
        inOrder(node.left);
        //当前节点
        System.out.print(node+",");
        //右
        inOrder(node.right);
    }

    //后序遍历 --先处理孩子，再处理节点本身，比如：内存释放、分治算法、回溯算法、动态规划
    public void postOrder() {
        postOrder(root);
    }

    //后序遍历 递归方法
    private void postOrder(Node node) {
        if(node == null) {
            return;
        }

        //左
        postOrder(node.left);
        //右
        postOrder(node.right);
        //当前节点
        System.out.print(node+",");
    }

    //层序遍历 - 非递归（借助队列）
    public void levelOrder() {
        if(root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Node cur = null;
        while(!q.isEmpty()) {
            cur = q.remove();
            //访问队首元素
            System.out.print(cur+",");

            //左
            if(cur.left != null) {
                q.add(cur.left);
            }
            //右
            if(cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    //找到二分搜索树中的最小值
    public E min() {
        //参数校验
        if(root == null) {
            return null;
        }
        return min(root).e;
    }
    //递归方法 ： 在以node为根节点的二分搜索树中找最小值节点，
    //并返回最小值的节点
    private Node min(Node node) {
        //结束条件
        if(node.left == null) {
            return node;
        }

        return min(node.left);
    }

    //找到二分搜索树中的最大值--非递归
    public E max00() {
        //参数校验
        if(root == null) {
            return null;
        }

        Node cur = root;
        while(cur.right!=null) {
            cur = cur.right;
        }
        return cur.e;
    }

    //找到二分搜索树中的最大值
    public E max() {
        //参数校验
        if(root == null) {
            return null;
        }
        return max(root).e;
    }

    //递归方法 ： 在以node为根节点的二分搜索树中找最大值节点，
    //并返回最大值的节点
    private Node max(Node node) {
        //结束条件
        if(node.right == null) {
            return node;
        }

        return max(node.right);
    }

    //删除最大元素所在的节点,并返回最大值
    public E removeMax() {
        E maxVal = max();
        root = removeMax(root);//修改root指向
        return maxVal;
    }

    //删除以node为根节点的二分搜索树的最大值所在节点，并返回新的二分搜索树的根
    private Node removeMax(Node node) {
        //参数校验
        if(node == null) {
            return null;
        }
        //结束条件
        if(node.right == null) {//找到最大值node
            node = node.left;//摘除元素
            size --;//注意维护size！！
            return node;
        }
        //函数逻辑主体
        node.right = removeMax(node.right);
        return node;
    }

    //删除最小值
    public E removeMin() {
        E minVal = min();
        root = removeMin(root);
        return minVal;
    }

    //递归函数：删除以node为根节点的二分搜索树中的最小值，并返回新的二分搜索树的根
    private Node removeMin(Node node) {
        //参数校验
        if(node == null) {
            return null;
        }
        //结束条件
        if(node.left == null) {//找到最小值node
            node = node.right;
            size --;
            return node;
        }
        //函数主体
        node.left = removeMin(node.left);
        return node;
    }

    //删除指定元素所在的节点
    public void remove(E e) {
        root = remove(root,e);
    }

    //递归函数：删除以node为根节点的二分搜索树中的指定元素，并返回新的二分搜索树的根
    private Node remove(Node node,E e) {
        if(node == null || e == null) {
            return null;
        }

        if(node.e.compareTo(e) == 0) {
            if (node.left == null && node.right == null) {//是叶子节点
                node = null;
                size--;
                return node;

            } else if (node.left != null && node.right == null) {//只有左子树
                node = node.left;
                size--;
                return node;

            } else if (node.left == null && node.right != null) {//只有右子树
                node = node.right;
                size--;
                return node;

            } else {//左右子树均不空--在右子树中找到最小值的节点N，使N取代e所在节点----此处使用后继取代e所在节点，当然，也可以使用前驱取代e所在节点。
                Node minNode = min(node.right);
                minNode.right = removeMin(node.right);//!!!!!这里隐含着 size --
                size ++;//抵消上面的size --
                minNode.left = node.left;
                node.left = null;
                node.right = null;

                size--;//删除node后，少了一个节点!!!
                return minNode;

            }
        }

        if(node.e.compareTo(e)>0) {//在左子树
            node.left = remove(node.left,e);
        } else if(node.e.compareTo(e)<0) {//在右子树
            node.right = remove(node.right,e);
        }

        return node;
    }

//    //递归方法：在以node为根节点的树中查找指定元素，并返回新的搜索树的根---根是不断变化的
//    public Node find(Node node,E e) {
//        //参数校验
//        if(e == null) {
//            throw new IllegalArgumentException("input element is null");
//        }
//        //递归结束条件
//        if(node == null) {
//            return null;
//        }
//
//        if(node.e.compareTo(e) == 0) {
//            return node;
//        } else if(node.e.compareTo(e)>0) {
//            node.left = find(node.left, e);
//        } else if(node.e.compareTo(e)<0) {
//            node.right = find(node.right, e);
//        }
//
//        return node;
//    }


    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        System.out.println(bst);
        System.out.println("size="+bst.getSize());
        System.out.println("isEmpty="+bst.isEmpty());

        bst.add(50);
        bst.add(30);
        bst.add(60);
        bst.add(80);
        bst.add(40);
        bst.add(70);
        bst.add(20);

        bst.add(55);
        bst.add(52);
        bst.add(57);
        bst.add(88);
        bst.add(86);
        bst.add(90);

//        bst.add(5);
//        bst.add(3);
//        bst.add(6);
//        bst.add(8);
//        bst.add(4);
//        bst.add(7);
//        bst.add(2);

//        bst.add(7);
//        bst.add(3);
//        bst.add(-2);
//        bst.add(4);
//        bst.add(-1);
//        bst.add(6);
//        bst.add(9);
//        bst.add(29);
//        bst.add(12);
        System.out.println(bst);
        System.out.println("size="+bst.getSize());
        System.out.println("isEmpty="+bst.isEmpty());

        System.out.println("contains="+bst.contains(-7));
        System.out.println("contains="+bst.contains(3));
        System.out.println("contains="+bst.contains(6));
//        System.out.println("contains="+bst.contains(null));

        //前序遍历
        System.out.print("前序遍历:");
        bst.preOrder();

        System.out.println();

        //中序遍历
        System.out.print("中序遍历:");
        bst.inOrder();

        System.out.println();

        //后序遍历
        System.out.print("后序遍历:");
        bst.postOrder();
        System.out.println();

        //层序遍历
        System.out.print("层序遍历:");
        bst.levelOrder();
        System.out.println();

        //最小值
        System.out.println("min="+bst.min());
        //最大值
        System.out.println("max="+bst.max());


        //删除最小值
        System.out.println("remove min="+bst.removeMin());
        System.out.print("after remove min, size=" +bst.getSize()+",bst=");
        bst.inOrder();
        System.out.println();

        //删除任意值
        int val = 60;
        System.out.println("remove val="+val);
        bst.remove(val);
        System.out.print("after remove val="+val+",size="+bst.getSize()+",bst=");
        bst.inOrder();
        System.out.println();

        //删除最大值
        System.out.println("remove max="+bst.removeMax());
        System.out.print("after remove max, size="+bst.getSize()+", bst=");
        bst.inOrder();

    }

}
