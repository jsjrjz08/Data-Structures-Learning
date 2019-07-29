import java.util.TreeMap;

public class Solution_208 {

    class Trie {
        private class Node {
            public boolean isWord;
            public TreeMap<Character,Node> next;

            public Node(boolean isWord) {
                this.isWord = isWord;
                this.next = new TreeMap<>();
            }

            public Node() {
                this(false);
            }
        }

        private Node root;

        /** Initialize your data structure here. */
        public Trie() {
            this.root = new Node();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            insertChar(root,word,0);
        }

        //递归函数 : 在以node为根节点的Trie上添加字符：word[index]
        private void insertChar(Node node,String word,int index) {
            if(index == word.length()) {
                if(! node.isWord) {
                    node.isWord = true;
                }
                return;
            }

            char cur = word.charAt(index);
            if(!node.next.containsKey(cur)) {
                node.next.put(cur,new Node());
            }

            insertChar(node.next.get(cur),word,index+1);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return searchChar(root,word,0);
        }

        //递归函数：在以node为根节点的Trie中添加字符word[index]
        private boolean searchChar(Node node,String word,int index) {
            if(index == word.length()) {
                return node.isWord;
            }

            char cur = word.charAt(index);
            if(!node.next.containsKey(cur)) {
                return false;
            } else {
                return searchChar(node.next.get(cur),word,index);
            }
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return startsWithChar(root,prefix,0);
        }

        //递归函数：在以node为根节点的Trie上是否存在字符word[index]
        private boolean startsWithChar(Node node,String word, int index) {
            if(index == word.length()) {
                return true;
            }

            char cur = word.charAt(index);
            if(!node.next.containsKey(cur)) {
                return false;
            } else {
                return startsWithChar(node.next.get(cur),word,index+1);
            }
        }

    }


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
