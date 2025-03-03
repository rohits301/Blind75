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
    // refer STRIVER for pre-requisite questions
    // BRUTE/BETTER/OPTIMAL
    // T: O(nlogn + n); sorting + iteration
    // S: O(1)
    // Similar to GFG - [N meetings in one room]
    public boolean canAttendMeetings(List<Interval> intervals) {
        int n = intervals.size();
        if(n == 0){
            return true; // no meeting, so can attend
        }
        intervals.sort((a,b)->Integer.compare(a.end, b.end));
        
        int count = 1;
        int lastEndTime = intervals.get(0).end;
        for(int i=1; i<intervals.size(); i++){
            if(intervals.get(i).start >= lastEndTime){
                count++;
                lastEndTime = intervals.get(i).end;
            }
        }
        return (n == count); // if the maximum meetings == total meetings, then true
    }

}
