class Solution {
    // BETTER
    // T: O(n^2)
    // S: O(n^2)
    // DP approach
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int ans = 0;

        // 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            ans++;
        }

        // 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                ans++;
            }
        }

        // 3 and more
        for (int diff = 2; diff < n; diff++) {
            for (int i = 0; i < n - diff; i++) {
                int j = i + diff;
                if (s.charAt(i) == s.charAt(j) &&
                        dp[i + 1][j - 1] == true) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }

        return ans;
    }
}

class Solution {
    // Basically, an extension to the longest palindromic substring
    // OPTIMAL
    // T: O(n^2), S: O(1)
    // refer Neetcode
    public int countSubstrings(String s) {
        int ans = 0;
        for (int start = 0; start < s.length(); start++) {
            ans += expandAroundCenter(s, start, start);
            ans += expandAroundCenter(s, start, start + 1);
        }

        return ans;
    }

    private int expandAroundCenter(String s, int begin, int end) {
        int res = 0;
        while (begin >= 0 && end < s.length() &&
                s.charAt(begin) == s.charAt(end)) {
            res += 1;
            begin--;
            end++;
        }
        return res;
    }
}
