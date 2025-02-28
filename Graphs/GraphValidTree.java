class Solution {
    // refer NEETCODE and ChatGPT
    // T: O(V+E)
    // S: O(V+E)
    public boolean validTree(int n, int[][] edges) {
        // A valid tree must have exactly n-1 edges (otherwise, it's either disconnected or has cycles)
        if (edges.length != n - 1) {
            return false;
        }

        // Step 1: Construct adjacency list for the graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Step 2: Use DFS to check for cycles
        boolean[] vis = new boolean[n];
        boolean hasCycle = dfs(0, -1, vis, adj);
        
        if (hasCycle) {
            return false; // A valid tree should not contain cycles
        }

        // Step 3: Ensure all nodes are connected (no disconnected components)
        return allNodesVisited(vis);
    }

    /**
     * Detects if a cycle exists in an undirected graph using DFS.
     * returns True if a cycle is detected, otherwise False
     */
    private boolean dfs(int node, int parent, boolean[] vis, List<List<Integer>> adj) {
        if (vis[node]) {
            return true; // A cycle is detected if we revisit an already visited node
        }

        vis[node] = true; // Mark node as visited
        for (int nbr : adj.get(node)) {
            if (nbr == parent) {
                continue; // Skip the parent node to avoid a false cycle detection
            }

            boolean cycleFound = dfs(nbr, node, vis, adj);
            if (cycleFound) {
                return true; // If a cycle is found in any branch, return true
            }
        }
        return false; // No cycles detected in this DFS path
    }

    /**
     * Checks if all nodes were visited during DFS, ensuring the graph is fully connected.
     * returns True if all nodes were visited, otherwise False
     */
    private boolean allNodesVisited(boolean[] vis) {
        for (boolean v : vis) {
            if (!v) return false; // If any node remains unvisited, graph is disconnected
        }
        return true;
    }
}
