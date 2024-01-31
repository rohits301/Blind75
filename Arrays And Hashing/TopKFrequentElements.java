class Solution {
    // BRUTE FORCE
    // T: O(k*n), S: O(n)
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            int maxVal = 0;
            int maxKey = 0;
            for (int key : map.keySet()) {
                if (map.get(key) > maxVal) {
                    maxVal = map.get(key);
                    maxKey = key;
                }
            }
            ans[i] = maxKey;
            map.remove(maxKey);
        }
        return ans;
    }
}
class Solution {
    // BETTER
    // T: O(k log D), S: O(D + D = D), D = no. of distinct elements, time to build heap is O(D)
    public int[] topKFrequent(int[] nums, int k) {
        
        // create a freq. map
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        // initialize a max heap of keys
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)-> map.get(b) - map.get(a)); 

        // buildHeap in O(D)
        for(int key : map.keySet()){
            maxHeap.add(key);
        }

        // poll the top k items
        int[] res = new int[k];
        for(int i=0; i<k; i++){
            res[i] = maxHeap.poll();
        }
        return res;
    }
}