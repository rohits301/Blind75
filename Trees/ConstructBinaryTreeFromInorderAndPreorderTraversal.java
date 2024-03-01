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
    // refer STRIVER
    // Brute/Better/Optimal
    // T: O(n), S: O(n) - visiting all elements of array so, tc: O(n)
    // space for map - O(n) + auxillary space - O(n), so sc: O(n)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1,
                         inorder, 0, inorder.length - 1, map);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, 
                               int[] inorder, int inStart, int inEnd, Map<Integer, Integer> map) {

        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);

        int inRoot = map.get(preorder[preStart]); // rootIndexInInorder
        int numsLeft = inRoot - inStart; // no. of elements in left subtree

        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft,
                              inorder, inStart, inRoot - 1, map);
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,
                               inorder, inRoot + 1, inEnd, map);

        return root;
    }
}