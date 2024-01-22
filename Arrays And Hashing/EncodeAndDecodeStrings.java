/*
 * Premium problem, hence, not submitted anywhere
 */
// From LintCode, T: O(n), S: O(n) - n = total number of characters in all strings combined in strs
public class Solution {
    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        // write your code here
        StringBuilder encoded = new StringBuilder();
        for(String str: strs){
            encoded.append(str.length()).append("#").append(str);
        }
        return encoded.toString();
    }

    /*
     * @param str: A string
     * @return: decodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        // write your code here
        int i=0;
        List<String> ans = new ArrayList<>();
        while(i < str.length()){
            int j=i;
            while(str.charAt(j) != '#') j++;

            int len = Integer.valueOf(str.substring(i, j));
            i = j+1+len;
            ans.add(str.substring(j+1, i));
        }
        return ans;
    }
}