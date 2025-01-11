/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    // refer NEETCODE
    // T: O(V+E)
    // S: O(V+E)
    // APPROACH -
    // DFS
    // 1. maintain a map of oldToNew nodes
    // 2. if node exists in clone, then return node
    // 3. else create a new node and put the mapping as old -> new
    // 4. run dfs for neighbors of node
    public Node cloneGraph(Node node) {
        Map<Node, Node> oldToNew = new HashMap<>();
        return dfs(node, oldToNew);
    }

    private Node dfs(Node node, Map<Node, Node> oldToNew) {

        if (node == null) {
            return null;
        }

        if (oldToNew.containsKey(node)) {
            return oldToNew.get(node);
        }

        Node newNode = new Node(node.val);
        oldToNew.put(node, newNode);

        // for each neighbor of newNode, we call dfs
        // it a node is already there, then, it is added
        // else, newNode is created
        for (Node nbr : node.neighbors) {
            newNode.neighbors.add(dfs(nbr, oldToNew));
        }
        return newNode;
    }
}
