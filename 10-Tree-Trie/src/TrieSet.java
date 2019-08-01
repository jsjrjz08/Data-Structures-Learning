public class TrieSet implements Set<String> {
    private Trie trie;

    public TrieSet() {
        trie = new Trie();
    }

    @Override
    public int getSize() {
        return trie.getSize();
    }

    @Override
    public boolean isEmpty() {
        return trie.isEmpty();
    }

    @Override
    public boolean contains(String s) {
        return trie.contains(s);
    }

    @Override
    public void add(String s) {
        trie.add(s);
    }

    @Override
    public void remove(String s) {
        trie.remove(s);
    }
}
