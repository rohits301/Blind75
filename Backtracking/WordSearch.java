class Solution {
    // refer NEETCODE
    // T: O(m*n*lengthOfWord), worst case lengthOfWord = m*n , S: O(m*n)
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
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
        if (board[i][j] == word.charAt(idx)) {
            char temp = board[i][j];
            board[i][j] = '#'; // mark visited

            res = dfs(board, i - 1, j, word, idx + 1) ||
                  dfs(board, i, j - 1, word, idx + 1) ||
                  dfs(board, i + 1, j, word, idx + 1) ||
                  dfs(board, i, j + 1, word, idx + 1);

            board[i][j] = temp;
        }
        return res;
    }
}