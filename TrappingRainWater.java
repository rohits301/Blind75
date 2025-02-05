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

class Solution {
    // BETTER
    // refer STRIVER video and NEETCODE code
    // pre-compute prefix sum (leftMax) and suffix sum (rightMax)
    // this saves time by using O(2n) space
    // T: O(n) - Three passes: one for leftMax, one for rightMax, and one for computing trapped water.
    // S: O(n) - Two auxiliary arrays (leftMax, rightMax) of size n.
    public int trap(int[] height) {
        int n = height.length; 
        int ans = 0; 
        int[] leftMax = new int[n];  
        int[] rightMax = new int[n]; 

        // Compute the prefix max array (leftMax)
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // Compute the suffix max array (rightMax)
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        for (int i = 0; i < n; i++) {
            // Water trapped at index i = min(leftMax[i], rightMax[i]) - height[i]
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return ans; 
    }
}

class Solution {
    // OPTIMAL
    // TWO-POINTER
    // refer STRIVER video
    // INTUITION
    /** 
     * Instead of precomputing prefix (`leftMax[]`) and suffix (`rightMax[]`) arrays,  
     * we calculate them dynamically to save space.  
     *
     * To determine the trapped water at each index, we use:
     *     min(leftMax, rightMax) - height[i]
     *
     * **Key Idea:**  
     * - If we only have one boundary available at any step, it must be the **minimum** one.
     * - If `height[left] <= height[right]`, we are sure that the left height is smaller,  
     *   and there exists some height on the right that is **equal or greater** than `height[left]`.  
     *   This guarantees that water can be trapped from the **right end**.
     *
     * **Logic Breakdown:**  
     * - If `height[left] >= leftMax` → No water is trapped at `left` (since there’s no left boundary).  
     *   - Update `leftMax` to ensure it can serve as a boundary for future buildings.
     * - Else (`height[left] < leftMax`) → Water can be trapped.  
     *   - Water trapped at `left = leftMax - height[left]`.
     * IMP. - 
     * The ans = leftMax - height[left] works because we have crossed the values at left and all those values are <= height[right], hence
     * the leftMax is always <= height[right]
     * Similarly, for `right`:  
     * - We only enter the right case when `height[right] < height[left]`,  
     *   meaning the left boundary is **strong enough** to trap water.  
     * - We then check whether `rightMax` is high enough to trap water on the right side.
     */
    
    // T: O(n);
    // S: O(1);
    public int trap(int[] height) {
        int n = height.length; 
        int ans = 0; 
        int leftMax = 0, rightMax = 0;
        int left = 0, right = n-1; 

        while(left <= right) {

            if(height[left] <= height[right]){
                if(height[left] >= leftMax){
                    leftMax = height[left];
                } else {
                    ans += leftMax - height[left];
                }
                left++;
            } else {
                if(height[right] >= rightMax){
                    rightMax = height[right];
                } else {
                    ans += rightMax - height[right];
                }
                right--;
            }
        }

        return ans; 
    }
}
