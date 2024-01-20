class Solution {
    // T: O(n) - 2 iterations, S: O(26) = O(1) - constant space
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        int[] freq = new int[26];
        for(int i=0; i<s.length(); i++){
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for(int f: freq){
            if(f != 0) return false;
        }

        return true;
    }
}