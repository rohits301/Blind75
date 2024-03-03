/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // Brute/Better/Optimal
    // NEETCODE and STRIVER
    // T: O(n), S: O(height)
    public int maxPathSum(TreeNode root) {
        int[] res = { Integer.MIN_VALUE };
        int val = maxPathSum(root, res);
        System.out.println(val);
        return res[0];
    }

    private int maxPathSum(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }

        int leftSum = maxPathSum(root.left, res);
        int rightSum = maxPathSum(root.right, res);
        leftSum = Math.max(leftSum, 0);
        rightSum = Math.max(rightSum, 0);

        res[0] = Math.max(res[0], leftSum + rightSum + root.val); // the sum considering splitting at root node
        return root.val + Math.max(leftSum, rightSum); // the sum without any splitting
    }
}