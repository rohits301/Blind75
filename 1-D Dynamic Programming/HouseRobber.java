class Solution {
    // refer STRIVER
    // Recursion (BRUTE FORCE)
    // T: O(2^n); two choices every time
    // S: O(n); stack space
    public int rob(int[] nums) {
        int n = nums.length;
        return helper(n - 1, nums);
    }

    private int helper(int idx, int[] nums) {
        if (idx == 0) {
            return nums[idx];
        }
        if (idx < 0) {
            return 0;
        }

        int pick = nums[idx] + helper(idx - 2, nums); // skipping adjacent value
        int notPick = 0 + helper(idx - 1, nums);

        return Math.max(pick, notPick);
    }
}

class Solution {
    // refer STRIVER
    // Memoization (BETTER)
    // T: O(n); saving unnecessary calls
    // S: O(n) + O(n); stack space + dp array space
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return helper(n - 1, dp, nums);
    }

    private int helper(int idx, int[] dp, int[] nums) {
        if (idx == 0) {
            return nums[idx];
        }
        if (idx < 0) {
            return 0;
        }

        if(dp[idx] != -1){
            return dp[idx];
        }

        int pick = nums[idx] + helper(idx - 2, dp, nums);
        int notPick = 0 + helper(idx - 1, dp, nums);

        return dp[idx] = Math.max(pick, notPick);
    }
}

class Solution {
    // refer STRIVER
    // Tabulation (EVEN BETTER)
    // T:O(n); saving unnecessary calls
    // S:O(n); only dp array space
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            int pick = nums[i];
            if (i > 1) {
                pick += dp[i - 2];
            }
            int notPick = 0 + dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[n - 1];
    }
}

class Solution {
    // refer STRIVER
    // Tabulation (OPTIMAL - SPACE OPTIMIZED)
    // T:O(n); saving unnecessary calls
    // S:O(1); no extra space reqd.
    // we only need last two to calculate the answer
    public int rob(int[] nums) {
        int n = nums.length;
        int prev = nums[0];
        int prev2 = 0;

        for (int i = 1; i < n; i++) {
            int pick = nums[i];
            if (i > 1) {
                pick += prev2;
            }
            int notPick = 0 + prev;
            int curr = Math.max(pick, notPick);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }
}