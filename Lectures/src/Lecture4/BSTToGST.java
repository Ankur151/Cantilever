package Lecture4;

public class BSTToGST {
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;

        // Reverse Inorder Traversal (Right -> Node -> Left)
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);

        return root;
    }

    // Helper function to print BST (Inorder)
    private void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        BSTToGST obj = new BSTToGST();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(8);

        TreeNode newRoot = obj.convertBST(root);
        obj.printInorder(newRoot);
    }
}

