package Recursion.Advanced;

import java.util.ArrayList;
import java.util.List;

class GenerateParanthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        // Start the recursive generation with initial values
        generate(0, 0, n, "", result);
        return result;
    }

    private void generate(int open, int close, int n, String current, List<String> result) {

        // base condition
        if ((open + close == 2 * n) && open == close) {
            result.add(current);
            return;
        }

        if (open > n)
            return;

        // Open braces
        generate(n, open+1, close, current+"(", result);

        // close braces
        if (open > close) {
            generate(n, open, close+1, current + ")", result);
        }
    }

    public static void main(String[] args) {
        GenerateParanthesis sol = new GenerateParanthesis();
        int n = 4; // Example input
        List<String> result = sol.generateParenthesis(n);

        System.out.println("All combinations of balanced parentheses for n = " + n + " are:");
        for (String combination : result) {
            System.out.println(combination);
        }
    }
}
