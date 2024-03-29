//K不一定要继承Comparable接口！！
//public class LinkedListMap<K extends Comparable,V> implements Map<K,V> {
public class LinkedListMap<K,V> implements Map<K,V> {

    private class Node {
        K key;
        V value;
        Node next;
        public Node(K key,V value,Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key,V value) {
            this(key,value,null);
        }
        public Node() {
            this(null,null,null);
        }

        @Override
        public String toString() {
            return key+":"+value;
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public String toString() {
        return "size="+size+",data="+printString(dummyHead.next);
    }

    //递归函数：打印输出以node为头节点的链表元素
    private String printString(Node node) {
        if(node == null) {
            return "";
        }

        return node.toString()+","+printString(node.next);
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

        //return contains(dummyHead.next, key);
        return get(dummyHead.next,key) != null;
    }

    //递归函数：在以node为头节点的链表中看是否存在键为key的节点
//    private boolean contains(Node node,K key) {
//        if(node == null) {
//            return false;
//        }
//
//        if(node.key.equals(key)) {
//            return true;
//        } else {
//            return contains(node.next, key);
//        }
//    }

    @Override
    public V get(K key) {
        Node node = get(dummyHead.next,key);
        return node==null ? null : node.value;
    }

    //递归函数：在以node为头节点的链表中查找键为key的节点，并返回该节点
    private Node get(Node node,K key) {
        if(node == null) {
            return null;
        }

        if(node.key.equals(key)) {
            return node;
        } else {
            return get(node.next,key);
        }
    }

    @Override
    public void set(K key, V value) {
        Node node = get(dummyHead.next,key);
        if(node != null) {
            node.value = value;
        } else {
            throw new IllegalArgumentException("the key doesn't exist.");
        }
    }

    @Override
    public void add(K key, V value) {

        dummyHead.next = add(dummyHead.next,key,value);
    }

    //递归函数：在以node为头节点的链表的头部添加新节点，并返回新链表的头节点
    private Node add(Node node,K key,V value) {
        if(node == null) {
            size ++;
            return new Node(key,value);
        }

        Node findNode = get(node,key);

        if(findNode != null) {//用新的value覆盖
            findNode.value = value;
            return node;

        } else {//在链表头部添加元素
            size++;
            return new Node(key, value, node);
        }
    }

    @Override
    public V remove(K key) {
        Node delNode = get(dummyHead.next,key);
        if(delNode != null) {
            dummyHead = remove(dummyHead, key);
            return delNode.value;
        }
        return null;
    }

    //递归函数：在以node为虚拟头节点的链表中删除键为key的节点，并返回新链表的虚拟头节点
    private Node remove(Node dummyHead,K key) {
        if(dummyHead.next == null) {
            return dummyHead;
        }

        if(dummyHead.next.key.equals(key)) {//dummyHead.next就是要删除的、当前节点
            dummyHead.next = dummyHead.next.next;//摘除当前节点: dummyHead.next
            size --;
        }

        dummyHead.next = remove(dummyHead.next,key);

        return dummyHead;
    }

    public static void main(String[] args) {
        LinkedListMap<Integer,Integer> map = new LinkedListMap<>();
        System.out.println(map);
        System.out.println(map.isEmpty());
        System.out.println(map.getSize());
        System.out.println(map.contains(3));
        System.out.println(map.contains(9));
        map.add(5,5);
        map.add(7,7);
        map.add(3,3);
        map.add(8,8);
        map.add(6,6);
        map.add(4,4);
        System.out.println(map);
        System.out.println(map.isEmpty());
        System.out.println(map.getSize());
        System.out.println(map.contains(3));
        System.out.println(map.contains(9));

        System.out.println(map.get(4));
        map.set(8,9);
        System.out.println(map);
        map.add(8,19);
        System.out.println(map);

        //删除
        System.out.println("删除 ： "+map.remove(7));
        System.out.println(map);


        System.out.println("-----------map2------");
        LinkedListMap<Character,Integer> map2 = new LinkedListMap<>();
        System.out.println(map2.isEmpty());
        System.out.println(map2.getSize());
        System.out.println(map2.contains('a'));
        System.out.println(map2.contains('z'));
        map2.add('c',5);
        map2.add('e',7);
        map2.add('a',3);
        map2.add('f',8);
        map2.add('d',6);
        map2.add('b',4);
        System.out.println(map2);
        System.out.println(map2.isEmpty());
        System.out.println(map2.getSize());
        System.out.println(map2.contains('a'));
        System.out.println(map2.contains('z'));

    }
}
