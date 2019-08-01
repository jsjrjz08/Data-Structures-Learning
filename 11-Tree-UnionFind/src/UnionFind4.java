/**
 * 基于rank的优化
 * Quick Union ----将每一个元素看作一个节点，由孩子指向父亲，形成一颗树，根节点指向自身。
 * 同一集合的元素有同一个根节点；不同的集合构成森林
 * 并--O(h) h为树的高度
 * 查--O(h)
 * 优点：不用修改连续区间；基于rank/深度/高度/层数 进行合并优化：合并过程中考虑两棵树本身具有的特点，将节点少的树的根节点，指向节点多的数据的根节点，尽量保持树的高度变化不大，避免退化成链表
 * 缺点：find操作需要不断跳转内存地址；随着节点数的增多，树的高度还是在不断增长的，O(h)性能可能越来越差
 *
 */
public class UnionFind4 implements UF {

    //各个元素对应的父亲节点索引
    private int[] parent;

    //rank[i]:以i为根节点的集合中对应的树的高度
    private int[] rank;

    //构造函数
    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];

        //初始化parent
        for(int i=0; i<size; i++) {
            parent[i] = i;
            rank[i] = 1;
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
        for(int i=0;i<rank.length; i++) {
            sb.append(rank[i]);
            if(i != rank.length-1) {
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
            if(rank[rootP] >= rank[rootQ]) {
                parent[rootQ] = rootP;
                if(rank[rootP] == rank[rootQ]) {
                    rank[rootP] += 1;
                }

            } else {//rank[rootP] < rank[rootQ]:使用q的集合id作为并集的父亲节点
                parent[rootP] = rootQ;
                //rank[rootQ] 不变
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
        UF uf = new UnionFind4(10);

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
