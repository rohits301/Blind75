class Solution {
    // refer NEETCODE
    // T: O(1)
    // S: O(1)
    public int getSum(int a, int b) {
        // sum without carry = a ^ b
        // sum with carry = ((a & b) << 1) - & operation and left shift by 1
        // we left shift by 1 because carry is forwarded, so it represents left shift
        // -ve integers are auto handled by the language
        // stopping condition for loop -> until carry is zero

        while(b != 0){
            int temp = ((a & b) << 1);
            a = (a ^ b);
            b = temp;
        }
        return a;
    }
}
