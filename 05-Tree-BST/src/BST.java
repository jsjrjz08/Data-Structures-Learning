
/**
 * 二分搜索树：是二叉树；节点上的元素要有可比性；左子树所有节点小于根节点、右子树所有节点大于根节点、没有重复元素；
 * 每一颗子树均为二分搜索树；除根节点外，所有节点都有且只有一个父节点；空节点也是一颗二分搜索树；二分搜索树不一定是一颗满二叉树
 * 查询效率高；创建二分搜索树耗时长
 * @param <E>
 */
public class BST<E extends Comparable<E>> {//元素可比较！Comparable
    //节点定义（对外屏蔽！private）
    private class Node<E extends Comparable<E>> {
        E e;
        Node left,right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
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

    //添加元素节点
    public Node add(E e) {
        if(root == null) {
            return new Node(e);
        }

        return addElement(root,e);
    }

    //递归方法:在二分搜索树上添加指定元素的节点，并返回根节点
    private Node addElement(Node node, E e) {
        if((node.e.compareTo(e) >0 && node.right != null)) {
            return addElement(node.right,e);
        } else if(node.e.compareTo(e) <0 && node.left != null) {
            return addElement(node.left,e);
        } else {
            return node;
        }
    }


}
