package Recursion.Advanced;

import java.util.ArrayList;
import java.util.List;

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
