class Solution {
    // Linear Search - STRIVER video and NC code
    // can use Binary search to find index but have to do
    // O(n) iteration for merging the intervals.
    // refer LC editorial
    // T: O(n); 
    // S: O(1); array space is allowed in ques.
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> res = new ArrayList<>();

        int i = 0;
        // left portion
        // newInterval start > end of arr[i]
        while(i < n && intervals[i][1] < newInterval[0]){
            res.add(intervals[i]);
            i++;
        }
        // middle portion (might overlap with new Interval)
        // newInterval end <= start of arr[i]
        while(i < n && intervals[i][0] <= newInterval[1]){
            // for merging
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]); // min start
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]); // max end
            i++;
        }
        res.add(newInterval);
        // right portion
        // rest of the array items after merging
        // here the start of arr[i] > end of newInterval 
        while(i < n){
            res.add(intervals[i]);
            i++;
        }
        return res.toArray(new int[res.size()][]);
    }
}
