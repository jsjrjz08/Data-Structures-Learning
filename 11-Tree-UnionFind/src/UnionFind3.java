/**
 * 基于size的优化
 * Quick Union ----将每一个元素看作一个节点，由孩子指向父亲，形成一颗树，根节点指向自身。
 * 同一集合的元素有同一个根节点；不同的集合构成森林
 * 并--O(h) h为树的高度
 * 查--O(h)
 * 优点：不用修改连续区间；基于节点个数进行合并优化：合并过程中考虑两棵树本身具有的特点，将节点少的树的根节点，指向节点多的数据的根节点，尽量保持树的高度变化不大，避免退化成链表
 * 缺点：find操作需要不断跳转内存地址；时间复杂度是与树的高度相关的，节点个数少不一定保证树的高度小,也就是说，即使把节点数少的树挂到节点数多的节点上，也不能保证树的高度尽量小
 *
 */
public class UnionFind3 implements UF {

    //各个元素对应的父亲节点索引
    private int[] parent;

    //sz[i]:以i为根节点的集合中元素的个数
    private int[] sz;

    //构造函数
    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];

        //初始化parent
        for(int i=0; i<size; i++) {
            parent[i] = i;
            sz[i] = 1;
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
        sb.append("],[");
        for(int i=0;i<sz.length; i++) {
            sb.append(sz[i]);
            if(i != sz.length-1) {
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

        if(rootP == rootQ) {
            return ;
        } else {
            //使用p的集合id作为并集的父亲节点
            if(sz[rootP] >= sz[rootQ]) {
                parent[rootQ] = rootP;
                sz[rootP] += sz[rootQ];

            } else {//sz[rootP] < sz[rootQ]:使用q的集合id作为并集的父亲节点
                parent[rootP] = rootQ;
                sz[rootQ] += sz[rootP];
            }
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
        UF uf = new UnionFind3(10);

        System.out.println(uf.getSize());
        System.out.println(uf.isConnected(2,6));
        uf.unionElements(2,6);
        System.out.println(uf.isConnected(2,6));
        System.out.println(uf);
        uf.unionElements(2,7);
        System.out.println(uf);
        uf.unionElements(1,6);
        System.out.println(uf);
        uf.unionElements(8,6);
        System.out.println(uf);

        System.out.println(uf.isConnected(1,8));
        System.out.println(uf);
    }

}
