class Solution {
    // BRUTE FORCE - passed
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