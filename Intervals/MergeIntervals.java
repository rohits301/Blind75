class Solution {
    // refer STRIVER
    // BRUTE FORCE
    // T: O(nlogn) + O(2n); sorting + loop, every element is visited twice at max
    // S: O(n); answer array
    // e.g. [[1,3],[2,6],[8,9],[9,12],[8,10],[2,4],[15,16],[16,17]]
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
        // APPROACH -
        // STEP 1.
        // sort the array on starting index
        // STEP 2.
        // if the start of intervals[i] is less than the end index of lastElement in result
        // then we have a new interval, add it to list
        // else
        // update the end index of the lastElement with max(lastElement end index, intervals[i][1])

        // this is the intended sorting as per the solution video.
        Arrays.sort(intervals, (a, b) -> {
            int compareResult = Integer.compare(a[0], b[0]);
            if(compareResult == 0){
                return Integer.compare(b[1], a[1]); // desc. of end indices
            }
            return compareResult;
        });

        for (int i = 0; i < n; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            // start of intervals[i] is greater than the end index of last merged interval -> cannot be merged, add separately
            if (res.isEmpty() || start > res.get(res.size() - 1)[1]) {
                res.add(intervals[i]);
            } else {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], intervals[i][1]);
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}
