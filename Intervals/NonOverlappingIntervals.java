class Solution {
    // refer STRIVER
    // BRUTE/BETTER/OPTIMAL
    // GREEDY
    // T: O(nlogn + n); sorting + iteration
    // S: O(1);
    // similar to GFG - [N meetings in one room]
    // ans is just the inverse of the above problem
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        // 1. sort the intervals on end time in asc. order
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        // 2. if intervals[i][0] (start time) <= lastEndTime, then we can have the
        // current meeting
        // 3. so increment count and change lastEndTime to the end of this
        // for this particular question, we take "<=" as mentioned in Note.

        int count = 1;
        int lastEndTime = intervals[0][1];
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= lastEndTime) {
                count++;
                lastEndTime = intervals[i][1];
            }
        }
        // max. meetings we can have = count
        // so the min. number of intervals to remove so as to have max meetings
        // is (n - count)
        return (n - count);
    }
}
