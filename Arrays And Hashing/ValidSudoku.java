class Solution {
    // refer NEETCODE
    // BRUTE/BETTER
    // T: O(n^2) - O(81)
    // S: O(n) - O(9)
    public boolean isValidSudoku(char[][] board) {
        int n = board.length; // n = 9
        Set<Character> set;

        // row-check
        for (int i = 0; i < n; i++) {
            set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                // auto-type cast to int
                if (set.contains(board[i][j])) {
                    return false;
                } else {
                    set.add(board[i][j]);
                }
            }
        }

        // col-check
        for (int j = 0; j < n; j++) {
            set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (board[i][j] == '.') {
                    continue;
                }
                // auto-type cast to int
                if (set.contains(board[i][j])) {
                    return false;
                } else {
                    set.add(board[i][j]);
                }
            }
        }

        // square check (3x3)
        /*
         * There are 9 (3x3) grids in the 9x9 grid
         * According to sudoku, all should be unique
         * so, we derive a formula such that
         * for every square, we can get the -
         * indices of the left-most corner, that is, the starting corner of the square grid.
         * With the starting indices, we just have to loop through a 3x3 grid to check for duplicates.
         * observation -
         * 0 1 2
         * 3 4 5
         * 6 7 8
         * for first row squares - 0,1,2
         * starting row is 0, starting colns. are 0,3,6
         * for second row squares - 3,4,5
         * starting row is 3, starting colns. are 0,3,6
         * similarly, for third row squares - 6,7,8
         * starting row is 6, starting colns. are 0,3,6
         * hence, the formula -
         * row = (square / 3) * 3
         * col = (square % 3) * 3
         */

        for (int square = 0; square < n; square++) {
            int startRow = (square / 3) * 3;
            int startCol = (square % 3) * 3;

            // check in 3x3 grid
            set = new HashSet<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int r = startRow + i;
                    int c = startCol + j;

                    if (board[r][c] == '.') {
                        continue;
                    }
                    // auto-type cast to int
                    if (set.contains(board[r][c])) {
                        return false;
                    } else {
                        set.add(board[r][c]);
                    }
                }
            }
        }
        return true;
    }
}

