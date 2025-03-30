package Lecture4;

import java.util.HashMap;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
        left = right = null;
    }
}

public class ConstructTree {
    private int preorderIndex = 0;
    private HashMap<Integer, Integer> inorderMap = new HashMap<>();

    // Build Tree function
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Store indices of inorder elements in HashMap for quick lookup
        for (int i = 0; i < inorder.length; i++)
            inorderMap.put(inorder[i], i);
        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int left, int right) {
        if (left > right) return null;  // Base condition

        // Pick current root from preorder
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // Find index of root in inorder
        int inorderIndex = inorderMap.get(rootValue);

        // Recursively construct left and right subtrees
        root.left = build(preorder, left, inorderIndex - 1);
        root.right = build(preorder, inorderIndex + 1, right);

        return root;
    }

    public static void main(String[] args) {
        ConstructTree tree = new ConstructTree();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = tree.buildTree(preorder, inorder);
        System.out.println("Root of Constructed Tree: " + root.val);  // Output: 3
    }
}

