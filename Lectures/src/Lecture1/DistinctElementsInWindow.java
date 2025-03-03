package Lecture1;

import java.util.*;

public class DistinctElementsInWindow {
    public static void countDistinct(int[] arr, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>(); // HashMap to store frequency of elements
        int distinctCount = 0;

        // Step 1: Process the first window
        for (int i = 0; i < k; i++) {
            if (frequencyMap.getOrDefault(arr[i], 0) == 0) {
                distinctCount++; // New distinct element
            }
            frequencyMap.put(arr[i], frequencyMap.getOrDefault(arr[i], 0) + 1);
        }
        System.out.println(distinctCount); // Print distinct count for first window

        // Step 2: Slide the window across the array
        for (int i = k; i < arr.length; i++) {
            // Remove the leftmost element of the previous window
            if (frequencyMap.get(arr[i - k]) == 1) {
                distinctCount--; // Element is completely removed, so decrement count
            }
            frequencyMap.put(arr[i - k], frequencyMap.get(arr[i - k]) - 1);

            // Add the new element for the current window
            if (frequencyMap.getOrDefault(arr[i], 0) == 0) {
                distinctCount++; // New distinct element found
            }
            frequencyMap.put(arr[i], frequencyMap.getOrDefault(arr[i], 0) + 1);

            System.out.println(distinctCount); // Print distinct count for current window
        }
        System.out.println(frequencyMap);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 4, 2, 3};
        int k = 4;
        countDistinct(arr, k);
    }
}
