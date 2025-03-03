package Lecture1;

import java.util.HashMap;

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";

        // Map to store the frequency of characters in t
        HashMap<Character, Integer> tCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            tCount.put(c, tCount.getOrDefault(c, 0) + 1);
        }

        // Sliding window variables
        int left = 0, right = 0, minLength = Integer.MAX_VALUE, start = 0;
        int requiredChars = tCount.size();  // Number of distinct characters to match
        int matchedChars = 0;  // Number of characters matched so far

        // Map to track window characters
        HashMap<Character, Integer> windowCount = new HashMap<>();

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            windowCount.put(rightChar, windowCount.getOrDefault(rightChar, 0) + 1);

            // If the current character count matches that in t, increase matchedChars
            if (tCount.containsKey(rightChar) && windowCount.get(rightChar).intValue() == tCount.get(rightChar).intValue()) {
                matchedChars++;
            }

            // Try to shrink the window if all required characters are matched
            while (matchedChars == requiredChars) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;
                }

                // Remove left character from window
                char leftChar = s.charAt(left);
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);

                // If removing the left character breaks a match, decrease matchedChars
                if (tCount.containsKey(leftChar) && windowCount.get(leftChar) < tCount.get(leftChar)) {
                    matchedChars--;
                }

                left++; // Shrink window
            }

            right++; // Expand window
        }

        return (minLength == Integer.MAX_VALUE) ? "" : s.substring(start, start + minLength);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));  // Output: "BANC"
        System.out.println(minWindow("a", "a"));  // Output: "a"
        System.out.println(minWindow("a", "aa"));  // Output: ""
    }
}
