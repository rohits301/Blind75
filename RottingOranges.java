class Solution {
    // refer STRIVER
    // T: O(m*n); grid iteration: m*n + queue can have max m*n elements + m*n*4 (for all 4 directions) -> m*n + m*n*4
    // S: O(m*n); queue can have at max all m*n oranges 
    // for the case when all are rotten

    // Directions for 4-way adjacent cells (up, left, right, down)
    private static final int[][] directions = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0; // Count of fresh oranges
        
        // Initialize queue with initially rotten oranges and count fresh ones
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] { i, j });
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        // If there are no fresh oranges, no time is needed
        if (freshCount == 0) {
            return 0;
        }
        int time = -1; // Time counter, starts at -1 to adjust for the initial queue state

        // Perform BFS level-wise
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] pair = queue.poll();
                int r = pair[0];
                int c = pair[1];

                // Try all 4 adjacent directions
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // If within bounds and fresh, make it rotten
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                        queue.offer(new int[] { nr, nc });
                        grid[nr][nc] = 2; // Mark as rotten
                        freshCount--;
                    }
                }
            }
            time++; // One level (minute) completed
        }

        // If there are still fresh oranges, return -1 (impossible to rot all)
        return (freshCount == 0) ? time : -1;
    }
}
