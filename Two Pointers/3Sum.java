class Solution {
    // T: O(n^2 + nlogn), S: O(no. of triplets) - OPTIMAL
    // STRIVER video solution for Brute, Better and Optimal
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        // 1. Sort the array
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            // to avoid duplicate i
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // 2. two-pointer on i and j 
            // for a given i, find j and k such that all sum to zero
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i]+nums[j]+nums[k];
                if(sum < 0){
                    j++;
                } else if(sum >0){
                    k--;
                } else {
                    // sum == 0, found the triplet
                    List<Integer> list = new ArrayList<>();
                    Collections.addAll(list, nums[i],nums[j],nums[k]);
                    ans.add(list);
                    j++;
                    k--;

                    // to avoid duplicate j and duplicate k
                    while(j<k && nums[j] == nums[j-1]) j++;
                    while(j<k && nums[k] == nums[k+1]) k--;
                }
            }
        }
        return ans;
    }
}