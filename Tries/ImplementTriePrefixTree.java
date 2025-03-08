public class TrieNode {
    // Array to store references to child nodes (26 for lowercase English letters)
    private TrieNode[] children;
    // Flag to mark if a node represents the end of a word
    private boolean isEndOfWord;

    public TrieNode(){
        children = new TrieNode[26]; // Initializes an array for children nodes
        isEndOfWord = false; // By default, a node is not the end of a word
    }

    // Getter method to retrieve children nodes
    public TrieNode[] getChildren() {
        return children;
    }
    
    // Getter method to check if this node marks the end of a word
    public boolean getEndOfWord(){
        return isEndOfWord;
    }
    
    // Setter method to create a link between this node and a child node
    public void setChildren(char ch, TrieNode node) {
        children[ch - 'a'] = node; // Maps character to its corresponding child node
    }
    
    // Marks the current node as the end of a word
    public void setEndOfWord(){
        this.isEndOfWord = true;
    }
}

// refer STRIVER for understanding, code from ChatGPT
class Trie {
    private TrieNode root; // Root node of the Trie

    public Trie() {
        root = new TrieNode(); // Initializes Trie with an empty root node
    }

    // T: O(n), S: O(n) - to store the word
    // Inserts a word into the Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            // If there is no child node for the current character, create one
            if (node.getChildren()[ch - 'a'] == null) {
                node.setChildren(ch, new TrieNode());
            }
            // Move to the next node
            node = node.getChildren()[ch - 'a']; 
        }
        node.setEndOfWord(); // Marks the last node as the end of the word
    }

    // T: O(n), S: O(1)
    // Searches for a word in the Trie
    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            // If the character node doesn't exist, word is not present
            if (node.getChildren()[ch - 'a'] == null) {
                return false;
            } 
            // Move to the next node
            node = node.getChildren()[ch - 'a'];
        }
        // Return true only if this node marks the end of a word
        return node.getEndOfWord();
    }

    // T: O(n), S: O(1)
    // Checks if there is any word in the Trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            // If the character node doesn't exist, prefix is not present
            if (node.getChildren()[ch - 'a'] == null) {
                return false;
            }
            // Move to the next node
            node = node.getChildren()[ch - 'a'];
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
