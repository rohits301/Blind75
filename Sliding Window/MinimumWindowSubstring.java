class Solution {
    // BRUTE FORCE
    // T: O(m*m*52) - as max 52 unique characters in both maps (including 26 upper case and 26 lower case) 
    // S: O(52 + m*52) - max map space is 52, "have" map is created maximum m times
    public String minWindow(String s, String t) {
        int minLength = Integer.MAX_VALUE;
        int m = s.length();
        int minStart = -1;
        Map<Character, Integer> need = new HashMap<>();

        for (char ch : t.toCharArray()) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < m; i++) {
            Map<Character, Integer> have = new HashMap<>();
            for (int j = i; j < m; j++) {
                char ch = s.charAt(j);
                have.put(ch, have.getOrDefault(ch, 0) + 1);

                boolean flag = true;
                for (char key : need.keySet()) {
                    if (!have.containsKey(key) || have.get(key) < need.get(key)) {
                        flag = false;
                        break;
                    }
                }
                // first occurence where the if condition satisfies is the minimum
                // we can get for a given i as j is increasing throughout
                if (flag && minLength > (j - i + 1)) {
                    minLength = j - i + 1;
                    minStart = i;
                    break;
                }
            }
        }
        return (minLength != Integer.MAX_VALUE) ? (s.substring(minStart, minStart + minLength)) : "";
    }
}

class Solution {
    // OPTIMAL
    // T: O(m+n), S: O(128) max as maximum unique characters is 128 
    // (upper and lowercase combined)
    public String minWindow(String s, String t) {
        int[] freq = new int[128];

        for (char ch : t.toCharArray()) {
            freq[ch]++;
        }

        int left = 0;
        int minLength = s.length() + 1;
        int need = t.length();
        int minStart = 0;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            if (freq[rightChar] > 0) {
                need--; // denotes required character is found
            }
            freq[rightChar]--; // acquire

            while (need == 0) {
                if (minLength > (right - left + 1)) {
                    minLength = right - left + 1;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                freq[leftChar]++; // release
                if (freq[leftChar] > 0) {
                    need++; // released a character that must be included
                }
                left++;
            }
        }
        return minLength > s.length() ? "" : s.substring(minStart, minStart + minLength);
    }
}