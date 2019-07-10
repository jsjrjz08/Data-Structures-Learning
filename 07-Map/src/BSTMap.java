public class BSTMap<K extends Comparable, V> implements Map<K,V> {
    private class Node {
        K key;
        V value;
        Node left,right;

        public Node(K key,V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
        public Node() {
            this(null,null);
        }

        @Override
        public String toString() {
            return key+":"+value;
        }
    }

    private Node root;
    private int size;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Map size="+size+",data="+printString(root));
        return sb.toString();
    }

    private String printString(Node node) {
        StringBuilder sb = new StringBuilder();
        if( node == null) {
            return sb.toString();
        }
        sb.append(printString(node.left));
        sb.append(node).append(",");
        sb.append(printString(node.right));
        return sb.toString();
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

//        return contains(root,key);
        return get(root,key) != null;
    }

//    //递归函数：以node为根节点的二分搜索树是否包含键key
//    private boolean contains(Node node, K key) {
//        //结束条件
//        if(node == null) {
//            return false;
//        }
//
//        //函数主体
//        if(node.key.compareTo(key) == 0) {
//            return true;
//        } else if(node.key.compareTo(key) > 0) {
//            return contains(node.left,key);
//        } else {
//            return contains(node.right,key);
//        }
//    }

    @Override
    public V get(K key) {
        Node node = get(root,key);
        return node == null ? null :node.value;
    }

    //递归函数：在以node为根节点的二分搜索树中查找键为key的元素，并返回该节点
    private Node get(Node node,K key) {
        if(node == null) {
            return null;
        }

        if(node.key.compareTo(key) > 0) {
            return get(node.left,key);
        } else if(node.key.compareTo(key) < 0) {
            return get(node.right,key);
        } else {
            return node;
        }
    }

    //注意与add的区别
    //set针对已经存在的元素进行更新
    //add添加不存在的新元素
    @Override
    public void set(K key, V value) {
        Node node = get(root,key);
        if(node != null ) {
            node.value = value;
        } else {
            throw new IllegalArgumentException("this key doesn't exist");
        }
    }

    @Override
    public void add(K key, V value) {

        root = add(root,key,value);
    }

    //递归函数：在以node为根节点的二分搜索树上添加新节点，并且返回新的二分搜索树的根
    //不添加键重复的元素！！
    private Node add(Node node, K key, V value) {
        //结束条件
        if(node == null) {
            node = new Node(key,value);
            size ++;
            return node;
        }

        //函数主体
        if(node.key.compareTo(key) > 0) {
            node.left = add(node.left,key,value);
        } else if(node.key.compareTo(key) < 0) {
            node.right = add(node.right,key,value);
        } else {//node.key.compareTo(key) == 0
            // -------->不添加键重复的元素！！，更新value
            node.value = value;
        }

        return node;
    }

    @Override
    public V remove(K key) {
        root = remove(root,key);
        return get(key);
    }

    //递归函数：在以node为根节点的二分搜索树中删除键为key的元素，并返回新的二分搜索树的根
    private Node remove(Node node,K key) {
        if(node == null) {
            return null;
        }

        if(node.key.compareTo(key) == 0) {
            if(node.left == null) {
                size --;
                return node.right;
            }

            if(node.right == null) {
                size --;
                return node.left;
            }

            if(node.left != null && node.right != null) {
                //在node的右子树种找到并删除最小值节点，作为取代node的节点newNode,
                Node newNode = min(node.right);//只是找到这个节点，此时，这个节点还挂在node的右子树上！
                //newNode的右子树是node的右子树去掉newNode节点后的子树，newNode的左子树是node的左子树
                //1和2顺序不能调换！
                newNode.right = removeMin(node.right);//1 --摘除newNode之后的node的右子树
                newNode.left = node.left;//2
                node.right = null;
                node.left = null;
//                size --;//不需要维护了！！！！！！
                return newNode;
            }

        } else if(node.key.compareTo(key) > 0) {
            node.left = remove(node.left,key);
        } else if(node.key.compareTo(key) < 0) {
            node.right = remove(node.right,key);
        }
        return node;
    }

    //递归函数：在以node为根节点的二分搜索树中查找最小值节点，并返回最小值节点
    private Node min(Node node) {
        if(node == null || node.left == null) {
            return node;
        }
        return min(node.left);
    }

    //递归函数：在以node为根的二分搜索树中删除最小值元素，并返回新的二分搜索树的根
    private Node removeMin(Node node) {
        if(node == null) {
            return node;
        }

        if(node.left == null) {//找到最小值节点node
            node = node.right;//摘除节点
            size --;
            return node;
        } else {
            node.left = removeMin(node.left);
            return node;
        }
    }

    public static void main(String[] args) {
        BSTMap<Integer,Integer> map = new BSTMap<>();
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

        map.remove(7);
        System.out.println(map);


        System.out.println("-----------map2------");
        BSTMap<Character,Integer> map2 = new BSTMap<>();
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
