
class Solution {
    // refer STRIVER
    // OPTIMAL
    // T: O(n*m)
    // S: O(1)
    public List<Integer> spiralOrder(int[][] matrix) {
        // directions -> right, bottom, left, top
        int m = matrix.length;
        int n = matrix[0].length;
        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;
        List<Integer> ans = new ArrayList<>();

        // extra if-checks for single row & single column matrix
        // single row case, top = bottom
        // so matrix[bottom][i] is repitition
        // similarly, for single column, left = right
        // so matrix[i][left] is repitition

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                ans.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                ans.add(matrix[i][right]);
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    ans.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    ans.add(matrix[i][left]);
                }
                left++;
            }
        }
        return ans;
    }
}
