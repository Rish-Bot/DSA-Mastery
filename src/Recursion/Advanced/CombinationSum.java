package Recursion.Advanced;

import java.util.ArrayList;
import java.util.List;

class CombinationSum {
    // Main method to test the solution
    public static void main(String[] args) {
        CombinationSum sol = new CombinationSum();
        int[] candidates = {2, 3, 6, 7};
        int target = 8;
        List<List<Integer>> result = sol.combinationSum(candidates, target);
        System.out.println(result);
    }

    private void combos(int index, int[] candidates, int target,
                        List<Integer> curr, List<List<Integer>> out) {

        if (target == 0) {
            out.add(new ArrayList<>(curr)); //deep copy is needed like struct.
            return;
        }

        if (target < 0 || index == candidates.length) {
            return;
        }
        // Include the curr index.
        curr.add(candidates[index]);
        combos(index, candidates, target - candidates[index], curr, out);
        curr.remove(curr.size() - 1);

        // Exclude and move to next index.
        combos(index + 1, candidates, target, curr, out);

    }

    // Main function to find all unique combinations of candidates that sum to the target
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<Integer> curr = new ArrayList<>();
        List<List<Integer>> out = new ArrayList<>();

        combos(0, candidates, target, curr, out);

        return out;
    }
}
