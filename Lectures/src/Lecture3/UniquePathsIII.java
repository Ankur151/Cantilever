package Lecture3;

public class UniquePathsIII {
    private int totalPaths = 0, emptyCells = 1; // Empty cells count includes start

    public int uniquePathsIII(int[][] grid) {
        int startX = 0, startY = 0;

        // Find the start position and count empty cells
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == 0) {
                    emptyCells++;
                }
            }
        }

        backtrack(grid, startX, startY, 0);
        return totalPaths;
    }

    private void backtrack(int[][] grid, int x, int y, int visitedCells) {
        // Out of bounds or obstacle
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == -1) {
            return;
        }

        // Reached the end cell
        if (grid[x][y] == 2) {
            if (visitedCells == emptyCells) {
                totalPaths++;
            }
            return;
        }

        // Mark the cell as visited
        grid[x][y] = -1;

        // Move in all 4 directions
        backtrack(grid, x + 1, y, visitedCells + 1);
        backtrack(grid, x - 1, y, visitedCells + 1);
        backtrack(grid, x, y + 1, visitedCells + 1);
        backtrack(grid, x, y - 1, visitedCells + 1);

        // Backtrack: Restore the cell
        grid[x][y] = 0;
    }

    public static void main(String[] args) {
        UniquePathsIII solver = new UniquePathsIII();
        int[][] grid = {
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, -1}
        };
        System.out.println(solver.uniquePathsIII(grid)); // Output: 2
    }
}

