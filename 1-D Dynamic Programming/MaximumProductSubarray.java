class Solution {
    // BRUTE FORCE
    // refer STRIVER
    // NC is complex soln.
    // T: O(n^3), S: O(1)
    // generate all subarrays and find max product
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int prd = 1;
                for (int k = i; k <= j; k++) {
                    prd = prd * nums[k];
                }
                max = Math.max(max, prd);
            }
        }
        return max;
    }
}

class Solution {
    // BETTER
    // T: O(n^2), S: O(1)
    // only find the product of subarrays and max among them
    public int maxProduct(int[] nums) {
        int max = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int prd = nums[i];
            max = Math.max(max, prd); // for case when nums[i] is greatest among all products
            for (int j = i + 1; j < nums.length; j++) {
                prd = prd * nums[j];
                max = Math.max(max, prd);
            }
        }
        return max;
    }
}

class Solution {
    // OPTIMAL
    // T: O(n), S: O(1)
    // based on observation
    // prefix and suffix product
    // split on 0, because 0 will make product 0, so reset prefix and suffix to 1
    // 4 cases
    // 1. all +ve
    // 2. even -ve
    // 3. odd -ve
    // 4. zeroes 
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        int prefix = 1, suffix = 1;

        for (int i = 0; i < n; i++) {
            prefix *= nums[i];
            suffix *= nums[n - i - 1];
            max = Math.max(max, Math.max(prefix, suffix));

            // for next iteration to avoid considering a 0 
            // virtually splitting array on 0
            if (prefix == 0) {
                prefix = 1;
            }
            if (suffix == 0) {
                suffix = 1;
            }
        }
        return max;
    }
}
