/**
 * Map不存储key重复的键值对
 * @param <K>
 * @param <V>
 */
public interface Map<K,V> {
    int getSize();
    boolean isEmpty();

    //当key存在时，返回true；当key不存在时，返回false
    boolean contains(K key);

    //当key存在时，返回value；当key不存在时，返回null
    V get(K key);

    //当key存在时，更新value；当key不存在时，抛异常
    void set(K key,V value);

    //当key存在时，更新value；当key不存在时，添加键值对
    void add(K key,V value);

    //当key存在时，删除key，并返回value；当key不存在时，返回null
    V remove(K key);
}
