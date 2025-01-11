class Solution {
    // refer NEETCODE
    public int[] countBits(int n) {
        // BRUTE
        // T: O(nlogn); n * logn
        // S: O(n); ans space
        // approach - 
        /*
        1. for every integer upto n, 
        loop and call a function findOnes(i)
        2. findOnes(i)
        for every integer "i" until i>0
        i mod 2 -> gives counts of 1
        i = i/2 -> after every iteration
        3. this (log n) operation is performed n times
        4. hence, nlogn
        */
        //OPTIMAL
        // DP
        // T: O(n); iteration of dp array
        // S: O(n); ans space
        // e.g. n=8
        // it repeats after an offset
        // offset is power of 2.
        // we add 1 to previous value
        // code
        int[] dp = new int[n+1]; // intially all are 0
        int offset = 1;
        for(int i=1; i<=n; i++){
            if(offset * 2 == i){
                offset = i;
            }
            dp[i] = 1 + dp[i - offset];
        }
        return dp;
    }
}
