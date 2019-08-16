/**
 * ********************************  2-3树 ********************************
 * 1.有两种节点：2节点、3节点。2节点存储一个元素、有两个孩子；3节点存储两个元素、有三个孩子
 * 2.满足二分搜索树的基本性质，即：2节点的左子树的值<根节点的值<右子树的值；3节点的左子树的值<根节点的左边的值<中间子树的值<根节点的右边的值<右子树的值。（也可以依次大于）
 * 3.不是二叉树
 * 4.是绝对平衡的树，即：节点的左右子树的高度是相等的 ~~~~~
 * 5.2-3树可以转化成红黑树
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
 * 3.不会退化成链表
 * 4.最大高度为2logn（每一个节点都是2-3树上的3节点转化过来的），时间复杂度为O(logn)
 * 5.AVL树的最大高度为logn，它的查找性能是优于红黑树的2logn，但红黑树的统计性能更优（综合增删改查所有的操作）
 * 6.java.util中的TreeSet、TreeMap就是基于红黑树实现的
 *
 */
public class RBTree {

}
