class Solution {
    // refer NEETCODE
    // number of unique dfs = number of connected comps.
    // T: O(V+E)
    // S: O(V+E)
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge: edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] vis = new boolean[n];
        int res = 0;
        for(int i=0; i<n; i++){
            if(!vis[i]){
                dfs(i, vis, adj);
                res++;
            }
        }
        return res;
    }
    
    private void dfs(int i, boolean[] vis, List<List<Integer>> adj){
        vis[i] = true;
        
        for(int nbr: adj.get(i)){
            if(!vis[nbr]){
                dfs(nbr, vis, adj);
            }
        }
    }
}
