/*
* Refer NEETCODE
* Time Complexity:
* - addWord(word) -> O(N), where N is the length of the word.
* - search(word) -> O(26^M) in the worst case (when word consists entirely of '.' wildcards).
*
* Space Complexity: O(N) for storing words in the Trie.
*/
class WordDictionary {

    // private static - restricts direct access, enforces encapuslation
    // Eliminates unnecessary outer class references (store in case of non-static
    // members), improving memory efficiency
    private static class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        // insert in trie
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        return dfsSearch(word, 0, root);
    }

    private boolean dfsSearch(String word, int i, TrieNode node) {
        // base case
        if (i == word.length()) {
            return node.isEnd; // If at the end of the word, check if it's a valid word
        }

        char ch = word.charAt(i);
        if (ch == '.') {
            // Check all possible children
            for (TrieNode child : node.children) {
                if (child != null && dfsSearch(word, i + 1, child)) {
                    return true; // If any path returns true, the word exists
                }
            }
            return false; // No matching path found
        } else {
            // normal search in trie
            return (node.children[ch - 'a'] != null && dfsSearch(word, i + 1, node.children[ch - 'a']));
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
