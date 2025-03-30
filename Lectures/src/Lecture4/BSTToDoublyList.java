package Lecture4;

public class BSTToDoublyList {
    TreeNode head = null, prev = null;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        inorder(root);
        head.left = prev;
        prev.right = head; // Make it circular
        return head;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        if (prev != null) {
            prev.right = node;
            node.left = prev;
        } else {
            head = node; // First node becomes head
        }
        prev = node;
        inorder(node.right);
    }

    // Print Doubly Linked List
    private void printDoublyList(TreeNode head) {
        TreeNode temp = head;
        while (temp.right != head) {
            System.out.print(temp.val + " <-> ");
            temp = temp.right;
        }
        System.out.println(temp.val + " (circular)");
    }

    public static void main(String[] args) {
        BSTToDoublyList obj = new BSTToDoublyList();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        TreeNode head = obj.treeToDoublyList(root);
        obj.printDoublyList(head);
    }
}

