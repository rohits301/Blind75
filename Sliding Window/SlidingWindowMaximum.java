class Solution {
    // refer STRIVER
    // BRUTE FORCE
    // T: O(n*k); 
    // S: O(n-k+1); answer array
    // Iterate over the array and find max for each window of size k
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n-k+1];
        for(int i=0; i<(n-k+1); i++){
            ans[i] = nums[i];
            for(int j=i; j<i+k; j++){
                ans[i] = Math.max(ans[i], nums[j]);
            }
        }

        return ans;
    }
}

class Solution {
    // refer STRIVER
    // OPTIMAL
    // T: O(n); O(n) for iteration + O(n) for removing from queue
    // S: O(n-k+1) + O(); answer array + extra space for array
    // Keep a Deque which maintains the max in window of size k
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int idx = 0;

        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            if(!q.isEmpty() && q.peek() == (i-k)){
                q.poll(); // remove from start the elements which are not part of the window
            }

            while(!q.isEmpty() && nums[q.peekLast()] < nums[i]){
                q.pollLast(); //remove from last the elements that are smaller
            }
            q.offer(i);

            if(i >= k-1){
                ans[idx++] = nums[q.peek()];
            }
        }

        return ans;
    }
}
