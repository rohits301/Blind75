class Solution {
    // BRUTE FORCE
    // T: O(nlogn); for sorting
    // S: O(n); for converting to arrays
    // for two anagrams, their sorted arrays/strings must be equal
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        Arrays.sort(s1);
        Arrays.sort(t1);

        // compare whether two arrays are equal
        return Arrays.equals(s1, t1);
    }
}

class Solution {
    // BETTER
    // T: O(2*n); for frequency maps, 2 iterations
    // S: O(n); for map
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        // increase frequency of characters of "s"
        // decrease frequency of characters of "t"
        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            map.put(ch1, map.getOrDefault(ch1, 0) + 1);
            map.put(ch2, map.getOrDefault(ch2, 0) - 1);
        }

        for (int freq : map.values()) {
            if (freq != 0) {
                return false;
            }
        }
        return true;
    }
}

class Solution {
    // OPTIMAL
    // T: O(2*n); 2 iterations of the frequency array
    // S: O(1)
    // FOLLOW-UP: UNICODE CHARACTERS -> int[] freq = new int[128]
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // a freq array of size 26 to map all characters
        int[] freq = new int[26];

        // increase frequency of characters of "s"
        // decrease frequency of characters of "t"
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for (int f : freq) {
            if (f != 0) {
                return false;
            }
        }
        return true;
    }
}
