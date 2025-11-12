public class NQueensWithFirstPlaced {

    static int N; // Size of the chessboard

    // Function to print the board
    static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if it's safe to place a queen at board[row][col]
    static boolean isSafe(int[][] board, int row, int col) {
        int i, j;

        // Check this column on upper side
        for (i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;

        // Check upper left diagonal
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Check upper right diagonal
        for (i = row, j = col; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    // Recursive function to solve N-Queens using backtracking
    static boolean solveNQueens(int[][] board, int row) {
        // Base case: all queens are placed
        if (row == N)
            return true;

        // Try placing queen in all columns of this row
        for (int col = 0; col < N; col++) {
            if (board[row][col] == 1) { 
                // Skip already placed queen (first one)
                if (solveNQueens(board, row + 1))
                    return true;
            } else if (isSafe(board, row, col)) {
                // Place the queen
                board[row][col] = 1;

                // Recur to place rest
                if (solveNQueens(board, row + 1))
                    return true;

                // Backtrack if not a solution
                board[row][col] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        N = 8; // Change N for different board sizes
        int[][] board = new int[N][N];

        // Place first Queen manually — example: first row, first column
        board[0][0] = 1;

        // Solve starting from row 1 since first queen is already placed
        if (solveNQueens(board, 1)) {
            System.out.println("Final N-Queens solution:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for N = " + N);
        }
    }
}

