package Recursion.Advanced;

/*
Given an integer array nums, which can have duplicate entries, provide the power set.
Duplicate subsets cannot exist in the solution set.
Return the answer in any sequence.

example:
Input : nums = [1, 2, 2]
Output : [ [ ] , [1] , [1, 2] , [1, 2, 2] , [2] , [2, 2] ]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SubsetsII {
    public static void main(String[] args) {
        SubsetsII sol = new SubsetsII();
        int[] nums = {1, 2, 2};  // Example input
        List<List<Integer>> result = sol.subsetsWithDup(nums);

        // Print the resulting subsets
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }

    private void combos(int index, int[] nums, List<Integer> curr, List<List<Integer>> ans) {

        if (index == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        curr.add(nums[index]);
        // taking
        combos(index + 1, nums, curr, ans);
        curr.remove(curr.size() - 1);

        int nextIndex = index + 1;
        while (nextIndex < nums.length && nums[nextIndex] == nums[index]) {
            nextIndex++;
        }
        //non-taking
        combos(nextIndex, nums, curr, ans);

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //your code goes here

        List<Integer> curr = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        combos(0, nums, curr, ans);

        return ans;
    }
}

