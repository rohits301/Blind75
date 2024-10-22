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