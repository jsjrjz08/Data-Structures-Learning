import java.util.TreeMap;

/**
 * 简单的正则匹配
 * Trie完成字符匹配
 */
public class Solution_211 {

    class WordDictionary {
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
        public WordDictionary() {
            root = new Node();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            addChar(root,word,0);
        }

        //递归函数：在以node为根节点的Trie中添加字符word[index]
        private void addChar(Node node,String word,int index) {
            if(index == word.length()) {
                if(!node.isWord) {
                    node.isWord = true;
                }
                return;
            }

            char cur = word.charAt(index);
            if(!node.next.containsKey(cur)) {
                node.next.put(cur,new Node());
            }

            addChar(node.next.get(cur),word,index+1);
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return searchChar(root,word,0);
        }

        //递归函数：在以node为根节点的Trie中查找是否存在字符word[index]
        private boolean searchChar(Node node,String word,int index) {
            if(index == word.length()) {
                return node.isWord;
            }

            char cur = word.charAt(index);
            if(cur != '.') {
                if(!node.next.containsKey(cur)) {
                    return false;
                }
                return searchChar(node.next.get(cur),word,index+1);
            } else {
                for(Node nextNode : node.next.values()) {//遍历所有下一个节点
                    if(searchChar(nextNode,word,index+1)) {
                        //只要有一个节点匹配，就返回true
                        return true;
                    }
                }

                //遍历完所有下一个节点后，仍匹配不上，就返回false
                return false;
            }
        }

    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
}
