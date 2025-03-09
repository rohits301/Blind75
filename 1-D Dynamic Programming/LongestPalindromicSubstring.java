class Solution {
    // Brute -> Recursion, Memoization -> Better
    // BETTER
    // recursion + memoization
    // T: O(n^3); O(n^2) for loop, O(n) for recursion on string
    // S: O(n^2); O(n^2) for dp table + O(n) auxilary space for recursion
    public String longestPalindrome(String s) {
        int maxLength = 0;
        int start = 0;
        int[][] dp = new int[s.length()][s.length()];
        for (int[] ar : dp) {
            Arrays.fill(ar, -1);
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (solve(s, i, j, dp)) {
                    if (j - i + 1 > maxLength) {
                        maxLength = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLength);
    }

    private boolean solve(String s, int i, int j, int[][] dp) {
        // i == j, single character case, odd-length susbtring
        // i > j, empty string case, even-length substring
        // both are palindrome, so return true
        if (i >= j) {
            return true;
        }

        if (dp[i][j] != -1) {
            return dp[i][j] == 0 ? false : true;
        }
        if (s.charAt(i) == s.charAt(j)) {
            boolean val = solve(s, i + 1, j - 1, dp);
            dp[i][j] = val == false ? 0 : 1;
            return val;
        }
        dp[i][j] = 0;
        return false;
    }
}

class Solution {
    // Iteration Brute Force (with improvements) - passed
    // EVEN BETTER
    // LEETCODE EDITORIAL
    // we check the biggest length substring first, followed by smaller length becoz. we are interested in largest length
    // there is 1 substring of len=n, 2 of len=n-1, 3 of len=n-2 ... and n of len=1, but we don't check them all, we return from wherever we find the largest length substring
    // this reduces the practical runtime complexity of the algorithm
    // T: O(n^3); explained above
    // S: O(1); no extra space, answer is not considered in extra space
    public String longestPalindrome(String s) {
        for(int length = s.length(); length >= 0; length--){
            for(int start = 0; start <= s.length() - length; start++){
                if(isPalindrome(s, start, start + length)){
                    return s.substring(start, start+length);
                }
            }
        }
        return "";
    }
    private boolean isPalindrome(String s, int i, int j){
        int left = i;
        int right = j-1;

        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

class Solution {
    // refer LEETCODE EDITORIAL
    // Better (DP - Tabulation) 
    // T: O(n^2); time to fill the n^2 dp 
    // S: O(n^2); space of dp array
    /*
     * In the dp array, on both sides of the array, denoted by `i` and `j`, imagine the string is there
     * The susbtring under consideration is `i` to `j`. Starting at `i` and ending at `j`.
     * `i` to `i`, that is 1 length substring is always a palindrome
     * `i` to `i+1` - 2 length substring, palindrome if `charAt(i) == charAt(j)`
     * For substrings of length 3 or more (`s[i:j]`), check if:
     * The first and last characters are equal (`s[i] == s[j]`).
     * The inner substring `s[i+1:j-1]` is already marked as a palindrome in `dp`
     * 
     * Note: the longest substring is mapped to dp[0][n-1].
     */
    public String longestPalindrome(String s) {
        int[] ans = new int[2];
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        // 1 - length substrings
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            ans[0] = i;
            ans[1] = i;
        }
        // 2 - length substrings
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                ans[0] = i;
                ans[1] = i + 1;
            }
        }
        // 3 and more length substrings
        for (int diff = 2; diff < n; diff++) {
            for (int i = 0; i < n - diff; i++) {
                int j = i + diff;
                if (s.charAt(i) == s.charAt(j)
                    && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }
        int i = ans[0], j = ans[1];
        return s.substring(i, j + 1);
    }
}

class Solution {
    // OPTIMAL
    // refer Java brains
    // T: O(n^2), S: O(n)
   /*
     * The "Expand Around Center" approach is based on the idea that for a substring to be palindromic, 
     * the characters at its two ends must be equal, and the substring between them must also be a palindrome.
     *
     * Instead of checking all possible substrings separately (O(n^3) brute force), we expand outward 
     * from a center and check if it remains a palindrome. This saves extra loops.
     *
     * Since a palindrome can be:
     *  - Odd-length: The center is a single character (e.g., "aba" → center at 'b')
     *  - Even-length: The center is between two characters (e.g., "abba" → center between 'bb')
     *
     * To account for both cases, we check **two centers for every character**:
     *  1. `expandAroundCenter(s, start, start, ans)`  → Odd-length palindromes
     *  2. `expandAroundCenter(s, start, start + 1, ans)` → Even-length palindromes
     */
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }

        int[] ans = new int[2]; // Stores the starting index and length of the longest palindrome found.
        // Iterate through each character as a potential center
        for (int start = 0; start < s.length() - 1; start++) {
            expandAroundCenter(s, start, start, ans); // odd length palindromes
            expandAroundCenter(s, start, start + 1, ans); // even length palindromes
        }
        int resStart = ans[0];
        int resLength = ans[1];
        return s.substring(resStart, resStart + resLength);
    }

    private void expandAroundCenter(String s, int begin, int end, int[] ans) {
        while (begin >= 0 && end < s.length()
                && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        
        // When expansion stops, the actual palindrome length is (end - begin - 1)
        // Example: "aba", after expansion stops: begin = -1, end = 3, length = 3 - (-1) - 1 = 3
        int length = end - begin - 1;
        if (length > ans[1]) {
            ans[0] = begin + 1;
            ans[1] = length;
        }
    }
}

// OPTIMAL - MAX
// MANACHER'S ALGO.
