/**
 * 集合Set
 * 此处约定：集合中没有重复元素；元素无序
 * 事实上，集合分为有序集合、无序集合，也可以存在重复元素，叫做多重集合
 * @param <E>
 */
public interface Set<E> {
    int getSize();
    boolean isEmpty();
    boolean contains(E e);
    void add(E e);
    E remove();
}
