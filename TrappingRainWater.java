class Solution {
    // BRUTE FORCE
    // refer STRIVER video and NEETCODE code
    // T: O(n^2) - For each index, we scan both left and right to find max heights.
    // S: O(1)
    public int trap(int[] height) {
        int n = height.length; 
        int ans = 0; 

        for (int i = 0; i < n; i++) {
            int leftMax = height[i];  // Maximum height on the left of index 'i' (including itself)
            int rightMax = height[i]; // Maximum height on the right of index 'i' (including itself)

            // Find the maximum height to the left of index 'i'
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }

            // Find the maximum height to the right of index 'i'
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            /**
             * The amount of water stored at index 'i' is determined by:
             *  - The minimum of the highest bars to the left and right.
             *  - Subtracting the height of the current bar.
             */
            ans += Math.min(leftMax, rightMax) - height[i];
        }

        return ans; 
    }
}
