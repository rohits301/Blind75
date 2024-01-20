class Solution {
    // Using O(n) space and T: O(n)
    public boolean containsDuplicate(int[] nums) {
        
        if(nums.length == 1) return false;

        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            if(!set.isEmpty() && set.contains(num)){
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }
}