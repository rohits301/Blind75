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
    // refer NEETCODE
    // T: O(s*t), S: O(s+t), s = no. of nodes in root tree, t = no. of nodes in subRoot tree
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null)
            return true;
        if (root == null)
            return false;

        if (isSameTree(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return (p == null && q == null);
        }

        return p.val == q.val &&
                isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right);
    }
}