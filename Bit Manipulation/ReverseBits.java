public class Solution {

    // refer NEETCODE
    public int reverseBits(int n) {
        // approach
        // 1. right shift the number and take "&" with 1
        // 2. this gives us, whether the bit should be set
        // e.g. n=0010 (i=0)
        // 0010 & 1 => 0000
        // next iteration, (i=1) and n = 001 (after right shift)
        // 001 & 1 => 001 so the bit is set
        // 3. now for making the result we want it in reverse order
        // 4. so, taking OR (|) with shifting the bit to (31-i)
        // res = 0
        // res = 0000...0 (i=0);
        // res = 0100...0 (i=30);

        // code
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int bit = ((n >> i) & 1);
            res |= (bit << (31 - i));
        }
        return res;
    }
}
