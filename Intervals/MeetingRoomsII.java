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
    // BRUTE/BETTER/OPTIMAL - TWO POINTER
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
    // MIN-HEAP APPROACH (INTUITVE), PREFERRED
    // refer ChatGPT for understanding
    // Time Complexity: O(n log n) + O(n log n) = O(n log n)
    //   - Sorting the intervals: O(n log n)
    //   - Adding/removing from min-heap (for n intervals): O(n log n)
    // Space Complexity: O(n) (Heap stores at most n end times)
    
    public int minMeetingRooms(List<Interval> intervals) {
        // If there are no meetings, no rooms are required
        if (intervals.isEmpty()) return 0;
        
        // Step 1: Sort intervals based on start times
        // Sorting ensures that we process meetings in chronological order
        intervals.sort((a, b) -> a.start - b.start);

        // Step 2: Min-heap to store meeting end times
        // - The heap helps track the earliest ending meeting
        // - If a new meeting can reuse an existing room, we remove the earliest ending meeting
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (Interval interval : intervals) {
            int start = interval.start;
            int end = interval.end;

            // If a room is free (i.e., the earliest ending meeting has ended before the new meeting starts), reuse it
            if (!minHeap.isEmpty() && minHeap.peek() <= start) {
                minHeap.poll(); // Remove the earliest meeting that has ended
            }

            // Allocate a new room (or reuse an existing one) by adding the meeting's end time to the heap
            minHeap.offer(end);
        }

        // The number of elements in the heap represents the number of meeting rooms required
        return minHeap.size();
    }
}
