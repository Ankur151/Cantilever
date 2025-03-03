package Lecture1;

import java.util.*;

public class LongestZeroSumSubarray {
    public static int longestZeroSumSubarray(int[] nums) {
        // HashMap to store (prefixSum, first index it appeared)
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int maxLength = 0;
        int sum = 0; // Initialize prefix sum

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // Compute prefix sum

            // If sum is 0, entire subarray from index 0 to i has sum 0
            if (sum == 0) {
                maxLength = i + 1;
            }

            // If the prefix sum is already in the map, that means the subarray
            // between the previous occurrence and the current index has sum 0.
            if (prefixSumMap.containsKey(sum)) {
                // Update maxLength with the largest subarray found so far
                maxLength = Math.max(maxLength, i - prefixSumMap.get(sum));
            } else {
                // Store the first occurrence of this sum
                prefixSumMap.put(sum, i);
            }
        }

        return maxLength; // Return the maximum subarray length found
    }

    public static void main(String[] args) {
        int[] nums1 = {15, -2, 2, -8, 1, 7, 10, 23};
        System.out.println("Longest Zero Sum Subarray Length: " + longestZeroSumSubarray(nums1));

        int[] nums2 = {1, 2, 3, 4, 5};
        System.out.println("Longest Zero Sum Subarray Length: " + longestZeroSumSubarray(nums2));

        int[] nums3 = {1, 3, -2, -1, 2, -3, 2, 3};
        System.out.println("Longest Zero Sum Subarray Length: " + longestZeroSumSubarray(nums3));
    }
}
