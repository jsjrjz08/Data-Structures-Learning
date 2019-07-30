import java.util.Set;
import java.util.TreeMap;

/**
 * Trie
 * 1.是一种多叉树。又称字典树、前缀树
 * 2.专门处理字符串。根节点不包含字符，除根节点外每个节点只包含一个字符
 * 3.时间复杂度为O(w),w为字符串的长度。时间复杂度与Trie规模无关，查找效率比二分搜索树更高
 * 4.以空间换时间
 *
 * 局限性 -------- 空间！！！
 * 解决：
 * 变种：压缩字典树(节省空间、维护成本增高)、三分搜索树()、后缀树(模式匹配)
 *
 */
public class Trie {
    private class Node {
        public boolean isWord;
        public TreeMap<Character,Node> next;

        //构造函数
        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("isWord="+isWord+",next=[");
            Set<Character> keys = next.keySet();
            int i=0;
            for(char key : keys) {
                sb.append(key +":("+next.get(key)+")");
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
    private int size;

    //构造函数
    public Trie() {
        root = new Node();
        size = 0;
    }

    @Override
    public String toString() {
        return "size="+size+",root={"+root+"}";
    }

    //包含word的个数
    public int getSize() {
        return size;
    }

    //是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //添加一个word
    //维护size
    public void add(String word) {
        if(word == null || word.length() == 0) {
            throw new IllegalArgumentException("word is illegal");
        }
        addChar(root,word,0);
    }

    //递归函数：将word所在index的字符添加在以node为根节点的Trie树上
    private void addChar(Node node,String word,int index) {
        //结束条件---word遍历完毕 ----------相同word，不重复计数
        if(index == word.length()) {
            if( ! node.isWord) {
                node.isWord = true;
                size ++;
            }
            return ;
        }

        char cur = word.charAt(index);
        if(! node.next.containsKey(cur)) {//node的next不包含当前字符
            //添加一个Node
            node.next.put(cur,new Node());
        }

        //下一个node
        addChar(node.next.get(cur),word,index+1);
    }

    //查找一个word
    public boolean contains(String word) {
        if(word == null || word.length() == 0) {
            throw new IllegalArgumentException("word is illegal");
        }
        return containsChar(root,word,0);
    }

    //递归函数：在以node为根节点的Trie中查找字符word[index]
    private boolean containsChar(Node node, String word, int index) {
        //结束条件
        if(index == word.length()) {
            return node.isWord;
        }

        char cur = word.charAt(index);
        if(! node.next.containsKey(cur)) {//node的next不包含当前字符
            return false;
        } else {//node的next包含当前字符
            return containsChar(node.next.get(cur), word, index+1);
        }
    }

    //判断一个Word是否为前缀
    public boolean isPrefix(String word) {
        if(word == null || word.length() == 0) {
            throw new IllegalArgumentException("word is illegal");
        }
        return isPrefixChar(root,word,0);
    }

    //递归函数：判断以node为根节点的Trie是否包含字符word[index]
    private boolean isPrefixChar(Node node,String word,int index) {
        //结束条件
        if(index == word.length()) {
            return true;
        }

        char cur = word.charAt(index);
        if( ! node.next.containsKey(cur)) {
            return false;
        } else {
            return isPrefixChar(node.next.get(cur),word,index+1);
        }
    }

    //删除一个word,返回是否成功删除
    //维护size
    //首先查找该字符串，边查询边将经过的节点压栈，若找不到，则返回假；否则依次判断栈顶节点是否为树叶，若是则，删除该节点，否则返回真
    public boolean remove(String word) {
        if(word == null || word.length() == 0) {
            throw new IllegalArgumentException("word is illegal");
        }
        return removeChar(root,word,0);
    }

    private boolean removeChar(Node node,String word, int index) {
        //结束条件
        if(index == word.length()) {
            if(node.isWord) {
                node.isWord = false;
                size --;
                return true;

            } else {//直到最后一个字符，也没找到这个word，则删除失败
                return false;
            }
        }

        char cur = word.charAt(index);
        if(!node.next.containsKey(cur)) {
            return false;
        } else {
            boolean ret = removeChar(node.next.get(cur), word, index + 1);
            Node nextNode = node.next.get(cur);
            if(!nextNode.isWord && nextNode.next.size() == 0)
                node.next.remove(word.charAt(index));
            return ret;
        }
    }


    public static void main(String[] args) {
//        System.out.println("helo".substring(0,"helo".length()-1));
        Trie trie = new Trie();
        System.out.println(trie.isEmpty());
        System.out.println(trie.getSize());
//        trie.add("have");
//        trie.add("helo");
//        trie.add("pandas");
//        trie.add("pan");
//        trie.add("trie");
//        trie.add("tree");
//        trie.add("try");
//        trie.add("pan");//重复添加

//        System.out.println(trie);
//        System.out.println(trie.isEmpty());
//        System.out.println(trie.getSize());
//        containsAndIsPrefix(trie,"trie");
//        containsAndIsPrefix(trie,"hel");
//        containsAndIsPrefix(trie,"pan");
//        containsAndIsPrefix(trie,"pand");
//        containsAndIsPrefix(trie,"pandas");
//        containsAndIsPrefix(trie,"pandas3");
//
//        removeWord(trie,"helo");
//        removeWord(trie,"trie");

//["Trie","insert","search","search","startsWith","insert","search"]
//[[],["apple"],["apple"],["app"],["app"],["app"],["app"]]
        trie.add("Trie");
        trie.add("insert");
        trie.add("search");
        trie.add("search");
        trie.add("startsWith");
        trie.add("insert");
        trie.add("search");
        System.out.println(trie.isEmpty());
        System.out.println(trie.getSize());

//        containsAndIsPrefix(trie,"");
        containsAndIsPrefix(trie,"apple");
        containsAndIsPrefix(trie,"app");
    }

    private static void removeWord(Trie trie, String word) {
        System.out.println(String.format("remove result:%s,after remove %s, this newest trie is %s",trie.remove(word),word,trie));
    }

    private static void containsAndIsPrefix (Trie trie, String word) {
        System.out.println("this trie contains "+word+"? \t:"+trie.contains(word)+"; "+word+" isPrefix? \t:"+trie.isPrefix(word));
    }
}
