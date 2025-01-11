class Solution {
    // refer STRIVER
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        // BRUTE
        // iterate on matrix and set the row and cols as -1 (instead of 0), this is to avoid false markings
        // approach
        // for(int i=0; i<n; i++){
        //     for(int j=0; j<m; j++){
        //         if(matrix[i][j] == 0){
        //             markRow(i);
        //             markCol(j);
        //         }
        //     }
        // }
        // // markRow(i)
        // for(int j=0; j<m; j++){
        //     if(matrix[i][j] != 0){
        //         matrix[i][j] = -1;
        //     }
        // }
        //similarly for cols
        // another iteration to turn -1 to 0
        // T: O( (n*m) * (m+n) + (n*m))
        // S: O(1)

        // BETTER
        // T: O(n*m*2)
        // S: O(n+m)
        // if any element in the matrix is 0, then that entire row will be 0 and so will be the column
        // so instead of marking whole row as 0, just store the index of that row and that column
        // so two 1D arrays are enough for this
        // code for better
        
        int[] row = new int[n];
        int[] col = new int[m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] == 0){
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }
        //marking the elements
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(row[i] == 1 || col[j] == 1){
                    matrix[i][j] = 0;
                }
            }
        }
        

        // OPTIMAL
        // in-place

    }
}
