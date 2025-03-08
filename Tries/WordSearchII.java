class Solution {
    // refer NEETCODE
    // BRUTE FORCE
    // TLE
    // T: O(w*(m*n)*4^L), L=length of word, w = no. of words in words[]
    // S: O(L) - recursion stack space
    // use set to avoid returning multiple occurrences of the same word in the board, 
    // e.g. words["oa", "oaa"] in the board of [["o","a","b","n"],["o","t","a","e"],["a","h","k","r"],["a","f","l","v"]]
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> set = new HashSet<>();

        for (String word : words) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == word.charAt(0) &&
                        dfs(board, i, j, word, 0)) {
                        set.add(word);
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }

    private boolean dfs(char[][] board, int i, int j, String word, int idx) {
        if (idx == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
            || board[i][j] != word.charAt(idx)) {
            return false;
        }
        
        boolean res = false;
        char temp = board[i][j];
        board[i][j] = '#';

        res = dfs(board, i - 1, j, word, idx + 1) ||
              dfs(board, i, j - 1, word, idx + 1) ||
              dfs(board, i + 1, j, word, idx + 1) ||
              dfs(board, i, j + 1, word, idx + 1);

        board[i][j] = temp;
        return res;
    }
}

class Solution {
    // refer CodingDecoded and LeetCode discuss
    // OPTIMAL
    // T: O(m*n*4^L), S: O(L*words)
    // L= average length of word, words = no. of words
    private class TrieNode {
        private TrieNode[] children;
        private String word;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode curr = root;
            for (char ch : word.toCharArray()) {
                if (curr.children[ch - 'a'] == null) {
                    curr.children[ch - 'a'] = new TrieNode();
                }
                curr = curr.children[ch - 'a'];
            }
            curr.word = word;
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> res = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, boardroot, res);
            }
        }
        return res;
    }
    
    private void dfs(int i, int j, char[][] board, TrieNode node, List<String> res) {
        // Base conditions: Check bounds and visited cells
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '#') {
            return;
        }

        char ch = board[i][j];
        if (node.children[ch - 'a'] == null){
            return;
        }
        node = node.children[ch - 'a'];

        // If we found a word, add it to result
        if (node.word != null) {
            res.add(node.word);
            node.word = null; // Remove duplicate words
        }

        // Mark as visited
        board[i][j] = '#';

        // Explore in all 4 directions
        dfs(i - 1, j, board, node, res); // Up
        dfs(i + 1, j, board, node, res); // Down
        dfs(i, j - 1, board, node, res); // Left
        dfs(i, j + 1, board, node, res); // Right

        // Restore the board for backtracking
        board[i][j] = ch;
    }
}
