/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // refer STRIVER
    // Using Level Order Traversal
    // T: O(n), S: O(n)
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode removeNode = queue.poll();
                if (removeNode == null) {
                    res.append("null#");
                    continue;
                }
                res.append(removeNode.val).append("#");
                queue.offer(removeNode.left);
                queue.offer(removeNode.right);
            }
        }
        // delete extra # at the end
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }
        String[] values = data.split("#");

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        queue.offer(root);

        for (int i = 1; i < values.length; i++) {
            TreeNode removeNode = queue.poll();
            if (!values[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                removeNode.left = left;
                queue.offer(left);
            }
            if (!values[++i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                removeNode.right = right;
                queue.offer(right);
            }
        }
        return root;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));


public class Codec {
    // refer NEETCODE
    // using Preorder traversal
    // T: O(n), S: O(n) - recursion stack space
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        buildString(root, res);
        return res.toString();
    }

    private void buildString(TreeNode root, StringBuilder res) {
        if (root == null) {
            res.append("null").append("#");
            return;
        }
        res.append(root.val).append("#");
        buildString(root.left, res);
        buildString(root.right, res);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") {
            return null;
        }
        String[] values = data.split("#");
        TreeNode root = buildTree(values, new int[1]);
        return root;
    }

    private TreeNode buildTree(String[] values, int[] idx) {
        if (values[idx[0]].equals("null")) {
            idx[0]++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(values[idx[0]]));
        idx[0]++;
        node.left = buildTree(values, idx);
        node.right = buildTree(values, idx);
        return node;
    }
}