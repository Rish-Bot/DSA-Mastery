package Recursion;

class Fib {
    public int fib(int n) {
        // Base cases: F(0) = 0, F(1) = 1
        if (n == 0) return 0;
        if (n == 1) return 1;
        // Recursive case: F(n) = F(n-1) + F(n-2)
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        Fib solution = new Fib();
        int n = 5; // Example input
        System.out.println("Fibonacci number at position " + n + " is " + solution.fibonacci(n));
    }
}
