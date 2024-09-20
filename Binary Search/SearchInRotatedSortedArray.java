class Solution {
    // BRUTE FORCE
    // Linear Search
    // T: O(n), S: O(1)
    public int search(int[] nums, int target) {
        int ans = -1;

        for(int i=0; i<nums.length; i++){
            if(nums[i] == target){
                ans = i;
                break;
            }
        }
        return ans;
    }
}

class Solution {
    // OPTIMAL
    // refer Neetcode's video
    // T: O(log n), S: O(1)
    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // left sorted half
            if (nums[lo] <= nums[mid]) {
                if (target < nums[lo] || target > nums[mid]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            } else {
                // right sorted half
                if (target > nums[hi] || target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
        }
        return -1;
    }
}