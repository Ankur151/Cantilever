package Lecture1;

import java.util.HashMap;

public class LongestSubstringKDistinct {
    public static int longestSubstringWithKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;

        // HashMap to store character frequencies in the current window
        HashMap<Character, Integer> charCount = new HashMap<>();
        int left = 0;  // Left pointer of the sliding window
        int maxLength = 0;

        // Expand the right pointer
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);

            // If we exceed k distinct characters, shrink the window from the left
            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);

                // If frequency becomes 0, remove the character from HashMap
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++; // Shrink window
            }

            // Update maxLength if this window is the largest valid window so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(longestSubstringWithKDistinct("eceba", 2));  // Output: 3
        System.out.println(longestSubstringWithKDistinct("aa", 1));  // Output: 2
        System.out.println(longestSubstringWithKDistinct("aabbcc", 2));  // Output: 4
    }
}































//        Step 1: right = 0, Window: ["e"] → Distinct Count: 1 → maxLength = 1
//        Step 2: right = 1, Window: ["ec"] → Distinct Count: 2 → maxLength = 2
//        Step 3: right = 2, Window: ["ece"] → Distinct Count: 2 → maxLength = 3
//        Step 4: right = 3, Window: ["eceb"] → Distinct Count: 3 → Too many distinct → shrink left
//        Step 5: left = 1, Window: ["ceb"] → Distinct Count: 2 → maxLength remains 3
//        Step 6: right = 4, Window: ["ba"] → Distinct Count: 2 → maxLength remains 3
//        Final Answer: **3**