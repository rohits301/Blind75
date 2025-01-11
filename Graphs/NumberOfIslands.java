class Solution {
    // refer NEETCODE
    // BFS
    // T: O(V+E)
    // S: O(V+E)
    private static final int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];
        int islands = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == '1' && vis[i][j] == false){
                    bfs(i,j, vis, grid);
                    islands += 1;
                }
            }
        }
        return islands;
    }

    private void bfs(int i, int j, boolean[][] vis, char[][] grid){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        vis[i][j] = true;

        while(!q.isEmpty()){
            int[] node = q.poll();
            int r = node[0];
            int c = node[1];

            for(int[] direction : directions){
                int nr = r + direction[0];
                int nc = c + direction[1];
                if(nr >= 0 && nr < grid.length
                   && nc >= 0 && nc < grid[0].length 
                   && grid[nr][nc] != '0'
                   && vis[nr][nc] != true){
                        q.add(new int[]{nr, nc});
                        vis[nr][nc] = true;
                }
            }
        }
    }
    
}
