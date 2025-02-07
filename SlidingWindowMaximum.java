class Solution {
    // refer STRIVER
    // BRUTE FORCE
    // T: O(n*k);
    // S: O(n-k+1); answer array
    // Iterate over the array and find max for each window of size k
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        
        for (int i = 0; i < (n - k + 1); i++) {
            ans[i] = nums[i];
            for (int j = i; j < i + k; j++) {
                ans[i] = Math.max(ans[i], nums[j]);
            }
        }

        return ans;
    }
}

