class Solution {
    // refer STRIVER
    // BRUTE FORCE
    // RECURSION
    // T: O(2^n); going through all possible subsequences
    // S: O(n); stack space
    public int lengthOfLIS(int[] nums) {
        // APPROACH - 
        // we need two variables to keep track of LIS
        // index & prevIndex
        // index -> becoz. array problem, this is how we will try the options for the current index
        // prevIndex -> we require increasing subsequence, so we need to keep track which was the last [valid] starting point
        // two calls -> take and notTake
        // Take
        // either the prevIndex == -1, i.e.,the first element case
        // or the arr[index] > arr[prevIndex], 
        // same result -> take and update the index 
        // Not Take
        // shift the index, prevIndex remains same

        return dfs(nums, 0, -1);
    }
    
    private int dfs(int[] nums, int idx, int prevIdx){
        if(idx == nums.length){
            return 0;
        }

        // not take
        int len = 0 + dfs(nums, idx+1, prevIdx);
        // take
        if(prevIdx == -1 || nums[idx] > nums[prevIdx]){
            len = Math.max(len, 1 + dfs(nums, idx + 1, idx));
        }
        return len;
    }

}

class Solution {
    // refer STRIVER
    // BETTER
    // TOP-DOWN (MEMOIZATION)
    // T: O(n*n);
    // S: O(n*n) + O(n); dp size + stack space
    public int lengthOfLIS(int[] nums) {
        // APPROACH -
        // we need two variables to define a state => 2D dp[][]
        // since we cannot have -ve indices in array
        // shift the index to right
        // => prevIdx -> prevIdx + 1

        // dp[i][prevIdx+1] -> LIS till i, prevIdx

        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int[] ar : dp) {
            Arrays.fill(ar, -1);
        }

        return dfs(nums, 0, -1, dp);
    }

    private int dfs(int[] nums, int idx, int prevIdx, int[][] dp) {
        if (idx == nums.length) {
            return 0; // no element here, so no subsequence to consider
        }

        if (dp[idx][prevIdx + 1] != -1) {
            return dp[idx][prevIdx + 1];
        }
        // not take
        int len = 0 + dfs(nums, idx + 1, prevIdx, dp);
        // take
        if (prevIdx == -1 || nums[idx] > nums[prevIdx]) {
            len = Math.max(len, 1 + dfs(nums, idx + 1, idx, dp));
        }
        return dp[idx][prevIdx + 1] = len;
    }

}

class Solution {
    // refer STRIVER
    // BETTER
    // BOTTOM-UP TABULATION
    // T: O(n*n);
    // S: O(n*n); dp size
    public int lengthOfLIS(int[] nums) {
        // APPROACH -
        // we need two variables to define a state => 2D dp[][]
        // since we cannot have -ve indices in array
        // shift the index to right
        // => prevIdx -> prevIdx + 1

        // dp[i][prevIdx+1] -> LIS till i, prevIdx

        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1];

        // by defn. prevIdx = idx - 1
        // so, loops are ->
        // i -> n-1 to 0
        // prevIdx -> i - 1 -> -1
        for (int i = n - 1; i >= 0; i--) {
            for (int prevIdx = i - 1; prevIdx >= -1; prevIdx--) {
                // not take
                int len = 0 + dp[i + 1][prevIdx + 1]; // becoz. prevIdx is stored in "+1" state
                // take
                if (prevIdx == -1 || nums[i] > nums[prevIdx]) {
                    len = Math.max(len, 1 + dp[i + 1][i + 1]);
                }
                dp[i][prevIdx + 1] = len;
            }
        }
        return dp[0][-1 + 1]; // dp[0][prevIdx+1]
    }

}

class Solution {
    // refer STRIVER
    // BETTER
    // BOTTOM-UP TABULATION - SPACE OPTIMISED
    // T: O(n*n);
    // S: O(n); dp size
    public int lengthOfLIS(int[] nums) {
        // APPROACH -
        // we need two variables to define a state => 2D dp[][]
        // since we cannot have -ve indices in array
        // shift the index to right
        // => prevIdx -> prevIdx + 1

        // dp[i][prevIdx+1] -> LIS till i, prevIdx

        int n = nums.length;
        int[] curr = new int[n + 1];
        int[] next = new int[n + 1];

        // by defn. prevIdx = idx - 1
        // so, loops are ->
        // i -> n-1 to 0
        // prevIdx -> i - 1 -> -1
        for (int i = n - 1; i >= 0; i--) {
            for (int prevIdx = i - 1; prevIdx >= -1; prevIdx--) {
                // not take
                int len = 0 + next[prevIdx + 1]; // becoz. prevIdx is stored in "+1" state
                // take
                if (prevIdx == -1 || nums[i] > nums[prevIdx]) {
                    len = Math.max(len, 1 + next[i + 1]);
                }
                curr[prevIdx + 1] = len;
            }
            int[] temp = curr;
            curr = next;
            next = temp;
        }
        return next[-1 + 1]; // dp[0][prevIdx+1]
    }

}

class Solution {
    // refer STRIVER
    // T: O(n log n) - due to binary search in lowerBound
    // S: O(n) - for the temp list storing LIS sequence
    // the LIS sequence stored is just a sorted sequence, it is not the correct LIS sequence
    public int lengthOfLIS(int[] nums) {
        // List to store the smallest possible end elements of increasing subsequences
        List<Integer> temp = new ArrayList<>();
        temp.add(nums[0]);  // Initialize with the first element
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > temp.get(temp.size() - 1)) {
                // If nums[i] extends the LIS, append it to temp
                temp.add(nums[i]);
                count++;
            } else {
                // If nums[i] can replace an element in temp
                // Find the first index where temp[idx] >= nums[i] (lower bound)
                int lb = lowerBound(temp, nums[i]);
                temp.set(lb, nums[i]);  // Replace it to maintain the smallest possible LIS
            }
        }
        return count; // Length of temp is the length of LIS
    }

    /**
     * Finds the index of the first element in arr that is >= x
     * This is essentially the "lower bound" function using binary search.
     * If no such element exists, it returns the index where x can be placed.
     */
    private int lowerBound(List<Integer> arr, int x) {
        int lo = 0, hi = arr.size() - 1;
        int ans = arr.size(); // Default to size if x is larger than all elements

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr.get(mid) >= x) {
                ans = mid;  // Possible position for x
                hi = mid - 1; // Search in the left half
            } else {
                lo = mid + 1; // Search in the right half
            }
        }
        return ans;
    }
}
