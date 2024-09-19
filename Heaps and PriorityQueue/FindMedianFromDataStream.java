class MedianFinder {
    // Brute Force - by NEETCODE
    // TLE in most situations
    // T: O(n) for every add operation n = no. of elements in the list
    // findMedian - O(1)
    // S: O(n)
    private static ArrayList<Integer> list;

    public MedianFinder() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        int i = 0;
        while (i < list.size() && num >= list.get(i)) {
            i++;
        }

        // add(index, num) -> takes O(n) time, shifts current element at the index to
        // the right
        list.add(i, num);
    }

    public double findMedian() {
        int n = list.size();
        if (n % 2 == 0) {
            return ((double) list.get((n / 2) - 1) + (double) list.get(n / 2)) / 2;
        }
        return (double) list.get(n / 2);
    }
}

class MedianFinder {
    // Refer NEETCODE
    // OPTIMAL
    // T: O(log n) for each addNum() call - n = no. of elements in the heap
    // S: O(n)
    PriorityQueue<Integer> smallHeap; // maxHeap holding small numbers
    PriorityQueue<Integer> largeHeap; // minHeap holding large numbers

    public MedianFinder() {
        smallHeap = new PriorityQueue<>(Collections.reverseOrder());
        largeHeap = new PriorityQueue<>(); // default is minHeap in java
    }

    public void addNum(int num) {
        smallHeap.add(num);

        // every element in smallHeap should be <= every element in largeHeap
        if (!smallHeap.isEmpty() && !largeHeap.isEmpty()
                && smallHeap.peek() > largeHeap.peek()) {
            largeHeap.add(smallHeap.poll());
        }

        // uneven size, so shift elements so that difference of sizes is atmost 1
        if (smallHeap.size() > largeHeap.size() + 1) {
            largeHeap.add(smallHeap.poll());
        }

        if (largeHeap.size() > smallHeap.size() + 1) {
            smallHeap.add(largeHeap.poll());
        }
    }

    public double findMedian() {
        if (smallHeap.size() > largeHeap.size()) {
            return (double) smallHeap.peek();
        } else if (largeHeap.size() > smallHeap.size()) {
            return (double) largeHeap.peek();
        } else {
            // same size => even no. of elements
            return (smallHeap.peek() + largeHeap.peek()) / 2.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
