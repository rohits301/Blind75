class Solution {
    // Basically, an extension to the longest palindromic substring
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
