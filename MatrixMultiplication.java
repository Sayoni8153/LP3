import java.util.Random;

public class MatrixMultiplication {
    static int SIZE = 500;  // Matrix size (you can change for testing)

    public static void main(String[] args) throws InterruptedException {
        int[][] A = generateMatrix(SIZE, SIZE);
        int[][] B = generateMatrix(SIZE, SIZE);
        int[][] C1 = new int[SIZE][SIZE];
        int[][] C2 = new int[SIZE][SIZE];
        int[][] C3 = new int[SIZE][SIZE];

        long start, end;

        // --- 1️⃣ Normal Multiplication ---
        start = System.currentTimeMillis();
        multiplyNormal(A, B, C1);
        end = System.currentTimeMillis();
        System.out.println("Normal multiplication time: " + (end - start) + " ms");

        // --- 2️⃣ Multithreaded: One thread per row ---
        start = System.currentTimeMillis();
        multiplyThreadPerRow(A, B, C2);
        end = System.currentTimeMillis();
        System.out.println("Thread per row time: " + (end - start) + " ms");

        // --- 3️⃣ Multithreaded: One thread per cell ---
        start = System.currentTimeMillis();
        multiplyThreadPerCell(A, B, C3);
        end = System.currentTimeMillis();
        System.out.println("Thread per cell time: " + (end - start) + " ms");
    }

    // ----------------- Normal Multiplication -----------------
    static void multiplyNormal(int[][] A, int[][] B, int[][] C) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }
    }

    // ----------------- Multithreaded (One Thread per Row) -----------------
    static void multiplyThreadPerRow(int[][] A, int[][] B, int[][] C) throws InterruptedException {
        int n = A.length;
        Thread[] threads = new Thread[n];

        for (int i = 0; i < n; i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < n; j++) {
                    int sum = 0;
                    for (int k = 0; k < n; k++) {
                        sum += A[row][k] * B[k][j];
                    }
                    C[row][j] = sum;
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) t.join();
    }

    // ----------------- Multithreaded (One Thread per Cell) -----------------
    static void multiplyThreadPerCell(int[][] A, int[][] B, int[][] C) throws InterruptedException {
        int n = A.length;
        Thread[][] threads = new Thread[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                final int row = i, col = j;
                threads[i][j] = new Thread(() -> {
                    int sum = 0;
                    for (int k = 0; k < n; k++) {
                        sum += A[row][k] * B[k][col];
                    }
                    C[row][col] = sum;
                });
                threads[i][j].start();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                threads[i][j].join();
            }
        }
    }

    // ----------------- Generate Random Matrix -----------------
    static int[][] generateMatrix(int rows, int cols) {
        Random r = new Random();
        int[][] mat = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                mat[i][j] = r.nextInt(10);
        return mat;
    }
}
