package Recursion;
import java.util.*;

class ReverseString {
    /* Recursive function to reverse the
    string character by character */
    private void reverse(Vector<Character> s, int left, int right) {

        // Base case
        if (left >= right) return;

        // Swap characters at left and right positions
        char temp = s.get(left);
        s.set(left, s.get(right));
        s.set(right, temp);

        // Recursive call with updated indices
        reverse(s, left + 1, right - 1);
    }

    // Function to reverse the given string
    public Vector<Character> reverseString(Vector<Character> s) {
        int left = 0;
        int right = s.size() - 1;
        reverse(s, left, right);
        return s;
    }

    // Main function
    public static void main(String[] args) {
        ReverseString solution = new ReverseString();
        Vector<Character> s = new Vector<>();
        s.add('h');
        s.add('e');
        s.add('l');
        s.add('l');
        s.add('o');

        // Function call to reverse the given string
        Vector<Character> reversed = solution.reverseString(s);
        System.out.println(reversed);
    }
}