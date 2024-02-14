class Solution {
    // BRUTE FORCE
    // T: O(m*n*128), S: O(128*2) - max map space is 128+128 unique characters (upper and lowercase combined)
    public String minWindow(String s, String t) {
        int minLength = Integer.MAX_VALUE;
        int n = s.length();
        int minStart = -1;
        Map<Character, Integer> need = new HashMap<>();

        for (char ch : t.toCharArray()) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            Map<Character, Integer> have = new HashMap<>();
            for (int j = i; j < n; j++) {
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

