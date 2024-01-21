class Solution {
    // T: O(n), S: O(2n = n) - not the most optimal approach 
    public int[] productExceptSelf(int[] nums) {
        
        int n = nums.length;
        int[] answer = new int[n];
        int[] lp = new int[n];
        int[] rp = new int[n];
        
        // handling corner cases
        lp[0] = 1;
        rp[n-1] = 1;
        // creating left product array
        for(int i=1; i<n; i++){
            lp[i] = lp[i-1] * nums[i-1];
        }
        // creating right product array
        for(int i=n-2; i>=0; i--){
            rp[i] = rp[i+1] * nums[i+1];
        }

        for(int i=0; i<n; i++){
            answer[i] = lp[i] * rp[i];
        }
        return answer;
    }
}

/*
 * The optimal approach using O(1) extra space
 */
class Solution {
    // T: O(n), S: O(1) - constant space
        public int[] productExceptSelf(int[] nums) {
        
        int n = nums.length;
        int[] answer = new int[n];
        int left = 1, right = 1;
        // initializing corner elements
        answer[0] = 1;
        answer[n-1] = 1;
        
        // building answer with left product, that is answer[i] = product of num before i;
        for(int i=1; i<n; i++){
            left = left * nums[i-1];
            answer[i] = left;
        }
        // building answer with right product, that is, we have left product already in answer[i], so multiply it with right product of i to get final answer 
        for(int i=n-2; i>=0; i--){
            right = right * nums[i+1];
            answer[i] = answer[i] * right;
        }
        // this handles corner cases as answer[n-1] is updating in the first iteration and ans[0] is updated in the second iteration
        return answer;
    }
}