class Solution {
    // refer STRIVER
    // BRUTE FORCE
    // T: O(nlogn) + O(2n); sorting + loop, every element is visited twice at max, once in the outer for to decide whether already contained in a merged interval
    // and the next time when merging the interval 
    // S: O(n); answer array
    // e.g. [[1,3],[2,6],[8,9],[9,12],[8,10],[2,4],[15,18],[16,17]]
    // sorted -> [16,17]
    // [1,6], [8,12], [15,18] - res
    // [16,17] - 17 <= 18
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        // APPROACH -
        // STEP 1.
        // sort the array on starting index
        // STEP 2.
        // if the end idx of intervals[i] is less than the end index of last element in result
        // continue as the intervals[i] is already in the merged range
        // else
        // for all j = i+1 to n (all other values)
        // evaluate if the intervals overlap and then update the end index
        // add the new start and end to the result

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 0; i < n; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            // already contained in the interval
            // current end idx <= end idx of last element in result
            // 4 <= 6
            if (!res.isEmpty() && end <= res.get(res.size() - 1)[1]) {
                continue;
            }

            for (int j = i + 1; j < n; j++) {
                // the merged interval will have the end index as max of both end and intervals[j][1]
                if (intervals[j][0] <= end) {
                    end = Math.max(end, intervals[j][1]);
                } else {
                    break;
                }
            }
            res.add(new int[] { start, end });
        }
        return res.toArray(new int[res.size()][]);
    }
}

class Solution { 
    // refer STRIVER
    // OPTIMAL
    // single-pass solution
    // T: O(nlogn); sorting
    // S: O(n); answer array
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        
        // STEP 1: Sort the array on starting index, 
        // if the start is same, sort by end index in ascending order
        Arrays.sort(intervals, (a, b) -> {
            int compareStart = Integer.compare(a[0], b[0]);
            if(compareStart == 0) {
                return Integer.compare(a[1], b[1]); // Ascending order for end index
            }
            return compareStart;
        });

        // STEP 2: Iterate through intervals
        for (int i = 0; i < n; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            // Check if result list is empty or if current interval does not overlap with the last one
            if (res.isEmpty() || start > res.get(res.size() - 1)[1]) {
                // No overlap, so add the interval to result
                res.add(intervals[i]);
            } else {
                // Overlapping interval, so merge by updating the end
                int lastEnd = res.get(res.size() - 1)[1];
                res.get(res.size() - 1)[1] = Math.max(lastEnd, end);
            }
        }

        return res.toArray(new int[res.size()][]); // Convert list to array
    }
}

