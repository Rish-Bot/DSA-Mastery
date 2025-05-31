package Recursion;

class isPalindrome {
    // Method to check if a string is a palindrome
    public boolean palindromeCheck(String s) {
        // Start recursion with the whole string
        return isPalindrome(s, 0, s.length() - 1);
    }

    // Helper method to perform the recursive check
    private boolean isPalindrome(String s, int left, int right) {
        // Base Case: If the start index is greater than or equal to the end index, it's a palindrome
        if (left >= right) {
            return true;
        }
        // Check if characters at the current positions are the same
        if (s.charAt(left) != s.charAt(right)) {
            return false; // Characters do not match, so it's not a palindrome
        }
        // Recur for the next set of characters
        return isPalindrome(s, left + 1, right - 1);
    }

    // Main method to test the palindromeCheck function
    public static void main(String[] args) {
        isPalindrome isPalindrome = new isPalindrome();
        System.out.println(isPalindrome.palindromeCheck("hannah")); // Output: true
        System.out.println(isPalindrome.palindromeCheck("aabbaaa")); // Output: false
        System.out.println(isPalindrome.palindromeCheck("aba")); // Output: true
    }
}
