class Solution {
    // refer NEETCODE
    // BRUTE FORCE - TLE
    // T: O(2^(m+n))
    // S: O(m+n); stack space
    // backtracking not required, we are always moving forward
    // no backtracking in recursion tree as well.
    public int uniquePaths(int m, int n) {
        return dfs(0, 0, m, n);
    }

    private int dfs(int i, int j, int m, int n) {
        // base case, we've reached the destination
        if (i == (m - 1) && j == (n - 1)) {
            return 1;
        }

        // invalid position check
        if (i >= m || j >= n) {
            return 0;
        }

        int res = 0;
        res += dfs(i + 1, j, m, n); // down
        res += dfs(i, j + 1, m, n); // right

        return res; // total ways
    }

}

class Solution {
    // refer NEETCODE
    // BETTER
    // TOP-DOWN (MEMOIZATION)
    // T: O(m*n)
    // S: O(m*n) + O(m+n); dp size + stack space
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] ar : dp) {
            Arrays.fill(ar, -1);
        }
        return dfs(0, 0, m, n, dp);
    }

    private int dfs(int i, int j, int m, int n, int[][] dp) {
        // base case, we've reached the destination
        if (i == (m - 1) && j == (n - 1)) {
            return 1;
        }

        // invalid position check
        if (i >= m || j >= n) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int res = 0;
        res += dfs(i + 1, j, m, n, dp); // down
        res += dfs(i, j + 1, m, n, dp); // right

        return dp[i][j] = res;
    }

}

class Solution {
    // refer NEETCODE
    // BETTER
    // BOTTOM-UP (TABULATION)
    // T: O(m*n)
    // S: O(m*n); dp size
    public int uniquePaths(int m, int n) {
        // in order to avoid managing the out-of-bounds
        // for last row and last column separately
        // we make dp[][] of row+1, col+1 size
        int[][] dp = new int[m + 1][n + 1];
        dp[m - 1][n - 1] = 1; // base case, there is only way to reach (m-1,n-1) from (m-1,n-1), i.e., don't move

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] += dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];
    }

}

class Solution {
    // refer NEETCODE
    // OPTIMAL
    // BOTTOM-UP (TABULATION) SPACE OPTIMIZED
    // T: O(m*n)
    // S: O(n); dp size
    public int uniquePaths(int m, int n) {
        int[] prev = new int[n];
        Arrays.fill(prev, 1);
        // prev[] stores the previous state
        // in this case, since we ar filling the dp from m-1,n-1
        // so the previous is row = i+1
        // current is row = i
        // acc. to recurrence, we only require, (i,j+1) & (i+1, j)
        // => curr[j+1] & prev[j]

        // logic for filling "1" in arrays, the last column (n-1)
        // and last row (m-1) will always have "1" since only one way to reach destination from there

        for (int i = m - 2; i >= 0; i--) {
            int[] curr = new int[n];
            Arrays.fill(curr, 1);
            for (int j = n - 2; j >= 0; j--) {
                curr[j] = curr[j + 1] + prev[j];
            }
            prev = curr; // this won't give copying problem
            // because curr[] is new every-time
        }
        return prev[0];
    }

}
