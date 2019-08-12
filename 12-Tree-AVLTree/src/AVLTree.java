import java.util.ArrayList;

/**
 * AVL树
 * 1.是一颗平衡二叉树 ： 对于任意节点，它的左右子树高度差不能超过 1
 * 2.高度和节点数量之间的关系 O(logn)
 * 3.节点的高度 ：空节点的高度为0；叶子节点的高度为 1；其余节点的高度为左右子树中最高的高度加 1
 * 4.平衡因子 = 左子树的高度 - 右子树的高度 ：叶子节点的平衡因子为0；其余节点的平衡因子为左右孩子的高度差
 * 插入和删除操作都会导致AVL树的自我调整（自我平衡），使得所有结点的平衡因子保持为+1、-1或0
 *  a.当子树的根结点的平衡因子为+1时，它是左倾斜的（left-heavy)
 *  b.当子树的根结点的平衡因子为-1时，它是右倾斜的(right-heavy)
 *  c.一颗子树的根结点的平衡因子就代表该子树的平衡性；
 *   保持所有子树几乎都处于平衡状态，AVL树在总体上就能够基本保持平衡
 *
 * 在插入一个结点后应该沿搜索路径将路径上的节点计算平衡因子，当平衡因子的绝对值大于1时，就需要进行平衡化处理。
 * 从发生不平衡的节点起，沿刚才回溯的路径取直接下两层节点：
 *  a.如果这三个节点在一条直线上，则采用单旋转进行平衡化--LL/RR ；
 *  b.如果这三个节点位于一条折线上，则采用双旋转进行平衡化--LR/RL
 *
 * 解决二分搜索树BST可能退化成链表造成性能下降的问题 --将不平衡的节点进行 左旋转+右旋转
 * AVL树的读取、删除操作时间复杂度 O(logn)
 *
 */
public class AVLTree<K extends Comparable<K>, V> implements Map<K,V> {
    private class Node {
        public K key;
        public V value;
        public int height;//新增属性
        public Node left;
        public Node right;

        public Node(K key,V value,Node left,Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = 1;
        }

        public Node(K key,V value) {
            this(key,value,null,null);
        }

        @Override
        public String toString() {
            return "[key:value="+key+":"+value+",height="+height+",left="+left+",right="+right+"]";
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        return "size="+size+",root="+root;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //查找指定节点的高度
    private int getHeight(Node node) {
        if(node == null) {
            return 0;
        }
        return node.height;
    }

    //计算指定节点的平衡因子 ---可能为负数哦
    public int getBalanceFactor(Node node) {
        if(node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    //判断一棵树是否具有平衡性
    public boolean isBalanced() {
        return isBalanced(root);
    }

    //递归函数：判断以node为根的树是否平衡
    private boolean isBalanced(Node node) {
        if(node == null) {
            return true;
        }

        if(Math.abs(getBalanceFactor(node)) > 1) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }

    //判断一棵树是否是BST
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

    //右旋转 ：y为不平衡节点y所在的最小子树的根节点，对以y为根节点的树进行右旋转，并返回新的树的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        x.right = y;
        y.left = T3;

        //更新height
        y.height = 1 + Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));

        return x;
    }

