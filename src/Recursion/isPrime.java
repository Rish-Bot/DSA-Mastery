package Recursion;

class PrimeNo {
    // Main method for testing the checkPrime function
    public static void main(String[] args) {
        PrimeNo solution = new PrimeNo();
        int num = 71;
        boolean result = solution.checkPrime(num);
        System.out.println(result);
    }

    public boolean checkPrime(int num) {

        // Base conditions.
        if (num < 2) return false;

        return isPrime(num, 2);
    }

    private boolean isPrime(int num, int curr) {

        if (curr > Math.sqrt(num))
            return true;

        if (num % curr == 0)
            return false;

        return isPrime(num, curr+1);
    }
}
