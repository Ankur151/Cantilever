package Lecture4;

class TreNode {
    int val;
    TreNode left, right;

    TreNode(int x) {
        val = x;
        left = right = null;
    }
}

public class LowestAncestorBinaryTree {
    public TreNode lowestCommonAncestor(TreNode root, TreNode p, TreNode q) {
        if (root == null || root == p || root == q) return root;

        TreNode left = lowestCommonAncestor(root.left, p, q);
        TreNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        LowestAncestorBinaryTree tree = new LowestAncestorBinaryTree();
        TreNode root = new TreNode(3);
        root.left = new TreNode(5);
        root.right = new TreNode(1);
        root.left.left = new TreNode(6);
        root.left.right = new TreNode(2);
        root.right.left = new TreNode(0);
        root.right.right = new TreNode(8);

        TreNode lca = tree.lowestCommonAncestor(root, root.left, root.right);
        System.out.println("LCA: " + lca.val);  // Output: 3
    }
}

