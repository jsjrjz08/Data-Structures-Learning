import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树：动态数据结构；非线性数据结构；是二叉树(每个节点最多有两个孩子)；节点上的元素要有可比性；
 * 每个节点上的值都小于其左子树的所有非空节点、都大于右子树的所有非空节点、没有重复元素；
 * 每一颗子树均为二分搜索树；除根节点外，所有节点都有且只有一个父节点；空节点也是一颗二分搜索树；二分搜索树不一定是一颗满二叉树
 * 查询效率高；创建二分搜索树耗时长；元素可比较
 *
 * 深度优先遍历-前序遍历： 根节点 - 左孩子 - 右孩子
 * 深度优先遍历-中序遍历： 左孩子  - 根结点 - 右孩子
 * 深度优先遍历-后序遍历： 左孩子 - 右孩子 - 根结点
 *
 * 广度优先遍历-层序遍历：
 * @param <E>
 */
public class BST00<E extends Comparable<E>> {//元素可比较！Comparable
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
    public BST00() {
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

        return childString(root);
    }

    private String childString(Node node) {
        //结束条件
        if(node == null) {
            return null;
        }

        //递归
        StringBuilder sb = new StringBuilder();
        sb.append("root["+node.e+"],");
        sb.append("left["+childString(node.left)+"],");
        sb.append("right["+childString(node.right)+"].");

        return sb.toString();
    }

    //添加元素节点
    public void add(E e) {
        if(root == null) {
            root = new Node(e);
            size ++;
        } else {//调用递归方法，向子树添加节点
            add(root,e);
        }
    }

    //递归:向以node为根节点的二分搜索树添加新元素e
    private void add(Node node, E e) {
        //结束条件--重复的条件判断--递归不彻底
        if(node.e.compareTo(e) == 0) {
            return;//不做任何操作
        } else if(node.e.compareTo(e) >0 && node.left == null) {
            node.left = new Node(e);
            size ++;
            return;
        } else if(node.e.compareTo(e) <0 && node.right == null) {
            node.right = new Node(e);
            size ++;
            return;
        }

        //递归调用--重复的条件判断--递归不彻底
        if(node.e.compareTo(e) >0) {//新元素小于根节点、但左子树不为null
            add(node.left,e);
        } else {//新元素大于根节点、但右子树不为null[等于根节点在结束条件已经判断]
            add(node.right,e);
        }
    }

    //前序遍历 - 非递归（借助栈）
    public void preOrder() {

        if(root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            Node cur = stack.pop();
            //当前节点
//            if(cur != null) {//null不会被压入栈，所以，弹出的元素不会为null
                System.out.print(cur+",");
//            }
            //由于栈是先入后出的，所以，前序遍历的时候，先放入右孩子，后放入左孩子
            //右
            if(cur.right != null) {
                stack.push(cur.right);
            }

            //左
            if(cur.left != null) {
                stack.push(cur.left);
            }
        }

    }

    //中序遍历 - 非递归（借助栈）
    public void inOrder() {

        Stack<Node> stack = new Stack<>();
        Node cur = root;

        while(cur != null || !stack.isEmpty()) {
            if(cur != null) {//当前节点不为null，就入栈
                stack.push(cur);
                cur = cur.left;//修改当前节点的指向

            } else {//否则，就出栈：访问该节点->检查右孩子
               cur = stack.pop();//修改当前节点的指向
               System.out.print(cur+",");
               cur = cur.right;//修改当前节点的指向
            }
        }
    }

    //后序遍历 - 非递归（借助栈）
    public void postOrder() {
        if(root == null) {
            return;
        }
        Stack<Node> stack01 = new Stack<>();
        Stack<Node> stack02 = new Stack<>();//存放后序遍历的倒序
        Node cur = null;
        stack01.push(root);

        while(! stack01.isEmpty()) {
            cur = stack01.pop();
            stack02.push(cur);
            //左
            if(cur.left != null) {
                stack01.push(cur.left);
            }

            //右
            if(cur.right != null) {
                stack01.push(cur.right);
            }
        }

        while(!stack02.isEmpty()) {
            System.out.print(stack02.pop()+",");
        }
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

    public static void main(String[] args) {
        BST00<Integer> bst = new BST00<>();
        System.out.println(bst);
        System.out.println("size="+bst.getSize());
        System.out.println("isEmpty="+bst.isEmpty());

        bst.add(5);
        bst.add(3);
        bst.add(6);
        bst.add(8);
        bst.add(4);
        bst.add(7);
        bst.add(2);

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
    }

}
