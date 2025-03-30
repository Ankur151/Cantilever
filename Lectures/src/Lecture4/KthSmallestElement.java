package Lecture4;

public class KthSmallestElement {
    private int count = 0;
    private int result = -1;

    public int kthSmallest(TreeNode root, int k) {
        inOrderTraversal(root, k);
        return result;
    }

    private void inOrderTraversal(TreeNode root, int k) {
        if (root == null) return;

        inOrderTraversal(root.left, k);  // Visit left subtree
        count++;
        if (count == k) {  // When count reaches k, store result
            result = root.val;
            return;
        }
        inOrderTraversal(root.right, k);  // Visit right subtree
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(4);

        KthSmallestElement tree = new KthSmallestElement();
        System.out.println("K-th Smallest: " + tree.kthSmallest(root, 2));  // Output: 2
    }
}

