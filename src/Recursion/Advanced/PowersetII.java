package Recursion.Advanced;


import java.util.ArrayList;
import java.util.List;

class PowersetII {

    // Main method to test the code
    public static void main(String[] args) {
        PowersetII sol = new PowersetII();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = sol.powerSet(nums);

        // Print the result
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }

    // Helper function to generate all subsets
    private void backtrack(int index, int n, int[] nums, List<Integer> current, List<List<Integer>> ans) {
        // Base case: if the index reaches the length of the array,
        // add the current subset to the answer list
        if (index == n) {
            ans.add(new ArrayList<>(current));
            return;
        }

        // Recursive case: Exclude the current element and move to the next element
        backtrack(index + 1, n, nums, current, ans);

        // Include the current element in the subset and move to the next element
        current.add(nums[index]);
        backtrack(index + 1, n, nums, current, ans);

        // Backtrack: remove the last added element to explore other subsets
        current.remove(current.size() - 1);
    }

    // Main function to generate the power set of the given array
    public List<List<Integer>> powerSet(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();  // List to store all subsets
        List<Integer> current = new ArrayList<>();  // Temporary list to store the current subset
        backtrack(0, nums.length, nums, current, ans);  // Start the recursive process
        return ans;  // Return the list of all subsets
    }
}
