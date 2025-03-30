package Lecture3;

import java.util.*;

public class WordSearchII {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;  // Stores the full word at the end of a path
    }

    // Inserts words into Trie
    private static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                node.children.putIfAbsent(ch, new TrieNode());
                node = node.children.get(ch);
            }
            node.word = word;  // Store the full word at the last character
        }
        return root;
    }

    public static List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        TrieNode root = buildTrie(words);

        int rows = board.length, cols = board[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(board, r, c, root, result);
            }
        }
        return new ArrayList<>(result);
    }

    private static void dfs(char[][] board, int r, int c, TrieNode node, Set<String> result) {
        char ch = board[r][c];
        if (!node.children.containsKey(ch)) return; // Prune unnecessary paths

        TrieNode nextNode = node.children.get(ch);
        if (nextNode.word != null) {
            result.add(nextNode.word); // Found a valid word
            nextNode.word = null; // Avoid duplicate addition
        }

        board[r][c] = '#'; // Mark as visited
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}}; // Right, Down, Left, Up
        for (int[] d : directions) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length) {
                dfs(board, nr, nc, nextNode, result);
            }
        }
        board[r][c] = ch; // Restore original value

        // Optimization: Remove leaf nodes
        if (nextNode.children.isEmpty()) {
            node.children.remove(ch);
        }
    }

    public static void main(String[] args) {
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};

        System.out.println("Words Found: " + findWords(board, words));
    }
}

