// refer NEETCODE
class WordDictionary {
    private static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
        }
    }

    private static TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    // tc: O(n), sc: O(n)
    public void addWord(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            if (curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.isEndOfWord = true;
    }

    // tc: O(m^2) where m = no. of characters at each level, sc: O(1)
    public boolean search(String word) {
        return searchHelper(word, root, 0);
    }

    private boolean searchHelper(String word, TrieNode curr, int idx) {
        for (int i = idx; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch == '.') {
                // explore all paths, so dfs
                for (TrieNode child : curr.children) {
                    if (child != null && searchHelper(word, child, i + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                if (curr.children[ch - 'a'] == null) {
                    return false;
                } else {
                    curr = curr.children[ch - 'a']; // move to next
                }
            }
        }
        return curr.isEndOfWord;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */