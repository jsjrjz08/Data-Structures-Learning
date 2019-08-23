import java.util.TreeMap;

/**
 * 哈希表
 *
 * 一.哈希表特点
 *  以空间换时间。对于n的数据规模，当只有一个空间时，时间复杂度为O(n)；当有n个空间时，时间复杂度为O(1)
 *  第一步在动态数组中查找元素是O(1)；--数组随机读取
 *  第二步在TreeMap中查找元素均摊复杂度是O(1) --均摊时间复杂度(耗时的操作不是每次都发生，可以将一次耗时的操作平摊到每一次基本操作上)
 *       ||原因
 *      \_/
 *   动态数组的容量M会随着数据规模N的变化而发生相当的变化 resize，此时，平均每个数组中冲突的元素个数 N/M 在[lowerTol,upperTol)范围内，
 *   相应的每个数组内查找元素的平均时间复杂度为TreeMap O(log(N/M))=[O(log lowerTol),O(log upperTol))、链表 O(N/M)=[O(lowTol),O(upperTol)),
 *   无论是TreeMap还是链表，它的时间复杂度都是一个与数据规模无关的常数，因此，哈希表的均摊时间复杂度为O(1)
 *   如果添加n个元素，要进行n次add操作，当n达到upperTol*M规模的时候发生扩容，此时已经完成了n次add操作，resize内又有n次add操作，因此,
 *   添加这n个元素的均摊复杂度是O(2n/n)=O(2)=O(1)
 *
 * 二.哈希函数
 * 1.哈希函数的作用：将键映射成一个整型的索引。
 *  映射关系有一对一、多对一。其中，多对一会发生哈希冲突，即：不同的键对应相同的索引
 * 2.哈希函数设计原则：
 *  a.一致性：如果a==b，则hash(a)==hash(b)
 *  b.高效性：计算高效简便
 *  c.均匀性：索引均匀分布
 * 3.一般地，
 *   >B表示进制；
 *   >M表示哈希表的容量，一般取一个素数；
 *   >hash函数与hashCode函数的关系：hash结果是hashCode经过取模M计算后的结果；hashCode计算结果是整型，hash结果是HashTable中的数组索引
 *   >由于索引值是非负的，并且M是正整数，所以，如果hashCode的值是负数，应该先把它转化成非负的，因此，就有hashCode & 0x7fffffff （31位1，是整型的数值位，最高位为符号位） 的操作
 *  hash("code")=(hashCode("code") & 0x7fffffff) % M =( (hashCode("c")*B^3+hashCode("o")*B^2+hashCode("d")*B+hashCode("e")) & 0x7fffffff)% M  ----当B很大时，B^3,B^2计算可能很慢，也可能发生整型溢出
 *  简化 B^x计算：
 *  hash("code")=(hashCode("code") & 0x7fffffff) % M =( (((hashCode("c")*B+hashCode("o"))*B+hashCode("d"))*B+hashCode("e")) & 0x7fffffff)% M  ----加和这一步，还是可能发生整型溢出
 *  提前进行模计算：
 *  hash("code")=(hashCode("code") & 0x7fffffff) % M =( (((hashCode("c")%M*B+hashCode("o"))%M*B+hashCode("d"))%M*B+hashCode("e")) & 0x7fffffff)%M
 *
 * 三.哈希冲突 --不同的键对应同一个索引
 *  1.链地址法 Separate Chaining:
 *   a.索引使用数组存储 --随机读取，快速定位 O(1)；
 *   b.每个索引中的冲突值使用链表或树结构存储，形成查找表 --查找性能高的数据结构：链表O(N/M)、树O(log N/M)
 *  2.其他解决哈希冲突的方法：
 *   开放地址法--使用动态数组实现，通过哈希函数计算获得索引，如果在该索引已经有元素了，就按照一定方法（线性探测、平方探测）依次向后寻找空余位置存放
 *   再哈希法--2个哈希函数
 *
 * 四.其他
 * 1.java8中的HashTable：当冲突的数量小于某个程度时，冲突的值使用链表存储；当冲突的数量大于这个程度且存储的元素是可以比较的时，冲突的值转而使用TreeMap（红黑树）存储，如果元素不可比较，就不会转为TreeMap。
 * 2.java中，Object有hashCode和equals的默认实现，都是通过内存地址计算的。
 *  如果自定义的类没有重写hashCode和equals，会继承Object中的实现，造成与业务不相符的结果。因此，如果使用哈希表存储自定义对象，该自定义对象一定要重写hashCode和equals方法！
 *
 */
public class HashTable<K,V> implements Map<K,V> {//此处可不必实现这个接口。

    //[java整型范围]内的素数
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private static final int upperTol = 10;//哈希冲突最大值
    private static final int lowerTol = 2;//哈希冲突最小值

    private TreeMap<K,V>[] maps;//map型数组，存储元素
    private int M;//哈希表的容量，一般取素数
    private int size;//哈希表中元素个数
    private int capacityIndex = 0;//初始值为0

    //构造函数
    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        maps = new TreeMap[M];//初始化数组
        for(int i=0;i<M;i++) {
            maps[i] = new TreeMap<>();//初始化数组中的每一个TreeMap
        }
    }

    //数组容量变化
    //为了保持均匀分布，newM应选取合适的素数
    //O(N/M *M)=O(N)
    private void resize(int newM) {
        TreeMap<K,V>[] newMaps = new TreeMap[newM];
        for(int i=0;i<newM;i++) {
            newMaps[i] = new TreeMap<>();//初始化数组中的每一个TreeMap
        }

        int oldM = M;
        this.M = newM;

        //maps中的元素 重新分布到 newMaps中
        for(int i=0; i<oldM; i++) {//此处应该使用原来的M
            TreeMap<K,V> map = maps[i];
            for(K key : map.keySet()) {
                newMaps[hash(key)].put(key,map.get(key));//此处的hash() 内部使用到M,此时，应该使用newM
            }
        }

        //size 没有发生变化
        this.maps = newMaps;
//        System.out.println("容量变化[" + oldM + "->" + this.M + "],size="+size);
    }

    //哈希函数 : 将键转换成索引
    private int hash(K key) {
        //获得key的hashCode并去掉符号位，然后对M取余，得到索引
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public void add(K key, V value) {
        TreeMap<K,V> map = maps[hash(key)];//O(1)
        if(!map.containsKey(key)) {//O(logn)
            map.put(key,value);
            size ++;
            //扩容？
            if(size >= upperTol * M && (capacityIndex + 1) < capacity.length) {//平均每个TreeMap中的元素达到upperTol个，进行扩容
                resize(capacity[++ capacityIndex]);
            }

        } else {
            map.put(key, value);
        }
    }

    @Override
    public V remove(K key) {
        TreeMap<K,V> map = maps[hash(key)];
        V ret = null;
        if(map.containsKey(key)) {
            ret = map.remove(key);
            size --;
            //缩容？
            if(size < lowerTol * M && (capacityIndex - 1) >= 0) {//平均每个TreeMap中的元素不足lowerTol个，进行缩容
                resize(capacity[-- capacityIndex]);
            }
        }
        return ret;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(K key) {
        return maps[hash(key)].containsKey(key);
    }

    @Override
    public V get(K key) {
        TreeMap<K,V> map = maps[hash(key)];
        if(!map.containsKey(key)) {
            return null;
        }
        return map.get(key);
    }

    @Override
    public void set(K key, V value) {
        TreeMap<K,V> map = maps[hash(key)];
        if(!map.containsKey(key)) {
            throw new IllegalArgumentException(key+" doesn't exists.");
        }
        map.put(key,value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{M="+M+",size="+size+",data=");
        for(int i=0;i<M;i++) {
            TreeMap<K,V> map = maps[i];
            sb.append("("+i+")=[");
            int j=0;
            for(K key:map.keySet()) {
                sb.append(key+":"+map.get(key));
                if(j < (map.keySet().size()-1)) {
                    sb.append(",");
                }
                j++;
            }
            sb.append("]");
            if(i<M-1) {
               sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        HashTable<Integer, Integer> ht = new HashTable<>();

        System.out.println(ht);
        System.out.println(ht.isEmpty());
        System.out.println(ht.getSize());
        System.out.println(ht.contains(3));
        System.out.println(ht.contains(9));
        ht.add(4, 4);
        ht.add(3, 3);
        ht.add(2, 2);
        ht.add(1, 1);
        ht.add(0, 0);
        ht.add(5, 5);
        ht.add(6, 6);
        ht.add(7, 7);

        System.out.println(ht);
        System.out.println(ht.isEmpty());
        System.out.println(ht.getSize());
        System.out.println(ht.contains(3));
        System.out.println(ht.contains(9));

        System.out.println(ht.get(4));
        ht.set(3, 9);
        System.out.println(ht);
        ht.add(3, 19);
        System.out.println(ht);

        //删除
        System.out.println("删除 ： " + ht.remove(4));
        System.out.println(ht);

        System.out.println("======================");

        int opCnt = 200000;
        HashTable<Integer,Integer> hb2 = new HashTable<>();
        for(int i=0;i<opCnt;i++) {
            hb2.add(i,i);
        }
        for(int i=0;i<opCnt;i++) {
            hb2.remove(i);
        }
    }
//    容量变化[53->97],size=530
//    容量变化[97->193],size=970
//    容量变化[193->389],size=1930
//    容量变化[389->769],size=3890
//    容量变化[769->1543],size=7690
//    容量变化[1543->3079],size=15430
//    容量变化[3079->1543],size=6157
//    容量变化[1543->769],size=3085
//    容量变化[769->389],size=1537
//    容量变化[389->193],size=777
//    容量变化[193->97],size=385
//    容量变化[97->53],size=193
}
