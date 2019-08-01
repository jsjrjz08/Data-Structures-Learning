/**
 * Quick Find ----使用数组做底层存储，数组能快速随机读取数据
 * 并--O(n)
 * 查--O(1)
 * 优点：查快
 * 缺点：合并慢
 */
public class UnionFind1 implements UF {

    //各个元素对应的集合编号
    private int[] id;

    //构造函数
    public UnionFind1(int size) {
        id = new int[size];
        //初始化id
        for(int i=0; i<size; i++) {
            id[i] = i;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0;i<id.length; i++) {
            sb.append(id[i]);
            if(i != id.length-1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    //查找索引为p的元素所在集合的id编号
    // O(1)
    private int find(int p) {
        if(p<0 || p>=id.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return id[p];
    }

    @Override
    //O(n)
    public void unionElements(int p, int q) {
        if(p<0 || p>=id.length || q<0 || q>=id.length) {
            throw new IllegalArgumentException("index is illegal");
        }

        int idP = find(p);
        int idQ = find(q);

        //使用p的集合id作为并集的id
        if(idP == idQ) {
            return;
        } else {
            for(int i=0; i<id.length; i++) {
                if(id[i] == idQ) {
                    id[i] = idP;
                }
            }
        }

    }

    @Override
    // O(1)
    public boolean isConnected(int p, int q) {
        if(p<0 || p>=id.length || q<0 || q>=id.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return find(p) == find(q);
    }

    @Override
    public int getSize() {
        return id.length;
    }

    public static void main(String[] args) {
        UF uf = new UnionFind1(10);

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
