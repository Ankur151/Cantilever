package Lecture2;

import java.util.*;

public class WordBreak {
    // Recursive function to check if string can be segmented
    public static boolean wordBreakHelper(String s, Set<String> wordDict, int start) {
        // Base Case: If we reach the end, return true
        if (start == s.length()) return true;

        // Try breaking the string at every possible index
        for (int end = start + 1; end <= s.length(); end++) {
            String prefix = s.substring(start, end); // Extract substring

            // If prefix is in dictionary and remaining string is also breakable
            if (wordDict.contains(prefix) && wordBreakHelper(s, wordDict, end)) {
                return true;
            }
        }
        return false; // If no valid segmentation found
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakHelper(s, new HashSet<>(wordDict), 0);
    }

    public static void main(String[] args) {
        List<String> wordDict = Arrays.asList("apple", "pen");
        String s = "applepenapple";
        System.out.println(wordBreak(s, wordDict)); // Output: true
    }
}














//    Solution Explanation (Recursive Approach)
//        We try to break s into prefix + remaining substring and check if:
//
//        The prefix exists in the dictionary.
//        The remaining substring can be broken into valid words using recursion.
//
//        Example Walkthrough for "applepenapple":
//
//        Check apple (exists in dictionary), now check "penapple" recursively.
//        Check pen (exists), now check "apple" recursively.
//        "apple" is in dictionary, so return true.

//    Time Complexity:
//        Worst case: O(2ⁿ) (Since we try all possible partitions).
