/**
 * LeetCode Problem: Build Array from Permutation
 * Problem Link: https://leetcode.com/problems/build-array-from-permutation/
 * Difficulty: Easy
 * Date Solved: 2026-10-13
 * Submission Link: https://leetcode.com/problems/build-array-from-permutation/submissions/1627235703/
 */

class Solution {
    public int[] buildArray(int[] nums) {
        int[] ans=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            ans[i]=nums[nums[i]];
        }
        return ans;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: nums = [0,2,1,5,3,4]
        int[] nums1 = {0, 2, 1, 5, 3, 4};
        int[] result1 = solution.buildArray(nums1);
        System.out.print("Test 1: nums = [0,2,1,5,3,4] => [");
        for (int i = 0; i < result1.length; i++) {
            System.out.print(result1[i] + (i < result1.length - 1 ? ", " : ""));
        }
        System.out.println("]"); // [0,1,2,4,5,3]
        
        // Test case 2: nums = [5,0,1,2,3,4]
        int[] nums2 = {5, 0, 1, 2, 3, 4};
        int[] result2 = solution.buildArray(nums2);
        System.out.print("Test 2: nums = [5,0,1,2,3,4] => [");
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i] + (i < result2.length - 1 ? ", " : ""));
        }
        System.out.println("]"); // [4,5,0,1,2,3]
        
        System.out.println("All test cases completed!");
    }
}
