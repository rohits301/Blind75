class Solution {
    // BRUTE FORCE
    // T: O(n^2), S: O(n)
    public int lengthOfLongestSubstring(String s) {

        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            int length = 0;
            for (int j = i; j < s.length(); j++) {
                char ch = s.charAt(j);
                if (set.contains(ch)) {
                    break;
                } else {
                    set.add(ch);
                    length += 1;
                }
            }
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }
}

class Solution {
    // SLIDING WINDOW
    // BETTER
    // T: O(2n), S: O(1)- Space is not O(n) coz. worst case set would have 256
    // distinct elements so O(256)
    public int lengthOfLongestSubstring(String s) {

        int maxLength = 0;
        int left = 0, right = 0;
        int n = s.length();
        Set<Character> set = new HashSet<>();

        while (right < n) {
            char rightChar = s.charAt(right);

            if (!set.contains(rightChar)) {
                set.add(rightChar);
                maxLength = Math.max(maxLength, right - left + 1);
            } else {
                while (set.contains(rightChar)) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.add(rightChar);
                // we do not update maxLength here, as left has increased so, currentLength cannot beat the maxLength
            }
            right++;
        }
        return maxLength;
    }
}

class Solution {
    // OPTIMAL
    // refer STRIVER's video 
    // T: O(n), S: O(n)
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int n = s.length();
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (right < n) {
            char rightChar = s.charAt(right);
            // left is jumping instead of sliding to optimise more
            // check whether left is in the window under consideration
            if (map.containsKey(rightChar)) {
                left = Math.max(left, map.get(rightChar) + 1);
            }

            maxLength = Math.max(maxLength, right - left + 1);
            map.put(rightChar, right);
            right++;
        }
        return maxLength;
    }
}
