/**
 * Quick Union ----将每一个元素看作一个节点，由孩子指向父亲，形成一颗树，根节点指向自身。
 * 同一集合的元素有同一个根节点；不同的集合构成森林
 * 并--O(h) h为树的高度
 * 查--O(h)
 * 优点：合并速度比较快，不用修改连续区间
 * 缺点：find操作需要不断跳转内存地址；合并过程中没有考虑两棵树本身具有的特点，可能导致树过高，造成复杂度大，最坏情况是退化成链表
 *
 */
public class UnionFind2 implements UF {

    //各个元素对应的父亲节点索引
    private int[] parent;

    //构造函数
    public UnionFind2(int size) {
        parent = new int[size];
        //初始化parent
        for(int i=0; i<size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0;i<parent.length; i++) {
            sb.append(parent[i]);
            if(i != parent.length-1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    //查找索引为p的元素所在集合的根节点索引
    // O(h)
    private int find(int p) {
        if(p<0 || p>=parent.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        while(p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    @Override
    //O(h)
    public void unionElements(int p, int q) {
        if(p<0 || p>=parent.length || q<0 || q>=parent.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        int rootP = find(p);
        int rootQ = find(q);

        //使用p的集合id作为并集的父亲
        if(rootP == rootQ) {
            return ;
        } else {
            parent[rootQ] = rootP;
        }
    }

    @Override
    // O(h)
    public boolean isConnected(int p, int q) {
        if(p<0 || p>=parent.length || q<0 || q>=parent.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return find(p) == find(q);
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    public static void main(String[] args) {
        UF uf = new UnionFind2(10);

        System.out.println(uf.getSize());
        System.out.println(uf.isConnected(2,6));
        uf.unionElements(2,6);
        System.out.println(uf.isConnected(2,6));
        System.out.println(uf);
        uf.unionElements(2,7);
        System.out.println(uf);
        uf.unionElements(1,6);
        System.out.println(uf);
    }

}
