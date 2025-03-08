// refer STRIVER for understanding and NEETCODE for code
class Trie {
    /**
     * The TrieNode class represents individual nodes in the Trie.
     * 
     * Why `private static`?
     * ✅ `private` - Ensures encapsulation; the TrieNode is only accessible within Trie.
     * ✅ `static`  - Ensures each TrieNode instance does NOT store an implicit reference to the outer Trie class.
     *                - Without `static`, each TrieNode would hold a reference to the parent Trie, wasting memory.
     *                - This allows efficient memory usage when creating multiple TrieNodes.
     */
    private static class TrieNode {
        // Array to store references to child nodes (26 for lowercase English letters)
        TrieNode[] children; //default-access, package-private
        boolean isEndOfWord;
    
        public TrieNode(){
            children = new TrieNode[26]; // Initializes an array for children nodes
            isEndOfWord = false; // By default, a node is not the end of a word
        }
    }
    
    private TrieNode root; // Root node of the Trie

    public Trie() {
        root = new TrieNode(); // Initializes Trie with an empty root node
    }

    // T: O(n), S: O(n) - worst case, we insert a new word
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            // If there is no child node for the current character, create one
            if (node.children[ch - 'a'] == null) {
                node.children[ch-'a'] = new TrieNode();
            }
            // Move to the next node
            node = node.children[ch - 'a']; 
        }
        node.isEndOfWord = true; // Marks the last node as the end of the word
    }

    // T: O(n), S: O(1)
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            // If the character node doesn't exist, word is not present
            if (node.children[ch - 'a'] == null) {
                return false;
            } 
            // Move to the next node
            node = node.children[ch - 'a'];
        }
        // Return true only if this node marks the end of a word
        return node.isEndOfWord;
    }

    // T: O(n), S: O(1)
    // Checks if there is any word in the Trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            // If the character node doesn't exist, prefix is not present
            if (node.children[ch - 'a'] == null) {
                return false;
            }
            // Move to the next node
            node = node.children[ch - 'a'];
        }
        return true; // If we reached here, prefix exists in the Trie
    }
}

/**
 * Usage Example:
 * Trie obj = new Trie();
 * obj.insert("apple");
 * boolean param_2 = obj.search("apple"); // Returns true
 * boolean param_3 = obj.startsWith("app"); // Returns true
 * boolean param_4 = obj.search("appl"); // Returns false
 */
