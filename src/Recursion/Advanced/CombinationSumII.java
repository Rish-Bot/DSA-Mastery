package Recursion.Advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Given collection of candidate numbers (candidates) and an integer target.Find all unique combinations in candidates where the sum is equal to the target.There can only be one usage of each number in the candidates combination and return the answer in sorted order.
e.g : The combination [1, 1, 2] and [1, 2, 1] are not unique.

Input : candidates = [2, 1, 2, 7, 6, 1, 5] , target = 8
Output : [ [1, 1, 6] , [1, 2, 5] , [1, 7] , [2, 6] ]

Explanation : The combinations sum up to target are
1 + 1 + 6 => 8.
1 + 2 + 5 => 8.
1 + 7 => 8.
2 + 6 => 8.
 */

class CombinationSumII {

    private void combos(
            int index, int[] candidates, int target, List<Integer> curr, List<List<Integer>> ans) {

        if (target == 0) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        if (target < 0 || index == candidates.length) return;

        // taking
        curr.add(candidates[index]);

        combos(index + 1, candidates, target - candidates[index], curr, ans);

        curr.remove(curr.size() - 1);

        // non-taking
        for (int i = index + 1; i < candidates.length; i++) {
            if (candidates[i] != candidates[index]) {
                combos(i, candidates, target, curr, ans);
                break;
            }
        }
    }

    // Main function to find all unique combinations
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // your code goes here

        List<Integer> curr = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);

        combos(0, candidates, target, curr, ans);

        return ans;
    }
}

class Main {
    public static void main(String[] args) {
        CombinationSumII sol = new CombinationSumII();

        // Sample input
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        // Call the combinationSum2 function
        List<List<Integer>> result = sol.combinationSum2(candidates, target);

        // Output the result
        System.out.println("Combinations are: ");
        for (List<Integer> combination : result) {
            for (int num : combination) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}