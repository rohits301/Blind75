class Solution {
    // BRUTE and OPTIMAL are same
    // refer NeetCode video
    // T: O(n), S: O(n)
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (st.isEmpty() && (ch == ')' || ch == ']' || ch == '}')) {
                return false;
            } else {
                if (ch == ')' && st.peek() == '(' ||
                    ch == ']' && st.peek() == '[' ||
                    ch == '}' && st.peek() == '{') {
                    st.pop();
                } else {
                    st.push(ch);
                }
            }
        }
        return st.isEmpty();
    }
}