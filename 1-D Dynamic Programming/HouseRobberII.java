class Solution {
    // refer STRIVER
    // OPTIMAL 
    // building upon solution for house robber 1
    // T: O(n); time taken by helper function is O(n)
    // S: O(n); space taken by both lists

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        List<Integer> a1 = new ArrayList<>(); // 0 to n-2
        List<Integer> a2 = new ArrayList<>(); // 1 to n-1

        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                a1.add(nums[i]);
            }
            if (i != 0) {
                a2.add(nums[i]);
            }
        }

        return Math.max(helper(a1), helper(a2));
    }

    private int helper(List<Integer> arr) {
        int n = arr.size();
        int prev2 = 0;
        int prev = arr.get(0);

        for (int i = 1; i < n; i++) {
            int take = arr.get(i);
            if (i > 1) {
                take += prev2;
            }
            int notTake = prev;

            int curr = Math.max(take, notTake);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }
}

class Solution {
    // refer NEETCODE
    // OPTIMAL - Space Optimized
    // building upon solution for house robber 1
    // only change is for circular array, two types of start and end
    // T: O(n); time taken by robHelper function is O(n)
    // S: O(1); space taken

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        return Math.max(robHelper(nums, 0, n - 2), robHelper(nums, 1, n - 1));
    }

    private int robHelper(int[] arr, int start, int end) {
        int prev1 = 0;
        int prev2 = 0;

        for (int i = start; i <= end; i++) {
            int temp = Math.max(prev1, prev2 + arr[i]);
            prev2 = prev1;
            prev1 = temp;
        }
        return prev1;
    }
}