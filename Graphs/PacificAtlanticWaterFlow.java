class Solution {
    private final int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    
    // refer NEETCODE
    // T: O(m*n)
    // S: O(m*n)
    // DFS
    // the first row & first col, these elements can reach pacific ocean, 
    // so check if they can reach atlantic as well
    // similarly, last row & last col, these can reach atlantic, 
    // so check if they can reach pacific as well
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        // top and left = pac
        // bottom and right = atl

        for (int i = 0; i < m; i++) {
            dfs(i, 0, heights, pac); // left
            dfs(i, n - 1, heights, atl); // right
        }

        for (int j = 0; j < n; j++) {
            dfs(0, j, heights, pac); // top
            dfs(m - 1, j, heights, atl); // bottom
        }

        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         System.out.print(pac[i][j] ? "P  " : ".  ");
        //     }
        //     System.out.println();
        // }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pac[i][j] && atl[i][j]) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }

        return ans;
    }

    private void dfs(int r, int c, int[][] heights, boolean[][] vis) {
        vis[r][c] = true;

        for (int[] d : directions) {
            int nr = r + d[0]; // new row
            int nc = c + d[1]; // new col

            if (nr >= 0 && nr < heights.length && 
                nc >= 0 && nc < heights[0].length && 
                vis[nr][nc] != true && 
                heights[nr][nc] >= heights[r][c]) {
                    // water flows downhill
                    // so height(nr, nc) should be >= height(r,c)
                    dfs(nr, nc, heights, vis);
            }
        }
    }
}
