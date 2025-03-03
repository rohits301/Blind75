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

class Solution {
   /**
   * Given a list of meeting time intervals, determine if a person can attend all meetings.
   * 
   * Steps:
   * 1. Sort the intervals by their **start time**.
   * 2. Iterate through the sorted list and check if any meeting **overlaps** with the previous one.
   * 3. If an overlap is found (`start time of current < end time of previous`), return `false`.
   * 4. Otherwise, return `true` (no overlaps).
   *
   * Time Complexity: O(n log n) -> Sorting dominates
   * Space Complexity: O(1) -> No extra space used
   * OPTIMAL
   */
    public boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return true;
        }

        // Sort intervals by start time
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));

        // Check for overlapping meetings
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start < intervals.get(i - 1).end) {
                return false; // Overlapping meeting found
            }
        }

        return true; // No overlaps
    }
}
