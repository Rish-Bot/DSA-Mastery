package Recursion.Advanced;

import java.util.ArrayList;
import java.util.List;

public class ReviseRecursion {

    public static void main(String[] args) {
        System.out.println("Hello - Recursion");

        ReviseRecursion obj = new ReviseRecursion();
        int sum = 3;
        int[] nums = {1, 2, 3, 4};
        System.out.println(obj.combos(sum, nums));
    }

    public List<List<Integer>> combos(int sum, int[] nums) { // target should match
        List<Integer> curr = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();

        subsetI(0, sum, nums, curr, ans);
        return ans;
    }

    // numbers subset matches target
    private void subsetI(int index, int sum, int[] nums, List<Integer> curr, List<List<Integer>> ans) {
        // base conditions
        if (sum == 0) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        if (sum < 0 || nums.length == index) {
            return;
        }
        curr.add(nums[index]);
        // recursion - loop - taken
        subsetI(index + 1, sum - nums[index], nums, curr, ans);
        curr.remove(curr.size() - 1);
        // non-taken
        subsetI(index + 1, sum, nums, curr, ans);

    }
}


