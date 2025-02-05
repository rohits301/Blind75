class Solution {
    // refer STRIVER and ChatGPT
    // T: O(V+E)
    // S: O(V+E)
    // BFS Kahn's Algo.
    // We take edge from bi to ai, because bi must be completed before ai
    // For e.g. -> numCourses = 4
    // prerequisites = [[1,0],[2,0],[3,1],[3,2]]
    // adjacency list ->
    /*
     * 0 -> 1,2 (0 must complete before 1,2)
     * 1 -> 3
     * 2 -> 3
     * 3 -> []
     * So, indegree[0] = 0, as no incoming edge to 0
     * indegree[1] = 1
     * indegree[2] = 1
     * indegree[3] = 2
     * Since indegree for 0 is 0, so it can be processed first
     * hence, we take this course before its neighbors
     * With topological sort, we get the order of taking these courses
     * -> 0,1,2,3
     */

    // THIS CAN BE USED FOR CYCLE-DETECTION IN Directed Graph
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // If there are no prerequisites, all courses can be completed
        if (prerequisites.length == 0) {
            return true;
        }

        // Step 1: Build the graph (adjacency list) and compute indegrees
        List<List<Integer>> adj = new ArrayList<>();
        int v = numCourses;
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        // Build the graph based on prerequisites
        for (int i = 0; i < prerequisites.length; i++) {
            int ai = prerequisites[i][0];  // course to be taken
            int bi = prerequisites[i][1];  // course to complete before ai
            adj.get(bi).add(ai); // bi must be completed before ai, so we add the edge bi -> ai
        }

        // Step 2: Initialize indegree array (number of prerequisites for each course)
        int[] indegree = new int[v];
        for (int i = 0; i < v; i++) {
            for (int nbr : adj.get(i)) {
                indegree[nbr]++; // Each neighbor (course) gets one additional prerequisite
            }
        }

        // Step 3: Initialize the queue with all courses that have no prerequisites (indegree = 0)
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0) {
                q.offer(i);  // Add course to queue if it has no prerequisites
            }
        }

        // Step 4: Process courses and update indegree for dependent courses
        int count = 0; // To keep track of the number of courses processed
        while (!q.isEmpty()) {
            int val = q.poll();  // Process course
            count++;  // Increment count of completed courses

            // Process each course that depends on the current course
            for (int nbr : adj.get(val)) {
                indegree[nbr]--; // Reduce indegree as we process the current course
                if (indegree[nbr] == 0) {
                    q.offer(nbr);  // Add the course to queue if it can now be processed (indegree = 0)
                }
            }
        }

        // Step 5: If all courses are processed, return true, else return false (cycle detected)
        // If count equals numCourses, all courses can be finished.
        // If a cycle exists, some courses will always have indegree > 0,
        // preventing them from being added to the queue.
        return (count == v); 
    }
}
