package Recursion.Advanced;

import java.util.ArrayList;
import java.util.List;
/*
Given an array nums of n integers.Return sum of all subsets of the array nums.
Output can be printed in any order.

ex:
Input : nums = [2, 3]
Output : [0, 2, 3, 5]

Explanation :
When no elements is taken then Sum = 0.
When only 2 is taken then Sum = 2.
When only 3 is taken then Sum = 3.
When element 2 and 3 are taken then sum = 2+3 = 5.


 */

class SubSetI {
    public static void main(String[] args) {
        SubSetI sol = new SubSetI();
        int[] nums = {1, 2, 3};
        List<Integer> result = sol.subsetSums(nums);

        System.out.println("Subset sums are: " + result);
    }

    public List<Integer> subsetSums(int[] nums) {
        //your code goes here
        List<Integer> ans = new ArrayList<>();
        subset(0, nums, 0, ans);
        return ans;
    }

    private void subset(int index, int[] nums, int sum, List<Integer> ans) {

        if (index == nums.length) {
            ans.add(sum);
            return;
        }

        // not-taking
        subset(index + 1, nums, sum, ans);
        // taking
        subset(index + 1, nums, sum + nums[index], ans);
    }
}

