package Recursion;

import java.util.ArrayList;
import java.util.List;

class CheckArraySort {
    public boolean isSorted(ArrayList<Integer> nums) {
        if (nums.size() < 2)
            return true;
       return checkSort(nums, 0, 1);
    }
    private boolean checkSort(ArrayList<Integer> nums, int left, int right) {

        if(right >= nums.size())
            return true;

        if (nums.get(left) > nums.get(right))
            return false;

        return checkSort(nums, left+1, right+1);
    }

    // Main method for testing the isSorted function
    public static void main(String[] args) {
        CheckArraySort solution = new CheckArraySort();
        ArrayList<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        boolean result = solution.isSorted(nums);
        System.out.println(result ? "Array is sorted" : "Array is not sorted");
    }
}

