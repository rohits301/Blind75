class Solution {
    // refer NEETCODE
    // T: O(n*m)
    // S: O(n*m)
    // DFS
    // the first row & first col, these elements can reach pacific ocean, so check
    // if they can reach atlantic as well
    // similarly, last row & last col, these can reach atlantic, so check if they
    // can reach pacific as well

    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();

        int n = heights.length;
        int m = heights[0].length;
        boolean[][] pac = new boolean[n][m];
        boolean[][] atl = new boolean[n][m];

        for (int r = 0; r < n; r++) {
            dfs(r, 0, pac, heights);
            dfs(r, m - 1, atl, heights);
        }
        for (int c = 0; c < m; c++) {
            dfs(0, c, pac, heights);
            dfs(n - 1, c, atl, heights);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pac[i][j] && atl[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void dfs(int r, int c, boolean[][] vis, int[][] heights) {

        vis[r][c] = true;
        for (int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr >= 0 && nr < heights.length
                    && nc >= 0 && nc < heights[0].length
                    && heights[nr][nc] >= heights[r][c]
                    && vis[nr][nc] != true) {
                dfs(nr, nc, vis, heights);
            }
        }
    }

}
