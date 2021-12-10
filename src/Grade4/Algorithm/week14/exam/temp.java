package Grade4.Algorithm.week14.exam;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Stack;

// Java program to implement
// the above approach
import java.util.*;
class temp {
    // Direction vectors
    static int dRow[] = { -1, 0, 1, 0 };
    static int dCol[] = { 0, 1, 0, -1 };

    // Function to check if current
// position is valid or not
    static boolean isValid(boolean[][] vis,
                           int row, int col,
                           int COL, int ROW)
    {
        // Check if the cell is out of bounds
        if (row < 0 || col < 0 || col > COL - 1
                || row > ROW - 1)
            return false;

        // Check if the cell is visited or not
        if (vis[row][col] == true)
            return false;

        return true;
    }

    // Utility function to print matrix
// elements using DFS Traversal
    static void DFSUtil(int row, int col,
                        int[][] grid,
                        boolean[][] vis,
                        int M, int N)
    {

        // Mark the current cell visited
        vis[row][col] = true;

        // Print the element at the cell
        System.out.print(grid[row][col] + " ");

        // Traverse all four adjacent
        // cells of the current element
        for (int i = 0; i < 4; i++) {

            int x = row + dRow[i];
            int y = col + dCol[i];

            // Check if x and y is valid index or not
            if (isValid(vis, x, y, M, N)) DFSUtil(x, y, grid, vis, M, N);
        }
    }
    // Function to print the matrix elements
    static void DFS(int row, int col,
                    int[][] grid,
                    int M, int N)
    {
        // Initialize a visiting matrix
        boolean[][] vis = new boolean[M + 1][N + 1];
        for(int i = 0; i < M + 1; i++)
        {
            for(int j = 0; j < N + 1; j++)
            {
                vis[i][j] = false;
            }
        }

        // Function call to print matrix
        // elements by DFS traversal
        DFSUtil(0, 0, grid, vis, M, N);
    }

    // Driver Code
    public static void main(String args[])
    {
        // Given matrix
        int[][] grid = { { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };

        // Row of the matrix
        int M = grid.length;

        // Column of the matrix
        int N = grid[0].length;

        DFS(0, 0, grid, M, N);

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%3d", grid[i][j]);
            }
            System.out.println();
        }
    }

}



