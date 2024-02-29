/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 class Solution {
    // BRUTE FORCE
    // refer STRIVER's LCA of binary tree video
    // using the Binary tree approach on BST
    // T: O(height), S: O(height) - space is recursion auxillary space
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            // both are non-null
            return root;
        }
    }
}

class Solution {
    // BETTER
    // refer STRIVER
    // Recursive
    // T: O(height), S: O(height), height = log n in case of BST
    // sc is of recursion auxillary space
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return root;
        }

        int curr = root.val;
        if(curr < p.val && curr < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        if(curr > p.val && curr > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }
}

class Solution {
    // OPTIMAL
    // refer NEETCODE
    // Iterative
    // T: O(height), S: O(1), height = log n in case of BST
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }

        TreeNode curr = root;
        while (curr != null) {
            if (curr.val < p.val && curr.val < q.val) {
                curr = curr.right;
            } else if (curr.val > p.val && curr.val > q.val) {
                curr = curr.left;
            } else {
                return curr;
            }
        }
        return curr; // case when curr is null
    }
}