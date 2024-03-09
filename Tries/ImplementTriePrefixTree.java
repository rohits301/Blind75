class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean flag;

    boolean contains(char ch) {
        return (children[ch - 'a'] != null);
    }

    TrieNode get(char ch) {
        return children[ch - 'a'];
    }

    void put(char ch, TrieNode node) {
        children[ch - 'a'] = node;
    }

    void setEnd() {
        flag = true;
    }

    boolean isEnd() {
        return flag;
    }
}

// refer STRIVER
class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // tc: O(len), sc: O(len)
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.contains(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    // tc: O(len), sc: O(1)
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.contains(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return node.isEnd();
    }

    // tc: O(len), sc: O(1)
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.contains(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */