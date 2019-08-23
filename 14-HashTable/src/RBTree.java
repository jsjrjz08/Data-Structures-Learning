import java.util.ArrayList;

/**
 * ******************************** 2-3树 ********************************
 * 1.有两种节点：2节点、3节点。2节点存储一个元素、有两个孩子；3节点存储两个元素、有三个孩子
 * 2.满足二分搜索树的基本性质，即：2节点的左子树的值<根节点的值<右子树的值；3节点的左子树的值<根节点的左边的值<中间子树的值<根节点的右边的值<右子树的值。（也可以依次大于）
 * 3.不是二叉树
 * 4.是绝对平衡的树，即：节点的左右子树的高度是相等的 ~~~~~
 * 5.2-3树可以转化成红黑树
 * 6.2-3树是向上生长的，当2-3的根节点由3树生长为（红黑树中的旋转）2节点，树的高度增加
 *
 * ******************************** 2-3树 转化成 红黑树 **************************************
 * a.空节点是黑节点
 * b.2节点是黑节点
 * c.3节点：假设3节点由左右两个元素A,B组成：
 *      A作为红黑树的红色节点，A的左孩子是原来3节点的左孩子，A的右孩子是原来3节点的中间孩子；
 *      A的父亲节点是原来3节点的B；
 *      B作为红黑树的黑色节点，B的左孩子是A，B的右孩子是原来3节点的右孩子
 * d.红色节点向左倾斜 --左倾红黑树 ~~~~~~~标准红黑树，此处实现的就是这种红黑树
 * e.红色节点向右倾斜 --右倾红黑树
 *
 * ********************************* 红黑树 *************************************
 *
 * 五点性质：
 * 1.每个节点要么是红色的，要么是黑色的
 * 2.根节点是黑色的
 * 3.每一个叶子节点（最后的空节点）是黑色的 --> 空节点是黑色的
 * 4.如果一个节点是红色的，那么它的孩子节点都是黑色的
 * 5.从任一节点到达叶子节点，所经过的黑节点个数是一样多的
 *
 * 其他：
 * 1.是一颗二分搜索树
 * 2.是一颗“黑平衡”的二叉树--黑节点绝对平衡，不是严格意义上的绝对平衡
 * 3.不会退化成链表，自平衡
 * 4.最大高度为2logn（每一个节点都是2-3树上的3节点转化过来的），时间复杂度为O(logn)
 * 5.AVL树的最大高度为logn，它的查找性能是优于红黑树的2logn，但红黑树的统计性能更优（综合增删改查所有的操作）
 * 6.java.util中的TreeSet、TreeMap就是基于红黑树实现的
 *
 */