    //左旋转 ：y为不平衡节点y所在的最小子树的根节点，对以y为根节点的树进行左旋转，并返回新的树的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        //更新height
        y.height = 1 + Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));

        return x;
    }


    //递归函数：在以node为根节点的树中查找key对应的节点信息，并将节点信息返回
    private Node findNode(Node node,K key) {
        //结束条件
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
    public boolean contains(K key) {
        return findNode(root,key) == null ? false : true;
    }

    @Override
    public V get(K key) {
        Node node = findNode(root,key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = findNode(root,key);
        if(node == null) {
            throw new IllegalArgumentException("Key " + key + " does not exist.");
        }
        node.value = value;
    }

    @Override
    public void add(K key, V value) {
        root = addNode(root,key,value);
    }

    //递归函数：在以node为根节点的树中添加节点（key,value），并返回新的根
    private Node addNode(Node node,K key, V value) {
        if(node == null) {
            size ++;
            return new Node(key,value);
        }

        if(key.compareTo(node.key) < 0) {
            node.left = addNode(node.left,key,value);
        } else if(key.compareTo(node.key) > 0) {
            node.right = addNode(node.right,key,value);
        } else {
            node.value = value;
        }
        //维护height
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        //计算平衡因子 左 - 右
        int balanceFactor = getBalanceFactor(node);
//        if(Math.abs(balanceFactor) > 1) {
//            System.out.println("noBalanced:"+balanceFactor);
//        }

        //LL
        //左子树不平衡 且 新加节点在左子树的左侧（左子树的左子树高度 大于或等于 左子树的右子树高度）
        //右旋转
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            //右旋转
            node = rightRotate(node);
        }

        //RR
        //右子树不平衡 且 新加节点在右子树的右侧（右子树的左子树高度 小于或等于 右子树的右子树高度）
        //左旋转
        else if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            //左旋转
            node = leftRotate(node);
        }

        //LR
        //左子树不平衡 且 新加节点在左子树的右侧（左子树的左子树高度 小于 左子树的右子树高度）
        //先左旋转，再右旋转
        else if(balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            //node向下两层的节点进行左旋转，转化成LL问题
            node.left = leftRotate(node.left);
            //右旋转
            node = rightRotate(node);
        }

        //RL
        //右子树不平衡 且 新加节点在右子树的左侧（右子树的左子树高度 大于 右子树的右子树高度）
        //先右旋转，再左旋转
        else if(balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            //node向下两层的节点进行右旋转，转化成RR问题
            node.right = rightRotate(node.right);
            //左旋转
            node = leftRotate(node);
        }

        return node;
    }

    @Override
    public V remove(K key) {
        Node node = findNode(root,key);
        root = removeNode(root,key);
        return node == null ? null : node.value;
    }

    //递归函数：在以node为根节点的树中删除键为key的节点，并返回新的树的根节点
    private Node removeNode(Node node,K key) {
        if(node == null) {
            return null;
        }

        Node retNode = node;
        if(key.compareTo(retNode.key) < 0) {
            retNode.left = removeNode(retNode.left,key);

        } else if(key.compareTo(retNode.key) > 0) {
            retNode.right = removeNode(retNode.right,key);

        } else {
            //1.retNode左孩子为空
            if(retNode.left == null) {
                size --;
                retNode = retNode.right;

            } else if(retNode.right == null) {//2.retNode右孩子为空
                size --;
                retNode = retNode.left;

            } else {//3.retNode左右孩子均不为空 if(retNode.left != null && retNode.right != null)
                //使用最大key值替换被删除的元素
                Node maxNode = maxNode(retNode.right);//注意：此时找到的newNode还是node.right中的一个节点！！！
                maxNode.left = retNode.left;
                //删除最大key值所在节点，得到一颗新树
//                newNode.right = removeMax(retNode.right);
                maxNode.right = removeNode(retNode.right,maxNode.key);
                retNode = maxNode;

            }
        }

        if(retNode == null) {
            return null;
        }

        //维护height
        retNode.height = 1 + Math.max(getHeight(retNode.left),getHeight(retNode.right));
        int balanceFactor = getBalanceFactor(retNode);

        //维护平衡性
        //LL
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            retNode = rightRotate(retNode);
        }

        //RR
        else if(balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            retNode = leftRotate(retNode);
        }

        //LR
        else if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            retNode = rightRotate(retNode);
        }

        //RL
        else if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            retNode = leftRotate(retNode);
        }

        return retNode;
    }

    //递归函数：在以node为根节点的树中查找最大key所在的节点，并将其返回
    private Node maxNode(Node node) {
        //空树或只有一个节点的树
        if(node == null || node.right == null) {
            return node;
        }
        return maxNode(node.right);
    }

    //递归函数：在以node为根节点的树中删除最大key所在的节点，并返回新的树的根节点
//    private Node removeMax(Node node) {
//        if(node == null) {
//            return null;
//        }
//
//        if(node.right == null) {
//            node = node.left;
//            size --;
//        }
//        node.right = removeMax(node.right);
//        return node;
//    }

    public static void main(String[] args) {
        AVLTree<Integer,Integer> avl = new AVLTree<>();

        System.out.println(avl);
        System.out.println(avl.isEmpty());
        System.out.println(avl.getSize());
        System.out.println(avl.contains(3));
        System.out.println(avl.contains(9));
        avl.add(4,4);
        avl.add(3,3);
        avl.add(2,2);
        avl.add(1,1);
        avl.add(0,0);
        avl.add(5,5);
        avl.add(6,6);
        avl.add(7,7);
//        avl.add(5,5);
//        avl.add(7,7);
//        avl.add(3,3);
//        avl.add(8,8);
//        avl.add(6,6);
//        avl.add(4,4);
        System.out.println(avl);
        System.out.println(avl.isEmpty());
        System.out.println(avl.getSize());
        System.out.println(avl.contains(3));
        System.out.println(avl.contains(9));

        System.out.println(avl.get(4));
        avl.set(3,9);
        System.out.println(avl);
        avl.add(3,19);
        System.out.println(avl);

        //删除
        System.out.println("删除 ： "+avl.remove(4));
        System.out.println(avl);
        System.out.println("isBalanced="+avl.isBalanced());
        System.out.println("isBST="+avl.isBST());

    }
}
