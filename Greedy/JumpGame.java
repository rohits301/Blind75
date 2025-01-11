class Solution {
    // refer NEETCODE video solution and Striver video as well
    // BRUTE FORCE
    // T: O(n^n); at max, every index has "n" options, so n^n options in total
    // S: O(n) - stack space
    public boolean canJump(int[] nums) {
        return dfs(nums, 0);
    }

    private boolean dfs(int[] nums, int i) {
        if (i == nums.length - 1) {
            return true; // reached the end index so return true
        }

        int end = Math.min(nums.length - 1, i + nums[i]); // this is the maximum end index that can be reached
        // in the array size range, we take min to avoid going out of array bounds

        // at every level my options are from i+1 to end
        // in case when nums[i] = 0, end = i and loop does not execute
        // so we return false directly
        for (int jump = i + 1; jump <= end; jump++) {
            if (dfs(nums, jump)) {
                return true;
            }
        }
        return false;
    }
}

class Solution {
    // BETTER 
    // TOP-DOWN (MEMOIZATION)
    // T: O(n^2); at max, every index's options are explored once
    // S: O(n) + O(n); array size + stack space
    public boolean canJump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return dfs(nums, 0, dp);
    }

    private boolean dfs(int[] nums, int i, int[] dp){
        if(i == nums.length - 1){
            return true; // reached the end index so return true
        }

        if(dp[i] != -1){
            return (dp[i]==1)? true : false;
        }

        int end = Math.min(nums.length - 1, i+nums[i]); // this is the maximum end index that can be reached
        // in the array size range, we take min to avoid going out of array bounds

        // at every level my options are from i+1 to end
        // in case when nums[i] = 0, end = i and loop does not execue
        // so we return false directly
        for(int jump = i + 1; jump <= end; jump++){
            if(dfs(nums, jump, dp)){
                dp[jump] = 1;
                return true;
            }
        }
        dp[i] = 0;
        return false;
    }
}

class Solution {
    // BETTER
    // BOTTOM-UP (TABULATION)
    // T: O(n^2);
    // S: O(n);
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        // base case for bottom-up
        dp[n - 1] = true; // at n-1, I need no jumps, i.e., 0 jumps, to reach n-1. Hence, always true.

        // we explore all options(jumps) for each index
        // if there is even one true for a jump, that indicates, it is possible to reach
        // end index from there
        // hence, for every dp[jump] = true, we have dp[i] = true;
        // and we don't need to process for other jumps for same "i"
        for (int i = n - 2; i >= 0; i--) {
            int end = Math.min(n - 1, i + nums[i]); // this is the maximum end index that can be reached
            for (int jump = i + 1; jump <= end; jump++) {
                if (dp[jump]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

}

class Solution {
    // refer NEETCODE for all approaches
    // OPTIMAL
    // GREEDY
    // T: O(n);
    // S: O(1);
    public boolean canJump(int[] nums) {
        // re-imagine the problem
        // in the dp tabulation solution, instead of exploring all jumps, we check if
        // the jump is possible and it makes us reach the goal or cross it (>=)
        // we shift the goal post, that is, the endIndex we want to reach to "i"
        // in the end if the goal == 0, means, we have an answer.

        int goal = nums.length - 1; // initially, the goal is to reach the end of the array
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }
        return (goal == 0) ? true : false;
    }

}
