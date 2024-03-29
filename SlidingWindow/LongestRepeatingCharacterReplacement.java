class Solution {
    // BRUTE FORCE
    // T: O(n^2), S: O(n) - will be O(26) as map would have max 26 unique keys
    public int characterReplacement(String s, int k) {
        int maxLength = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            Map<Character, Integer> map = new HashMap<>();
            int maxFrequency = 0;
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                maxFrequency = Math.max(maxFrequency, map.get(ch));
                // windowSize = j-i+1;
                if ((j - i + 1) - maxFrequency > k) {
                    break;
                }
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }
}

class Solution {
    // BETTER
    // T: O(n^2), S: O(26) = O(1)
    public int characterReplacement(String s, int k) {
        int maxLength = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char[] freq = new char[26];
            int maxFrequency = 0;
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);
                freq[ch - 'A']++;
                maxFrequency = Math.max(maxFrequency, freq[ch - 'A']);
                // windowSize = j-i+1;
                if ((j - i + 1) - maxFrequency > k) {
                    break;
                }
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }
}

class Solution {
    // OPTIMAL
    // refer Neetcode's video
    // T: O(n), S: O(26)
    public int characterReplacement(String s, int k) {
        int maxLength = 0;
        int n = s.length();
        int left = 0, right = 0;
        int[] freq = new int[26];
        int maxFrequency = 0;

        while (right < n) {
            char ch = s.charAt(right);
            freq[ch - 'A']++;
            maxFrequency = Math.max(maxFrequency, freq[ch - 'A']);

            // count of non-repeating characters should be <= k for a valid window
            // when window is invalid, we start sliding the window
            while ((right - left + 1) - maxFrequency > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }
        return maxLength;
    }
}