public class RBTree<K extends Comparable<K>,V> implements Map<K,V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left,right;
        public boolean color;

        public Node(K key,V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED;
        }

        @Override
        public String toString() {
            return "[key:value="+key+":"+value+",color="+color+",left="+left+",right="+right+"]";
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(K key) {
        return findNode(root,key) != null;
    }

    //递归函数：在 以node为根节点的红黑树中查找键为key的节点
    private Node findNode(Node node,K key) {
        if(node == null) {
            return null;
        }
        if(key.compareTo(node.key) < 0) {
            return findNode(node.left,key);
        } else if(key.compareTo(node.key) > 0) {
            return findNode(node.right,key);
        } else {
            return node;
        }
    }

    @Override
    public V get(K key) {
        Node node = findNode(root,key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = findNode(root,key);
        if(node != null) {
            node.value = value;
        }
    }

    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
        //红黑树的根节点为黑色
        root.color = BLACK;
    }

    //递归函数：在以node为根节点的红黑树中添加一个节点，并返回新的红黑树的根节点
    private Node add(Node node,K key,V value) {
        if(node == null) {
            size ++;
            return new Node(key,value);//红色
        }

        if(key.compareTo(node.key) < 0) {
            node.left = add(node.left,key,value);
        } else if(key.compareTo(node.key) > 0) {
            node.right = add(node.right,key,value);
        } else {
            node.value = value;
        }

        //维护颜色 依次执行左旋转->右旋转->颜色翻转
        //1.左旋转
        if(!isRed(node.left) && isRed(node.right)) {
            node = leftRotate(node);
        }

        //2.右旋转
        if(isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        //3.颜色翻转
        if(isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    //判断给定节点是否为红色
    private boolean isRed(Node node) {
        if(node == null) {//空节点是黑色的
            return false;
        }
        return node.color == RED;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    //左旋转：对以node为根的红黑树进行左旋转，并返回新的红黑树的根 --------------- 2节点变3节点+3节点->临时4节点->红黑节点
    private Node leftRotate(Node node) {
        Node x = node.right;

        //旋转
        node.right = x.left;
        x.left = node;

        //维护颜色 ---此时，可能造成两个父子相连的节点都是红色，因为这是中间过程，可以存在那种不合理情况
        x.color = node.color;
        node.color = RED;//与x是融合的节点

        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->    T3 node
    //  / \                       /  \
    // T3  T1                     T1  T2
    //右旋转：对以node为根的红黑树进行右旋转，并返回新的红黑树的根 ---------------3节点->临时4节点->红黑节点
    private Node rightRotate(Node node){
        Node x = node.left;

        //旋转
        node.left = x.right;
        x.right = node;

        //维护颜色 ---此时，可能造成两个父子相连的节点都是红色，因为这是中间过程，可以存在那种不合理情况
        x.color = node.color;
        node.color = RED;//与x是融合的节点

        return x;
    }

    // 颜色翻转
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //删除节点，此处没有维护红黑属性，因为过于复杂！
    @Override
    public V remove(K key) {
        Node node = findNode(root,key);
        root = remove(root,key);
        return node == null ? null : node.value;
    }

    //递归函数：在以node为根节点的树中删除以key为键的节点，并返回新的树的根节点
    private Node remove(Node node,K key) {
        if(node == null) {
            return null;
        }

        if(key.compareTo(node.key) < 0) {
            node.left = remove(node.left,key);
        } else if(key.compareTo(node.key) > 0) {
            node.right = remove(node.right,key);
        } else {//node就是要被删除的节点
            if(node.left == null) {
                size --;
                node = node.right;
            } else if(node.right == null) {
                size --;
                node = node.left;
            } else {//node的左右孩子都不为null
                Node maxNode = max(node.right);//maxNode肯定不为 null
                maxNode.left = node.left;
                maxNode.right = remove(node.right,maxNode.key);//删除右子树中的最大值，size --
                node = maxNode;
            }
        }

        return node;
    }

    //递归函数：在以node为根节点的树中找到最大值节点
    private Node max(Node node) {
        if(node == null) {
            return null;
        }
        if(node.right == null) {
            return node;
        }
        return max(node.right);
    }

    @Override
    public String toString() {
        return "size="+size+",root="+root;
    }

    //判断一棵红黑树是否是BST
    public boolean isBST() {
        ArrayList<K> arrList = new ArrayList<>();
        inOrder(root,arrList);
        for(int i=1;i<arrList.size();i++) {
            if(arrList.get(i-1).compareTo(arrList.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    //递归函数：中序遍历一棵树
    private void inOrder(Node node, ArrayList<K> arrList) {
        if(node == null) {
            return;
        }

        inOrder(node.left,arrList);
        arrList.add(node.key);
        inOrder(node.right,arrList);
    }

    public static void main(String[] args) {
        RBTree<Integer,Integer> rbt = new RBTree<>();

        System.out.println(rbt);
        System.out.println(rbt.isEmpty());
        System.out.println(rbt.getSize());
        System.out.println(rbt.contains(3));
        System.out.println(rbt.contains(9));
        rbt.add(4,4);
        rbt.add(3,3);
        rbt.add(2,2);
        rbt.add(1,1);
        rbt.add(0,0);
        rbt.add(5,5);
        rbt.add(6,6);
        rbt.add(7,7);
//        rbt.add(5,5);
//        rbt.add(7,7);
//        rbt.add(3,3);
//        rbt.add(8,8);
//        rbt.add(6,6);
//        rbt.add(4,4);
        System.out.println(rbt);
        System.out.println(rbt.isEmpty());
        System.out.println(rbt.getSize());
        System.out.println(rbt.contains(3));
        System.out.println(rbt.contains(9));

        System.out.println(rbt.get(4));
        rbt.set(3,9);
        System.out.println(rbt);
        rbt.add(3,19);
        System.out.println(rbt);

        //删除
        System.out.println("删除 ： "+rbt.remove(4));
        System.out.println(rbt);
        System.out.println("isBST="+rbt.isBST());

    }
}
