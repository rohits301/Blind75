/*
 * Premium problem, hence, not submitted anywhere
 */
// From LintCode, T: O(n), S: O(n) - n = total number of characters in all strings combined in strs
class Solution {

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();

        for(String str: strs){
            sb.append(str.length()).append("#").append(str);
        }
        return sb.toString();
    }

    // "i" is always on the number
    // "j" is on "#"
    // and "j+1" on first character of string
    public List<String> decode(String str) {
        List<String> strs = new ArrayList<>();
        int i = 0, j = 0;

        while(j < str.length()){
            while(str.charAt(j) != '#'){
                j++;
            }

            // now str.charAt(j) = '#'
            int len = Integer.parseInt(str.substring(i, j));
            i = j + 1 + len;
            strs.add(str.substring(j + 1, i));

            // setup for next iteration
            j = i;
        }
        return strs;
    }
}
