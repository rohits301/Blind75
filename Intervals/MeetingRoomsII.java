/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

 class Solution {
    // submit on NEETCODE
    // refer NEETCODE
    // BRUTE/BETTER/OPTIMAL
    // T: O(nlogn + n); sorting + iteration
    // S: O(2*n); start and end arrays
    // test case: [[0,30],[5,10],[10,20]]
    public int minMeetingRooms(List<Interval> intervals) {
        //APPROACH - 
        // 1. sort both start and end times in separate arrays
        // 2. if a meeting is already on and the previous has not ended
        // then we need new meeting room, hence, count++
        // 3. If meeting have times like, start[s] == end[e]
        // then, we first end the previous meeting (execute end[e] first)
        // & then begin the next meeting
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];

        for(int i=0; i<n; i++){
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int s = 0, e = 0; // start and end indices for iteration
        int res = 0, count = 0; // result and count
        while(s < n){
            if(start[s] < end[e]){
                s++;
                count++;
            } else {
                // when the start[s] is >= end[e]
                // i.e, when the both arrays have same value
                // then we first end the existing meeting and then start next
                e++;
                count--;
            }
            res = Math.max(res, count); // max no. of rooms required
        }
        return res;
    }
}
