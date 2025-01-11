class Solution {
    // TOP-DOWN MEMOIZATION
    // T: O(n), S: O(n)
    public int numDecodings(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return dfs(s, 0, s.length(), dp);
    }

    private int dfs(String s, int i, int n, int[] dp) {
        if (i == n) {
            return 1;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int res = 0;
        // cannot start with 0
        if (s.charAt(i) > '0') {
            res += dfs(s, i + 1, n, dp);
        }
        // if we have double digit, then check if it's valid
        if (i < n - 1) {
            if (s.charAt(i) == '1' ||
                    (s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
                res += dfs(s, i + 2, n, dp);
            }
        }
        dp[i] = res;
        return res;
    }
}

class Solution {
    // refer Neetcode
    // BOTTOM-UP DP, TABULATION
    // T:O(n), S:O(n)
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        // dp[i] = the number of decodings for substring i to n.
        dp[n] = 1; // the stopping condition (base case). For substring of length = s.length()
        // there's only one way to decode, i.e., do nothing.

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
            } else {
                dp[i] += dp[i + 1];
                if (i < n - 1) {
                    if (s.charAt(i) == '1' ||
                            s.charAt(i) == '2' && s.charAt(i + 1) < '7') {
                        dp[i] += dp[i + 2];
                    }
                }
            }
        }
        return dp[0];
    }
}

class Solution {
    // refer NEETCODE
    // TABULATION (SPACE OPTIMISED)
    // T: O(n), S: O(1)
    // we only require previous two states
    public int numDecodings(String s) {
        int n = s.length();

        int dp2 = 0; // for currentIndex + 2
        int dp1 = 1; // for currentIndex + 1, the no. of decodings for substring(n) = 1, because after s.length()-1, at s.length(), i.e., for empty string, there is only 1 way
        int dp = 0; // for currentIndex
        // dp & dp2 are placeholders

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp = 0;
            } else {
                dp += dp1;
                if (i < n - 1) {
                    if (s.charAt(i) == '1' ||
                            (s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
                        dp += dp2;
                    }
                }
            }
            dp2 = dp1;
            dp1 = dp;
            dp = 0;
        }
        return dp1; // after the last iteration, the answer is in dp1.
    }
}
