package Lecture4;

import java.util.*;

public class SerializeAndDeseialize {
    public String serialize(TreeNode root) {
        if (root == null) return "null,";
        return root.val + "," + serialize(root.left) + serialize(root.right);
    }

    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("null")) return null;

        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left = buildTree(queue);
        node.right = buildTree(queue);
        return node;
    }

    public static void main(String[] args) {
        SerializeAndDeseialize codec = new SerializeAndDeseialize();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String serializedTree = codec.serialize(root);
        System.out.println("Serialized: " + serializedTree);

        TreeNode deserializedTree = codec.deserialize(serializedTree);
        System.out.println("Deserialized Root: " + deserializedTree.val);
    }
}

