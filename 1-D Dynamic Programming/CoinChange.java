class Solution {
    // BRUTE
    // refer NEETCODE
    // STRIVER soln. is 2D DP, Neetcode's soln. is much simpler and straight-forward
    // NO GREEDY coz. Eg. [1,3,4,5], amount = 11, greedy = 3 (5+5+1), ans = 2 (3+4)
    // T:O(n^amount), S: O(amount)
    // n = size of coins array
    public int coinChange(int[] coins, int amount) {
        int minCoins = dfs(coins, amount);
        return (minCoins >= 1e9) ? -1 : minCoins;
    }

    private int dfs(int[] coins, int amount) {
        if (amount == 0) {
            return 0; // no coin is required to make amount = 0
        }

        int res = (int) 1e9; // min. number of coins
        for (int coin : coins) {
            if (amount - coin >= 0) {
                int numberOfCoins = 1 + dfs(coins, amount - coin);
                res = Math.min(res, numberOfCoins);
            }
        }
        return res;
    }
}

class Solution {
    // BETTER
    // MEMOIZATION, TOP-DOWN DP
    // T:O(n*amount), S: O(amount+1)
    // for every amount, all options of coins are explored atleast once
    // so TIME = O(n*amount)
    // n = size of coins array
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);

        int minCoins = dfs(coins, amount, dp);
        return (minCoins >= 1e9) ? -1 : minCoins;
    }

    private int dfs(int[] coins, int amount, int[] dp) {
        if (amount == 0) {
            return 0; // no coin is required to make amount = 0
        }

        if (dp[amount] != -1) {
            return dp[amount];
        }

        int res = (int) 1e9; // min. number of coins
        for (int coin : coins) {
            if (amount - coin >= 0) {
                res = Math.min(res, 1 + dfs(coins, amount - coin, dp));
            }
        }
        return dp[amount] = res;
    }
}

class Solution {
    // OPTIMAL (TC SAME AS MEMOIZATION, SPACE IS LESS AS NO STACK SPACE REQD.)
    // T:O(n*amount), S: O(amount+1)
    // TABULATION, BOTTOM-UP DP
    // for every amount, all options of coins are explored atleast once
    // so T = n*amount
    // n = size of coins array
    public int coinChange(int[] coins, int amount) {
        // the maximum amount is amount+1, so instead of 1e9 or Integer.MAX_VALUE, this
        // can be considered
        // assume, denomination 1 coin, so max coins required = amount.
        // hence, amount + 1 is the bigger than all possible values
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        // dp[i] = minimum number of coins required to make amount = i.

        // order of the below loops doesn't affect TC
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] = Math.min(dp[j], 1 + dp[j - coins[i]]);
                }
            }
        }
        return (dp[amount] >= amount + 1) ? -1 : dp[amount];
    }
}
