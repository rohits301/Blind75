class Solution {
    // BRUTE/BETTER
    // if any element in the matrix is 0, then that entire row will be 0 and so will be the column
    // so instead of marking whole row as 0, just mark that row and that column
    // so two 1D arrays are enough for this
    // T: O(m*n*2)
    // S: O(m+n)
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] rowMarker = new boolean[m];
        boolean[] colMarker = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowMarker[i] = true;
                    colMarker[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rowMarker[i] || colMarker[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

class Solution {
    // OPTIMAL (in-place)
    // T: O(2*m*n) + O(m) + O(n)
    // S: O(1)
    // improving on BETTER
    /*
     * since time will not be less than m*n
     * we go after the space, so in-place
     * one major problem - the order of marking
     * and how to keep the matrix[0][0]
     * so, separate variable to track col0
     * colMarker[n] -> matrix[0][...]
     * rowMarker[m] -> matrix[...][0]
     */
    
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int col0 = matrix[0][0]; // to keep track of 0th coln

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // marking 0th column
                    
                    if (j != 0) {
                        matrix[0][j] = 0; // marking 0th row
                    } else {
                        col0 = 0;
                    }
                }
            }
        }

        // start from 1,1 to m,n to start marking rows and cols
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != 0) { // this check is IMP. - this makes sure we avoid redundant writes
                    if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        // The 0th column values are tracked by col0
        // the 0th row is tracked by matrix[0][0]
        // Applying the result of `col0` first will change the entire 0th column, including matrix[0][0]
        // So, this will result in incorrect values
        // Hence, first apply the result of matrix[0][0]
        // then result of `col0`
        if (matrix[0][0] == 0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (col0 == 0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
