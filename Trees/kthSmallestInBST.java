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
    // Brute Force (Naive)
    // T: O(height), S: O(n) - space of storing n elements in list
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list.get(k - 1);
    }

    private void helper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }
}

class Solution {
    // BETTER 
    // count and ans are taken as static variables
    // as Java does not support pass by reference
    // T: O(height), S: O(n) - auxillary space
    int count = 0;
    int ans = 0;

    public int kthSmallest(TreeNode root, int k) {
        helper(root, k);
        return ans;
    }

    private void helper(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        helper(root.left, k);
        count += 1;
        if (count == k) {
            ans = root.val;
            return;
        }
        helper(root.right, k);
    }
}
class Solution {
    // OPTIMAL
    // refer STRIVER
    // Morris Traversal - inorder, only change, instead of adding to list
    // we increase count and store the value in ans
    // T: O(n), S: O(1)
    public int kthSmallest(TreeNode root, int k) {
        int count = 0, ans = 0;
        
        TreeNode curr = root;
        while(curr != null){
            if(count == k){
                break;
            }

            if(curr.left == null){
                ans = curr.val;
                count++;
                curr = curr.right;
            } else {
                TreeNode prev = curr.left;
                while(prev.right != null && prev.right != curr){
                    prev = prev.right;
                }
                if(prev.right == null){
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    prev.right = null;
                    ans = curr.val;
                    count++;
                    curr = curr.right;
                }
            }
        }
        return ans;
    }
}