public class Fibonacci {

    // Recursive method to calculate the nth Fibonacci number
    public static int fibonacciRecursive(int n) {
        // Base case: if n is 0 or 1, return n (F(0)=0, F(1)=1)
        if (n <= 1) {
            return n;
        }
        // Recursive call: F(n) = F(n-1) + F(n-2)
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Iterative (non-recursive) method to calculate the nth Fibonacci number
    public static int fibonacciIterative(int n) {
        // Base case: if n is 0 or 1, return n directly
        if (n <= 1) {
            return n;
        }

        // Initialize first two Fibonacci numbers
        int a = 0, b = 1, c = 0;

        // Loop from 2 to n to calculate the Fibonacci number iteratively
        for (int i = 2; i <= n; i++) {
            // Compute the next Fibonacci number
            c = a + b;
            // Update a and b for next iteration
            a = b;
            b = c;
        }

        // Return the computed Fibonacci number
        return c;
    }

    // Main method: entry point of the program
    public static void main(String[] args) {
        // Define the value of n (position in Fibonacci sequence)
        int n = 10; // Example: Find the 10th Fibonacci number

        // Call and print the result of the recursive method
        System.out.println("Recursive Fibonacci of " + n + " is: " + fibonacciRecursive(n));

        // Call and print the result of the iterative method
        System.out.println("Iterative Fibonacci of " + n + " is: " + fibonacciIterative(n));
    }
}

