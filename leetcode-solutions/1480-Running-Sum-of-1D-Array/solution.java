/**
 * LeetCode Problem: Running Sum of 1D Array
 * Problem Link: https://leetcode.com/problems/running-sum-of-1d-array/
 * Difficulty: Easy
 * Date Solved: 2026-08-14
 * Submission Link: https://leetcode.com/problems/running-sum-of-1d-array/submissions/1610119186/
 */

class Solution {
    public int[] runningSum(int[] nums) {
        int sum=nums[0];
        int[] arr=new int[nums.length];
        for(int i=0;i<nums.length-1;i++){
            arr[i]=sum;
            sum=sum+nums[i+1];
        }
        arr[nums.length-1]=sum;
        return arr;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: nums = [1,2,3,4]
        int[] nums1 = {1, 2, 3, 4};
        int[] result1 = solution.runningSum(nums1);
        System.out.print("Test 1: nums = [1,2,3,4] => [");
        for (int i = 0; i < result1.length; i++) {
            System.out.print(result1[i] + (i < result1.length - 1 ? ", " : ""));
        }
        System.out.println("]"); // [1, 3, 6, 10]
        
        // Test case 2: nums = [1,1,1,1,1]
        int[] nums2 = {1, 1, 1, 1, 1};
        int[] result2 = solution.runningSum(nums2);
        System.out.print("Test 2: nums = [1,1,1,1,1] => [");
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i] + (i < result2.length - 1 ? ", " : ""));
        }
        System.out.println("]"); // [1, 2, 3, 4, 5]
        
        // Test case 3: nums = [3,1,2,10,1]
        int[] nums3 = {3, 1, 2, 10, 1};
        int[] result3 = solution.runningSum(nums3);
        System.out.print("Test 3: nums = [3,1,2,10,1] => [");
        for (int i = 0; i < result3.length; i++) {
            System.out.print(result3[i] + (i < result3.length - 1 ? ", " : ""));
        }
        System.out.println("]"); // [3, 4, 6, 16, 17]
        
        System.out.println("All test cases completed!");
    }
}
