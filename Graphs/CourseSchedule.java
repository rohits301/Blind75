class Solution {
    // refer STRIVER
    // T: O(V+E); numCourses + prerequistes.length
    // S: O(V+E) + O(V); adjacency list + topo list
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 2 approaches are possible
        // 1. DFS for detecting cycle
        // 2. BFS for topo sort (if topo sort is possible, then true, else false)

        List<List<Integer>> adj = new ArrayList<>();
        int V = numCourses; // no. of vertices
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        int m = prerequisites.length; // no. of edges
        for (int i = 0; i < m; i++) {
            int ai = prerequisites[i][0];
            int bi = prerequisites[i][1];
            adj.get(bi).add(ai); // directed edges
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

        // 3. create a topo list for storing order of topo sort
        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            // 4. remove from queue
            // add to topo list
            // decrement the indegree of nbrs
            // if indegree of nbr == 0
            // add them to queue
            int val = q.poll();
            topo.add(val);

            for (int nbr : adj.get(val)) {
                indegree[nbr]--;
                if (indegree[nbr] == 0) {
                    q.add(nbr);
                }
            }
        }

        // if the order is valid, then topo.size() = V
        return (topo.size() == V) ? true : false; 
    }
}
