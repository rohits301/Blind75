class Solution {
    // Similar to course schedule - I
    // refer STRIVER
    // BRUTE/BETTER/OPTIMAL
    // T: O(V + E)
    // S: O(V + E)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int V = numCourses; // no. of vertices
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        int m = prerequisites.length; // no. of edges
        for (int i = 0; i < m; i++) {
            int ai = prerequisites[i][0];
            int bi = prerequisites[i][1];
            adj.get(bi).add(ai);// directed edges
        }

        // Kahn's algo - TOPO sort
        // 1. create indegree[]
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int nbr : adj.get(i)) {
                indegree[nbr]++;
            }
        }

        // 2. add elements with indegree == 0 to queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // 3. create a topo array for storing order of topo sort
        int[] topo = new int[V];
        int idx = 0;
        while (!q.isEmpty()) {
            // 4. remove from queue
            // add to topo list
            // decrement the indegree of nbrs
            // if indegree of nbr == 0
            // add them to queue
            int val = q.poll();
            topo[idx++] = val;

            for (int nbr : adj.get(val)) {
                indegree[nbr]--;
                if (indegree[nbr] == 0) {
                    q.add(nbr);
                }
            }
        }

        // in case of cycle, or invalid topo sort
        // the array is still populated
        // but the idx != V
        // Test Case: 
        // prerequisites = [[0,1],[1,0]]
        // numCourses = 2
        if(idx == V){
            return topo;
        } else {
            return new int[]{};
        }
    }
}
