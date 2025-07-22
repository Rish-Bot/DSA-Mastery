package Recursion.Advanced;

class CheckSubsequenceSum {
    // Main method to test the solution
    public static void main(String[] args) {
        CheckSubsequenceSum sol = new CheckSubsequenceSum();
        int[] nums = {1, 2, 3, 4};
        int target = 5;
        System.out.println(sol.checkSubsequenceSum(nums, target)); // Expected output: true
    }

    // This method recursively checks for the subsequence with the given sum
    private boolean checkSum(int index, int[] nums, int sum) {

        if (sum == 0)
            return true;
        if (sum < 0)
            return false;
        if (index == nums.length)
            return sum == 0;

        return (checkSum(index + 1, nums, sum - nums[index])) || (checkSum(index + 1, nums, sum));
    }

    // This method initiates the recursive process
    public boolean checkSubsequenceSum(int[] nums, int target) {
        return checkSum(0, nums, target);
    }
}

