/**
 * LeetCode Problem: Count Subarrays of Length Three with a Condition
 * Problem Link: https://leetcode.com/problems/count-subarrays-of-length-three-with-a-condition/
 * Difficulty: Easy
 * Date Solved: 2026-08-24
 * Submission Link: https://leetcode.com/problems/count-subarrays-of-length-three-with-a-condition/submissions/1619098719/
 */

class Solution {
    public int countSubarrays(int[] nums) {
        int c=0;
        for(int i=0;i<nums.length-2;i++){
           int sum=nums[i]+nums[i+2];
           if(2*sum==(nums[i+1])){
               c++;
           }
       }
       return c;
    }
}

// Test cases
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: nums = [1,2,3,4,5]
        int[] nums1 = {1, 2, 3, 4, 5};
        int result1 = solution.countSubarrays(nums1);
        System.out.println("Test 1: nums = [1,2,3,4,5] => " + result1); // 2
        
        // Test case 2: nums = [3,2,1]
        int[] nums2 = {3, 2, 1};
        int result2 = solution.countSubarrays(nums2);
        System.out.println("Test 2: nums = [3,2,1] => " + result2); // 0
        
        System.out.println("All test cases completed!");
    }
}
