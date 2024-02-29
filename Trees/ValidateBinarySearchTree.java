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
    // Better
    // T: O(n), S: O(n) - space for storing all values in arraylist
    // using property of BST that inorder is sorted
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        isBSTHelper(root, list);

        int prev = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= prev) {
                return false;
            }
            prev = list.get(i);
        }
        return true;
    }

    private void isBSTHelper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        isBSTHelper(root.left, list);
        list.add(root.val); // inorder
        isBSTHelper(root.right, list);
    }
}

class Solution {
    // refer STRIVER
    // OPTIMAL
    // T: O(2*n), S: O(height) - auxilary space
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long min, long max){
        if(root == null){
            return true;
        }
        if(root.val >= max || root.val <= min){
            return false;
        }

        return isValidBST(root.left, min, root.val) && 
               isValidBST(root.right, root.val, max);
    }
}
