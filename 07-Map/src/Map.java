public interface Map<K,V> {
    int getSize();
    boolean isEmpty();
    boolean contains(K key);
    V get(K key);
    void set(K key,V value);
    void add(K key,V value);
    V remove(K key);
}
