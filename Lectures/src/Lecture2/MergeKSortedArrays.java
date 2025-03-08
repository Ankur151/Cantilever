package Lecture2;

import java.util.PriorityQueue;
import java.util.Arrays;

public class MergeKSortedArrays {
    // A helper class to store elements along with their array and index position
    static class Node implements Comparable<Node> {
        int value;  // The value of the element
        int arrayIndex;  // Index of the array it belongs to
        int elementIndex;  // Index of the element in the array

        // Constructor
        public Node(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        // Compare function for PriorityQueue (Min Heap)
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.value, other.value);  // Min Heap based on value
        }
    }

    public static int[] mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();  // Min Heap
        int totalElements = 0;

        // Step 1: Insert the first element of each array into the heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.add(new Node(arrays[i][0], i, 0));
                totalElements += arrays[i].length;  // Count total elements
            }
        }

        int[] result = new int[totalElements];  // Output array to store merged result
        int index = 0;

        // Step 2: Extract min, insert next element from the same array
        while (!minHeap.isEmpty()) {
            Node current = minHeap.poll();  // Get the smallest element
            result[index++] = current.value;  // Store in result array

            // Get the next element from the same array, if available
            if (current.elementIndex + 1 < arrays[current.arrayIndex].length) {
                int nextValue = arrays[current.arrayIndex][current.elementIndex + 1];
                minHeap.add(new Node(nextValue, current.arrayIndex, current.elementIndex + 1));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arrays = {
                {2, 6, 12},
                {1, 9},
                {23, 34, 90, 200}
        };

        int[] mergedArray = mergeKSortedArrays(arrays);
        System.out.println(Arrays.toString(mergedArray));
    }
}
