/**
 * LeetCode Problem: Minimum Operations to Make Array Sum Divisible By K
 * Problem Link: https://leetcode.com/problems/minimum-operations-to-make-array-sum-divisible-by-k/
 * Difficulty: Easy
 * Date Solved: 2026-08-12
 * Submission Link: https://leetcode.com/problems/minimum-operations-to-make-array-sum-divisible-by-k/submissions/1607611541/
 */

class Solution {
    public int minOperations(int[] nums, int k) {
        int sum=0;
        int c=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        c=sum%k;
        return c;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: nums = [1,2,3,4,5], k = 3
        int[] nums1 = {1, 2, 3, 4, 5};
        int result1 = solution.minOperations(nums1, 3);
        System.out.println("Test 1: nums = [1,2,3,4,5], k = 3 => " + result1); 
        
        // Test case 2: nums = [5,5,5,5], k = 5
        int[] nums2 = {5, 5, 5, 5};
        int result2 = solution.minOperations(nums2, 5);
        System.out.println("Test 2: nums = [5,5,5,5], k = 5 => " + result2);
        
        System.out.println("All test cases completed!");
    }
}
