class Solution {
    // STRIVER's Video for reference
    // BF - O(n^3), Better - O(nlogn), Optimal - O(n) time and space both
    // the iteration in inner while is max. n so O(2n = n) time
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int longest = 0;

        // Store all the elements in a set to avoid duplicates as they do not affect our calcn.
        for(int num : nums){
            set.add(num);
        }
        
        // iterate thru all elements in set
        for(int num : set){
            int x = 1, count=1;
            // check if num is a valid starting point, i.e., there is no 'num-1' smaller than num in the set
            if(!set.contains(num-1)){
                while(set.contains(num+x)){
                    x += 1;
                    count += 1;
                }
                longest = Math.max(count, longest);
            }
        }
        return longest;
    }
}