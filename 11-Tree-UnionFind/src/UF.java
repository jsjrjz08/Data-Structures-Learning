/**
 * 应用：求集合的并集；连接问题；路径问题
 * 不考虑添加和删除元素
 */
public interface UF {
    //合并元素p,q所属的集合 （ p,q为元素索引）
    void unionElements(int p,int q);

    //查看元素p,q是否属于同一个集合（ p,q为元素索引）
    boolean isConnected(int p,int q);

    //元素个数
    int getSize();
}
