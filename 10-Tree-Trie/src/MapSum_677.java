import java.util.Set;
import java.util.TreeMap;

public class MapSum_677 {
    private class Node {
        public int val;
        public boolean isWord;
        public TreeMap<Character,Node> children;

        public Node(int val,boolean isWord) {
            children = new TreeMap<>();
            this.val = val;
            this.isWord = isWord;
        }

        public Node() {
            this(0,false);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("val="+val+",isWord="+isWord+",children=[");
            Set<Character> keys = children.keySet();
            int i=0;
            for(char key : keys) {
                sb.append(key +":("+children.get(key)+")");
                i++;
                if(i != keys.size()) {
                    sb.append(",");
                }
            }
            sb.append("]");
            return sb.toString();
        }

    }

    private Node root;

    /** Initialize your data structure here. */
    public MapSum_677() {
        root = new Node();
    }

    @Override
    public String toString() {
        return root.toString();
    }

    //如果键已经存在，那么原来的键值对将被替代成新的键值对。
    public void insert(String key, int val) {
        insertChar(root,key,0,val);
    }

    //递归函数：在以node为根节点的Trie上添加字符key[index]
    private void insertChar(Node node,String key,int index,int val) {
        if(index == key.length()) {
            if(!node.isWord) {
                node.isWord = true;
            }
            //覆盖原来的值
            node.val = val;
            return;
        }

        char cur = key.charAt(index);
        if(!node.children.containsKey(cur)) {
            node.children.put(cur,new Node());
        }

        insertChar(node.children.get(cur),key,index+1,val);
    }

    //返回所有以该前缀开头的键的值的总和
    public int sum(String prefix) {
        Node cur = root;
        for(int i=0;i<prefix.length();i++) {
            char c = prefix.charAt(i);
            //很重要的判断！！！
            if(!cur.children.containsKey(c)) {
                return 0;
            }
            cur = cur.children.get(c);
        }
        //目前为止，cur指向的是前缀的最后一个字符所在节点的上一个节点,cur的children就是所有以prefix为前缀的key了
        return sumKey(cur,cur.val);

    }

    //递归函数：在以node为根节点的Trie中查找所有key的value，并返回该node下所有value之和
    private int sumKey(Node node,int sum) {

        for(Node nextNode : node.children.values()) {
            sum += sumKey(nextNode,nextNode.val);
        }

        return sum;
    }

    public static void main(String[] args) {
        MapSum_677 ms = new MapSum_677();
        ms.insert("aa",3);
        System.out.println(ms);
        System.out.println("a:"+ms.sum("a"));

        ms.insert("aa",2);
        System.out.println(ms);
        System.out.println("a:"+ms.sum("a"));
        System.out.println("aa:"+ms.sum("aa"));

        ms.insert("aaa",3);
        System.out.println(ms);
        System.out.println("aaa:"+ms.sum("aaa"));
        System.out.println("bbb:"+ms.sum("bbb"));
    }
}
