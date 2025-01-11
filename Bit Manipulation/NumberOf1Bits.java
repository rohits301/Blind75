class Solution {
    // refer NEETCODE
    // BRUTE FORCE
    // T: O(32)
    // S: O(1)
    public int hammingWeight(int n) {
        int res = 0;
        while(n > 0){
            res += (n%2); // remainder is either 1 or 0
            // 1 => the bit is set
            // 0 => the bit is not set
            n = (n>>1); // dividing the number by 2
        }
        return res;
    }
}

class Solution {
    // refer NEETCODE
    // OPTIMAL
    // BIT MASK
    // T: O(32) but slightly more efficient
    // S: O(1)
    // jumping over the ones
    // Kernighan algo.
    public int hammingWeight(int n) {
        int res = 0;
        while (n > 0) {
            // the below operation is -
            // decrease 1 from number
            // so if it has 1, this will make it to 0
            // "&" to remove that 1 from n
            // so that the new value of "n" doesn't contain the counted 1
            n = (n & (n - 1));
            res += 1;
        }
        return res;
    }
}
