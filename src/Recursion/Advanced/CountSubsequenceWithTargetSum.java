package Recursion.Advanced;

/*
Question:
Given an array nums and an integer k.Return the number of non-empty subsequences of nums such that the sum of all elements in the subsequence is equal to k.

Example;
Input : nums = [4, 9, 2, 5, 1] , k = 10
Output : 2
Explanation : The possible subsets with sum k are [9, 1] , [4, 5, 1].

Input : nums = [4, 2, 10, 5, 1, 3] , k = 5
Output : 3
Explanation : The possible subsets with sum k are [4, 1] , [2, 3] , [5].

Constraints:
1 <= nums.length <= 20
1 <= nums[i] <= 100
1 <= k <= 2000
 */


//Sol
class CountSubsequenceWithTargetSum {
    // Main function to test the solution
    public static void main(String[] args) {
        CountSubsequenceWithTargetSum sol = new CountSubsequenceWithTargetSum();
        int[] nums = {1, 2, 3, 4, 1, 3};
        int target = 5;
        System.out.println("Number of subsequences with target sum " + target + ": "
                + sol.countSubsequenceWithTargetSum(nums, target));
    }

    // Helper function to count subsequences
    // with the target sum
    private int count(int index, int[] nums, int k, int out, int sum) {
        // All base conditions.
        if (sum == k) {
            out += 1;
            sum = 0;
            return out;
        }

        if (k < 0) {
            return out;
        }

        if (index == nums.length) {
            if (sum == k)
                out += 1;
            return out;
        }

        // add both the recursive loops.
        return count(index + 1, nums, k, out, sum) + count(index + 1, nums, k, out, sum + nums[index]);

    }

    // Function to start counting subsequences
    public int countSubsequenceWithTargetSum(int[] nums, int target) {
        return count(0, nums, target, 0, 0);
    }
}

