package Lecture2;

public class TowerOfHanoi {
    // Recursive function to solve Tower of Hanoi
    public static void solveHanoi(int n, char from, char to, char aux) {
        // Base case: If there's only one disk, move it directly
        if (n == 1) {
            System.out.println("Move disk 1: from " + from + " to " + to);
            return;
        }

        // Move the top n-1 disks from "from" to "aux" using "to" as an auxiliary
        solveHanoi(n - 1, from, aux, to);

        // Move the nth (largest) disk from "from" to "to"
        System.out.println("Move disk " + n + " from " + from + " to " + to);

        // Move the n-1 disks from "aux" to "to" using "from" as an auxiliary
        solveHanoi(n - 1, aux, to, from);
    }

    public static void main(String[] args) {
        int n = 3; // Number of disks
        solveHanoi(n, 'A', 'C', 'B');
    }
}



















//    The solution follows recursion with the following steps:
//
//        Move N-1 disks from A to B using C as a helper.
//        Move the Nth disk from A to C.
//        Move N-1 disks from B to C using A as a helper.
//    Solving it gives O(2ⁿ), exponential complexity.
