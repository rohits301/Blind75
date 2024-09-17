class Solution {
    // refer STRIVER
    // Brute/better/optimal
    // T: O((2^target) * k); 
    // 2^target as length of tree is target
    // assume avg. length of list generated is k
    // and time to add this `list` to answer is proportional to length of list 
    // hence, we multiple both
    // S: O(k*x); x = no. of combinations
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        helper(candidates, 0, target, ans, new ArrayList<>());
        return ans;
    }

    private void helper(int[] arr, int i, int target, List<List<Integer>> ans, List<Integer> list) {
        if (i == arr.length) {
            if (target == 0) {
                ans.add(new ArrayList<>(list));
            }
            return;
        }

        // pick
        if (arr[i] <= target) {
            list.add(arr[i]);
            helper(arr, i, target - arr[i], ans, list);
            list.remove(list.size() - 1);
        }
        // not pick
        helper(arr, i + 1, target, ans, list);
    }
}
